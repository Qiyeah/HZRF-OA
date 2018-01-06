package com.oa.controller.common;

import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.kit.StrKit;
import com.oa.controller.BaseAssociationController;
import com.oa.model.approve.T_Unit;
import com.oa.model.document.T_Doc_Achieve;
import com.oa.model.document.T_Doc_Send;
import com.zhilian.annotation.RouteBind;
import com.zhilian.model.T_Common_Detail;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_Icon;
import com.zhilian.model.T_User;

@Clear
@RouteBind(path = "Main/search", viewPath = "Common/Search")
public class Search extends BaseAssociationController {

    /** 输入框文字选择(操作t_common和t_common_detail的输入框) */
    @Clear
    public void searchValue() {
	String key = getPara();
	StringBuffer sb = new StringBuffer();
	List<T_Common_Detail> details = T_Common_Detail.dao.find(
		"select t2.* from t_common t1 left join t_common_detail t2 on t1.id = t2.cid where t1.key=?", key);
	if (details.size() > 0) {
	    for (T_Common_Detail detail : details) {
		sb.append(",{\"" + key + "\":\"" + detail.getStr("name") + "\"}");
	    }
	    sb.deleteCharAt(0);
	}
	setAttr("data", sb.toString());
	render("find_name.jsp");
    }

    /** 查找单位 */
    @Clear
    public void searchDepartment() {
	StringBuffer sb = new StringBuffer();
	List<T_Department> departments = null;
	if (!isParaBlank("inputValue")) {
	    departments = T_Department.dao
		    .list("where d_pid is not null and fname like '%" + getPara("inputValue") + "%' order by fname");
	} else {
	    departments = T_Department.dao.list("where d_pid is not null order by fname");
	}
	if (departments.size() > 0) {
	    for (T_Department department : departments) {
		sb.append(",{\"department\":\"" + department.getStr("fname") + "\"}");
	    }
	    sb.deleteCharAt(0);
	}
	setAttr("data", sb.toString());
	render("find_name.jsp");
    }

    /** 查找单位 */
    @Clear
    public void searchDept() {
	StringBuffer sb = new StringBuffer();
	List<T_Department> departments = null;
	if (!isParaBlank("inputValue")) {
	    departments = T_Department.dao
		    .list("where pid is not null and fname like '%" + getPara("inputValue") + "%' order by fname");
	} else {
	    departments = T_Department.dao.list("where pid is not null order by fname");
	}
	if (departments.size() > 0) {
	    for (T_Department department : departments) {
		sb.append(",{\"id\":\"" + department.getInt("id") + "\",\"department\":\"" + department.getStr("fname")
			+ "\"}");
	    }
	    sb.deleteCharAt(0);
	}
	setAttr("data", sb.toString());
	render("find_name.jsp");
    }

    /** 查找部门s */
    @Clear
    public void searchDepts() {
	List<T_Department> checkDepts = null;
	String dids = getPara();
	if (dids != null && !"".equals(dids)) {
	    /*
	     * checkDepts = T_Department.dao.list("where id in(" + dids + ")");
	     */
	}
	setAttr("checkDepts", checkDepts);
	setAttr("deparments", T_Department.getAllBaseDepts());
	render("find_depts.jsp");
    }

    /** 查找部门s */
    @Clear
    public void searchDepts1() {
	List<T_Department> checkDepts = null;
	String dids = getPara();
	if (dids != null && !"".equals(dids)) {
	    checkDepts = T_Department.dao.list("where id in(" + dids + ")");
	}
	setAttr("checkDepts", checkDepts);
	setAttr("deparments", T_Department.getAllBaseDepts());
	render("find_depts1.jsp");
    }

