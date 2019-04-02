package com.lidong.ZALUAN;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TestEquaks {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("This is a list");
        Vector<String> vector = new Vector<>();
        vector.add("This is a list");
        System.out.println(list.equals(vector));

    }


}
