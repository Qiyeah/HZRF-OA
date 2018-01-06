<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<form method="post" action="Main/employee_education/add" class="pageForm required-validate" onsubmit="return validateCallback(this, reloadAjaxDone);">
	<input type="hidden" name="t_Employee_Education.eid" value="${employee.id }"/>
	<div class="pageFormContent" >
		<table class="wordInfo" align="center">
			<tr><td style="border: 0px;font-size: 15;" class="title" colspan="4">教育培训</td></tr>
			<tr>
				<td class="title">时间</td>
				<td><input name="t_Employee_Education.time" class="date" readonly type="text" /></td>
				<td class="title">主办单位</td>
				<td><input name="t_Employee_Education.unit" type="text"/></td>
			</tr>
			<tr>
				<td class="title">学习内容</td>
				<td><input name="t_Employee_Education.content" type="text" /></td>
				<td class="title">课时</td>
				<td><input name="t_Employee_Education.hour" type="text" /></td>
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
