; CLW file contains information for the MFC ClassWizard

[General Info]
Version=1
LastClass=sock
LastTemplate=CSocket
NewFileInclude1=#include "stdafx.h"
NewFileInclude2=#include "sockagent.h"

ClassCount=4
Class1=CSockagentApp
Class2=CSockagentDlg
Class3=CAboutDlg

ResourceCount=3
Resource1=IDD_ABOUTBOX
Resource2=IDR_MAINFRAME
Class4=sock
Resource3=IDD_SOCKAGENT_DIALOG

[CLS:CSockagentApp]
Type=0
HeaderFile=sockagent.h
ImplementationFile=sockagent.cpp
Filter=N
LastObject=CSockagentApp

[CLS:CSockagentDlg]
Type=0
HeaderFile=sockagentDlg.h
ImplementationFile=sockagentDlg.cpp
Filter=D
LastObject=CSockagentDlg
BaseClass=CDialog
VirtualFilter=dWC

[CLS:CAboutDlg]
Type=0
HeaderFile=sockagentDlg.h
ImplementationFile=sockagentDlg.cpp
Filter=D

[DLG:IDD_ABOUTBOX]
Type=1
Class=CAboutDlg
ControlCount=4
Control1=IDC_STATIC,static,1342177283
Control2=IDC_STATIC,static,1342308480
Control3=IDC_STATIC,static,1342308352
Control4=IDOK,button,1342373889

[DLG:IDD_SOCKAGENT_DIALOG]
Type=1
Class=CSockagentDlg
ControlCount=17
Control1=IDOK,button,1342242817
Control2=IDCANCEL,button,1342242816
Control3=IDC_STATIC,static,1342308352
Control4=IDC_LIST_OUTPUT,listbox,1352728833
Control5=IDC_BUTTON_TEST,button,1342242816
Control6=IDC_BUTTON_SEND,button,1342242816
Control7=IDC_BUTTON_SET,button,1342242816
Control8=IDC_STATIC,button,1342177287
Control9=IDC_IPADDRESS_REMOTEIP,SysIPAddress32,1342242816
Control10=IDC_STATIC_REMOTEIP,static,1342308352
Control11=IDC_STATIC_LOCALIP,static,1342308352
Control12=IDC_IPADDRESS_LOCALIP,SysIPAddress32,1342242816
Control13=IDC_STATIC_LOCALID,static,1342308352
Control14=IDC_STATIC_REMOTEID,static,1342308352
Control15=IDC_EDIT_LOCALID,edit,1350631552
Control16=IDC_EDIT_REMOTEID,edit,1350631552
Control17=IDC_BUTTON_CLOSE,button,1342242816

[CLS:sock]
Type=0
HeaderFile=sock.h
ImplementationFile=sock.cpp
BaseClass=CSocket
Filter=N
LastObject=sock
VirtualFilter=uq

