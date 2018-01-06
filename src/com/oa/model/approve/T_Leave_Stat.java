package com.oa.model.approve;

import com.jfinal.plugin.activerecord.Model;

public class T_Leave_Stat extends Model<T_Leave_Stat> {

    private static final long serialVersionUID = 1L;
    public static final T_Leave_Stat dao = new T_Leave_Stat();

    public T_Leave_Stat findByUidandYear(int u_id, String year) {
	T_Leave_Stat temp = dao.findFirst("select * from t_leave_stat where u_id=" + u_id + " and year='" + year + "'");
	return temp;
    }

    public void statLeave(int u_id, int d_id, String type, Double days, String year) {
	T_Leave_Stat temp = dao.findFirst("select * from t_leave_stat where u_id=" + u_id + " and year='" + year + "'");
	if (temp == null) {
	    temp = new T_Leave_Stat();
	    temp.set("u_id", u_id);
	    temp.set("d_id", d_id);
	    temp.set("year", year).set("nxj", 0).set("jhj", 0).set("jsj", 0).set("tqj", 0).set("bij", 0).set("sij", 0);
	    switch (type) {
	    case "年休假":
		temp.set("nxj", days);
		break;
	    case "事假":
		temp.set("sij", days);
		break;
	    case "病假":
		temp.set("bij", days);
		break;
	    case "婚丧假":
		temp.set("jhj", days);
		break;
	    case "产假":
		temp.set("jsj", days);
		break;
	    case "探亲假":
		temp.set("tqj", days);
		break;
	    default:
		break;
	    }
	    temp.set("days", days).save();
	} else {
	    switch (type) {
	    case "年休假":
		temp.set("nxj", temp.getDouble("nxj") + days);
		break;
	    case "事假":
		temp.set("sij", temp.getDouble("sij") + days);
		break;
	    case "病假":
		temp.set("bij", temp.getDouble("bij") + days);
		break;
	    case "婚丧假":
		temp.set("jhj", temp.getDouble("jhj") + days);
		break;
	    case "产假":
		temp.set("jsj", temp.getDouble("jsj") + days);
		break;
	    case "探亲假":
		temp.set("tqj", temp.getDouble("tqj") + days);
		break;
	    default:
		break;
	    }
	    temp.set("days", temp.getInt("days") + days).update();
	}
    }

}