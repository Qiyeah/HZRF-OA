package com.oa.model.work;

import com.jfinal.plugin.activerecord.Model;

public class T_Meeting_Approve extends Model<T_Meeting_Approve> {

	private static final long serialVersionUID = 1L;
	public static final T_Meeting_Approve dao = new T_Meeting_Approve();
	/**
     * 根据pid获取T_Doc_Send
     * @param pid
     * @return
     */
    public T_Meeting_Approve findByPid(int pid) {
        String sql = "select * from t_meeting_approve where pid = "+pid;
        return dao.findFirst(sql);
    }
}
