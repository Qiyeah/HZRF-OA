package com.oa.model.document;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class T_Doc_Send extends Model<T_Doc_Send> {

    private static final long serialVersionUID = 1L;
    public static final T_Doc_Send dao = new T_Doc_Send();

    /** 计算文号数 */
    public static int countWH(String boferStr, String year) {
	int result = 0;
	String selectStr = "";
	if (boferStr.equals("1")) {
	    selectStr = "河组通";
	} else if (boferStr.equals("2")) {
	    selectStr = "河组干";
	} else if (boferStr.equals("3")) {
	    selectStr = "河组字";
	} else if (boferStr.equals("4")) {
	    selectStr = "河组函";
	} else if (boferStr.equals("5")) {
	    selectStr = "河组党";
	} else if (boferStr.equals("6")) {
	    selectStr = "河组电传";
	} else if (boferStr.equals("7")) {
	    selectStr = "会议纪要";
	}

	selectStr += "〔" + year + "〕";

	try {
	    String sql = "select * from t_doc_send t where t.docno like '%" + selectStr + "%' order by t.id desc";
	    T_Doc_Send rd = dao.findFirst(sql);
	    if (rd == null) {
		return 0;
	    }
	    String docnum = rd.get("docno");
	    result = Integer.parseInt(docnum.substring(docnum.lastIndexOf("〕") + 1, docnum.length() - 1));
	    // result = rd.getLong("count").intValue();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /**
     * 根据pid获取T_Doc_Send
     * 
     * @param pid
     * @return
     */
    public static T_Doc_Send findByPid(int pid) {
	String sql = "select * from t_doc_send where pid = " + pid;
	return dao.findFirst(sql);
    }
    
    public static T_Doc_Send findAllById(String id) {
	String sql = "select a.*,b.name as uname,c.fname as dname "
		+ "from t_doc_send a left join t_user b on a.u_id=b.id left join t_department c on a.d_id=c.id where a.id = " + id;
	return dao.findFirst(sql);
    }
    
    public static T_Doc_Send findAllByPid(Object id) {
	String sql = "select a.*,b.name as uname,c.fname as dname "
		+ "from t_doc_send a left join t_user b on a.u_id=b.id left join t_department c on a.d_id=c.id where a.pid = " + id;
	return dao.findFirst(sql);
    }
    
    public List<T_Doc_Send> getType(String sql){
   	return dao.find(sql);
       }
}
