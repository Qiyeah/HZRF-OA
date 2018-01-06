package com.base.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.oa.controller.login.Login;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Loginlog;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent sessionEvent) {
	// 删除过期邮件
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
	LoginModel loginModel = (LoginModel) sessionEvent.getSession().getAttribute("loginModel");
	if (loginModel != null) {
	    // 注销登录日志
	    T_Loginlog dljl = T_Loginlog.findBySessionId(loginModel.getSessionId());
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    dljl.set("loDate", format.format(new Date())).set("dlms", "注销").update();
	    // 删除在线列表
	    Login.userMap.remove(loginModel.getDlId());
	}

	try {
	    sessionEvent.getSession().removeAttribute("loginModel");
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
