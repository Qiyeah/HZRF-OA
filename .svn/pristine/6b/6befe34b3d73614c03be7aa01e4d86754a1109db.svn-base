package com.oa.controller.system.settings;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Common;
import com.zhilian.model.T_Common_Detail;

@RouteBind(path = "Main/CommonParameter", viewPath = "System/Settings/CommonParameter")
public class CommonParameter extends BaseAssociationController {

    private String divId = "commonBox";
    private String treeId = "commonTree";
    private String menuId = "common";

    public void main() {
	Integer cid = 1;
	if (null != getPara("cid")) {
	    try {
		cid = getParaToInt("cid");
	    } catch (Exception e) {
		toErrorJson(Constant.EXCEPTION);
		return;
	    }
	}
	List<T_Common> commons = T_Common.getNoFirstList();
	setAttr("commons", commons);
	setAttr("cid", cid);
	setAttr("boxID", divId);
	render("main.jsp");
    }

    @ActionKey("/Main/CommonParameter/main/tree")
    public void tree() {
	List<T_Common> commons = T_Common.getNoFirstList();
	setAttr("commons", commons);
	render("tree.jsp");
    }

    @ActionKey("/Main/CommonParameter/main/list")
    public void list() {
	Integer cid;
	try {
	    cid = getParaToInt(0);
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (1 == cid) {
	    setAttr("commons", T_Common.getNoFirstList());
	    render("dir_list.jsp");
	} else {
	    T_Common common = T_Common.dao.findById(cid);
	    if (null == common) {
		toErrorJson(Constant.EXCEPTION);
		return;
	    }
	    if (common.getInt("status") == 0) {
		toErrorJson(Constant.DISABLE);
		return;
	    }
	    setAttr("details", T_Common_Detail.getListByCid(cid));
	    setAttr("common", common);
	    render("list.jsp");
	}
    }

    /*********************** 参数值操作 ************************/
    public void addip() {
	int cid = getParaToInt(0);
	T_Common common = T_Common.dao.findById(cid);
	setAttr("common", common);
	render("add.jsp");
    }

    public void add() {
	try {
	    T_Common_Detail model = getModel(T_Common_Detail.class);
	    int cid = model.getInt("cid");
	    if (model.save()) {
		toRefreshDivJson(200, Constant.SUCCESS, "true", divId + ":Main/CommonParameter/main/list/" + cid + ";"
			+ treeId + ":/Main/CommonParameter/main/tree");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void updateip() {
	Integer id = getParaToInt();
	T_Common_Detail model = T_Common_Detail.dao.findById(id);
	String commonname = (String) T_Common.dao.findById(model.getInt("cid")).getStr("name");
	setAttr("model", model);
	setAttr("commonname", commonname);
	render("update.jsp");
    }

    public void update() {
	try {
	    T_Common_Detail model = getModel(T_Common_Detail.class);
	    int cid = model.getInt("cid");
	    if (model.update()) {
		toRefreshDivJson(200, Constant.SUCCESS, "true", divId + ":Main/CommonParameter/main/list/" + cid + ";"
			+ treeId + ":/Main/CommonParameter/main/tree");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void delete() {
	Integer id = getParaToInt(0);
	try {
	    T_Common_Detail model = T_Common_Detail.dao.findById(id);
	    int cid = model.getInt("cid");
	    model.delete();
	    toRefreshDivJson(200, Constant.SUCCESS, "", divId + ":Main/CommonParameter/main/list/" + cid + ";" + treeId
		    + ":/Main/CommonParameter/main/tree");
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /******************* 参数操作 *************/
    public void dir_addip() {
	render("dir_add.jsp");
    }

    public void dir_add() {
	try {
	    T_Common model = getModel(T_Common.class);
	    if (model.save()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void dir_updateip() {
	Integer id = getParaToInt();
	T_Common model = T_Common.dao.findById(id);
	setAttr("model", model);
	render("dir_update.jsp");
    }

    public void dir_update() {
	try {
	    T_Common model = getModel(T_Common.class);
	    if (model.update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void dir_delete() {
	Integer id = getParaToInt(0);
	try {
	    T_Common.dao.deleteById(id);
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

}
