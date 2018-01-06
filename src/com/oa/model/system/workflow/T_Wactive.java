package com.oa.model.system.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.oa.model.common.T_Commessage;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_User;

public class T_Wactive extends Model<T_Wactive> {

    private static final long serialVersionUID = 1L;

    public static final T_Wactive dao = new T_Wactive();

    public int deleteByProcessId(int pid) {
	return Db.update("delete from t_wactive where wid =  ?", pid);
    }

    public List<T_Wactive> findByProcessId(int pid) {
	return this.find("select * from t_wactive where wid = ? order by num", pid);
    }

    public HashMap<String, String> getActiveHm() {
	HashMap<String, String> hm = null;
	List<T_Wactive> actives = T_Wactive.dao.find("select * from t_wactive");
	if (actives.size() > 0) {
	    hm = new HashMap<String, String>();
	    for (T_Wactive temp : actives) {
		hm.put(String.valueOf(temp.getInt("id")), temp.getStr("name"));
	    }
	}
	return hm;
    }

    // 该流程共有几个步骤
    public int findStepCountByProcessId(Object pid) {
	T_Wactive o = this.findFirst("select count(id) as count from t_wactive where wid = ?", pid);
	return o != null ? o.getInt("count") : 0;
    }

    public List<T_Wactive> findByName(String name) {
	return this.find("select top(1) * from t_wactive where name=?", name);
    }

    public List<T_Wactive> findAllExceptMe(int pid, Object mid) {
	return this.find("select * from t_wactive where wid = ? and id != ?", pid, mid);
    }

    public int getAtype(int pid) {
	List<T_Wactive> begin = this.find("select atype from t_wactive where atype='1' and wid = ?", pid);
	List<T_Wactive> end = this.find("select atype from t_wactive where atype='3' and wid = ?", pid);
	if (begin.size() != 0 && end.size() != 0) {
	    return 3;
	} else if (begin.size() != 0) {
	    return 1;
	} else if (end.size() != 0) {
	    return 2;
	}
	return 0;
    }

    // ** 读取下一环节数 */
    public int nextActives(int id) {
	String strsql = " select count(1) as count from t_wtrans where afrom='" + id + "'";
	T_Wtrans tw = T_Wtrans.dao.findFirst(strsql);
	Integer count = tw.getInt("count");
	return count;
	// return tw.getInt("count");
    }

    /** 读取下一环节ID */
    public String getNextStep(int id) {
	String strsql = "select * from t_wtrans where afrom='" + id + "'";
	return T_Wtrans.dao.findFirst(strsql).getStr("ato");
    }

    /**
     * 读取环节名称
     * 
     * @param id
     * @return String
     */
    public String getStepName(Object id) {
	String strsql = "select * from t_wactive where id='" + id + "'";
	return T_Wactive.dao.findFirst(strsql).getStr("name");
    }

    /**
     * 读取下一环节IDs
     * 
     * @param id
     * @return String
     */
    public String getNextSteps(String id) {
	String tmpids = "";
	String strsql = "select * from t_wtrans where afrom='" + id + "'";
	List<T_Wtrans> wt = T_Wtrans.dao.find(strsql);
	if (wt != null && wt.size() != 0) {
	    for (int i = 0; i < wt.size(); i++) {
		tmpids = tmpids + "," + wt.get(i).getStr("ato");
	    }
	}
	if (tmpids.length() > 0)
	    tmpids = tmpids.substring(1);
	return tmpids;
    }

