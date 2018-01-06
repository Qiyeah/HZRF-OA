package com.oa.controller.statitics.work;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.oa.controller.BaseAssociationController;
import com.oa.model.document.T_Doc_Achieve;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Common;
import com.zhilian.model.T_Common_Detail;

/**
 * 收件登记汇总统计
 * 
 * @author zhilian199
 * 
 */
@RouteBind(path = "Main/TherecipientStatitics", viewPath = "Statitics/Work/TherecipientStatitics")
public class TherecipientStatitics extends BaseAssociationController {
    String menuId = "TherecipientStatitics";

    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "id";
	String orderDirection = "desc";

	String sdate = null, edate = null, ltype = null, status = null;
	String select = "select * ";
	String sqlExceptSelect = "from t_doc_achieve  where 1=1 ";
	String sqlExceptSelect1 = "";
	try {
	    if (null != getPara("status") && !"".equals(getPara("status").trim())) {
		status = getPara("status");
		if (!status.equals("99")) {
		    sqlExceptSelect1 += " and status ='" + status + "'";
		}
		setAttr("status", status);
	    }

	    if (null != getPara("ltype") && !"".equals(getPara("ltype").trim())) {
		ltype = getPara("ltype");
		if (!ltype.equals("0")) {
		    sqlExceptSelect += " and type= '" + ltype + "'";
		}
		setAttr("ltype", ltype);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and receivedate>='" + sdate + "'";

		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and receivedate<='" + edate + "'";

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

	    List<T_Common_Detail> TClist = new ArrayList<T_Common_Detail>();
	    int zwfNumber = 0; // 未分发总计
	    int zyfNumber = 0; // 已分发总计
	    int zzyfNumber = 0; // 总计 的 合计

	    if (null != status || null != ltype || StrKit.notBlank(sdate) || StrKit.notBlank(edate)) {
		List<T_Common_Detail> tlist = getListByKey("sourcetype", ltype);

		for (T_Common_Detail ti : tlist) {
		    T_Common_Detail TCmodel = new T_Common_Detail();
		    int wfNumber = 0;
		    int yfNumber = 0;

		    List<T_Doc_Achieve> list = T_Doc_Achieve.dao.find(
			    select + sqlExceptSelect + sqlExceptSelect1 + " and type='" + ti.getStr("name") + "'");
		    if (list.size() > 0) {
			for (T_Doc_Achieve model : list) {
			    if (model.getStr("status").equals("0")) { // 未分发数量
				wfNumber = wfNumber + 1;
				zwfNumber = zwfNumber + 1;
			    } else if (model.getStr("status").equals("1")) { // 已分发数量
				yfNumber = yfNumber + 1;
				zyfNumber = zyfNumber + 1;
			    }
			}
		    }
		    zzyfNumber = zzyfNumber + wfNumber + yfNumber;// 算最后一行总计 的 合计

		    TCmodel.set("name", ti.getStr("name"));
		    TCmodel.set("cid", wfNumber);
		    TCmodel.set("value", yfNumber);
		    TClist.add(TCmodel);

		}
	    }
	    setAttr("zwfNumber", zwfNumber);
	    setAttr("zyfNumber", zyfNumber);
	    setAttr("zzyfNumber", zzyfNumber);

	    setAttr("TClist", TClist);
	    setAttr("details", T_Common_Detail.getListByKey("sourcetype"));
	    render("main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("服务器存在错误，数据读取失败！");
	}
    }

    /** 根据T_Common的key获取 */
    public List<T_Common_Detail> getListByKey(String key, String name) {
	String sql1 = "";
	if (StrKit.notBlank(name) && !name.equals("0")) {
	    sql1 += " and name='" + name + "'";
	}
	String typesql = "select * from t_common t where t.fieldname = '" + key + "'";
	String sql = "select * from t_common_detail where cid = " + T_Common.dao.findFirst(typesql).getInt("id") + " "
		+ sql1 + "  order by id";
	System.out.println(sql);
	return T_Common_Detail.dao.find(sql);
    }
}
