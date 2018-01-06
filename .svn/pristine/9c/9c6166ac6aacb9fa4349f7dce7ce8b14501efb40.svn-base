package com.oa.controller.statitics.work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.oa.controller.BaseAssociationController;
import com.oa.model.document.T_Doc_Receive;
import com.oa.model.document.T_Doc_Send;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Common;
import com.zhilian.model.T_Common_Detail;
import com.zhilian.model.T_Department;

/**
 * 收发文档案汇总统计
 * 
 * @author zhilian199
 * 
 */
@RouteBind(path = "Main/DispatchStatitics", viewPath = "Statitics/Work/DispatchStatitics")
public class DispatchStatitics extends BaseAssociationController {
    String menuId = "DispatchStatitics";

    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "id";
	String orderDirection = "desc";

	Integer type = null, qdeptid = null, nqdeptid = null;
	String ltype = null;
	String sdate = null, edate = null;

	String select = "select * ";
	String sqlExceptSelect = "from t_doc_receive where 1=1 ";
	String sqlExceptSelect1 = "from t_doc_send where 1=1 ";
	String sql = "select * from t_department where area=1 ORDER BY no";
	try {

	    if (StrKit.notBlank(getPara("type"))) {
		type = getParaToInt("type");
		setAttr("type", type);
	    }

	    if (null != getPara("ltype") && !"".equals(getPara("ltype").trim())) {
		ltype = getPara("ltype");
		if (!ltype.equals("0")) {
		    sqlExceptSelect += " and type= '" + ltype + "'";
		}
		setAttr("ltype", ltype);
	    }

	    if (null != getPara("qdeptid") && !"".equals(getPara("qdeptid").trim())) {// 收文的
		qdeptid = getParaToInt("qdeptid");
		if (qdeptid != 0) {
		    sqlExceptSelect += " and d_id=" + qdeptid;
		    sql += " and id=" + qdeptid;
		}
		setAttr("qdeptid", qdeptid);
	    }

	    if (null != getPara("nqdeptid") && !"".equals(getPara("nqdeptid").trim())) {// 发文的
		nqdeptid = getParaToInt("nqdeptid");
		if (nqdeptid != 0) {
		    sqlExceptSelect1 += " and d_id=" + nqdeptid;
		    sql += " and id=" + nqdeptid;
		}
		setAttr("nqdeptid", nqdeptid);
	    }

	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and receivedate>='" + sdate + "'";
		sqlExceptSelect1 += " and approvedate>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and receivedate<='" + edate + "'";
		sqlExceptSelect1 += " and approvedate<='" + edate + "'";
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
	// sqlExceptSelect += " order by " + orderField + " " + orderDirection; itemid
	try {
	    LoginModel loginModel = getSessionAttr("loginModel");
	    int areaId = loginModel.getAreaId();

	    List<T_Department> depts = T_Department.getAdminDeptsByArea(areaId);

	    setAttr("depts", depts);

	    HashMap<Integer, String> deptHm = T_Department.dao.hashMapById("sname");
	    setAttr("deptHM", deptHm);

	    Map<String, Integer> swmap = new HashMap<String, Integer>();
	    Map<String, Integer> fwmap = new HashMap<String, Integer>();
	    List<T_Department> SWlist = new ArrayList<T_Department>();
	    List<T_Department> FWlist = new ArrayList<T_Department>();

	    sqlExceptSelect += " and pstatus='2' ";
	    sqlExceptSelect1 += " and pstatus='2' ";

	    if (null != type || StrKit.notBlank(sdate) || StrKit.notBlank(edate)) {
		if (null == type) {
		    toErrorJson("请选择收发文类型!");
		    // render("error.jsp");
		    return;
		}
		List<T_Department> listdepts = T_Department.dao.find(sql);
		int swNumber = 0;
		int fwNumber = 0;
		if (type == 0 || type == 2 || type == 1) {

		    if (listdepts.size() > 0) {

			// 收文
			for (T_Department lis : listdepts) {
			    List<T_Doc_Receive> list = T_Doc_Receive.dao
				    .getType(select + sqlExceptSelect + " and receive1 ='" + lis.getInt("id") + "'");
			    T_Department SWmodel = new T_Department();
			    SWmodel.set("fname", lis.getStr("sname"));
			    SWmodel.set("pid", list.size());
			    SWlist.add(SWmodel);
			    swmap.put(lis.getStr("sname"), list.size());
			    swNumber = swNumber + list.size();
			}
			setAttr("swmap", swmap);
			setAttr("swNumber", swNumber);

			// 发文
			for (T_Department lis : listdepts) {

			    List<T_Doc_Send> list = T_Doc_Send.dao
				    .getType(select + sqlExceptSelect1 + " and d_id =" + lis.getInt("id"));
			    T_Department FWmodel = new T_Department();
			    FWmodel.set("fname", lis.getStr("sname"));
			    FWmodel.set("pid", list.size());
			    FWlist.add(FWmodel);

			    fwmap.put(lis.getStr("sname"), list.size());
			    fwNumber = fwNumber + list.size();
			}
			setAttr("fwmap", fwmap);
			setAttr("fwNumber", fwNumber);

		    }
		}
	    }
	    setAttr("SWlist", SWlist);
	    setAttr("FWlist", FWlist);
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
