#if !defined(AFX_SOCK_H__4D25E1C9_3D0F_4E4A_8E3D_3B85356AB522__INCLUDED_)
#define AFX_SOCK_H__4D25E1C9_3D0F_4E4A_8E3D_3B85356AB522__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// sock.h : header file
//



/////////////////////////////////////////////////////////////////////////////
// sock command target

class sock : public CSocket
{
	DECLARE_DYNAMIC(sock);
// Attributes
public:

// Operations
public:
	sock();
	virtual ~sock();

// Overrides
public:
	int iFromPort;
	CString strFrom;
	CArchive*  m_pArchiveOut;
	CArchive*  m_pArchiveIn;
	CSocketFile*  m_pFile;
	void Init();
	int fromlen;
	struct sockaddr_in from;
	void SendMsg(CString & strText);
	bool CreateSocket(LPCTSTR lpszAddress,UINT nPort);
//	int rcvMsgIndex;
	int m_uiBasePort;
	CString m_strRemoteIP;
	CString m_strLocalIP;
	BYTE m_byteRemotePort;
	BYTE m_byteLocalPort;
	sock(CString localIP,UINT localPort,CString remoteIP,UINT remotePort);
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(sock)
	public:
	virtual void OnReceive(int nErrorCode);
	virtual void OnAccept(int nErrorCode);
	virtual void OnClose(int nErrorCode);
	//}}AFX_VIRTUAL

	// Generated message map functions
	//{{AFX_MSG(sock)
		// NOTE - the ClassWizard will add and remove member functions here.
	//}}AFX_MSG

// Implementation
protected:
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_SOCK_H__4D25E1C9_3D0F_4E4A_8E3D_3B85356AB522__INCLUDED_)
