package com.oa.controller.document;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.oa.controller.BaseAssociationController;
import com.oa.controller.export.ExportDocAchieve;
import com.oa.model.approve.T_Personal;
import com.oa.model.common.T_Attachment;
import com.oa.model.document.T_Doc_Achieve;
import com.oa.model.document.T_Doc_Receive;
import com.oa.model.system.log.T_Operation_Log;
import com.oa.model.system.log.T_Sms_Log;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.system.workflow.T_Workitem;
import com.oa.model.system.workflow.T_Wprocess;
import com.oa.util.BusinessConstant;
import com.oa.util.ExportExcel;
import com.oa.util.SMSUtil;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Common_Detail;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;
import com.zhilian.util.StringUtil;

@RouteBind(path = "/Main/Docachieve", viewPath = "Document/Docachieve")
public class Docachieve extends BaseAssociationController {

    private String menuId = "Docachieve";

    @Clear
    public void main() {// 收件登记列表
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "dc.id";
	String orderDirection = "desc";
	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null;
	String type = null, status = null;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String select = "select dc.*, u.name as uname, re.receive1 as dpname ";
	String sqlExceptSelect = "from t_doc_achieve dc left join t_user u on dc.u_id=u.id left join t_doc_receive re on re.achieveid=dc.id where 1=1 ";
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
	    if (null != getPara("type") && !"".equals(getPara("type").trim())) {
		type = getPara("type");
		sqlExceptSelect += " and dc.type='" + type + "'";
		setAttr("type", type);
	    }
	    if (null != getPara("status") && !"".equals(getPara("status").trim())) {
		status = getPara("status");
		sqlExceptSelect += " and dc.status ='" + status + "'";
		setAttr("status", status);
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

	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");

	int d_id = loginModel.getDid();
	// 办公室职员可以查看所有文件，其他可是职员只能查看已经分发的
	if (d_id != 2) {
	    sqlExceptSelect += " and dc.status ='1'";
	}

	sqlExceptSelect += " order by " + orderField + " " + orderDirection;

	Page<T_Doc_Achieve> page = T_Doc_Achieve.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);
	setAttr("details", T_Common_Detail.getListByKey("sourcetype"));
	render("list.jsp");
    }

