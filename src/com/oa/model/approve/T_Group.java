package com.oa.model.approve;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class T_Group extends Model<T_Group> {

    private static final long serialVersionUID = 1L;
    public static final T_Group dao = new T_Group();
    
    public List<T_Group> findByUserId(int uid){
	return dao.find("select * from t_group where u_id="+uid+" or u_id=0");
    }
}
