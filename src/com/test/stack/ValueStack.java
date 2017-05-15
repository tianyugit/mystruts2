/**
 * 
 */
package com.test.stack;

import java.util.ArrayList;
import java.util.List;

/**========================================================================
 * 
 * 版本:1.0
 *
 *创建日期:2017年5月13日 下午8:36:09
 *
 *作者:马天宇
 *
 *描述:值栈
 *
 *
 *版权:T世界版权所有(c) 2017
 *
 *==========================================================================
 */
public class ValueStack {
	
	private List<Object> list = new ArrayList<Object>();
	//弹栈
	public Object pop(){
		return list.remove(0);
	}
	//压栈
	public void push(Object o){
		list.add(0, o);
	}
	//取出顶部对象
	public Object seek(){
		return list.get(0);
	}

}
