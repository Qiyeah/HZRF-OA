package com.oa.model.common;

import java.util.List;

import com.zhilian.model.T_User;
import com.jfinal.plugin.activerecord.Model;
import com.oa.model.system.workflow.T_Workitem;

public class T_Commessage extends Model<T_Commessage> {
	private static final long serialVersionUID = -1L;
	public static T_Commessage dao = new T_Commessage();

	/** 获取该用户没有确定的第一条记录 */
	public T_Commessage getFirst(int deptId,int userId) {
		String sql = "SELECT t.* FROM t_commessage t WHERE (t.d_id = ? OR t.u_id = ? OR FIND_IN_SET(?,t.u_ids) )AND t.isread = ? ORDER BY t.id ASC;";
		return dao.findFirst(sql,new Object[]{deptId,userId,userId,0});
	}

    /**
     * 修改信息状态为已读
     * @param id 信息ID
     * @return 修改是否成功
     */
    public boolean messageRead(int id){
        T_Commessage commessage = new T_Commessage();
        commessage.set("id",id);
        commessage.set("isread", 1);
        return commessage.update();
    }
    
    /** 获取所有可用的用户列表 */
	public List<T_User> getDocList() {
		return T_User.dao.find("select * from t_user where status=1 and pid <> 10 order by d_id,no");
	}
	
	/** 获取所有可用的用户列表 */
	public List<T_User> getAllList() {
		return T_User.dao.find("select * from t_user where status=1 and pid <> 10 and pid <> 1  and pid <> 3 order by d_id,no");
	}
	
	public List<T_User> getOtherList(String pid){
	    List<T_Workitem> item=T_Workitem.dao.find("select user1 from t_workitem where pid="+pid);
	    String users="";
	    for(int i=0;i<item.size();i++){
		users+=","+item.get(i).getStr("user1");
	    }
	    if(users.length()>1){
		users=users.substring(1);
	    }
	    
	    String sql="select * from t_user where status=1 and pid <> 10 and id not in("+users+")  order by d_id,no";
	    return T_User.dao.find(sql);
	}
	
	public String getOtherUsers(String pid){
	    List<T_Workitem> item=T_Workitem.dao.find("select user1 from t_workitem where pid="+pid);
	    String users="";
	    for(int i=0;i<item.size();i++){
		users+=","+item.get(i).getStr("user1");
	    }
	    users=users.substring(1);
	    //String sql="select * from t_user where status=1 and pid <> 10 and id not in("+users+")  order by d_id,no";
	    return users;
	}

}
