package com.oa.model.work;

import com.jfinal.plugin.activerecord.Model;

public class T_Announce_Viewer extends Model<T_Announce_Viewer> {
	private static final long serialVersionUID = 1L;
	public static final T_Announce_Viewer dao = new T_Announce_Viewer();

	public T_Announce_Viewer getViewer(Integer announceid, Integer userid) {
		String sql = "select * from t_announce_viewer where announceid = ? and viewerid = ?";
		return dao.findFirst(sql, announceid, userid);
	}
}
