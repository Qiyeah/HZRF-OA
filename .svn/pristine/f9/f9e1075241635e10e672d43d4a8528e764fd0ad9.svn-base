package com.oa.controller.system.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Loginlog;

@RouteBind(path = "/Main/LoginLog", viewPath = "System/Log/LoginLog")
public class LoginLog extends BaseAssociationController {

	public void main() {
		int pageSize = Constant.PAGE_SIZE;
		int pageNumber = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String select = "select t.ip, u.dlid, u.name, t.dltime, t.loDate, t.dlms";
		String qname = "";
		String sdate = "";
		String edate = "";
		String sqlExceptSelect = "from t_loginlog t left join t_user u on t.u_id=u.id where 1=1 ";
		try {
			Date start = null, end = null;
			if (null != getPara("qname") && !"".equals(getPara("qname").trim())) {
				qname = getPara("qname");
				sqlExceptSelect += " and u.name like '%" + qname + "%'";
				setAttr("qname", qname);
			}
			if (null != getPara("sdate") && !"".equals(getPara("sdate").trim())) {
				start = format.parse(getPara("sdate").trim());
				sdate = getPara("sdate").trim();
				sqlExceptSelect +=" and t.dltime>='" + sdate + " 00:00:00'";
				setAttr("sdate", sdate);
			}
			if (null != getPara("edate") && !"".equals(getPara("edate").trim())) {
				end = format.parse(getPara("edate").trim());
				edate = getPara("edate").trim();
				sqlExceptSelect +=" and t.dltime<='" + edate + " 59:59:59'";
				setAttr("edate", edate);
			}
			if (null != start && null != end && start.after(end)) {
				throw new Exception();
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
			e.getMessage();
			toErrorJson(Constant.EXCEPTION);
			return;
		}
		sqlExceptSelect += " order by t.dltime desc";
		try {
			Page<T_Loginlog> page = T_Loginlog.dao.paginate(pageNumber, pageSize, select, sqlExceptSelect);
			setAttr("page", page);
			render("main.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			toErrorJson("服务器存在错误，数据读取失败！");
		}
	}

}
