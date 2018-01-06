package com.oa.controller.work;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Page;
import com.oa.model.common.T_Attachment;
import com.oa.model.common.T_Commessage;
import com.oa.model.system.workflow.Instance;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Wfield;
import com.oa.model.system.workflow.T_Woperation;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.system.workflow.T_Workitem;
import com.oa.model.system.workflow.T_Wprocess;
import com.oa.model.system.workflow.Transaction;
import com.oa.model.system.workflow.WorkflowReturn;
import com.oa.model.work.T_Grant;
import com.oa.model.work.T_Meeting_Approve;
import com.oa.model.work.T_Myschedule;
import com.oa.util.Report;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.controller.BaseController;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Common_Detail;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;
import com.zhilian.util.ArrayUtils;
import com.zhilian.util.DateUtils;

@RouteBind(path = "/Main/Meeting", viewPath = "Work/Meeting")
public class Meeting extends BaseController {

    /** 所有列表 */
    @Clear
    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, stype = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle").trim())) {
		stitle = getPara("stitle").trim();
	    }
	    if (null != getPara("stype") && !"".equals(getPara("stype").trim())) {
		stype = getPara("stype").trim();
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
	String select = "select mt.*, u.name as uname, dp.fname as dpname ";
	String sqlExceptSelect = "from t_meeting_approve mt left join t_user u on mt.u_id=u.id left join t_department dp on mt.d_id=dp.id where 1=1 ";
	if (null != stitle) {
	    sqlExceptSelect += " and mt.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != stype) {
	    sqlExceptSelect += " and mt.type like '%" + stype + "%'";
	    setAttr("stype", stype);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and mt.mdate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and mt.mdate<='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by mt.mdate desc";
	Page<T_Meeting_Approve> page = T_Meeting_Approve.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);
	render("list.jsp");
    }

    /** 待办列表 */
    public void todolist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, stype = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle").trim())) {
		stitle = getPara("stitle").trim();
	    }
	    if (null != getPara("stype") && !"".equals(getPara("stype").trim())) {
		stype = getPara("stype").trim();
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
	int u_id = loginModel.getUserId();
	String nowDate=DateUtils.getNowDate();
	// 授权情况查询
	String grantSelect = " SELECT * FROM t_grant g where g.g_id = " + u_id;
	grantSelect += " AND (g.type = 9 or g.type = 5) AND g.usable = 0";
	grantSelect += " AND (g.s_date is null OR g.s_date <='"+nowDate+"')";
	grantSelect += " AND (g.e_date is null OR g.e_date >= '"+nowDate+"')";
	String grantSet = "";
	List<T_Grant> grants = T_Grant.dao.find(grantSelect);
	for (T_Grant frant : grants) {
	    grantSet += " OR (';'+wf.todoman+';') like '%;" + frant.getInt("u_id") + ";%'";
	}
	String select = "select wf.*, wa.name as active_name,wa.sealword, u.name as uname, dp.fname as dpname, mt.type as meetingtype, mt.title as meetingtitle ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id "
		+ "left join t_meeting_approve mt on wf.id = mt.pid where ((';'+todoman+';') like '%;"
		+ loginModel.getUserId() + ";%' " + grantSet + ") and wf.flowform='meeting' and wf.isend='0'";
	if (null != stitle) {
	    sqlExceptSelect += " and mt.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != stype) {
	    sqlExceptSelect += " and mt.type like '%" + stype + "%'";
	    setAttr("stype", stype);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and mt.approvedate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and mt.approvedate<='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by wf.id desc";
	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);
	render("todolist.jsp");
    }

    /** 办理中列表 */
    public void noendlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, stype = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle").trim())) {
		stitle = getPara("stitle").trim();
	    }
	    if (null != getPara("stype") && !"".equals(getPara("stype").trim())) {
		stype = getPara("stype").trim();
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
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, mt.type as meetingtype, mt.title as meetingtitle ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id "
		+ "left join t_meeting_approve mt on wf.id = mt.pid where (';'+reader+';') like '%;"
		+ loginModel.getUserId() + ";%' and wf.flowform='meeting' and wf.isend='0'";
	if (null != stitle) {
	    sqlExceptSelect += " and mt.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != stype) {
	    sqlExceptSelect += " and mt.type like '%" + stype + "%'";
	    setAttr("stype", stype);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and mt.approvedate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and mt.approvedate<='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by wf.id desc";
	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);
	render("noendlist.jsp");
    }

    /** 已办结列表 */
    public void endlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, stype = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle").trim())) {
		stitle = getPara("stitle").trim();
	    }
	    if (null != getPara("stype") && !"".equals(getPara("stype").trim())) {
		stype = getPara("stype").trim();
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
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, mt.type as meetingtype, mt.title as meetingtitle ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id "
		+ "left join t_meeting_approve mt on wf.id = mt.pid where (';'+reader+';') like '%;"
		+ loginModel.getUserId() + ";%' and wf.flowform='meeting' and wf.isend='1' and wf.isnormalend='1'";
	if (null != stitle) {
	    sqlExceptSelect += " and mt.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != stype) {
	    sqlExceptSelect += " and mt.type like '%" + stype + "%'";
	    setAttr("stype", stype);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and mt.approvedate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and mt.approvedate<='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by wf.id desc";
	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	render("endlist.jsp");
    }

    /** 已终止列表 */
    public void nmendlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, stype = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle").trim())) {
		stitle = getPara("stitle").trim();
	    }
	    if (null != getPara("stype") && !"".equals(getPara("stype").trim())) {
		stype = getPara("stype").trim();
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
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, mt.type as meetingtype, mt.title as meetingtitle ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id "
		+ "left join t_meeting_approve mt on wf.id = mt.pid where (';'+reader+';') like '%;"
		+ loginModel.getUserId() + ";%' and wf.flowform='meeting' and wf.isend='1' and wf.isnormalend='0'";
	if (null != stitle) {
	    sqlExceptSelect += " and mt.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != stype) {
	    sqlExceptSelect += " and mt.type like '%" + stype + "%'";
	    setAttr("stype", stype);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and mt.approvedate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and mt.approvedate<='" + edate + "'";
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
	String stitle = null, stype = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle").trim())) {
		stitle = getPara("stitle").trim();
	    }
	    if (null != getPara("stype") && !"".equals(getPara("stype").trim())) {
		stype = getPara("stype").trim();
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
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, mt.type as meetingtype, mt.title as meetingtitle ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id "
		+ "left join t_meeting_approve mt on wf.id = mt.pid where wf.flowform='meeting' and wf.isend='0'";
	T_Wprocess wp = T_Wprocess.dao.findFirst("select * from t_wprocess where flow=?", "meeting");
	if (null == wp.getStr("managers")
		|| ("," + wp.getStr("managers") + ",").indexOf("," + loginModel.getUserId() + ",") < 0) {
	    sqlExceptSelect += " and (';'+supervisor+';') like '%;" + loginModel.getUserId() + ";%'";
	}
	if (null != stitle) {
	    sqlExceptSelect += " and mt.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != stype) {
	    sqlExceptSelect += " and mt.type like '%" + stype + "%'";
	    setAttr("stype", stype);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and mt.approvedate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and mt.approvedate<='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by wf.id desc";
	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);
	render("superlist.jsp");
    }

    /** 填写申请 */
    public void apply() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	setAttr("u_id", u_id);
	setAttr("positionid",loginModel.getPid());
	setAttr("uname", loginModel.getUserName());
	setAttr("d_id", loginModel.getDid());
	setAttr("dname", loginModel.getDepartment());
	// 流程环节信息
	T_Wprocess wp = T_Wprocess.dao.findFirst("select * from t_wprocess where flow=?", "meeting");
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

	Date now = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = formatter.format(now);
	setAttr("nowday", nowday);

	render("apply.jsp");
    }

    /** 编辑审批 */
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
	int nextstepsnum = T_Wactive.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1)
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	setAttr("nextitemid", nextitemid);

	// 业务信息
	T_Meeting_Approve mt = T_Meeting_Approve.dao.findFirst("select * from t_meeting_approve where pid=?", pid);
	if (null != opinionfield && !"".equals(opinionfield)) {
	    mt.set(opinionfield, opinions);
	}
	setAttr("mt", mt);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(mt.getStr("fjid"));
	setAttr("fileList", fileList);
	int mt_u_id = mt.getInt("u_id");
	int mt_d_id = mt.getInt("d_id");
	setAttr("starter", T_User.dao.findValueById("name", mt_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", mt_d_id));
	String chairman = "";
	if (null != mt.getStr("chairman") && !"".equals(mt.getStr("chairman")))
	    chairman = T_User.findUsernames(mt.getStr("chairman"));
	setAttr("chairman", chairman);
	String attendee = "";
	if (null != mt.getStr("attendee") && !"".equals(mt.getStr("attendee")))
	    attendee = T_User.findUsernames(mt.getStr("attendee"));
	setAttr("attendee", attendee);

	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	setAttr("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	setAttr("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));

	render("approve.jsp");
    }

    /** 提交表单，流程流转 */
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

	    T_Meeting_Approve temp = getModel(T_Meeting_Approve.class);
	    id = wfr.getId();
	    temp.set("pid", id);
	    String[] fjids = getParaValues("fjid");
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    String chairman = getPara("chairman.userid");
	    temp.set("chairman", chairman);
	    String attendee = getPara("attendee.id");
	    temp.set("attendee", attendee);
	    temp.set("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	    temp.set("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	    temp.set("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	    temp.set("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));
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
	    } else {
		flag = temp.update();
	    }
	 // 发布会议通知
	    
	    //String sendnote = getPara("sendnote");
	    //if ("1".equals(sendnote) && ins.getOperation().equals("FaSong")) {
	    if(ins.getNextitemid().equals("62") &&ins.getOperation().equals("FaSong")){
		String users = chairman + "," + attendee;
		if (users.length() > 0) {
		    String[] userlist = users.split(",");
		    if (null != userlist && userlist.length > 0) {
			for (int i = 0; i < userlist.length; i++) {
			    releseMeeting(userlist[i], temp);
			}
		    }
		}
	    }
	    if (wfr.getMessage().equals("wfr1")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！";
	    } else if (wfr.getMessage().equals("wfr2")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！ <br><br>会议审批单已流转到："
			+ T_User.findUsernames(wfr.getTodoman().replace(';', ','));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	if (!flag) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	} else {
	    if (ins.getOperation().equals("ZanCun")) {
		toBJUIJson(200, "【暂存】操作成功！会议审批单已保存。", "", "approve_dialog", "", "true", "Main/Meeting/approve/" + id);
	    } else {
		toBJUIJson(200, msgstr, "meeting_todo", "", "", "true", "");
	    }
	}
    }

    /** 查看申请单 */
    @Clear
    public void detail() {
	int pid = getParaToInt();
	// 业务信息
	T_Meeting_Approve mt = T_Meeting_Approve.dao.findFirst("select * from t_meeting_approve where pid=?", pid);
	setAttr("mt", mt);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(mt.getStr("fjid"));
	setAttr("fileList", fileList);
	int mt_u_id = mt.getInt("u_id");
	int mt_d_id = mt.getInt("d_id");
	setAttr("starter", T_User.dao.findValueById("name", mt_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", mt_d_id));
	String chairman = "";
	if (null != mt.getStr("chairman"))
	    chairman = T_User.findUsernames(mt.getStr("chairman"));
	setAttr("chairman", chairman);
	String attendee = "";
	if (null != mt.getStr("attendee"))
	    attendee = T_User.findUsernames(mt.getStr("attendee"));
	setAttr("attendee", attendee);

	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	setAttr("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	setAttr("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));

	render("detail.jsp");
    }

    @Clear
    public void toapprove() {
	String pid = getPara();
	setAttr("url", "Main/Meeting/approve1/" + pid);
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
	setAttr("userid", userid);
	setAttr("positionid",loginModel.getPid());
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

	if (wa.getStr("name").indexOf("通知") >= 0) {
	    setAttr("sendnote", "1");
	} else {
	    setAttr("sendnote", "0");
	}

	String tmpuserid = ";" + u_id + ";";
	String tmptodoman = ";" + wf.getStr("todoman") + ";";
	String nowDate=DateUtils.getNowDate();
	// 授权情况查询
	Boolean isgrant = false;
	String grantSelect = " SELECT * FROM t_grant g where g.g_id = " + loginModel.getUserId();
	grantSelect += " AND (g.type = 9 or g.type = 5) AND g.usable = 0";
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
	T_Meeting_Approve mt = T_Meeting_Approve.dao.findFirst("select * from t_meeting_approve where pid=?", pid);
	if (null != opinionfield && !"".equals(opinionfield)) {
	    mt.set(opinionfield, opinions);
	}
	setAttr("mt", mt);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(mt.getStr("fjid"));
	setAttr("fileList", fileList);
	int mt_u_id = mt.getInt("u_id");
	int mt_d_id = mt.getInt("d_id");
	setAttr("starter", T_User.dao.findValueById("name", mt_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", mt_d_id));
	String chairman = "";
	if (null != mt.getStr("chairman") && !"".equals(mt.getStr("chairman")))
	    chairman = T_User.findUsernames(mt.getStr("chairman"));
	setAttr("chairman", chairman);
	String attendee = "";
	if (null != mt.getStr("attendee") && !"".equals(mt.getStr("attendee")))
	    attendee = T_User.findUsernames(mt.getStr("attendee"));
	setAttr("attendee", attendee);

	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	setAttr("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	setAttr("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));

	render("approve1.jsp");
    }

    /** 提交表单，流程流转 */
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
	    System.out.print("ins 的值：" + ins.getOperation());
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
	    T_Meeting_Approve temp = getModel(T_Meeting_Approve.class);
	    id = wfr.getId();
	    temp.set("pid", id);
	    String[] fjids = getParaValues("fjid");
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    String chairman = getPara("chairman.userid");
	    temp.set("chairman", chairman);
	    String attendee = getPara("attendee.id");
	    temp.set("attendee", attendee);
	    temp.set("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	    temp.set("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	    temp.set("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	    temp.set("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));
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
	    } else {
		flag = temp.update();
	    }
	    // 发布会议通知
	    String sendnote = getPara("sendnote");
	    if ("1".equals(sendnote) && ins.getOperation().equals("FaSong")) {
		String users = chairman + "," + attendee;
		if (users.length() > 0) {
		    String[] userlist = users.split(",");
		    if (null != userlist && userlist.length > 0) {
			for (int i = 0; i < userlist.length; i++) {
			    releseMeeting(userlist[i], temp);
			}
		    }
		}
	    }
	    if (wfr.getMessage().equals("wfr1")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！";
	    } else if (wfr.getMessage().equals("wfr2")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！ <br><br>会议审批单已流转到："
			+ T_User.findUsernames(wfr.getTodoman().replace(';', ','));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    setAttr("msgstr", "数据处理出现错误！请检查后重新提交！");
	    render("error.jsp");
	    // toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    setAttr("msgstr", msgstr);
	    render("success.jsp");
	    // toBJUIJson(200,msgstr,"meeting_todo","","","true","");
	}
    }

    // 发布会议通知
    @Clear
    public void releseMeeting(String userid, T_Meeting_Approve meeting) {
	// 个人提醒插入会议通知
	T_Commessage message = new T_Commessage();
	message.set("u_id", userid);
	int mt_u_id = meeting.getInt("u_id");
	int mt_d_id = meeting.getInt("d_id");
	String starter = T_User.dao.findValueById("name", mt_u_id).toString();
	String startdept = T_Department.dao.findValueById("fname", mt_d_id).toString();
	String chairman = T_User.dao.findValueById("name", Integer.valueOf(meeting.getStr("chairman"))).toString();
	// 督办提醒日期取得
	String alertmessage = "";
	List<T_Common_Detail> details = T_Common_Detail.getListByKey("meetingalert");
	for (T_Common_Detail detail : details) {
	    alertmessage = detail.getStr("name");
	}
	if (alertmessage.indexOf("{科室名}") > -1) {
	    alertmessage = alertmessage.replace("{科室名}", startdept);
	}
	if (alertmessage.indexOf("{发布人名}") > -1) {
	    alertmessage = alertmessage.replace("{发布人名}", starter);
	}
	if (alertmessage.indexOf("{会议日期}") > -1) {
	    alertmessage = alertmessage.replace("{会议日期}", meeting.getDate("mdate").toString());
	}
	if (alertmessage.indexOf("{小时}") > -1) {
	    alertmessage = alertmessage.replace("{小时}", meeting.getStr("hour"));
	}
	if (alertmessage.indexOf("{分钟}") > -1) {
	    alertmessage = alertmessage.replace("{分钟}", meeting.getStr("minute"));
	}
	if (alertmessage.indexOf("{会议标题}") > -1) {
	    alertmessage = alertmessage.replace("{会议标题}", meeting.getStr("title"));
	}
	if (alertmessage.indexOf("{会议地点}") > -1) {
	    alertmessage = alertmessage.replace("{会议地点}", meeting.getStr("address"));
	}
	if (alertmessage.indexOf("{主持人}") > -1) {
	    alertmessage = alertmessage.replace("{主持人}", chairman);
	}
	// String content = startdept + " " + starter + "发布了新的会议通知，";

	// content += "该通知已经插入到你的个人日程中，请查阅。会议时间:" +
	// meeting.getDate("mdate").toString() + " " + meeting.getStr("hour") +
	// "时" + meeting.getStr("minute") + "分；会议地点：" +
	// meeting.getStr("address");
	message.set("content", alertmessage);
	
	message.set("isread", 0);
	message.remove("id").save();
	// 个人日程插入会议通知
	T_Myschedule myschedule;
	int pid = Integer.valueOf(meeting.getStr("pid"));
	int uid = Integer.valueOf(userid);
	int isnew = 0;
	myschedule = T_Myschedule.dao.getFirstSchedule(pid, uid);
	if (myschedule == null) {
	    myschedule = new T_Myschedule();
	    isnew = 1;
	}
	myschedule.set("u_id", userid);
	myschedule.set("wdate", meeting.getDate("mdate")+" "+meeting.getStr("hour")+":"+meeting.getStr("minute"));
	myschedule.set("whour", meeting.getStr("hour"));
	myschedule.set("scope", 3);//私人事件
	int ehour=Integer.parseInt(meeting.getStr("hour"))+2;
	myschedule.set("edate", meeting.getDate("mdate")+" "+ehour+":"+meeting.getStr("minute"));
	myschedule.set("wminute", meeting.getStr("minute"));
	myschedule.set("title", meeting.getStr("title"));
	myschedule.set("type", "1");
	myschedule.set("meetingid", meeting.getStr("pid"));
	if (isnew == 1) {
	    myschedule.remove("id").save();
	} else {
	    myschedule.update();
	}

    }

    @Clear
    public void toprint() {
	String pid = getPara(0);
	if (getParaToInt(1) != null) {
	    setAttr("url", "Main/Meeting/print/" + pid + "-2");
	} else {
	    setAttr("url", "Main/Meeting/print/" + pid);
	}
	render("toprint.jsp");
    }

    /** 打印申请单 */
    @Clear
    public void print() {
	int pid = getParaToInt(0);
	/*if (getParaToInt(1) != null) {
	    setAttr("attendflg", "2");
	}*/
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	setAttr("u_id", u_id);
	// 业务信息
	T_Meeting_Approve mt = T_Meeting_Approve.dao.findFirst("select * from t_meeting_approve where pid=?", pid);
	setAttr("mt", mt);

	if (mt != null) {
	    List<T_Attachment> fileList = T_Attachment.dao.listInIds(mt.getStr("fjid"));
	    setAttr("fileList", fileList);

	    int mt_u_id = mt.getInt("u_id");
	    int mt_d_id = mt.getInt("d_id");
	    setAttr("starter", T_User.dao.findValueById("name", mt_u_id));
	    setAttr("startdept", T_Department.dao.findValueById("fname", mt_d_id));

	    String chairman = "";
	    if (null != mt.getStr("chairman") && !"".equals(mt.getStr("chairman")))
		chairman = T_User.findUsernames(mt.getStr("chairman"));
	    setAttr("chairman", chairman);
	    String attendee = "";
	    if (null != mt.getStr("attendee") && !"".equals(mt.getStr("attendee"))) {
		attendee = T_User.findUsernames(mt.getStr("attendee"));
		
		  String username=(String) T_User.dao.findValueById("name",u_id); if(attendee.indexOf(username) != -1){
		  setAttr("attendflg", "2");
		  }
		 
	    }
	    setAttr("attendee", attendee);
	}
	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	setAttr("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	setAttr("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));
	render("print.jsp");
    }

    /** 会议参加回馈 */
    @Clear
    public void attend() {
	int pid = getParaToInt(0);
	int uid = getParaToInt(1);
	try {
	    T_Myschedule myschedule = T_Myschedule.dao.getFirstSchedule(pid, uid);
	    if (myschedule != null) {
		myschedule.set("isattend", "1");
		myschedule.update();
		toBJUIJson(200, "操作成功！", "", "", "", "true", "");
	    } else {
		
	    }
	} catch (Exception ex) {
	    toErrorJson("数据处理有误！");
	    ex.printStackTrace();
	}
	
	
    }

    /** 会议不参加回馈 连接 */
    @Clear
    public void tonoattend() {
	int pid = getParaToInt(0);
	int uid = getParaToInt(1);
	setAttr("pid", pid);
	setAttr("uid", uid);
	//toBJUIJson(200, "操作成功！", "", "", "Meeting", "true", "");
	render("reason.jsp");
    }

    /** 会议不参加回馈 */
    @Clear
    public void noattend() {
	int pid = getParaToInt("pid");
	int uid = getParaToInt("uid");
	String reason = "";
	try {
	    reason = getPara("reason");
	    T_Myschedule myschedule = T_Myschedule.dao.getFirstSchedule(pid, uid);
	    if (myschedule != null) {
		myschedule.set("isattend", "2");
		myschedule.set("reason", reason);
		myschedule.update();
		toBJUIJson(200, "操作成功！", "Meeting", "", "", "true", "");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	    toErrorJson("ss");
	}
    }

    /** 会议不参加回馈 连接 */
    @Clear
    public void toattendDetail() {
	int pid = getParaToInt();
	try {
	    // 确认参加人员列表
	    String noticesql = "select * from t_user u,t_myschedule s" + " where u.id = s.u_id"
		    + "   and s.meetingid = " + pid;
	    List<T_User> noticesqlusers = T_User.dao.find(noticesql);
	    if (noticesqlusers == null || noticesqlusers.size() == 0) {
		setAttr("noticesqlusers", "还没有人员收到会议通知，请确认该会议是否已经发布通知！");
		toErrorJson("还没有人员收到会议通知，请确认该会议是否已经发布通知！");
		render("attender.jsp");
	    }
	    // 确认参加人员列表
	    String attendsql = "select * from t_user u,t_myschedule s" + " where u.id = s.u_id"
		    + "   and s.meetingid = " + pid + "   and s.isattend = 1";
	    List<T_User> attendusers = T_User.dao.find(attendsql);
	    setAttr("attendusers", attendusers);
	    // 未确认人员列表
	    String unconfirmsql = "select * from t_user u,t_myschedule s" + " where u.id = s.u_id"
		    + "   and s.meetingid = " + pid + "   and s.isattend = 0";
	    List<T_User> unconfirmuers = T_User.dao.find(unconfirmsql);
	    setAttr("unconfirmuers", unconfirmuers);

	    // 未确认人员列表
	    String noattend = "select u.name,s.reason as zwxx " + "  from t_user u,t_myschedule s"
		    + " where u.id = s.u_id" + "   and s.meetingid = " + pid + "   and s.isattend = 2";
	    List<T_User> noattenduers = T_User.dao.find(noattend);
	    setAttr("noattenduers", noattenduers);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	render("attender.jsp");
    }

    public void delete() {
	int id = getParaToInt();
	T_Meeting_Approve ds = T_Meeting_Approve.dao.findByPid(id);
	if (null == ds) {
	    toErrorJson("会议不存在！");
	} else {
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    int u_id = loginModel.getUserId();
	    if (ds.getInt("u_id") == u_id) {
		T_Workflow.dao.deleteById(id);

		T_Workitem.dao.deleteBySqlwhere("where pid=" + id);
		T_Meeting_Approve.dao.deleteBySqlwhere("where pid=" + id);

		toBJUIJson(200, "会议审批流程删除完成！", "meeting_noend", "", "", "", "");
	    } else {
		toErrorJson("只有本人人才能删除该会议申请流程！");
	    }
	}
    }

    /** 编辑审批 */
    @Clear
    public void approve2() {
	int pid = getParaToInt();
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	String userid = String.valueOf(u_id);
	setAttr("u_id", u_id);
	setAttr("userid", userid);
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
	int nextstepsnum = T_Wactive.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1)
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	setAttr("nextitemid", nextitemid);

	// 业务信息
	T_Meeting_Approve mt = T_Meeting_Approve.dao.findFirst("select * from t_meeting_approve where pid=?", pid);
	if (null != opinionfield && !"".equals(opinionfield)) {
	    mt.set(opinionfield, opinions);
	}
	setAttr("mt", mt);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(mt.getStr("fjid"));
	setAttr("fileList", fileList);
	int mt_u_id = mt.getInt("u_id");
	int mt_d_id = mt.getInt("d_id");
	setAttr("starter", T_User.dao.findValueById("name", mt_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", mt_d_id));
	String chairman = "";
	if (null != mt.getStr("chairman") && !"".equals(mt.getStr("chairman")))
	    chairman = T_User.findUsernames(mt.getStr("chairman"));
	setAttr("chairman", chairman);
	String attendee = "";
	if (null != mt.getStr("attendee") && !"".equals(mt.getStr("attendee")))
	    attendee = T_User.findUsernames(mt.getStr("attendee"));
	setAttr("attendee", attendee);

	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	setAttr("opinion3", T_Workitem.dao.getOpinions1(pid, "opinion3"));
	setAttr("opinion4", T_Workitem.dao.getOpinions1(pid, "opinion4"));

	render("approve2.jsp");
    }

    // 打印
    @Clear
    public void exportPdf() {
	int pid = getParaToInt();
	String templateName = "惠州人防办会议审批单.doc";
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	Report.replaceTextReceive(pid, templateName, "惠州人防办会议审批单", loginModel);
	setAttr("filename", templateName.substring(0, templateName.length() - 4));
	render("viewPdf.jsp");
    }

}
