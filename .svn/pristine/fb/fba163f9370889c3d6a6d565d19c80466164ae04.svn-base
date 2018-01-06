package com.oa.controller.system.office;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.jfinal.aop.Clear;
import com.oa.controller.BaseAssociationController;
import com.oa.model.system.office.T_Html_History;
import com.oa.model.system.office.T_Html_Signature;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.util.StringUtil;

@RouteBind(path = "Main/HtmlSignature", viewPath = "")
public class iSignature extends BaseAssociationController {

    @Clear
    public void ExecuteRun() {
	String mCommand;
	String mDocumentID = "";
	String mSignatureID = "";
	String mSignature = "";
	String mSignatures;

	String strSql;
	String mUserName;
	// String mExtParam;

	@SuppressWarnings("unused")
	boolean mResult;
	java.lang.String KeyName; // 文件名
	java.io.File ObjFile; // 文件对象

	byte[] SignatureBody = null;
	int mSignatureSize;

	String mSignatureName; // 印章名称
	String mSignatureUnit; // 签章单位
	String mSignatureUser; // 持章人
	String mSignatureSN; // 签章SN
	String mSignatureGUID; // 全球唯一标识符

	String mMACHIP; // 机器IP
	String OPType; // 操作标志
	String mKeySn; // KEY序列号
	try {
	    mCommand = getPara("COMMAND");
	    mUserName = getPara("USERNAME");
	    // mExtParam = getPara("EXTPARAM");
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    String mDateTime = formatter.format(cal.getTime());

	    if (mCommand.equalsIgnoreCase("SAVESIGNATURE")) {
		mDocumentID = getPara("DOCUMENTID");
		mSignatureID = getPara("SIGNATUREID");
		// mSignature = new
		// String(getRequest().getParameter("SIGNATURE").getBytes("8859_1"));
		mSignature = getPara("SIGNATURE");
		SignatureBody = mSignature.getBytes();
		mSignatureSize = SignatureBody.length;

		T_Html_Signature temp = new T_Html_Signature();
		temp.set("documentid", mDocumentID);
		temp.set("signatureid", mSignatureID);
		temp.set("signaturesize", mSignatureSize);
		temp.set("signature", SignatureBody);

		T_Html_Signature temp1 = T_Html_Signature.getHtmlSignature(mSignatureID, mDocumentID);
		if (temp1 != null) {
		    temp.set("id", temp1.getInt("id"));
		    temp.update();
		} else {
		    java.util.Date dt = new java.util.Date();
		    long lg = dt.getTime();
		    Long ld = new Long(lg);
		    mSignatureID = ld.toString();
		    temp.set("signatureid", mSignatureID);
		    temp.save();
		}
		getResponse().getWriter().print("SIGNATUREID=" + mSignatureID + "\r\n" + "RESULT=OK");
	    } else if (mCommand.equalsIgnoreCase("GETNOWTIME")) { // 获取服务器时间
		getResponse().getWriter().print("NOWTIME=" + mDateTime + "\r\nRESULT=OK");
	    } else if (mCommand.equalsIgnoreCase("DELESIGNATURE")) { // 删除签章数据信息
		mDocumentID = getPara("DOCUMENTID");
		mSignatureID = getPara("SIGNATUREID");
		T_Html_Signature temp1 = T_Html_Signature.getHtmlSignature(mSignatureID, mDocumentID);
		if (temp1 != null) {
		    temp1.delete();
		}
		getResponse().getWriter().print("RESULT=OK");
	    } else if (mCommand.equalsIgnoreCase("LOADSIGNATURE")) { // 调入签章数据信息
		mDocumentID = getPara("DOCUMENTID");
		mSignatureID = getPara("SIGNATUREID");
		T_Html_Signature temp1 = T_Html_Signature.getHtmlSignature(mSignatureID, mDocumentID);
		if (temp1 != null) {
		    mSignatureSize = temp1.getInt("SignatureSize");
		    mSignature = temp1.getStr("Signature");
		}
		getResponse().getWriter().print(mSignature + "\r\nRESULT=OK");
	    } else if (mCommand.equalsIgnoreCase("SHOWSIGNATURE")) { // 获取当前签章SignatureID，调出SignatureID，再自动调LOADSIGNATURE数据
		mDocumentID = getPara("DOCUMENTID");
		mSignatures = "";
		strSql = "SELECT * from t_html_signature Where DocumentID='" + mDocumentID + "'";
		List<T_Html_Signature> list = T_Html_Signature.dao.find(strSql);
		for (int i = 0; i < list.size(); i++) {
		    T_Html_Signature temp = list.get(i);
		    mSignatures = mSignatures + temp.getStr("SignatureID") + ";";
		}
		getResponse().getWriter().print("SIGNATURES=" + mSignatures + "\r\nRESULT=OK");
	    } else if (mCommand.equalsIgnoreCase("SIGNATUREKEY")) {
		mUserName = getPara("USERNAME");
		KeyName = Constant.PATH_WEBROOT + "\\" + mUserName + "\\" + mUserName + ".key";
		ObjFile = new java.io.File(KeyName); // 创建文件对象
		if (ObjFile.exists()) {// 文件存在
		    mSignature = StringUtil.ConvertToString(new FileInputStream(KeyName));
		    getResponse().getWriter().print(mSignature + "\r\nRESULT=OK");
		} else {
		    getResponse().getWriter().print("File Not Found" + KeyName); // 文件不存在
		}
	    } else if (mCommand.equalsIgnoreCase("SAVEHISTORY")) { // 保存签章历史信息
		mSignatureName = getPara("SIGNATURENAME");// 印章名称
		mSignatureUnit = getPara("SIGNATUREUNIT");// 印章单位
		mSignatureUser = getPara("SIGNATUREUSER");// 印章用户名
		mSignatureSN = getPara("SIGNATURESN");// 印章序列号
		mSignatureGUID = getPara("SIGNATUREGUID");// 全球唯一标识
		mDocumentID = getPara("DOCUMENTID");// 页面ID
		mSignatureID = getPara("SIGNATUREID");// 签章序列号
		mMACHIP = getPara("MACHIP");// 签章机器IP
		OPType = getPara("LOGTYPE");// 日志标志
		mKeySn = getPara("KEYSN");// KEY序列号
		// java.sql.Date mDate;
		T_Html_History temp = new T_Html_History();
		temp.set("SignatureName", mSignatureName);
		temp.set("SignatureUnit", mSignatureUnit);
		temp.set("SignatureUser", mSignatureUser);
		temp.set("SignatureSN", mSignatureSN);
		temp.set("SignatureGUID", mSignatureGUID);
		temp.set("DocumentID", mDocumentID);
		temp.set("SignatureID", mSignatureID);
		temp.set("IP", mMACHIP);
		temp.set("LogType", OPType);
		temp.set("KeySN", mKeySn);
		if (temp.save()) {
		    mResult = true;
		} else {
		    mResult = false;
		}
		getResponse().getWriter().print("SIGNATUREID=" + mSignatureID + "\r\nRESULT=OK");
	    }
	} catch (Exception e) {
	    System.out.println(e.toString());
	}
	renderNull();
    }

}