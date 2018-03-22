// FtpView.h : interface of the CFtpView class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_FTPVIEW_H__AEA0030B_C551_11D1_B07C_0060083CFB2D__INCLUDED_)
#define AFX_FTPVIEW_H__AEA0030B_C551_11D1_B07C_0060083CFB2D__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000

#include <afxinet.h>

typedef struct{
	char szFtpFile[300];
	char szFtpDirectory[300];
	char szLocalFile[300];
	char szPassword[300];
	char szHost[300];
	char szUser[300];
	char szAppName[300];
	BOOL bSendFlag;
	HGLOBAL hGlobal;
	BOOL bDoneFlag;
} FTP_INFO;

class CFtpView : public CView
{
protected: // create from serialization only
	CFtpView();
	DECLARE_DYNCREATE(CFtpView)

// Attributes
public:
	CFtpDoc* GetDocument();

	FTP_INFO *GetParams( BOOL, BOOL );

	static UINT FileThread( LPVOID );
	static UINT DirectoryThread( LPVOID );

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CFtpView)
	public:
	virtual void OnDraw(CDC* pDC);  // overridden to draw this view
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	protected:
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CFtpView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CFtpView)
	afx_msg void OnFileGetftpfile();
	afx_msg void OnFileSendftpfile();
	afx_msg void OnFileFtpdirectory();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

#ifndef _DEBUG  // debug version in FtpView.cpp
inline CFtpDoc* CFtpView::GetDocument()
   { return (CFtpDoc*)m_pDocument; }
#endif

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Developer Studio will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_FTPVIEW_H__AEA0030B_C551_11D1_B07C_0060083CFB2D__INCLUDED_)
