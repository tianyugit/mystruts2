/**
 * 
 */
package com.test.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.config.ActionConfig;
import com.test.config.ConfigurationManager;
import com.test.context.ActionContext;
import com.test.invocation.ActionInvocation;

/**========================================================================
 * 
 * �汾:1.0
 *
 *��������:2017��5��13�� ����9:41:15
 *
 *����:������
 *
 *����:�������ʼ������,��������,�ַ�����.
 *
 *
 *��Ȩ:T�����Ȩ����(c) 2017
 *
 *==========================================================================
 */
public class StrutsPrepareAndExcuteFilter implements Filter{
	
	//�����ļ��еĹ�����������Ϣ
	private List<String> InterceptorList;
	//struts�����action��׺
	private String extension;
	// �����ļ���action������Ϣ
	private Map<String, ActionConfig> actionConfigs;

	/**
	 * ��ʼ��
	 */
	public void init(FilterConfig arg0) throws ServletException {
		//1> ׼��������������
		InterceptorList = ConfigurationManager.getInterceptors();
		//2> ׼��constant����=> ���ʺ�׺��������Ϣ
		extension = ConfigurationManager.getConstant("struts.action.extension");
		//3> ����action����
		actionConfigs = ConfigurationManager.getActionConfig();
		
	}

	/**
	 * ����������
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//0  ǿתrequest��responseΪ HttpServletRequest �� HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse  resp = (HttpServletResponse) response;
		//1 �������·��
		// http://localhost:8080/MyStruts/HellAction.action
		String path = req.getServletPath(); //  /HellAction.action
		//2 �ж������Ƿ���Ҫ����action
			if(!path.endsWith(extension)){
				// ��׺����".action"��β => ����Ҫ����action��Դ => chain.doFIlter()����
				chain.doFilter(request, response);
				return;
			}else{
				// ��׺��".action"��β => ��Ҫ����action 
				//3 �����Ҫ���ʵ�action����=>��ȡ��Ҫ���ʵ�action����
				path = path.substring(1);// HellAction.action
				path = path.replace("."+extension, "");// HellAction
				//4 ����action��Ӧ��������Ϣ
				ActionConfig config = actionConfigs.get(path);
				if(config == null){
					//δ�ҵ�������Ϣ => �׳��쳣��ʾ���ʵ�action������
					throw new RuntimeException("���ʵ�action������!");
				}
				//�ҵ�������Ϣ  => ��õ�������Ϣ=>����
				//5 ����actionInvocationʵ��,��ɶ������������Լ�action�ķ���
				ActionInvocation invocation = new ActionInvocation(InterceptorList,config,req,resp);
				//6 ��ý���� 
				String result = invocation.invoke(invocation); //success
				//7 ��������Ϣ�ҵ��������Ӧ��·��
				String dispatcherPath = config.getResult().get(result);
				//�Ҳ������·��=> �׳��쳣��ʾ���ص�·���Ҳ�����Ӧҳ��
				if(dispatcherPath ==null || "".equals(dispatcherPath)){
					throw new RuntimeException("��Ҫ���ʵĽ��û���ҵ�����!");
				}
				//8 ������ת�������õ�·��
				req.getRequestDispatcher(dispatcherPath).forward(req, resp);
				//�ͷ���Դ
				ActionContext.tl.remove();
			}
		
	}


	/**
	 * ����
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
