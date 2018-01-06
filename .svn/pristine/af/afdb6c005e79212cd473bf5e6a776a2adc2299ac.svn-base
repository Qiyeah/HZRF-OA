package com.oa.controller.approve;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.controller.export.ExportPersonalTel;
import com.oa.model.approve.T_Group;
import com.oa.model.approve.T_Personal;
import com.oa.util.ExportExcel;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;

@RouteBind(path = "Main/Personal", viewPath = "Approve/Personal")
public class Personal extends BaseAssociationController {

    private String menuId = "Personals";

    public void main() {
	String uname = "";
	int d_id = 0;
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "u.d_id,u.no";
	String orderDirection = "";
	String select = "select p.*, u.name as uname, u.d_id as d_id,o.xh ";
	String sqlExceptSelect = "from t_personal p left join t_user u on p.u_id=u.id left join t_position o on u.pid=o.id where 1=1 ";
	try {
	    if (null != getPara("uname") && !"".equals(getPara("uname").trim())) {
		uname = getPara("uname").trim();
		sqlExceptSelect += " and u.name like '%" + uname + "%'";
		setAttr("uname", uname);
	    }
	    if (null != getPara("d_id") && !"".equals(getPara("d_id").trim())) {
		d_id = getParaToInt("d_id");
		sqlExceptSelect += " and u.d_id=" + d_id;
		setAttr("d_id", d_id);
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
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	sqlExceptSelect += " order by o.xh," + orderField + " " + orderDirection;
	getdept(getRequest());
	try {
	    HashMap<Integer, String> deptHm = T_Department.dao.hashMapById("fname");
	    setAttr("deptHM", deptHm);
	    Page<T_Personal> page = T_Personal.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    render("list.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("服务器存在错误，数据读取失败！");
	}
    }

    @Clear
    public void telList() {

	System.err.println(Constant.PATH_WEBROOT);
	System.out.println(Constant.PATH_TEMPLATE);

	render("telListmain.jsp");
    }

    @Clear
    @ActionKey("/Main/Personal/main/tree")
    public void tree() {
	// List<T_Department> depts = T_Department.dao.getList();
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();

	List<T_Department> depts = T_Department.getOrderDept();
	List<T_Group> group = T_Group.dao.findByUserId(u_id);
	setAttr("group", group);
	setAttr("depts", depts);
	render("tree.jsp");
    }

    @Clear
    @ActionKey("/Main/Personal/main/telGroup")
    public void telGroup() {
	// List<T_Department> depts = T_Department.dao.getList();
	String id = getPara(0);

	String uname = "";
	int pageSize = 20;
	int pageNumber = 1;
	String orderField = "u.d_id,u.no";
	String orderDirection = "";
	T_Group group = T_Group.dao.findById(id);
	String select = "SELECT p.*,u.name as uname, u.d_id as d_id";
	String sqlExceptSelect = "from t_user u LEFT JOIN t_personal p on p.u_id=u.id  where u.id in ("
		+ group.getStr("u_ids") + ") ";
	try {
	    if (null != getPara("uname") && !"".equals(getPara("uname").trim())) {
		uname = getPara("uname").trim();
		sqlExceptSelect += " and u.name like '%" + uname + "%'";
		setAttr("uname", uname);
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
		System.out.println(pageSize);
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
	try {
	    HashMap<Integer, String> deptHm = T_Department.dao.hashMapById("fname");
	    setAttr("deptHM", deptHm);
	    setAttr("groupid", group.get("id"));
	    Page<T_Personal> page = T_Personal.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    render("tellist.jsp");
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    @Clear
    @ActionKey("/Main/Personal/main/telListmain")
    public void telListmain() {
	String uname = "";
	int pageSize = 20;
	int pageNumber = 1;
	String orderField = "u.d_id,o.xh";
	String orderDirection = "";
	Integer id = null;
	String select = "select p.*, u.name as uname, u.d_id as d_id ";
	String sqlExceptSelect = "from t_personal p left join t_user u on p.u_id=u.id left join t_position o on u.pid=o.id where 1=1 and u.status=1";
	try {
	    if (null != getPara("uname") && !"".equals(getPara("uname").trim())) {
		uname = getPara("uname").trim();
		sqlExceptSelect += " and u.name like '%" + uname + "%'";
		setAttr("uname", uname);
	    }
	    if (StrKit.notBlank(getPara(0))) {
		id = getParaToInt(0);
		if (id > 0) {
		    sqlExceptSelect += " and u.d_id=" + id;
		    setAttr("d_id", id);
		}
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
		System.out.println(pageSize);
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
	try {
	    HashMap<Integer, String> deptHm = T_Department.dao.hashMapById("fname");
	    setAttr("deptHM", deptHm);
	    Page<T_Personal> page = T_Personal.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    setAttr("groupid", 0);
	    render("tellist.jsp");
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void creategroupip() {
	List<T_Department> deparmentList = T_Department.getAllDepts();
	List<T_User> userList = T_User.dao.find("select * from t_user where status=1 order by d_id");
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	setAttr("deparmentList", deparmentList);
	setAttr("userList", userList);
	setAttr("u_id", u_id);
	render("creategroup.jsp");
    }

    public void creategroup() {// 保存新增群组信息
	String[] u_ids = getParaValues("u_ids");
	String[] u_names = getParaValues("u_names");
	String name = getPara("t_Group.name");
	if (u_ids == null) {
	    toErrorJson("请选择群成员！");
	    return;
	}
	String uids = "", unames = "";
	for (int i = 0; i < u_ids.length; i++) {
	    uids += "," + u_ids[i];
	    unames += "," + u_names[i];
	}
	int u_id = getParaToInt("u_id");
	uids = uids.substring(1, uids.length());
	unames = unames.substring(1, unames.length());
	T_Group group = new T_Group();
	group.set("u_id", u_id);
	group.set("u_ids", uids);
	group.set("u_names", unames);
	group.set("name", name);
	if (group.save()) {
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	} else {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void editgroupip() {
	Integer id = getParaToInt(0);
	T_Group group = T_Group.dao.findById(id);
	List<T_User> checkUsers = T_User.dao
		.find("select * from t_user where status=1 and id in (" + group.getStr("u_ids") + ") order by d_id");
	List<T_Department> deparmentList = T_Department.getAllDepts();
	List<T_User> userList = T_User.dao.find("select * from t_user where status=1 order by d_id");
	setAttr("deparmentList", deparmentList);
	setAttr("userList", userList);
	setAttr("group", group);
	setAttr("checkUsers", checkUsers);
	render("editgroup.jsp");
    }

    public void editgroup() {
	String[] u_ids = getParaValues("u_ids");
	String[] u_names = getParaValues("u_names");

	if (u_ids == null) {
	    toErrorJson("请选择群成员！");
	    return;
	}
	String uids = "", unames = "";
	for (int i = 0; i < u_ids.length; i++) {
	    uids += "," + u_ids[i];
	    unames += "," + u_names[i];
	}

	uids = uids.substring(1, uids.length());
	unames = unames.substring(1, unames.length());
	T_Group group = getModel(T_Group.class);
	group.set("u_ids", uids);
	group.set("u_names", unames);
	if (group.update()) {
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	} else {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void deletegroup() {// 删除群组
	Integer id = getParaToInt(0);
	T_Group group = T_Group.dao.findById(id);
	try {
	    if (group.delete()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void addip() {
	render("add.jsp");
    }

    public void add() {
	Integer uid = getParaToInt("user.userid");
	List<T_Personal> list = T_Personal.dao.find("select * from t_personal where u_id=" + uid);
	if (null != list && 0 < list.size()) {
	    toErrorJson("您提交的数据有误，该用户档案已经存在！");
	    return;
	}
	if (null == uid) {
	    toErrorJson("您提交的数据有误，请选择用户！");
	    return;
	}
	try {
	    T_Personal personal = getModel(T_Personal.class);
	    personal.set("u_id", uid);
	    if (personal.save()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void updateip() {
	Integer id = getParaToInt();
	T_Personal personal = T_Personal.dao.findFirst("select * from t_personal where id=" + id);
	if (null == personal) {
	    toErrorJson("您提交的数据有误，用户档案不存在！");
	    return;
	}
	String username = T_User.getUserNameById(personal.get("u_id"));
	setAttr("username", username);
	setAttr("personal", personal);
	render("update.jsp");
    }

    public void update() {
	int id = getParaToInt("t_Personal.id");
	T_Personal personal1 = T_Personal.dao.findById(id);
	if (null == personal1) {
	    toErrorJson("您提交的数据有误，用户档案不存在！");
	    return;
	}
	try {
	    T_Personal personal = getModel(T_Personal.class);
	    if (personal.update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void delete() {
	Integer id = getParaToInt();
	T_Personal personal = T_Personal.dao.findById(id);
	try {
	    if (personal.delete()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /**
     * 获取部门选项信息
     */
    public void getdept(HttpServletRequest request) {
	List<T_Department> alldepts = new ArrayList<T_Department>();
	List<T_Department> deparments = T_Department.dao.find("select * from t_department where status='1' and pid =0");
	if (null != deparments && deparments.size() > 0) {
	    List<String[]> deptstrs = new ArrayList<String[]>();
	    for (T_Department dept : deparments) {
		getchilddept(dept, alldepts, deptstrs);
	    }
	    request.setAttribute("depts", alldepts);
	    request.setAttribute("deptstrs", deptstrs);
	}
    }

    public void getchilddept(T_Department pdept, List<T_Department> alldepts, List<String[]> deptstrs) {
	alldepts.add(pdept);
	String[] depts = new String[2];
	StringBuffer buffer = new StringBuffer("");
	for (int i = 0; i < pdept.getInt("dlvl"); i++) {
	    buffer.append("---");
	}
	depts[0] = pdept.getInt("id") + "";
	depts[1] = buffer.substring(0) + pdept.getStr("sname");
	deptstrs.add(depts);
	List<T_Department> childdepts = T_Department.getChildDepts(pdept.getInt("id"));
	if (null != childdepts && 0 < childdepts.size()) {
	    for (T_Department dept : childdepts) {
		getchilddept(dept, alldepts, deptstrs);
	    }
	}
    }

    @SuppressWarnings("unchecked")
    public void exportTel() {
	String uname = "";
	int d_id = 0;
	String select = "select p.*, u.name as uname, u.d_id as d_id ";
	String sqlExceptSelect = "from t_personal p left join t_user u on p.u_id=u.id where 1=1 ";
	try {
	    if (null != getPara("uname") && !"".equals(getPara("uname").trim())) {
		uname = getPara("uname").trim();
		sqlExceptSelect += " and u.name like '%" + uname + "%'";
		setAttr("uname", uname);
	    }
	    if (null != getPara("d_id") && !"".equals(getPara("d_id").trim())) {
		d_id = getParaToInt("d_id");
		sqlExceptSelect += " and u.d_id=" + d_id;
		setAttr("d_id", d_id + "");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	sqlExceptSelect += " order by p.id";
	List<T_Personal> list = T_Personal.dao.find(select + sqlExceptSelect);

	HttpServletResponse response = getResponse();
	response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
	response.setContentType("application/octet-stream");
	try {
	    OutputStream os = response.getOutputStream();
	    String title = "通讯录";
	    String[] headers = new String[] { "姓名", "内部电话", "手机", "所属科室", "职务名称", "职务级别" };
	    ExportExcel<T_Personal> ex = new ExportPersonalTel();
	    ex.exportExcel(title, headers, list, os, null);
	    renderNull();
	    os.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
