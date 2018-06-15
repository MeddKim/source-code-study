package com.stuframework.beans.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * 在BeanDefinition中使用，保存了bean定义中的配置信息
 */
public class MutablePropertyValues {
    private List<PropertyValue> propertyValuesList;

    public MutablePropertyValues() {
        this.propertyValuesList = new ArrayList(10);
    }

    public MutablePropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValuesList = new ArrayList(10);
    }

    /**
     * 添加一个属性对象，当前操作对象会覆盖前面名称相同的对象
     * @param pv
     */
    public void addPropertyValue(PropertyValue pv){
        for(int i = 0; i < this.propertyValuesList.size(); i++){
            PropertyValue currentPv = this.propertyValuesList.get(i);
            if(pv.getName().equals(currentPv.getName())){
                this.propertyValuesList.set(i,pv);
                return;
            }
        }
        this.propertyValuesList.add(pv);
    }

    public void addPropertyValue(String propertyName, Object propertyValue){
        addPropertyValue(new PropertyValue(propertyName,propertyValue));
    }

    public void removePropertyValue(PropertyValue propertyValue){
        this.propertyValuesList.remove(propertyValue);
    }

    public void removePropertyValue(String propertyName) {
        removePropertyValue(getPropertyValue(propertyName));
    }

    public void setPropertyValueAt(PropertyValue pv, int i) {
        this.propertyValuesList.set(i, pv);
    }

    public PropertyValue[] getPropertyValues(){
        return (PropertyValue[])this.propertyValuesList.toArray();
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (int i = 0; i < this.propertyValuesList.size(); i++) {
            PropertyValue pv = (PropertyValue) this.propertyValuesList.get(i);
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }

    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName) != null;
    }

    public MutablePropertyValues changesSince(MutablePropertyValues old) {
        MutablePropertyValues changes = new MutablePropertyValues();
        if (old == this)
            return changes;

        // For each property value in the new set
        for (int i = 0; i < this.propertyValuesList.size(); i++) {
            PropertyValue newPv = (PropertyValue) this.propertyValuesList.get(i);
            // If there wasn't an old one, add it
            PropertyValue pvOld = old.getPropertyValue(newPv.getName());
            if (pvOld == null) {
                changes.addPropertyValue(newPv);
            }
            else if (!pvOld.equals(newPv)) {
                // It's changed
                changes.addPropertyValue(newPv);
            }
        }
        return changes;
    }

    public String toString() {
        PropertyValue[] pvs = getPropertyValues();
        StringBuffer sb = new StringBuffer("MutablePropertyValues: length=" + pvs.length + "; ");
//        sb.append(StringUtils.arrayToDelimitedString(pvs, ","));
        return sb.toString();
    }

}
