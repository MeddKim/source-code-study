package com.stuframework.beans.factory.support;


import com.stuframework.beans.factory.BeanFactory;
import com.stuframework.beans.factory.config.BeanDefinition;

import java.util.*;

public abstract class AbstractBeanFactory implements BeanFactory {

    public static final String FACTORY_BEAN_PREFIX = "&";

    private BeanFactory parentBeanFactory;

    private Map customEditors = new HashMap();

    private final Set ignoreDependencyTypes = new HashSet();

    private final List beanPostProcessors = new ArrayList();

    private final Map aliasMap = Collections.synchronizedMap(new HashMap());

    private final Map singletonCache = Collections.synchronizedMap(new HashMap());

    //=======================================================================//
    //      构造方法                                                        //
    //======================================================================//
    public AbstractBeanFactory(){
//        ignoreDependencyType(BeanFactory.class);
    }
    public AbstractBeanFactory(BeanFactory parentBeanFactory){
        this();
        this.parentBeanFactory = parentBeanFactory;
    }

    //=======================================================================//
    //      BeanFactory接口的实现                                           //
    //======================================================================//
    @Override
    public Object getBean(String name){
        String beanName = transformedBeanName(name);
        return null;
    }

    @Override
    public Object getBean(String name, Class requiredType){
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public boolean isSingleton(String name){
        return false;
    }

    @Override
    public String[] getAliases(String name){
        return new String[0];
    }
    //======================================================================//
    //      抽取出来的方法                                                  //
    //======================================================================//

    /**
     * 如果名称是别名的话需要转换
     * @param name
     * @return
     */
    protected String transformedBeanName(String name){
        if(null == name){
//            throw new NoSuchBeanDefinitionException(name, "bean名称不可为空");
        }
        if(name.startsWith(FACTORY_BEAN_PREFIX)){
            name = name.substring(FACTORY_BEAN_PREFIX.length());
        }
        //处理别名
        String canonicalName = (String) this.aliasMap.get(name);
        return null != canonicalName ? canonicalName : name;
    }



    //======================================================================//
    //      需要子类去具体实现的抽象方法                                    //
    //======================================================================//

    public abstract boolean containsBeanDefinition(String beanName);

    public abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, RootBeanDefinition mergedBeanDefinition);

    protected abstract void destroyBean(String beanName, Object bean);

}
