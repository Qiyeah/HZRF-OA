package com.oa.model.work;

import com.jfinal.plugin.activerecord.Model;

/**
 * 邮件所有相关附件表
 */
public class T_Mail_Attach extends Model<T_Mail_Attach> {
	private static final long serialVersionUID = 1L;
	public static T_Mail_Attach dao = new T_Mail_Attach();

	/** 获取刚添加的附件记录 */
	public T_Mail_Attach getLastSave() {
		String sql = "select * from t_mail_attach order by id desc;";
		return dao.findFirst(sql);
	}
}
