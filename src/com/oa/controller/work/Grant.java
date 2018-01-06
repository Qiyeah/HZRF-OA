package com.oa.controller.work;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.work.T_Grant;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_User;

@RouteBind(path = "Main/Grant", viewPath = "Work/Grant")
public class Grant extends BaseAssociationController {

    private String menuId = "Grant";

    public void main() {
	String stype = null;
	String sdate = null;
	String edate = null;
	
	String orderField = "id";
	String orderDirection = "desc";
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String select = "select gr.* , u.name as uname";
	String sqlExceptSelect = "from t_grant gr left join t_user u on gr.g_id=u.id where gr.u_id=" + u_id;
	try {

	    if (null != getPara("stype") && !"".equals(getPara("stype").trim())) {
		stype = getPara("stype").trim();
		sqlExceptSelect += " and type = '" + stype + "' ";
		setAttr("stype", stype);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and s_date>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and e_date<='" + edate + "'";
		setAttr("edate", edate);
	    }
	    
	    if (null != getPara("orderField") && !"".equals(getPara("orderField").trim())) {
		orderField = getPara("orderField");
		setAttr("orderField", orderField);
	    }
	    if (null != getPara("orderDirection") && !"".equals(getPara("orderDirection").trim())) {
		orderDirection = getPara("orderDirection");
		setAttr("orderDirection", orderDirection);
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
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    return;
	}
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    Page<T_Grant> page = T_Grant.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    render("main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    public void addip() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	setAttr("u_id", loginModel.getUserId());
	Date now = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = formatter.format(now);
	setAttr("nowday", nowday);
	render("add.jsp");
    }

    public void add() {
	try {
	    T_Grant temp = getModel(T_Grant.class);
	    String chairman = getPara("chairman.userid");
	    if (StrKit.isBlank(chairman)) {
		toErrorJson("请选择授权人！");
		return;
	    }
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    int u_id = loginModel.getUserId();
	    
	    if(temp.getStr("usable").equals("0")){
		Date sdate = temp.getDate("s_date");
		Date edate = temp.getDate("e_date");
		if(T_Grant.dao.isGrant(sdate, edate, temp.getStr("type"),0,u_id)){
		    toErrorJson("该时间段的授权范围已被占用，请核实后再提交！");
		    return;
		}
		String name=T_Grant.dao.isHadOtherGrant(sdate, edate, temp.getStr("type"),0,u_id);
		if(StrKit.notBlank(name)){
		    toErrorJson("该时间段该范围您已被"+name+"赋予权限，不能将权限转赠!");
		}
	    }
	    
	    temp.set("g_id", getParaToInt("chairman.userid"));
	    if (temp.save()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    public void updateip() {
	Integer id = getParaToInt();
	T_Grant temp = T_Grant.dao.findById(id);
	if (null == temp) {
	    toErrorJson("您提交的数据有误，记录不存在！");
	    return;
	}
	
	T_User user = T_User.dao.findById(temp.get("g_id"));
	setAttr("uname", user.get("name"));

	setAttr("grant", temp);
	render("update.jsp");
    }

    public void update() {
	try {
	    T_Grant temp = getModel(T_Grant.class);
	    Integer chairman = getParaToInt("chairman.userid");
	    T_User user = T_User.dao.findFirst("select * from t_user where status =1 and id = " + chairman);
	    if (!user.getStr("name").equals(getPara("chairman.username"))) {
		toErrorJson("请选择授权人");
		return;
	    }
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    int u_id = loginModel.getUserId();
	    if(temp.getStr("usable").equals("0")){
		Date sdate = temp.getDate("s_date");
		Date edate = temp.getDate("e_date");
		if(T_Grant.dao.isGrant(sdate, edate, temp.getStr("type"),temp.getInt("id"), u_id)){
		    toErrorJson("该时间段的授权范围已被占用，请核实后再提交！");
		    return;
		}
		String name=T_Grant.dao.isHadOtherGrant(sdate, edate, temp.getStr("type"),temp.getInt("id"),u_id);
		if(StrKit.notBlank(name)){
		    toErrorJson("该时间段该范围您已被"+name+"赋予权限，不能将权限转赠!");
		}
	    }
	    temp.set("g_id", chairman);
	    if (temp.update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    public void delete() {
	Integer id = getParaToInt();
	T_Grant temp = T_Grant.dao.findById(id);
	try {
	    if (temp.delete()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    public void view() {
	Integer id = getParaToInt();
	T_Grant temp = T_Grant.dao.findById(id);
	if (null == temp) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    return;
	}
	setAttr("grant", temp);
	render("view.jsp");
    }

}
