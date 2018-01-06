package com.oa.controller.statitics.personal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.controller.export.ExportPersonalTel;
import com.oa.model.approve.T_Personal;
import com.oa.util.ExportExcel;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;

@RouteBind(path = "Main/PersonalLoginStatitics", viewPath = "Statitics/Personal")
public class PersonalLoginStatitics extends BaseAssociationController {

    public void main() {
	String qname = "";
	int qdeptid = 0;
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "dlcount";
	String orderDirection = "desc";
	String select = "select *, webcount+appcount as dlcount ";
	String sqlExceptSelect = "from t_user where id>1 ";
	try {
	    if (null != getPara("qname") && !"".equals(getPara("qname").trim())) {
		qname = getPara("qname").trim();
		sqlExceptSelect += " and name like '%" + qname + "%'";
		setAttr("qname", qname);
	    }
	    if (null != getPara("qdeptid") && !"".equals(getPara("qdeptid").trim())) {
		qdeptid = getParaToInt("qdeptid");
		sqlExceptSelect += " and d_id=" + qdeptid;
		setAttr("qdeptid", qdeptid);
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
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    Page<T_User> page = T_User.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    LoginModel loginModel = getSessionAttr("loginModel");
	    int areaId = loginModel.getAreaId();
	    List<T_Department> depts = T_Department.getAdminDeptsByArea(areaId);
	    setAttr("depts", depts);
	    HashMap<Integer, String> deptHm = T_Department.dao.hashMapById("sname");
	    setAttr("deptHM", deptHm);
	    render("loginstatitics.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("服务器存在错误，数据读取失败！");
	}
    }

    @SuppressWarnings("unchecked")
    public void exportTel() {
	String uname = "";
	int d_id = 0;
	String select = "select p.*, u.name as uname, u.d_id as d_id ";
	String sqlExceptSelect = "from t_personal p left join t_user u on p.u_id=u.id where 1=1 ";
	try {
	    if (null != getPara("uname") && !"".equals(getPara("uname").trim())) {
		uname = getPara("uname").trim();
		sqlExceptSelect += " and u.name like '%" + uname + "%'";
		setAttr("uname", uname);
	    }
	    if (null != getPara("d_id") && !"".equals(getPara("d_id").trim())) {
		d_id = getParaToInt("d_id");
		sqlExceptSelect += " and u.d_id=" + d_id;
		setAttr("d_id", d_id + "");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	sqlExceptSelect += " order by p.id";
	List<T_Personal> list = T_Personal.dao.find(select + sqlExceptSelect);

	HttpServletResponse response = getResponse();
	response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
	response.setContentType("application/octet-stream");
	try {
	    OutputStream os = response.getOutputStream();
	    String title = "通讯录";
	    String[] headers = new String[] { "姓名", "内部电话", "手机", "所属科室", "职务名称", "职务级别" };
	    ExportExcel<T_Personal> ex = new ExportPersonalTel();
	    ex.exportExcel(title, headers, list, os, null);
	    renderNull();
	    os.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
