package com.oa.controller.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.kit.JsonKit;
import com.jfinal.upload.UploadFile;
import com.oa.model.common.T_Attachment;
import com.oa.util.BusinessConstant;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.controller.BaseController;
import com.zhilian.model.LoginModel;
import com.zhilian.util.DateUtils;
import com.zhilian.util.FileUtils;

@RouteBind(path = "Main/Attachment", viewPath = "Common/Attachment")
public class Attachment extends BaseController {

    @Clear
    public void saveAttachment() { // 附件上传
	try {
	    LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	    // String saveDirectory = DateUtils.getNowDate();
	    List<UploadFile> files = getFiles("/File", Constant.MAX_POST_SIZE);
	    List<T_Attachment> list = new ArrayList<T_Attachment>();
	    for (int i = 0; i < files.size(); i++) {
		UploadFile file = files.get(i);
		String fileName = file.getFileName();
		String suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
		String size = FileUtils.getFileSize(file.getFile().length());
		String tempStamp = String.valueOf(System.currentTimeMillis());
		// String url = saveDirectory + "/" + fileName;
		String url = tempStamp + "." + suffix;
		// 换名字保存
		File oldfile = new File(Constant.PATH_WEBROOT + Constant.PATH_FILE + fileName);
		File newfile = new File(Constant.PATH_WEBROOT + Constant.PATH_FILE + url);
		oldfile.renameTo(newfile);
		T_Attachment attach = new T_Attachment();
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
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    @Clear
    public void savePDF() { // 附件上传
	try {
	    String saveDirectory = DateUtils.getNowDate();
	    List<UploadFile> files = getFiles("/File", Constant.MAX_POST_SIZE);
	    String tempId = getPara("tempId");
	    List<T_Attachment> list = new ArrayList<T_Attachment>();
	    for (int i = 0; i < files.size(); i++) {
		UploadFile uploadFile = files.get(i);
		String fileName = uploadFile.getFileName();
		String suffix = fileName.substring(fileName.lastIndexOf(BusinessConstant.DOT) + 1);
		String size = FileUtils.getFileSize(uploadFile.getFile().length());
		fileName = fileName.replace(BusinessConstant.DOT + suffix, Constant.PDF);
		String url = saveDirectory + "/" + fileName;
		File file = uploadFile.getFile();
		String newFilePath = file.getAbsolutePath().replace(BusinessConstant.DOT + suffix, Constant.PDF);
		file.renameTo(new File(newFilePath));
		T_Attachment attach = new T_Attachment();
		attach.set("name", fileName);
		attach.set("url", url);
		attach.set("size", size);
		attach.set("suffix", Constant.PDF.substring(Constant.PDF.lastIndexOf(BusinessConstant.DOT) + 1));
		attach.set("markDate", DateUtils.getNowDate());
		attach.set("tempId", tempId);
		attach.save();
		list.add(attach);
	    }
	    String jsonstr = JsonKit.toJson(list);
	    renderJson(jsonstr);
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /** 附件-删除 */
    @Clear
    public void delete() {
	Integer id = getParaToInt(0);
	T_Attachment model = T_Attachment.dao.findById(id);
	try {
	    String modelurl = model.getStr("url");
	    // String url = Constant.PATH_WEBROOT + Constant.PATH_FILE +
	    // modelurl.substring(10, modelurl.length());
	    String url = Constant.PATH_WEBROOT + Constant.PATH_FILE + modelurl;
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

    /**
     * 根据临时ID删除文件
     */
    @Clear
    public void deleteFileByTempId() {
	String tempId = getPara(0);
	T_Attachment model = T_Attachment.dao.findByTempId(tempId);
	try {
	    String fileUrl = model.getStr("url");
	    // String url = Constant.PATH_WEBROOT + Constant.PATH_FILE +
	    // fileUrl.substring(10, fileUrl.length());
	    String url = Constant.PATH_WEBROOT + Constant.PATH_FILE + fileUrl;
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
    @Clear
    public void download() {
	String idstr = getPara(0);
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	T_Attachment model = T_Attachment.dao.findById(idstr);
	if (model == null) {
	    render("ajaxDoneErr.html");
	    return;
	}

	/*
	 * String fileName = model.getStr("name"); try { String header =
	 * getRequest().getHeader("User-Agent").toUpperCase(); if
	 * (header.contains("MSIE") || header.contains("TRIDENT") ||
	 * header.contains("EDGE")) { fileName = URLEncoder.encode(fileName,
	 * "utf-8"); fileName = fileName.replace("+", "%20"); // IE下载文件名空格变+号问题
	 * } else { fileName = new String(fileName.getBytes("UTF-8"),
	 * "ISO8859-1"); } } catch (Exception e) { e.printStackTrace(); }
	 */
	try {
	    // 记录日志
	    // new T_Operation_Log(loginModel.getUserId(), "下载了文件，文件名为：：", model.getStr("name"), "下载");
	    String modelurl = model.getStr("url");
	    String filename = model.getStr("name");

	    String url = Constant.PATH_WEBROOT + Constant.PATH_FILE + modelurl;

	    File file = new File(url);

	    if (file.exists()) {
		if (loginModel.getUserId() == 67) {
		    String newurl = Constant.PATH_WEBROOT + "/File/Tempfile/" + filename;
		    File newFile = new File(newurl);
		    FileUtils.copyFile(file, newFile);
		    //file.renameTo(newFile);
		    renderFile(newFile);
		} else {
		    renderFile(file);
		}
	    } else {
		render("ajaxDoneErr.html");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    /**
     * 根据临时ID下载文件
     */
    @Clear
    public void downloadByTempId() {
	String tempId = getPara(0);
	// LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	T_Attachment model = T_Attachment.dao.findByTempId(tempId);
	if (model == null) {
	    render("ajaxDoneErr.html");
	    return;
	}
	try {
	    // 记录日志
	    // new T_Operation_Log(loginModel.getUserId(), "下载了文件，文件名为：：" +
	    // model.getStr("name"), "下载");
	    String fileUrl = model.getStr("url");
	    // String url = Constant.PATH_WEBROOT + Constant.PATH_FILE +
	    // fileUrl.substring(10, fileUrl.length());
	    String url = Constant.PATH_WEBROOT + Constant.PATH_FILE + fileUrl;
	    File file = new File(url);
	    if (file.exists()) {
		renderFile(file);
	    } else {
		render("ajaxDoneErr.html");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
}
