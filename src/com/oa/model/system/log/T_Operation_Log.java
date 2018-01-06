package com.oa.model.system.log;

import java.util.Date;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhilian.config.Constant;
import com.zhilian.util.DateUtils;

/**
 * 操作日志
 */
public class T_Operation_Log extends Model<T_Operation_Log> {
	private static final long serialVersionUID = -1L;
	public static final T_Operation_Log dao = new T_Operation_Log();

	/** 无参构造函数 */
	public T_Operation_Log() {
	}

	/** 有参构造函数 */
	
	public T_Operation_Log(int userId, String operation,String module) {
		set("u_id", userId);
		set("operation", operation);
		set("operation_time", DateUtils.getNowTime());
		set("operation_module",module);
		save();
	}

	/** 有参构造函数(操作类型) */
	public T_Operation_Log(int userId, String operation, int operationType) {
		set("u_id", userId);
		set("operation", operation);
		set("operation_time", new Date());
		set("operation_type", operationType);
		save();
	}

	/** 分页查回 */
	public Page<T_Operation_Log> getPage(Integer pageNum, Integer pageSize, String sdate, String edate, String userId, Integer departmentId, Integer operationType) {
		pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
		pageSize = pageSize == null || pageSize < 1 ? Constant.PAGE_SIZE : pageSize;
		StringBuffer sqlWhere = new StringBuffer();
		sqlWhere.insert(0, " where 1=1");
		if (StrKit.notBlank(userId)) {
			sqlWhere.append(" and t.u_id=" + userId);
		}
		if (sdate != null && !"".equals(sdate)) {
			sqlWhere.append(" and t.operation_time>='" + sdate + "'");
		}
		if (edate != null && !"".equals(edate)) {
			sqlWhere.append(" and t.operation_time<='" + edate + "'");
		}
		if (null != operationType && operationType > 0) {
			sqlWhere.append(" and t.operation_type =" + Integer.valueOf(operationType));
		}

		String select = "select u.dlid, u.name, t.*";
		StringBuffer sqlExceptSelect = new StringBuffer();
		if (null == departmentId) {
			sqlExceptSelect.append(" from t_operation_log t inner join t_user u on t.u_id=u.id");
		} else {
			sqlExceptSelect.append(" from t_operation_log t inner join t_user u on t.u_id=u.id inner join t_department d on u.d_id=d.id AND (d.d_pid=" + departmentId + " OR u.d_id="
					+ departmentId + ")");
		}
		sqlExceptSelect.append(sqlWhere).append(" order by t.operation_time desc");
		return dao.paginate(pageNum, pageSize, select, sqlExceptSelect.toString());
	}

}
