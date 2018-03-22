// sockagentDlg.cpp : implementation file
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
// CAboutDlg dialog used for App About

class CAboutDlg : public CDialog
{
public:
	CAboutDlg();

// Dialog Data
	//{{AFX_DATA(CAboutDlg)
	enum { IDD = IDD_ABOUTBOX };
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CAboutDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	//{{AFX_MSG(CAboutDlg)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialog(CAboutDlg::IDD)
{
	//{{AFX_DATA_INIT(CAboutDlg)
	//}}AFX_DATA_INIT
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CAboutDlg)
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialog)
	//{{AFX_MSG_MAP(CAboutDlg)
		// No message handlers
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CSockagentDlg dialog

CSockagentDlg::CSockagentDlg(CWnd* pParent /*=NULL*/)
	: CDialog(CSockagentDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CSockagentDlg)
	m_uiLocalID = 0;
	m_uiRemoteID = 0;
	//}}AFX_DATA_INIT
	// Note that LoadIcon does not require a subsequent DestroyIcon in Win32
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CSockagentDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CSockagentDlg)
	DDX_Control(pDX, IDC_IPADDRESS_REMOTEIP, m_addRemoteIP);
	DDX_Control(pDX, IDC_IPADDRESS_LOCALIP, m_addLocalIP);
	DDX_Text(pDX, IDC_EDIT_LOCALID, m_uiLocalID);
	DDX_Text(pDX, IDC_EDIT_REMOTEID, m_uiRemoteID);
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CSockagentDlg, CDialog)
	//{{AFX_MSG_MAP(CSockagentDlg)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_BUTTON_TEST, OnButtonTest)
	ON_WM_TIMER()
	ON_BN_CLICKED(IDC_BUTTON_SEND, OnButtonSend)
	ON_BN_CLICKED(IDC_BUTTON_SET, OnButtonSet)
	ON_BN_CLICKED(IDC_BUTTON_CLOSE, OnButtonClose)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CSockagentDlg message handlers

BOOL CSockagentDlg::OnInitDialog()
{
	CDialog::OnInitDialog();

	// Add "About..." menu item to system menu.

	// IDM_ABOUTBOX must be in the system command range.
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		CString strAboutMenu;
		strAboutMenu.LoadString(IDS_ABOUTBOX);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// Set the icon for this dialog.  The framework does this automatically
	//  when the application's main window is not a dialog
	SetIcon(m_hIcon, TRUE);			// Set big icon
	SetIcon(m_hIcon, FALSE);		// Set small icon
	
	// TODO: Add extra initialization here
	strLocalIP="10.144.26.75";
	strRemoteIP=strLocalIP;
//	uiLocalPort=2;
//	uiRemotePort=1;

	m_addLocalIP.SetAddress(10,144,26,75);
	m_addRemoteIP.SetAddress(10,144,26,75);
	m_uiLocalID=2;
	m_uiRemoteID=1;
	UpdateData(FALSE);
	
	SetTimer(8888,5000,NULL);

	return TRUE;  // return TRUE  unless you set the focus to a control
}

void CSockagentDlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialog::OnSysCommand(nID, lParam);
	}
}

// If you add a minimize button to your dialog, you will need the code below
//  to draw the icon.  For MFC applications using the document/view model,
//  this is automatically done for you by the framework.

void CSockagentDlg::OnPaint() 
{
	if (IsIconic())
	{
		CPaintDC dc(this); // device context for painting

		SendMessage(WM_ICONERASEBKGND, (WPARAM) dc.GetSafeHdc(), 0);

		// Center icon in client rectangle
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// Draw the icon
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialog::OnPaint();
	}
}

// The system calls this to obtain the cursor to display while the user drags
//  the minimized window.
HCURSOR CSockagentDlg::OnQueryDragIcon()
{
	return (HCURSOR) m_hIcon;
}


void CSockagentDlg::OnButtonTest() 
{
	// TODO: Add your control notification handler code here
	CSockagentApp *pApp=(CSockagentApp *)AfxGetApp();
	CString String;

	String.Format("working..\n");
	pApp->OutputString(String);	
}

void CSockagentDlg::OnTimer(UINT nIDEvent) 
{
	// TODO: Add your message handler code here and/or call default
	CSockagentApp *pApp=(CSockagentApp *)AfxGetApp();
	CString String;
	String.Format("waitting..\n");
	switch (nIDEvent)
	{
	case 8888:
		{
			//pApp->OutputString(String);	
			break;
		}
	default:
		{
			break;
		}
	}



	CDialog::OnTimer(nIDEvent);
}
//create socket
void CSockagentDlg::OnOK() 
{
	// TODO: Add extra validation here
	CSockagentApp *theApp=(CSockagentApp *) AfxGetApp();
	CString string;
	if (!theApp->m_sockOpen)
	{
		theApp->m_pSocket=new sock(strLocalIP,m_uiLocalID,strRemoteIP,m_uiRemoteID);

		if (theApp->sockCreate(strLocalIP,
			m_uiLocalID+theApp->m_pSocket->m_uiBasePort))
		{
			string.Format("create socket( ip: %s port: %d)",strLocalIP,m_uiLocalID+theApp->m_pSocket->m_uiBasePort);
			theApp->OutputString(string);
			theApp->m_sockOpen=TRUE;
		}
		
	}else
	{
		string.Format("connection....");
		theApp->OutputString(string);
	}
	//CDialog::OnOK();
}


