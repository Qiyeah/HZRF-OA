package com.oa.model.system.workflow;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class T_Wprocess extends Model<T_Wprocess> {

	private static final long serialVersionUID = 1L;

	public static final T_Wprocess dao = new T_Wprocess();

	public boolean deleteProcess() {
		T_Wactive.dao.deleteByProcessId(this.getInt("id"));
		return this.delete();
	}
	
	public String getTemplateId(String flow){
	    return dao.findFirst("select template from t_wprocess where flow='"+flow+"'").getStr("template");
	}

	public List<T_Wprocess> getAllSubProcess() {
		return this.find("select * from t_wprocess where type = 1");
	}

	public T_Wprocess getSubProcessByName(String name) {
		return this.findFirst("select  * from t_wprocess where name = '" + name + "'");
	}

	public T_Wprocess getProcessByFlow(String flow) {
		return this.findFirst("select  * from t_wprocess where flow = '" + flow + "'");
	}

}
