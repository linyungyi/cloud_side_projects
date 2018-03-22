#if !defined(AFX_FTPFILEDLG_H__AEA00312_C551_11D1_B07C_0060083CFB2D__INCLUDED_)
#define AFX_FTPFILEDLG_H__AEA00312_C551_11D1_B07C_0060083CFB2D__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000
// FtpFileDlg.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CFtpFileDlg dialog

class CFtpFileDlg : public CDialog
{
// Construction
public:
	CFtpFileDlg(CWnd* pParent = NULL);   // standard constructor

	void GrayOutFileItems( void );

	BOOL m_bGrayOutFileItems;

// Dialog Data
	//{{AFX_DATA(CFtpFileDlg)
	enum { IDD = IDD_FTPFILE };
	CString	m_strFtpFile;
	CString	m_strFtpDir;
	CString	m_strLocalFile;
	CString	m_strPassword;
	CString	m_strServer;
	CString	m_strUser;
	//}}AFX_DATA


// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CFtpFileDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CFtpFileDlg)
	virtual BOOL OnInitDialog();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Developer Studio will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_FTPFILEDLG_H__AEA00312_C551_11D1_B07C_0060083CFB2D__INCLUDED_)
