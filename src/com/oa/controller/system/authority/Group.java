package com.oa.controller.system.authority;

import java.util.HashMap;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.approve.T_Group;
import com.oa.model.approve.T_Personal;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;

@RouteBind(path = "Main/Group", viewPath = "System/Authority/Group")
public class Group extends BaseAssociationController {

    String menuId = "group";
    public void main(){
	render("main.jsp");
    }
    
    @ActionKey("/Main/Group/main/tree")
    public void tree() {
	List<T_Department> depts = T_Department.getOrderDept();
	List<T_Group> group=T_Group.dao.findByUserId(0);
	setAttr("group", group);
	setAttr("depts", depts);
	render("tree.jsp");
	render("tree.jsp");
    }
    
    @ActionKey("/Main/Group/main/list")
    public void list() {
	Integer id=getParaToInt(0);
	
	String uname = getPara("uname");
	int pageSize = 20;
	int pageNumber = 1;
	String orderField = "u.d_id,u.no";
	String orderDirection = "";
	T_Group group = null;
	if(id>0){
	    group=T_Group.dao.findById(id);
	}else{
	    group = T_Group.dao.findById(5);
	}
	String select ="SELECT p.*,u.name as uname, u.d_id as d_id";
	String sqlExceptSelect = "from t_user u, t_personal p   where  p.u_id=u.id and u.id in ("+group.getStr("u_ids")+") ";	
	try {
	    if (StrKit.notBlank(uname)) {
		sqlExceptSelect += " and u.name like '%" + uname + "%'";
		setAttr("uname", uname);
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
		System.out.println(pageSize);
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
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    HashMap<Integer, String> deptHm = T_Department.dao.hashMapById("sname");
	    setAttr("deptHM", deptHm);
	    setAttr("groupid",group.get("id"));
	    Page<T_Personal> page = T_Personal.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	    setAttr("page", page);
	    render("list.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
	
    }
    
    @Clear
    public void creategroupip(){
	List<T_Department> deparmentList = T_Department.getAllDepts();
	List<T_User> userList = T_User.dao.find("select * from t_user where status=1 order by d_id");
	setAttr("deparmentList", deparmentList);
	setAttr("userList", userList);
	setAttr("u_id", 0);
	render("creategroup.jsp");
    }
    
    @Clear
    public void creategroup(){//保存新增群组信息
	String[] u_ids=getParaValues("u_ids");
	String[] u_names=getParaValues("u_names");
	String name=getPara("t_Group.name");
	if(u_ids==null){
	    toErrorJson("请选择群成员！");
	    return ;
	}
	String uids ="",unames = "";
	for(int i=0;i<u_ids.length;i++){
	    uids+=","+u_ids[i];
	    unames+=","+u_names[i];
	}
	int u_id = getParaToInt("u_id");
	uids=uids.substring(1, uids.length());
	unames=unames.substring(1, unames.length());
	T_Group group=new T_Group();
	group.set("u_id", u_id);
	group.set("u_ids", uids);
	group.set("u_names", unames);
	group.set("name", name);
	if(group.save()){
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	}else{
	    toErrorJson(Constant.EXCEPTION);
	}
	
    }
    
    @Clear
    public void editgroupip(){
	Integer id=getParaToInt(0);
	T_Group group = T_Group.dao.findById(id);
	List<T_User> checkUsers = T_User.dao.find("select * from t_user where status=1 and id in ("+group.getStr("u_ids")+") order by d_id");
	List<T_Department> deparmentList = T_Department.getAllDepts();
	List<T_User> userList = T_User.dao.find("select * from t_user where status=1 order by d_id");
	setAttr("deparmentList", deparmentList);
	setAttr("userList", userList);
	setAttr("group", group);
	setAttr("checkUsers", checkUsers);
	render("editgroup.jsp");
    }
    
    @Clear
    public void editgroup(){
	String[] u_ids=getParaValues("u_ids");
	String[] u_names=getParaValues("u_names");
	
	if(u_ids==null){
	    toErrorJson("请选择群成员！");
	    return ;
	}
	String uids ="",unames = "";
	for(int i=0;i<u_ids.length;i++){
	    if(StrKit.notBlank(u_ids[i])){
    	        uids+=","+u_ids[i];
    	        unames+=","+u_names[i];
	    }
	}
	T_Group group=getModel(T_Group.class);
	if(uids.length()>1){
	    uids=uids.substring(1, uids.length());
	    unames=unames.substring(1, unames.length());
	    group.set("u_ids", uids);
	    group.set("u_names", unames);
	}
	if(group.update()){
	    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	}else{
	    toErrorJson(Constant.EXCEPTION);
	}
    }
    
    @Clear
    public void deletegroup(){//删除群组
	Integer id=getParaToInt(0);
	T_Group group = T_Group.dao.findById(id);
	try {
	    if (group.delete()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
	
	
    }
}
