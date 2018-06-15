package com.stuframework.beans.definition;

/**
 * 属性对象，描述了 key-value关系
 */
public class PropertyValue {
    private String name;
    private Object value;

    public PropertyValue(String name,Object value){
        if (name == null) {
            throw new IllegalArgumentException("属性名称不可为空");
        }
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public String toString() {
        return "PropertyValue: name='" + name + "'; value=[" + value + "]";
    }

    /**
     * 重写equal方法，两个PropertyValue对象相等的条件是名称和值都相等
     * @param other
     * @return 是否相等
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PropertyValue)) {
            return false;
        }
        PropertyValue otherPv = (PropertyValue) other;
        return (this.name.equals(otherPv.name) &&
                ((this.value == null && otherPv.value == null) || this.value.equals(otherPv.value)));
    }

    public int hashCode() {
        return this.name.hashCode() * 29 + (this.value != null ? this.value.hashCode() : 0);
    }
}
