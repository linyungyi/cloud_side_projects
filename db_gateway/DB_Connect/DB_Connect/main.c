#include "cpe.h"
#include "dbgw.h"
#include <time.h>

int main(int argc, char *argv[])
{
	unsigned char A[20];
    	unsigned char B[20];
    	unsigned char C[20];
    	int len;
    	
    	SOCKET_HANDLE g_hSocket;

	memcpy(A, "0422212344", 10);
	A[10]=0;
	memcpy(B, "0933946930", 10);
	B[10]=0;	
	C[0]=0;
	//method 1
	printf("<<Method 1>>\n");
    	tlSetGateway("192.168.23.50",9001);    	
    	len=tlGetRingid(A,B,C);
    	printf("Ringid=%s len=%d\n",C ,len);
    	
    	//method2
    	printf("<<Method 2>>\n");
	if(!GW_Connect(&g_hSocket))
	{
		printf("Error in GW_connect().0 \n");
	} // Do not handle like this!
	len=tlGetRingid2(A,B,C,&g_hSocket);	
    	
    	printf("Ringid=%s len=%d\n",C ,len);
	
	return 0;
}


