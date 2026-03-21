package com.onik.eduspring.util;

/**
 * 计算成绩等级
 */
public class ScoreUtil {

    public static String getGradeLevel(Double score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
