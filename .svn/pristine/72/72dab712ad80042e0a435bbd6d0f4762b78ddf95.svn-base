package com.oa.controller.system.authority;

import java.util.HashMap;
import java.util.List;

import com.jfinal.core.ActionKey;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Area;
import com.zhilian.model.T_Department;

@RouteBind(path = "Main/Department", viewPath = "System/Authority/Department")
public class Department extends BaseAssociationController {

    private static String divId = "deptBox";
    private static String treeId = "deptTree";

    public void main() {
	Integer pId = 0;

	if (null != getPara("pId")) {
	    try {
		pId = getParaToInt("pId");
	    } catch (Exception e) {
		e.printStackTrace();
		toErrorJson(Constant.EXCEPTION);
		return;
	    }
	}
	setAttr("pId", pId);
	render("main.jsp");
    }

    @ActionKey("/Main/Department/main/tree")
    public void tree() {
	// List<T_Department> depts = T_Department.dao.getList();
	List<T_Department> depts = T_Department.getOrderDept();
	setAttr("depts", depts);
	render("tree.jsp");
    }

    @ActionKey("/Main/Department/main/list")
    public void list() {
	Integer id;
	try {
	    id = getParaToInt();
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (0 == id) {
	    List<T_Department> depts = T_Department.getAllBaseDepts();
	    setAttr("depts", depts);
	    setAttr("deptname", "惠州市人防办");
	} else {
	    T_Department dept = T_Department.dao.findById(id);
	    if (null == dept) {
		toErrorJson(Constant.EXCEPTION);
		return;
	    }
	    List<T_Department> depts = dept.getChildDepts();
	    setAttr("depts", depts);
	    setAttr("deptname", dept.getStr("fname"));
	}
	setAttr("id", id);
	HashMap<Integer, String> areaHM = T_Area.dao.hashMapById("name");
	setAttr("areaHM", areaHM);
	render("list.jsp");
    }

    public void addip() {
	int organId = 0;
	int dlvl = 1;
	int area = 1;
	try {
	    organId = getParaToInt();
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (0 == organId) {
	    dlvl = 1;
	    area = 1;
	} else {
	    T_Department organ = T_Department.dao.findById(organId);
	    if (null == organ) {
		toErrorJson("您提交的数据有误，部门信息不存在！");
		return;
	    }
	    dlvl = organ.getInt("dlvl") + 1;
	    area = organ.getInt("area");
	}
	setAttr("organId", organId);
	setAttr("area", area);
	setAttr("areaname", T_Area.getAreaName(area));
	setAttr("dlvl", dlvl);
	List<T_Area> areas = T_Area.dao.list();
	setAttr("areas", areas);
	render("add.jsp");
    }

    public void add() {
	Integer pid = getParaToInt("t_Department.pid");
	if (null == pid) {
	    pid = 0;
	}
	T_Department model = getModel(T_Department.class);
	try {
	    if (model.save()) {
		toRefreshDivJson(200, Constant.SUCCESS, "true",
			divId + ":Main/Department/main/list/" + pid + ";" + treeId + ":/Main/Department/main/tree");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void updateip() {
	int id;
	try {
	    id = getParaToInt();
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (0 == id) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	T_Department model = T_Department.dao.findById(id);
	if (null == model) {
	    toErrorJson("您提交的数据有误，部门信息不存在！");
	    return;
	}
	setAttr("model", model);
	List<T_Area> areas = T_Area.dao.list();
	setAttr("areas", areas);
	if (null != model.get("area"))
	    setAttr("areaname", T_Area.getAreaName(model.getInt("area")));
	render("update.jsp");
    }

    public void update() {
	if (null == getPara("t_Department.id") || "".equals(getPara("t_Department.id").trim())) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	int id;
	try {
	    id = getParaToInt("t_Department.id");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (0 == id) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	Integer pid = getParaToInt("t_Department.pid");
	if (null == pid)
	    pid = 0;
	T_Department model = getModel(T_Department.class);
	try {
	    if (model.update()) {
		toRefreshDivJson(200, Constant.SUCCESS, "true",
			divId + ":Main/Department/main/list/" + pid + ";" + treeId + ":/Main/Department/main/tree");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void delete() {
	int organId;
	int organIdParentId;
	try {
	    organId = getParaToInt();
	    organIdParentId = getParaToInt("organIdParentId");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (0 == organId) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}

	T_Department organ = T_Department.dao.findById(organId);
	if (null == organ) {
	    toErrorJson("您提交的数据有误，部门信息不存在！");
	    return;
	}
	T_Department organ_sum = T_Department.dao.findFirst("select * from t_department where pid=" + organId);
	if (null != organ_sum) {
	    toErrorJson("部门信息已经被引用，部门信息不能够删除！");
	    return;
	}
	try {
	    if (organ.delete()) {
		toRefreshDivJson(200, "部门信息删除成功！", "", divId + ":Main/Department/main/list/" + organIdParentId + ";"
			+ treeId + ":/Main/Department/main/tree");
	    } else {
		toErrorJson("部门信息已经被引用或者数据错误，部门信息删除失败！");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("部门信息已经被引用或者数据错误，部门信息删除失败！");
	}
    }

}
