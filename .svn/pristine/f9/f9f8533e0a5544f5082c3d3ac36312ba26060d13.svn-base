package com.oa.controller.work;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.work.T_Note;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;

@RouteBind(path = "Main/Note", viewPath = "Work/Note")
public class Note extends BaseAssociationController {

    private String menuId = "Note";

    public void main() {
	String stitle = null;
	String stype = null;
	String sdate = null;
	String edate = null;
	String orderField = "wdate";
	String orderDirection = "desc";
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String select = "select * ";
	String sqlExceptSelect = "from t_note where u_id=" + u_id;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle").trim())) {
		stitle = getPara("stitle").trim();
		sqlExceptSelect += " and title like '%" + stitle + "%'";
		setAttr("stitle", stitle);
	    }
	    if (null != getPara("stype") && !"".equals(getPara("stype").trim())) {
		stype = getPara("stype").trim();
		sqlExceptSelect += " and type like '%" + stype + "%'";
		setAttr("stype", stype);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and wdate>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and wdate<='" + edate + "'";
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
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    Page<T_Note> page = T_Note.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    render("main.jsp");
	} catch (Exception e) {
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
	    T_Note temp = getModel(T_Note.class);
	    if (temp.save()) {
		
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    public void updateip() {
	Integer id = getParaToInt();
	T_Note temp = T_Note.dao.findById(id);
	if (null == temp) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    return;
	}
	setAttr("note", temp);
	render("update.jsp");
    }

    public void update() {
	try {
	    T_Note temp = getModel(T_Note.class);
	    if (temp.update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    public void delete() {
	Integer id = getParaToInt();
	T_Note temp = T_Note.dao.findById(id);
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
	T_Note temp = T_Note.dao.findById(id);
	if (null == temp) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    return;
	}
	setAttr("note", temp);
	render("view.jsp");
    }

}
