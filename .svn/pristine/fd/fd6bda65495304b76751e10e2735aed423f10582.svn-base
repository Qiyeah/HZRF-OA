package com.oa.model.log;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhilian.config.Constant;

import java.util.Date;

/**
 * 操作记录
 * @Author Andersen
 * mail: yawen199@163.com
 * Date: 15-4-15
 * Time: 下午4:33
 */
public class Operating_Record  extends Model<Operating_Record> {
    private static final long serialVersionUID = -1L;
    public static final Operating_Record dao = new Operating_Record();

    /**无参构造函数*/
    public Operating_Record(){}

    /**有参构造函数*/
    public Operating_Record(int userId, String operation){
        set("u_id", userId);
        set("operation", operation);
        set("operation_time", new Date());
        save();
    }

    /**有参构造函数(操作类型)*/
    public Operating_Record(int userId, String operation,int operationType){
        set("u_id", userId);
        set("operation", operation);
        set("operation_time", new Date());
        set("operation_type", operationType);
        save();
    }

    /**分页查回*/
    public Page<Operating_Record> getPage(Integer pageNum,Integer pageSize,String sdate,String edate,String userId,Integer departmentId,Integer operationType){
        pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize < 1 ? Constant.PAGE_SIZE : pageSize;
        StringBuffer sqlWhere = new StringBuffer();
        sqlWhere.insert(0, " where 1=1");
        if(StrKit.notBlank(userId)){
            sqlWhere.append(" and t.u_id=" + userId);
        }
        if(sdate!=null&&!"".equals(sdate)){
            sqlWhere.append(" and t.operation_time>='" + sdate + "'");
        }
        if(edate!=null&&!"".equals(edate)){
            sqlWhere.append(" and t.operation_time<='" + edate + "'");
        }
        if(null != operationType && operationType>0){
            sqlWhere.append(" and t.operation_type ="+Integer.valueOf(operationType));
        }

        String select = "select u.dlid, u.name, t.*";
        StringBuffer sqlExceptSelect = new StringBuffer();
        if(null == departmentId){
            sqlExceptSelect.append(" from operating_record t inner join t_user u on (t.u_id=u.id)");
        }else{
            sqlExceptSelect.append(" from operating_record t inner join t_user u on (t.u_id=u.id) inner join t_department d on (u.d_id=d.id AND (d.d_pid="+departmentId+" OR u" +
                    ".d_id="+departmentId+"))");
        }
        sqlExceptSelect.append(sqlWhere).append(" order by t.operation_time desc");
        return dao.paginate(pageNum, pageSize, select, sqlExceptSelect.toString());
    }

}
