package com.oa.controller.system.workflow;

import java.util.HashMap;

import com.jfinal.plugin.activerecord.Page;
import com.oa.model.system.office.T_Bookmark;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.controller.BaseController;

@RouteBind(path = "Main/Bookmark", viewPath = "System/Workflow/Bookmark")
public class Bookmark extends BaseController {

    private String menuId = "Bookmark";

    public void main() {
	Integer pageNum = getParaToInt("pageNum");
	Integer numPerPage = getParaToInt("numPerPage");
	String sname = getPara("sname");
	HashMap<String, String> paraHm = new HashMap<String, String>();
	paraHm.put("name,like", sname);
	Page<T_Bookmark> page = T_Bookmark.dao.page(pageNum, numPerPage, paraHm);
	setAttr("sname", sname);
	setAttr("page", page);
	render("main.jsp");
    }

    public void addip() {
	render("add.jsp");
    }

    public void add() {
	T_Bookmark model = getModel(T_Bookmark.class);
	if (model.save()) {
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	} else {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void updateip() {
	int id = getParaToInt();
	T_Bookmark temp = T_Bookmark.dao.findById(id);
	setAttr("bookmark", temp);
	render("update.jsp");
    }

    public void update() {
	T_Bookmark model = getModel(T_Bookmark.class);
	if (model.update()) {
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	} else {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void delete() {
	int id = getParaToInt();
	T_Bookmark temp = T_Bookmark.dao.findById(id);
	if (temp.delete()) {
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	} else {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

}
