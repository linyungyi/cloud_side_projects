// Pop3Monitor.h : main header file for the POP3MONITOR application
//

#if !defined(AFX_POP3MONITOR_H__A92A51CA_40D0_4C75_86C7_05C11E420D37__INCLUDED_)
#define AFX_POP3MONITOR_H__A92A51CA_40D0_4C75_86C7_05C11E420D37__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"		// main symbols

#define WM_DLG_SOCKET_MESSAGE WM_USER+400 //定义网络事件 

/////////////////////////////////////////////////////////////////////////////
// CPop3MonitorApp:
// See Pop3Monitor.cpp for the implementation of this class
//

class CPop3MonitorApp : public CWinApp
{
public:
	CPop3MonitorApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CPop3MonitorApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

	//{{AFX_MSG(CPop3MonitorApp)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_POP3MONITOR_H__A92A51CA_40D0_4C75_86C7_05C11E420D37__INCLUDED_)
