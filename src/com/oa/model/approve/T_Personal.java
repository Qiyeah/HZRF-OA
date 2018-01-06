package com.oa.model.approve;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.zhilian.model.T_User;

public class T_Personal extends Model<T_Personal> {

    private static final long serialVersionUID = 1L;
    public static final T_Personal dao = new T_Personal();

    /** 计算工龄 */
    public int statWorkyear(int userid) {
	int result = 0;
	try {
	    String sql = "select begindate from t_personal where u_id=" + userid;
	    Record rd = Db.findFirst(sql);
	    if (rd != null && rd.get("begindate") != null) {
		Date date = rd.getDate("begindate");
		Date now = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		int year1 = cal.get(Calendar.YEAR);
		int month1 = cal.get(Calendar.MONTH);

		cal.setTime(date);
		int year2 = cal.get(Calendar.YEAR);
		int month2 = cal.get(Calendar.MONTH);
		int t = year1 - year2;
		int m = month1 - month2;
		if (m < 0) {
		    t = t - 1;
		    m = 12 + m;
		}
		result = t;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /** 计算年龄 */
    @SuppressWarnings("deprecation")
    public int statYear(int userid) {
	int result = 0;
	try {
	    String sql = "select birthday from t_personal where u_id=" + userid;
	    Record rd = Db.findFirst(sql);
	    if (rd != null && rd.getBoolean("brithday") != null) {
		Date date = rd.getDate("birthday");
		Date now = new Date();
		int year1 = now.getYear();
		int year2 = date.getYear();
		int month1 = now.getMonth();
		int month2 = now.getMonth();
		int day1 = now.getDay();
		int day2 = now.getDay();
		if (month1 < month2 | (month1 == month2 && day1 < day2))
		    result = year1 - year2 - 1;
		else
		    result = year1 - year2;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    public List<T_Personal> findPersonnalList(int pid) {
	return dao
		.find("select b.id,b.name,a.gradename,a.phone,a.mbphone from t_personal a LEFT JOIN t_user b ON a.u_id=b.id WHERE b.d_id="
			+ pid + " order by b.no");
    }

    public T_Personal findByUid(int id) {
	return dao
		.findFirst("select a.*,b.name from t_personal a LEFT JOIN t_user b ON a.u_id=b.id where a.u_id=" + id);
    }

    public List<T_User> findPAD() {
	String sql = "select a.id,a.name,b.id as d_id,b.sname from t_user a left join t_department b on a.d_id=b.id where 1=1 ";
	return T_User.dao.find(sql);
    }

    public List<T_User> getTop(Integer num) {
	return T_User.dao.find("SELECT top " + num + " * FROM t_user where id>1 order by webcount+appcount desc");
    }

    public String getMobileByUsers(String userids) {
	StringBuffer sb = new StringBuffer();
	List<T_Personal> list = dao.find("select * from t_personal where u_id in (" + userids + ")");
	if (list.size() > 0) {
	    for (int i = 0; i < list.size(); i++) {
		sb.append("," + list.get(i).getStr("mbphone"));
	    }
	    sb.deleteCharAt(0);
	}
	return sb.toString();
    }
}
