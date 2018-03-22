#include <sys/types.h>
#include "cpe.h"
#include "capgw.h"
//#include "perrno.h"
#include <pthread.h>

//BOOL tlSetGateway(char *host,u_int port);

static u_int DBGW_PORT                  =0;     //capture music server port
static char  DBGW_Address[MAX_ADDR_LEN] = "";   //capture music server ip
static int  DBGW_Timeout_Sec            = 5;
static int  DBGW_Timeout_uSec           = 0;    //micro-second

#define MSG_TIME_TAG        "<CAPTURE><TIME>"
#define MSG_VM_TAG          "</TIME><VMIP>"
#define MSG_CALLING_TAG     "</VMIP><CALLING>"
#define MSG_CALLED_TAG      "</CALLING><CALLED>"
#define MSG_TONEID_TAG      "</CALLED><TONEID>"
#define MSG_DTMF_TAG        "</TONEID><DTMF>"
#define MSG_CLOSE           "</DTMF></CAPTURE>\r\n"


/*typedef struct RecvMsg
{
    char ringid[20];
} sRecvMsg;
*/
typedef struct SendMsg
{
    char body[512];
    int  len;
} sSendMsg;
/*
typedef struct tUserInfo
{
    char VM[20];
    char CALLED[20];
    char CALLER[20];
    char TONEID[10];
    char DTMF[10];
} UserInfo;
*/

int GW_Disconnect(SOCKET_HANDLE *g_hGWSocket)
{
    if (*g_hGWSocket != -1)
    {
        Socket_Destroy(*g_hGWSocket);
        *g_hGWSocket = -1;
#if defined(DEBUG)
        printf("DBGW: disconnected\n");
#endif
    }
    return 0;
}

BOOL GW_Connect(SOCKET_HANDLE *g_hGWSocket)
{
    int status;
    int err=sizeof(int);
    socklen_t len=sizeof(socklen_t);

    //int nBlock=0;
    fd_set fdsRead;
    fd_set fdsWrite;

    struct timeval hTimeout;

    if (*g_hGWSocket != -1)
    {
        GW_Disconnect((SOCKET_HANDLE*)g_hGWSocket);
    }

    /* create socket */
    *g_hGWSocket = Socket_Create(SOCK_STREAM, NULL, 0);

    if (*g_hGWSocket == -1)
    {
#if defined(DEBUG)
        printf("FEP: create client socket failed (%s)\n", strerror(errno));
#endif
        return FALSE;
    }

    ASSERT(*g_hGWSocket != -1);

    fcntl(*g_hGWSocket, F_SETFL, O_NONBLOCK );    //conect non-block mode
    //ioctl(*g_hGWSocket, FIONBIO, &nBlock);

#if defined(DEBUG)
        printf("GWDB0: fcntl errno %s\n", strerror(errno));
#endif

    if (Socket_Connect(*g_hGWSocket, DBGW_Address, DBGW_PORT))
    {
#if defined(DEBUG)
        printf("GWDB1: connecting to DBGW server %s:%u\n", DBGW_Address, DBGW_PORT);
#endif
        return TRUE;
    }else if(errno ==EINPROGRESS || errno == 0)
    {
       //printf("\nSocket in progress");
       FD_ZERO(&fdsRead);
       FD_ZERO(&fdsWrite);
       FD_SET(*g_hGWSocket, &fdsRead);
       FD_SET(*g_hGWSocket, &fdsWrite);

       hTimeout.tv_sec = DBGW_Timeout_Sec;
       hTimeout.tv_usec = DBGW_Timeout_uSec;

       if (select (*g_hGWSocket + 1, &fdsRead, &fdsWrite, 0, &hTimeout) == 0)
       {
        // problems, bail out...

#if defined(DEBUG)
            printf("DBGW2: connect to DBGW server failed. ");
            printf("Error in GW_connect() %s\n", strerror(errno));
#endif
            Socket_Destroy(*g_hGWSocket);
            *g_hGWSocket = -1;
            return FALSE;
       }
       else if (FD_ISSET (*g_hGWSocket, &fdsRead) || FD_ISSET (*g_hGWSocket, &fdsWrite))
       {
            struct sockaddr_in addr;
            int len = sizeof addr;
         // Check to see if we can determine our peer's address.
            if (getpeername (*g_hGWSocket, (struct sockaddr *) &addr, &len) == -1)
            {
                // Error, fd is not connected, errno contains reason.

#if defined(DEBUG)
                printf("DBGW3: connect to DBGW server failed. ");
                printf("Error in GW_connect() %s\n",  strerror(errno));
#endif
                Socket_Destroy(*g_hGWSocket);
                *g_hGWSocket = -1;
                return FALSE;
            }
            else
            {
#if defined(DEBUG)
                printf("GWDB4: connecting to DBGW server %s:%u\n", DBGW_Address, DBGW_PORT);
#endif
                return TRUE;
            }

       }
    }else
    {
        if((status=getsockopt(*g_hGWSocket, SOL_SOCKET, SO_ERROR, &err, &len)) < 0)
        {
#if defined(DEBUG)
            printf("DBGW: connect to DBGW server failed. sec=%d usec=%d host=%s port=%d .",hTimeout.tv_sec,hTimeout.tv_usec,DBGW_Address,DBGW_PORT);
            printf("Error in GW_connect().5 %s\n", strerror(errno));
            printf("Error in GW_connect().5 (status=%d)(err=%d)%s\n", status,err,strerror(err));
#endif
            Socket_Destroy(*g_hGWSocket);
            *g_hGWSocket = -1;
            return FALSE;
        }

        if (err != 0)
        {
#if defined(DEBUG)
            printf("DBGW: connect to DBGW server failed. sec=%d usec=%d host=%s port=%d .",hTimeout.tv_sec,hTimeout.tv_usec,DBGW_Address,DBGW_PORT);
            printf("Error in GW_connect().6 %s\n", strerror(errno));
            printf("Error in GW_connect().6 (status=%d)(err=%d)%s\n", status,err,strerror(err));
#endif
            Socket_Destroy(*g_hGWSocket);
            *g_hGWSocket = -1;
            return FALSE;
        }


    }

    /* must destroy the socket before next connect */
#if defined(DEBUG)
        printf("GWDB.7: connecting to DBGW server %s:%u\n", DBGW_Address, DBGW_PORT);
#endif
        return TRUE;
}

