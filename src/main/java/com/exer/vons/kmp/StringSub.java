package com.exer.vons.kmp;

/**
 * @author ： fjl
 * @date ： 2018/9/28/028 11:21
 */
public class StringSub {
    public static void main(String[] args) {
        String src = "abcqwertopadalbcf";
        String target = "wert";
        System.out.println("" + findSubString(src, target));
    }

    public static boolean findSubString(String src, String target) {
        boolean flag = false;
        for (int i = 0; i < src.length()-target.length()+1; i++) {
            if (src.substring(i, target.length()+i).equalsIgnoreCase(target)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