    /**
     * 读取下一环节处理人
     * 
     * @param cond
     * @return
     */
    public ArrayList<HashMap<String, Object>> getTodomans(String cond) {
	ArrayList<HashMap<String, Object>> mans = new ArrayList<HashMap<String, Object>>();
	String strsql = "";
	String tmpstr[] = cond.split("~");
	String starter = tmpstr[0];
	String curdept = tmpstr[1];
	String seldept = tmpstr[2];
	String startdept = tmpstr[3];
	if (this.getStr("atype").equals("1")) {
	    strsql = "select id,name,d_id,pid from t_user where id=" + starter + " and status=1 order by d_id,no";
	} else {
	    String users = this.getStr("user1");
	    switch (Integer.parseInt(this.getStr("ptype"))) {
	    case 1:
	    case 5:
		if (users.equals("0")) {
		    strsql = "select id,name,d_id,pid from t_user where status=1 order by d_id,no";
		} else {
		    if (users.equals("leader")) {
			users = T_Leader_Dept.getLeader(curdept);
			strsql = "select id,name,d_id,pid from t_user where id in (" + users + ")";
		    } else {
			if (users.equals("curdept")) {
			    users = curdept;
			} else if (users.equals("updept")) {
			    users = T_Department.getUpDept(curdept);
			} else if (users.equals("selectdept")) {
			    users = seldept;
			} else if (users.equals("startdept")) {
			    users = startdept;
			}
			strsql = "select id,name,d_id,pid from t_user where d_id=" + users
				+ " and status=1 order by d_id,no";
		    }
		}
		break;
	    case 2:
		String tmpstr1[] = users.split("#");
		if (tmpstr1[0].equals("0")) {
		    strsql = "select id,name,d_id,pid from t_user where pid in (" + tmpstr1[1]
			    + ") and status=1 order by d_id,no";
		} else {
		    if (tmpstr1[0].equals("curdept")) {
			tmpstr1[0] = curdept;
		    } else if (tmpstr1[0].equals("updept")) {
			tmpstr1[0] = T_Department.getUpDept(curdept);
		    } else if (tmpstr1[0].equals("selectdept")) {
			tmpstr1[0] = seldept;
		    } else if (tmpstr1[0].equals("startdept")) {
			tmpstr1[0] = startdept;
		    }
		    strsql = "select id,name,d_id,pid from t_user where d_id='" + tmpstr1[0] + "' and pid in ("
			    + tmpstr1[1] + ") and status=1 order by d_id,no";
		}
		break;
	    case 3:
		strsql = "select id,name,d_id,pid from t_user where status=1 and id in (" + users
			+ ") order by d_id,no";
		break;
	    case 4:
		strsql = "select id,name,d_id,pid from t_user where status=1 and id=" + starter;
		break;
	    }
	}
	List<T_User> us = T_User.dao.find(strsql);
	if (us != null && us.size() != 0) {
	    for (int i = 0; i < us.size(); i++) {
		HashMap<String, Object> man = new HashMap<String, Object>();
		man.put("id", us.get(i).getInt("id"));
		man.put("name", us.get(i).getStr("name"));
		man.put("d_id", us.get(i).getInt("d_id"));
		man.put("pid", us.get(i).getInt("pid"));
		mans.add(man);
	    }
	}
	return mans;
    }

    public ArrayList<HashMap<String, Object>> getTodomans(String cond, String activename, String pid) {
	ArrayList<HashMap<String, Object>> mans = new ArrayList<HashMap<String, Object>>();
	String strsql = "";
	String tmpstr[] = cond.split("~");
	String starter = tmpstr[0];
	String curdept = tmpstr[1];
	String seldept = tmpstr[2];
	String startdept = tmpstr[3];
	if (this.getStr("atype").equals("1")) {
	    strsql = "select id,name,d_id,pid from t_user where id=" + starter + " and status=1 order by d_id,no";
	} else {
	    String users = this.getStr("user1");
	    switch (Integer.parseInt(this.getStr("ptype"))) {
	    case 1:
	    case 5:
		if (users.equals("0")) {
		    strsql = "select id,name,d_id,pid from t_user where status=1 order by d_id,no";
		} else {
		    if (users.equals("leader")) {
			users = T_Leader_Dept.getLeader(curdept);
			strsql = "select id,name,d_id,pid from t_user where id in (" + users + ")";
		    } else {
			if (users.equals("curdept")) {
			    users = curdept;
			} else if (users.equals("updept")) {
			    users = T_Department.getUpDept(curdept);
			} else if (users.equals("selectdept")) {
			    users = seldept;
			} else if (users.equals("startdept")) {
			    users = startdept;
			}
			if (activename.equals("传阅")) {
			    strsql = "select id,name,d_id,pid from t_user where d_id=" + users + " and id not in("
				    + T_Commessage.dao.getOtherUsers(pid) + ") and status=1 order by d_id,no";
			} else {
			    strsql = "select id,name,d_id,pid from t_user where d_id=" + users
				    + " and status=1 order by d_id,no";
			}
		    }
		}
		break;
	    case 2:
		String tmpstr1[] = users.split("#");
		if (tmpstr1[0].equals("0")) {
		    if (activename.equals("传阅")) {
			strsql = "select id,name,d_id,pid from t_user where pid in (" + tmpstr1[1] + ") and id not in ("
				+ T_Commessage.dao.getOtherUsers(pid) + ") and status=1 order by d_id,no";
		    } else {
			strsql = "select id,name,d_id,pid from t_user where pid in (" + tmpstr1[1]
				+ ") and status=1 order by d_id,no";
		    }
		} else {
		    if (tmpstr1[0].equals("curdept")) {
			tmpstr1[0] = curdept;
		    } else if (tmpstr1[0].equals("updept")) {
			tmpstr1[0] = T_Department.getUpDept(curdept);
		    } else if (tmpstr1[0].equals("selectdept")) {
			tmpstr1[0] = seldept;
		    } else if (tmpstr1[0].equals("startdept")) {
			tmpstr1[0] = startdept;
		    }
		    if (activename.equals("传阅")) {
			strsql = "select id,name,d_id,pid from t_user where d_id='" + tmpstr1[0] + "' and pid in ("
				+ tmpstr1[1] + ") and id not in (" + T_Commessage.dao.getOtherUsers(pid)
				+ ") and status=1 order by d_id,no";
		    } else {
			strsql = "select id,name,d_id,pid from t_user where d_id='" + tmpstr1[0] + "' and pid in ("
				+ tmpstr1[1] + ") and status=1 order by d_id,no";
		    }
		}
		break;
	    case 3:
		strsql = "select id,name,d_id,pid from t_user where id in (" + users + ") order by d_id,no";
		break;
	    case 4:
		strsql = "select id,name,d_id,pid from t_user where id=" + starter;
		break;
	    }
	}
	List<T_User> us = T_User.dao.find(strsql);
	if (us != null && us.size() != 0) {
	    for (int i = 0; i < us.size(); i++) {
		HashMap<String, Object> man = new HashMap<String, Object>();
		man.put("id", us.get(i).getInt("id"));
		man.put("name", us.get(i).getStr("name"));
		man.put("d_id", us.get(i).getInt("d_id"));
		man.put("pid", us.get(i).getInt("pid"));
		mans.add(man);
	    }
	}
	return mans;
    }

