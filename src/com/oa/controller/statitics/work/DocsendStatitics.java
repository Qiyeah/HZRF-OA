package com.oa.controller.statitics.work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.kit.StrKit;
import com.oa.controller.BaseAssociationController;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.system.workflow.T_Wprocess;
import com.oa.util.BusinessConstant;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Common;
import com.zhilian.model.T_Common_Detail;
import com.zhilian.model.T_Department;

/**
 * 发文汇总统计
 * 
 * @author zhilian199
 * 
 */
@RouteBind(path = "Main/DocsendStatitics", viewPath = "Statitics/Work/DocsendStatitics")
public class DocsendStatitics extends BaseAssociationController {
    String menuId = "DocsendStatitics";

    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "id";
	String orderDirection = "desc";

	Integer type = null, qdeptid = null;
	String sdate = null, edate = null, doflag = null;
	String sqlExceptSelect = "";
	String sqlExceptSelect1 = "";
	try {
	    if (StrKit.notBlank(getPara("type"))) {
		type = getParaToInt("type");
		if (type != 0) {
		    sqlExceptSelect1 += " and a.id=" + type;
		}
		setAttr("type", type);
	    }
	    if (null != getPara("qdeptid") && !"".equals(getPara("qdeptid").trim())) {
		qdeptid = getParaToInt("qdeptid");
		if (qdeptid != 0) {
		    sqlExceptSelect += " and dc.d_id=" + qdeptid;

		}
		setAttr("qdeptid", qdeptid);
	    }
	    if (null != getPara("doflag") && !"".equals(getPara("doflag").trim())) {
		doflag = getPara("doflag");
		setAttr("doflag", doflag);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and dc.approvedate>='" + sdate + "'";

		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and dc.approvedate<='" + edate + "'";

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
	    LoginModel loginModel = getSessionAttr("loginModel");
	    int areaId = loginModel.getAreaId();
	    List<T_Department> depts = T_Department.getAdminDeptsByArea(areaId);
	    setAttr("depts", depts);

	    T_Wprocess t_wp = T_Wprocess.dao.getProcessByFlow("docsend");
	    List<T_Wactive> TWalist = T_Wactive.dao.find(
		    "select a.id, a.name, a.wid, a.atype, a.num from t_wactive a ,t_wprocess p where a.wid=p.id and a.wid="
			    + t_wp.getInt("id") + " order by num,id");
	    List<T_Wactive> list = T_Wactive.dao.find(
		    "select a.id, a.name, a.wid, a.atype, a.num from t_wactive a ,t_wprocess p where a.wid=p.id and a.wid="
			    + t_wp.getInt("id") + " " + sqlExceptSelect1 + " order by num,id");

	    List<T_Workflow> TWorkflowList = new ArrayList<T_Workflow>();

	    List<String> set = new ArrayList<String>();
	    Map<String, Integer> map1 = new HashMap<String, Integer>();

	    setAttr("docnoList", T_Common_Detail.getListByKey(BusinessConstant.DOCNO));

	    List<T_Common_Detail> tcoList = getListByKey(BusinessConstant.DOCNO, doflag);
	    setAttr("tcoList", tcoList);
	    setAttr("TWalist", TWalist);
	    int zjNumber = 0;// 最后一行总计 的合计;
	    if (null != type || null != doflag || null != qdeptid || StrKit.notBlank(sdate) || StrKit.notBlank(edate)) {
		if (list.size() > 0) {
		    int total[] = new int[tcoList.size()];
		    for (T_Wactive model : list) {
			T_Workflow ss = new T_Workflow();
			ss.set("workpath", model.getStr("name"));

			int ii = 0;
			int Total = 0;
			Integer i[] = new Integer[tcoList.size()];
			for (T_Common_Detail tc : tcoList) {

			    List<T_Workflow> yb = T_Workflow.dao.find(
				    "select  wf.*, dc.docno  from  t_workflow wf left join t_doc_send dc  on wf.id = dc.pid WHERE dc.pstatus!=2 and wf.flowform = 'docsend' and wf.isend='0' and dc.docno like '%"
					    + tc.getStr("name") + "%' and wf.itemid= " + model.getInt("id")
					    + sqlExceptSelect);
			    i[ii] = yb.size();
			    total[ii] += i[ii];
			    Total = Total + yb.size();
			    if (!set.contains(tc.get("name"))) {
				set.add(tc.getStr("name"));
			    }
			    ii++;
			}
			// total[tcoList.size()] += Total;
			// i[tcoList.size()]=Total;
			map1.put(model.getStr("name"), Total);
			ss.set("formname", i);
			TWorkflowList.add(ss);
		    }
		    setAttr("totalArray", total);
		}
	    }
	    for (Entry<String, Integer> nn : map1.entrySet()) {
		zjNumber = zjNumber + nn.getValue();
	    }
	    setAttr("zjNumber", zjNumber);
	    setAttr("map1", map1);
	    setAttr("set", set);
	    setAttr("TWorkflowList", TWorkflowList);

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
	return T_Common_Detail.dao.find(sql);
    }
}
