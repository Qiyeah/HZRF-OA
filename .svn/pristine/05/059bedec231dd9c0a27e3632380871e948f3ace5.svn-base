package com.oa.model.work;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
/**
 * 日程类型
 * @author zhilian199
 *
 */
public class T_ScheduleType extends Model<T_ScheduleType>{
  
    private static final long serialVersionUID = -2658469314387921019L;
    public static final T_ScheduleType dao = new T_ScheduleType();
    
    public List<T_ScheduleType> getTypeLike(Integer u_id,String type){
	if (StrKit.notBlank(type)) {
	    return dao.find("SELECT * FROM t_scheduletype WHERE u_id="+u_id+" and type like '%"+type+"%' order by type");
	} else {
	    return dao.find("SELECT * FROM t_scheduletype WHERE u_id="+u_id+" order by type");
	}
    }

    public List<T_ScheduleType> getType(Integer u_id,String type){
  	return dao.find("SELECT * FROM t_scheduletype WHERE u_id="+u_id+" and type='"+type+"'");
      }
}
