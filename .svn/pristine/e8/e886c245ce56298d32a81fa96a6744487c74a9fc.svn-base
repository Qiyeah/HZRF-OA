package com.oa.util;

import java.io.File;
import java.util.HashMap;

import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.util.FileUtils;

public class Report {

    // 生成文件目录路径
    private static String ReportPath = Constant.PATH_WEBROOT + Constant.PATH_REPORT;
    // 模板根目录路径
    private static String TemplatePath = Constant.PATH_WEBROOT + Constant.PATH_TEMPLATE;

    /**
     * 替换文件里面的具体内容
     * 
     * @param report_text
     *            替换的变量集合
     * @param reportName
     *            生成后文档名称
     * @Return 错误信息
     */
    public static String ReplaceReport(HashMap<String, String> report_text, String reportName) {
	String errorMessage = null;
	Draw draw = null;
	try {
	    String report_url = ReportPath + reportName + Constant.DOC;
	    // 文件处理类
	    draw = new Draw();
	    // 打开文件
	    draw.open(report_url);
	    // 替换字符
	    draw.replaceText(report_text, null);// 填充报告信息
	    // 关闭文件
	    draw.close();
	} catch (Exception e) {
	    e.printStackTrace();
	    errorMessage = "出现异常了";
	} finally {
	    if (draw != null) {
		draw.close();
	    }
	}
	return errorMessage;
    }

    // 打印
    public static String replaceTextReceive(Integer pid, String templateName, String reportName,
	    LoginModel loginModel) {
	String errorMessage = null;
	Draw draw = null;
	File report_file = null;
	try {
	    File template = new File(TemplatePath + templateName);
	    if (template.exists()) {
		String report_url = ReportPath + reportName + Constant.DOC;
		String pdf_url = ReportPath + reportName + Constant.PDF;
		report_file = new File(report_url);
		FileUtils.copyFile(template, report_file);
		draw = new Draw();
		draw.open(report_url);
		switch (reportName) {
		case "惠州人防办文件呈批表":
		    draw.replaceTextReceive(pid, loginModel);// 填充收文信息
		    break;
		case "惠州人防办会议审批单":
		    draw.replaceMeetingText(pid, loginModel);// 填充会议信息
		    break;
		case "惠州人防办发文呈批笺":
		    draw.replaceDispatchText(pid, loginModel);// 填充报告信息
		    break;
		case "请休假审批单":
		    draw.replaceLeaveText(pid, loginModel);// 填充报告信息
		    break;
		}
		draw.saveToPDF(pdf_url);
		draw.close();
	    } else {
		errorMessage = "模板不存在，请上传模板！";
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    if (report_file.exists()) {
		report_file.delete();
	    }
	    errorMessage = "出现异常了";
	} finally {
	    if (draw != null) {
		report_file.delete();
		draw.close();
	    }
	}
	return errorMessage;
    }

    public static String fastReport(HashMap<String, String> report_text, String templateName, String reportName) {

	String errorMessage = null;
	Draw draw = null;
	File report_file = null;
	try {
	    File template = new File(TemplatePath + templateName);
	    if (template.exists()) {
		String report_url = ReportPath + reportName + Constant.DOC;
		report_file = new File(report_url);
		FileUtils.copyFile(template, report_file);
		draw = new Draw();
		draw.open(report_url);
		draw.replaceRelationText(report_text, null);// 填充报告信息
		draw.saveToPDF(ReportPath + reportName + Constant.PDF);
		draw.close();
	    } else {
		errorMessage = "介绍信模板不存在，请上传模板！";
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    if (report_file.exists()) {
		report_file.delete();
	    }
	    errorMessage = "出现异常了";
	} finally {
	    if (draw != null) {
		report_file.delete();
		draw.close();
	    }
	}
	return errorMessage;
    }

}
