package com.oa.model.common;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class T_Attachment extends Model<T_Attachment> {
	private static final long serialVersionUID = 1L;
	public static T_Attachment dao = new T_Attachment();

	/** 获取刚添加的附件记录 */
	public T_Attachment getLastSave() {
		String sql = "select * from t_attachment order by id desc;";
		return dao.findFirst(sql);
	}
	
	public List<T_Attachment> getListByIds(String ids){
	    String sql="select url,id,name,size from t_attachment where id in("+ids+")";
	    return dao.find(sql);
	}

	/**
	 * 根据临时ID获取附件信息
	 * @param tempId 临时ID
	 * @return
	 */
	public T_Attachment findByTempId(String tempId) {
		String sql = "select * from t_attachment WHERE tempId = ?";
		return dao.findFirst(sql,tempId);
	}
}
