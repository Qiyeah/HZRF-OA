package com.oa.model.document;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.oa.model.system.workflow.T_Wprocess;
import com.oa.model.work.T_Grant;
import com.zhilian.model.T_User;
import com.zhilian.util.DateUtils;

public class T_Doc_Receive extends Model<T_Doc_Receive> {

    private static final long serialVersionUID = 1L;
    public static final T_Doc_Receive dao = new T_Doc_Receive();

    /**
     * 根据文号获取收件记录
     * 
     * @param docNo
     *            文号
     * @return
     */
    public List<T_Doc_Receive> getListByDocNo(String docNo) {
	String sql = "select * from t_doc_receive where docno = '" + docNo + "'";
	return dao.find(sql);
    }

    /**
     * 根据文件Id获取收件记录
     * 
     * @param docNo
     *            文号
     * @return
     */
    public List<T_Doc_Receive> getListByAchieveId(Object achieveId) {
	String sql = "select * from t_doc_receive where achieveId=" + achieveId;
	return dao.find(sql);
    }
   
    
    /**
     * 根据pid获取T_Doc_Receive
     * 
     * @param pid
     * @return
     */
    public T_Doc_Receive findByPid(int pid) {
	String sql = "select * from t_doc_receive where pid = " + pid;
	return dao.findFirst(sql);
    }

    /**
     * 根据achieveId获取T_Doc_Receive
     * 
     * @param achieveid
     * @return
     */
    public T_Doc_Receive findByAchieveId(int achieveId) {
	String sql = "select * from t_doc_receive where achieveid = " + achieveId;
	return dao.findFirst(sql);
    }

