package com.oa.controller.common;

import java.io.File;

import com.jfinal.aop.Clear;
import com.jfinal.upload.UploadFile;
import com.oa.util.Excel;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.controller.BaseController;

@Clear
@RouteBind(path = "Main/upload", viewPath = "Common/Upload")
public class Upload extends BaseController {

    public void uploadip() {
	setAttr("flag", getParaToInt(0));
	render("upload.jsp");
    }

    /** 上传 */

    public void upload() { // 上传excel
	try {
	    String navTabID = null;
	    UploadFile excelFile = getFile();
	    // key == 1 表示出国境证件导入；key == 2 表示 出国干部导入 key == 3 党组织转接导入
	    Integer key = getParaToInt(0);
	    if (key == 3) {
		navTabID = "Relation";
	    } else {
		navTabID = "eecertmg";
	    }
	    if (importData(excelFile.getFile(), key)) {
		toBJUIJson(200, Constant.SUCCESS, navTabID, "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    // ** 直接导入 *//*

    public boolean importData(File excelFile, int key) {
	Excel excel = null;
	try {
	    excel = new Excel(excelFile); // 获取excel表信息
	    for (int row = 1; row < excel.getRownum(); row++) {
		switch (key) {
		default:
		    break;
		}
	    }
	} catch (Exception e) {
	    // toErrorJson("上传数据日期格式不对，请检查后重新上传！");
	    e.printStackTrace();
	    return false;

	} finally {
	    if (excel != null) {
		excel.close();
	    }
	}
	return true;
    }

}