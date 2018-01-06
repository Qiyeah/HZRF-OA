package com.oa.controller.system.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Module;
import com.zhilian.model.T_Role;
import com.zhilian.model.T_Schedule;
import com.zhilian.model.T_User;
import com.zhilian.model.T_UserRoles;

@RouteBind(path = "/Main/Role", viewPath = "/System/Authority/Role")
public class Role extends BaseAssociationController {

    private static String menuId = "Role";

    public void main() {
	String select = "select id, name, memo, xh, dblimit, status ";
	String qname = "";
	String qstatus = "";
	String orderField = "xh";
	String orderDirection = "";
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String sqlExceptSelect = "from t_role where 1=1";
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
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    Page<T_Role> page = T_Role.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    setAttr("scheduleHM", T_Schedule.getNameMap());
	    render("main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /** 角色信息添加页面 */
    public void addip() {
	render("add.jsp");
    }

    /** 角色信息添加处理 */
    public void add() {
	int xh;
	if (null == getPara("xh") || 0 == getPara("xh").trim().length()) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	try {
	    xh = getParaToInt("xh");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (null == getPara("name") || 0 == getPara("name").trim().length()) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	List<T_Role> roles = T_Role.dao.find("select * from t_role where name='" + getPara("name").trim() + "'");
	if (null != roles && 0 < roles.size()) {
	    toErrorJson("您提交的数据有误，角色名称已经存在！");
	    return;
	}
	try {
	    T_Role role = new T_Role();
	    role.set("name", getPara("name").trim()).set("memo", getPara("memo")).set("xh", xh).set("status",
		    getParaToInt("status"));
	    if (role.save()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /** 角色信息修改页面 */
    public void updateip() {
	Integer rId;
	try {
	    rId = getParaToInt();
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	T_Role role = T_Role.dao.findById(rId);
	if (null == role) {
	    toBJUIJson(300, "您提交的数据有误，角色信息不存在！", "", "", "", "", "");
	    return;
	}
	setAttr("role", role);
	render("update.jsp");
    }

    /** 角色信息修改处理 */
    public void update() {
	Integer rId;
	try {
	    rId = getParaToInt("id");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	int xh;
	if (null == getPara("xh") || 0 == getPara("xh").trim().length()) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	try {
	    xh = getParaToInt("xh");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (null == getPara("name") || 0 == getPara("name").trim().length()) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	T_Role role = T_Role.dao.findById(rId);
	if (null == role) {
	    toErrorJson("您提交的数据有误，角色信息不存在！");
	    return;
	}
	role.set("memo", getPara("memo")).set("xh", xh).set("status", getParaToInt("status"));
	if (!role.getStr("name").equals(getPara("name"))) {
	    List<T_Role> roles = T_Role.dao
		    .find("select * from t_role where name='" + getPara("name").trim() + "' and id<>" + rId + "");
	    if (null != roles && 0 < roles.size()) {
		toErrorJson("您提交的数据有误，角色名称已经存在！");
		return;
	    }
	    role.set("name", getPara("name").trim());
	}
	try {
	    if (role.update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /** 角色信息复制页面 */
    @ActionKey("/Main/Role/addip/copy")
    public void copyip() {
	Integer rId;
	try {
	    rId = getParaToInt();
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	T_Role role = T_Role.dao.findById(rId);
	if (null == role) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	setAttr("role", role);
	render("copy.jsp");
    }

    /** 角色信息复制处理 */
    @ActionKey("/Main/Role/add/copy")
    public void copy() {
	Integer rId;
	try {
	    rId = getParaToInt("id");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	int xh;
	if (null == getPara("xh") || 0 == getPara("xh").trim().length()) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	try {
	    xh = getParaToInt("xh");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (null == getPara("name") || 0 == getPara("name").trim().length()) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	T_Role role = T_Role.dao.findById(rId);
	if (null == role) {
	    toErrorJson("您提交的数据有误，角色名称已经存在！");
	    return;
	}
	List<T_Role> roles = T_Role.dao.find("select * from t_role where name='" + getPara("name").trim() + "'");
	if (null != roles && 0 < roles.size()) {
	    toErrorJson("您提交的数据有误，角色名称已经存在！");
	    return;
	}
	T_Role nwerole = new T_Role();
	nwerole.set("name", getPara("name").trim()).set("memo", getPara("memo")).set("xh", xh)
		.set("xtlimit", role.getStr("xtlimit")).set("dblimit", role.getStr("dblimit"))
		.set("status", getParaToInt("status"));
	try {
	    if (nwerole.save()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /** 角色信息批量删除处理 */
    @Before(Tx.class)
    public void deletes() {
	String rid = getPara("delids");
	String[] rids = rid.split(",");
	try {
	    for (int i = 0; i < rids.length; i++) {
		rid = rids[i];
		if (null != rid && !"".equals(rid)) {
		    T_Role.dao.deleteById(rid);
		    T_UserRoles.dao.deleteBySqlwhere("where r_id=" + rid);
		}
	    }
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /** 角色信息删除处理 */
    @Before(Tx.class)
    public void delete() {
	Integer rId;
	try {
	    rId = getParaToInt();
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	T_Role role = T_Role.dao.findById(rId);
	if (null == role) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	List<T_UserRoles> userRoles = T_UserRoles.dao.find("select * from t_userroles where r_id=" + rId);
	if (null != userRoles && 0 < userRoles.size()) {
	    toErrorJson("角色信息已经被引用，不能够删除！");
	    return;
	}
	try {
	    if (role.delete()) {
		T_UserRoles.dao.deleteBySqlwhere("where r_id=" + rId);
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /** 角色权限分配显示页面 */
    public void persetip() {
	Integer rId;
	try {
	    rId = getParaToInt();
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	T_Role role = T_Role.dao.findById(rId);
	if (null == role) {
	    toErrorJson("您提交的数据有误，角色信息不存在！");
	    return;
	}
	setAttr("role", role);
	String cmlimit = "";
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	T_User user_op = T_User.dao.findById(loginModel.getUserId());
	List<T_UserRoles> roles = user_op.getRoles();
	if (null == roles || 0 == roles.size()) {
	    toErrorJson("您提交的数据有误，用户角色信息不存在！");
	    return;
	} else {
	    List<String> rcmlimits = new ArrayList<String>();
	    for (T_UserRoles userRoles : roles) {
		if (null != userRoles.getRole().getStr("xtlimit")) {
		    rcmlimits.add(userRoles.getRole().getStr("xtlimit").trim());
		}
	    }
	    if (0 == rcmlimits.size()) {
		render("perset.jsp");
	    } else {
		StringBuffer buffer = new StringBuffer("");
		for (String string : rcmlimits) {
		    String[] strings = string.split(",");
		    for (int i = 0; i < strings.length; i++) {
			if (buffer.substring(0).indexOf(strings[i] + ",") == -1) {
			    buffer.append(strings[i] + ",");
			}
		    }
		}
		cmlimit = buffer.substring(0);
	    }
	}
	if (cmlimit == null || cmlimit.trim().length() == 0) {
	    render("perset.jsp");
	} else {
	    List<T_Module> modules = T_Module.getAllModules();
	    setAttr("modules", modules);
	    render("perset.jsp");
	}
    }

    /** 角色权限分配处理 */
    public void perset() {
	Integer rId;
	try {
	    rId = getParaToInt("id");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	if (null == getPara("xtlimit")) {
	    toErrorJson("您提交的数据有误，未设置拥有权限信息！");
	    return;
	}
	T_Role role = T_Role.dao.findById(rId);
	if (null == role) {
	    toErrorJson("您提交的数据有误，用户角色信息不存在！");
	    return;
	}

	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	T_User user_op = T_User.dao.findById(loginModel.getUserId());
	List<T_UserRoles> roles = user_op.getRoles();
	if (null == roles || 0 == roles.size()) {
	    toErrorJson("您提交的数据有误，用户角色信息不存在！");
	    return;
	}

	try {
	    if (role.set("xtlimit", getPara("xtlimit").trim()).update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，权限分配失败！");
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson("数据处理存在错误，权限分配失败！");
	}
    }

    /** 角色待办分配显示页面 */
    public void scheduleip() {
	int id = getParaToInt();
	T_Role role = T_Role.dao.findById(id);
	String dblimit = role.getStr("dblimit");
	Map<Integer, Integer> dbli = new HashMap<Integer, Integer>();
	String[] limits = null;
	if (dblimit != null && dblimit.length() > 0) {
	    limits = dblimit.split(",");
	    for (String li : limits) {
		if (li.length() > 0)
		    dbli.put(Integer.parseInt(li.substring(1)), 1);
	    }
	}
	List<T_Schedule> schedules = T_Schedule.getList();
	setAttr("schedules", schedules);
	setAttr("dbli", dbli);
	setAttr("role", role);
	render("schedule.jsp");
    }

    /** 角色待办分配处理 */
    public void schedule() {
	Integer id = getParaToInt("id");
	T_Role role = T_Role.dao.findById(id);
	String[] limits = getParaValues("schedule");
	StringBuffer dblimit = new StringBuffer();
	if (limits != null) {
	    for (String limit : limits) {
		dblimit.append(",_" + limit);
	    }
	    dblimit.deleteCharAt(0);
	}
	role.set("dblimit", dblimit.toString());
	role.update();
	toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
    }

}
