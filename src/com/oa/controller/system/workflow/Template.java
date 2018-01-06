package com.oa.controller.system.workflow;

import java.util.HashMap;

import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Page;
import com.oa.model.system.office.T_Template_File;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.controller.BaseController;
import com.zhilian.model.LoginModel;

@RouteBind(path = "Main/Template", viewPath = "System/Workflow/Template")
public class Template extends BaseController {

    private static String menuId = "Template";

    public void main() {
	Integer pageNum = getParaToInt("pageNum");
	Integer numPerPage = getParaToInt("numPerPage");
	String sname = getPara("sname");
	HashMap<String, String> paraHm = new HashMap<String, String>();
	paraHm.put("filename,like", sname);
	Page<T_Template_File> page = T_Template_File.dao.page(pageNum, numPerPage, paraHm);
	setAttr("sname", sname);
	setAttr("page", page);
	render("main.jsp");
    }

    @Clear
    public void update() {
	String mRecordID = getPara("RecordID");
	String mFileType = getPara("FileType");
	String mEditType = getPara("EditType");
	String param = "";
	if (null != mRecordID && !"".equals(mRecordID)) {
	    param += "&RecordID=" + mRecordID;
	}
	if (null != mFileType && !"".equals(mFileType)) {
	    param += "&FileType=" + mFileType;
	}
	if (null != mEditType && !"".equals(mEditType)) {
	    param += "&EditType=" + mEditType;
	}
	if (param.length() > 0)
	    param = "?" + param.substring(1);
	setAttr("mRecordID", mRecordID);
	setAttr("mFileType", mFileType);
	setAttr("mEditType", mEditType);
	setAttr("url", "Main/Template/edit" + param);
	render("update.jsp");
    }

    @Clear
    public void edit() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String mUserName = loginModel.getUserName();

	String mHttpUrlName = getRequest().getRequestURI();
	String mScriptName = getRequest().getServletPath();
	String mServerName = "Main/WebOffice/ExecuteRun";
	String mServerUrl = "http://" + getRequest().getServerName() + ":" + getRequest().getServerPort()
		+ getRequest().getContextPath() + "/" + mServerName;

	String mRecordID = getPara("RecordID");
	String mFileType = getPara("FileType");
	String mEditType = getPara("EditType");
	String mFileName = "";
	String mDescript = "";

	if (mEditType == null) {
	    mEditType = "1,1";
	}
	if (mFileType == null) {
	    mFileType = ".doc";
	}
	if (mUserName == null) {
	    mUserName = "管理员";
	}
	if (mRecordID == null) {
	    mRecordID = "";
	}

	T_Template_File temp = T_Template_File.getTemplateByRecordId(mRecordID);
	if (temp != null) {
	    mRecordID = temp.getStr("RecordID");
	    mFileName = temp.getStr("FileName");
	    mFileType = temp.getStr("FileType");
	    mDescript = temp.getStr("Descript");
	} else {
	    java.util.Date dt = new java.util.Date();
	    long lg = dt.getTime();
	    Long ld = new Long(lg);
	    // 初始化值
	    mRecordID = ld.toString();
	    mFileName = "公文模版" + mFileType;
	    mDescript = "发文公文模版";
	}

	setAttr("mHttpUrlName", mHttpUrlName);
	setAttr("mScriptName", mScriptName);
	setAttr("mServerName", mServerName);
	setAttr("mServerUrl", mServerUrl);
	setAttr("mRecordID", mRecordID);
	setAttr("mFileName", mFileName);
	setAttr("mFileType", mFileType);
	setAttr("mEditType", mEditType);
	setAttr("mUserName", mUserName);
	setAttr("mDescript", mDescript);
	render("edit.jsp");
    }

    @Clear
    public void save() {
	String mRecordID = getPara("RecordID");
	String mFileName = getPara("FileName");
	String mDescript = getPara("Descript");
	T_Template_File temp = T_Template_File.getTemplateByRecordId(mRecordID);
	if (temp != null) {
	    temp.set("filename", mFileName);
	    temp.set("descript", mDescript);
	    temp.update();
	}
	toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
    }

    @Clear
    public void delete() {
	String id = getPara();
	T_Template_File.dao.deleteById(id);
	toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
    }

}
