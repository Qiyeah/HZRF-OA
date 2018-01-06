package com.oa.model.work;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.zhilian.model.T_User;

public class T_Grant extends Model<T_Grant> {
	private static final long serialVersionUID = 1L;
	public static final T_Grant dao = new T_Grant();
	
	/**
	 * 
	 * @param sdate
	 * @param edate
	 * @param type 授权范围
	 * @param id如果为修改，则剔除当前项
	 * @param userId 授权人
	 * @return
	 */
	public boolean isGrant(Date sdate, Date edate, String type , int id, int userId){//判断授权人该时间段对该范围是否已进行授权
	    boolean flag=true;
	    String sql = "select * from t_grant "
	    	+ " where id not in (select id from t_grant "
	    	+ " where s_date >='"+edate+"' or e_date <= '"+sdate+"' "
	    	+ " or '"+sdate+"' >=e_date or '"+edate+"'<= s_date) "//找到所有时间段有重复的项
	    	+ " and ( type = 9 or type="+type+")"//找到范围重复的项
	    	+ " and u_id = "+userId
	    	+ " and usable = 0 " ;
	    if(id>0){
		sql += " and id != "+id;
	    }
	    System.out.println(sql);
	    List<T_Grant> grant=dao.find(sql);
	    if(grant.size()==0){
		flag = false;
	    }
	    
	    return flag;
	}
	
	
	
	public String isHadOtherGrant(Date sdate, Date edate, String type , int id, int userId){//判断授权人在此期间已被他人授权
	    String name="";
	    String sql = "select * from t_grant "
	    	+ " where id not in (select id from t_grant "
	    	+ " where s_date >='"+edate+"' or e_date <= '"+sdate+"' "
	    	+ " or '"+sdate+"' >=e_date or '"+edate+"'<= s_date) "//找到所有时间段有重复的项
	    	+ " and ( type = 9 or type="+type+")"//找到范围重复的项
	    	+ " and g_id = "+userId  
	    	+ " and usable = 0 " ;
	    if(id>0){
		sql += " and id != "+id;
	    }
	    System.out.println(sql);
	    List<T_Grant> grant=dao.find(sql);
	    if(grant.size()>0){
		for(int i=0; i<grant.size(); i++){
		    name="、"+T_User.getUserNameById(grant.get(i).getInt("u_id"));
		}
		name = name.substring(1);
		return name;
	    }else{
		return null;
	    }
	    
	  
		
	}

}
