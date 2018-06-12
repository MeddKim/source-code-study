/*
 * Copyright 2002-2004 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 

package com.studnue.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *  该接口会分析并个类的所有 public static final 类型的字段
 *  并将之值保存到 map 中  name(code) - value
 * @version $Id: Constants.java,v 1.2 2004/03/18 02:46:06 trisberg Exp $
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 16-Mar-2003
 */
public class Constants {

	//保存待分析类的public static final类型属性的名称-值键值对
	private final Map map = new HashMap();

	/** 待分析类的class对象 */
	private final Class clazz;

	/**
	 * 获取待处理类的public static final 属性并保存到map中
	 * @param clazz class to analyze.
	 */
	public Constants(Class clazz) {
		this.clazz = clazz;
		Field[] fields = clazz.getFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			if (Modifier.isFinal(f.getModifiers())
					&& Modifier.isStatic(f.getModifiers())
					&& Modifier.isPublic(f.getModifiers())) {
				String name = f.getName();
				try {
					//对于static类型的字段，直接用f.get(null)，不需要获取哪个实例的属性值
					Object value = f.get(null);
					this.map.put(name, value);
				}
				catch (IllegalAccessException ex) {
					// just leave this field and continue
				}
			}
		}
	}
	
	public int getSize() {
		return this.map.size();
	}

	/**
	 * Return a constant value cast to a Number.
	 * @param code name of the field
	 * @return long value if successful
	 * @see #asObject
	 * @throws com.studnue.core.ConstantException if the field name wasn't found or
	 * if the type wasn't compatible with Number
	 */
	public Number asNumber(String code) throws ConstantException {
		Object o = asObject(code);
		if (!(o instanceof Number))
			throw new ConstantException(this.clazz, code, "not a Number");
		return (Number) o;
	}

	/**
	 * Return a constant value as a String.
	 * @param code name of the field
	 * @return String string value if successful.
	 * Works even if it's not a string (invokes toString()).
	 * @see #asObject
	 * @throws com.studnue.core.ConstantException if the field name wasn't found
	 */
	public String asString(String code) throws ConstantException {
		return asObject(code).toString();
	}

	/**
	 * Parse the given string (upper or lower case accepted) and return 
	 * the appropriate value if it's the name of a constant field in the
	 * class we're analysing.
	 * @throws com.studnue.core.ConstantException if there's no such field
	 */
	public Object asObject(String code) throws ConstantException {
		code = code.toUpperCase();
		Object val = this.map.get(code);
		if (val == null) {
			throw new ConstantException(this.clazz, code, "not found");
		}
		return val;
	}

	/**
	 * 根据名称前缀获取值的合集，前缀忽略大小写（会被转为大写）
	 * @param namePrefix prefix of the constant names to search
	 * @return the set of values
	 */
	public Set getValues(String namePrefix) {
		namePrefix = namePrefix.toUpperCase();
		Set values = new HashSet();
		for (Iterator it = this.map.keySet().iterator(); it.hasNext();) {
			String code = (String) it.next();
			if (code.startsWith(namePrefix)) {
				values.add(this.map.get(code));
			}
		}
		return values;
	}

	/**
	 * 根据属性名称获取所有的字符串  驼峰式命名会被转换
	 * @param propertyName the name of the bean property
	 * @return the set of values
	 * @see #propertyToConstantNamePrefix
	 */
	public Set getValuesForProperty(String propertyName) {
		return getValues(propertyToConstantNamePrefix(propertyName));
	}

	/**
	 * Look up the given value within the given group of constants.
	 * Will return the first match.
	 * @param value constant value to look up
	 * @param namePrefix prefix of the constant names to search
	 * @return the name of the constant field
	 * @throws com.studnue.core.ConstantException if the value wasn't found
	 */
	public String toCode(Object value, String namePrefix) throws ConstantException {
		namePrefix = namePrefix.toUpperCase();
		for (Iterator it = this.map.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			if (key.startsWith(namePrefix) && entry.getValue().equals(value)) {
				return key;
			}
		}
		throw new ConstantException(this.clazz, namePrefix, value);
	}

	/**
	 * Look up the given value within the group of constants for
	 * the given bean property name. Will return the first match.
	 * @param value constant value to look up
	 * @param propertyName the name of the bean property
	 * @return the name of the constant field
	 * @throws com.studnue.core.ConstantException if the value wasn't found
	 * @see #propertyToConstantNamePrefix
	 */
	public String toCodeForProperty(Object value, String propertyName) throws ConstantException {
		return toCode(value, propertyToConstantNamePrefix(propertyName));
	}

	/**
     * 将驼峰式字符串变为 下划线式全大写字符串
	 * <p>Example: "imageSize" -> "IMAGE_SIZE".
	 * @param propertyName the name of the bean property
	 * @return the corresponding constant name prefix
	 * @see #getValuesForProperty
	 * @see #toCodeForProperty
	 */
	public String propertyToConstantNamePrefix(String propertyName) {
	  StringBuffer parsedPrefix = new StringBuffer();
	  for(int i = 0; i < propertyName.length(); i++) {
	    char c = propertyName.charAt(i);
	    if (Character.isUpperCase(c)) {
	      parsedPrefix.append("_");
	      parsedPrefix.append(c);
	    }
	    else {
	      parsedPrefix.append(Character.toUpperCase(c));
	    }
	  }
		return parsedPrefix.toString();
	}

}
