package com.oa.controller.kit;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

/**
 * Msg 拦截器 1：通过 MsgController.getApiConfig() 得到 ApiConfig 对象，并将其绑定到当前线程之上(利用了
 * ApiConfigKit 中的 ThreadLocal 对象) 2：响应开发者中心服务器配置 URL 与 Token 请求 3：签名检测 注意：
 * MsgController 的继承类如果覆盖了 index 方法，则需要对该 index 方法声明该拦截器
 * 因为子类覆盖父类方法会使父类方法配置的拦截器失效，从而失去本拦截器的功能
 */
public class ApiInterceptor implements Interceptor {

    // private static final Logger log = Logger.getLogger(ApiInterceptor.class);

    public void intercept(Invocation ai) {
	Controller controller = ai.getController();
	if (controller instanceof ApiBase == false)
	    throw new RuntimeException("控制器需要继承 MsgController");

	try {
	    // 签名检测
	    if (checkSignature(controller)) {
		ai.invoke();
	    } else {
		controller.renderText("check signature failure");
	    }
	} finally {
	    // ApiConfigKit.removeThreadLocalApiConfig();
	}
    }

    /**
     * 检测签名
     */
    private boolean checkSignature(Controller controller) {
	String accessToken = controller.getPara("accessToken");
	String signature = controller.getPara("signature");
	String timestamp = controller.getPara("timestamp");
	String nonce = controller.getPara("nonce");
	if (StrKit.isBlank(signature) || StrKit.isBlank(timestamp) || StrKit.isBlank(nonce)
		|| StrKit.isBlank(accessToken)) {
	    controller.renderText("check signature failure");
	    return false;
	}

	// String url = controller.getRequest().getRequestURL().toString();
	/*
	 * T_App_Login_Log loginLog = T_App_Login_Log.dao.getBySessionId(accessToken);
	 * String key = ""; String userAccount = ""; String type =
	 * loginLog.getStr("type"); Integer uid = loginLog.getInt("uid");
	 * if(type.equals(BusinessConstant.LOGIN_TYPE_OFFICE)){ T_User user =
	 * T_User.dao.findById(uid); key =
	 * "InVjlo7czsOWrCSmTPgEUXBzlFnmqpNMQU3ZfilULHyHZiRjVUhxxWpexhYH6f4i";//user.
	 * getStr("pwd"); userAccount = user.getStr("dlid"); }else
	 * if(type.equals(BusinessConstant.LOGIN_TYPE_ENTERPRISE)){ T_Enterprise_Info
	 * enterprise = T_Enterprise_Info.dao.findById(uid); key =
	 * "InVjlo7czsOWrCSmTPgEUXBzlFnmqpNMQU3ZfilULHyHZiRjVUhxxWpexhYH6f4i";//
	 * enterprise.getStr("password"); userAccount = enterprise.getStr("account"); }
	 * 
	 * // 获取请求数据 String inJson = HttpKit.readData(controller.getRequest()); ApiBase
	 * apiBase = (ApiBase)controller; // 解密 String decryptMsgJson =
	 * AESKit.msgDecrypt(inJson,key); apiBase.setInMsgJson(decryptMsgJson); if
	 * (SignatureCheckKit.me.checkSignature(accessToken, nonce, timestamp,
	 * signature, key, url)) { return true; } else { log.error("签名检测失败: " +
	 * " accessToken = " + accessToken + " signature = " + signature +
	 * " timestamp = " + timestamp + " nonce = " + nonce);
	 * 
	 * 
	 * }
	 */
	return false;
    }

}
