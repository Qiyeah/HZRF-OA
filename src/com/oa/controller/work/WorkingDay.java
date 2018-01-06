package com.oa.controller.work;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.work.T_Working_Day;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;

/**
 * 工作日时间设定
 *
 */
@RouteBind(path = "Main/WorkingDay", viewPath = "Work/WorkingDay")
public class WorkingDay extends BaseAssociationController {

    private static String menuId = "WorkingDay";

    public void main() {
	String startDate = "";
	String endDate = "";
	Integer pageSize = Constant.PAGE_SIZE;
	Integer pageCurrent = 1;

	try {
	    startDate = getPara("startDate");
	    endDate = getPara("endDate");
	    if (pageSize <= 0) {
		throw new Exception();
	    }
	    if (null != getPara("pageSize") && !"".equals(getPara("pageSize").trim())) {
		pageSize = getParaToInt("pageSize");
	    }
	    if (null != getPara("pageCurrent") && !"".equals(getPara("pageCurrent").trim())) {
		pageCurrent = getParaToInt("pageCurrent");
	    }
	    if (pageCurrent <= 0) {
		throw new Exception();
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	try {
	    Page<T_Working_Day> page = T_Working_Day.dao.page(pageCurrent, pageSize, startDate, endDate);
	    setAttr("page", page);
	    keepPara();
	    render("main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void addip() {
	render("add.jsp");
    }

    public void add() {
	try {
	    final String[] date = getPara("workingDays").split(",");
	    if (!StrKit.notBlank(date)) {
		toErrorJson("请选择日期！");
		return;
	    }
	    final StringBuffer msg = new StringBuffer();
	    Boolean succeed = Db.tx(new IAtom() {
		public boolean run() throws SQLException {
		    boolean value1 = true;
		    for (String st : date) {
			if (null == T_Working_Day.dao.getWorkingDay(st)) {
			    T_Working_Day model = new T_Working_Day();
			    model.set("workdate", st);
			    value1 = model.save();
			} else {
			    msg.append(",");
			    msg.append(st);
			}
		    }
		    return value1;
		}
	    });
	    if (succeed) {
		String success = "";
		success = (null == msg || msg.length() == 0 ? "" : "<br>已添加过日期：" + msg.deleteCharAt(0).toString());

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("statusCode", Constant.STATUS_CODE_OK);
		jsonMap.put("message", Constant.SUCCESS + success);
		// jsonMap.put("tabId", menuId);
		// jsonMap.put("closeCurrent", "true");
		jsonMap.put("flag", getPara("flag"));
		renderJson(jsonMap);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void delete() {
	Integer id = getParaToInt();
	try {
	    if (T_Working_Day.dao.deleteById(id)) {
		toBJUIJson(Constant.STATUS_CODE_OK, Constant.SUCCESS, menuId, "", "", "", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.getMessage();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

}
