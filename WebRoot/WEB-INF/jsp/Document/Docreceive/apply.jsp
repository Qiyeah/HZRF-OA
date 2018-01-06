<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>

<style>
<!--
form#approveForm span.wrap_bjui_btn_box {
	width: 100%;
}
-->
</style>
<script type="text/javascript">
	function hasFill() {
		return true;
	}

	function FaSongCheck() {
		if ($("#doflag").val() == '0') {
			$(this).alertmsg("warn", "请选择文件的处理类型！");
			return false;
		} else if ($("#doflag").val() == '1') {
			$("#nextstepsnum").val("1");
			$("#nextitemid").val("105");
		} else {
			$("#nextstepsnum").val("${nextstepsnum}");
			$("#nextitemid").val("${nextitemid}");
		}
		FaSong();

	}
</script>
<div class="bjui-pageContent tableContent">
	<form id="approveForm" action="Main/Docreceive/save" method="post" data-toggle="validate">
		<input type="hidden" name="applyFlg" value="1" /> 
		<input type="hidden" name="t_Doc_Receive.id" value="0" /> 
		<input type="hidden" name="t_Doc_Receive.u_id" id="userid" value="${u_id}" /> 
		<input type="hidden" name="t_Doc_Receive.d_id" value="${d_id}" /> 
		<input type="hidden" name="t_Doc_Receive.achieveid" value="${dc.id}" /> 
		<input type="hidden" name="t_Doc_Receive.pid" id="pid" value="0" /> 
		<input type="hidden" name="t_Doc_Receive.degree" value="${dc.degree}" /> 
		<input type="hidden" name="t_Doc_Receive.pstatus" value="0" /> 
		<input type="hidden" name="t_Doc_Receive.type" value="${dc.type }" /> 
		<input type="hidden" name="t_Doc_Receive.receiver" value="${dc.u_id}" /> 
		<input type="hidden" name="flag" value="0" />
		<div style="width: 100%; height: 60px; padding: 15px; text-align: center">
			<span style="font-size: 30px; margin-left: 10px"><B>文件呈批表</B></span>
		</div>
		<table class="wordInfo" align="center" style="width: 95%; margin-bottom: 6px">
			<tr>
				<td width="15%" class="title">来文单位</td>
				<td width="25%" colspan="2"><input name="t_Doc_Receive.unit" type="text" value="${dc.unit}" class="required" data-msg-required="必填" data-rule="required" maxlength="50"
					style="width: 100%" /></td>
				<td width="13%" class="title">收文日期</td>
				<td width="17%"><input name="t_Doc_Receive.receivedate" type="text" value="${dc.receivedate}" data-toggle="datepicker" class="required date" data-rule="required"
					style="width: 100%" /></td>
				<td width="12%" class="title">办文编号</td>
				<td width="18%"><input name="t_Doc_Receive.docno" type="text" value="${dc.docno}" maxlength="50" style="width: 100%" /></td>
			</tr>
			<tr>
				<td class="title">经办人</td>
				<td colspan="2">${receiver}</td>
				<td class="title">分办人</td>
				<td><input name="zhk.auditor" type="text" suggestFields="auditor" suggestUrl="Main/search/auditor" lookupGroup="zhk" value="${uname}" maxlength="50" style="width: 100%"
					style="text-align: center;" /></td>
				<td class="title">处理类型</td>
				<td><select name="t_Doc_Receive.doflag" id="doflag" data-toggle="selectpicker" data-width="100%">
						<option value="2" <c:if test="${dc.doflag=='2'}">selected</c:if>>普通收文</option>
						<option value="1" <c:if test="${dc.doflag=='1'}">selected</c:if>>一般阅知</option>
				</select></td>
			</tr>
			<tr>
				<td class="title">密 级</td>
				<td width="15%"><select name="t_Doc_Receive.security" data-style="selectrequired" data-msg-required="必填" data-rule="required" data-toggle="selectpicker" data-width="100%">
						<option value="无" <c:if test="${dc.security=='无' }">selected="selected"</c:if>>无</option>
						<option value="秘密" <c:if test="${dc.security=='秘密' }">selected="selected"</c:if>>秘密</option>
				</select></td>
				<td class="title" rowspan="2">标 题</td>
				<td colspan="4" rowspan="2" class="normal" align="center"><input name="t_Doc_Receive.title" type="text" value="${dc.title}" data-rule="required" class="required" 
					data-msg-required="必填" maxlength="250" style="font-size: 20px; width: 100%; line-height: 30px<c:if test="${dc.degree!='0'}">; color: red</c:if>" /></td>
			</tr>
			<tr>
				<td class="title">份 数</td>
				<td><input name="t_Doc_Receive.count" value="${dc.count }" type="text" data-rule="number" style="width: 100%" style="text-align: center;" /></td>
			</tr>
			<tr>
				<td class="title">文件列表</td>
				<td colspan="6"><div id="uploadFile" class="unitBox">
						<c:import url="../../Common/Attachment/edit.jsp" />
					</div></td>
			</tr>
            <tr>
                <td class="title">备 注</td>
                <td colspan="6"><textarea name="t_Doc_Receive.memo" rows="2" maxlength="250" style="width: 100%;">${dc.memo}</textarea></td>
            </tr>
			<tr>
				<td class="title"><br>批办意见<br>
				<br>及<br>
				<br>领导批示<br></td>
				<td colspan="6"><div id="opinion1_1">${opinion1 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion1'}">
						<div class="noprint">
							<br /> <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a> <a class="btn btn-blue" data-icon="fa-check"
								onclick="FaSongCheck()" style="margin-left: 5px;">提交</a>
						</div>
					</c:if></td>
			</tr>
			<tr>
				<td class="title" style="height: 150px;"><br>传<br>
				<br>阅<br>
				<br>签<br>
				<br>名<br></td>
				<td colspan="6"><div id="opinion2_1">${opinion2}</div> <c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion2'}">
						<div class="noprint">
							<br /> <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
							<c:if test="${wa.sealword == '1'}">
								<a class="btn btn-blue" data-icon="fa-tag" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
							</c:if>
							<c:if test="${todomannum > 1}">
								<c:if test="${wa.amount != 3}">
									<a class="btn btn-blue" data-icon="fa-check" onclick="ChuLiWanBi()" style="margin-left: 5px;">提交</a>
								</c:if>
								<c:if test="${wa.amount == 3}">
									<a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
								</c:if>
							</c:if>
							<c:if test="${todomannum <= 1}">
								<c:if test="${wa.atype == '3'}">
									<a class="btn btn-default" data-icon="fa-save" onclick="WanCheng()" style="margin-left: 5px;">完成</a>
								</c:if>
								<c:if test="${wa.atype != '3'}">
									<a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
								</c:if>
							</c:if>
						</div>
					</c:if></td>
			</tr>
		</table>
		<input type="hidden" name="positionid" id="positionid" value="${positionid }" /> 
		<input type="hidden" name="t_Workflow.id" id="id" value="0" /> 
		<input type="hidden" name="t_Workflow.flowname" id="flowname" value="${wp.name}" /> 
		<input type="hidden" name="t_Workflow.workpath" value="${wp.path}" /> 
		<input type="hidden" name="t_Workflow.flowform" value="${wp.flow}" /> 
		<input type="hidden" name="t_Workflow.formname" value="收文处理单" /> 
		<input type="hidden" name="t_Workflow.title" value="${wp.name}" /> 
		<input type="hidden" name="t_Workflow.starter" id="starter" value="${u_id}" /> 
		<input type="hidden" name="t_Workflow.startdept" value="${d_id}" /> 
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
		<input type="hidden" name="seldept"	id="seldept" value="${d_id}" /> 
		<input type="hidden" name="curuser1" id="curuser1" value="${uname}" /> 
		<input type="hidden" name="opinion1" id="opinion1" />
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<c:if test="${wa.specialsend == '1'}">
			<li><a class="btn btn-default" data-icon="fa-plane" onclick="TeSong()">特 送</a></li>
		</c:if>
		<li><a class="btn btn-blue" data-icon="check" data-icon="check" onclick="FaSongCheck()">提 交</a></li>
	</ul>
</div>