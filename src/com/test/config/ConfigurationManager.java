/**
 * 
 */
package com.test.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**========================================================================
 * 
 * 版本:1.1
 *
 *创建日期:2017年5月13日 下午7:13:29
 *
 *作者:马天宇
 *
 *描述:读取struts.xml配置文件
 *
 *
 *版权:T世界版权所有(c) 2017
 *
 *==========================================================================
 */
public class ConfigurationManager {
	
	/**
	 * 读取Interceptor
	 * @return
	 */
	public static List<String> getInterceptors(){
		List<String> interceptors = null;
		//1创建解析器
		SAXReader reader = new SAXReader();
		//2.加载配置文件=>document
		//获得配置文件流
		InputStream is = ConfigurationManager.class.getResourceAsStream("/struts.xml");
		Document doc = null;
		try{
			doc = reader.read(is);
			
		}catch(DocumentException e){
			e.printStackTrace();
			throw new RuntimeException("配置文件加载失败！");
		}
		//3.书写xpath
		String xpath = "//interceptor";
		//4.根据xpath获得拦截器配置
		List<Element> list = doc.selectNodes(xpath);
		//5.将配置信息封装到list集合中
		if(list != null && list.size()>0){
			interceptors = new ArrayList<String>();
			for(Element ele : list){
				String className = ele.attributeValue("class");
				interceptors.add(className);
			}
		}
		//返回
		return interceptors;
	}
	
	/**
	 * 读取action
	 * @return
	 */
	public static Map<String,ActionConfig> getActionConfig() {
		Map<String, ActionConfig> actionMap;
		Document doc = getDocument();
		
		String xpath = "//action";
		
		List<Element> list = doc.selectNodes(xpath);
		
		if(list == null || list.size() ==0){
			return null;
		}
		actionMap = new HashMap<String,ActionConfig>();
		
		for(Element e : list){
			ActionConfig action = new ActionConfig();
			action.setName(e.attributeValue("name"));
			action.setClassName(e.attributeValue("class"));
			String method = e.attributeValue("method");
			action.setMethod(method==null||method.trim().equals("")?"execute":method);
			
			List<Element> results = e.elements("result");
			for(Element result : results){
				action.getResult().put(result.attributeValue("name"), result.getText());
			}
			actionMap.put(action.getName(),action);
		}
		return actionMap;
	}

	private static Document getDocument()  {
		Map<String,ActionConfig> actionMap =null;
		//1创建解析器
		SAXReader reader = new SAXReader();
		//2.加载配置文件=>document
		//获得配置文件流
		InputStream is = ConfigurationManager.class.getResourceAsStream("/struts.xml");
		
		Document doc;
		try {
			doc = reader.read(is);
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("加载配置文件失败!");
		}
	}
	
	/**
	 * 读取constant
	 * @param key
	 * @return
	 */
	public static String getConstant(String key) {
		Document doc = getDocument();
		
		String path = "//constant[@name='"+key+"']";
		
		Element constant = (Element) doc.selectSingleNode(path);
		
		if(constant!=null){
			return constant.attributeValue("value");
		}else{
			return null;
		}
	}

}
