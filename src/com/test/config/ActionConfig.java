/**
 * 
 */
package com.test.config;

import java.util.HashMap;
import java.util.Map;

/**========================================================================
 * 
 * 版本:1.0
 *
 *创建日期:2017年5月13日 下午7:17:07
 *
 *作者:马天宇
 *
 *描述: 用于封装struts文件中的属性
 *
 *
 *版权:T世界版权所有(c) 2017
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
	
	private String name;//对应的是action中的name
	
	private String method;//对应的是action中的method
	
	private String className;//对应的是action中的class
	
	private Map<String,String> result = new HashMap<String, String>();//对应的是action中的result

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
