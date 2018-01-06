package com.oa.model.work;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhilian.config.Constant;

/** 邮件表 */
public class T_Mail extends Model<T_Mail> {
    private static final long serialVersionUID = 1L;
    public static T_Mail dao = new T_Mail();

    /** 获取收件箱未读的邮件数 */
    public int getReceiveCount(Integer userId) {
	String sql = "select count(*) from t_mail where toid=? and boxid=1 and isread=0";
	return Db.queryInt(sql, userId);
    }

    /** 获取收件箱未读的邮件数 */
    public int getTotalReceiveCount(Integer userId) {
	String sql = "select count(*) from t_mail where toid=? and boxid=1";
	return Db.queryInt(sql, userId);
    }

    /** 获取发件箱未读的邮件数 */
    public int getSendCount(Integer userId) {
	String sql = "select count(*) from t_mail where fromid=? and boxid=2";
	return Db.queryInt(sql, userId);
    }

    /** 获取草稿箱未读的邮件数 */
    public int getDraftCount(Integer userId) {
	String sql = "select count(*) from t_mail where fromid=? and boxid=3 ";
	return Db.queryInt(sql, userId);
    }

    /** 获取垃圾箱未读的邮件数 */
    public int getRubbishCount(Integer userId) {
	String sql = "select count(*) from t_mail where (fromid=? or toid=?) and boxid=4 ";
	return Db.queryInt(sql, userId, userId);
    }

    /** 获取单条记录 */
    public T_Mail getById(Integer id) {
	String select = "select t1.*,t2.name as fromUserName,t2.dlid,t3.name as toUserName";
	StringBuffer sqlExceptSelect = new StringBuffer();
	sqlExceptSelect.append(" from t_mail t1 left join t_user t2 on t1.fromid=t2.id");
	sqlExceptSelect.append(" left join t_user t3 on t1.toid=t3.id");
	sqlExceptSelect.append(" where t1.id=?").append(" order by t1.id desc");
	return dao.findFirst(select + sqlExceptSelect, id);
    }

    /** 删除邮件-存草稿箱 */
    public int deleteFail(String ids) {
	String sql = "update t_mail set boxId=4,deleteTime = getdate() where id in(" + ids + ")";
	return Db.update(sql);
    }

    /** 删除邮件-永久删除 */
    public int deleteReal(String ids) {
	String sql = "delete from t_mail where id in(" + ids + ")";
	return Db.update(sql);
    }

    /** 获取收件箱分页列表 */
    public Page<T_Mail> getPageForReceive(Integer pageNum, Integer pageSize, Integer userId, String title, Integer flag,
	    String senderId, String sdate, String edate) {
	pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
	pageSize = pageSize == null || pageSize < 1 ? Constant.PAGE_SIZE : pageSize;
	StringBuffer sqlWhere = new StringBuffer();
	sqlWhere.append(" and t1.toid=" + userId);
	sqlWhere.append(" and t1.boxid=1");
	if (StrKit.notBlank(title)) {
	    sqlWhere.append(" and t1.title like '%" + title + "%'");
	}
	if (flag != null) {
	    sqlWhere.append(" and t1.isRead = 0 ");
	}
	if (StrKit.notBlank(senderId)) {
	    sqlWhere.append(" and t1.fromId =  " + senderId);
	}
	if (StrKit.notBlank(sdate)) {
	    sqlWhere.append(" and t1.sendTime >=  '" + sdate + "'");
	}
	if (StrKit.notBlank(edate)) {
	    sqlWhere.append(" and t1.sendTime <=  '" + edate + "'");
	}
	sqlWhere.insert(0, " where 1=1");
	String select = "select t1.*,t2.name as fromUserName,t3.name as toUserName";
	StringBuffer sqlExceptSelect = new StringBuffer();
	sqlExceptSelect.append("from t_mail t1 left join t_user t2 on t1.fromid=t2.id");
	sqlExceptSelect.append(" left join t_user t3 on t1.toid=t3.id");
	sqlExceptSelect.append(sqlWhere).append(" order by t1.id desc");
	return dao.paginate(pageNum, pageSize, select, sqlExceptSelect.toString());
    }

    /** 获取发件箱分页列表 */
    public Page<T_Mail> getPageForSend(Integer pageNum, Integer pageSize, Integer userId, String title,
	    String receiverId, String sdate, String edate) {
	pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
	pageSize = pageSize == null || pageSize < 1 ? Constant.PAGE_SIZE : pageSize;
	StringBuffer sqlWhere = new StringBuffer();
	sqlWhere.append(" and t1.fromid=" + userId);
	sqlWhere.append(" and t1.boxid=2");
	if (StrKit.notBlank(title)) {
	    sqlWhere.append(" and t1.title like '%" + title + "%'");
	}
	sqlWhere.insert(0, " where 1=1");
	if (StrKit.notBlank(receiverId)) {
	    sqlWhere.append(" and t1.receiverId in (" + receiverId + ")");
	}
	if (StrKit.notBlank(sdate)) {
	    sqlWhere.append(" and t1.sendTime >=  '" + sdate + "'");
	}
	if (StrKit.notBlank(edate)) {
	    sqlWhere.append(" and t1.sendTime <=  '" + edate + "'");
	}
	String select = "select t1.*,t2.name as fromUserName,t3.name as toUserName";
	StringBuffer sqlExceptSelect = new StringBuffer();
	sqlExceptSelect.append("from t_mail t1 left join t_user t2 on t1.fromid=t2.id");
	sqlExceptSelect.append(" left join t_user t3 on t1.toid=t3.id");
	sqlExceptSelect.append(sqlWhere).append(" order by t1.id desc");
	return dao.paginate(pageNum, pageSize, select, sqlExceptSelect.toString());
    }

