package com.lidong.suanfa.chazhao;

import java.util.Arrays;

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

        showDijkstra(roads, 1);

    }

    private static void print(int[] array) {
        for (int temp : array) {
            System.out.print(temp + "\t");
        }
        System.out.println();
    }


    public static void showDijkstra(int[][] roads, int start) {
        int len = roads[start].length;
        int[] line = Arrays.copyOf(roads[start], len); //start到其他点的原本距离

        int[] shortPath = new int[len]; //表示start到个点的最短路径
        int[] visited = new int[len]; //表示start到个点的最短路径是否已经确认

        shortPath[start] = 0;// start到自身的距离为0
        visited[start] = 1;// 为1则表示start到该点的最短路径已经确认

        for (int count = 1; count < len; count++) {//总共还需要再确认 len-1 次

            int shortestAndNoVisited = -1;
            int minValue = -1;
            for (int i = 0; i < len; i++) {
                if (visited[i] == 0 && line[i] != -1 && minValue == -1) {
                    minValue = line[i];
                    shortestAndNoVisited = i;
                } else if (visited[i] == 0 && line[i] != -1 && minValue != -1 && line[i] < minValue) {
                    minValue = line[i];
                    shortestAndNoVisited = i;
                }
            }

            if (shortestAndNoVisited == -1) {
                continue;
            }

            // 将新选出的顶点标记为已求出最短路径，且到start的最短路径就是minValue
            visited[shortestAndNoVisited] = 1;
            shortPath[shortestAndNoVisited] = minValue;

            System.out.println("shortestAndNoVisited : " + shortestAndNoVisited);
            System.out.print("ShortPath    : ");
            print(shortPath);
            System.out.print("Visited Info : ");
            print(visited);

            for (int i = 0; i < len; i++) {
                if (roads[shortestAndNoVisited][i] != -1 && visited[i] == 0) {
                    if (line[i] == -1) {
                        line[i] = roads[shortestAndNoVisited][i] + shortPath[shortestAndNoVisited];
                    } else if (line[i] > (roads[shortestAndNoVisited][i] + shortPath[shortestAndNoVisited])) {
                        line[i] = roads[shortestAndNoVisited][i] + shortPath[shortestAndNoVisited];
                    }
                }
            }
            System.out.print("line Info    : ");
            print(line);
        }

        print(line);
    }
}
