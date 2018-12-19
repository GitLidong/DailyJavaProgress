package com.lidong.others.myEqualsHash;

public class Test {

    public static void main(String[] args) {
        MyEquals myEquals1 = new MyEquals("1");
        MyEquals myEquals2 = new MyEquals("2");

        System.out.println(myEquals1.equals(myEquals2));
        System.out.println(myEquals1.hashCode() + " , " + myEquals2.hashCode());


        int i = 1;
        i = i++;
        System.out.println(i);

    }

}
