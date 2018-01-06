package com.oa.controller.work;

import java.io.File;

import com.jfinal.aop.Clear;
import com.jfinal.upload.UploadFile;
import com.oa.model.approve.T_Leave_Approve;
import com.oa.model.work.Attendance_Status;
import com.oa.util.BusinessConstant;
import com.oa.util.Dates;
import com.oa.util.Excel;
import com.zhilian.annotation.RouteBind;
import com.zhilian.config.Constant;
import com.zhilian.controller.BaseController;
import com.zhilian.model.T_User;

@Clear
@RouteBind(path = "Main/AttenceUpload", viewPath = "Common/Upload")
public class AttenceUpload extends BaseController {
    public void uploadip() {
	render("attenceUpload.jsp");
    }

    /** 上传 */
    public void upload() {
	/*
	 * try { UploadFile excelFile = getFile("excel", Constant.PATH_WEBROOT +
	 * Constant.PATH_EXCEL, Constant.MAX_POST_SIZE); UploadFile excelFile =
	 * getFile(); String fileName = excelFile.getFileName(); String fileurl
	 * = Constant.PATH_WEBROOT + Constant.PATH_EXCEL + fileName; //String
	 * navTabID = importData(excelFile); if (importData(excelFile)) {
	 * //setAttr("navTabID", navTabID); render("success.jsp");
	 * toBJUIJson(200,"导入成功","personattencemg","","","true",""); } else {
	 * render("failure.jsp"); toErrorJson("导入数据出现问题！"); } } catch (Exception
	 * e) { toErrorJson("导入数据出现问题！"); }
	 */
	try {
	    UploadFile excelFile = getFile();
	    if (importData(excelFile.getFile())) {
		toBJUIJson(200, Constant.SUCCESS, "personattencemg", "", "", "true", "");
	    } else {
		toErrorJson(Constant.EXCEPTION);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson(Constant.EXCEPTION);
	}
    }

    /** 直接导入 */
    public boolean importData(File excelFile) {
	/*
	 * Excel excel = null; File file = null; String navTabID = null; try {
	 * //file = new File(excelFile); if (!file.exists()) { return null; }
	 * excel = new Excel(excelFile);
	 */
	Excel excel = null;
	try {
	    excel = new Excel(excelFile); // 获取excel表信息
	    // 获取excel表信息
	    int rowCount = excel.getRownum();
	    if (rowCount > 3) {
		// 获取表数据导入
		for (int row = 3; row < rowCount; row++) {
		    Attendance_Status model = new Attendance_Status();
		    String name = excel.getValue(0, row);
		    if (!name.equals("")) {
			name = name.replaceAll(" ", "").replaceAll("　", "").trim();
		    } else {
			continue;
		    }
		    // 日期
		    String date = excel.getValue(1, row);
		    String am_on_time = excel.getValue(2, row).trim();
		    String am_off_time = excel.getValue(3, row).trim();
		    String pm_on_time = excel.getValue(4, row).trim();
		    String pm_off_time = excel.getValue(5, row).trim();
		    T_User user = T_User.dao
			    .findFirst("SELECT a.* FROM t_user a WHERE a.name = '" + name + "' ORDER BY id limit 1");
		    model.set("u_id", user.getInt("id"));
		    model.set("d_id", user.getInt("d_id"));
		    model.set("date", date.trim());
		    // 上午上班时间处理
		    if (null != am_on_time && !"".equals(am_on_time.trim())) {
			// 判断上午是否迟到
			if (Dates.parse(am_on_time).getTime() > Dates.parse(date + " " + BusinessConstant.AM_ON_TIME)
				.getTime()) {
			    model.set("am_on_status", BusinessConstant.ATTENDANCE_STATUS_LATE);
			} else {
			    model.set("am_on_status", BusinessConstant.ATTENDANCE_STATUS_NORMAL);
			}
			model.set("am_on_time", am_on_time);
		    } else {
			// 当上午上班时间为空时 判断是否请假
			T_Leave_Approve approve = T_Leave_Approve.dao
				.findFirst(" SELECT a.* FROM t_leave_approve a WHERE a.begindate < '" + date + "' AND '"
					+ date + "' < a.enddate AND u_id = " + model.getInt("u_id") + " limit 1");
			if (null != approve) {
			    model.set("am_on_status", BusinessConstant.ATTENDANCE_STATUS_LEAVE);
			} else {
			    // 缺勤
			    model.set("am_on_status", BusinessConstant.ATTENDANCE_STATUS_ABSENCE);
			}
		    }
		    // 上午下班时间处理
		    if (null != am_off_time && !"".equals(am_off_time.trim())) {
			// 判断上午是否早退
			if (Dates.parse(am_off_time).getTime() < Dates.parse(date + " " + BusinessConstant.AM_OFF_TIME)
				.getTime()) {
			    model.set("am_off_status", BusinessConstant.ATTENDANCE_STATUS_LEAVE_EARLY);
			} else {
			    model.set("am_off_status", BusinessConstant.ATTENDANCE_STATUS_NORMAL);
			}
			model.set("am_off_time", am_off_time);
		    } else {
			// 当上午下班时间为空时 判断是否请假
			T_Leave_Approve approve = T_Leave_Approve.dao.findFirst(
				" SELECT a.* FROM t_leave_approve a WHERE a.begindate < '" + date + "' AND  '" + date
					+ "' < a.enddate AND u_id = " + model.getInt("u_id") + " limit 1");
			if (null != approve) {
			    model.set("am_off_status", BusinessConstant.ATTENDANCE_STATUS_LEAVE);
			} else {
			    // 缺勤
			    model.set("am_off_status", BusinessConstant.ATTENDANCE_STATUS_ABSENCE);
			}
		    }
		    // 下午上班时间处理
		    if (null != pm_on_time && !"".equals(pm_on_time.trim())) {
			// 判断下午是否迟到
			if (Dates.parse(pm_on_time).getTime() > Dates.parse(date + " " + BusinessConstant.PM_ON_TIME)
				.getTime()) {
			    model.set("pm_on_status", BusinessConstant.ATTENDANCE_STATUS_LATE);
			} else {
			    model.set("pm_on_status", BusinessConstant.ATTENDANCE_STATUS_NORMAL);
			}
			model.set("pm_on_time", pm_on_time);
		    } else {
			// 当下午上班时间为空时 判断是否请假
			T_Leave_Approve approve = T_Leave_Approve.dao
				.findFirst(" SELECT a.* FROM t_leave_approve a WHERE a.begindate < '" + date + "' AND '"
					+ date + "' < enddate AND u_id = " + model.getInt("u_id") + " limit 1");
			if (null != approve) {
			    model.set("pm_on_status", BusinessConstant.ATTENDANCE_STATUS_LEAVE);
			} else {
			    // 缺勤
			    model.set("pm_on_status", BusinessConstant.ATTENDANCE_STATUS_ABSENCE);
			}
		    }
		    // 下午下班时间处理
		    if (null != pm_off_time && !"".equals(pm_off_time.trim())) {
			// 判断下午是否早退
			if (Dates.parse(pm_off_time).getTime() < Dates.parse(date + " " + BusinessConstant.PM_OFF_TIME)
				.getTime()) {
			    model.set("pm_off_status", BusinessConstant.ATTENDANCE_STATUS_LEAVE_EARLY);
			} else {
			    model.set("pm_off_status", BusinessConstant.ATTENDANCE_STATUS_NORMAL);
			}
			model.set("pm_off_time", pm_off_time);
		    } else {
			// 当下午下班时间为空时 判断是否请假
			T_Leave_Approve approve = T_Leave_Approve.dao.findFirst(
				" SELECT a.* FROM t_leave_approve a WHERE a.begindate < '" + date + "' AND  '" + date
					+ "' < a.enddate AND u_id = " + model.getInt("u_id") + " limit 1");
			if (null != approve) {
			    model.set("pm_off_status", BusinessConstant.ATTENDANCE_STATUS_LEAVE);
			} else {
			    // 缺勤
			    model.set("pm_off_status", BusinessConstant.ATTENDANCE_STATUS_ABSENCE);
			}
		    }
		    model.save();
		}
	    }
	    // navTabID = "personattencemg";
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("表中数据有误，请检查后重新导入！");
	    return false;
	    // navTabID = null;
	} finally {
	    if (excel != null) {
		excel.close();
	    }
	}
	return true;
    }

}
