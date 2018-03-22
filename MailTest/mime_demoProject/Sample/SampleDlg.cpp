// SampleDlg.cpp : implementation file
//

#include "stdafx.h"
#include "Sample.h"
#include "SampleDlg.h"

#include "atlconv.h"
#include "atlconv.cpp"

#include "ISSHelper.h"
#include "FileSystemStorage.h"

#import ".\MimeSniffer.dll" no_namespace

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
// CSampleDlg dialog

CSampleDlg::CSampleDlg(CWnd* pParent /*=NULL*/)
	: CDialog(CSampleDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CSampleDlg)
	m_strAddress = _T("friend@server.com");
	//}}AFX_DATA_INIT
	// Note that LoadIcon does not require a subsequent DestroyIcon in Win32
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CSampleDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CSampleDlg)
	DDX_Text(pDX, IDC_ADDRESS, m_strAddress);
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CSampleDlg, CDialog)
	//{{AFX_MSG_MAP(CSampleDlg)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_SEND, OnSend)
	ON_BN_CLICKED(IDC_SEND2, OnSend2)
	ON_BN_CLICKED(IDC_SEND3, OnSend3)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CSampleDlg message handlers

BOOL CSampleDlg::OnInitDialog()
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
	
	return TRUE;  // return TRUE  unless you set the focus to a control
}

void CSampleDlg::OnSysCommand(UINT nID, LPARAM lParam)
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

void CSampleDlg::OnPaint() 
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
HCURSOR CSampleDlg::OnQueryDragIcon()
{
	return (HCURSOR) m_hIcon;
}

// Test #1
void CSampleDlg::OnSend() 
{
	UpdateData();

	IMimeDecoderPtr Decoder;

	// create Mime-Object
	if (SUCCEEDED(Decoder.CreateInstance(__uuidof(MimeDecoder))))
	{
		// supply minimal information
		Decoder->From		= "me@home.com";
		Decoder->To			= (LPCTSTR)m_strAddress;
		Decoder->Subject	= "Lunch";
		Decoder->Body		= "Hi! Let's do lunch.....";

		// "Send" the email to a stream
		HANDLE hFile = ::CreateFile("Mail1.txt", GENERIC_WRITE, 0, NULL, CREATE_ALWAYS, 0, NULL);

		if (hFile != INVALID_HANDLE_VALUE)
		{
			CFileSystemStream ResultStream(hFile);

			// ask for IPersistStream
			IPersistStreamPtr pPersist;
			Decoder->QueryInterface(&pPersist);

			pPersist->Save(&ResultStream, FALSE);

			AfxMessageBox("Done!", MB_ICONINFORMATION);
		}
		else
		{
			AfxMessageBox("File couldn't be created!");
		}
	}
	else
	{
		AfxMessageBox("Mime-Object couldn't be created! COM-DLL \"MimeSniffer.dll\" registered?");
	}
}

