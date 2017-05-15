/**
 * 
 */
package com.test.action;

import java.io.Serializable;

import com.test.context.ActionContext;


/**========================================================================
 * 
 * 版本:1.0
 *
 *创建日期:2017年5月14日 上午12:06:17
 *
 *作者:马天宇
 *
 *描述:注冊用的action類
 *
 *
 *版权:T世界版权所有(c) 2017
 *
 *==========================================================================
 */
public class RegistAction implements Serializable{
	
	private static final long serialVersionUID = -793621223468025885L;
	
	private String name;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String regist(){
		System.out.println("注册的用户名：" + name+" ==> 密码："+password);
	  //  session.setAttribute("name", "ss");
		
		ActionContext.getActionContext().getRequest().setAttribute("name", name);
		return "regist";
	} 
}
