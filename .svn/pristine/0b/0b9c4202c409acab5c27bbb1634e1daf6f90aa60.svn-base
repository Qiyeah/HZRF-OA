package com.oa.controller.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Clear;
import com.oa.controller.BaseAssociationController;
import com.oa.model.approve.T_Personal;
import com.oa.model.document.T_Doc_Achieve;
import com.oa.model.document.T_Doc_Receive;
import com.oa.model.document.T_Inner_Send;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.work.T_Announce;
import com.oa.model.work.T_Mail;
import com.oa.model.work.T_Myschedule;
import com.oa.model.work.T_Sound;
import com.zhilian.annotation.RouteBind;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_Loginlog;
import com.zhilian.model.T_Module;
import com.zhilian.util.DateUtils;
import com.zhilian.util.Permit;

@RouteBind(path = "/Main", viewPath = "Index/Main")
public class Main extends BaseAssociationController {

    /** 主界面 */
    public void index() {
	List<T_Module> modules = T_Module.getBaseModules();
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	List<T_Module> userModules = new ArrayList<T_Module>();
	Map<Integer, List<T_Module>> childModuleMap = new HashMap<Integer, List<T_Module>>();
	Map<Integer, List<T_Module>> threeModuleMap = new HashMap<Integer, List<T_Module>>();
	if (null != modules && 0 < modules.size()) {
	    for (T_Module module : modules) {
		if (Permit.haspermit(module.getInt("id"), loginModel.getLimit())) {
		    userModules.add(module);
		    List<T_Module> childModules = new ArrayList<T_Module>();
		    List<T_Module> tChildModules = module.getChildModules();
		    if (null != tChildModules && 0 < tChildModules.size()) {
			Integer m_id = module.getInt("id");
			for (T_Module childModule : tChildModules) {
			    Integer p_id = childModule.getInt("id");
			    if (Permit.haspermit(p_id, loginModel.getLimit())) {
				childModules.add(childModule);
				List<T_Module> threeModules = new ArrayList<T_Module>();
				List<T_Module> tThreeModules = childModule.getChildModules();
				if (null != tThreeModules && 0 < tThreeModules.size()) {
				    Integer t_id = childModule.getInt("id");
				    for (T_Module threeModule : tThreeModules) {
					Integer tp_id = threeModule.getInt("id");
					if (Permit.haspermit(tp_id, loginModel.getLimit())) {
					    threeModules.add(threeModule);
					}
				    }
				    if (t_id != null) {
					threeModuleMap.put(t_id, threeModules);
				    }
				}
			    }
			}
			if (m_id != null) {
			    childModuleMap.put(m_id, childModules);
			}
		    }
		}
	    }

	    setAttr("threeModuleMap", threeModuleMap);
	    setAttr("childModuleMap", childModuleMap);
	    setAttr("modules", userModules);
	}
	Integer userId = loginModel.getUserId();
	T_Sound sound = T_Sound.dao.findByUserId(userId);
	int soundId = 1;
	if (null != sound && null != sound.get("voice_id")) {
	    soundId = sound.get("voice_id");
	}
	setAttr("soundId", soundId);
	setAttr("loginLog", T_Loginlog.getLast(userId));
	render("main.jsp");
    }

