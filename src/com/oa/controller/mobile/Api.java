package com.oa.controller.mobile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.JsonKit;
//import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.json.LeaveDetailJson;
import com.oa.controller.json.ReceiveDetailJson;
import com.oa.controller.json.ReceiverJson;
import com.oa.controller.json.T_FJList;
import com.oa.controller.json.WorkflowJson;
import com.oa.controller.kit.ApiBase;
import com.oa.controller.kit.InDeleteMsg;
import com.oa.controller.kit.InQueryMsg;
import com.oa.controller.kit.InSaveMsg;
import com.oa.model.approve.T_Leave_Approve;
import com.oa.model.approve.T_Personal;
import com.oa.model.common.T_Attachment;
import com.oa.model.document.T_Doc_Achieve;
import com.oa.model.document.T_Doc_Receive;
import com.oa.model.document.T_Doc_Send;
import com.oa.model.document.T_Inner_Send;
import com.oa.model.document.T_Inner_Send_Viewer;
import com.oa.model.document.T_Received;
import com.oa.model.system.app.T_App_Version;
import com.oa.model.system.office.T_Doc_File;
import com.oa.model.system.workflow.Instance;
import com.oa.model.system.workflow.T_Myopinion;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Woperation;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.system.workflow.T_Workitem;
import com.oa.model.system.workflow.T_Wprocess;
import com.oa.model.system.workflow.T_Wtrans;
import com.oa.model.system.workflow.T_Wtrans_Sp;
import com.oa.model.system.workflow.Transaction;
import com.oa.model.system.workflow.WorkflowReturn;
import com.oa.model.work.T_Announce;
import com.oa.model.work.T_Announce_Viewer;
import com.oa.model.work.T_Mail;
import com.oa.model.work.T_Myschedule;
import com.oa.model.work.T_MyscheduleMessage;
import com.oa.model.work.T_Note;
import com.oa.model.work.T_ScheduleType;
import com.oa.service.LeaveService;
import com.oa.service.ReceiveService;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Common_Detail;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;
import com.zhilian.util.ArrayUtils;

import com.zhilian.util.DateUtils;
import com.zhilian.util.FileUtils;

import DBstep.iMsgServer2000;

@RouteBind(path = "/Api", viewPath = "")
public class Api extends ApiBase {

    static ReceiveService receiveservice = ReceiveService.me;
    static LeaveService leaveservice = LeaveService.me;
    private BussinessContent bc = new BussinessContent();
    private static String filePath = Constant.PATH_WEBROOT;

    // private static String filename = "file.doc";

