// ISSHelper.h: interface for the CISSHelper class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ISSHELPER_H__7B88E5F3_263F_11D2_9D1F_00C04F96B8B2__INCLUDED_)
#define AFX_ISSHELPER_H__7B88E5F3_263F_11D2_9D1F_00C04F96B8B2__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#define POS_OF_STREAM(lv,stream)	{																	\
										ULARGE_INTEGER	__lResult;										\
										LARGE_INTEGER	__lSet;											\
										memset(&__lSet, 0, sizeof(__lSet));								\
										if (stream->Seek(__lSet, STREAM_SEEK_CUR, &__lResult) == S_OK)	\
										{																\
											lv = __lResult.u.LowPart;									\
										}																\
									}

#define LENGTH_OF_STREAM(lv,stream)	{																	\
										LARGE_INTEGER	__lCur;										\
										LARGE_INTEGER	__lZero;											\
										memset(&__lZero, 0, sizeof(__lZero));								\
										if (stream->Seek(__lZero, STREAM_SEEK_CUR, (ULARGE_INTEGER*)&__lCur) == S_OK)	\
										{																\
											ULARGE_INTEGER	__lResult;										\
											if (stream->Seek(__lZero, STREAM_SEEK_END, &__lResult) == S_OK)	\
											{																\
												lv = __lResult.u.LowPart;									\
												stream->Seek(__lCur, STREAM_SEEK_SET, NULL);				\
											}																\
										}																\
									}

#define SEEK_TO_BEGIN(stream)		{																	\
										LARGE_INTEGER	__lSet;											\
										memset(&__lSet, 0, sizeof(__lSet));								\
										stream->Seek(__lSet, STREAM_SEEK_SET, NULL);					\
									}

#define	SETSIZEOF_STREAM(lv,stream)	{																	\
										ULARGE_INTEGER	__lSet;											\
										memset(&__lSet, 0, sizeof(__lSet));								\
										__lSet.u.LowPart = lv;											\
										stream->SetSize(__lSet);										\
									}

class CISSHelper : public IStream  
{
public:

	// Constructor/destructor.
	CISSHelper(bool bDeleteOnRelease = false);
	CISSHelper(const CISSHelper& h);

	virtual ~CISSHelper();

	// Helper function to clean up memory.
	virtual void Clear();

	static BOOL PersistObjectToString(IPersistStream* pPersist, char** ppResult, int* pLen = NULL);
	static BOOL PersistObjectFromString(IPersistStream* pPersist, const char* pBuf);
	static BOOL PersistObjectToString(IPersistStreamInit* pPersist, char** ppResult, int* pLen = NULL);
	static BOOL PersistObjectFromString(IPersistStreamInit* pPersist, const char* pBuf);

	// ISequentialStream interface implementation.
	STDMETHODIMP_(ULONG)	AddRef(void);
	STDMETHODIMP_(ULONG)	Release(void);
	STDMETHODIMP QueryInterface(REFIID riid, LPVOID *ppv);
    STDMETHODIMP Read( 
            /* [out] */ void __RPC_FAR *pv,
            /* [in] */ ULONG cb,
            /* [out] */ ULONG __RPC_FAR *pcbRead);
    STDMETHODIMP Write( 
            /* [in] */ const void __RPC_FAR *pv,
            /* [in] */ ULONG cb,
            /* [out] */ ULONG __RPC_FAR *pcbWritten);

	STDMETHODIMP Seek( 
            /* [in] */ LARGE_INTEGER dlibMove,
            /* [in] */ DWORD dwOrigin,
            /* [out] */ ULARGE_INTEGER __RPC_FAR *plibNewPosition);
        
    STDMETHODIMP SetSize( 
        /* [in] */ ULARGE_INTEGER libNewSize);
    
    STDMETHODIMP CopyTo( 
        /* [unique][in] */ IStream __RPC_FAR *pstm,
        /* [in] */ ULARGE_INTEGER cb,
        /* [out] */ ULARGE_INTEGER __RPC_FAR *pcbRead,
        /* [out] */ ULARGE_INTEGER __RPC_FAR *pcbWritten);
    
    STDMETHODIMP Commit( 
        /* [in] */ DWORD grfCommitFlags)  
	{
		ATLTRACE(_T("CISSHelper::Commit\n"));
		return E_NOTIMPL;
	}
    
    STDMETHODIMP Revert( void)  
	{
		ATLTRACE(_T("CISSHelper::Revert\n"));
		return E_NOTIMPL;
	}
    
    STDMETHODIMP LockRegion( 
        /* [in] */ ULARGE_INTEGER libOffset,
        /* [in] */ ULARGE_INTEGER cb,
        /* [in] */ DWORD dwLockType)  
	{
		ATLTRACE(_T("CISSHelper::LockRegion\n"));
		return E_NOTIMPL;
	}
    
    STDMETHODIMP UnlockRegion( 
        /* [in] */ ULARGE_INTEGER libOffset,
        /* [in] */ ULARGE_INTEGER cb,
        /* [in] */ DWORD dwLockType)  
	{
		ATLTRACE(_T("CISSHelper::UnlockRegion\n"));
		return E_NOTIMPL;
	}
    
    STDMETHODIMP Stat( 
        /* [out] */ STATSTG __RPC_FAR *pstatstg,
        /* [in] */ DWORD grfStatFlag)  
	{
		ATLTRACE(_T("CISSHelper::Stat\n"));
		return E_NOTIMPL;
	}
    
    STDMETHODIMP Clone( 
        /* [out] */ IStream __RPC_FAR *__RPC_FAR *ppstm);
    
public:
	CISSHelper& operator = (ISequentialStream* pStream);
	CISSHelper& operator = (IStream* pStream);
	CISSHelper& operator += (IStream* pStream);
	CISSHelper& operator = (const CISSHelper& h);

public:

	void*       m_pBuffer;		// Buffer
	ULONG       m_ulLength;     // Total buffer size.
	ULONG		m_ulSize;		// Buffer Size
	ULONG		m_ulBulkAlloc;	// Let's the Buffer grow in bigger steps
	ULONG		m_nExpFactor;	// Factor of bulk grow

private:

	ULONG		m_cRef;			// Reference count (not used).
	ULONG       m_iReadPos;     // Current index position for reading from the buffer.
	ULONG       m_iWritePos;    // Current index position for writing to the buffer.
	bool		m_bDeleteOnRelease;
	ULONG		m_nReallocCount;
};


#endif // !defined(AFX_ISSHELPER_H__7B88E5F3_263F_11D2_9D1F_00C04F96B8B2__INCLUDED_)
