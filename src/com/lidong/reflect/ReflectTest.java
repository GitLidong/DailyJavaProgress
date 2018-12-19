package com.lidong.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args)
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException,
            ClassNotFoundException, NoSuchFieldException {

        Fruit fruit = new Fruit();
        Class<?> class1 = fruit.getClass();//方法一

        Class<?> class2 = Fruit.class;//方法二

        Class class3 = null;

        //方法三，如果这里不指定类所在的包名会报 ClassNotFoundException 异常
        class3 = Class.forName("com.lidong.reflect.Fruit");

        System.out.println(class1 + "  " + class2 + "    " + class3);

        Constructor<Fruit> constructor1 =
                class3.getConstructor();

        Constructor<Fruit> constructor2 =
                class3.getConstructor(String.class);

        Fruit fruit1 = constructor1.newInstance();
        Fruit fruit2 = constructor2.newInstance("Apple");

        Field field = null;
        field = class3.getField("num");

        //getField() 方法不能获取私有的属性,访问私有字段时会报NoSuchFieldException异常
        // field = class3.getField("type");

        field = class3.getDeclaredField("type");//获取私有 type 属性
        field.setAccessible(true); //对私有字段的访问取消检查

        field.set(fruit1, "Apple");//为无参对象实例属性赋值

        Object type = field.get(fruit1); //通过 fruit 对象获取属性值
        System.out.println(type);

        Class clazz = null;
        Method method = null;
        clazz = Class.forName("com.lidong.reflect.Fruit");
        Constructor<Fruit> fruitConstructor = clazz.getConstructor(String.class);
        Fruit fruit3 = fruitConstructor.newInstance("Apple");//创建有参对象实例

        method = clazz.getMethod("show", null);  //获取空参数 show 方法
        method.invoke(fruit3, null);  //执行无参方法

        method = clazz.getMethod("show", int.class); //获取有参 show 方法
        method.invoke(fruit3, 20);  //执行有参方法
    }

}
