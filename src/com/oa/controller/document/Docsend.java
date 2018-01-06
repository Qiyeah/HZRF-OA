package com.oa.controller.document;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Clear;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.controller.export.ExportSendCab;
import com.oa.model.common.T_Attachment;
import com.oa.model.document.T_Doc_Send;
import com.oa.model.system.log.T_Operation_Log;
import com.oa.model.system.office.T_Doc_File;
import com.oa.model.system.office.T_Doc_Version;
import com.oa.model.system.office.T_Template_File;
import com.oa.model.system.workflow.Instance;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Wfield;
import com.oa.model.system.workflow.T_Woperation;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.system.workflow.T_Workitem;
import com.oa.model.system.workflow.T_Wprocess;
import com.oa.model.system.workflow.T_Wtrans;
import com.oa.model.system.workflow.T_Wtrans_Sp;
import com.oa.model.system.workflow.Transaction;
import com.oa.model.system.workflow.WorkflowReturn;
import com.oa.model.work.T_Grant;
import com.oa.util.BusinessConstant;
import com.oa.util.ExportExcel;
import com.oa.util.Report;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Common_Detail;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;
import com.zhilian.util.ArrayUtils;
import com.zhilian.util.DateUtils;

@RouteBind(path = "Main/Docsend", viewPath = "Document/Docsend")
public class Docsend extends BaseAssociationController {
    String menuId = "Docsend";

