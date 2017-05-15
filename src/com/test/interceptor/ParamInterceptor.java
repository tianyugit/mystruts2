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
 * 版本:1.0
 *
 *创建日期:2017年5月13日 下午10:19:21
 *
 *作者:马天宇
 *
 *描述:参数封装拦截器
 *
 *
 *版权:T世界版权所有(c) 2017
 *
 *==========================================================================
 */
public class ParamInterceptor implements Interceptor{

	
	public void init() {
		// TODO Auto-generated method stub
		
	}

	
	public String interceptor(ActionInvocation invocation) {
		//1 获得参数
		//2 获得action对象
		//ActionContext ac = ActionContext.getActionContext().getStack();//第一种获得ActionContext对象
		ActionContext ac = 	invocation.getActionContext();//第二种获得ActionContext对象
		ValueStack vs = ac.getStack();
		Object action = vs.seek();
		//3 封装
		try {
			BeanUtils.populate(action, ac.getRequest().getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//4 放行
		return invocation.invoke(invocation);
	}

	
	public void destory() {
		// TODO Auto-generated method stub
		
	}

}
