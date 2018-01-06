package com.oa.controller.system.authority;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Position;
import com.zhilian.model.T_User;

@RouteBind(path = "/Main/Position", viewPath = "System/Authority/Position")
public class Position extends BaseAssociationController {

    private static String menuId = "Position";

    public void main() {
	String select = "select id, xh, name, status";
	String qname = "";
	String qstatus = "";
	String orderField = "xh";
	String orderDirection = "";
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String sqlExceptSelect = "from T_Position where 1=1";
	try {
	    if (null != getPara("qname") && !"".equals(getPara("qname").trim())) {
		qname = getPara("qname");
		sqlExceptSelect += " and name like '%" + qname + "%'";
		setAttr("qname", qname);
	    }
	    if (null != getPara("qstatus") && !"".equals(getPara("qstatus").trim())) {
		qstatus = getPara("qstatus");
		sqlExceptSelect += " and status='" + qstatus + "'";
		setAttr("qstatus", qstatus);
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
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    Page<T_Position> page = T_Position.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    render("main.jsp");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson("服务器存在错误，数据读取失败！");
	}
    }

    public void addip() {
	render("add.jsp");
    }

    public void add() {
	int xh;
	try {
	    xh = getParaToInt("t_Position.xh");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	List<T_Position> positions = T_Position.dao.find("select * from T_Position where xh=" + xh);
	if (null != positions && 0 < positions.size()) {
	    toErrorJson("您提交的数据有误，该职位序号已经存在！");
	    return;
	}
	List<T_Position> position_name = T_Position.dao
		.find("select * from T_Position where name='" + getPara("t_Position.name").trim() + "'");
	if (null != position_name && 0 < position_name.size()) {
	    toErrorJson("您提交的数据有误，该职位序号已经存在！");
	    return;
	}
	try {
	    T_Position position = getModel(T_Position.class);
	    if (position.save()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void updateip() {
	Integer id;
	try {
	    id = getParaToInt();
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	T_Position position = T_Position.dao.findById(id);
	if (null == position) {
	    toErrorJson("您提交的数据有误，职位信息不存在！");
	    return;
	}
	setAttr("position", position);
	render("update.jsp");
    }

    public void update() {
	int id = 0;
	try {
	    id = getParaToInt("t_Position.id");
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	int xh;
	try {
	    xh = getParaToInt("t_Position.xh");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	T_Position position1 = T_Position.dao.findById(id);
	if (null == position1) {
	    toErrorJson("您提交的数据有误，职位信息不存在！");
	    return;
	}
	// 查询职位序号是否重复
	if (!(position1.getInt("xh") == xh)) {
	    List<T_Position> position_pid = T_Position.dao.find("select * from T_Position where xh=" + xh);
	    if (null != position_pid && 0 < position_pid.size()) {
		toErrorJson("您提交的数据有误，职位序号已经存在！");
		return;
	    }
	}
	// 查询职位名称是否重复
	if (!position1.getStr("name").equals(getPara("t_Position.name"))) {
	    List<T_Position> position_name = T_Position.dao
		    .find("select * from T_Position where name='" + getPara("t_Position.name") + "'");
	    if (null != position_name && 0 < position_name.size()) {
		toErrorJson("您提交的数据有误，职位名称已经存在！");
		return;
	    }
	}
	try {
	    T_Position position = getModel(T_Position.class);
	    if (position.update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void deletes() {
	String pid = getPara("delids");
	String[] pids = pid.split(",");
	try {
	    for (int i = 0; i < pids.length; i++) {
		pid = pids[i];
		if (null != pid && !"".equals(pid)) {
		    List<T_User> user = T_User.dao.find("select * from t_user where pid=" + pid);
		    if (null == user | user.size() == 0) {
			T_Position.dao.deleteById(pid);
		    }
		}
	    }
	    toBJUIJson(200, Constant.SUCCESS + "（注：被用户引用的职位不能删除）", "positionmg", "", "", "", "");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void delete() {
	Integer id = getParaToInt();
	T_Position position = T_Position.dao.findById(id);
	List<T_User> user = T_User.dao.find("select * from t_user where pid=" + id);
	if (null != user && 0 < user.size()) {
	    toErrorJson("职位信息已经被用户引用，不能够删除！");
	    return;
	}
	try {
	    if (position.delete()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

}
