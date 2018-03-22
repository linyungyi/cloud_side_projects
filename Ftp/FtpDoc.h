// FtpDoc.h : interface of the CFtpDoc class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_FTPDOC_H__AEA00309_C551_11D1_B07C_0060083CFB2D__INCLUDED_)
#define AFX_FTPDOC_H__AEA00309_C551_11D1_B07C_0060083CFB2D__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000


class CFtpDoc : public CDocument
{
protected: // create from serialization only
	CFtpDoc();
	DECLARE_DYNCREATE(CFtpDoc)

// Attributes
public:

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CFtpDoc)
	public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CFtpDoc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CFtpDoc)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Developer Studio will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_FTPDOC_H__AEA00309_C551_11D1_B07C_0060083CFB2D__INCLUDED_)
