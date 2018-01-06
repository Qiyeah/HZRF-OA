package com.oa.model.system.workflow;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

public class T_Wtrans extends Model<T_Wtrans> {

    private static final long serialVersionUID = 1L;

    public static final T_Wtrans dao = new T_Wtrans();

    public int deleteOldById(int aid) {
	return Db.update("delete from t_wtrans where afrom = ?", aid);
    }

    /** 读取下一环节数 */
    public int nextActives(int id) {
	String strsql = " select count(id) as count from t_wtrans where afrom=" + id;
	return Db.queryInt(strsql);

    }

    /** 读取下一环节ID */
    public String getNextStep(int id) {
	String strsql = "select * from t_wtrans where afrom=" + id;
	return T_Wtrans.dao.findFirst(strsql).getStr("ato");
    }

    /** 读取下一环节数，还需判断是否可以办结归档 ,如果为已签收则下一步能有办结归档，未签收下一步不能有办结归档,除传阅环节 */
    public int nextActives1(int itemid, String isreceive) {
	String strsql = null;

	if (isreceive.equals("1")) {
	    strsql = " select count(id) as count from t_wtrans where afrom=" + itemid
		    + " and ato in(select id from t_wactive where atype=3)";
	} else {
	    strsql = " select count(id) as count from t_wtrans where afrom=" + itemid
		    + " and ato not in(select id from t_wactive where atype=3)";
	}
	if (itemid == 26) {
	    strsql = " select count(id) as count from t_wtrans where afrom=" + itemid;
	}

	return Db.queryInt(strsql);
    }

    /** 读取下一环节ID ，还需判断是否可以办结归档 */
    public String getNextStep1(int id, String isreceive) {
	String strsql = null;
	if (isreceive.equals("1")) {
	    strsql = " select * from t_wtrans where afrom=" + id
		    + " and ato in(select id from t_wactive where atype=3)";
	} else {
	    strsql = " select * from t_wtrans where afrom=" + id
		    + " and ato not in(select id from t_wactive where atype=3)";
	}
	if (id == 26) {
	    strsql = " select * from t_wtrans where afrom=" + id;
	}
	return T_Wtrans.dao.findFirst(strsql).getStr("ato");
    }

    /** 读取下一环节信息 */
    public List<T_Wtrans> getNextSteps(int id) {
	String strsql = "select * from t_wtrans where afrom=" + id;
	return T_Wtrans.dao.find(strsql);
    }

    /** 读取下一环节名称 */
    public List<T_Wtrans> getNextStepNames(String id) {
	String strsql = "select * from t_wtrans where afrom='" + id + "'";
	String ato;
	List<T_Wtrans> wt = T_Wtrans.dao.find(strsql);
	if (wt != null && wt.size() != 0) {
	    for (int i = 0; i < wt.size(); i++) {
		wt.get(i).set("id", wt.get(i).get("ato"));
		ato = T_Wactive.dao.getStepName(wt.get(i).get("ato"));
		wt.get(i).set("ato", ato);
	    }
	}
	return wt;
    }

    /** 读取下一环节信息（判断承办环节）*/
    public List<T_Wtrans> getNextSteps1(String id, String iscanreceive, String isreceive) {
	String strsql = null;
	if (StrKit.isBlank(iscanreceive) || StrKit.isBlank(isreceive) ? false : (isreceive.equals("1"))) {
	    strsql = " select * from t_wtrans where afrom=" + id;
	} else {
	    strsql = " select * from t_wtrans where afrom=" + id
		    + " and ato not in(select id from t_wactive where atype=3)";
	}
	String ato;
	List<T_Wtrans> wt = T_Wtrans.dao.find(strsql);
	if (wt != null && wt.size() != 0) {
	    for (int i = 0; i < wt.size(); i++) {
		wt.get(i).set("id", wt.get(i).get("ato"));
		ato = T_Wactive.dao.getStepName(wt.get(i).get("ato"));
		wt.get(i).set("ato", ato);
	    }
	}
	return wt;
    }

}
