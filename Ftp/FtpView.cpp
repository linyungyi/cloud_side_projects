// FtpView.cpp : implementation of the
// CFtpView class
//

#include "stdafx.h"
#include "Ftp.h"

#include "FtpDoc.h"
#include "FtpView.h"

#include "FtpFileDlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CFtpView

IMPLEMENT_DYNCREATE(CFtpView, CView)

BEGIN_MESSAGE_MAP(CFtpView, CView)
	//{{AFX_MSG_MAP(CFtpView)
	ON_COMMAND(ID_FILE_GETFTPFILE, OnFileGetftpfile)
	ON_COMMAND(ID_FILE_SENDFTPFILE, OnFileSendftpfile)
	ON_COMMAND(ID_FILE_FTPDIRECTORY, OnFileFtpdirectory)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CFtpView construction/destruction

CFtpView::CFtpView()
{
}

CFtpView::~CFtpView()
{
}

BOOL CFtpView::PreCreateWindow(CREATESTRUCT& cs)
{
	return CView::PreCreateWindow(cs);
}

/////////////////////////////////////////////////////////////////////////////
// CFtpView drawing

void CFtpView::OnDraw(CDC* pDC)
{
	CFtpDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
}

/////////////////////////////////////////////////////////////////////////////
// CFtpView diagnostics

#ifdef _DEBUG
void CFtpView::AssertValid() const
{
	CView::AssertValid();
}

void CFtpView::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

CFtpDoc* CFtpView::GetDocument() // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CFtpDoc)));
	return (CFtpDoc*)m_pDocument;
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CFtpView message handlers

void CFtpView::OnFileGetftpfile() 
{

	FTP_INFO *lpInfo = GetParams( FALSE, TRUE );

	if( lpInfo != NULL ){
		// Kick off the thread.
		AfxBeginThread( CFtpView::FileThread,
			(LPVOID) lpInfo );
		}

}

void CFtpView::OnFileSendftpfile() 
{

	FTP_INFO *lpInfo = GetParams( TRUE, TRUE );

	if( lpInfo != NULL ){
		// Kick off the thread.
		AfxBeginThread( CFtpView::FileThread,
			(LPVOID) lpInfo );
		}

}

void CFtpView::OnFileFtpdirectory() 
{

	FTP_INFO *lpInfo = GetParams( FALSE, FALSE );

	if( lpInfo != NULL ){

		// Allocate memory into which the
		// directory will be copied.
		lpInfo->hGlobal =
			GlobalAlloc( GMEM_MOVEABLE, 20000 );

		// Kick off the thread.
		AfxBeginThread( CFtpView::DirectoryThread,
			(LPVOID) lpInfo );
		}

}

FTP_INFO *CFtpView::GetParams( BOOL bSendFlag,
	BOOL bFileFlag )
{
	FTP_INFO *lpInfo = NULL;

	CFtpFileDlg FileDlg;
	
	// Set the dialog flag that grays
	// out the file items since we
	// don't need them specified for a
	// directory.
	FileDlg.m_bGrayOutFileItems = !bFileFlag;

	// Invoke the dialog that'll allow
	// user to enter in information for
	// the Ftp transfer.
	if( FileDlg.DoModal() == IDOK ){

		// Allocate a structure.
		lpInfo = new FTP_INFO;

		// Copy the Ftp file into
		// the structure.
		strcpy( lpInfo->szFtpFile,
			FileDlg.m_strFtpFile );

		// Copy the Ftp directory into
		// the structure.
		strcpy( lpInfo->szFtpDirectory,
			FileDlg.m_strFtpDir );

		// Copy the Local file into
		// the structure.
		strcpy( lpInfo->szLocalFile,
			FileDlg.m_strLocalFile );

		// Copy the password into
		// the structure.
		strcpy( lpInfo->szPassword,
			FileDlg.m_strPassword );

		// Copy the host name into
		// the structure.
		strcpy( lpInfo->szHost,
			FileDlg.m_strServer );

		// Copy the user name into
		// the structure.
		strcpy( lpInfo->szUser,
			FileDlg.m_strUser );

		// Copy the application name into
		// the structure.
		strcpy( lpInfo->szAppName,
			AfxGetAppName() );

		// Set our flag to indicate
		// we'll be receiving.
		lpInfo->bSendFlag = FALSE;

		// Set our flag to indicate
		// we'll be sending or receiving.
		lpInfo->bSendFlag = bSendFlag;

		}

	return( lpInfo );

}

