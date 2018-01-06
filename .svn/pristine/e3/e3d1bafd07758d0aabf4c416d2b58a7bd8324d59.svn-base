package com.oa.controller.work;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.work.T_Announce;
import com.oa.model.work.T_Announce_Viewer;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.LoginModel;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;
import com.zhilian.util.DateUtils;

@RouteBind(path = "Main/Announce", viewPath = "Work/Announce")
public class Announce extends BaseAssociationController {

    String menuId = "Announce";

    /** 公告信息主页面 */
    public void main() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int userid = loginModel.getUserId();
	String select="select * ";
	String sqlExceptSelect="from t_announce "
		+ " where ((scope=0 or (scope=1 and ','+scope_uid+',' like '%,"+userid+",%'))"
		+ " and status=1 or uid = "+userid +") " ;
	Integer pageNumber = 1;
	Integer pageSize = Constant.PAGE_SIZE;
	String stitle=null,stype=null,sdate=null,edate=null;
	List<T_Announce> contenthtmls=T_Announce.dao.find("select id,content from t_announce where (scope=0 or (scope=1 and '%,'+scope_uid+',%' like ',"+userid+",'))") ;
	Map<Long,String> map = new HashMap<Long,String>();
	for(int i=0;i<contenthtmls.size();i++){
	    Long id=contenthtmls.get(i).getLong("id");
	    String contenthtml=contenthtmls.get(i).getStr("content");
	    String content=Html2Text(contenthtml);
	    map.put(id, content);
	}
	setAttr("contentMap",map);
	try{
	    if(getPara("stitle") != null && !"".equals(getPara("stitle").trim())){
		stitle=getPara("stitle");
		sqlExceptSelect += " and title like '%"+stitle+"%' ";
		setAttr("stitle",stitle);
	    }
	    
	    if(getPara("stype") != null && !"".equals(getPara("stype").trim())){
		stype=getPara("stype");
		sqlExceptSelect +="and atype ="+stype;
		setAttr("stype",stype);
	    }
	    
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect +="and sendtime >='"+sdate+"' ";
		setAttr("sdate",sdate);
	    }
	    
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect +="and sendtime <='"+edate+"' ";
		setAttr("edate",edate);
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
	}catch(Exception e){
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    e.printStackTrace();
	}
	//Page<T_Announce> page = T_Announce.dao.getAllPageAnno(pageNum, numPerPage, name, u_id);
	String sql = "select id " + sqlExceptSelect +"and id not in (select announceid from t_announce_viewer where viewerid="+userid+") and "+userid +" <> uid ";
	List<T_Announce> newannounce =T_Announce.dao.find(sql);
	
	sqlExceptSelect += "order by sendtime desc";
	
	Page<T_Announce> page = T_Announce.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	
	setAttr("newannounce",newannounce);
	setAttr("page", page);
	render("main.jsp");
    }

    /** 公告信息添加页面 */
    public void addip() {
	T_Announce announce = new T_Announce();
	announce.set("scope", 0);//默认选择全部人员
	setAttr("announce", announce);
	render("add.jsp");
    }

    /** 公告信息添加 */
    public void add() {
	String title = getPara("T_Announce.title");
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int userid = loginModel.getUserId();
	int did = loginModel.getDid();
	List<T_Announce> annos = T_Announce.dao.getAllAnnoByTitle(title, null);
	if (null != annos && 0 != annos.size()) {
	    toErrorJson("您提交的数据有误，公告标题已经存在！");
	    return;
	}
	T_Announce model = getModel(T_Announce.class);
	if (null != model.get("scope") && model.getInt("scope") == Constant.SCOPE_PART) {    
	    String[] userids=getPara("userId").split(",");
	    String[] usernames=getPara("userName").split(",");
	    removeDuplicate(userids);
	    model.set("scope_uid", removeDuplicate(userids));
	    model.set("scope_uname", removeDuplicate(usernames));
	}
	model.remove("id");
	model.set("uid", userid).set("did", did).set("sendtime", DateUtils.getNowTime()).save();
	toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
    }

    /** 公告信息修改页面 */
    public void updateip() {
	Integer id = getParaToInt(0);
	T_Announce announce = T_Announce.dao.findById(id);
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int userid = loginModel.getUserId();
	if(userid != announce.getInt("uid")){
	    toErrorJson("只有发布人可对该文进行编辑！");
	    return;
	}
	setAttr("announce", announce);
	render("update.jsp");
    }

    /** 公告信息修改处理 */
    public void update() {
	String title = getPara("T_Announce.title");
	Integer id = getParaToInt("T_Announce.id");
	List<T_Announce> annos = T_Announce.dao.getAllAnnoByTitle(title, id);
	if (null != annos && 0 != annos.size()) {
	    toErrorJson("您提交的数据有误，公告标题已经存在！");
	    return;
	}
	T_Announce model = getModel(T_Announce.class);
	if (null != model.get("scope") && model.getInt("scope") == Constant.SCOPE_PART) {
	  
	    String[] userids=getPara("userId").split(",");
	    String[] usernames=getPara("userName").split(",");
	    model.set("scope_uid", removeDuplicate(userids));
	    model.set("scope_uname", removeDuplicate(usernames));
	}
	model.update();
	toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
    }

    /** 公告信息删除处理 */
    public void delete() {
	Integer id = getParaToInt(0);
	T_Announce model = T_Announce.dao.findById(id);
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	int userid = loginModel.getUserId();
	if(userid != model.getInt("uid")){
	    toErrorJson("只有发布人才可以删除该文！");
	    return ;
	}
	model.delete();
	toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
    }

    /** 查看公告通知内容 */
    public void viewip() {
	Integer id = getParaToInt(0);
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	T_Announce_Viewer viewer = T_Announce_Viewer.dao.getViewer(id, loginModel.getUserId());
	setAttr("viewer", viewer);
	T_Announce announce = T_Announce.dao.findById(id);
	setAttr("announce", announce);

	setAttr("announcer", T_User.dao.findValueById("name", announce.getInt("uid")));
	setAttr("announcedept", T_Department.dao.findValueById("fname", announce.getInt("did")));
	render("view.jsp");
    }

    /** 已阅 */
    public void view() {
	LoginModel loginModel = (LoginModel) getSessionAttr("loginModel");
	T_Announce_Viewer model = getModel(T_Announce_Viewer.class);
	model.set("viewerid", loginModel.getUserId()).set("viewtime", DateUtils.getNowTime()).save();
	toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
    }

    /** 已阅者 */
    public void viewer() {
	Integer id = getParaToInt();
	List<T_Announce_Viewer> viewers = T_Announce_Viewer.dao.list("where announceid = " + id);
	setAttr("user", T_User.dao.hashMapById("name"));
	setAttr("viewers", viewers);
	render("viewer.jsp");
    }
    
    public static String Html2Text(String inputString){
	     String htmlStr = inputString; //含html标签的字符串
	     String textStr ="";
	     java.util.regex.Pattern p_script;
	     java.util.regex.Matcher m_script;
	     java.util.regex.Pattern p_style;
	     java.util.regex.Matcher m_style;
	     java.util.regex.Pattern p_html;
	     java.util.regex.Matcher m_html;

	    try{
	          String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
	          String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
	          String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

	          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
	          m_script = p_script.matcher(htmlStr);
	          htmlStr = m_script.replaceAll(""); //过滤script标签

	          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
	          m_style = p_style.matcher(htmlStr);
	          htmlStr = m_style.replaceAll(""); //过滤style标签

	          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
	          m_html = p_html.matcher(htmlStr);
	          htmlStr = m_html.replaceAll(""); //过滤html标签

	          textStr = htmlStr;
	     }catch(Exception e){
	         /* Manager.log.debug("neiNewsAction","Html2Text: " + e.getMessage());*/
		 e.printStackTrace();
	     }
	     return textStr;//返回文本字符串
	 }
    
    public static String removeDuplicate(String[] s) {
	List<String> list = new LinkedList<String>();
	String[] arrayOfString = s;
	String temp="";
	int j = s.length;
	for (int i = 0; i < j; i++) {
		String st = arrayOfString[i];
		if (!list.contains(st)) {
			list.add(st);
			temp+=","+st;
		}
	}
	return temp.substring(1);
}

}
