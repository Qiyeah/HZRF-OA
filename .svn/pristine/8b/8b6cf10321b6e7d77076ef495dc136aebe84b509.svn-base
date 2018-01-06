<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<form method="post" action="Main/employee_train/add" class="pageForm required-validate" onsubmit="return validateCallback(this, reloadAjaxDone);">
	<input type="hidden" name="t_Employee_Train.eid" value="${employee.id }"/>
	<div class="pageFormContent" >
		<table class="wordInfo" align="center">
			<tr><td style="border: 0px;font-size: 15;" class="title" colspan="4">培训记录</td></tr>
			<tr>
				<td class="title">培训日期</td>
				<td><input name="t_Employee_Train.time" class="date" readonly type="text" /></td>
				<td class="title">培训项目</td>
				<td><input name="t_Employee_Train.item" type="text"/></td>
			</tr>
			<tr>
				<td class="title">培训内容</td>
				<td><input name="t_Employee_Train.content" type="text" /></td>
				<td class="title">培训结果</td>
				<td><input name="t_Employee_Train.result" type="text" /></td>
			</tr>
			<tr>
				<td class="title">证书号</td>
				<td><input name="t_Employee_Train.certificate_no" type="text"/></td>
				<td class="title">备注</td>
				<td><input name="t_Employee_Train.remark" type="text"/></td>
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
