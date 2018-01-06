<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent" style="overflow: auto;">
	<div><c:import url="${jsp }"/></div>
	<table class="table" width="100%" layoutH="155">
		<thead>
			<tr align="center">
				<th width="80">时间</th>
				<th width="20%">主办单位</th>
				<th>学习内容</th>
				<th>课时</th>
				<th width="55">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${educations}" var="education" >
				<tr align="center" target="id" rel="${education.id}" >
					<td>${education.time}</td>
					<td>${education.unit}</td>
					<td>${education.content}</td>
					<td>${education.hour}</td>
					<td>
						<a title="点击删除" target="ajaxTodo" callback="reloadAjaxDone" href="Main/employee_education/delete/${education.id}" class="btnDel">删除</a>
						<a title="教育培训" target="dialog" href="Main/employee_education/main/${education.eid }-${education.id}" mask="true" rel="employee_education" class="btnEdit">修改</a>
					</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
</div>
