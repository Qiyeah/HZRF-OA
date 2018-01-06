package com.oa.controller.document;

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
import com.oa.controller.export.ExportReceiveCab;
import com.oa.model.approve.T_Personal;
import com.oa.model.common.T_Attachment;
import com.oa.model.common.T_Commessage;
import com.oa.model.common.T_Superdo;
import com.oa.model.document.T_Doc_Achieve;
import com.oa.model.document.T_Doc_Receive;
import com.oa.model.document.T_Received;
import com.oa.model.system.log.T_Operation_Log;
import com.oa.model.system.log.T_Sms_Log;
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
import com.oa.util.ExportExcel;
import com.oa.util.Report;
import com.oa.util.SMSUtil;
import com.oa.util.Util;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.controller.BaseController;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;
import com.zhilian.util.ArrayUtils;
import com.zhilian.util.DateUtils;

@RouteBind(path = "Main/Docreceive", viewPath = "Document/Docreceive")
public class Docreceive extends BaseController {

    /* 所有办文 */
    @Clear
    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
	    }
	    if (null != getPara("sunit") && !"".equals(getPara("sunit"))) {
		sunit = getPara("sunit");
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
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname,dc.security,dc.degree, dc.unit as unit, dc.title as doctitle, dc.docno as docno,dc.receive1 ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id "
		+ "left join t_doc_receive dc on wf.id = dc.pid where wf.flowform like 'docreceive' and wf.isend='0'";
	if (null != stitle) {
	    sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != sunit) {
	    sqlExceptSelect += " and dc.unit like '%" + sunit + "%'";
	    setAttr("sunit", sunit);
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
	setAttr("deptHm", T_Department.dao.hashMapByIds("sname"));
	render("list.jsp");
    }

    /* 初审分办 */
    public void apply() {
	int id = getParaToInt();
	// 业务信息
	T_Doc_Achieve dc = T_Doc_Achieve.dao.findById(id);
	setAttr("dc", dc);
	setAttr("receiver", T_User.findUsernames(String.valueOf(dc.getInt("u_id"))));
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	setAttr("fileList", fileList);
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	setAttr("u_id", u_id);
	setAttr("positionid", loginModel.getPid());
	setAttr("uname", loginModel.getUserName());
	setAttr("d_id", loginModel.getDid());
	setAttr("dname", loginModel.getDepartment());
	setAttr("localarea", "hys");
	// 流程环节信息
	T_Wprocess wp = T_Wprocess.dao.findFirst("select * from t_wprocess where flow=?", "docreceive");
	setAttr("wp", wp);
	int itemid = wp.getInt("beginstep");
	setAttr("itemid", itemid);
	T_Wactive wa = T_Wactive.dao.findFirst("select * from t_wactive where id=?", itemid);
	setAttr("wa", wa);

	Date now = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = df.format(now);
	setAttr("nowday", nowday);

	// 下一环节信息
	int todomannum = 1;
	setAttr("todomannum", todomannum);
	int nextstepsnum = T_Wactive.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1)
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	setAttr("nextitemid", nextitemid);
	// 当前处理人是科长的时候，下一步不再进行科室审核
	if (nextstepsnum == 1) {
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	    T_Wactive nextwa = T_Wactive.dao.findById(nextitemid);
	    if (loginModel.getPid() == 12 && loginModel.getDid().equals(dc.getInt("d_id"))) {
		if (nextwa.getStr("user1") != null && nextwa.getStr("user1").endsWith("12")) {
		    int newnextstepsnum = T_Wactive.dao.nextActives(Integer.parseInt(nextitemid));
		    setAttr("nextstepsnum", newnextstepsnum);
		    String newnextitemid = "";
		    if (newnextstepsnum == 1) {
			newnextitemid = T_Wactive.dao.getNextStep(Integer.parseInt(nextitemid));
		    }
		    setAttr("nextitemid", newnextitemid);
		}
	    }
	}
	int dept = loginModel.getDid();
	int position = loginModel.getPid();
	/*
	 * int nextstepsnum = 0; String nextitemid = "";
	 */
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
	    // }

	}
	setAttr("nextstepsnum", nextstepsnum);
	setAttr("nextitemid", nextitemid);
	setAttr("applyFlg", "1");
	render("apply.jsp");
    }

    /** 提交审批单，流程流转 */
    @Clear
    public void save() {
	boolean flag = false;
	Instance ins = new Instance();
	String msgstr = "";
	String id = "";
	String achieveid = getPara("t_Doc_Receive.achieveid");
	String applyFlg = getPara("applyFlg");
	T_Doc_Achieve dc = null;
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	if (achieveid != null && "1".equals(applyFlg)) {
	    dc = T_Doc_Achieve.dao.findById(achieveid);
	    if (!dc.getStr("status").equals("0")) {
		// 记录日记
		new T_Operation_Log(loginModel.getUserId(), "分办了收文：" + dc.getStr("docno"), "Docreceive");
		toBJUIJson(Constant.STATUS_CODE_OK, dc.getStr("receive2") + "分办完成，本页面自动关闭", "achieve", "", "", "true",
			"");
		return;
	    }
	}

	try {
	    String nextitemid = getPara("nextitemid");
	    T_Workflow wf = getModel(T_Workflow.class);
	    String isnewdoc = wf.getStr("isnewdoc");
	    int pid = wf.getInt("id");
	    int did = 0;
	    T_Doc_Receive dr = T_Doc_Receive.dao.findByAchieveId(Integer.parseInt(achieveid));
	    if (null != dr && null != dr.getInt("pid")) {
		did = dr.getInt("id");
		pid = dr.getInt("pid");
		wf.set("id", pid);
		wf.set("isnewdoc", "0");
		wf.set("starttime", DateUtils.getNowTime());
		isnewdoc = "0";
	    }
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

	    T_Doc_Receive temp = getModel(T_Doc_Receive.class);
	    String degree = temp.getStr("degree");
	    id = wfr.getId();
	    temp.set("pid", id);
	    String[] fjids = getParaValues("fjid");
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    // 审核人（分办人）
	    temp.set("auditor", getPara("zhk.auditor"));

	    // 将签名更换为图片签名
	    temp.set("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	    temp.set("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	    if (ins.getOperation().equals("WanCheng")) { // 审批完成
		temp.set("pstatus", "2");
	    } else if (ins.getOperation().equals("FaSong")) { // 审批中
		temp.set("pstatus", "1");
		/*
		 * T_Workitem item=new T_Workitem(); item.set("pid", pid);
		 * item.set("opinionfield", getPara("opinionfield"));
		 * item.set("opinion", getPara("opinion1"));
		 * item.set("opiniontime", DateUtils.getDateTime());
		 * item.save();
		 */
	    } else if (ins.getOperation().equals("TuiHuiNiGao")) { // 退回申请人
		temp.set("pstatus", "0");
	    } else if (ins.getOperation().equals("ZhongZhi")) { // 已终止
		temp.set("pstatus", "3");
	    }
	    if (isnewdoc.equals("1")) {
		flag = temp.remove("id").save();
	    } else {
		temp.set("id", did);
		flag = temp.update();
	    }
	    if (null != achieveid) {
		// 业务信息
		dc = T_Doc_Achieve.dao.findById(achieveid);
		dc.set("status", "1");
		dc.set("receive1", 2);
		dc.set("receive2", getPara("curuser1"));
		dc.update();
	    }
	    if (wfr.getMessage().equals("wfr1")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！";
	    } else if (wfr.getMessage().equals("wfr2")) {
		String todoman = wfr.getTodoman().replace(';', ',');
		String users = T_User.findUsernames(todoman);
		// 记录日记
		new T_Operation_Log(loginModel.getUserId(),
			T_Woperation.dao.getNameByCode(ins.getOperation()) + "了收文：" + dc.getStr("docno"), "Docreceive");
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】操作成功！ <br><br>收文处理单已流转到：" + users;
		// 发送短信
		if (T_Wactive.dao.canSendSms(nextitemid)) {
		    String mobile = T_Personal.dao.getMobileByUsers(todoman);
		    String message = "";
		    if (degree.equals("2")) {
			message = "您有一个特急件，须尽快登录OA系统处理！";
		    } else if (degree.equals("1")) {
			message = "您有一个平急件，请及时登录OA系统处理！";
		    } else {
			message = "您有一个新文件需要处理。";
		    }
		    String result = SMSUtil.sendsms(mobile, message);
		    new T_Sms_Log(users, mobile, message, result, pid);
		}
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    // if (ins.getOperation().equals("ZanCun")) {
	    // toBJUIJson(Constant.STATUS_CODE_OK, "【暂存】操作成功！收文处理单已保存。",
	    // "acheive", "approve_dialog", "", "true",
	    // "Main/Docreceive/approve/" + id);
	    // } else {
	    if (null != achieveid) {
		toBJUIJson(200, msgstr, "achieve", "", "", "true", "");
	    } else {
		toBJUIJson(200, msgstr, "achieve", "", "", "true", "");
	    }
	    // }
	}
    }

    @Clear
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

	String tmpuserid = ";" + u_id + ";";
	String tmptodoman = ";" + wf.getStr("todoman") + ";";

	// 授权情况查询
	Boolean isgrant = false;
	String nowdate = DateUtils.getNowDate();
	String grantSelect = " SELECT * FROM t_grant g where g.g_id = " + u_id;
	grantSelect += " AND (g.type = 9 or g.type = 1) AND g.usable = 0";
	grantSelect += " AND (g.s_date is null OR g.s_date <='" + nowdate + "')";
	grantSelect += " AND (g.e_date is null OR g.e_date >= '" + nowdate + "')";
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
	// 下一环节信息
	String mans[] = wf.getStr("todousers").split(";");
	int todomannum = mans.length;
	setAttr("todomannum", todomannum);
	int nextstepsnum = T_Wactive.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1) {
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	}
	// 特例环节处理
	int dept = loginModel.getDid();
	int position = loginModel.getPid();
	// 获取该流程设置的特例环节
	String spids = T_Wtrans_Sp.dao.getNextSteps(String.valueOf(itemid), dept, position);
	if (null == spids || spids.length() == 0) {
	    // 特例环节获取失败，获取正常环节
	} else {
	    nextstepsnum = spids.split(",").length;
	    nextitemid = spids;

	}
	setAttr("nextstepsnum", nextstepsnum);
	setAttr("nextitemid", nextitemid);
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

	// 业务信息
	T_Doc_Receive dc = T_Doc_Receive.dao.findFirst("select * from t_doc_receive where pid=?", pid);
	if (null != opinionfield && !"".equals(opinionfield)) {
	    dc.set(opinionfield, opinions);
	}

	setAttr("dc", dc);
	setAttr("receiver", T_User.findUsernames(String.valueOf(dc.getInt("receiver"))));
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	setAttr("fileList", fileList);
	if (fileList.size() == 1) {
	    T_Attachment attah = fileList.get(0);
	    String suffix = attah.getStr("suffix");
	    if (suffix.equals("doc")) {
		setAttr("fileurl", attah.getStr("url"));
	    }
	}
	int dc_u_id = dc.getInt("u_id");
	int dc_d_id = dc.getInt("d_id");
	setAttr("starter", T_User.dao.findValueById("name", dc_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", dc_d_id));
	if (null != dc.get("receiver")) {
	    if (dc.getInt("receiver") > 0) {
		setAttr("receiver", T_User.dao.findValueById("name", dc.getInt("receiver")));
	    }
	}

	String receive2 = dc.getStr("receive2");
	if (null != receive2 && !"".equals(receive2)) {
	    setAttr("receive2", T_Department.findDeptnames(receive2));
	}
	/*
	 * setAttr("opinion1", T_Workitem.dao.getOpinions(pid, "opinion1"));
	 * setAttr("opinion2", T_Workitem.dao.getOpinions(pid, "opinion2"));
	 */

	// 将签名更换为图片签名
	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	render("approve.jsp");
    }

    @Clear
    public void todolist() {// 待办列表
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();

	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "wf.id";
	String orderDirection = "desc";
	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null;
	String select = "select wf.*, wa.name as active_name,wa.sealword,u.name as uname,"
		+ " dp.sname as dpname,dc.unit as unit,dc.title as doctitle,"
		+ " dc.docno as docno,dc.receive1,dc.security,dc.degree,ts.supervisor as temp1 ";// 将temp改为temp1
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id "
		+ "left join t_user u on wf.starter=u.id " + "left join t_department dp on wf.startdept=dp.id "
		+ "left join t_doc_receive dc on wf.id = dc.pid "
		+ "left join t_superdo ts on wf.id = ts.pid and wf.itemid = ts.itemid "
		+ "where ';'+wf.todoman+';' like '%;" + u_id + ";%' and wf.flowform='docreceive' and wf.isend='0'";

	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
		sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
		setAttr("stitle", stitle);
	    }
	    if (null != getPara("sunit") && !"".equals(getPara("sunit"))) {
		sunit = getPara("sunit");
		sqlExceptSelect += " and dc.unit like '%" + sunit + "%'";
		setAttr("sunit", sunit);
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
	    if (null != getPara("pageSize") && !"".equals(getPara("pageSize").trim())) {
		pageSize = getParaToInt("pageSize");

	    }
	    if (pageSize <= 0) {
		throw new Exception();
	    }
	    if (null != getPara("pageCurrent") && !"".equals(getPara("pageCurrent").trim())) {
		pageNumber = getParaToInt("pageCurrent");
		setAttr("pageCurrent", pageNumber);
	    }
	    if (pageNumber <= 0) {
		throw new Exception();
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}

	String doflag = getPara("doflag"); // 处理类型的判断
	if (StrKit.notBlank(doflag)) {
	    setAttr("doflag", getPara("doflag"));
	    switch (doflag) {
	    case "1":
		sqlExceptSelect += " and dc.doflag=1";
		break;
	    case "2":
		sqlExceptSelect += " and dc.doflag !=1";
		break;
	    case "3":
		sqlExceptSelect += " and dc.doflag !=1";
		break;
	    default:
		break;
	    }
	} else {
	    sqlExceptSelect += " and dc.doflag !=1";
	}
	if (StrKit.notBlank(getPara("orderDirection"))) {
	    setAttr("sort", "asc");
	    if (getPara("sort").equals("asc")) {
		orderDirection = "desc";
		setAttr("sort", "desc");
	    } else {
		orderDirection = "asc";
		setAttr("sort", "asc");
	    }
	}
	sqlExceptSelect += " order by  " + orderField + " " + orderDirection + " ";

	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("deptHm", T_Department.dao.hashMapByIds("sname"));
	setAttr("userHM", userHm);
	render("todolist.jsp");
    }

    /** 编辑审批 */
    public void toapprove() {
	String pid = getPara();
	setAttr("url", "Main/Docreceive/approve1/" + pid);
	render("toprint.jsp");
    }

    @Clear
    public void approve1() {
	int pid = getParaToInt(0);
	int superdoflag = 0;
	if (this.getParaToInt(1) != null) {
	    superdoflag = this.getParaToInt(1);
	    setAttr("superdoflag", superdoflag);
	}

	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	@SuppressWarnings("unused")
	int d_id = loginModel.getDid();
	String userid = String.valueOf(u_id);
	int p_id = loginModel.getPid();
	// 获取承办环节的所有执行人
	String doman = T_Workitem.dao.findUnderTake(pid);// 获取该流程下的承办人员
	setAttr("doman", doman);
	setAttr("u_id", u_id);
	setAttr("userid", userid);
	setAttr("positionid", p_id);
	boolean candelay = false;
	if (p_id == 12 || p_id == 14 || p_id == 16) {
	    candelay = true;
	}
	setAttr("candelay", candelay);
	setAttr("uname", loginModel.getUserName());
	setAttr("d_id", loginModel.getDid());
	setAttr("dname", loginModel.getDepartment());
	setAttr("localarea", "hys");
	// 获取办公室人员
	List<T_User> offmebers = T_User.findLeaderByDepart(2);
	setAttr("offmebers", offmebers);
	String mServerUrl = "http://" + getRequest().getServerName() + ":" + getRequest().getServerPort()
		+ getRequest().getContextPath() + "/Main/HtmlSignature/ExecuteRun";
	setAttr("mServerUrl", mServerUrl);

	// 流程环节信息
	// 流程环节信息
	T_Wprocess wp = T_Wprocess.dao.findFirst("select * from t_wprocess where flow=?", "docreceive");
	setAttr("wp", wp);
	T_Workflow wf = T_Workflow.dao.findById(pid);
	setAttr("wf", wf);
	int itemid = wf.getInt("itemid");
	setAttr("itemid", itemid);
	T_Wactive wa = T_Wactive.dao.findById(itemid);

	setAttr("wa", wa);

	String tmpuserid = ";" + u_id + ";";
	String tmptodoman = ";" + wf.getStr("todoman") + ";";
	String nowDate = DateUtils.getNowDate();
	// 授权情况查询
	Boolean isgrant = false;
	String grantSelect = " SELECT * FROM t_grant g where g.g_id = " + loginModel.getUserId();
	grantSelect += " AND (g.type = 9 or g.type = 1) AND g.usable = 0";
	grantSelect += " AND (g.s_date is null OR g.s_date <='" + nowDate + "')";
	grantSelect += " AND (g.e_date is null OR g.e_date >= '" + nowDate + "')";
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

	if ("1".equals(wf.getStr("isreceive")) && StrKit.isBlank(wf.getStr("shoulddo"))) {// 判断当前环节为已签收，并且同一环节处理人为空
	    setAttr("todomannum", 1);
	    setAttr("nextstepsnum", 1);
	    setAttr("nextitemid", 105);
	} else {
	    String todousers = wf.getStr("todousers");
	    int todomannum = 0;
	    if (StrKit.notBlank(todousers)) {
		String mans[] = todousers.split(";");
		todomannum = mans.length;
	    } else {
		todomannum = 1;
	    }

	    setAttr("todomannum", todomannum);
	    int nextstepsnum;
	    String nextitemid = "";

	    // 特例环节处理
	    int dept = loginModel.getDid();
	    int position = loginModel.getPid();
	    /*
	     * int nextstepsnum = 0; String nextitemid = "";
	     */
	    // 获取该流程设置的特例环节
	    String spids = T_Wtrans_Sp.dao.getNextSteps(String.valueOf(itemid), dept, position);
	    if (null == spids || spids.length() == 0) {
		// 特例环节获取失败，获取正常环节
		// nextstepsnum = T_Wactive.dao.nextActives(itemid);
		nextstepsnum = T_Wtrans.dao.nextActives1(itemid, wf.getStr("isreceive"));
		if (nextstepsnum == 1)
		    // nextitemid = T_Wactive.dao.getNextStep(itemid);
		    nextitemid = T_Wtrans.dao.getNextStep1(itemid, wf.getStr("isreceive"));
	    } else {
		nextstepsnum = spids.split(",").length;
		nextitemid = spids;

	    }
	    setAttr("nextstepsnum", nextstepsnum);
	    setAttr("nextitemid", nextitemid);
	}
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
		if (u_id == 93) {//
		    wf.set("iscanreceive", "1");
		}
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
	// 部领导强制做意见域修改

	String opinions = T_Workitem.dao.getOpinions(pid, itemid, userid, opinionfield);
	String opinionname = (String) T_Wfield.dao.findValueByWhere("description",
		"where name='" + opinionfield + "' and wid='" + wa.getInt("wid") + "'");
	setAttr("opinionname", opinionname);

	boolean canedit = false;
	String editor = wf.getStr("editor");
	if (StrKit.notBlank(editor) && editor.equals(userid) && wa.getStr("editable").equals("1")) {
	    canedit = true;
	}
	boolean candeal = false;
	if (StrKit.notBlank(editor) && editor.equals(userid) && wf.getStr("ishold").equals("0")) {
	    candeal = true;
	} else if ((";" + wf.getStr("todoman") + ";").indexOf(";" + userid + ";") >= 0) {
	    candeal = true;
	}
	setAttr("candeal", candeal);

	// 业务信息
	T_Doc_Receive dc = T_Doc_Receive.dao.findFirst("select *  from t_doc_receive  where pid=?", pid);
	// setAttr("opiniontext",dc.getStr("opiniontext"));
	if (null != opinionfield && !"".equals(opinionfield)) {
	    dc.set(opinionfield, opinions);
	}

	if (loginModel.getPid() == 12 && wa.getInt("id") == 107) {
	    dc.set("opinion1", T_Workitem.dao.getOpinions(pid, "opinion1"));
	}
	setAttr("dc", dc);
	setAttr("receiver", T_User.findUsernames(String.valueOf(dc.getInt("receiver"))));
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	setAttr("fileList", fileList);
	if (fileList.size() == 1) {
	    T_Attachment attah = fileList.get(0);
	    String suffix = attah.getStr("suffix");
	    if (suffix.equals("doc")) {
		setAttr("fileurl", attah.getStr("url"));
	    }
	}

	int dc_u_id = dc.getInt("u_id");
	int dc_d_id = dc.getInt("d_id");
	setAttr("starter", T_User.dao.findValueById("name", dc_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", dc_d_id));
	if (null != dc.get("receiver")) {
	    if (dc.getInt("receiver") > 0) {
		setAttr("receiver", T_User.dao.findValueById("name", dc.getInt("receiver")));
	    }
	}
	String iscanreceive = "0";// 可以签收，1
	String issubmit = "0";// 可以提交 ，1
	String mustreceive = "0";// 必须签收,1
	String mustsubmit = "0";// 必须提交
	if (wa.getInt("id") == 107 && T_Received.dao.judgeisreceive(dc.getInt("id"), u_id)) {
	    mustreceive = "1";
	}
	/*
	 * if(T_Received.dao.judgeissubmit(wf,dc.getInt("id"),u_id)){
	 * mustsubmit="1"; }
	 */
	if (itemid == 107) {// 如果当前在承办环节，判断本人是否已签收
	    if (T_Received.dao.hadReceive(u_id, dc.getInt("id"))) {
		mustsubmit = "1";
	    }
	}

	setAttr("mustsubmit", mustsubmit);
	setAttr("mustreceive", mustreceive);
	setAttr("iscanreceive", iscanreceive);// 判断是否可签收 0为不可以，1为可以
	setAttr("issubmit", issubmit);// 判断是否可提交 0为不可以提交，1为可以提交
	String receive2 = dc.getStr("receive2");
	if (null != receive2 && !"".equals(receive2)) {
	    setAttr("receive2", T_Department.findDeptnames(receive2));
	}

	// 将签名更换为图片签名
	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	setAttr("opinion3", T_Received.dao.getOpinion(dc.get("id"), u_id));
	setAttr("opinionother", T_Received.dao.getOtherOpinion(dc.get("id"), u_id));
	setAttr("opinion10", T_Received.dao.getOthermeOpinion(dc.get("id"), u_id));
	List<T_Received> receiverlist = T_Received.dao.findUsersByDoc(dc.get("id"));
	setAttr("receiverlist", receiverlist);
	if (canedit) {
	    render("approve1.jsp");
	} else {
	    render("approveview.jsp");
	}

    }

    public void todolist1() {// 待查阅列表
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
	    }
	    if (null != getPara("sunit") && !"".equals(getPara("sunit"))) {
		sunit = getPara("sunit");
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
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	String nowDate = DateUtils.getNowDate();
	// 授权情况查询
	String grantSelect = " SELECT * FROM t_grant g where g.g_id = " + u_id;
	grantSelect += " AND (g.type = 9 or g.type = 1) AND g.usable = 0";
	grantSelect += " AND (g.s_date is null OR g.s_date <='" + nowDate + "' )";
	grantSelect += " AND (g.e_date is null OR g.e_date >= '" + nowDate + "' )";
	String grantSet = "";
	List<T_Grant> grants = T_Grant.dao.find(grantSelect);
	for (T_Grant frant : grants) {
	    grantSet += " OR (';'+wf.todoman+';') like '%;" + frant.getInt("u_id") + ";%'";
	}
	String select = "select wf.*, wa.name as active_name, " + "wa.sealword," + "u.name as uname,"
		+ " dc.receive1 as dpname," + " dc.unit as unit," + " dc.title as doctitle,"
		+ " dc.docno as docno,dc.receive1, " + " dc.security," + " ts.supervisor as temp1 ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id "
		+ "left join t_user u on wf.starter=u.id " + "left join t_department dp on wf.startdept=dp.id "
		+ "left join t_doc_receive dc on wf.id = dc.pid "
		+ "left join t_superdo ts on wf.id = ts.pid and wf.itemid = ts.itemid "
		+ "where ((';'+wf.todoman+';') like '%;" + u_id + ";%' " + grantSet
		+ ") and wf.flowform='docreceive' and wf.isend='0'";
	if (null != stitle) {
	    sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != sunit) {
	    sqlExceptSelect += " and dc.unit like '%" + sunit + "%'";
	    setAttr("sunit", sunit);
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

	String doflag = getPara("doflag");
	if (StrKit.notBlank(doflag)) {
	    setAttr("doflag", getPara("doflag"));
	    switch (doflag) {
	    case "1":
		sqlExceptSelect += " and dc.doflag=1";
		break;
	    case "2":
		sqlExceptSelect += " and dc.doflag !=1";
		break;
	    case "3":
		sqlExceptSelect += " and dc.doflag!= 1";
		break;
	    default:
		break;
	    }
	} else {
	    sqlExceptSelect += " and dc.doflag='1'";
	}
	String orderDirection = "desc";
	if (StrKit.notBlank(getPara("orderDirection"))) {
	    setAttr("sort", "asc");
	    if (getPara("sort").equals("asc")) {
		orderDirection = "desc";
		setAttr("sort", "desc");
	    } else {
		orderDirection = "asc";
		setAttr("sort", "asc");
	    }
	}
	sqlExceptSelect += " order by wf.id " + orderDirection + "";
	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("deptHm", T_Department.dao.hashMapByIds("sname"));
	setAttr("userHM", userHm);
	render("todolist1.jsp");
    }

    public void delete() {
	int id = getParaToInt();
	T_Doc_Receive dc = T_Doc_Receive.dao.findByPid(id);
	if (null == dc) {
	    toErrorJson("收文不存在！");
	} else {
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    int u_id = loginModel.getUserId();
	    if (dc.getInt("u_id") == u_id) {
		T_Workflow.dao.deleteById(id);
		T_Workitem.dao.deleteBySqlwhere("where pid=" + id);
		T_Doc_Receive.dao.deleteBySqlwhere("where pid=" + id);
		toBJUIJson(200, "收文审批单删除完成！", "receive_todo", "", "", "", "");
		T_Doc_Achieve da = T_Doc_Achieve.dao.findById(dc.get("achieveid"));
		da.set("status", 0);
		da.update();
		T_Workflow.dao.deleteById(id);
		T_Workitem.dao.deleteBySqlwhere("where pid=" + id);
		T_Doc_Receive.dao.deleteBySqlwhere("where pid=" + id);
		toBJUIJson(200, "收文审批单删除完成！", "receive_todo", "", "", "", "");
	    } else {
		toErrorJson("只有经办人才能删除该文件的办理流程！");
	    }
	}
    }

    /** 办理中列表 */
    public void noendlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
	    }
	    if (null != getPara("sunit") && !"".equals(getPara("sunit"))) {
		sunit = getPara("sunit");
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
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.sname as dpname, dc.unit as unit, dc.security,dc.degree,dc.title as doctitle, dc.docno as docno, dc.receive1 as receive1 ";

	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id  "
		+ "left join t_doc_receive dc on wf.id = dc.pid " + " where (';'+reader+';') like '%;"
		+ loginModel.getUserId() + ";%' and wf.flowform like 'docreceive' and wf.isend='0'";
	System.out.println(select + sqlExceptSelect);
	if (null != stitle) {
	    sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != sunit) {
	    sqlExceptSelect += " and dc.unit like '%" + sunit + "%'";
	    setAttr("sunit", sunit);
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
	setAttr("deptMap", T_Department.dao.hashMapByIds("sname"));
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);
	render("noendlist.jsp");
    }

    @Clear
    public void toprint() {
	String pid = getPara();
	setAttr("url", "Main/Docreceive/print/" + pid);
	render("toprint.jsp");
    }

    @Clear
    public void print() {// 打印申请单
	int pid = getParaToInt();
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String mServerUrl = "http://" + getRequest().getServerName() + ":" + getRequest().getServerPort()
		+ getRequest().getContextPath() + "/Main/HtmlSignature/ExecuteRun";
	setAttr("mServerUrl", mServerUrl);

	// 业务信息
	T_Doc_Receive dc = T_Doc_Receive.dao.findFirst("select * from t_doc_receive where pid=?", pid);
	setAttr("dc", dc);
	setAttr("receiver", T_User.findUsernames(String.valueOf(dc.getInt("receiver"))));
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	setAttr("fileList", fileList);
	int dc_u_id = dc.getInt("u_id");
	int dc_d_id = dc.getInt("d_id");
	setAttr("starter", T_User.dao.findValueById("name", dc_u_id));
	setAttr("startdept", T_Department.dao.findValueById("fname", dc_d_id));
	if (null != dc.get("receiver")) {
	    if (dc.getInt("receiver") > 0) {
		setAttr("receiver", T_User.dao.findValueById("name", dc.getInt("receiver")));
	    }
	}
	String receive1 = dc.getStr("receive1");
	setAttr("mainDept", receive1);
	/*
	 * if (null != receive1 && !"".equals(receive1)) { setAttr("receive1",
	 * T_Department.dao.findValueById("fname", Integer.parseInt(receive1)));
	 * }
	 */
	String receive2 = dc.getStr("receive2");
	if (null != receive2 && !"".equals(receive2)) {
	    setAttr("receive2", T_Department.findDeptnames(receive2));
	}
	// 将签名更换为图片签名
	setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
	setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));
	setAttr("opinion3", T_Received.dao.getOpinion(dc.get("id"), loginModel.getUserId()));
	List<T_Received> receiverlist = T_Received.dao.findUsersByDoc(dc.get("id"));
	setAttr("receiverlist", receiverlist);
	render("view.jsp");
    }

    /** 已办结列表 */
    @Clear
    public void endlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
	    }
	    if (null != getPara("sunit") && !"".equals(getPara("sunit"))) {
		sunit = getPara("sunit");
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
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.sname as dpname, dc.unit as unit, dc.security,dc.degree,dc.title as doctitle, dc.docno as docno,dc.receive1 as receive1 ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id "
		+ "left join t_doc_receive dc on wf.id = dc.pid" + " where (';'+reader+';') like '%;"
		+ loginModel.getUserId()
		+ ";%' and wf.flowform like 'docreceive' and wf.isend='1' and wf.isnormalend='1'";

	if (null != stitle) {
	    sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != sunit) {
	    sqlExceptSelect += " and dc.unit like '%" + sunit + "%'";
	    setAttr("sunit", sunit);
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
	setAttr("deptHm", T_Department.dao.hashMapByIds("sname"));
	render("endlist.jsp");
    }

    @Clear
    public void save1() {
	boolean flag = false;
	Instance ins = new Instance();
	String msgstr = "";
	String achieveid = getPara("achieveid");
	String superdoflag = getPara("superdoflag");
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	T_Doc_Receive temp = getModel(T_Doc_Receive.class);
	String degree = temp.getStr("degree");
	// 当前流程序号
	int pid = getParaToInt("t_Workflow.id");
	// 当前环节序号
	int itemid = getParaToInt("t_Workflow.itemid");
	// 当前日期
	String nowday = DateUtils.getNowDate();
	
	T_Workflow wf = T_Workflow.dao.findById(pid);
	String isnewdoc = wf.getStr("isnewdoc");

	T_Wactive wa = T_Wactive.dao.findById(getPara("nextitemid"));

	String nextitemid = getPara("nextitemid");
	ins.setWActive(wa);

	ins.setWorkflow(wf);
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
	if (ins.getOperation().equals("TuiHuiShangBu")) {
	    wf.set("title", getPara("flowname") + " [回退]");
	} else if (ins.getOperation().equals("TuiHuiNiGao")) {
	    wf.set("title", getPara("flowname") + " [修改后提交]");
	} else {
	    wf.set("title", getPara("flowname"));
	}
	int u_id = loginModel.getUserId();
	try {
	    // 督办判断
	    if ("2".equals(superdoflag)) {

		String superman = T_User.dao.findValueById("name", u_id).toString();
		// 督办人员获得
		String todoman = getPara("t_Workflow.todoman");
		String[] mans = todoman.split(";");
		// 督办消息消息生成
		String docmessage = getPara("dc.receivedate") + "日来自" + getPara("dc.unit") + "的公文（"
			+ getPara("dc.title") + "）";
		String commessage = docmessage + "，已经由督办员" + superman + " 于" + nowday + "日发起了督办，请尽快办理";
		// 督办信息发送
		for (int i = 0; i < mans.length; i++) {
		    T_Commessage message = new T_Commessage();
		    message.set("u_id", mans[i]);
		    message.set("content", commessage);
		    message.set("isread", 0);
		    message.remove("id").save();
		}

		// 保存督办信息
		T_Superdo ts = new T_Superdo();
		ts.set("pid", pid);
		ts.set("itemid", itemid);
		ts.set("supervisor", u_id);
		ts.set("todoman", todoman);
		ts.set("superday", nowday);
		ts.set("status", "1");
		ts.remove("id").save();
		setAttr("superdoflag", superdoflag);
		msgstr = "督办成功！";
		flag = true;
	    } else {
		String isreceive = getPara("isreceive");
		String isPoint = getPara("isPoint");
		if (isreceive.equals("2")) {// 判断是否为签收操作，如果为签收则将签收人和签收时间保存到t_receive中

		    T_Received receiver = new T_Received();
		    receiver.set("doc_id", getPara("t_Doc_Receive.id"));
		    receiver.set("receive_man", loginModel.getUserName());
		    receiver.set("u_id", loginModel.getUserId());
		    receiver.set("opiniontime", DateUtils.getNowTime());

		    if (!"1".equals(wf.getStr("isreceive"))) {// 为初次签收则将除自己的所有人加入
			wf.set("isreceive", "1");
			wf.set("receiveitem", wf.getInt("itemid"));
		    }

		    T_Doc_Receive temp2 = T_Doc_Receive.dao.findById(getPara("t_Doc_Receive.id"));
		    String receive = Util.arrayAddstr(temp2.getStr("receive1"), "、", loginModel.getDepartment());
		    temp2.set("receive1", receive);
		    flag = receiver.save() && wf.update() && temp2.update();
		    msgstr = "签收操作成功，请填写办理情况！";
		} else if (null == isPoint ? false : isPoint.equals("1")) {// 判断是否为指派操作
		    ins.setOperation("ZhiPai");
		    Transaction trans = new Transaction();
		    WorkflowReturn wfr = trans.point(ins);
		    if (wfr.getMessage().equals("wfr2")) {
			flag = true;
			String todoman = wfr.getTodoman().replace(';', ',');
			String users = T_User.findUsernames(todoman);
			// 记录日记
			new T_Operation_Log(loginModel.getUserId(),
				T_Woperation.dao.getNameByCode(ins.getOperation()) + "了收文：" + temp.getStr("docno"),
				"Docreceive");
			msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】操作成功！ <br><br>收文处理单已流转到："
				+ users;
			// 发送短信
			String mobile = T_Personal.dao.getMobileByUsers(todoman);
			String message = "";
			if (degree.equals("2")) {
			    message = "您有一个特急件，须尽快登录OA系统处理！";
			} else if (degree.equals("1")) {
			    message = "您有一个平急件，请及时登录OA系统处理！";
			} else {
			    message = "您有一个新文件需要处理。";
			}
			String result = SMSUtil.sendsms(mobile, message);
			new T_Sms_Log(users, mobile, message, result, pid);
		    } else {
			flag = false;
			msgstr = "数据处理存在错误！";
		    }
		} else {
		    String opinion10 = getPara("opinion10");// 获取办理情况是否有填写意见
		    if (StrKit.notBlank(opinion10)) {
			T_Received receiver = T_Received.dao.findByDoc(getPara("t_Doc_Receive.id"),
				loginModel.getUserId());
			receiver.set("opinion", opinion10);
			receiver.update();
		    }
		    Transaction trans = new Transaction();
		    WorkflowReturn wfr = trans.Trans(ins);
		    // id = getPara("t_Doc_Receive.id");
		    // 0409 放开后续流程中可修改 文号，标题，来文单位，处理类型信息 by 徐
		    // T_Doc_Receive temp = T_Doc_Receive.dao.findById(id);

		    // 审核人（分办人）
		    temp.set("auditor", getPara("zhk.auditor"));

		    if (null != getPara("t_Doc_Receive.termdate") && !"".equals(getPara("t_Doc_Receive.termdate"))) {
			temp.set("termdate", getPara("t_Doc_Receive.termdate"));
		    }
		    String[] fjids = getParaValues("fjid");
		    temp.set("fjid", ArrayUtils.ArrayToString(fjids));
		    setAttr("opinion1", T_Workitem.dao.getOpinions(pid, "opinion1"));
		    setAttr("opinion2", T_Workitem.dao.getOpinions(pid, "opinion2"));

		    // 将签名更换为图片签名
		    setAttr("opinion1", T_Workitem.dao.getOpinions1(pid, "opinion1"));
		    setAttr("opinion2", T_Workitem.dao.getOpinions1(pid, "opinion2"));

		    if (ins.getOperation().equals("WanCheng")) { // 审批完成
			temp.set("pstatus", "2");
		    } else if (ins.getOperation().equals("FaSong")) { // 审批中
			temp.set("pstatus", "1");
		    } else if (ins.getOperation().equals("TuiHuiNiGao")) { // 退回申请人
			temp.set("pstatus", "0");
		    } else if (ins.getOperation().equals("ZhongZhi")) { // 已终止
			temp.set("pstatus", "3");
			T_Doc_Receive temp2 = T_Doc_Receive.dao.findById(getPara("t_Doc_Receive.id"));
			// 业务信息
			T_Doc_Achieve dc = T_Doc_Achieve.dao.findById(temp2.get("achieveid"));
			dc.set("status", 0);
			dc.update();
		    } else if (ins.getOperation().equals("TeSong") && ins.getOperation().equals("ChongZhi")) { // 审批中
			temp.set("pstatus", "1");
		    }
		    if (isnewdoc.equals("1")) {
			flag = temp.remove("id").save();
		    } else {
			flag = temp.update();
		    }
		    if (null != achieveid && !temp.getStr("pstatus").equals("0")) {
			// 业务信息
			T_Doc_Achieve dc = T_Doc_Achieve.dao.findById(achieveid);
			dc.set("status", temp.getStr("pstatus"));
			dc.update();
		    }
		    // 更新督办信息
		    T_Superdo ts = T_Superdo.dao.getWorkitem(pid, itemid);
		    if (ts != null) {
			// 获取用户的科室和姓名

			int d_id = loginModel.getDid();
			String u_name = T_User.dao.findValueById("name", u_id).toString();
			String d_name = T_Department.dao.findValueById("fname", d_id).toString();
			ts.set("opinionday", nowday);
			ts.set("doman", u_id);
			ts.set("status", "2");
			ts.update();
			// 给督办员发办理提醒
			String docmessage = getPara("dc.receivedate") + "日来自" + getPara("dc.unit") + "的公文（"
				+ getPara("dc.title") + "）";
			String commessage = docmessage + "，您在" + ts.getStr("superday") + "日发起了督办，";
			commessage += d_name + " " + u_name + "已经在" + nowday + "日办理，请确认";
			T_Commessage message = new T_Commessage();
			message.set("u_id", ts.getStr("supervisor"));
			message.set("content", commessage);
			message.set("isread", 0);
			message.remove("id").save();
		    }

		    if (wfr.getMessage().equals("wfr1")) {
			msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！";
		    } else if (wfr.getMessage().equals("wfr2")) {
			String todoman = wfr.getTodoman().replace(';', ',');
			String users = T_User.findUsernames(todoman);
			new T_Operation_Log(loginModel.getUserId(),
				T_Woperation.dao.getNameByCode(ins.getOperation()) + "了收文：" + temp.getStr("docno"),
				"Docreceive");// 记录日记
			msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】"
				+ "操作成功！ <br><br>收文处理单已流转到：" + users;
			if (T_Wactive.dao.canSendSms(nextitemid)) {
			    String mobile = T_Personal.dao.getMobileByUsers(todoman);
			    String message = "";
			    if (degree.equals("2")) {
				message = "您有一个特急件，须尽快登录OA系统处理！";
			    } else if (degree.equals("1")) {
				message = "您有一个平急件，请及时登录OA系统处理！";
			    } else {
				message = "您有一个新文件需要处理。";
			    }
			    String result = SMSUtil.sendsms(mobile, message);
			    new T_Sms_Log(users, mobile, message, result, pid);
			}
		    } else if (wfr.getMessage().equals("wfr3")) {
			msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！ <br><br>";
			if (StrKit.notBlank(wfr.getNextShoulddo())) {
			    msgstr += "下一处理人 " + T_User.findUsernames(wfr.getNextShoulddo().replace(';', ',')) + "已暂存，";
			}
			if (StrKit.notBlank(wfr.getTodoman())) {
			    msgstr += "该环节将继续由" + T_User.findUsernames(wfr.getTodoman().replace(';', ',')) + "处理！";
			}
			flag = true;
		    }
		}
		setAttr("flag", flag);
		setAttr("msgstr", msgstr);
		render("success.jsp");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	    flag = false;
	    setAttr("flag", flag);
	    render("success.jsp");
	}
    }

    /** 督办列表 */
    @SuppressWarnings("unused")
    @Clear
    public void superlist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null;
	String ssuperman = null, stdate = null, etdate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
	    }
	    if (null != getPara("sunit") && !"".equals(getPara("sunit"))) {
		sunit = getPara("sunit");
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
	    if (null != getPara("ssuperman") && !"".equals(getPara("ssuperman"))) {
		ssuperman = getPara("ssuperman");
	    }
	    if (null != getPara("stdate") && !"".equals(getPara("stdate"))) {
		stdate = getPara("stdate");
	    }
	    if (null != getPara("etdate") && !"".equals(getPara("etdate"))) {
		etdate = getPara("etdate");
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
	// 获取办公室人员
	List<T_User> offmebers = T_User.findLeaderByDepart(2);
	setAttr("offmebers", offmebers);
	// LoginModel loginModel = (LoginModel)
	// getSession().getAttribute("loginModel");
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, dc.unit as unit, dc.title as doctitle, dc.docno as docno, dc.termdate as termdate1,dc.receive1 ";
	String sqlExceptSelect = "from t_workflow wf " + " ,t_wactive wa  " + " ,t_user u " + " ,t_department dp, "
		+ " t_doc_receive dc " + " where wf.flowform = 'docreceive' " + " and wf.itemid=wa.id "
		+ " and wf.startdept=dp.id " + " and dc.superman=u.id " + " and wf.id = dc.pid "
		+ " and dc.superman is not null " + " and wf.isend='0' and dc.superman is not null and dc.doflag = 2 ";
	// 督办员
	// 所属科室id
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int DepartmentId = loginModel.getDid();
	Integer userId = loginModel.getUserId();
	int pid = loginModel.getPid();
	// 办公室主任能查看所有的督办件；其他办公室职员时能看到自己负责督办的文件。
	T_Wprocess wpc = T_Wprocess.dao.getProcessByFlow("docreceive");
	if (("," + wpc.getStr("managers") + ",").indexOf("," + userId + ",") < 0) {
	    sqlExceptSelect = sqlExceptSelect + " and dc.superman= " + userId;
	}
	if (null != stitle) {
	    sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != sunit) {
	    sqlExceptSelect += " and dc.unit like '%" + sunit + "%'";
	    setAttr("sunit", sunit);
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
	if (null != ssuperman && ssuperman != "") {
	    sqlExceptSelect += " and dc.superman ='" + ssuperman + "'";
	    setAttr("ssuperman", ssuperman);
	}
	if (null != stdate) {
	    sqlExceptSelect += " and dc.termdate>='" + stdate + "'";
	    setAttr("stdate", stdate);
	}
	if (null != etdate) {
	    sqlExceptSelect += " and dc.termdate<='" + etdate + "'";
	    setAttr("etdate", etdate);
	}
	sqlExceptSelect += " order by dc.termdate desc";
	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);
	setAttr("deptHm", T_Department.dao.hashMapByIds("sname"));
	render("superlist.jsp");
    }

    @Clear
    public void uncirculationlist() {// 系统归档列表
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null;
	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle"))) {
		stitle = getPara("stitle");
	    }
	    if (null != getPara("sunit") && !"".equals(getPara("sunit"))) {
		sunit = getPara("sunit");
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
	// LoginModel loginModel = (LoginModel)
	// getSession().getAttribute("loginModel");
	String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, dc.unit as unit, dc.security,dc.title as doctitle, dc.docno as docno , dc.termdate as termdate1";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id left join t_department dp on wf.startdept=dp.id "
		+ "left join t_doc_receive dc on wf.id = dc.pid"
		+ " where  wf.flowform like 'docreceive' and wf.isend='1' and wf.isnormalend='2'";
	if (null != stitle) {
	    sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != sunit) {
	    sqlExceptSelect += " and dc.unit like '%" + sunit + "%'";
	    setAttr("sunit", sunit);
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
	render("uncirculationlist.jsp");
    }

    @Clear
    public void OrderTest() {
	render("test.jsp");
    }

    /** 档案柜 */

    public void filecab() {
	setAttr("page", filecabCommon());
	setAttr("deptHm", T_Department.dao.hashMapByIds("sname"));
	setAttr("departs", T_Department.dao.list());// 承办科室
	render("filecablist.jsp");
    }

    public Page<T_Workflow> filecabCommon() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "dc.id";// 排序字段名
	String orderDirection = "desc";// asc || desc

	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	// 所属科室id
	int DepartmentId = loginModel.getDid();

	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null, sdepart = null;

	String select = "select wf.*, dc.receivedate, wa.name as active_name, u.name as uname, dc.receive1, dc.unit as unit, dc.title as doctitle, dc.docno as docno ";
	String sqlExceptSelect = "from t_workflow wf left join t_wactive wa on wf.itemid=wa.id left join t_user u on wf.starter=u.id "
		+ "left join t_doc_receive dc on wf.id = dc.pid left join t_department dp on wf.startdept=dp.id "
		+ " where wf.flowform like 'docreceive' and wf.isend='1' and wf.isnormalend > 0 " + " ";
	try {
	    if (DepartmentId != 2) {
		sqlExceptSelect += " and (dc.d_id ='" + DepartmentId + "' or dc.doflag = 1) ";
	    }
	    if (StrKit.notBlank(getPara("sdepart"))) {
		sdepart = getPara("sdepart");
		sqlExceptSelect += " and dc.receive1 like '%" + sdepart + "%'";
		setAttr("sdepart", sdepart);
	    }
	    if (StrKit.notBlank(getPara("stitle"))) {
		stitle = getPara("stitle");
		sqlExceptSelect += " and dc.title like '%" + stitle + "%'";
		setAttr("stitle", stitle);
	    }
	    if (StrKit.notBlank(getPara("sunit"))) {
		sunit = getPara("sunit");
		sqlExceptSelect += " and dc.unit like '%" + sunit + "%'";
		setAttr("sunit", sunit);
	    }
	    if (StrKit.notBlank(getPara("sdocno"))) {
		sdocno = getPara("sdocno");
		sqlExceptSelect += " and dc.docno like '%" + sdocno + "%'";
		setAttr("sdocno", sdocno);
	    }
	    if (StrKit.notBlank(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and dc.receivedate>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (StrKit.notBlank(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and dc.receivedate<='" + edate + "'";
		setAttr("edate", edate);
	    }
	    if (StrKit.notBlank(getPara("orderField"))) {
		orderField = getPara("orderField");
		setAttr("orderField", orderField);
	    }
	    if (StrKit.notBlank(getPara("orderDirection"))) {
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
	// 流程环节信息
	T_Wprocess wp = T_Wprocess.dao.findFirst("select * from t_wprocess where flow=?", "docreceive");
	setAttr("wp", wp);
	int itemid = wp.getInt("beginstep");
	setAttr("itemid", itemid);
	T_Wactive wa = T_Wactive.dao.findFirst("select * from t_wactive where id=?", itemid);
	setAttr("wa", wa);

	Date now = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy");
	String nowyear = df.format(now);
	setAttr("nowyear", nowyear);

	// 下一环节信息
	int todomannum = 1;
	setAttr("todomannum", todomannum);
	int nextstepsnum = T_Wactive.dao.nextActives(itemid);
	setAttr("nextstepsnum", nextstepsnum);
	String nextitemid = "";
	if (nextstepsnum == 1)
	    nextitemid = T_Wactive.dao.getNextStep(itemid);
	setAttr("nextitemid", nextitemid);

	render("newCab.jsp");
    }

    /** 提交审批单，流程流转 */
    @Clear
    public void saveCab() {
	boolean flag = false;
	@SuppressWarnings("unused")
	String msgstr = "";
	try {
	    String docno = getPara("t_Doc_Receive.docno");
	    List<T_Doc_Receive> list = T_Doc_Receive.dao.getListByDocNo(docno);
	    if (list.size() > 0) {
		toErrorJson("该文号已存在，请检查后重新提交！");
		return;
	    }

	    T_Workflow wf = getModel(T_Workflow.class);
	    wf.set("isend", "1");
	    wf.set("title", getPara("flowname"));
	    wf.set("isnormalend", "1");
	    wf.set("isopen", "0");
	    wf.remove("id");
	    wf.set("starttime", getPara("t_Doc_Receive.receivedate"));
	    wf.save();
	    int pid = wf.getInt("id");

	    T_Doc_Receive temp = getModel(T_Doc_Receive.class);
	    temp.set("pid", pid);
	    String[] fjids = getParaValues("fjid");
	    temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    temp.set("receiver", getPara("jbr.userid"));
	    temp.set("receive1", getPara("zrks.id"));
	    if (getPara("zrks.id") == null || getPara("zrks.id").length() < 1) {
		temp.set("receive1", 2);
	    }
	    temp.set("pstatus", "2");
	    temp.remove("id").save();

	    flag = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    toBJUIJson(200, Constant.SUCCESS, "Docreceive", "", "", "true", "");
	}
    }

    /**
     * 档案柜,收文归档删除
     */
    public void deleteCab() {
	int id = getParaToInt();
	T_Doc_Receive docReceive = T_Doc_Receive.dao.findByPid(id);
	T_Workflow workflow = T_Workflow.dao.findById(id);
	if (null == docReceive || null == workflow) {
	    toErrorJson("您提交的数据有误，档案不存在！");
	    return;
	}
	if (StrKit.notBlank(docReceive.getStr("opinion1"))) {
	    toErrorJson("本文件不是上传的历史办文记录，禁止删除！");
	    return;
	}
	docReceive.set("pstatus", "3");
	workflow.set("isnormalend", "0");
	try {
	    if (docReceive.update() && workflow.update()) {
		toBJUIJson(200, Constant.SUCCESS, "Docreceive", "", "", "", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	}
    }

    public void selectman() {
	String doman = getPara(0);
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int did = loginModel.getDid();

	String sql = "select * from t_user where d_id =" + did + " and (';" + doman
		+ ";' not like '%;'+cast(id as varchar(10))+';%') order by no ";
	List<T_User> users = T_User.dao.find(sql);
	setAttr("users", users);
	render("findUsers.jsp");
    }

    /**
     * 收文归档导出
     */
    @SuppressWarnings("unchecked")
    public void exportCab() {
	filecabCommon();
	HttpServletResponse response = getResponse();
	response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
	response.setContentType("application/octet-stream");
	try {
	    OutputStream os = response.getOutputStream();
	    String title = "收文归档";
	    String[] headers = new String[] { "序号", "收文时间", "承办科室", "来文单位", "来文文号", "来文标题" };
	    ExportExcel<T_Workflow> ex = new ExportReceiveCab();
	    ex.exportExcel(title, headers, filecabCommon().getList(), os, null);
	    renderNull();
	    os.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // 打印
    @Clear
    public void exportPdf() {
	int pid = getParaToInt();
	String templateName = "惠州人防办文件呈批表.doc";
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	Report.replaceTextReceive(pid, templateName, "惠州人防办文件呈批表", loginModel);
	setAttr("filename", templateName.substring(0, templateName.length() - 4));
	render("viewPdf.jsp");
    }

    @Clear
    public void editopinion() {
	render("editopinion.jsp");
    }

    @Clear
    public void viewReceiver() {
	int pid = getParaToInt(0);
	T_Doc_Receive receive = T_Doc_Receive.dao.findByPid(pid);
	List<T_Received> list = T_Received.dao.findUsersByDoc(receive.get("id"));
	setAttr("list", list);
	render("viewReceiver.jsp");

    }

    public String arrayDelstr(String s, String s1) {
	String tmpstr = "";
	if (s == null)
	    s = "";
	String str[] = s.split(";");
	for (int i = 0; i < str.length; i++) {
	    if (!str[i].equals(s1)) {
		if (tmpstr.equals(""))
		    tmpstr = str[i];
		else
		    tmpstr = tmpstr + ";" + str[i];
	    }
	}
	return tmpstr;
    }
}
