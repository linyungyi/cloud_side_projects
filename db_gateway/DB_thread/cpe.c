#include "cpe.h"
#include <sys/ioctl.h>

void _cpe_assert(int exp, const char *file, int line)
{
    if (!exp)
    {
        printf("ASSERT: %s:%d\n", file, line);
        getchar();
    }
}

/*
 * Socket
 */

#define SOCKET_CHUNK_SIZE 512

SOCKET_HANDLE Socket_Create(int nType, const char *lpszAddr, u_int nPort)
{
    int nSock = socket(PF_INET, nType, 0);

    if (nSock != -1)
    {
        //int nError;
        int nReuseAddr = 1;
        struct linger lingerVal;
        struct sockaddr_in sockAddr;

        /* do not linger on close */
        lingerVal.l_onoff = 0;
        setsockopt(nSock, SOL_SOCKET, SO_LINGER, (void*)&lingerVal, sizeof(lingerVal));
        setsockopt(nSock, SOL_SOCKET, SO_REUSEADDR, (void*)&nReuseAddr, sizeof(nReuseAddr));

        memset(&sockAddr, 0, sizeof(sockAddr));

        sockAddr.sin_family      = AF_INET;
        sockAddr.sin_port        = htons((u_short)nPort);
        sockAddr.sin_addr.s_addr = (lpszAddr == 0) ? htonl(INADDR_ANY) : inet_addr(lpszAddr);

        if (bind(nSock, (struct sockaddr*)&sockAddr, sizeof(sockAddr)) != 0)
        {
            TRACE(("[Socket_Create] %s\n", strerror(errno)));
            close(nSock);
            nSock = -1;
        }
    }

    return nSock;
}

BOOL Socket_Destroy(SOCKET_HANDLE hSocket)
{
    shutdown(hSocket, 1);
    close(hSocket);
    return TRUE;
}


BOOL Socket_Connect(SOCKET_HANDLE hSocket, const char *lpszHost, u_int nPort)
{
    struct sockaddr_in sockAddr;
    BOOL fResult = FALSE;


    memset(&sockAddr, 0, sizeof(sockAddr));

    sockAddr.sin_family      = AF_INET;
    sockAddr.sin_port        = htons((u_short)nPort);
    sockAddr.sin_addr.s_addr = inet_addr(lpszHost);

    if (sockAddr.sin_addr.s_addr == INADDR_NONE)
    {
        struct hostent *lphost;

        lphost = gethostbyname(lpszHost);

        if (lphost != NULL)
            sockAddr.sin_addr.s_addr = ((struct in_addr*)(void*)(lphost->h_addr))->s_addr;
        else
        {
            errno = EINVAL;
            TRACE(("[Socket_Connect] Cannot find address\n"));
        }
    }

    if (sockAddr.sin_addr.s_addr != INADDR_NONE)
    {

        if (connect(hSocket, (struct sockaddr*)&sockAddr, sizeof(sockAddr)) == 0)
            fResult = TRUE;
        else if (errno == EWOULDBLOCK) /* non-blocking mode */
            fResult = TRUE;
    }

    return fResult;
}

int Socket_Send(SOCKET_HANDLE hSocket, void *lpData, u_int nLen)
{
    char *lpSend = (char*)lpData;
    int nCount;
    int nResult = 0;

    while (nLen > 0)
    {
        nCount = send(hSocket, lpSend, min(nLen, SOCKET_CHUNK_SIZE), 0);

        if (nCount > 0)
        {
            nLen    -= nCount;
            nResult += nCount;
            lpSend  += nCount;
        }
        else
        {
            nResult = -1;
            break;
        }
    }

    return nResult;
}

int Socket_SendTo(SOCKET_HANDLE hSocket, const char *lpszHost, u_int nPort, void *lpData, u_int nLen)
{
    struct sockaddr_in sockAddr;
    int nResult = -1;

    memset(&sockAddr, 0, sizeof(sockAddr));

    sockAddr.sin_family      = AF_INET;
    sockAddr.sin_port        = htons((u_short)nPort);
    sockAddr.sin_addr.s_addr = inet_addr(lpszHost);

    if (sockAddr.sin_addr.s_addr == INADDR_NONE)
    {
        struct hostent *lphost;

        lphost = gethostbyname(lpszHost);

        if (lphost != NULL)
            sockAddr.sin_addr.s_addr = ((struct in_addr*)(void*)(lphost->h_addr))->s_addr;
        else
        {
            errno = EINVAL;
            TRACE(("[Socket_Connect] Cannot find address\n"));
        }
    }

    if (sockAddr.sin_addr.s_addr != INADDR_NONE)
        nResult = sendto(hSocket, lpData, nLen, 0, (void*)&sockAddr, sizeof(sockAddr));

    return nResult;
}

int Socket_Receive(SOCKET_HANDLE hSocket, void *lpData, u_int nLen)
{
    return recv(hSocket, lpData, nLen, 0);
}

int Socket_ReceiveFrom(SOCKET_HANDLE hSocket, char *lpszHost, int nSize, u_int *pPort, void *lpData, u_int nLen)
{
	struct sockaddr_in sockAddr;
	int nAddrLen = sizeof(sockAddr);
    int nResult = -1;

    memset(&sockAddr, 0, sizeof(sockAddr));

    nResult = recvfrom(hSocket, lpData, nLen, 0, (struct sockaddr*)&sockAddr, &nAddrLen);

    if (nResult > 0)
    {
        if (lpszHost != NULL)
        {
            strncpy(lpszHost, inet_ntoa(sockAddr.sin_addr), nSize-1);
            lpszHost[nSize-1] = 0;
        }

        if (pPort != NULL)
        {
            *pPort = ntohs(sockAddr.sin_port);
        }
    }

    return nResult;
}


