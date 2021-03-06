<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<form method="post" action="Main/employee_duty/update" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, reloadAjaxDone);">
	<input type="hidden" name="t_Employee_Duty.id" value="${duty.id }"/>
	<input type="hidden" name="eid" value="${duty.eid }"/>
	<input type="hidden" name="oldOriginal" value="${duty.original }"/>
	<div class="pageFormContent" >
		<table class="wordInfo" align="center">
			<tr><td style="border: 0px;font-size: 15;" class="title" colspan="4">员工技术档案卡</td></tr>
			<tr>
				<td class="title">证件号</td>
				<td><input name="t_Employee_Duty.no" type="text" value="${duty.no }"/></td>
				<td class="title">发件单位</td>
				<td><input name="t_Employee_Duty.unit" type="text" value="${duty.unit }"/></td>
			</tr>
			<tr>
				<td class="title">级　别</td>
				<td><input name="t_Employee_Duty.level" type="text" value="${duty.level }"/></td>
				<td class="title">有效期</td>
				<td><input name="t_Employee_Duty.deadline" readonly type="text" class="date" value="${duty.deadline }"/></td>
			</tr>
			<tr>
				<td class="title">复印件</td>
				<td><input name="t_Employee_Duty.copy" type="text" value="${duty.copy }"/></td>
				<td class="title">原　件</td>
				<td><input name="original" type="file"/></td>
			</tr>
		</table>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
