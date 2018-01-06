package com.oa.model.system.workflow;

import java.io.File;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Position;
import com.zhilian.model.T_User;

/**
 * 流程环节执行过程
 */
public class T_Workitem extends Model<T_Workitem> {

    private static final long serialVersionUID = 1L;
    private String signPath = Constant.PATH_WEBROOT + Constant.PATH_SIGNATURE;
    public static final T_Workitem dao = new T_Workitem();

    /**
     * 根据用户需求，现在需要改成上级职位可以看到下级职位的处理人是谁 下级职位不能看到上级职位的处理人是谁 取意见1-5
     * 
     * @param pid
     * @param opinionfield
     * @return
     */
    public String getOpinions_1(String pid, String opinionfield, String userid) {
	String temp = "";
	String strsql = "select * from t_workitem where pid=" + pid + " and opinionfield='" + opinionfield
		+ "' and len(opinion)>0 order by id";
	List<T_Workitem> items = this.find(strsql);
	if (items != null && items.size() != 0) {
	    for (int i = 0; i < items.size(); i++) {
		String user1 = items.get(i).getStr("user1") == null ? "" : items.get(i).getStr("user1");
		String opinion = items.get(i).getStr("opinion") == null ? "" : items.get(i).getStr("opinion");
		String opiniontime = items.get(i).getStr("opiniontime") == null ? ""
			: items.get(i).getStr("opiniontime");
		String username = T_User.getUserNameById(user1);
		// 得到环节处理人的职位序号
		Integer user1_xh = T_Position.getPositionPid(user1);
		// 得到登陆者的职位序号
		Integer login_xh = T_Position.getPositionPid(userid);
		// 如果登陆者的职位高于环节处理人的级别，则显示环节处理人姓名
		if (login_xh >= user1_xh) {
		    temp += opinion + "<br><span style=\"margiin-left:200px\">" + username + "&nbsp;&nbsp;["
			    + opiniontime + "]<span><br>";
		} /*
		   * else{ //否则显示环节处理人的职位 String
		   * position_name=T_position.dao.getPosition(user1); temp +=
		   * opinion + "<br>&nbsp;&nbsp;&nbsp;&nbsp;--" + position_name
		   * + " " + opiniontime + "<br>"; }
		   */
		else {
		    // 否则不显示处理人名字和意见
		    temp = "";
		}
	    }
	}
	return temp;
    }

    /**
     * 根据用户需求，现在需要改成上级职位可以看到下级职位的处理人是谁 下级职位不能看到上级职位的处理人是谁 取意见1-5
     * 
     * @param pid
     * @param opinionfield
     * @return
     */
    public String getOpinions_1(String pid, String itemid, String userid, String opinionfield) {
	String temp = "";
	String strsql = "select * from t_workitem where pid=" + pid + " and opinionfield='" + opinionfield
		+ "' and len(opinion)>0 and itemid1<>'" + itemid + "' and user1<>'" + userid + "' order by id";
	List<T_Workitem> items = this.find(strsql);
	if (items != null && items.size() != 0) {
	    for (int i = 0; i < items.size(); i++) {
		String user1 = items.get(i).getStr("user1") == null ? "" : items.get(i).getStr("user1");
		String opinion = items.get(i).getStr("opinion") == null ? "" : items.get(i).getStr("opinion");
		String opiniontime = items.get(i).getStr("opiniontime") == null ? ""
			: items.get(i).getStr("opiniontime");
		String username = T_User.getUserNameById(user1);
		// 得到环节处理人的职位序号
		// Integer user1_xh = T_Position.dao.getPositionPid(user1);
		// 得到登陆者的职位序号
		// Integer login_xh = T_Position.dao.getPositionPid(userid);
		// 如果登陆者的职位高于环节处理人的级别，则显示环节处理人姓名
		// if (login_xh >= user1_xh) {
		temp += opinion + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + username + "&nbsp;&nbsp;["
			+ opiniontime + "]<br>";
		// } else {
		// 否则显示环节处理人的职位
		// String position_name = T_Position.dao.getPosition(user1);
		// temp += opinion + "<br>&nbsp;&nbsp;&nbsp;&nbsp;--" +
		// position_name + " " + opiniontime + "<br>";
		// }
	    }
	}
	return temp;
    }

