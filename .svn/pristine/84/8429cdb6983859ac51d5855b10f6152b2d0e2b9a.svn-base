package com.oa.model.system.workflow;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class T_Workflow extends Model<T_Workflow> {

    private static final long serialVersionUID = 1L;

    public static final T_Workflow dao = new T_Workflow();

    public List<T_Workflow> findByItem(Object itemId) {
	return dao.find("select * from t_workflow where itemid= ?", itemId);
    }

    public List<T_Workflow> findByIds(String ids) {
	return dao.find("select * from t_workflow where id in (" + ids + ")");
    }

    /** 统计某人某流程的待办数 */
    public int countTodo(int userid, String flow) {
	int result = 0;
	try {
	    String sql = "select count(*) as count from t_workflow where ';'+todoman+';' like '%;" + userid
		    + ";%' and flowform='" + flow + "' and isend='0' ";
	    Record rd = Db.findFirst(sql);
	    result = rd.getInt("count");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /** 统计某人某流程的新到数 */
    public int countNew(int userid, String flow) {
	int result = 0;
	try {
	    String sql = "select count(*) as count from t_workflow where ';'+todoman+';' like '%;" + userid
		    + ";%' and flowform='" + flow + "' and isend='0' ";
	    Record rd = Db.findFirst(sql);
	    result = rd.getInt("count");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /** 统计某人某流程的正在办理中数 */
    public int countDing(int userid, String flow) {
	// 授权情况转化
	int result = 0;
	try {
	    String sql = "select count(*) as count from t_workflow where ';'+reader+';' like  '%;" + userid
		    + ";%' and flowform='" + flow + "' and isend='0'";
	    Record rd = Db.findFirst(sql);
	    result = rd.getInt("count");
	    ;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /** 统计某人请休假审批完毕数 */
    public int countleverdo(int userid, String flow) {

	int result = 0;
	try {
	    String sql = "select count(*) as count from t_workflow where (';'+starter+';') like  '%;" + userid
		    + ";%' and flowform='" + flow + "' and itemid <> '5' and isend='0'";
	    Record rd = Db.findFirst(sql);
	    result = rd.getInt("count");
	    ;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /** 统计某人请休假审批完毕数 */
    public int countlevering(int userid, String flow) {

	int result = 0;
	try {
	    String sql = "select count(*) as count from t_workflow where (';'+starter+';') like  '%;" + userid
		    + ";%' and flowform='" + flow + "' and itemid = '5' and isend='0'";
	    Record rd = Db.findFirst(sql);
	    result = rd.getInt("count");
	    ;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /** 统计某人某流程的督办数 */
    public int countSuperdo(int userid, String flow) {
	int result = 0;
	try {
	    T_Wprocess process = T_Wprocess.dao.findFirst("select managers from t_wprocess where flow = ?", flow);
	    String managers = process.getStr("managers");
	    String sql = "select count(*) as count from t_workflow where flowform='" + flow + "' and isend='0'";
	    if (null == managers || (("," + managers + ",").indexOf("," + userid + ",") < 0)) {
		sql += " and (';'+supervisor+';') like '%;" + userid + ";%'";
	    }
	    Record rd = Db.findFirst(sql);
	    result = rd.getInt("count");
	    ;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /** 判断某人对某流程实例是否有督办权限 */
    public int canSuperdo(int pid, int userid) {
	int result = 0;
	try {
	    T_Workflow temp = T_Workflow.dao.findById(pid);
	    String flow = temp.getStr("flowform");
	    String supervisor = temp.getStr("supervisor");
	    T_Wprocess process = T_Wprocess.dao.findFirst("select managers from t_wprocess where flow = ?", flow);
	    String managers = process.getStr("managers");
	    if (null != supervisor && (',' + supervisor + ',').indexOf("," + userid + ",") >= 0) {
		result = 1;
	    } else if (null == managers || (("," + managers + ",").indexOf("," + userid + ",") < 0)) {
		result = 1;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /** 统计某人的发文拟稿中数 */
    public int countnigao(int userid) {
	int result = 0;
	try {
	    // 获取发文的开始环节
	    T_Wprocess wp = T_Wprocess.dao.findFirst("select * from t_wprocess where flow=?", "docsend");
	    int itemid = wp.getInt("beginstep");
	    // 计算单人在此环节的流程数
	    String sql = "select count(wf.id) as count from t_workflow wf where (';'+wf.todoman+';') like '%;" + userid
		    + ";%' and wf.isend='0' and wf.itemid=" + itemid;
	    Record rd = Db.findFirst(sql);
	    result = rd.getInt("count");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    // 获取收文待办列表分页
    public Page<T_Workflow> getReceiveTodoList(int id, String condition, int pageNumber, int pageSize) {
	String select = "SELECT dc.pid as pid,dc.id as docid,dc.unit,wf.starttime,dc.title as doctitle,dc.docno as docno,wa.name as active ";
	String sqlExceptSelect = " from t_workflow wf left join t_doc_receive dc on wf.id = dc.pid left join t_wactive wa on wf.itemid=wa.id where ';'+wf.todoman+';' like '%;"
		+ id + ";%' and wf.flowform='docreceive' and wf.isend='0' and dc.doflag =2 ";
	if (StrKit.notBlank(condition)) {
	    sqlExceptSelect += " and (dc.title like '%" + condition + "%' or dc.docno like '%" + condition + "%') ";
	}
	sqlExceptSelect += " order by wf.id desc";
	return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);

    }

    // 获取收文待办列表未分页
    public List<T_Workflow> getReceiveTodoList1(int id, String condition) {
	String sql = "SELECT dc.pid as pid,dc.id as docid,dc.unit,wf.starttime,dc.title as doctitle,dc.docno as docno,wa.name as active ";
	sql += " from t_workflow wf left join t_doc_receive dc on wf.id = dc.pid left join t_wactive wa on wf.itemid=wa.id where ';'+wf.todoman+';' like '%;"
		+ id + ";%' and wf.flowform='docreceive' and wf.isend='0' and dc.doflag =2  ";
	if (StrKit.notBlank(condition)) {
	    sql += " and (dc.title like '%" + condition + "%' or dc.docno like '%" + condition + "%') ";
	}
	sql += " order by wf.id desc";
	return dao.find(sql);
    }

    // 获取收文已办列表分页
    public Page<T_Workflow> getReceiveDoneList(int id, String condition, int pageNumber, int pageSize) {
	String select = "SELECT dc.pid as pid,dc.id as docid,dc.unit,wf.starttime,dc.title as doctitle,dc.docno as docno,wa.name as active ";
	String sqlExceptSelect = " from t_workflow wf left join t_doc_receive dc on wf.id = dc.pid left join t_wactive wa on wf.itemid=wa.id where ';'+wf.reader+';' like '%;"
		+ id + ";%' and wf.flowform='docreceive' and wf.isend='0' and dc.doflag=2 ";
	if (StrKit.notBlank(condition)) {
	    sqlExceptSelect += " and (dc.title like '%" + condition + "%' or dc.docno like '%" + condition + "%') ";
	}
	sqlExceptSelect += " order by wf.id desc";
	return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

    // 获取收文已办列表未分页
    public List<T_Workflow> getReceiveDoneList(int id, String condition) {
	String sql = "SELECT dc.pid as pid,dc.id as docid,dc.unit,wf.starttime,dc.title as doctitle,dc.docno as docno,wa.name as active ";
	sql += " from t_workflow wf left join t_doc_receive dc on wf.id = dc.pid left join t_wactive wa on wf.itemid=wa.id where ';'+wf.reader+';' like '%;"
		+ id + ";%' and wf.flowform='docreceive' and wf.isend='0' and dc.doflag=2 ";
	if (StrKit.notBlank(condition)) {
	    sql += " and (dc.title like '%" + condition + "%' or dc.docno like '%" + condition + "%') ";
	}
	sql += " order by wf.id desc";
	return dao.find(sql);
    }

    public int getIdByPosition(int id, String position) {
	List<T_Workflow> list = dao
		.find("SELECT dc.id as receive_id,dc.unit,wf.starttime,dc.title as doctitle,dc.docno as docno from t_workflow wf left join t_doc_receive dc on wf.id = dc.pid "
			+ "where ((';'+wf.todoman+';') like '%;" + id + ";%'"
			+ ") and wf.flowform='docreceive' and wf.isend='0' and dc.doflag =2");
	if (list.size() > 0) {
	    return list.get(Integer.parseInt(position)).getInt("receive_id");
	} else {
	    return 0;
	}
    }

    // 发文待办列表分页
    public Page<T_Workflow> getDocsendTodoList(int id, String condition, int pageNumber, int pageSize) {
	String select = "select wf.id as pid,dc.id as docid,wf.starttime, dp.fname as unit,dc.title as doctitle, dc.docno as docno,wa.name as active";
	String sqlExceptSelect = " from t_workflow wf left join t_doc_send dc on wf.id = dc.pid"
		+ " left join t_department dp on wf.startdept=dp.id left join t_wactive wa on wf.itemid=wa.id where ';'+todoman+';' like '%;"
		+ id + ";%' " + "and wf.flowform='docsend' and wf.isend='0' ";
	if (StrKit.notBlank(condition)) {
	    sqlExceptSelect += " and (dc.title like '%" + condition + "%' or dc.docno like '%" + condition + "%')";
	}
	sqlExceptSelect += " ORDER BY wf.id DESC";
	return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

    public Page<T_Workflow> getDocsendDoneList(int id, String condition, int pageNumber, int pageSize) {// 发文已办列表,condition为返回的查询条件,
	String select = "select wf.id as pid,dc.id as docid,wf.starttime as applytime, dp.fname as unit,dc.title as doctitle, dc.docno as docno,wa.name as active ";
	String sqlExceptSelect = "from t_workflow wf left join t_doc_send dc on wf.id = dc.pid"
		
		+ " left join t_department dp on wf.startdept=dp.id left join t_wactive wa on wf.itemid=wa.id where ';'+reader+';' like '%;"
		+ id + ";%' " + "and wf.flowform='docsend' and wf.isend='0' ";
	if (StrKit.notBlank(condition)) {
	    sqlExceptSelect += " and (dc.title like '%" + condition + "%' or dc.docno like '%" + condition + "%')";
	}
	sqlExceptSelect += " ORDER BY wf.id DESC";
	return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

    /**
     * 请休假待办分页
     * 
     * @param id
     *            用户ID
     * @param condition
     *            查询条件
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<T_Workflow> getLeaveTodoList(int id, String condition, int pageNumber, int pageSize) {
	String select = "select wf.id as pid,dc.approvedate,dc.id as docid,wf.starttime, dp.fname as unit,dc.type,dc.dayt,u.name,wa.name as active";
	String sqlExceptSelect = " from t_workflow wf left join t_leave_approve dc on wf.id = dc.pid left join t_user u on dc.u_id=u.id "
		+ " left join t_department dp on wf.startdept=dp.id left join t_wactive wa on wf.itemid=wa.id where ';'+todoman+';' like '%;"
		+ id + ";%' " + "and wf.flowform='leave' and wf.isend='0' ";
	if (StrKit.notBlank(condition)) {
	    sqlExceptSelect += " and (dc.type like '%" + condition + "%')";
	}
	sqlExceptSelect += " ORDER BY wf.id DESC";
	return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

    /**
     * 请休假已办分页
     * 
     * @param id
     *            用户ID
     * @param condition
     *            查询条件
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<T_Workflow> getLeaveDoneList(int id, String condition, int pageNumber, int pageSize) {
	String select = "select wf.id as pid,dc.id as docid,wf.starttime, dp.fname as unit,dc.title as doctitle, dc.docno as docno,wa.name as active ";
	String sqlExceptSelect = "from t_workflow wf left join t_doc_send dc on wf.id = dc.pid"
		+ " left join t_department dp on wf.startdept=dp.id left join t_wactive wa on wf.itemid=wa.id where ';'+reader+';' like '%;"
		+ id + ";%' " + "and wf.flowform='leave' and wf.isend='0' ";
	if (StrKit.notBlank(condition)) {
	    sqlExceptSelect += " and (dc.title like '%" + condition + "%' or dc.docno like '%" + condition + "%')";
	}
	sqlExceptSelect += " ORDER BY wf.id DESC";
	return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

    /** 发文归档列表 */
    public Page<T_Workflow> getDocsendCabList(int pageNumber, int pageSize, int departmentId, String name) {// 发文归档
	String select = "select dp.fname as unit,dc.id, dc.title as title, dc.docno as docno ,dc.approvedate as receivedate ";
	String sqlExceptSelect = "from t_workflow wf "
		+ "left join t_doc_send dc on wf.id=dc.pid left join t_department dp on dc.d_id=dp.id "
		+ "where wf.flowform='docsend' and wf.isend='1' and wf.isnormalend='1'";
	if (departmentId != 2) {
	    sqlExceptSelect += " and (dc.d_id ='" + departmentId + "' ) ";
	}
	if (StrKit.notBlank(name)) {
	    sqlExceptSelect += " and (dc.title like '%" + name + "%' or dc.docno like '%" + name + "%')";
	}
	sqlExceptSelect += " ORDER BY wf.id DESC";
	return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

    /** 收文归档列表 */
    public Page<T_Workflow> getReceiveCabList(int pageNumber, int pageSize, int departmentId, String name) {// 收文归档分页
	String select = "select dc.id , dc.unit as unit, dc.title as title, dc.docno as docno ,dc.receivedate ";
	String sqlExceptSelect = "from t_workflow wf  "
		+ "left join t_doc_receive dc on wf.id = dc.pid left join t_department dp on dc.receive1=dp.id  "
		+ "where wf.flowform='docreceive' and wf.isend='1' and wf.isnormalend> 0 ";
	if (departmentId != 2) {
	    sqlExceptSelect += " and (dc.d_id ='" + departmentId + "' or dc.doflag = 1) ";
	}
	if (StrKit.notBlank(name)) {
	    sqlExceptSelect += " and (dc.title like '%" + name + "%' or dc.docno like '%" + name + "%')";
	}
	sqlExceptSelect += "ORDER BY wf.id DESC";
	return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

    @SuppressWarnings("static-access")
    public Page<T_Workflow> getReadabilityList(String content, int pageNumber, int pageSize) {// 一般阅知
	Date dNow = new Date(); // 当前时间
	Date dBefore = new Date();
	Calendar calendar = Calendar.getInstance(); // 得到日历
	calendar.setTime(dNow);// 把当前时间赋给日历
	calendar.add(calendar.MONTH, -3); // 设置为前3月
	dBefore = calendar.getTime(); // 得到前3月的时间
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
	String defaultStartDate = sdf.format(dBefore); // 格式化前3月的时间
	String select = "select dc.id as id,dc.receivedate, dc.unit as unit, dc.title, dc.docno ";
	String sqlExceptSelect = "from t_workflow wf left join t_doc_receive dc on wf.id = dc.pid "
		+ " where wf.flowform like 'docreceive' and wf.isend='1' and wf.isnormalend > 0 and dc.doflag=1 and wf.endtime > '"
		+ defaultStartDate + "'";
	if (StrKit.notBlank(content)) {
	    sqlExceptSelect += " and (dc.docno like '%" + content + "%' or dc.unit like '%" + content
		    + "%' or dc.title like '%" + content + "%' )";
	}
	sqlExceptSelect += " order by dc.id desc";
	return dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

}
