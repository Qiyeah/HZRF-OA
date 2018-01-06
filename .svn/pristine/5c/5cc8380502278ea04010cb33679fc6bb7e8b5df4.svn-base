package com.oa.model.system.workflow;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class T_Myopinion extends Model<T_Myopinion> {
    private static final long serialVersionUID = 1L;
    public static final T_Myopinion dao = new T_Myopinion();

    /** 根据用户ID获取意见 */
    public List<T_Myopinion> getListByUserID(int userid) {
	String sql = "select * from t_myopinion t where t.u_id = " + userid + " and t.status = 1";
	return T_Myopinion.dao.find(sql);
    }
}
