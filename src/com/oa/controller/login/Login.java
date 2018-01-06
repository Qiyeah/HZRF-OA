package com.oa.controller.login;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.regexp.RE;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_Loginlog;
import com.zhilian.model.T_User;
import com.zhilian.model.T_UserRoles;
import com.zhilian.util.DateUtils;
import com.zhilian.util.IpAddr;
import com.zhilian.util.Pwd;
import com.zhilian.util.StringUtil;

@RouteBind(path = "/Login", viewPath = "Index/Login")
public class Login extends Controller {

    public static Map<String, HttpSession> userMap = new HashMap<String, HttpSession>();

    @Clear
    public void index() {
	render("login.jsp");
    }

    /** 登录 */
    @Clear
    public void login() {
	String dlid = "";
	String pwd = "";
	if (null == getPara("dlid") || getPara("dlid").trim().equals("") || null == getPara("pwd")
		|| getPara("pwd").trim().equals("")) {
	    setAttr("dlid", getPara("dlid"));
	    setAttr("pwd", getPara("pwd"));
	    setAttr("utype", getPara("utype"));
	    setAttr("mes", "alert('用户名或者密码未输入！')");
	    render("login.jsp");
	    return;
	}
	dlid = getPara("dlid");
	pwd = getPara("pwd");
	RE re = new RE("^[A-Za-z0-9]+$");
	if (!re.match(dlid) || !re.match(pwd)) {
	    setAttr("dlid", dlid);
	    setAttr("pwd", pwd);
	    setAttr("utype", getPara("utype"));
	    setAttr("mes", "alert('输入错误,用户名和密码为英文字母和数字组合！')");
	    render("login.jsp");
	    return;
	}
	T_User user = T_User.exists(dlid);
	if (null != user) {
	    if (user.get("pwd").equals(Pwd.encrypt(user.get("id") + pwd))) {
		if (user.getInt("status") == 1) {
		    try {
			if (userMap.containsKey(dlid)) {
			    userMap.get(dlid).removeAttribute("loginModel");
			    userMap.remove(dlid);
			} else {
			    if (userMap.containsValue(getSession())) {
				getSession().removeAttribute("loginModel");
			    }
			}
			if (null != T_Loginlog.findBySessionId(getSession().getId())) {
			    getSession().removeAttribute("loginModel");
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		    userMap.put(dlid, getSession(true));
		    T_Loginlog dljl = T_Loginlog.findBySessionId(getSession().getId());
		    if (null == dljl) {
			dljl = new T_Loginlog();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dljl.set("sessionId", getSession().getId()).set("dlms", "正常")
				.set("dltime", format.format(new Date())).set("u_id", user.get("id"))
				.set("ip", IpAddr.getIpAddr(getRequest())).save();
		    }
		    LoginModel loginModel = new LoginModel();
		    loginModel.setUserId(user.getInt("id"));
		    loginModel.setDlId(user.getStr("dlid"));
		    loginModel.setUserName(user.getStr("name"));
		    loginModel.setPid(user.getInt("pid"));
		    loginModel.setLo("0");
		    loginModel.setSessionId(getSession().getId());

		    if (null != user.get("d_id")) {
			loginModel.setDid(user.getInt("d_id"));
			T_Department dept = user.getDept();
			loginModel.setAreaId(dept.getInt("area"));
			loginModel.setDepartment(dept.getStr("sname"));
		    }
		    loginModel.setLo(user.getStr("lo"));
		    List<T_UserRoles> roles = user.getRoles();
		    if (null == roles || 0 == roles.size()) {
			loginModel.setLimit("");
		    } else {
			List<String> rlimits = new ArrayList<String>();
			for (T_UserRoles userRoles : roles) {
			    if (null != userRoles.getRole().getStr("xtlimit")) {
				rlimits.add(userRoles.getRole().getStr("xtlimit").trim());
			    }
			}
			if (0 == rlimits.size()) {
			    loginModel.setLimit("");
			} else {
			    loginModel.setLimit(StringUtil.distinctStr(rlimits));
			}
		    }
		    setSessionAttr("loginModel", loginModel);
		    // 记录用户登陆次数和最后登陆时间
		    user.set("webcount", user.getInt("webcount") + 1).set("logindate", DateUtils.getNowDate()).update();
		    // 主页跳转
		    String rootpath = getRequest().getScheme() + "://" + getRequest().getServerName() + ":"
			    + getRequest().getServerPort() + getRequest().getContextPath();
		    if (Constant.SERVER_NAME.equals("") || !Constant.SERVER_NAME.equals(getRequest().getServerName())) {
			Constant.SERVER_NAME = getRequest().getServerName();
			Constant.SERVER_PORT = getRequest().getServerPort() + "";
		    }
		    Constant.PATH_SHOW = rootpath;
		    setCookie("oa_sessionId", loginModel.getSessionId(), 60 * 60 * 24);
		    redirect(rootpath + "/Main");
		} else {
		    setAttr("dlid", dlid);
		    setAttr("pwd", pwd);
		    setAttr("mes", "alert('用户已禁用！')");
		    render("login.jsp");
		}
	    } else {
		setAttr("dlid", dlid);
		setAttr("pwd", pwd);
		setAttr("mes", "alert('用户名或者密码错误！')");
		render("login.jsp");
	    }
	} else {
	    setAttr("dlid", dlid);
	    setAttr("pwd", pwd);
	    setAttr("mes", "alert('用户名或者密码错误！')");
	    render("login.jsp");
	}

    }

    /** 注销 */
    @Clear
    public void logout() throws Exception {
	if (getSession().getAttribute("loginModel") != null) {
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    T_Loginlog dljl = T_Loginlog.findBySessionId(loginModel.getSessionId());
	    String dlId = loginModel.getDlId();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    dljl.set("loDate", format.format(new Date())).set("dlms", "注销").update();
	    try {
		getSession().removeAttribute("loginModel");
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    userMap.remove(dlId);
	}
	if (getCookie("oa_sessionId") != null) {
	    removeCookie("oa_sessionId");
	}
	forwardAction("/Login/index");
    }

    /** 转到提示更换浏览器界面 */
    public void changeBrowse() {
	render("changeBrowse.jsp");
    }

}