    /** 读取流转记录 */
    public List<T_Workitem> getWorkitem(String pid) {
	String strsql = "select * from t_workitem where pid=" + pid + " order by id";
	return this.find(strsql);
    }

    /** 读取第一个流转记录 */
    public T_Workitem getFirstWorkitem(int pid) {
	String strsql = "select * from t_workitem where pid=" + pid + " order by id";
	return this.findFirst(strsql);
    }

    /** 读取上一个流转记录 */
    public T_Workitem getLastWorkitem(int pid, int itemid) {
	String strsql = "select * from t_workitem where (operation='FaSong' or operation = 'FaSongdoc') and pid=" + pid + " and itemid2='" + itemid
		+ "' order by id desc";
	T_Workitem ti = this.findFirst(strsql);
	if (ti == null) {
	    strsql = "select * from t_workitem where (operation='FaSong' or operation = 'FaSongdoc') and pid=" + pid
		    + " and itemid2 is not null order by id desc";
	    ti = this.findFirst(strsql);
	}
	return ti;
    }

    /** 读取当前流转记录 */
    public T_Workitem getCurrentWorkitem(int pid, int itemid) {
	String strsql = "select * from t_workitem where pid=" + pid + " and itemid1='" + itemid + "' order by id desc";
	return this.findFirst(strsql);
    }

    /** 读取一个流转记录 */
    public T_Workitem getWorkitem(int pid, int itemid, String userid) {
	String strsql = "select * from t_workitem where pid=" + pid + " and itemid1='" + itemid + "' and user1='"
		+ userid + "' order by id desc";
	return this.findFirst(strsql);
    }

    /** 读取一个流转记录 (多选一处理时调用） */
    public T_Workitem getWorkitem(int pid, int itemid) {
	String strsql = "select * from t_workitem where pid=" + pid + " and itemid1='" + itemid + "'"
		+ " order by id desc";
	return this.findFirst(strsql);
    }

    /** 读取同一意见域的所有意见 */
    public String getOpinions(int pid, String opinionfield) {
	String temp = "";
	String strsql = "select * from t_workitem  a left join t_role b on a.user1=b.id where a.pid=" + pid
		+ " and a.opinionfield='" + opinionfield
		+ "' and a.opinion is not null and a.opinion <> '' order by b.xh";
	List<T_Workitem> items = this.find(strsql);
	if (items != null && items.size() != 0) {
	    for (int i = 0; i < items.size(); i++) {
		if (!"".equals(items.get(i).getStr("opinion"))) {
		    String user1 = items.get(i).getStr("user1");
		    int islast = 0;
		    for (int j = i + 1; j < items.size(); j++) {
			String user2 = items.get(j).getStr("user1");
			if (user2.equals(user1)) {
			    islast = 1;
			    break;
			}
		    }
		    if (islast == 1) {
			continue;
		    }
		    String opinion = items.get(i).getStr("opinion");
		    String opiniontime = items.get(i).getStr("opiniontime");
		    if (opiniontime.length() > 0) {
			opiniontime = opiniontime.substring(0, 16);
		    }
		    String username = T_User.getUserNameById(user1);
		    temp += opinion + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + username
			    + "&nbsp;&nbsp;[" + opiniontime + "]<br>";
		    // temp += username + "&nbsp; ";
		}
	    }
	}
	return temp;
    }

