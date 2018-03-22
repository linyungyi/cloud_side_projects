#include <sys/types.h>
#include "cpe.h"
#include "dbgw.h"
#include <fcntl.h> 


static u_int DBGW_PORT=0;
static char  DBGW_Address[MAX_ADDR_LEN] = "";
static int  DBGW_Timeout_Sec     = 1;
static int  DBGW_Timeout_uSec     = 0;//micro-second

typedef struct RecvMsg
{
    char ringid[20];
} sRecvMsg;

typedef struct SendMsg
{
    char body[40];
} sSendMsg;

BOOL GW_Connect(SOCKET_HANDLE *g_hGWSocket)
{
    int nBlock=0;    
    fd_set fdsRead;
    fd_set fdsWrite;
    
    struct timeval hTimeout; 

    hTimeout.tv_sec = DBGW_Timeout_Sec;
    hTimeout.tv_usec = DBGW_Timeout_uSec;  
    
    if (*g_hGWSocket != -1)    
    {
        GW_Disconnect(*g_hGWSocket);
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
    
    //fcntl(*g_hGWSocket, F_SETFL, O_NONBLOCK );    //conect non-block mode
    //ioctl(*g_hGWSocket, FIONBIO, &nBlock);     
    if(fcntl(*g_hGWSocket, F_SETFL, O_NONBLOCK )<0)
    {
#if defined(DEBUG)    
    	printf("Error in GW_connect(). Cannot set nonblock connect mode.( %s )\n", strerror(errno)); 
#endif
	Socket_Destroy(*g_hGWSocket);
	*g_hGWSocket = -1;	
    	return FALSE;	
    }
	   
    if (Socket_Connect(*g_hGWSocket, DBGW_Address, DBGW_PORT))
    {	
#if defined(DEBUG)
    	printf("GWDB1: connecting to DBGW server %s:%u\n", DBGW_Address, DBGW_PORT);
#endif
        return TRUE;
    }else if(errno ==EINPROGRESS)
    {	
       //printf("\nSocket in progress");
       FD_ZERO(&fdsRead);
       FD_ZERO(&fdsWrite);
       FD_SET(*g_hGWSocket, &fdsRead);
       FD_SET(*g_hGWSocket, &fdsWrite);
       
       if (select (*g_hGWSocket + 1, &fdsRead, &fdsWrite, 0, &hTimeout) == 0)
       {
        // problems, bail out...

#if defined(DEBUG)    
    		printf("DBGW: connect to DBGW server failed. sec=%d usec=%d host=%s port=%d .",hTimeout.tv_sec,hTimeout.tv_usec,DBGW_Address,DBGW_PORT);
    		printf("Error in GW_connect().1 %s\n", strerror(errno)); 
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
    		printf("DBGW: connect to DBGW server failed. sec=%d usec=%d host=%s port=%d .",hTimeout.tv_sec,hTimeout.tv_usec,DBGW_Address,DBGW_PORT);
    		printf("Error in GW_connect().2 %s\n",  strerror(errno)); 
#endif
    		Socket_Destroy(*g_hGWSocket);
    		*g_hGWSocket = -1;
    		return FALSE;                  
            }
            else 
            {
#if defined(DEBUG)
    		printf("GWDB2: connecting to DBGW server %s:%u\n", DBGW_Address, DBGW_PORT);
#endif
        	return TRUE;            
            }

       }
      }else
      {
#if defined(DEBUG)    
	printf("DBGW: connect to DBGW server failed. sec=%d usec=%d host=%s port=%d .",hTimeout.tv_sec,hTimeout.tv_usec,DBGW_Address,DBGW_PORT);
	printf("Error in GW_connect().3 %s\n", strerror(errno)); 
#endif
	Socket_Destroy(*g_hGWSocket);
	*g_hGWSocket = -1;
	return FALSE;   
      }      

    /* must destroy the socket before next connect */

}

