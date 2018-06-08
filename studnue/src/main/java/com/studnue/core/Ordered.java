package com.studnue.core;

/**
 *  需要区分顺序的对象（如在在集合中）可实现该接口。这里的顺序可以解释为
 *  优先级，第一个对象（有最小顺序值）具有最高优先级
 * @author Juergen Hoeller
 * @since 07.04.2003
 */
public interface Ordered {
  /**
   * 返回该对象的的优先级，通常开始与0或1，拥有最大优先级，
   * Integer.MAX_VALUE为最低优先级
   * @return 数值
   */
	public int getOrder();
}
