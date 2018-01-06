package com.oa.model.system.office;

import com.jfinal.plugin.activerecord.Model;

public class T_Signature extends Model<T_Signature> {
    private static final long serialVersionUID = 1L;
    public static T_Signature dao = new T_Signature();

    public static T_Signature getSignature(String name, String pswd) {
	return dao.findFirst("select * from t_signature where markname=? and password=?", name, pswd);
    }

}
