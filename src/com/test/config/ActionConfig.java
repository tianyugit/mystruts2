/**
 * 
 */
package com.test.config;

import java.util.HashMap;
import java.util.Map;

/**========================================================================
 * 
 * �汾:1.0
 *
 *��������:2017��5��13�� ����7:17:07
 *
 *����:������
 *
 *����: ���ڷ�װstruts�ļ��е�����
 *
 *
 *��Ȩ:T�����Ȩ����(c) 2017
 *
 *==========================================================================
 */
public class ActionConfig {
	/**
	 * <action name="" method="" 
						class="" >
			<result name="success"  >/index.jsp</result>
		</action>
	 */
	
	private String name;//��Ӧ����action�е�name
	
	private String method;//��Ӧ����action�е�method
	
	private String className;//��Ӧ����action�е�class
	
	private Map<String,String> result = new HashMap<String, String>();//��Ӧ����action�е�result

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, String> getResult() {
		return result;
	}

	public void setResult(Map<String, String> result) {
		this.result = result;
	}

}
