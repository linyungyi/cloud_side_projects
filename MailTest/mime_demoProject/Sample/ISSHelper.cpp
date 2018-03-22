// ISSHelper.cpp: implementation of the CISSHelper class.
//
//////////////////////////////////////////////////////////////////////

#include "stdafx.h"
#include "ISSHelper.h"

/////////////////////////////////////////////////////////////////////////////

static int _CalcSize(int nSize)
{
	return 8 + nSize * 2 + 1;
}

static int _WriteTo(char* pMem, unsigned char* pData, int nSize)
{
	int nResult = sprintf(pMem, "%08X", nSize);

	for (int lauf = 0; lauf < nSize; lauf++)
	   nResult += sprintf(pMem+nResult, "%02X", (int)pData[lauf]);

	return nResult;
}

static int _ReadSize(const char* pMem)
{
	int nResult = 0;

	sscanf(pMem, "%08X", &nResult);
	
	return nResult;
}

static int _ReadFrom(const char* pMem, unsigned char* pData)
{
	int nSize = 0;

	sscanf(pMem, "%08X", &nSize);
	
	int nResult = 8;
	int nData;

	for (int lauf = 0; lauf < nSize; lauf++)
	{
	   sscanf(pMem+nResult, "%02X", &nData);
	   pData[lauf] = nData;
	   nResult += 2;
	}
	return nResult;
}

template <class T>
static BOOL _PersistObjectToString(T pPersist, char** ppResult, int* pLen)
{
	if (pPersist == NULL || ppResult == NULL)
		return FALSE;

	CISSHelper Helper;

	Helper.Clear();
	pPersist->Save(&Helper, FALSE);

	int nLen = _CalcSize(Helper.m_ulLength);

	*ppResult = new char[nLen];

	memset(*ppResult, 0, nLen);
	_WriteTo(*ppResult, (LPBYTE)Helper.m_pBuffer, Helper.m_ulLength);

	if (pLen != NULL)
		*pLen = nLen;

	return TRUE;
}

template <class T>
static BOOL _PersistObjectFromString(T pPersist, const char* pBuf)
{
	if (pPersist == NULL || pBuf == NULL)
		return FALSE;

	CISSHelper Helper;

	Helper.Clear();

	Helper.m_ulLength = _ReadSize(pBuf);
	Helper.m_pBuffer = CoTaskMemAlloc(Helper.m_ulLength);

	_ReadFrom(pBuf, (LPBYTE)Helper.m_pBuffer);

	pPersist->Load(&Helper);

	return TRUE;
}


///////////////////////////////////////////////////////////////////////////////
// Class CISSHelper
// 
// Implementation of ISequentialStream interface
///////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

CISSHelper::CISSHelper(bool bDeleteOnRelease /* = false */)
{
	ATLTRACE(_T("CISSHelper()\n"));

	m_cRef			= 0;
	m_pBuffer		= NULL;
	m_ulLength		= 0;
	m_iReadPos		= 0;
	m_iWritePos		= 0;
	m_ulBulkAlloc	= 0;
	m_nExpFactor	= 0; // if > 0 means: m_nExpFactor X m_ulSize for every realloc 
						 // (e.g. m_nExpFactor = 1 for doubleing every realloc)
	m_ulSize		= 0;
	m_nReallocCount	= 0;
	
	m_bDeleteOnRelease = bDeleteOnRelease;
}

CISSHelper::CISSHelper(const CISSHelper& h)
{
	operator=(h);

	m_bDeleteOnRelease = false;
}

CISSHelper::~CISSHelper()
{
	Clear();

	ATLTRACE(_T("~CISSHelper()\n"));
}

void CISSHelper::Clear() 
{
	if (m_pBuffer != NULL)
		CoTaskMemFree( m_pBuffer );
	m_pBuffer		= NULL;
	m_ulLength		= 0;
	m_iReadPos		= 0;
	m_iWritePos		= 0;
	m_ulSize		= 0;
	m_nReallocCount	= 0;
}

