package com.oa.controller.system.office;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.upload.UploadFile;
import com.oa.controller.BaseAssociationController;
import com.oa.model.system.office.T_Bookmark;
import com.oa.model.system.office.T_Doc_File;
import com.oa.model.system.office.T_Doc_Signature;
import com.oa.model.system.office.T_Doc_Version;
import com.oa.model.system.office.T_Signature;
import com.oa.model.system.office.T_Template_Bookmark;
import com.oa.model.system.office.T_Template_File;
import com.oa.model.system.workflow.T_Template;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;

import DBstep.iMsgServer2000;

@Clear
@RouteBind(path = "Main/WebOffice", viewPath = "System/Workflow/WebOffice")
public class iWebOffice extends BaseAssociationController {

    private int mFileSize;
    private byte[] mFileBody;
    private String mFileName;
    private String mFileType;
    private String mFileDate;
    private String mFileID;
    private String mRecordID;
    private String mTemplate;
    private String mDateTime;
    private String mOption;
    private String mMarkName;
    private String mPassword;
    private String mMarkList;
    private String mBookmark;
    private String mDescript;
    private String mHostName;
    private String mMarkGuid;
    private String mCommand;
    private String mContent;
    private String mHtmlName;
    private String mDirectory;
    private String mFilePath;
    private String mUserName;
    private int mColumns;
    private int mCells;
    @SuppressWarnings("unused")
    private String mMyDefine1;
    @SuppressWarnings("unused")
    private String mLocalFile;
    private String mRemoteFile;
    private String mLabelName;
    private String mImageName;
    private String mTableContent;
    // 打印控制
    private String mOfficePrints;
    private int mCopies;
    // 自定义信息传递
    private String mInfo;
    private iMsgServer2000 MsgObj;

    Date now = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String nowtime = formatter.format(now);

