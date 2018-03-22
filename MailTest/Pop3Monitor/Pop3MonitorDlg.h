// Pop3MonitorDlg.h : header file
//

#if !defined(AFX_POP3MONITORDLG_H__72EFD127_9AF4_4F1D_8E40_412257910199__INCLUDED_)
#define AFX_POP3MONITORDLG_H__72EFD127_9AF4_4F1D_8E40_412257910199__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "pop3.h"

/////////////////////////////////////////////////////////////////////////////
// CPop3MonitorDlg dialog

class CPop3MonitorDlg : public CDialog
{
// Construction
public:
	//DWORD PrecessSocketMessage(WPARAM wParam, LPARAM lParam);
	CPop3MonitorDlg(CWnd* pParent = NULL);	// standard constructor

// Dialog Data
	//{{AFX_DATA(CPop3MonitorDlg)
	enum { IDD = IDD_POP3MONITOR_DIALOG };
	CString	m_edtMsg;
	CString	m_edtPassword;
	CString	m_edtServer;
	CString	m_edtUser;
	int		m_edtMailNum;
	BOOL	m_chkAuth;
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CPop3MonitorDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	HICON m_hIcon;
	CPop3 m_pop3handle;
	HANDLE hThread;

	// Generated message map functions
	//{{AFX_MSG(CPop3MonitorDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	virtual void OnOK();
	afx_msg void OnClose();
	afx_msg void OnBtnConnect();
	virtual void OnCancel();
	afx_msg void OnBtnList();
	afx_msg void OnBtnCreate();
	afx_msg void OnBtnClose();
	afx_msg void OnBtnGetHeader();
	afx_msg void OnBtnGet();
	afx_msg void OnBtnStat();
	afx_msg void OnBtnSize();
	afx_msg void OnBtnSubject();
	afx_msg void OnBtnSender();
	afx_msg void OnBtnReceiver();
	afx_msg void OnBtnDate();
	afx_msg void OnBtnDelete();
	afx_msg void OnBtnReset();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
private:
	void GetFile();
	void GetMemory();
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_POP3MONITORDLG_H__72EFD127_9AF4_4F1D_8E40_412257910199__INCLUDED_)