void CSockagentDlg::OnButtonSend() 
{
	// TODO: Add your control notification handler code here
	CSockagentApp *theApp=(CSockagentApp *) AfxGetApp();
	CString msg;
	msg="OAM 820820 hi_link STOP";
	CString string;

	sock *out=new sock();
	if (!(out->Create(0,SOCK_STREAM,NULL)))
	{
		delete out;
		out=NULL;
		string.Format("create error %d\n",WSAGetLastError());
		theApp->OutputString(string);
		//WSACleanup();
		//AfxMessageBox("Fail to create a socket");
	}else
	{
	//char temp[2];
	//AfxMessageBox(itoa(m_uiRemoteID+2000,temp,2));	
		if (out->Connect(strRemoteIP,m_uiRemoteID+out->m_uiBasePort))
		//if (theApp->m_pSocket->Connect(strRemoteIP,m_uiRemoteID+theApp->m_pSocket->m_uiBasePort))
		{
			out->strFrom=strRemoteIP;
			out->iFromPort=m_uiRemoteID+out->m_uiBasePort;
			out->SendMsg(CString(msg));

			delete out;
			out=NULL;
			//theApp->m_pSocket->SendMsg(CString(msg));
		}else
		{
			string.Format("connect error %d\n",WSAGetLastError());
			theApp->OutputString(string);
			//WSACleanup();
			delete out;
			
		}
	}
	
}

void CSockagentDlg::OnButtonSet() 
{
	// TODO: Add your control notification handler code here
	UpdateData(TRUE);
	//test
	CSockagentApp *theApp=(CSockagentApp *) AfxGetApp();
	CString string;
	//CString strLocalIP,strRemoteIP;
	getIP(&m_addLocalIP,&strLocalIP);
	getIP(&m_addRemoteIP,&strRemoteIP);
	//AfxMessageBox(strLocalIP);
	string.Format(" LOCAL IP: %s\n LOCAL ID: %d\n REMOTE IP: %s\n LOCAL ID: %d\n",
		strLocalIP,m_uiLocalID,strRemoteIP,m_uiRemoteID);
	AfxMessageBox(string);
	if (theApp->m_sockOpen)
	{
		delete theApp->m_pSocket;
		string.Format("socket close.");
		theApp->OutputString(string);
		theApp->m_sockOpen=FALSE;
	}
}

void CSockagentDlg::getIP(CIPAddressCtrl* ctrlIP,CString* strIP)
{
	char buf[3],buf1[3],buf2[3],buf3[3];
	BYTE a,b,c,d;
	//BYTE *a,*b,*c,*d;
	ctrlIP->GetAddress((BYTE &)a,(BYTE &)b,(BYTE &)c,(BYTE &)d);
	//_itoa(a,buf,10);
	strIP->Format("%s.%s.%s.%s",
		_itoa(a,buf,10),_itoa(b,buf1,10),_itoa(c,buf2,10),_itoa(d,buf3,10));
	
	//m_addRemoteIP.GetAddress((BYTE &)a,(BYTE &)b,(BYTE &)c,(BYTE &)d);
	//strLocalIP=_itoa(a,buf,10);
	//AfxMessageBox(buf);
}

void CSockagentDlg::OnCancel() 
{
	// TODO: Add extra cleanup here
	
	CSockagentApp *theApp=(CSockagentApp *) AfxGetApp();
	CString string;
	if (theApp->m_sockOpen)
	{
		delete theApp->m_pSocket;
		string.Format("socket close.");
		theApp->OutputString(string);
		theApp->m_sockOpen=FALSE;  //destructor do so
	}
	CDialog::OnCancel();
}

void CSockagentDlg::OnButtonClose() 
{
	// TODO: Add your control notification handler code here
	CSockagentApp *theApp=(CSockagentApp *) AfxGetApp();
	CString string;
	if (theApp->m_sockOpen)
	{
		delete theApp->m_pSocket;
		string.Format("socket close.");
		theApp->OutputString(string);
		theApp->m_sockOpen=FALSE;  //destructor do so
	}else
	{
		string.Format("socket dosen't open.");
		theApp->OutputString(string);
	}	
	//AfxMessageBox((theApp->m_sockOpen?"true":"false"));
}
