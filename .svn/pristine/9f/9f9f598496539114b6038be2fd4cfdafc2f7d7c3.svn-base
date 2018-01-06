package com.oa.controller.system.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.approve.T_Personal;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_Position;
import com.zhilian.model.T_Role;
import com.zhilian.model.T_User;
import com.zhilian.model.T_UserRoles;
import com.zhilian.util.Pwd;

@RouteBind(path = "/Main/User", viewPath = "System/Authority/User")
public class User extends BaseAssociationController {

    private static String menuId = "User";

    public void main() {
	String select = "select * ";
	int qdeptid = 0;
	String qname = "";
	String orderField = "name";
	String orderDirection = "";
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String sqlExceptSelect = "from t_user where 1=1 ";
	try {
	    if (null != getPara("qname") && !"".equals(getPara("qname").trim())) {
		qname = getPara("qname").trim();
		sqlExceptSelect += " and name like '%" + qname + "%'";
		setAttr("qname", qname);
	    }
	    if (null != getPara("qdeptid") && !"".equals(getPara("qdeptid").trim())) {
		qdeptid = getParaToInt("qdeptid");
		sqlExceptSelect += " and d_id=" + qdeptid;
		setAttr("qdeptid", qdeptid + "");
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
	getdept();

	try {
	    Page<T_User> page = T_User.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    if (null != page.getList() && 0 < page.getList().size()) {
		Map<String, String> rnameMap = new HashMap<String, String>();
		for (int i = 0; i < page.getList().size(); i++) {
		    List<T_UserRoles> userRoless = page.getList().get(i).getAllRoleInfo();
		    if (null != userRoless && 0 < userRoless.size()) {
			StringBuffer buffer = new StringBuffer("");
			for (T_UserRoles userRoles : userRoless) {
			    buffer.append("【" + userRoles.getStr("name") + "】");
			}
			rnameMap.put(page.getList().get(i).getInt("id") + "", buffer.substring(0));
		    }
		}
		setAttr("rnameMap", rnameMap);
	    }
	    setAttr("deptHM", T_Department.dao.hashMapById("fname"));
	    setAttr("positionHM", T_Position.dao.hashMapById("name"));
	    render("main.jsp");
	} catch (Exception e) {
	    toErrorJson("服务器存在错误，数据读取失败！");
	}
    }

    public void addip() {
	LoginModel loginModel = getSessionAttr("loginModel");
	int areaId = loginModel.getAreaId();
	List<T_Department> depts = T_Department.getAdminDeptsByArea(areaId);
	setAttr("depts", depts);
	//getdept1(areaId);
	List<T_Position> positions = T_Position.getList();
	setAttr("positions", positions);
	render("add.jsp");
    }

    public void add() {
	if (null == T_Department.dao.findById(getParaToInt("dept.id"))) {
	    toErrorJson("您提交的数据有误，部门信息不存在！");
	    return;
	}
	if (null == T_Position.dao.findById(getParaToInt("position.id"))) {
	    toErrorJson("您提交的数据有误，职位信息不存在！");
	    return;
	}
	if (null != T_User.dao.findFirst("select * from t_user where dlid='" + getPara("dlid").trim() + "'")) {
	    toErrorJson("您提交的数据有误，用户登录ID已存在！");
	    return;
	}
	T_User user = new T_User();

	try {
	    boolean value1 = user.set("dlid", getPara("dlid").trim()).set("name", getPara("name").trim())
		    .set("pwd", getPara("pwd").trim()).set("status", getParaToBoolean("usable"))
		    .set("d_id", getParaToInt("dept.id")).set("pid", getParaToInt("position.id"))
		    .set("memo", getPara("memo")).set("lo", getPara("lo")).save();
	    if (value1) {
		boolean value2 = user.set("pwd", Pwd.encrypt(user.getInt("id") + getPara("pwd").trim())).update();
		//将新增用户添加至通讯录
		T_Personal personnal=new T_Personal();
		personnal.set("u_id", user.getInt("id"));
		personnal.set("gradename", T_Position.dao.findValueById("name", user.getInt("pid")));
		boolean value3=personnal.save();
		if (value1 && value2 && value3) {
		    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
		} else {
		    toErrorJson(Constant.EXCEPTION);
		}
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	}

    }

    public void updateip() {
	String uid = getPara();
	if (null == uid || "".equals(uid.trim())) {
	    toErrorJson("您提交的数据有误，未选择用户信息！");
	    return;
	}
	int u_id;
	try {
	    u_id = Integer.parseInt(uid);
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson("您提交的数据有误，未选择用户信息！");
	    return;
	}
	T_User user = T_User.dao.findById(u_id);
	if (null == user) {
	    toErrorJson("您提交的数据有误，用户信息不存在！");
	    return;
	}
	LoginModel loginModel = getSessionAttr("loginModel");
	int areaId = loginModel.getAreaId();
	List<T_Department> depts = T_Department.getAdminDeptsByArea(areaId);
	setAttr("depts", depts);
	// getdept1(areaId);

	Integer d_id = user.getInt("d_id");
	List<T_Position> positions = T_Position.getList();
	setAttr("positions", positions);
	T_Department department = T_Department.dao.findById(d_id);
	setAttr("user", user);
	setAttr("department", department);
	render("update.jsp");
    }

    public void update() {
	if (null == T_Department.dao.findById(getParaToInt("dept.id"))) {
	    toErrorJson("您提交的数据有误，部门信息不存在！");
	    return;
	}
	if (null == T_Position.dao.findById(getParaToInt("position.id"))) {
	    toErrorJson("您提交的数据有误，职位信息不存在！");
	    return;
	}
	T_User user = T_User.dao.findById(getParaToInt("id"));
	if (null == user) {
	    toErrorJson("您提交的数据有误，用户信息不存在！");
	    return;
	}
	user.set("name", getPara("name").trim()).set("status", getParaToBoolean("usable")).set("lo", getPara("lo"))
		.set("d_id", getParaToInt("dept.id")).set("memo", getPara("memo")).set("pid", getParaToInt("position.id"));
	String pwd = "";
	String qrpwd = "";
	if (null != getPara("pwd") && !"".equals(getPara("pwd").trim())) {
	    if (null != getPara("qrpwd") && !"".equals(getPara("qrpwd").trim())) {
		pwd = getPara("pwd").trim();
		qrpwd = getPara("qrpwd").trim();
		if (!pwd.equals(qrpwd)) {
		    toErrorJson("您提交的数据有误，新密码和确认 密码不一致！");
		    return;
		}
	    } else {
		toErrorJson("您提交的数据有误，确认 密码不能为空！");
		return;
	    }
	    user.set("pwd", Pwd.encrypt(getParaToInt("id") + pwd));
	}
	
	T_Personal personnal=T_Personal.dao.findFirst("select * from t_personal where u_id ="+user.getInt("id"));
	personnal.set("gradename", T_Position.dao.findValueById("name", user.getInt("pid")));
	personnal.update();
	if (user.update()) {
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	} else {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void deletes() {
	String uid = getPara("delids");
	String[] uids = uid.split(",");
	try {
	    for (int i = 0; i < uids.length; i++) {
		uid = uids[i];
		if (null != uid && !"".equals(uid)) {
		    T_User.dao.deleteById(uid);
		    T_UserRoles.dao.deleteBySqlwhere("where u_id=" + uid);
		    T_Personal.dao.deleteBySqlwhere("where u_id=" + uid);
		}
	    }
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void delete() {
	String uid = getPara();
	if (null == uid || "".equals(uid.trim())) {
	    toErrorJson("您提交的数据有误，未选择用户信息！");
	    return;
	}
	int u_id;
	try {
	    u_id = Integer.parseInt(uid);
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson("您提交的数据有误，未选择用户信息！");
	    return;
	}
	T_User user = T_User.dao.findById(u_id);
	if (null == user) {
	    toErrorJson("您提交的数据有误，用户信息不存在！");
	    return;
	}
	try {
	    if (user.delete()) {
		T_UserRoles.dao.deleteBySqlwhere("where u_id=" + u_id);
		T_Personal.dao.deleteBySqlwhere("where u_id=" + u_id);
		toBJUIJson(200, Constant.SUCCESS, "usermg", "", "", "", "");
	    } else {
		toErrorJson("用户信息已经被引用，用户信息删除失败！");
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson("用户信息已经被引用，用户信息删除失败！");
	}
    }

    public void rolesetip() {
	String uid = getPara();
	if (null == uid || "".equals(uid.trim())) {
	    toBJUIJson(300, "您提交的数据有误，未选择用户信息！", "", "", "", "true", "");
	    return;
	}
	int u_id;
	try {
	    u_id = Integer.parseInt(uid);
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson("您提交的数据有误，未选择用户信息！");
	    return;
	}
	T_User user = T_User.dao.findById(u_id);
	if (null == user) {
	    toErrorJson("您提交的数据有误，用户信息不存在！");
	    return;
	}
	setAttr("user", user);
	setAttr("userroles", user.getAllRoleInfo());
	setAttr("allroles", T_Role.getList());
	render("roleset.jsp");
    }

    public void roleset() {
	Integer uId, priority, r_id;
	String[] prioritys, rids;
	try {
	    uId = getParaToInt("id");
	    prioritys = getParaValues("priority");
	    rids = getParaValues("r_id");
	    priority = getParaToInt("priority");
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	T_User user = T_User.dao.findById(uId);
	if (null == user) {
	    toErrorJson("您提交的数据有误，用户信息不存在！");
	    return;
	}
	T_UserRoles userRoles = null;
	try {
	    for (int i = 0; i < rids.length; i++) {
		r_id = Integer.parseInt(rids[i]);
		if (r_id > 0) {
		    priority = Integer.parseInt(prioritys[i]);
		    userRoles = T_UserRoles.dao.findFirst("select * from t_userroles where u_id=? and r_id=?", uId,
			    r_id);
		    if (userRoles == null) {
			userRoles = new T_UserRoles();
			userRoles.set("status", "1").set("u_id", uId).set("r_id", r_id).set("priority", priority)
				.save();
		    } else {
			userRoles.set("priority", priority).update();
		    }
		}
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson("数据处理存在错误，系统角色分配失败！");
	}
	toBJUIJson(200, "用户角色分配完成！", menuId, "", "", "true", "");
    }

    @Clear
    public void roledelete() {
	Integer urId;
	try {
	    urId = getParaToInt();
	} catch (Exception e) {
	    toErrorJson("您提交的数据有误，请检查后重新提交！");
	    return;
	}
	if (0 >= urId) {
	    toErrorJson("您提交的数据有误，请检查后重新提交！");
	    return;
	}
	T_UserRoles userRoles = T_UserRoles.dao.findById(urId);
	if (null == userRoles) {
	    toErrorJson("您提交的数据有误，用户角色信息不存在！");
	    return;
	}
	int uId = userRoles.getInt("u_id");
	try {
	    if (userRoles.delete()) {
		toBJUIJson(200, "用户角色删除完成！", "", "userroleset", "", "", "Main/User/rolesetip/" + uId);
	    } else {
		toErrorJson("数据处理存在错误，用户角色删除失败！");
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson("数据处理存在错误，用户角色删除失败！");
	}
    }

    @Clear
    public void roledelete_temp() {
	toBJUIJson(200, "用户角色删除完成！", "", "", "", "", "");
    }

    /** 修改用户密码 */
    @Clear
    public void pwordsetip() {
	LoginModel loginModel = getSessionAttr("loginModel");
	Integer id = loginModel.getUserId();
	T_User user = T_User.dao.findById(id);
	setAttr("user", user);
	render("pwordset.jsp");
    }

    @Clear
    public void pwordset() {
	T_User user = T_User.dao.findById(getParaToInt());
	if (null == user) {
	    toErrorJson("您提交的数据有误，用户信息不存在！");
	    return;
	}
	if ("".equals(getPara("oldpassword")) || null == getPara("oldpassword")) {
	    toErrorJson("请输入密码！");
	    return;
	}
	String oldpassword = getPara("oldpassword");
	if (!user.get("pwd").equals(Pwd.encrypt(user.get("id") + oldpassword))) {
	    toErrorJson("您提交的密码有误，请重新输入！");
	    return;
	}
	if (null != getPara("password") && !"".equals(getPara("password").trim())) {
	    user.set("pwd", Pwd.encrypt(getParaToInt() + getPara("password").trim()));
	}
	try {
	    if (user.update()) {
		toBJUIJson(200, Constant.SUCCESS, "", "pwdset", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}

    }

    /** 获取部门选项信息 */
    public void getdept() {
	List<T_Department> alldepts = new ArrayList<T_Department>();
	List<T_Department> departments = T_Department.getAllBaseDepts();
	if (null != departments && departments.size() > 0) {
	    List<String[]> deptstrs = new ArrayList<String[]>();
	    for (T_Department dept : departments) {
		T_Department.getchilddept(dept, alldepts, deptstrs);
	    }
	    setAttr("depts", alldepts);
	    setAttr("deptstrs", deptstrs);
	}
    }

    /** 获取部门选项信息 */
    public void getdept1(int areaId) {
	List<T_Department> alldepts = new ArrayList<T_Department>();
	T_Department dept = T_Department.getTopDeptByArea(areaId);
	if (null != dept) {
	    List<String[]> deptstrs = new ArrayList<String[]>();
	    T_Department.getchilddept(dept, alldepts, deptstrs);
	    setAttr("depts", alldepts);
	    setAttr("deptstrs", deptstrs);
	}
    }

}
