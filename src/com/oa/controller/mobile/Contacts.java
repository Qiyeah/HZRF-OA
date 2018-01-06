package com.oa.controller.mobile;

import java.util.List;
import java.util.Map;

import com.jfinal.aop.Clear;
import com.oa.controller.BaseAssociationController;
import com.zhilian.annotation.RouteBind;
import com.zhilian.model.T_User;

@RouteBind(path = "/Mobile/Contacts", viewPath = "/")
public class Contacts extends BaseAssociationController {

    @Clear
    public void main() {
	BussinessContent bc = new BussinessContent();
	Map<String, T_User> userMap = bc.getUserMap();
	String key = getPara();
	T_User user = userMap.get(key);
	int d_id = user.getInt("d_id");
	String select = "select * ";
	String sqlExceptSelect = "from t_user where d_id=" + d_id;
	List<T_User> contacts = T_User.dao.find(select + sqlExceptSelect);
	renderJson(contacts);
    }

    @Clear
    public void details() {
	// int contactid = getParaToInt(1);

    }
}
