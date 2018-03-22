// sockagentDlg.h : header file
//

#if !defined(AFX_SOCKAGENTDLG_H__9F87BF2B_A7C9_47D8_AD51_AEAC7F688625__INCLUDED_)
#define AFX_SOCKAGENTDLG_H__9F87BF2B_A7C9_47D8_AD51_AEAC7F688625__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

/////////////////////////////////////////////////////////////////////////////
// CSockagentDlg dialog

class CSockagentDlg : public CDialog
{
// Construction
public:
	void getIP(CIPAddressCtrl* ctrlIP,CString* strIP);
//	UINT uiRemotePort;
//	UINT uiLocalPort;
	CString strRemoteIP;
	CString strLocalIP;
	CSockagentDlg(CWnd* pParent = NULL);	// standard constructor

// Dialog Data
	//{{AFX_DATA(CSockagentDlg)
	enum { IDD = IDD_SOCKAGENT_DIALOG };
	CIPAddressCtrl	m_addRemoteIP;
	CIPAddressCtrl	m_addLocalIP;
	UINT	m_uiLocalID;
	UINT	m_uiRemoteID;
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CSockagentDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	HICON m_hIcon;

	// Generated message map functions
	//{{AFX_MSG(CSockagentDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	afx_msg void OnButtonTest();
	afx_msg void OnTimer(UINT nIDEvent);
	virtual void OnOK();
	afx_msg void OnButtonSend();
	afx_msg void OnButtonSet();
	virtual void OnCancel();
	afx_msg void OnButtonClose();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_SOCKAGENTDLG_H__9F87BF2B_A7C9_47D8_AD51_AEAC7F688625__INCLUDED_)
