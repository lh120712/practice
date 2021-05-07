package com.leetcode;

import java.util.*;

public class Three {
    public static void main(String[] args) {
        String s=new String("3.1 2.2 1.0 6.4 4.5 5.2");
        String s1 = sortVersion(s);
        System.out.println(s1);
    }

    /**
     * 给N个软件版本号的输入，按照字典序降序排序后输出。
     * @param versions string字符串 N个软件版本号
     * @return string字符串
     */
    public static String sortVersion (String versions) {
        // write code here
        //例:"3.1 2.2 1.0 6.4 4.5 5.2"  输出:"[6.4, 5.2, 4.5 3.1, 2.2, 1.0]"
        String[] chars = versions.split(" ");
        float[] num=new float[chars.length];
        float[] num1=new float[chars.length];
        for (int i = 0; i <chars.length ; i++) {
            num[i]=Float.parseFloat(chars[i])*10;
        }
        for (int i = 0; i <chars.length ; i++) {
            for (int j=i;j<chars.length;j++){
                if (num[i]<num[j]){
                    float temp=num[i];
                    num[i]=num[j];
                    num[j]=temp;
                }
            }
        }
        for (int i = 0; i <chars.length ; i++) {
            System.out.println(num[i]);
        }

        for (int i=0;i<chars.length;i++){
            num1[i]=(float) (num[i]/10.0);
        }
        for (int i = 0; i <chars.length ; i++) {
            num[i]=(float) (num1[i]/1.0);
            System.out.println(num[i]);
        }
//        StringBuilder string=new StringBuilder("[");
//        string.append(num.toString());
//        string.append("]");
        String string = Arrays.toString(num);
        System.out.println(string);
        return string;
    }
}
