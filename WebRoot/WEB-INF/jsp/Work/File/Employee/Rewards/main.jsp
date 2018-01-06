<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent" style="overflow: auto;">
	<div><c:import url="${jsp }"/></div>
	<table class="table" width="100%" layoutH="155">
		<thead>
			<tr align="center">
				<th width="80">时间</th>
				<th>具体内容</th>
				<th width="55">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rewardss}" var="rewards" >
				<tr align="center" target="id" rel="${rewards.id}" >
					<td>${rewards.time}</td>
					<td>${rewards.content}</td>
					<td>
						<a title="点击删除" target="ajaxTodo" callback="reloadAjaxDone" href="Main/employee_rewards/delete/${rewards.id}" class="btnDel">删除</a>
						<a title="工作业绩" target="dialog" href="Main/employee_rewards/main/${rewards.eid }-${rewards.id}" mask="true" rel="employee_rewards" class="btnEdit">修改</a>
					</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
</div>
