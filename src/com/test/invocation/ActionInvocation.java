/**
 * 
 */
package com.test.invocation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.test.config.ActionConfig;
import com.test.context.ActionContext;
import com.test.interceptor.Interceptor;

/**========================================================================
 * 
 * �汾:1.0
 *
 *��������:2017��5��13�� ����8:52:23
 *
 *����:������
 *
 *����:ActionInvocation���������������״�����Լ�action����,�Լ���������(ActionContext)���ṩ
 *
 *
 *��Ȩ:T�����Ȩ����(c) 2017
 *
 *==========================================================================
 */
public class ActionInvocation {
	//��������
	private Iterator<Interceptor> interceptors;
	//�������õ�actionʵ��
	private Object action;
	//action������Ϣ
	private ActionConfig config;
	
	//��������
	private ActionContext ac;
	
	public ActionInvocation(List<String> InterceptorClassNames,ActionConfig config,HttpServletRequest request,HttpServletResponse response) {
		//1 ׼��Interceptor��
		List<Interceptor> interceptorList = null;
		if(InterceptorClassNames!=null && InterceptorClassNames.size()>0){
			 interceptorList = new ArrayList<Interceptor>();
			for(String className : InterceptorClassNames){
				Interceptor interceptor;
				try {
					//��ȡʵ��
					interceptor = (Interceptor) Class.forName(className).newInstance();
					interceptor.init();//��ʼ��
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("����Interceptorʧ��!!"+className);
				} 
				
				interceptorList.add(interceptor);
			}
			this.interceptors = interceptorList.iterator();
		}
		//2 ׼��actionʵ��
		this.config = config;
		
		try {
			action = Class.forName(config.getClassName()).newInstance();//��ȡaction����
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("action����ʧ��!"+config.getClassName());
		} 
		//3 ׼����������actionContext
		 ac = new ActionContext(request, response, action);
	}
	
	public ActionContext getActionContext() {
		return ac;
	}
	
	/**
	 * �ݹ������������
	 * @param invocation
	 * @return
	 */
	public String invoke(ActionInvocation invocation){
		//1 ׼��һ����������action���н����·�ɴ�
		String result = null;
		//2 �ж������������Ƿ�����һ��������&&�����Ƿ񱻸�ֵ
		if(interceptors!= null  && interceptors.hasNext() && result==null ){
			//��=>������һ�����ص����ط���
			Interceptor it = interceptors.next();
			result = it.interceptor(invocation);
		}else{
			//û��=> ����actionʵ���Ĵ�����
			//��ý�Ҫ���õ�action��������
			String methodName = config.getMethod(); // execute
			//����action����ͷ������ƻ�÷�����Ӧ��Method����
			try {
				Method executeMethod = action.getClass().getMethod(methodName);
				//����Ŀ�귽��
				result = (String) executeMethod.invoke(action);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("�����õ�action����������!");
			} 
			
		}
		//3��action�Ľ��·�ɴ�����
		return result;
	}

}