//int GW_Send(SOCKET_HANDLE *g_hGWSocket,char *lpSendMSG,int nCount,char *lpRecvMsg)
int GW_Send(SOCKET_HANDLE *g_hGWSocket,char *lpSendMSG,int nCount)
{
    char g_hSendBuf[MAX_SEND_BUF+1];
    memcpy(g_hSendBuf, lpSendMSG , nCount);

    int nRet;
    //fd_set fdsRead;
    //struct timeval hTimeout;
    //errno=0;
    if ((nRet=Socket_Send(*g_hGWSocket, g_hSendBuf, strlen(g_hSendBuf))) != strlen(g_hSendBuf))
    {
#if defined(DEBUG)
        printf("CAPGW: GW_Send send fail. nRet=%d.\n",nRet);
        printf("Error in GW_Send() %s\n", strerror(errno));
        sendERRNO(errno);
#endif
        return TL_ERROR_SEND_FAIL;
    }

    /* set timeout */
/*    FD_ZERO(&fdsRead);
    FD_SET(*g_hGWSocket, &fdsRead);

    hTimeout.tv_sec = DBGW_Timeout_Sec;
    hTimeout.tv_usec = DBGW_Timeout_uSec;

    nRet = select(*g_hGWSocket+1, &fdsRead, 0, 0, &hTimeout);

    if (nRet == 1 && FD_ISSET(*g_hGWSocket, &fdsRead))
    {
        if (Socket_Receive(*g_hGWSocket, lpRecvMsg, sizeof(sRecvMsg)) <= 0)
        {
#if defined(DEBUG)
            printf("DBGW: Receive socket error\n");
#endif
            nRet = TL_ERROR_RECEIVE_FAIL;
        }
    }
*/
    return nRet;
}

BOOL FormatTime(char *lt){
    time_t t;
    struct tm *val;

    t=time(NULL);
    val=localtime(&t);
    if(val!=NULL)
    {
        sprintf(lt,"%d%02d%02d%02d%02d%02d",val->tm_year+1900,val->tm_mon+1,val->tm_mday,val->tm_hour,val->tm_min,val->tm_sec);
        return TRUE;
    }
    return FALSE;

}

