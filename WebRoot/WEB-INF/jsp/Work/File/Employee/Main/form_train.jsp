<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<table class="wordInfo" align="center" style="margin-top:20px;">
	<tr align="center">
		<td class="title">姓名</td>
		<td colspan="2">${employee.name }</td>
		<td class="title">所在科室</td>
		<td colspan="2">${employee.department }</td>
	</tr>
	<tr align="center">
		<td class="title">职务/职称</td>
		<td colspan="2">${employee.duty }</td>
		<td class="title">入单位时间</td>
		<td colspan="2">${employee.indate }</td>
	</tr>
	<tr>
		<td class="title">培训日期</td>
		<td class="title">培训项目</td>
		<td class="title">培训内容</td>
		<td class="title">培训结果</td>
		<td class="title">证书号</td>
		<td class="title">备注</td>
	</tr>
	<c:forEach items="${trains }" var="train">
		<tr align="center">
			<td>${train.time }</td>
			<td>${train.item }</td>
			<td>${train.content }</td>
			<td>${train.result }</td>
			<td>${train.certificate_no }</td>
			<td>${train.remark }</td>
		</tr>
	</c:forEach>
</table>
<br/><br/>