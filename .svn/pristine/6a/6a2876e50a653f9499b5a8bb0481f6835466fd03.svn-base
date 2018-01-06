package com.oa.controller.system.office;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.aop.Clear;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.model.LoginModel;

@RouteBind(path = "Main/Office", viewPath = "System/Workflow/Office")
public class Office extends BaseAssociationController {

    /** 编辑正文 */
    @Clear
    public void edit() {
	String mRecordID = getPara("RecordID");
	String mTemplate = getPara("Template");
	String mFileType = getPara("FileType");
	String mEditType = getPara("EditType");
	String mShowType = getPara("ShowType");
	String mFilePath = getPara("FilePath");
	String title = getPara("Title");
	String docno = getPara("Docno");
	String send1 = getPara("Send1");
	String send2 = getPara("Send2");
	String num = getPara("Num");
	String param = "";
	if (null != mRecordID && !"".equals(mRecordID)) {
	    param += "&RecordID=" + mRecordID;
	}
	if (null != mTemplate && !"".equals(mTemplate)) {
	    param += "&Template=" + mTemplate;
	}
	if (null != mFileType && !"".equals(mFileType)) {
	    param += "&FileType=" + mFileType;
	}
	if (null != mEditType && !"".equals(mEditType)) {
	    param += "&EditType=" + mEditType;
	}
	if (null != mShowType && !"".equals(mShowType)) {
	    param += "&ShowType=" + mShowType;
	}
	if (null != mFilePath && !"".equals(mFilePath)) {
	    param += "&FilePath=" + mFilePath;
	}
	if (null != title && !"".equals(title)) {
	    param += "&Title=" + title;
	}
	if (null != docno && !"".equals(docno)) {
	    param += "&Docno=" + docno;
	}
	if (null != send1 && !"".equals(send1)) {
	    param += "&Send1=" + send1;
	}
	if (null != send2 && !"".equals(send2)) {
	    param += "&Send2=" + send2;
	}
	if (null != num && !"".equals(num)) {
	    param += "&Num=" + num;
	}
	if (param.length() > 0)
	    param = "?" + param.substring(1);
	setAttr("url", "Main/Office/editdoc" + param);
	render("edit.jsp");
    }

    @Clear
    public void editdoc() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String mUserName = loginModel.getUserName();

	String mSubject = null;
	String mStatus = null;
	String mAuthor = null;
	String mFileName = null;
	String mFileDate = null;
	String mHTMLPath = "";

	String mDisabled = "";
	String mDisabledSave = "";
	String mWord = "";
	String mExcel = "";

	String mHttpUrlName = getRequest().getRequestURI();
	String mScriptName = getRequest().getServletPath();
	String mServerName = "Main/WebOffice/ExecuteRun";

	String mServerUrl = "http://" + getRequest().getServerName() + ":" + getRequest().getServerPort()
		+ getRequest().getContextPath() + "/" + mServerName;
	String mHttpUrl = "http://" + getRequest().getServerName() + ":" + getRequest().getServerPort()
		+ mHttpUrlName.substring(0, mHttpUrlName.lastIndexOf(mScriptName)) + "/";

	String mRecordID = getPara("RecordID");
	String mTemplate = getPara("Template");
	String mFileType = getPara("FileType");
	String mEditType = getPara("EditType");
	String mShowType = getPara("ShowType");
	String mFilePath = getPara("FilePath");

	if (mRecordID == null) {
	    mRecordID = "0";
	}
	if (mEditType == null || mEditType == "") {
	    mEditType = "1,0";
	}
	if (mShowType == null || mShowType == "") {
	    mShowType = "1";
	}
	if (mFileType == null || mFileType == "") {
	    mFileType = ".doc";
	}
	if (mUserName == null || mUserName == "") {
	    mUserName = "匿名";
	}
	if (mTemplate == null) {
	    mTemplate = "";
	}
	if (mEditType == "0,0") {
	    mDisabled = "disabled";
	    mDisabledSave = "disabled";
	} else {
	    mDisabled = "";
	}
	mFileName = mRecordID + mFileType; // 取得完整的文档名称
	if (mFileType.equalsIgnoreCase(".doc") || mFileType.equalsIgnoreCase(".wps")) {
	    mWord = "";
	    mExcel = "disabled";
	} else if (mFileType == ".xls") {
	    mWord = "disabled";
	    mExcel = "";
	} else {
	    mDisabled = "disabled";
	}
	setAttr("mSubject", mSubject);
	setAttr("mStatus", mStatus);
	setAttr("mAuthor", mAuthor);
	setAttr("mFileName", mFileName);
	setAttr("mFileDate", mFileDate);
	setAttr("mHTMLPath", mHTMLPath);
	setAttr("mDisabled", mDisabled);
	setAttr("mDisabledSave", mDisabledSave);
	setAttr("mWord", mWord);
	setAttr("mExcel", mExcel);

	setAttr("mHttpUrlName", mHttpUrlName);
	setAttr("mScriptName", mScriptName);
	setAttr("mServerName", mServerName);
	setAttr("mServerUrl", mServerUrl);
	setAttr("mHttpUrl", mHttpUrl);
	setAttr("mRecordID", mRecordID);
	setAttr("mTemplate", mTemplate);
	setAttr("mFileType", mFileType);
	setAttr("mEditType", mEditType);
	setAttr("mShowType", mShowType);
	setAttr("mFilePath", mFilePath);
	setAttr("mUserName", mUserName);

	String title = getPara("Title");
	String docno = getPara("Docno");
	String send1 = getPara("Send1");
	String send2 = getPara("Send2");
	String num = getPara("Num");
	setAttr("title", title);
	setAttr("docno", docno);
	setAttr("send1", send1);
	setAttr("send2", send2);
	setAttr("num", num);

