package com.ning.demosky.view.reflect;

/**
 * Created by yorki on 2016/12/27.
 */

public class MyClass {

    /**
     * PUBLIC: 1
     * PRIVATE: 2
     * PROTECTED: 4
     * STATIC: 8
     * FINAL: 16
     * SYNCHRONIZED: 32
     * VOLATILE: 64
     * TRANSIENT: 128
     * NATIVE: 256
     * INTERFACE: 512
     * ABSTRACT: 1024
     * STRICT: 2048
     * */

    public String user;

    private int phone;

    public static int age ;

    protected  String name;

    public String getUser() {
        return user;
    }

    private void setUser(String user) {
        this.user = user;
    }
}
