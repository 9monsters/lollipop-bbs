package com.just.lollipop.bbs.util.str;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.*;

public abstract class StringUtils {

    private static final String FOLDER_SEPARATOR = "/";
    private static final char EXTENSION_SEPARATOR = '.';
    private static final Random RANDOM = new Random();

    /**
     * 判断readable sequence是否为空
     * @param str
     * @return
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    /**
     * 判断是否含有文字
     * <p><pre>
     * StringUtils.hasText(null) = false
     * StringUtils.hasText("") = false
     * StringUtils.hasText(" ") = false
     * StringUtils.hasText("12345") = true
     * StringUtils.hasText(" 12345 ") = true
     * </pre>
     *
     * @param str
     * @return the boolean
     * @see Character#isWhitespace
     */
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param str
     * @return the boolean
     * @see #hasText(CharSequence)
     */
    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }

    /**
     * 判断是否包含空格
     * @param str
     * @return the boolean
     */
    public static boolean containsWhitespace(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param str
     * @return the boolean
     */
    public static boolean containsWhitespace(String str) {
        return containsWhitespace((CharSequence) str);
    }

    /**
     * 删除字符串空格
     * @param str
     * @return
     */
    public static String trim(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
            buf.deleteCharAt(0);
        }
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * 删除所有空格
     * @param str
     * @return
     */
    public static String trimAllWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        int index = 0;
        while (buf.length() > index) {
            if (Character.isWhitespace(buf.charAt(index))) {
                buf.deleteCharAt(index);
            } else {
                index++;
            }
        }
        return buf.toString();
    }

    /**
     * 删除开始空格
     * @param str
     * @return
     */
    public static String trimLeadingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
            buf.deleteCharAt(0);
        }
        return buf.toString();
    }

    /**
     * 删除结尾空格
     * @param str
     * @return
     */
    public static String trimTrailingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * 判断是否以指定字符串开头并忽略大小写
     * @param str
     * @param prefix
     * @return
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        if (str.startsWith(prefix)) {
            return true;
        }
        if (str.length() < prefix.length()) {
            return false;
        }
        String lcStr = str.substring(0, prefix.length()).toLowerCase();
        String lcSuffix = prefix.toLowerCase();
        return lcStr.equals(lcSuffix);
    }

    /**
     * 判断是否以指定字符串结尾并忽略大小写
     * @param str
     * @param prefix
     * @return
     */
    public static boolean endsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        if (str.startsWith(prefix)) {
            return true;
        }
        if (str.length() < prefix.length()) {
            return false;
        }
        String lcStr = str.substring(str.length() - prefix.length()).toLowerCase();
        String lcSuffix = prefix.toLowerCase();
        return lcStr.equals(lcSuffix);
    }

    /**
     * 获取文件名
     * @param path
     * @return
     */
    public static String getFilename(String path) {
        if (path == null) {
            return path;
        }
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
    }

    /**
     * 获取文件扩展名
     * @param path
     * @return
     */
    public static String getFilenameExtension(String path) {
        if (path == null) {
            return path;
        }
        int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? path.substring(sepIndex + 1) : path);
    }

    /**
     * 将字符串转换为数组
     * @param array
     * @param str
     * @return
     */
    public static String[] addStringToArray(String[] array, String str) {
        if (array == null || array.length == 0) {
            return new String[]{str};
        }
        String[] newArr = new String[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        newArr[array.length] = str;
        return newArr;
    }

    /**
     * 连接字符串数组
     * @param array1
     * @param array2
     * @return
     */
    public static String[] concatenateStringArrays(String[] array1, String[] array2) {
        if (array1 == null || array1.length == 0) {
            return array2;
        }
        if (array2 == null || array2.length == 0) {
            return array1;
        }
        String[] newArr = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, newArr, 0, array1.length);
        System.arraycopy(array2, 0, newArr, array1.length, array2.length);
        return newArr;
    }

    /**
     * 排序
     * @param array
     * @return
     */
    public static String[] sortStringArray(String[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        Arrays.sort(array);
        return array;
    }

    /**
     * 转换为String数组
     * @param collection
     * @return
     */
    public static String[] toStringArray(Collection<?> collection) {
        if (collection == null) {
            return null;
        }
        return (String[]) collection.toArray(new String[collection.size()]);
    }

    /**
     * 计算GUID
     * @return
     */
    public static String generateGUID() {
        return new BigInteger(165, RANDOM).toString(36).toUpperCase();
    }

    /**
     * 计算UUID
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 编码
     * @param name
     * @return
     */
    public static String getEncoder(String name) {
        try {
            name = new String(name.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return name;
    }

    /**
     * String To List
     *
     * @param val
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String> stringToList(String val) {
        if (val != null) {
            String[] list = val.split("[ ]*,[ ]*");
            return Arrays.asList(list);
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}