    /** 所有列表 */
    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "wf.id ";
	String orderDirection = "desc";
	String stitle = null, sdocno = null, sdate = null, edate = null;
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, dc.title as doctitle, dc.docno as docno ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id "
		+ "left join t_department dp on wf.startdept=dp.id left join t_doc_send dc on wf.id=dc.pid "
		+ "where wf.flowform='docsend'";
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
		sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
		setAttr("stitle", stitle);
	    }
	    if (null != getPara("sdocno") && !"".equals(getPara("sdocno"))) {
		sdocno = getPara("sdocno");
		sqlExceptSelect += " and dc.docno like '%" + sdocno + "%'";
		setAttr("sdocno", sdocno);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and dc.approvedate>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and dc.approvedate<='" + edate + "'";
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
	    render("main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}

    }

    /** 待办列表 */
    public void todolist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "wf.id ";
	String orderDirection = "desc";
	String stitle = null, sdocno = null, sdate = null, edate = null;

	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	String nowDate=DateUtils.getNowDate();
	// 授权情况查询
	String grantSelect = " SELECT * FROM t_grant g where g.g_id = " + u_id;
	grantSelect += " AND (g.type = 9 or g.type = 2) AND g.usable = 0";
	grantSelect += " AND (g.s_date is null OR g.s_date <='"+nowDate+"')";
	grantSelect += " AND (g.e_date is null OR g.e_date >= '"+nowDate+"')";
	String grantSet = "";
	List<T_Grant> grants = T_Grant.dao.find(grantSelect);
	for (T_Grant frant : grants) {
	    grantSet += " OR (';'+wf.todoman+';') like '%;" + frant.getInt("u_id") + ";%'";
	}

	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, dc.title as doctitle, dc.docno as docno ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id "
		+ "left join t_department dp on wf.startdept=dp.id left join t_doc_send dc on wf.id = dc.pid "
		+ "where ((';'+todoman+';') like '%;" + loginModel.getUserId() + ";%' " + grantSet + ") "
		+ "and wf.flowform='docsend' and wf.isend='0'";
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
		sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
		setAttr("stitle", stitle);
	    }
	    if (null != getPara("sdocno") && !"".equals(getPara("sdocno"))) {
		sdocno = getPara("sdocno");
		sqlExceptSelect += " and dc.docno like '%" + sdocno + "%'";
		setAttr("sdocno", sdocno);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and dc.approvedate>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and dc.approvedate<='" + edate + "'";
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
	    render("todolist.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}

    }

    /** 办理中列表 */
    public void noendlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "wf.id ";
	String orderDirection = "desc";
	String stitle = null, sdocno = null, sdate = null, edate = null;
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, dc.title as doctitle, dc.docno as docno ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id "
		+ "left join t_department dp on wf.startdept=dp.id left join t_doc_send dc on wf.id = dc.pid "
		+ "where (';'+reader+';') like '%;" + loginModel.getUserId()
		+ ";%' and wf.flowform='docsend' and wf.isend='0'";
	try {System.out.println(select+sqlExceptSelect);
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
		sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
		setAttr("stitle", stitle);
	    }
	    if (null != getPara("sdocno") && !"".equals(getPara("sdocno"))) {
		sdocno = getPara("sdocno");
		sqlExceptSelect += " and dc.docno like '%" + sdocno + "%'";
		setAttr("sdocno", sdocno);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and dc.approvedate>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and dc.approvedate<='" + edate + "'";
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
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    /** 已办结列表 */

    public void endlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, sdocno = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
	    }
	    if (null != getPara("sdocno") && !"".equals(getPara("sdocno"))) {
		sdocno = getPara("sdocno");
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
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, dc.title as doctitle, dc.docno as docno ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id "
		+ "left join t_department dp on wf.startdept=dp.id left join t_doc_send dc on wf.id = dc.pid "
		+ "where (';'+reader+';') like '%;" + loginModel.getUserId()
		+ ";%' and wf.flowform='docsend' and wf.isend='1' and wf.isnormalend='1'";
	if (null != stitle) {
	    sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != sdocno) {
	    sqlExceptSelect += " and dc.docno like '%" + sdocno + "%'";
	    setAttr("sdocno", sdocno);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and dc.approvedate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and dc.approvedate<='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by wf.id desc";
	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	render("endlist.jsp");
    }

    /** 档案柜 */
    public void filecab() {
	setAttr("page", filecabCommon());
	// 所有部门取得
	List<T_Department> deparments = T_Department.dao
		.find("select * from t_department where status='1' and pid = 0");
	setAttr("departs", deparments);
	render("filecablist.jsp");
    }

    private Page<T_Workflow> filecabCommon() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, sdocno = null, sdate = null, edate = null;
	String orderField = "id";// 排序字段名
	String orderDirection = "desc";// asc || desc
	String stype = null;
	Integer sdepart = null;
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, dc.title as doctitle, dc.docno as docno ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id  "
		+ "left join t_doc_send dc on wf.id=dc.pid left join t_department dp on dc.d_id=dp.id "
		+ "where wf.flowform='docsend' and wf.isend='1' and wf.isnormalend='1'" + "";
	try {
	    if (StrKit.notBlank(getPara("sdepart"))) {
		sdepart = getParaToInt("sdepart");
		sqlExceptSelect += " and dc.d_id =" + sdepart;
		setAttr("sdepart", sdepart);
	    }
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
		sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
		setAttr("stitle", stitle);
	    }
	    if (null != getPara("sdocno") && !"".equals(getPara("sdocno"))) {
		sdocno = getPara("sdocno");
		sqlExceptSelect += " and dc.docno like '%" + sdocno + "%'";
		setAttr("sdocno", sdocno);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and dc.approvedate>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and dc.approvedate<='" + edate + "'";
		setAttr("edate", edate);
	    }
	    if (null != getPara("stype") && !"".equals(getPara("stype"))) {
		stype = getPara("stype");
		sqlExceptSelect += " and dc.docno like'%" + stype + "%'";
		setAttr("stype", stype);
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
	}
	sqlExceptSelect += " order by " + orderField + " " + orderDirection + "";

	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	return page;
    }

    /** 已终止列表 */
    public void nmendlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, sdocno = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
	    }
	    if (null != getPara("sdocno") && !"".equals(getPara("sdocno"))) {
		sdocno = getPara("sdocno");
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
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, dc.title as doctitle, dc.docno as docno ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id "
		+ "left join t_department dp on wf.startdept=dp.id left join t_doc_send dc on wf.id=dc.pid "
		+ "where (';'+reader+';') like '%;" + loginModel.getUserId()
		+ ";%' and wf.flowform='docsend' and wf.isend='1' and wf.isnormalend='0'";
	if (null != stitle) {
	    sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != sdocno) {
	    sqlExceptSelect += " and dc.docno like '%" + sdocno + "%'";
	    setAttr("sdocno", sdocno);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and dc.approvedate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and dc.approvedate<='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by wf.id desc";
	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	render("nmendlist.jsp");
    }

    /** 督办列表 */
    public void superlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, sdocno = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
	    }
	    if (null != getPara("sdocno") && !"".equals(getPara("sdocno"))) {
		sdocno = getPara("sdocno");
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
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, dc.title as doctitle, dc.docno as docno ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id "
		+ "left join t_department dp on wf.startdept=dp.id left join t_doc_send dc on wf.id=dc.pid "
		+ "where wf.flowform='docsend' and wf.isend='0'";
	T_Wprocess wp = T_Wprocess.dao.findFirst("select * from t_wprocess where flow=?", "docsend");
	if (null == wp.getStr("managers")
		|| ("," + wp.getStr("managers") + ",").indexOf("," + loginModel.getUserId() + ",") < 0) {
	    sqlExceptSelect += " and (';'+supervisor+';') like '%;" + loginModel.getUserId() + ";%'";
	}
	if (null != stitle) {
	    sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != sdocno) {
	    sqlExceptSelect += " and dc.docno like '%" + sdocno + "%'";
	    setAttr("sdocno", sdocno);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and dc.approvedate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and dc.approvedate<='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by wf.id desc";
	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);
	render("superlist.jsp");
    }

    /** 填写审批单 */
    @Clear
    public void apply() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int templateid=0;
	int u_id = loginModel.getUserId();
	setAttr("u_id", u_id);
	setAttr("uname", loginModel.getUserName());
	setAttr("positionid",loginModel.getPid());
	setAttr("d_id", loginModel.getDid());
	setAttr("dname", loginModel.getDepartment());
	setAttr("localarea", "hys");
	int tempid = (int) (Math.random() * 99999 + 0);
	tempid = -tempid;
	setAttr("tempid", tempid);
	String template=T_Wprocess.dao.getTemplateId("docsend");
	try {
	    templateid = Integer.parseInt(template);
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	}
	setAttr("templateid",T_Template_File.dao.findValueById("recordid",templateid));
	// 流程环节信息
	T_Wprocess wp = T_Wprocess.dao.findFirst("select * from t_wprocess where flow=?", "docsend");
	setAttr("wp", wp);
	int itemid = wp.getInt("beginstep");
	setAttr("itemid", itemid);
	T_Wactive wa = T_Wactive.dao.findFirst("select * from t_wactive where id=?", itemid);
	setAttr("wa", wa);

	Date now = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = df.format(now);
	setAttr("nowday", nowday);
	setAttr("nowyear", nowday.substring(0, 4));

	// 下一环节信息
	int todomannum = 1;
	setAttr("todomannum", todomannum);
	int nextstepsnum = T_Wactive.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1)
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	setAttr("nextitemid", nextitemid);

	List<T_Template_File> templates = T_Template_File.dao.list();
	setAttr("templates", templates);
	
	setAttr("docnoList", T_Common_Detail.getListByKey(BusinessConstant.DOCNO));
	render("apply.jsp");
    }

    /** 编辑审批单 */
    @Clear
    public void approve() {
	int pid = getParaToInt();
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	String userid = String.valueOf(u_id);
	setAttr("u_id", u_id);
	setAttr("userid", userid);
	setAttr("positionid",loginModel.getPid());
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

	String tmpuserid = ";" + u_id + ";";
	String tmptodoman = ";" + wf.getStr("todoman") + ";";

	Date now = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String nowtime = formatter.format(now);

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = df.format(now);
	setAttr("nowday", nowday);
	setAttr("nowyear", nowday.substring(0, 4));

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

	// 下一环节信息
	String mans[] = wf.getStr("todousers").split(";");
	int todomannum = mans.length;
	setAttr("todomannum", todomannum);
	/*int nextstepsnum = T_Wactive.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1)
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	setAttr("nextitemid", nextitemid);*/
	int nextstepsnum = 0;
	String nextitemid = "";
	int dept = loginModel.getDid();
	int position = loginModel.getPid();
	// 获取该流程设置的特例环节
	String spids = T_Wtrans_Sp.dao.getNextSteps(String.valueOf(itemid), dept, position);
	if (null == spids || spids.length() == 0) {
	    // 特例环节获取失败，获取正常环节
	    nextstepsnum = T_Wactive.dao.nextActives(itemid);
	    if (nextstepsnum == 1)
		nextitemid = T_Wactive.dao.getNextStep(itemid);
	} else {
	    nextstepsnum = spids.split(",").length;
	    if (nextstepsnum == 1)
		nextitemid = spids;
	}
	setAttr("nextstepsnum", nextstepsnum);
	setAttr("nextitemid", nextitemid);

	// 业务信息
	T_Doc_Send dc = T_Doc_Send.dao.findFirst("select * from t_doc_send where pid=?", pid);
	if (null != opinionfield && !"".equals(opinionfield)) {
	    dc.set(opinionfield, opinions);
	}
	setAttr("dc", dc);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	setAttr("fileList", fileList);
	int dc_u_id = dc.getInt("u_id");
	int dc_d_id = dc.getInt("d_id");
	setAttr("starter", T_User.dao.findValueById("name", dc_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", dc_d_id));

	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	/*setAttr("opinion2", T_Workitem.dao.getOpinions(pid, "opinion2"));
	setAttr("opinion3", T_Workitem.dao.getOpinions(pid, "opinion3"));
	setAttr("opinion4", T_Workitem.dao.getOpinions(pid, "opinion4"));
	setAttr("opinion5", T_Workitem.dao.getOpinions(pid, "opinion5"));*/

	List<T_Template_File> templates = T_Template_File.dao.list();
	setAttr("templates", templates);
	List<T_Workitem> list = T_Workitem.dao.find("select * from t_workitem where pid=?", pid);
	setAttr("list", list);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);
	setAttr("activeHM", T_Wactive.dao.getActiveHm());
	setAttr("operationHM", T_Woperation.dao.getOperationHm());
	setAttr("docnoList", T_Common_Detail.getListByKey(BusinessConstant.DOCNO));
	render("approve.jsp");
    }

    /** 提交审批单，流程流转 */
    @Clear
    public void save() {
	boolean flag = false;
	Instance ins = new Instance();
	String msgstr = "";
	String id = "";
	LoginModel loginModel=(LoginModel)getSession().getAttribute("loginModel");
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

	    T_Doc_Send temp = getModel(T_Doc_Send.class);
	    id = wfr.getId();
	    temp.set("pid", id);
	    String[] fjids = getParaValues("fjid");
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    temp.set("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	    /*temp.set("opinion2", T_Workitem.dao.getOpinions(pid, "opinion2"));
	    temp.set("opinion3", T_Workitem.dao.getOpinions(pid, "opinion3"));
	    temp.set("opinion4", T_Workitem.dao.getOpinions(pid, "opinion4"));
	    temp.set("opinion5", T_Workitem.dao.getOpinions(pid, "opinion5"));*/
	    // 主送
	    temp.set("send1", getPara("sendDept1.send1"));
	    // 抄送
	    temp.set("send2", getPara("sendDept2.send2"));
	    // 校稿人
	    // temp.set("proof", getPara("jbr.username"));
	    if (ins.getOperation().equals("WanCheng")) { // 审批完成
		temp.set("pstatus", "2");
	    } else if (ins.getOperation().equals("FaSong")) { // 审批中
		temp.set("pstatus", "1");
	    } else if (ins.getOperation().equals("TuiHuiNiGao")) { // 退回申请人
		temp.set("pstatus", "0");
	    } else if (ins.getOperation().equals("ZhongZhi")) { // 已终止
		temp.set("pstatus", "3");
	    }
	    if (isnewdoc.equals("1")) {
		flag = temp.remove("id").save();
		new T_Operation_Log(loginModel.getUserId(), "新增了发文：" + temp.getStr("docno"),"Docsend");//记录日记
		String tempid = getPara("tempid");
		
		T_Doc_File.dao.updates(" pid=" + temp.getStr("pid") + ",filename='" + temp.getStr("pid") + ".doc'",
			" where pid=" + tempid);
		T_Doc_Version.dao.updates(" pid=" + temp.getStr("pid") + ",filename='" + temp.getStr("pid") + ".doc'",
			" where pid=" + tempid);
	    } else {
		flag = temp.update();
	    }
	    if (wfr.getMessage().equals("wfr1")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！";
	    } else if (wfr.getMessage().equals("wfr2")) {
		new T_Operation_Log(loginModel.getUserId(), T_Woperation.dao.getNameByCode(ins.getOperation())+"了发文：" + temp.getStr("docno"),"Docsend");//记录日记
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！ <br><br>发文处理单已流转到："
			+ T_User.findUsernames(wfr.getTodoman().replace(';', ','));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    if (ins.getOperation().equals("ZanCun")) {
		toBJUIJson(200, "【暂存】操作成功！发文处理单已保存。", "docsendtodo", "approve_dialog", "", "true",
			"Main/Docsend/approve/" + id);
	    } else {
		toBJUIJson(200, msgstr, "docsendtodo", "", "", "true", "");
	    }
	}
    }

    /** 查看审批单 */

    @Clear
    public void detail() {
	int pid = getParaToInt();
	// 业务信息
	T_Doc_Send dc = T_Doc_Send.findByPid(pid);
	setAttr("dc", dc);
	if (dc.get("fjid") != null) {
	    List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	    setAttr("fileList", fileList);
	}
	int dc_u_id = dc.getInt("u_id");
	int dc_d_id = dc.getInt("d_id");
	setAttr("starter", T_User.dao.findValueById("name", dc_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", dc_d_id));
	render("detail.jsp");
    }

    @Clear
    public void toapprove() {
	String pid = getPara();
	setAttr("url", "Main/Docsend/approve1/" + pid);
	render("toprint1.jsp");
    }

    /** 编辑审批 */
    @Clear
    public void approve1() {
	int pid = getParaToInt();
	int templateid=0;
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	String userid = String.valueOf(u_id);
	String template=T_Wprocess.dao.getTemplateId("docsend");
	try {
	    templateid = Integer.parseInt(template);
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	}
	setAttr("templateid",T_Template_File.dao.findValueById("recordid",templateid));
	setAttr("u_id", u_id);
	setAttr("positionid",loginModel.getPid());
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
	/*if (loginModel.getPid() == 3) {
	    wa.set("opinionfield", "opinion3");
	} else if (loginModel.getPid() == 1) {
	    wa.set("opinionfield", "opinion4");
	}*/
	setAttr("wa", wa);
	
	String tmpuserid = ";" + u_id + ";";
	String tmptodoman = ";" + wf.getStr("todoman") + ";";
	String nowDate=DateUtils.getNowDate();
	// 授权情况查询
	Boolean isgrant = false;
	String grantSelect = " SELECT * FROM t_grant g where g.g_id = " + loginModel.getUserId();
	grantSelect += " AND (g.type = 9 or g.type = 1) AND g.usable = 0";
	grantSelect += " AND (g.s_date is null OR g.s_date <='"+nowDate+"')";
	grantSelect += " AND (g.e_date is null OR g.e_date >= '"+nowDate+"')";
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
	    if (wkit == null) {
		wkit = new T_Workitem();
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
	int nextstepsnum = T_Wtrans.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1)
	    nextitemid = T_Wtrans.dao.getNextStep(itemid);
	setAttr("nextitemid", nextitemid);
	// 当前处理人是科长的时候，下一步不再进行科室审核
	if (nextstepsnum == 1 && !"1".equals(wa.getStr("handround"))) {
	    nextitemid = T_Wtrans.dao.getNextStep(itemid);
	    T_Wactive nextwa = T_Wactive.dao.findById(nextitemid);
	    if (loginModel.getPid() == 12 || loginModel.getPid() == 8) {
		if (nextwa.getStr("user1") != null && nextwa.getStr("user1").endsWith("12")) {
		    int newnextstepsnum = T_Wtrans.dao.nextActives(Integer.parseInt(nextitemid));
		    setAttr("nextstepsnum", newnextstepsnum);
		    wf.set("itemid", nextitemid);
		    itemid = Integer.parseInt(nextitemid);
		    String newnextitemid = "";
		    if (newnextstepsnum == 1) {
			newnextitemid = T_Wtrans.dao.getNextStep(Integer.parseInt(nextitemid));
		    }
		    setAttr("nextitemid", newnextitemid);
		}
	    }
	}
	// 特例环节处理
	int dept = loginModel.getDid();
	int position = loginModel.getPid();
	/*int nextstepsnum = 0;
	String nextitemid = "";*/
	// 获取该流程设置的特例环节
	String spids = T_Wtrans_Sp.dao.getNextSteps(String.valueOf(itemid), dept, position);
	if (null == spids || spids.length() == 0) {
	    // 特例环节获取失败，获取正常环节
	    nextstepsnum = T_Wactive.dao.nextActives(itemid);
	    if (nextstepsnum == 1)
		nextitemid = T_Wactive.dao.getNextStep(itemid);
	} else {
	    nextstepsnum = spids.split(",").length;
	   // if (nextstepsnum == 1){
		nextitemid = spids;
	    //}
		
	}
	setAttr("nextstepsnum", nextstepsnum);
	setAttr("nextitemid", nextitemid);

	// 业务信息
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = df.format(now);
	setAttr("nowday", nowday);
	setAttr("nowyear", nowday.substring(0, 4));
	T_Doc_Send dc = T_Doc_Send.dao.findFirst("select * from t_doc_send where pid=?", pid);
	if (null != opinionfield && !"".equals(opinionfield)) {
	    dc.set(opinionfield, opinions);
	}
	// 添加校稿人
	if (wa.getStr("name").equals("校稿")) {
	    dc.set("proof", loginModel.getUserName());
	}
	setAttr("dc", dc);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	setAttr("fileList", fileList);
	int dc_u_id = dc.getInt("u_id");
	int dc_d_id = dc.getInt("d_id");
	setAttr("starter", T_User.dao.findValueById("name", dc_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", dc_d_id));
	setAttr("d_id", dc_d_id);

	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	/*setAttr("opinion2", T_Workitem.dao.getOpinions(pid, "opinion2"));
	setAttr("opinion3", T_Workitem.dao.getOpinions(pid, "opinion3"));
	setAttr("opinion4", T_Workitem.dao.getOpinions(pid, "opinion4"));
	setAttr("opinion5", T_Workitem.dao.getOpinions(pid, "opinion5"));*/

	List<T_Template_File> templates = T_Template_File.dao.list();
	setAttr("templates", templates);
	List<T_Workitem> list = T_Workitem.dao.find("select * from t_workitem where pid=?", pid);
	setAttr("list", list);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);
	setAttr("activeHM", T_Wactive.dao.getActiveHm());
	setAttr("operationHM", T_Woperation.dao.getOperationHm());
	setAttr("docnoList", T_Common_Detail.getListByKey(BusinessConstant.DOCNO));
	if(canedit){
	    render("approve1.jsp");
	}else{
	    render("approveview.jsp");
	}
	
    }

    /** 提交表单，流程流转 */
    @Clear
    public void save1() {

	boolean flag = false;
	Instance ins = new Instance();
	String msgstr = "";
	String id = "";
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
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
	    if(!getPara("nextitemid").contains(",")){
		T_Wactive wa = T_Wactive.dao.findById(getPara("nextitemid"));
		ins.setWActive(wa);
	    }
	    
	   
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

	    T_Doc_Send temp = getModel(T_Doc_Send.class);
	    id = wfr.getId();
	    temp.set("pid", id);
	    String[] fjids = getParaValues("fjid");
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    // 主送
	    temp.set("send1", getPara("sendDept1.send1"));
	    // 抄送
	    temp.set("send2", getPara("sendDept2.send2"));
	    // 校稿人
	    temp.set("proof", getPara("jbr.username"));
	    temp.set("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	    /*temp.set("opinion2", T_Workitem.dao.getOpinions(pid, "opinion2"));
	    temp.set("opinion3", T_Workitem.dao.getOpinions(pid, "opinion3"));
	    temp.set("opinion4", T_Workitem.dao.getOpinions(pid, "opinion4"));
	    temp.set("opinion5", T_Workitem.dao.getOpinions(pid, "opinion5"));*/
	    if (ins.getOperation().equals("WanCheng")) { // 审批完成
		temp.set("pstatus", "2");
	    } else if (ins.getOperation().equals("FaSong")) { // 审批中
		temp.set("pstatus", "1");
	    } else if (ins.getOperation().equals("TuiHuiNiGao")) { // 退回申请人
		temp.set("pstatus", "0");
	    } else if (ins.getOperation().equals("ZhongZhi")) { // 已终止
		temp.set("pstatus", "3");
	    }
	    if (isnewdoc.equals("1")) {
		flag = temp.remove("id").save();
		String tempid = getPara("tempid");
		T_Doc_File.dao.updates(" pid=" + temp.getStr("pid") + ",filename='" + temp.getStr("pid") + ".doc'",
			" where pid=" + tempid);
		T_Doc_Version.dao.updates(" pid=" + temp.getStr("pid") + ",filename='" + temp.getStr("pid") + ".doc'",
			" where pid=" + tempid);
	    } else {
		flag = temp.update();
	    }
	    if (wfr.getMessage().equals("wfr1")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！";
	    } else if (wfr.getMessage().equals("wfr2")) {
		new T_Operation_Log(loginModel.getUserId(), T_Woperation.dao.getNameByCode(ins.getOperation())+"了发文：" + temp.getStr("docno"),"Docsend");//记录日记
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！ <br><br>发文处理单已流转到："
			+ T_User.findUsernames(wfr.getTodoman().replace(';', ','));
	    }else{
		
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

    @Clear
    public void toprint() {
	String pid = getPara();
	setAttr("url", "Main/Docsend/print/" + pid);
	render("toprint.jsp");
    }

    /** 打印申请单 */
    @Clear
    public void print() {
	int pid = getParaToInt();

	String mServerUrl = "http://" + getRequest().getServerName() + ":" + getRequest().getServerPort()
		+ getRequest().getContextPath() + "/Main/HtmlSignature/ExecuteRun";
	setAttr("mServerUrl", mServerUrl);

	// 业务信息
	T_Doc_Send dc = T_Doc_Send.findByPid(pid);
	setAttr("dc", dc);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	setAttr("fileList", fileList);
	int dc_u_id = dc.getInt("u_id");
	int dc_d_id = dc.getInt("d_id");
	setAttr("starter", T_User.dao.findValueById("name", dc_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", dc_d_id));
	setAttr("d_id", dc_d_id);
	List<T_Template_File> templates = T_Template_File.dao.list();
	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("templates", templates);
	render("print.jsp");
    }

    /** 获取同一文号下的年度流水号 */
    @Clear
    public void getwh() {
	int count = 0;
	try {
	    String boferStr = getPara("boferStr");
	    String year = getPara("year");
	    count = T_Doc_Send.countWH(boferStr, year);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	renderText("" + (count + 1));
    }

    /** 填写审批单 */
    @Clear
    public void newcab() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	setAttr("u_id", u_id);
	setAttr("uname", loginModel.getUserName());
	setAttr("d_id", loginModel.getDid());
	setAttr("dname", loginModel.getDepartment());
	setAttr("localarea", "hys");
	int tempid = (int) (Math.random() * 99999 + 0);
	tempid = -tempid;
	setAttr("tempid", tempid);
	// 流程环节信息
	T_Wprocess wp = T_Wprocess.dao.findFirst("select * from t_wprocess where flow=?", "docsend");
	setAttr("wp", wp);
	int itemid = wp.getInt("beginstep");
	setAttr("itemid", itemid);
	T_Wactive wa = T_Wactive.dao.findFirst("select * from t_wactive where id=?", itemid);
	setAttr("wa", wa);

	Date now = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = df.format(now);
	setAttr("nowday", nowday);
	setAttr("nowyear", nowday.substring(0, 4));

	// 下一环节信息
	int todomannum = 1;
	setAttr("todomannum", todomannum);
	int nextstepsnum = T_Wactive.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1)
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	setAttr("nextitemid", nextitemid);

	List<T_Template_File> templates = T_Template_File.dao.list();
	setAttr("templates", templates);

	render("newCab.jsp");
    }

    /** 提交审批单，流程流转 */
    @SuppressWarnings("unused")
    @Clear
    public void saveCab() {
	boolean flag = false;
	String msgstr = "";
	try {
	    T_Workflow wf = getModel(T_Workflow.class);
	    wf.set("isend", "1");
	    wf.set("title", getPara("flowname"));
	    wf.set("isnormalend", "1");
	    wf.set("isopen", "0");
	    wf.remove("id");
	    wf.set("starttime", getPara("t_Doc_Send.approvedate"));

	    /*if (getPara("zrks.id") == null || getPara("zrks.id").length() < 1) {
		wf.set("d_id", 2);
	    }*/
	    wf.save();
	    int pid = wf.getInt("id");

	    T_Doc_Send temp = getModel(T_Doc_Send.class);
	    temp.set("pid", pid);
	    String[] fjids = getParaValues("fjid");
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));

	    // 主送
	    temp.set("send1", getPara("sendDept1.send1"));
	    // 抄送
	    temp.set("send2", getPara("sendDept2.send2"));
	    // 拟稿人
	    temp.set("u_id", getPara("jbr.userid"));
	    // 校稿人
	    temp.set("proof", getPara("jbr1.username"));

	    temp.set("d_id", getPara("zrks.id"));
	    if (getPara("zrks.id") == null || getPara("zrks.id").length() < 1) {
		temp.set("d_id", 2);
	    }
	    temp.set("pstatus", "2");
	    temp.remove("id").save();

	    msgstr = "操作成功！";
	    flag = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	}
    }

    /**
     * 发文归档导出
     */
    @SuppressWarnings("unchecked")
    public void exportCab() {
	HttpServletResponse response = getResponse();
	response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
	response.setContentType("application/octet-stream");
	try {
	    OutputStream os = response.getOutputStream();
	    String title = "发文归档";
	    String[] headers = new String[] { "序号", "拟稿时间", "拟稿科室", "文号", "文件标题" };
	    ExportExcel<T_Workflow> ex = new ExportSendCab();
	    ex.exportExcel(title, headers, filecabCommon().getList(), os, null);
	    renderNull();
	    os.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void deleteCab() {
	int id = getParaToInt();
	T_Doc_Send docSend = T_Doc_Send.findByPid(id);
	T_Workflow workflow = T_Workflow.dao.findById(id);
	if (null == docSend || null == workflow) {
	    toErrorJson("您提交的数据有误，档案不存在！");
	    return;
	}
	if (StrKit.notBlank(docSend.getStr("opinion1"))) {
	    toErrorJson("本文件不是上传的历史办文记录，禁止删除！");
	    return;
	}
	docSend.set("pstatus", "3");
	workflow.set("isnormalend", "0");
	try {
	    if (docSend.update() && workflow.update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	}
    }

    /** 修改面单 */
    @Clear
    public void approve2() {
	int pid = getParaToInt();
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	@SuppressWarnings("unused")
	String userid = String.valueOf(u_id);
	// 业务信息
	T_Doc_Send dc = T_Doc_Send.findByPid(pid);
	if (null == dc) {
	    toErrorJson("发文不存在！");
	    return;
	} else {
	    if (dc.getInt("u_id") == u_id || loginModel.getDid() == 2) {
		setAttr("dc", dc);
		List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
		setAttr("fileList", fileList);
		int dc_u_id = dc.getInt("u_id");
		int dc_d_id = dc.getInt("d_id");
		setAttr("starter", T_User.dao.findValueById("name", dc_u_id));
		setAttr("startdept", T_Department.dao.findValueById("fname", dc_d_id));

		setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
		/*setAttr("opinion2", T_Workitem.dao.getOpinions(pid, "opinion2"));
		setAttr("opinion3", T_Workitem.dao.getOpinions(pid, "opinion3"));
		setAttr("opinion4", T_Workitem.dao.getOpinions(pid, "opinion4"));
		setAttr("opinion5", T_Workitem.dao.getOpinions(pid, "opinion5"));*/

		List<T_Template_File> templates = T_Template_File.dao.list();
		setAttr("templates", templates);
		List<T_Workitem> list = T_Workitem.dao.find("select * from t_workitem where pid=?", pid);
		setAttr("list", list);
		HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
		setAttr("userHM", userHm);
		setAttr("docnoList", T_Common_Detail.getListByKey(BusinessConstant.DOCNO));
		render("approve2.jsp");
	    } else {
		toErrorJson("只有拟稿人才能修改文件的基本信息！");
	    }
	}
    }

    /** 提交审批单，流程流转 */
    @Clear
    public void save2() {
	boolean flag = false;
	try {
	    T_Doc_Send temp = getModel(T_Doc_Send.class);
	    String[] fjids = getParaValues("fjid");
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));

	    // 主送
	    temp.set("send1", getPara("sendDept1.send1"));
	    // 抄送
	    temp.set("send2", getPara("sendDept2.send2"));
	    // 校稿人
	    temp.set("proof", getPara("jbr.username"));

	    flag = temp.update();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    toBJUIJson(200, Constant.SUCCESS, menuId+",docsendnoend", "", "", "true", "");
	}
    }

    /** 删除审批单 */
    @Clear
    public void delete() {
	int id = getParaToInt();
	T_Doc_Send ds = T_Doc_Send.findByPid(id);
	if (null == ds) {
	    toErrorJson("发文不存在！");
	    return;
	} else {
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    int u_id = loginModel.getUserId();
	    if (ds.getInt("u_id") == u_id) {
		T_Workflow.dao.deleteById(id);
		T_Workitem.dao.deleteBySqlwhere("where pid=" + id);
		T_Doc_Send.dao.deleteBySqlwhere("where pid=" + id);
		toBJUIJson(200, Constant.SUCCESS, "docsendnoend", "", "", "", "");
	    } else {
		toErrorJson("只有拟稿人才能删除该文件的办理流程！");
	    }
	}
    }

    /***
     * 图标分析页面 2016-06-15 黄灵添加 演示使用未添加数据
     */
    @Clear
    public void AnalyzeMap() {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	// 获取前月的第一天
	// 获取当前日期
	Calendar cal_1 = Calendar.getInstance();
	cal_1.add(Calendar.MONTH, -1);
	// 设置为1号,当前日期既为本月第一天
	cal_1.set(Calendar.DAY_OF_MONTH, 1);
	String firstDay = format.format(cal_1.getTime());
	System.out.println("-----1------firstDay:" + firstDay);
	// 获取前月的最后一天
	Calendar cale = Calendar.getInstance();
	// 设置为1号,当前日期既为本月第一天
	cale.set(Calendar.DAY_OF_MONTH, 0);
	String lastDay = format.format(cale.getTime());
	System.out.println("-----2------lastDay:" + lastDay);

	System.out.println(getPara("type"));
	String type = getPara("type") == null ? "z_01" : getPara("type");// 初始化分析条件
	String sdate = getPara("sdate") == null ? firstDay : getPara("sdate");// 初始化开始统计日期
	String edate = getPara("sdate") == null ? lastDay : getPara("edate");// 初始化开始统计日期

	String key = type.split("_")[1];
	String mapType = type.split("_")[0];
	String title_text = "sdfsdf";
	String title_subtext = "sdfsdf";
	String legend_data = "['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']";// ['收文','发文']
	String series_name = "sdfsdf";
	String series_data = "[{value:335, name:'直接访问'},{value:310, name:'邮件营销'},{value:234, name:'联盟广告'},{value:135, name:'视频广告'},{value:1548, name:'搜索引擎'}]";

	switch (key) {
	case "01":

	    break;

	default:
	    break;
	}

	System.out.println(type.split("_")[0]);
	System.out.println(type.split("_")[1]);
	setAttr("mapType", mapType);// 图形类型
	setAttr("title_text", title_text);
	setAttr("title_subtext", title_subtext);
	setAttr("legend_data", legend_data);
	setAttr("series_name", series_name);
	setAttr("series_data", series_data);

	setAttr("type", type);
	setAttr("sdate", sdate);
	setAttr("edate", edate);
	render("AnalyzeMap.jsp");
    }

    /***
     * 图标分析页面 Ajax获取统计数据 2016-06-16 黄灵添加
     */
    @SuppressWarnings("unused")
    @Clear
    public void AnalyzeMapGetData() {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	// 获取前月的第一天
	// 获取当前日期
	Calendar cal_1 = Calendar.getInstance();
	cal_1.add(Calendar.MONTH, -1);
	// 设置为1号,当前日期既为本月第一天
	cal_1.set(Calendar.DAY_OF_MONTH, 1);
	String firstDay = format.format(cal_1.getTime());
	// 获取前月的最后一天
	Calendar cale = Calendar.getInstance();
	// 设置为1号,当前日期既为本月第一天
	cale.set(Calendar.DAY_OF_MONTH, 0);
	String lastDay = format.format(cale.getTime());

	// 获得查询条件
	String type = getPara("type") == null ? "z_01" : getPara("type");// 初始化分析条件
	String sdate = getPara("sdate") == null ? firstDay : getPara("sdate");// 初始化开始统计日期
	String edate = getPara("sdate") == null ? lastDay : getPara("edate");// 初始化开始统计日期

	HashMap<String, Object> map = new HashMap<String, Object>();

	String titleText = "sdfsdf";// 标题
	String titleSubtext = "sdfsdf";// 子标题

	switch (type) {
	case "z_01":// 收发文分析图表
	    titleText = "拟稿科室 (" + sdate + "至" + edate + ")";
	    titleSubtext = sdate + "至" + edate;
	    // 类别
	    List<String> legendData = new ArrayList<String>();
	    legendData.add("发文");

	    // 横坐标，最后由数据库中提取,这里是部门信息
	    List<String> xAxisData = new ArrayList<String>();
	    xAxisData.add("办公室");
	    xAxisData.add("干部一科");
	    xAxisData.add("干部二科");
	    xAxisData.add("干部三科");
	    xAxisData.add("干部信息科");
	    xAxisData.add("干部监督科");
	    xAxisData.add("党员电教科");
	    xAxisData.add("组织一科");
	    xAxisData.add("组织二科");
	    xAxisData.add("调研科");
	    xAxisData.add("人才工作科");
	    xAxisData.add("党联科");

	    List<Map<String, Object>> series = new ArrayList<Map<String, Object>>();// 纵坐标，如果有两个那么就是两个数据

	    for (String s : legendData) {
		Map<String, Object> seriesMap = new HashMap<String, Object>();// series的数据
		ArrayList<Integer> iSeriesData = new ArrayList<Integer>();

		// 写入每个部门的数据没有数据模拟11个随机整数
		Random ra = new Random();
		for (String d : xAxisData) {
		    String sql = " select count(wf.id) as count " + " from t_workflow wf ,t_doc_send dc,t_department d "
			    + " where wf.isend = 1 " + " and wf.isnormalend > 0 " + " and dc.pid = wf.id "
			    + " and dc.approvedate <= '" + edate + "' " + " and dc.approvedate >= '" + sdate + "' "
			    + " and dc.d_id = d.id " + " and d.sname = '" + d + "'";
		    T_Workflow rd = T_Workflow.dao.findFirst(sql);
		    int count = rd.getInt("count");
		    iSeriesData.add(count);
		}
		seriesMap.put("data", iSeriesData);
		seriesMap.put("name", s);
		series.add(seriesMap);
	    }
	    map.put("legendData", legendData);
	    map.put("xAxisData", xAxisData);
	    map.put("series", series);
	    break;
	case "z_02":// 收发文分析图表
	    titleText = "发文类型(" + sdate + "至" + edate + ")";
	    titleSubtext = sdate + "至" + edate;
	    // 获取办公室人员
	    List<T_User> offmebers = T_User.findLeaderByDepart(2);
	    // 类别
	    legendData = new ArrayList<String>();
	    legendData.add("发文");

	    // 横坐标，最后由数据库中提取,这里是部门信息
	    xAxisData = new ArrayList<String>();
	    xAxisData.add("惠人防");
	    /*xAxisData.add("河组干");
	    xAxisData.add("河组字");
	    xAxisData.add("河组函");
	    xAxisData.add("河组党");
	    xAxisData.add("河组电传");*/
	    xAxisData.add("会议纪要");

	    series = new ArrayList<Map<String, Object>>();// 纵坐标，如果有两个那么就是两个数据

	    for (String s : legendData) {
		Map<String, Object> seriesMap = new HashMap<String, Object>();// series的数据
		ArrayList<Integer> iSeriesData = new ArrayList<Integer>();

		// 写入每个部门的数据没有数据模拟11个随机整数
		Random ra = new Random();
		for (String d : xAxisData) {
		    String sql = " select count(wf.id) as count " + " from t_workflow wf ,t_doc_send dc"
			    + " where wf.isend = 1 " + " and dc.approvedate <= '" + edate + "' "
			    + " and dc.approvedate >= '" + sdate + "' " + " and wf.isnormalend > 0 "
			    + " and dc.pid = wf.id " + " and dc.docno like '%" + d + "%'";

		    T_Workflow rd = T_Workflow.dao.findFirst(sql);
		    int count = rd.getInt("count");
		    iSeriesData.add(count);
		}
		seriesMap.put("data", iSeriesData);
		seriesMap.put("name", s);
		series.add(seriesMap);
	    }
	    map.put("legendData", legendData);
	    map.put("xAxisData", xAxisData);
	    map.put("series", series);
	    break;
	case "b_01":
	    titleText = "办文类型(" + sdate + "至" + edate + ")";
	    titleSubtext = sdate + "至" + edate;
	    // 类别
	    legendData = new ArrayList<String>();
	    legendData.add("一般阅知");
	    legendData.add("普通收文");
	    legendData.add("征求意见函");

	    series = new ArrayList<Map<String, Object>>();// 纵坐标，如果有两个那么就是两个数据

	    List<Map<String, Object>> seriesData = new ArrayList<Map<String, Object>>();
	    Map<String, Object> seriesMap = new HashMap<String, Object>();// series的数据
	    for (int i = 1; i < 4; i++) {
		Map<String, Object> dataValue = new HashMap<String, Object>();
		// 写入每个部门的数据没有数据模拟11个随机整数
		String sql = " select count(wf.id) as count " + " from t_workflow wf ,t_doc_receive dc"
			+ " where wf.isend = 1 " + " and dc.receivedate <= '" + edate + "' "
			+ " and dc.receivedate >= '" + sdate + "' " + " and wf.isnormalend > 0 "
			+ " and dc.pid = wf.id " + " and dc.doflag = " + i;

		T_Workflow rd = T_Workflow.dao.findFirst(sql);
		int count = rd.getInt("count");
		dataValue.put("value", count);
		if (i == 1) {
		    dataValue.put("name", "一般阅知");
		} else if (i == 2) {
		    dataValue.put("name", "普通收文");
		} else {
		    dataValue.put("name", "征求意见函");
		}

		seriesData.add(dataValue);
	    }
	    seriesMap.put("data", seriesData);
	    seriesMap.put("name", "收文数量");
	    series.add(seriesMap);
	    map.put("legendData", legendData);
	    map.put("series", series);
	    break;
	case "l_01":// 曲线图
	    titleText = "收文按日别 (" + sdate + "至" + edate + ")";
	    titleSubtext = sdate + "至" + edate;

	    // 类别这里是部门信息
	    legendData = new ArrayList<String>();
	    legendData.add("收文");

	    series = new ArrayList<Map<String, Object>>();// 纵坐标，如果有两个那么就是两个数据

	    Date endDate = StrToDate(edate);
	    boolean flag = true;
	    ArrayList<String> dates = new ArrayList<String>();
	    Date startDate = StrToDate(sdate);
	    while (startDate.before(endDate)) {
		dates.add(format.format(startDate.getTime()));
		System.out.println(startDate.before(endDate) + "，" + startDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DAY_OF_MONTH, +1);// +1今天的时间加一天
		startDate = calendar.getTime();
	    }

	    for (String s : legendData) {
		seriesMap = new HashMap<String, Object>();// series的数据
		ArrayList<Integer> iSeriesData = new ArrayList<Integer>();

		// 写入每个部门的数据没有数据模拟11个随机整数
		Random ra = new Random();
		for (String d : dates) {
		    iSeriesData.add(ra.nextInt(30) + 1);
		}
		seriesMap.put("data", iSeriesData);
		seriesMap.put("name", s);
		series.add(seriesMap);
	    }
	    map.put("legendData", legendData);
	    map.put("xAxisData", dates);
	    map.put("series", series);
	    break;
	case "l_02":// 曲线图
	    System.out.println(sdate + "," + edate);
	    titleText = "发文月份别 (" + sdate + "至" + edate + ")";
	    titleSubtext = sdate + "至" + edate;

	    // 类别这里是部门信息
	    legendData = new ArrayList<String>();
	    legendData.add("发文");
	    // legendData.add("发文");

	    series = new ArrayList<Map<String, Object>>();// 纵坐标，如果有两个那么就是两个数据

	    dates = new ArrayList<String>();
	    try {
		dates = (ArrayList<String>) getMonthBetween(sdate, edate);
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    for (String s : legendData) {
		seriesMap = new HashMap<String, Object>();// series的数据
		ArrayList<Integer> iSeriesData = new ArrayList<Integer>();
		String sql = " select dc.approvedate as var " + " from t_workflow wf ,t_doc_send dc"
			+ " where wf.isend = 1 " + " and dc.approvedate <= '" + edate + "' "
			+ " and dc.approvedate >= '" + sdate + "' " + " and wf.isnormalend > 0 "
			+ " and dc.pid = wf.id ";

		// 写入每个部门的数据没有数据模拟11个随机整数
		List<T_Doc_Send> sendlist = T_Doc_Send.dao.find(sql);
		Map<String, Integer> maprdate = new TreeMap<String, Integer>();
		for (String lit : dates) { // 日期放入maprdate
		    maprdate.put(lit, 0);
		}
		for (T_Doc_Send tr : sendlist) {
		    for (String key : maprdate.keySet()) {
			Object rdat = tr.get("var");
			String rdate = String.valueOf(rdat);
			if (rdate.substring(0, 7).equals(key)) {
			    int i = maprdate.get(key) + 1;
			    maprdate.put(key, i);
			}
		    }
		}
		for (String d : dates) {
		    iSeriesData.add(maprdate.get(d));
		}
		seriesMap.put("data", iSeriesData);
		seriesMap.put("name", s);
		series.add(seriesMap);
	    }
	    map.put("legendData", legendData);
	    map.put("xAxisData", dates);
	    map.put("series", series);
	    break;
	default:
	    break;
	}

	map.put("titleText", titleText);
	map.put("titleSubtext", titleSubtext);

	renderJson(map);
    }

    @Clear
    public void OrderTest() {
	render("test.jsp");
    }

    /**
     * 字符串转换成日期
     * 
     * @param str
     * @return date
     */
    private static Date StrToDate(String str) {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date date = null;
	try {
	    date = format.parse(str);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return date;
    }

    /***
     * 获得两个日期之间的月份
     * 
     * @param minDate
     * @param maxDate
     * @return
     * @throws Exception
     */
    private static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {
	ArrayList<String> result = new ArrayList<String>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月

	Calendar min = Calendar.getInstance();
	Calendar max = Calendar.getInstance();

	min.setTime(sdf.parse(minDate));
	min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

	max.setTime(sdf.parse(maxDate));
	max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

	Calendar curr = min;
	while (curr.before(max)) {
	    result.add(sdf.format(curr.getTime()));
	    curr.add(Calendar.MONTH, 1);
	}

	return result;
    }

    // 打印
    @Clear
    public void exportPdf() {
	int pid = getParaToInt();
	String templateName = "惠州人防办发文呈批笺.doc";
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	Report.replaceTextReceive(pid, templateName, "惠州人防办发文呈批笺", loginModel);
	setAttr("filename", templateName.substring(0, templateName.length() - 4));
	render("viewPdf.jsp");
    }
}
