package com.oa.controller.system.workflow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.base.controller.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.oa.model.system.office.T_Template_File;
import com.oa.model.system.workflow.T_Myopinion;
import com.oa.model.system.workflow.T_Template;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Wfield;
import com.oa.model.system.workflow.T_Woperation;
import com.oa.model.system.workflow.T_Workitem;
import com.oa.model.system.workflow.T_Wprocess;
import com.oa.model.system.workflow.T_Wtrans;
import com.oa.model.system.workflow.T_Wtrans_Sp;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Common_Detail;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_Position;
import com.zhilian.model.T_User;
import com.zhilian.util.StringUtil;

@Clear
@RouteBind(path = "/Main/Workflow", viewPath = "System/Workflow")
public class Workflow extends BaseController {

    private String menuId = "Workflow";

    public void main() {
	List<T_Wprocess> wplist = T_Wprocess.dao.find("select id, name from t_wprocess order by id");
	setAttr("wplist", wplist);
	List<T_Wactive> walist = T_Wactive.dao.find(
		"select a.id, a.name, a.wid, a.atype, a.num from t_wactive a ,t_wprocess p where a.wid=p.id order by num,id");
	setAttr("walist", walist);
	render("list.jsp");
    }

    /* 流程配置 */
    public void processaddip() {
	List<T_Template> templates = T_Template.dao.find("select * from t_template where status='1' order by name");
	setAttr("templates", templates);
	render("process_add.jsp");
    }

