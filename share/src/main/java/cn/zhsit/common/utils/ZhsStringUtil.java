package cn.zhsit.common.utils;

/**
 * Created by gcj on 2017/7/24.
 */
public class ZhsStringUtil {

    /**
     * 判断是空
     *
     * @param str
     * @return true  empty;
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0) ? true : false;
    }

    public static boolean isNotBlank(String str) {
        return (str == null || str.trim().length() == 0) ? false : true;
    }

    public static boolean isAllBlank(String... strs) {
        for (int i = 0; i < strs.length; i++) {
            boolean notBlank = isNotBlank(strs[i]);
            if (notBlank) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否含有
     *
     * @param seq       源库
     * @param searchSeq 搜索项
     * @return
     */
    public static boolean contains(final CharSequence seq, final CharSequence searchSeq) {
        if (seq == null || searchSeq == null) {
            return false;
        }
        return seq.toString().indexOf(searchSeq.toString(), 0) >= 0;
    }


    /**
     *
     * @param source
     * @param frontNum
     * @param lastNum
     * @return
     */
    public static String replaceStar(String source, int frontNum, int lastNum) {
        if (isBlank(source)) {
            return source;
        }
        char[] arr = source.toCharArray();

        int frontIndex = frontNum - 1;
        int lastIndex = arr.length - lastNum;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i <= frontIndex) {
                sb.append(arr[i]);
            }
            if (i > frontIndex) {
                break;
            }
        }
        sb.append("****");
        for (int i = 0; i < arr.length; i++) {
            if (i <= frontIndex) {
                continue;
            }
            if (i >= lastIndex) {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    /**
     * @param str
     * @param num
     * @return
     */
    public static String lastBit(String str, int num) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        int startIndex = length - num;
        if(startIndex<0){
            return str;
        }
        return str.substring(startIndex);
    }

    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }
}
