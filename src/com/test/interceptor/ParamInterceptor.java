/**
 * 
 */
package com.test.interceptor;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.test.context.ActionContext;
import com.test.invocation.ActionInvocation;
import com.test.stack.ValueStack;

/**========================================================================
 * 
 * �汾:1.0
 *
 *��������:2017��5��13�� ����10:19:21
 *
 *����:������
 *
 *����:������װ������
 *
 *
 *��Ȩ:T�����Ȩ����(c) 2017
 *
 *==========================================================================
 */
public class ParamInterceptor implements Interceptor{

	
	public void init() {
		// TODO Auto-generated method stub
		
	}

	
	public String interceptor(ActionInvocation invocation) {
		//1 ��ò���
		//2 ���action����
		//ActionContext ac = ActionContext.getActionContext().getStack();//��һ�ֻ��ActionContext����
		ActionContext ac = 	invocation.getActionContext();//�ڶ��ֻ��ActionContext����
		ValueStack vs = ac.getStack();
		Object action = vs.seek();
		//3 ��װ
		try {
			BeanUtils.populate(action, ac.getRequest().getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//4 ����
		return invocation.invoke(invocation);
	}

	
	public void destory() {
		// TODO Auto-generated method stub
		
	}

}
