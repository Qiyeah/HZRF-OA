<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<form method="post" action="Main/employee_train/update" class="pageForm required-validate" onsubmit="return validateCallback(this, reloadAjaxDone);">
	<input type="hidden" name="t_Employee_Train.id" value="${train.id }"/>
	<input type="hidden" name="eid" value="${train.eid }"/>
	<div class="pageFormContent" >
		<table class="wordInfo" align="center">
			<tr><td style="border: 0px;font-size: 15;" class="title" colspan="4">培训记录</td></tr>
			<tr>
				<td class="title">培训日期</td>
				<td><input name="t_Employee_Train.time"  class="date" readonly type="text" value="${train.time }"/></td>
				<td class="title">培训项目</td>
				<td><input name="t_Employee_Train.item"  type="text" value="${train.item }"/></td>
			</tr>
			<tr>
				<td class="title">培训内容</td>
				<td><input name="t_Employee_Train.content" type="text" value="${train.content }"/></td>
				<td class="title">培训结果</td>
				<td><input name="t_Employee_Train.result" type="text" value="${train.result }" /></td>
			</tr>
			<tr>
				<td class="title">证书号</td>
				<td><input name="t_Employee_Train.certificate_no" type="text" value="${train.certificate_no }"/></td>
				<td class="title">备注</td>
				<td><input name="t_Employee_Train.remark" type="text" value="${train.remark }"/></td>
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
