//#ifndef SOCK_BUF_DEF
#define MAX_RECV_BUF                1024
#define MAX_SEND_BUF                MAX_RECV_BUF
//#endif

#define MAX_ADDR_LEN                32

#define DBGW_Default_Timeout_Sec    1
#define DBGW_Default_Timeout_uSec   0


#define TL_ERROR_GLOBAL 		    0   		//maybe timeout etc
#define TL_ERROR_INVALID_MSISDN 	-400
#define TL_ERROR_ENCODE_FAIL 		-401
#define TL_ERROR_CONNECT_FAIL 		-402
#define TL_ERROR_SEND_FAIL 		    -403
#define TL_ERROR_RECEIVE_FAIL 		-404
#define TL_ERROR_INVALID_HOST 		-405
#define TL_ERROR_INVALID_PARAMETER	-406
#define TL_ERROR_SOCKET_CREATE		-407
#define TL_ERROR_FORMATTIME_FAIL 	-501

#ifndef typeb
typedef int 	BOOL;
#endif

BOOL tlSetGateway(char *host,u_int port);
void tlPrintGateway();

int tlSendInfo_udp(char *VM,char *CALLED,char *CALLER,char *TONEID,char *DTMF);
int tlSendInfo_tcp(char *VM,char *CALLED,char *CALLER,char *TONEID,char *DTMF);
int tlSendInfo(char *VM,char *CALLED,char *CALLER,char *TONEID,char *DTMF);
