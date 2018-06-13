package com.stuframework.beans;

public interface PropertyValues {

    PropertyValue[] getPropertyValues();

    PropertyValue getPropertyValue(String propertyName);

    boolean contains(String propertyName);

    PropertyValues changesSince(PropertyValues old);
}
