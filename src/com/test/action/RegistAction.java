/**
 * 
 */
package com.test.action;

import java.io.Serializable;

import com.test.context.ActionContext;


/**========================================================================
 * 
 * �汾:1.0
 *
 *��������:2017��5��14�� ����12:06:17
 *
 *����:������
 *
 *����:ע���õ�action�
 *
 *
 *��Ȩ:T�����Ȩ����(c) 2017
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
		System.out.println("ע����û�����" + name+" ==> ���룺"+password);
	  //  session.setAttribute("name", "ss");
		
		ActionContext.getActionContext().getRequest().setAttribute("name", name);
		return "regist";
	} 
}
