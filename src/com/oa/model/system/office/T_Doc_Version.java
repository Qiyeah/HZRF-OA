package com.oa.model.system.office;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class T_Doc_Version extends Model<T_Doc_Version> {
    private static final long serialVersionUID = 1L;
    public static final T_Doc_Version dao = new T_Doc_Version();
    
    public static List<T_Doc_Version> getList(String recordId) {
	return dao.find("select * from t_doc_version where pid=?", recordId);
    }

}
