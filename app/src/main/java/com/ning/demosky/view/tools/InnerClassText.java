package com.ning.demosky.view.tools;

/**
 * Created by yorki on 2016/12/29.
 */

public class InnerClassText {

    public static void main(String[] a){

        OutClass outClass = new OutClass();

        //OutClass.InnerClass innerClass = outClass.new InnerClass();

        OutClass.InnerClass innerClass = outClass.getInnerClass();

        outClass.show(100_000);

    }
}