    @SuppressWarnings("unchecked")
    @Clear
    public void export() { // excel导出
	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null;
	String type = null, status = null;
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
	    if (null != getPara("type") && !"".equals(getPara("type").trim())) {
		type = getPara("type");
	    }
	    if (null != getPara("status") && !"".equals(getPara("status").trim())) {
		status = getPara("status");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	String select = "select dc.*, u.name as uname, dp.fname as dpname ";
	String sqlExceptSelect = "from t_doc_achieve dc left join t_user u on dc.u_id=u.id left join t_department dp on dc.d_id=dp.id where 1=1 ";
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
	    sqlExceptSelect += " and dc.receivedate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and dc.receivedate<='" + edate + "'";
	    setAttr("edate", edate);
	}
	if (null != type) {
	    sqlExceptSelect += " and dc.type='" + type + "'";
	    setAttr("type", type);
	}
	if (null != status && !"9".equals(status)) {
	    sqlExceptSelect += " and dc.status ='" + status + "'";
	    setAttr("status", status);
	}
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int d_id = loginModel.getDid();
	// 办公室职员可以查看所有文件，其他可是职员只能查看已经分发的
	if (d_id != 2) {
	    sqlExceptSelect += " and dc.status ='1'";
	}
	sqlExceptSelect += " order by dc.id desc";

	List<T_Doc_Achieve> list = T_Doc_Achieve.dao.find(select + sqlExceptSelect);

	HttpServletResponse response = getResponse();
	response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
	response.setContentType("application/octet-stream");
	try {
	    OutputStream os = response.getOutputStream();
	    String title = "收件登记表";
	    String[] headers = new String[] { "序号", "收文日期", "来文单位", "文号", "文件标题", "秘密等级", "紧急程度", "份数", "承办科室", "备考",
		    "状态" };
	    ExportExcel<T_Doc_Achieve> ex = new ExportDocAchieve();
	    ex.exportExcel(title, headers, list, os, null);
	    renderNull();
	    os.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Clear
    public void addip() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int u_id = loginModel.getUserId();
	setAttr("u_id", u_id);
	setAttr("uname", loginModel.getUserName());
	setAttr("d_id", loginModel.getDid());
	setAttr("dname", loginModel.getDepartment());
	setAttr("localarea", "hys");

	Date now = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String nowday = df.format(now);
	setAttr("nowday", nowday);
	setAttr("nowyear", nowday.substring(0, 4));
	setAttr("details", T_Common_Detail.getListByKey("sourcetype"));
	render("add.jsp");
    }

    @Clear
    public void add() {// 收件登记
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	T_Doc_Achieve temp = null;
	String degree = "";
	boolean flag = false;
	try {
	    temp = getModel(T_Doc_Achieve.class);
	    degree = temp.getStr("degree");
	    String docnotemp = temp.getStr("docno");
	    docnotemp = docnotemp.replace(" ", "");
	    if (!docnotemp.equals("无文号")) {
		List<T_Doc_Achieve> list = T_Doc_Achieve.dao.getListByDocNo(docnotemp);
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
	    temp.set("receiver", tempAttachmentIds.length);// 判断收文长度
	    temp.set("unit", getPara("achieveDepart.unit"));
	    flag = temp.save();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    // 向涂科发送短信
	    T_Wprocess wp = T_Wprocess.dao.getProcessByFlow("docreceive"); // 寻找收文流程
	    int itemid = wp.getInt("beginstep"); // 锁定第一环节ID
	    T_Wactive wa = T_Wactive.dao.findFirst("select * from t_wactive where id=?", itemid); // 获取环节配置
	    if (wa.getStr("sendsms").equals("1")) { // 如果发送短信
		String mobile = T_Personal.dao.getMobileByUsers("85");
		String message = "";
		if (degree.equals("2")) {
		    message = "您有一个特急件，须尽快登录OA系统处理！";
		} else if (degree.equals("1")) {
		    message = "您有一个平急件，请及时登录OA系统处理！";
		} else {
		    message = "您有一个新文件需要处理。";
		}
		String result = SMSUtil.sendsms(mobile, message);
		new T_Sms_Log("涂序庆", mobile, message, result, 0);
	    }
	    new T_Operation_Log(loginModel.getUserId(), "新增了收文：" + temp.getStr("docno"), "Docachieve");// 记录日记
	    toBJUIJson(Constant.STATUS_CODE_OK, "资料收取成功，等待登记分发！", menuId, "", "", "true", "");
	}
    }

    @Clear
    public void updateip() {
	int id = getParaToInt();
	T_Doc_Achieve dc = T_Doc_Achieve.dao.findById(id);
	setAttr("uname", T_User.dao.findValueById("name", dc.getInt("u_id")));
	setAttr("dname", T_Department.dao.findValueById("fname", dc.getInt("d_id")));
	setAttr("dc", dc);
	// 允许修改进入流程的文件
	// if (!dc.getStr("status").equals("0")) {
	// toErrorJson("收文登记已进入审批流程，不可修改！");
	// return;
	// }
	String fjid = dc.getStr("fjid");
	if (fjid != null && fjid.length() == 0) {
	    fjid = "000";
	}
	List<T_Attachment> fileList = T_Attachment.dao.listInIds(fjid);
	setAttr("fileList", fileList);
	setAttr("details", T_Common_Detail.getListByKey("sourcetype"));
	List<T_Department> deparments = T_Department.dao
		.find("select * from t_department where status='0' and pid = 1");
	setAttr("departs", deparments);
	render("update.jsp");
    }

    public void update() {// 编辑
	boolean flag = false;
	try {
	    LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	    T_Doc_Achieve temp = getModel(T_Doc_Achieve.class);
	    T_Doc_Achieve original = T_Doc_Achieve.dao.findById(temp.getInt("id"));
	    String docnotemp = temp.getStr("docno");
	    docnotemp = docnotemp.replace(" ", "");

	    if (!docnotemp.equals(original.getStr("docno")) && !docnotemp.equals("无文号")) {
		List<T_Doc_Achieve> list = T_Doc_Achieve.dao.getListByDocNo(docnotemp);
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
	    temp.set("receiver", loginModel.getUserId());
	    temp.set("unit", getPara("achieveDepart.unit"));
	    temp.set("docno", docnotemp);
	    flag = temp.update();
	    int id = temp.getInt("id");
	    T_Doc_Receive dc = T_Doc_Receive.dao.findFirst("select * from t_doc_receive where achieveid=?", id);
	    if (dc != null) {
		dc.set("unit", temp.get("unit"));
		dc.set("docno", temp.get("docno"));
		dc.set("title", temp.get("title"));
		dc.set("degree", temp.get("degree"));
		dc.set("fjid", attachmentIdStr);
		flag = dc.update();
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	if (!flag) {
	    toErrorJson("数据处理出现错误！请检查后重新提交！");
	} else {
	    toBJUIJson(Constant.STATUS_CODE_OK, "资料修改成功，等待登记分发！", menuId, "", "", "true", "");
	}
    }

    @Clear
    public void detail() { // 查看详情
	int id = getParaToInt();
	T_Doc_Achieve dc = T_Doc_Achieve.dao.findById(id);
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
	render("detail.jsp");
    }

    /** 分办情况列表 */
    public void achievelist() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String stitle = null, sunit = null, sdocno = null, sdate = null, edate = null;
	String type = null, status = null;
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
	    if (null != getPara("type") && !"".equals(getPara("type").trim())) {
		type = getPara("type");
	    }
	    if (null != getPara("status") && !"".equals(getPara("status").trim())) {
		status = getPara("status");
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
	String select = "select dc.*, u.name as uname, dp.fname as dpname ";
	String sqlExceptSelect = "from t_doc_achieve dc left join t_user u on dc.u_id=u.id left join t_department dp on dc.receive1=dp.id where 1=1 ";
	sqlExceptSelect += " and dc.status = 0";
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
	    sqlExceptSelect += " and dc.receivedate>='" + sdate + "'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and dc.receivedate<='" + edate + "'";
	    setAttr("edate", edate);
	}
	if (null != type) {
	    sqlExceptSelect += " and dc.type='" + type + "'";
	    setAttr("type", type);
	}
	if (null != status && !"9".equals(status)) {
	    sqlExceptSelect += " and dc.status ='" + status + "'";
	    setAttr("status", status);
	}
	sqlExceptSelect += " order by dc.id desc";
	Page<T_Doc_Achieve> page = T_Doc_Achieve.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	HashMap<String, String> userHm = T_User.dao.hashMapByIds("name");
	setAttr("userHM", userHm);
	setAttr("details", T_Common_Detail.getListByKey("sourcetype"));

	render("achievelist.jsp");
    }

    @Clear
    public void delete() { // 删除
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int id = getParaToInt();
	T_Doc_Achieve dc = T_Doc_Achieve.dao.findById(id);
	if (!dc.get("status").equals("0")) {
	    toErrorJson("收文登记已进入审批流程，不可删除！");
	    return;
	} else {
	    dc.delete();
	    new T_Operation_Log(loginModel.getUserId(), "删除了收文：" + dc.getStr("docno"), "Docachieve");// 记录日记
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	}
    }

    @Clear
    @Before(Tx.class)
    public void deleteWithFLow() { // 删除
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int id = getParaToInt();
	T_Doc_Achieve dc = T_Doc_Achieve.dao.findById(id);
	try {
	    List<T_Doc_Receive> list = T_Doc_Receive.dao.getListByAchieveId(id);
	    int pid = 0;
	    for (T_Doc_Receive dr : list) {
		pid = dr.getInt("pid");
		T_Workflow.dao.deleteById(pid);
		T_Workitem.dao.deleteBySqlwhere(" where pid=" + pid);
		dr.delete();
	    }
	    dc.delete();
	    new T_Operation_Log(loginModel.getUserId(), "删除了收文：" + dc.getStr("docno"), "Docachieve");// 记录日记
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("删除收文登记并删除收文审批流程出错！");
	}

    }

    @SuppressWarnings("static-access")
    @Clear
    public void readabilityList() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "dc.id";// 排序字段名
	String orderDirection = "desc";// asc || desc
	Date dNow = new Date(); // 当前时间
	Date dBefore = new Date();
	Calendar calendar = Calendar.getInstance(); // 得到日历
	calendar.setTime(dNow);// 把当前时间赋给日历
	calendar.add(calendar.MONTH, -3); // 设置为前3月
	dBefore = calendar.getTime(); // 得到前3月的时间
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
	String defaultStartDate = sdf.format(dBefore); // 格式化前3月的时间
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
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	String select = "select wf.*,  dc.unit as unit, dc.title as doctitle, dc.docno as docno ";
	String sqlExceptSelect = "from t_workflow wf left join t_doc_receive dc on wf.id = dc.pid "
		+ " where wf.flowform like 'docreceive' and wf.isend='1' and wf.isnormalend > 0 and dc.doflag=1 and wf.endtime > '"
		+ defaultStartDate + "'";
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
	    sqlExceptSelect += " and wf.starttime>='" + sdate + " 00:00:00'";
	    setAttr("sdate", sdate);
	}
	if (null != edate) {
	    sqlExceptSelect += " and wf.starttime<='" + edate + " 23:59:59'";
	    setAttr("edate", edate);
	}
	sqlExceptSelect += " order by " + orderField + " " + orderDirection + "";
	Page<T_Workflow> page = T_Workflow.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	setAttr("page", page);
	render("readabilityList.jsp");
    }

    @Clear
    public void photograph() {
	render("photograph.jsp");
    }
}
