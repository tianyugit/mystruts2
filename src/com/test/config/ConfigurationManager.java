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
 * �汾:1.1
 *
 *��������:2017��5��13�� ����7:13:29
 *
 *����:������
 *
 *����:��ȡstruts.xml�����ļ�
 *
 *
 *��Ȩ:T�����Ȩ����(c) 2017
 *
 *==========================================================================
 */
public class ConfigurationManager {
	
	/**
	 * ��ȡInterceptor
	 * @return
	 */
	public static List<String> getInterceptors(){
		List<String> interceptors = null;
		//1����������
		SAXReader reader = new SAXReader();
		//2.���������ļ�=>document
		//��������ļ���
		InputStream is = ConfigurationManager.class.getResourceAsStream("/struts.xml");
		Document doc = null;
		try{
			doc = reader.read(is);
			
		}catch(DocumentException e){
			e.printStackTrace();
			throw new RuntimeException("�����ļ�����ʧ�ܣ�");
		}
		//3.��дxpath
		String xpath = "//interceptor";
		//4.����xpath�������������
		List<Element> list = doc.selectNodes(xpath);
		//5.��������Ϣ��װ��list������
		if(list != null && list.size()>0){
			interceptors = new ArrayList<String>();
			for(Element ele : list){
				String className = ele.attributeValue("class");
				interceptors.add(className);
			}
		}
		//����
		return interceptors;
	}
	
	/**
	 * ��ȡaction
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
		//1����������
		SAXReader reader = new SAXReader();
		//2.���������ļ�=>document
		//��������ļ���
		InputStream is = ConfigurationManager.class.getResourceAsStream("/struts.xml");
		
		Document doc;
		try {
			doc = reader.read(is);
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("���������ļ�ʧ��!");
		}
	}
	
	/**
	 * ��ȡconstant
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