    /** 读取同一意见域的所有意见 ,替换图片签名 */
    public String getOpinions1(int pid, String opinionfield) {
	String temp = "";
	String strsql = "select * from t_workitem a left join t_role b on a.user1=b.id where a.pid=" + pid
		+ " and a.opinionfield='" + opinionfield + "' and a.opinion <> '' order by b.xh";
	List<T_Workitem> items = this.find(strsql);
	if (items != null && items.size() != 0) {
	    for (int i = 0; i < items.size(); i++) {
		String user1 = items.get(i).getStr("user1") == null ? "" : items.get(i).getStr("user1");
		String itemid1=items.get(i).getStr("itemid1");
		int islast = 0;
		for (int j = i + 1; j < items.size(); j++) {
			String user2 = items.get(j).getStr("user1");
			String itemid2=items.get(j).getStr("itemid1"); 
			if (user2.equals(user1) && itemid1.equals(itemid2)) {
			    islast = 1;
			    break;
			}
		    }
		    if (islast == 1) {
			continue;
		    }
		String opinion = StrKit.isBlank(items.get(i).getStr("opinion")) ? "" : items.get(i).getStr("opinion");
		String opiniontime = StrKit.isBlank(items.get(i).getStr("opiniontime")) ? ""
			: items.get(i).getStr("opiniontime");
		if (opiniontime.length() > 0) {
		    opiniontime = opiniontime.substring(0, 16);
		}
		String username = T_User.getUserNameById(user1);
		
		String did=T_User.dao.findFirst("select * from t_user where id="+user1).getStr("dlid");
		String signfile = Constant.PATH_SIGNATURE +"/"+ did + ".png";
		File file = new File(signPath  +"/"+ did + ".png");
		if (file.exists()) {
		    temp += opinion + "<br><span style='padding-left:50px'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='"
			    + signfile.substring(1) + "' width='200' height='70'>&nbsp;&nbsp;[" + opiniontime
			    + "]</span><br><br>";
		} else {
		     temp += opinion +
		     "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
		     username + "&nbsp;&nbsp;[" + opiniontime + "]<br><br>";
		   /* temp += username + "&nbsp;&nbsp;";*/
		}
	    }
	}
	return temp;
    }

    /** 读取同一意见域的所有意见，横向放置 */
    public String getOpinions2(int pid, String opinionfield) {
	String temp = "";
	String strsql = "select * from t_workitem a left join t_role b on a.user1=b.id where a.pid=" + pid
		+ " and a.opinionfield='" + opinionfield
		+ "' and a.opinion is not null and a.endtime <> '' and a.opiniontime <> '' order by b.xh";
	String user1 = "";
	String opinion = "";
	String opiniontime = "";
	String username = "";
	List<T_Workitem> items = this.find(strsql);
	if (items != null && items.size() != 0) {
	    int num = items.size();
	    int rows = 0;
	    if (num % 5 == 0) {
		rows = num / 5;
	    } else {
		rows = num / 5 + 1;
	    }
	    T_Workitem wkit = null;
	    temp += "<table style=\"width: 100%\">";
	    int i = 0;
	    for (int m = 0; m < rows; m++) {
		temp += "<tr>";
		for (int n = 0; n < 5; n++) {
		    i = m * 5 + n;
		    if (i < num) {
			wkit = items.get(i);
			user1 = wkit.getStr("user1") == null ? "" : wkit.getStr("user1");
			opinion = wkit.getStr("opinion") == null ? "" : wkit.getStr("opinion");
			opiniontime = wkit.getStr("opiniontime") == null ? "" : wkit.getStr("endtime");
			username = T_User.getUserNameById(user1);
			String tmpopiniontime = "";
			if (opiniontime.length() > 0) {
			    tmpopiniontime = opiniontime.substring(0, 10);
			}
			temp += "<td width=\"20%\" style=\"border: 0px\">" + opinion
				+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + username
				+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;" + tmpopiniontime + "</td>";
		    } else {
			temp += "<td width=\"20%\" style=\"border: 0px\">&nbsp;</td>";
		    }
		}
		temp += "</tr>";
	    }
	    temp += "</table>";
	}
	return temp;
    }