    /**
     * 企业提交读取下一环节处理人
     * 
     * @param cond
     * @return
     */
    public ArrayList<HashMap<String, Object>> getTodomans1(String cond) {
	ArrayList<HashMap<String, Object>> mans = new ArrayList<HashMap<String, Object>>();
	String strsql = "";
	String tmpstr[] = cond.split("~");
	String starter = tmpstr[0];
	String curdept = tmpstr[1];
	String seldept = tmpstr[2];
	String startdept = tmpstr[3];

	String users = this.getStr("user1");
	switch (Integer.parseInt(this.getStr("ptype"))) {
	case 1:
	case 5:
	    if (users.equals("0")) {
		strsql = "select u.id,u.name,u.d_id from t_user u left join t_department d on u.d_id=d.id where u.status=1 and d.area="
			+ startdept + " order by u.d_id";
	    } else {
		if (users.equals("leader")) {
		    users = T_Leader_Dept.getLeader(curdept);
		    strsql = "select id,name,d_id,pid from t_user where id in (" + users + ")";
		} else {
		    if (users.equals("curdept")) {
			users = curdept;
		    } else if (users.equals("updept")) {
			users = T_Department.getUpDept(curdept);
		    } else if (users.equals("selectdept")) {
			users = seldept;
		    } else if (users.equals("startdept")) {
			users = startdept;
		    }
		    strsql = "select u.id,u.name,u.d_id from t_user u left join t_department d on u.d_id=d.id where u.d_id="
			    + users + " and u.status=1 and d.area=" + startdept + " order by u.d_id";
		}
	    }
	    break;
	case 2:
	    String tmpstr1[] = users.split("#");
	    if (tmpstr1[0].equals("0")) {
		strsql = "select u.id,u.name,u.d_id from t_user u left join t_department d on u.d_id=d.id where u.pid in ("
			+ tmpstr1[1] + ") and u.status=1 and d.area=" + startdept + " order by u.d_id";
	    } else {
		if (tmpstr1[0].equals("curdept")) {
		    tmpstr1[0] = curdept;
		} else if (tmpstr1[0].equals("updept")) {
		    tmpstr1[0] = T_Department.getUpDept(curdept);
		} else if (tmpstr1[0].equals("selectdept")) {
		    tmpstr1[0] = seldept;
		} else if (tmpstr1[0].equals("startdept")) {
		    tmpstr1[0] = startdept;
		}
		strsql = "select u.id,u.name,u.d_id from t_user u left join t_department d on u.d_id=d.id where u.d_id='"
			+ tmpstr1[0] + "' and u.pid in (" + tmpstr1[1] + ") and d.area=" + startdept
			+ " and u.status=1 order by u.d_id";
	    }
	    break;
	case 3:
	    strsql = "select id,name,d_id from t_user where id in (" + users + ") order by d_id";
	    break;
	case 4:
	    strsql = "select id,name,d_id from t_user where id=" + starter;
	    break;
	}
	List<T_User> us = T_User.dao.find(strsql);
	if (us != null && us.size() != 0) {
	    for (int i = 0; i < us.size(); i++) {
		HashMap<String, Object> man = new HashMap<String, Object>();
		man.put("id", us.get(i).getInt("id"));
		man.put("name", us.get(i).getStr("name"));
		man.put("d_id", us.get(i).getInt("d_id"));
		mans.add(man);
	    }
	}
	return mans;
    }

    public List<T_Wactive> findInIds(String ids) {
	String sql = "select * from t_wactive where id in(" + ids + ") order by num";
	return T_Wactive.dao.find(sql);
    }

    public String getNamesByStepIds(String stepIds) {
	stepIds = stepIds.replace(";", ",");
	String names = "";
	List<T_Wactive> wactive = T_Wactive.dao
		.find("select * from t_wactive where id in(" + stepIds + ") order by num");
	for (int i = 0; i < wactive.size(); i++) {
	    names += wactive.get(i).getStr("name");
	}
	return names;
    }

    public boolean canSendSms(Object id) {
	boolean flag = false;
	T_Wactive active = dao.findById(id);
	if (null != active) {
	    String sendsms = active.getStr("sendsms");
	    if (sendsms.equals("1"))
		flag = true;
	}
	return flag;
    }

}
