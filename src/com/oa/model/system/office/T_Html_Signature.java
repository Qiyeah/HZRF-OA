package com.oa.model.system.office;

import com.jfinal.plugin.activerecord.Model;

public class T_Html_Signature extends Model<T_Html_Signature> {
    private static final long serialVersionUID = 1L;
    public static final T_Html_Signature dao = new T_Html_Signature();
    
    public static T_Html_Signature getHtmlSignature(String sid, String did) {
	return dao.findFirst("SELECT * from t_html_signature Where SignatureID=? and DocumentID=?", sid, did);
    }

}
