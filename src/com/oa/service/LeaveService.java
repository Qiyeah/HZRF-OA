package com.oa.service;

import java.util.Map;

import com.jfinal.kit.StrKit;
import com.oa.controller.mobile.MoblieWorkflow;
import com.oa.controller.mobile.SelectStep;
import com.oa.model.approve.T_Leave_Approve;
import com.oa.model.approve.T_Personal;
import com.oa.model.system.log.T_Operation_Log;
import com.oa.model.system.log.T_Sms_Log;
import com.oa.model.system.workflow.Instance;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Woperation;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.system.workflow.T_Workitem;
import com.oa.model.system.workflow.T_Wprocess;
import com.oa.model.system.workflow.Transaction;
import com.oa.model.system.workflow.WorkflowReturn;
import com.oa.util.SMSUtil;
import com.zhilian.model.T_User;
import com.zhilian.util.DateUtils;

public class LeaveService {

    public static LeaveService me = new LeaveService();

    /** 保存意见 */
    public String editopinion(T_User user, Map<String, String> para) {
	String msg = null;
	int u_id = user.getInt("id");

	String opinion = para.get("opinion");
	Integer pid = Integer.parseInt(para.get("pid"));

	T_Workflow wf = T_Workflow.dao.findById(pid);
	int itemid = wf.getInt("itemid");
	T_Wactive active = T_Wactive.dao.findById(itemid);
	String opinionfield = active.getStr("opinionfield");
	T_Workitem item = T_Workitem.dao.getWorkitem(pid, itemid, String.valueOf(u_id));
	if (StrKit.notBlank(opinionfield)) {
	    item.set("opinion", opinion);
	    item.set("opinionfield", opinionfield);
	    item.set("opiniontime", DateUtils.getNowTime());
	    if (item.update()) {
		msg = "保存意见成功！";
	    } else {
		msg = "数据处理存在错误！";
	    }
	}
	return msg;
    }

    public SelectStep save(T_User user, Map<String, String> modelProperty) {
	String msgstr = "";
	SelectStep selectstep = new SelectStep();
	MoblieWorkflow workflow = new MoblieWorkflow();
	String ids = modelProperty.get("nextitemid"); // 选中环节的id
	int pid = Integer.parseInt(modelProperty.get("pid"));
	int d_id = user.getInt("d_id");
	String nexttype = modelProperty.get("nexttype");

	if (StrKit.notBlank(nexttype) ? nexttype.equals("windows") : false) {
	    selectstep = workflow.selectman2(ids, pid, d_id);
	} else {
	    nexttype = "toast";
	    msgstr = leaveflow(user, modelProperty);
	    selectstep.setType(msgstr);
	}
	selectstep.setNextType(nexttype);
	return selectstep;

    }