ULONG CISSHelper::AddRef(void)
{
	return ++m_cRef;
}

ULONG CISSHelper::Release(void)
{
	ULONG lResult = --m_cRef;

	if (lResult == 0 && m_bDeleteOnRelease)
		delete this;

	return lResult;
}

HRESULT CISSHelper::QueryInterface( REFIID riid, void** ppv )
{
	*ppv = NULL;
	if ( riid == IID_IUnknown )			 *ppv = this;
	if ( riid == IID_ISequentialStream ) *ppv = this;
	if ( riid == IID_IStream ) *ppv = this;
	if ( *ppv )
	{
		( (IUnknown*) *ppv )->AddRef();
		return S_OK;
	}
	return E_NOINTERFACE;
}

HRESULT CISSHelper::Read( void *pv,	ULONG cb, ULONG* pcbRead )
{
	// Check parameters.
	if ( pcbRead ) *pcbRead = 0;
	if ( !pv ) return STG_E_INVALIDPOINTER;
	if ( 0 == cb ) return S_OK; 

	// Calculate bytes left and bytes to read.
	ULONG cBytesLeft = m_ulLength - m_iReadPos;
	ULONG cBytesRead = cb > cBytesLeft ? cBytesLeft : cb;

	// If no more bytes to retrive return S_FALSE.
	if ( 0 == cBytesLeft ) return S_FALSE;

	// Copy to users buffer the number of bytes requested or remaining
	memcpy( pv, (void*)((BYTE*)m_pBuffer + m_iReadPos), cBytesRead );
	m_iReadPos += cBytesRead;

	// Return bytes read to caller.
	if ( pcbRead ) *pcbRead = cBytesRead;
	if ( cb != cBytesRead ) return S_FALSE; 

	return S_OK;
}
        
HRESULT CISSHelper::Write( const void *pv, ULONG cb, ULONG* pcbWritten )
{
	// Check parameters.
	if ( !pv ) return STG_E_INVALIDPOINTER;
	if ( pcbWritten ) *pcbWritten = 0;
	if ( 0 == cb ) return S_OK;

	// Enlarge the current buffer.
	m_ulLength += cb;

	// Grow internal buffer to new size.
	if (m_ulLength > m_ulSize)
	{
		if (m_ulBulkAlloc > 0 && m_nExpFactor > 0 && m_nReallocCount > 0)
		{
			m_ulSize = max(m_ulBulkAlloc * (m_nExpFactor << m_nReallocCount), m_ulLength);
		}
		else
		{
			if (m_ulBulkAlloc > 0)
				m_ulSize += max(m_ulBulkAlloc, (m_ulLength-m_ulSize));
			else
				m_ulSize = m_ulLength;
		}
		m_nReallocCount++;
		m_pBuffer = CoTaskMemRealloc( m_pBuffer, m_ulSize );
	}

	// Check for out of memory situation.
	if ( NULL == m_pBuffer ) 
	{
		Clear();
		return E_OUTOFMEMORY;
	}

	// Copy callers memory to internal bufffer and update write position.
	memcpy( (void*)((BYTE*)m_pBuffer + m_iWritePos), pv, cb );
	m_iWritePos += cb;

	// Return bytes written to caller.
	if ( pcbWritten ) *pcbWritten = cb;

	return S_OK;
}