    /** 获取草稿箱分页列表 */
    public Page<T_Mail> getPageForDraft(Integer pageNum, Integer pageSize, Integer userId, String title,
	    String receiverId, String sdate, String edate) {
	pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
	pageSize = pageSize == null || pageSize < 1 ? Constant.PAGE_SIZE : pageSize;
	StringBuffer sqlWhere = new StringBuffer();
	sqlWhere.append(" and t1.fromid=" + userId);
	sqlWhere.append(" and t1.boxid=3");
	if (StrKit.notBlank(title)) {
	    sqlWhere.append(" and t1.title like '%" + title + "%'");
	}
	sqlWhere.insert(0, " where 1=1");
	if (StrKit.notBlank(receiverId)) {
	    sqlWhere.append(" and t1.receiverId in (" + receiverId + ")");
	}
	if (StrKit.notBlank(sdate)) {
	    sqlWhere.append(" and t1.sendTime >=  '" + sdate + "'");
	}
	if (StrKit.notBlank(edate)) {
	    sqlWhere.append(" and t1.sendTime <=  '" + edate + "'");
	}
	String select = "select t1.*,t2.name as fromUserName,t3.name as toUserName";
	StringBuffer sqlExceptSelect = new StringBuffer();
	sqlExceptSelect.append("from t_mail t1 left join t_user t2 on t1.fromid=t2.id");
	sqlExceptSelect.append(" left join t_user t3 on t1.toId=t3.id");
	sqlExceptSelect.append(sqlWhere).append(" order by t1.id desc");
	return dao.paginate(pageNum, pageSize, select, sqlExceptSelect.toString());
    }

    /** 获取垃圾箱分页列表 */
    public Page<T_Mail> getPageForRubbish(Integer pageNum, Integer pageSize, Integer userId, String title,
	    String senderId, String sdate, String edate) {
	pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
	pageSize = pageSize == null || pageSize < 1 ? Constant.PAGE_SIZE : pageSize;
	StringBuffer sqlWhere = new StringBuffer();
	sqlWhere.append(" and (t1.fromid=" + userId + " or t1.toid=" + userId + ")");
	sqlWhere.append(" and t1.boxid=4");
	if (StrKit.notBlank(title)) {
	    sqlWhere.append(" and t1.title like '%" + title + "%'");
	}
	sqlWhere.insert(0, " where 1=1");
	if (StrKit.notBlank(senderId)) {
	    sqlWhere.append(" and t1.fromId =  " + senderId);
	}
	if (StrKit.notBlank(sdate)) {
	    sqlWhere.append(" and t1.sendTime >=  '" + sdate + "'");
	}
	if (StrKit.notBlank(edate)) {
	    sqlWhere.append(" and t1.sendTime <=  '" + edate + "'");
	}
	String select = "select t1.*,t2.name as fromUserName,t3.name as toUserName";
	StringBuffer sqlExceptSelect = new StringBuffer();
	sqlExceptSelect.append("from t_mail t1 left join t_user t2 on t1.fromid=t2.id");
	sqlExceptSelect.append(" left join t_user t3 on t1.toid=t3.id");
	sqlExceptSelect.append(sqlWhere).append(" order by t1.id desc");
	return dao.paginate(pageNum, pageSize, select, sqlExceptSelect.toString());
    }

    /** 任务待办 邮件 */
    public List<T_Mail> getMails(Integer userid) {
	String sql = "select t1.*,t2.name username from t_mail t1 left join t_user t2 on t1.fromId=t2.id where t1.toid=? and t1.boxid=1  order by t1.sendtime desc LIMIT 5 ";
	return dao.find(sql, userid);
    }

    /** 恢复邮件 */
    public int recover(String ids) {
	String sql = "update t_mail  set boxId=oboxId where id in(" + ids + ")";
	return Db.update(sql);
    }

    public List<T_Mail> getReceiveMailList(int userId) {
	String sql = "select t1.id,t1.sendTime,t1.title,t2.name as fromUserName,t1.isRead "
		+ "from t_mail t1 left join t_user t2 on t1.fromid=t2.id " + "where t1.toid=" + userId
		+ " and t1.boxid=1 order by t1.id desc";
	return dao.find(sql);
    }

    public List<T_Mail> getRubbishMailList(int userId) {
	String sql = "select t1.id,t1.sendTime,t1.title,t2.name as fromUserName,t1.isRead "
		+ "from t_mail t1 left join t_user t2 on t1.fromid=t2.id " + "where (t1.fromid=" + userId
		+ " or t1.toid=" + userId + ") and t1.boxid=4 order by t1.id desc";
	return dao.find(sql);
    }

    public List<T_Mail> getSendMailList(int userId) {
	String sql = "select t1.id,t1.sendTime,t1.title,t2.name as fromUserName,t1.isRead "
		+ "from t_mail t1 left join t_user t2 on t1.toid=t2.id " + "where t1.fromid=" + userId
		+ " and t1.boxid=2 order by t1.id desc";
	return dao.find(sql);
    }

    public List<T_Mail> getDraftMailList(int userId) {
	String sql = "select t1.id,t1.sendTime,t1.title,t2.name as fromUserName,t1.isRead "
		+ "from t_mail t1 left join t_user t2 on t1.toid=t2.id " + "where t1.fromid=" + userId
		+ " and t1.boxid=3 order by t1.id desc";
	return dao.find(sql);
    }

    public T_Mail getMailDetail(int id) {
	String sql = "select t.id,t.copyer,t.receiver,t.fromId,t.title,t.fjid,t.content,t.sendTime,u.name as fromname,t.oboxId,t.boxId "
		+ "from t_mail t LEFT JOIN t_user u on t.fromId=u.id  " + " where t.id=" + id;
	return dao.findFirst(sql);
    }

}
