// Pop3MonitorDlg.cpp : implementation file
//

#include "stdafx.h"
#include "Pop3Monitor.h"
#include "Pop3MonitorDlg.h"

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
// CPop3MonitorDlg dialog

CPop3MonitorDlg::CPop3MonitorDlg(CWnd* pParent /*=NULL*/)
	: CDialog(CPop3MonitorDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CPop3MonitorDlg)
	m_edtMsg = _T("");
	m_edtPassword = _T("123456");
	m_edtServer = _T("127.0.0.1");
	m_edtUser = _T("wanggang");
	m_edtMailNum = 1;
	m_chkAuth = FALSE;
	//}}AFX_DATA_INIT
	// Note that LoadIcon does not require a subsequent DestroyIcon in Win32
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CPop3MonitorDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CPop3MonitorDlg)
	DDX_Text(pDX, IDC_EDT_MSG, m_edtMsg);
	DDX_Text(pDX, IDC_EDT_PASSWORD, m_edtPassword);
	DDX_Text(pDX, IDC_EDT_SERVER, m_edtServer);
	DDX_Text(pDX, IDC_EDT_USER, m_edtUser);
	DDX_Text(pDX, IDC_EDT_NUM, m_edtMailNum);
	DDV_MinMaxInt(pDX, m_edtMailNum, 1, 1000);
	DDX_Check(pDX, IDC_CHK_APOP, m_chkAuth);
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CPop3MonitorDlg, CDialog)
	//{{AFX_MSG_MAP(CPop3MonitorDlg)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_WM_CLOSE()
	ON_BN_CLICKED(IDC_BTN_CONNECT, OnBtnConnect)
	ON_BN_CLICKED(IDC_BTN_LIST, OnBtnList)
	ON_BN_CLICKED(IDC_BTN_CREATE, OnBtnCreate)
	ON_BN_CLICKED(IDC_BTN_CLOSE, OnBtnClose)
	ON_BN_CLICKED(IDC_BTN_GETHEADER, OnBtnGetHeader)
	ON_BN_CLICKED(IDC_BTN_GET, OnBtnGet)
	ON_BN_CLICKED(IDC_BTN_STAT, OnBtnStat)
	ON_BN_CLICKED(IDC_BTN_SIZE, OnBtnSize)
	ON_BN_CLICKED(IDC_BTN_SUBJECT, OnBtnSubject)
	ON_BN_CLICKED(IDC_BTN_SENDER, OnBtnSender)
	ON_BN_CLICKED(IDC_BTN_RECEIVER, OnBtnReceiver)
	ON_BN_CLICKED(IDC_BTN_DATE, OnBtnDate)
	ON_BN_CLICKED(IDC_BTN_DELETE, OnBtnDelete)
	ON_BN_CLICKED(IDC_BTN_RESET, OnBtnReset)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CPop3MonitorDlg message handlers

BOOL CPop3MonitorDlg::OnInitDialog()
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
	/*
	CString a="ERR";
	int b=a.Find('-' , 0);
	*/
	return TRUE;  // return TRUE  unless you set the focus to a control
}

void CPop3MonitorDlg::OnSysCommand(UINT nID, LPARAM lParam)
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

void CPop3MonitorDlg::OnPaint() 
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
HCURSOR CPop3MonitorDlg::OnQueryDragIcon()
{
	return (HCURSOR) m_hIcon;
}

void CPop3MonitorDlg::OnOK() 
{
	// TODO: Add extra validation here
	m_pop3handle.Close();
	PostQuitMessage(0);
	CDialog::OnOK();
}

void CPop3MonitorDlg::OnClose() 
{
	// TODO: Add your message handler code here and/or call default
	m_pop3handle.Close();
	
	CDialog::OnClose();
}

void CPop3MonitorDlg::OnBtnConnect() 
{
	// TODO: Add your control notification handler code here
	CString msg;

	UpdateData(true);
	
	if( m_pop3handle.Connect(m_edtUser , m_edtPassword , m_chkAuth) )
		m_edtMsg += "Connect POP3 Server OK!\r\n";
	else
	{
		m_pop3handle.GetLastError(&msg);
		m_edtMsg += msg+"\r\n";
	}
	
	UpdateData(false);
}

void CPop3MonitorDlg::OnCancel() 
{
	// TODO: Add extra cleanup here
	m_pop3handle.Close();
	PostQuitMessage(0);
	
	CDialog::OnCancel();
}

void CPop3MonitorDlg::OnBtnList() 
{
	// TODO: Add your control notification handler code here
	CStringList msgList;
	CString strtmp;

	UpdateData(true);

	if( m_pop3handle.GetMailList(&msgList) )
	{
		m_edtMsg += "Get POP3 Mail List OK!\r\n";
		POSITION pos = msgList.GetHeadPosition();
		while( NULL != pos)
		{
			strtmp = msgList.GetAt(pos);
			m_edtMsg += strtmp+"\r\n";
			msgList.GetNext(pos);
			if( NULL == pos )
				break;
		}
	}
	else
	{
		m_pop3handle.GetLastError(&strtmp);
		m_edtMsg += strtmp+"\r\n";
	}
	UpdateData(false);
}

void CPop3MonitorDlg::OnBtnCreate() 
{
	// TODO: Add your control notification handler code here
	CString msg;

	UpdateData(true);

	//m_pop3handle.SetReceiveBufSize(1024);
	if( m_pop3handle.Create(m_edtServer ,  110) )
		m_edtMsg += "Create POP3 OK!\r\n";
	else
	{
		m_pop3handle.GetLastError(&msg);
		m_edtMsg += msg+"\r\n";
	}
	UpdateData(false);
}