UINT CFtpView::FileThread( LPVOID lpInf )
{
	FTP_INFO *lpInfo = (FTP_INFO *) lpInf;

	CInternetSession *lpInetSession;
	CFtpConnection *lpFtpConnection;

	// Allocate a CInternetSession object.
	lpInetSession = new CInternetSession(
		lpInfo->szAppName, 1,
		PRE_CONFIG_INTERNET_ACCESS );

	// If the CInternetSession object didn't
	// allocate, bail out.
	if( lpInetSession == NULL ){
		delete lpInfo;
		return( 0 );
		}

	// Attempt to make the Ftp connection.	
	try{

		// We'll use a NULL pointer for the
		// user name since GetFtpConnection()
		// will use a default value if it
		// gets a NULL for it. If a user
		// name was given by user, go ahead and
		// point to that string.
		char *lpUser = NULL;
		if( lpInfo->szUser[0] != 0 )
			lpUser = lpInfo->szUser;

		// The same thing happens here for the
		// password. Use a NULL pointer unless
		// user gave a password.
		char *lpPassword = NULL;
		if( lpInfo->szPassword[0] != 0 )
			lpPassword = lpInfo->szPassword;

		lpFtpConnection =
			lpInetSession->GetFtpConnection(
				lpInfo->szHost, lpUser, lpPassword );
		}

	// If GetFtpConnection() throws an exception,
	// catch it, clean up, and return.
	catch( CInternetException *lpEx ){
		lpEx->Delete();
		delete lpInetSession;
		delete lpInfo;
		return( 0 );
		}

	CString strCurDir;
	lpFtpConnection->GetCurrentDirectory( strCurDir );
	AfxMessageBox(strCurDir);
	// If user specified an Ftp directory, set to that
	// directory.
	if( lpInfo->szFtpDirectory[0] != 0 )
		lpFtpConnection->SetCurrentDirectory(
		lpInfo->szFtpDirectory );
	else
		lpFtpConnection->SetCurrentDirectory(
		strCurDir );

	if( lpInfo->bSendFlag ){
		// Attempt to send the file from
		// the local machine to the remote server.
		if( lpFtpConnection->PutFile( lpInfo->szLocalFile,
			lpInfo->szFtpFile ) )
			AfxMessageBox( "The file was sent." );
		else
			AfxMessageBox( "The file wasn't sent." );

		}
	else{
		// Attempt to retrieve the file from
		// the remote server to the local machine.
		if( lpFtpConnection->GetFile( lpInfo->szFtpFile,
			lpInfo->szLocalFile ) )
			AfxMessageBox( "The file was retrieved." );
		else
			AfxMessageBox( "The file wasn't retrieved." );

		}

	// Close the Ftp connection and delete
	// the CFtpConnection and CInternetSession
	// objects.
	lpFtpConnection->Close();
	delete lpFtpConnection;
	delete lpInetSession;
	delete lpInfo;

	return( 0 );

}

UINT CFtpView::DirectoryThread( LPVOID lpInf )
{
	FTP_INFO *lpInfo = (FTP_INFO *) lpInf;

	int nFileCount = 0;

	CInternetSession *lpInetSession;
	CFtpConnection *lpFtpConnection;

	// Allocate a CInternetSession object.
	lpInetSession = new CInternetSession(
		lpInfo->szAppName, 1,
		PRE_CONFIG_INTERNET_ACCESS );

	// If the CInternetSession object didn't
	// allocate, bail out.
	if( lpInetSession == NULL ){
		delete lpInfo;
		return( 0 );
		}

	// Attempt to make the Ftp connection.	
	try{

		// We'll use a NULL pointer for the
		// user name since GetFtpConnection()
		// will use a default value if it
		// gets a NULL for it. If a user
		// name was given by user, go ahead and
		// point to that string.
		char *lpUser = NULL;
		if( lpInfo->szUser[0] != 0 )
			lpUser = lpInfo->szUser;

		// The same thing happens here for the
		// password. Use a NULL pointer unless
		// user gave a password.
		char *lpPassword = NULL;
		if( lpInfo->szPassword[0] != 0 )
			lpPassword = lpInfo->szPassword;

		lpFtpConnection =
			lpInetSession->GetFtpConnection(
				lpInfo->szHost, lpUser, lpPassword );
		}

	// If GetFtpConnection() throws an exception,
	// catch it, clean up, and return.
	catch( CInternetException *lpEx ){
		lpEx->Delete();
		delete lpInetSession;
		delete lpInfo;
		return( 0 );
		}

	// If user specified an Ftp directory, set to that
	// directory.
	if( lpInfo->szFtpDirectory[0] != 0 )
		lpFtpConnection->SetCurrentDirectory(
		lpInfo->szFtpDirectory );

	// Create a new CFtpFileFind object.
	CFtpFileFind *lpFtpFind	=
		new CFtpFileFind( lpFtpConnection );
	if( lpFtpFind == NULL )
		goto GetDirBailout;

	// Look for the first file.
	BOOL bContinue;
	bContinue = lpFtpFind->FindFile( "*" );

	// If there aren't any files,
	// bail out.
	if( !bContinue )
		goto GetDirBailout;

	// While files are found, stay in this
	// loop.
	while( bContinue ){

		// Increment the file counter.
		nFileCount++;

		// Get the next file.
		bContinue = lpFtpFind->FindNextFile();

		// Get the file name.
		CString strFilename = lpFtpFind->GetFileName();

		// Bracket the file name if it's
		// a directory and add a CR.
		if( lpFtpFind->IsDirectory() )
			strFilename = "[" + strFilename + "]";
		strFilename += "\15";

		// Lock the memory and concatenate
		// this file name to the list.
		char *pDest =
			(char *) GlobalLock( lpInfo->hGlobal );
		if( pDest != NULL ){
			strcat( pDest, strFilename );
			GlobalUnlock( lpInfo->hGlobal );
			}

		}

	if( nFileCount < 10 ){
		char *pDest =
			(char *) GlobalLock( lpInfo->hGlobal );
		if( pDest != NULL ){
			AfxMessageBox( pDest );
			GlobalUnlock( lpInfo->hGlobal );
			}
		}
	else
		AfxMessageBox( "Too many entries." );

	// Close the Ftp find object and delete it.
	lpFtpFind->Close();
	delete lpFtpFind;

GetDirBailout:
	// Close the Ftp connection and delete
	// the CFtpConnection and CInternetSession
	// objects.
	lpFtpConnection->Close();
	delete lpFtpConnection;
	delete lpInetSession;
	delete lpInfo;

	return( 0 );

}