    /** 查找人员s */
    @Clear
    public void searchUsers() {
	String uids = getPara();
	List<T_User> checkUsers = null;
	if (uids != null && !"".equals(uids)) {
	    checkUsers = T_User.dao.list("where id in(" + uids + ") order by name");
	}
	setAttr("checkUsers", checkUsers);
	setAttr("deparments", T_Department.getAllDepts());
	setAttr("users", T_User.getList());
	render("find_ids_user.jsp");
    }

    /** 查找人员s */
    @Clear
    public void searchUsers1() {
	String uids = getPara();
	List<T_User> checkUsers = null;
	if (uids != null && !"".equals(uids)) {
	    checkUsers = T_User.dao.list("where id in(" + uids + ") order by name");
	}
	setAttr("checkUsers", checkUsers);
	setAttr("deparments", T_Department.getAllDepts());
	setAttr("users", T_User.getList());
	render("find_ids_user1.jsp");
    }

    /** 模糊获取用户 */
    @Clear
    public void searchUser() {
	String userName = getPara("inputValue");
	StringBuffer sql = new StringBuffer("select * from t_user where status =1");
	if (userName != null && !"".equals(userName)) {
	    sql.append(" and name like '%" + userName + "%'");
	}
	List<T_User> users = T_User.dao.find(sql.toString());
	setAttr("users", users);
	render("find_user.jsp");
    }

    @Clear
    public void searchIcon() {
	List<T_Icon> icons = T_Icon.dao.find("select * from t_icon");
	setAttr("icons", icons);
	render("find_icon.jsp");
    }

    /**
     * 黄美基添加 2016-7-25 查询单位
     */
    @Clear
    public void unit() {
	if (!isParaBlank("inputValue")) {
	    List<T_Unit> getUnit = T_Unit.dao.getUnit(getPara("inputValue"));
	    setAttr("list", getUnit);
	} else {
	    List<T_Unit> getUnit = T_Unit.dao
		    .find("select * from t_unit where 1=1 and dbstate='0' and dlvl =2 order by id");
	    setAttr("list", getUnit);
	}

	render("find_unit.jsp");
    }

    /** 查询来文单位 */
    @Clear
    public void achieveDepart() {
	String units = "";
	String sql = "";
	String inputValue = getPara("inputValue");
	if (StrKit.notBlank(inputValue)) {
	    inputValue = inputValue.replaceAll("'", "");
	}
	// if (!isParaBlank("inputValue")) {
	if (StrKit.notBlank(inputValue)) {
	    sql = "select unit from t_doc_achieve where unit like '%" + inputValue + "%' group by unit order by unit";
	} else {
	    sql = "select unit from t_doc_achieve where unit is not NULL group by unit order by unit";
	}
	List<T_Doc_Achieve> achieveDepart = T_Doc_Achieve.dao.find(sql);
	if (achieveDepart != null && achieveDepart.size() > 0) {
	    for (int i = 0; i < achieveDepart.size(); i++) {
		units += ",{\"unit\":\"" + achieveDepart.get(i).getStr("unit") + "\"}";
	    }
	    units = units.substring(1);
	}
	setAttr("data", units);
	render("find_name.jsp");
    }

