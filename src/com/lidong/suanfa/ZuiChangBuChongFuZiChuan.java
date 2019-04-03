package com.lidong.suanfa;

import java.util.ArrayDeque;
import java.util.Queue;

public class ZuiChangBuChongFuZiChuan {


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }


    public static int lengthOfLongestSubstring(String s) {
        if (s == null) return -1;
        if (s.length() == 1) return 1;

        ArrayDeque<Character> queue = new ArrayDeque<>();
        queue.push(s.charAt(0));
        int max = 1;
        int temp = 1;
        for (int i = 1; i < s.length(); i++) {
            while (queue.contains(s.charAt(i))) {
                Character popC = queue.removeLast();
                temp--;
            }
            queue.push(s.charAt(i));
            temp++;
            max = Math.max(max, temp);
        }
        return max;
    }
}
