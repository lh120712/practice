package com.leetcode;

public class Two {
    public static void main(String[] args) {
        int[] a={1,4,2};
        int[] b={1,2,4};
        Solution solution = new Two().new Solution();
        solution.maxUncrossedLines(a,b);
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
    class Solution {
        public int maxUncrossedLines(int[] nums1, int[] nums2) {
//            int max=0;
//            int j=0;
//            int count=0;
//            for (int i = 0; i <nums1.length ; i++) {
//                for (int k=j;k<nums2.length;k++){
//                    j++;
//                    if (nums1[i]==nums2[k]){
//                        count++;
//                        break;
//                    }
//                }
//                if (count>max){
//                    max=count;
//                }
//            }
//            return max;
            int m=nums1.length,n=nums2.length;
            int [][]dp=new int[m+1][n+1];
            for (int i = 1; i <=m ; i++) {
                int num1=nums1[i-1];
                for (int j=1;j<=n;j++){
                    int num2=nums2[j-1];
                    if (num1==num2){
                        dp[i][j]=dp[i-1][j-1]+1;
                    }else{
                        dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
                    }
                }
            }
            return dp[m][n];
        }
    }
}
