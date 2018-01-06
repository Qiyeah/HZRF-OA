package com.oa.controller.system.workflow;

import com.base.controller.BaseController;
import com.jfinal.plugin.activerecord.Page;
import com.oa.model.system.workflow.T_Leader_Dept;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_User;
import com.zhilian.util.StringUtil;

@RouteBind(path = "/Main/LeaderDept", viewPath = "System/Workflow/LeaderDept")
public class LeaderDept extends BaseController {

    private String menuId = "LeaderDept";

    public void main() {

	String select = "select d.*,u.name,u.no ";
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String sqlExceptSelect = "FROM t_leader_dept d LEFT JOIN t_user u ON d.userId=u.id WHERE 1=1";
	try {
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
	sqlExceptSelect += " ORDER BY u.no ";
	try {
	    Page<T_Leader_Dept> page = T_Leader_Dept.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);

	    setAttr("userHM", T_User.getUserHmName());

	    render("main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void addip() {
	setAttr("userList", T_Leader_Dept.getNoLeaderList());
	setAttr("deptList", T_Leader_Dept.getNoLeaderDeptList());
	render("add.jsp");
    }

    public void add() {
	try {
	    T_Leader_Dept model = this.getModel(T_Leader_Dept.class);
	    String[] depts = getParaValues("depts");
	    model.set("deptIds", StringUtil.combine(depts));
	    if (model.save()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
	    }
	} catch (Exception e) {
	    toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
	}
    }

    public void updateip() {
	int id = getParaToInt(0);
	T_Leader_Dept leaderDept = T_Leader_Dept.dao.findById(id);
	setAttr("leaderDept", leaderDept);
	setAttr("deptList", T_Leader_Dept.getNoLeaderDeptWithSelfList(leaderDept.getStr("deptIds")));
	setAttr("userName", T_User.getUserNameById(leaderDept.getInt("userId")));

	render("update.jsp");
    }

    public void update() {
	try {
	    T_Leader_Dept model = this.getModel(T_Leader_Dept.class);
	    String[] depts = getParaValues("depts");
	    model.set("deptIds", StringUtil.combine(depts));
	    if (model.update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void delete() {
	int id = getParaToInt(0);
	T_Leader_Dept model = T_Leader_Dept.dao.findById(id);

	if (model.delete()) {
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	} else {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

}