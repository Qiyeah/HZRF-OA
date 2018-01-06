package com.oa.controller.system.settings;

import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Schedule;

@RouteBind(path = "Main/Schedule", viewPath = "System/Settings/Schedule")
public class Schedule extends BaseAssociationController {

	public static String muneId = "schedule";

	public void main() {
		String select = "select * ";
		String qname = "";
		String orderField = "xh";
		String orderDirection = "";
		int pageSize = Constant.PAGE_SIZE;
		int pageNumber = 1;
		String sqlExceptSelect = " from t_schedule where 1=1";
		try {
			if (null != getPara("qname") && !"".equals(getPara("qname").trim())) {
				qname = getPara("qname");
				sqlExceptSelect += " and name like '%" + qname + "%'";
				setAttr("qname", qname);
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
			Page<T_Schedule> page = T_Schedule.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
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
			T_Schedule model = this.getModel(T_Schedule.class);
			if (model.save()) {
				toBJUIJson(200, Constant.SUCCESS, muneId, "", "", "true", "");
			} else {
				toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
			}
		} catch (Exception e) {
			toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
		}
	}

	public void updateip() {
		int id = getParaToInt(0);
		T_Schedule model = T_Schedule.dao.findById(id);
		setAttr("model", model);
		render("update.jsp");
	}

	public void update() {
		try {
			T_Schedule model = this.getModel(T_Schedule.class);
			if (model.update()) {
				toBJUIJson(200, Constant.SUCCESS, muneId, "", "", "true", "");
			} else {
				toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
			}
		} catch (Exception e) {
			toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
		}
	}

	public void delete() {
		int id = getParaToInt(0);
		if (T_Schedule.dao.deleteById(id)) {
			toBJUIJson(200, Constant.SUCCESS, muneId, "", "", "", "");
		} else {
			toBJUIJson(300, Constant.EXCEPTION, "", "", "", "", "");
		}
	}

}
