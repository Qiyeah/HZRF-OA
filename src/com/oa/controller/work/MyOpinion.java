package com.oa.controller.work;

import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.system.workflow.T_Myopinion;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;

@RouteBind(path = "Main/MyOpinion", viewPath = "Work/MyOpinion")
public class MyOpinion extends BaseAssociationController {

    private String menuId = "MyOpinion";

    public void main() {
	String opinion = null;
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "opinionindex";
	String orderDirection = "";
	String select = "select * ";
	String sqlExceptSelect = "from t_myopinion where u_id=" + u_id;
	try {
	    if (null != getPara("opinion") && !"".equals(getPara("opinion").trim())) {
		opinion = getPara("opinion").trim();
		sqlExceptSelect += " and opinion like '%" + opinion + "%'";
		setAttr("opinion", opinion);
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
	    Page<T_Myopinion> page = T_Myopinion.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    render("main.jsp");
	} catch (Exception e) {
	    toErrorJson("服务器存在错误，数据读取失败！");
	}
    }

    public void addip() {
	render("add.jsp");
    }

    public void add() {
	try {
	    T_Myopinion temp = getModel(T_Myopinion.class);
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    temp.set("u_id", loginModel.getUserId());
	    temp.remove("id");
	    if (temp.save()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	}
    }

    public void updateip() {
	Integer id = getParaToInt();
	T_Myopinion temp = T_Myopinion.dao.findById(id);
	if (null == temp) {
	    toErrorJson("您提交的数据有误，记录不存在！");
	    return;
	}
	setAttr("t_Myopinion", temp);
	render("update.jsp");
    }

    public void update() {
	try {
	    T_Myopinion temp = getModel(T_Myopinion.class);
	    if (temp.update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	}
    }

    public void delete() {
	Integer id = getParaToInt();
	T_Myopinion temp = T_Myopinion.dao.findById(id);
	try {
	    if (temp.delete()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	}
    }

    public void view() {
	Integer id = getParaToInt();
	T_Myopinion temp = T_Myopinion.dao.findById(id);
	if (null == temp) {
	    toErrorJson("您提交的数据有误，记录不存在！");
	    return;
	}
	setAttr("t_Myopinion", temp);
	render("view.jsp");
    }

}
