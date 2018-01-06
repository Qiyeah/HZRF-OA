<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<form method="post" action="Main/employee_work/update" class="pageForm required-validate" onsubmit="return validateCallback(this, reloadAjaxDone);">
	<input type="hidden" name="t_Employee_Work.id" value="${work.id }"/>
	<input type="hidden" name="eid" value="${work.eid }"/>
	<div class="pageFormContent" >
		<table class="wordInfo" align="center">
			<tr><td style="border: 0px;font-size: 15;" class="title" colspan="4">工作业绩</td></tr>
			<tr>
				<td class="title">时间</td>
				<td><input name="t_Employee_Work.time"  class="date" readonly type="text" value="${work.time }"/></td>
				<td class="title">职称</td>
				<td><input name="t_Employee_Work.duty"  type="text" value="${work.duty }"/></td>
			</tr>
			<tr>
				<td class="title">工作岗位</td>
				<td><input name="t_Employee_Work.duty_place" type="text" value="${work.duty_place }" /></td>
				<td class="title">工作内容</td>
				<td><input name="t_Employee_Work.content" type="text" value="${work.content }"/></td>
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
