package com.stuframework.beans;

/**
 * 改接口用于保存PropertyValue用于保存的合集
 * 并进行集合层面上的操作
 */
public interface PropertyValues {

    PropertyValue[] getPropertyValues();

    PropertyValue getPropertyValue(String propertyName);

    boolean contains(String propertyName);

    PropertyValues changesSince(PropertyValues old);
}
