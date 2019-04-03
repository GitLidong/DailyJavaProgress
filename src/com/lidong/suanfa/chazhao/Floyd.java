package com.lidong.suanfa.chazhao;

public class Floyd {


    public static void main(String[] args) {

        int[][] road = {
                {0, 2, 6, 4},
                {-1, 0, 3, -1},
                {7, -1, 0, 1},
                {5, -1, 12, 0}
        };

        print(road);

        floyd(road);

        print(road);

        System.out.println(find(road, 3, 4));
        System.out.println(find(road, 4, 3));

    }

    public static void print(int[][] road) {
        for (int i = 0; i < road.length; i++) {
            for (int j = 0; j < road[i].length; j++) {
                System.out.print(road[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void floyd(int[][] road) {
        for (int i = 0; i < road.length; i++) {
            for (int j = 0; j < road.length; j++) {
                for (int k = 0; k < road[j].length; k++) {
                    if (road[j][i] != -1 && road[i][k] != -1) {
                        if (road[j][k] == -1) {
                            road[j][k] = road[j][i] + road[i][k];
                        } else if ((road[j][i] + road[i][k]) < road[j][k]) {
                            road[j][k] = road[j][i] + road[i][k];
                        }
                    }
                }
            }
        }
    }

    public static int find(int[][] road, int i, int j) {
        return road[i - 1][j - 1];
    }

}
