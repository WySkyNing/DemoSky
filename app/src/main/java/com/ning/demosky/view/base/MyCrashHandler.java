package com.ning.demosky.view.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;

/**
 * Created by wy on 2016/8/18.
 *
 * 异常处理类
 */
public class MyCrashHandler implements Thread.UncaughtExceptionHandler {

    private Context context;

    private MyCrashHandler(){}

    private static final class SingleHolder {
        private static final MyCrashHandler myCrashHandler = new MyCrashHandler();
    }

    public static MyCrashHandler getInstance() {
        return SingleHolder.myCrashHandler;
    }

    public void init(Context context) {
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(getInstance());
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        // 1.获取当前程序的版本号. 版本的id
        String versionInfo = getVersionInfo();

        // 2.获取手机的硬件信息.
        String mobileInfo = getMobileInfo();

        // 3.把错误的堆栈信息 获取出来
        String errorInfo = getErrorInfo(ex);

        // 4.把所有的信息 还有信息对应的时间 提交到服务器
//        try {
//            service.createNote(new PlainTextConstruct(dataFormat.format(new Date())),
//                    new PlainTextConstruct(versioninfo + mobileInfo + errorinfo), "public", "yes");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Log.e("wy_error",versionInfo + " ****\n " + mobileInfo + " ***\n " + errorInfo);

        //干掉当前的程序
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    /**
     * 获取手机的版本信息
     *
     * @return 包名
     */
    private String getVersionInfo() {
        try {
            PackageManager packageManager = context.getPackageManager();
            //第二参数就是指定的info，比如想得到权限info就传PackageManager.GET_PERMISSIONS
            PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本号未知";
        }
    }

    /**
     * 获取手机的硬件信息
     *
     * @return 硬件信息
     */
    private String getMobileInfo() {
        StringBuilder sb = new StringBuilder();
        //通过反射获取系统的硬件信息
        try {

            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                //暴力反射 ,获取私有的信息
                field.setAccessible(true);
                String name = field.getName();
                String value = field.get(null).toString();
                sb.append(name);
                sb.append("=");
                sb.append(value);
                sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * 获取错误的信息
     *
     * @param throwable 所有异常的父类
     * @return 错误的信息
     */
    private String getErrorInfo(Throwable throwable) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        throwable.printStackTrace(pw);
        pw.close();

        return writer.toString();
    }


}
