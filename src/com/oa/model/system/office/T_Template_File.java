package com.oa.model.system.office;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

public class T_Template_File extends Model<T_Template_File> {

    private static final long serialVersionUID = 1L;
    public static T_Template_File dao = new T_Template_File();

    /** 列出所有模版 */
    public static String GetTemplateList(String ObjType, String FileType) {
	String mTemplateList = "";
	mTemplateList = "<select name='" + ObjType + "'>";
	mTemplateList = mTemplateList + "<option value=''>--------不用模版--------</option>";
	String Sql = "select recordid,descript from t_template_file where filetype='" + FileType + "'"; // 打开数据库
	try {
	    List<Record> list = Db.find(Sql);
	    for (int i = 0; i < list.size(); i++) {
		Record rc = list.get(i);
		mTemplateList = mTemplateList + "<option value='" + rc.getStr("recordid") + "'>" + rc.getStr("descript")
			+ "</option>";
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	mTemplateList = mTemplateList + "</select>";
	return mTemplateList;
    }
    
    public static T_Template_File getTemplateByRecordId(String recordId) {
	return dao.findFirst("select * from t_template_file where recordid=?", recordId);
    }

}
