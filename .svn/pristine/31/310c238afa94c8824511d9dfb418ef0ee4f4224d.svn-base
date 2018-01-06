package com.oa.model.approve;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class T_Unit extends Model<T_Unit> {

    private static final long serialVersionUID = 1L;
    public static final T_Unit dao = new T_Unit();

    /** 获取所有部门列表 */
    public List<T_Unit> getAllDepts() {
	return dao.find("select * from t_unit where 1=1 and dbstate='0' and dlvl =1 order by id");
    }

    /** 得到根组织 */
    public List<T_Unit> getAllpdepts() {
	return dao.find("select * from t_unit where d_pid = 1 order by id ");
    }

    /** 得到当前组织 */
    public List<T_Unit> getdepts() {
	return dao.find("select * from t_unit where d_pid=? and dbstate='0' " + " order by id ", get("id"));
    }

    /** 查找单位 */
    public List<T_Unit> getUnit(String inputValue) {
	return dao.find("select * from t_unit where 1=1 and dbstate='0' and dlvl =2 and  fname like '%" + inputValue
		+ "%' order by id");
    }

    /** 查找 父单位 */
    public T_Unit getFname(Integer id) {
	String sql = "SELECT fname FROM t_unit WHERE id =" + id;
	return dao.findFirst(sql);

    }

    /**
     * 递归遍历父节点
     * 
     * @param eventualSet
     *            最终的父件货品编号Set
     * @param cBom
     *            子件Bom
     */
    @SuppressWarnings("unused")
    private String upToBom(Integer cid) {
	T_Unit fin = T_Unit.dao.findById(cid);

	if (fin != null) {
	    Integer dlvl = fin.get("dlvl");
	    if (dlvl == 1) {
		return fin.getStr("fname");
	    }
	    Integer d_pid = fin.get("d_pid");

	    upToBom(d_pid);
	}
	return null;
    }
}
