package com.oa.model.document;

import java.io.File;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.oa.model.system.workflow.T_Workflow;
import com.zhilian.config.Constant;
import com.zhilian.model.T_User;

public class T_Received extends Model<T_Received> {

    private static final long serialVersionUID = 1L;
    public static final T_Received dao = new T_Received();
    private String signPath = Constant.PATH_WEBROOT + Constant.PATH_SIGNATURE;

    public String judge(int docid, int u_id) {
	String sql = "select * from t_received where doc_id =" + docid;
	List<T_Received> model = dao.find(sql);
	String isreceived = "0";
	for (int i = 0; i < model.size(); i++) {
	    if (u_id == model.get(i).getInt("u_id")) {
		isreceived = "1";
	    }
	}

	return isreceived;
    }

    public T_Received findByDoc(Object docid, Object u_id) {
	String sql = "select * from t_received where doc_id =" + docid + " and u_id=" + u_id;
	return dao.findFirst(sql);
    }

    public boolean judgeisreceive(int docid, int u_id) {// 判断该文已签收，但自己并没有签收
	boolean isreceive = false;
	String sql = "select * from t_received where doc_id=" + docid;
	if (dao.find(sql).size() > 0) {
	    if (!hadReceive(u_id, docid)) {
		isreceive = true;
	    }
	}
	return isreceive;
    }

    /**
     * 判断该文没有签收，直接提交，那么自己也只能选择提交,或自己已经签收
     * 
     * @param wf
     * @param docid
     * @param u_id
     *            当前用户
     * @return
     */
    public boolean judgeissubmit(T_Workflow wf, Object docid, Object u_id) {
	boolean ismustsubmit = false;
	if (!wf.getStr("isreceive").equals("1") && StrKit.notBlank(wf.getStr("nextshoulddo"))) {// 是否被签收
	    ismustsubmit = true;
	}
	String sql = "select * from t_received where doc_id =" + docid + " and u_id=" + u_id;
	List<T_Received> model = dao.find(sql);
	if (model.size() > 0) {
	    ismustsubmit = true;
	}
	return ismustsubmit;
    }

    public boolean hadReceive(int u_id, int docid) {// 判断该文自己是否已签收
	boolean flag = false;
	String sql1 = "select * from t_received a where doc_id =" + docid + " and u_id =" + u_id;
	if (dao.find(sql1).size() > 0) {
	    flag = true;
	}

	return flag;
    }

    public String getOpinion(Object docid, Object u_id) {
	String sql = "select * from t_received where doc_id =" + docid;

	List<T_Received> model = dao.find(sql);
	String temp = "";
	for (int i = 0; i < model.size(); i++) {
	    String opinion = StrKit.isBlank(model.get(i).getStr("opinion")) ? "" : model.get(i).getStr("opinion");
	    String opiniontime = StrKit.isBlank(String.valueOf(model.get(i).getDate("opiniontime"))) ? ""
		    : String.valueOf(model.get(i).getDate("opiniontime"));
	    if (opiniontime.length() > 16) {
		opiniontime = opiniontime.substring(0, 16);
	    }
	    String username = T_User.getUserNameById(model.get(i).getInt("u_id"));

	    String did = T_User.dao.findFirst("select * from t_user where id=" + model.get(i).getInt("u_id"))
		    .getStr("dlid");
	    String signfile = Constant.PATH_SIGNATURE + "/" + did + ".png";
	    File file = new File(signPath + "/" + did + ".png");
	    if (file.exists() && StrKit.notBlank(opinion)) {
		temp += opinion + "<br><span style='padding-left:50px'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='"
			+ signfile.substring(1) + "' width='200' height='70'>&nbsp;&nbsp;[" + opiniontime
			+ "]</span><br><br>";
	    } else if (StrKit.notBlank(opinion)) {
		temp += opinion + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + username + "&nbsp;&nbsp;["
			+ opiniontime + "]<br><br>";
		/* temp += username + "&nbsp;&nbsp;"; */
	    }
	}

	return temp;
    }

