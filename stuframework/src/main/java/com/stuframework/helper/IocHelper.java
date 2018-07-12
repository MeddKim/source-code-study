package com.stuframework.helper;

import com.stuframework.annotation.Inject;
import com.stuframework.util.ArrayUtil;
import com.stuframework.util.CollectionUtil;
import com.stuframework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

public final class IocHelper {

    static {

        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isEmpty(beanMap)){
            beanMap.forEach((beanClass,beanInstance)->{
                //获取bean类所有的成员变量（简称 Bean Field）
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtil.isEmpty(beanFields)){
                    //遍历 beanField
                    for(Field beanField: beanFields){
                        //判断当前BeanField是否带有Inject注解
                        if(beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if(beanFieldInstance != null){
                                //通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            });
        }
    }
}
