
//#ifndef SOCK_BUF_DEF
#define MAX_RECV_BUF              1024
#define MAX_SEND_BUF              MAX_RECV_BUF
//#endif

#define MAX_ADDR_LEN              32
#define DBGW_Default_Address      "127.0.0.1"
#define DBGW_Default_PORT         9001
#define DBGW_Default_Timeout_Sec  1
#define DBGW_Default_Timeout_uSec 0
#define Default_Ringid            "999999"

#define TL_ERROR_GLOBAL 	   0   		//maybe timeout etc
#define TL_ERROR_INVAKID_MSISDN -400
#define TL_ERROR_ENCODE_FAIL 	-401
#define TL_ERROR_CONNECT_FAIL 	-402
#define TL_ERROR_SEND_FAIL 	-403
#define TL_ERROR_RECEIVE_FAIL 	-404

int tlGetRingid(char *called,char *caller,char *ringid);
int tlGetRingid2(char *called,char *caller,char *ringid,SOCKET_HANDLE *Socket);
void tlPrintTimeout();
void tlSetTimeout(int sec,int usec);
void tlPrintGateway();
BOOL tlSetDefaultGateway();
BOOL tlSetGateway(char *host,u_int port);

