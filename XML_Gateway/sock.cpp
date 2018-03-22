// sock.cpp : implementation file
//

#include "stdafx.h"
#include "sockagent.h"
#include "sock.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// sock
IMPLEMENT_DYNAMIC(sock, CSocket)

sock::sock()
{
		m_uiBasePort=3000;
}

sock::~sock()
{
//	CSockagentApp *theApp=(CSockagentApp *) AfxGetApp();
//	theApp->m_sockOpen=FALSE;
	this->Close();
}


// Do not edit the following lines, which are needed by ClassWizard.
#if 0
BEGIN_MESSAGE_MAP(sock, CSocket)
	//{{AFX_MSG_MAP(sock)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()
#endif	// 0

/////////////////////////////////////////////////////////////////////////////
// sock member functions

sock::sock(CString localIP, UINT localPort, CString remoteIP, UINT remotePort)
{
	m_byteLocalPort=(BYTE)localPort;
	m_byteRemotePort=(BYTE)remotePort;
	m_strLocalIP=localIP;
	m_strRemoteIP=remoteIP;
	m_uiBasePort=3000;
}





bool sock::CreateSocket(LPCTSTR lpszAddress, UINT nPort)
{
//	if (!(Create(nPort,SOCK_DGRAM,lpszAddress)))	//UDP
	return TRUE;
}

void sock::OnReceive(int nErrorCode) 
{
	// TODO: Add your specialized code here and/or call the base class
	CSockagentApp *theApp=(CSockagentApp *) AfxGetApp();

	theApp->sockReceive(this);

	CSocket::OnReceive(nErrorCode);
}

void sock::SendMsg(CString & strText)
{
	CSockagentApp *theApp=(CSockagentApp *) AfxGetApp();

	CString string;
	
	//if (SendTo(strText,strText.GetLength(),m_byteRemotePort+m_uiBasePort,m_strRemoteIP,0)!=SOCKET_ERROR)
if (SendTo(strText,strText.GetLength(),NULL,NULL,0)!=SOCKET_ERROR)
	{
		string.Format("send %d sucess[ %s ]\n",strText.GetLength(),strText);
		theApp->OutputString(string);
	}
	else
	{
		string.Format("Send Socket Failed %s\n",strText);
		AfxMessageBox(string);
	}
	string.Empty();
}

void sock::OnAccept(int nErrorCode) 
{
	// TODO: Add your specialized code here and/or call the base class
	CSockagentApp *theApp=(CSockagentApp *) AfxGetApp();

	fromlen=sizeof(from);
	theApp->sockAccept();

	CSocket::OnAccept(nErrorCode);
}

void sock::Init()
{
	m_pFile=new CSocketFile(this);
	m_pArchiveIn=new CArchive(m_pFile,CArchive::load);
	m_pArchiveOut=new CArchive(m_pFile,CArchive::store);
}

void sock::OnClose(int nErrorCode) 
{
	// TODO: Add your specialized code here and/or call the base class
	CSockagentApp *theApp=(CSockagentApp *) AfxGetApp();
	CString string;
	/*string.Format("closed connection from %s ,port %d\n",
		inet_ntoa(from.sin_addr),htons(from.sin_port));	*/
	string.Format("closed connection from %s ,port %d\n",
		strFrom,iFromPort);	

	theApp->OutputString(string);
	delete this;
	CSocket::OnClose(nErrorCode);
}
