package com.oa.model.document;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class T_Inner_Send extends Model<T_Inner_Send> {

    private static final long serialVersionUID = 1L;
    public static final T_Inner_Send dao = new T_Inner_Send();

    /**
     * 根据文号获取内文发文列表
     * 
     * @param docNo
     *            文号
     * @return
     */
    public List<T_Inner_Send> getListByDocNo(String docNo) {
	String sql = "select * from t_inner_send where docno = '" + docNo + "'";
	return dao.find(sql);
    }

    /**
     * 根据用户ID获取未读内文发文列表
     * 
     * @param userid
     *            用户ID
      * @param flag
     *           0:未读/1:已读
     *            
     * @return
     */
    public Page<T_Inner_Send> getReadListByUserId(Integer userid, String condition, int pageNumber, int pageSize, int flag) {
	String select = "select * ";
	String sqlExceptSelect = " FROM t_inner_send WHERE (',' + receiver + ',') LIKE '%," + userid + ",%' ";
	if (flag == 1) {
	    sqlExceptSelect += " and id in (select doc_id FROM t_inner_send_viewer where u_id=" + userid + ") ";
	} else {
	    sqlExceptSelect += " and id not in (select doc_id FROM t_inner_send_viewer where u_id=" + userid + ") ";
	}
	if (StrKit.notBlank(condition)) {
	    sqlExceptSelect += " and (title like '%" + condition + "%' or docno like '%" + condition + "%') ";
	}
	sqlExceptSelect += " order by id desc";
	return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }
    
    /**
     * 根据userid获取T_Inner_Send的待阅数量
     * 
     * @param userid
     *            用户ID
     * @return
     */
    /** 统计某人某流程的待办数 */
    public int countTodo(int userid) {
	int result = 0;
	// 授权他人的情况下，本人不在提示任务
	try {

	    String sql = "SELECT count(1) as num FROM t_inner_send WHERE (',' + receiver + ',') LIKE '%," + userid
		    + ",%' and id not in (select doc_id FROM t_inner_send_viewer where u_id=" + userid + ")";
	    Record rd = Db.findFirst(sql);
	    result = rd.getInt("num");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

}
