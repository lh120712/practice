//package com.leetcode;
//
//import java.util.HashSet;
//import java.util.Scanner;
//import java.util.Set;
//
///**
// *
// */
//public class One {
//
////    /**
////     *
////     * @param nums1
////     * @param m
////     * @param nums2
////     * @param n
////     */
////    public void merge(int[] nums1,int m,int[] nums2,int n){
////        int desc=nums1.length;
////        for (int i=m;i<nums1.length;i++){
////            nums1[i]=
////        }
////    }
//
//    public static void main(String[] args) {
//        Scanner scanner=new Scanner(System.in);
//        while(scanner.hasNext()){
//            String s = scanner.nextLine();
//            One one=new One();
//            Solution solution=one.new Solution();
//            int i = solution.lengthOfLongestSubString(s);
//            System.out.println(i);
//        }
//    }
//
////    public int[] twoSum(int[] nums,int target){
////        int[] a=new int[2];
////        for (int i = 0; i <nums.length/2+1 ; i++) {
////            for (int j=i+1;j<nums.length;j++){
////                 if (nums[i]+nums[j]!=target){
////                     break;
////                 }
////                 a[0]=i;
////                 a[1]=j;
////            }
////        }
////        return a;
////    }
//    class Solution{
//        public int lengthOfLongestSubString(String s){
//           int n=s.length();
//            Set<Character> hashSet=new HashSet<>();
//            char[] chars=s.toCharArray();
//            int maxLength=0;
//            int right=-1;
//            if (chars.length==0){
//                return 0;
//            }
//            for (int i = 0; i <n ; i++) {
//                if (i!=0){
//                    hashSet.remove(chars[i-1]);
//                }
//                while (right+1<n&&!hashSet.contains(chars[right+1])){
//                    hashSet.add(chars[right+1]);
//                    right++;
//                }
//                maxLength=Math.max(maxLength,hashSet.size());
//
//            }
//            return maxLength;
//        }
//        public String longestPalindrome(String s){
//            String longestPalindrome="";
//            char[] chars=s.toCharArray();
//            int end=0;
//            for (int i = 0; i <chars.length ; i++) {
//                if ()
//            }
//        }
//    }
//}
