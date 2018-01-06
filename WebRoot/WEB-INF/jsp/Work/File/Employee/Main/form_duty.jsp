<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<table class="wordInfo" align="center" style="margin-top:20px;">
	<tr align="center">
		<td class="title">姓名</td>
		<td colspan="2">${employee.name }</td>
		<td class="title">性别</td>
		<td colspan="2"><c:if test="${employee.sex==0 }">女</c:if><c:if test="${employee.sex==1 }">男</c:if></td>
	</tr>
	<tr align="center">
		<td class="title">年龄</td>
		<td colspan="2">${employee.age }</td>
		<td class="title">籍贯</td>
		<td colspan="2">${employee.brithplace }</td>
	</tr>
	<tr align="center">
		<td class="title">所学专业</td>
		<td colspan="2">${employee.major }</td>
		<td class="title">文件程度</td>
		<td colspan="2">${employee.education }</td>
	</tr>
	<tr align="center">
		<td class="title">毕业时间</td>
		<td colspan="2">${employee.graduation }</td>
		<td class="title">毕业学校</td>
		<td colspan="2">${employee.school }</td>
	</tr>
	<tr align="center">
		<td class="title">岗位</td>
		<td colspan="2">${employee.job }</td>
		<td class="title">从事本专业时间</td>
		<td colspan="2">${employee.majortime }</td>
	</tr>
	<tr><td colspan="6" class="title">专业资质、职称证书登记</td></tr>
	<tr align="center">
		<td class="title">证件号</td>
		<td class="title">发证单位</td>
		<td class="title">级别</td>
		<td class="title">有效期</td>
		<td class="title">复印件</td>
		<td class="title">原件</td>
	</tr >
	<c:forEach items="${dutys }" var="duty">
		<tr align="center">
			<td>${duty.no }</td>
			<td>${duty.unit }</td>
			<td>${duty.level }</td>
			<td>${duty.deadline }</td>
			<td>${duty.copy }</td>
			<td><c:if test="${not empty duty.original }"><a href="Main/employee_duty/download/${duty.id}" style="color:blue;">点击下载</a></c:if></td>
		</tr>
	</c:forEach>
</table>
<br/><br/>
