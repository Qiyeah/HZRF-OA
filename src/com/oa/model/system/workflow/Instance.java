package com.oa.model.system.workflow;

public class Instance {

	private T_Workflow workflow;
	private T_Wactive wactive;
	private String wid = "";
	private String curuser = "";
	private String curdept = "";
	private String localarea = "";
	private String nextstepsnum = "";
	private String nextitemid = "";
	private String nexttodoman = "";
	private String sendsms = "";
	private String sendemail = "";
	private String operation = "";
	private String operationtime = "";
	private String opinionfield = "";
	private String opinion = "";
	private String opiniontime = "";

	public T_Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(T_Workflow workflow) {
		this.workflow = workflow;
	}

	public T_Wactive getWActive() {
		return wactive;
	}

	public void setWActive(T_Wactive wactive) {
		this.wactive = wactive;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public String getCuruser() {
		return curuser;
	}

	public void setCuruser(String curuser) {
		this.curuser = curuser;
	}

	public String getCurdept() {
		return curdept;
	}

	public void setCurdept(String curdept) {
		this.curdept = curdept;
	}

	public String getLocalarea() {
		return localarea;
	}

	public void setLocalarea(String localarea) {
		this.localarea = localarea;
	}

	public String getNextstepsnum() {
		return nextstepsnum;
	}

	public void setNextstepsnum(String nextstepsnum) {
		this.nextstepsnum = nextstepsnum;
	}

	public String getNextitemid() {
		return nextitemid;
	}

	public void setNextitemid(String nextitemid) {
		this.nextitemid = nextitemid;
	}

	public String getNexttodoman() {
		return nexttodoman;
	}

	public void setNexttodoman(String nexttodoman) {
		this.nexttodoman = nexttodoman;
	}

	public String getSendsms() {
		return sendsms;
	}

	public void setSendsms(String sendsms) {
		this.sendsms = sendsms;
	}

	public String getSendemail() {
		return sendemail;
	}

	public void setSendemail(String sendemail) {
		this.sendemail = sendemail;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperationtime() {
		return operationtime;
	}

	public void setOperationtime(String operationtime) {
		this.operationtime = operationtime;
	}

	public String getOpinionfield() {
		return opinionfield;
	}

	public void setOpinionfield(String opinionfield) {
		this.opinionfield = opinionfield;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getOpiniontime() {
		return opiniontime;
	}

	public void setOpiniontime(String opiniontime) {
		this.opiniontime = opiniontime;
	}

}