int GW_Encode(char *vm,char *called,char *caller,char *toneid,char *dtmf,sSendMsg *Msg){

    //int len;
    char d[15];
    //if( strlen(called)+strlen(caller) >= sizeof(sSendMsg))
    //  return 0;

    Msg->body[0]=0;
    if(!FormatTime(d))
        return TL_ERROR_FORMATTIME_FAIL;
    d[14]=0;
    strncat(Msg->body, MSG_TIME_TAG, strlen(MSG_TIME_TAG));
    strncat(Msg->body, d, strlen(d));
    strncat(Msg->body, MSG_VM_TAG, strlen(MSG_VM_TAG));
    strncat(Msg->body, vm, strlen(vm));
    strncat(Msg->body, MSG_CALLING_TAG, strlen(MSG_CALLING_TAG));
    strncat(Msg->body, caller, strlen(caller));
    strncat(Msg->body, MSG_CALLED_TAG, strlen(MSG_CALLED_TAG));
    strncat(Msg->body, called, strlen(called));
    strncat(Msg->body, MSG_TONEID_TAG, strlen(MSG_TONEID_TAG));
    strncat(Msg->body, toneid, strlen(toneid));
    strncat(Msg->body, MSG_DTMF_TAG, strlen(MSG_DTMF_TAG));
    strncat(Msg->body, dtmf, strlen(dtmf));
    strncat(Msg->body, MSG_CLOSE, strlen(MSG_CLOSE));

#if defined(DEBUG)
    printf("CAPGW: tlGW_Encode=%s\n",Msg->body);
#endif
    //Msg->len=strlen(Msg->body);
    return strlen(Msg->body);
}

BOOL tlSetGateway(char *host,u_int port){

    if(host == NULL || port == 0)
    {
        return TL_ERROR_INVALID_HOST;
    }
    memcpy(DBGW_Address, host, strlen(host));
    DBGW_PORT=port;

    return TRUE;
}
/*
BOOL tlSetDefaultGateway(){
    //char   hostname[128];
    //char   host[128]  ;
    //struct hostent*   hn;
    //gethostname(hostname,128);
    //hn   =   gethostbyname(hostname);
    //inet_ntoa(*(struct in_addr*)hn->h_addr_list[0])  ;

    //memcpy(DBGW_Address, DBGW_Default_Address, strlen(DBGW_Default_Address));
    memcpy(DBGW_Address, inet_ntoa(*(struct in_addr*)hn->h_addr_list[0]), strlen(inet_ntoa(*(struct in_addr*)hn->h_addr_list[0])));
    DBGW_PORT=DBGW_Default_PORT;
    return TRUE;
}
*/
void tlPrintGateway(){
    printf("DBGW : Gateway host= %s port= %d\n", DBGW_Address, DBGW_PORT);
}

void tlSetTimeout(int sec,int usec){
    DBGW_Timeout_Sec=sec;
    DBGW_Timeout_uSec=usec;
}

void tlPrintTimeout(){
    printf("DBGW : timeout is %d.%d\n",DBGW_Timeout_Sec ,DBGW_Timeout_uSec);
}

int tlSendInfo_tcp(char *VM,char *CALLED,char *CALLER,char *TONEID,char *DTMF){

    SOCKET_HANDLE g_hSocket = -1;
    sSendMsg sMsg;
    //sRecvMsg ResultMsg;
    int      MsgLen;
    int      nRet;

    if(DBGW_Address == NULL || DBGW_PORT == 0)
        return TL_ERROR_INVALID_HOST;

    if(VM==NULL||CALLED==NULL||CALLER==NULL||TONEID==NULL||DTMF==NULL)
        return TL_ERROR_INVALID_PARAMETER;

    if((MsgLen=GW_Encode(VM,CALLED,CALLER,TONEID,DTMF,&sMsg))<=0)
    {
        return TL_ERROR_ENCODE_FAIL;
    }

    if(!GW_Connect(&g_hSocket))
    {
        return TL_ERROR_CONNECT_FAIL;
    }

    //if((nRet=GW_Send(&g_hSocket,QueryMsg.body,MsgLen,ResultMsg.ringid))<=0)
    if((nRet=GW_Send(&g_hSocket,sMsg.body,MsgLen))<=0)
    {
        return nRet;
    }

    GW_Disconnect(&g_hSocket);

    return nRet;
}