    /**
     * 根据userid获取T_Doc_Receive的督办数量
     * 
     * 当userid 是 流程的管理员时，取 所有分配了督办员并未结束的收文 当userid 不是 流程的管理时， 取都办员是userid并
     * 未结束的收文
     * 
     * @param userid
     *            用户ID
     * @return
     */
    public Integer countSuperdo(Integer userId, Integer pid) {
	int result = 0;
	try {
	    String sql = "";
	    T_Wprocess wpc = T_Wprocess.dao.getProcessByFlow("docreceive");
	    if (("," + wpc.getStr("managers") + ",").indexOf("," + userId + ",") < 0) {
		sql = "select count(1) as num from t_workflow wf left join t_doc_receive dc on dc.pid = wf.id "
			+ "where wf.flowform='docreceive' and wf.isend='0' and dc.superman=" + userId
			+ " and dc.doflag = 2";
	    } else {
		sql = "select count(1) as num from t_workflow wf left join t_doc_receive dc on dc.pid = wf.id "
			+ "where wf.flowform='docreceive' and wf.isend='0' and dc.superman is not null and dc.doflag = 2 ";
	    }

	    Record rd = Db.findFirst(sql);
	    result = rd.getInt("num");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /**
     * 根据userid获取T_Doc_Receive的待办数量(普通收文、征求意见函)
     * 
     * @param userid
     *            用户ID
     * @return
     */
    /** 统计某人某流程的待办数 */
    public int countTodo(int userid) {
	int result = 0;
	// 授权他人的情况下，本人不在提示任务
	String nowDate = DateUtils.getNowDate();
	String grantSelect = " SELECT * FROM t_grant g where g.u_id = " + userid;
	grantSelect += " AND (g.type = 9 or g.type = 1) AND g.usable='1'";
	grantSelect += " AND (g.s_date is null OR g.s_date <='" + nowDate + "')";
	grantSelect += " AND (g.e_date is null OR g.e_date >= '" + nowDate + "')";
	List<T_Grant> grantList = T_Grant.dao.find(grantSelect);
	if (grantList != null && grantList.size() > 0) {
	    return 0;
	}
	try {
	    String sql = "select count(1) as num from t_workflow wf left join t_doc_receive dc on dc.pid = wf.id  left join t_wactive wa on wf.itemid=wa.id "
		    + "where ((';'+wf.todoman+';') like '%;" + userid + ";%' ";
	    // 授权情况查询
	    grantSelect = " SELECT * FROM t_grant g where g.g_id = " + userid;
	    grantSelect += " AND (g.type = 9 or g.type = 1) AND g.usable='1'";
	    grantSelect += " AND (g.s_date is null OR g.s_date <='" + nowDate + "')";
	    grantSelect += " AND (g.e_date is null OR g.e_date >= '" + nowDate + "')";
	    String grantSet = "";
	    List<T_Grant> grants = T_Grant.dao.find(grantSelect);
	    for (T_Grant grant : grants) {
		grantSet += " OR (';'+wf.todoman+';') like '%;" + grant.getInt("u_id") + ";%'";
	    }
	    sql += grantSet + ") and wf.flowform='docreceive' and wf.isend='0' and dc.doflag>1  ";
	    Record rd = Db.findFirst(sql);
	    result = rd.getInt("num");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /**
     * 根据userid获取T_Doc_Receive的待办数量(一般传阅)
     * 
     * @param userid
     *            用户ID
     * @return
     */
    /** 统计某人某流程的待办数 */
    public int countTodo1(int userid) {
	int result = 0;
	// 授权他人的情况下，本人不在提示任务
	String nowDate = DateUtils.getNowDate();
	String grantSelect = " SELECT * FROM t_grant g where g.u_id = " + userid;
	grantSelect += " AND (g.type = 9 or g.type = 1) AND g.usable='1'";
	grantSelect += " AND (g.s_date is null OR g.s_date <='" + nowDate + "')";
	grantSelect += " AND (g.e_date is null OR g.e_date >= '" + nowDate + "')";
	List<T_Grant> grantList = T_Grant.dao.find(grantSelect);
	if (grantList != null && grantList.size() > 0) {
	    return 0;
	}
	try {
	    String sql = "select count(1) as num from t_workflow wf left join t_doc_receive dc on dc.pid = wf.id "
		    + "where ((';'+wf.todoman+';') like '%;" + userid + ";%' ";
	    // 授权情况查询
	    grantSelect = " SELECT * FROM t_grant g where g.g_id = " + userid;
	    grantSelect += " AND (g.type = 9 or g.type = 1) AND g.usable='1'";
	    grantSelect += " AND (g.s_date is null OR g.s_date <='" + nowDate + "')";
	    grantSelect += " AND (g.e_date is null OR g.e_date >= '" + nowDate + "')";
	    String grantSet = "";
	    List<T_Grant> grants = T_Grant.dao.find(grantSelect);
	    for (T_Grant grant : grants) {
		grantSet += " OR (';'+wf.todoman+';') like '%;" + grant.getInt("u_id") + ";%'";
	    }
	    sql += grantSet + ") and wf.flowform='docreceive' and wf.isend='0' and dc.doflag=1 ";
	    Record rd = Db.findFirst(sql);
	    result = rd.getInt("num");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /**
     * 获取收文详细列表
     * 
     * @param id
     * @return
     */
    public T_Doc_Receive getReceiveDetail(int id) {
	return dao.findFirst("select b.name as superman1, a.* from t_doc_receive a "
		+ " LEFT JOIN t_user b on a.superman=b.id where a.id=" + id);
    }

    public List<T_Doc_Receive> getType(String sql) {
	System.out.println(sql);
	return dao.find(sql);
    }

    public List<T_User> getUsersNotInclude(int did, String doman) {
	String sql = "select u.id, dept.sname as d_id, p.name as pid, u.name "
		+ " from t_user u, t_department dept, t_position p where u.d_id =" + did
		+ " and ('%;'+cast(u.id as varchar(10))+';%') not like ';" + doman + ";'"
		+ "  and u.d_id = dept.id and u.pid=p.id order by u.no ";
	List<T_User> users = T_User.dao.find(sql);
	return users;
    }

}
