/**
 * 
 */
package com.test.interceptor;

import java.io.Serializable;

import com.test.invocation.ActionInvocation;

/**========================================================================
 * 
 * �汾:1.0
 *
 *��������:2017��5��13�� ����8:58:44
 *
 *����:������
 *
 *����:�������ӿ�
 *
 *
 *��Ȩ:T�����Ȩ����(c) 2017
 *
 *==========================================================================
 */
public interface Interceptor extends Serializable{
	/**
	 * ��ʼ��
	 */
	public void init();
	/**
	 * ����
	 * @param invocation
	 * @return
	 */
	public String interceptor(ActionInvocation invocation);
	/**
	 * ����
	 */
	public void destory();

}
