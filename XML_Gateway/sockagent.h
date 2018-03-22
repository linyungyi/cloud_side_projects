// sockagent.h : main header file for the SOCKAGENT application
//

#if !defined(AFX_SOCKAGENT_H__59A54979_2300_424F_9B42_7A8C3B6FDEBB__INCLUDED_)
#define AFX_SOCKAGENT_H__59A54979_2300_424F_9B42_7A8C3B6FDEBB__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"		// main symbols
#include "sock.h"	// Added by ClassView

/////////////////////////////////////////////////////////////////////////////
// CSockagentApp:
// See sockagent.cpp for the implementation of this class
//

class CSockagentApp : public CWinApp
{
public:
	int ClientHandler(char *byte);
	//sock *out;
	CPtrList m_connectionList;
	bool sockCreate(LPCTSTR lpszAddress, UINT nPort);
	void sockReceive(sock* client);
	void sockAccept();
	BOOL m_sockOpen;
	sock *m_pSocket;
	sock *m_pClient;
	void OutputString(CString string);
	CSockagentApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CSockagentApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

	//{{AFX_MSG(CSockagentApp)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_SOCKAGENT_H__59A54979_2300_424F_9B42_7A8C3B6FDEBB__INCLUDED_)
