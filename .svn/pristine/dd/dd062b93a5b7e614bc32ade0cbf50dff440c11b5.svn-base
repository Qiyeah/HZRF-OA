package com.oa.controller.common;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.common.T_Commessage;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.work.T_Announce;
import com.oa.model.work.T_Mail;
import com.oa.model.work.T_Myschedule;
import com.oa.model.work.T_MyscheduleMessage;
import com.zhilian.annotation.RouteBind;
import com.zhilian.model.LoginModel;
import com.zhilian.util.DateUtils;

@RouteBind(path = "Main/backlog", viewPath = "Main")
public class Backlog extends BaseAssociationController {

    /** 公告通知 */
    @Clear
    public void announce() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	StringBuffer htmlData = new StringBuffer("<tr align='center'><td><b>公告标题</b></td><td><b>公告日期</b></td></tr>");
	String html = "";
	// StringBuffer htmlData = new StringBuffer("<tr
	// align='center'><td><b>邮件标题</b></td><td><b>发件人</b></td><td><b>发件日期</b></td></tr>");
	String fileImg = "<img src=\"images/file.gif\"/>";
	String style = "background-color:#f13f40;display:inline-block;padding:2px;text-align:center;vertical-align:text-bottom;font-size:12px;line-height:100%;font-style:normal;color:#fff;overflow:hidden;";
	String stylenew = "background-color:#006600;display:inline-block;padding:2px;text-align:center;vertical-align:text-bottom;font-size:12px;line-height:100%;font-style:normal;color:#fff;overflow:hidden;";
	if (loginModel != null) {
	    Integer userId = loginModel.getUserId();
	    List<T_Announce> annos = T_Announce.dao.getList(userId);
	    Date today = new Date();
	    if (annos != null) {
		for (T_Announce anno : annos) {
		    Date sendtime = anno.get("sendtime");
		    String img = "　";
		    if (DateUtils.isNewForToday(sendtime, today)) {
			img = img + "<span style=" + stylenew + ">新</span> ";
		    }
		    if (anno.getInt("atype") == 1) {
			img = img + "<span style=" + style + ">急</span>";
		    }
		    String readColor = anno.get("viewid") == null ? "#993300" : "black";
		    String formDate = DateUtils.getDateByDate(sendtime);
		    html = "<tr><td align=\"left\">" + fileImg + " " + "<a href=\"Main/Announce/viewip/"
			    + anno.get("id")
			    + "\" data-toggle=\"dialog\" data-width=\"1100\" data-height=\"600\" data-id=\"viewAnnounce\" data-title=\"查看内容\" style=\"font-size: 13px;color: "
			    + readColor + ";\">" + convert(anno.getStr("title"), 26) + "</a>" + img
			    + "</td><td align=\"center\" width='95'>" + formDate + "</td></tr>";
		    htmlData.append(html);
		}
	    }
	}
	renderText(htmlData.toString());
    }

    /** 我的邮件 */
    @Clear
    public void email() {
	StringBuffer htmlData = new StringBuffer(
		"<tr align='center'><td><b>邮件标题</b></td><td><b>发件人</b></td><td><b>发件日期</b></td></tr>");
	try {
	    LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	    if (loginModel != null) {
		Integer userId = loginModel.getUserId();
		List<T_Mail> emails = T_Mail.dao.getMails(userId);
		for (T_Mail email : emails) {
		    String formDate = DateUtils.getDateByDateZh(email.getDate("sendTime"));
		    htmlData.append("<tr><td><a href=\"Main/Mail/view/" + email.getInt("id")
			    + "\" data-toggle=\"dialog\" data-width=\"1000\" data-height=\"600\" data-id=\"Mailview\" data-title=\"查看\" style=\"font-size: 13px;\">"
			    + convert(email.getStr("title"), 11) + "</a></td><td align=\"center\" width='75'>"
			    + email.getStr("username") + "</td><td align=\"center\" width='95'>" + formDate
			    + "</td></tr>");
		}
	    }
	} catch (Exception e) {
	}
	renderText(htmlData.toString());
    }

    /** 我的督办 */
    @Clear
    public void superdo() {
	StringBuffer htmlData = new StringBuffer();
	// 所属科室id
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int DepartmentId = loginModel.getDid();
	int pid = loginModel.getPid();
	Integer userId = ((LoginModel) getSessionAttr("loginModel")).getUserId();
	try {

	    String select = "select wf.*, wa.name as active_name, u.name as uname, dp.fname as dpname, dc.unit as unit, dc.title as doctitle, dc.docno as docno, dc.termdate as dctermdate";
	    String sqlExceptSelect = "from t_workflow wf " + " ,t_wactive wa  " + " ,t_user u " + " ,t_department dp "
		    + " ,t_doc_receive dc " + " where wf.flowform = 'docreceive' " + " and wf.itemid=wa.id "
		    + " and wf.startdept=dp.id " + " and dc.superman=u.id " + " and wf.id = dc.pid "
		    + " and dc.superman is not null " + " and wf.isnormalend='0' ";
	    // 督办员

	    if (pid != 12) {
		sqlExceptSelect = sqlExceptSelect + " and dc.superman= " + userId;
	    }
	    if (DepartmentId != 2) {
		sqlExceptSelect = sqlExceptSelect + " 1 = 2 ";
	    }
	    Page<T_Workflow> page = T_Workflow.dao.paginate(1, 9999, select, sqlExceptSelect);
	    // 表头
	    htmlData.append("<tr align='center'>" + "<td width=\"40%\">" + "    <b>督办标题</b>" + "</td>"
		    + "<td width=\"20%\">" + "    <b>来文单位</b>" + "</td>" + "<td width=\"20%\">" + "    <b>承办科室</b>"
		    + "</td>" + "<td width=\"20%\">" + "    <b>办理时限</b>" + "</td>" + "</tr>");
	    if (page.getList() != null && page.getList().size() > 0) {
		// 数据
		for (T_Workflow wf : page.getList()) {
		    String termdate = "";
		    if (wf.getDate("dctermdate") != null) {
			termdate = wf.getDate("dctermdate").toString();
		    }
		    htmlData.append("<tr style=\"color:#003399\">" + "<td> ");
		    // htmlData.append("<a href=\"#\" onclick =
		    // \"openTodoNavTab('Main/Docreceive/superlist','receive_super','督办列表')\"
		    // style=\"font-size: 13px;color:#003399\">" );
		    htmlData.append(
			    "<a  href=\"Main/Docreceive/superlist\" data-toggle=\"navtab\" data-id=\"receivesuper\" data-title=\"督办列表\" style=\"font-size: 13px;color:#003399\">");
		    htmlData.append(convert(wf.getStr("doctitle"), 18) + "</a>" + "</td>" + "<td>" + wf.getStr("unit")
			    + "</td>" + "<td>" + wf.getStr("dpname") + "</td>" + "<td>" + termdate + "</td>" + "</tr>");
		}
	    }
	} catch (Exception e) {
	}
	renderText(htmlData.toString());
    }

    public String convert(int count) {
	String convertStr = "";
	if (count > 0) {
	    convertStr = "<font style=\"font-size:15px;font-weight:strong\">&nbsp;" + count + "&nbsp;</font>";
	} else {
	    convertStr = "" + count;
	}
	return convertStr;
    }

    public String convert(String Str, int length) {
	String convertStr = "";
	if (Str == null) {
	    return null;
	}
	if (Str.length() > length) {
	    convertStr = Str.substring(0, length - 2) + "...";
	} else {
	    convertStr = Str;
	}
	return convertStr;
    }

    // 主页日历
    @Clear
    public void calendar() {
	String date = getPara("date");
	if (StrKit.notBlank(date)) {
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    int u_id = loginModel.getUserId();
	    String title = T_Myschedule.dao.getTitle(u_id, date.replaceAll("/", "-"));
	    if (null != title) {
		renderText(title);
	    }
	}
    }

  // 主页日历编辑页面
    @Clear
    public void onedit() {
	String date = getPara("date");
	if (StrKit.notBlank(date)) {
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    int u_id = loginModel.getUserId();
	    String page = T_Myschedule.dao.getPage(u_id, date.replaceAll("/", "-"));
	    if (null != page) {
		renderText(page);
	    }
	}
    }
    
    
    /** 获取一条我的消息 */
    @SuppressWarnings("unused")
    @Clear
    public void refreshMain() {
	String messageStr = "";
	try {
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	    Integer userId = loginModel.getUserId();
	    // 科室id
	    Integer deptId = loginModel.getDid();
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	renderText(messageStr);
    }
 

    /** 获取一条我的消息 */
    @Clear
    public void getalert() {
	String messageStr = "";
	try {
	    LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	   
	    Integer userId = loginModel.getUserId();
	    /*// 收文待办数量
	    int docreceivenum = T_Workflow.dao.countTodo(userId, "docreceive");
	    // 获取发文待办数量
	    int docsendnum = T_Workflow.dao.countTodo(userId, "docsend");
	    // 获取请休假待办数量
	    int leavenum = T_Workflow.dao.countTodo(userId, "leave");
	    // 获取出国境待办数量
	    int entryexitnum = T_Workflow.dao.countTodo(userId, "entryexit");
	    // 获取会议审批待办数量
	    int meetingnum = T_Workflow.dao.countTodo(userId, "meeting");
	    // 获取出国境证件逾期数
	    int entryexitlatenum = T_Workflow.dao.counteecertlate(userId);
	    // 党员组织关系逾期未落实数
	    int relationexit = T_Relation.dao.CountNoR();
	    if (docreceivenum > 0) {
		messageStr = "有公文已经流转到你本人处理，请及时办理";
	    } else if (docsendnum > 0) {
		messageStr = "有发文已经流转到你本人处理，请及时办理";
	    } else if (leavenum > 0) {
		messageStr = "有一份请休假申请流转到你本人处理，请及时办理";
	    } else if (entryexitnum > 0) {
		messageStr = "有一份出国境申请流转到你本人处理，请及时办理";
	    } else if (meetingnum > 0) {
		messageStr = "有一份会议申请流转到你本人处理，请及时办理";
	    } else if (entryexitlatenum > 0 && userId == 47) {
		messageStr = "有" + entryexitlatenum + "人出国境证件逾期未缴回，请及时催缴";
		if (relationexit > 0 && userId == 47) {
		    messageStr += "<br/>有" + relationexit + "人党组织关系调转情况未落实";
		}
	    } else if (relationexit > 0 && userId == 47) {
		messageStr = "有" + relationexit + "人党组织关系调转情况未落实";
	    }*/
	    List<T_MyscheduleMessage> message=T_MyscheduleMessage.dao.getSchedule(userId);
	    for(int i=0;i<message.size();i++){
		int mid=message.get(i).getInt("m_id");
		T_Myschedule schedule=T_Myschedule.dao.findById(mid);
		messageStr += schedule.getStr("title")+"<br>";
		
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	renderText(messageStr);
    }
    
    public void messageRead(){
        @SuppressWarnings("unused")
	String messageStr = "";
        try {
            int id = getParaToInt();
            boolean success = T_Commessage.dao.messageRead(id);
            if(success){
                toBJUIJson(200, "消息已读！", "", "", "","","");
            }
        } catch (Exception e) {
            toErrorJson("您提交的查询参数有误，请检查后重新提交！");
            return;
        }
    }
    
    @Clear
    public void changeStatus(){
	 LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	  Integer userId = loginModel.getUserId();
	 List<T_MyscheduleMessage> message=T_MyscheduleMessage.dao.getSchedule(userId);
	    for(int i=0;i<message.size();i++){
		int id=message.get(i).getInt("id");
		T_MyscheduleMessage schedule=T_MyscheduleMessage.dao.findById(id);
		schedule.set("isread", 1);
		schedule.update();
	    }
	    renderNull();
    }
}
