package com.oa.controller.work;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.export.ExportAttenceStatistics;
import com.oa.model.work.Attendance_Status;
import com.oa.model.work.T_Attence;
import com.oa.util.ExportExcel;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.controller.BaseController;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;

@RouteBind(path = "/Main/Attence", viewPath = "Work/Attence")
public class Attence extends BaseController {

    private String menuId = "personattencemg";

    @Clear
    public void main() {
	String sname = null;
	String sdate = null;
	String edate = null;
	String departmentId = null;

	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	int d_id = loginModel.getDid();
	int positionId = loginModel.getPid();
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String select = "select  a.*,u.name as name,d.fname as deptName  ";
	try {
	    if (null != getPara("sname") && !"".equals(getPara("sname"))) {
		sname = getPara("sname");
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
	    }
	    if (null != getPara("departmentId") && !"".equals(getPara("departmentId"))) {
		departmentId = getPara("departmentId");
	    }
	    if (null != getPara("numPerPage") && !"".equals(getPara("numPerPage").trim())) {
		pageSize = getParaToInt("numPerPage");
	    }
	    if (pageSize <= 0) {
		throw new Exception();
	    }
	    if (null != getPara("pageNum") && !"".equals(getPara("pageNum").trim())) {
		pageNumber = getParaToInt("pageNum");
	    }
	    if (pageNumber <= 0) {
		throw new Exception();
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	String sqlExceptSelect = " from attendance_status a ,t_user u,t_department d WHERE a.u_id=u.id AND a.d_id = d.id";
	if (positionId == Constant.POSITION_ID_MINISTER || positionId == Constant.POSITION_ID_VICE_MINISTER) {// 部长、副部长看全部记录,无操作权限
	    if (null != departmentId) {
		sqlExceptSelect = sqlExceptSelect + " AND a.d_id = " + departmentId;
	    }
	} else if (positionId == Constant.POSITION_ID_CHIEF) {// 科长看自己科的记录
	    sqlExceptSelect = sqlExceptSelect + " AND a.d_id = " + d_id;
	} else {// 副科长、科员、其他只看自己的
	    sqlExceptSelect = sqlExceptSelect + " AND a.u_id = " + u_id;
	}
	if (null != sname) {
	    sqlExceptSelect += " AND u.name like '%" + sname + "%'";
	    setAttr("sname", sname);
	}
	if (null != sdate) {
	    sqlExceptSelect += " AND a.date >='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " AND a.date <='" + edate + "'";
	    setAttr("edate", edate);
	}
	if (null != departmentId) {
	    setAttr("departmentId", departmentId);
	}
	sqlExceptSelect += " order by a.date desc";
	getdept(getRequest());
	try {
	    Page<Attendance_Status> page = Attendance_Status.dao.paginate(pageNumber, pageSize, select,
		    sqlExceptSelect);
	    setAttr("attencePage", page);
	    render("attence_main.jsp");
	} catch (Exception e) {
	    toErrorJson("服务器存在错误，数据读取失败！");
	    e.printStackTrace();
	}
    }

    public void addip() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	setAttr("u_id", loginModel.getUserId());
	Date now = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = formatter.format(now);
	setAttr("nowday", nowday);
	render("add.jsp");
    }

    public void add() {
	try {
	    T_Attence temp = getModel(T_Attence.class);
	    if (temp.save()) {
		toBJUIJson(200, "记录添加完成！", menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	    e.printStackTrace();
	}
    }

    public void updateip() {
	Integer id = getParaToInt();
	Attendance_Status temp = Attendance_Status.getById(id);
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();

	if (null == temp) {
	    toErrorJson("您提交的数据有误，记录不存在！");
	    return;
	}

	if (temp.getInt("u_id") != u_id) {
	    toErrorJson("非本人的考勤记录，不可操作！");
	    return;
	}
	if (null != temp.get("status") && temp.getStr("status").equals("2")) {
	    toErrorJson("已确认的考勤记录，不可操作！");
	    return;
	}
	if (!((null != temp.get("am_on_status") && Integer.valueOf(temp.getStr("am_on_status")) > 2
		&& Integer.valueOf(temp.getStr("am_on_status")) < 6)
		|| (null != temp.get("am_off_status") && Integer.valueOf(temp.getStr("am_off_status")) > 2
			&& Integer.valueOf(temp.getStr("am_off_status")) < 6)
		|| (null != temp.get("pm_on_status") && Integer.valueOf(temp.getStr("pm_on_status")) > 2
			&& Integer.valueOf(temp.getStr("pm_on_status")) < 6)
		|| (null != temp.get("pm_off_status") && Integer.valueOf(temp.getStr("pm_off_status")) > 2
			&& Integer.valueOf(temp.getStr("pm_off_status")) < 6))) {
	    toErrorJson("考勤结果正常，不可操作！");
	    return;
	}
	setAttr("attendance_Status", temp);
	render("update.jsp");
    }

    public void update() {
	try {
	    Attendance_Status temp = getModel(Attendance_Status.class);
	    temp.set("status", 1);
	    if (temp.update()) {
		toBJUIJson(200, "记录修改完成！", menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	    e.printStackTrace();
	}
    }

    public void delete() {
	Integer id = getParaToInt();
	T_Attence temp = T_Attence.dao.findById(id);
	try {
	    if (temp.delete()) {
		toBJUIJson(200, "记录删除完成！", menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	    e.printStackTrace();
	}
    }

    public void view() {
	Integer id = getParaToInt();
	T_Attence temp = T_Attence.dao.findById(id);
	if (null == temp) {
	    toErrorJson("您提交的数据有误，记录不存在！");
	    return;
	}
	setAttr("note", temp);
	render("view.jsp");
    }

    /**
     * 进入审批列表页面，科长才进这个页面
     */
    @Clear
    public void approveMain() {
	String sname = null;
	String sdate = null;
	String edate = null;

	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int d_id = loginModel.getDid();
	int positionId = loginModel.getPid();
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String select = "select  a.*,u.name as name,d.fname as deptName  ";
	try {
	    if (null != getPara("sname") && !"".equals(getPara("sname"))) {
		sname = getPara("sname");
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
	    }
	    if (null != getPara("numPerPage") && !"".equals(getPara("numPerPage").trim())) {
		pageSize = getParaToInt("numPerPage");
	    }
	    if (pageSize <= 0) {
		throw new Exception();
	    }
	    if (null != getPara("pageNum") && !"".equals(getPara("pageNum").trim())) {
		pageNumber = getParaToInt("pageNum");
	    }
	    if (pageNumber <= 0) {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	String sqlExceptSelect = " from attendance_status a ,t_user u,t_department d WHERE a.u_id=u.id  AND a.status ="
		+ 1 + " AND a.d_id = " + d_id;

	if (null != sname) {
	    sqlExceptSelect += " and u.name like '%" + sname + "%'";
	    setAttr("sname", sname);
	}
	sqlExceptSelect = sqlExceptSelect + " AND a.d_id = d.id";
	if (positionId != Constant.POSITION_ID_CHIEF) { // 不是科长就看不到数据
	    /* sqlExceptSelect = sqlExceptSelect + " AND false"; */
	    sqlExceptSelect = " from attendance_status where 1=0 ";
	}
	if (null != sdate) {
	    sqlExceptSelect += " and a.date >='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and a.date <='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by a.date desc";
	try {
	    Page<Attendance_Status> page = Attendance_Status.dao.paginate(pageNumber, pageSize, select,
		    sqlExceptSelect);
	    setAttr("attencePage", page);
	    render("approve_main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("服务器存在错误，数据读取失败！");
	}
    }

    /**
     * 进入审批页面
     */
    @Clear
    public void approveip() {
	Integer id = getParaToInt();
	Attendance_Status temp = Attendance_Status.getById(id);
	if (null == temp) {
	    toErrorJson("您提交的数据有误，记录不存在！");
	    return;
	}
	if (null != temp.get("approver")) {
	    T_User approver = T_User.dao.findById(temp.get("approver"));
	    if (null != approver) {
		setAttr("approverName", approver.get("name"));
	    }
	}
	setAttr("attendance_Status", temp);
	render("approve.jsp");
    }

    /**
     * 审批
     */
    public void approve() {
	try {
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    int u_id = loginModel.getUserId();

	    Attendance_Status temp = getModel(Attendance_Status.class);
	    if (temp.getStr("status").equals("2")) {
		Attendance_Status oAttendance = Attendance_Status.dao.findById(temp.getInt("id"));
		if (oAttendance.getStr("am_on_need_explain").equals("1")) {
		    temp.set("am_on_status", 6);
		}
		if (oAttendance.getStr("am_off_need_explain").equals("1")) {
		    temp.set("am_off_status", 6);
		}
		if (oAttendance.getStr("pm_on_need_explain").equals("1")) {
		    temp.set("pm_on_status", 6);
		}
		if (oAttendance.getStr("pm_off_need_explain").equals("1")) {
		    temp.set("pm_off_status", 6);
		}
	    }

	    temp.set("approver", u_id);
	    temp.set("approve_time", new Date());
	    if (temp.update()) {
		toBJUIJson(200, "审批完成！", menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	    e.printStackTrace();
	}
    }

    /**
     * 获取部门选项信息
     */
    public void getdept(HttpServletRequest request) {
	List<T_Department> alldepts = new ArrayList<T_Department>();
	List<T_Department> deparments = T_Department.dao
		.find("select * from t_department where status='0' and pid is null");
	if (null != deparments && deparments.size() > 0) {
	    List<String[]> deptstrs = new ArrayList<String[]>();
	    for (T_Department dept : deparments) {
		T_Department.getchilddept(dept, alldepts, deptstrs);
	    }
	    request.setAttribute("depts", alldepts);
	    request.setAttribute("deptstrs", deptstrs);
	}
    }

    @Clear
    public void attenceStatistics() {
	String sdate = null;
	String edate = null;
	String name = null;
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	@SuppressWarnings("unused")
	int u_id = loginModel.getUserId();
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String select = "select  u.name as name  "; //,d.fname as deptName
	try {
	    if (null != getPara("sname") && !"".equals(getPara("sname").trim())) {
		name = getPara("sname").trim();
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
	    }
	    if (null != getPara("numPerPage") && !"".equals(getPara("numPerPage").trim())) {
		pageSize = getParaToInt("numPerPage");
	    }
	    if (pageSize <= 0) {
		throw new Exception();
	    }
	    if (null != getPara("pageNum") && !"".equals(getPara("pageNum").trim())) {
		pageNumber = getParaToInt("pageNum");
	    }
	    if (pageNumber <= 0) {
		throw new Exception();
	    }
	} catch (Exception e) {  
	    
	     
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	String sqlExceptSelect = " from attendance_status a  ";

	sqlExceptSelect += " left join t_user u on a.u_id=u.id "
		+ "left join t_department d on a.d_id = d.id where 1=1 ";
	if (null != name) {
	    sqlExceptSelect += " and u.name like '%" + name + "%'";
	    setAttr("sname", name);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and a.date >='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and a.date <='" + edate + "'";
	    setAttr("edate", edate);
	}

	sqlExceptSelect = sqlExceptSelect + "group by u.name";
	List<Attendance_Status> attenceStatisticsList = Attendance_Status.dao.attenceStatistics(select,
		sqlExceptSelect);

	try {
	    setAttr("attenceStatisticsList", attenceStatisticsList);
	    render("attence_statistics.jsp");
	} catch (Exception e) {
	    toErrorJson("服务器存在错误，数据读取失败！");
	}

    }

    /* 出勤情况统计 */
    @SuppressWarnings("unchecked")
    public void exportAttenceStatistics() {
	String sdate = null;
	String edate = null;
	String name = null;
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	@SuppressWarnings("unused")
	int u_id = loginModel.getUserId();
	/*
	 * int pageSize = Constant.PAGE_SIZE; int pageNumber = 1;
	 */
	String select = "select  u.name as name,d.fname as deptName  ";
	try {
	    if (null != getPara("sname") && !"".equals(getPara("sname").trim())) {
		name = getPara("sname").trim();
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
	    }
	    /*
	     * if (null != getPara("numPerPage") &&
	     * !"".equals(getPara("numPerPage").trim())) { pageSize =
	     * getParaToInt("numPerPage"); } if (pageSize <= 0) { throw new
	     * Exception(); } if (null != getPara("pageNum") &&
	     * !"".equals(getPara("pageNum").trim())) { pageNumber =
	     * getParaToInt("pageNum"); } if (pageNumber <= 0) { throw new
	     * Exception(); }
	     */
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	String sqlExceptSelect = " from attendance_status a  ";

	sqlExceptSelect += " left join t_user u on a.u_id=u.id "
		+ "left join t_department d on a.d_id = d.id where 1=1 ";
	if (null != name) {
	    sqlExceptSelect += " and u.name like '%" + name + "%'";
	    setAttr("sname", name);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and a.date >='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and a.date <='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect = sqlExceptSelect + "group by u_id";
	List<Attendance_Status> list = Attendance_Status.dao.attenceStatistics(select, sqlExceptSelect);
	HttpServletResponse response = getResponse();

	response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
	response.setContentType("application/octet-stream");
	try {
	    OutputStream os = response.getOutputStream();
	    String title = "考勤记录统计";
	    String[] headers = new String[] { "名称", "科室", "迟到(次)", "早退(次)", "缺勤(次)" };
	    ExportExcel<Attendance_Status> ex = new ExportAttenceStatistics();
	    ex.exportExcel(title, headers, list, os, null);
	    renderNull();
	    os.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