HRESULT CISSHelper::Seek(LARGE_INTEGER dlibMove, DWORD dwOrigin, ULARGE_INTEGER __RPC_FAR *plibNewPosition)
{
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
			m_iReadPos	= m_ulLength - lMove;
			m_iWritePos	= m_ulLength - lMove;
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

HRESULT CISSHelper::SetSize(ULARGE_INTEGER libNewSize)
{

	if (libNewSize.u.LowPart == 0)
	{
		Clear();
		return S_OK;
	}

	if (libNewSize.u.LowPart <= m_ulLength)
	{
		m_ulLength = libNewSize.u.LowPart;

		if (m_iReadPos > m_ulLength)
			m_iReadPos = m_ulLength;
		if (m_iWritePos > m_ulLength)
			m_iWritePos = m_ulLength;

		return S_OK;
	}
	DWORD	dwLen	= libNewSize.u.LowPart - m_ulLength;
	LPBYTE  pBuf	= new BYTE[dwLen];
	DWORD	dwSave	= m_iWritePos;

	memset(pBuf, 0, dwLen);
	m_iWritePos = m_ulLength;
	Write(pBuf, dwLen, NULL);

	m_iWritePos = dwSave;

	delete [] pBuf;

	return S_OK;
}

HRESULT CISSHelper::CopyTo(IStream __RPC_FAR *pstm, ULARGE_INTEGER cb, ULARGE_INTEGER __RPC_FAR *pcbRead, ULARGE_INTEGER __RPC_FAR *pcbWritten)
{
	ATLTRACE(_T("CISSHelper::CopyTo\n"));
	return E_NOTIMPL;
}

HRESULT CISSHelper::Clone(IStream __RPC_FAR *__RPC_FAR *ppstm)  
{
	CISSHelper* pNew = new CISSHelper(true);

	*pNew = this;

	*ppstm = pNew;
	(*ppstm)->AddRef();

	return S_OK;
}


BOOL CISSHelper::PersistObjectToString(IPersistStream* pPersist, char** ppResult, int* pLen /*= NULL*/)
{
	return _PersistObjectToString(pPersist, ppResult, pLen);
}

BOOL CISSHelper::PersistObjectFromString(IPersistStream* pPersist, const char* pBuf)
{
	return _PersistObjectFromString(pPersist, pBuf);
}

BOOL CISSHelper::PersistObjectToString(IPersistStreamInit* pPersist, char** ppResult, int* pLen /*= NULL*/)
{
	return _PersistObjectToString(pPersist, ppResult, pLen);
}

BOOL CISSHelper::PersistObjectFromString(IPersistStreamInit* pPersist, const char* pBuf)
{
	return _PersistObjectFromString(pPersist, pBuf);
}

CISSHelper& CISSHelper::operator = (ISequentialStream* pStream)
{
	Clear();

	LPBYTE	lpBuf = new BYTE[4096];
	ULONG	nRead = 0;

	while(SUCCEEDED(pStream->Read(lpBuf, 4096, &nRead)))
	{
		if (nRead == 0)
			break;

		Write(lpBuf, nRead, NULL);
	}
	delete [] lpBuf;

	return *this;
}

CISSHelper& CISSHelper::operator += (IStream* pStream)
{
	LPBYTE	lpBuf = new BYTE[4096];
	ULONG	nRead = 0;

	while(SUCCEEDED(pStream->Read(lpBuf, 4096, &nRead)))
	{
		if (nRead == 0)
			break;

		Write(lpBuf, nRead, NULL);
	}
	delete [] lpBuf;

	return *this;
}

CISSHelper& CISSHelper::operator = (IStream* pStream)
{
	SEEK_TO_BEGIN(pStream);

	operator=((ISequentialStream*)pStream);

	SEEK_TO_BEGIN(pStream);

	return *this;
}

CISSHelper& CISSHelper::operator = (const CISSHelper& h)
{
	Clear();

	m_ulLength		= h.m_ulLength;

	m_iReadPos		= h.m_iReadPos;
	m_iWritePos		= h.m_iWritePos;

	m_ulBulkAlloc	= h.m_ulBulkAlloc;
	m_nExpFactor	= h.m_nExpFactor;
	
	m_ulSize		= m_ulLength;

	if (m_ulSize > 0 && h.m_pBuffer != NULL)
	{
		m_pBuffer	= CoTaskMemAlloc(m_ulSize);
		memcpy(m_pBuffer, h.m_pBuffer, m_ulSize);
	}
	return *this;
}
