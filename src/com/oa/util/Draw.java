package com.oa.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.jacob.com.Dispatch;
import com.jfinal.kit.StrKit;
import com.oa.model.approve.T_Leave_Approve;
import com.oa.model.approve.T_Personal;
import com.oa.model.common.T_Attachment;
import com.oa.model.document.T_Doc_Receive;
import com.oa.model.document.T_Doc_Send;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.system.workflow.T_Workitem;
import com.oa.model.work.T_Meeting_Approve;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;

public class Draw {
    private Word word = null;

    /**
     * 初始化
     */
    public Draw() {
	word = new Word();
    }

    /**
     * 打开文档
     */
    public void open(String docPath) {
	word.openDocument(docPath);
    }

    /**
     * 替换页脚和页眉
     */
    public void replaceHF(String toFindText, String newText, Integer select) {
	word.replaceHeaderFoot(toFindText, newText, select);
    }

    public void copyTableEnter(int tableIndex) {
	Dispatch table = word.getTable(tableIndex);
	word.copyTableEnter(table);
    }

    /**
     * 正文替换(多文本)
     * 
     * @param singleText
     *            只替换一次的hashmap
     * @param doubleText
     *            要替换多次的hashmap
     */
    public boolean replaceRelationText(HashMap<String, String> singleText, HashMap<String, String> doubleText) {
	try {
	    if (singleText != null) {
		// 单次替换
		Iterator<Entry<String, String>> iter = singleText.entrySet().iterator();
		while (iter.hasNext()) {
		    Entry<String, String> entry = iter.next();
		    Object key = entry.getKey();
		    Object val = entry.getValue();
		    if (val != null) {
			word.replaceText(key.toString(), val.toString());
		    } else {
			word.replaceText(key.toString(), "－－");
		    }
		}
	    }
	    if (doubleText != null) {
		// 多次替换
		Iterator<Entry<String, String>> ator = doubleText.entrySet().iterator();
		while (ator.hasNext()) {
		    Entry<String, String> entry = ator.next();
		    Object key = entry.getKey();
		    Object val = entry.getValue();
		    if (val != null) {
			word.replaceAllText(key.toString(), val.toString());
		    } else {
			word.replaceAllText(key.toString(), "－－");
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return true;
    }

    public boolean replaceText(HashMap<String, String> singleText, HashMap<String, String> doubleText) {
	try {
	    if (singleText != null) {
		// 单次替换
		Iterator<Entry<String, String>> iter = singleText.entrySet().iterator();
		while (iter.hasNext()) {
		    Entry<String, String> entry = iter.next();
		    Object key = entry.getKey();
		    Object val = entry.getValue();
		    if (val != null) {
			if (key.equals("{remark}") && val.toString().contains("e")) {
			    word.replaceRemark(key.toString(), val.toString());// 如果remark有特殊字符，则要另外处理
			} else {
			    word.replaceAllText(key.toString(), val.toString());
			}
		    } else {
			word.replaceText(key.toString(), "－－");
		    }
		}
	    }
	    if (doubleText != null) {
		// 多次替换
		Iterator<Entry<String, String>> ator = doubleText.entrySet().iterator();
		while (ator.hasNext()) {
		    Entry<String, String> entry = ator.next();
		    Object key = entry.getKey();
		    Object val = entry.getValue();
		    if (val != null) {
			word.replaceAllText(key.toString(), val.toString());
		    } else {
			word.replaceAllText(key.toString(), "－－");
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return true;
    }

    public boolean replaceDispatchText(Integer pid, LoginModel loginModel) {
	try {
	    T_Doc_Send dc = T_Doc_Send.dao.findFirst("select * from t_doc_send where pid=?", pid);

	    Object d_id = T_Department.dao.findValueById("fname", dc.getInt("d_id")); // 科室
	    // 科室
	    if (StrKit.notBlank(String.valueOf(d_id))) {
		word.replaceAllText("A1", String.valueOf(d_id));
	    } else {
		word.replaceAllText("A1", null);
	    }
	    // 文号
	    String docno = dc.getStr("docno");
	    if (StrKit.notBlank(docno)) {
		word.replaceAllText("A2", docno);
	    } else {
		word.replaceAllText("A2", null);
	    }
	    // 拟稿时间
	    java.util.Date approvedate = dc.getDate("approvedate");
	    if (StrKit.notBlank(String.valueOf(approvedate))) {
		word.replaceAllText("A3", String.valueOf(approvedate));
	    } else {
		word.replaceAllText("A3", null);
	    }
	    // 密级
	    word.replaceAllText("A4", dc.getStr("security"));

	    // 拟稿人
	    Object uid = T_User.dao.findValueById("name", dc.getInt("u_id"));
	    if (StrKit.notBlank(String.valueOf(uid))) {
		word.replaceAllText("A5", String.valueOf(uid));
	    } else {
		word.replaceAllText("A5", null);
	    }
	    // 校稿人
	    word.replaceAllText("A6", dc.getStr("proof"));
	    // 份 数
	    Integer num = dc.getInt("num");

	    if (null != num) {
		word.replaceAllText("A7", String.valueOf(num));
	    } else {
		word.replaceAllText("A7", null);
	    }
	    // 主送
	    String send1 = dc.getStr("send1");
	    if (StrKit.notBlank(send1)) {
		word.replaceAllText("A8", send1);
	    } else {
		word.replaceAllText("A8", null);
	    }
	    // 抄送
	    String send2 = dc.getStr("send2");
	    if (StrKit.notBlank(send2)) {
		word.replaceAllText("A9", send2);
	    } else {
		word.replaceAllText("A9", null);
	    }
	    // 文件标题
	    String title = dc.getStr("title");
	    if (StrKit.notBlank(title)) {
		word.replaceAllText("A10", title);
	    } else {
		word.replaceAllText("A10", null);
	    }
	    Dispatch table = word.getTable(1);
	    Integer did = dc.getInt("d_id");
	    int i = 8;

	    StringBuffer buffer = new StringBuffer();
	    List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	    if (fileList.size() > 0) {
		for (T_Attachment ta : fileList) {
		    buffer.append(ta.getStr("name"));
		    buffer.append("\n");
		}
	    }
	    word.addRow(table);
	    word.putTxtToCell(table, i, 1, "附件列表");
	    word.putTxtToCell(table, i, 2, buffer.toString());
	    i++;
	    String opinion1 = T_Workitem.dao.getOpinions(pid, "opinion1");
	    String opinion2 = T_Workitem.dao.getOpinions(pid, "opinion2");
	    String opinion3 = T_Workitem.dao.getOpinions(pid, "opinion3");
	    String opinion4 = T_Workitem.dao.getOpinions(pid, "opinion4");
	    String opinion5 = T_Workitem.dao.getOpinions(pid, "opinion5");

	    // 流程环节信息
	    T_Workflow wf = T_Workflow.dao.findById(pid);

	    int itemid = wf.getInt("itemid");

	    T_Wactive wa = T_Wactive.dao.findById(itemid);
	    // 根据当前人的职务，强制更换意见域
	    if (loginModel.getPid() == 3) {
		wa.set("opinionfield", "opinion3");
	    } else if (loginModel.getPid() == 1) {
		wa.set("opinionfield", "opinion4");
	    }

	    if (did != 2) {
		word.addRow(table);
		word.putTxtToCell(table, i, 1, "科室意见");
		word.putTxtToCell(table, i, 2,
			opinion1.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));
		i++;
		try {
		    if (wa.getStr("opinionfield").equals("opinion5")) {
			word.addRow(table);
			word.putTxtToCell(table, i, 1, "相关科室意见");
			word.putTxtToCell(table, i, 2,
				opinion5.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));
			i++;
		    }
		} catch (Exception e) {
		}

		try {
		    if (!wa.getStr("opinionfield").equals("opinion5")) {
			if (StrKit.notBlank(opinion5)) {
			    word.addRow(table);
			    word.putTxtToCell(table, i, 1, "相关科室意见");
			    word.putTxtToCell(table, i, 2, opinion5.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n")
				    .replaceAll("<br/>", "\n"));
			    i++;
			}
		    }
		} catch (Exception e) {
		}
		word.addRow(table);
		word.putTxtToCell(table, i, 1, "办公室意见");
		word.putTxtToCell(table, i, 2,
			opinion2.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));
		i++;
	    }

	    if (did == 2) {
		word.addRow(table);
		word.putTxtToCell(table, i, 1, "办公室意见");
		word.putTxtToCell(table, i, 2,
			opinion1.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));
		i++;
		try {
		    if (wa.getStr("opinionfield").equals("opinion5")) {
			word.addRow(table);
			word.putTxtToCell(table, i, 1, "相关科室意见");
			word.putTxtToCell(table, i, 2,
				opinion5.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));
			i++;
		    }
		} catch (Exception e) {
		}
		try {
		    if (!wa.getStr("opinionfield").equals("opinion5")) {
			if (StrKit.notBlank(opinion5)) {
			    word.addRow(table);
			    word.putTxtToCell(table, i, 1, "相关科室意见");
			    word.putTxtToCell(table, i, 2, opinion5.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n")
				    .replaceAll("<br/>", "\n"));
			    i++;
			}
		    }
		} catch (Exception e) {
		}
	    }

	    word.addRow(table);
	    word.putTxtToCell(table, i, 1, "分管副部长批示");
	    word.putTxtToCell(table, i, 2,
		    opinion3.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));
	    i++;

	    word.putTxtToCell(table, i, 1, "部长批示");
	    word.putTxtToCell(table, i, 2,
		    opinion4.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return true;

    }

    public boolean replaceLeaveText(Integer pid, LoginModel loginModel) {
	try {
	    T_Leave_Approve la = T_Leave_Approve.dao.findFirst("select * from t_leave_approve where pid=?", pid);
	    String u_id = (String) T_User.dao.findValueById("name", la.getInt("u_id")); // 申请人
	    if (StrKit.notBlank(u_id)) {
		word.replaceAllText("A1", u_id);
	    } else {
		word.replaceAllText("A1", null);
	    }

	    String d_id = (String) T_Department.dao.findValueById("fname", la.getInt("d_id"));// 所在科室
	    if (StrKit.notBlank(d_id)) {
		word.replaceAllText("A2", d_id);
	    } else {
		word.replaceAllText("A2", null);
	    }

	    word.replaceAllText("A3", la.getStr("type"));// 请假类型

	    T_Personal personal = T_Personal.dao.findFirst("select * from t_personal where u_id=?", la.getInt("u_id"));// 婚姻
	    if (null != personal) {
		String married = personal.getStr("married");
		if (StrKit.notBlank(married)) {
		    word.replaceAllText("A4", married);
		} else {
		    word.replaceAllText("A4", null);
		}
	    }
	    word.replaceAllText("A5", String.valueOf(la.getDate("approvedate")));// 申请时间

	    String begindate = String.valueOf(la.getDate("begindate"));
	    String enddate = String.valueOf(la.getDate("enddate"));
	    word.replaceAllText("A6", begindate + "—" + enddate);// 起止时间

	    word.replaceAllText("A7", String.valueOf(la.getInt(("dayt")))); // dayt

	    word.replaceAllText("A8", la.getStr("reason"));// 请休假事由

	    StringBuffer buffer = new StringBuffer();
	    List<T_Attachment> fileList = T_Attachment.dao.listInIds(la.getStr("fjid")); // 附件列表
	    if (fileList.size() > 0) {
		for (T_Attachment ta : fileList) {
		    buffer.append(ta.getStr("name"));
		    buffer.append("\n");
		}
	    }
	    word.replaceAllText("A9", buffer.toString());

	    // 科室意见
	    String opinion1 = T_Workitem.dao.getOpinions(pid, "opinion1");
	    String opinion2 = T_Workitem.dao.getOpinions(pid, "opinion2");
	    String opinion3 = T_Workitem.dao.getOpinions(pid, "opinion3");
	    word.replaceAllText("A10",
		    opinion1.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));
	    word.replaceAllText("A11",
		    opinion2.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));
	    word.replaceAllText("A12",
		    opinion3.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));

	    String backdate = String.valueOf(la.getDate("backdate"));
	    if (backdate.equals("null")) {
		word.replaceAllText("A13", null);// 销假时间
	    } else {
		word.replaceAllText("A13", backdate);
	    }
	    String days = String.valueOf(la.getInt("days"));
	    if (StrKit.notBlank(days)) {
		word.replaceAllText("A14", days);// 实际天数
	    } else {
		word.replaceAllText("A14", null);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return true;
    }

    public boolean replaceTextReceive(Integer pid, LoginModel loginModel) {
	try {

	    T_Doc_Receive receive = T_Doc_Receive.dao.findFirst("select * from t_doc_receive where pid=?", pid);
	    // Object d_id = T_Workflow.dao.findValueById("starter", pid);
	    String unit = receive.getStr("unit"); // 来文单位
	    if (StrKit.notBlank(String.valueOf(unit))) {
		word.replaceAllText("A1", String.valueOf(unit));
	    } else {
		word.replaceAllText("A1", null);
	    }

	    java.util.Date receivedate = receive.getDate("receivedate"); // 收文日期
	    if (StrKit.notBlank(String.valueOf(receivedate))) {
		word.replaceAllText("A2", String.valueOf(receivedate));
	    } else {
		word.replaceAllText("A2", null);
	    }

	    String docno = receive.getStr("docno"); // 办文编号
	    if (StrKit.notBlank(String.valueOf(docno))) {
		word.replaceAllText("A3", String.valueOf(docno));
	    } else {
		word.replaceAllText("A3", null);
	    }

	    String security = receive.getStr("security");
	    if (StrKit.notBlank(String.valueOf(security))) {
		word.replaceAllText("A4", security);
	    } else {
		word.replaceAllText("A4", null);
	    }

	    Integer count = receive.getInt("count");
	    if (StrKit.notBlank(String.valueOf(count))) {
		word.replaceAllText("A5", String.valueOf(count));
	    } else {
		word.replaceAllText("A5", null);
	    }

	    String title = receive.getStr("title"); // 来文标题
	    if (StrKit.notBlank(String.valueOf(title))) {
		word.replaceAllText("A6", String.valueOf(title));
	    } else {
		word.replaceAllText("A6", null);
	    }

	    String opinion1 = T_Workitem.dao.getOpinions(pid, "opinion1");
	    String opinion2 = T_Workitem.dao.getOpinions(pid, "opinion2");
	    if (StrKit.notBlank(String.valueOf(opinion1))) {
		opinion1 = opinion1.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n");
		word.replaceAllText("A7", opinion1);
	    } else {
		word.replaceAllText("A7", null);
	    }

	    if (StrKit.notBlank(String.valueOf(opinion2))) {
		opinion2 = opinion2.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n");
		word.replaceAllText("A8", String.valueOf(opinion2));
	    } else {
		word.replaceAllText("A8", null);
	    }

	    // Object d_id = T_Workflow.dao.findValueById("starter", pid); //
	    // 经办人
	    // String starter = T_User.getUserNameById(d_id);
	    // if (StrKit.notBlank(String.valueOf(starter))) {
	    // word.replaceAllText("A9", String.valueOf(starter));
	    // } else {
	    // word.replaceAllText("A9", null);
	    // }
	    word.replaceAllText("A9", "周玲凤");

	    String auditor = receive.getStr("auditor"); // 来文标题
	    if (StrKit.notBlank(String.valueOf(auditor))) {
		word.replaceAllText("A10", String.valueOf(auditor));
	    } else {
		word.replaceAllText("A10", null);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return true;
    }

    public boolean replaceMeetingText(Integer pid, LoginModel loginModel) {
	try {
	    T_Meeting_Approve meeting = T_Meeting_Approve.dao.findFirst("select * from t_meeting_approve where pid=?",
		    pid);
	    Object d_id = T_Workflow.dao.findValueById("starter", pid); // 申请人
	    String starter = T_User.getUserNameById(d_id);
	    if (StrKit.notBlank(String.valueOf(starter))) {
		word.replaceAllText("A1", String.valueOf(starter));
	    } else {
		word.replaceAllText("A1", null);
	    }

	    Integer did = meeting.getInt("d_id"); // 申请科室
	    Object startdept = T_Department.dao.findValueById("fname", did);
	    if (StrKit.notBlank(String.valueOf(startdept))) {
		word.replaceAllText("A2", String.valueOf(startdept));
	    } else {
		word.replaceAllText("A2", null);
	    }

	    java.util.Date approvedate = meeting.getDate("approvedate"); // 申请日期
	    if (StrKit.notBlank(String.valueOf(approvedate))) {
		word.replaceAllText("A3", String.valueOf(approvedate));
	    } else {
		word.replaceAllText("A3", null);
	    }

	    java.util.Date mdate = meeting.getDate("mdate"); // 会议时间
	    String hour = meeting.getStr("hour");
	    String minute = meeting.getStr("minute");
	    if (StrKit.notBlank(String.valueOf(mdate + "日" + hour + "时" + minute + "分"))) {
		word.replaceAllText("A4", String.valueOf(mdate + "日" + hour + "时" + minute + "分"));
	    } else {
		word.replaceAllText("A4", null);
	    }

	    String address = meeting.getStr("address"); // 地点
	    if (StrKit.notBlank(String.valueOf(address))) {
		word.replaceAllText("A5", String.valueOf(address));
	    } else {
		word.replaceAllText("A5", null);
	    }

	    String chairmanid = meeting.getStr("chairman"); // 主持人
	    String chairman = T_User.getUserNameById(Integer.parseInt(chairmanid));
	    if (StrKit.notBlank(String.valueOf(chairman))) {
		word.replaceAllText("A6", String.valueOf(chairman));
	    } else {
		word.replaceAllText("A6", null);
	    }

	    String type = meeting.getStr("type"); // 类型
	    if (StrKit.notBlank(String.valueOf(type))) {
		word.replaceAllText("A7", String.valueOf(type));
	    } else {
		word.replaceAllText("A7", null);
	    }

	    String attendeeid = meeting.getStr("attendee"); // 参加人员
	    String attendee = "";
	    if (StrKit.notBlank(attendeeid)) {
		attendee = T_User.findUsernames(attendeeid);
	    }
	    if (StrKit.notBlank(String.valueOf(attendee))) {
		word.replaceAllText("A8", String.valueOf(attendee));
	    } else {
		word.replaceAllText("A8", null);
	    }

	    String title = meeting.getStr("title"); // 主题
	    if (StrKit.notBlank(String.valueOf(startdept))) {
		word.replaceAllText("A9", String.valueOf(title));
	    } else {
		word.replaceAllText("A9", null);
	    }

	    String content = meeting.getStr("content"); // 内容
	    if (StrKit.notBlank(String.valueOf(content))) {
		word.replaceAllText("A10", String.valueOf(content));
	    } else {
		word.replaceAllText("A10", null);
	    }

	    /*
	     * String startdept=meeting.getStr("startdept"); //附件列表 if
	     * (StrKit.notBlank(String.valueOf(startdept))) {
	     * word.replaceAllText("A11", String.valueOf(startdept)); } else {
	     * word.replaceAllText("A11", ""); }
	     */
	    Dispatch table = word.getTable(1);
	    int i = 8;

	    StringBuffer buffer = new StringBuffer();
	    List<T_Attachment> fileList = T_Attachment.dao.listInIds(meeting.getStr("fjid"));
	    if (fileList.size() > 0) {
		for (T_Attachment ta : fileList) {
		    buffer.append(ta.getStr("name"));
		    buffer.append("\n");
		}
	    }
	    word.addRow(table);
	    word.putTxtToCell(table, i, 1, "附件列表");
	    word.putTxtToCell(table, i, 2, buffer.toString());
	    i++;
	    String opinion1 = T_Workitem.dao.getOpinions(pid, "opinion1");
	    String opinion2 = T_Workitem.dao.getOpinions(pid, "opinion2");
	    String opinion3 = T_Workitem.dao.getOpinions(pid, "opinion3");

	    // 流程环节信息
	    T_Workflow wf = T_Workflow.dao.findById(pid);

	    int itemid = wf.getInt("itemid");

	    T_Wactive wa = T_Wactive.dao.findById(itemid);
	    // 根据当前人的职务，强制更换意见域
	    if (loginModel.getPid() == 3) {
		wa.set("opinionfield", "opinion3");
	    } else if (loginModel.getPid() == 1) {
		wa.set("opinionfield", "opinion4");
	    }

	    word.addRow(table);
	    word.putTxtToCell(table, i, 1, "科     室\n意     见");
	    word.putTxtToCell(table, i, 2,
		    opinion1.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));
	    i++;

	    word.addRow(table);
	    word.putTxtToCell(table, i, 1, "分     管\n部 领 导\n意     见");
	    word.putTxtToCell(table, i, 2,
		    opinion2.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));
	    i++;

	    // word.addRow(table);
	    word.putTxtToCell(table, i, 1, "部     长\n批     示");
	    word.putTxtToCell(table, i, 2,
		    opinion3.replaceAll("&nbsp;", " ").replaceAll("<br>", "\n").replaceAll("<br/>", "\n"));
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return true;
    }

    /**
     * 获取页数
     */
    public Integer getTotalPage() {
	return word.getPage();
    }

    /**
     * 保存
     */
    public void save() {
	word.save();
    }

    /**
     * 另存word文本
     */
    public void saveAs(String savePath) {
	word.saveAs(savePath);
    }

    /**
     * 保存为PDF
     */
    public void saveToPDF(String pdfURL) {
	word.saveToPDF(pdfURL);
    }

    /**
     * 关闭
     */
    public void close() {
	if (word != null) {
	    word.close();
	}
    }

    /**
     * 插入文本框
     */
    public void inputShapeValue(int index, String value) {
	word.inputShapeValue(index, value);
    }

}
