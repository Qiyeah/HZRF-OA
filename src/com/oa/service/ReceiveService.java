package com.oa.service;

import java.util.Map;

import com.jfinal.kit.StrKit;
import com.oa.controller.mobile.MoblieWorkflow;
import com.oa.controller.mobile.SelectStep;
import com.oa.model.approve.T_Personal;
import com.oa.model.document.T_Doc_Achieve;
import com.oa.model.document.T_Doc_Receive;
import com.oa.model.document.T_Received;
import com.oa.model.system.log.T_Operation_Log;
import com.oa.model.system.log.T_Sms_Log;
import com.oa.model.system.workflow.Instance;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Woperation;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.system.workflow.T_Workitem;
import com.oa.model.system.workflow.Transaction;
import com.oa.model.system.workflow.WorkflowReturn;
import com.oa.util.SMSUtil;
import com.zhilian.model.T_User;
import com.zhilian.util.DateUtils;

public class ReceiveService {

    public static ReceiveService me = new ReceiveService();

    /** 保存意见 */
    public String editopinion(T_User user, Map<String, String> para) {
	String msg = null;
	int u_id = user.getInt("id");

	String docid = para.get("docid");
	String opinion = para.get("opinion");
	System.out.println(para.get("pid"));
	Integer pid = Integer.parseInt(para.get("pid"));

	String isreceiveopinion = para.get("isreceiveopinion");
	// 判断是签收意见还是一般意见
	if ("1".equals(isreceiveopinion)) {
	    T_Received receiver = T_Received.dao.findByDoc(docid, u_id);
	    receiver.set("opinion", opinion);
	    if (receiver.update()) {
		msg = "保存意见成功！";
	    } else {
		msg = "数据处理存在错误！";
	    }
	} else {
	    T_Workflow wf = T_Workflow.dao.findById(pid);
	    int itemid = wf.getInt("itemid");
	    T_Wactive active = T_Wactive.dao.findById(itemid);
	    String opinionfield = active.getStr("opinionfield");
	    T_Workitem item = T_Workitem.dao.getWorkitem(pid, itemid, String.valueOf(u_id));
	    if (itemid == 107 && (user.getInt("pid") == 12 || user.getInt("pid") == 14 || user.getInt("pid") == 16)) {
		opinionfield = "opinion1";
	    }
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
	    msgstr = receiveflow(user, modelProperty);
	    selectstep.setType(msgstr);
	}
	selectstep.setNextType(nexttype);
	return selectstep;

    }

    /** 收文流转 */
    public String receiveflow(T_User user, Map<String, String> modelProperty) {
	Instance ins = new Instance();
	String msgstr = "";
	String operation = modelProperty.get("operation");
	int pid = Integer.parseInt(modelProperty.get("pid"));
	// 流程流转处理
	try {
	    T_Doc_Receive dc = T_Doc_Receive.dao.findByPid(pid);
	    String degree = "";
	    if (null != dc) {
		degree = dc.getStr("degree");
	    }
	    T_Workflow wf = T_Workflow.dao.findById(pid);
	    String docno = dc.getStr("docno");
	    String userIdStr = user.get("id").toString();
	    String userDeptStr = user.get("d_id").toString();
	    ins.setCuruser(userIdStr);
	    ins.setCurdept(userDeptStr);
	    String nextitemid = modelProperty.get("nextitemid");
	    ins.setNextitemid(modelProperty.get("nextitemid"));
	    String nexttodoman = modelProperty.get("nexttodoman");
	    ins.setNexttodoman(StrKit.notBlank(nexttodoman) ? nexttodoman.replace(",", ";") : null);

	    ins.setOperation(operation);
	    ins.setOpinionfield(modelProperty.get("opinionfield"));
	    ins.setOpinion(modelProperty.get("opinion"));
	    ins.setOpinion(modelProperty.get("opiniontime"));

	    T_Wactive wa = T_Wactive.dao.findById(modelProperty.get("nextitemid"));
	    ins.setWActive(wa);

	    if (ins.getOperation().equals("TuiHuiShangBu")) {
		wf.set("title", modelProperty.get("flowname") + " [回退]");
	    } else if (ins.getOperation().equals("TuiHuiNiGao")) {
		wf.set("title", modelProperty.get("flowname") + " [修改后提交]");
	    } else {
		wf.set("title", modelProperty.get("flowname"));
	    }
	    ins.setWorkflow(wf);

	    Transaction trans = new Transaction();
	    WorkflowReturn wfr;
	    if (ins.getOperation().equals("ZhiPai")) {
		wfr = trans.point(ins);
	    } else {
		wfr = trans.Trans(ins);
	    }
	    
	    int achieveId = dc.getInt("achieveId");
	    T_Doc_Achieve da = T_Doc_Achieve.dao.findById(achieveId);
	    if (da.getStr("status").equals("0")) {
		// 设置收文的经办人
		dc.set("receiver", user.getInt("id"));
		dc.update();
		// 设置收文登记的分办状态
		da.set("receive1", 2);
		da.set("receive2", user.getStr("name"));
		da.set("status", "1").update();
	    }

	    boolean value1 = true;
	    boolean value2 = true;

	    String opname = T_Woperation.dao.getNameByCode(ins.getOperation());
	    if (wfr.getMessage().equals("wfr1")) {
		msgstr = "【" + opname + "】" + "操作成功！";
	    } else if (wfr.getMessage().equals("wfr2")) {
		String todoman = wfr.getTodoman().replace(';', ',');
		String users = T_User.findUsernames(todoman);
		msgstr = "【" + opname + "】" + "操作成功！\n\n收文处理单已流转到：" + users;
		// 记录日记
		new T_Operation_Log(user.getInt("id"), opname + "了收文：" + docno, "Docreceive");
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