    @Override
    protected void processInQueryMsg(InQueryMsg msg) {
	String json = JsonKit.toJson(msg);
	System.out.println("json = " + json);
	String key = msg.getKey();
	String msgstr = "";
	String content = "";// 查询条件
	String pageNumber;
	String pageSize = null;
	int pageNumberInt;
	int pageSizeInt = 0;
	BussinessContent bc = new BussinessContent();
	Map<String, T_User> userMap = bc.getUserMap();
	T_User user = userMap.get(key);
	if (user != null) {
	    String nexttype;
	    Integer curitemid;
	    Integer pid;
	    int u_id = user.getInt("id");
	    int d_id = user.getInt("d_id");
	    int positionid = user.getInt("pid");
	    String queryName = msg.getQueryName();
	    String condition;// 查询条件
	    int id;
	    Map<String, String> queryPara = msg.getQueryPara();
	    switch (queryName) {
	    case "getAnnounceList": // 通知列表
		List<T_Announce> announce = T_Announce.dao.getAnnounceList(u_id);
		renderJson(announce);
		break;
	    case "AnnounceDetail":// 通知通告详细信息
		id = Integer.parseInt(queryPara.get("id"));
		String sql = "select id,atype,sendtime,title,content,uid,did from t_announce where id=" + id;
		T_Announce announcedetail = T_Announce.dao.findFirst(sql);
		announcedetail.set("uid", T_User.getUserNameById(announcedetail.get("uid")));
		announcedetail.set("did", T_Department.dao.findValueById("sname", announcedetail.getInt("did")));
		renderJson(announcedetail);
		break;
	    case "viewAnnounce":// 查看通知通告并确认阅读
		id = Integer.parseInt(queryPara.get("id"));
		T_Announce_Viewer model = new T_Announce_Viewer();
		model.set("viewerid", u_id).set("viewtime", DateUtils.getNowTime()).set("announceid", id);
		if (model.save()) {
		    renderJson("{msg:'操作成功！'}");
		} else {
		    renderJson("{msg:'操作失败！'}");
		}
		break;
	    case "getDepartmentList":// 获取部门列表
		List<T_Department> departmentList = T_Department.getAllDepts();
		renderJson(departmentList);
		break;
	    case "getPersonalsList":// 获取某一部门所有人
		pid = Integer.valueOf(queryPara.get("id"));
		List<T_Personal> personnal = T_Personal.dao.findPersonnalList(pid);
		renderJson(personnal);
		break;
	    case "PersonalDetail":// 个人详细联系方式。。。
		id = Integer.valueOf(queryPara.get("id"));
		T_Personal personnalDetail = T_Personal.dao.findByUid(id);
		renderJson(personnalDetail);
		break;
	    case "PersonnalADepartment":
		/*
		 * List<T_User> PAD=T_Personal.dao.findPAD(); renderJson(PAD);
		 */
		renderJson(usertree());
		break;
	    case "PersonnalInfo":
		List<T_User> userinfo = T_Note.dao.getUsersInfo();
		renderJson(userinfo);
		break;
	    case "getAchieveList": // 获取一般阅读列表
		pageNumber = queryPara.get("pageNumber");
		content = queryPara.get("content");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		Page<T_Workflow> achieveList = T_Workflow.dao.getReadabilityList(content, pageNumberInt, pageSizeInt);
		renderJson(achieveList);
		break;
	    case "searchAchieveList": // 查询收文登记列表
		content = queryPara.get("content");
		List<T_Doc_Achieve> searchachieveList = T_Doc_Achieve.dao.getSearchAchieveList(d_id, content);
		// if (searchachieveList.size() > 20) {
		// renderJson("该条件查询的结果超过20条，请输入更详细的查询条件！");
		// } else {
		renderJson(searchachieveList);
		// }
		break;
	    case "getToDoAchieveList": // 查询收文登记列表
		List<T_Doc_Achieve> todoachieveList = T_Doc_Achieve.dao.getTodoAchieveList();
		renderJson(todoachieveList);
		break;
	    case "AchieveDetail": // 获取收文登记信息
		String adocid = queryPara.get("docid");
		achieveDetail(user, adocid);
		break;
	    case "getReceiveTodoList":// 获取收文待办列表
		condition = queryPara.get("condition");
		pageNumber = queryPara.get("pageNumber");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		Page<T_Workflow> receivetodoList = T_Workflow.dao.getReceiveTodoList(u_id, condition, pageNumberInt,
			pageSizeInt);
		renderJson(receivetodoList);
		break;
	    case "getReceiveDoneList":// 获取收文已办列表
		condition = queryPara.get("condition");
		pageNumber = queryPara.get("pageNumber");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		Page<T_Workflow> receivedoneList = T_Workflow.dao.getReceiveDoneList(u_id, condition, pageNumberInt,
			pageSizeInt);
		renderJson(receivedoneList);
		break;
	    case "receive":// 签收操作
		receive(user, queryPara);
		break;
	    case "getDocsendTodoList":// 获取发文待办列表
		condition = queryPara.get("condition");
		pageNumber = queryPara.get("pageNumber");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		Page<T_Workflow> docsendtodoList = T_Workflow.dao.getDocsendTodoList(u_id, condition, pageNumberInt,
			pageSizeInt);
		renderJson(docsendtodoList);
		break;
	    case "getDocsendDoneList":// 获取发文已办列表
		condition = queryPara.get("condition");
		pageNumber = queryPara.get("pageNumber");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		Page<T_Workflow> docsenddoneList = T_Workflow.dao.getDocsendDoneList(u_id, condition, pageNumberInt,
			pageSizeInt);
		renderJson(docsenddoneList);
		break;
	    case "getAchieveDetail":
		condition = queryPara.get("condition");
		id = Integer.parseInt(queryPara.get("id"));
		T_Doc_Receive achieveDetail = T_Doc_Receive.dao.getReceiveDetail(id);
		List<T_Attachment> list1 = T_Attachment.dao.getListByIds(achieveDetail.getStr("fjid"));
		List<T_Workitem> opinion1list = T_Workitem.dao.getOpinion1(achieveDetail.getInt("pid"), "opinion1");
		List<T_Workitem> opinion2list = T_Workitem.dao.getOpinion1(achieveDetail.getInt("pid"), "opinion2");
		achieveDetail.set("u_id", T_User.getUserNameById(achieveDetail.getInt("u_id")));
		achieveDetail.set("fjid", list1);
		achieveDetail.set("opinion1", opinion1list);
		achieveDetail.set("opinion2", opinion2list);
		renderJson(achieveDetail);
		break;
	    case "ReceiveDetail":// 收文详情
		String rdocid = queryPara.get("docid");
		String risdone = queryPara.get("isdone");
		receiveDetail(user, rdocid, risdone);
		break;
	    case "DocsendDetail":// 发文详情
		String sdocid = queryPara.get("docid");
		String sisdone = queryPara.get("isdone");
		docsendDetail(u_id, sdocid, sisdone);
		break;
	    case "ReceiveRecord":// 流转记录
		id = Integer.parseInt(queryPara.get("pid"));
		receiveRecord(u_id, id);
		break;
	    case "newopinion":// 发文填写意见
		String opinionsend = queryPara.get("opinion");
		pid = Integer.parseInt(queryPara.get("pid"));
		saveAndgetSendDetail(u_id, opinionsend, pid);
		break;
	    case "fasong":// 提交
		curitemid = Integer.parseInt(queryPara.get("curitemid"));// 当前环节的id
		pid = Integer.parseInt(queryPara.get("pid"));// 当前流程的id
		String doc = queryPara.get("doc");
		// nexttype = queryPara.get("nexttype");
		selectman(curitemid, pid, d_id, positionid, doc);
		break;
	    case "fasong1":// 确定
		String ids = queryPara.get("nextitemid");// 选中环节的id
		pid = Integer.parseInt(queryPara.get("pid"));
		nexttype = queryPara.get("nexttype");// 如果为windows则返回环节id，否则为个人id
		SelectStep selectstep = new SelectStep();
		MoblieWorkflow workflow = new MoblieWorkflow();
		if (nexttype.equals("windows")) {
		    selectstep = workflow.selectman2(ids, pid, d_id);
		} else {
		    // msgstr = toNext(queryPara, u_id, d_id);
		    msgstr = Save(queryPara, u_id, d_id);
		    selectstep.setType(msgstr);
		}
		selectstep.setNextType(nexttype);
		renderJson(selectstep);
		break;
	    case "getfile":// 查看正文
		pid = Integer.parseInt(queryPara.get("pid"));
		getFile(pid);
		break;
	    case "getInnerSendList": // 内部发文列表
		condition = queryPara.get("condition");
		pageNumber = queryPara.get("pageNumber");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		Page<T_Inner_Send> innersendlist = T_Inner_Send.dao.getReadListByUserId(u_id, condition, pageNumberInt,
			pageSizeInt, 0);
		renderJson(innersendlist);
		break;
	    case "getInnerSendList1": // 内部发文列表
		condition = queryPara.get("condition");
		pageNumber = queryPara.get("pageNumber");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		Page<T_Inner_Send> innersendlist1 = T_Inner_Send.dao.getReadListByUserId(u_id, condition, pageNumberInt,
			pageSizeInt, 1);
		renderJson(innersendlist1);
		break;
	    case "InnerSendDetail":// 内部发文信息
		id = Integer.parseInt(queryPara.get("docid"));
		String sql1 = "select u_id,senddate,unit,docno,title,security,degree,fjid,unames,memo from t_inner_send where id="
			+ id;
		T_Inner_Send innersenddetail = T_Inner_Send.dao.findFirst(sql1);
		innersenddetail.set("u_id", T_User.getUserNameById(innersenddetail.get("u_id")));
		List<T_Attachment> list3 = T_Attachment.dao.getListByIds(innersenddetail.getStr("fjid"));
		innersenddetail.set("fjid", list3);
		if (!T_Inner_Send_Viewer.isReaded(u_id, id)) {
		    T_Inner_Send_Viewer isv = new T_Inner_Send_Viewer();
		    isv.set("doc_id", id);
		    isv.set("uname", T_User.getUserNameById(u_id));
		    isv.set("viewTime", DateUtils.getNowTime());
		    isv.set("u_id", u_id);
		    isv.save();
		}
		renderJson(innersenddetail);
		break;
	    case "getLeaveTodoList":// 获取请休假待办列表
		condition = queryPara.get("condition");
		pageNumber = queryPara.get("pageNumber");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		Page<T_Workflow> leavetodoList = T_Workflow.dao.getLeaveTodoList(u_id, condition, pageNumberInt,
			pageSizeInt);
		renderJson(leavetodoList);
		break;
	    case "getLeaveDoneList":// 获取请休假已办列表
		condition = queryPara.get("condition");
		pageNumber = queryPara.get("pageNumber");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		Page<T_Workflow> leavedoneList = T_Workflow.dao.getLeaveDoneList(u_id, condition, pageNumberInt,
			pageSizeInt);
		renderJson(leavedoneList);
		break;
	    case "getMyLeaveList":// 获取我的请休假列表
		condition = queryPara.get("condition");
		pageNumber = queryPara.get("pageNumber");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		Page<T_Leave_Approve> leavelist = T_Leave_Approve.dao.getListByUserId(u_id, condition, pageNumberInt,
			pageSizeInt);
		renderJson(leavelist);
		break;
	    case "LeaveDetail"://
		String leaveid = queryPara.get("docid");
		String leaveisdone = queryPara.get("isdone");
		leaveDetail(user, leaveid, leaveisdone);
		break;
	    case "schduleDate":
		String month = queryPara.get("month");
		try {
		    selectDate(u_id, month);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		break;
	    case "schduleEvent":
		String date = queryPara.get("date");
		schduleEvent(u_id, date);
		break;
	    case "scheduleDetail":
		id = Integer.parseInt(queryPara.get("id"));
		T_Myschedule schduleDetail = T_Myschedule.dao.getDetail(id);
		renderJson(schduleDetail);
		break;
	    case "getReceiveMailList":// 收件箱
		List<T_Mail> mailreceiveList = T_Mail.dao.getReceiveMailList(u_id);
		renderJson(mailreceiveList);
		break;
	    case "getRubbishList":// 垃圾箱
		List<T_Mail> mailrubbishList = T_Mail.dao.getRubbishMailList(u_id);
		renderJson(mailrubbishList);
		break;
	    case "getSendList":// 发件箱
		List<T_Mail> mailsendList = T_Mail.dao.getSendMailList(u_id);
		renderJson(mailsendList);
		break;
	    case "getDraftList":// 草稿箱
		List<T_Mail> maildraftList = T_Mail.dao.getDraftMailList(u_id);
		renderJson(maildraftList);
		break;
	    case "MailDetail":
		id = Integer.parseInt(queryPara.get("id"));
		T_Mail maildetail = T_Mail.dao.getMailDetail(id);
		List<T_Attachment> list = T_Attachment.dao.getListByIds(maildetail.getStr("fjid"));
		maildetail.set("fjid", list);
		String contenttemp = maildetail.getStr("content").replace("<br />", "").replace("<br/>", "");
		maildetail.set("content", contenttemp);
		renderJson(maildetail);
		break;
	    case "getNoteList":
		List<T_Note> noteList = T_Note.dao.getList(u_id);
		renderJson(noteList);
		break;
	    case "NoteDetail":
		id = Integer.parseInt(queryPara.get("id"));
		T_Note notedetail = T_Note.dao.findById(id);
		renderJson(notedetail);
		break;
	    case "getReceiveCadList":// 获取收文归档列表
		pageNumber = queryPara.get("pageNumber");
		pageSize = queryPara.get("pageSize");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		condition = queryPara.get("condition");
		Page<T_Workflow> page = T_Workflow.dao.getReceiveCabList(pageNumberInt, pageSizeInt, d_id, condition);
		renderJson(page);

		break;
	    case "getDocsendCadList":// 获取发文归档列表
		pageNumber = queryPara.get("pageNumber");
		pageSize = queryPara.get("pageSize");
		pageNumberInt = StrKit.isBlank(pageNumber) ? 1 : Integer.valueOf(pageNumber);
		pageSizeInt = StrKit.isBlank(pageSize) ? 10 : Integer.valueOf(pageSize);
		condition = queryPara.get("condition");
		Page<T_Workflow> receivecablist = T_Workflow.dao.getDocsendCabList(pageNumberInt, pageSizeInt, d_id,
			condition);
		renderJson(receivecablist);
		break;
	    case "getRemindInfo":
		// 新邮件数量
		// int mailboxnum=T_Mail.dao.getReceiveCount(u_id);
		// 新公告数量
		// int noticenum=T_Announce.dao.countUnread(u_id);
		// 督办数量
		// int superdonum=T_Doc_Receive.dao.countSuperdo(u_id);
		// 收文待阅数量
		// int readnum=T_Doc_Receive.dao.countTodo1(u_id);
		// 收文待办数量
		int docreceivenum = T_Workflow.dao.countTodo(u_id, "docreceive");
		// 发文待办数量
		int docsendnum = T_Workflow.dao.countTodo(u_id, "docsend");
		// 内部发文带阅数量
		int innersendnum = T_Inner_Send.dao.countTodo(u_id);
		// 请假待办数量
		int leavenum = T_Workflow.dao.countTodo(u_id, "leave");
		// 会议待办数量
		// int meetingnum=T_Workflow.dao.countTodo(u_id, "meeting");

		renderJson("{docreceivenum:" + docreceivenum + ",docsendnum:" + docsendnum + ",innersendnum:"
			+ innersendnum + ",leavenum:" + leavenum + "}");
		break;
	    case "updateVersion": // 查询升级版本
		int apptype = Integer.parseInt(queryPara.get("app_type"));
		T_App_Version newVersion = T_App_Version.dao.getLastVersion(apptype);
		renderJson(newVersion);
		break;
	    case "getManInDept":// 获取同一部门的科员(不去除已被选的)
		List<T_User> users = T_Doc_Receive.dao.getUsersNotInclude(d_id, "");
		renderJson(users);
		break;
	    default:
		renderJson("{status:0,msg:'接口写错啦！'}");
		break;
	    }
	} else {
	    renderJson("用户登录超时！");
	}

    }

    private void receive(T_User user, Map<String, String> para) {
	T_Received receiver = new T_Received();
	T_Workflow wf = T_Workflow.dao.findById(para.get("pid"));
	String docid = para.get("docid");
	receiver.set("doc_id", docid);
	receiver.set("receive_man", user.get("name"));
	receiver.set("u_id", user.get("id"));
	receiver.set("opiniontime", DateUtils.getNowTime());

	if ("1".equals(para.get("isreceive"))) {//

	} else {
	    wf.set("isreceive", "1");
	    wf.set("receiveitem", wf.getInt("itemid"));
	    wf.update();
	}
	T_Doc_Receive temp2 = T_Doc_Receive.dao.findById(docid);
	String dname = T_Department.dao.findFirst("select sname from t_department where id=" + user.getInt("d_id"))
		.getStr("sname");
	String receive = StrKit.isBlank(temp2.getStr("receive1")) ? dname : temp2.getStr("receive1") + "、" + dname;
	temp2.set("receive1", receive);
	temp2.update();
	try {
	    if (receiver.save()) {
		renderJson("签收操作成功，请填写办理情况！");
	    } else {
		renderJson("数据处理有误！");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /** 初审分办 */
    private void achieveDetail(T_User user, String docid) {
	int achieveid = Integer.parseInt(docid);
	int u_id = user.getInt("id");
	int d_id = user.getInt("d_id");

	ReceiveDetailJson json = new ReceiveDetailJson();
	// 公共常用意见
	List<T_Common_Detail> list = T_Common_Detail.getListByKey("opinion");
	// 个人常用意见
	List<T_Myopinion> mylist = T_Myopinion.dao.getListByUserID(u_id);
	// 常用意见
	String[] opinions = new String[list.size() + mylist.size() + 1];
	for (int i = 0; i < list.size(); i++) {
	    opinions[0] = "--请选择常用意见--";
	    opinions[i + 1] = list.get(i).getStr("name");
	}
	for (int i = 0; i < mylist.size(); i++) {
	    opinions[list.size() + 1 + i] = mylist.get(i).getStr("opinion");
	}
	// 获取流程发起环节
	T_Wprocess wp = T_Wprocess.dao.getProcessByFlow("docreceive");
	Integer itemid = wp.getInt("beginstep");
	T_Wactive active = T_Wactive.dao.findById(itemid);
	String opinionfield = active.getStr("opinionfield");

	int pid = 0;
	T_Workflow wf = null;
	T_Workitem wkit = null;

	T_Doc_Receive detail = T_Doc_Receive.dao.findByAchieveId(achieveid);
	if (null == detail) {
	    // 新建流程实例
	    wf = new T_Workflow();
	    wf.set("flowname", "收文办理");
	    wf.set("flowform", "docreceive");
	    wf.set("formname", "收文处理单");
	    wf.set("starter", u_id);
	    wf.set("startdept", d_id);
	    wf.set("starttime", DateUtils.getNowTime());
	    wf.set("isopen", "0");
	    wf.set("isend", "0");
	    wf.set("isnormalend", "0");
	    wf.set("ishold", "0");
	    wf.set("islock", "0");
	    wf.set("isnewdoc", "0");
	    wf.set("itemid", itemid);
	    wf.set("hastemplate", "0");
	    wf.set("templatename", "0");
	    wf.set("iscanreceive", "1");
	    wf.set("isreceive", "0");
	    wf.save();

	    pid = wf.getInt("id");
	    // 新建流程流传记录
	    wkit = new T_Workitem();
	    wkit.set("pid", pid);
	    wkit.set("user1", u_id);
	    wkit.set("user2", "");
	    wkit.set("itemid1", itemid);
	    wkit.set("itemid2", "");
	    wkit.set("begintime", DateUtils.getNowTime());
	    wkit.set("endtime", "");
	    wkit.set("operation", "");
	    wkit.set("opinionfield", "");
	    wkit.set("opinion", "");
	    wkit.set("opiniontime", "");
	    wkit.save();
	    // 复制收文登记信息止收文信息
	    T_Doc_Achieve dc = T_Doc_Achieve.dao.findById(achieveid);
	    detail = new T_Doc_Receive();
	    detail.set("u_id", u_id);
	    detail.set("d_id", d_id);
	    detail.set("achieveid", achieveid);
	    detail.set("receivedate", dc.getDate("receivedate"));
	    detail.set("unit", dc.getStr("unit"));
	    detail.set("docno", dc.getStr("docno"));
	    detail.set("count", dc.getInt("count"));
	    detail.set("title", dc.getStr("title"));
	    detail.set("security", dc.getStr("security"));
	    detail.set("degree", dc.getStr("degree"));
	    detail.set("fjid", dc.getStr("fjid"));
	    detail.set("type", dc.getStr("type"));
	    detail.set("doflag", "2");
	    detail.set("pid", pid);
	    detail.set("pstatus", "1");
	    detail.set("auditor", user.getStr("name"));
	    detail.save();
	} else {
	    pid = detail.getInt("pid");
	    wf = T_Workflow.dao.findById(pid);
	    wkit = T_Workitem.dao.getWorkitem(pid, itemid, String.valueOf(u_id));
	    if (null != wkit) {
		json.setOpinion(wkit.getStr("opinion"));
	    }
	}

	// 文件列表
	List<T_Attachment> fileList = new ArrayList<T_Attachment>();
	T_FJList ajson = null;
	List<T_FJList> ajsonlist = new ArrayList<T_FJList>();
	if (StrKit.notBlank(detail.getStr("fjid"))) {
	    fileList = T_Attachment.dao.getListByIds(detail.getStr("fjid"));
	    for (int i = 0; i < fileList.size(); i++) {
		ajson = new T_FJList();
		ajson.setId(fileList.get(i).getInt("id"));
		ajson.setName(fileList.get(i).getStr("name"));
		ajson.setUrl(fileList.get(i).getStr("url"));
		ajson.setSize(fileList.get(i).getStr("size"));
		ajsonlist.add(ajson);
	    }
	}

	WorkflowJson wfjson = new WorkflowJson();
	wfjson.setId(pid);
	wfjson.setItemid(itemid);
	wfjson.setIsreceive("0");
	wfjson.setIscanreceive("1");

	json.setDocid(String.valueOf(detail.getInt("id")));
	json.setActivename(active.getStr("name"));
	json.setAtype(active.getStr("atype"));
	json.setAuditor(detail.getStr("auditor"));
	json.setCount(String.valueOf(detail.getInt("count")));
	json.setDate(String.valueOf(detail.getDate("receivedate")));
	json.setDocno(detail.getStr("docno"));
	json.setFjlist(ajsonlist);
	json.setItemid(String.valueOf(itemid));
	json.setOpinions(opinions);
	json.setOpinion1(T_Workitem.dao.getOpinions1(pid, "opinion1"));
	json.setPositionId(user.getInt("pid"));

	json.setOpinionfield(opinionfield);
	json.setSecurity(detail.getStr("security"));
	json.setSuperman(detail.getStr("superman"));
	json.setTitle(detail.getStr("title"));
	json.setUnit(detail.getStr("unit"));
	json.setUname(T_User.getUserNameById(u_id));
	json.setWf(wfjson);
	renderJson(json);
    }

    /** 收文详情 */
    private void receiveDetail(T_User user, String docid, String isdone) {
	int achieveid = Integer.parseInt(docid);
	int u_id = user.getInt("id");
	ReceiveDetailJson json = new ReceiveDetailJson();
	T_Doc_Receive detail = T_Doc_Receive.dao.getReceiveDetail(achieveid);
	List<T_Common_Detail> list = T_Common_Detail.getListByKey("opinion");
	List<T_Myopinion> mylist = T_Myopinion.dao.getListByUserID(u_id);

	String[] opinions = new String[list.size() + mylist.size() + 1];
	for (int i = 0; i < list.size(); i++) {
	    opinions[0] = "--请选择常用意见--";
	    opinions[i + 1] = list.get(i).getStr("name");
	}
	for (int i = 0; i < mylist.size(); i++) {
	    opinions[list.size() + 1 + i] = mylist.get(i).getStr("opinion");
	}
	T_Workflow wf = T_Workflow.dao.findFirst("select * from t_workflow where id=" + detail.getInt("pid"));
	T_Workitem wkit = null;
	int pid = wf.getInt("id");
	Integer itemid = wf.getInt("itemid");
	T_Wactive active = T_Wactive.dao.findById(wf.getInt("itemid"));
	String opinionfield = active.getStr("opinionfield");
	String tmpuserid = ";" + u_id + ";";
	String tmptodoman = ";" + wf.getStr("todoman") + ";";
	if (isdone.equals("0")) {
	    if (wf.getStr("isopen").equals("0") && tmptodoman.indexOf(tmpuserid) < 0) {
		// 如果该文件未打开过且当前用户不是待办人 则取上一个环节记录，用去退回或取回
		wkit = T_Workitem.dao.getLastWorkitem(pid, itemid);
	    } else { // 查找当前环节记录，没有则创建
		wkit = T_Workitem.dao.getWorkitem(pid, itemid, String.valueOf(u_id));
		if (wkit == null) {
		    wkit = new T_Workitem();
		    wkit.set("pid", pid);
		    wkit.set("user1", u_id);
		    wkit.set("user2", "");
		    wkit.set("itemid1", itemid);
		    wkit.set("itemid2", "");
		    if (StrKit.notBlank(opinionfield)) {
			wkit.set("opinionfield", opinionfield);
		    }
		    wkit.set("begintime", DateUtils.getNowTime());
		    wkit.set("endtime", "");
		    wkit.set("operation", "");
		    wkit.set("opinion", "");
		    wkit.set("opiniontime", "");
		    wkit.save();
		    if (u_id == 93) {//
			wf.set("iscanreceive", "1");
		    }
		    wf.set("isopen", "1");
		    wf.set("editor", u_id);
		    wf.update();
		} else {
		    wkit = T_Workitem.dao.getWorkitem(pid, itemid, String.valueOf(u_id));
		}
	    }
	    if (null != wkit) {
		json.setOpinion(wkit.getStr("opinion"));
	    }
	}

	List<T_Attachment> fileList = new ArrayList<T_Attachment>();
	T_FJList ajson = null;
	List<T_FJList> ajsonlist = new ArrayList<T_FJList>();
	if (StrKit.notBlank(detail.getStr("fjid"))) {
	    fileList = T_Attachment.dao.getListByIds(detail.getStr("fjid"));
	    for (int i = 0; i < fileList.size(); i++) {
		ajson = new T_FJList();
		ajson.setId(fileList.get(i).getInt("id"));
		ajson.setName(fileList.get(i).getStr("name"));
		ajson.setUrl(fileList.get(i).getStr("url"));
		ajson.setSize(fileList.get(i).getStr("size"));
		ajsonlist.add(ajson);
	    }

	}
	String type;
	String doflag = detail.getStr("doflag");
	if (doflag.equals("1")) {
	    type = "一般阅知";
	} else {
	    type = "普通收文";
	}
	String mustreceive = "0";// 必须签收,1
	String mustsubmit = "0";// 必须提交
	if (itemid == 107) {
	    if (T_Received.dao.hadReceive(u_id, detail.getInt("id"))) {
		mustsubmit = "1";
	    }
	    // 如果用户是在承办环节可指派操作的科长、站长或中心主任，去指派意见
	    if (user.getInt("pid") == 12 || user.getInt("pid") == 14 || user.getInt("pid") == 16) {
		json.setTempopinion1(T_Workitem.dao.getOpinions(pid, itemid, String.valueOf(u_id), "opinion1"));
	    }
	}

	WorkflowJson wfjson = new WorkflowJson();
	wfjson.setId(wf.getInt("id"));
	wfjson.setItemid(itemid);
	wfjson.setIsreceive(wf.getStr("isreceive"));
	wfjson.setIscanreceive(wf.getStr("iscanreceive"));
	json.setDocid(String.valueOf(detail.getInt("id")));
	json.setMustreceive(mustreceive);
	json.setMustsubmit(mustsubmit);
	json.setActivename(active.getStr("name"));
	json.setAtype(active.getStr("atype"));
	json.setBacklaststep(active.getStr("backlaststep"));
	json.setAuditor(detail.getStr("auditor"));
	json.setCount(String.valueOf(detail.getInt("count")));
	json.setDate(String.valueOf(detail.getDate("receivedate")));
	json.setDocno(detail.getStr("docno"));
	json.setFjlist(ajsonlist);
	json.setItemid(String.valueOf(itemid));
	json.setOpinions(opinions);
	json.setOpinion1(T_Workitem.dao.getOpinions1(pid, "opinion1"));
	json.setOpinion2(T_Workitem.dao.getOpinions1(pid, "opinion2"));
	json.setOpinion3(T_Received.dao.getOpinion(detail.get("id"), u_id));
	json.setPositionId(user.getInt("pid"));

	List<T_Received> tempReceiverList = T_Received.dao.findUsersByDoc(docid);
	ReceiverJson receiver;
	List<ReceiverJson> receiverList = new ArrayList<>();
	for (int i = 0; i < tempReceiverList.size(); i++) {
	    T_Received temp = tempReceiverList.get(i);
	    receiver = new ReceiverJson();
	    receiver.setName(temp.getStr("receive_name"));
	    receiver.setOpinion(temp.getStr("opinion"));
	    receiver.setReceiveTime(temp.getDate("receive_time"));
	    receiverList.add(receiver);
	}
	json.setReceiverList(receiverList);// 签收人列表

	json.setTempopinion3(T_Received.dao.getOpinionButme(detail.get("id"), u_id));
	if (StrKit.notBlank(opinionfield)) {
	    switch (opinionfield) {
	    case "opinion1":
		json.setTempopinion1(T_Workitem.dao.getOpinions(pid, itemid, String.valueOf(u_id), "opinion1"));
		break;
	    case "opinion2":
		json.setTempopinion2(T_Workitem.dao.getOpinions(pid, itemid, String.valueOf(u_id), "opinion2"));
		break;
	    }
	}
	json.setOpinionfield(opinionfield);
	json.setReceiver(T_User.findUsernames(String.valueOf(detail.getInt("receiver"))));
	json.setSecurity(detail.getStr("security"));
	json.setSuperman(detail.getStr("superman"));
	json.setTitle(detail.getStr("title"));
	json.setType(type);
	json.setUnit(detail.getStr("unit"));
	json.setUname(T_User.getUserNameById(u_id));
	json.setWf(wfjson);
	renderJson(json);
    }

    /** 发文详情 */
    protected void docsendDetail(int u_id, String docid, String isdone) {
	T_Doc_Send docsendDetail = T_Doc_Send.findAllById(docid);

	List<T_Attachment> list1 = T_Attachment.dao.getListByIds(docsendDetail.getStr("fjid"));
	List<T_Workitem> opinion1list = T_Workitem.dao.getOpinion1(docsendDetail.getInt("pid"), "opinion1");
	T_Workflow wf = T_Workflow.dao.findFirst("select * from t_workflow where id=" + docsendDetail.getInt("pid"));
	Integer itemid = wf.getInt("itemid");
	T_Workitem wkit = null;
	T_Wactive active = T_Wactive.dao.findById(itemid);
	String opinionfield = active.getStr("opinionfield");
	// T_Workitem
	// item=T_Workitem.dao.getWorkitem(wf.getInt("id"),wf.getInt("itemid"),String.valueOf(u_id));
	if (isdone.equals("0")) {
	    if (wf.getStr("isopen").equals("0")) {
		wkit = new T_Workitem();
		wkit.set("pid", wf.getInt("id"));
		wkit.set("user1", u_id);
		wkit.set("user2", "");
		wkit.set("itemid1", itemid);
		wkit.set("itemid2", "");
		wkit.set("begintime", DateUtils.getNowTime());
		wkit.set("endtime", "");
		wkit.set("operation", "");
		if (StrKit.notBlank(opinionfield)) {
		    wkit.set("opinionfield", opinionfield);
		    docsendDetail.set("opinion2", opinionfield); // 意见域
								 // 用于判断填写意见按钮是否可见
		}
		wkit.set("opinion", "");
		wkit.set("opiniontime", "");
		wkit.save();
		wf.set("isopen", "1");
		wf.set("editor", u_id);
		wf.update();

	    } else {
		wkit = T_Workitem.dao.getWorkitem(wf.getInt("id"), itemid, String.valueOf(u_id));
		docsendDetail.set("opinion2", opinionfield); // 意见域
							     // 用于判断填写意见按钮是否可见
		/*
		 * if (StrKit.notBlank(opinionfield)) { wkit.set("opinionfield",
		 * opinionfield);
		 * 
		 * wkit.update(); }
		 */
	    }
	    if (StrKit.notBlank(wkit.getStr("opinion")) && opinionfield.equals("opinion1")) {
		docsendDetail.set("opinion3", 1);// 用于判断提交按钮是否可见
	    }
	    if (StrKit.isBlank(opinionfield)) {
		docsendDetail.set("opinion3", 1);// 是否已经填写意见 用于判断提交按钮是否可见
	    }
	}
	docsendDetail.set("opinion5", itemid);/// 当前环节id
	docsendDetail.set("fjid", list1);
	docsendDetail.set("opinion1", opinion1list);
	renderJson(docsendDetail);
    }

    /** 请休假详情 */
    private void leaveDetail(T_User user, String docid, String isdone) {
	try {
	    int id = Integer.parseInt(docid);
	    int u_id = user.getInt("id");
	    LeaveDetailJson json = new LeaveDetailJson();
	    WorkflowJson wfjson = new WorkflowJson();
	    T_Leave_Approve detail = null;
	    List<T_Common_Detail> list = T_Common_Detail.getListByKey("opinion");
	    List<T_Myopinion> mylist = T_Myopinion.dao.getListByUserID(u_id);
	    String[] opinions = new String[list.size() + mylist.size() + 1];
	    for (int i = 0; i < list.size(); i++) {
		opinions[0] = "--请选择常用意见--";
		opinions[i + 1] = list.get(i).getStr("name");
	    }
	    for (int i = 0; i < mylist.size(); i++) {
		opinions[list.size() + 1 + i] = mylist.get(i).getStr("opinion");
	    }
	    if (id == 0) {
		detail = new T_Leave_Approve();
	    } else {
		detail = T_Leave_Approve.dao.getLeaveDetail(id);
		T_Workflow wf = T_Workflow.dao.findFirst("select * from t_workflow where id=" + detail.getInt("pid"));
		T_Workitem wkit = null;
		int pid = wf.getInt("id");
		Integer itemid = wf.getInt("itemid");
		T_Wactive active = T_Wactive.dao.findById(wf.getInt("itemid"));
		String opinionfield = active.getStr("opinionfield");
		String tmpuserid = ";" + u_id + ";";
		String tmptodoman = ";" + wf.getStr("todoman") + ";";
		if (isdone.equals("0")) {
		    if (wf.getStr("isopen").equals("0") && tmptodoman.indexOf(tmpuserid) < 0) {
			// 如果该文件未打开过且当前用户不是待办人 则取上一个环节记录，用去退回或取回
			wkit = T_Workitem.dao.getLastWorkitem(pid, itemid);
		    } else { // 查找当前环节记录，没有则创建
			wkit = T_Workitem.dao.getWorkitem(pid, itemid, String.valueOf(u_id));
			if (wkit == null) {
			    wkit = new T_Workitem();
			    wkit.set("pid", pid);
			    wkit.set("user1", u_id);
			    wkit.set("user2", "");
			    wkit.set("itemid1", itemid);
			    wkit.set("itemid2", "");
			    if (StrKit.notBlank(opinionfield)) {
				wkit.set("opinionfield", opinionfield);
			    }
			    wkit.set("begintime", DateUtils.getNowTime());
			    wkit.set("endtime", "");
			    wkit.set("operation", "");
			    wkit.set("opinion", "");
			    wkit.set("opiniontime", "");
			    wkit.save();

			    wf.set("isopen", "1");
			    wf.set("editor", u_id);
			    wf.update();
			} else {
			    wkit = T_Workitem.dao.getWorkitem(pid, itemid, String.valueOf(u_id));
			}
		    }
		    if (null != wkit) {
			json.setOpinion(wkit.getStr("opinion"));
		    }
		}
		List<T_Attachment> fileList = new ArrayList<T_Attachment>();
		T_FJList ajson = null;
		List<T_FJList> ajsonlist = new ArrayList<T_FJList>();
		if (StrKit.notBlank(detail.getStr("fjid"))) {
		    fileList = T_Attachment.dao.getListByIds(detail.getStr("fjid"));
		    for (int i = 0; i < fileList.size(); i++) {
			ajson = new T_FJList();
			ajson.setId(fileList.get(i).getInt("id"));
			ajson.setName(fileList.get(i).getStr("name"));
			ajson.setUrl(fileList.get(i).getStr("url"));
			ajson.setSize(fileList.get(i).getStr("size"));
			ajsonlist.add(ajson);
		    }

		}
		wfjson.setId(wf.getInt("id"));
		wfjson.setItemid(itemid);
		wfjson.setIsreceive(wf.getStr("isreceive"));
		wfjson.setIscanreceive(wf.getStr("iscanreceive"));
		wfjson.setId(wf.getInt("id"));
		json.setId(String.valueOf(detail.getInt("id")));
		json.setD_id(String.valueOf(detail.getInt("d_id")));
		
		json.setApprovedate(DateUtils.getDateByDate(detail.getDate("approvedate")));
		json.setBegindate(DateUtils.getDateByDate(detail.getDate("begindate")));
		json.setEnddate(DateUtils.getDateByDate(detail.getDate("enddate")));
		json.setDayt(String.valueOf(detail.getDouble("dayt")));
		
		json.setMarried(detail.getStr("married"));
		T_User applyer = T_User.dao.findById(detail.getInt("u_id"));
		json.setReason(detail.getStr("reason"));
		json.setPositionId(applyer.getInt("pid"));
		json.setActivename(active.getStr("name"));
		json.setAtype(active.getStr("atype"));
		json.setBacklaststep(active.getStr("backlaststep"));
		json.setFjlist(ajsonlist);
		json.setItemid(String.valueOf(itemid));
		json.setOpinions(opinions);
		json.setOpinion1(T_Workitem.dao.getOpinions1(pid, "opinion1"));
		json.setOpinion2(T_Workitem.dao.getOpinions1(pid, "opinion2"));
		json.setPositionId(user.getInt("pid"));
		
		if (StrKit.notBlank(opinionfield)) {
		    switch (opinionfield) {
		    case "opinion1":
			json.setTempopinion1(T_Workitem.dao.getOpinions(pid, itemid, String.valueOf(u_id), "opinion1"));
			break;
		    case "opinion2":
			json.setTempopinion2(T_Workitem.dao.getOpinions(pid, itemid, String.valueOf(u_id), "opinion2"));
			break;
		    }
		}
		json.setOpinionfield(opinionfield);
		json.setType(detail.getStr("type"));
		json.setUname(applyer.getStr("name"));
		json.setDname((String)T_Department.dao.findValueById("sname", detail.getInt("d_id")));
	    }
	    json.setWf(wfjson);
	    renderJson(json);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /*
     * protected void saveAndgetReceiveDetail(int u_id, String opinion, int pid)
     * { // 保存意见并获取收文详细信息 // T_Doc_Receive ReceiveDetail = //
     * T_Doc_Receive.dao.getReceiveDetail(docid); T_Doc_Receive ReceiveDetail =
     * T_Doc_Receive.dao.findByPid(pid); T_Workflow wf =
     * T_Workflow.dao.findFirst("select * from t_workflow where id=" + pid);
     * T_Wactive active = T_Wactive.dao.findById(wf.getInt("itemid")); String
     * opinionfield = active.getStr("opinionfield"); T_Workitem item =
     * T_Workitem.dao.getWorkitem(wf.getInt("id"), wf.getInt("itemid"),
     * String.valueOf(u_id)); int itemid = wf.getInt("itemid");
     * 
     * if (StrKit.notBlank(opinionfield)) { item.set("opinion", opinion);
     * item.set("opinionfield", "opinion1"); item.set("operation", "Fasong");
     * item.set("opiniontime", DateUtils.getNowTime()); item.update();
     * ReceiveDetail.set("opinion5", itemid);
     * 
     * 
     * List<T_Attachment> list1 =
     * T_Attachment.dao.getListByIds(ReceiveDetail.getStr("fjid"));
     * List<T_Workitem> opinion1list =
     * T_Workitem.dao.getOpinion1(ReceiveDetail.getInt("pid"), "opinion1");
     * List<T_Workitem> opinion2list =
     * T_Workitem.dao.getOpinion1(ReceiveDetail.getInt("pid"), "opinion2");
     * 
     * ReceiveDetail.set("fjid", list1); ReceiveDetail.set("opinion1",
     * opinion1list); ReceiveDetail.set("opinion2", opinion2list); if
     * (StrKit.notBlank(item.getStr("opinion"))&&opinionfield.equals("opinion1")
     * ) { ReceiveDetail.set("opinion3", 1);//是否已经填写意见 用于判断提交按钮是否可见 }
     * ReceiveDetail.set("opinion6", opinionfield);//意见域 用于判断填写意见按钮是否可见 } else {
     * ReceiveDetail.set("opinion4", "您不能填写意见!");//做了填写按钮的可见性后可删
     * ReceiveDetail.set("opinion5", itemid);
     * 
     * } renderJson(ReceiveDetail);
     * 
     * // List<T_Attachment> list1 = //
     * T_Attachment.dao.listInIds(ReceiveDetail.getStr("fjid"));
     * 
     * }
     */

    public void saveAndgetSendDetail(int u_id, String opinion, int pid) {
	T_Doc_Send sendDetail = T_Doc_Send.findAllByPid(pid);
	T_Workflow wf = T_Workflow.dao.findFirst("select * from t_workflow where id=" + pid);
	T_Wactive active = T_Wactive.dao.findById(wf.getInt("itemid"));
	String opinionfield = active.getStr("opinionfield");
	T_Workitem item = T_Workitem.dao.getWorkitem(wf.getInt("id"), wf.getInt("itemid"), String.valueOf(u_id));
	int itemid = wf.getInt("itemid");
	if (StrKit.notBlank(opinionfield)) {
	    item.set("opinion", opinion);
	    item.set("opiniontime", DateUtils.getNowTime());
	    item.update();
	    sendDetail.set("opinion5", itemid);
	    sendDetail.update();
	    List<T_Attachment> list1 = T_Attachment.dao.getListByIds(sendDetail.getStr("fjid"));
	    List<T_Workitem> opinion1list = T_Workitem.dao.getOpinion1(sendDetail.getInt("pid"), "opinion1");
	    sendDetail.set("fjid", list1);
	    sendDetail.set("opinion1", opinion1list);
	    sendDetail.set("opinion5", itemid);
	    sendDetail.set("opinion2", opinionfield);
	    if (StrKit.notBlank(item.getStr("opinion")) && opinionfield.equals("opinion1")) {
		sendDetail.set("opinion3", 1);// 用于判断提交按钮是否可见,1可见，0不可见
	    }
	} else {
	    sendDetail.set("opinion4", "您不能填写意见!");

	}
	renderJson(sendDetail);

    }

    protected void selectman(int curitemid, int pid, int d_id, int positionid, String doc) { // 选择执行人
	SelectStep selectstep = null;
	// T_Workitem item = T_Workitem.dao.findById(id);
	// int afrom = Integer.parseInt(item.getStr("itemid1"));
	// 获取该流程设置的特例环节
	MoblieWorkflow workflow = new MoblieWorkflow();

	int spidnum = 0;
	String spids = T_Wtrans_Sp.dao.getNextSteps(String.valueOf(curitemid), d_id, positionid);
	if (StrKit.notBlank(spids)) {
	    String[] spid = spids.split(",");
	    spidnum = spid.length;// 获取符合条件的特例流转数量
	    if (spidnum > 1) {
		selectstep = workflow.selectSpecStep(spids, d_id, positionid);
	    } else {
		int ato = Integer.parseInt(spids);
		selectstep = workflow.selectman(ato, pid, d_id);

		// bc.setItemid(Integer.parseInt(spids));
	    }

	} else {
	    int nextstepsnum;

	    if (StrKit.isBlank(doc) ? false : "receive".equals(doc)) {// 判断是否为收文的流程
		T_Workflow wf = T_Workflow.dao.findById(pid);
		int itemid = wf.getInt("itemid");
		nextstepsnum = T_Wtrans.dao.nextActives1(itemid, wf.getStr("isreceive"));
		if (nextstepsnum > 1) {
		    selectstep = workflow.selectStep1(String.valueOf(wf.getInt("itemid")), wf.getStr("iscanreceive"),
			    wf.getStr("isreceive"));
		} else if (nextstepsnum == 1) {

		    int ato = Integer.parseInt(T_Wtrans.dao.getNextStep1(itemid, wf.getStr("isreceive")));
		    selectstep = workflow.selectman(ato, pid, d_id);

		    // bc.setItemid(Integer.parseInt(trans.getStr("ato")));
		}
	    } else {
		nextstepsnum = T_Wtrans.dao.nextActives(curitemid);
		if (nextstepsnum > 1) {
		    selectstep = workflow.selectStep(String.valueOf(curitemid), d_id, positionid);
		} else if (nextstepsnum == 1) {
		    T_Wtrans trans = T_Wtrans.dao.findFirst("select ato from t_wtrans where afrom=" + curitemid);
		    int ato = Integer.parseInt(trans.getStr("ato"));
		    selectstep = workflow.selectman(ato, pid, d_id);

		    // bc.setItemid(Integer.parseInt(trans.getStr("ato")));
		} else {
		    selectstep = new SelectStep();
		    selectstep.setType("9");// 无下一处理人，请联系管理员
		}
	    }

	}
	renderJson(selectstep);
    }

    public void receiveRecord(int u_id, int id) { // 收文流转记录
	List<T_Workitem> list = T_Workitem.dao.find(
		"select a.*,b.name,b.memo from t_workitem a  LEFT JOIN t_user b on a.user1=b.id where a.pid=?", id);
	for (int i = 0; i < list.size(); i++) {
	    if (StrKit.notBlank(list.get(i).getStr("user2"))) {
		list.get(i).set("user2", T_Doc_Achieve.dao.getUserNameByIds(list.get(i).getStr("user2")));
	    }

	    list.get(i).set("itemid1",
		    T_Wactive.dao.findValueById("name", Integer.parseInt(list.get(i).getStr("itemid1"))));// 环节名称
	    if (StrKit.notBlank(list.get(i).getStr("operation"))) {
		list.get(i).set("operation", T_Woperation.dao.getNameByCode(list.get(i).getStr("operation")));// 处理操作
	    }
	}

	renderJson(list);
    }

    protected void selectDate(int u_id, String month) throws ParseException {
	// List<T_Myschedule> myschedule;
	// myschedule=T_Myschedule.dao.getMyschedule(u_id, month);
	String dates = T_Myschedule.dao.getMyschedule(u_id, month);
	renderJson(dates);
    }

    protected void schduleEvent(int u_id, String date) {
	List<T_Myschedule> myschedule;
	myschedule = T_Myschedule.dao.getSchduleEvent(u_id, date);
	renderJson(myschedule);
    }

    protected String Save(Map<String, String> modelProperty, int u_id, int d_id) {
	Instance ins = new Instance();
	String msgstr = "";
	try {
	    // 流程流转处理
	    int pid = Integer.valueOf(modelProperty.get("pid"));
	    T_Workflow wf = T_Workflow.dao.findById(pid);
	    String userIdStr = String.valueOf(u_id);
	    String userDeptStr = String.valueOf(d_id);
	    String itemid = modelProperty.get("nextitemid"); // 选中环节的id
	    String nexttodoman = modelProperty.get("nexttodoman"); // 选中的处理人的id
	    ins.setCuruser(userIdStr);
	    ins.setCurdept(userDeptStr);
	    ins.setNextitemid(itemid);
	    ins.setNexttodoman(nexttodoman);

	    ins.setOperation(modelProperty.get("operation"));
	    ins.setOpinionfield(modelProperty.get("opinionfield"));
	    ins.setOpinion(modelProperty.get("opinion"));

	    T_Wactive wa = T_Wactive.dao.findById(modelProperty.get("nextitemid"));
	    ins.setWActive(wa);

	    if (ins.getOperation().equals("TuiHuiShangBu")) {
		wf.set("title", modelProperty.get("flowname") + " [回退]");
	    } else if (ins.getOperation().equals("WanCheng")) {
		wf.set("title", modelProperty.get("flowname") + " [完成]");
	    } else {
		wf.set("title", modelProperty.get("flowname"));
	    }
	    ins.setWorkflow(wf);
	    Transaction trans = new Transaction();
	    WorkflowReturn wfr = null;
	    if (ins.getOperation().equals("ZhiPai")) {
		wfr = trans.point(ins);
	    } else {
		wfr = trans.Trans(ins);
	    }

	    String name = "";
	    boolean value = true;
	    switch (modelProperty.get("flow")) {
	    case "receive":// 收文
		name = "收文";
		T_Doc_Receive receive = T_Doc_Receive.dao.findByPid(pid);
		if (StrKit.notBlank(modelProperty.get("opinionfield"))
			&& StrKit.notBlank(modelProperty.get("opinion"))) {
		    receive.set(modelProperty.get("opinionfield"), modelProperty.get("opinion"));
		}
		if (ins.getOperation().equals("WanCheng")) { // 审批完成
		    receive.set("pstatus", "2");
		} else if (ins.getOperation().equals("FaSong") || ins.getOperation().equals("FaSongdoc")
			|| ins.getOperation().equals("ZhiPai")) { // 审批中
		    receive.set("pstatus", "1");
		} else if (ins.getOperation().equals("TuiHuiNiGao")) { // 退回申请人
		    receive.set("pstatus", "0");
		} else if (ins.getOperation().equals("ZhongZhi")) { // 已终止
		    receive.set("pstatus", "3");
		}
		value = receive.update();
		break;
	    case "send":// 发文
		name = "发文";
		T_Doc_Send send = T_Doc_Send.findByPid(pid);
		if (ins.getOperation().equals("WanCheng")) { // 审批完成
		    send.set("pstatus", "2");
		} else if (ins.getOperation().equals("FaSong")) { // 审批中
		    send.set("pstatus", "1");
		} else if (ins.getOperation().equals("TuiHuiNiGao")) { // 退回申请人
		    send.set("pstatus", "0");
		} else if (ins.getOperation().equals("ZhongZhi")) { // 已终止
		    send.set("pstatus", "3");
		}
		value = send.update();
		break;
	    default:
		value = false;
		break;
	    }

	    if (wfr.getMessage().equals("wfr1")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！";
	    } else if (wfr.getMessage().equals("wfr2")) {
		msgstr = "【" + T_Woperation.dao.getNameByCode(ins.getOperation()) + "】" + "操作成功！ \n " + name
			+ "审批单已流转到：" + T_User.findUsernames(wfr.getTodoman().replace(';', ','));
	    }
	    if (value) {
		return msgstr;
	    } else {
		return "出错啦！";
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return "出错啦！";
	}
    }

    protected String toNext(Map<String, String> queryPara, int u_id, int d_id) {

	String msgstr = "";
	String itemid = queryPara.get("itemid");// 选中环节的id
	String userids = queryPara.get("userids");// 选中的处理人的id
	String operation = queryPara.get("operation");// 所做操作
	String opinionfield = queryPara.get("opinionfield");// 意见域
	int pid = Integer.parseInt(queryPara.get("pid"));
	T_Workitem wkit = new T_Workitem();
	T_Workflow wf = T_Workflow.dao.findById(pid);
	int curitemid = wf.getInt("itemid");
	// T_Workitem witem = T_Workitem.dao.findById(curitemid);
	T_Wactive wa = T_Wactive.dao.findById(curitemid);

	if (wa.getStr("amount").equals("3")) {
	    wkit = T_Workitem.dao.getWorkitem(pid, curitemid);
	} else {
	    wkit = T_Workitem.dao.getWorkitem(pid, curitemid, String.valueOf(u_id));
	}
	if (wkit != null) {
	    wkit.set("user1", u_id);
	    wkit.set("itemid2", itemid);
	    wkit.set("user2", userids);
	    wkit.set("endtime", DateUtils.getNowTime());
	    wkit.set("operation", operation);
	    wkit.set("opinionfield", opinionfield);
	    // wkit.set("opinion", opinion);
	    // wkit.set("opiniontime", opiniontime);
	    wkit.update();

	    wf.set("itemid", itemid);
	    wf.set("reader", arrayAddstr(wf.getStr("reader"), String.valueOf(u_id)));
	    wf.set("doneuser", "");

	    if (wa.getStr("amount").equals("2") && !wa.getStr("issequence").equals("0")) {
		String tmpuser[] = wf.getStr("todousers").split(";");
		userids = tmpuser[0];
	    }
	    // 传阅会签时，去掉已经签署意见的领导
	    if (wa.getStr("handround").equals("1")) {
		String strsql = "select * from t_workitem where pid=" + pid
			+ " and operation='FaSong' and opinion is not null and endtime <> '' and opiniontime <> '' order by id";
		List<T_Workitem> items = T_Workitem.dao.find(strsql);
		for (T_Workitem item : items) {
		    String uid = item.getStr("user1");
		    userids = arrayDelstr(userids, uid);
		}
	    }
	    String todoman = userids.replace(",", ";");
	    wf.set("todoman", todoman);
	    wf.set("todousers", todoman);
	    wf.set("isopen", "0");
	    wf.set("editor", "");
	    wf.update();
	    T_Doc_Receive temp = T_Doc_Receive.dao.findByPid(pid);
	    String atype = T_Wactive.dao.findById(itemid).getStr("atype");
	    if (atype.equals("3")) {// 如果下一环节是办结，则保存处理人的部门作为承办科室
		int did = T_User.dao.findFirst("select d_id from t_user where id=" + u_id).getInt("d_id");
		temp.set("receive1", did);
	    }
	    temp.update();
	    msgstr = "【" + T_Woperation.dao.getNameByCode(operation) + "】" + "操作成功！文件处理单已流转到："
		    + T_User.findUsernames(userids.replace(';', ','));
	} else {
	    msgstr = "出错啦！";
	}

	return msgstr;
    }

    public List<T_Department> usertree() {
	List<T_Department> depts = T_Department.dao.find("select id as d_id,sname,fname from t_department where 1=1 ");
	List<T_User> users = T_User.dao.find("select id,name,d_id from t_user where 1=1 ");

	for (int i = 0; i < depts.size(); i++) {
	    List<T_User> temp = new ArrayList<>();
	    for (int j = 0; j < users.size(); j++) {
		int d_id = users.get(j).getInt("d_id");
		int did = depts.get(i).getInt("d_id");
		if (d_id == did) {
		    T_User user = users.get(j);
		    temp.add(user);
		}

	    }
	    depts.get(i).set("fname", temp);
	}

	return depts;
    }

    private iMsgServer2000 MsgObj;

    public void getFile(int pid) {
	MsgObj = new iMsgServer2000();
	T_Doc_File file = T_Doc_File.getDocFileByRecordId(String.valueOf(pid));
	String filename = pid + ".doc";

	byte[] mFileBody;
	byte[] mFile;

	if (file != null) {
	    mFileBody = file.getBytes("filebody");
	    mFile = MsgObj.ToDocument(mFileBody);
	    String path = filePath + "/File/Tempfile/";

	    try {
		File oldfile = new File(path + "file.doc");
		File newfile = new File(path + filename);
		FileUtils.copyFile(oldfile, newfile);
		writeFile(mFile, path + filename);
		renderJson("/File/Tempfile/" + filename);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} else {
	    renderJson("没有该文件！");
	}

    }

    public String arrayAddstr(String s, String s1) {
	if (s == null)
	    s = "";
	String[] str = s.split(";");
	boolean flag = true;
	for (int i = 0; i < str.length; i++) {
	    if (str[i].equals(s1)) {
		flag = false;
		break;
	    }
	}
	if (flag) {
	    if (s.equals(""))
		s = s1;
	    else
		s = s + ";" + s1;
	}
	return s;
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

    @Override
    protected void processInSaveMsg(InSaveMsg msg) {
	 String json = JsonKit.toJson(msg);
	 System.out.println(json);
	String key = msg.getKey();
	Map<String, T_User> userMap = bc.getUserMap();
	T_User user = userMap.get(key);
	if (user != null) {
	    int u_id = user.getInt("id");
	    String modelName = msg.getModelName();
	    Map<String, String> modelProperty = msg.getModelProperty();
	    String id = modelProperty.get("id");
	    switch (modelName) {// 通过modelname判断使用哪个model保存
	    case "t_Note":
		T_Note note;
		if (id.equals("0")) {// id=0则为新添事件
		    note = new T_Note();
		} else {
		    note = T_Note.dao.findById(id);
		}
		for (Map.Entry<String, String> entry : modelProperty.entrySet()) {// 遍历已修改的数据
		    note.set(entry.getKey(), entry.getValue());
		}
		note.set("u_id", u_id);
		if (id.equals("0")) {
		    note.remove("id");
		    if (note.save()) {
			renderJson("保存成功！");
		    } else {
			renderJson("数据处理有问题！");
		    }
		} else {
		    if (note.update()) {

			renderJson("保存成功！");
		    } else {
			renderJson("数据处理有问题！");
		    }
		}
		break;
	    case "t_Myschedule":
		T_Myschedule myschedule;
		if (id.equals("0")) {
		    myschedule = new T_Myschedule();
		    myschedule.set("u_id", u_id);

		    for (Map.Entry<String, String> entry : modelProperty.entrySet()) {
			myschedule.set(entry.getKey(), entry.getValue());
		    }
		    myschedule.remove("id");
		    if (myschedule.save()) {
			T_Myschedule temp = T_Myschedule.dao.findById(myschedule.getInt("id"));
			if (null != temp.getInt("remind")) {
			    scheduleFormatToSave(temp);// 整理格式保存到数据库
			}
			renderJson("保存成功！");
		    } else {
			renderJson("数据处理有问题！");
		    }
		} else {
		    myschedule = T_Myschedule.dao.findById(id);
		    if (myschedule.getInt("u_id") != u_id) {
			renderJson("只有本人才能执行该操作！");
		    } else {
			for (Map.Entry<String, String> entry : modelProperty.entrySet()) {
			    myschedule.set(entry.getKey(), entry.getValue());
			}
			if (myschedule.update()) {
			    T_Myschedule temp = T_Myschedule.dao.findById(id);
			    if (StrKit.notBlank(temp.getStr("type"))) {
				List<T_ScheduleType> list = T_ScheduleType.dao.getType(temp.getInt("u_id"),
					temp.getStr("type"));
				if (list.size() == 0) {
				    T_ScheduleType scheduletype = new T_ScheduleType();
				    scheduletype.set("u_id", temp.getInt("u_id"));
				    scheduletype.set("type", temp.getStr("type"));
				    scheduletype.save();
				}
			    }

			    T_MyscheduleMessage messagemodel = T_MyscheduleMessage.dao.getMid(temp.getInt("id"));
			    if (null != messagemodel) {
				messagemodel.delete();
			    }
			    if (null != temp.getInt("remind")) {
				scheduleFormatToSave(temp);// 整理格式保存到数据库
			    }
			    renderJson("保存成功！");
			} else {
			    renderJson("数据处理有问题！");
			}
		    }
		}

		break;
	    case "t_Mail":
		T_Mail mail;
		if (id.equals("0")) {
		    mail = new T_Mail();
		    mail.set("fromId", u_id);
		    for (Map.Entry<String, String> entry : modelProperty.entrySet()) {
			mail.set(entry.getKey(), entry.getValue());
		    }
		    String receiver = modelProperty.get("receiverId");
		    String copyer = modelProperty.get("copyerId");
		    receiver = T_User.findUsernames(receiver.replace(';', ','));
		    if (StrKit.notBlank(copyer)) {
			copyer = T_User.findUsernames(copyer.replace(';', ','));
		    }

		    String[] toIds = modelProperty.get("receiverId").split(",");
		    toIds = ArrayUtils.removeDuplicate(toIds);

		    mail.set("receiver", receiver);
		    mail.set("copyer", copyer);
		    mail.remove("id");
		    if (mail.save()) {
			renderJson("保存成功！");
		    } else {
			renderJson("数据处理有问题！");
		    }
		    T_Mail temp = T_Mail.dao.findById(mail.getInt("id"));
		    if (temp.getInt("boxId") == 2) {
			for (String s : toIds) {
			    if (StrKit.notBlank(s)) {
				temp.set("boxId", 1);
				temp.set("oboxId", 1);
				temp.set("toId", Integer.parseInt(s));
				// model.set("id", null);
				temp.remove("id");
				temp.set("sendtime", DateUtils.getNowTime());
				temp.save();
			    }
			}
		    }
		} else {
		    mail = T_Mail.dao.findById(id);

		    for (Map.Entry<String, String> entry : modelProperty.entrySet()) {
			mail.set(entry.getKey(), entry.getValue());
		    }
		    String receiver = modelProperty.get("receiverId");
		    String copyer = modelProperty.get("copyer");
		    receiver = T_User.findUsernames(receiver.replace(';', ','));
		    copyer = T_User.findUsernames(copyer.replace(';', ','));
		    String[] toIds = receiver.split(",");
		    toIds = ArrayUtils.removeDuplicate(toIds);

		    if (mail.update()) {
			renderJson("保存成功！");
		    } else {
			renderJson("数据处理有问题！");
		    }
		    T_Mail temp = T_Mail.dao.findById(mail.getInt("id"));
		    if (temp.getInt("boxId") == 2) {
			for (String s : toIds) {
			    if (s != null) {
				temp.set("sendtime", DateUtils.getNowTime());
				temp.set("boxId", 1);
				temp.set("oboxId", 1);
				temp.set("toId", Integer.parseInt(s));
				// model.set("id", null);
				temp.remove("id");
				temp.save();
			    }
			}
		    }
		}
		break;
	    case "editopinion":
		renderJson(receiveservice.editopinion(user, modelProperty));
		break;
	    case "receivesave":
		renderJson(receiveservice.save(user, modelProperty));
		break;
	    case "leavesave":
		renderJson(leaveservice.save(user, modelProperty));
		break;
	    default:
		renderJson("接口写错啦！");
		break;
	    }
	} else {
	    renderJson("用户登录超时!");
	}

    }

    private void scheduleFormatToSave(T_Myschedule myschedule) {

	Calendar calendar = Calendar.getInstance();
	Integer remind = myschedule.getInt("remind");
	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date = null;
	Date before = null;
	switch (remind) {
	case 1:
	    calendar.setTime(myschedule.getDate("wdate"));
	    // 5分钟前
	    calendar.add(Calendar.MINUTE, -5);
	    before = calendar.getTime();
	    date = sim.format(before);
	    break;
	case 2:
	    calendar.setTime(myschedule.getDate("wdate"));
	    // 10分钟前
	    calendar.add(Calendar.MINUTE, -10);
	    before = calendar.getTime();
	    date = sim.format(before);
	    break;
	case 3:
	    calendar.setTime(myschedule.getDate("wdate"));
	    // 30分钟前
	    calendar.add(Calendar.MINUTE, -30);
	    before = calendar.getTime();
	    date = sim.format(before);
	    break;
	case 4:
	    calendar.setTime(myschedule.getDate("wdate"));
	    // 60分钟前
	    calendar.add(Calendar.MINUTE, -60);
	    before = calendar.getTime();
	    date = sim.format(before);
	    break;
	case 5:
	    calendar.setTime(myschedule.getDate("wdate"));
	    // 120分钟前
	    calendar.add(Calendar.MINUTE, -120);
	    before = calendar.getTime();
	    date = sim.format(before);
	    break;
	case 6:
	    calendar.setTime(myschedule.getDate("wdate"));
	    // 1天前
	    calendar.add(Calendar.DAY_OF_MONTH, -1);
	    before = calendar.getTime();
	    date = sim.format(before);
	    break;
	default:
	    break;
	}
	// 记录日程提醒
	T_MyscheduleMessage messagemodel = new T_MyscheduleMessage();
	messagemodel.set("sdate", date);
	messagemodel.set("content", myschedule.getStr("title"));
	messagemodel.set("u_id", myschedule.getInt("u_id"));
	messagemodel.set("m_id", myschedule.getInt("id"));
	messagemodel.save();

    }

    @Override
    protected void processInDeleteMsg(InDeleteMsg msg) {
	// String json = JsonKit.toJson(msg);
	// System.out.println(json);
	String key = msg.getKey();
	Map<String, T_User> userMap = bc.getUserMap();
	T_User user = userMap.get(key);
	if (user != null) {
	    int u_id = user.getInt("id");
	    String modelName = msg.getModelName();
	    String id = msg.getEntityId();
	    switch (modelName) {
	    case "t_Note":
		T_Note note = T_Note.dao.findById(id);
		if (note.delete()) {
		    renderJson("删除成功！");
		} else {
		    renderJson("数据处理存在问题！");
		}
		break;
	    case "t_Myschedule":
		T_Myschedule myschedule = T_Myschedule.dao.findById(id);
		int uid = myschedule.getInt("u_id");
		if (u_id != uid) {
		    renderJson("只有本人才能执行该操作！");
		} else {
		    if (T_Myschedule.dao.deleteById(id)) {
			T_MyscheduleMessage message = T_MyscheduleMessage.dao.getMid(Integer.parseInt(id));
			if (null != message) {
			    message.delete();
			}
			renderJson("删除成功！");
		    } else {
			renderJson("您提交的查询参数有误，请检查后重新提交!");
		    }
		}
		break;
	    case "t_Mail":
		if (T_Mail.dao.deleteById(id)) {
		    renderJson("删除成功！");
		} else {
		    renderJson("您提交的数据有误，请检查后重新提交!");
		}
		break;
	    default:
		renderJson("接口写错啦！");
		break;

	    }

	} else {
	    renderJson("{status:0,msg:'用户登录超时！'}");
	}
    }

    // byte[]转file
    public static File getFile(byte[] bfile, String filePath, String fileName) {
	BufferedOutputStream bos = null;
	FileOutputStream fos = null;
	File file = null;
	try {
	    File dir = new File(filePath);
	    if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
		dir.mkdirs();
	    }
	    file = new File(filePath + "\\" + fileName);
	    fos = new FileOutputStream(file);
	    bos = new BufferedOutputStream(fos);
	    bos.write(bfile);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (bos != null) {
		try {
		    bos.close();
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
	    }
	    if (fos != null) {
		try {
		    fos.close();
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
	    }
	}
	return file;
    }

    public static boolean writeFile(byte data[], String path) {
	boolean flag = true;
	OutputStreamWriter osw = null;
	try {
	    File file = new File(path);
	    if (!file.exists()) {
		file = new File(file.getParent());
		if (!file.exists()) {
		    file.mkdirs();
		}
	    }

	    FileOutputStream mFile = new FileOutputStream(file);

	    mFile.write(data);
	    mFile.close();
	    /*
	     * if("asci".equals(code)){ code = "GBK"; } osw = new
	     * OutputStreamWriter(new FileOutputStream(path),code);
	     * osw.write(new String(data,code)); osw.flush();
	     */
	} catch (Exception e) {
	    e.printStackTrace();
	    // Log.info("toFile IO Exception:"+e.getMessage());
	    flag = false;
	} finally {
	    try {
		if (osw != null) {
		    osw.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
		// log.info("toFile IO Exception:"+e.getMessage());
		flag = false;
	    }
	}
	return flag;
    }

}