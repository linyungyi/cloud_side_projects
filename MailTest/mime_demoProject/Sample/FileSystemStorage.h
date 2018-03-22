/////////////////////////////////////////////////////////
// FileSystemStorage.h
//
// CFileSystemStorage


class CFileSystemStream  : public IStream
{
protected:
	inline void __FlushWriteBuffer()
	{
		if (m_pWriteBuf != NULL && m_nWriteBufPos > 0)
		{
			SetFilePointer(m_hFile, m_iWritePos, NULL, FILE_BEGIN);

			DWORD dwWritten = 0;

			WriteFile(m_hFile, m_pWriteBuf, m_nWriteBufPos, &dwWritten, NULL);

			m_iWritePos += dwWritten;
			m_nWriteBufPos = 0;
		}
	}
public:
	static void Grf2Sam(DWORD grfMode, DWORD& dwDesiredAccess, DWORD& dwShareMode, BOOL& bCreate)
	{
		dwDesiredAccess = GENERIC_READ;

		bCreate = FALSE;

		if		((grfMode & STGM_WRITE) == STGM_WRITE)
		   dwDesiredAccess = GENERIC_WRITE;
		else if ((grfMode & STGM_READWRITE) == STGM_READWRITE)
		   dwDesiredAccess = GENERIC_READ|GENERIC_WRITE;

		if (grfMode & STGM_CREATE)
			bCreate = TRUE;

		dwShareMode = 0;

		if		((grfMode & STGM_SHARE_DENY_NONE) == STGM_SHARE_DENY_NONE)
			dwShareMode = FILE_SHARE_READ|FILE_SHARE_WRITE;
		if		((grfMode & STGM_SHARE_DENY_READ) == STGM_SHARE_DENY_READ)
			dwShareMode = FILE_SHARE_WRITE;
		if		((grfMode & STGM_SHARE_DENY_WRITE)== STGM_SHARE_DENY_WRITE)
			dwShareMode = FILE_SHARE_READ;
		if		((grfMode & STGM_SHARE_EXCLUSIVE) == STGM_SHARE_EXCLUSIVE)
			dwShareMode = 0;

	}
	CFileSystemStream(HANDLE hFile)
	{
		m_nRef			= 1;
		m_hFile			= hFile;
		m_iReadPos		= 0;
		m_iWritePos		= 0;
		m_pWriteBuf		= NULL;
		m_nWriteBufPos	= 0;
		m_pReadBuf		= NULL;
		m_nReadBufSize	= 0;
		m_nReadBufPos	= 0;
		m_bAutoClose	= true;
	}
	virtual ~CFileSystemStream()
	{
		__FlushWriteBuffer();

		if (m_pWriteBuf != NULL)
			delete [] m_pWriteBuf;

		if (m_pReadBuf != NULL)
			delete [] m_pReadBuf;

		if (m_bAutoClose)
			CloseHandle(m_hFile);
	}
	// IStream interface implementation.
	STDMETHODIMP_(ULONG)	AddRef(void)
	{
		return ++m_nRef;
	}
	STDMETHODIMP_(ULONG)	Release(void)
	{ 
		--m_nRef;

		if (m_nRef == 0)
		{
			delete this;
			return 0;
		}
		return m_nRef;
	}
	STDMETHODIMP QueryInterface(REFIID riid, LPVOID *ppv)
	{
		*ppv = NULL;

		if ( riid == IID_IUnknown )			 *ppv = this;
		if ( riid == IID_ISequentialStream ) *ppv = this;
		if ( riid == IID_IStream )			 *ppv = this;
		if ( *ppv )
		{
			AddRef();
			return S_OK;
		}
		return E_NOINTERFACE;
	}

