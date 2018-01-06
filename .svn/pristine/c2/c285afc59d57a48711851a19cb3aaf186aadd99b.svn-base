package com.oa.model.work;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.jfinal.plugin.activerecord.Model;
import com.zhilian.model.T_User;
import com.zhilian.util.DateUtils;

public class T_Myschedule extends Model<T_Myschedule> {
    private static final long serialVersionUID = 1L;
    public static final T_Myschedule dao = new T_Myschedule();

    /** 读取第一个会议通知 */
    public T_Myschedule getFirstSchedule(int pid, int uid) {
	String strsql = "select * from t_myschedule where meetingid=" + pid + " and u_id = " + uid + " order by id";
	return this.findFirst(strsql);
    }

    // 个人日程查看
    public String events(Integer u_id) {
	StringBuffer buffer = new StringBuffer();
	List<T_Myschedule> list = T_Myschedule.dao.getDate(u_id);
	String events = null;
	try {
	    if (list.size() > 0) {
		Map<Integer, String> map = T_User.dao.hashMapById("name");
		for (T_Myschedule tm : list) {
		    if (null != tm.getDate("wdate")) {
			Date dat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse(String.valueOf(tm.getDate("wdate")));
			// 开始定义日期
			SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
			SimpleDateFormat sdf3 = new SimpleDateFormat("HH");
			SimpleDateFormat sdf4 = new SimpleDateFormat("mm");
			String str1 = sdf0.format(dat);
			String str2 = sdf1.format(dat);
			String str3 = sdf2.format(dat);

			String str4 = sdf3.format(dat);
			String str5 = sdf4.format(dat);
			Integer i = null;
			if (str2.equals("1")) {
			    i = 12;
			} else {
			    i = Integer.valueOf(str2) - 1;
			}

			Date edat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse(String.valueOf(tm.getDate("edate")));
			// 结束定义日期
			SimpleDateFormat esdf0 = new SimpleDateFormat("yyyy");
			SimpleDateFormat esdf1 = new SimpleDateFormat("MM");
			SimpleDateFormat esdf2 = new SimpleDateFormat("dd");
			SimpleDateFormat esdf3 = new SimpleDateFormat("HH");
			SimpleDateFormat esdf4 = new SimpleDateFormat("mm");
			String estr1 = esdf0.format(edat);
			String estr2 = esdf1.format(edat);
			String estr3 = esdf2.format(edat);

			String estr4 = esdf3.format(edat);
			String estr5 = esdf4.format(edat);
			Integer ei = null;
			if (estr2.equals("1")) {
			    ei = 12;
			} else {
			    ei = Integer.valueOf(estr2) - 1;
			}
			String allDay = "false"; // 是否是全天事件,默认不是
			if (null != tm.getDate("event")) {
			    allDay = "true";
			}
			String color = null;
			// 今天事件橙色
			if (CalendarToday(tm.getDate("wdate"), tm.getDate("edate"))) {
			    color = "orange";
			}
			// 过去事件灰色
			if (CalendarMonth(tm.getDate("wdate"), tm.getDate("edate"))) {
			    color = "#cfcfcf";
			}

			buffer.append("{id:" + tm.getInt("id") + ",title : '" + map.get(tm.getInt("u_id")) + ": "
				+ tm.getStr("title").replace("'", "\\'") + "',start : new Date(" + str1 + ", " + i
				+ ", " + str3 + " ," + str4 + " ," + str5 + "),end:" + "new Date(" + estr1 + ", " + ei
				+ ", " + estr3 + " ," + estr4 + " ," + estr5 + "),allDay : " + allDay + ",color:'"
				+ color + "'},");
			String eve = buffer.toString();
			events = eve.substring(0, eve.length() - 1);
		    }
		}
		String eve = buffer.toString();
		events = eve.substring(0, eve.length() - 1);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return events;
    }

    /**
     * 今天事件橙色
     * 
     * @param sdate
     * @param edate
     * @return
     */
    public boolean CalendarToday(Date sdate, Date edate) {
	boolean flag = false;
	Calendar startCalendar = Calendar.getInstance();
	Calendar endCalendar = Calendar.getInstance();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	Set<String> set = new TreeSet<String>();
	try {
	    Date startDate = df.parse(String.valueOf(sdate));
	    startCalendar.setTime(startDate);
	    Date endDate = df.parse(String.valueOf(edate));

	    endCalendar.setTime(endDate);
	    String ss = df.format(startDate);
	    set.add(ss);
	    String ess = df.format(endDate);
	    set.add(ess);
	    while (true) { // 获得两个日期之间的所有日期
		startCalendar.add(Calendar.DAY_OF_MONTH, 1);
		if (startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()) {
		    String s = df.format(startCalendar.getTime());
		    set.add(s);
		} else {
		    break;
		}
	    }
	    String date = DateUtils.getNowDate();
	    for (String se : set) { // 今天事件橙色
		if (se.equals(date)) {
		    flag = true;
		}
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	}

	return flag;
    }

    /**
     * 过去事件灰色
     * 
     * @param sdate
     * @param edate
     * @return
     */
    public boolean CalendarMonth(Date sdate, Date edate) {
	boolean flag = false;
	Calendar startCalendar = Calendar.getInstance();
	Calendar endCalendar = Calendar.getInstance();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	Set<String> set = new TreeSet<String>();
	try {
	    Date startDate = df.parse(String.valueOf(sdate));
	    startCalendar.setTime(startDate);
	    Date endDate = df.parse(String.valueOf(edate));

	    endCalendar.setTime(endDate);
	    String ess = df.format(endDate); // 只要结束的日期
	    set.add(ess);

	    Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();

	    String date = DateUtils.getNowDate();
	    for (String se : set) { // 不是今天事件灰色
		c1.setTime(df.parse(date + " 00:00:00"));
		c2.setTime(df.parse(se));
		int result = c1.compareTo(c2);
		if (result > 0) {
		    flag = true;
		}
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	}

	return flag;
    }

    // 日历日程
    public List<T_Myschedule> getDate(Integer u_id) {
	return dao.find("select * from t_myschedule where (scope=3 and u_id= " + u_id
		+ ") OR scope=0 OR (scope=1 AND (','+receiver_id+',') like '%," + u_id + ",%')");
    }

    // 主页日历日程
    public String getSchedule(Integer userid) {
	StringBuffer buffer = new StringBuffer();
	Set<String> set = new TreeSet<String>();
	List<T_Myschedule> list = T_Myschedule.dao.getDate(userid);
	String schedule = null;
	try {
	    if (list.size() > 0) {
		for (T_Myschedule model : list) { // 获得日程天数
		    if (null != model.getDate("wdate")) {

			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = df.parse(String.valueOf(model.getDate("wdate")));
			startCalendar.setTime(startDate);
			Date endDate = df.parse(String.valueOf(model.getDate("edate")));

			endCalendar.setTime(endDate);
			String ss = df.format(startDate).replaceAll("-", "");
			set.add(ss.substring(4, 8));
			String ess = df.format(endDate).replaceAll("-", "");
			set.add(ess.substring(4, 8));
			while (true) { // 获得两个日期之间的所有日期
			    startCalendar.add(Calendar.DAY_OF_MONTH, 1);
			    if (startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()) {
				String s = df.format(startCalendar.getTime()).replaceAll("-", "");
				set.add(s.substring(4, 8));
			    } else {
				break;
			    }
			}
		    }
		}

	    }

	    if (set.size() > 0) {
		for (String se : set) {
		    buffer.append("\"" + se + "\",");
		}
		String str = buffer.toString();
		schedule = str.substring(0, str.length() - 1);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return schedule;
    }

    // 获得日程标题
    public String getTitle(Integer u_id, String date) {
	StringBuffer buffer = new StringBuffer();

	List<T_Myschedule> list = dao.find("select * from t_myschedule where ((scope=3 and u_id =" + u_id
		+ " ) OR scope=0 OR (scope=1 AND (','+receiver_id+',') like '%," + u_id + ",%'))" + " and wdate <= '"
		+ date + " 23:59:59' and '" + date + " 00:00:00' <= edate");
	if (list.size() > 0) {
	    Map<Integer, String> map = T_User.dao.hashMapById("name");
	    buffer.append(date + "<br>");
	    for (T_Myschedule model : list) {
		buffer.append(map.get(model.getInt("u_id")) + ":"); // 得到姓名
		buffer.append(model.getStr("title") + "<br>"); // 得到标题
	    }
	}
	return buffer.toString();
    }

    // 获得日程标题
    public String getPage(Integer u_id, String date) {
	StringBuffer buffer = new StringBuffer();

	List<T_Myschedule> list = dao.find("select * from t_myschedule where ((scope=3 and u_id =" + u_id
		+ " ) OR scope=0 OR (scope=1 AND (','+receiver_id+',') like '%," + u_id + ",%'))" + " and wdate <= '"
		+ date + " 23:59:59' and '" + date + " 00:00:00' <= edate");
	buffer.append(
		"<table class=\"table table-condensed table-hover\" style=\"table-layout: fixed;\"><thead><tr><th width=\"50px\" align=\"center\">姓名</th><th>日程标题</th><th width=\"60px\">操作 <a href=\"Main/Myschedule/addip?add=add\" data-toggle=\"dialog\" data-mask=\"ture\" data-id=\"Myscheduleaddip\" data-title=\"日程添加\" data-width=\"800\" data-height=\"500\" style=\"color: green\"><i class=\"fa fa-lg fa-plus\" title=\"添加\"></i></a></tr></thead> <tbody>");
	if (list.size() > 0) {
	    Map<Integer, String> map = T_User.dao.hashMapById("name");
	    for (T_Myschedule model : list) {
		String title = model.getStr("title"); // 得到标题

		if (u_id == model.getInt("u_id")) { // 本人才能有编辑删除
		    buffer.append("<tr><td align=\"center\"> " + map.get(model.getInt("u_id"))
			    + " </td> <td style=\"white-space: nowrap;overflow: hidden;text-overflow: ellipsis;\"> "
			    + title + " </td> " + " <td align=\"center\"><a href=\"Main/Myschedule/updateip?id="
			    + model.getInt("id") + "&date=" + date
			    + "\" data-toggle=\"dialog\" data-mask=\"ture\" data-id=\"Myscheduleupdateip\" data-title=\"日程修改\"data-width=\"800\" data-height=\"500\" style=\"color:#005094\"><i class=\"fa fa-lg fa-edit\" title=\"编辑\"></i></a>"
			    + " <a href=\"Main/Myschedule/delete1?id=" + model.getInt("id") + "&u_id="
			    + model.getInt("u_id") + "&date=" + date
			    + "\" data-toggle=\"doajax\" data-confirm-msg=\"您确定要删除该记录吗?\" style=\"color: red\"><i class=\"fa fa-lg fa-trash-o\" title=\"删除\"></i></a></td></tr>");
		} else {
		    buffer.append("<tr><td align=\"center\"> " + map.get(model.getInt("u_id"))
			    + " </td> <td style=\"white-space: nowrap;overflow: hidden;text-overflow: ellipsis;\"> "
			    + title + " </td><td></td></tr>");
		}
	    }
	    buffer.append("<tbody></table>");
	}
	return buffer.toString();
    }

    public String getMyschedule(int id, String mouth) throws ParseException {
	int yy = Integer.parseInt(mouth.substring(0, 4));
	int mm = Integer.parseInt(mouth.substring(5, mouth.length()));
	String smm;
	if (mm < 10) {
	    smm = "0" + mm;
	} else {
	    smm = mm + "";
	}
	String yymm = yy + "-" + smm;
	int dd = getMaxDayByYearMonth(yy, mm);
	List<T_Myschedule> myschedule = dao.find("select * from t_myschedule where ((scope=3 and u_id= " + id
		+ ") OR scope=0 OR (scope=1 AND (','+receiver_id+',') like '%," + id + ",%')) and edate>= '" + mouth
		+ "-1 00:00:00' " + "and wdate<='" + mouth + "-" + dd + " 23:59:59'");

	List<String> list = new ArrayList<String>();
	String sdate = "";
	// StringBuffer sb = new StringBuffer();
	String empty = "0";

	for (int i = 0; i < myschedule.size(); i++) {
	    if (myschedule.get(i).getDate("event") != null) {// 如果为全天事件，则获取中间的所有日期
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		Calendar mouthstartCalendar = Calendar.getInstance();
		Calendar mouthendCalendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		startCalendar.setTime(myschedule.get(i).getDate("event"));
		endCalendar.setTime(myschedule.get(i).getDate("event1"));
		Date mouthstart = sdf.parse(mouth + "-1");
		Date mouthend = sdf.parse(mouth + "-" + dd);
		mouthstartCalendar.setTime(mouthstart);
		mouthendCalendar.setTime(mouthend);
		// startCalendar.add(Calendar.DAY_OF_MONTH, 1);
		for (; startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis(); startCalendar
			.add(Calendar.DAY_OF_MONTH, 1)) {
		    sdate = startCalendar + "";
		    String ssdate = sdf.format(startCalendar.getTime());
		    sdate = ssdate.substring(8, 10);
		    if (!list.contains(sdate) && ssdate.substring(0, 7).equals(yymm)) {
			list.add(sdate);
		    }
		}

		// getAllDate(myschedule.get(i).getDate("event"),myschedule.get(i).getDate("event1"));
	    }
	    Date date = myschedule.get(i).getDate("wdate");
	    sdate = date + "";
	    sdate = sdate.substring(8, 10);
	    if (!list.contains(sdate)) {
		list.add(sdate);
	    }
	}
	if (list.size() > 0) {
	    String dates = "";
	    for (int i = 0; i < list.size(); i++) {
		dates += "," + list.get(i);
	    }
	    return dates.substring(1);
	} else {
	    return empty;
	}

    }

    public T_Myschedule getDetail(int id) {
	return dao
		.findFirst("select a.*,b.name from t_myschedule a left join t_user b on a.u_id=b.id where a.id=" + id);
    }

    public List<T_Myschedule> getSchduleEvent(int uid, String date) {
	return dao.find("select id,title,wdate,edate,event,event1 from t_myschedule where ((scope=3 and u_id= " + uid
		+ ") OR scope=0 OR (scope=1 AND (','+receiver_id+',') like '%," + uid + ",%'))  and wdate <= '" + date
		+ " 23:59:59' and '" + date + " 00:00:00' <= edate");

    }

    public int getMaxDayByYearMonth(int year, int month) {
	int maxDay = 0;
	int day = 1;
	Calendar calendar = Calendar.getInstance();
	calendar.set(year, month - 1, day);
	maxDay = calendar.getActualMaximum(Calendar.DATE);
	return maxDay;
    }
}