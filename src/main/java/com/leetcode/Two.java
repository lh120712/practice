package com.leetcode;

public class Two {
    public static void main(String[] args) {

    }

    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        boolean contains = haystack.contains(needle);
        if (contains) {
            char[] chars = haystack.toCharArray();
            char[] chars1 = needle.toCharArray();
            for (int i = 0; i < chars.length - chars1.length; i++) {
                if (chars[i] == chars1[0]) {
                    for (int j = 0; j < chars1.length; j++) {
                        if (chars[i + j] == chars1[j]) {
                            continue;
                        } else {
                            return -1;
                        }

                    }
                    return i;
                }

            }
        } else {
            return -1;
        }
        return -1;
    }
}