	STDMETHODIMP Read( 
			/* [out] */ void __RPC_FAR *pv,
			/* [in] */ ULONG cb,
			/* [out] */ ULONG __RPC_FAR *pcbRead)
	{
		USES_CONVERSION;

		if ( pcbRead ) *pcbRead = 0;
		if ( !pv ) return STG_E_INVALIDPOINTER;
		if ( 0 == cb ) return S_OK; 

		DWORD	dwRead	= 0;
		HRESULT hr		= S_OK;

		while(dwRead < cb)
		{
			ULONG nFill = m_nReadBufSize-m_nReadBufPos;

			if (cb-dwRead < 4096 || nFill > 0)
			{
				if (m_pReadBuf == NULL)
				{
					m_pReadBuf		= new BYTE[4096];
					m_nReadBufSize	= 0;
					m_nReadBufPos	= 0;
				}
				if (m_nReadBufSize == 0 || nFill == 0)
				{
					SetFilePointer(m_hFile, m_iReadPos, NULL, FILE_BEGIN);

					DWORD _dwRead = 0;

					hr = ReadFile(m_hFile, m_pReadBuf, 4096, &_dwRead, NULL) ? S_OK : E_FAIL;

					if (hr == E_FAIL || _dwRead == 0)
						break;

					m_nReadBufSize	= _dwRead;
					m_nReadBufPos	= 0;
					nFill = m_nReadBufSize-m_nReadBufPos;
				}
				
				DWORD _dwRead = min(nFill, (cb-dwRead));

				memcpy(((LPBYTE)pv)+dwRead, m_pReadBuf + m_nReadBufPos, _dwRead);

				m_nReadBufPos += _dwRead;
				m_iReadPos    += _dwRead;

				dwRead += _dwRead;
			}
			else
			{
				SetFilePointer(m_hFile, m_iReadPos, NULL, FILE_BEGIN);

				DWORD _dwRead = 0;

				hr = ReadFile(m_hFile, ((LPBYTE)pv)+dwRead, cb-dwRead, &_dwRead, NULL) ? S_OK : E_FAIL;

				if (hr == E_FAIL || _dwRead == 0)
					break;

				dwRead += _dwRead;

				if (hr == S_OK)
				{
					m_iReadPos += _dwRead;
				}
			}
		}
		if ( pcbRead ) 
			*pcbRead = dwRead;

		return hr;
	}
	STDMETHODIMP Write( 
			/* [in] */ const void __RPC_FAR *pv,
			/* [in] */ ULONG cb,
			/* [out] */ ULONG __RPC_FAR *pcbWritten)
	{
		USES_CONVERSION;

		if ( !pv ) return STG_E_INVALIDPOINTER;
		if ( pcbWritten ) *pcbWritten = 0;
		if ( 0 == cb ) return S_OK;

		DWORD	dwWritten	= 0;
		HRESULT hr			= E_FAIL;

		if (cb < 4096)
		{
			if (m_nWriteBufPos + cb > 4096)
				__FlushWriteBuffer();

			if (m_pWriteBuf == NULL)
				m_pWriteBuf = new BYTE[4096];

			memcpy(m_pWriteBuf + m_nWriteBufPos, pv, cb);
			
			dwWritten = cb;
			m_nWriteBufPos += dwWritten;

			hr = S_OK;
		}
		else
		{
			__FlushWriteBuffer();

			SetFilePointer(m_hFile, m_iWritePos, NULL, FILE_BEGIN);

			hr = WriteFile(m_hFile, pv, cb, &dwWritten, NULL) ? S_OK : E_FAIL;

			if (hr == S_OK)
			{
				m_iWritePos += dwWritten;
				
				if ( pcbWritten ) *pcbWritten = dwWritten;
			}
		}
		return hr;
	}

	STDMETHODIMP Seek( 
			/* [in] */ LARGE_INTEGER dlibMove,
			/* [in] */ DWORD dwOrigin,
			/* [out] */ ULARGE_INTEGER __RPC_FAR *plibNewPosition) 
	{
		__FlushWriteBuffer();
		m_nReadBufSize	= 0;
		m_nReadBufPos	= 0;

		LONG lMove = dlibMove.u.LowPart;

		switch(dwOrigin)
		{
			case STREAM_SEEK_SET:
			{
				m_iReadPos	= lMove;
				m_iWritePos	= lMove;
			}
			break;

			case STREAM_SEEK_CUR:
			{
				m_iReadPos	+= lMove;
				m_iWritePos	+= lMove;
			}
			break;

			case STREAM_SEEK_END:
			{
				DWORD ulLength = GetFileSize(m_hFile, NULL);
				m_iReadPos	= ulLength - lMove;
				m_iWritePos	= ulLength - lMove;
			}
			break;
		}
		if (plibNewPosition != NULL)
		{
			memset(plibNewPosition, 0, sizeof(ULARGE_INTEGER));

			plibNewPosition->u.LowPart = m_iReadPos;
		}
		return S_OK;
	}
    
