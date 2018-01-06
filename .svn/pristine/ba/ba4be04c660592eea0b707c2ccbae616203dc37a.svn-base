package com.oa.controller.system.data;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Table;

@RouteBind(path = "Main/Table", viewPath = "System/Data/Table")
public class Table extends BaseAssociationController {

	String menuId = "Table";

	public void main() {
		String select = "select id, tablecode, tablename, status";
		String qname = "";
		String qstatus = "";
		String orderField = "tablecode";
		String orderDirection = "";
		int pageSize = Constant.PAGE_SIZE;
		int pageNumber = 1;
		String sqlExceptSelect = "from t_table where 1=1";
		try {
			if (null != getPara("qname") && !"".equals(getPara("qname").trim())) {
				qname = getPara("qname");
				sqlExceptSelect += " and tablename like '%" + qname + "%'";
				setAttr("qname", qname);
			}
			if (null != getPara("qstatus") && !"".equals(getPara("qstatus").trim())) {
				qstatus = getPara("qstatus");
				sqlExceptSelect += " and status='" + qstatus + "'";
				setAttr("qstatus", qstatus);
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
			Page<T_Table> page = T_Table.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
			setAttr("page", page);
			render("main.jsp");
		} catch (Exception e) {
			toBJUIJson(300, "服务器存在错误，数据读取失败！", "", "", "", "", "");
		}
	}

	public void addip() {
		render("add.jsp");
	}

	public void add() {
		try {
			T_Table model = getModel(T_Table.class);
			if (model.save()) {
				toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
			} else {
				toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
			}
		} catch (Exception e) {
			toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
		}
	}

	public void updateip() {
		Integer id = getParaToInt(0);
		T_Table model = T_Table.dao.findById(id);
		setAttr("model", model);
		render("update.jsp");
	}

	public void update() {
		try {
			T_Table model = getModel(T_Table.class);
			if (model.update()) {
				toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
			} else {
				toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
			}
		} catch (Exception e) {
			toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
		}
	}

	public void deletes() {
		String ids = getPara("ids");
		try {
			Db.update("delete from t_table where id in (" + ids + ")");
			toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
		} catch (Exception e) {
			toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
		}
	}

	public void delete() {
		String id = getPara(0);
		try {
			T_Table.dao.deleteById(id);
			toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
		} catch (Exception e) {
			toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
		}
	}

}