	render("editdoc.jsp");
    }

    /** 查看正文 */
    @Clear
    public void view() {
	String mRecordID = getPara("RecordID");
	String mTemplate = getPara("Template");
	String mFileType = getPara("FileType");
	String mEditType = getPara("EditType");
	String mShowType = getPara("ShowType");
	String param = "";
	if (null != mRecordID && !"".equals(mRecordID)) {
	    param += "&RecordID=" + mRecordID;
	}
	if (null != mTemplate && !"".equals(mTemplate)) {
	    param += "&Template=" + mTemplate;
	}
	if (null != mFileType && !"".equals(mFileType)) {
	    param += "&FileType=" + mFileType;
	}
	if (null != mEditType && !"".equals(mEditType)) {
	    param += "&EditType=" + mEditType;
	}
	if (null != mShowType && !"".equals(mShowType)) {
	    param += "&ShowType=" + mShowType;
	}
	if (param.length() > 0)
	    param = "?" + param.substring(1);
	setAttr("mRecordID", mRecordID);
	setAttr("mTemplate", mTemplate);
	setAttr("mFileType", mFileType);
	setAttr("mEditType", mEditType);
	setAttr("mShowType", mShowType);
	setAttr("url", "Main/Office/viewdoc" + param);
	render("edit.jsp");
    }

    @Clear
    public void viewdoc() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	String mUserName = loginModel.getUserName();

	String mSubject = null;
	String mStatus = null;
	String mAuthor = null;
	String mFileName = null;
	String mFileDate = null;
	String mHTMLPath = "";

	String mDisabled = "";
	String mDisabledSave = "";
	String mWord = "";
	String mExcel = "";

	// 自动获取OfficeServer和OCX文件完整URL路径
	String mHttpUrlName = getRequest().getRequestURI();
	String mScriptName = getRequest().getServletPath();
	String mServerName = "Main/WebOffice/ExecuteRun";

	String mServerUrl = "http://" + getRequest().getServerName() + ":" + getRequest().getServerPort()
		+ getRequest().getContextPath() + "/" + mServerName;
	String mHttpUrl = "http://" + getRequest().getServerName() + ":" + getRequest().getServerPort()
		+ mHttpUrlName.substring(0, mHttpUrlName.lastIndexOf(mScriptName)) + "/";

	String mRecordID = getPara("RecordID");
	String mTemplate = getPara("Template");
	String mFileType = getPara("FileType");
	String mEditType = getPara("EditType");
	String mShowType = getPara("ShowType");

	if (mRecordID == null) {
	    mRecordID = "0";
	}
	if (mEditType == null || mEditType == "") {
	    mEditType = "1,0";
	}
	if (mShowType == null || mShowType == "") {
	    mShowType = "1";
	}
	if (mFileType == null || mFileType == "") {
	    mFileType = ".doc";
	}
	if (mUserName == null || mUserName == "") {
	    mUserName = "匿名";
	}
	if (mTemplate == null) {
	    mTemplate = "";
	}
	if (mEditType == "0,0") {
	    mDisabled = "disabled";
	    mDisabledSave = "disabled";
	} else {
	    mDisabled = "";
	}
	mFileName = mRecordID + mFileType; // 取得完整的文档名称
	if (mFileType.equalsIgnoreCase(".doc") || mFileType.equalsIgnoreCase(".wps")) {
	    mWord = "";
	    mExcel = "disabled";
	} else if (mFileType == ".xls") {
	    mWord = "disabled";
	    mExcel = "";
	} else {
	    mDisabled = "disabled";
	}
	setAttr("mSubject", mSubject);
	setAttr("mStatus", mStatus);
	setAttr("mAuthor", mAuthor);
	setAttr("mFileName", mFileName);
	setAttr("mFileDate", mFileDate);
	setAttr("mHTMLPath", mHTMLPath);
	setAttr("mDisabled", mDisabled);
	setAttr("mDisabledSave", mDisabledSave);
	setAttr("mWord", mWord);
	setAttr("mExcel", mExcel);

	setAttr("mHttpUrlName", mHttpUrlName);
	setAttr("mScriptName", mScriptName);
	setAttr("mServerName", mServerName);
	setAttr("mServerUrl", mServerUrl);
	setAttr("mHttpUrl", mHttpUrl);
	setAttr("mRecordID", mRecordID);
	setAttr("mTemplate", mTemplate);
	setAttr("mFileType", mFileType);
	setAttr("mEditType", mEditType);
	setAttr("mShowType", mShowType);
	setAttr("mUserName", mUserName);
	render("viewdoc.jsp");
    }

    /** 保存正文 （无用） */
    @Clear
    public void savedoc() {
	toBJUIJson(200, "编辑正文成功！", "", "", "", "true", "");
    }

    /**
     * 功能或作用：格式化日期时间
     * 
     * @param DateValue
     *            输入日期或时间
     * @param DateType
     *            格式化 EEEE是星期, yyyy是年, MM是月, dd是日, HH是小时, mm是分钟, ss是秒
     * @return 输出字符串
     */
    public String FormatDate(String DateValue, String DateType) {
	String Result;
	SimpleDateFormat formatter = new SimpleDateFormat(DateType);
	try {
	    Date mDateTime = formatter.parse(DateValue);
	    Result = formatter.format(mDateTime);
	} catch (Exception ex) {
	    Result = ex.getMessage();
	}
	if (Result.equalsIgnoreCase("1900-01-01")) {
	    Result = "";
	}
	return Result;
    }

}
