package com.oa.model.system.workflow;

import java.util.List;

import com.zhilian.model.T_Department;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

public class T_Wtrans_Sp extends Model<T_Wtrans_Sp> {

	private static final long serialVersionUID = 1L;

	public static final T_Wtrans_Sp dao = new T_Wtrans_Sp();

	public int deleteOldById(int aid) {
		return Db.update("delete from t_wtrans_sp where afrom = ?", aid);
	}
	
	/**
	 * 读取下一环节IDs
	 * 
	 * @param afrom 本环节
	 * @param dept 部门id
	 * @param position 职位id
	 * @return String
	 */
	public String getNextSteps(String afrom, int dept,int position) {
		String tmpids = "";
		// 查询特定科室 AND 特定职位的特例流程
		String strsql = "select * from t_wtrans_sp where afrom='" + afrom + "' AND dept = '" + dept + "' AND positions = '" + position+ "'";
		List<T_Wtrans_Sp> wt = T_Wtrans_Sp.dao.find(strsql);
		// 获取数据不为空，则返回该特例流程
		if (wt != null && wt.size() != 0) {
			for (int i = 0; i < wt.size(); i++) {
				tmpids = tmpids + "," + wt.get(i).getInt("ato");
			}
		} else {
			// 以上获取数据失败，追加获取仅符合部门设置的数据
			strsql = "select * from t_wtrans_sp where afrom='" + afrom + "' AND dept = '" + dept + "' AND positions = '0'";
			List<T_Wtrans_Sp> wtnext = T_Wtrans_Sp.dao.find(strsql);
			if (wtnext != null && wtnext.size() != 0) {
				for (int j = 0; j < wtnext.size(); j++) {
					tmpids = tmpids + "," + wtnext.get(j).getInt("ato");
				}
			} else {
				//获取上级部门且符合职位的人，或者只符合职位的人
				T_Department department = T_Department.dao.findById(dept);
				strsql = "select * from t_wtrans_sp where afrom='" + afrom + "' AND dept = '"+department.getInt("pid")+"' AND positions = '" + position + "' ";
				List<T_Wtrans_Sp> wtlast = T_Wtrans_Sp.dao.find(strsql);
				if (wtlast != null && wtlast.size() != 0) {
					for (int k = 0; k < wtlast.size(); k++) {
						tmpids = tmpids + "," + wtlast.get(k).getInt("ato");
					}
				}
			}
		}
		if (tmpids.length() > 0)
			tmpids = tmpids.substring(1);
		return tmpids;
	}

}
