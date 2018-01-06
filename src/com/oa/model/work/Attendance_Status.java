package com.oa.model.work;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * 考勤状态
 * @author Ansersen
 * @date 2015-2-1 16:11:30
 */
public class Attendance_Status extends Model<Attendance_Status> {
    private static final long serialVersionUID = -1L;
    public static final Attendance_Status dao = new Attendance_Status();

    /**
     * 通过ID获取记录
     * @param id 记录ID
     * @return 记录
     */
    public static Attendance_Status getById(int id){
        String sql = "select a.*,u.name as name,d.fname as deptName from attendance_status a ,t_user u,t_department d " +
                "WHERE a.u_id=u.id AND a.d_id = d.id AND a.id =" + id;
        return dao.findFirst(sql);
    }

    public List<Attendance_Status> attenceStatistics(String select,String sqlExceptSelect){
        select +=",sum((case when (a.am_on_status='3' AND a.pm_on_status='3') then 2 "+
                           " when (a.am_on_status='3' OR a.pm_on_status='3') then 1 else 0 end ))as late ,"+
                 " sum((case when (a.am_off_status='4' AND a.pm_off_status='4') then 2 "+
                           " when (a.am_off_status='4' OR a.pm_off_status='4') then 1 else 0 end ))as leave_early,"+
                 " sum((case when (a.am_on_status='5' OR a.am_off_status='5' OR a.pm_on_status='5' OR a.pm_off_status='5') then 1 else 0 end)) as absence ";
        return dao.find(select+sqlExceptSelect);
    }
}
