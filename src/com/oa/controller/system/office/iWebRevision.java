package com.oa.controller.system.office;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.jfinal.aop.Clear;
import com.oa.model.system.office.T_Html_History;
import com.oa.model.system.office.T_Html_Signature;
import com.zhilian.annotation.RouteBind;
import com.zhilian.controller.BaseController;

import DBstep.iMsgServer2000;

@Clear
@RouteBind(path = "Main/WebRevision", viewPath = "System/Workflow/WebRevision")
public class iWebRevision extends BaseController {

    @SuppressWarnings("unused")
    private int mFileSize;
    private byte[] mFileBody;
    @SuppressWarnings("unused")
    private String mFileName;
    private String mFieldName;
    private String mFileType;
    private String mRecordID;
    private String mDateTime;
    private String mOption;
    private String mMarkName;
    private String mPassword;
    private String mMarkList;
    private String mHostName;
    private String mMarkGuid;
    private String mFieldValue;
    private String mUserName;
    @SuppressWarnings("unused")
    private String mFilePath;
    private iMsgServer2000 MsgObj;

    Date now = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String nowtime = formatter.format(now);

    // 保存签章的历史信息
    private boolean SaveHistory() {
	boolean mResult = false;
	try {
	    T_Html_History temp = new T_Html_History();
	    temp.set("recordid", mRecordID);
	    temp.set("fieldname", mFieldName);
	    temp.set("markname", mMarkName);
	    temp.set("username", mUserName);
	    temp.set("datetime", mDateTime);
	    temp.set("hostname", mHostName);
	    temp.set("markguid", mMarkGuid);
	    temp.save();
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return (mResult);
    }

    // 列出所有历史信息
    private boolean ShowHistory() {
	mMarkName = "印章名称" + "\r\n";
	mUserName = "签名人" + "\r\n";
	mHostName = "客户端IP" + "\r\n";
	mDateTime = "签章时间" + "\r\n";
	mMarkGuid = "序列号" + "\r\n";
	boolean mResult = false;
	T_Html_History temp = null;
	try {
	    List<T_Html_History> list = T_Html_History.dao.find("select * from t_html_history where recordid='"
		    + mRecordID + "' and fieldname='" + mFieldName + "'");
	    for (int i = 0; i < list.size(); i++) {
		temp = list.get(i);
		mMarkName += temp.getStr("markname") + "\r\n";
		mDateTime += temp.getStr("datetime") + "\r\n";
		mUserName += temp.getStr("username") + "\r\n";
		mHostName += temp.getStr("hostname") + "\r\n";
		mMarkGuid += temp.getStr("markguid") + "\r\n";
	    }
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return (mResult);
    }

    // 取得签名列表
    private boolean SignatureList() {
	boolean mResult = false;
	// String Sql = "SELECT MarkName FROM Signature";
	// mMarkList = "";
	// if (DbaObj.OpenConnection()) {
	// try {
	// mResult = true;
	// ResultSet result = DbaObj.ExecuteQuery(Sql);
	// while (result.next()) {
	// mMarkList += result.getString("MarkName") + "\r\n";
	// }
	// result.close();
	// } catch (Exception e) {
	// System.out.println(e.toString());
	// mResult = false;
	// }
	// DbaObj.CloseConnection();
	// }
	return (mResult);
    }

    // 调入签章图案
    private boolean SignatureImage(String vMarkName, String vPassWord) {
	boolean mResult = false;
	// String Sql = "SELECT MarkBody,MarkType FROM Signature WHERE
	// MarkName='" + vMarkName + "' and PassWord='" + vPassWord + "'";
	// if (DbaObj.OpenConnection()) {
	// try {
	// ResultSet result = DbaObj.ExecuteQuery(Sql);
	// if (result.next()) {
	// mResult = true;
	// mFileBody = result.getBytes("MarkBody");
	// mFileType = result.getString("MarkType");
	// mFileSize = mFileBody.length;
	// }
	// result.close();
	// } catch (Exception e) {
	// System.out.println(e.toString());
	// mResult = false;
	// }
	// DbaObj.CloseConnection();
	// }
	return (mResult);
    }

    // 保存签章数据信息
    private boolean SaveSignature() {
	boolean mResult = false;
	try {
	    T_Html_Signature temp = new T_Html_Signature();
	    temp.set("recordid", mRecordID);
	    temp.set("fieldname", mFieldName);
	    temp.set("username", mUserName);
	    temp.set("datetime", mDateTime);
	    temp.set("hostname", mHostName);
	    temp.set("fieldvalue", mFieldValue);
	    temp.save();
	    mResult = true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return (mResult);
    }

    // 更新签章数据信息
    private boolean UpdateSignature() {
	boolean mResult = false;
	try {
	    T_Html_Signature temp = T_Html_Signature.dao.findFirst(
		    "select * from t_html_signature where recordid=? and fieldname=?", mRecordID, mFieldName);
	    if (temp != null) {
		temp.set("username", mUserName);
		temp.set("datetime", mDateTime);
		temp.set("hostname", mHostName);
		temp.set("fieldvalue", mFieldValue);
		temp.update();
		mResult = true;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return (mResult);
    }

    // 判断签章数据信息是否存在
    private boolean ShowSignatureIS() {
	boolean mResult = false;
	try {
	    T_Html_History temp = T_Html_History.dao.findFirst("select * from t_html_signature where recordid='"
		    + mRecordID + "' and fieldname='" + mFieldName + "'");
	    if (temp != null) {
		mResult = true;
	    }
	} catch (Exception e) {
	    System.out.println(e.toString());
	    mResult = false;
	}
	return (mResult);
    }

    // 调出签章数据信息
    private boolean LoadSignature() {
	boolean mResult = false;
	try {
	    T_Html_History temp = T_Html_History.dao.findFirst("select * from t_html_signature where recordid='"
		    + mRecordID + "' and fieldname='" + mFieldName + "'");
	    if (temp != null) {
		mFieldValue = temp.getStr("FieldValue");
		mResult = true;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return (mResult);
    }

    // 取得客户端发来的数据包
    private byte[] ReadPackage() {
	byte mStream[] = null;
	int totalRead = 0;
	int readBytes = 0;
	int totalBytes = 0;
	try {
	    totalBytes = getRequest().getContentLength();
	    mStream = new byte[totalBytes];
	    while (totalRead < totalBytes) {
		getRequest().getInputStream();
		readBytes = getRequest().getInputStream().read(mStream, totalRead, totalBytes - totalRead);
		totalRead += readBytes;
		continue;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return (mStream);
    }

    // 发送处理后的数据包
    private void SendPackage() {
	try {
	    ServletOutputStream OutBinarry = getResponse().getOutputStream();
	    OutBinarry.write(MsgObj.MsgVariant());
	    OutBinarry.flush();
	    OutBinarry.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    // 具体处理客户端控件请求的函数
    public void ExecuteRun() {
	mOption = "";
	mRecordID = "";
	mFileBody = null;
	mFileName = "";
	mFileType = "";
	mFileSize = 0;
	mDateTime = "";
	mMarkName = "";
	mPassword = "";
	mMarkList = "";
	mMarkGuid = "";
	mUserName = "";
	mFieldName = "";
	mHostName = "";
	mFieldValue = "";
	MsgObj = new DBstep.iMsgServer2000();
	mFilePath = getRequest().getSession().getServletContext().getRealPath(""); // 取得服务器路径
	try {
	    if (getRequest().getMethod().equalsIgnoreCase("POST")) {
		MsgObj.MsgVariant(ReadPackage());
		if (MsgObj.GetMsgByName("DBSTEP").equalsIgnoreCase("DBSTEP")) { // 检测客户端传递的数据包格式
		    mOption = MsgObj.GetMsgByName("OPTION"); // 取得操作类型
		    // System.out.println(mOption);
		    // System.out.println(MsgObj.GetMsgByName("RECORDID"));
		    if (mOption.equalsIgnoreCase("SIGNATRUELIST")) { // 下面的代码为创建印章列表
			MsgObj.MsgTextClear(); // 清除SetMsgByName设置的值
			if (SignatureList()) { // 调入印章列表
			    MsgObj.SetMsgByName("SIGNATRUELIST", mMarkList); // 设置印章列表字符串
			    MsgObj.MsgError(""); // 清除错误信息
			} else {
			    MsgObj.MsgError("创建印章列表失败!"); // 设置错误信息
			}
		    } else if (mOption.equalsIgnoreCase("SIGNATRUEIMAGE")) { // 下面的代码为调入印章图案
			mMarkName = MsgObj.GetMsgByName("IMAGENAME"); // 取得印章名称
			mUserName = MsgObj.GetMsgByName("USERNAME"); // 取得用户名
			mPassword = MsgObj.GetMsgByName("PASSWORD"); // 取得印章密码
			MsgObj.MsgTextClear(); // 清除SetMsgByName设置的值
			if (SignatureImage(mMarkName, mPassword)) { // 调入印章
			    MsgObj.SetMsgByName("IMAGETYPE", mFileType); // 设置图片类型
			    MsgObj.MsgFileBody(mFileBody); // 将签章数据信息打包
			    MsgObj.SetMsgByName("STATUS", "打开成功!"); // 设置状态信息
			    MsgObj.MsgError(""); // 清除错误信息
			} else {
			    MsgObj.MsgError("签名或密码错误!"); // 设置错误信息
			}
		    } else if (mOption.equalsIgnoreCase("SAVESIGNATURE")) { // 下面的代码为更新印章数据
			mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
			mFieldName = MsgObj.GetMsgByName("FIELDNAME"); // 取得签章字段名称
			mFieldValue = MsgObj.GetMsgByName("FIELDVALUE"); // 取得签章数据内容
			mUserName = MsgObj.GetMsgByName("USERNAME"); // 取得用户名称
			mDateTime = MsgObj.GetMsgByName("DATETIME"); // 取得签章日期时间
			mHostName = getRequest().getRemoteAddr(); // 取得客户端IP
			MsgObj.MsgTextClear(); // 清除SetMsgByName设置的值
			// MsgObj.MsgFileSave(mFilePath+"/"+mRecordID+"_"+mFieldName+".gif");
			// //在服务器保存输出成图片
			if (ShowSignatureIS()) { // 判断是否已经存在签章记录
			    if (UpdateSignature()) { // 更新签章数据
				MsgObj.SetMsgByName("STATUS", "更新成功!"); // 设置状态信息
				MsgObj.MsgError(""); // 清除错误信息
			    } else {
				MsgObj.MsgError("保存签章信息失败!"); // 设置错误信息
			    }
			} else {
			    if (SaveSignature()) { // 保存签章数据
				MsgObj.SetMsgByName("STATUS", "保存成功!"); // 设置状态信息
				MsgObj.MsgError(""); // 清除错误信息
			    } else {
				MsgObj.MsgError("保存签章信息失败!"); // 设置错误信息
			    }
			}
		    } else if (mOption.equalsIgnoreCase("LOADSIGNATURE")) { // 下面的代码为调入签章数据
			mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
			mFieldName = MsgObj.GetMsgByName("FIELDNAME"); // 取得签章字段名称
			mUserName = MsgObj.GetMsgByName("USERNAME"); // 取得用户名称
			MsgObj.MsgTextClear(); // 清除SetMsgByName设置的值
			if (LoadSignature()) { // 调入签章数据信息
			    MsgObj.SetMsgByName("FIELDVALUE", mFieldValue); // 设置签章数据
			    MsgObj.SetMsgByName("STATUS", "调入签批数据成功!"); // 设置状态信息
			    MsgObj.MsgError(""); // 清除错误信息
			} else {
			    MsgObj.MsgError("调入签批数据失败!"); // 设置错误信息
			}
		    } else if (mOption.equalsIgnoreCase("SAVEHISTORY")) { // 下面的代码为保存印章历史信息
			mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
			mFieldName = MsgObj.GetMsgByName("FIELDNAME"); // 取得签章字段名称
			mMarkName = MsgObj.GetMsgByName("MARKNAME"); // 取得签章名称
			mUserName = MsgObj.GetMsgByName("USERNAME"); // 取得用户名称
			mDateTime = MsgObj.GetMsgByName("DATETIME"); // 取得签章日期时间
			mHostName = getRequest().getRemoteAddr(); // 取得客户端IP
			mMarkGuid = MsgObj.GetMsgByName("MARKGUID"); // 取得序列号
			MsgObj.MsgTextClear(); // 清除SetMsgByName设置的值
			if (SaveHistory()) { // 保存印章历史信息
			    MsgObj.SetMsgByName("MARKNAME", mMarkName); // 将签章名称列表打包
			    MsgObj.SetMsgByName("USERNAME", mUserName); // 将用户名列表打包
			    MsgObj.SetMsgByName("DATETIME", mDateTime); // 将签章日期列表打包
			    MsgObj.SetMsgByName("HOSTNAME", mHostName); // 将客户端IP列表打包
			    MsgObj.SetMsgByName("MARKGUID", mMarkGuid); // 将序列号列表打包
			    MsgObj.SetMsgByName("STATUS", "保存印章日志成功!"); // 设置状态信息
			    MsgObj.MsgError(""); // 清除错误信息
			} else {
			    MsgObj.MsgError("保存印章日志失败!"); // 设置错误信息
			}
		    } else if (mOption.equalsIgnoreCase("SHOWHISTORY")) { // 下面的代码为打开签章历史信息
			mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
			mFieldName = MsgObj.GetMsgByName("FIELDNAME"); // 取得签章字段名称
			mUserName = MsgObj.GetMsgByName("USERNAME"); // 取得用户名
			MsgObj.MsgTextClear(); // 清除SetMsgByName设置的值
			if (ShowHistory()) { // 调入印章历史信息
			    MsgObj.SetMsgByName("MARKNAME", mMarkName); // 将签章名称列表打包
			    MsgObj.SetMsgByName("USERNAME", mUserName); // 将用户名列表打包
			    MsgObj.SetMsgByName("DATETIME", mDateTime); // 将签章日期列表打包
			    MsgObj.SetMsgByName("HOSTNAME", mHostName); // 将客户端IP列表打包
			    MsgObj.SetMsgByName("MARKGUID", mMarkGuid); // 将序列号列表打包
			    MsgObj.SetMsgByName("STATUS", "调入印章日志成功"); // 设置状态信息
			    MsgObj.MsgError(""); // 清除错误信息
			} else {
			    MsgObj.SetMsgByName("STATUS", "调入印章日志失败"); // 设置状态信息
			    MsgObj.MsgError("调入印章日志失败"); // 设置错误信息
			}
		    } else if (mOption.equalsIgnoreCase("SENDMESSAGE")) {
			String mCommand = MsgObj.GetMsgByName("COMMAND");
			String mInfo = MsgObj.GetMsgByName("TESTINFO");
			MsgObj.MsgTextClear();
			MsgObj.MsgFileClear();
			System.out.println(mCommand);
			if (mCommand.equalsIgnoreCase("SELFINFO")) {
			    mInfo = "服务器端收到客户端传来的信息：“" + mInfo + "”\r\n";
			    // 组合返回给客户端的信息
			    mInfo = mInfo + "服务器端发回当前服务器时间：" + nowtime;
			    MsgObj.SetMsgByName("RETURNINFO", mInfo); // 将返回的信息设置到信息包中
			} else {
			    MsgObj.MsgError("客户端Web发送数据包命令没有合适的处理函数![" + mCommand + "]");
			    MsgObj.MsgTextClear();
			    MsgObj.MsgFileClear();
			}
		    }
		} else {
		    MsgObj.MsgError("客户端发送数据包错误!");
		    MsgObj.MsgTextClear();
		    MsgObj.MsgFileClear();
		}
	    } else {
		MsgObj.MsgError("请使用Post方法");
		MsgObj.MsgTextClear();
		MsgObj.MsgFileClear();
	    }
	    SendPackage();
	} catch (Exception e) {
	    System.out.println(e.toString());
	}
	renderNull();
    }

}