package com.oa.model.work;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.zhilian.model.T_User;

public class T_Note extends Model<T_Note> {
	private static final long serialVersionUID = 1L;
	public static final T_Note dao = new T_Note();
	
	public List<T_Note> getList(int uid){
	    String sql="select id,content,wdate,title from t_note where u_id="+uid;
	    return dao.find(sql);
	}
	
	public List<T_User> getUsersInfo(){
	String sql="select a.id,a.name,dept.sname,p.name as pname from t_user a LEFT JOIN t_department dept"
		+ " on dept.id=a.d_id LEFT JOIN t_position p on p.id=a.pid where a.status=1 order by a.d_id,a.no";
	return T_User.dao.find(sql);
	}
	
	
	public List<T_User> getUsersInfo1(){
		String sql="select a.id,a.name,dept.sname as d_id,p.name as pid from t_user a LEFT JOIN t_department dept"
			+ " on dept.id=a.d_id LEFT JOIN t_position p on p.id=a.pid where a.status=1  and a.pid <> 10 order by a.d_id,a.no";
		return T_User.dao.find(sql);
		}
	public List<T_User> getAllList(){
		String sql="select a.id,a.name,dept.sname,p.name from t_user a LEFT JOIN t_department dept"
			+ " on dept.id=a.d_id LEFT JOIN t_position p on p.id=a.pid where a.status=1 and a.pid <> 10 and a.pid <> 1  and a.pid <> 3 order by a.d_id,a.no";
		return T_User.dao.find(sql);
		}
	
	public T_User getUserInfo(Object id){
		String sql="select a.id,a.name,dept.sname as d_id,p.name as pid from t_user a LEFT JOIN t_department dept"
			+ " on dept.id=a.d_id LEFT JOIN t_position p on p.id=a.pid where id="+id;
		return T_User.dao.findFirst(sql);
		}

	
}