    @Clear
    public void getTask() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	Integer userid = loginModel.getUserId();
	Integer pid = loginModel.getPid();
	try {
	    int mailboxnum = T_Mail.dao.getReceiveCount(userid);
	    int noticenum = T_Announce.dao.countUnread(userid);
	    int superdonum = T_Doc_Receive.dao.countSuperdo(userid, pid);
	    int readnum = T_Doc_Receive.dao.countTodo1(userid);
	    int docreceivenum = T_Doc_Receive.dao.countTodo(userid);
	    // int docsendnum = T_Workflow.dao.countTodo(userid, "docsend");
	    int innersendnum = T_Inner_Send.dao.countTodo(userid);
	    int leavenum = T_Workflow.dao.countTodo(userid, "leave");
	    int meetingnum = T_Workflow.dao.countTodo(userid, "meeting");
	    int achievenum = T_Doc_Achieve.dao.getCount();
	    setAttr("achievenum", achievenum);
	    setAttr("mailboxnum", mailboxnum);
	    setAttr("noticenum", noticenum);
	    setAttr("superdonum", superdonum);
	    setAttr("readnum", readnum);
	    setAttr("docreceivenum", docreceivenum);
	    // setAttr("docsendnum", docsendnum);
	    setAttr("innersendnum", innersendnum);
	    setAttr("leavenum", leavenum);
	    setAttr("meetingnum", meetingnum);
	    setAttr("set", T_Myschedule.dao.getSchedule(userid));// 日历
	    setAttr("Mydate", DateUtils.getNowDate().substring(0, 4));

	    setAttr("ulist", T_Personal.dao.getTop(5));
	    setAttr("deptHM", T_Department.dao.hashMapById("sname"));

	    //
	    int isremain = 0;
	    //if ((mailboxnum + noticenum + superdonum + readnum + docreceivenum + docsendnum + leavenum + meetingnum) > 0) {
	    if ((mailboxnum + noticenum + superdonum + readnum + docreceivenum + innersendnum + leavenum + meetingnum) > 0) {
		isremain = 1;
	    }
	    setAttr("isremain", isremain);

	    T_Sound sound = T_Sound.dao.findByUserId(userid);
	    int soundId = 1;
	    if (null != sound && null != sound.get("voice_id")) {
		soundId = sound.get("voice_id");
	    }
	    setAttr("soundId", soundId);

	    render("task1.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Clear
    public void getRemainder() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	Integer pid = loginModel.getPid();
	Integer userid = loginModel.getUserId();
	StringBuffer htmlData = new StringBuffer();
	htmlData.append("<table style='width: 100%'>");
	htmlData.append("<tr align='center' style='height: 100px; top: 5px'>");
	htmlData.append("<td><div style=\"position: relative; width: 100px\">");
	if (Permit.haspermit(T_Module.getModuleByMark("Mail").getInt("id").toString(), loginModel.getLimit())) {
	    htmlData.append(
		    "<a href=\"Main/Mail/main\" data-toggle=\"navtab\" data-id=\"Mail\"><img src=\"images/mailbox.png\" /><br>内部邮件</a>");
	    int mailboxnum = T_Mail.dao.getReceiveCount(userid);
	    if (mailboxnum > 0) {
		htmlData.append("<i class=\"num\">" + mailboxnum + "</i>");
	    }
	} else {
	    htmlData.append("<img src=\"images/mailbox1.png\" /><br>内部邮件</div></td>");
	}
	htmlData.append("<td><div style=\"position: relative; width: 100px\">");
	if (Permit.haspermit(T_Module.getModuleByMark("Announce").getInt("id").toString(), loginModel.getLimit())) {
	    htmlData.append(
		    "<a href=\"Main/Announce/main\" data-toggle=\"navtab\" data-id=\"Announce\"><img src=\"images/notice.png\" /><br>公告通知</a>");
	    Integer noticenum = T_Announce.dao.countUnread(userid);
	    if (noticenum > 0) {
		htmlData.append("<i class=\"num\">" + noticenum + "</i>");
	    }
	} else {
	    htmlData.append("<img src=\"images/notice1.png\" /><br>公告通知</div></td>");
	}
	htmlData.append("<td><div style=\"position: relative; width: 100px\">");
	if (Permit.haspermit(T_Module.getModuleByMark("receive_super").getInt("id").toString(),
		loginModel.getLimit())) {
	    htmlData.append(
		    "<a href=\"Main/Docreceive/superlist\" data-toggle=\"navtab\" data-id=\"receive_super\"><img src=\"images/superdo.png\" /><br>收文督办</a>");
	    Integer superdonum = T_Doc_Receive.dao.countSuperdo(userid, pid);
	    if (superdonum > 0) {
		htmlData.append("<i class=\"num\">" + superdonum + "</i>");
	    }

	} else {
	    htmlData.append("<img src=\"images/superdo1.png\" /><br>收文督办</div></td>");
	}

	htmlData.append("<td><div style=\"position: relative; width: 100px\">");
	if (Permit.haspermit(T_Module.getModuleByMark("receive_todo1").getInt("id").toString(),
		loginModel.getLimit())) {
	    htmlData.append(
		    "<a href=\"Main/Docreceive/todolist1\" data-toggle=\"navtab\" data-id=\"receive_todo1\"><img src=\"images/read.png\" /><br>待阅文件</a>");
	    Integer readnum = T_Doc_Receive.dao.countTodo1(userid);
	    if (readnum > 0) {
		htmlData.append("<i class=\"num\">" + readnum + "</i>");
	    }
	} else {
	    htmlData.append("<img src=\"images/read1.png\" /><br>待阅文件</div></td>");
	}
	htmlData.append("</tr>");
	htmlData.append("<tr align='center' style='height: 100px; top: 5px'>");
	htmlData.append("<td><div style=\"position: relative; width: 100px\">");
	if (Permit.haspermit(T_Module.getModuleByMark("receive_todo").getInt("id").toString(), loginModel.getLimit())) {
	    htmlData.append(
		    "<a href=\"Main/Docreceive/todolist\" data-toggle=\"navtab\" data-id=\"receive_todo\"><img src=\"images/receive.png\" /><br>待办收文</a>");
	    Integer docreceivenum = T_Doc_Receive.dao.countTodo(userid);
	    if (docreceivenum > 0) {
		htmlData.append("<i class=\"num\">" + docreceivenum + "</i>");
	    }
	} else {
	    htmlData.append("<img src=\"images/receive1.png\" /><br>待办收文</div></td>");
	}
	htmlData.append("<td><div style=\"position: relative; width: 100px\">");
	// 暂时关闭发文提醒
//	if (Permit.haspermit(T_Module.getModuleByMark("docsendtodo").getInt("id").toString(), loginModel.getLimit())) {
//	    htmlData.append(
//		    "<a href=\"Main/Docsend/todolist\" data-toggle=\"navtab\" data-id=\"docsendtodo\"><img src=\"images/send.png\" /><br>待办发文</a>");
//	    Integer docsendnum = T_Workflow.dao.countTodo(userid, "docsend");
//	    if (docsendnum > 0) {
//		htmlData.append("<i class=\"num\">" + docsendnum + "</i>");
//	    }
//	} else {
//	    htmlData.append("<img src=\"images/send1.png\" /><br>待办发文</div></td>");
//	}
	// 增加内部发文提醒
	if (Permit.haspermit(T_Module.getModuleByMark("readInnerSend").getInt("id").toString(), loginModel.getLimit())) {
	    htmlData.append(
		    "<a href=\"Main/InnerSend/readList\" data-toggle=\"navtab\" data-id=\"readInnerSend\"><img src=\"images/send.png\" /><br>内部发文</a>");
	    int innersendnum = T_Inner_Send.dao.countTodo(userid);
	    if (innersendnum > 0) {
		htmlData.append("<i class=\"num\">" + innersendnum + "</i>");
	    }
	} else {
	    htmlData.append("<img src=\"images/send1.png\" /><br>内部发文</div></td>");
	}	
	htmlData.append("<td><div style=\"position: relative; width: 100px\">");
	if (Permit.haspermit(T_Module.getModuleByMark("leavetodo").getInt("id").toString(), loginModel.getLimit())) {
	    htmlData.append(
		    "<a href=\"Main/Leave/todolist\" data-toggle=\"navtab\" data-id=\"leavetodo\"><img src=\"images/leave.png\" /><br>待办请休假</a>");
	    Integer leavenum = T_Workflow.dao.countTodo(userid, "leave");
	    if (leavenum > 0) {
		htmlData.append("<i class=\"num\">" + leavenum + "</i>");
	    }

	} else {
	    htmlData.append("<img src=\"images/leave1.png\" /><br>待办请休假</div></td>");
	}
	htmlData.append("<td><div style=\"position: relative; width: 100px\">");
	if (Permit.haspermit(T_Module.getModuleByMark("meeting_todo").getInt("id").toString(), loginModel.getLimit())) {
	    htmlData.append(
		    "<a href=\"Main/Meeting/todolist\" data-toggle=\"navtab\" data-id=\"meeting_todo\"><img src=\"images/meeting.png\" /><br>待办会议</a>");
	    Integer meetingnum = T_Workflow.dao.countTodo(userid, "meeting");
	    if (meetingnum > 0) {
		htmlData.append("<i class=\"num\">" + meetingnum + "</i>");
	    }

	} else {
	    htmlData.append("<img src=\"images/meeting1.png\" /><br>待办会议</div></td>");
	}
	htmlData.append("</tr>");
	htmlData.append("</table>");
	renderText(htmlData.toString());
    }

    @Clear
    public void downloadApp() {
	render("downloadApp.jsp");
    }
}
