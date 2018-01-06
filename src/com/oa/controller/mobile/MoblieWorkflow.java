package com.oa.controller.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.kit.StrKit;
import com.oa.controller.BaseAssociationController;
import com.oa.model.common.T_Commessage;
import com.oa.model.system.workflow.T_Wactive;
import com.oa.model.system.workflow.T_Workflow;
import com.oa.model.system.workflow.T_Wtrans;
import com.oa.model.work.T_Note;
import com.zhilian.model.T_Department;
import com.zhilian.model.T_Position;
import com.zhilian.model.T_User;

public class MoblieWorkflow extends BaseAssociationController {

    public SelectStep selectman(int itemid, int pid, Object d_id) {
	SelectStep selectman = new SelectStep();
	T_Wactive wa = T_Wactive.dao.findById(itemid);
	String amount = wa.getStr("amount");
	List<T_User> userList = new ArrayList<T_User>();
	if (wa.getStr("handround").equals("1")) {
	    userList = T_Commessage.dao.getDocList();
	} else {
	    try {
		T_Workflow wf = T_Workflow.dao.findById(pid);
		String cond = wf.getStr("starter") + "~" + d_id + "~" + d_id + "~" + wf.getStr("startdept");
		ArrayList<HashMap<String, Object>> mans = wa.getTodomans(cond);
		// 循环构造人员
		for (int i = 0; i < mans.size(); i++) {
		    HashMap<String, Object> man = mans.get(i);
		    T_User user = new T_User();
		    T_User temp = T_User.dao.findById(man.get("id"));
		    user.set("d_id", T_Department.dao.findValueById("sname", (Integer) man.get("d_id")));
		    user.set("id", man.get("id"));
		    user.set("name", man.get("name"));
		    user.set("pid", T_Position.dao.findValueById("name", temp.getInt("pid")));
		    userList.add(user);
		}
		selectman.setNextStep(itemid);
		selectman.setType(amount);
		selectman.setUser(userList);
	    } catch (Exception e) {
		e.printStackTrace();
		toErrorJson("数据处理出现错误！请重新输入！");
	    }
	}
	return selectman;
    }

    public SelectStep selectman2(String id, int pid, int d_id) {
	SelectStep selectstep = new SelectStep();
	T_Wactive wa = T_Wactive.dao.findById(id);
	String amount = wa.getStr("amount");
	List<T_User> userList = new ArrayList<T_User>();
	if (wa.getStr("handround").equals("1")) {
	    userList = T_Note.dao.getUsersInfo1();
	} else {
	    try {
		T_Workflow wf = T_Workflow.dao.findById(pid);
		String cond = wf.getStr("starter") + "~" + d_id + "~" + d_id + "~" + wf.getStr("startdept");
		ArrayList<HashMap<String, Object>> mans = wa.getTodomans(cond);
		// 如果下一环节执行人是按指定职位来选取，取出相应的职位信息
		if (wa.get("ptype") != null && wa.get("ptype").equals("2")) {
		    String users = wa.getStr("user1");
		    String[] users1 = users.split("#");
		    if (users1.length > 1) {
			// TODO 未知用途
		    }
		}
		String default1 = wa.getStr("default1");
		if (null != default1 && !"".equals(default1)) {
		    String[] defaults = default1.split(",");
		    setAttr("default1", defaults[0]);
		}
		// 循环构造人员
		for (int i = 0; i < mans.size(); i++) {
		    HashMap<String, Object> man = mans.get(i);
		    T_User user = new T_User();
		    T_User temp = T_User.dao.findById(man.get("id"));
		    user.set("name", man.get("name"));
		    user.set("d_id", T_Department.dao.findValueById("sname", (Integer) man.get("d_id")));
		    user.set("id", man.get("id"));
		    user.set("pid", T_Position.dao.findValueById("name", temp.getInt("pid")));
		    userList.add(user);
		}
	    } catch (Exception e) {
		e.printStackTrace();
		toErrorJson("数据处理出现错误！请重新输入！");
	    }
	}
	selectstep.setUser(userList);
	// 将执行人数传过去,1为单，2为多，3为多选一
	selectstep.setType(amount);
	return selectstep;
    }

    @Clear
    public SelectStep selectStep(String itemid, int d_id, int positionid) {
	SelectStep selectstep = new SelectStep();
	List<T_Wtrans> trans = T_Wtrans.dao.getNextStepNames(itemid);
	selectstep.setType("step");
	selectstep.setTrans(trans);
	return selectstep;
    }

    @Clear
    public SelectStep selectStep1(String itemid, String iscanreceive, String isreceive) {
	SelectStep selectstep = new SelectStep();
	List<T_Wtrans> trans = T_Wtrans.dao.getNextSteps1(itemid, iscanreceive, isreceive);
	selectstep.setType("step");
	selectstep.setTrans(trans);
	return selectstep;
    }

    @Clear
    public SelectStep selectSpecStep(String spids, int d_id, int positionid) {
	SelectStep selectstep = new SelectStep();
	List<T_Wtrans> trans = new ArrayList<T_Wtrans>();
	try {
	    int spidnum = 0;
	    String[] spid = null;
	    if (StrKit.notBlank(spids)) {
		spid = spids.split(",");
		spidnum = spid.length;// 获取符合条件的特例流转数量
	    }
	    // 获取该流程设置的特例环节
	    for (int i = 0; i < spidnum; i++) {
		T_Wtrans tran = new T_Wtrans();
		tran.set("id", spid[i]);
		tran.set("ato", T_Wactive.dao.getStepName(spid[i]));
		trans.add(tran);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    toErrorJson("数据处理出现错误！请重新输入！");
	}
	selectstep.setType("step");
	selectstep.setTrans(trans);
	return selectstep;
    }

}
