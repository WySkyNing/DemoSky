package com.ning.demosky.view.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * ��Ƭ���ɵ�Ŀ¼�� sd����/a/image/camera/.. .jpg
 *
 * 
 * @author baozi
 * 
 */
public class UseCameraActivity extends Activity {
	private String mImageFilePath;
	public static final String IMAGEFILEPATH = "ImageFilePath";
	public final static String IMAGE_PATH = "image_path";
	static Activity mContext;
	public final static int GET_IMAGE_REQ = 5000;
	private static Context applicationContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//�ж� activity�����ٺ� ��û�����ݱ���������
		if (savedInstanceState != null) {

			mImageFilePath = savedInstanceState.getString(IMAGEFILEPATH);

			Log.i("123savedInstanceState", mImageFilePath);

			File mFile = new File(IMAGEFILEPATH);
			if (mFile.exists()) {
				Intent rsl = new Intent();
				rsl.putExtra(IMAGE_PATH, mImageFilePath);
				setResult(Activity.RESULT_OK, rsl);
				Log.i("123savedInstanceState", "ͼƬ����ɹ�");
				finish();
			} else {
				Log.i("123savedInstanceState", "ͼƬ����ʧ��");
			}
		}

		mContext = this;
		applicationContext = getApplicationContext();
		if (savedInstanceState == null) {
			initialUI();
		}

	}

	public void initialUI() {
		//����ʱ������ ��׺Ϊ  .jpg ��ͼƬ

		long ts = System.currentTimeMillis();
		mImageFilePath = getCameraPath() + ts + ".jpg";
		File out = new File(mImageFilePath);
		showCamera(out);

	}

	private void showCamera(File out) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(out)); // set
		startActivityForResult(intent, GET_IMAGE_REQ);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {

		if (GET_IMAGE_REQ == requestCode && resultCode == Activity.RESULT_OK) {

			Intent rsl = new Intent();
			rsl.putExtra(IMAGE_PATH, mImageFilePath);
			mContext.setResult(Activity.RESULT_OK, rsl);
			mContext.finish();

		} else {
			mContext.finish();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("ImageFilePath", mImageFilePath + "");

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

	}

	public static String getCameraPath() {
		String filePath = getImageRootPath() + "/camera/";
		File file = new File(filePath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		file = null;
		return filePath;
	}

	public static String getImageRootPath() {
		String filePath = getAppRootPath() + "/image";
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = null;
		return filePath;
	}

	public static String getAppRootPath() {
		String filePath = "/a";
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			filePath = Environment.getExternalStorageDirectory() + filePath;
		} else {
			filePath = applicationContext.getCacheDir() + filePath;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = null;
		File nomedia = new File(filePath + "/.nomedia");
		if (!nomedia.exists())
			try {
				nomedia.createNewFile();
			} catch (IOException e) {
			}
		return filePath;
	}

}

