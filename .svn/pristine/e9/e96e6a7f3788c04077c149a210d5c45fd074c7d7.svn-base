package com.oa.model.system.office;

import com.jfinal.plugin.activerecord.Model;

public class T_Doc_File extends Model<T_Doc_File> {
    private static final long serialVersionUID = 1L;
    public static final T_Doc_File dao = new T_Doc_File();
    
    public static T_Doc_File getDocFileByRecordId(String recordId) {
	return dao.findFirst("select * from t_doc_file where pid=?", recordId);
    }

}
