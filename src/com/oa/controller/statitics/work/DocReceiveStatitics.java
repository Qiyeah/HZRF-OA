package com.oa.controller.statitics.work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.oa.controller.BaseAssociationController;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.system.workflow.T_Wprocess;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;

/**
 * 收文汇总统计
 * 
 * @author zhilian199
 * 
 */
@RouteBind(path = "Main/DocReceiveStatitics", viewPath = "Statitics/Work/DocReceiveStatitics")
public class DocReceiveStatitics extends BaseAssociationController {
    String menuId = "DocReceiveStatitics";

    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "id";
	String orderDirection = "desc";

	Integer type = null, qdeptid = null, doflag = null;
	String sdate = null, edate = null;
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
	    if (null != getPara("qdeptid") && !"".equals(getPara("qdeptid").trim())) {// 收文的
		qdeptid = getParaToInt("qdeptid");
		if (qdeptid != 0) {
		    sqlExceptSelect += " and dc.d_id=" + qdeptid;

		}
		setAttr("qdeptid", qdeptid);
	    }
	    if (null != getPara("doflag") && !"".equals(getPara("doflag").trim())) {// 收文的
		doflag = getParaToInt("doflag");
		if (doflag != 0) {
		    sqlExceptSelect += " and dc.doflag='" + doflag + "'";
		}
		setAttr("doflag", doflag);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and dc.receivedate>='" + sdate + "'";

		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and dc.receivedate<='" + edate + "'";

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

	    T_Wprocess t_wp = T_Wprocess.dao.getProcessByFlow("docreceive");
	    List<T_Wactive> TWalist = T_Wactive.dao.find(
		    "select a.id, a.name, a.wid, a.atype, a.num from t_wactive a ,t_wprocess p where a.wid=p.id and a.wid="
			    + t_wp.getInt("id") + " order by num,id");
	    List<T_Wactive> list = T_Wactive.dao.find(
		    "select a.id, a.name, a.wid, a.atype, a.num from t_wactive a ,t_wprocess p where a.wid=p.id and a.wid="
			    + t_wp.getInt("id") + " " + sqlExceptSelect1 + " order by num,id");

	    Map<Integer, String> TWmap = new HashMap<Integer, String>();
	    for (T_Wactive tw : TWalist) {
		TWmap.put(tw.getInt("id"), tw.getStr("name"));
	    }
	    List<T_Workflow> TWorkflowList = new ArrayList<T_Workflow>();

	    setAttr("TWalist", TWalist);
	    int ybNumber = 0; // 一般总计
	    int btNumber = 0;// 普通总计
	    int zjNumber = 0;// 最后一行总计 的合计

	    if (null != type || null != doflag || null != qdeptid || StrKit.notBlank(sdate) || StrKit.notBlank(edate)) {

		if (list.size() > 0) {

		    for (T_Wactive model : list) {
			T_Workflow ss = new T_Workflow();
			List<T_Workflow> yb = T_Workflow.dao.find(
				"select  wf.*, dc.doflag  from  t_workflow wf left join t_doc_receive dc  on wf.id = dc.pid WHERE dc.pstatus!=2 and wf.flowform = 'docreceive' and wf.isend='0' and dc.doflag='1' and wf.itemid= "
					+ model.getInt("id") + sqlExceptSelect);
			List<T_Workflow> bt = T_Workflow.dao.find(
				"select  wf.*, dc.doflag  from  t_workflow wf left join t_doc_receive dc  on wf.id = dc.pid WHERE dc.pstatus!=2 and wf.flowform = 'docreceive' and wf.isend='0' and dc.doflag='2' and wf.itemid= "
					+ model.getInt("id") + sqlExceptSelect);
			ss.set("workpath", model.getStr("name"));
			ss.set("flowname", yb.size());
			ss.set("flowform", bt.size());

			ybNumber = yb.size() + ybNumber;
			btNumber = bt.size() + btNumber;
			zjNumber = yb.size() + bt.size() + zjNumber;
			TWorkflowList.add(ss);
		    }
		}
	    }
	    setAttr("ybNumber", ybNumber);
	    setAttr("btNumber", btNumber);
	    setAttr("zjNumber", zjNumber);

	    setAttr("TWorkflowList", TWorkflowList);
	    render("main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("服务器存在错误，数据读取失败！");
	}
    }
}
