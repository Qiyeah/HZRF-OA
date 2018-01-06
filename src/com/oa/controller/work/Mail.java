package com.oa.controller.work;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.oa.controller.BaseAssociationController;
import com.oa.model.approve.T_Group;
import com.oa.model.common.T_Attachment;
import com.oa.model.system.log.T_Operation_Log;
import com.oa.model.work.T_Mail;
import com.oa.model.work.T_Mail_Attach;
import com.oa.util.BusinessConstant;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_Position;
import com.zhilian.model.T_User;
import com.zhilian.util.ArrayUtils;
import com.zhilian.util.DateUtils;
import com.zhilian.util.FileUtils;

@RouteBind(path = "Main/Mail", viewPath = "Work/Mail")
public class Mail extends BaseAssociationController {
    private String navTabId = "Mail";
    private int maxPostSize = Constant.MAX_POST_SIZE;
    private String mailPath = "/" + Constant.PATH_EMAIL_UPLOAD;
    private static String divId = "Box_Mail";
    private static String treeId = "Tree_Mail";

    /** 邮箱-主页面 */
    public void main() {

	render("mail_main.jsp");
    }

    @ActionKey("/Main/Mail/main/tree")
    public void tree() {
	try {
	    Integer flag = getParaToInt(0);
	    LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	    int receiveCount = T_Mail.dao.getReceiveCount(loginModel.getUserId());
	    int receiveCountTotal = T_Mail.dao.getTotalReceiveCount(loginModel.getUserId());
	    int sendCount = T_Mail.dao.getSendCount(loginModel.getUserId());
	    int draftCount = T_Mail.dao.getDraftCount(loginModel.getUserId());
	    int rubbishCount = T_Mail.dao.getRubbishCount(loginModel.getUserId());
	    Page<T_Mail> page = T_Mail.dao.getPageForReceive(null, null, loginModel.getUserId(), null, flag, null, null,
		    null);
	    setAttr("receiveCount", receiveCount);
	    setAttr("receiveCountTotal", receiveCountTotal);
	    setAttr("sendCount", sendCount);
	    setAttr("draftCount", draftCount);
	    setAttr("rubbishCount", rubbishCount);
	    setAttr("totle", receiveCountTotal + sendCount + draftCount + rubbishCount);
	    setAttr("page", page);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	render("tree.jsp");
    }

    @ActionKey("/Main/Mail/main/list")
    public void list() {

	receive();
	render("box_receive.jsp");
    }

    /** 邮箱-收件箱 */
    public void receive() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	Integer pageNum = getParaToInt("pageCurrent");
	Integer pageSize = getParaToInt("pageSize");
	String title = getPara("title");
	String senderId = getPara("mail.userid");
	String senderName = getPara("mail.username");
	String sdate = getPara("sdate");
	String edate = getPara("edate");

	Page<T_Mail> page = T_Mail.dao.getPageForReceive(pageNum, pageSize, loginModel.getUserId(), title, null,
		senderId, sdate, edate);
	setAttr("page", page);
	int receiveCount = T_Mail.dao.getReceiveCount(loginModel.getUserId());
	int receiveCountTotal = T_Mail.dao.getTotalReceiveCount(loginModel.getUserId());
	int sendCount = T_Mail.dao.getSendCount(loginModel.getUserId());
	int draftCount = T_Mail.dao.getDraftCount(loginModel.getUserId());
	int rubbishCount = T_Mail.dao.getRubbishCount(loginModel.getUserId());

	setAttr("title", title);
	setAttr("senderId", senderId);
	setAttr("senderName", senderName);
	setAttr("sdate", sdate);
	setAttr("edate", edate);
	setAttr("receiveCount", receiveCount);
	setAttr("receiveCountTotal", receiveCountTotal);
	setAttr("sendCount", sendCount);
	setAttr("draftCount", draftCount);
	setAttr("rubbishCount", rubbishCount);
	setAttr("totle", receiveCountTotal + sendCount + draftCount + rubbishCount);
	render("box_receive.jsp");
    }

