package com.base.interception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Module;
import com.zhilian.util.Permit;

public class AssociationInterceptor implements Interceptor {

	public void intercept(Invocation ai) {
		HttpServletRequest request = ai.getController().getRequest();
		HttpServletResponse response = ai.getController().getResponse();

		String requrl = request.getServletPath();

		if (requrl.startsWith("/Msg") || requrl.startsWith("/Api") || requrl.startsWith("/Mobile")) {// 不拦截微信消息和API、移动端
			ai.invoke();
			return;
		}
//		if (requrl.equalsIgnoreCase("/Main/News/uploadFile") || requrl.equalsIgnoreCase("/Main/News/uploadImage") || requrl.equalsIgnoreCase("/Main/News/uploadFlash")
//				|| requrl.equalsIgnoreCase("/Main/News/uploadMedia")) {// 不拦截信息门户上传文件
//			ai.invoke();
//			return;
//		}
		if (requrl.endsWith(".js") || requrl.endsWith(".gif") || requrl.endsWith(".jpg") || requrl.endsWith(".png") || requrl.endsWith(".css") || requrl.endsWith(".html")
				|| requrl.endsWith(".htm") || requrl.endsWith(".xml") || requrl.endsWith(".swf") || requrl.endsWith(".htc") || requrl.endsWith(".cab")|| requrl.endsWith(".apk")) {
			ai.invoke();
			return;
		}

		if (null == ai.getController().getSession().getAttribute("loginModel")) {
			try {
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(
						"<script language='JavaScript' type='text/javascript' charset='utf-8'>alert('用户未登录或者登录超时，请重新登录！');\n top.location.href='" + request.getContextPath()
								+ "/Login/index';</script>");
				response.getWriter().flush();
				response.getWriter().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}

		if ("/Main".equals(requrl) || "/Main/index".equals(requrl) || requrl.startsWith("/Main/index/")) {
			ai.invoke();
			return;
		}

		requrl = requrl.substring(6);
		int fid = requrl.indexOf("/");
		if (-1 != fid) {
			boolean flag = false;
			int sid = requrl.indexOf("/", fid + 1);
			String maddress = "";
			if (-1 != sid) {
				String subString = requrl.substring(0, sid);
				if (subString.endsWith("ip")) {
					flag = true;
					maddress = "Main/" + subString.substring(0, subString.length() - 2);
				} else {
					maddress = "Main/" + subString;
				}
			} else {
				if (requrl.endsWith("ip")) {
					flag = true;
					maddress = "Main/" + requrl.substring(0, requrl.length() - 2);
				} else {
					maddress = "Main/" + requrl;
				}
			}

			LoginModel loginModel = (LoginModel) request.getSession().getAttribute("loginModel");

			T_Module module = T_Module.dao.findFirst("select * from t_module where address='" + maddress + "' and status='1'");
			if (null == module) {
				ai.getController().renderJson("{\"statusCode\":\"300\", \"message\":\"该功能不存在！\"}");
				return;
			} else {
				if (flag) {
					if (!Permit.haspermit(module.getInt("id"), loginModel.getLimit())) {
						ai.getController().renderJson("{\"statusCode\":\"300\", \"message\":\"无权限进行该操作！\"}");
						return;
					}
				} else {
					String address = module.getStr("address");
					if (address.endsWith("main")) {
						if (!Permit.haspermit(module.getInt("id"), loginModel.getLimit())) {
							ai.getController().renderJson("{\"statusCode\":\"300\", \"message\":\"无权限进行该操作！\"}");
							return;
						}
					}
				}
			}
		}
		ai.invoke();
	}

}
