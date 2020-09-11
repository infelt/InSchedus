# InSchedus
用于android快速的切换线程

使用方法
--

 1、项目根目录：build.gradle中dependencies添加：
 ```classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.5```
 
 2、app模块build.gradle中添加：
 ```apply plugin: 'android-aspectjx'```

 3、在application中初始化(@InScheduleInit)：
 ```java
@InScheduleInit
@Override
public void onCreate() {
    super.onCreate();
}
 ```
4、在需要通过切换线程的方法上添加注解（@InSchedule(InScheduleType.TYPE_IO)或@InSchedule(InScheduleType.TYPE_MAIN)）
```java
@InSchedule(InScheduleType.TYPE_IO)
private void testRunIo() {
    Log.d(TAG, "testRunIo TID: " + android.os.Process.myTid());
    testRunMain();
}

@InSchedule(InScheduleType.TYPE_MAIN)
private void testRunMain() {
    Log.d(TAG, "testRunMain TID: " + android.os.Process.myTid());
}
```