    /** 请休假流转 */
    public String leaveflow(T_User user, Map<String, String> modelProperty) {
	Instance ins = new Instance();
	T_Workflow wf = null;
	T_Wactive wa = null;
	Integer u_id = user.getInt("id");
	Integer d_id = user.getInt("d_id");
	String applyer = "";
	String nextitemid = "";
	String nexttodoman = "";
	String msgstr = "";
	String operation = modelProperty.get("operation");
	int pid = Integer.parseInt(modelProperty.get("pid"));
	// 流程流转处理
	try {
	    T_Leave_Approve lv = T_Leave_Approve.dao.findByPid(pid);
	    if (null != lv) { // 已经建立审批单
		applyer = T_User.getUserNameById(lv.getInt("u_id"));
		wf = T_Workflow.dao.findById(pid);
		String userIdStr = u_id.toString();
		String userDeptStr = d_id.toString();
		ins.setCuruser(userIdStr);
		ins.setCurdept(userDeptStr);
		nextitemid = modelProperty.get("nextitemid");
		ins.setNextitemid(modelProperty.get("nextitemid"));
		nexttodoman = modelProperty.get("nexttodoman");
		ins.setNexttodoman(StrKit.notBlank(nexttodoman) ? nexttodoman.replace(",", ";") : null);

		ins.setOperation(operation);
		ins.setOpinionfield(modelProperty.get("opinionfield"));
		ins.setOpinion(modelProperty.get("opinion"));
		ins.setOpinion(modelProperty.get("opiniontime"));

		wa = T_Wactive.dao.findById(modelProperty.get("nextitemid"));
		ins.setWActive(wa);

		if (ins.getOperation().equals("TuiHuiShangBu")) {
		    wf.set("title", modelProperty.get("flowname") + " [回退]");
		} else if (ins.getOperation().equals("TuiHuiNiGao")) {
		    wf.set("title", modelProperty.get("flowname") + " [修改后提交]");
		} else {
		    wf.set("title", modelProperty.get("flowname"));
		}

	    } else { // 没有建立审批单
		T_Wprocess wp = T_Wprocess.dao.findFirst("select * from t_wprocess where flow=?", "leave");
		int itemid = wp.getInt("beginstep");
		// 新建流程实例
		wf = new T_Workflow();
		wf.set("flowname", wp.getStr("name"));
		wf.set("flowform", "leave");
		wf.set("formname", "请休假申请单");
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
		T_Workitem wkit = new T_Workitem();
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
		// 新建请假申请单
		lv = new T_Leave_Approve();
		lv.set("u_id", u_id);
		lv.set("d_id", d_id);
		lv.set("married", T_Personal.dao.findValueById("married", u_id));
		lv.set("approvedate", modelProperty.get("nextitemid"));
		lv.set("begindate", modelProperty.get("begindate"));
		lv.set("enddate", modelProperty.get("enddate"));
		lv.set("type", modelProperty.get("type"));
		lv.set("workyear", T_Personal.dao.statWorkyear(u_id));
		lv.set("daye", modelProperty.get("daye"));
		lv.set("dayn", modelProperty.get("dayn"));
		lv.set("dayc", modelProperty.get("dayc"));
		lv.set("dayz", modelProperty.get("dayz"));
		lv.set("dayg", modelProperty.get("dayg"));
		lv.set("dayt", modelProperty.get("dayt"));
		lv.set("days", modelProperty.get("days"));
		lv.set("reason", modelProperty.get("reason"));
		lv.set("pid", pid);
		lv.set("pstatus", "1");
		lv.save();
	    }

	    ins.setWorkflow(wf);
	    Transaction trans = new Transaction();
	    WorkflowReturn wfr = trans.Trans(ins);
	    boolean value1 = true;
	    boolean value2 = true;

	    String opname = T_Woperation.dao.getNameByCode(ins.getOperation());
	    if (wfr.getMessage().equals("wfr1")) {
		msgstr = "【" + opname + "】" + "操作成功！";
	    } else if (wfr.getMessage().equals("wfr2")) {
		String todoman = wfr.getTodoman().replace(';', ',');
		String users = T_User.findUsernames(todoman);
		msgstr = "【" + opname + "】" + "操作成功！\n\n请休假审批单已流转到：" + users;
		// 记录日记
		new T_Operation_Log(user.getInt("id"), opname + "了" + applyer + "的请休假审批单：", "Leave");
		// 发送短信
		if (T_Wactive.dao.canSendSms(nextitemid)) {
		    String mobile = T_Personal.dao.getMobileByUsers(todoman);
		    String message = "您有一个新请休假审批单需要处理。";
		    String result = SMSUtil.sendsms(mobile, message);
		    new T_Sms_Log(users, mobile, message, result, pid);
		}
	    } else if (wfr.getMessage().equals("wfr3")) {
		msgstr = "【" + opname + "】" + "操作成功！ \n\n下一处理人 "
			+ T_User.findUsernames(wfr.getNextShoulddo().replace(';', ',')) + "已暂存，" + "该环节将继续由"
			+ T_User.findUsernames(wfr.getTodoman().replace(';', ',')) + "处理！";
	    }
	    if (value1 && value2) {
		return msgstr;
	    } else {
		return null;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

}