void CPop3MonitorDlg::OnBtnClose() 
{
	// TODO: Add your control notification handler code here
	m_pop3handle.Close();
	
}

void CPop3MonitorDlg::OnBtnGetHeader() 
{
	// TODO: Add your control notification handler code here
	CString msg;
	UpdateData(true);
	if( m_pop3handle.GetMailHeader(m_edtMailNum , &msg) )
		m_edtMsg += msg+"\r\n";
	else
	{
		m_pop3handle.GetLastError(&msg);
		m_edtMsg += msg+"\r\n";
	}
	UpdateData(false);
	
}

void CPop3MonitorDlg::OnBtnGet() 
{
	// TODO: Add your control notification handler code here

	//get mail to a temp file
	GetFile();

	//get mail to memory
	//GetMemory();
}

void CPop3MonitorDlg::GetMemory()
{
	CString msg;
	UpdateData(true);
	
	if( m_pop3handle.GetMail(m_edtMailNum , &msg) )
		m_edtMsg += msg+"\r\n";
	else
	{
		m_pop3handle.GetLastError(&msg);
		m_edtMsg += msg+"\r\n";
	}
	
	UpdateData(false);
}

void CPop3MonitorDlg::GetFile()
{
	CString msg;
	UpdateData(true);
	
	m_pop3handle.GetMail(m_edtMailNum , "msg.tmp");
	m_pop3handle.GetLastError(&msg);
	m_edtMsg += msg+"\r\n";
	
	UpdateData(false);

}

void CPop3MonitorDlg::OnBtnStat() 
{
	// TODO: Add your control notification handler code here
	CString msg;
	UpdateData(true);
	if( m_pop3handle.GetStat(&msg) )
		m_edtMsg += msg+"\r\n";
	else
	{
		m_pop3handle.GetLastError(&msg);
		m_edtMsg += msg+"\r\n";
	}
	UpdateData(false);
	
}

void CPop3MonitorDlg::OnBtnSize() 
{
	// TODO: Add your control notification handler code here
	long lsize;
	char msg[255];
	CString msgerror;
	UpdateData(true);
	if( m_pop3handle.GetMailSize(m_edtMailNum , &lsize) )
	{
		sprintf(msg , "Mail %d\'s size is: %ld\r\n" , m_edtMailNum , lsize);
		m_edtMsg += msg;
	}
	else
	{
		m_pop3handle.GetLastError(&msgerror);
		m_edtMsg += msgerror+"\r\n";
	}
	UpdateData(false);
	
}

void CPop3MonitorDlg::OnBtnSubject() 
{
	// TODO: Add your control notification handler code here
	CString msg;
	UpdateData(true);

	if( m_pop3handle.GetMailSubject(m_edtMailNum , &msg) )
	{
		m_edtMsg += "Subject: " + msg+"\r\n";
	}
	else
	{
		m_pop3handle.GetLastError(&msg);
		m_edtMsg += msg+"\r\n";
	}
	UpdateData(false);

}

void CPop3MonitorDlg::OnBtnSender() 
{
	// TODO: Add your control notification handler code here
	CString msg;
	UpdateData(true);
	if( m_pop3handle.GetMailSender(m_edtMailNum , &msg) )
	{
		m_edtMsg += "Sender: " + msg+"\r\n";
	}
	else
	{
		m_pop3handle.GetLastError(&msg);
		m_edtMsg += msg+"\r\n";
	}
	UpdateData(false);
	
}

void CPop3MonitorDlg::OnBtnReceiver() 
{
	// TODO: Add your control notification handler code here
	CString msg;
	UpdateData(true);
	if( m_pop3handle.GetMailReceiver(m_edtMailNum , &msg) )
	{
		m_edtMsg += "Receiver: " + msg+"\r\n";
	}
	else
	{
		m_pop3handle.GetLastError(&msg);
		m_edtMsg += msg+"\r\n";
	}
	UpdateData(false);
	
}

void CPop3MonitorDlg::OnBtnDate() 
{
	// TODO: Add your control notification handler code here
	CString msg;
	UpdateData(true);
	if( m_pop3handle.GetMailDate(m_edtMailNum , &msg) )
	{
		m_edtMsg += "Date: " + msg+"\r\n";
	}
	else
	{
		m_pop3handle.GetLastError(&msg);
		m_edtMsg += msg+"\r\n";
	}
	UpdateData(false);
}

void CPop3MonitorDlg::OnBtnDelete() 
{
	// TODO: Add your control notification handler code here
	CString msg;
	UpdateData(true);
	if( m_pop3handle.DeleteMail(m_edtMailNum) )
	{
		m_edtMsg += "Delete OK!\r\n";
	}
	else
	{
		m_pop3handle.GetLastError(&msg);
		m_edtMsg += msg+"\r\n";
	}
	UpdateData(false);
	
}

void CPop3MonitorDlg::OnBtnReset() 
{
	// TODO: Add your control notification handler code here
	CString msg;
	UpdateData(true);
	if( m_pop3handle.ResetMail() )
	{
		m_edtMsg += "Reset OK!\r\n";
	}
	else
	{
		m_pop3handle.GetLastError(&msg);
		m_edtMsg += msg+"\r\n";
	}
	UpdateData(false);
	
	
}
