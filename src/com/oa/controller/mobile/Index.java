package com.oa.controller.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Clear;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.model.T_Module;
import com.zhilian.model.T_User;
import com.zhilian.model.T_UserRoles;
import com.zhilian.util.DateUtils;
import com.zhilian.util.Permit;
import com.zhilian.util.Pwd;
import com.zhilian.util.StringUtil;

@RouteBind(path = "/Api/Mobile", viewPath = "/")
public class Index extends BaseAssociationController {

    public static Map<String, T_User> userMap = new HashMap<String, T_User>();

    @Clear
    public void login() {
	String dlid = "";
	String pwd = "";
	if (null != getPara(0) && !getPara(0).trim().equals("") && null != getPara(1)
		&& !getPara(1).trim().equals("")) {
	    dlid = getPara(0);
	    pwd = getPara(1);

	}
	String key = Pwd.encrypt(dlid);

	T_User user = T_User.exists(dlid);
	if (null != user) {
	    if (user.get("pwd").equals(Pwd.encrypt(user.get("id") + pwd))) {
		int status = user.getInt("status");
		if (status == 1 || status == 3) { // 审核通过或暂停
		    try {
			if (userMap.containsKey(key)) {
			    userMap.remove(key);
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		    userMap.put(key, user);
		    BussinessContent bc = new BussinessContent();
		    bc.setUserMap(userMap);
		    // 记录用户登陆次数和最后登陆时间
		    user.set("appcount", user.getInt("appcount") + 1).set("logindate", DateUtils.getNowDate()).update();
		    // 是够显示初审分办
		    String startReceive = "0";

		    T_Module module = T_Module.getModuleByMark("Achieve_apply");
		    if (null != module) {
			int moduleId = module.getInt("id");
			String limits = "";
			List<T_UserRoles> roles = user.getRoles();
			if (null != roles && roles.size() > 0) {
			    List<String> rlimits = new ArrayList<String>();
			    for (T_UserRoles userRoles : roles) {
				if (null != userRoles.getRole().getStr("xtlimit")) {
				    rlimits.add(userRoles.getRole().getStr("xtlimit").trim());
				}
			    }
			    if (0 == rlimits.size()) {
				limits = "";
			    } else {
				limits = StringUtil.distinctStr(rlimits);
			    }
			}
			
			if (Permit.haspermit(moduleId, limits)) {
			    startReceive = "1";
			}
		    }

		    renderJson("{status:1,uname:'" + user.getStr("name") + "',msg:'" + key + "',startReceive:'"
			    + startReceive + "'}");
		} else {
		    renderJson("{status:0,msg:'用户已禁用！'}");
		}
	    } else {
		renderJson("{status:0,msg:'用户名或者密码错误！'}");
	    }
	} else {
	    renderJson("{status:0,msg:'用户名或者密码错误！'}");
	}
    }

    @Clear
    public void myInfo() {
	String key = getPara();
	T_User user = userMap.get(key);
	/*
	 * T_Personnal_Info info=T_Personnal_Info.dao.
	 * findFirst("select * from t_personnal_info where prid="+register.
	 * getInt("id")); String
	 * photourl=Constant.PATH_WEBROOT+"/File/Pdf/"+info.getStr("photourl");
	 * File photo = new File(photourl + ".pdf");
	 */
	renderJson(user);
    }

    @Clear
    public void index() {
	redirect("/Login/index");
	return;
    }

}
