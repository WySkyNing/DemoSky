package com.ning.demosky.view.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by yorki on 2016/12/27.
 * 反射
 */

public class ReflectDemo {

    public static void main(String [] array){


        try {

            /**
             * 反射机制获取类的三种方法
             * */
            Class myClass_1 = Class.forName("com.ning.demosky.view.reflect.MyClass");

            Class myClass_2 = MyClass.class;

            MyClass myClass = new MyClass();
            Class myClass_3 = myClass.getClass();

            System.out.println(myClass_1);
            System.out.println(myClass_2);
            System.out.println(myClass_3);

            System.out.println();




            /**
             * 创建对象
             * */
            Object object_1 = myClass_1.newInstance();
            Object object_2 = myClass_2.newInstance();
            Object object_3 = myClass_3.newInstance();

            System.out.println(object_1);
            System.out.println(object_2);
            System.out.println(object_3);
            System.out.println();


            /**
             * 获取属性：分为全部属性和指定属性
             * */
            /* 全部属性 */
            Field [] field = myClass_1.getDeclaredFields(); //获取全部属性

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder
                    .append(Modifier.toString(myClass_1.getModifiers()))//getModifiers 返回的 int 型
                    .append(" class ")
                    .append(myClass_1.getSimpleName())
                    .append("{\n");

            //里边的每一个属性
            for (Field f : field){

                stringBuilder.append("\t");//空格

                stringBuilder
                        .append(Modifier.toString(f.getModifiers()))
                        .append(" ");//获取属性修饰符，public,static 等

                stringBuilder
                        .append(f.getType().getSimpleName())
                        .append(" ");//属性的类型名字

                stringBuilder
                        .append(f.getName())
                        .append(";\n");//属性的名字
            }

            stringBuilder.append("}");

            System.out.println(stringBuilder.toString());
            System.out.println();



            /* 获取特定的属性 */
            Field userField = myClass_1.getDeclaredField("user");
            Field ageField = myClass_1.getDeclaredField("age");

            userField.setAccessible(true);

            //如果有 final 修饰会报异常
            userField.set(object_1,"反射小菜鸟");
            ageField.set(object_1,15);

            System.out.println(userField.get(object_1));
            System.out.println(ageField.get(object_1));
            System.out.println();





        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | NoSuchFieldException e)
        {
            e.printStackTrace();
        }


    }

}
