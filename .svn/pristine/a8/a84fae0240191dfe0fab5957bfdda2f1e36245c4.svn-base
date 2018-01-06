package com.oa.model.system.log;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhilian.config.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 短信记录
 */
public class T_Sms_Log extends Model<T_Sms_Log> {
    private static final long serialVersionUID = -1L;
    public static final T_Sms_Log dao = new T_Sms_Log();

    /** 无参构造函数 */
    public T_Sms_Log() {
    }

    /** 有参构造函数 */
    public T_Sms_Log(String users, String mobiles, String content, String result, Integer pid) {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	set("users", users);
	set("mobiles", mobiles);
	set("content", content);
	set("sendtime", format.format(new Date()));
	set("pid", pid);
	set("result", result);
	save();
    }

    /** 分页查回 */
    public Page<T_Sms_Log> getPage(Integer pageNum, Integer pageSize, String sdate, String edate, String user) {
	pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
	pageSize = pageSize == null || pageSize < 1 ? Constant.PAGE_SIZE : pageSize;
	String select = "select * from t_sms_log where 1=1 ";
	StringBuffer sqlExceptSelect = new StringBuffer();
	if (StrKit.notBlank(user)) {
	    sqlExceptSelect.append(" and users like '%" + user + "%' ");
	}
	if (sdate != null && !"".equals(sdate)) {
	    sqlExceptSelect.append(" and sendtime>='" + sdate + " 00:00:00'");
	}
	if (edate != null && !"".equals(edate)) {
	    sqlExceptSelect.append(" and sendtime<='" + edate + " 23:59:59'");
	}
	sqlExceptSelect.append(" order by sendtime desc");
	return dao.paginate(pageNum, pageSize, select, sqlExceptSelect.toString());
    }

}
