/**
 * 
 */
package com.test.invocation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.test.config.ActionConfig;
import com.test.context.ActionContext;
import com.test.interceptor.Interceptor;

/**========================================================================
 * 
 * 版本:1.0
 *
 *创建日期:2017年5月13日 下午8:52:23
 *
 *作者:马天宇
 *
 *描述:ActionInvocation负责完成拦截器链状调用以及action调用,以及数据中心(ActionContext)的提供
 *
 *
 *版权:T世界版权所有(c) 2017
 *
 *==========================================================================
 */
public class ActionInvocation {
	//过滤器链
	private Iterator<Interceptor> interceptors;
	//即将调用的action实例
	private Object action;
	//action配置信息
	private ActionConfig config;
	
	//数据中心
	private ActionContext ac;
	
	public ActionInvocation(List<String> InterceptorClassNames,ActionConfig config,HttpServletRequest request,HttpServletResponse response) {
		//1 准备Interceptor链
		List<Interceptor> interceptorList = null;
		if(InterceptorClassNames!=null && InterceptorClassNames.size()>0){
			 interceptorList = new ArrayList<Interceptor>();
			for(String className : InterceptorClassNames){
				Interceptor interceptor;
				try {
					//获取实例
					interceptor = (Interceptor) Class.forName(className).newInstance();
					interceptor.init();//初始化
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("创建Interceptor失败!!"+className);
				} 
				
				interceptorList.add(interceptor);
			}
			this.interceptors = interceptorList.iterator();
		}
		//2 准备action实例
		this.config = config;
		
		try {
			action = Class.forName(config.getClassName()).newInstance();//获取action对象
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("action创建失败!"+config.getClassName());
		} 
		//3 准备数据中心actionContext
		 ac = new ActionContext(request, response, action);
	}
	
	public ActionContext getActionContext() {
		return ac;
	}
	
	/**
	 * 递归调用拦截器链
	 * @param invocation
	 * @return
	 */
	public String invoke(ActionInvocation invocation){
		//1 准备一个变量接受action运行结果的路由串
		String result = null;
		//2 判断拦截器链中是否有下一个拦截器&&变量是否被赋值
		if(interceptors!= null  && interceptors.hasNext() && result==null ){
			//有=>调用下一个拦截的拦截方法
			Interceptor it = interceptors.next();
			result = it.interceptor(invocation);
		}else{
			//没有=> 调用action实例的处理方法
			//获得将要调用的action方法名称
			String methodName = config.getMethod(); // execute
			//根据action对象和方法名称获得方法对应的Method对象
			try {
				Method executeMethod = action.getClass().getMethod(methodName);
				//调用目标方法
				result = (String) executeMethod.invoke(action);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("您配置的action方法不存在!");
			} 
			
		}
		//3将action的结果路由串返回
		return result;
	}

}
