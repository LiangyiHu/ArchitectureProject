package com.architecture.project.utils;

/**
 * @author taoranxue on 9/15/16 7:38 PM.
 */
public class ProjectUtils {
    public static boolean inArrays(int num, int [] arrays) {
        for (int a : arrays) {
            if (num == a) return true;
        }
        return false;
    }
}
