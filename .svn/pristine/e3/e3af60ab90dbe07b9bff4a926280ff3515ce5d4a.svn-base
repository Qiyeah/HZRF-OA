package com.oa.model.system.office;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class T_Doc_Signature extends Model<T_Doc_Signature> {
    private static final long serialVersionUID = 1L;
    public static final T_Doc_Signature dao = new T_Doc_Signature();
    
    public static List<T_Doc_Signature> getList(String recordId) {
	return dao.find("select * from t_doc_signature where pid=?", recordId);
    }

}
