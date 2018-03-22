// FtpDoc.cpp : implementation of the CFtpDoc class
//

#include "stdafx.h"
#include "Ftp.h"

#include "FtpDoc.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CFtpDoc

IMPLEMENT_DYNCREATE(CFtpDoc, CDocument)

BEGIN_MESSAGE_MAP(CFtpDoc, CDocument)
	//{{AFX_MSG_MAP(CFtpDoc)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CFtpDoc construction/destruction

CFtpDoc::CFtpDoc()
{
}

CFtpDoc::~CFtpDoc()
{
}

BOOL CFtpDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	return TRUE;
}



/////////////////////////////////////////////////////////////////////////////
// CFtpDoc serialization

void CFtpDoc::Serialize(CArchive& ar)
{
	if (ar.IsStoring())
	{
	}
	else
	{
	}
}

/////////////////////////////////////////////////////////////////////////////
// CFtpDoc diagnostics

#ifdef _DEBUG
void CFtpDoc::AssertValid() const
{
	CDocument::AssertValid();
}

void CFtpDoc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CFtpDoc commands
