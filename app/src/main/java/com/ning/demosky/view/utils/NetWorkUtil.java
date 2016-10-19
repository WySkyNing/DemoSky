package com.ning.demosky.view.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * NetWorkUtil
 * 
 * @author wei2bei132
 * 
 */
public class NetWorkUtil {
	private static State mWifiState = null;
	private static State mMobileState = null;

	public enum NetWorkState {
		WIFI, MOBILE, NONE;

	}

	public static NetWorkState getConnectState(Context context) {
		mWifiState = null;
		mMobileState = null;
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		manager.getActiveNetworkInfo();
		mWifiState = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		mMobileState = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		if (mWifiState != null && mMobileState != null
				&& State.CONNECTED != mWifiState
				&& State.CONNECTED == mMobileState) {
			return NetWorkState.MOBILE;
		} else if (mWifiState != null && mMobileState != null
				&& State.CONNECTED != mWifiState
				&& State.CONNECTED != mMobileState) {
			return NetWorkState.NONE;
		} else if (mWifiState != null && State.CONNECTED == mWifiState) {
			return NetWorkState.WIFI;
		}
		return NetWorkState.NONE;
	}

    /**
     * 检查网络是否可用
     *
     * @param paramContext
     * @return
     */
    public static boolean checkEnable(Context paramContext) {
        boolean i = false;
        NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
            return true;
        return false;
    }

    /**
     * 将ip的整数形式转换成ip形式
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    /**
     * 获取当前ip地址
     *
     * @param context
     * @return
     */
    public static String getLocalIpAddress(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext()
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            return int2ip(i);
        } catch (Exception ex) {
            return " 获取IP出错鸟!!!!请保证是WIFI,或者请重新打开网络!\n" + ex.getMessage();
        }
        // return null;
    }
}
