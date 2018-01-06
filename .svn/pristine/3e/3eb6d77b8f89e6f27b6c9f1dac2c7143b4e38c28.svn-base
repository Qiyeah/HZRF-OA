package com.oa.model.common;

import com.jfinal.plugin.activerecord.Model;

public class T_Superdo extends Model<T_Superdo> {
	private static final long serialVersionUID = 1L;
	public static T_Superdo dao = new T_Superdo();
	/** 读取一个环节的督办信息 */
	public T_Superdo getWorkitem(int pid, int itemid) {
		String strsql = "select * from t_superdo where pid=" + pid + " and itemid='" + itemid +"' order by id desc";
		return this.findFirst(strsql);
	}
}
