package com.oa.controller.work;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.oa.controller.BaseAssociationController;
import com.oa.model.common.T_Attachment;
import com.oa.model.work.T_MinistryMeeting;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.model.T_Common_Detail;
import com.zhilian.util.ArrayUtils;

/**
 * 其他工作会议
 * 
 */
@RouteBind(path = "Main/OtherMeeting", viewPath = "Work/Meeting/OtherMeeting")
public class OtherMeeting extends BaseAssociationController {

    String menuId = "OtherMeeting";

    /** 页面 */
    public void main() {
	int pageSize = Constant.PAGE_SIZE;
	int pageNumber = 1;
	String orderField = "id ";
	String orderDirection = "desc";
	String stitle = null, sdate = null, edate = null;
	Integer classification = null;
	String select = "select * ";
	String sqlExceptSelect = "from t_ministrymeeting  where menuId='" + menuId + "' ";

	try {
	    if (null != getPara("stitle") && !"".equals(getPara("stitle").trim())) {
		stitle = getPara("stitle").trim();
		sqlExceptSelect += " and stitle like '%" + stitle + "%'";
		setAttr("stitle", stitle);
	    }
	    if (null != getPara("classification") && !"".equals(getPara("classification").trim())) {
		classification = getParaToInt("classification");
		sqlExceptSelect += " and classification=" + classification;
		setAttr("classification", classification);
	    }
	    if (null != getPara("sdate") && !"".equals(getPara("sdate"))) {
		sdate = getPara("sdate");
		sqlExceptSelect += " and date>='" + sdate + "'";
		setAttr("sdate", sdate);
	    }
	    if (null != getPara("edate") && !"".equals(getPara("edate"))) {
		edate = getPara("edate");
		sqlExceptSelect += " and date<='" + edate + "'";
		setAttr("edate", edate);
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
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    return;
	}
	sqlExceptSelect += " order by " + orderField + " " + orderDirection;
	try {
	    Page<T_MinistryMeeting> page = T_MinistryMeeting.dao.paginate(pageNumber, pageSize, select,
		    sqlExceptSelect);
	    setAttr("page", page);
	    setAttr("classificationHM", T_Common_Detail.dao.hashMapById("name"));// 分类
	    List<T_Common_Detail> list = T_Common_Detail.getListByKey(menuId);
	    setAttr("list", list);
	    render("main.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	}
    }

    public void addip() {
	try {
	    List<T_Common_Detail> list = T_Common_Detail.getListByKey(menuId);
	    setAttr("list", list);
	    List<T_Attachment> fileList = new ArrayList<T_Attachment>();
	    setAttr("fileList", fileList);
	    render("add.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	}
    }

    public void add() {
	try {
	    T_MinistryMeeting temp = getModel(T_MinistryMeeting.class);
	    temp.set("hostid", getPara("chairman.userid"));// 主持人
	    temp.set("host", getPara("chairman.username"));

	    temp.set("attendeeid", getPara("attendee.userId"));// 参加人员
	    temp.set("attendee", getPara("attendee.userName"));

	    temp.set("attendid", getPara("attend.userId"));// 列席人员
	    temp.set("attend", getPara("attend.userName"));

	    temp.set("menuId", menuId);// 是什么会议

	    String[] fjids = getParaValues("fjid");
	    if (StrKit.notBlank(fjids)) {
		temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    }
	    if (temp.save()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	}
    }

    public void updateip() {
	try {
	    Integer id = getParaToInt();
	    T_MinistryMeeting temp = T_MinistryMeeting.dao.findById(id);
	    if (null == temp) {
		toErrorJson("您提交的数据有误，记录不存在！");
		return;
	    }
	    List<T_Common_Detail> list = T_Common_Detail.getListByKey(menuId);
	    setAttr("list", list);
	    List<T_Attachment> fileList = T_Attachment.dao.listInIds(temp.getStr("fjid"));
	    setAttr("fileList", fileList);
	    setAttr("model", temp);
	    render("update.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	}
    }

    public void update() {
	try {
	    T_MinistryMeeting temp = getModel(T_MinistryMeeting.class);

	    temp.set("hostid", getPara("chairman.userid"));// 主持人
	    temp.set("host", getPara("chairman.username"));

	    temp.set("attendeeid", getPara("attendee.userId"));// 参加人员
	    temp.set("attendee", getPara("attendee.userName"));

	    temp.set("attendid", getPara("attend.userId"));// 列席人员
	    temp.set("attend", getPara("attend.userName"));

	    String[] fjids = getParaValues("fjid");
	    if (StrKit.notBlank(fjids)) {
		temp.set("fjid", ArrayUtils.ArrayToString(fjids));
	    }

	    if (temp.update()) {
		toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "true", "");
	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	}
    }

    public void delete() {
	Integer id = getParaToInt();
	T_MinistryMeeting temp = T_MinistryMeeting.dao.findById(id);
	try {
	    if (null != temp) {
		if (temp.delete()) {
		    toBJUIJson(200, Constant.SUCCESS, menuId, "", "", "", "");
		}
	    } else {
		toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	    }
	} catch (Exception e) {
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	}
    }

    public void view() {
	try {
	    Integer id = getParaToInt();
	    T_MinistryMeeting temp = T_MinistryMeeting.dao.findById(id);
	    if (null == temp) {
		toErrorJson("您提交的数据有误，记录不存在！");
		return;
	    }
	    List<T_Common_Detail> list = T_Common_Detail.getListByKey(menuId);
	    setAttr("list", list);
	    List<T_Attachment> fileList = T_Attachment.dao.listInIds(temp.getStr("fjid"));
	    setAttr("fileList", fileList);
	    setAttr("model", temp);
	    render("view.jsp");
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
	}
    }

}