int tlSendInfo_udp(char *VM,char *CALLED,char *CALLER,char *TONEID,char *DTMF){
//int uSendInfo(){
    SOCKET_HANDLE g_hSocket = -1;
    sSendMsg sMsg;
    int len;
    int MsgLen;

    if(DBGW_Address == NULL || DBGW_PORT == 0)
        return TL_ERROR_INVALID_HOST;

    if(VM==NULL||CALLED==NULL||CALLER==NULL||TONEID==NULL||DTMF==NULL)
        return TL_ERROR_INVALID_PARAMETER;

    if((MsgLen=GW_Encode(VM,CALLED,CALLER,TONEID,DTMF,&sMsg))<=0)
    {
        return TL_ERROR_ENCODE_FAIL;
    }

    if ((g_hSocket = Socket_Create(SOCK_DGRAM, NULL, 0)) == -1) {
        return TL_ERROR_SOCKET_CREATE;
    }
    if((len=Socket_SendTo(g_hSocket, DBGW_Address, DBGW_PORT, sMsg.body, MsgLen))!=MsgLen)
        return TL_ERROR_SEND_FAIL;

    Socket_Destroy(g_hSocket);

    return len;
}
THREAD_RESULT client_handler(void *lpArg)
{
    //printf("%s\n%d\n",(char*)((sSendMsg*)lpArg)->body,strlen((char*)((sSendMsg*)lpArg)->body));
    //printf("%s\n",(char*)((sSendMsg*)lpArg)->body);
    SOCKET_HANDLE g_hSocket = -1;
    int nRet;
    if(!GW_Connect(&g_hSocket))
    {
#if defined(DEBUG)
        printf("CAPGW: client_handler connect error(%s , %d).\n",DBGW_Address, DBGW_PORT);
#endif
        return (THREAD_RESULT)-1;
    }else
    {
        //if((nRet=GW_Send(&g_hSocket,QueryMsg.body,MsgLen,ResultMsg.ringid))<=0)
        if((nRet=GW_Send(&g_hSocket,(char*)((sSendMsg*)lpArg)->body,strlen((char*)((sSendMsg*)lpArg)->body)))<=0)
        {
#if defined(DEBUG)
            printf("CAPGW: client_handler send fail. nRet=%d(%s , %d).\n",nRet, DBGW_Address, DBGW_PORT);
#endif
            return (THREAD_RESULT)-1;
        }
    }
    Socket_Destroy(g_hSocket);

#if defined(DEBUG)
        printf("CAPGW: client_handler send complete(%s , %d).\n",DBGW_Address, DBGW_PORT);
#endif
    pthread_exit(0); // exit thread
    return (THREAD_RESULT)0;
}
int tlSendInfo(char *VM,char *CALLED,char *CALLER,char *TONEID,char *DTMF)
{
    //THREAD_HANDLE g_hClientThread = 0;
    pthread_attr_t  tlcap_attr;
    pthread_t       tlcap_hd;

    sSendMsg sMsg;
    int      MsgLen;
    int      nRet;

    if(DBGW_Address == NULL || DBGW_PORT == 0)
        return TL_ERROR_INVALID_HOST;

    if(VM==NULL||CALLED==NULL||CALLER==NULL||TONEID==NULL||DTMF==NULL)
        return TL_ERROR_INVALID_PARAMETER;

    if((MsgLen=GW_Encode(VM,CALLED,CALLER,TONEID,DTMF,&sMsg))<=0)
    {
        return TL_ERROR_ENCODE_FAIL;
    }

    if (pthread_attr_init(&tlcap_attr) == 0)
    {
        /* init pthread attribute */
        //pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);


        if (pthread_create(&tlcap_hd, &tlcap_attr, client_handler, (void*)&sMsg) != 0)
        {
            pthread_attr_destroy(&tlcap_attr);
        }
    }

    pthread_join(tlcap_hd,NULL);

    nRet=MsgLen;
    return nRet;
}
