package com.stuframework.helper;

import com.stuframework.annotation.Controller;
import com.stuframework.annotation.Service;
import com.stuframework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

public final class ClassHelper {

    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包下所有的类
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取所偶的Service类
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls: CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取所偶的Service类
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls: CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取所有的Bean类 包括Service和Controller
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls: CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class) || cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
}