    /** 查询出国境证件 *//*
		   * @Clear public void searchEECert() { T_Entryexit_Cert
		   * eecerts = null; if (!isParaBlank("catchValue")) { eecerts =
		   * T_Entryexit_Cert.dao.findById(getPara("catchValue")); }
		   * 
		   * for(T_Entryexit_Cert eecert : eecerts){
		   * if("1".equals(eecert.get("type"))){
		   * eecert.set("type","护照"); } else if
		   * ("2".equals(eecert.get("type"))) {
		   * eecert.set("type","港澳通行证"); } else if
		   * ("3".equals(eecert.get("type"))) {
		   * eecert.set("type","台湾通行证"); } }
		   * 
		   * List<T_Entryexit_Cert> list = new ArrayList<>();
		   * T_Entryexit_Cert certHK = new T_Entryexit_Cert();
		   * certHK.set("type", Constant.HKMC); certHK.set("name",
		   * Constant.HKMC_REMARK); T_Entryexit_Cert certPass = new
		   * T_Entryexit_Cert(); certPass.set("type",
		   * Constant.PASSPORT); certPass.set("name",
		   * Constant.PASSPORT_REMARK); if (eecerts != null) { if
		   * (!"".equals(eecerts.getStr("signdate"))) {
		   * certHK.set("code", eecerts.getStr("code"));
		   * certHK.set("id", eecerts.getInt("id"));
		   * certPass.set("memo", eecerts.getStr("memo")); } if
		   * (!"".equals(eecerts.getStr("signdate2"))) {
		   * certPass.set("code", eecerts.getStr("code2"));
		   * certPass.set("id", eecerts.getInt("id"));
		   * certPass.set("memo", eecerts.getStr("memo2")); }
		   * 
		   * if (!"".equals(eecerts.getStr("signdate3"))) {
		   * T_Entryexit_Cert certTW = new T_Entryexit_Cert();
		   * certTW.set("type", Constant.TAIWAN); certTW.set("name",
		   * Constant.TAIWAN_REMARK); certTW.set("code",
		   * eecerts.getStr("code3")); certTW.set("id",
		   * eecerts.getInt("id")); list.add(certTW); }
		   * 
		   * } list.add(certHK); list.add(certPass); setAttr("datas",
		   * list); render("find_eecert.jsp"); }
		   */

    /** 查询主送单位 */
    @Clear
    public void send1() {
	String units = "";
	String sql = "";
	if (!isParaBlank("inputValue")) {
	    sql = "select send1 from t_doc_send where send1 like '%" + getPara("inputValue")
		    + "%' group by send1 order by send1";
	} else {
	    sql = "select send1 from t_doc_send where send1 is not NULL group by send1 order by send1";
	}
	List<T_Doc_Send> achieveDepart = T_Doc_Send.dao.find(sql);
	if (achieveDepart != null && achieveDepart.size() > 0) {
	    for (int i = 0; i < achieveDepart.size(); i++) {
		units += ",{\"send1\":\"" + achieveDepart.get(i).getStr("send1") + "\"}";
	    }
	    units = units.substring(1);
	}
	setAttr("data", units);
	render("find_name.jsp");
    }

    /** 查询主送单位 */
    @Clear
    public void send2() {
	String units = "";
	String sql = "";
	if (!isParaBlank("inputValue")) {
	    sql = "select send2 from t_doc_send where send2 like '%" + getPara("inputValue")
		    + "%' group by send2 order by send2";
	} else {
	    sql = "select send2 from t_doc_send where send2 is not NULL group by send2 order by send2";
	}
	List<T_Doc_Send> achieveDepart = T_Doc_Send.dao.find(sql);
	if (achieveDepart != null && achieveDepart.size() > 0) {
	    for (int i = 0; i < achieveDepart.size(); i++) {
		units += ",{\"send2\":\"" + achieveDepart.get(i).getStr("send2") + "\"}";
	    }
	    units = units.substring(1);
	}
	setAttr("data", units);
	render("find_name.jsp");
    }

    /** 查询综合科所有人员 作为审核人选项 **/
    @Clear
    public void auditor() {
	String auditors = "";
	String sql = "";
	if (!isParaBlank("inputValue")) {
	    sql = "select * from t_user where d_id=2 and name like '%" + getPara("inputValue") + "%' order by no ";
	} else {
	    sql = "select * from t_user where d_id=2 order by no ";
	}
	List<T_User> users = T_User.dao.find(sql);
	if (users != null && users.size() > 0) {
	    for (int i = 0; i < users.size(); i++) {
		auditors += ",{\"auditor\":\"" + users.get(i).getStr("name") + "\"}";
	    }
	    auditors = auditors.substring(1);
	}
	setAttr("data", auditors);
	render("find_name.jsp");
    }
}
