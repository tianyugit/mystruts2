/**
 * 
 */
package com.test.stack;

import java.util.ArrayList;
import java.util.List;

/**========================================================================
 * 
 * �汾:1.0
 *
 *��������:2017��5��13�� ����8:36:09
 *
 *����:������
 *
 *����:ֵջ
 *
 *
 *��Ȩ:T�����Ȩ����(c) 2017
 *
 *==========================================================================
 */
public class ValueStack {
	
	private List<Object> list = new ArrayList<Object>();
	//��ջ
	public Object pop(){
		return list.remove(0);
	}
	//ѹջ
	public void push(Object o){
		list.add(0, o);
	}
	//ȡ����������
	public Object seek(){
		return list.get(0);
	}

}