	STDMETHODIMP SetSize( 
		/* [in] */ ULARGE_INTEGER libNewSize)
	{ 
		return E_NOTIMPL;
	}

	STDMETHODIMP CopyTo( 
		/* [unique][in] */ IStream __RPC_FAR *pstm,
		/* [in] */ ULARGE_INTEGER cb,
		/* [out] */ ULARGE_INTEGER __RPC_FAR *pcbRead,
		/* [out] */ ULARGE_INTEGER __RPC_FAR *pcbWritten)
	{
		return E_NOTIMPL;
	}

	STDMETHODIMP Commit( 
		/* [in] */ DWORD grfCommitFlags)
	{
		return E_NOTIMPL;
	}

	STDMETHODIMP Revert( void)
	{
		return E_NOTIMPL;
	}

	STDMETHODIMP LockRegion( 
		/* [in] */ ULARGE_INTEGER libOffset,
		/* [in] */ ULARGE_INTEGER cb,
		/* [in] */ DWORD dwLockType)
	{
		return E_NOTIMPL;
	}

	STDMETHODIMP UnlockRegion( 
		/* [in] */ ULARGE_INTEGER libOffset,
		/* [in] */ ULARGE_INTEGER cb,
		/* [in] */ DWORD dwLockType)
	{
		return E_NOTIMPL;
	}

	STDMETHODIMP Stat( 
		/* [out] */ STATSTG __RPC_FAR *pstatstg,
		/* [in] */ DWORD grfStatFlag)
	{
		return E_NOTIMPL;
	}

	STDMETHODIMP Clone( 
		/* [out] */ IStream __RPC_FAR *__RPC_FAR *ppstm)
	{
		return E_NOTIMPL;
	}
public:
	DWORD		m_nRef;
	HANDLE		m_hFile;
	ULONG       m_iReadPos;     // Current index position for reading from the buffer.
	ULONG       m_iWritePos;    // Current index position for writing to the buffer.
	LPBYTE		m_pWriteBuf;
	ULONG		m_nWriteBufPos;
	LPBYTE		m_pReadBuf;
	ULONG		m_nReadBufSize;
	ULONG		m_nReadBufPos;
	bool		m_bAutoClose;
};

////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////

