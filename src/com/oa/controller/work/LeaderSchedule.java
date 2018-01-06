package com.oa.controller.work;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.work.T_LeaderSchedule;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_User;

@RouteBind(path = "Main/LeaderSchedule", viewPath = "Work/LeaderSchedule")
public class LeaderSchedule extends BaseAssociationController {

    private String menuId = "LeaderSchedule";

    public void main() {
	String stitle = null;
	String sdate = null;
	String edate = null;
	String orderField = "date";
	String orderDirection = "desc";
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String select = "select * ";
	String sqlExceptSelect = " from t_leaderschedule ";
	// 查看人员控制
	sqlExceptSelect += " where (scope=0 OR ( scope=1 AND (','+receiver_id+',') like '%," + u_id + ",%')) OR u_id=" + u_id;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle").trim())) {
		stitle = getPara("stitle").trim();
		sqlExceptSelect += " and title like '%" + stitle + "%'";
		setAttr("stitle", stitle);
	    }

	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and date >='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and date <='" + edate + "'";
		setAttr("edate", edate);
	    }
	    if (null != getPara("pageSize") && !"".equals(getPara("pageSize").trim())) {
		pageSize = getParaToInt("pageSize");
	    }
	    if (null != getPara("orderField") && !"".equals(getPara("orderField").trim())) {
		orderField = getPara("orderField");
		setAttr("orderField", orderField);
	    }
	    if (null != getPara("orderDirection") && !"".equals(getPara("orderDirection").trim())) {
		orderDirection = getPara("orderDirection");
		setAttr("orderDirection", orderDirection);
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
	
	System.out.println(select + sqlExceptSelect);
	try {
	    Page<T_LeaderSchedule> page = T_LeaderSchedule.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("userHM", T_User.getUserHmName());
	    setAttr("page", page);
	    render("main.jsp");
	} catch (Exception e) {
	    toErrorJson("服务器存在错误，数据读取失败！");
	}
    }

    public void addip() {
	Date now = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = formatter.format(now);
	setAttr("nowday", nowday);
	setAttr("leaders", T_User.findLeaderByDepart(1));
	T_LeaderSchedule leader_schedule = new T_LeaderSchedule();
	leader_schedule.set("scope", Constant.SCOPE_ALL);
	setAttr("leader_Schedule", leader_schedule);
	setAttr("option", "option");
	render("add.jsp");
    }

    public void add() {
	try {
	    T_LeaderSchedule temp = getModel(T_LeaderSchedule.class);
	    if(temp.getInt("leader_id") == null){
		toErrorJson("请选择领导！");
		return;
	    }
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    int u_id = loginModel.getUserId();
	    temp.set("u_id", u_id);
	    temp.set("receiver_id", getPara("userId"));
	    temp.set("receiver", getPara("userName"));
	    temp.remove("id");
	    if (temp.save()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
		}
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	    e.printStackTrace();
	}
    }

    public void updateip() {
	Integer id = getParaToInt();
	T_LeaderSchedule temp = T_LeaderSchedule.dao.findById(id);
	if (null == temp) {
	    toErrorJson("您提交的数据有误，记录不存在！");
	    return;
	}
	setAttr("leaders", T_User.findLeaderByDepart(1));
	setAttr("leader_Schedule", temp);
	render("update.jsp");
    }

    public void update() {
	try {
	    T_LeaderSchedule temp = getModel(T_LeaderSchedule.class);
	    temp.set("receiver_id", getPara("userId"));
	    temp.set("receiver", getPara("userName"));
	    
	    if (temp.update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	}
    }

    public void delete() {
	Integer id = getParaToInt();
	T_LeaderSchedule temp = T_LeaderSchedule.dao.findById(id);
	try {
	    if (temp.delete()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	}
    }

    public void view() {
	Integer id = getParaToInt();
	T_LeaderSchedule temp = T_LeaderSchedule.dao.findById(id);
	int leaderId = temp.getInt("leader_id");
	T_User leader = T_User.dao.findById(leaderId);
	if (null != leader) {
	    setAttr("leaderName", leader.getStr("name"));
	}
	if (null != temp) {
	    setAttr("leader_Schedule", temp);
	}
	render("view.jsp");
    }

}
