// sockagent.cpp : Defines the class behaviors for the application.
//

#include "stdafx.h"
#include "sockagent.h"
#include "sockagentDlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CSockagentApp

BEGIN_MESSAGE_MAP(CSockagentApp, CWinApp)
	//{{AFX_MSG_MAP(CSockagentApp)
		// NOTE - the ClassWizard will add and remove mapping macros here.
		//    DO NOT EDIT what you see in these blocks of generated code!
	//}}AFX_MSG
	ON_COMMAND(ID_HELP, CWinApp::OnHelp)
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CSockagentApp construction

CSockagentApp::CSockagentApp()
{
	// TODO: add construction code here,
	// Place all significant initialization in InitInstance
}

/////////////////////////////////////////////////////////////////////////////
// The one and only CSockagentApp object

CSockagentApp theApp;

/////////////////////////////////////////////////////////////////////////////
// CSockagentApp initialization

BOOL CSockagentApp::InitInstance()
{
	if (!AfxSocketInit())
	{
		AfxMessageBox(IDP_SOCKETS_INIT_FAILED);
		return FALSE;
	}

	AfxEnableControlContainer();

	// Standard initialization
	// If you are not using these features and wish to reduce the size
	//  of your final executable, you should remove from the following
	//  the specific initialization routines you do not need.

#ifdef _AFXDLL
	Enable3dControls();			// Call this when using MFC in a shared DLL
#else
	Enable3dControlsStatic();	// Call this when linking to MFC statically
#endif

	//albert add
	m_sockOpen=FALSE;

	CSockagentDlg dlg;
	m_pMainWnd = &dlg;
	int nResponse = dlg.DoModal();
	if (nResponse == IDOK)
	{
		// TODO: Place code here to handle when the dialog is
		//  dismissed with OK
	}
	else if (nResponse == IDCANCEL)
	{
		// TODO: Place code here to handle when the dialog is
		//  dismissed with Cancel
	}

	// Since the dialog has been closed, return FALSE so that we exit the
	//  application, rather than start the application's message pump.
	return FALSE;
}

void CSockagentApp::OutputString(CString string)
{
	CSockagentDlg *pDlg=(CSockagentDlg *) m_pMainWnd;
	CListBox *theListBox=(CListBox *) m_pMainWnd->GetDlgItem(IDC_LIST_OUTPUT);
	theListBox->AddString(string);
	theListBox->SetCurSel(theListBox->GetCount()-1);
	theListBox->SendMessage(WM_PAINT,0,0);
}

void CSockagentApp::sockAccept()
{
	CString string;
	sock* pclient=new sock();

	if (m_pSocket->Accept(*pclient,
		(struct sockaddr*)&(m_pSocket->from),
		&(m_pSocket->fromlen))==INVALID_SOCKET)
//	if (m_pSocket->Accept(*m_pClient))
	{
		string.Format("accept error %d\n",WSAGetLastError());
		OutputString(string);
		//WSACleanup();
		delete m_pSocket;
		string.Format("socket close.");
		OutputString(string);
	}else
	{
		string.Format("accept connection from %s ,port %d\n",
			inet_ntoa(m_pSocket->from.sin_addr),htons(m_pSocket->from.sin_port));
		OutputString(string);
		pclient->from=m_pSocket->from;
		pclient->fromlen=m_pSocket->fromlen;
		pclient->strFrom.Format(inet_ntoa(m_pSocket->from.sin_addr));
		pclient->iFromPort=htons(m_pSocket->from.sin_port);

		//OutputString(string);
	}
	string.Empty();
}

void CSockagentApp::sockReceive(sock* client)
{
	CString string;
	char ch[256];
	for (int i=0;i<256;i++) ch[i]=0x00;
	int status;

//	if((status=ReceiveFrom(ch,256,m_strLocalIP,(UINT &)port,0))>0)	//UDP
	if((status=client->Receive(ch,256,0))>0)	//TCP
	{
		string.Format(ch);
		OutputString(string);
	}
	else
	{
		string.Format("Socket Error: %d\n Fail code=%d\n",
			status,GetLastError());
		OutputString(string);
	}
	string.Empty();
	//client->SendMsg(CString("hi albert"));

}

bool CSockagentApp::sockCreate(LPCTSTR lpszAddress, UINT nPort)
{
	if (!(m_pSocket->Create(nPort,SOCK_STREAM,lpszAddress)))	//TCP
//	if (!(Create(nPort)))
	{
		delete m_pSocket;
		CString string;
		string.Format("Create Fail code=%d\n",GetLastError());
		AfxMessageBox(string);
		return FALSE;
	}
	m_pSocket->Bind(nPort,lpszAddress);
	m_pSocket->Listen(5);
	return TRUE;
}
