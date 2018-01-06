package com.oa.model.system.workflow;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;

public class T_Leader_Dept extends Model<T_Leader_Dept> {

    private static final long serialVersionUID = 1L;
    public static T_Leader_Dept dao = new T_Leader_Dept();

    public static String getLeader(String dept) {
	String result = "";
	String sql = "SELECT * FROM t_leader_dept WHERE (','+deptIds+',') Like '%," + dept + ",%'";
	List<T_Leader_Dept> list = dao.find(sql);
	for (T_Leader_Dept leaderDept : list) {
	    result += "," + leaderDept.getInt("userId");
	}
	if (result.length() > 0) {
	    result = result.substring(1);
	}
	return result;
    }

    /**
     * 获取主要领导列表
     */
    public static List<T_User> getLeaderList() {
	String sql = "select * from t_user where pid in (select id from t_position where xh in (11,12)) and status=1 order by no";
	return T_User.dao.find(sql);
    }

    /**
     * 获取分管领导列表（包含非领导职务）
     */
    public static List<T_User> getAllLeaderList() {
	String sql = "select * from t_user where pid in (select id from t_position where xh in (11,12,13,14)) and status=1 order by no";
	return T_User.dao.find(sql);
    }

    /**
     * 获取非分配分管领导列表（包含非领导职务）
     */
    public static List<T_User> getNoLeaderList() {
	String sql = "select * from t_user where pid in (select id from t_position where xh in (11,12,13,14)) "
		+ "and id not in (select userId from t_leader_dept) and status=1 order by no";
	return T_User.dao.find(sql);
    }

    /**
     * 获取非分配部门列表
     */
    public static List<T_Department> getNoLeaderDeptList() {
	String usedDept = getUsedDept();
	if (StrKit.notBlank(usedDept)) {
	    String sql = "select * from t_department where id not in (" + usedDept + ") and dlvl=1 and status='1' order by no";
	    return T_Department.dao.find(sql);
	} else {
	    return T_Department.getBaseDepts();
	}

    }

    /**
     * 获取非分配部门列表（包含自己管辖部门）
     */
    public static List<T_Department> getNoLeaderDeptWithSelfList(String deptIds) {
	String usedDept = getUsedDept();
	if (StrKit.notBlank(usedDept)) {
	    String sql = "select * from t_department where (id not in (" + usedDept + ") or id in (" + deptIds
		    + ")) and dlvl=1 and status='1' order by no";
	    return T_Department.dao.find(sql);
	} else {
	    return null;
	}
    }

    public static String getUsedDept() {
	String ids = "";
	Set<String> depts = new HashSet<String>();
	List<T_Leader_Dept> list = dao.list();
	for (T_Leader_Dept dept : list) {
	    String deptIds = dept.getStr("deptIds");
	    if (StrKit.notBlank(deptIds)) {
		String[] deptss = deptIds.split(",");
		for (String str : deptss) {
		    depts.add(str);
		}
	    }
	}
	if (null != depts && depts.size() > 0) {
	    for (String dept : depts) {
		ids += "," + dept;
	    }
	    ids = ids.substring(1);
	}
	return ids;
    }

}
