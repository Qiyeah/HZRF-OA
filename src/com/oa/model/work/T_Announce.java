package com.oa.model.work;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhilian.config.Constant;

public class T_Announce extends Model<T_Announce> {
    private static final long serialVersionUID = 1L;
    public static final T_Announce dao = new T_Announce();

    /** 公告通知分页 */
    public Page<T_Announce> getAllPageAnno(Integer pageNum, Integer pageSize, String name, Integer u_id) {
	pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
	pageSize = pageSize == null || pageSize < 1 ? Constant.PAGE_SIZE : pageSize;
	StringBuffer sqlWhere = new StringBuffer();
	sqlWhere.insert(0, " where 1=1");
	String select = "select *";
	StringBuffer sqlExceptSelect = new StringBuffer();
	sqlExceptSelect.append(" from t_announce a");
	sqlWhere.append(" AND (a.scope=0 OR ( a.scope=1 AND (','+a.scope_uid+',') like '%," + u_id + ",%'))");
	sqlExceptSelect.append(sqlWhere).append(" order by id desc");
	return dao.paginate(pageNum, pageSize, select, sqlExceptSelect.toString());
    }

    /** 根据标题获取公告 */
    public List<T_Announce> getAllAnnoByTitle(String title, Integer selfid) {
	String sql = "select * from t_announce where title ='" + title + "'";
	if (selfid != null) {
	    sql = sql + " and id not in(" + selfid + ")";
	}
	return T_Announce.dao.find(sql);
    }

    public List<T_Announce> getList(Integer userid) {
	String sql = "select t1.*," + " t2.id viewid " + " from " + " t_announce t1 " + " left join "
		+ " (select * from t_announce_viewer where viewerid = ?) t2 " + " on t2.announceid = t1.id "
		+ " where t1.status=1 " + " AND (t1.scope=0 OR ( t1.scope=1 AND (','+t1.scope_uid+',') like '%,"
		+ userid + ",%'))" + " order by t1.sendtime desc LIMIT 5";
	return dao.find(sql, userid);
    }

    public Integer countUnread(Integer userid) {
	String sql = "select count(1) as count from  t_announce where status=1 AND (scope=0 OR (scope=1 AND (','+scope_uid+',') like '%,"+userid+",%')) "
		+ "and id not in (select announceid from t_announce_viewer where viewerid="+userid+")";
	return dao.findFirst(sql).getInt("count");
    }
    
    public List<T_Announce> getAnnounceList(int userid){
	String sql = "select a.id,a.atype,b.name,a.title,a.sendtime "
		+ " from t_announce a left join t_user b on a.uid=b.id "
		+ " where (a.scope=0 or (a.scope=1 and '%,'+a.scope_uid+',%' like ',"+userid+",'))"
		+ " and a.status=1 "
		+ "order by sendtime desc";
	return dao.find(sql);
    }
    
    public T_Announce getAnnounceDetail(){
	return dao.findFirst("select a.id,a.content,b.name,a.title,a.sendtime from t_announce a left join t_user b on a.uid=b.id where a.status=1 ");
    }
}
