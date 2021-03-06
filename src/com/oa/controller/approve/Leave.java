package com.oa.controller.approve;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Clear;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.controller.export.ExportLeaveDetails;
import com.oa.controller.export.ExportLeaveStatistics;
import com.oa.model.approve.T_Leave_Approve;
import com.oa.model.approve.T_Leave_Stat;
import com.oa.model.approve.T_Personal;
import com.oa.model.common.T_Attachment;
import com.oa.model.system.workflow.Instance;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Wfield;
import com.oa.model.system.workflow.T_Woperation;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.system.workflow.T_Workitem;
import com.oa.model.system.workflow.T_Wprocess;
import com.oa.model.system.workflow.T_Wtrans;
import com.oa.model.system.workflow.Transaction;
import com.oa.model.system.workflow.WorkflowReturn;
import com.oa.model.work.T_Grant;
import com.oa.util.BusinessConstant;
import com.oa.util.ExportExcel;
import com.oa.util.Report;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;
import com.zhilian.util.ArrayUtils;
import com.zhilian.util.DateUtils;

@RouteBind(path = "Main/Leave", viewPath = "Approve/Leave")
public class Leave extends BaseAssociationController {

    /** 所有列表 */
    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String sname = getPara("sname");
	String sdate = getPara("sdate");
	String edate = getPara("edate");
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
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	String select = "select lv.*, u.name as uname, dp.fname as dpname ";
	String sqlExceptSelect = "from t_leave_approve lv left join t_user u on lv.u_id=u.id left join t_department dp on lv.d_id=dp.id where 1=1 ";
	if (StrKit.notBlank(sname)) {
	    sqlExceptSelect += " and u.name like '%" + sname + "%'";
	    setAttr("sname", sname);
	}
	if (StrKit.notBlank(sdate)) {
	    sqlExceptSelect += " and lv.begindate <='" + sdate + "'";
	    sqlExceptSelect += " and lv.enddate >='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (StrKit.notBlank(edate)) {
	    sqlExceptSelect += " and lv.begindate <='" + edate + "'";
	    sqlExceptSelect += " and lv.enddate >='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by lv.id desc";
	Page<T_Leave_Approve> page = T_Leave_Approve.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);

	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);

	render("list.jsp");
    }

    /** 待办列表 */
    public void todolist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "wf.id";
	String orderDirection = "desc";
	String sname = null, sdate = null, edate = null;
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	// 授权情况查询
	String nowdate = DateUtils.getNowDate();
	String grantSelect = " SELECT * FROM t_grant g where g.g_id = " + u_id;
	grantSelect += " AND (g.type = 9 or g.type = 3) AND g.usable = 0";
	grantSelect += " AND (g.s_date is null OR g.s_date <='" + nowdate + "')";
	grantSelect += " AND (g.e_date is null OR g.e_date >= '" + nowdate + "')";
	String grantSet = "";
	List<T_Grant> grants = T_Grant.dao.find(grantSelect);
	for (T_Grant frant : grants) {
	    grantSet += " OR (';'+wf.todoman+';') like '%;" + frant.getInt("u_id") + ";%'";
	}
	String select = "select wf.*, wa.name as active_name, wa.sealword, u.name as uname, dp.fname as dpname, lv.type as leavetype, lv.dayt ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id "
		+ "left join t_leave_approve lv on wf.id = lv.pid" + " where ((';'+todoman+';') like '%;"
		+ loginModel.getUserId() + ";%'" + grantSet + ") and wf.flowform like 'leave' and wf.isend='0'";
	try {
	    if (null != getPara("sname") && !"".equals(getPara("sname"))) {
		sname = getPara("sname");
		sqlExceptSelect += " and u.name like '%" + sname + "%'";
		setAttr("sname", sname);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and lv.approvedate>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and lv.approvedate<='" + edate + "'";
		setAttr("edate", edate);
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
	try {
	    Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	    setAttr("userHM", userHm);
	    render("todolist.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}

    }

    /** 办理中列表 */
    public void noendlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "wf.id";
	String orderDirection = "desc";
	String sname = null, sdate = null, edate = null;
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, lv.type as leavetype, lv.dayt ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id "
		+ "left join t_leave_approve lv on wf.id = lv.pid" + " where (';'+reader+';') like '%;"
		+ loginModel.getUserId() + ";%' and wf.flowform like 'leave' and wf.isend='0'";
	try {
	    if (null != getPara("sname") && !"".equals(getPara("sname"))) {
		sname = getPara("sname");
		sqlExceptSelect += " and u.name like '%" + sname + "%'";
		setAttr("sname", sname);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and lv.approvedate>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and lv.approvedate<='" + edate + "'";
		setAttr("edate", edate);
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
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	    setAttr("userHM", userHm);
	    render("noendlist.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	}

    }

    /** 已办结列表 */
    public void endlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "wf.id";
	String orderDirection = "desc";
	String sname = null, sdate = null, edate = null;
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, lv.type as leavetype, lv.days as leavedays ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id "
		+ "left join t_leave_approve lv on wf.id = lv.pid" + " where (';'+reader+';') like '%;"
		+ loginModel.getUserId() + ";%' and wf.flowform like 'leave' and wf.isend='1' and wf.isnormalend='1'";
	try {
	    if (null != getPara("sname") && !"".equals(getPara("sname"))) {
		sname = getPara("sname");
		sqlExceptSelect += " and u.name like '%" + sname + "%'";
		setAttr("sname", sname);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and lv.approvedate>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and lv.approvedate<='" + edate + "'";
		setAttr("edate", edate);
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
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    render("endlist.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	}
    }

    /** 请休假统计列表 */
    public void stat() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "u.name";
	String orderDirection = "desc";
	String sname = null, syear = null, sdept = null;
	String select = "select ls.*, u.name as uname, dp.fname as dpname ";
	String sqlExceptSelect = "from t_leave_stat ls left join t_user u on ls.u_id=u.id left join t_department dp on ls.d_id=dp.id where 1=1 ";
	try {
	    if (null != getPara("sname") && !"".equals(getPara("sname"))) {
		sname = getPara("sname");
		sqlExceptSelect += " and u.name like '%" + sname + "%'";
		setAttr("sname", sname);
	    }
	    if (null != getPara("sdept") && !"".equals(getPara("sdept"))) {
		sdept = getPara("sdept");
		sqlExceptSelect += " and ls.d_id=" + sdept;
		setAttr("sdept", sdept);
	    }
	    if (null != getPara("syear") && !"".equals(getPara("syear"))) {
		syear = getPara("syear");
		sqlExceptSelect += " and ls.year=" + syear;
		setAttr("syear", syear);
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
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    Page<T_Leave_Stat> page = T_Leave_Stat.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    List<T_Department> deparments = T_Department.dao.find("select * from t_department where status='1'");
	    setAttr("deparments", deparments);
	    render("stat.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	}
    }

    /**
     * 请休假统计导出
     */
    @SuppressWarnings("unchecked")
    public void exportStat() {
	String sname = null, syear = null, sdept = null;
	try {
	    if (null != getPara("sname") && !"".equals(getPara("sname"))) {
		sname = getPara("sname");
	    }
	    if (null != getPara("sdept") && !"".equals(getPara("sdept"))) {
		sdept = getPara("sdept");
	    }
	    if (null != getPara("syear") && !"".equals(getPara("syear"))) {
		syear = getPara("syear");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	String select = "select ls.*, u.name as uname, dp.fname as dpname ";
	String sqlExceptSelect = "from t_leave_stat ls left join t_user u on ls.u_id=u.id left join t_department dp on ls.d_id=dp.id where 1=1 ";
	if (null != sname) {
	    sqlExceptSelect += " and u.name like '%" + sname + "%'";
	    setAttr("sname", sname);
	}
	if (null != sdept) {
	    sqlExceptSelect += " and ls.d_id=" + sdept;
	    setAttr("sdept", sdept);
	}
	if (null != syear) {
	    sqlExceptSelect += " and ls.year=" + syear;
	    setAttr("syear", syear);
	}
	sqlExceptSelect += " order by u.name desc";

	List<T_Leave_Stat> list = T_Leave_Stat.dao.find(select + sqlExceptSelect);

	HttpServletResponse response = getResponse();
	response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
	response.setContentType("application/octet-stream");
	try {
	    OutputStream os = response.getOutputStream();
	    String title = "请休假统计";
	    String[] headers = new String[] { "年度", "姓名", "部门", "年休假", "事假", "病假", "婚丧假", "产假", "探亲假", "总计" };
	    ExportExcel<T_Leave_Stat> ex = new ExportLeaveStatistics();
	    ex.exportExcel(title, headers, list, os, null);
	    renderNull();
	    os.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /** 请休假详情列表 */
    public void details() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "u.name";
	String orderDirection = "desc";
	String sname = null, sdept = null, sdate = null, edate = null;
	try {
	    if (null != getPara("sname") && !"".equals(getPara("sname"))) {
		sname = getPara("sname");
	    }
	    if (null != getPara("sdept") && !"".equals(getPara("sdept"))) {
		sdept = getPara("sdept");
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
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
	String select = "select leaveApprove.*, u.name as uname, dp.fname as dpname ";
	String sqlExceptSelect = "from t_leave_approve leaveApprove left join t_user u on leaveApprove.u_id=u.id left join t_department dp on leaveApprove.d_id=dp.id where leaveApprove.pstatus != 3 ";
	if (null != sname) {
	    sqlExceptSelect += " and u.name like '%" + sname + "%'";
	    setAttr("sname", sname);
	}
	if (null != sdept) {
	    sqlExceptSelect += " and leaveApprove.d_id=" + sdept;
	    setAttr("sdept", sdept);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and ((leaveApprove.begindate >='" + sdate + "'";
	    sqlExceptSelect += " or leaveApprove.enddate >='" + sdate + "')";
	    setAttr("sdate", sdate);
	    if (null != edate) {
		sqlExceptSelect += " and ( leaveApprove.begindate <='" + edate + "'";
		sqlExceptSelect += " or leaveApprove.enddate <='" + edate + "')";
		setAttr("edate", edate);
	    }
	    sqlExceptSelect += ")";
	} else {
	    if (null != edate) {
		sqlExceptSelect += " and (leaveApprove.begindate <='" + edate + "'";
		sqlExceptSelect += " or leaveApprove.enddate <='" + edate + "')";
		setAttr("edate", edate);
	    }
	}
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    Page<T_Leave_Approve> page = T_Leave_Approve.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    List<T_Department> deparments = T_Department.dao.find("select * from t_department where status='1'");
	    setAttr("deparments", deparments);
	    render("details.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	}

    }

    /**
     * 请休假详情导出
     */
    @SuppressWarnings("unchecked")
    public void exportDetails() {
	String sname = null, sdept = null, sdate = null, edate = null;
	try {
	    if (null != getPara("sname") && !"".equals(getPara("sname"))) {
		sname = getPara("sname");
	    }
	    if (null != getPara("sdept") && !"".equals(getPara("sdept"))) {
		sdept = getPara("sdept");
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	String select = "select leaveApprove.*, u.name as uname, dp.fname as dpname ";
	String sqlExceptSelect = "from t_leave_approve leaveApprove left join t_user u on leaveApprove.u_id=u.id left join t_department dp on leaveApprove.d_id=dp.id where leaveApprove.pstatus= "
		+ BusinessConstant.LEAVE_STATUS_APPROVED;
	if (null != sname) {
	    sqlExceptSelect += " and u.name like '%" + sname + "%'";
	    setAttr("sname", sname);
	}
	if (null != sdept) {
	    sqlExceptSelect += " and leaveApprove.d_id=" + sdept;
	    setAttr("sdept", sdept);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and ((leaveApprove.begindate >='" + sdate + "'";
	    sqlExceptSelect += " or leaveApprove.enddate >='" + sdate + "')";
	    setAttr("sdate", sdate);
	    if (null != edate) {
		sqlExceptSelect += " and ( leaveApprove.begindate <='" + edate + "'";
		sqlExceptSelect += " or leaveApprove.enddate <='" + edate + "')";
		setAttr("edate", edate);
	    }
	    sqlExceptSelect += ")";
	} else {
	    if (null != edate) {
		sqlExceptSelect += " and (leaveApprove.begindate <='" + edate + "'";
		sqlExceptSelect += " or leaveApprove.enddate <='" + edate + "')";
		setAttr("edate", edate);
	    }
	}
	sqlExceptSelect += " order by u.name desc";

	List<T_Leave_Approve> list = T_Leave_Approve.dao.find(select + sqlExceptSelect);

	HttpServletResponse response = getResponse();
	response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
	response.setContentType("application/octet-stream");
	try {
	    OutputStream os = response.getOutputStream();
	    String title = "请休假详情";
	    String[] headers = new String[] { "姓名", "科室", "类型", "开始时间", "结束时间" };
	    ExportExcel<T_Leave_Approve> ex = new ExportLeaveDetails();
	    ex.exportExcel(title, headers, list, os, null);
	    renderNull();
	    os.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /** 填写申请 */
    public void apply() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	setAttr("u_id", u_id);
	setAttr("uname", loginModel.getUserName());
	setAttr("positionid", loginModel.getPid());
	setAttr("d_id", loginModel.getDid());
	setAttr("dname", loginModel.getDepartment());
	T_Personal personal = T_Personal.dao.findFirst("select * from t_personal where u_id=?", u_id);
	if (personal == null) {
	    setAttr("errmsg", "找不到用户档案，请先建立用户档案。");
	} else {
	    setAttr("gradename", personal.getStr("gradename"));
	    setAttr("married", personal.getStr("married"));
	    setAttr("localarea", "hys");
	}
	// 流程环节信息
	T_Wprocess wp = T_Wprocess.dao.findFirst("select * from t_wprocess where flow=?", "leave");
	setAttr("wp", wp);
	int itemid = wp.getInt("beginstep");
	setAttr("itemid", itemid);
	T_Wactive wa = T_Wactive.dao.findFirst("select * from t_wactive where id=?", itemid);
	setAttr("wa", wa);
	// 下一环节信息
	int todomannum = 1;
	setAttr("todomannum", todomannum);
	int nextstepsnum = T_Wactive.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1)
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	setAttr("nextitemid", nextitemid);
	// 请假天数
	Date now = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	String year = formatter.format(now);
	SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = formatter1.format(now);
	double nxjs = T_Leave_Approve.dao.statLeave(u_id, year, "年休假");
	int workyear = T_Personal.dao.statWorkyear(u_id);
	int enableday = 0;
	if (workyear > 0 && workyear < 10)
	    enableday = 5;
	else if (workyear >= 10 && workyear < 20)
	    enableday = 10;
	else if (workyear >= 20)
	    enableday = 15;
	double canday = enableday - nxjs;
	setAttr("year", year);
	setAttr("nowday", nowday);
	setAttr("nxjs", nxjs);

	setAttr("workyear", workyear);
	setAttr("enableday", enableday);
	setAttr("canday", canday);
	render("apply.jsp");
    }

    public void delete() {
	int id = getParaToInt();
	T_Leave_Approve ds = T_Leave_Approve.dao.findByPid(id);
	if (null == ds) {
	    toErrorJson("请假申请不存在！");
	    return;
	} else {
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    int u_id = loginModel.getUserId();
	    if (ds.getInt("u_id") == u_id) {
		T_Workflow.dao.deleteById(id);
		T_Workitem.dao.deleteBySqlwhere("where pid=" + id);
		T_Leave_Approve.dao.deleteBySqlwhere("where pid=" + id);
		toBJUIJson(200, Constant.SUCCESS, "leavenoend", "", "", "", "");
	    } else {
		toErrorJson("只有本人才能删除该请假流程！");
	    }
	}
    }

    /** 编辑审批 */
    public void approve() {
	int pid = getParaToInt();
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	String userid = String.valueOf(u_id);
	setAttr("u_id", u_id);
	setAttr("userid", userid);
	setAttr("positionid", loginModel.getPid());
	setAttr("uname", loginModel.getUserName());
	setAttr("d_id", loginModel.getDid());
	setAttr("dname", loginModel.getDepartment());
	setAttr("localarea", "hys");

	// 流程环节信息
	T_Workflow wf = T_Workflow.dao.findById(pid);
	setAttr("wf", wf);
	int itemid = wf.getInt("itemid");
	setAttr("itemid", itemid);
	T_Wactive wa = T_Wactive.dao.findById(itemid);
	setAttr("wa", wa);
	List<T_Wtrans> trans = T_Wtrans.dao.getNextSteps(wa.getInt("id"));
	setAttr("trans", trans);
	
	String tmpuserid = ";" + u_id + ";";
	String tmptodoman = ";" + wf.getStr("todoman") + ";";

	Date now = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String nowtime = formatter.format(now);

	T_Workitem wkit = new T_Workitem();
	T_Workitem wkit1 = null;
	if (wf.getStr("isopen").equals("0")) {
	    if (tmptodoman.indexOf(tmpuserid) < 0) {
		wkit1 = T_Workitem.dao.getLastWorkitem(pid, itemid);
		setAttr("wkit1", wkit1);
	    } else {
		wkit.set("pid", pid);
		wkit.set("user1", userid);
		wkit.set("user2", "");
		wkit.set("itemid1", itemid);
		wkit.set("itemid2", "");
		wkit.set("begintime", nowtime);
		wkit.set("endtime", "");
		wkit.set("operation", "");
		wkit.set("opinionfield", "");
		wkit.set("opinion", "");
		wkit.set("opiniontime", "");
		wkit.save();
		wf.set("isopen", "1");
		wf.set("editor", userid);
		wf.update();
	    }
	} else {
	    wkit = T_Workitem.dao.getWorkitem(pid, itemid, userid);
	}
	setAttr("wkit", wkit);
	String opinionfield = wa.getStr("opinionfield");
	String opinions = T_Workitem.dao.getOpinions(pid, itemid, userid, opinionfield);
	String opinionname = (String) T_Wfield.dao.findValueByWhere("description",
		"where name='" + opinionfield + "' and wid='" + wa.getInt("wid") + "'");
	setAttr("opinionname", opinionname);

	boolean canedit = false;
	String editor = wf.getStr("editor");
	if (wa.getStr("editable").equals("1") && editor.equals(userid))
	    canedit = true;
	setAttr("canedit", canedit);
	boolean candeal = false;
	if (wf.getStr("editor").equals(userid) && wf.getStr("ishold").equals("0")) {
	    candeal = true;
	} else if ((";" + wf.getStr("todoman") + ";").indexOf(";" + userid + ";") >= 0) {
	    candeal = true;
	}
	setAttr("candeal", candeal);

	// 下一环节信息
	String mans[] = wf.getStr("todousers").split(";");
	int todomannum = mans.length;
	setAttr("todomannum", todomannum);
	int nextstepsnum = T_Wactive.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1)
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	setAttr("nextitemid", nextitemid);

	// 业务信息
	String year = DateUtils.getNowYear();
	String today = DateUtils.getNowDate();

	T_Leave_Approve la = T_Leave_Approve.dao.findFirst("select * from t_leave_approve where pid=?", pid);
	if (null != opinionfield && !"".equals(opinionfield)) {
	    la.set(opinionfield, opinions);
	}
	if (la.getDouble("days") == 0) {
	    la.set("days", la.getDouble("dayt"));
	}
	if (null == la.getDate("backDate")) {
	    la.set("backDate", today);
	}
	setAttr("la", la);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(la.getStr("fjid"));
	setAttr("fileList", fileList);
	int la_u_id = la.getInt("u_id");
	int la_d_id = la.getInt("d_id");
	T_Personal personal = T_Personal.dao.findFirst("select * from t_personal where u_id=?", la_u_id);
	setAttr("starter", T_User.dao.findValueById("name", la_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", la_d_id));
	setAttr("gradename", personal.getStr("gradename"));
	setAttr("married", personal.getStr("married"));

	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	setAttr("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	setAttr("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));

	// 请假天数
	double nxjs = T_Leave_Approve.dao.statLeave(u_id, year, "年休假");
	int workyear = T_Personal.dao.statWorkyear(u_id);
	int enableday = 0;
	if (workyear > 0 && workyear < 10)
	    enableday = 5;
	else if (workyear >= 10 && workyear < 20)
	    enableday = 10;
	else if (workyear >= 20)
	    enableday = 15;
	double canday = enableday - nxjs;
	setAttr("year", year);
	setAttr("today", today);
	setAttr("nxjs", nxjs);
	setAttr("workyear", workyear);
	setAttr("enableday", enableday);
	setAttr("canday", canday);
	render("approve.jsp");
    }

    /** 提交表单，流程流转 */
    @SuppressWarnings("deprecation")
    @Clear
    public void save() {
	boolean flag = false;
	Instance ins = new Instance();
	String msgstr = "";
	String id = "";
	try {
	    T_Workflow wf = getModel(T_Workflow.class);
	    int pid = wf.getInt("id");
	    String isnewdoc = wf.getStr("isnewdoc");
	    ins.setWid(getPara("wid"));
	    ins.setCuruser(getPara("curuser"));
	    ins.setCurdept(getPara("curdept"));
	    // ins.setLocalarea(getPara("curuser"));
	    ins.setNextitemid(getPara("nextitemid"));
	    ins.setNexttodoman(getPara("nexttodoman"));
	    ins.setNextstepsnum(getPara("nextstepsnum"));
	    ins.setSendsms(getPara("sendsms"));
	    ins.setSendemail(getPara("sendemail"));
	    ins.setOperation(getPara("operation"));
	    ins.setOperationtime(getPara("operationtime"));
	    ins.setOpinionfield(getPara("opinionfield"));
	    ins.setOpinion(getPara("opinion"));
	    ins.setOpiniontime(getPara("opiniontime"));

	    T_Wactive wa = T_Wactive.dao.findById(getPara("nextitemid"));
	    ins.setWActive(wa);

	    if (ins.getOperation().equals("TuiHuiShangBu")) {
		wf.set("title", getPara("flowname") + " [回退]");
	    } else if (ins.getOperation().equals("TuiHuiNiGao")) {
		wf.set("title", getPara("flowname") + " [修改后提交]");
	    } else {
		wf.set("title", getPara("flowname"));
	    }
	    ins.setWorkflow(wf);

	    Transaction trans = new Transaction();
	    WorkflowReturn wfr = trans.Trans(ins);

	    T_Leave_Approve temp = getModel(T_Leave_Approve.class);
	    id = wfr.getId();
	    temp.set("pid", id);
	    String[] fjids = getParaValues("fjid");
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    temp.set("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	    temp.set("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	    temp.set("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	    temp.set("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));
	    if (ins.getOperation().equals("WanCheng")) { // 审批完成
		temp.set("pstatus", "2");
		String year = String.valueOf(1900 + temp.getDate("begindate").getYear());
		T_Leave_Stat.dao.statLeave(temp.getInt("u_id"), temp.getInt("d_id"), temp.getStr("type"),
			temp.getDouble("days"), year);
	    } else if (ins.getOperation().equals("FaSong")) { // 审批中
		temp.set("pstatus", "1");
	    } else if (ins.getOperation().equals("TuiHuiNiGao")) { // 退回申请人
		temp.set("pstatus", "0");
	    } else if (ins.getOperation().equals("ZhongZhi")) { // 已终止
		temp.set("pstatus", "3");
	    }
	    if (isnewdoc.equals("1")) {
		flag = temp.remove("id").save();
	    } else {
		flag = temp.update();
	    }
	    if (wfr.getMessage().equals("wfr1")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！";
	    } else if (wfr.getMessage().equals("wfr2")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！ <br><br>请休假审批单已流转到："
			+ T_User.findUsernames(wfr.getTodoman().replace(';', ','));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	if (!flag) {
	    toErrorJson(Constant.EXCEPTION);
	} else {
	    if (ins.getOperation().equals("ZanCun")) {
		toBJUIJson(200, "【暂存】操作成功！请休假审批单已保存。", "leavetodo", "approve_dialog", "", "true",
			"Main/Leave/approve/" + id);
	    } else {
		toBJUIJson(200, msgstr, "leavetodo", "", "", "true", "");
	    }
	}
    }

    /** 查看申请单 */
    /*
     * @ClearInterceptor(ClearLayer.ALL) public void detail() { int pid =
     * getParaToInt(); // 业务信息 T_Leave_Approve la =
     * T_Leave_Approve.dao.findFirst("select * from t_leave_approve where pid=?"
     * , pid); setAttr("la", la); List<T_Attachment> fileList =
     * T_Attachment.dao.listInIds(la.getStr("fjid")); setAttr("fileList",
     * fileList); int la_u_id = la.getInt("u_id"); int la_d_id =
     * la.getInt("d_id"); T_Personal personal =
     * T_Personal.dao.findFirst("select * from t_personal where u_id=?",
     * la_u_id); setAttr("starter", T_User.dao.findValueById("name", la_u_id));
     * setAttr("startdept", T_Department.dao.findValueById("fname", la_d_id));
     * setAttr("gradename", personal.getStr("gradename")); setAttr("married",
     * personal.getStr("married")); setAttr("opinion1",
     * T_Workitem.dao.getOpinions1(pid, "opinion1")); setAttr("opinion2",
     * T_Workitem.dao.getOpinions1(pid, "opinion2")); setAttr("opinion3",
     * T_Workitem.dao.getOpinions1(pid, "opinion3")); setAttr("opinion4",
     * T_Workitem.dao.getOpinions1(pid, "opinion4"));
     * 
     * render("detail.jsp"); }
     */

    /** 查询年度已请休假记录 */
    @Clear
    public void showhistory() {
	int u_id = getParaToInt(0);
	String year = getPara(1);
	List<T_Leave_Approve> list = T_Leave_Approve.dao.list(
		" where u_id=" + u_id + " and approvedate>='" + year + "-01-01' and approvedate<='" + year + "-12-31'");
	setAttr("list", list);
	render("showhistory.jsp");
    }

    /** 查询年度已请休假记录 */
    @Clear
    public void showrule() {
	render("rules.jsp");
    }

    @Clear
    public void toapprove() {
	String pid = getPara();
	setAttr("url", "Main/Leave/approve1/" + pid);
	render("toprint.jsp");
    }

    /** 编辑审批 */
    @Clear
    public void approve1() {
	int pid = getParaToInt();
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	String userid = String.valueOf(u_id);
	setAttr("u_id", u_id);
	setAttr("positionid", loginModel.getPid());
	setAttr("userid", userid);
	setAttr("uname", loginModel.getUserName());
	setAttr("d_id", loginModel.getDid());
	setAttr("dname", loginModel.getDepartment());
	setAttr("localarea", "hys");

	String mServerUrl = "http://" + getRequest().getServerName() + ":" + getRequest().getServerPort()
		+ getRequest().getContextPath() + "/Main/HtmlSignature/ExecuteRun";
	setAttr("mServerUrl", mServerUrl);

	// 流程环节信息
	T_Workflow wf = T_Workflow.dao.findById(pid);
	setAttr("wf", wf);
	int itemid = wf.getInt("itemid");
	setAttr("itemid", itemid);
	T_Wactive wa = T_Wactive.dao.findById(itemid);
	// 根据当前人的职务，强制更换意见域
	if (loginModel.getPid() == 3) {
	    wa.set("opinionfield", "opinion2");
	}
	setAttr("wa", wa);

	String tmpuserid = ";" + u_id + ";";
	String tmptodoman = ";" + wf.getStr("todoman") + ";";
	String nowdate = DateUtils.getNowDate();
	// 授权情况查询
	Boolean isgrant = false;
	String grantSelect = " SELECT * FROM t_grant g where g.g_id = " + loginModel.getUserId();
	grantSelect += " AND (g.type = 9 or g.type = 3) AND g.usable = 0";
	grantSelect += " AND (g.s_date is null OR g.s_date <='" + nowdate + "')";
	grantSelect += " AND (g.e_date is null OR g.e_date >='" + nowdate + "')";
	List<T_Grant> grants = T_Grant.dao.find(grantSelect);
	for (T_Grant grant : grants) {
	    String grantid = ";" + grant.getInt("u_id") + ";";
	    if (tmptodoman.indexOf(grantid) >= 0) {
		isgrant = true;
		break;
	    }
	}
	Date now = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String nowtime = formatter.format(now);

	T_Workitem wkit = new T_Workitem();
	T_Workitem wkit1 = null;
	if (wf.getStr("isopen").equals("0")) {
	    if (tmptodoman.indexOf(tmpuserid) < 0 && !isgrant) {
		wkit1 = T_Workitem.dao.getLastWorkitem(pid, itemid);
		setAttr("wkit1", wkit1);
	    } else {
		wkit.set("pid", pid);
		wkit.set("user1", userid);
		wkit.set("user2", "");
		wkit.set("itemid1", itemid);
		wkit.set("itemid2", "");
		wkit.set("begintime", nowtime);
		wkit.set("endtime", "");
		wkit.set("operation", "");
		wkit.set("opinionfield", "");
		wkit.set("opinion", "");
		wkit.set("opiniontime", "");
		wkit.save();
		wf.set("isopen", "1");
		wf.set("editor", userid);
		wf.update();
	    }
	} else {
	    wkit = T_Workitem.dao.getWorkitem(pid, itemid, userid);
	}
	setAttr("wkit", wkit);
	String opinionfield = wa.getStr("opinionfield");
	String opinions = T_Workitem.dao.getOpinions(pid, itemid, userid, opinionfield);
	String opinionname = (String) T_Wfield.dao.findValueByWhere("description",
		"where name='" + opinionfield + "' and wid='" + wa.getInt("wid") + "'");
	setAttr("opinionname", opinionname);

	boolean canedit = false;
	String editor = wf.getStr("editor");
	if (wa.getStr("editable").equals("1") && editor.equals(userid))
	    canedit = true;
	setAttr("canedit", canedit);
	boolean candeal = false;
	if (wf.getStr("editor").equals(userid) && wf.getStr("ishold").equals("0")) {
	    candeal = true;
	} else if ((";" + wf.getStr("todoman") + ";").indexOf(";" + userid + ";") >= 0) {
	    candeal = true;
	}
	setAttr("candeal", candeal);

	// 下一环节信息
	String mans[] = wf.getStr("todousers").split(";");
	int todomannum = mans.length;
	setAttr("todomannum", todomannum);
	int nextstepsnum = T_Wactive.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1)
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	setAttr("nextitemid", nextitemid);

	// 业务信息
	T_Leave_Approve la = T_Leave_Approve.dao.findFirst("select * from t_leave_approve where pid=?", pid);
	if (null != opinionfield && !"".equals(opinionfield)) {
	    la.set(opinionfield, opinions);
	}
	setAttr("la", la);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(la.getStr("fjid"));
	setAttr("fileList", fileList);
	int la_u_id = la.getInt("u_id");
	int la_d_id = la.getInt("d_id");
	T_Personal personal = T_Personal.dao.findFirst("select * from t_personal where u_id=?", la_u_id);
	setAttr("starter", T_User.dao.findValueById("name", la_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", la_d_id));
	setAttr("gradename", personal.getStr("gradename"));
	setAttr("married", personal.getStr("married"));

	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	setAttr("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	setAttr("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));

	// 请假天数
	SimpleDateFormat formattery = new SimpleDateFormat("yyyy");
	String year = formattery.format(now);
	SimpleDateFormat formatterd = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = formatterd.format(now);
	double nxjs = T_Leave_Approve.dao.statLeave(la_u_id, year, "年休假");
	int workyear = T_Personal.dao.statWorkyear(la_u_id);
	int enableday = 0;
	if (workyear > 0 && workyear < 10)
	    enableday = 5;
	else if (workyear >= 10 && workyear < 20)
	    enableday = 10;
	else if (workyear >= 20)
	    enableday = 15;
	double canday = enableday - nxjs;
	setAttr("year", year);
	setAttr("nowday", nowday);
	setAttr("nxjs", nxjs);
	setAttr("workyear", workyear);
	setAttr("enableday", enableday);
	setAttr("canday", canday);
	render("approve1.jsp");
    }

    /** 提交表单，流程流转 */
    @SuppressWarnings("deprecation")
    @Clear
    public void save1() {
	boolean flag = false;
	Instance ins = new Instance();
	String msgstr = "";
	String id = "";
	try {
	    T_Workflow wf = getModel(T_Workflow.class);
	    int pid = wf.getInt("id");
	    String isnewdoc = wf.getStr("isnewdoc");
	    ins.setWid(getPara("wid"));
	    ins.setCuruser(getPara("curuser"));
	    ins.setCurdept(getPara("curdept"));
	    // ins.setLocalarea(getPara("curuser"));
	    ins.setNextitemid(getPara("nextitemid"));
	    ins.setNexttodoman(getPara("nexttodoman"));
	    ins.setNextstepsnum(getPara("nextstepsnum"));
	    ins.setSendsms(getPara("sendsms"));
	    ins.setSendemail(getPara("sendemail"));
	    ins.setOperation(getPara("operation"));
	    ins.setOperationtime(getPara("operationtime"));
	    ins.setOpinionfield(getPara("opinionfield"));
	    ins.setOpinion(getPara("opinion"));
	    ins.setOpiniontime(getPara("opiniontime"));

	    T_Wactive wa = T_Wactive.dao.findById(getPara("nextitemid"));
	    ins.setWActive(wa);

	    if (ins.getOperation().equals("TuiHuiShangBu")) {
		wf.set("title", getPara("flowname") + " [回退]");
	    } else if (ins.getOperation().equals("TuiHuiNiGao")) {
		wf.set("title", getPara("flowname") + " [修改后提交]");
	    } else {
		wf.set("title", getPara("flowname"));
	    }
	    ins.setWorkflow(wf);

	    Transaction trans = new Transaction();
	    WorkflowReturn wfr = trans.Trans(ins);

	    id = getPara("t_Leave_Approve.id");
	    T_Leave_Approve temp = T_Leave_Approve.dao.findById(id);
	    id = wfr.getId();
	    temp.set("pid", id);
	    String[] fjids = getParaValues("fjid");
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    if (null != getPara("t_Leave_Approve.backdate") && !"".equals(getPara("t_Leave_Approve.backdate"))) {
		temp.set("backdate", getPara("t_Leave_Approve.backdate"));
	    }
	    if (null != getPara("t_Leave_Approve.days") && !"".equals(getPara("t_Leave_Approve.days"))) {
		temp.set("days", getParaToInt("t_Leave_Approve.days"));
	    }

	    temp.set("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	    temp.set("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	    temp.set("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	    temp.set("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));
	    if (ins.getOperation().equals("WanCheng")) { // 审批完成
		temp.set("pstatus", "2");
		String year = String.valueOf(1900 + temp.getDate("begindate").getYear());
		T_Leave_Stat.dao.statLeave(temp.getInt("u_id"), temp.getInt("d_id"), temp.getStr("type"),
			temp.getDouble("days"), year);
	    } else if (ins.getOperation().equals("FaSong")) { // 审批中
		temp.set("pstatus", "1");
	    } else if (ins.getOperation().equals("TuiHuiNiGao")) { // 退回申请人
		temp.set("pstatus", "0");
	    } else if (ins.getOperation().equals("ZhongZhi")) { // 已终止
		temp.set("pstatus", "3");
	    }
	    if (isnewdoc.equals("1")) {
		flag = temp.remove("id").save();
	    } else {
		flag = temp.update();
	    }
	    if (wfr.getMessage().equals("wfr1")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！";
	    } else if (wfr.getMessage().equals("wfr2")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！ <br><br>请休假审批单已流转到："
			+ T_User.findUsernames(wfr.getTodoman().replace(';', ','));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    setAttr("msgstr", "数据处理出现错误！请检查后重新提交！");
	    render("error.jsp");
	} else {
	    setAttr("msgstr", msgstr);
	    render("success.jsp");
	}
    }

    /** 打印申请单 */
    @Clear
    public void print() {
	int pid = getParaToInt();
	// 业务信息
	T_Leave_Approve la = T_Leave_Approve.dao.findFirst("select * from t_leave_approve where pid=?", pid);
	setAttr("la", la);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(la.getStr("fjid"));
	setAttr("fileList", fileList);
	int la_u_id = la.getInt("u_id");
	int la_d_id = la.getInt("d_id");
	T_Personal personal = T_Personal.dao.findFirst("select * from t_personal where u_id=?", la_u_id);
	setAttr("starter", T_User.dao.findValueById("name", la_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", la_d_id));
	setAttr("gradename", personal.getStr("gradename"));
	setAttr("married", personal.getStr("married"));
	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	setAttr("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	setAttr("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));
	render("print.jsp");
    }

    // 打印
    @Clear
    public void exportPdf() {
	int pid = getParaToInt();
	String templateName = "请休假审批单.doc";
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	Report.replaceTextReceive(pid, templateName, "请休假审批单", loginModel);
	setAttr("filename", templateName.substring(0, templateName.length() - 4));
	render("viewPdf.jsp");
    }
    
}
