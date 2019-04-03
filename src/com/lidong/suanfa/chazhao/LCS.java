package com.lidong.suanfa.chazhao;


import java.util.ArrayList;
import java.util.List;

public class LCS {

    public static void main(String[] args) throws Exception {
        System.out.println(maxLcsLength("abcbdab", "bdcabd") + "");
    }


    public static int maxLcsLength(String s1, String s2) throws Exception {

        if (s1 == null || s2 == null) {
            throw new Exception("input can not be null");
        }

        int len1 = s1.length();
        int len2 = s2.length();

        int[][] c = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    c[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
                }
            }
        }
        return c[len1][len2];
    }

}

class aim {
    int i;
    int j;

    public aim(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
