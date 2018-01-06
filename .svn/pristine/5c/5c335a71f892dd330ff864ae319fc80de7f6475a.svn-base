package com.oa.model.work;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class T_Working_Day extends Model<T_Working_Day> {

    /**
     * 工作日设定
     */
    private static final long serialVersionUID = 1L;
    public static final T_Working_Day dao = new T_Working_Day();

    public Page<T_Working_Day> page(int pageCurrent, int pageSize, String startDate, String endDate) {
	String select = " SELECT * ";
	String sqlExceptSelect = " FROM t_working_day where 1=1 ";
	if (StrKit.notBlank(startDate)) {
	    sqlExceptSelect += " and workdate >= '" + startDate + "' ";
	}
	if (StrKit.notBlank(endDate)) {
	    sqlExceptSelect += " and workdate <= '" + endDate + "' ";
	}
	sqlExceptSelect += " ORDER by id DESC";

	return dao.paginate(pageCurrent, pageSize, select, sqlExceptSelect);
    }

    public T_Working_Day getWorkingDay(String date) {
	String sql = " SELECT * FROM working_day where date=?";
	return dao.findFirst(sql, date);
    }

}