class CFileSystemEnumSTATSTG : public IEnumSTATSTG
{
public:
	CFileSystemEnumSTATSTG(LPCTSTR strFolder)
	{
		m_nRef		= 1;

		m_str = strFolder;

		if (m_str[m_str.length()-1] != _T('\\'))
		   m_str += _T('\\');

		m_str += _T("*.*");

		::SetLastError(0);

		m_h = ::FindFirstFile(m_str.c_str(), &m_fd);

		while(m_h != INVALID_HANDLE_VALUE && GetLastError() != ERROR_NO_MORE_FILES)
		{
			if (   (m_fd.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY )
			    && (   (_tcslen(m_fd.cFileName) == 1 && m_fd.cFileName[0] == _T('.'))
				    || (_tcslen(m_fd.cFileName) == 2 && m_fd.cFileName[0] == _T('.') && m_fd.cFileName[1] == _T('.'))
			       )
			   )
			{
			// Folders "." and ".."
			// .....
			  ::SetLastError(0);
			  ::FindNextFile(m_h, &m_fd);
			}
			else
				break;
		}
		if (GetLastError() == ERROR_NO_MORE_FILES)
		{
			if (m_h != 	INVALID_HANDLE_VALUE)
				FindClose(m_h);
				
			m_h = INVALID_HANDLE_VALUE;
		}
			
	}
	~CFileSystemEnumSTATSTG()
	{
		if (m_h != 	INVALID_HANDLE_VALUE)
			FindClose(m_h);
			
		m_h = INVALID_HANDLE_VALUE;
	}
	// IEnumSTATSTG interface implementation.
	STDMETHODIMP_(ULONG)	AddRef(void)
	{
		return ++m_nRef;
	}
	STDMETHODIMP_(ULONG)	Release(void)
	{ 
		--m_nRef;

		if (m_nRef == 0)
		{
			delete this;
			return 0;
		}
		return m_nRef;
	}
	STDMETHODIMP QueryInterface(REFIID riid, LPVOID *ppv)
	{
		*ppv = NULL;

		if ( riid == IID_IUnknown )			 *ppv = this;
		if ( riid == IID_IEnumSTATSTG )		 *ppv = this;
		if ( *ppv )
		{
			AddRef();
			return S_OK;
		}
		return E_NOINTERFACE;
	}
    STDMETHODIMP Next( 
		/* [in] */ ULONG celt,
		/* [length_is][size_is][out] */ STATSTG __RPC_FAR *rgelt,
		/* [out] */ ULONG __RPC_FAR *pceltFetched)
	{
		USES_CONVERSION;

		if (rgelt == NULL)
			return STG_E_INVALIDPOINTER;

		if (m_h == INVALID_HANDLE_VALUE)
			return E_FAIL;

		DWORD dwCount = 0;
		DWORD dwCelt  = celt;

		while(celt--)
		{
			STATSTG& statstg = rgelt[dwCount];

			memset(&statstg, 0, sizeof(STATSTG));

			if (m_fd.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY) 
			{
				statstg.type = STGTY_STORAGE;
			}
			else
			{
				statstg.type = STGTY_STREAM;
			}

			if ((statstg.pwcsName = (WCHAR*)CoTaskMemAlloc((_tcslen(m_fd.cFileName)+1)*sizeof(WCHAR))) != NULL)
					wcscpy(statstg.pwcsName, T2COLE(m_fd.cFileName));
			
			dwCount++;

			::SetLastError(0);
			
			if (!::FindNextFile(m_h, &m_fd) || GetLastError() == ERROR_NO_MORE_FILES)
			{
				FindClose(m_h);
				m_h = INVALID_HANDLE_VALUE;
				break;
			}
		}
		
		if (pceltFetched != NULL)
			*pceltFetched = dwCount;
		return dwCelt == dwCount ? S_OK : S_FALSE;
	}

	STDMETHODIMP Skip( 
		/* [in] */ ULONG celt)
	{
		if (m_h == INVALID_HANDLE_VALUE)
			return E_FAIL;

		if (celt == 0)
			return S_OK;

		while(celt--)
		{
			::SetLastError(0);
			
			if (!::FindNextFile(m_h, &m_fd) || GetLastError() == ERROR_NO_MORE_FILES)
			{
				FindClose(m_h);
				m_h = INVALID_HANDLE_VALUE;
				break;
			}
		}
		return S_OK;
	}

	STDMETHODIMP Reset( void)
	{
		if (m_h != 	INVALID_HANDLE_VALUE)
			FindClose(m_h);

		::SetLastError(0);

		m_h = ::FindFirstFile(m_str.c_str(), &m_fd);

		while(m_h != INVALID_HANDLE_VALUE && GetLastError() != ERROR_NO_MORE_FILES)
		{
			if (   (m_fd.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY )
			    && (   (_tcslen(m_fd.cFileName) == 1 && m_fd.cFileName[0] == _T('.'))
				    || (_tcslen(m_fd.cFileName) == 2 && m_fd.cFileName[0] == _T('.') && m_fd.cFileName[1] == _T('.'))
			       )
			   )
			{
			// Folders "." and ".."
			// .....
			  ::SetLastError(0);
			  ::FindNextFile(m_h, &m_fd);
			}
			else
				break;
		}
		if (GetLastError() == ERROR_NO_MORE_FILES)
		{
			if (m_h != 	INVALID_HANDLE_VALUE)
				FindClose(m_h);
				
			m_h = INVALID_HANDLE_VALUE;
		}

		return S_OK;
	}

