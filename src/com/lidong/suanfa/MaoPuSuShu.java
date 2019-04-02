package com.lidong.suanfa;

/**
 * 猫扑数：指以2开头，后面跟任意个3的十进制数。如：2、23、233等。
 * <p>
 * 1和0既非素数也非合数。
 */

public class MaoPuSuShu {

    public static void main(String[] args) {
        for (int i = 0; i < 100000000; i++) {
            if (isMappuPrime(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isMappuPrime(int n) {
        return isMaopu(n) && isSuShu(n);
    }

    private static boolean isMaopu(int n) {
        if (n < 10) {
            return n == 2;
        } else
            return n % 10 == 3 && isMaopu(n / 10);
    }

    private static boolean isSuShu(int n) {
        boolean flag = true;
        if (n < 2) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

}
