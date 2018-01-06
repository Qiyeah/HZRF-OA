<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<style>

form#approveForm span.wrap_bjui_btn_box{
	 width:100%; 
}

</style>
<c:if test="${errmsg != null }">
	<script type="text/javascript">
		alertMsg.error("${errmsg}");
		$.pdialog.closeCurrent();
	</script>
</c:if>
<script type="text/javascript">
	function hasFill() {
		return true;
	}
</script>
<div class="bjui-pageContent tableContent">
	<form id="approveForm" method="post" action="Main/Meeting/save" class="pageForm" data-toggle="validate">
		<div class="pageFormContent" layoutH="25">
			<input type="hidden" name="t_Meeting_Approve.id" value="0" />
			<input type="hidden" name="t_Meeting_Approve.u_id" id="userid" value="${u_id}" />
			<input type="hidden" name="t_Meeting_Approve.d_id" value="${d_id}" />
			<input type="hidden" name="t_Meeting_Approve.pid" id="pid" value="0" />
			<input type="hidden" name="t_Meeting_Approve.pstatus" value="0" />
			<input type="hidden" name="flag" value="0" />
            <div style="width: 95%;height: 50px;padding:15px;text-align:center">
                <span style="font-size:25px; margin-left:10px"><B>会议审批单</B></span>
            </div>			
			<table class="wordInfo" align="center" style="width: 95%;margin-bottom:23px">
				<tr>
					<td width="15%" class="title">申 请 人</td>
					<td width="35%">${uname}</td>
					<td width="15%" class="title">申请科室</td>
					<td width="35%">${dname}</td>
				</tr>
				<tr>
					<td class="title">申请日期</td>
					<td><input type="text" data-toggle="datepicker" name="t_Meeting_Approve.approvedate" value="${nowday}" class="required date" style="width: 100%" /></td>
					<td class="title">会议时间</td>
					<td>
                        <table>
                            <tr>
                                <td colspan="3" style="border: 0px;">
                                    <input data-toggle="datepicker" readonly type="text" class="required" name="t_Meeting_Approve.mdate" value="" style="width: 120px" />
                                </td>
                                <td style="border: 0px;">日</td>
                                <td style="border: 0px;"><select name="t_Meeting_Approve.hour" data-toggle="selectpicker">
                                    <c:forEach var="x" begin="7" end="23" step="1">
                                        <option value="${x}">${x}</option>
                                    </c:forEach>
                                    </select>
                                </td>
                                <td style="border: 0px;">时</td>
                                <td style="border: 0px;"><select name="t_Meeting_Approve.minute"  data-toggle="selectpicker">
                                    <c:forEach var="x" begin="0" end="50" step="10">
                                        <option value="${x}">${x}</option>
                                    </c:forEach>
                                    </select>
                                </td>
                                <td style="border: 0px;">分</td>
                            </tr>
                        </table>
                    </td>
				</tr>
				<tr>
					<td class="title">地　　点</td>
					<td colspan="3"><input type="text" name="t_Meeting_Approve.address" data-rule="required" class="required" style="width: 100%" /></td>				
				</tr>
				<tr>
					<td class="title">主 持 人</td>
					<td><input type="hidden" name="chairman.userid" />					
						<input name="chairman.username" type="text" suggestFields="username" suggestUrl="Main/search/searchUser" lookupGroup="chairman" style="width: 100%" class="required" data-rule="required"/></td>
					<td class="title">类　　型</td>
					<td><input type="text" name="t_Meeting_Approve.type" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">参加人员<input type="hidden" name="attendee.id" /></td>
					<td colspan="3"><input  readonly="readonly" name="attendee.name"  data-url="Main/search/searchUsers" lookupGroup="attendee" data-toggle="lookup" data-id="searchUser" data-width="500" data-height="480"
					 data-title="出席人" style="padding-right: 0px;width:100%;" />
				</tr>
				<tr>
					<td class="title">标　　题</td>
					<td colspan="3"><input type="text" name="t_Meeting_Approve.title" class="required" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">内　　容</td>
					<td colspan="3"><textarea name="t_Meeting_Approve.content" rows="6" style="width: 100%; overflow:auto"></textarea></td>
				</tr>
				<tr>
					<td class="title">附件列表</td>
					<td colspan="3">
						<div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/add.jsp" /></div>
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 80px">科　　室<br/>意　　见</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td class="title" style="height: 120px">副 主 任<br/>批　　办</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td class="title" style="height: 120px">主　　任<br/>批　　办</td>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>
			<input type="hidden" name="positionid" id="positionid" value="${positionid }" />
			<input type="hidden" name="t_Workflow.id" id="id" value="0" />
			<input type="hidden" name="t_Workflow.flowname" id="flowname" value="${wp.name}" />
			<input type="hidden" name="t_Workflow.workpath" value="${wp.path}" />
			<input type="hidden" name="t_Workflow.flowform" value="${wp.flow}" />
			<input type="hidden" name="t_Workflow.formname" value="会议申请单" />
			<input type="hidden" name="t_Workflow.title" id="title" value="${wp.name}" />
			<input type="hidden" name="t_Workflow.starter" id="starter" value="${u_id}" />
			<input type="hidden" name="t_Workflow.startdept" id="startdept" value="${d_id}" />
			<input type="hidden" name="t_Workflow.starttime" value="" />
			<input type="hidden" name="t_Workflow.reader" value="" />
			<input type="hidden" name="t_Workflow.todoman" value="${u_id}" />
			<input type="hidden" name="t_Workflow.doneuser" value="" />
			<input type="hidden" name="t_Workflow.todousers" value="" />
			<input type="hidden" name="t_Workflow.isopen" value="1" />
			<input type="hidden" name="t_Workflow.editor" value="${u_id}" />
			<input type="hidden" name="t_Workflow.mainflowid" value="" />
			<input type="hidden" name="t_Workflow.subflowid" value="" />
			<input type="hidden" name="t_Workflow.subflowname" value="${wa.subflow}" />
			<input type="hidden" name="t_Workflow.isend" value="0" />
			<input type="hidden" name="t_Workflow.isnormalend" value="0" />
			<input type="hidden" name="t_Workflow.ishold" value="0" />
			<input type="hidden" name="t_Workflow.islock" value="0" />
			<input type="hidden" name="t_Workflow.isnewdoc" value="1" />
			<input type="hidden" name="t_Workflow.itemid" id="itemid" value="${itemid}" />
			<input type="hidden" name="t_Workflow.hastemplate" value="${wp.model}" />
			<input type="hidden" name="t_Workflow.templatename" value="${wp.template}" />
			<input type="hidden" name="t_Workflow.worddocname" value="" />
			<input type="hidden" name="t_Workflow.bodyiscreated" value="0" />
			<input type="hidden" name="t_Workflow.bodyauthor" value="" />
			<input type="hidden" name="t_Workflow.bodyversion" value="" />			
			<input type="hidden" name="todomannum" value="${todomannum}" />
			<input type="hidden" name="issequence" value="0" />
			<input type="hidden" name="havesubflow" value="${wa.havesubflow}" />
			<input type="hidden" name="editword" value="${wa.editword}" />
			<input type="hidden" name="viewword" value="${wa.viewword}" />
			<input type="hidden" name="endprocess" value="${wa.endprocess}" />
			<input type="hidden" name="specialsend" value="${wa.specialsend}" />
			<input type="hidden" name="backlaststep" value="${wa.backlaststep}" />
			<input type="hidden" name="backfirststep" value="${wa.backfirststep}" />
			<input type="hidden" name="haveopinionfield" id="haveopinionfield" value="${wa.haveopinionfield}" />
			<input type="hidden" name="opinionfield" id="opinionfield" value="${wa.opinionfield}" />
			<input type="hidden" name="opinion" id="opinion" value="" />
			<input type="hidden" name="opiniontime" id="opiniontime" value="" />
			<input type="hidden" name="operation" id="operation" value="" />
			<input type="hidden" name="wid" id="wid" value="${wp.id}" />
			<input type="hidden" name="nexttodoman" id="nexttodoman" value="" />
			<input type="hidden" name="nextitemid" id="nextitemid" value="${nextitemid}" />
			<input type="hidden" name="nextstepsnum" id="nextstepsnum" value="${nextstepsnum}" />
			<input type="hidden" name="sendsms" id="sendsms" value="0" />
			<input type="hidden" name="sendemail" id="sendemail" value="0" />
			<input type="hidden" name="curuser" id="curuser" value="${u_id}" />
			<input type="hidden" name="curdept" id="curdept" value="${d_id}" />
			<input type="hidden" name="localarea" value="${localarea}" />
			<input type="hidden" name="seldept" id="seldept" value="${d_id}" />
			<input type="hidden" name="curuser1" id="curuser1" value="${uname}" />
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<c:if test="${wa.specialsend == '1'}">
			<li><a class="btn btn-blue" data-icon="plane" onclick="TeSong()">特 送 </a></li>
		</c:if>
		<li><a class="btn btn-default" data-icon="save" onclick="ZanCun()">暂存</a></li>
		<li><a class="btn btn-green" data-icon="save" onclick="BaoCunTuiChu()">保存</a></li>
		<li><a class="btn btn-blue" data-icon="check" onclick="FaSong()">提交</a></li>
	</ul>
</div>