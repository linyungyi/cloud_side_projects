// Ftp.h : main header file for the FTP application
//

#if !defined(AFX_FTP_H__AEA00303_C551_11D1_B07C_0060083CFB2D__INCLUDED_)
#define AFX_FTP_H__AEA00303_C551_11D1_B07C_0060083CFB2D__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"       // main symbols

/////////////////////////////////////////////////////////////////////////////
// CFtpApp:
// See Ftp.cpp for the implementation of this class
//

class CFtpApp : public CWinApp
{
public:
	CFtpApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CFtpApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

	//{{AFX_MSG(CFtpApp)
	afx_msg void OnAppAbout();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Developer Studio will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_FTP_H__AEA00303_C551_11D1_B07C_0060083CFB2D__INCLUDED_)
