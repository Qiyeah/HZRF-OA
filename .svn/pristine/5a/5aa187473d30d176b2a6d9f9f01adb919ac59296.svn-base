<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<table class="wordInfo" align="center" style="margin-top:20px;">
	<tr align="center">
		<td align="center"><b>姓名</b></td>
		<td >${employee.name }</td>
		<td align="center"><b>性别</b></td>
		<td >
			<c:if test="${employee.sex==1 }">男</c:if>
			<c:if test="${employee.sex==0 }">女</c:if>
		</td>
		<td style="width:100px;" rowspan="4"><img style="width:100px; height:123px;" src="File/Employee/HeadIcon/${employee.headicon }"/></td>
	</tr>
	<tr align="center">
		<td align="center"><b>年龄</b></td>
		<td>${employee.age }</td>
		<td align="center"><b>出生年月</b></td>
		<td >${employee.brithday }</td>
	</tr>
	<tr align="center">
		<td  align="center"><b>籍贯</b></td>
		<td >${employee.brithplace }</td>
		<td align="center"><b>文化程度</b></td>
		<td >
			<c:if test="${employee.education=='小学' }">小学</c:if>
			<c:if test="${employee.education=='初中' }">初中</c:if>
			<c:if test="${employee.education=='高中' }">高中</c:if>
			<c:if test="${employee.education=='中专' }">中专</c:if>
			<c:if test="${employee.education=='大专' }">大专</c:if>
			<c:if test="${employee.education=='本科' }">本科</c:if>
			<c:if test="${employee.education=='硕士' }">硕士</c:if>
			<c:if test="${employee.education=='博士' }">博士</c:if>
		</td>
	</tr>
	<tr align="center">
		<td align="center"><b>毕业学校</b></td>
		<td colspan="3">${employee.school }</td>
	</tr>
	<tr align="center">
		<td align="center"><b>毕业时间</b></td>
		<td colspan="4">${employee.graduation }</td>
	</tr>
	<tr align="center">
		<td align="center"><b>岗位</b></td>
		<td >${employee.job }</td>
		<td  align="center"><b>所学专业</b></td>
		<td colspan="2">${employee.major }</td>
	</tr>
	<tr align="center">
		<td align="center"><b>从事本专业时间</b></td>
		<td >${employee.majortime }</td>
		<td align="center"><b>所在科室</b></td>
		<td colspan="2">${employee.department }</td>
	</tr>
	<tr align="center">
		<td align="center"><b>入单位时间</b></td>
		<td >${employee.indate }</td>
		<td align="center"><b>职务/职称</b></td>
		<td colspan="2">${employee.duty }</td>
	</tr>
	<tr align="center">
		<td align="center"><b>技术资质</b></td>
		<td colspan="4">${employee.technology }</td>
	</tr>
</table>