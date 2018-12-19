package com.lidong.ZALUAN;

import java.io.*;

public class TestClone3 implements Serializable {

    private static final long serialVersionUID = 369285298572941L;//最好是显式声明ID

    public Inner inner;


    public TestClone3 myClone() {
        TestClone3 data = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            // 将流序列化成对象
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            data = (TestClone3) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }


    public static void main(String[] args) {
        Inner inner = new Inner("AAA");
        TestClone3 data1 = new TestClone3();
        data1.inner = inner;
        TestClone3 data2 = data1.myClone();

        System.out.println(data1.inner.toString());
        System.out.println(data2.inner.toString());


        inner.name = "BBBBBB";

        System.out.println(data1.inner.toString());
        System.out.println(data2.inner.toString());
    }
}

class Inner implements Serializable {
    private static final long serialVersionUID = 872390113109L; //最好是显式声明ID
    public String name = "";

    public Inner(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Inner的name值为：" + name;
    }
}