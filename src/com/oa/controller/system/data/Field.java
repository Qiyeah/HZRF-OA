package com.oa.controller.system.data;

import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Field;
import com.zhilian.model.T_Table;

@RouteBind(path = "Main/Field", viewPath = "System/Data/Field")
public class Field extends BaseAssociationController {

	String menuId = "Field";

	public void main() {
		String select = "select *";
		String qtid = "";
		String qname = "";
		String qkeyword = "";
		String orderField = "name";
		String orderDirection = "";
		int pageSize = Constant.PAGE_SIZE;
		int pageNumber = 1;
		String sqlExceptSelect = " from t_field where 1=1";
		try {
			if (null != getPara("qtid") && !"".equals(getPara("qtid").trim())) {
				qtid = getPara("qtid");
				sqlExceptSelect += " and tid=" + qtid;
				setAttr("qtid", qtid);
			}
			if (null != getPara("qname") && !"".equals(getPara("qname").trim())) {
				qname = getPara("qname");
				sqlExceptSelect += " and name like '%" + qname + "%'";
				setAttr("qname", qname);
			}
			if (null != getPara("qkeyword") && !"".equals(getPara("qkeyword").trim())) {
				qname = getPara("qkeyword");
				sqlExceptSelect += " and keyword like '%" + qkeyword + "%'";
				setAttr("qkeyword", qkeyword);
			}
			if (null != getPara("orderField") && !"".equals(getPara("orderField").trim())) {
				orderField = getPara("orderField");
				setAttr("orderField", orderField);
			}
			if (null != getPara("orderDirection") && !"".equals(getPara("orderDirection").trim())) {
				orderDirection = getPara("orderDirection");
				setAttr("orderDirection", orderDirection);
			}
			if (null != getPara("pageSize") && !"".equals(getPara("pageSize").trim())) {
				pageSize = getParaToInt("pageSize");
			}
			if (pageSize <= 0) {
				throw new Exception();
			}
			if (null != getPara("pageCurrent") && !"".equals(getPara("pageCurrent").trim())) {
				pageNumber = getParaToInt("pageCurrent");
			}
			if (pageNumber <= 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
			return;
		}
		sqlExceptSelect += " order by " + orderField + " " + orderDirection;
		try {
			Page<T_Field> page = T_Field.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
			setAttr("page", page);
			setAttr("tableHm", T_Table.dao.hashMapById("tablename"));
			setAttr("tables", T_Table.dao.list());
			render("main.jsp");
		} catch (Exception e) {
			toErrorJson("服务器存在错误，数据读取失败！");
		}
	}

	public void addip() {
		setAttr("tables", T_Table.dao.list());
		render("add.jsp");
	}

	public void add() {
		T_Field model = getModel(T_Field.class);
		if (model.save()) {
			toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
		} else {
			toErrorJson(Constant.EXCEPTION);
		}
	}

	public void updateip() {
		Integer id = getParaToInt(0);
		T_Field model = T_Field.dao.findById(id);
		setAttr("tables", T_Table.dao.find("select * from t_table"));
		setAttr("model", model);
		render("update.jsp");
	}

	public void update() {
		T_Field model = getModel(T_Field.class);
		if (model.update()) {
			toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
		} else {
			toErrorJson(Constant.EXCEPTION);
		}
	}

	public void deletes() {
		String ids = getPara("ids");
		if (T_Field.dao.deletesByIds(ids)) {
			toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
		} else {
			toErrorJson(Constant.EXCEPTION);
		}
	}

	public void delete() {
		String id = getPara();
		if (T_Field.dao.deleteById(id)) {
			toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
		} else {
			T_Field.dao.deleteById(id);
		}
	}

	@Clear
	public void importTableip() {
		setAttr("tables", T_Table.dao.list());
		render("import.jsp");
	}

	// @Clear
	// public void importTable() {
	// Integer tid = getParaToInt("tid");
	// T_Table table = T_Table.dao.findById(tid);
	// String tablecode = table.getStr("tablecode");
	// String[] fields = null;
	// T_Field fieldObj = null;
	// if (tablecode.equals("t_sample")) {
	// T_Sample sample = T_Sample.dao.findFirst("select * from t_sample");
	// if (sample != null) {
	// fields = sample.getAttrNames();
	// }
	// } else if (tablecode.equals("t_commission")) {
	// T_Commission report =
	// T_Commission.dao.findFirst("select * from t_commission");
	// if (report != null) {
	// fields = report.getAttrNames();
	// }
	// }
	// if (fields != null) {
	// String sql = "select * from t_field where tid = ?";
	// List<T_Field> fieldList = T_Field.dao.find(sql, tid);
	// HashMap<String, T_Field> hm = T_Field.dao.toHashMap(fieldList, "name");
	// for (String field : fields) {
	// fieldObj = hm.get(field);
	// if (fieldObj == null) {
	// fieldObj = new T_Field();
	// fieldObj.set("tid", tid).set("name", field.toLowerCase());
	// fieldObj.save();
	// }
	// }
	// }
	// toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	// }

}
