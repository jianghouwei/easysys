/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.androidpn.server.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * This is a helper class to look up service objects.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
/**
 * ClassName: ServiceLocator <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年3月17日 上午11:19:35 <br/>
 *
 * @author mao.ru
 * @version
 * @since JDK 1.7
 */
public class ServiceLocator implements ApplicationContextAware {

	public static String USER_SERVICE = "userService";

	private static ApplicationContext context = null;

	private static ServiceLocator servlocator = null;

	/**
	 * Generic method to obtain a service object for a given name.
	 * 
	 * @param name
	 *            the service bean name
	 * @return
	 */
	public static Object getService(String name) {
		return context.getBean(name);
		// return XmppServer.getInstance().getBean(name);
	}

	/**
	 * Obtains the user service.
	 * 
	 * @return the user service
	 */
	public static UserService getUserService() {
        //TODO  集成spring。 web。xml启动     
		return (UserService) context.getBean(USER_SERVICE);
		// return (UserService) XmppServer.getInstance().getBean(USER_SERVICE);
	}

	// add fun
	public static ServiceLocator getInstance() {
		if (servlocator == null)
			servlocator = (ServiceLocator) context.getBean("serviceLocator");
		return servlocator;
	}
	// add fun
	public Object getBean(String name) {  
	    return context.getBean(name);  
	}
	
	// add fun
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		context = arg0;
	}

	// add fun
	public ApplicationContext getApplicationContext() {
		return ServiceLocator.context;
	}

}
