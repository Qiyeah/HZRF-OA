package com.oa.controller.statitics.work;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.oa.controller.BaseAssociationController;
import com.oa.model.work.T_Announce;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;

/**
 * 公告通知汇总统计
 * 
 * @author zhilian199
 * 
 */
@RouteBind(path = "Main/AnnounceStatitics", viewPath = "Statitics/Work/AnnounceStatitics")
public class AnnounceStatitics extends BaseAssociationController {
    String menuId = "AnnounceStatitics";

    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "id";
	String orderDirection = "desc";

	String sdate = null, edate = null;
	Integer atype = null;
	String select = "select * ";
	String sqlExceptSelect = "from t_announce  where 1=1 ";

	try {
	    if (null != getPara("atype") && !"".equals(getPara("atype").trim())) {
		atype = getParaToInt("atype");
		if (atype != 99) {
		    sqlExceptSelect += " and atype =" + atype + "";
		}
		setAttr("atype", atype);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and sendtime>='" + sdate + "'";

		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and sendtime<='" + edate + "'";

		setAttr("edate", edate);
	    }
	    if (null != getPara("orderField") && !"".equals(getPara("orderField").trim())) {
		orderField = getPara("orderField");
		setAttr("orderField", orderField);
	    }
	    if (null != getPara("orderDirection") && !"".equals(getPara("orderDirection").trim())) {
		orderDirection = getPara("orderDirection");
		setAttr("orderDirection", orderDirection);
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
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	// sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    int swNumber = 0;
	    int fwNumber = 0;
	    if (StrKit.notBlank(getPara(atype)) || StrKit.notBlank(sdate) || StrKit.notBlank(edate)) {
		List<T_Announce> tlist = T_Announce.dao.find(select + sqlExceptSelect);
		for (T_Announce ti : tlist) {
		    if (ti.getInt("atype") == 0) {
			swNumber = swNumber + 1;
		    } else if (ti.getInt("atype") == 1) {
			fwNumber = fwNumber + 1;
		    }
		}
	    }
	    setAttr("swNumber", swNumber);
	    setAttr("fwNumber", fwNumber);

	    render("main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("服务器存在错误，数据读取失败！");
	}
    }

}
