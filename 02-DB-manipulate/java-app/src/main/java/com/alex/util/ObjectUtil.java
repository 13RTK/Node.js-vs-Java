package com.alex.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public final class ObjectUtil {
    /**
     * Get all args constructor
     *
     * @param tClass the class to get all args
     * @param <T>    the class
     * @return all args constructor
     */
    public static  <T> Constructor<T> getAllArgsConstructor(Class<T> tClass) {
        Constructor<T> constructor = null;
        try {
            constructor = tClass.getConstructor(getAllFieldsArr(tClass));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return constructor;
    }


    private static Class<?>[] getAllFieldsArr(Class<?> tClass) {
        Class<?>[] fields = new Class[tClass.getDeclaredFields().length];
        int idx = 0;

        for (Field declaredField : tClass.getDeclaredFields()) {
            declaredField.setAccessible(true);
            fields[idx++] = declaredField.getType();
        }

        return fields;
    }
}
