package com.oa.model.system.workflow;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 流程表单域
 */
public class T_Wfield extends Model<T_Wfield> {

	private static final long serialVersionUID = 1L;

	public static final T_Wfield dao = new T_Wfield();

	public List<T_Wfield> getAllOpinionfieldByWid(int wid) {
		return this.find("select * from t_wfield where type='1' and wid=?", wid);
	}

	public List<T_Wfield> getAllCondfieldByWid(int wid) {
		return this.find("select * from t_wfield where type='2' and wid=?", wid);
	}

	public static String getOpinionName(int wid, String opinionfield) {
		String opinionname = "";
		T_Wfield wfield = T_Wfield.dao.findFirst("select * from t_wfield where wid=? and name=?", wid, opinionfield);
		if (wfield != null)
			opinionname = wfield.getStr("description");
		return opinionname;
	}

}