    /** 邮箱-发件箱 */
    public void send() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	Integer pageNum = getParaToInt("pageCurrent");
	Integer pageSize = getParaToInt("pageSize");
	String title = getPara("title");
	String receiverId = getPara("mail.userid");
	String receiverName = getPara("mail.username");
	String sdate = getPara("sdate");
	String edate = getPara("edate");
	Page<T_Mail> page = T_Mail.dao.getPageForSend(pageNum, pageSize, loginModel.getUserId(), title, receiverId,
		sdate, edate);
	setAttr("title", title);
	setAttr("receiverId", receiverId);
	setAttr("receiverName", receiverName);
	setAttr("sdate", sdate);
	setAttr("edate", edate);
	setAttr("page", page);
	render("box_send.jsp");
    }

    /** 邮箱-草稿箱 */
    public void draft() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	Integer pageNum = getParaToInt("pageNum");
	Integer numPerPage = getParaToInt("numPerPage");
	String title = getPara("title");
	String senderId = getPara("mail.userid");
	String senderName = getPara("mail.username");
	String sdate = getPara("sdate");
	String edate = getPara("edate");
	Page<T_Mail> page = T_Mail.dao.getPageForDraft(pageNum, numPerPage, loginModel.getUserId(), title, senderId,
		sdate, edate);
	setAttr("title", title);
	setAttr("senderId", senderId);
	setAttr("senderName", senderName);
	setAttr("sdate", sdate);
	setAttr("edate", edate);
	setAttr("page", page);
	render("box_draft.jsp");
    }

    /** 邮箱-垃圾箱 */
    public void rubbish() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	Integer pageNum = getParaToInt("pageNum");
	Integer numPerPage = getParaToInt("numPerPage");
	String title = getPara("title");
	String senderId = getPara("mail.userid");
	String senderName = getPara("mail.username");
	String sdate = getPara("sdate");
	String edate = getPara("edate");
	Page<T_Mail> page = T_Mail.dao.getPageForRubbish(pageNum, numPerPage, loginModel.getUserId(), title, senderId,
		sdate, edate);
	setAttr("title", title);
	setAttr("senderId", senderId);
	setAttr("senderName", senderName);
	setAttr("sdate", sdate);
	setAttr("edate", edate);
	setAttr("page", page);
	render("box_rubbish.jsp");
    }

    /** 邮件-新建邮件 */
    public void add() {
	render("mail_add.jsp");
    }

    /** 邮件-编辑邮件 */
    public void edit() {
	Integer id = getParaToInt(0);
	T_Mail model = T_Mail.dao.getById(id);
	List<T_Mail_Attach> fileList = T_Mail_Attach.dao.listInIds(model.getStr("fjid"));
	setAttr("fileList", fileList);
	setAttr("model", model);
	render("mail_edit.jsp");
    }

    /** 邮件-获取所有用户 */
    @Clear
    public void getUsers() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	List<T_Department> deparmentList = T_Department.getAllDepts();
	List<T_Position> positionList = T_Position.getList();
	List<T_User> userList = T_User.dao.find("select * from t_user where status=1 order by d_id,no");
	List<T_Group> group = T_Group.dao.findByUserId(u_id);// 分组

	String uids = "";
	String unames = "";
	List<T_User> groupuserlist = new ArrayList<T_User>();
	T_User usertemp = null;
	for (int i = 0; i < group.size(); i++) {// 构建群组树
	    uids = group.get(i).getStr("u_ids");
	    unames = group.get(i).getStr("u_names");
	    String[] u_ids = uids.split(",");
	    String[] u_names = unames.split(",");
	    for (int j = 0; j < u_ids.length; j++) {
		usertemp = new T_User();
		usertemp.set("id", u_ids[j]);
		usertemp.set("name", u_names[j]);
		usertemp.set("pid", group.get(i).getInt("id"));
		groupuserlist.add(usertemp);
	    }
	}
	setAttr("deptHM", T_Department.dao.hashMapById("sname"));
	setAttr("positionList", positionList);
	setAttr("deparmentList", deparmentList);
	setAttr("userList", userList);
	setAttr("group", group);
	setAttr("groupuserList", groupuserlist);
	render("findData/userList.jsp");
    }

    /** 邮件-查看邮件 */
    public void view() {
	Integer id = getParaToInt(0);
	T_Mail model = T_Mail.dao.getById(id);
	if (model.getInt("isRead") != 1) {
	    model.set("isRead", 1);
	    model.update();
	}
	List<T_Mail_Attach> fileList = T_Mail_Attach.dao.listInIds(model.getStr("fjid"));
	setAttr("fileList", fileList);
	// 系统自动发送的催办文件，换成其他
	if (model.getStr("title") != null && model.getStr("title").equals("【系统邮件】传阅文件过期提醒")) {
	    List<T_Attachment> fileList1 = T_Attachment.dao.listInIds(model.getStr("fjid"));
	    setAttr("fileList", fileList1);
	}
	setAttr("model", model);
	render("mail_view.jsp");
    }

    /** 查看-邮件关闭 */
    public void closeView() {
	toBJUIJson(200, "", navTabId, "", "", "true", "");
    }

    /** 邮件-删除邮件 */
    public void delete() {
	try {
	    String flag = getPara(0);
	    String ids = getPara("ids");
	    String tree = getPara(1);
	    if ("1".equals(flag)) {
		T_Mail.dao.deleteReal(ids);
	    } else {
		T_Mail.dao.deleteFail(ids);
	    }
	    toRefreshDivJson(200, Constant.SUCCESS, "",
		    divId + ":Main/Mail/" + tree + ";" + treeId + ":Main/Mail/main/tree");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /** 邮件-保存邮件 */
    public void save() {
	try {
	    T_Mail model = getModel(T_Mail.class);
	    model.set("receiver", getPara("param.userName"));
	    model.set("receiverId", getPara("param.userId"));
	    model.set("copyer", getPara("param2.userName"));
	    model.set("copyerId", getPara("param2.userId"));
	    String[] fjids = getParaValues("fjid");
	    model.set("fjid", ArrayUtils.ArrayToString(fjids));
	    String userId = getPara("param.userId");
	    if (!isParaBlank("param2.userId")) {
		userId += "," + getPara("param2.userId");
	    }
	    String[] toIds = userId.split(",");
	    toIds = ArrayUtils.removeDuplicate(toIds);
	    if (model.getInt("id") != null) {
		model.set("isRead", 1);
		model.update();
	    } else {
		model.set("isRead", 1);
		model.set("oboxId", model.getInt("boxId"));
		model.remove("id");
		model.save();
	    }
	    if (model.getInt("boxId") == 2) {
		for (String s : toIds) {
		    if (s != null) {
			model.set("boxId", 1);
			model.set("oboxId", 1);
			model.set("isRead", 0);
			model.set("toId", Integer.parseInt(s));
			// model.set("id", null);
			model.remove("id");
			model.save();
		    }
		}
	    }

	    if (!isParaBlank("originalMailId")) {
		Integer originalMailId = getParaToInt("originalMailId");
		String originalMailStatus = getPara("originalMailStatus");
		T_Mail mail = new T_Mail();
		mail.set("id", originalMailId);
		mail.set("status", originalMailStatus);
		mail.update();
	    }
	    toBJUIJson(200, Constant.SUCCESS, navTabId, "", "", "true", "");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("保存异常！");
	}
    }

    /** 附件-保存 */
    public void saveAccess() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	String saveDirectory = DateUtils.getNowDate();
	List<UploadFile> files = getFiles(mailPath + saveDirectory, maxPostSize);
	List<T_Mail_Attach> list = new ArrayList<T_Mail_Attach>();
	for (int i = 0; i < files.size(); i++) {
	    UploadFile file = files.get(i);
	    String fileName = file.getFileName();
	    String suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
	    String size = FileUtils.getFileSize(file.getFile().length());
	    String url = saveDirectory + "/" + fileName;
	    T_Mail_Attach attach = new T_Mail_Attach();
	    attach.set("name", fileName);
	    attach.set("url", url);
	    attach.set("size", size);
	    attach.set("suffix", suffix);
	    attach.set("mark", loginModel.getUserId());
	    attach.set("markDate", DateUtils.getNowDate());
	    attach.save();
	    list.add(attach);
	}
	String jsonstr = JsonKit.toJson(list);
	renderJson(jsonstr);
    }

    /** 附件-删除 */
    public void deleteAccess() {
	Integer id = getParaToInt(0);
	T_Mail_Attach model = T_Mail_Attach.dao.findById(id);
	try {
	    String url = Constant.PATH_WEBROOT + Constant.PATH_EMAIL + model.getStr("url");
	    File file = new File(url);
	    if (file.exists()) {
		file.delete();
	    }
	    model.delete();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	renderNull();
    }

    /** 附件-下载 */
    public void downloadAccess() {
	String idstr = getPara(0);
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	T_Mail_Attach model = T_Mail_Attach.dao.findById(idstr);
	if (model == null) {
	    render("ajaxDoneErr.html");
	    return;
	}
	// 记录日志
	new T_Operation_Log(loginModel.getUserId(), "下载了文件，文件名为：：" + model.getStr("name"), "邮件下载");
	String url = Constant.PATH_WEBROOT + Constant.PATH_EMAIL + model.getStr("url");
	File file = new File(url);
	if (file.exists()) {
	    renderFile(file);
	} else {
	    render("ajaxDoneErr.html");
	}

    }

    /** 转发 */
    public void forward() {
	Integer id = getParaToInt(0);
	T_Mail model = T_Mail.dao.getById(Integer.valueOf(id));
	List<T_Mail_Attach> fileList = T_Mail_Attach.dao.listInIds(model.getStr("fjid"));
	model.set("title", "转发：" + model.getStr("title"));
	model.set("status", BusinessConstant.MAIL_FORWARD);
	setAttr("fileList", fileList);
	setAttr("model", model); // 系统自动发送的催办文件，换成其他
	if (model.getStr("title").equals("【系统邮件】传阅文件过期提醒")) {
	    toErrorJson("该邮件不可转发，请点击查看打开邮件！");
	    return;
	}
	setAttr("fromUser", T_User.dao.findById(model.getInt("fromId")).getStr("name"));
	render("mail_forward.jsp");
    }

    /** 回复 */
    public void reply() {
	Integer id = getParaToInt(0);
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	T_Mail model = T_Mail.dao.getById(id);
	List<T_Mail_Attach> fileList = T_Mail_Attach.dao.listInIds(model.getStr("fjid"));
	model.set("status", BusinessConstant.MAIL_REPLY);
	model.set("title", "回复：" + model.getStr("title"));
	setAttr("fileList", fileList);
	// 系统自动发送的催办文件，换成其他
	if (model.getStr("title").equals("【系统邮件】传阅文件过期提醒")) {
	    toErrorJson("该邮件不可回复，请点击查看打开邮件！");
	    return;
	}
	setAttr("model", model);
	if (model.getInt("fromId") == loginModel.getUserId()) {
	    model.set("fromId", model.get("receiverId"));
	    setAttr("fromUser", model.get("receiver"));
	} else {
	    setAttr("fromUser", T_User.dao.findById(model.getInt("fromId")).getStr("name"));
	}
	render("mail_reply.jsp");
    }

    /** 恢复邮件 */
    public void recover() {
	try {
	    String ids = getPara("ids");
	    T_Mail.dao.recover(ids);
	    toRefreshDivJson(200, Constant.SUCCESS, "",
		    divId + ":Main/Mail/rubbish;" + treeId + ":Main/Mail/main/tree");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }
    /**
     * 统计邮件份数
     */
    /*
     * @ClearInterceptor(ClearLayer.ALL) public void count(){ LoginModel
     * loginModel = (LoginModel) getSessionAttr("loginModel"); Long
     * receiveCountTotal =
     * T_Mail.dao.getTotalReceiveCount(loginModel.getUserId()); Long sendCount =
     * T_Mail.dao.getSendCount(loginModel.getUserId()); Long draftCount =
     * T_Mail.dao.getDraftCount(loginModel.getUserId()); Long rubbishCount =
     * T_Mail.dao.getRubbishCount(loginModel.getUserId());
     * setAttr("receiveCountTotal", receiveCountTotal); setAttr("sendCount",
     * sendCount); setAttr("draftCount", draftCount); setAttr("rubbishCount",
     * rubbishCount); render("mail_count.jsp"); }
     */
}
