package com.oa.model.system.workflow;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.zhilian.util.DateUtils;

public class Transaction {
    private String webroot = "";

    public WorkflowReturn Trans(Instance ins) throws Exception {
	WorkflowReturn wfr = new WorkflowReturn();
	T_Workitem wkit = new T_Workitem();
	T_Workitem wkit1 = new T_Workitem();
	T_Workflow wkfl = ins.getWorkflow();
	T_Wactive wa = ins.getWActive();

	String op = ins.getOperation();
	if (op == null) {
	    op = "";
	}
	int ipid = wkfl.getInt("id");
	String pid = String.valueOf(ipid);
	String workpath = wkfl.getStr("workpath");
	// String flowname = wkfl.getStr("flowname");
	String formname = wkfl.getStr("formname");
	String starter = wkfl.getStr("starter");
	int curitemid = wkfl.getInt("itemid");
	String nextitemid = ins.getNextitemid();
	String nexttodoman = ins.getNexttodoman();
	String user = ins.getCuruser();
	String mainflowid = wkfl.getStr("mainflowid");
	if (mainflowid == null || mainflowid.equals("")) {
	    mainflowid = "0";
	}
	String opinion = ins.getOpinion();
	String opinionfield = ins.getOpinionfield();
	String opiniontime = ins.getOpiniontime();

	wfr.setId(pid);
	wfr.setWebroot(webroot);
	wfr.setWorkpath(workpath);
	wfr.setFormname(formname);

	String nowtime = DateUtils.getNowTime();

	// 后台保存签署意见的时间
	opiniontime = nowtime;

	if (wkfl.getStr("isnewdoc").equals("1")) { // 新建文档
	    wkfl.remove("id");
	    if (op.equals("ZanCun") | op.equals("BaoCunTuiChu")) { // 暂存 或 保存退出
		wkfl.set("doneuser", "");
		wkfl.set("todousers", user);
		wkfl.set("todoman", user);
		wkfl.set("isopen", "1");
		wkfl.set("editor", user);
		wkfl.set("isnewdoc", "0");
		wkfl.set("starttime", nowtime);
		wkfl.save();
		pid = wkfl.getInt("id").toString();
		wkit.set("pid", pid);
		wkit.set("user1", wkfl.getStr("starter"));
		wkit.set("user2", "");
		wkit.set("itemid1", String.valueOf(curitemid));
		wkit.set("itemid2", "");
		wkit.set("begintime", wkfl.getStr("starttime"));
		wkit.set("endtime", "");
		wkit.set("operation", "");
		wkit.set("opinionfield", "");
		wkit.set("opinion", "");
		wkit.set("opiniontime", "");
		wkit.save();

		wfr.setMessage("wfr1");
	    } else if (op.equals("FaSong") | op.equals("TeSong")) { // 发送、特送
		wkfl.set("isnewdoc", "0");
		wkfl.set("starttime", nowtime);
		wkfl.set("itemid", nextitemid);
		wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user));
		wkfl.set("doneuser", "");
		wkfl.set("todousers", nexttodoman);
		if (wa.getStr("amount").equals("1")) {
		    wkfl.set("todoman", nexttodoman);
		} else {
		    if (wa.getStr("issequence").equals("0")) {
			wkfl.set("todoman", nexttodoman);
		    } else {
			String tmpuser[] = wkfl.getStr("todousers").split(";");
			wkfl.set("todoman", tmpuser[0]);
		    }
		}
		wkfl.set("isopen", "0");
		wkfl.set("editor", "");
		wkfl.save();

		pid = wkfl.getInt("id").toString();
		wkit.set("pid", pid);
		wkit.set("user1", wkfl.getStr("starter"));
		wkit.set("user2", nexttodoman);
		wkit.set("itemid1", String.valueOf(curitemid));
		wkit.set("itemid2", nextitemid);
		wkit.set("begintime", wkfl.getStr("starttime"));
		wkit.set("endtime", nowtime);
		wkit.set("operation", op);
		wkit.set("opinionfield", opinionfield);
		wkit.set("opinion", opinion);
		wkit.set("opiniontime", opiniontime);
		wkit.save();

		wfr.setMessage("wfr2");
		wfr.setTodoman(wkfl.getStr("todoman"));
	    }
	} else {  // 已建文档
	    if (op.equals("ZanCun") | op.equals("BaoCunTuiChu")) { // 暂存 或 保存退出
		wkit = T_Workitem.dao.getWorkitem(ipid, curitemid, user);
		wkit.set("opinionfield", opinionfield);
		wkit.set("opinion", opinion);
		wkit.set("opiniontime", opiniontime);
		wkit.update();

		wkfl.set("doneuser", "");
		wkfl.set("todousers", user);
		wkfl.set("todoman", user);
		wkfl.set("isopen", "1");
		wkfl.set("editor", user);
		wkfl.update();

		wfr.setMessage("wfr1");
	    } else if (op.equals("QuHui")) { // 取回
		wkit = T_Workitem.dao.getLastWorkitem(ipid, curitemid);
		String itemid1 = wkit.getStr("itemid1");
		String user1 = wkit.getStr("user1");
		String itemid2 = wkit.getStr("itemid2");
		String user2 = wkit.getStr("user2");
		nextitemid = itemid1;
		nexttodoman = user1;
		wkit.set("user1", user2);
		wkit.set("user2", user1);
		wkit.set("itemid1", itemid2);
		wkit.set("itemid2", itemid1);
		wkit.set("begintime", wkfl.getStr("begintime"));
		wkit.set("endtime", nowtime);
		wkit.set("operation", op);
		wkit.set("opinionfield", "");
		wkit.set("opinion", "");
		wkit.set("opiniontime", "");
		wkit.remove("id");
		wkit.save();

		wkfl.set("itemid", nextitemid);
		wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user1));
		wkfl.set("doneuser", "");
		if (!wkit.getStr("itemid1").equals(wkit.getStr("itemid2")))
		    wkfl.set("todousers", nexttodoman);
		else
		    wkfl.set("todousers", arrayAddstr(wkfl.getStr("todousers"), nexttodoman));
		wkfl.set("todoman", nexttodoman);
		wkfl.set("isopen", "0");
		wkfl.set("editor", arrayDelstr(wkfl.getStr("editor"), user1));
		wkfl.update();

		wfr.setMessage("wfr2");
		wfr.setTodoman(nexttodoman);
	    } else if (op.equals("TuiHuiShangBu")) { // 退回上一步
		wkit1 = T_Workitem.dao.getLastWorkitem(ipid, curitemid);
		nextitemid = wkit1.get("itemid1");
		nexttodoman = wkit1.get("user1");

		if (wa.getStr("amount").equals("3")) {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid);
		} else {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid, user);
		}
		if (wkit != null) {
		    wkit.set("user1", user);
		    wkit.set("itemid2", nextitemid);
		    wkit.set("user2", nexttodoman);
		    wkit.set("endtime", nowtime);
		    wkit.set("operation", op);
		    wkit.set("opinionfield", opinionfield);
		    wkit.set("opinion", opinion);
		    wkit.set("opiniontime", opiniontime);
		    wkit.update();

		    wkfl.set("itemid", nextitemid);
		    wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user));
		    wkfl.set("doneuser", "");
		    if (!wkit.getStr("itemid1").equals(wkit.getStr("itemid2")))
			wkfl.set("todousers", nexttodoman);
		    else
			wkfl.set("todousers", arrayAddstr(wkfl.getStr("todousers"), nexttodoman));
		    wkfl.set("todoman", nexttodoman);
		    wkfl.set("isopen", "0");
		    wkfl.set("editor", "");
		    wkfl.update();

		    wfr.setMessage("wfr2");
		    wfr.setTodoman(nexttodoman);
		} else {
		    wfr.setMessage("wfr3");
		}
	    } else if (op.equals("TuiHuiNiGao")) { // 退回第一步
		wkit1 = T_Workitem.dao.getFirstWorkitem(ipid);
		nextitemid = wkit1.get("itemid1");
		nexttodoman = wkit1.get("user1");

		if (wa.getStr("amount").equals("3")) {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid);
		} else {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid, user);
		}
		if (wkit != null) {
		    wkit.set("user1", user);
		    wkit.set("itemid2", ins.getNextitemid());
		    wkit.set("user2", ins.getNexttodoman());
		    wkit.set("endtime", nowtime);
		    wkit.set("operation", op);
		    wkit.set("opinionfield", opinionfield);
		    wkit.set("opinion", opinion);
		    wkit.set("opiniontime", opiniontime);
		    wkit.update();

		    wkfl.set("itemid", nextitemid);
		    wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user));
		    wkfl.set("doneuser", "");
		    wkfl.set("todousers", nexttodoman);
		    wkfl.set("todoman", nexttodoman);
		    wkfl.set("isopen", "0");
		    wkfl.set("editor", "");
		    wkfl.update();

		    wfr.setMessage("wfr2");
		    wfr.setTodoman(starter);
		} else {
		    wfr.setMessage("wfr3");
		}
	    } else if (op.equals("FaSong") | op.equals("TeSong")) { // 发送、特送
		if (wa.getStr("amount").equals("3")) {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid);
		} else {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid, user);
		}
		if (wkit != null) {
		    wkit.set("user1", user);
		    wkit.set("itemid2", ins.getNextitemid());
		    wkit.set("user2", ins.getNexttodoman());
		    wkit.set("endtime", nowtime);
		    wkit.set("operation", op);
		    wkit.set("opinionfield", opinionfield);
		    wkit.set("opinion", opinion);
		    wkit.set("opiniontime", opiniontime);
		    wkit.update();

		    wkfl.set("itemid", nextitemid);
		    wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user));
		    wkfl.set("doneuser", "");

		    if (wa.getStr("amount").equals("2") && !wa.getStr("issequence").equals("0")) {
			String tmpuser[] = wkfl.getStr("todousers").split(";");
			nexttodoman = tmpuser[0];
		    }
		    // 传阅会签时，去掉已经签署意见的领导
		    if (wa.getStr("handround").equals("1")) {
			String strsql = "select * from t_workitem where pid=" + pid
				+ " and operation='FaSong' and opinion is not null and endtime <> '' and opiniontime <> '' order by id";
			List<T_Workitem> items = T_Workitem.dao.find(strsql);
			for (T_Workitem item : items) {
			    String uid = item.getStr("user1");
			    nexttodoman = arrayDelstr(nexttodoman, uid);
			}
		    }
		    if (op.equals("TeSong")) {
			wkfl.set("isreceive", "0");
		    }

		    wkfl.set("todoman", nexttodoman);
		    wkfl.set("todousers", nexttodoman);
		    wkfl.set("isopen", "0");
		    wkfl.set("editor", "");
		    wkfl.update();

		    wfr.setMessage("wfr2");
		    wfr.setTodoman(nexttodoman);
		} else {
		    if (op.equals("TeSong")) {
			wkfl.set("isreceive", "0").update();
		    }
		    wfr.setMessage("wfr3");
		}
	    } else if (op.equals("FaSongdoc")) {// 收文提交
		String todousers = wkfl.getStr("todousers");
		int todomannum = 1;
		if (null != todousers) {
		    String mans[] = todousers.split(";");
		    todomannum = mans.length;
		}
		if (todomannum > 1) {// 将自己从代办人中剔除以及暂存下一处理人
		    if (wa.getStr("amount").equals("3")) {
			wkit = T_Workitem.dao.getWorkitem(ipid, curitemid);
		    } else {
			wkit = T_Workitem.dao.getWorkitem(ipid, curitemid, user);
		    }
		    wkit.set("itemid2", String.valueOf(curitemid));

		    String usr2 = arrayDelstr(wkfl.getStr("todoman"), user);

		    wkit.set("user2", usr2);
		    wkit.set("endtime", nowtime);
		    wkit.set("operation", op);
		    wkit.set("opinionfield", opinionfield);
		    if (StrKit.notBlank(opinion)) {
			wkit.set("opinion", opinion);
		    }
		    if (StrKit.notBlank(opiniontime)) {
			wkit.set("opiniontime", opiniontime);
		    }
		    wkit.update();
		    wkfl.set("nextshoulddo", arrayAddstr(wkfl.getStr("nextshoulddo"), nexttodoman));// 暂存下一环节处理人
		    wkfl.set("todousers", arrayDelstr(wkfl.getStr("todousers"), user));
		    wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user));
		    wkfl.set("doneuser", arrayAddstr(wkfl.getStr("doneuser"), user));

		    usr2 = arrayDelstr(wkfl.getStr("todousers"), user);
		    // 传阅会签时，去掉已经签署意见的领导
		    if (wa.getStr("handround").equals("1")) {
			String strsql = "select * from t_workitem where pid=" + pid
				+ " and operation='FaSongdoc' and opinion is not null and endtime <> '' and opiniontime <> '' order by id";
			List<T_Workitem> items = T_Workitem.dao.find(strsql);
			for (T_Workitem item : items) {
			    String uid = item.getStr("user1");
			    usr2 = arrayDelstr(usr2, uid);
			}
		    }
		    wkfl.set("todousers", usr2);
		    wkfl.set("isopen", "0");
		    wkfl.set("editor", "");
		    /*
		     * if (wa.getStr("issequence").equals("0")) nexttodoman =
		     * wkfl.getStr("todousers"); else { String tmpuser[] =
		     * wkfl.getStr("todousers").split(";"); nexttodoman =
		     * tmpuser[0]; }
		     */
		    wkfl.set("todoman", arrayDelstr(wkfl.getStr("todousers"), user));
		    wkfl.update();
		    wfr.setNextShoulddo(wkfl.getStr("nextshoulddo"));
		    wfr.setMessage("wfr3");
		    wfr.setTodoman(arrayDelstr(wkfl.getStr("todousers"), user));
		} else {// 相当于发送
		    if (wa.getStr("amount").equals("3")) {
			wkit = T_Workitem.dao.getWorkitem(ipid, curitemid);
		    } else {
			wkit = T_Workitem.dao.getWorkitem(ipid, curitemid, user);
		    }
		    if (wkit != null) {
			wkit.set("user1", user);
			wkit.set("itemid2", ins.getNextitemid());
			wkit.set("user2", ins.getNexttodoman());
			wkit.set("endtime", nowtime);
			wkit.set("operation", op);
			wkit.set("opinionfield", opinionfield);
			if (StrKit.notBlank(opinion)) {
			    wkit.set("opinion", opinion);
			}
			if (StrKit.notBlank(opiniontime)) {
			    wkit.set("opiniontime", opiniontime);
			}
			wkit.update();

			wkfl.set("itemid", nextitemid);
			wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user));
			wkfl.set("doneuser", "");
			if (StrKit.notBlank(wkfl.getStr("nextshoulddo"))) {
			    nexttodoman = arrayAddstr(wkfl.getStr("nextshoulddo"), nexttodoman);
			    wkfl.set("nextshoulddo", "");
			    wkfl.set("todousers", nexttodoman);
			} else {
			    if (wa.getStr("amount").equals("2") && !wa.getStr("issequence").equals("0")) {// 顺序执行
				String tmpuser[] = wkfl.getStr("todousers").split(";");
				nexttodoman = tmpuser[0];
				wkfl.set("todousers", nexttodoman);
			    }
			    if (wa.getStr("amount").equals("2") && wa.getStr("issequence").equals("0")) {// 同时执行
				nexttodoman = ins.getNexttodoman();
				wkfl.set("islock", "0");
				wkfl.set("todousers", nexttodoman);
			    }
			    // 传阅会签时，去掉已经签署意见的领导
			    if (wa.getStr("handround").equals("1")) {
				String strsql = "select * from t_workitem where pid=" + pid
					+ " and operation='FaSongdoc' and opinion is not null and endtime <> '' and opiniontime <> '' order by id";
				List<T_Workitem> items = T_Workitem.dao.find(strsql);
				for (T_Workitem item : items) {
				    String uid = item.getStr("user1");
				    nexttodoman = arrayDelstr(nexttodoman, uid);
				}
				wkfl.set("todousers", nexttodoman);
			    }

			}
			wkfl.set("todoman", nexttodoman);

			wfr.setTodoman(nexttodoman);
			wkfl.set("isopen", "0");
			wkfl.set("editor", "");
			wkfl.update();

			wfr.setMessage("wfr2");

		    } else {
			wfr.setMessage("wfr3");
		    }
		}
	    } else if (op.equals("ChuLiWanBi")) { // 处理完毕
		// wkit = T_Workitem.dao.getWorkitem(ipid, curitemid,user);
		if (wa.getStr("amount").equals("3")) {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid);
		} else {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid, user);
		}
		wkit.set("itemid2", String.valueOf(curitemid));

		String usr2 = arrayDelstr(wkfl.getStr("todoman"), user);
		// 传阅会签时，去掉已经签署意见的领导
		if (wa.getStr("handround").equals("1")) {
		    String strsql = "select * from t_workitem where pid=" + pid
			    + " and operation='ChuLiWanBi' and opinion is not null and endtime <> '' and opiniontime <> '' order by id";
		    List<T_Workitem> items = T_Workitem.dao.find(strsql);
		    for (T_Workitem item : items) {
			String uid = item.getStr("user1");
			usr2 = arrayDelstr(usr2, uid);
		    }
		}
		wkit.set("user2", usr2);
		wkit.set("endtime", nowtime);
		wkit.set("operation", op);
		wkit.set("opinionfield", opinionfield);
		wkit.set("opinion", opinion);
		wkit.set("opiniontime", opiniontime);
		wkit.update();

		wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user));
		wkfl.set("doneuser", arrayAddstr(wkfl.getStr("doneuser"), user));

		usr2 = arrayDelstr(wkfl.getStr("todousers"), user);
		// 传阅会签时，去掉已经签署意见的领导
		if (wa.getStr("handround").equals("1")) {
		    String strsql = "select * from t_workitem where pid=" + pid
			    + " and operation='ChuLiWanBi' and opinion is not null and endtime <> '' and opiniontime <> '' order by id";
		    List<T_Workitem> items = T_Workitem.dao.find(strsql);
		    for (T_Workitem item : items) {
			String uid = item.getStr("user1");
			usr2 = arrayDelstr(usr2, uid);
		    }
		}
		wkfl.set("todousers", usr2);
		wkfl.set("isopen", "0");
		wkfl.set("editor", "");
		if (wa.getStr("issequence").equals("0"))
		    nexttodoman = wkfl.getStr("todousers");
		else {
		    String tmpuser[] = wkfl.getStr("todousers").split(";");
		    nexttodoman = tmpuser[0];
		}
		wkfl.set("todoman", nexttodoman);
		wkfl.update();

		wfr.setMessage("wfr2");
		wfr.setTodoman(nexttodoman);
	    } else if (op.equals("WanCheng")) { // 完成
		if (wa.getStr("amount").equals("3")) {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid);
		} else {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid, user);
		}
		if (wkit != null) {
		    wkit.set("user1", user);
		    wkit.set("endtime", nowtime);
		    wkit.set("operation", op);
		    wkit.set("opinionfield", opinionfield);
		    wkit.set("opinion", opinion);
		    wkit.set("opiniontime", opiniontime);
		    wkit.update();

		    wkfl.set("isend", "1");
		    wkfl.set("isnormalend", "1");
		    wkfl.set("isopen", "0");
		    wkfl.set("todoman", "");
		    wkfl.set("todousers", "");
		    wkfl.set("doneuser", "");
		    wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user));
		    wkfl.set("editor", "");
		    wkfl.set("endtime", nowtime);
		    wkfl.update();
		    if (!mainflowid.equals("0")) {
			T_Workflow mainflow = T_Workflow.dao.findById(mainflowid);
			mainflow.set("ishold", "0");
			mainflow.update();
		    }

		    wfr.setMessage("wfr1");
		} else {
		    wfr.setMessage("wfr3");
		}
	    } else if (op.equals("ZhongZhi")) { // 终止ֹ
		if (wa.getStr("amount").equals("3")) {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid);
		} else {
		    wkit = T_Workitem.dao.getWorkitem(ipid, curitemid, user);
		}
		if (wkit != null) {
		    wkit.set("user1", user);
		    wkit.set("endtime", nowtime);
		    wkit.set("operation", op);
		    wkit.set("opinionfield", opinionfield);
		    wkit.set("opinion", opinion);
		    wkit.set("opiniontime", opiniontime);
		    wkit.update();

		    wkfl.set("isend", "1");
		    wkfl.set("isnormalend", "0");
		    wkfl.set("isopen", "0");
		    wkfl.set("todoman", "");
		    wkfl.set("todousers", "");
		    wkfl.set("doneuser", "");
		    wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user));
		    wkfl.set("editor", "");
		    wkfl.set("endtime", nowtime);
		    wkfl.update();
		    if (!mainflowid.equals("0")) {
			T_Workflow mainflow = T_Workflow.dao.findById(mainflowid);
			mainflow.set("ishold", "0");
			mainflow.update();
		    }

		    wfr.setMessage("wfr1");
		} else {
		    wfr.setMessage("wfr3");
		}
	    } else if (op.equals("JieSuo")) { // 解锁
		wkfl.set("isopen", "0");
		wkfl.set("islock", "0");
		wkfl.set("editor", "");
		wkfl.update();

		wfr.setMessage("wfr1");
	    } else if (op.equals("ChoneZhi")) { // 重置
		wkfl.set("todousers", nexttodoman);
		if (!wa.getStr("amount").equals("1") && !wa.getStr("issequence").equals("0")) {
		    String tmpuser[] = wkfl.getStr("todousers").split(";");
		    nexttodoman = tmpuser[0];
		}
		wkfl.set("todoman", nexttodoman);
		wkfl.set("isopen", "0");
		wkfl.set("editor", "");
		wkfl.update();

		wfr.setMessage("wfr2");
	    } else if (op.equals("ChongZhi")) {
		wkit = new T_Workitem();
		wkit.set("pid", ipid);
		wkit.set("itemid1", curitemid);
		wkit.set("begintime", nowtime);
		wkit.set("user1", user);
		wkit.set("itemid2", ins.getNextitemid());
		wkit.set("user2", ins.getNexttodoman());
		wkit.set("endtime", nowtime);
		wkit.set("operation", op);
		wkit.set("opinionfield", opinionfield);
		wkit.set("opinion", opinion);
		wkit.set("opiniontime", opiniontime);
		wkit.save();

		wkfl.set("itemid", nextitemid);
		wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user));
		wkfl.set("doneuser", "");

		if (wa.getStr("amount").equals("2") && !wa.getStr("issequence").equals("0")) {
		    String tmpuser[] = wkfl.getStr("todousers").split(";");
		    nexttodoman = tmpuser[0];
		}
		// 传阅会签时，去掉已经签署意见的领导
		if (wa.getStr("handround").equals("1")) {
		    String strsql = "select * from t_workitem where pid=" + pid
			    + " and operation='FaSong' and opinion is not null and endtime <> '' and opiniontime <> '' order by id";
		    List<T_Workitem> items = T_Workitem.dao.find(strsql);
		    for (T_Workitem item : items) {
			String uid = item.getStr("user1");
			nexttodoman = arrayDelstr(nexttodoman, uid);
		    }
		}
		wkfl.set("isreceive", "0");
		wkfl.set("todoman", nexttodoman);
		wkfl.set("todousers", nexttodoman);
		wkfl.set("isopen", "0");
		wkfl.set("editor", "");
		wkfl.update();

		wfr.setMessage("wfr2");
		wfr.setTodoman(nexttodoman);
	    }
	}
	wfr.setId(pid);

	return wfr;
    }

    public WorkflowReturn point(Instance ins) {
	WorkflowReturn wfr = new WorkflowReturn();
	T_Workflow wkfl = ins.getWorkflow();
	int pid = wkfl.getInt("id");
	int itemid = wkfl.getInt("itemid");
	String nexttodoman = ins.getNexttodoman();
	String user = ins.getCuruser();
	T_Workitem wkit = T_Workitem.dao.getWorkitem(pid, itemid, user);
	String todoman = arrayDelstr(wkfl.getStr("todoman"), user);// 剔除自己
	todoman = arrayAddstr(todoman, nexttodoman);// 加入新人
	boolean flag = false;

	if (wkit != null) {
	    wkit.set("user1", user);
	    wkit.set("itemid2", itemid);
	    wkit.set("user2", todoman);
	    wkit.set("begintime", wkfl.getTimestamp("starttime"));
	    wkit.set("endtime", DateUtils.getNowTime());
	    wkit.set("operation", "ZhiPai");
	    wkit.set("opinionfield", "opinion1");
	    if (StrKit.notBlank(ins.getOpinion())) {
		wkit.set("opinion", ins.getOpinion());
	    }
	    if (StrKit.notBlank(ins.getOpiniontime())) {
		wkit.set("opiniontime", ins.getOpiniontime());
	    }
	    wkit.set("isPoint", 1);
	    flag = wkit.update();

	    wkfl.set("todousers", todoman);
	    wkfl.set("reader", arrayAddstr(wkfl.getStr("reader"), user));
	    wkfl.set("doneuser", arrayAddstr(wkfl.getStr("doneuser"), user));
	    wkfl.set("isopen", "0");
	    wkfl.set("editor", "");
	    wkfl.set("todoman", todoman);
	    flag = flag & wkfl.update();
	    if (flag) {
		wfr.setMessage("wfr2");
		wfr.setTodoman(wkfl.getStr("todoman"));
		wfr.setId(String.valueOf(pid));
	    }
	} else {
	    wfr.setMessage("wfr3");
	}
	return wfr;
    }

    public String arrayAddstr(String s, String s1) {
	if (s == null)
	    s = "";
	String[] str = s.split(";");
	boolean flag = true;
	for (int i = 0; i < str.length; i++) {
	    if (str[i].equals(s1)) {
		flag = false;
		break;
	    }
	}
	if (flag) {
	    if (s.equals(""))
		s = s1;
	    else
		s = s + ";" + s1;
	}
	return s;
    }

    public String arrayDelstr(String s, String s1) {
	String tmpstr = "";
	if (s == null)
	    s = "";
	String str[] = s.split(";");
	for (int i = 0; i < str.length; i++) {
	    if (!str[i].equals(s1)) {
		if (tmpstr.equals(""))
		    tmpstr = str[i];
		else
		    tmpstr = tmpstr + ";" + str[i];
	    }
	}
	return tmpstr;
    }

}