/*
BOOL GW_Connect(SOCKET_HANDLE *g_hGWSocket)
{
    //SOCKET m_socket;
    struct sockaddr_in servAddr;
    int opt = 1;
    struct timeval timeout;
    fd_set rdevents, wrevents, exevents;
    int ret;
    int err=sizeof(int);
    socklen_t len=sizeof(socklen_t);

    *g_hGWSocket=-1;
    *g_hGWSocket = socket( AF_INET, SOCK_STREAM, IPPROTO_TCP );
    if(*g_hGWSocket<0)
    {
        close(*g_hGWSocket);
        return FALSE;
    }

    if(setsockopt(*g_hGWSocket,SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt))<0)
    {
        close(*g_hGWSocket);
        return FALSE;
    }

    if(setsockopt(*g_hGWSocket,SOL_SOCKET, SO_KEEPALIVE, &opt, sizeof(opt))<0)
    {
        close(*g_hGWSocket);
        return FALSE;
    }
    servAddr.sin_family      = AF_INET;
    servAddr.sin_addr.s_addr = inet_addr(DBGW_Address);
    servAddr.sin_port        = htons(DBGW_PORT);

    if(fcntl(*g_hGWSocket, F_SETFL, O_NONBLOCK)<0)
    {
	printf("Error in GW_connect().1 %s\n", strerror(errno));    	
        close(*g_hGWSocket);
        return FALSE;
    }

    if((ret = connect(*g_hGWSocket, (struct sockaddr *) &servAddr, sizeof(servAddr))) && errno != EINPROGRESS)
    {
    	printf("Error in GW_connect().2 %s\n", strerror(errno));
        close(*g_hGWSocket);
        return FALSE;
    }

    //printf("Try connecting to [ %s ] ......\n",connect_to_ip);

    if(ret == 0)
    {
	return TRUE;
    }

    FD_ZERO(&rdevents);
    FD_SET(*g_hGWSocket, &rdevents);
    wrevents = rdevents;
    exevents = rdevents;
    timeout.tv_sec  = DBGW_Timeout_Sec;
    timeout.tv_usec = DBGW_Timeout_uSec;

    ret = select(*g_hGWSocket+1, &rdevents, &wrevents, &exevents, &timeout);
    if(ret <= 0)
    {
        close(*g_hGWSocket);
        return FALSE;
    }
    else
    {
        if(!FD_ISSET(*g_hGWSocket, &rdevents) && !FD_ISSET(*g_hGWSocket, &wrevents))
        {
            printf("Error in GW_connect().3 %s\n", strerror(errno));
            close(*g_hGWSocket);
            return FALSE;
        }

        if(getsockopt(*g_hGWSocket, SOL_SOCKET, SO_ERROR, &err, &len) < 0)
        {
            printf("Error in GW_connect().4 %s\n", strerror(errno));	
            close(*g_hGWSocket);
            return FALSE;
    	}

    if (err != 0)
    {
    	printf("Error in GW_connect().5 %s\n", strerror(errno));
     	close(*g_hGWSocket);
    	return FALSE;
    }

    return TRUE;
  }
}*/

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
}

int GW_Send(SOCKET_HANDLE *g_hGWSocket,char *lpSendMSG,int nCount,char *lpRecvMsg)
{
    char g_hSendBuf[MAX_SEND_BUF+1];
    memcpy(g_hSendBuf, lpSendMSG , nCount);
    g_hSendBuf[nCount] = '\r';
    g_hSendBuf[nCount+1] = '\n';
     
    int nRet;
    fd_set fdsRead;
    struct timeval hTimeout; 
    
    if (Socket_Send(*g_hGWSocket, g_hSendBuf, strlen(g_hSendBuf)) != strlen(g_hSendBuf))
    {
        return TL_ERROR_SEND_FAIL;
    }

    /* set timeout */
    FD_ZERO(&fdsRead);
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
    
    return nRet;
}

int GW_Encode(char *called,char *caller,sSendMsg *Msg){
	
	int len;	
	if( strlen(called)+strlen(caller) >= sizeof(sSendMsg))
		return 0;
	
	Msg->body[0]=0;
	strncat(Msg->body, called, strlen(called));

	strncat(Msg->body, " ", 1);
	strncat(Msg->body, caller, strlen(caller));
	len=strlen(Msg->body);
	
#if defined(DEBUG)               	
        printf("DBGW: GW_Encode=%s\n",Msg->body);
#endif 

	return strlen(Msg->body);
}

