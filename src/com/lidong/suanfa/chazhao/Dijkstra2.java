package com.lidong.suanfa.chazhao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstra2 {

    public static void main(String[] args) {

        int[][] roads = {
                {0, 1, 12, -1, -1, -1},
                {-1, 0, 9, 3, -1, -1},
                {-1, -1, 0, -1, 5, -1},
                {-1, -1, 4, 0, 13, 15},
                {-1, -1, -1, -1, 0, 4},
                {-1, -1, -1, -1, -1, 0}
        };

        dijkstra(roads, 0);

    }

    public static void dijkstra(int[][] roads, int start) {

        int[] sure = Arrays.copyOf(roads[start], roads[start].length);
        List<Integer> sureList = new ArrayList<>();//可以确定最短路径的下标
        sureList.add(start);

        int[] unSure = getUnSure(sure, sureList);

        while (sureList.size() != roads[start].length) {
            int minIndex = minIndex(unSure);
            if (minIndex == -1) break;
            int[] minLine = roads[minIndex];
            for (int i = 0; i < minLine.length; i++) {
                if (minLine[i] > 0) {
                    if (sure[i] < 0) {
                        sure[i] = sure[minIndex] + minLine[i];
                    } else {
                        if (sure[minIndex] + minLine[i] < sure[i]) {
                            sure[i] = sure[minIndex] + minLine[i];
                        }
                    }
                }
            }
            sureList.add(minIndex);
            unSure = getUnSure(sure, sureList);
            print(sure);
        }

        System.out.println("from " + start + " to others shortest path is : ");
        print(sure);

    }

    private static void print(int[] array) {
        for (int temp : array) {
            System.out.print(temp + "\t");
        }
        System.out.println();
    }

    private static int minIndex(int[] array) {
        int minIndex = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0 && minIndex == -1) {
                minIndex = i;
            }
            if (array[i] > 0 && array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static int[] getUnSure(int[] sure, List<Integer> sureList) {
        int[] unSure = Arrays.copyOf(sure, sure.length);
        for (Integer integer : sureList) {
            unSure[integer] = -1;
        }
        return unSure;
    }
}
