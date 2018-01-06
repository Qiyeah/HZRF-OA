package com.oa.model.work;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.zhilian.util.DateUtils;

/**
 * 个人日程提醒
 * @author zhilian199
 *
 */
public class T_MyscheduleMessage extends Model<T_MyscheduleMessage>{

    private static final long serialVersionUID = 3981708378978862864L;
    public static final T_MyscheduleMessage dao = new T_MyscheduleMessage();
    
    public T_MyscheduleMessage getMid(Integer id){
	return dao.findFirst("SELECT * FROM t_myschedulemessage WHERE m_id=" + id);
    }

    public List<T_MyscheduleMessage> getSchedule(Integer userId) {
	// TODO Auto-generated method stub
	String nowtime=DateUtils.getNowTime();
	return find("SELECT * from t_myschedulemessage where sdate >= '"+nowtime+"' and isread is null and u_id="+userId);
    }
}
