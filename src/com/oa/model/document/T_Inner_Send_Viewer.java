package com.oa.model.document;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class T_Inner_Send_Viewer extends Model<T_Inner_Send_Viewer> {

    private static final long serialVersionUID = 1L;
    public static final T_Inner_Send_Viewer dao = new T_Inner_Send_Viewer();

    /**
     * 根据文件Id获取阅读记录
     * 
     * @param doc_id
     * 
     * @return
     */
    public static List<T_Inner_Send_Viewer> getListByDocId(int doc_id) {
	String sql = "select * from t_inner_send_viewer where doc_id = '" + doc_id + "'";
	return dao.find(sql);
    }

    /**
     * 根据userid,doc_id判断是否已经阅读过
     * 
     * @param userid
     *            用户ID
     * @param doc_id
     * @return
     */
    public static boolean isReaded(int userid, int doc_id) {
	boolean flag = false;
	try {
	    String sql = "select * from t_inner_send_viewer where u_id=? and doc_id=?";
	    List<T_Inner_Send_Viewer> list = dao.find(sql, userid, doc_id);
	    if (list.size() > 0) {
		flag = true;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return flag;
    }

}
