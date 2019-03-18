package com.lidong.ZALUAN;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareTest {
    public static void main(String[] args) {
        int[] datas = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        Arrays.sort(datas);

        for (int i : datas) {
            System.out.print(i + "\t");
        }
        System.out.println();

        A[] datasA = {new A("A4", 4),
                new A("A3", 3),
                new A("A2", 3),
                new A("A1", 1)};
        Arrays.sort(datasA);
        for (A a : datasA) {
            System.out.print(a.toString() + "\t");
        }
        System.out.println();


        B[] datasB = {new B("B4", 4),
                new B("B3", 3),
                new B("B2", 3),
                new B("B1", 1)};
        Arrays.sort(datasB, new Comparator<B>() {
            @Override
            public int compare(B o1, B o2) {
                return o1.compare(o1, o2);
            }
        });

        for (B b : datasB) {
            System.out.print(b.toString() + "\t");
        }
    }

}

class A implements Comparable<A> {

    private String name;
    private int age;

    public A(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "-" + age;
    }

    @Override
    public int compareTo(A o) {
        return age <= o.age ? -1 : 1;
    }
}

class B implements Comparator<B> {

    private String name;
    private int age;

    public B(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "-" + age;
    }


    @Override
    public int compare(B o1, B o2) {
        return o1.age <= o2.age ? -1 : 1;
    }
}




