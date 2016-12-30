package com.ning.demosky.view.tools;

import android.os.Handler;

/**
 * Created by yorki on 2016/12/29.
 */

public class OutClass {

    private String name;
    private int age;


    public OutClass(){

        InnerClass innerClass = new InnerClass();

        innerClass.innerName = "out_wy";


    }

    public InnerClass getInnerClass(){
        return new InnerClass();
    }


    // 在Java中内部类主要分为成员内部类、局部内部类、匿名内部类、静态内部类。

    /**
     * 第一：成员内部类中不能存在任何static的变量和方法；
     *
     * 第二：成员内部类是依附于外围类的，所以只有先创建了外围类才能够创建内部类。
     *
     * 第三：成员内部类也是最普通的内部类，它是外围类的一个成员，
     *      所以他是可以无限制的访问外围类的所有 成员属性和方法，尽管是private的，
     *      但是外围类要访问内部类的成员属性和方法则需要通过内部类实例来访问。
     * */
    public class InnerClass{

        private String innerName;

        InnerClass(){

            name = "wy";

            innerName = "inner_wy";
        }

    }


    //匿名内部类
    public abstract class Bird{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public abstract int fly();
    }



       public void show(Bird bird){

           System.out.println(bird.getName() + "能够飞 " + bird.fly() + "米");
       }

    /**
     *   1、使用匿名内部类时，我们必须是继承一个类或者实现一个接口，
     *      但是两者不可兼得，同时也只能继承一个类或者实现一个接口。（@Override 证明了继承或者实现）

     *   2、匿名内部类中是不能定义构造函数的(没有类名)。

     *   3、匿名内部类中不能存在任何的静态成员变量和静态方法。

     *   4、匿名内部类为局部内部类，所以局部内部类的所有限制同样对匿名内部类生效。

     *   5、匿名内部类不能是抽象的，它必须要实现继承的类或者实现的接口的所有抽象方法。
     *
     *   6.当所在的方法的形参需要被内部类里面使用时，该形参必须为final。
     *     (理由) 拷贝引用，为了避免引用值发生改变，例如被外部类的方法修改等，而导致内部类得到的值不一致，
     *     于是用final来让该引用不可改变。故如果定义了一个匿名内部类，并且希望它使用一个其外部定义的参数，
     *     那么编译器会要求该参数引用是final的。
     *
     * */

    public void show(final int distance){

        show(new Bird() {
            @Override
            public int fly() {
                return distance;
            }

            @Override
            public String getName() {
                return "大鸟";
        }
        });
    }

}