    /** 读取同一意见域的所有意见，除去当前环节，当前人的意见 */
    public String getOpinions(int pid, int itemid, String userid, String opinionfield) {
	String temp = "";
	String strsql = "select * from t_workitem a left join t_role b on a.user1=b.id where a.pid=" + pid
		+ " and a.opinionfield='" + opinionfield + "' and a.opinion is not null and (a.itemid1<>'" + itemid
		+ "' or a.user1<>'" + userid + "') order by b.xh";
	List<T_Workitem> items = this.find(strsql);
	if (items != null && items.size() != 0) {
	    for (int i = 0; i < items.size(); i++) {
		String user1 = StrKit.isBlank(items.get(i).getStr("user1")) ? "" : items.get(i).getStr("user1");
		int islast = 0;
		    for (int j = i + 1; j < items.size(); j++) {
			String user2 = items.get(j).getStr("user1");
			if (user2.equals(user1)) {
			    islast = 1;
			    break;
			}
		    }
		    if (islast == 1) {
			continue;
		    }
		String opinion = StrKit.isBlank(items.get(i).getStr("opinion")) ? "" : items.get(i).getStr("opinion");
		String opiniontime = StrKit.isBlank(items.get(i).getStr("opiniontime")) ? ""
			: items.get(i).getStr("opiniontime");
		if (opiniontime.length() > 0) {
		    opiniontime = opiniontime.substring(0, 16);
		}
		String username = T_User.getUserNameById(user1);
		String did=T_User.dao.findFirst("select * from t_user where id="+user1).getStr("dlid");
		/*temp += "&nbsp;&nbsp;" + opinion + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + username
			+ "&nbsp;&nbsp;[" + opiniontime + "]<br>";*/
		
		//更换签名为图片签名
		String signfile = Constant.PATH_SIGNATURE +"/" + did + ".png";
		File file = new File(signPath +"/" + did + ".png");
		if (file.exists()) {
		    temp += "&nbsp;"+ opinion + "<br><span style='padding-left:200px'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='"
			    + signfile.substring(1) + "' width='200' height='70'>&nbsp;&nbsp;[" + opiniontime
			    + "]</span><br><br>";
		} else {
		    temp += "&nbsp;" + opinion + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + username
				+ "&nbsp;&nbsp;[" + opiniontime + "]<br><br>";
		}
		
		
	    }
	}
	return temp;
    }

    public List<T_Workitem> getOpinion1 (int pid, String opinionfield){
	String strsql = "select a.*,c.name as leader from t_workitem a left join t_role b on a.user1=b.id left join t_user c on c.id=a.user1 where a.pid=" + pid
		+ " and a.opinionfield='" + opinionfield + "' and a.opinion <> '' order by b.xh";
	List<T_Workitem> items = this.find(strsql);
	if (items != null && items.size() != 0) {
	    for (int i = 0; i < items.size(); i++) {
		String user1 = items.get(i).getStr("user1") == null ? "" : items.get(i).getStr("user1");
		
		/*String opiniontime = items.get(i).getStr("opiniontime") == null ? ""
			: items.get(i).getStr("opiniontime");
		if (opiniontime.length() > 0) {
		    opiniontime = opiniontime.substring(0, 16);
		}*/
		String did=T_User.dao.findFirst("select * from t_user where id="+user1).getStr("dlid");
		String signfile = Constant.PATH_SIGNATURE +"/"+ did + ".png";
		File file = new File(signPath  +"/"+ did + ".png");
		if (file.exists()) {
		    items.get(i).set("url",signfile.substring(1) );
		    
		}
	    }
	}
	return items;
    }
    
    public String findUnderTake(int pid){//获取所有被指定承办的人
	String doman = "";
	String sql = "select user2 from t_workitem where itemid2 = 107 and pid = "+pid ;//获取初始承办时的人
	T_Workitem wkit = dao.findFirst(sql);
	if(null != wkit){
	    doman = wkit.getStr("user2");
	    String sql1 = "select user2 from t_workitem where itemid1 = 107  and pid = "+pid ;
	    List<T_Workitem> wkit1 = dao.find(sql1);//获取科长指定的人
	    for(int i=0;i<wkit1.size();i++){
		if (null != wkit1.get(i).getStr("user2")){
		    doman +=";"+wkit1.get(i).getStr("user2");
		}
	    }
	}
	
	return doman ;
    }
    
    
}
