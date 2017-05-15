/**
 * 
 */
package com.test.interceptor;

import java.io.Serializable;

import com.test.invocation.ActionInvocation;

/**========================================================================
 * 
 * 版本:1.0
 *
 *创建日期:2017年5月13日 下午8:58:44
 *
 *作者:马天宇
 *
 *描述:拦截链接口
 *
 *
 *版权:T世界版权所有(c) 2017
 *
 *==========================================================================
 */
public interface Interceptor extends Serializable{
	/**
	 * 初始化
	 */
	public void init();
	/**
	 * 拦截
	 * @param invocation
	 * @return
	 */
	public String interceptor(ActionInvocation invocation);
	/**
	 * 销毁
	 */
	public void destory();

}
