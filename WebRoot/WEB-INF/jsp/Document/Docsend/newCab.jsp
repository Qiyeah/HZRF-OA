<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	function hasFill() {
		return true;
	}
	function unitwh() { // 编辑文号
	    $("#wh").val($("#wh1").val() + $("#wh2").val() + $("#wh3").val() + $("#wh4").val());
	}
	function BianJiZhengWen() {
		var templateid = $("#templateid").val();
		var title = $("#title").val();
		var docno = $("#wh").val();
		var send1 = $("#send1").val();
		var send2 = $("#send2").val();
		var num = $("#num").val();
		var url = encodeURI("Main/Office/edit?RecordID=${tempid}&EditType=1,0&Template=" + templateid + "&Title=" + title + "&Docno=" + docno + "&Send1=" + send1 + "&Send2=" + send2 + "&Num=" + num);		
		$(this).dialog({id:'editword',url:url, title:'正文拟稿', mask:'true',drawable:'true',resizable:'false',maxable:'false',max:'true'});
	}
</script>
<div class="bjui-pageContent">
	  <form id="approveForm" action="Main/Docsend/saveCab" method="post" data-toggle="validate" >   
		<div class="pageFormContent" layoutH="25">
			<input type="hidden" name="t_Doc_Send.id" value="0" />
			<input type="hidden" name="t_Doc_Send.u_id" id="userid" value="${u_id}" />
			<input type="hidden" name="t_Doc_Send.d_id" value="${d_id}" />
			<input type="hidden" name="t_Doc_Send.pid" id="pid" value="0" />
			<input type="hidden" name="t_Doc_Send.pstatus" value="0" />
			<input type="hidden" name="tempid" value="${tempid}" />
			<input type="hidden" name="flag" value="0" />			
			<div style="width: 100%;height: 40px;text-align:center">
		    	<span style="font-size:25px; margin-left:10px"><B>发文呈批笺</B></span>
			</div>
			<table class="wordInfo" align="center" style="width: 98%">				
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">拟稿科室</td>
					<td >
					    <input name="zrks.id" id="selectdept" type="hidden" value="">
						<input name="zrks.department"  type="text" suggestFields="department"  suggestUrl="Main/search/searchDept" lookupGroup="zrks" style="width: 100%" />
				    </td>
					
				</tr>
				<tr>				    
					<td class="title" style="height: 40px;font-size:15px;">拟稿人<input name="jbr.userid" type="hidden" value=""></td>
					<td><input name="jbr.username" type="text" suggestFields="username" suggestUrl="Main/search/searchUser" lookupGroup="jbr" style="width: 100%" /></td>
					
				</tr>
				<tr>
					<td  class="title" style="height: 40px;font-size:15px;">校稿人</td>
					<td ><input name="jbr1.username" type="text" suggestFields="username" suggestUrl="Main/search/searchUser" lookupGroup="jbr1"  style="width: 100%" /></td>
					
				</tr>
				<tr>
					<td  class="title" style="height: 40px;font-size:15px;">份　数</td>
					<td ><input name="t_Doc_Send.num" id="num" class="number" type="text"  style="width: 10%" /> 份</td>
				</tr>
				<tr>
					<td class="title" style="height: 80px;font-size:15px;">文　号</td>
					<td><select id="wh1" name="wh1" data-toggle="selectpicker" onchange="unitwh()">
					    <option value="">请选择</option>
						<option value="惠人防">惠人防</option>
						<option value="会议纪要">会议纪要</option>
					</select>
					<input id="wh2" name="wh2" value="〔${nowyear}〕" size="8" onchange="unitwh()" />
					<input id="wh3" name="wh3" value="" size="4" onchange="unitwh()" />
					<select id="wh4" name="wh4" data-toggle="selectpicker" onchange="unitwh()">
						<option value="号">号</option>
						<option value="期">期</option>
					</select><br><br>
					<input type="text" name="t_Doc_Send.docno" id="wh" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">拟稿时间</td>
					<td ><input name="t_Doc_Send.approvedate" type="text" value="" class="required" data-toggle="datepicker" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" size="15" /></td>
				</tr>
				
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">主　送</td>
					<td >
					<input name="sendDept1.send1" id="send1" type="text"  suggestFields="send1" suggestUrl="Main/search/send1" lookupGroup="sendDept1" value="${t_Doc_Send.send1}" style="width: 100%" />
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">抄　送</td>
					<td >
					<input name="sendDept2.send2" id="send2" type="text"  suggestFields="send2" suggestUrl="Main/search/send2" lookupGroup="sendDept2" value="${t_Doc_Send.send2}" style="width: 100%" />
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 60px;font-size:15px;">文件标题</td>
					<td ><input name="t_Doc_Send.title" id="title" type="text" class="required" data-rule="required" data-msg-required="必填" style="font-family: '方正小标宋'; font-size: 18px; font-weight: bold; width: 100%;height: 25px;" /></td>
				</tr>
				<tr>
					<td class="title">附件列表</td>
					<td >
						<div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/add.jsp" /></div>
					</td>
				</tr>
			</table>
			<input type="hidden" name="t_Workflow.id" id="id" value="0" />
			<input type="hidden" name="t_Workflow.flowname" id="flowname" value="${wp.name}" />
			<input type="hidden" name="t_Workflow.workpath" value="${wp.path}" />
			<input type="hidden" name="t_Workflow.flowform" value="${wp.flow}" />
			<input type="hidden" name="t_Workflow.formname" value="发文处理单" />
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
			<input type="hidden" name="seldept" id="seldept" value="${d_id}" />
			<input type="hidden" name="curuser1" id="curuser1" value="${uname}" />
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<li><button type="button" class="btn-default" onclick="ZanCun()" data-icon="save">保存</button></li>
	</ul>
</div>