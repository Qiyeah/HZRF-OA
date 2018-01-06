package com.oa.model.document;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.oa.model.common.T_Attachment;
import com.zhilian.model.T_User;

public class T_Doc_Achieve extends Model<T_Doc_Achieve> {

    private static final long serialVersionUID = 1L;
    public static final T_Doc_Achieve dao = new T_Doc_Achieve();

    /**
     * 根据文号获取收件记录
     * 
     * @param docNo
     *            文号
     * @return
     */
    public List<T_Doc_Achieve> getListByDocNo(String docNo) {
	String sql = "select * from t_doc_achieve where docno='" + docNo + "'";
	return dao.find(sql);
    }

    public List<T_Doc_Achieve> getAchieveList(int did, int i) {
	String sql = "select top(20) dc.id,dc.docno,dc.unit,dc.title,dc.status,dc.receivedate "
		+ "from t_doc_achieve dc left join t_department dp on dc.receive1=dp.id where 1=1 ";
	if (did != 2) {
	    sql += " and dc.status ='1'";
	}
	if (i != 0) {
	    sql += " and dc.id not in(select top(" + i + ") dc.id from t_doc_achieve dc order by dc.id desc) ";
	}
	sql += " order by dc.id desc";
	return T_Doc_Achieve.dao.find(sql);
    }

    /** 获取待分办收文登记列表 */
    public List<T_Doc_Achieve> getTodoAchieveList() {
	String sql = "select id,docno,unit,title,degree,receivedate from t_doc_achieve where status='0'";
	return T_Doc_Achieve.dao.find(sql);
    }

    /** 根据部门和条件查询收文登记列表 */
    public List<T_Doc_Achieve> getSearchAchieveList(int did, String content) {
	String sql = "select dc.id,dc.docno,dc.unit,dc.title,dc.status,dc.receivedate "
		+ "from t_doc_achieve dc left join t_department dp on dc.receive1=dp.id where 1=1 ";
	if (did != 2) {
	    sql += " and dc.status ='1'";
	}
	sql += " and (dc.docno like '%" + content + "%' or dc.unit like '%" + content + "%' or " + "dc.title like '%" + content + "%') ";

	sql += " order by dc.id desc";
	return T_Doc_Achieve.dao.find(sql);
    }

    /** 根据文档ID获取收文登记文档信息 */
    public T_Doc_Achieve getAchieveDetail(int id) {
	String sql = "select dc.docno,dc.unit,dc.title,dc.receivedate,u.name as uname,dc.type,dc.count,dc.security,dc.degree,dc.memo,dc.fjid "
		+ " from t_doc_achieve dc left join t_department dp on dc.receive1=dp.id "
		+ "left join t_user u on dc.u_id=u.id where dc.id=" + id;
	T_Doc_Achieve achieve = dao.findFirst(sql);
	if (achieve != null) {
	    achieve.set("fjid", T_Attachment.dao.getListByIds(achieve.getStr("fjid")));
	}
	return achieve;
    }

    /** 获取未分办收文登记文件数量 */
    public int getCount() {
	String sql = "select count(*) as num from t_doc_achieve where status='0'";
	Record rd = Db.findFirst(sql);
	return rd.getInt("num");
    }

    public String getUserNameByIds(String ids) {
	ids = ids.replace(";", ",");
	if (ids.charAt(0) == ',') {
	    ids = ids.substring(1);
	}
	return T_User.findUsernames(ids);
    }
    
}
