/**
 * 
 */
package com.test.context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.stack.ValueStack;

/**========================================================================
 * 
 * 版本:1.0
 *
 *创建日期:2017年5月13日 下午8:25:36
 *
 *作者:马天宇
 *
 *描述:数据中心ActionContext对象
 *
 *
 *版权:T世界版权所有(c) 2017
 *
 *==========================================================================
 */
public class ActionContext implements Serializable{
	
	private static final long serialVersionUID = 1294441883362417229L;

	//为了保证能获得ActionContext
	public static ThreadLocal<ActionContext> tl = new ThreadLocal<ActionContext>();
	
	///用于存放各个域
	private Map<String, Object> context;
	
	public ActionContext(Map<String, Object> context) {
	    this.context = context;
	}

	public ActionContext(HttpServletRequest request,
			HttpServletResponse response, Object action) {
		// 准备域
		context = new HashMap<String, Object>();
		// 1.request
		context.put(Constant.REQUEST, request);
		// 2. response
		context.put(Constant.RESPONSE, response);
		// 3. param
		context.put(Constant.PARAM, request.getParameterMap());
		// 4.session
		context.put(Constant.SESSION, request.getSession());
		// 5.application
		context.put(Constant.APPLICATION, request.getSession()
				.getServletContext());
		// ---------------------------------------------------
		// 6.valuestack 值栈
		ValueStack vs = new ValueStack();
		// 将action压入栈顶
		vs.push(action);
		// 将值栈放入request域
		request.setAttribute(Constant.VALUE_STACK, vs);
		// 将值栈放入数据中心
		context.put(Constant.VALUE_STACK, vs);
		// -----------------------------------------------------------------
		tl.set(this);
	}
	
	 
	//下面提供域的获取方法
	public HttpServletRequest getRequest() {
		return (HttpServletRequest) context.get(Constant.REQUEST);
	}

	public HttpServletResponse getResponse() {
		return (HttpServletResponse) context.get(Constant.RESPONSE);
	}

	public HttpSession getSession() {
		return (HttpSession) context.get(Constant.SESSION);
	}
	
	/* public Map<String, Object> getSession() {
	        return (Map<String, Object>) get(Constant.SESSION);
	 }*/

	public ServletContext getApplication() {
		return (ServletContext) context.get(Constant.APPLICATION);
	}

	public Map<String, String[]> getParam() {
		return (Map<String, String[]>) context.get(Constant.PARAM);
	}

	public ValueStack getStack() {
		return (ValueStack) context.get(Constant.VALUE_STACK);
	}

	public static ActionContext getActionContext(){
		return tl.get();
	}
	
	/* public Object get(String key) {
	        return context.get(key);
	    }
*/
}
