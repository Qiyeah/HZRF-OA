package com.oa.model.system.app;

import com.jfinal.plugin.activerecord.Model;

/**
 * @Author Andersen
 * mail: yawen199@163.com
 * Date: 2016-12-26 11:12
 */
public class T_App_Version extends Model<T_App_Version> {
	private static final long serialVersionUID = 1L;
	public static final T_App_Version dao = new T_App_Version();

	/**
	 * 获取最新版本
	 * 已版本代码为准
	 * @return
	 */
	public T_App_Version getLastVersion(int appType){
		String sql = "SELECT " +
						 " TOP 1 " +
						 "  a.app_version, " +
						 "  a.publish_date, " +
						 "  a.version_desc, " +
						 "  a.version_code, " +
						 "  a.force_update, " +
						 "  a.down_url " +
						 " FROM " +
						 "  t_app_version a " +
						 " WHERE " +
						 "  a.app_type = " + appType +
						 " ORDER BY " +
						 "  a.version_code DESC";
		return dao.findFirst(sql);
	}
}
