#include <jni.h>
#include <string>


extern "C"
jstring
Java_com_elouyi_lely_JniTest_myJniTest(JNIEnv *env,jclass clazz) {
    std::string url = "花q";
    return env->NewStringUTF(url.c_str());
}
