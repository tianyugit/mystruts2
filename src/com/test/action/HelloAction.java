/**
 * 
 */
package com.test.action;

/**========================================================================
 * 
 * �汾:1.0
 *
 *��������:2017��5��13�� ����10:07:35
 *
 *����:������
 *
 *����:
 *
 *
 *��Ȩ:T�����Ȩ����(c) 2017
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
