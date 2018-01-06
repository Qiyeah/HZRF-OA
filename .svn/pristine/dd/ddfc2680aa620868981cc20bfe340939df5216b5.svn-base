package com.oa.controller.document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.oa.model.common.T_Attachment;
import com.oa.model.work.T_Soft;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.controller.BaseController;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_User;
import com.zhilian.util.ArrayUtils;
import com.zhilian.util.DateUtils;

@RouteBind(path = "Main/Soft", viewPath = "Work/Soft/")
public class Soft extends BaseController {
    String menuId = "Soft";

    /** 所有列表 */
    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
	    }

	    if (null != getPara("pageSize") && !"".equals(getPara("pageSize").trim())) {
		pageSize = getParaToInt("pageSize");
	    }
	    if (pageSize <= 0) {
		throw new Exception();
	    }
	    if (null != getPara("pageCurrent") && !"".equals(getPara("pageCurrent").trim())) {
		pageNumber = getParaToInt("pageCurrent");
	    }
	    if (pageNumber <= 0) {
		throw new Exception();
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	String select = "select dc.*, u.name as uname";
	String sqlExceptSelect = "from t_soft dc left join t_user u on dc.u_id=u.id where 1=1 ";
	if (null != stitle) {
	    sqlExceptSelect += " and dc.softname like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}

	sqlExceptSelect += " order by dc.updatetime desc";
	Page<T_Soft> page = T_Soft.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	render("main.jsp");
    }

    public void addip() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int u_id = loginModel.getUserId();
	setAttr("u_id", u_id);
	setAttr("uname", loginModel.getUserName());
	setAttr("d_id", loginModel.getDid());
	setAttr("dname", loginModel.getDepartment());
	setAttr("localarea", "hys");

	Date now = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = df.format(now);
	setAttr("nowday", nowday);
	render("add.jsp");
    }

    public void add() {
	boolean flag = false;
	try {
	    T_Soft temp = getModel(T_Soft.class);
	    String[] fjids = getParaValues("fjid");
	    if(fjids == null){
		toErrorJson("请上传附件！");
		return;
	    }
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    
	    temp.set("updatetime", DateUtils.getNowDate());
	    flag = temp.save();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	}
    }

    public void updateip() {
	int id = getParaToInt();
	T_Soft dc = T_Soft.dao.findById(id);
	setAttr("uname", T_User.dao.findValueById("name", dc.getInt("u_id")));
	setAttr("dc", dc);

	List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	setAttr("fileList", fileList);
	render("update.jsp");
    }

    public void update() {
	boolean flag = false;
	try {
	    T_Soft temp = getModel(T_Soft.class);
	    String[] fjids = getParaValues("fjid");
	    if(fjids == null){
		toErrorJson("请上传附件！");
		return;
	    }
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    Date now = new Date();
	    temp.set("updatetime", now);
	    flag = temp.update();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	}
    }

    public void delete() {
	int id = getParaToInt();
	T_Soft dc = T_Soft.dao.findById(id);
	dc.delete();
	toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
    }
}
