package cn.zhsit.common.utils;

import java.lang.reflect.Field;

/**
 * Created by zhsit on 2016/8/26.
 */
public class ZhsReflectUtil {

    /**
     * @param obj
     * @param propertyName
     * @param newVal
     */
    public static void setFieldValue(Object obj, String propertyName, Object newVal) {
        Class clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            boolean fieldAccessible = field.isAccessible();
            field.setAccessible(true);
            try {
                if (field.getName().equals(propertyName)) {
                    field.set(obj, newVal);
                    break;
                }
            } catch (Throwable throwable) {
                System.err.println("ZhsReflectUtil.setFieldValue  error,name:" + clazz.getName() + ",propertyName:" + propertyName + ",newVal:" + newVal);
            } finally {
                field.setAccessible(fieldAccessible);
            }
        }
    }

    /**
     *
     * @param obj
     * @param propertyName
     * @return
     */
    public static Object getFieldValue(final Object obj, final String propertyName) {
        Class clazz = obj.getClass();
        {
            Object val = getFieldVal(clazz, obj, propertyName);
            if (null != val) {
                return val;
            }
        }
        clazz = clazz.getSuperclass();
        {
            if (null != clazz) {
                Object val = getFieldVal(clazz, obj, propertyName);
                if (null != val) {
                    return val;
                }
            }
        }
        clazz = clazz.getSuperclass();
        {
            if (null != clazz) {
                Object val = getFieldVal(clazz, obj, propertyName);
                if (null != val) {
                    return val;
                }
            }
        }
        return null;
    }

    public static Object getFieldVal(final Class clazz, final Object obj, final String propertyName) {
        for (Field field : clazz.getDeclaredFields()) {
            boolean fieldAccessible = field.isAccessible();
            field.setAccessible(true);
            try {
                if (field.getName().equals(propertyName)) {
                    return field.get(obj);
                }
            } catch (Throwable throwable) {
                System.err.println("ZhsReflectUtil.getObject  error,name:" + clazz.getName() + ",propertyName:" + propertyName);
            } finally {
                field.setAccessible(fieldAccessible);
            }
        }
        return null;
    }

}
