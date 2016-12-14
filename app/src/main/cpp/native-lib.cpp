#include <jni.h>
#include <string>

extern "C"
jstring
Java_particle_andengine_com_andengine_1particle_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
