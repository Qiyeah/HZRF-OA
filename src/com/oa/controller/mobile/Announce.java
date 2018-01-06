package com.oa.controller.mobile;

import java.util.List;
import java.util.Map;

import com.oa.controller.BaseAssociationController;
import com.oa.model.work.T_Announce;
import com.zhilian.annotation.RouteBind;
import com.zhilian.model.T_User;

@RouteBind(path = "/Mobile/Announce", viewPath = "/")
public class Announce extends BaseAssociationController {

    /** 公告信息主页面 */
    public void main() {
	BussinessContent bc=new BussinessContent();
	Map<String, T_User> userMap=bc.getUserMap();
	String key=getPara();
	T_User user=userMap.get(key);
	if(user!=null){
	    
	String select="select * ";
	String sqlExceptSelect="from t_announce where 1=1 ";
    	String stitle=null;
    	List<T_Announce> announce=null;
    	try{
    	    if(getPara("stitle") != null && !"".equals(getPara("stitle").trim())){
    		stitle=getPara("stitle");
    		sqlExceptSelect += " and title like '%"+stitle+"%' ";
    		setAttr("stitle",stitle);
    	    }
    	    
    	announce = T_Announce.dao.find(select+sqlExceptSelect);
    	}catch(Exception e){
    	    toErrorJson("您提交的查询参数有误，请检查后重新提交！");
    	    e.printStackTrace();
    	}
    	renderJson(announce);
	}else{
	    renderJson("{errormsg:'用户登录超时，请重新登录'}");
	}
    }
    
    public void detail() {
	BussinessContent bc=new BussinessContent();
	Map<String, T_User> userMap=bc.getUserMap();
	String key=getPara();
	T_User user=userMap.get(key);
	if(user!=null){
	    int id=getParaToInt(1);
	    T_Announce announce = T_Announce.dao.findById(id);
	    renderJson(announce);
	}else{
	    renderJson("{errormsg:'用户登录超时，请重新登录'}");
	}
    }
   

  

    

  
}