    /** 正文管理 */
    private boolean LoadFile() {
	boolean mResult = false;
	try {
	    T_Doc_File temp = T_Doc_File.getDocFileByRecordId(mRecordID);
	    if (temp != null) {
		mFileBody = temp.getBytes("filebody");
	    } else {
		mFileBody = null;
	    }
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    private boolean SaveFile() {
	boolean mResult = false;
	try {
	    T_Doc_File df = T_Doc_File.getDocFileByRecordId(mRecordID);
	    T_Doc_File temp = new T_Doc_File();
	    temp.set("pid", Integer.parseInt(mRecordID));
	    temp.set("filename", mFileName);
	    temp.set("filetype", mFileType);
	    temp.set("filesize", mFileSize);
	    temp.set("filedate", mFileDate);
	    temp.set("filebody", mFileBody);
	    temp.set("filepath", mFilePath);
	    temp.set("username", mUserName);
	    temp.set("descript", mDescript);

	    if (df == null) {
		temp.save();
	    } else {
		temp.set("id", df.getInt("id"));
		temp.update();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    private boolean LoadTemplate() {
	boolean mResult = false;
	try {
	    T_Template_File temp = T_Template_File.getTemplateByRecordId(mTemplate);
	    if (temp != null) {
		mFileBody = temp.getBytes("filebody");
	    } else {
		mFileBody = null;
	    }
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    private boolean SaveTemplate() {
	boolean mResult = false;
	T_Template_File tf = T_Template_File.getTemplateByRecordId(mTemplate);
	T_Template_File temp = new T_Template_File();
	temp.set("recordid", mTemplate);
	temp.set("filename", mFileName);
	temp.set("filetype", mFileType);
	temp.set("filesize", mFileSize);
	temp.set("filedate", mFileDate);
	temp.set("filebody", mFileBody);
	temp.set("filepath", mFilePath);
	temp.set("username", mUserName);
	temp.set("descript", mDescript);

	if (tf == null) {
	    temp.save();
	} else {
	    temp.set("id", tf.getInt("id"));
	    temp.update();
	}
	return mResult;
    }

    /** 版本管理 */
    private boolean ListVersion() {
	boolean mResult = false;
	mFileID = "\r\n";
	mDateTime = "保存时间\r\n";
	mUserName = "用户名\r\n";
	mDescript = "版本说明\r\n";
	try {
	    List<T_Doc_Version> list = T_Doc_Version.getList(mRecordID);
	    for (int i = 0; i < list.size(); i++) {
		T_Doc_Version temp = list.get(i);
		mFileID += String.valueOf(temp.getInt("id")) + "\r\n"; // 文件号列表
		mUserName += temp.getStr("username") + "\r\n"; // 用户名列表
		mDateTime += temp.getDate("filedate").toString() + "\r\n"; // 日期列表
		mDescript += temp.getStr("descript") + "\r\n"; // 如果说明信息里有回车，则将回车变成>符号
	    }
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    private boolean LoadVersion(String mFileID) {
	boolean mResult = false;
	try {
	    T_Doc_Version temp = T_Doc_Version.dao.findById(mFileID);
	    if (temp != null) {
		mFileBody = temp.getBytes("filebody");
	    } else {
		mFileBody = null;
	    }
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    private boolean SaveVersion() {
	boolean mResult = false;
	try {
	    T_Doc_Version temp = new T_Doc_Version();
	    temp.set("pid", Integer.parseInt(mRecordID));
	    temp.set("filename", mFileName);
	    temp.set("filetype", mFileType);
	    temp.set("filesize", mFileSize);
	    temp.set("filedate", mFileDate);
	    temp.set("filebody", mFileBody);
	    temp.set("filepath", mFilePath);
	    temp.set("username", mUserName);
	    temp.set("descript", mDescript);
	    temp.save();
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    /** 书签管理 */
    private boolean ListBookmarks() {
	boolean mResult = false;
	mBookmark = "";
	mDescript = "";
	try {
	    List<T_Bookmark> list = T_Bookmark.dao.list();
	    for (int i = 0; i < list.size(); i++) {
		T_Bookmark temp = list.get(i);
		mBookmark += temp.getStr("name") + "\r\n"; // 用户名列表
		mDescript += temp.getStr("descript") + "\r\n"; // 如果说明信息里有回车，则将回车变成>符号
	    }
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    private boolean LoadBookMarks() {
	boolean mResult = false;
	try {
	    List<T_Template_Bookmark> list = T_Template_Bookmark.getList(mTemplate);
	    for (int i = 0; i < list.size(); i++) {
		T_Template_Bookmark temp = list.get(i);
		String mBookMarkName = temp.getStr("name");
		String mBookMarkValue = temp.getStr("value");
		MsgObj.SetMsgByName(mBookMarkName, mBookMarkValue);
	    }
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    private boolean SaveBookMarks() {
	boolean mResult = false;
	String mBookMarkName = "";
	int mIndex = 0;
	try {
	    T_Template_Bookmark.deleteByRid(mTemplate);
	    for (mIndex = 7; mIndex <= MsgObj.GetFieldCount() - 1; mIndex++) {
		mBookMarkName = MsgObj.GetFieldName(mIndex);
		T_Template_Bookmark temp = new T_Template_Bookmark();
		temp.set("recordid", mTemplate);
		temp.set("name", mBookMarkName);
		temp.save();
	    }
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    /** 签章管理 */
    private boolean LoadMarkList() {
	boolean mResult = false;
	mMarkList = "";
	try {
	    List<T_Signature> list = T_Signature.dao.list();
	    for (int i = 0; i < list.size(); i++) {
		T_Signature temp = list.get(i);
		mMarkList += temp.getStr("MarkName") + "\r\n";
	    }
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    // 调入签名纪录
    private boolean LoadMarkImage(String vMarkName, String vPassWord) {
	boolean mResult = false;
	try {
	    T_Signature temp = T_Signature.getSignature(vMarkName, vPassWord);
	    mFileBody = temp.getBytes("Mmarkbody");
	    mFileType = temp.getStr("marktype");
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    // 保存签名
    private boolean SaveSignature() {
	boolean mResult = false;
	try {
	    T_Doc_Signature temp = new T_Doc_Signature();
	    temp.set("pid", Integer.parseInt(mRecordID));
	    temp.set("markname", mMarkName);
	    temp.set("username", mUserName);
	    temp.set("signtime", mDateTime);
	    temp.set("hostname", mHostName);
	    temp.set("markguid", mMarkGuid);
	    temp.save();
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    // 列出所有签名
    private boolean LoadSignature() {
	boolean mResult = false;
	mMarkName = "印章名称\r\n";
	mUserName = "签名人\r\n";
	mDateTime = "签章时间\r\n";
	mHostName = "客户端IP\r\n";
	mMarkGuid = "序列号\r\n";
	try {
	    List<T_Doc_Signature> list = T_Doc_Signature.getList(mRecordID);
	    for (int i = 0; i < list.size(); i++) {
		T_Doc_Signature temp = list.get(i);
		mMarkName += temp.getStr("markname") + "\r\n"; // 文件号列表
		mUserName += temp.getStr("username") + "\r\n"; // 日期列表
		mDateTime += formatter.format(temp.getDate("signtime")) + "\r\n";
		mHostName += temp.getStr("hostname") + "\r\n";
		mMarkGuid += temp.getStr("markguid") + "\r\n";
	    }
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    /** 其他功能 */
    // 调出所对应的文本
    private boolean LoadContent() {
	boolean mResult = false;
	// 打开数据库 根据 mRecordID 或 mFileName 等信息
	// 提取文本信息付给mContent即可。
	// 本演示假设取得的文本信息如下：
	mContent = "";
	mContent += "本文的记录号：" + mRecordID + "\n";
	mContent += "本文的文件名：" + mFileName + "\n";
	mResult = true;
	return mResult;
    }

    // 保存所对应的文本
    private boolean SaveContent() {
	boolean mResult = false;
	// 打开数据库 根据 mRecordID 或 mFileName 等信息
	// 插入文本信息 mContent 里的文本到数据库中即可。
	mResult = true;
	return mResult;
    }

    // 增加行并填充表格内容
    private boolean GetWordTable() {
	boolean mResult = false;
	String strI, strN;
	mColumns = 3;
	mCells = 8;
	MsgObj.MsgTextClear();
	MsgObj.SetMsgByName("COLUMNS", String.valueOf(mColumns)); // 设置表格行
	MsgObj.SetMsgByName("CELLS", String.valueOf(mCells)); // 设置表格列
	// 该部分内容可以从数据库中读取
	try {
	    for (int i = 1; i <= mColumns; i++) {
		strI = String.valueOf(i);
		for (int n = 1; n <= mCells; n++) {
		    strN = String.valueOf(n);
		    MsgObj.SetMsgByName(strI + strN, "内容");
		}
	    }
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mResult;
    }

    // 更新打印份数
    private boolean UpdataCopies(int mLeftCopies) {
	boolean mResult = true;
	// 该函数可以把打印减少的次数记录到数据库
	// 根据自己的系统进行扩展该功能
	return mResult;
    }

    public void ExecuteRun() {
	MsgObj = new iMsgServer2000();
	mOption = "";
	mRecordID = "";
	mTemplate = "";
	mFileBody = null;
	mFileName = "";
	mFileType = "";
	mFileSize = 0;
	mFileID = "";
	mDateTime = "";
	mMarkName = "";
	mPassword = "";
	mMarkList = "";
	mBookmark = "";
	mMarkGuid = "";
	mDescript = "";
	mCommand = "";
	mContent = "";
	mLabelName = "";
	mImageName = "";
	mTableContent = "";
	mMyDefine1 = "";
	mOfficePrints = "0";
	mFilePath = getSession().getServletContext().getRealPath(""); // 取得服务器路径

	try {
	    MsgObj.Load(getRequest()); // 8.1.0.2版后台类新增解析接口，可支持UTF-8编码自适应功能
	    if (MsgObj.GetMsgByName("DBSTEP").equalsIgnoreCase("DBSTEP")) { // 判断是否是合法的信息包，或者数据包信息是否完整
		mOption = MsgObj.GetMsgByName("OPTION"); // 取得操作信息
		mUserName = MsgObj.GetMsgByName("USERNAME"); // 取得系统用户
		// System.out.println("OPTION:" + mOption); // 打印出调试信息
		if (mOption.equalsIgnoreCase("LOADFILE")) { // 下面的代码为打开服务器数据库里的文件
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
		    mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
		    MsgObj.MsgTextClear(); // 清除文本信息
		    // if (MsgObj.MsgFileLoad(mFilePath+"\\"+mFileName))
		    // //从文件夹调入文档
		    if (LoadFile() && mFileBody != null) { // 从数据库调入文档
			MsgObj.MsgFileBody(mFileBody); // 将文件信息打包
			MsgObj.SetMsgByName("STATUS", "打开成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("打开失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("SAVEFILE")) { // 下面的代码为保存文件在服务器的数据库里
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
		    mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
		    mDescript = "通用版本"; // 版本说明
		    mFileSize = MsgObj.MsgFileSize(); // 取得文档大小
		    mFileDate = nowtime; // 取得文档时间
		    mFileBody = MsgObj.MsgFileBody(); // 取得文档内容
		    MsgObj.MsgTextClear(); // 清除文本信息
		    // if (MsgObj.MsgFileSave(mFilePath+"\\"+mFileName))
		    // //保存文档内容到文件夹中
		    if (SaveFile()) { // 保存文档内容到数据库中
			MsgObj.SetMsgByName("STATUS", "保存成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("保存失败!"); // 设置错误信息
		    }
		    MsgObj.MsgFileClear(); // 清除文档内容
		} else if (mOption.equalsIgnoreCase("LOADTEMPLATE")) { // 下面的代码为打开服务器数据库里的模板文件
		    mTemplate = MsgObj.GetMsgByName("TEMPLATE"); // 取得模板文档类型
		    // 本段处理是否调用文档时打开模版，还是套用模版时打开模版。
		    mCommand = MsgObj.GetMsgByName("COMMAND"); // 取得客户端定义的变量COMMAND值
		    if (mCommand.equalsIgnoreCase("INSERTFILE")) {
			if (MsgObj.MsgFileLoad(mFilePath + "\\Document\\" + mTemplate)) { // 从服务器文件夹中调入模板文档
			    MsgObj.SetMsgByName("STATUS", "打开模板成功!"); // 设置状态信息
			    MsgObj.MsgError(""); // 清除错误信息
			} else {
			    MsgObj.MsgError("打开模板失败!"); // 设置错误信息
			}
		    } else {
			MsgObj.MsgTextClear(); // 清除文本信息
			if (LoadTemplate() && mFileBody != null) { // 调入模板文档
			    MsgObj.MsgFileBody(mFileBody); // 将文件信息打包
			    MsgObj.SetMsgByName("STATUS", "打开模板成功!"); // 设置状态信息
			    MsgObj.MsgError(""); // 清除错误信息
			} else {
			    MsgObj.MsgError("打开模板失败!"); // 设置错误信息
			}
		    }
		} else if (mOption.equalsIgnoreCase("SAVETEMPLATE")) { // 下面的代码为保存模板文件在服务器的数据库里
		    mTemplate = MsgObj.GetMsgByName("TEMPLATE"); // 取得文档编号
		    mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
		    mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
		    mDescript = "通用模板"; // 版本说明
		    mFileSize = MsgObj.MsgFileSize(); // 取得文档大小
		    mFileDate = nowtime; // 取得文档时间
		    mFileBody = MsgObj.MsgFileBody(); // 取得文档内容
		    MsgObj.MsgTextClear();
		    if (SaveTemplate()) { // 保存模板文档内容
			MsgObj.SetMsgByName("STATUS", "保存模板成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("保存模板失败!"); // 设置错误信息
		    }
		    MsgObj.MsgFileClear();
		} else if (mOption.equalsIgnoreCase("LISTVERSION")) { // 下面的代码为打开版本列表
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    MsgObj.MsgTextClear();
		    if (ListVersion()) { // 生成版本列表
			MsgObj.SetMsgByName("FILEID", mFileID); // 将文档号列表打包
			MsgObj.SetMsgByName("DATETIME", mDateTime); // 将日期时间列表打包
			MsgObj.SetMsgByName("USERNAME", mUserName); // 将用户名列表打包
			MsgObj.SetMsgByName("DESCRIPT", mDescript); // 将说明信息列表打包
			MsgObj.SetMsgByName("STATUS", "版本列表成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("版本列表失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("LOADVERSION")) { // 下面的代码为打开版本文档
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mFileID = MsgObj.GetMsgByName("FILEID"); // 取得版本文档号
		    MsgObj.MsgTextClear();
		    if (LoadVersion(mFileID)) { // 调入该版本文档
			MsgObj.MsgFileBody(mFileBody); // 将文档信息打包
			MsgObj.SetMsgByName("STATUS", "打开版本成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("打开版本失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("SAVEVERSION")) { // 下面的代码为保存版本文档
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mFileID = MsgObj.GetMsgByName("FILEID"); // 取得版本文档号
							     // 如:WebSaveVersionByFileID，则FileID值存在
		    mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
		    mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
		    mDescript = MsgObj.GetMsgByName("DESCRIPT"); // 取得说明信息
		    mFileSize = MsgObj.MsgFileSize(); // 取得文档大小
		    mFileDate = nowtime; // 取得文档时间
		    mFileBody = MsgObj.MsgFileBody(); // 取得文档内容
		    MsgObj.MsgTextClear();
		    if (SaveVersion()) { // 保存版本文档
			MsgObj.SetMsgByName("STATUS", "保存版本成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("保存版本失败!"); // 设置错误信息
		    }
		    MsgObj.MsgFileClear(); // 清除文档内容
		} else if (mOption.equalsIgnoreCase("LOADBOOKMARKS")) { // 下面的代码为取得文档标签
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mTemplate = MsgObj.GetMsgByName("TEMPLATE"); // 取得模板编号
		    mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
		    mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
		    MsgObj.MsgTextClear();
		    if (LoadBookMarks()) {
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("装入标签信息失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("SAVEBOOKMARKS")) { // 下面的代码为取得标签文档内容
		    mTemplate = MsgObj.GetMsgByName("TEMPLATE"); // 取得模板编号
		    if (SaveBookMarks()) {
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("保存标签信息失败!"); // 设置错误信息
		    }
		    MsgObj.MsgTextClear(); // 清除文本信息
		} else if (mOption.equalsIgnoreCase("LISTBOOKMARKS")) { // 下面的代码为显示标签列表
		    MsgObj.MsgTextClear(); // 清除文本信息
		    if (ListBookmarks()) {
			MsgObj.SetMsgByName("BOOKMARK", mBookmark); // 将用户名列表打包
			MsgObj.SetMsgByName("DESCRIPT", mDescript); // 将说明信息列表打包
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("调入标签失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("LOADMARKLIST")) { // 下面的代码为创建印章列表
		    MsgObj.MsgTextClear(); // 清除文本信息
		    if (LoadMarkList()) {
			MsgObj.SetMsgByName("MARKLIST", mMarkList); // 显示签章列表
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("创建印章列表失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("LOADMARKIMAGE")) { // 下面的代码为打开印章文件
		    mMarkName = MsgObj.GetMsgByName("IMAGENAME"); // 取得签名名称
		    mUserName = MsgObj.GetMsgByName("USERNAME"); // 取得用户名称
		    mPassword = MsgObj.GetMsgByName("PASSWORD"); // 取得用户密码
		    MsgObj.MsgTextClear(); // 清除文本信息
		    if (LoadMarkImage(mMarkName, mPassword)) { // 调入签名信息
			MsgObj.SetMsgByName("IMAGETYPE", mFileType); // 设置签名类型
			MsgObj.MsgFileBody(mFileBody); // 将签名信息打包
			MsgObj.SetMsgByName("POSITION", "Manager"); // 插入位置
								    // 在文档中标签"Manager"
			MsgObj.SetMsgByName("ZORDER", "5"); // 4:在文字上方
							    // 5:在文字下方
			MsgObj.SetMsgByName("STATUS", "打开成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("签名或密码错误!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("SAVESIGNATURE")) { // 下面的代码为保存签章基本信息
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文件名称
		    mMarkName = MsgObj.GetMsgByName("MARKNAME"); // 取得签名名称
		    mUserName = MsgObj.GetMsgByName("USERNAME"); // 取得用户名称
		    mDateTime = MsgObj.GetMsgByName("DATETIME"); // 取得签名时间
		    // mHostName = request.getRemoteAddr(); // 取得用户IP
		    mHostName = getRequest().getRemoteAddr(); // 取得用户IP
		    mMarkGuid = MsgObj.GetMsgByName("MARKGUID"); // 取得唯一编号
		    MsgObj.MsgTextClear(); // 清除文本信息
		    if (SaveSignature()) { // 保存签章
			MsgObj.SetMsgByName("STATUS", "保存印章成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("保存印章失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("LOADSIGNATURE")) { // 下面的代码为调出签章基本信息
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    MsgObj.MsgTextClear(); // 清除文本信息
		    if (LoadSignature()) { // 调出签章
			MsgObj.SetMsgByName("MARKNAME", mMarkName); // 将签名名称列表打包
			MsgObj.SetMsgByName("USERNAME", mUserName); // 将用户名列表打包
			MsgObj.SetMsgByName("DATETIME", mDateTime); // 将时间列表打包
			MsgObj.SetMsgByName("HOSTNAME", mHostName); // 将盖章IP地址列表打包
			MsgObj.SetMsgByName("MARKGUID", mMarkGuid); // 将唯一编号列表打包
			MsgObj.SetMsgByName("STATUS", "调入印章成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("调入印章失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("SAVEPDF")) { // 下面的代码为保存PDF文件
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
		    MsgObj.MsgTextClear(); // 清除文本信息
		    if (MsgObj.MsgFileSave(mFilePath + "\\Document\\" + mRecordID + ".pdf")) { // 保存文档到文件夹中
			MsgObj.SetMsgByName("STATUS", "保存成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("保存失败!"); // 设置错误信息
		    }
		    MsgObj.MsgFileClear(); // 清除文档内容
		} else if (mOption.equalsIgnoreCase("SAVEASHTML")) { // 下面的代码为将OFFICE存为HTML页面
		    mHtmlName = MsgObj.GetMsgByName("HTMLNAME"); // 取得文件名称
		    mDirectory = MsgObj.GetMsgByName("DIRECTORY"); // 取得目录名称
		    MsgObj.MsgTextClear();
		    if (mDirectory.trim().equalsIgnoreCase("")) {
			mFilePath = mFilePath + "\\HTML";
		    } else {
			mFilePath = mFilePath + "\\HTML\\" + mDirectory;
		    }
		    MsgObj.MakeDirectory(mFilePath); // 创建路径
		    if (MsgObj.MsgFileSave(mFilePath + "\\" + mHtmlName)) { // 保存HTML文件
			MsgObj.MsgError(""); // 清除错误信息
			MsgObj.SetMsgByName("STATUS", "保存HTML成功!"); // 设置状态信息
		    } else {
			MsgObj.MsgError("保存HTML失败!"); // 设置错误信息
		    }
		    MsgObj.MsgFileClear();
		} else if (mOption.equalsIgnoreCase("SAVEIMAGE")) { // 下面的代码为将OFFICE存为HTML图片页面
		    mHtmlName = MsgObj.GetMsgByName("HTMLNAME"); // 取得文件名称
		    mDirectory = MsgObj.GetMsgByName("DIRECTORY"); // 取得目录名称
		    MsgObj.MsgTextClear();
		    if (mDirectory.trim().equalsIgnoreCase("")) {
			mFilePath = mFilePath + "\\HTMLIMAGE";
		    } else {
			mFilePath = mFilePath + "\\HTMLIMAGE\\" + mDirectory;
		    }
		    MsgObj.MakeDirectory(mFilePath); // 创建路径
		    if (MsgObj.MsgFileSave(mFilePath + "\\" + mHtmlName)) { // 保存HTML文件
			MsgObj.MsgError(""); // 清除错误信息
			MsgObj.SetMsgByName("STATUS", "保存HTML图片成功!"); // 设置状态信息
		    } else {
			MsgObj.MsgError("保存HTML图片失败!"); // 设置错误信息
		    }
		    MsgObj.MsgFileClear();
		} else if (mOption.equalsIgnoreCase("SAVEASPAGE")) { // 下面的代码为将手写批注存为HTML图片页面
		    mHtmlName = MsgObj.GetMsgByName("HTMLNAME"); // 取得文件名称
		    mDirectory = MsgObj.GetMsgByName("DIRECTORY"); // 取得目录名称
		    MsgObj.MsgTextClear();
		    if (mDirectory.trim().equalsIgnoreCase("")) {
			mFilePath = mFilePath + "\\HTML";
		    } else {
			mFilePath = mFilePath + "\\HTML\\" + mDirectory;
		    }
		    MsgObj.MakeDirectory(mFilePath); // 创建路径
		    if (MsgObj.MsgFileSave(mFilePath + "\\" + mHtmlName)) { // 保存HTML文件
			MsgObj.MsgError(""); // 清除错误信息
			MsgObj.SetMsgByName("STATUS", "保存批注HTML图片成功!"); // 设置状态信息
		    } else {
			MsgObj.MsgError("保存批注HTML图片失败!"); // 设置错误信息
		    }
		    MsgObj.MsgFileClear();
		} else if (mOption.equalsIgnoreCase("INSERTFILE")) { // 下面的代码为插入文件
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
		    mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
		    MsgObj.MsgTextClear();
		    if (LoadFile()) { // 调入文档
			MsgObj.MsgFileBody(mFileBody); // 将文件信息打包
			MsgObj.SetMsgByName("POSITION", "Content"); // 设置插入的位置[书签]
			MsgObj.SetMsgByName("STATUS", "插入文件成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("插入文件成功!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("UPDATEFILE")) { // 下面的代码为更新保存文件
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
		    mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
		    mDescript = "定稿版本"; // 版本说明
		    mFileSize = MsgObj.MsgFileSize(); // 取得文档大小
		    mFileDate = nowtime; // 取得文档时间
		    mFileBody = MsgObj.MsgFileBody(); // 取得文档内容
		    MsgObj.MsgTextClear();
		    if (SaveVersion()) { // 保存文档内容
			MsgObj.SetMsgByName("STATUS", "保存定稿版本成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("保存定稿版本失败!"); // 设置错误信息
		    }
		    MsgObj.MsgFileClear();
		} else if (mOption.equalsIgnoreCase("INSERTIMAGE")) { // 下面的代码为插入服务器图片
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mLabelName = MsgObj.GetMsgByName("LABELNAME"); // 标签名
		    mImageName = MsgObj.GetMsgByName("IMAGENAME"); // 图片名
		    mFilePath = mFilePath + "\\Document\\" + mImageName; // 图片在服务器的完整路径
		    mFileType = mImageName.substring(mImageName.length() - 4).toLowerCase(); // 取得文件的类型
		    MsgObj.MsgTextClear();
		    if (MsgObj.MsgFileLoad(mFilePath)) { // 调入图片
			MsgObj.SetMsgByName("IMAGETYPE", mFileType); // 指定图片的类型
			MsgObj.SetMsgByName("POSITION", mLabelName); // 设置插入的位置[书签对象名]
			MsgObj.SetMsgByName("STATUS", "插入图片成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("插入图片失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("PUTFILE")) { // 下面的代码为请求上传文件操作
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mFileBody = MsgObj.MsgFileBody(); // 取得文档内容
		    mLocalFile = MsgObj.GetMsgByName("LOCALFILE"); // 取得本地文件名称
		    mRemoteFile = MsgObj.GetMsgByName("REMOTEFILE"); // 取得远程文件名称
		    MsgObj.MsgTextClear(); // 清除文本信息
		    mFilePath = mFilePath + "\\Document\\" + mRemoteFile;
		    if (MsgObj.MsgFileSave(mFilePath)) { // 调入文档
			MsgObj.SetMsgByName("STATUS", "保存上传文件成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("上传文件失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("GETFILE")) { // 下面的代码为请求下载文件操作
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mLocalFile = MsgObj.GetMsgByName("LOCALFILE"); // 取得本地文件名称
		    mRemoteFile = MsgObj.GetMsgByName("REMOTEFILE"); // 取得远程文件名称
		    MsgObj.MsgTextClear(); // 清除文本信息
		    mFilePath = mFilePath + "\\Document\\" + mRemoteFile;
		    if (MsgObj.MsgFileLoad(mFilePath)) { // 调入文档内容
			MsgObj.SetMsgByName("STATUS", "保存下载文件成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("下载文件失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("DATETIME")) { // 下面的代码为请求取得服务器时间
		    MsgObj.MsgTextClear(); // 清除文本信息
		    MsgObj.SetMsgByName("DATETIME", nowtime); // 标准日期格式字串，如
							      // 2005-8-16
							      // 10:20:35
		} else if (mOption.equalsIgnoreCase("SENDMESSAGE")) { // 下面的代码为Web页面请求信息[扩展接口]
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
		    mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
		    mCommand = MsgObj.GetMsgByName("COMMAND"); // 取得自定义的操作类型
		    mContent = MsgObj.GetMsgByName("CONTENT"); // 取得文本信息 Content
		    mOfficePrints = MsgObj.GetMsgByName("OFFICEPRINTS"); // 取得Office文档的打印次数
		    mInfo = MsgObj.GetMsgByName("TESTINFO"); // 取得客户端传来的自定义信息
		    MsgObj.MsgTextClear();
		    MsgObj.MsgFileClear();
		    // System.out.println("COMMAND:" + mCommand);
		    if (mCommand.equalsIgnoreCase("INPORTTEXT")) { // 导入文本内容功能
			if (LoadContent()) {
			    MsgObj.SetMsgByName("CONTENT", mContent);
			    MsgObj.SetMsgByName("STATUS", "导入成功!"); // 设置状态信息
			    MsgObj.MsgError(""); // 清除错误信息
			} else {
			    MsgObj.MsgError("导入失败!"); // 设置错误信息
			}
		    } else if (mCommand.equalsIgnoreCase("EXPORTTEXT")) { // 导出文本内容功能
			if (SaveContent()) {
			    MsgObj.SetMsgByName("STATUS", "导出成功!"); // 设置状态信息
			    MsgObj.MsgError(""); // 清除错误信息
			} else {
			    MsgObj.MsgError("导出失败!"); // 设置错误信
			}
		    } else if (mCommand.equalsIgnoreCase("WORDTABLE")) { // 插入远程表格功能
			if (GetWordTable()) {
			    MsgObj.SetMsgByName("COLUMNS", String.valueOf(mColumns)); // 列
			    MsgObj.SetMsgByName("CELLS", String.valueOf(mCells)); // 行
			    MsgObj.SetMsgByName("WORDCONTENT", mTableContent); // 表格内容
			    MsgObj.SetMsgByName("STATUS", "增加和填充成功成功!"); // 设置状态信息
			    MsgObj.MsgError(""); // 清除错误信息
			} else {
			    MsgObj.MsgError("增加表格行失败!"); // 设置错误信息
			}
		    } else if (mCommand.equalsIgnoreCase("COPIES")) { // 打印份数控制功能
			// System.out.println("PRINTS:" + mOfficePrints);
			mCopies = Integer.parseInt(mOfficePrints); // 获得客户需要打印的份数
			if (mCopies <= 2) { // 比较打印份数，拟定该文档允许打印的总数为2份，注：可以在数据库中设置好文档允许打印的份数
			    if (UpdataCopies(2 - mCopies)) { // 更新打印份数
				MsgObj.SetMsgByName("STATUS", "1"); // 设置状态信息，允许打印
				MsgObj.MsgError(""); // 清除错误信息
			    }
			} else {
			    MsgObj.SetMsgByName("STATUS", "0"); // 不允许打印
			    MsgObj.MsgError("超过打印限度不允许打印!"); // 设置错误信息
			}
		    } else if (mCommand.equalsIgnoreCase("SELFINFO")) {
			mInfo = "服务器端收到客户端传来的信息：“" + mInfo + "” | ";
			mInfo = mInfo + "当前服务器时间：" + nowtime; // 组合返回给客户端的信息
			MsgObj.SetMsgByName("RETURNINFO", mInfo); // 将返回的信息设置到信息包中
		    } else {
			MsgObj.MsgError("客户端Web发送数据包命令没有合适的处理函数![" + mCommand + "]");
			MsgObj.MsgTextClear();
			MsgObj.MsgFileClear();
		    }
		} else if (mOption.equalsIgnoreCase("SAVEPAGE")) { // 下面的代码为保存为全文批注格式文件
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    MsgObj.MsgTextClear(); // 清除文本信息
		    mFilePath = mFilePath + "\\Document\\" + mRecordID + ".pgf"; // 全文批注文件的完整路径
		    if (MsgObj.MsgFileSave(mFilePath)) { // 保存全文批注文件
			MsgObj.SetMsgByName("STATUS", "保存全文批注成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("保存全文批注失败!"); // 设置错误信息
		    }
		} else if (mOption.equalsIgnoreCase("LOADPAGE")) { // 下面的代码为调入全文批注格式文件
		    mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
		    MsgObj.MsgTextClear(); // 清除文本信息
		    mFilePath = mFilePath + "\\Document\\" + mRecordID + ".pgf"; // 全文批注文件的完整路径
		    if (MsgObj.MsgFileLoad(mFilePath)) { // 调入文档内容
			MsgObj.SetMsgByName("STATUS", "打开全文批注成功!"); // 设置状态信息
			MsgObj.MsgError(""); // 清除错误信息
		    } else {
			MsgObj.MsgError("打开全文批注失败!"); // 设置错误信息
		    }
		}
	    } else {
		MsgObj.MsgError("客户端发送数据包错误!");
		MsgObj.MsgTextClear();
		MsgObj.MsgFileClear();
	    }
	    MsgObj.Send(getResponse()); // 8.1.0.2新版后台类新增的功能接口，返回信息包数据
	} catch (Exception e) {
	    System.out.println(e.toString());
	}
	renderNull();
    }

    @SuppressWarnings("resource")
    @Clear
    public static byte[] getBytesFromFile(File file) throws IOException {
	InputStream is = new FileInputStream(file);
	long length = file.length(); // 获取文件大小
	if (length > Integer.MAX_VALUE) { // 文件太大，无法读取
	    throw new IOException("File is to large " + file.getName());
	}
	byte[] bytes = new byte[(int) length];
	int offset = 0;
	int numRead = 0;
	while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
	    offset += numRead;
	}
	if (offset < bytes.length) { // 确保所有数据均被读取
	    throw new IOException("Could not completely read file " + file.getName());
	}
	is.close();
	return bytes;
    }

    @Clear
    public void view() {
	UploadFile file = getFile("file", Constant.PATH_TEMPLATE_UPLOAD, Constant.MAX_POST_SIZE);
	T_Template model = getModel(T_Template.class);
	if (file != null) {
	    model.set("url", file.getFileName());
	    if (!isParaBlank("oldUrl")) {
		File deleteFile = new File(Constant.PATH_WEBROOT + Constant.PATH_TEMPLATE + getPara("oldUrl"));
		if (deleteFile.exists()) {
		    deleteFile.delete();
		}
	    }
	}
	if (model.update()) {
	    setAttr("message", Constant.SUCCESS);
	    render("/success.jsp");
	} else {
	    setAttr("message", Constant.EXCEPTION);
	    render("/failure.jsp");
	}
    }

}