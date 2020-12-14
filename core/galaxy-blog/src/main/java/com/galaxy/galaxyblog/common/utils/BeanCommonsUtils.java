package com.galaxy.galaxyblog.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 *  ①
 *
 * @author wangkq
 * @date 2020/12/6
 */
public class BeanCommonsUtils {
    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj)  {
        Map<String,Object> reMap = new HashMap<String,Object>();
        if (obj == null)
            return null;
        try {
            Class<?> objClass = obj.getClass();
            while(objClass != null){
                Field[] fields = objClass.getDeclaredFields();
                for(int i=0;i<fields.length;i++){
                    try {
                        Field f = objClass.getDeclaredField(fields[i].getName());
                        f.setAccessible(true);
                        Object o = f.get(obj);
                        reMap.put(fields[i].getName(), o);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                objClass = objClass.getSuperclass();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;

    }

    /**
     * 将Map里面的属性和值转化成实例对象
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object map2Object(Map<Object, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Class clz = obj.getClass();
            List<Field> fields = new ArrayList<>();
            while (clz != null){
                fields.addAll(Arrays.asList(clz.getDeclaredFields()));
                clz = clz.getSuperclass();//找到所有父类的属性
            }
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
