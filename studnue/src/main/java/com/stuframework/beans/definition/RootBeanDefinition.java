package com.stuframework.beans.definition;

import com.stuframework.beans.exception.BeanException;

import java.lang.reflect.Constructor;

public class RootBeanDefinition extends AbstractBeanDefinition {

    /**
     * 自动装配的模式
     */
    public static final int AUTOWIRE_NO = 0;

    public static final int AUTOWIRE_BY_NAME = 1;

    public static final int AUTOWIRE_BY_TYPE = 2;

    public static final int AUTOWIRE_CONSTRUCTOR = 3;

    public static final int AUTOWIRE_AUTODETECT = 4;


    public static final int DEPENDENCY_CHECK_NONE = 0;

    public static final int DEPENDENCY_CHECK_OBJECTS = 1;

    public static final int DEPENDENCY_CHECK_SIMPLE = 2;

    public static final int DEPENDENCY_CHECK_ALL = 3;

    private Object beanClass;

    private ConstructorArgumentValues constructorArgumentValues;

    /** 默认自动装配模式为AUTOWIRE_NO **/
    private int autowireMode = AUTOWIRE_NO;

    private int dependencyCheck = DEPENDENCY_CHECK_NONE;

    private String[] dependsOn;

    private String initMethodName;

    private String destroyMethodName;

    public RootBeanDefinition(Class beanClass, int autowireMode){
        super(null);
        this.beanClass = beanClass;
//        setAutowireMode(autowireMode);
    }

    public RootBeanDefinition(Class beanClass, int autowireMode, int dependencyCheck){
        super(null);
        this.beanClass = beanClass;
//        setAutowireMode(autowireMode);
//        if (dependencyCheck && getResolvedAutowireMode() != AUTOWIRE_CONSTRUCTOR) {
//            setDependencyCheck(RootBeanDefinition.DEPENDENCY_CHECK_OBJECTS);
//        }
    }
    public RootBeanDefinition(Class beanClass, MutablePropertyValues pvs) {
        super(pvs);
        this.beanClass = beanClass;
    }

    public RootBeanDefinition(Class beanClass, MutablePropertyValues pvs, boolean singleton) {
        super(pvs);
        this.beanClass = beanClass;
        setSingleton(singleton);
    }

    public RootBeanDefinition(Class beanClass, ConstructorArgumentValues cargs, MutablePropertyValues pvs) {
        super(pvs);
        this.beanClass = beanClass;
        this.constructorArgumentValues = cargs;
    }

    public RootBeanDefinition(String beanClassName, ConstructorArgumentValues cargs, MutablePropertyValues pvs) {
        super(pvs);
        this.beanClass = beanClassName;
        this.constructorArgumentValues = cargs;
    }

    /**
     * 深度拷贝构造信息
     */
    public RootBeanDefinition(RootBeanDefinition other) {
        super(new MutablePropertyValues(other.getPropertyValues()));
        this.beanClass = other.beanClass;
        this.constructorArgumentValues = other.constructorArgumentValues;
        setSingleton(other.isSingleton());
        setLazyInit(other.isLazyInit());
//        setDependsOn(other.getDependsOn());
//        setDependencyCheck(other.getDependencyCheck());
//        setAutowireMode(other.getAutowireMode());
//        setInitMethodName(other.getInitMethodName());
//        setDestroyMethodName(other.getDestroyMethodName());
    }

    public ConstructorArgumentValues getConstructorArgumentValues() {
        return constructorArgumentValues;
    }

    public boolean hasConstructorArgumentValues(){
        return (constructorArgumentValues != null && !constructorArgumentValues.isEmpty());
    }

    public final Class getBeanClass() throws IllegalStateException{
        if(!(this.beanClass instanceof Class)){
            throw new IllegalStateException("");
        }
        return (Class) this.beanClass;
    }

    public final String getBeanClassName(){
        if (this.beanClass instanceof Class) {
            return ((Class) this.beanClass).getName();
        }
        else {
            return (String) this.beanClass;
        }
    }

    public void setAutowireMode(int autowireMode) {
        this.autowireMode = autowireMode;
    }

    public int getAutowireMode() {
        return autowireMode;
    }

    public int getResolvedAutowireMode(){
        if (this.autowireMode == AUTOWIRE_AUTODETECT) {
            //获取所有的构造函数
            Constructor[] constructors = getBeanClass().getConstructors();
            //
            for(Constructor constructor: constructors){
                //构造函数的参数为空
                if(0 == constructor.getParameterTypes().length){
                    return AUTOWIRE_BY_TYPE;
                }
            }
            return AUTOWIRE_CONSTRUCTOR;
        }
        else {
            return this.autowireMode;
        }
    }

    public void setDependencyCheck(int dependencyCheck) {
        this.dependencyCheck = dependencyCheck;
    }

    public int getDependencyCheck() {
        return dependencyCheck;
    }

    public void setDependsOn(String[] dependsOn) {
        this.dependsOn = dependsOn;
    }

    public String[] getDependsOn() {
        return dependsOn;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getInitMethodName() {
        return this.initMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public String getDestroyMethodName() {
        return this.destroyMethodName;
    }

    public void validate(){
        super.validate();
        //beanClass必须定义
        if(this.beanClass == null){
            throw new BeanException("RooBeanDefinition参数beanClass必须有值");
        }
    }

    public String toString() {
        return "Root bean with class [" + getBeanClassName() + "] defined in " + getResourceDescription();
    }


}