	STDMETHODIMP Clone( 
		/* [out] */ IEnumSTATSTG __RPC_FAR *__RPC_FAR *ppenum)
	{
		return E_NOTIMPL;
	}
public:
	DWORD			m_nRef;        
	WIN32_FIND_DATA	m_fd;
	HANDLE			m_h;
	string			m_str;
};

////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////

class CFileSystemStorage : public IStorage
{
public:
	static HRESULT StgCreateFolder(LPCWSTR strFolder, DWORD grfMode, DWORD reserved, IStorage ** ppstgOpen)
	{
		USES_CONVERSION;

		if (ppstgOpen == NULL || strFolder == NULL)
			return STG_E_INVALIDPOINTER;
		
		BOOL		bCreate;
		DWORD		dwDesiredAccess;
		DWORD		dwShareMode;

		CFileSystemStream::Grf2Sam(grfMode, dwDesiredAccess, dwShareMode, bCreate);

		CFileSystemStorage* pStorage = new CFileSystemStorage(OLE2CT(strFolder), dwDesiredAccess, dwShareMode, bCreate);

		if (pStorage->m_bValid)
		{
			*ppstgOpen = pStorage;
			return S_OK;
		}
		delete pStorage;

		return E_FAIL;
	}
	static BOOL DeleteFileForEver(LPCTSTR FileName)
	{
		// write protected ?
		DWORD attrib = GetFileAttributes(FileName);

		if (attrib & FILE_ATTRIBUTE_READONLY)
		   SetFileAttributes(FileName, attrib & ~FILE_ATTRIBUTE_READONLY);

		return ::DeleteFile(FileName);
	}
	static void DeleteTree(LPCTSTR pDir)
	{
		WIN32_FIND_DATA	fd;
		HANDLE			h		= NULL;

		string			strPath(pDir);

		memset(&fd, 0, sizeof(fd));

		if (strPath[strPath.length()-1] != _T('\\'))
		   strPath += _T('\\');

		::SetLastError(0);

		if ((h = ::FindFirstFile((strPath + _T("*.*")).c_str(), &fd)) != INVALID_HANDLE_VALUE)
		{
		   while(GetLastError() != ERROR_NO_MORE_FILES)
		   {
			  if (   (fd.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY )
				  && (   (_tcslen(fd.cFileName) == 1 && fd.cFileName[0] == _T('.'))
					  || (_tcslen(fd.cFileName) == 2 && fd.cFileName[0] == _T('.') && fd.cFileName[1] == _T('.'))
					 )
				 )
			  {
			  // Folders "." and ".."
			  // .....
			  }
			  else
			  {
			  // all others ....
				 if (fd.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY && fd.cFileName[0] != _T('.'))
				 {
					DeleteTree((strPath + fd.cFileName).c_str());
				 }
				 else
				 {
					string strFile(strPath + fd.cFileName);
					
					DeleteFileForEver(strFile.c_str());
				 }
			  }
			  ::SetLastError(0);
			  ::FindNextFile(h, &fd);
		   }
		   FindClose(h);
		}
		RemoveDirectory(pDir);
	}
	static BOOL MyCreateDirectory(LPCTSTR Path)
	{
		string path(Path);
		string sub;

		string::size_type n = path.find(_T('\\'));

		while(n != string::npos)
		{
		   n = path.find(_T('\\'), n+1);

		   sub = path.substr(0, n);

		   if (!CreateDirectory(sub.c_str(), NULL))
		   {
			  if (GetLastError() != ERROR_ALREADY_EXISTS)
				 return FALSE;
		   }
		}
		return TRUE;
	}
	CFileSystemStorage(LPCTSTR strFolder, DWORD dwDesiredAccess = GENERIC_READ|GENERIC_WRITE, DWORD dwShareMode = 0, BOOL bCreate = FALSE)
	{
		m_nRef		= 1;
		m_strPath   = strFolder;

		if (m_strPath[m_strPath.length()-1] == _T('\\'))
		   m_strPath = m_strPath.substr(0, m_strPath.length()-1);

		if (bCreate)
		{
			m_bValid = MyCreateDirectory(m_strPath.c_str());
		}
		else
		{
			WIN32_FIND_DATA	fd;

			HANDLE h = ::FindFirstFile(m_strPath.c_str(), &fd);

			if (h != INVALID_HANDLE_VALUE)
			{
				FindClose(h);
				m_bValid = TRUE;
			}
			else
				m_bValid = FALSE;
		}
	}
	virtual ~CFileSystemStorage()
	{
	}
	// IStorage interface implementation.
	STDMETHODIMP_(ULONG)	AddRef(void)
	{
		return ++m_nRef;
	}
	STDMETHODIMP_(ULONG)	Release(void)
	{ 
		--m_nRef;

		if (m_nRef == 0)
		{
			delete this;
			return 0;
		}
		return m_nRef;
	}
	STDMETHODIMP QueryInterface(REFIID riid, LPVOID *ppv)
	{
		*ppv = NULL;

		if ( riid == IID_IUnknown )			 *ppv = this;
		if ( riid == IID_IStorage )			 *ppv = this;
		if ( *ppv )
		{
			AddRef();
			return S_OK;
		}
		return E_NOINTERFACE;
	}
    STDMETHODIMP CreateStream( 
        /* [string][in] */ const OLECHAR __RPC_FAR *pwcsName,
        /* [in] */ DWORD grfMode,
        /* [in] */ DWORD reserved1,
        /* [in] */ DWORD reserved2,
        /* [out] */ IStream __RPC_FAR *__RPC_FAR *ppstm)
	{
		USES_CONVERSION;

		if (!m_bValid)
			return STG_E_ACCESSDENIED;
		if (ppstm == NULL || pwcsName == NULL)
			return STG_E_INVALIDPOINTER;

		BOOL		bCreate;
		DWORD		dwDesiredAccess;
		DWORD		dwShareMode;

		CFileSystemStream::Grf2Sam(grfMode, dwDesiredAccess, dwShareMode, bCreate);

		string strFile = m_strPath + _T("\\") + OLE2CT(pwcsName);

		HANDLE hFile = CreateFile(strFile.c_str(), dwDesiredAccess, dwShareMode, NULL
											, bCreate ? CREATE_ALWAYS : OPEN_EXISTING
											, FILE_ATTRIBUTE_NORMAL
											, NULL);
		
		if (hFile != INVALID_HANDLE_VALUE)
		{
			*ppstm = new CFileSystemStream(hFile);

			return S_OK;
		}
		return E_FAIL;
	}
    