    public void processadd() {
	try {
	    T_Wprocess model = getModel(T_Wprocess.class);
	    model.set("managers", getPara("admin.userId"));
	    model.set("allowscope", getPara("scope.deptId"));
	    model.set("allowstation", getPara("station.pId"));
	    model.set("forbiddenuser", getPara("forbid.userId"));
	    if (model.save()) {
		toBJUIJson(200, "流程信息添加成功", menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	}
    }

    public void processupdateip() {
	T_Wprocess wp = T_Wprocess.dao.findById(getParaToInt(0));
	setAttr("wp", wp);
	// List<T_Template> templates = T_Template.dao.find("select * from
	// t_template where status='1' order by name");
	List<T_Template_File> templates = T_Template_File.dao
		.find("select * from t_template_file where 1=1 order by filename");
	setAttr("templates", templates);
	String mgids = wp.getStr("managers");
	if (mgids != null && !"".equals(mgids)) {
	    String mgNames = T_User.findUsernames(mgids);
	    setAttr("mgNames", mgNames);
	}
	String scopes = wp.getStr("managers");
	if (scopes != null && !"".equals(scopes)) {
	    String scopeNames = T_Department.findDeptnames(scopes);
	    setAttr("scopeNames", scopeNames);
	}
	String stations = wp.getStr("allowstation");
	if (stations != null && !"".equals(stations)) {
	    String stationNames = T_Position.findPositionNames(stations);
	    setAttr("stationNames", stationNames);
	}
	String forbids = wp.getStr("forbiddenuser");
	if (forbids != null && !"".equals(forbids)) {
	    String forbidNames = T_User.findUsernames(forbids);
	    setAttr("forbidNames", forbidNames);
	}
	render("process_update.jsp");
    }

    public void processupdate() {
	try {
	    T_Wprocess model = getModel(T_Wprocess.class);
	    model.set("managers", getPara("admin.userId"));
	    model.set("allowscope", getPara("scope.deptId"));
	    model.set("allowstation", getPara("station.pId"));
	    model.set("forbiddenuser", getPara("forbid.userId"));
	    if (model.update()) {
		toBJUIJson(200, "流程信息添加成功", menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	}

    }

    public void processdel() {
	try {
	    int id = Integer.parseInt(getPara(0));
	    T_Wprocess wp = T_Wprocess.dao.findById(id);
	    if (wp == null) {
		toErrorJson("数据处理存在错误，此流程不存在！");
	    }
	    if (wp.deleteProcess()) {
		toBJUIJson(200, "流程信息添加成功", menuId, "", "", "", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	}
    }

    /* 环节配置 */
    public void activeaddip() {
	int wid = getParaToInt(0);
	setAttr("wid", wid);
	List<T_Wactive> actives = T_Wactive.dao.findByProcessId(wid);
	setAttr("actives", actives);
	List<T_Wfield> conds = T_Wfield.dao.getAllCondfieldByWid(wid);
	setAttr("conds", conds);
	List<T_Wfield> fields = T_Wfield.dao.getAllOpinionfieldByWid(wid);
	setAttr("fields", fields);
	int typeflag = T_Wactive.dao.getAtype(wid);
	setAttr("tf", typeflag);
	List<T_Wprocess> process = T_Wprocess.dao.getAllSubProcess();
	setAttr("process", process);
	List<T_User> users = T_User.getList();
	setAttr("users", users);
	List<T_Department> depts = T_Department.getList();
	setAttr("depts", depts);
	List<T_Position> positions = T_Position.getList();
	setAttr("positions", positions);
	render("active_add.jsp");
    }

    public void activeadd() {
	boolean success;
	try {
	    success = Db.tx(new IAtom() {
		public boolean run() throws SQLException {
		    T_Wactive model = getModel(T_Wactive.class);
		    if ("0".equals(model.getStr("havesubflow"))) {
			model.set("subflow", null);
			model.set("ishold", null);
		    }
		    if ("0".equals(model.getStr("haveopinionfield"))) {
			model.set("opinionfield", null);
		    }
		    if ("1".equals(model.getStr("participant"))) {
			model.set("usergroup", getPara("param.userId"));
		    }
		    if ("1".equals(model.getStr("amount"))) {
			model.set("issequence", null);
		    }
		    if ("0".equals(model.getStr("havecondfield"))) {
			model.set("condfield", null);
		    }
		    String ptype = model.getStr("ptype");
		    if ("1".equals(ptype)) { // 执行人配置 1.指定部门
			model.set("user1", getPara("dept"));
		    } else if ("2".equals(ptype)) { // 2.指定职务
			String dept = getPara("dept");
			String[] position = getParaValues("position");
			model.set("user1", dept + "#" + StringUtil.combine(position));
		    } else if ("3".equals(ptype)) { // 3.指定用户
			model.set("user1", getPara("param.userId"));
		    } else {
			model.set("user1", "");
		    }
		    model.set("default1", getPara("default.userId"));
		    boolean saveflag = model.save();
		    if (saveflag) {
			int aid = model.getInt("id");
			if ("1".equals(model.get("num").toString())) {
			    T_Wprocess wp = T_Wprocess.dao.findById(model.getInt("wid"));
			    wp.set("beginstep", aid);
			    wp.update();
			}
			if ("0".equals(model.getStr("havecondfield"))) {
			    String[] nas = getParaValues("nextactives");
			    if (nas != null) {
				for (int i = 0; i < nas.length; i++) {
				    T_Wtrans trans = new T_Wtrans();
				    trans.set("wid", getPara("wid"));
				    trans.set("afrom", aid);
				    trans.set("ato", nas[i]);
				    saveflag = saveflag && trans.save();
				}
			    }
			} else {
			    for (int i = 1; i <= 3; i++) {
				T_Wtrans trans = new T_Wtrans();
				trans.set("condition1", getPara("condition" + i));
				trans.set("wid", getPara(""));
				trans.set("afrom", aid);
				trans.set("ato", getPara("ato" + i));
				saveflag = saveflag && trans.save();
			    }
			}
		    }
		    return saveflag;
		}
	    });
	    if (success) {
		toBJUIJson(200, "操作成功", menuId, "", "", "true", "");
	    } else {
		toErrorJson("数据处理存在错误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理存在错误，请检查后重新提交！");
	}
    }

    public void activeupdateip() {
	int id = getParaToInt();
	T_Wactive wa = T_Wactive.dao.findById(id);
	setAttr("wa", wa);
	String ptype = wa.getStr("ptype");
	String user1 = wa.getStr("user1");
	// 执行人配置
	if ("1".equals(ptype) || "5".equals(ptype)) {// 取部门
	    setAttr("seldept", user1);
	    setAttr("seldept", user1);
	} else if ("2".equals(ptype)) { // 取部门职位
	    if (!"".equals(user1) && user1 != null && user1.indexOf("#") != -1) {
		String[] deptposition = user1.split("#");
		if (deptposition.length == 2) {
		    setAttr("seldept", deptposition[0]);
		    setAttr("selposition", deptposition[1]);
		}
	    }
	} else if ("3".equals(ptype)) {// 取指定用户
	    setAttr("selUsersId", user1);
	    if (null != user1 && !"".equals(user1)) {
		setAttr("selUsersName", T_User.findUsernames(user1));
	    }
	}
	String default1 = wa.getStr("default1");
	setAttr("defUsersId", default1);
	if (null != default1 && !"".equals(default1))
	    setAttr("defUsersName", T_User.findUsernames(default1));
	List<T_Wtrans> trans = T_Wtrans.dao.getNextSteps(id);
	setAttr("trans", trans);
	String nextactive = "";
	for (int i = 0; i < trans.size(); i++) {
	    T_Wtrans tran = trans.get(i);
	    nextactive += "," + tran.getStr("ato");
	}
	if (!nextactive.equals(""))
	    nextactive = nextactive.substring(1);
	setAttr("nextactive", nextactive);
	int wid = wa.getInt("wid");
	setAttr("wid", wid);
	List<T_Wactive> actives = T_Wactive.dao.findByProcessId(wid);
	setAttr("actives", actives);
	List<T_Wfield> conds = T_Wfield.dao.getAllCondfieldByWid(wid);
	setAttr("conds", conds);
	List<T_Wfield> fields = T_Wfield.dao.getAllOpinionfieldByWid(wid);
	setAttr("fields", fields);
	int typeflag = T_Wactive.dao.getAtype(wid);
	setAttr("tf", typeflag);
	List<T_Wprocess> process = T_Wprocess.dao.getAllSubProcess();
	setAttr("process", process);
	List<T_User> users = T_User.getList();
	setAttr("users", users);
	List<T_Department> depts = T_Department.getList();
	setAttr("depts", depts);
	List<T_Position> positions = T_Position.getList();
	setAttr("positions", positions);
	int afrom = getParaToInt();
	String sql = "select t1.*,t2.fname department,t3.name position,t4.name active from t_wtrans_sp t1 left join t_department t2 on t1.dept=t2.id "
		+ "left join t_position t3 on t1.positions=t3.id left join t_wactive t4 on t1.ato=t4.id where t1.afrom='"
		+ afrom + "'";
	List<T_Wtrans_Sp> list = T_Wtrans_Sp.dao.find(sql);
	setAttr("list", list);
	setAttr("afrom", afrom);
	render("active_update.jsp");
    }

    public void activeupdate() {
	try {
	    T_Wactive model = getModel(T_Wactive.class);
	    int wid = model.getInt("wid");
	    if ("0".equals(model.getStr("havesubflow"))) {
		model.set("subflow", null);
		model.set("ishold", null);
	    }
	    if ("0".equals(model.getStr("haveopinionfield"))) {
		model.set("opinionfield", null);
	    }
	    if ("1".equals(model.getStr("participant"))) {
		model.set("usergroup", getPara("param.userId"));
	    }
	    if ("1".equals(model.getStr("amount"))) {
		model.set("issequence", null);
	    }
	    if ("0".equals(model.getStr("havecondfield"))) {
		model.set("condfield", null);
	    }
	    String ptype = model.getStr("ptype");
	    if ("1".equals(ptype) || "5".equals(ptype)) {
		model.set("user1", getPara("dept"));
	    } else if ("2".equals(ptype)) {
		String dept = getPara("dept");
		String[] position = getParaValues("position");
		model.set("user1", dept + "#" + StringUtil.combine(position));
	    } else if ("3".equals(ptype)) {
		model.set("user1", getPara("param.userId"));
	    } else {
		model.set("user1", "");
	    }
	    model.set("default1", getPara("default.userId"));
	    boolean saveflag = model.update();
	    if (saveflag) {
		int aid = model.getInt("id");
		T_Wtrans.dao.deleteOldById(aid);
		if ("0".equals(model.getStr("havecondfield"))) {// 没有完善
		    String[] nas = getParaValues("nextactives");
		    if (nas != null) {
			for (int i = 0; i < nas.length; i++) {
			    T_Wtrans trans = new T_Wtrans();
			    trans.set("wid", wid);
			    trans.set("afrom", String.valueOf(aid));
			    trans.set("ato", nas[i]);
			    trans.save();
			}
		    }
		} else {
		    for (int i = 1; i <= 3; i++) {
			String condition1 = getPara("condition" + i);
			if (StrKit.notBlank(condition1)) {
			    T_Wtrans trans = new T_Wtrans();
			    trans.set("wid", wid);
			    trans.set("condition1", condition1);
			    trans.set("afrom", String.valueOf(aid));
			    trans.set("ato", getPara("ato" + i));
			    trans.save();
			}
		    }
		}
	    }
	    toBJUIJson(200, "环节信息修改成功", menuId, "", "", "true", "");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    public void activedel() {
	try {
	    int aid = Integer.parseInt(getPara(0));
	    T_Wactive wa = T_Wactive.dao.findById(aid);
	    if (wa == null) {
		toErrorJson(Constant.EXCEPTION);
	    }
	    if (wa.delete()) {
		toBJUIJson(200, "删除成功", menuId, "", "", "", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /*********************** 流程域定义 *********************/
    public void fieldmain() {
	int wid = getParaToInt();
	List<T_Wfield> list = T_Wfield.dao.find("select * from t_wfield where wid='" + wid + "'");
	setAttr("list", list);
	setAttr("wid", wid);
	render("field.jsp");
    }

    public void fieldaddip() {
	int wid = getParaToInt();
	setAttr("wid", wid);
	render("field_add.jsp");
    }

    @Clear
    public void fieldadd() {
	T_Wfield model = new T_Wfield();
	String wid = getPara("wid");
	String name = getPara("name");
	String description = getPara("description");
	if (name.equals("")) {
	    toErrorJson("域名称不能为空");
	    return;
	}
	if (description.equals("")) {
	    toErrorJson("域说明不能为空");
	    return;
	}
	// model.set("id", getPara("id"));
	model.set("wid", wid);
	model.set("name", name);
	model.set("type", getPara("type"));
	model.set("description", description);
	model.save();
	toBJUIJson(200, "操作成功", "", "editfield", "", "true", "");
    }

    public void fieldupdateip() {
	Integer id = getParaToInt();
	T_Wfield model = T_Wfield.dao.findById(id);
	setAttr("model", model);
	render("field_update.jsp");
    }

    @Clear
    public void fieldupdate() {
	T_Wfield model = getModel(T_Wfield.class);
	model.update();
	toBJUIJson(200, "操作成功", "", "editfield", "", "true", "");
    }

    @Clear
    public void fielddel() {
	int id = getParaToInt(0);
	T_Wfield.dao.deleteById(id);
	toBJUIJson(200, "操作成功", "", "editfield", "", "", "");
    }

    /*********************** 流程过程函数 *********************/

    /*********************** 流转特例定义 *********************/
    @Clear
    public void transmain() {
	int afrom = getParaToInt();
	String sql = "select t1.*,t2.fname department,t3.name position,t4.name active from t_wtrans_sp t1 left join t_department t2 on t1.dept=t2.id left join t_position t3 on t1.positions=t3.id left join t_wactive t4 on t1.ato=t4.id where t1.afrom = '"
		+ afrom + "'";
	List<T_Wtrans_Sp> list = T_Wtrans_Sp.dao.find(sql);
	setAttr("list", list);
	setAttr("afrom", afrom);
	render("trans.jsp");
    }

    public void transaddip() {
	int afrom = getParaToInt();
	int pid = T_Wactive.dao.findById(afrom).getInt("wid");
	List<T_Wactive> actives = T_Wactive.dao.findByProcessId(pid);
	setAttr("actives", actives);
	List<T_Department> depts = T_Department.getList();
	setAttr("depts", depts);
	List<T_Position> positions = T_Position.getList();
	setAttr("positions", positions);
	setAttr("afrom", afrom);
	render("trans_add.jsp");
    }

    @Clear
    public void transadd() {
	T_Wtrans_Sp model = new T_Wtrans_Sp();
	String afrom = getPara("afrom");
	String dept = getPara("dept");
	String positions = getPara("positions");
	String ato = getPara("ato");
	if (StrKit.isBlank(ato)) {
	    toErrorJson("请配置下一环节！");
	    return;
	}
	model.set("afrom", afrom);
	model.set("dept", dept);
	model.set("positions", positions);
	model.set("ato", ato);
	model.save();
	toBJUIJson(200, "操作成功", "", "editactive", "", "true", "");
    }

    @Clear
    public void transupdateip() {
	int id = getParaToInt(0);
	T_Wtrans_Sp model = T_Wtrans_Sp.dao.findById(id);

	int pid = T_Wactive.dao.findById(model.getInt("afrom")).getInt("wid");
	List<T_Wactive> actives = T_Wactive.dao.findByProcessId(pid);
	setAttr("actives", actives);
	List<T_Department> depts = T_Department.getList();
	setAttr("depts", depts);
	List<T_Position> positions = T_Position.getList();
	setAttr("positions", positions);
	setAttr("model", model);
	render("trans_update.jsp");
    }

    public void transupdate() {
	T_Wtrans_Sp model = getModel(T_Wtrans_Sp.class);
	if (model.getInt("ato") == 0) {
	    toErrorJson("请配置下一环节！");
	    return;
	}
	model.update();
	toBJUIJson(200, "操作成功", "", "editactive", "", "true", "");
    }

    @Clear
    public void transdel() {
	int id = getParaToInt(0);
	T_Wtrans_Sp.dao.deleteById(id);
	toBJUIJson(200, "操作成功", "", "edittrans", "", "", "");
    }

    /*********************** 流程过程函数 *********************/

    /** 显示流转记录 */
    @Clear
    public void showsteps() {
	String id = getPara(0);
	System.out.println(id);
	List<T_Workitem> list = T_Workitem.dao.find("select * from t_workitem where pid=?", id);
	setAttr("list", list);
	System.out.println(list);
	List<T_User> user = T_User.dao.find("select * from t_user where status=1");
	Map<String, String> userHm = new TreeMap<String, String>();
	;
	if (user.size() > 0) {
	    for (T_User model : user) {
		userHm.put(String.valueOf(model.get("id")), model.getStr("name"));
	    }
	}
	setAttr("userHM", userHm);
	setAttr("activeHM", T_Wactive.dao.getActiveHm());
	setAttr("operationHM", T_Woperation.dao.getOperationHm());
	render("showsteps.jsp");
    }

    /** 填写除同意以外的其他意见 */
    @Clear
    public void writeopinion() {
	if (getPara() != null && !"".equals(getPara())) {
	    try {
		setAttr("opinion", java.net.URLDecoder.decode(getPara(), "UTF-8"));
	    } catch (Exception e) {
		toErrorJson("数据处理出现错误！请重新输入！");
	    }
	}
	List<T_Common_Detail> list = T_Common_Detail.getListByKey("opinion");
	setAttr("opinions", list);
	// 个人意见取得
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	List<T_Myopinion> mylist = T_Myopinion.dao.getListByUserID(u_id);
	setAttr("myopinions", mylist);
	render("writeopinion.jsp");
    }

    /** 填写除同意以外的其他意见 */
    @Clear
    public void writeopinion1() {
	if (getPara() != null && !"".equals(getPara())) {
	    try {
		setAttr("opinion", java.net.URLDecoder.decode(getPara(), "UTF-8"));
	    } catch (Exception e) {
		toErrorJson("数据处理出现错误！请重新输入！");
	    }
	}
	List<T_Common_Detail> list = T_Common_Detail.getListByKey("opinion");
	setAttr("opinions", list);
	// 个人意见取得
	LoginModel loginModel = (LoginModel) getSession().getAttribute("loginModel");
	int u_id = loginModel.getUserId();
	List<T_Myopinion> mylist = T_Myopinion.dao.getListByUserID(u_id);
	setAttr("myopinions", mylist);
	render("writeopinion1.jsp");
    }

    /** 发送下一环节 */
    @Clear
    public void selectstep() {
	String position = getPara(2);
	String id = getPara(0);
	String cond = getPara(1);
	String nextId = getPara(3);
	List<T_Wactive> was;
	Integer positionid = null;
	T_Wactive wa = T_Wactive.dao.findById(id);
	if (wa == null) {
	    toErrorJson("找不到相应的环节信息，请重新选择！");
	    return;
	}
	try {
	    String conds = java.net.URLDecoder.decode(getPara(1), "UTF-8");
	    String tmpstr[] = conds.split("~");

	    String curdept = tmpstr[1];

	    Integer d_id = Integer.parseInt(curdept);
	    if (StrKit.notBlank(position) && !position.equals("undefined")) {
		positionid = Integer.parseInt(position);
	    }
	    String spids = T_Wtrans_Sp.dao.getNextSteps(String.valueOf(id), d_id, positionid);
	    if (StrKit.notBlank(spids)) {
		was = T_Wactive.dao.findInIds(spids);
	    } else {
		String ids = T_Wactive.dao.getNextSteps(id);
		was = T_Wactive.dao.findInIds(ids);
	    }
	    if (was.size() ==1) {
		nextId = was.get(0).getInt("id").toString();
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("数据处理出现错误！请重新输入！");
	    return;
	}

	if (was.size() > 1) {
	    setAttr("was", was);
	    setAttr("wa", wa);
	    setAttr("id", id);
	    setAttr("cond", cond);
	    render("selectstep.jsp");
	} else {
	    selectman(nextId);
	}
    }

    /** 发送下一环节 */
    @Clear
    public void selectstep1() {
	Integer positionid = getParaToInt(2);
	String id = getPara(0);
	String cond = getPara(1);
	String pid = getPara(3);// 以找出已签的人
	T_Wactive wa = T_Wactive.dao.findById(id);
	List<T_Wactive> was = null;
	try {
	    // String conds = java.net.URLDecoder.decode(getPara(1), "UTF-8");
	    String tmpstr[] = cond.split("~");

	    String curdept = tmpstr[1];

	    Integer d_id = Integer.parseInt(curdept);

	    String spids = T_Wtrans_Sp.dao.getNextSteps(String.valueOf(id), d_id, positionid);
	    if (StrKit.notBlank(spids)) {
		was = T_Wactive.dao.findInIds(spids);

	    } else {
		String ids = T_Wactive.dao.getNextSteps(id);
		was = T_Wactive.dao.findInIds(ids);
	    }
	} catch (Exception e) {
	    toErrorJson("数据处理出现错误！请重新输入！");
	    e.printStackTrace();
	}
	setAttr("pid", pid);
	setAttr("was", was);
	setAttr("wa", wa);
	setAttr("id", id);
	setAttr("cond", cond);
	render("selectstep1.jsp");

    }

    /** 发送下一环节 */
    @Clear
    public void selectsteps() {
	String id = getPara(0);
	String cond = getPara(1);
	try {
	    List<T_Wactive> was = T_Wactive.dao.find("select * from t_wactive where wid=?", id);
	    setAttr("was", was);
	} catch (Exception e) {
	    toErrorJson("数据处理出现错误！请重新输入！");
	    return;
	}
	setAttr("id", id);
	setAttr("cond", cond);
	render("selectstep1.jsp");
    }

    /** 选择下一环节的执行人 */
    @Clear
    public void selectman(String id) {
	// String id = getPara(0);
	//String id = getPara(3);
	String position = "";
	T_Wactive wa = T_Wactive.dao.findById(id);
	if (wa == null) {
	    toErrorJson("找不到相应的环节信息，请重新选择！");
	    return;
	}
	if (wa.getStr("handround").equals("1")) {
	    List<T_Department> deparmentList = T_Department.getAllDepts();
	    List<T_User> userList = T_User.getList();
	    setAttr("deparmentList", deparmentList);
	    setAttr("userList", userList);
	    setAttr("id", id);
	    setAttr("wa", wa);
	    render("selectmans.jsp");
	} else {
	    try {
		String cond = java.net.URLDecoder.decode(getPara(1), "UTF-8");
		ArrayList<HashMap<String, Object>> mans = wa.getTodomans(cond);
		// 如果下一环节执行人是按指定职位来选取，取出相应的职位信息
		if (wa.get("ptype") != null && wa.get("ptype").equals("2")) {
		    String users = wa.getStr("user1");
		    String[] users1 = users.split("#");
		    if (users1.length > 1) {
			String positionids = users1[1];
			position = T_Position.findPositionNames(positionids);
		    }
		}
		String default1 = wa.getStr("default1");
		if (null != default1 && !"".equals(default1)) {
		    String[] defaults = default1.split(",");
		    setAttr("default1", defaults[0]);
		}
		setAttr("amount", wa.getStr("amount"));
		setAttr("position", position);
		setAttr("mans", mans);
		setAttr("mannum", mans.size());
	    } catch (Exception e) {
		toErrorJson("数据处理出现错误！请重新输入！");
		e.printStackTrace();
	    }
	    setAttr("id", id);
	    setAttr("wa", wa);
	    render("selectman.jsp");
	}
    }
    
    /** 选择下一环节的执行人 */
    @Clear
    public void selectman() {
	String id = getPara(0);
	String position = "";
	T_Wactive wa = T_Wactive.dao.findById(id);
	if (wa == null) {
	    toErrorJson("找不到相应的环节信息，请重新选择！");
	    return;
	}
	if (wa.getStr("handround").equals("1")) {
	    List<T_Department> deparmentList = T_Department.getAllDepts();
	    List<T_User> userList = T_User.getList();
	    setAttr("deparmentList", deparmentList);
	    setAttr("userList", userList);
	    setAttr("id", id);
	    setAttr("wa", wa);
	    render("selectmans.jsp");
	} else {
	    try {
		String cond = java.net.URLDecoder.decode(getPara(1), "UTF-8");
		ArrayList<HashMap<String, Object>> mans = wa.getTodomans(cond);
		// 如果下一环节执行人是按指定职位来选取，取出相应的职位信息
		if (wa.get("ptype") != null && wa.get("ptype").equals("2")) {
		    String users = wa.getStr("user1");
		    String[] users1 = users.split("#");
		    if (users1.length > 1) {
			String positionids = users1[1];
			position = T_Position.findPositionNames(positionids);
		    }
		}
		String default1 = wa.getStr("default1");
		if (null != default1 && !"".equals(default1)) {
		    String[] defaults = default1.split(",");
		    setAttr("default1", defaults[0]);
		}
		setAttr("amount", wa.getStr("amount"));
		setAttr("position", position);
		setAttr("mans", mans);
		setAttr("mannum", mans.size());
	    } catch (Exception e) {
		toErrorJson("数据处理出现错误！请重新输入！");
		e.printStackTrace();
	    }
	    setAttr("id", id);
	    setAttr("wa", wa);
	    render("selectman.jsp");
	}
    }

    /** 选择下一环节的执行人 */
    @Clear
    public void selectman1() {
	String id = getPara(0);
	String position = "";
	T_Wactive wa = T_Wactive.dao.findById(id);
	if (wa == null) {
	    toErrorJson("找不到相应的环节信息，请重新选择！");
	    return;
	}
	setAttr("wa", wa);
	if (wa.getStr("handround").equals("1")) {
	    List<T_Department> deparmentList = T_Department.getAllDepts();
	    List<T_User> userList = T_User.getList();
	    setAttr("deparmentList", deparmentList);
	    setAttr("userList", userList);
	    setAttr("id", id);
	    render("selectmans.jsp");
	} else {
	    try {
		String cond = java.net.URLDecoder.decode(getPara(1), "UTF-8");
		ArrayList<HashMap<String, Object>> mans = wa.getTodomans(cond);
		// 如果下一环节执行人是按指定职位来选取，取出相应的职位信息
		if (wa.get("ptype") != null && wa.get("ptype").equals("2")) {
		    String users = wa.getStr("user1");
		    String[] users1 = users.split("#");
		    if (users1.length > 1) {
			String positionids = users1[1];
			position = T_Position.findPositionNames(positionids);
		    }
		}
		String default1 = wa.getStr("default1");
		if (null != default1 && !"".equals(default1)) {
		    String[] defaults = default1.split(",");
		    setAttr("default1", defaults[0]);
		}

		setAttr("amount", wa.getStr("amount"));
		setAttr("position", position);
		setAttr("mans", mans);
		setAttr("mannum", mans.size());
	    } catch (Exception e) {
		toErrorJson("数据处理出现错误！请重新输入！");
	    }
	    setAttr("id", id);
	    setAttr("wa", wa);
	    render("selectman1.jsp");
	}
    }

    /** 根据页面选择的环节，选择处理人员组 */
    @Clear
    public void selectman2() {
	String id = getPara(0);//
	String pid = getPara(2);
	String position = "";
	T_Wactive wa = T_Wactive.dao.findById(id);
	List<T_Department> deparmentAllList = T_Department.getAllDepts();
	List<T_User> userList = new ArrayList<T_User>();
	try {
	    String cond = java.net.URLDecoder.decode(getPara(1), "UTF-8");
	    ArrayList<HashMap<String, Object>> mans = wa.getTodomans(cond, wa.getStr("name"), pid);
	    // 如果下一环节执行人是按指定职位来选取，取出相应的职位信息
	    if (wa.get("ptype") != null && wa.get("ptype").equals("2")) {
		String users = wa.getStr("user1");
		String[] users1 = users.split("#");
		if (users1.length > 1) {
		    String positionids = users1[1];
		    position = T_Position.findPositionNames(positionids);
		}
	    }
	    String default1 = wa.getStr("default1");
	    if (null != default1 && !"".equals(default1)) {
		String[] defaults = default1.split(",");
		setAttr("default1", defaults[0]);
	    }
	    setAttr("amount", wa.getStr("amount"));
	    setAttr("position", position);
	    setAttr("mans", mans);
	    setAttr("mannum", mans.size());
	    // 循环构造人员
	    for (int i = 0; i < mans.size(); i++) {
		HashMap<String, Object> man = mans.get(i);
		T_User user = new T_User();
		user.set("pid", man.get("pid"));
		user.set("name", man.get("name"));
		user.set("d_id", man.get("d_id"));
		user.set("id", man.get("id"));
		userList.add(user);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("数据处理出现错误！请重新输入！");
	}

	/*
	 * for(T_Department dept : deparmentList){ String users = ""; for
	 * (T_User user : userList){ if(user.getInt("d_id") ==
	 * dept.getInt("id")){ users += user.getInt("id") +";"; } }
	 * if(users!=null && users.length()>0){ dept.set("sname",
	 * users.substring(0, users.length()-1)); }
	 * 
	 * users = ""; }
	 */
	List<T_Department> deparmentList = null;
	String ids = "";
	for (T_Department dept : deparmentAllList) {
	    for (T_User user : userList) {
		int d_id = user.getInt("d_id");
		if (d_id == dept.getInt("id")) {
		    ids += "," + dept.getInt("id");
		}
	    }
	}
	if (ids.length() > 1) {
	    ids = ids.substring(1, ids.length());
	    deparmentList = T_Department.dao.find("select * from t_department where id in (" + ids + ")");
	    setAttr("deparmentList", deparmentList);

	}

	setAttr("userList", userList);

	render("selectman2.jsp");

    }

    // 2015/11/02 徐 追加 多环节和人员选择页面合并 end

    @Clear
    public void getUser() {
	String zxrid = getPara(0);
	if (zxrid != null && !"".equals(zxrid)) {
	    List<T_User> users = T_User.findUsers(zxrid);
	    Integer[] usersArr = StringUtil.splitInteger(zxrid);
	    setAttr("usersArr", usersArr);
	    setAttr("users", users);
	}
	List<T_Department> deparmentList = T_Department.getAllDepts();
	List<T_User> userList = T_User.getList();
	setAttr("deparmentList", deparmentList);
	setAttr("userList", userList);
	render("userList.jsp");
    }

    @Clear
    public void getDept() {
	String id = getPara(0);
	if (id != null && !"".equals(id)) {
	    List<T_Department> depts = T_Department.findDepts(id);
	    Integer[] deptsArr = StringUtil.splitInteger(id);
	    setAttr("deptsArr", deptsArr);
	    setAttr("depts", depts);
	}
	List<T_Department> deparmentList = T_Department.getAllDepts();
	setAttr("deparmentList", deparmentList);
	render("deptList.jsp");
    }

    @Clear
    public void getPosition() {
	String id = getPara(0);
	if (id != null && !"".equals(id)) {
	    List<T_Position> positions = T_Position.findPositions(id);
	    Integer[] positionsArr = StringUtil.splitInteger(id);
	    setAttr("positionsArr", positionsArr);
	    setAttr("positions", positions);
	}
	List<T_Position> positionList = T_Position.getList();
	setAttr("positionList", positionList);
	render("posiList.jsp");
    }

    public static boolean isNumeric(String str) {
	for (int i = str.length(); --i >= 0;) {
	    if (!Character.isDigit(str.charAt(i))) {
		return false;
	    }
	}
	return true;
    }

    /** 选择下一环节的执行人 */
    @Clear
    public void selectmanToMobile() {
	String id = getPara(0);
	T_Wactive wa = T_Wactive.dao.findById(id);
	if (wa == null) {
	    toErrorJson("找不到相应的环节信息，请重新选择！");
	    return;
	}
	setAttr("wa", wa);
	if (wa.getStr("handround").equals("1")) {
	    List<T_Department> deparmentList = T_Department.getAllDepts();
	    List<T_User> userList = T_User.getList();
	    setAttr("deparmentList", deparmentList);
	    setAttr("userList", userList);
	    setAttr("id", id);
	    render("selectmans.jsp");
	} else {
	    try {
		String cond = java.net.URLDecoder.decode(getPara(1), "UTF-8");
		ArrayList<HashMap<String, Object>> mans = wa.getTodomans(cond);
		renderJson(JsonKit.toJson(mans.get(0).get("id")));
	    } catch (Exception e) {
		toErrorJson("数据处理出现错误！请重新输入！");
	    }

	}
    }

    public void jieguo() {
	render("jieguo.jsp");
    }

}