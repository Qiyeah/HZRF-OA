package com.oa.controller.document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.approve.T_Personal;
import com.oa.model.common.T_Attachment;
import com.oa.model.document.T_Inner_Send;
import com.oa.model.document.T_Inner_Send_Viewer;
import com.oa.model.system.log.T_Operation_Log;
import com.oa.model.system.log.T_Sms_Log;
import com.oa.util.BusinessConstant;
import com.oa.util.SMSUtil;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;
import com.zhilian.util.DateUtils;
import com.zhilian.util.StringUtil;

@RouteBind(path = "/Main/InnerSend", viewPath = "Document/InnerSend")
public class InnerSend extends BaseAssociationController {

    private String menuId = "InnerSend";

    /** 内部发文列表 */
    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "senddate";
	String orderDirection = "desc";
	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String select = "select * ";
	String sqlExceptSelect = "from t_inner_send where 1=1 ";
	try {
	    Date start = null, end = null;
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
		start = format.parse(getPara("sdate").trim());
		sqlExceptSelect += " and dc.receivedate>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		end = format.parse(getPara("sdate").trim());
		sqlExceptSelect += " and dc.receivedate<='" + edate + "'";
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
	    }
	    if (pageNumber <= 0) {
		throw new Exception();
	    }
	    if (null != getPara("orderField") && !"".equals(getPara("orderField").trim())) {
		orderField = getPara("orderField");
		setAttr("orderField", orderField);
	    }
	    if (null != getPara("orderDirection") && !"".equals(getPara("orderDirection").trim())) {
		orderDirection = getPara("orderDirection");
		setAttr("orderDirection", orderDirection);

	    }
	    if (null != start && null != end && start.after(end)) {
		throw new Exception();
	    }
	} catch (Exception e) {
	    toErrorJson("服务器存在错误，数据读取失败！");
	    return;
	}
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	Page<T_Inner_Send> page = T_Inner_Send.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);

	render("list.jsp");
    }

    public void addip() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int u_id = loginModel.getUserId();
	setAttr("u_id", u_id);
	setAttr("uname", loginModel.getUserName());
	setAttr("d_id", loginModel.getDid());
	setAttr("dname", loginModel.getDepartment());
	setAttr("localarea", "hzs");

	Date now = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = df.format(now);
	setAttr("nowday", nowday);
	setAttr("nowyear", nowday.substring(0, 4));
	render("add.jsp");
    }

    public void add() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	T_Inner_Send temp = null;
	boolean flag = false;
	try {
	    temp = getModel(T_Inner_Send.class);
	    String docnotemp = temp.getStr("docno");
	    docnotemp = docnotemp.replace(" ", "");
	    if (!docnotemp.equals("无文号")) {
		List<T_Inner_Send> list = T_Inner_Send.dao.getListByDocNo(docnotemp);
		if (list.size() > 0) {
		    toErrorJson("该文号已存在，请检查后重新提交！");
		    return;
		}
	    }

	    String[] tempAttachmentIds = getParaValues("fjid");
	    List<Object> attachmentIds = new ArrayList<>();
	    // 临时ID转换
	    if (null != tempAttachmentIds && tempAttachmentIds.length > 0) {
		attachmentIds = new ArrayList<>(tempAttachmentIds.length);
		for (String tempId : tempAttachmentIds) {
		    if (tempId.startsWith(BusinessConstant.TEMP_ID_PREFIX)) {
			T_Attachment attachment = T_Attachment.dao.findByTempId(tempId);
			if (null != attachment) {
			    attachmentIds.add(String.valueOf(attachment.getInt("id")));
			}
		    } else {
			attachmentIds.add(tempId);
		    }
		}
	    } else {
		toErrorJson("请上传文件！");
		return;
	    }
	    temp.set("fjid", StringUtil.combine(attachmentIds));

	    temp.set("docno", docnotemp);
	    temp.set("receiver", getPara("param.userId"));
	    temp.set("unames", getPara("param.userName"));
	    flag = temp.save();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    // 接收人不为空时发送短信
	    if (StrKit.notBlank(temp.getStr("receiver"))) {
		String users = temp.getStr("unames");
		String mobile = T_Personal.dao.getMobileByUsers(temp.getStr("receiver"));
		String message = "您有一个新的内部发文需要收阅";
		String result = SMSUtil.sendsms(mobile, message);
		new T_Sms_Log(users, mobile, message, result, 0);
	    }
	    // 记录日记
	    new T_Operation_Log(loginModel.getUserId(), "新增了内部发文：" + temp.getStr("docno"), "InnerSend");
	    toBJUIJson(Constant.STATUS_CODE_OK, "内部发文成功，等待收阅！", menuId, "", "", "true", "");
	}
    }

    public void updateip() {
	int id = getParaToInt();
	T_Inner_Send dc = T_Inner_Send.dao.findById(id);
	setAttr("uname", T_User.dao.findValueById("name", dc.getInt("u_id")));
	setAttr("dname", T_Department.dao.findValueById("fname", dc.getInt("d_id")));
	setAttr("dc", dc);
	String fjid = dc.getStr("fjid");
	if (fjid != null && fjid.length() == 0) {
	    fjid = "000";
	}
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(fjid);
	setAttr("fileList", fileList);
	render("update.jsp");
    }

    public void update() {
	boolean flag = false;
	try {
	    T_Inner_Send temp = getModel(T_Inner_Send.class);
	    T_Inner_Send original = T_Inner_Send.dao.findById(temp.getInt("id"));
	    String docnotemp = temp.getStr("docno");
	    docnotemp = docnotemp.replace(" ", "");

	    if (!docnotemp.equals(original.getStr("docno")) && !docnotemp.equals("无文号")) {
		List<T_Inner_Send> list = T_Inner_Send.dao.getListByDocNo(docnotemp);
		if (list.size() > 0) {
		    toErrorJson("该文号已存在，请检查后重新提交！");
		    return;
		}
	    }
	    if (temp.getStr("security") == null) {
		toErrorJson("请选择秘密等级！");
		return;
	    }
	    String[] tempAttachmentIds = getParaValues("fjid");
	    List<Object> attachmentIds = new ArrayList<>();
	    // 临时ID转换
	    if (null != tempAttachmentIds && tempAttachmentIds.length > 0) {
		attachmentIds = new ArrayList<>(tempAttachmentIds.length);
		for (String tempId : tempAttachmentIds) {
		    if (tempId.startsWith(BusinessConstant.TEMP_ID_PREFIX)) {
			T_Attachment attachment = T_Attachment.dao.findByTempId(tempId);
			if (null != attachment) {
			    attachmentIds.add(String.valueOf(attachment.getInt("id")));
			}
		    } else {
			attachmentIds.add(tempId);
		    }
		}
	    } else {
		toErrorJson("请上传文件！");
		return;
	    }
	    String attachmentIdStr = StringUtil.combine(attachmentIds);
	    temp.set("fjid", attachmentIdStr);
	    temp.set("receiver", getPara("param.userId"));
	    temp.set("unames", getPara("param.userName"));
	    flag = temp.update();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    toBJUIJson(Constant.STATUS_CODE_OK, "内部发文修改成功！", menuId, "", "", "true", "");
	}
    }

    /** 删除内部发文 */
    public void delete() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int id = getParaToInt();
	T_Inner_Send dc = T_Inner_Send.dao.findById(id);
	dc.delete();
	// 记录日记
	new T_Operation_Log(loginModel.getUserId(), "删除了内部发文：" + dc.getStr("docno"), "InnerSend");
	toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
    }

    /** 查阅列表 */
    public void readList() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int userid = loginModel.getUserId();
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
	String select = "select * ";
	String sqlExceptSelect = "from t_inner_send where (','+receiver+',') like '%," + userid + ",%' ";
	if (null != stitle) {
	    sqlExceptSelect += " and title like '%" + stitle + "%'";
	    setAttr("stitle", stitle);
	}
	if (null != sunit) {
	    sqlExceptSelect += " and unit like '%" + sunit + "%'";
	    setAttr("sunit", sunit);
	}
	if (null != sdocno) {
	    sqlExceptSelect += " and docno like '%" + sdocno + "%'";
	    setAttr("sdocno", sdocno);
	}
	if (null != sdate) {
	    sqlExceptSelect += " and senddate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and senddate<='" + edate + "'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by senddate desc";
	Page<T_Inner_Send> page = T_Inner_Send.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	for (int i = 0; i < page.getList().size(); i++) {
	    T_Inner_Send model = page.getList().get(i);
	    if (T_Inner_Send_Viewer.isReaded(userid, model.getInt("id"))) {
		model.set("status", "1");
	    } else {
		model.set("status", "0");
	    }
	}
	setAttr("page", page);

	render("readlist.jsp");
    }

    /** 查看详情 */
    @Clear
    public void view() {
	int id = getParaToInt();
	T_Inner_Send dc = T_Inner_Send.dao.findById(id);
	int doc_id = dc.getInt("id");
	setAttr("dc", dc);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	setAttr("fileList", fileList);
	if (null != dc.get("u_id")) {
	    int dc_u_id = dc.getInt("u_id");
	    setAttr("uname", T_User.dao.findValueById("name", dc_u_id));
	}
	if (null != dc.get("d_id")) {
	    int dc_d_id = dc.getInt("d_id");
	    setAttr("dname", T_Department.dao.findValueById("fname", dc_d_id));
	}
	// 添加阅读记录
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int userid = loginModel.getUserId();
	String username = loginModel.getUserName();
	if (!T_Inner_Send_Viewer.isReaded(userid, doc_id)) {
	    T_Inner_Send_Viewer model = new T_Inner_Send_Viewer();
	    model.set("doc_id", doc_id);
	    model.set("uname", username);
	    model.set("viewTime", DateUtils.getNowTime());
	    model.set("u_id", userid);
	    model.save();
	}

	render("view.jsp");
    }

    /** 查看详情 */
    @Clear
    public void viewerList() {
	int id = getParaToInt();
	T_Inner_Send dc = T_Inner_Send.dao.findById(id);
	String[] userids = dc.getStr("receiver").split(",");
	setAttr("userids", userids);
	String[] usernames = dc.getStr("unames").split(",");
	setAttr("usernames", usernames);
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(dc.getStr("fjid"));
	setAttr("fileList", fileList);

	List<T_Inner_Send_Viewer> viewerlist = T_Inner_Send_Viewer.getListByDocId(id);
	setAttr("viewerlist", viewerlist);

	render("viewerlist.jsp");
    }

}
