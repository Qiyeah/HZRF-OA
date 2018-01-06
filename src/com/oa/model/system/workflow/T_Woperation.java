package com.oa.model.system.workflow;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class T_Woperation extends Model<T_Woperation> {

	private static final long serialVersionUID = 1L;

	public static final T_Woperation dao = new T_Woperation();

	public String getNameByCode(String code) {
		return this.findFirst("select name from t_woperation where code = '" + code + "'").get("name");
	}

	public HashMap<String, String> getOperationHm() {
		HashMap<String, String> hm = null;
		List<T_Woperation> operations = T_Woperation.dao.find("select * from t_woperation");
		if (operations.size() > 0) {
			hm = new HashMap<String, String>();
			for (T_Woperation temp : operations) {
				hm.put(temp.getStr("code"), temp.getStr("name"));
			}
		}
		return hm;
	}

}
