#include <windows.h>
#include <stdio.h>
#include "psapi.h"
#pragma comment(lib,"psapi.lib")

void PrintProcessNameAndID(DWORD processID)
{
	char szProcessName[MAX_PATH]="unknown";
	HANDLE hProcess=OpenProcess(PROCESS_QUERY_INFORMATION|PROCESS_VM_READ,FALSE,processID);
	if (hProcess)
	{
		HMODULE hMod;
		DWORD cbNeeded;
		if(EnumProcessModules(hProcess,&hMod,sizeof(hMod),&cbNeeded))
			GetModuleBaseName(hProcess,hMod,szProcessName,sizeof(szProcessName));
		//if(!strcmp(szProcessName,"ps.exe"))
		//	printf("ps.exe alive\n");
	}
	printf("\n%-20s%-20d",szProcessName,processID);
	CloseHandle(hProcess);
}

void main()
{
	DWORD aProcesses[1024],cbNeeded,cProcesses;
	unsigned int i;
	if (!EnumProcesses(aProcesses,sizeof(aProcesses),&cbNeeded))
		return;
	cProcesses=cbNeeded/sizeof(DWORD);
	for(i=0;i<cProcesses;i++)
		PrintProcessNameAndID(aProcesses[i]);
	return;
}