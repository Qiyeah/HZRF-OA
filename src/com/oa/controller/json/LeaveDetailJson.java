package com.oa.controller.json;

import java.util.List;

public class LeaveDetailJson {

    private String id; // 记录ID
    private String u_id; // 申请人
    private String d_id; // 所属部门
    private String married;// 婚姻状态
    private String type;// 请假类型
    private String approvedate;// 申请日期
    private String begindate;// 开始日期
    private String enddate;// 结束日期
    private String backdate;// 销假日期
    private String workyear;// 工龄
    private String daye;
    private String dayn;
    private String dayc;
    private String dayz;
    private String dayg;
    private String dayt; // 请假预计天数
    private String days; // 请假实际天数
    private String reason;
    private List<T_FJList> fjlist;// 附件列表
    private String opinion1;// 审核意见
    private String opinion2;// 审批意见
    private String activename;// 环节名称
    private String opinionfield;// 意见域
    private String atype;// 环节类型 3是结束
    private WorkflowJson wf;
    private String opinion;// 判断当前环节当前处理人是否有填写意见
    private String itemid;
    private String uname;// 用户名称
    private String dname;// 部门名称    
    private String[] opinions;// 常用意见
    private String backlaststep;// 退回
    private String tempopinion1;
    private String tempopinion2;
    private int positionId;// 职位id
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getU_id() {
        return u_id;
    }
    public void setU_id(String u_id) {
        this.u_id = u_id;
    }
    public String getD_id() {
        return d_id;
    }
    public void setD_id(String d_id) {
        this.d_id = d_id;
    }
    public String getMarried() {
        return married;
    }
    public void setMarried(String married) {
        this.married = married;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getApprovedate() {
        return approvedate;
    }
    public void setApprovedate(String approvedate) {
        this.approvedate = approvedate;
    }
    public String getBegindate() {
        return begindate;
    }
    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }
    public String getEnddate() {
        return enddate;
    }
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    public String getBackdate() {
        return backdate;
    }
    public void setBackdate(String backdate) {
        this.backdate = backdate;
    }
    public String getWorkyear() {
        return workyear;
    }
    public void setWorkyear(String workyear) {
        this.workyear = workyear;
    }
    public String getDaye() {
        return daye;
    }
    public void setDaye(String daye) {
        this.daye = daye;
    }
    public String getDayn() {
        return dayn;
    }
    public void setDayn(String dayn) {
        this.dayn = dayn;
    }
    public String getDayc() {
        return dayc;
    }
    public void setDayc(String dayc) {
        this.dayc = dayc;
    }
    public String getDayz() {
        return dayz;
    }
    public void setDayz(String dayz) {
        this.dayz = dayz;
    }
    public String getDayg() {
        return dayg;
    }
    public void setDayg(String dayg) {
        this.dayg = dayg;
    }
    public String getDayt() {
        return dayt;
    }
    public void setDayt(String dayt) {
        this.dayt = dayt;
    }
    public String getDays() {
        return days;
    }
    public void setDays(String days) {
        this.days = days;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public List<T_FJList> getFjlist() {
        return fjlist;
    }
    public void setFjlist(List<T_FJList> fjlist) {
        this.fjlist = fjlist;
    }
    public String getOpinion1() {
        return opinion1;
    }
    public void setOpinion1(String opinion1) {
        this.opinion1 = opinion1;
    }
    public String getOpinion2() {
        return opinion2;
    }
    public void setOpinion2(String opinion2) {
        this.opinion2 = opinion2;
    }
    public String getActivename() {
        return activename;
    }
    public void setActivename(String activename) {
        this.activename = activename;
    }
    public String getOpinionfield() {
        return opinionfield;
    }
    public void setOpinionfield(String opinionfield) {
        this.opinionfield = opinionfield;
    }
    public String getAtype() {
        return atype;
    }
    public void setAtype(String atype) {
        this.atype = atype;
    }
    public WorkflowJson getWf() {
        return wf;
    }
    public void setWf(WorkflowJson wf) {
        this.wf = wf;
    }
    public String getOpinion() {
        return opinion;
    }
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
    public String getItemid() {
        return itemid;
    }
    public void setItemid(String itemid) {
        this.itemid = itemid;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    
    public String getDname() {
        return dname;
    }
    public void setDname(String dname) {
        this.dname = dname;
    }
    public String[] getOpinions() {
        return opinions;
    }
    public void setOpinions(String[] opinions) {
        this.opinions = opinions;
    }
    public String getBacklaststep() {
        return backlaststep;
    }
    public void setBacklaststep(String backlaststep) {
        this.backlaststep = backlaststep;
    }
    public String getTempopinion1() {
        return tempopinion1;
    }
    public void setTempopinion1(String tempopinion1) {
        this.tempopinion1 = tempopinion1;
    }
    public String getTempopinion2() {
        return tempopinion2;
    }
    public void setTempopinion2(String tempopinion2) {
        this.tempopinion2 = tempopinion2;
    }
    public int getPositionId() {
        return positionId;
    }
    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
   
}