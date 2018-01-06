<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<table class="wordInfo" align="center" style="margin-top:20px;">
	<tr align="center">
		<td class="title" >姓名</td>
		<td >${employee.name }</td>
		<td class="title">性别</td>
		<td width="60"><c:if test="${employee.sex==0 }">女</c:if><c:if test="${employee.sex==1 }">男</c:if></td>
		<td class="title">出生年月</td>
		<td >${employee.brithday }</td>
	</tr>
	<tr align="center">
		<td class="title">毕业院校</td>
		<td colspan="5">${employee.school }</td>
	</tr>
	<tr align="center">
		<td class="title">技术资质</td>
		<td colspan="5">${employee.technology }</td>
	</tr>
	<tr><td colspan="6" align="center">教育培训经历</td></tr>
	<tr>
		<td class="title">时间</td>
		<td class="title">主办单位</td>
		<td class="title" colspan="3">学习内容</td>
		<td class="title">课时</td>
	</tr>
	<c:forEach items="${educations }" var="education">
		<tr align="center">
			<td>${education.time }</td>
			<td>${education.unit }</td>
			<td colspan="3">${education.content }</td>
			<td>${education.hour }</td>
		</tr>
	</c:forEach>
	<tr><td colspan="6" align="center">工作业绩简述</td></tr>
	<tr>
		<td class="title">时间</td>
		<td class="title">职称</td>
		<td class="title">工作岗位</td>
		<td class="title" colspan="3">工作内容</td>
	</tr>
	<c:forEach items="${works }" var="work">
		<tr align="center">
			<td>${work.time }</td>
			<td>${work.duty }</td>
			<td>${work.duty_place }</td>
			<td colspan="3">${work.content }</td>
		</tr>
	</c:forEach>
	<tr><td colspan="6" align="center">奖惩项目</td></tr>
	<tr>
		<td class="title">时间</td>
		<td class="title" colspan="5">具体内容</td>
	</tr>
	<c:forEach items="${rewardss }" var="rewards">
		<tr align="center">
			<td>${rewards.time }</td>
			<td colspan="5">${rewards.content }</td>
		</tr>
	</c:forEach>
</table>
<br/><br/>