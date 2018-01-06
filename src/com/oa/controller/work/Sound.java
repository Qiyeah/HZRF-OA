package com.oa.controller.work;

import com.oa.controller.BaseAssociationController;
import com.oa.model.work.T_Sound;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;

@RouteBind(path = "Main/Sound", viewPath = "Work/Sound")
public class Sound extends BaseAssociationController {

    public void main() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	T_Sound model = T_Sound.dao.findByUserId(u_id);
	if (null == model) {
	    model = new T_Sound();
	    model.set("voice_id", 1).set("user_id", u_id).save();
	}
	setAttr("sound", model);
	render("main.jsp");
    }

    public void update() {
	Integer no = getParaToInt(0);
	if (null == no) {
	    toErrorJson("您提交的数据有误，提示音不存在！");
	    return;
	}
	try {
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    int u_id = loginModel.getUserId();
	    T_Sound model = T_Sound.dao.findByUserId(u_id);
	    if (null == model) {
		toErrorJson(Constant.EXCEPTION);
	    } else {
		if (model.getInt("voice_id") != no) {
		    model.set("voice_id", no);
		    if (model.update()) {
			toBJUIJson(200, Constant.SUCCESS, "Sound", "", "", "", "");
		    } else {
			toErrorJson(Constant.EXCEPTION);
		    }
		}
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

}
