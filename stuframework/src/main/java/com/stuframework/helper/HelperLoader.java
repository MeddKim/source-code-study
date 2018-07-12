package com.stuframework.helper;

import com.stuframework.util.ClassUtil;

public final class HelperLoader {

    /**
     * 用于加载调用 helper 中 static 代码块
     */
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName());
        }
    }
}
