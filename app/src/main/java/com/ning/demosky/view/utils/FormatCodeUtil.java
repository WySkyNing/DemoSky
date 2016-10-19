package com.ning.demosky.view.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by wuqi on 15/7/3.
 */
public class FormatCodeUtil {
    public static String codingFormat(String str) {

        try {
            return URLEncoder.encode(str, "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "" ;
    }
}
