package com.stuframework.beans.factory;

import com.studnue.beans.factory.support.RootBeanDefinition;
import com.stuframework.beans.definition.BeanDefinition;
import com.stuframework.beans.exception.BeanException;

import java.util.*;

public abstract class AbstractBeanFactory implements BeanFactory{

    /**  配置文件中读取到的Bean文件存到该Map中  key为bean名称 **/
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap();

    /**  Bean 名称的合集 **/
    private List<String> beanDefinitionNames = new ArrayList();

    /** 保存单例对象 **/
    private final Map singletonCache = Collections.synchronizedMap(new HashMap());

    /**
     * 注册BeanDefinition
     * @param name  bean名称，也就是bean的唯一描述符（在spring对应了ID）
     * @param beanDefinition 类定义描述
     */
    public void registerBeanDefinition(String name,BeanDefinition beanDefinition){
        if(null != this.beanDefinitionMap.get(name)){
            throw new BeanException("名称为"+name+"的Bean已经存在");
        }
        this.beanDefinitionNames.add(name);
        this.beanDefinitionMap.put(name, beanDefinition);
    }

    public Object getBean(String name){
        Object sharedInstance = this.singletonCache.get(name);
        //单例对象已存在
        if(null != sharedInstance){
//            return getObjectForSharedInstance(name,sharedInstance);
        }
        //创建
        else {
            RootBeanDefinition mergedBeanDefinition = null;
            try{

            }catch (BeanException be){

            }

        }
        return null;
    }

    private Object createBean(String beanName, RootBeanDefinition beanDefinition){
        if(beanDefinition.getDependsOn() != null){

        }
        return null;
    }


}
