package com.oa.model.system.office;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class T_Template_Bookmark extends Model<T_Template_Bookmark> {

    private static final long serialVersionUID = 1L;
    public static T_Template_Bookmark dao = new T_Template_Bookmark();

    public static boolean deleteByRid(String id) {
	return T_Template_Bookmark.dao.deleteBySqlwhere(" where recordid=" + id);
    }

    public static List<T_Template_Bookmark> getList(String recordId) {
	return dao.find(
		"select b.name,b.value from t_template_bookmark a, t_bookmark b where a.name=b.name and a.recordid=?",
		recordId);
    }

}
