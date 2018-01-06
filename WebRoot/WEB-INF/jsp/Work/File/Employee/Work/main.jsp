<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent" style="overflow: auto;">
	<div><c:import url="${jsp }"/></div>
	<table class="table" width="100%" layoutH="155">
		<thead>
			<tr align="center">
				<th width="80">时间</th>
				<th width="20%">职称</th>
				<th>工作岗位</th>
				<th>工作内容</th>
				<th width="55">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${works}" var="work" >
				<tr align="center" target="id" rel="${work.id}" >
					<td>${work.time}</td>
					<td>${work.duty}</td>
					<td>${work.duty_place}</td>
					<td>${work.content}</td>
					<td>
						<a title="点击删除" target="ajaxTodo" callback="reloadAjaxDone" href="Main/employee_work/delete/${work.id}" class="btnDel">删除</a>
						<a title="工作业绩" target="dialog" href="Main/employee_work/main/${work.eid }-${work.id}" mask="true" rel="employee_work" class="btnEdit">修改</a>
					</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
</div>
