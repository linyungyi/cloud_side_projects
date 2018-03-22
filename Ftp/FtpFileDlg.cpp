// FtpFileDlg.cpp : implementation file
//

#include "stdafx.h"
#include "Ftp.h"
#include "FtpFileDlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CFtpFileDlg dialog


CFtpFileDlg::CFtpFileDlg(CWnd* pParent /*=NULL*/)
	: CDialog(CFtpFileDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CFtpFileDlg)
	m_strFtpFile = _T("");
	m_strFtpDir = _T("");
	m_strLocalFile = _T("");
	m_strPassword = _T("");
	m_strServer = _T("");
	m_strUser = _T("");
	//}}AFX_DATA_INIT

	m_bGrayOutFileItems = FALSE;

}


void CFtpFileDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CFtpFileDlg)
	DDX_Text(pDX, IDC_FILENAME, m_strFtpFile);
	DDX_Text(pDX, IDC_FTPDIRECTORY, m_strFtpDir);
	DDX_Text(pDX, IDC_LOCALFILE, m_strLocalFile);
	DDX_Text(pDX, IDC_PASSWORD, m_strPassword);
	DDX_Text(pDX, IDC_SERVER, m_strServer);
	DDX_Text(pDX, IDC_USER, m_strUser);
	//}}AFX_DATA_MAP
}

void CFtpFileDlg::GrayOutFileItems( void )
{
	static int nID[] = { IDC_STATIC1, IDC_STATIC2,
		IDC_LOCALFILE, IDC_FILENAME };

	for( int i=0; i<4; i++ ){
		CWnd *pWnd = GetDlgItem( nID[i] );
		if( pWnd != NULL )
			pWnd->EnableWindow( FALSE );
		}

}

BEGIN_MESSAGE_MAP(CFtpFileDlg, CDialog)
	//{{AFX_MSG_MAP(CFtpFileDlg)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CFtpFileDlg message handlers

BOOL CFtpFileDlg::OnInitDialog() 
{
	CDialog::OnInitDialog();
	
	if( m_bGrayOutFileItems )
		GrayOutFileItems();

	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}
