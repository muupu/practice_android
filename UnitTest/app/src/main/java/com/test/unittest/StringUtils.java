package com.test.unittest;

/**
 * Created by qiaoda.zqd on 2016/4/14.
 */
public class StringUtils {

    /**
     * 字符串首尾截除指定的字符
     * @param str 待截除的字符串
     * @param ch 制定的字符
     * @return 截断后的字符串
     */
    public static String trimString(String str, char ch) {
        int start = 0;
        int last = str.length() - 1;
        int end = last;
        while ((start < end) && (str.charAt(start) == ch)) {
            start++;
        }
        while ((end > start) && (str.charAt(end) == ch)) {
            end--;
        }
        if (start == 0 && end == last) {
            return str;
        }
        return str.substring(start, end + 1);
    }

    /**
     * 检测是否是英文字符
     * 英文占1byte，非英文（如中文）占2byte，根据这个特性来判断字符是否是英文字符
     */
    public static boolean isEnglishChar(char ch) {
        byte[] bytes = (ch + "").getBytes();
        if (bytes.length == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检测是否是中文字符
     */
    public static boolean isChineseChar(char ch) {
        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
        if (unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || unicodeBlock == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || unicodeBlock == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || unicodeBlock == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
}