// Test #2
void CSampleDlg::OnSend2() 
{
	UpdateData();

	IMimeDecoderPtr Decoder;

	// create Mime-Object
	if (SUCCEEDED(Decoder.CreateInstance(__uuidof(MimeDecoder))))
	{
		// supply minimal information
		Decoder->From		= "me@home.com";
		Decoder->To			= (LPCTSTR)m_strAddress;
		Decoder->Subject	= "Test";
		
		// get root body object...
		IMimeBodyPtr Body = Decoder->Body;

		// ...wich will consist of multiple parts
		Body->MajorContentType = (long)Multipart;
		Body->MinorContentType = (long)Mixed;

		// insert a new body object
		IMimeBodyPtr TextPart = Body->AddNew();

		// set header information for this part
		TextPart->MajorContentType	= (long)Text;
		TextPart->MinorContentType	= (long)Plain;
		TextPart->Encoding			= (long)QuotedPrintable;

		// set the content
		TextPart->Value = "This is the text part!";

		// insert a new body object
		IMimeBodyPtr PicturePart = Body->AddNew();

		// set header information for this part
		PicturePart->MajorContentType	= (long)Image;
		PicturePart->MinorContentType	= (long)Jpg;
		PicturePart->Encoding			= (long)Base64;

		// Pic from resource
		HRSRC h = ::FindResource(AfxGetResourceHandle(), MAKEINTRESOURCE(IDR_IMAGE), _T("BIN"));

		DWORD   dwLen	= ::SizeofResource(AfxGetResourceHandle(), h);
		HGLOBAL hRes	= ::LoadResource(AfxGetResourceHandle(), h);
		LPBYTE	pRes	= (LPBYTE)::LockResource(hRes);

		// write Pic to a stream object
		CISSHelper PicStream;
		PicStream.Write(pRes, dwLen, NULL);

		// import pic to email
		PicturePart->Import(&PicStream, FALSE);

		// declare the second part as an attachment
		PicturePart->FileName = "Paradise.jpg";

		// "Send" the email to a stream
		HANDLE hFile = ::CreateFile("Mail2.txt", GENERIC_WRITE, 0, NULL, CREATE_ALWAYS, 0, NULL);

		if (hFile != INVALID_HANDLE_VALUE)
		{
			CFileSystemStream ResultStream(hFile);

			// ask for IPersistStream
			IPersistStreamPtr pPersist;
			Decoder->QueryInterface(&pPersist);

			pPersist->Save(&ResultStream, FALSE);

			AfxMessageBox("Done!", MB_ICONINFORMATION);
		}
		else
		{
			AfxMessageBox("File couldn't be created!");
		}
	}
	else
	{
		AfxMessageBox("Mime-Object couldn't be created! COM-DLL \"MimeSniffer.dll\" registered?");
	}
	
}

// Test #3
void CSampleDlg::OnSend3() 
{
	UpdateData();

	IMimeDecoderPtr Decoder;

	// create Mime-Object
	if (SUCCEEDED(Decoder.CreateInstance(__uuidof(MimeDecoder))))
	{
		// read Mime Object
		HANDLE hFile = ::CreateFile("Mail2.txt", GENERIC_READ, FILE_SHARE_READ, NULL, OPEN_EXISTING, 0, NULL);

		if (hFile != INVALID_HANDLE_VALUE)
		{
			USES_CONVERSION;

			// assign stream object
			CFileSystemStream SourceStream(hFile);

			// ask for IPersistStream
			IPersistStreamPtr pPersist;
			Decoder->QueryInterface(&pPersist);

			// fill Mime-Object
			pPersist->Load(&SourceStream);

			// throw the subject
			AfxMessageBox(CString("Subject is: ") + OLE2CT(Decoder->Subject.bstrVal));

			// change the subject
			Decoder->Subject = L"Extended Test";

			// get root body object...
			IMimeBodyPtr Body = Decoder->Body;

			// get the text part, wich is the first part
			IMimeBodyPtr TextPart = Body->Item[0];

			// throw the content
			AfxMessageBox(CString("Body-Text is: ") + OLE2CT(TextPart->Value));

			// change the text part
			TextPart->Value = TextPart->Value + "\r\nNice, isn't it?";

			// get the pic part, wich is the second part
			IMimeBodyPtr PicturePart = Body->Item[1];

			HANDLE hExportFile = ::CreateFile("Copy of paradise.jpg", GENERIC_WRITE, 0, NULL, CREATE_ALWAYS, 0, NULL);

			if (hExportFile != INVALID_HANDLE_VALUE)
			{
				CFileSystemStream ResultStream(hExportFile);

				// export the content to a stream
				PicturePart->Export(&ResultStream);
			}
			// "Send" the chenged email to a stream
			HANDLE hNewFile = ::CreateFile("Mail3.txt", GENERIC_WRITE, 0, NULL, CREATE_ALWAYS, 0, NULL);

			if (hNewFile != INVALID_HANDLE_VALUE)
			{
				CFileSystemStream ResultStream(hNewFile);

				// ask for IPersistStream
				IPersistStreamPtr pPersist;
				Decoder->QueryInterface(&pPersist);

				pPersist->Save(&ResultStream, FALSE);

				AfxMessageBox("Done!", MB_ICONINFORMATION);
			}
			else
			{
				AfxMessageBox("File couldn't be created!");
			}
		}
		else
		{
			AfxMessageBox("The file couldn't be opened. (run \"Encode 2\" first!)");
		}
	}
	else
	{
		AfxMessageBox("Mime-Object couldn't be created! COM-DLL \"MimeSniffer.dll\" registered?");
	}
}

