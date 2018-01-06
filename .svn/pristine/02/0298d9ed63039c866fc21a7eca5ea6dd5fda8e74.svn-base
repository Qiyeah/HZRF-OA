<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
<!-- 
	function hasFill() {
		return true;
	}
	function ZanCunCheck() {
	    if($("#doflag").val() == 0){
	       alert("请选择文件的处理类型");
	    } else{
	      ZanCun();
	    }
		
	}
//-->
</script>
<div class="bjui-pageContent">
		<form id="approveForm" class="pageForm" action="Main/Docreceive/saveCab" method="post" data-toggle="validate">
			<input type="hidden" name="t_Doc_Receive.id" value="0" />
			<input type="hidden" name="t_Doc_Receive.u_id" id="userid" value="${u_id}" />
			<input type="hidden" name="t_Doc_Receive.d_id" value="${d_id}" />
			<input type="hidden" name="t_Doc_Receive.pid" id="pid" value="0" />
			<input type="hidden" name="t_Doc_Receive.pstatus" value="0" />
			<input type="hidden" name="flag" value="0" />
			<div style="width: 95%;height: 40px;text-align:center">
		    <span style="font-size:25px; margin-left:10px">收文记录上传</span>		    
			<br/>
		    </div>
			<table class="wordInfo" align="center" style="width: 98%">
				<tr>
					<td width="15%" class="title">来文单位</td>
					<td width="35%"><input name="t_Doc_Receive.unit" type="text" class="required" data-rule="required" data-msg-required="必填" style="width: 100%" /></td>
					<td width="15%" class="title">文　　号</td>
					<td width="35%"><input name="t_Doc_Receive.docno" type="text" value="〔${nowyear}〕 号"  style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">来文日期</td>
					<td><input name="t_Doc_Receive.receivedate" type="text" value=""  class="required" data-toggle="datepicker" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" style="width: 100%" /></td>
					<td class="title">处理类型</td>
					<td><select name="t_Doc_Receive.doflag" id = "doflag" class="combox" data-toggle="selectpicker">
					    <option value="0" >请选择</option>
						<option value="1">一般阅知</option>
						<option value="2">普通收文</option>
						<option value="3">征求意见函</option>
					</select></td>
				</tr>
				<tr>
					<td class="title">来文标题</td>
					<td colspan="3"><input name="t_Doc_Receive.title" class="required" data-rule="required" data-msg-required="必填" type="text" style="font-size:20px;width: 100%;height: 40px;"  value=""  /></td>
				</tr>
				<tr>
					<td class="title">文件列表</td>
					<td colspan="3"><div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/add.jsp" /></div></td>
				</tr>
				
				<tr>
					<td class="title">承办科室</td>
					<td colspan="3"><input name="zrks.id" id="selectdept" type="hidden" value="${dc.receive1 }">
						<input name="zrks.department"  type="text" suggestFields="department"  suggestUrl="Main/search/searchDept" lookupGroup="zrks" style="width: 100%" />
					</td>
					
				</tr>
			</table>
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
			<input type="hidden" name="haveopinionfield" id="haveopinionfield" value="0" />
			<input type="hidden" name="opinionfield" id="opinionfield" value="" />
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
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li>
			<button type="button" class="btn btn-close" data-icon="close">关闭</button></li>
		<li>
			<button type="button" onclick="WanCheng()" class="btn btn-default" data-icon="save">保存</button></li>
	</ul>
</div>