#ifndef __CPE_H__
#define __CPE_H__

//#ifndef SOLARIS
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
//#endif

#include <string.h>
#include <stdarg.h>
#include <errno.h>
#include <time.h>


#include <unistd.h>
#include <ctype.h>
#include <pthread.h>
#include <signal.h>
#include <stropts.h>
#include <netdb.h>
#include <fcntl.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/ioctl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/socket.h>
#include <sys/poll.h>
#include <sys/time.h>
#include <unistd.h>


#if defined(DEC)
#include <sys/mode.h>
#endif


/* Type */

#ifndef TRUE
#define TRUE    (1)
#endif

#ifndef FALSE
#define FALSE   (0)
#endif

#ifndef NULL
#define NULL    (0)
#endif

#define typeb ;
typedef int 	BOOL;
#ifndef INADDR_NONE
#define INADDR_NONE     ((in_addr_t)(-1))
#endif

#ifndef max
#define max(a,b)        (((a) > (b)) ? (a) : (b))
#endif

#ifndef min
#define min(a,b)        (((a) < (b)) ? (a) : (b))
#endif

#define SLEEP_INFINITE  (-1)
#define MAX_TIMER_CNT   64

typedef int             SOCKET_HANDLE;
typedef pthread_t       THREAD_HANDLE;
typedef void*           THREAD_RESULT;
typedef THREAD_RESULT (*THREAD_PROC)(void*);
typedef pthread_mutex_t MUTEX_HANDLE;
typedef struct
{
    pthread_mutex_t hMutex;
    pthread_cond_t hCondt;
    BOOL fSet;
} EVENT_HANDLE;
typedef int             TIMER_HANDLE;

/* Debug */

#if defined(DEBUG)
extern void _cpe_assert(int exp, const char *file, int line);

#define TRACE(x)    printf x
#define ASSERT(x)   _cpe_assert(x, __FILE__, __LINE__)
#else /* DEBUG */
#define TRACE(x)
#define ASSERT(x)
#endif /* DEBUG */

/* Socket */

SOCKET_HANDLE Socket_Create(int nType, const char *lpszAddr, u_int nPort);
BOOL Socket_Destroy(SOCKET_HANDLE hSocket);
BOOL Socket_Connect(SOCKET_HANDLE hSocket, const char *lpszHost, u_int nPort);
int Socket_Send(SOCKET_HANDLE hSocket, void *lpData, u_int nLen);
int Socket_SendTo(SOCKET_HANDLE hSocket, const char *lpszHost, u_int nPort, void *lpData, u_int nLen);
int Socket_Receive(SOCKET_HANDLE hSocket, void *lpData, u_int nLen);
int Socket_ReceiveFrom(SOCKET_HANDLE hSocket, char *lpszHost, int nSize, u_int *pPort, void *lpData, u_int nLen);

/**/
#if defined(SOLARIS)
#define IOCSIZE_MASK    0x1fff0000      /* Field which has parameter size */


#define IOCPARM_MASK    (IOCSIZE_MASK>>16) /* maximum parameter size */


#define IOC_OUT         0x40000000      /* copy out parameters */
#define IOC_IN          (int)0x80000000      /* copy in parameters  */


#define _IOW(x,y,t)  ((int)(IOC_IN|(((int)sizeof(t)&IOCPARM_MASK)<<16)|((x)<<8)|y))
#define _IOR(x,y,t)  ((int)(IOC_OUT|(((int)sizeof(t)&IOCPARM_MASK)<<16)|((x)<<8)|y))



#define FIOFATTACH      _IOW('f', 130, int)     /* internal only: fattach */
#define FIOFDETACH      _IOW('f', 129, int)     /* internal only: fdetach */
#define FIOPIPESTAT     _IOW('f', 128, int)     /* internal only: pipestat */
#define FIONREAD        _IOR('f', 127, int)     /* get # bytes to read */
#define FIONBIO         _IOW('f', 126, int)     /* set/clear non-blocking i/o */
#define FIOASYNC        _IOW('f', 125, int)     /* set/clear async i/o */
#endif


/* Timer */
/*
TIMER_HANDLE Timer_Create(u_long nMsecs);
BOOL Timer_Destroy(TIMER_HANDLE hTimer);
*/
#endif /* __CPE_H__ */