    STDMETHODIMP OpenStream( 
        /* [string][in] */ const OLECHAR __RPC_FAR *pwcsName,
        /* [unique][in] */ void __RPC_FAR *reserved1,
        /* [in] */ DWORD grfMode,
        /* [in] */ DWORD reserved2,
        /* [out] */ IStream __RPC_FAR *__RPC_FAR *ppstm)
	{
		return CreateStream(pwcsName, grfMode, 0 ,0, ppstm);
	}
    
    STDMETHODIMP CreateStorage( 
        /* [string][in] */ const OLECHAR __RPC_FAR *pwcsName,
        /* [in] */ DWORD grfMode,
        /* [in] */ DWORD reserved1,
        /* [in] */ DWORD reserved2,
        /* [out] */ IStorage __RPC_FAR *__RPC_FAR *ppstg)
	{
		USES_CONVERSION;

		if (!m_bValid)
			return STG_E_ACCESSDENIED;
		if (ppstg == NULL || pwcsName == NULL)
			return STG_E_INVALIDPOINTER;

		BOOL		bCreate;
		DWORD		dwDesiredAccess;
		DWORD		dwShareMode;

		CFileSystemStream::Grf2Sam(grfMode, dwDesiredAccess, dwShareMode, bCreate);

		string strFile = m_strPath + _T("\\") + OLE2CT(pwcsName);

		CFileSystemStorage* pStorage = new CFileSystemStorage(strFile.c_str(), dwDesiredAccess, dwShareMode, bCreate);

		if (pStorage->m_bValid)
		{
			*ppstg = pStorage;
			return S_OK;
		}
		delete pStorage;

		return E_FAIL;
	}
    
