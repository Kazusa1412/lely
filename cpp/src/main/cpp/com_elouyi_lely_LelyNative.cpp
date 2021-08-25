#include "com_elouyi_lely_LelyNative.h"
#include <iostream>
#include <jni.h>
#include <string>
#include <windows.h>


std::string getLolDir() {
    HKEY hKey;
    TCHAR szProductType[256];
    DWORD dwBufLen = 256;
    LONG IREt;
    IREt = RegOpenKeyEx(
        HKEY_CURRENT_USER,
        TEXT("SOFTWARE\\Tencent\\LOL"),
        0,
        KEY_QUERY_VALUE,
        &hKey
    );
    if(IREt != ERROR_SUCCESS) {
        std::cout << "233" << std::endl;
        return "null";
    }
    IREt = RegQueryValueEx(hKey,TEXT("InstallPath"),NULL,NULL,(LPBYTE)szProductType,&dwBufLen);
    if(IREt != ERROR_SUCCESS) {
    std::cout << "322" << std::endl;
        return "null";
    }
    RegCloseKey(hKey);
    std::string dir = szProductType;
    return dir;
}

char* getLolDir2() {
    HKEY hKey;
    TCHAR szProductType[256];
    DWORD dwBufLen = 256;
    LONG IREt;
    IREt = RegOpenKeyEx(
        HKEY_CURRENT_USER,
        TEXT("SOFTWARE\\Tencent\\LOL"),
        0,
        KEY_QUERY_VALUE,
        &hKey
    );
    if(IREt != ERROR_SUCCESS) {
        std::cout << "233" << std::endl;
        return "null";
    }
    IREt = RegQueryValueEx(hKey,TEXT("InstallPath"),NULL,NULL,(LPBYTE)szProductType,&dwBufLen);
    if(IREt != ERROR_SUCCESS) {
    std::cout << "322" << std::endl;
        return "null";
    }
    RegCloseKey(hKey);
    char* dir = (char*)szProductType;
    return dir;
}

// 网上抄的 gbk to utf8
char* Gb2312ToUtf8(char *p){
    DWORD dwNum = MultiByteToWideChar(CP_ACP, 0, p, -1, NULL, 0);
    char *psText;
    wchar_t *pwText = (wchar_t*)malloc(dwNum*sizeof(wchar_t));
    dwNum = MultiByteToWideChar(CP_ACP, 0, p, -1, pwText, dwNum);
    dwNum = WideCharToMultiByte(CP_UTF8, 0, pwText, -1, NULL, 0, NULL, NULL);
    psText = (char*)malloc(dwNum*sizeof(char));
    dwNum = WideCharToMultiByte(CP_UTF8, 0, pwText, -1, psText, dwNum, NULL, NULL);
    free(pwText);
    return psText;
}

JNIEXPORT jstring JNICALL
Java_com_elouyi_lely_LelyNative_lolDir(JNIEnv *env, jclass){
    std::string dir = getLolDir();
    std::cout<< dir << std::endl;
    return env->NewStringUTF(dir.c_str());
}

JNIEXPORT jbyteArray JNICALL
Java_com_elouyi_lely_LelyNative_lolDirBytes(JNIEnv *env, jclass) {
    char* dir = getLolDir2();
    dir = Gb2312ToUtf8(dir);
    int len = strlen(dir);
    auto b = env->NewByteArray(len);
    env->SetByteArrayRegion(b,0,len,(jbyte *) dir);
    return b;
}
