package com.oa.controller.system.log;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.system.log.T_Sms_Log;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;

@RouteBind(path = "/Main/SmsLog", viewPath = "System/Log/SmsLog")
public class SmsLog extends BaseAssociationController {

    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String qname = "";
	String edate = "";
	String sdate = "";
	String select = "select * ";
	String sqlExceptSelect = "from t_sms_log where 1=1 ";
	try {

	    if (StrKit.notBlank(getPara("sdate"))) {
		sdate = getPara("sdate").trim();
		sqlExceptSelect += " and sendtime>='" + sdate + " 00:00:00'";
		setAttr("sdate", sdate);
	    }
	    if (StrKit.notBlank(getPara("edate"))) {
		edate = getPara("edate").trim();
		sqlExceptSelect += " and sendtime<='" + sdate + " 23:59:59'";
		setAttr("edate", edate);
	    }
	    if (null != getPara("qname") && !"".equals(getPara("qname").trim())) {
		qname = getPara("qname");
		sqlExceptSelect += " and users like '%" + qname + "%'";
		setAttr("qname", qname);
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
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	    return;
	}
	sqlExceptSelect += " order by sendtime desc";
	try {
	    Page<T_Sms_Log> page = T_Sms_Log.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    render("main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }
    
}
