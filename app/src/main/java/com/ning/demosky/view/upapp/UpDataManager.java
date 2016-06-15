package com.ning.demosky.view.upapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ning.demosky.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/4/28.
 */
public class UpDataManager {

    public static final String PATH = "http://218.60.28.101/car/versionType/android_gas_version.xml";
    private static final int DOWNLOADING = 1;//正在下载
    private static final int DOWNLOAD_FINISH = 2;//下载完成

    private boolean mIsCancel = false;//是否取消下载

    private  String mVersion_code;
    private  String mVersion_name;
    private  String mVersion_desc;
    private  String mVersion_path;

    private Context mContext;

    private ProgressBar mProgressBar;
    private LayoutInflater layoutInflater;

    private AlertDialog mdowlodDialog;//设置全局Dialog

    private String mAPKSavePath;

    private int mProgess;//当前进度条位置

    public UpDataManager(Context context) {
        mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
    }

    private Handler responseHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {



            return false;
        }
    });

    /**获取服务器的版本信息*/
    private void getServiceAppInfo(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;

                try {

                    URL url = new URL(PATH);

                    connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");

                    InputStream is = connection.getInputStream();

                    parseXMLWithPull(is);
//
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
//
//                    StringBuilder builder = new StringBuilder();
//
//                    String line;
//
//                    while ((line = bufferedReader.readLine()) != null){
//                        builder.append(line);
//                    }
//
//                    Log.e("builder.toString()",builder.toString());
////
//                    Message message = new Message();
//
//                    message.obj = is;
//
//                    responseHandler.sendMessage(message);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                    if (null != connection){

                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


    /**XML解析*/
    private void parseXMLWithPull(InputStream response){

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            XmlPullParser xmlPullParser = factory.newPullParser();

            xmlPullParser.setInput(response,"UTF-8");
            Log.e("response", "--parseXMLWithPull");

            /**获取当前解析事件*/
            int eventType = xmlPullParser.getEventType();

            /**如果当前解析事件不等于 END_DOCUMENT ,说明解析还没完成,调用 next 获取下一个解析事件*/
            while (eventType != XmlPullParser.END_DOCUMENT){

                /**获取节点名字,如果等于字段,调用 netText 获取内容*/
                String nodeName = xmlPullParser.getName();

                switch (eventType){

                    /**开始解析某个节点*/
                    case XmlPullParser.START_TAG:



                        if ("version_code".equals(nodeName)){

                            mVersion_code = xmlPullParser.nextText()+"";

                        }else if ("version_name".equals(nodeName)){

                            mVersion_name = xmlPullParser.nextText()+"";

                        }else if ("url".equals(nodeName)){

                            mVersion_path = xmlPullParser.nextText()+"";
                        }

                        break;

                    /**完成解析某个节点*/
                    case XmlPullParser.END_TAG:

                        if ("root".equals(nodeName)){

                            Log.e("response", mVersion_code + mVersion_name + mVersion_path);

                            get(mVersion_code);

                            Message message = new Message();

                            Bundle bundle = new Bundle();

                            bundle.putString("mVersion_code",mVersion_code);
                            bundle.putString("mVersion_name",mVersion_name);
                            bundle.putString("mVersion_path",mVersion_path);

                            message.setData(bundle);

                            message.what = 3;
                            mUpDataDownlodProgessHandler.sendMessage(message);
                        }
                        break;

                    default:
                        break;
                }

                eventType = xmlPullParser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void get(String code){
        Log.e("---------------",code);

        mVersion_code = code;
    }

    /**
     * 检测软件是否需要跟新
     */
    public void checkUpData() {

        getServiceAppInfo();

        if (isUpData()) {
            //Toast.makeText(mContext, "需要更新", Toast.LENGTH_SHORT).show();
            showNoticeDialog();
        } else {
            Toast.makeText(mContext, "不需要更新", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 与本地版本比较是否需要更新
     */
    public boolean isUpData() {

        if (!"".equals(mVersion_code)) {
            Log.e("mVersion_code", "-" + mVersion_code);
            int serviceVersinCode = Integer.valueOf(mVersion_code).intValue();
            int localVersinCode = 1;

            try {
                localVersinCode = mContext.getPackageManager()
                        .getPackageInfo("com.ning.upapp", 0)
                        .versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            if (serviceVersinCode > localVersinCode) {

                return true;

            } else {

                return false;
            }


        }
        return false;
    }


    /**
     * 有更新时显示提示对话框
     */
    public void showNoticeDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        String message = "有新的版本,是否需要更新?" + mVersion_desc;
        builder.setMessage(message);

        builder.setNegativeButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //隐藏对话框
                dialog.dismiss();
                //显示下载对话框
                showDowloadDialog();

            }
        });

        builder.setPositiveButton("下载再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //隐藏对话框
                dialog.dismiss();
            }
        });

        builder.create().show();

    }


    /**
     * 显示下载的对话框
     */
    public void showDowloadDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("下载中");

        View view = layoutInflater.inflate(R.layout.dialog_progress,null);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        builder.setView(view);

        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                //设置下载状态为确定取消
                mIsCancel = true;

            }
        });

        mdowlodDialog = builder.create();
        mdowlodDialog.show();

        dowloadAPK();
    }


    private Handler mUpDataDownlodProgessHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what){

                case DOWNLOADING:
                    /**正在下载.设置进度条*/
                    mProgressBar.setProgress(mProgess);
                    break;

                case DOWNLOAD_FINISH:
                    /**下载完成,隐藏Dialog*/
                    mdowlodDialog.dismiss();

                    /**安装*/
                    installAPK();
                    break;

                case 3:

                    Bundle bundle = msg.getData();

                    Log.e("bundle.getString",bundle.getString("mVersion_code")+"---");

                    mVersion_code =  bundle.getString("mVersion_code");

                    break;
            }
            return false;
        }
    });

    /**
     * 开启新线程下载文件
     */
    private void dowloadAPK() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                /**判断SD卡是否挂载*/
                if (Environment.getExternalStorageState()
                        .equals(Environment.MEDIA_MOUNTED)){

                    /**获取SD卡路径*/
                    String sdPath = Environment.getDownloadCacheDirectory() + "/";

                    /**apk文件路径*/
                    mAPKSavePath = sdPath + "apk";
                    File dir = new File(mAPKSavePath);
                    if (!dir.exists()){
                        dir.mkdir();
                    }


                    /**下载文件*/
                    try {

                        URL url = new URL(mVersion_path);

                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                        /**建立连接*/
                        connection.connect();

                        InputStream inputStream = connection.getInputStream();

                        /**获取文件大小*/
                        int length = connection.getContentLength();

                        /** 创建文件*/
                        File akpFile = new File(mAPKSavePath,mVersion_name);

                        /**写流*/
                        FileOutputStream fos = new FileOutputStream(akpFile);

                        /**本次下载字节数*/
                        int count = 0;

                        byte[] buffer = new byte[1024];

                        while (!mIsCancel){
                            /**本次读取的字节数*/
                            int numRead = inputStream.read(buffer);
                            /**下载的总量*/
                            count += numRead;

                            /**计算当前进度条的位置*/
                            mProgess = (int) ((float)count/length * 100);

                            /**更新进度条*/
                            mUpDataDownlodProgessHandler.sendEmptyMessage(DOWNLOADING);

                            /**下载完成*/
                            if (numRead < 0){
                                mUpDataDownlodProgessHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                                break;
                            }

                            /**参数2 偏移量*/
                            fos.write(buffer,0,numRead);
                        }

                        fos.close();
                        inputStream.close();


                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    /**
     * 下载到本地执行安装
     */
    public void installAPK() {

        File apkFile = new File(mAPKSavePath,mVersion_name);

        if (!apkFile.exists()){
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);

        Uri uri = Uri.parse("file://" + apkFile.toString());

        intent.setDataAndType(uri,"application/vnd.android.package-archive");

        mContext.startActivity(intent);



    }


}