BOOL tlSetGateway(char *host,u_int port){
	
	if(host == NULL || port == 0)
	{
		memcpy(DBGW_Address, DBGW_Default_Address, strlen(DBGW_Default_Address));	
		DBGW_PORT=DBGW_Default_PORT;
	}
	memcpy(DBGW_Address, host, strlen(host));	
	DBGW_PORT=port;
	
	return TRUE;
}

BOOL tlSetDefaultGateway(){
	//char   hostname[128]; 
	//char   host[128]  ;
	//struct hostent*   hn;   
	//gethostname(hostname,128);   
	//hn   =   gethostbyname(hostname);   
	//inet_ntoa(*(struct in_addr*)hn->h_addr_list[0])  ; 
	
	memcpy(DBGW_Address, DBGW_Default_Address, strlen(DBGW_Default_Address));	
	//memcpy(DBGW_Address, inet_ntoa(*(struct in_addr*)hn->h_addr_list[0]), strlen(inet_ntoa(*(struct in_addr*)hn->h_addr_list[0])));	
	DBGW_PORT=DBGW_Default_PORT;	
	return TRUE;
}

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

int tlGetRingid(char *called,char *caller,char *ringid){

	SOCKET_HANDLE g_hSocket = -1;
	sSendMsg QueryMsg;
	sRecvMsg ResultMsg;
	int      MsgLen;
	int 	 nRet;

	if(DBGW_Address == NULL || DBGW_PORT == 0)
		tlSetDefaultGateway();
	
	if( called == NULL || caller == NULL )
	{
		printf("invalid MSISDN occured. called=%s caller=%s \n", called, caller);
		memcpy(ringid,Default_Ringid,strlen(Default_Ringid));
		return TL_ERROR_INVAKID_MSISDN;
	}
	if((MsgLen=GW_Encode(called,caller,&QueryMsg))<=0)
	{
		memcpy(ringid,Default_Ringid,strlen(Default_Ringid));
		return TL_ERROR_ENCODE_FAIL;
	}
	
	if(!GW_Connect(&g_hSocket))
	{
		memcpy(ringid,Default_Ringid,strlen(Default_Ringid));
		return TL_ERROR_CONNECT_FAIL;
	}
	
	if((nRet=GW_Send(&g_hSocket,QueryMsg.body,MsgLen,ResultMsg.ringid))<=0)
	{
		memcpy(ringid,Default_Ringid,strlen(Default_Ringid));
		return nRet;	
	}

	GW_Disconnect(&g_hSocket);

	//printf("Ringid=%s len=%d\n",ResultMsg.ringid ,strlen(ResultMsg.ringid));	
	memcpy(ringid,ResultMsg.ringid,6);
	ringid[6]=0;
	return strlen(ringid);

}

int tlGetRingid2(char *called,char *caller,char *ringid,SOCKET_HANDLE *g_hSocket){

	//SOCKET_HANDLE g_hSocket = -1;
	sSendMsg QueryMsg;
	sRecvMsg ResultMsg;
	int      MsgLen;
	int 	 nRet;

	if(DBGW_Address == NULL || DBGW_PORT == 0)
		tlSetDefaultGateway();
	
	if( called == NULL || caller == NULL )
	{
		printf("invalid MSISDN occured. called=%s caller=%s \n", called, caller);
		memcpy(ringid,Default_Ringid,strlen(Default_Ringid));
		return TL_ERROR_INVAKID_MSISDN;
	}
	if((MsgLen=GW_Encode(called,caller,&QueryMsg))<=0)
	{
		memcpy(ringid,Default_Ringid,strlen(Default_Ringid));
		return TL_ERROR_ENCODE_FAIL;
	}
/*	
	if(!GW_Connect(&g_hSocket))
	{
		memcpy(ringid,Default_Ringid,strlen(Default_Ringid));
		return TL_ERROR_CONNECT_FAIL;
	}
*/	
	if((nRet=GW_Send(g_hSocket,QueryMsg.body,MsgLen,ResultMsg.ringid))<=0)
	{
		memcpy(ringid,Default_Ringid,strlen(Default_Ringid));
		return nRet;	
	}

	GW_Disconnect(g_hSocket);

	//printf("Ringid=%s len=%d\n",ResultMsg.ringid ,strlen(ResultMsg.ringid));	
	memcpy(ringid,ResultMsg.ringid,6);
	ringid[6]=0;
	return strlen(ringid);

}
