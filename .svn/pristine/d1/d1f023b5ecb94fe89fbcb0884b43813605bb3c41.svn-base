<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<form method="post" action="Main/employee_rewards/add" class="pageForm required-validate" onsubmit="return validateCallback(this, reloadAjaxDone);">
	<input type="hidden" name="t_Employee_Rewards.eid" value="${employee.id }"/>
	<div class="pageFormContent" >
		<table class="wordInfo" align="center">
			<tr><td style="border: 0px;font-size: 15;" class="title" colspan="2">奖惩项目</td></tr>
			<tr>
				<td class="title">时间</td>
				<td><input name="t_Employee_Rewards.time" class="date" readonly type="text" /></td>
			</tr>
			<tr>
				<td class="title">具体内容</td>
				<td><input name="t_Employee_Rewards.content" type="text" size="50"  /></td>
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
