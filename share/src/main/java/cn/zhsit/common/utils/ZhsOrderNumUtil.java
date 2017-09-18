package cn.zhsit.common.utils;

/**
 * Created by Darren on 2017/7/18.
 */
public class ZhsOrderNumUtil {

    /**
     * 以秒为当前基数
     * @return
     */
    public static long currentBaseNum(){
        long d=System.currentTimeMillis();
        return d/1000*1000000;
    }


}
