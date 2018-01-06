package com.oa.controller.work;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.system.log.T_Operation_Log;
import com.oa.model.work.T_Date;
import com.oa.model.work.T_Myschedule;
import com.oa.model.work.T_MyscheduleMessage;
import com.oa.model.work.T_ScheduleType;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_User;
import com.zhilian.util.DateUtils;

@RouteBind(path = "Main/Myschedule", viewPath = "Work/Myschedule")
public class Myschedule extends BaseAssociationController {

    private String menuId = "Myschedule";

    public void main() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	try {
	    String events = T_Myschedule.dao.events(u_id);
	    if (StrKit.notBlank(events)) {
		setAttr("events", events);
	    }
	    render("main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    public void addip() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	setAttr("u_id", loginModel.getUserId());
	setAttr("name", loginModel.getUserName());
	setAttr("sdate", getPara("sdate"));
	setAttr("edate", getPara("edate"));
	setAttr("add", getPara("add"));
	render("add.jsp");
    }

    public void add() {
	try {
	    T_Myschedule model = getModel(T_Myschedule.class);
	    LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	    Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();
	    if (StrKit.isBlank(getPara("doc-check-t"))) {
		if (null == model.getDate("wdate") || null == model.getDate("edate")) {
		    toErrorJson("日程时间不能为空");
		    return;
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

		c1.setTime(sf.parse(String.valueOf(model.getDate("wdate"))));
		c2.setTime(sf.parse(String.valueOf(model.getDate("edate"))));
		int result = c1.compareTo(c2);
		if (result != 0) {
		    toErrorJson("只有全天事件才能跨天！");
		    return;
		}

		c1.setTime(model.getDate("wdate"));
		c2.setTime(model.getDate("edate"));
		int result1 = c1.compareTo(c2);
		if (result1 >= 0) {
		    toErrorJson("日程时间的结束时间必须大于开始时间");
		    return;
		}

	    } else {
		if (null == model.getDate("event") || null == model.getDate("event1")) {
		    toErrorJson("日程时间不能为空");
		    return;
		}
		c1.setTime(model.getDate("event"));
		c2.setTime(model.getDate("event1"));
		int result = c1.compareTo(c2);

		if (result > 0) {
		    toErrorJson("日程时间的结束时间必须大于或相等开始时间");
		    return;
		}
		model.set("remind", getParaToInt("rem")); // 全天事件是否提醒
		model.set("wdate", model.getDate("event"));
		model.set("edate", model.getDate("event1"));
	    }
	    if (model.getInt("scope") == 1 && StrKit.isBlank(getPara("userName"))) {
		toErrorJson("请选择公开人员！");
		return;
	    }
	    model.set("receiver_id", getPara("userId"));
	    model.set("receiver", getPara("userName"));
	    model.set("type", getPara("chairman.type"));
	    if (model.save()) {
		new T_Operation_Log(loginModel.getUserId(), "添加了个人日程：" + model.getStr("title"), "Myschedule");// 记录日记
		if (StrKit.notBlank(model.getStr("type"))) {
		    List<T_ScheduleType> list = T_ScheduleType.dao.getType(model.getInt("u_id"), model.getStr("type"));
		    if (list.size() == 0) {
			T_ScheduleType scheduletype = new T_ScheduleType();
			scheduletype.set("u_id", model.getInt("u_id"));
			scheduletype.set("type", model.getStr("type"));
			scheduletype.save();
		    }
		}
		if (null != model.getInt("remind")) {
		    Calendar calendar = Calendar.getInstance();
		    Integer remind = model.getInt("remind");
		    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String date = null;
		    Date before = null;
		    switch (remind) {
		    case 1:
			calendar.setTime(model.getDate("wdate"));
			// 5分钟前
			calendar.add(Calendar.MINUTE, -5);
			before = calendar.getTime();
			date = sim.format(before);
			break;
		    case 2:
			calendar.setTime(model.getDate("wdate"));
			// 10分钟前
			calendar.add(Calendar.MINUTE, -10);
			before = calendar.getTime();
			date = sim.format(before);
			break;
		    case 3:
			calendar.setTime(model.getDate("wdate"));
			// 30分钟前
			calendar.add(Calendar.MINUTE, -30);
			before = calendar.getTime();
			date = sim.format(before);
			break;
		    case 4:
			calendar.setTime(model.getDate("wdate"));
			// 60分钟前
			calendar.add(Calendar.MINUTE, -60);
			before = calendar.getTime();
			date = sim.format(before);
			break;
		    case 5:
			calendar.setTime(model.getDate("wdate"));
			// 120分钟前
			calendar.add(Calendar.MINUTE, -120);
			before = calendar.getTime();
			date = sim.format(before);
			break;
		    case 6:
			calendar.setTime(model.getDate("wdate"));
			// 1天前
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			before = calendar.getTime();
			date = sim.format(before);
			break;
		    default:
			break;
		    }
		    // 记录日程提醒
		    T_MyscheduleMessage messagemodel = new T_MyscheduleMessage();
		    messagemodel.set("sdate", date);
		    messagemodel.set("content", model.getStr("title"));
		    messagemodel.set("u_id", model.getInt("u_id"));
		    messagemodel.set("m_id", model.getInt("id"));
		    messagemodel.save();
		}
		if (StrKit.notBlank(getPara("add"))) {
		    T_Date td = new T_Date(); // 主页添加时用于刷新用
		    String ddate = String.valueOf(model.getDate("wdate"));
		    td.set("date", ddate.substring(0, 10));
		    td.save();
		    toBJUIJson(200, Constant.SUCCESS, "main", "", "", "true", "");
		} else {
		    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
		}

	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    public void updateip() {
	Integer id = getParaToInt("id");
	T_Myschedule model = T_Myschedule.dao.findById(id);
	if (null == model) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    return;
	}
	setAttr("HMname", T_User.dao.hashMapById("name"));
	setAttr("model", model);
	setAttr("date", getPara("date"));
	render("update.jsp");
    }

    public void update() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	Integer u_id = loginModel.getUserId();
	try {
	    T_Myschedule model = getModel(T_Myschedule.class);
	    int userId = model.getInt("u_id");
	    if (userId != u_id) {
		toErrorJson("只有本人才能执行该操作");
		return;
	    }

	    Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();
	    if (StrKit.isBlank(getPara("doc-check-t"))) {
		if (null == model.getDate("wdate") || null == model.getDate("edate")) {
		    toErrorJson("日程时间不能为空");
		    return;
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

		c1.setTime(sf.parse(String.valueOf(model.getDate("wdate"))));
		c2.setTime(sf.parse(String.valueOf(model.getDate("edate"))));
		int result = c1.compareTo(c2);
		if (result != 0) {
		    toErrorJson("只有全天时件才能跨天！");
		    return;
		}

		c1.setTime(model.getDate("wdate"));
		c2.setTime(model.getDate("edate"));
		int result1 = c1.compareTo(c2);
		if (result1 >= 0) {
		    toErrorJson("日程时间的结束时间必须大于开始时间");
		    return;
		}

	    } else {
		if (null == model.getDate("event") || null == model.getDate("event1")) {
		    toErrorJson("日程时间不能为空");
		    return;
		}
		c1.setTime(model.getDate("event"));
		c2.setTime(model.getDate("event1"));
		int result = c1.compareTo(c2);
		if (result > 0) {
		    toErrorJson("日程时间的结束时间必须大于或相等开始时间");
		    return;
		}
		model.set("remind", getParaToInt("rem")); // 全天事件是否提醒
		model.set("wdate", model.getDate("event"));
		model.set("edate", model.getDate("event1"));
	    }
	    if (model.getInt("scope") == 1 && StrKit.isBlank(getPara("userName"))) {
		toErrorJson("请选择公开人员！");
		return;
	    }
	    model.set("receiver_id", getPara("userId"));
	    model.set("receiver", getPara("userName"));
	    model.set("type", getPara("chairman.type"));

	    if (model.update()) {
		if (StrKit.notBlank(model.getStr("type"))) {
		    List<T_ScheduleType> list = T_ScheduleType.dao.getType(model.getInt("u_id"), model.getStr("type"));
		    if (list.size() == 0) {
			T_ScheduleType scheduletype = new T_ScheduleType();
			scheduletype.set("u_id", model.getInt("u_id"));
			scheduletype.set("type", model.getStr("type"));
			scheduletype.save();
		    }
		}

		T_MyscheduleMessage messagemodel = T_MyscheduleMessage.dao.getMid(model.getInt("id"));
		if (null != messagemodel) {
		    messagemodel.delete();
		}
		if (null != model.getInt("remind")) {
		    Calendar calendar = Calendar.getInstance();
		    Integer remind = model.getInt("remind");
		    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String date = null;
		    Date before = null;

		    switch (remind) {
		    case 1:
			calendar.setTime(model.getDate("wdate"));
			// 5分钟前
			calendar.add(Calendar.MINUTE, -5);
			before = calendar.getTime();
			date = sim.format(before);
			break;
		    case 2:
			calendar.setTime(model.getDate("wdate"));
			// 10分钟前
			calendar.add(Calendar.MINUTE, -10);
			before = calendar.getTime();
			date = sim.format(before);
			break;
		    case 3:
			calendar.setTime(model.getDate("wdate"));
			// 30分钟前
			calendar.add(Calendar.MINUTE, -30);
			before = calendar.getTime();
			date = sim.format(before);

			break;
		    case 4:
			calendar.setTime(model.getDate("wdate"));
			// 60分钟前
			calendar.add(Calendar.MINUTE, -60);
			before = calendar.getTime();
			date = sim.format(before);
			break;
		    case 5:
			calendar.setTime(model.getDate("wdate"));
			// 120分钟前
			calendar.add(Calendar.MINUTE, -120);
			before = calendar.getTime();
			date = sim.format(before);
			break;
		    case 6:
			calendar.setTime(model.getDate("wdate"));
			// 1天前
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			before = calendar.getTime();
			date = sim.format(before);
			break;
		    default:
			break;
		    }
		    // 记录日程提醒
		    T_MyscheduleMessage memodel = new T_MyscheduleMessage();
		    memodel.set("sdate", date);
		    memodel.set("content", model.getStr("title"));
		    memodel.set("u_id", model.getInt("u_id"));
		    memodel.set("m_id", model.getInt("id"));
		    memodel.save();
		}
		if (StrKit.notBlank(getPara("date"))) {
		    T_Date td = new T_Date(); // 主页修改时用于刷新用
		    td.set("date", getPara("date"));
		    td.save();
		    toBJUIJson(200, Constant.SUCCESS, "main", "", "", "true", "");
		} else {
		    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
		}
	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    public void delete() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	Integer id = getParaToInt(0);
	int uid = getParaToInt(1);
	try {
	    if (u_id != uid) {
		toErrorJson("只有本人才能执行该操作");
		return;
	    }
	    T_Myschedule model = T_Myschedule.dao.findById(id);
	    if (model.delete()) {
		new T_Operation_Log(loginModel.getUserId(), "删除了个人日程：" + model.getStr("title"), "Myschedule");// 记录日记
		T_MyscheduleMessage message = T_MyscheduleMessage.dao.getMid(id);
		if (null != message) {
		    message.delete();
		}
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    // 主页删除
    @Clear
    public void delete1() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	Integer id = getParaToInt("id");
	int uid = getParaToInt("u_id");
	try {
	    if (u_id != uid) {
		toErrorJson("只有本人才能执行该操作");
		return;
	    }
	    T_Myschedule model = T_Myschedule.dao.findById(id);
	    if (model.delete()) {
		T_MyscheduleMessage message = T_MyscheduleMessage.dao.getMid(id);
		if (null != message) {
		    message.delete();
		}
		T_Date td = new T_Date();// 主页删除时用于刷新用
		if (StrKit.notBlank(getPara("date"))) {
		    td.set("date", getPara("date"));
		    td.save();
		}
		toBJUIJson(200, Constant.SUCCESS, "main", "", "", "", "");
	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交");
	}
    }

    public void view() {
	Integer id = getParaToInt();
	T_Myschedule temp = T_Myschedule.dao.findById(id);
	if (null == temp) {
	    toErrorJson("您提交的数据有误，记录不存在！");
	    return;
	}
	setAttr("t_Myschedule", temp);
	render("view.jsp");
    }

    // 日程类型
    @Clear
    public void type() {
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	Integer u_id = loginModel.getUserId();
	List<T_ScheduleType> list = T_ScheduleType.dao.getTypeLike(u_id, getPara("inputValue"));
	setAttr("list", list);
	// setAttr("list1", T_Common_Detail.getListByKey("MyscheduleType"));
	render("find_type.jsp");
    }

    @Clear
    public void statisticsip() {
	setAttr("list", T_User.dao.list());
	Page<T_Myschedule> page = T_Myschedule.dao.paginate(1, 20, "select * ", "from t_myschedule where id=0");
	setAttr("page", page);
	render("statistics.jsp");
    }

    @Clear
    public void statistics() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	Integer u_id = null;
	String sdate = "";
	String edate = "";
	String type = "";
	String select = "select * ";
	String sqlExceptSelect = "from t_myschedule where 1=1 ";
	try {
	    if (StrKit.notBlank(getPara("u_id"))) {
		u_id = getParaToInt("u_id");
		sqlExceptSelect += " and u_id=" + u_id;
		setAttr("u_id", u_id);
	    }
	    if (StrKit.notBlank(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and wdate >= '" + sdate + " 00:00:00'";
		setAttr("sdate", sdate);
	    }
	    if (StrKit.notBlank(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and  edate <= '" + edate + " 23:59:59'";
		setAttr("edate", edate);
	    }
	    if (StrKit.notBlank(getPara("chairman.type"))) {
		type = getPara("chairman.type");
		sqlExceptSelect += " and  type like '%" + type + "%'";
		setAttr("type", type);
	    }
	    if (null != getPara("pageSize") && !"".equals(getPara("pageSize").trim())) {
		pageSize = getParaToInt("pageSize");
	    }
	    if (pageSize <= 0) {
		throw new Exception();
	    }
	    if (null != getPara("pageCurrent") && !"".equals(getPara("pageCurrent").trim())) {
		pageNumber = getParaToInt("pageCurrent");
	    }
	    if (pageNumber <= 0) {
		throw new Exception();
	    }
	    if (StrKit.isBlank(getPara("u_id")) || StrKit.notBlank(getPara("u_id")) || StrKit.notBlank(getPara("sdate"))
		    || StrKit.notBlank(getPara("edate")) || StrKit.notBlank(getPara("chairman.type"))) {
		Page<T_Myschedule> page = T_Myschedule.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
		setAttr("page", page);
		setAttr("HMuser", T_User.dao.hashMapById("name"));
	    }
	    setAttr("list", T_User.dao.list());
	    render("statistics.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    @Clear
    public void datetime() {
	renderText(DateUtils.getNowTime());
    }

    @Clear
    public void date() {
	renderText(DateUtils.getNowDate());
    }
}