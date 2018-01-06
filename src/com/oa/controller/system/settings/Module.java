package com.oa.controller.system.settings;

import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Module;

@RouteBind(path = "/Main/Module", viewPath = "System/Settings/Module")
public class Module extends BaseAssociationController {

    private static String divId = "moduleBox";
    private static String treeId = "moduleTree";

    public void main() {
	Integer pId = 0;
	if (null != getPara("pId")) {
	    try {
		pId = getParaToInt("pId");
	    } catch (Exception e) {
		toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
		return;
	    }
	}
	setAttr("pId", pId);
	render("main.jsp");
    }

    @ActionKey("/Main/Module/main/tree")
    public void tree() {
	List<T_Module> modules = T_Module.dao.find("select * from t_module where islast=0 order by orderindex");
	setAttr("modules", modules);
	render("tree.jsp");
    }

    @ActionKey("/Main/Module/main/list")
    public void list() {
	boolean flag = false;
	Integer id;
	try {
	    id = getParaToInt();
	} catch (Exception e) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    return;
	}
	if (0 > id) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    return;
	}
	if (0 == id) {
	    List<T_Module> modules = T_Module.getAllBaseModules();
	    setAttr("modules", modules);
	} else {
	    T_Module module = T_Module.dao.findById(id);
	    if (null == module) {
		toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
		return;
	    }
	    List<T_Module> modules = module.getAllChildModules();
	    if (module.getStr("address") != null && !"".equals(module.getStr("address"))) {
		flag = true;
	    }
	    setAttr("module", module);
	    setAttr("modules", modules);
	}
	setAttr("flag", flag);
	setAttr("id", id);
	render("list.jsp");
    }

    public void addip() {
	int pId = 0;
	int islast = 0;
	try {
	    pId = getParaToInt();
	} catch (Exception e) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    return;
	}
	if (0 != pId) {
	    T_Module model = T_Module.dao.findById(pId);
	    if (null == model) {
		toBJUIJson(Constant.STATUS_CODE_ERROR, "您提交的数据有误，上级模块信息不存在！", "", "", "", "", "");
		return;
	    }
	    if (model.getStr("address") != null && !"".equals(model.getStr("address"))) {
		islast = 1;
	    }
	}
	setAttr("pId", pId);
	setAttr("islast", islast);
	render("add.jsp");
    }

    public void add() {
	Integer pid = 0;
	try {
	    if (null == getParaToInt("t_Module.pid")) {
		throw new Exception();
	    }
	    pid = getParaToInt("t_Module.pid");
	} catch (Exception e) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    return;
	}
	if (0 != pid) {
	    if (null == T_Module.dao.findById(pid)) {
		toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
		return;
	    }
	}
	T_Module temp = T_Module.getModuleByMark(getPara("t_Module.mark").trim());
	if (null != temp) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, "您提交的数据有误，模块标识已存在！", "", "", "", "", "");
	    return;
	}
	T_Module module = getModel(T_Module.class);
	try {
	    if (module.save()) {
		toRefreshDivJson(Constant.STATUS_CODE_OK, Constant.SUCCESS, "true",
			divId + ":Main/Module/main/list/" + pid + ";" + treeId + ":/Main/Module/main/tree");
	    } else {
		toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    }
	} catch (Exception e) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	}
    }

    public void updateip() {
	int id;
	try {
	    id = getParaToInt();
	} catch (Exception e) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    return;
	}
	T_Module model = T_Module.dao.findById(id);
	if (null == model) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    return;
	}
	setAttr("model", model);
	render("update.jsp");
    }

    public void update() {
	int id;
	try {
	    id = getParaToInt("t_Module.id");
	} catch (Exception e) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    return;
	}
	int pid = 0;
	try {
	    pid = getParaToInt("t_Module.pid");
	} catch (Exception e) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    return;
	}
	T_Module module = T_Module.dao.findById(id);
	if (null == module) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, "您提交的数据有误，模块信息不存在！", "", "", "", "", "");
	    return;
	}
	List<T_Module> modules = T_Module.dao.find("select * from t_module where mark='" + getPara("t_Module.mark").trim() + "' and id<>" + id);
	if (null != modules && 0 < modules.size()) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, "您提交的数据有误，模块标识已存在！", "", "", "", "", "");
	    return;
	}
	module = getModel(T_Module.class);
	try {
	    if (module.update()) {
		toRefreshDivJson(Constant.STATUS_CODE_OK, Constant.SUCCESS, "true",
			divId + ":Main/Module/main/list/" + pid + ";" + treeId + ":/Main/Module/main/tree");
	    } else {
		toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    }
	} catch (Exception e) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	}
    }

    public void delete() {
	int id;
	try {
	    id = getParaToInt();
	} catch (Exception e) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    return;
	}
	if (0 == id) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	    return;
	}

	T_Module model = T_Module.dao.findById(id);
	if (null == model) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, "您提交的数据有误，功能模块不存在！", "", "", "", "", "");
	    return;
	}
	int pid = model.getInt("pid");
	List<T_Module> modules = T_Module.dao.getChildModules();
	if (null != modules && modules.size() > 0) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, "功能模块已经被引用，不能够删除！", "", "", "", "", "");
	    return;
	}
	try {
	    if (model.delete()) {
		toBJUIJson(Constant.STATUS_CODE_OK, "模块功能已删除！", "", "", "", "", "Main/Module/main?pId=" + pid);
	    } else {
		toBJUIJson(Constant.STATUS_CODE_ERROR, "功能模块已经被引用或者数据错误，删除失败！", "", "", "", "", "");
	    }
	} catch (Exception e) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, "功能模块已经被引用或者数据错误，删除失败！", "", "", "", "", "");
	}
    }

    /** 快速添加方法 */
    @Clear
    public void addFunc() {
	try {
	    Integer id = getParaToInt(0);
	    Integer flag = getParaToInt(1);
	    T_Module model = T_Module.dao.findById(id);
	    String address = model.getStr("address").substring(0, model.getStr("address").length() - 4);
	    String name = model.getStr("name");
	    String mark = model.getStr("mark");
	    List<T_Module> modules = T_Module.dao.find("select * from t_module where mark='" + mark.trim() + "_add'");
	    if (null != modules && 0 < modules.size()) {
		toBJUIJson(Constant.STATUS_CODE_ERROR, "您提交的数据有误，模块标识已存在！", "", "", "", "", "");
		return;
	    }
	    new T_Module().set("name", "添加" + name).set("pid", id).set("address", address + "add")
		    .set("mark", "add" + mark).set("islast", "1").set("opentype", 0).set("orderindex", 1)
		    .set("status", "1").save();
	    new T_Module().set("name", "修改" + name).set("pid", id).set("address", address + "update")
		    .set("mark", "update" + mark).set("islast", "1").set("opentype", 0).set("orderindex", 3)
		    .set("status", "1").save();
	    new T_Module().set("name", "删除" + name).set("pid", id).set("address", address + "delete")
		    .set("mark", "delete" + mark).set("islast", "1").set("opentype", 0).set("orderindex", 5)
		    .set("status", "1").save();
	    if (flag == 1) {
		new T_Module().set("name", "批量删除" + name).set("pid", id).set("address", address + "deletes")
			.set("mark", "deletes" + mark).set("islast", "1").set("opentype", 0).set("orderindex", 7)
			.set("status", "1").save();
	    }
	    toBJUIJson(Constant.STATUS_CODE_OK, "模块功能定义已增加！", "", "", divId, "", "Main/Module/main?pId=" + id);
	} catch (Exception e) {
	    toBJUIJson(Constant.STATUS_CODE_ERROR, Constant.EXCEPTION, "", "", "", "", "");
	}
    }

}