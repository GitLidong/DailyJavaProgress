package com.lidong.suanfa.chazhao;

public class KMP {
    public static void main(String[] args) {
        System.out.println(KMP("abababb", "ababababababb"));
    }

    public static int[] getNext(String target) {
        int[] next = new int[target.length()];
        //一个字母的时候，不存在相等最长前缀和最长后缀，所以值为0；
        next[0] = -1;
        //若下一个待求位置是next[i+1],则k的初始值为next[i],因为next[0]是固定的，
        // 下一个待求位置是next[1]，所以k=next[0]=-1
        int k = -1;
        for (int i = 1; i < target.length(); i++) {
            while (k > -1 && target.charAt(k + 1) != target.charAt(i)) {
                //能运行到这里，就说明不是我们最希望的状况，而这个循环就是当状况不好时，退而求其次，“缩短”能偷得懒。
                //k > -1有两个作用，1是是防止访问越界2是k如果<=-1表示不存在最长前/后缀，就没有必要找了
                k = next[k];
            }
            //跳出循环有两种情况，一种是找到了一个缩短后能用的，一个就是k等于-1了
            if (target.charAt(k + 1) == target.charAt(i)) {
                k = k + 1;
            }
            next[i] = k;
        }
        return next;
    }

    public static int KMP(String target, String source) {
        int next[] = getNext(target);
        int k = next[0];
        for (int i = 0; i < source.length(); i++) {
            while (k > -1 && target.charAt(k + 1) != source.charAt(i)) {
                k = next[k];
            }
            if (target.charAt(k + 1) == source.charAt(i)) {
                k = k + 1;//成功匹配一个节点
            }
            if (k == target.length() - 1) {//上面一直说k等于已经匹配的长度-1
                return i - target.length() + 1;
            }
        }
        return -1;
    }

}
