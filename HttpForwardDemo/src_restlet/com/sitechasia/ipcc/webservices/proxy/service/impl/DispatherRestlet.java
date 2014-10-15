/**
 * 
 */
package com.sitechasia.ipcc.webservices.proxy.service.impl;

import org.restlet.Redirector;
import org.restlet.Restlet;
import org.restlet.data.Request;
import org.restlet.data.Response;

/**
 * <p>
 * 
 * @author <a href="mailto:yaochangjie@ceopen.cn">姚长捷</a>
 *         </p>
 *         <br>
 *         create: 3:58:10 PM <br>
 *         tags:
 */
public class DispatherRestlet extends Restlet {
	/**
	 * 请求转发对象
	 */
	private Redirector reqRedirector = null;

	/*
	 * 对REST请求进行转发处理 (non-Javadoc)
	 * 
	 * @see org.restlet.Restlet#handle(org.restlet.data.Request,
	 *      org.restlet.data.Response)
	 */
	@Override
	public void handle(Request request, Response response) {
		this.getReqRedirector().handle(request, response);
	}

	public Redirector getReqRedirector() {
		return reqRedirector;
	}

	public void setReqRedirector(Redirector reqRedirector) {
		this.reqRedirector = reqRedirector;
	}

}
