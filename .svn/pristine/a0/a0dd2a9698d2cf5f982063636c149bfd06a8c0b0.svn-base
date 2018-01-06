package com.oa.controller.system.authority;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Area;

@RouteBind(path = "Main/Area", viewPath = "System/Authority/Area")
public class Area extends BaseAssociationController {

    private static String divId = "areaBox";
    private static String treeId = "areaTree";

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

    @ActionKey("/Main/Area/main/tree")
    public void tree() {
	try {
	    List<T_Area> areas = T_Area.dao.list();
	    setAttr("areas", areas);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	render("tree.jsp");
    }

    @ActionKey("/Main/Area/main/list")
    public void list() {
	Integer id;
	try {
	    id = getParaToInt();
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (0 >= id) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}

	T_Area area = T_Area.dao.findById(id);
	if (null == area) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	setAttr("area", area);
	List<T_Area> areas = T_Area.getAllChildAreas(id);
	setAttr("areas", areas);
	setAttr("id", id);
	render("list.jsp");
    }

    public void addip() {
	int areaId = 0;
	int level = 1;
	try {
	    areaId = getParaToInt();
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (0 != areaId) {
	    T_Area area = T_Area.dao.findById(areaId);
	    if (null == area) {
		toErrorJson("您提交的数据有误，区划信息不存在！");
		return;
	    }
	    level = area.getInt("level") + 1;
	}
	setAttr("areaId", areaId);
	setAttr("level", level);
	render("add.jsp");
    }

    public void add() {
	int pid;
	try {
	    pid = getParaToInt("t_Area.pid");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (0 != pid) {
	    T_Area area = T_Area.dao.findById(pid);
	    if (null == area) {
		toBJUIJson(300, "您提交的数据有误，区划信息不存在！", "", "", "", "", "");
		return;
	    }
	}
	T_Area model = getModel(T_Area.class);

	try {
	    if (model.save()) {
		toRefreshDivJson(200, Constant.SUCCESS, "true",
			divId + ":Main/Area/main/list/" + pid + ";" + treeId + ":/Main/Area/main/tree");
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
	T_Area model = T_Area.dao.findById(id);
	if (null == model) {
	    toErrorJson("您提交的数据有误，区划信息不存在！");
	    return;
	}
	setAttr("model", model);
	render("update.jsp");
    }

    public void update() {
	if (null == getPara("t_Area.id") || "".equals(getPara("t_Area.id").trim())) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	int id;
	try {
	    id = getParaToInt("t_Area.id");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (0 == id) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	int pid;
	try {
	    pid = getParaToInt("t_Area.pid");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	T_Area model = getModel(T_Area.class);
	try {
	    if (model.update()) {
		toRefreshDivJson(200, Constant.SUCCESS, "true",
			divId + ":Main/Area/main/list/" + pid + ";" + treeId + ":/Main/Area/main/tree");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void delete() {
	int areaId;
	int pId;
	try {
	    areaId = getParaToInt();
	    pId = getParaToInt("pId");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (0 == areaId) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}

	T_Area area = T_Area.dao.findById(areaId);
	if (null == area) {
	    toErrorJson("您提交的数据有误，区划信息不存在！");
	    return;
	}
	T_Area area_sum = T_Area.dao.findFirst("select * from t_area where pid=" + areaId);
	if (null != area_sum) {
	    toErrorJson("区划信息已经被引用，区划信息不能够删除！");
	    return;
	}
	try {
	    if (area.delete()) {
		toRefreshDivJson(200, "区划信息删除成功！", "",
			divId + ":Main/Area/main/list/" + pId + ";" + treeId + ":/Main/Area/main/tree");
	    } else {
		toErrorJson("区划信息已经被引用或者数据错误，区划信息删除失败！");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("区划信息已经被引用或者数据错误，区划信息删除失败！");
	}
    }

}
