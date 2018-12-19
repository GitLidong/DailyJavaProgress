package com.lidong.suanfa;

public class AppleEat {

    public static void main(String[] args) {
        System.out.println(findSteps(4));

    }


    /**
     * N 阶梯 ，每次走1补或者走2步，有多少种走法。
     */

    public static int findSteps(int N) {
        if (N < 0) {
            return 0;
        }

        if (N <= 2) {
            return N;
        }
        if (N > 2) {
            return findSteps(N - 1) + findSteps(N - 2);
        }
        return 0;
    }

    /**
     * 同理，有 X 个苹果，每天吃1 个 2个或者3个，有多少种吃法？
     */

    public static int getAppleSteps(int N) {
        if (N < 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 2;
        }
        if (N == 3) {
            return 4;
        }
        if (N > 3) {
            return getAppleSteps(N - 1) + getAppleSteps(N - 2) + getAppleSteps(N - 3);
        }
        return 0;
    }

}