    public String getOpinionButme(Object docid, Object u_id) {
	String sql = "select * from t_received where doc_id =" + docid + " and u_id !=" + u_id;

	List<T_Received> model = dao.find(sql);
	String temp = "";
	for (int i = 0; i < model.size(); i++) {
	    String opinion = StrKit.isBlank(model.get(i).getStr("opinion")) ? "" : model.get(i).getStr("opinion");
	    String opiniontime = StrKit.isBlank(String.valueOf(model.get(i).getDate("opiniontime"))) ? ""
		    : String.valueOf(model.get(i).getDate("opiniontime"));
	    if (opiniontime.length() > 16) {
		opiniontime = opiniontime.substring(0, 16);
	    }
	    String username = T_User.getUserNameById(model.get(i).getInt("u_id"));

	    String did = T_User.dao.findFirst("select * from t_user where id=" + model.get(i).getInt("u_id"))
		    .getStr("dlid");
	    String signfile = Constant.PATH_SIGNATURE + "/" + did + ".png";
	    File file = new File(signPath + "/" + did + ".png");
	    if (file.exists() && StrKit.notBlank(opinion)) {
		temp += opinion + "<br><span style='padding-left:50px'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='"
			+ signfile.substring(1) + "' width='200' height='70'>&nbsp;&nbsp;[" + opiniontime
			+ "]</span><br><br>";
	    } else if (StrKit.notBlank(opinion)) {
		temp += opinion + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + username + "&nbsp;&nbsp;["
			+ opiniontime + "]<br><br>";
		/* temp += username + "&nbsp;&nbsp;"; */
	    }
	}

	return temp;
    }

    public String getOtherOpinion(Object docid, Object u_id) {
	String sql = "select * from t_received where doc_id =" + docid + " and u_id !=" + u_id;

	List<T_Received> model = dao.find(sql);
	String temp = "";
	for (int i = 0; i < model.size(); i++) {
	    String opinion = StrKit.isBlank(model.get(i).getStr("opinion")) ? "" : model.get(i).getStr("opinion");
	    String opiniontime = StrKit.isBlank(String.valueOf(model.get(i).getDate("opiniontime"))) ? ""
		    : String.valueOf(model.get(i).getDate("opiniontime"));
	    if (opiniontime.length() > 0) {
		opiniontime = opiniontime.substring(0, 16);
	    }
	    String username = T_User.getUserNameById(model.get(i).getInt("u_id"));

	    String did = T_User.dao.findFirst("select * from t_user where id=" + model.get(i).getInt("u_id"))
		    .getStr("dlid");
	    String signfile = Constant.PATH_SIGNATURE + "/" + did + ".png";
	    File file = new File(signPath + "/" + did + ".png");
	    if (file.exists() && StrKit.notBlank(opinion)) {
		temp += opinion + "<br><span style='padding-left:50px'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='"
			+ signfile.substring(1) + "' width='200' height='70'>&nbsp;&nbsp;[" + opiniontime
			+ "]</span><br><br>";
	    } else if (StrKit.notBlank(opinion)) {
		temp += opinion + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + username + "&nbsp;&nbsp;["
			+ opiniontime + "]<br><br>";
		/* temp += username + "&nbsp;&nbsp;"; */
	    }
	}

	return temp;
    }

    public String getOthermeOpinion(Object docid, Object u_id) {
	String sql = "select * from t_received where doc_id =" + docid + " and u_id =" + u_id;

	List<T_Received> model = dao.find(sql);
	String temp = "";
	for (int i = 0; i < model.size(); i++) {
	    String opinion = StrKit.isBlank(model.get(i).getStr("opinion")) ? "" : model.get(i).getStr("opinion");
	    String opiniontime = StrKit.isBlank(String.valueOf(model.get(i).getDate("opiniontime"))) ? ""
		    : String.valueOf(model.get(i).getDate("opiniontime"));
	    if (opiniontime.length() > 0) {
		opiniontime = opiniontime.substring(0, 16);
	    }
	    String username = T_User.getUserNameById(model.get(i).getInt("u_id"));

	    String did = T_User.dao.findFirst("select * from t_user where id=" + model.get(i).getInt("u_id"))
		    .getStr("dlid");
	    String signfile = Constant.PATH_SIGNATURE + "/" + did + ".png";
	    File file = new File(signPath + "/" + did + ".png");
	    if (file.exists() && StrKit.notBlank(opinion)) {
		temp += opinion + "<br><span style='padding-left:50px'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='"
			+ signfile.substring(1) + "' width='200' height='70'>&nbsp;&nbsp;[" + opiniontime
			+ "]</span><br><br>";
	    } else if (StrKit.notBlank(opinion)) {
		temp += opinion + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + username + "&nbsp;&nbsp;["
			+ opiniontime + "]<br><br>";
		/* temp += username + "&nbsp;&nbsp;"; */
	    }
	}

	return temp;
    }

    public List<T_Received> findUsersByDoc(Object docid) {
	String sql = "select * from t_received where doc_id=" + docid;
	return dao.find(sql);
    }
    
    public void deleteByAid(Object docid) {
	String sql = "delete from t_received where doc_id=" + docid;
	Db.update(sql);
    }

}