    STDMETHODIMP OpenStorage( 
        /* [string][unique][in] */ const OLECHAR __RPC_FAR *pwcsName,
        /* [unique][in] */ IStorage __RPC_FAR *pstgPriority,
        /* [in] */ DWORD grfMode,
        /* [unique][in] */ SNB snbExclude,
        /* [in] */ DWORD reserved,
        /* [out] */ IStorage __RPC_FAR *__RPC_FAR *ppstg)
	{
		return CreateStorage(pwcsName, grfMode, 0, 0, ppstg);
	}
    
    STDMETHODIMP CopyTo( 
        /* [in] */ DWORD ciidExclude,
        /* [size_is][unique][in] */ const IID __RPC_FAR *rgiidExclude,
        /* [unique][in] */ SNB snbExclude,
        /* [unique][in] */ IStorage __RPC_FAR *pstgDest)
	{
		return E_NOTIMPL;
	}
    
    STDMETHODIMP MoveElementTo( 
        /* [string][in] */ const OLECHAR __RPC_FAR *pwcsName,
        /* [unique][in] */ IStorage __RPC_FAR *pstgDest,
        /* [string][in] */ const OLECHAR __RPC_FAR *pwcsNewName,
        /* [in] */ DWORD grfFlags)
	{
		return E_NOTIMPL;
	}
    
    STDMETHODIMP Commit( 
        /* [in] */ DWORD grfCommitFlags)
	{
		return E_NOTIMPL;
	}
    
    STDMETHODIMP Revert( void)
	{
		return E_NOTIMPL;
	}
    
    STDMETHODIMP EnumElements( 
        /* [in] */ DWORD reserved1,
        /* [size_is][unique][in] */ void __RPC_FAR *reserved2,
        /* [in] */ DWORD reserved3,
        /* [out] */ IEnumSTATSTG __RPC_FAR *__RPC_FAR *ppenum)
	{
		if (!m_bValid)
			return STG_E_ACCESSDENIED;
		if (ppenum == NULL)
			return STG_E_INVALIDPOINTER;
		
		*ppenum = new CFileSystemEnumSTATSTG(m_strPath.c_str());

		return S_OK;
	}
    
    STDMETHODIMP DestroyElement( 
        /* [string][in] */ const OLECHAR __RPC_FAR *pwcsName)
	{
		USES_CONVERSION;

		if (!m_bValid)
			return STG_E_ACCESSDENIED;

		if (pwcsName == NULL)
			return STG_E_INVALIDPOINTER;
		
		string strFile = m_strPath + _T("\\") + OLE2CT(pwcsName);

		if (!DeleteFileForEver(strFile.c_str()))
		{
			DeleteTree(strFile.c_str());
		}
		return S_OK;
	}
    
    STDMETHODIMP RenameElement( 
        /* [string][in] */ const OLECHAR __RPC_FAR *pwcsOldName,
        /* [string][in] */ const OLECHAR __RPC_FAR *pwcsNewName)
	{
		return E_NOTIMPL;
	}
    
    STDMETHODIMP SetElementTimes( 
        /* [string][unique][in] */ const OLECHAR __RPC_FAR *pwcsName,
        /* [unique][in] */ const FILETIME __RPC_FAR *pctime,
        /* [unique][in] */ const FILETIME __RPC_FAR *patime,
        /* [unique][in] */ const FILETIME __RPC_FAR *pmtime)
	{
		return E_NOTIMPL;
	}
    
    STDMETHODIMP SetClass( 
        /* [in] */ REFCLSID clsid)
	{
		return E_NOTIMPL;
	}
    
    STDMETHODIMP SetStateBits( 
        /* [in] */ DWORD grfStateBits,
        /* [in] */ DWORD grfMask)
	{
		return E_NOTIMPL;
	}
    
    STDMETHODIMP Stat( 
        /* [out] */ STATSTG __RPC_FAR *pstatstg,
        /* [in] */ DWORD grfStatFlag)
	{
		return E_NOTIMPL;
	}
public:
	DWORD		m_nRef;
	string		m_strPath;
	BOOL		m_bValid;
};

