//
//  NotificationApiController.java
//  FeOA
//
//  Created by LuTH on 2012-4-1.
//  Copyright 2012 flyrise. All rights reserved.
//

package org.androidpn.server.console.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.androidpn.server.util.Config;
import org.androidpn.server.xmpp.push.NotificationManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotificationApiController {

	protected final Log log = LogFactory.getLog(NotificationApiController.class);

	private NotificationManager notificationManager;

	public NotificationApiController() {
		notificationManager = new NotificationManager();
	}

	@RequestMapping(value = "/notification_api_send.do")
	public void send(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("推送消息开始。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		String broadcast = ServletRequestUtils.getStringParameter(request, "broadcast", "Y");
		String username = ServletRequestUtils.getStringParameter(request, "username");
		String title = ServletRequestUtils.getStringParameter(request, "title");
		String message = ServletRequestUtils.getStringParameter(request, "message");
		String uri = ServletRequestUtils.getStringParameter(request, "uri");
		String apiKey = Config.getString("apiKey", "");
		log.debug("apiKey=" + apiKey);
		log.info("推送消息开始。。。。。。。。。。。。。。。" + "broadcast=" + broadcast + ",username=" + username +",message="+message);
		if (broadcast.equalsIgnoreCase("Y")) {
			log.info("推送消息开始:.......... 批量发送" );
			notificationManager.sendBroadcast(apiKey, title, message, uri);
		} else {
			log.info("推送消息开始:.......... 指定发送" );
			notificationManager.sendNotifcationToUser(apiKey, username, title, message, uri);
		}

		response.getWriter().print("{\"result\":\"0\",\"description\":\"success\"}");
	}

}
