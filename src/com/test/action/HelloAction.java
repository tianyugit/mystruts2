/**
 * 
 */
package com.test.action;

/**========================================================================
 * 
 * 版本:1.0
 *
 *创建日期:2017年5月13日 下午10:07:35
 *
 *作者:马天宇
 *
 *描述:
 *
 *
 *版权:T世界版权所有(c) 2017
 *
 *==========================================================================
 */
public class HelloAction {

	private String name;
	private String password;
		
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String execute(){
		
		System.out.println("hello world!"+name+"==>"+password);
		
		return "success";
	}
}
