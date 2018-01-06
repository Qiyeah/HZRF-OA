<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent" style="overflow: auto;">
	<div><c:import url="${jsp }"/></div>
	<table class="table" width="100%" layoutH="185">
		<thead>
			<tr align="center">
				<th width="80">证件号</th>
				<th width="20%">发证单位</th>
				<th>级别</th>
				<th>有效期</th>
				<th>复印件</th>
				<th>原件</th>
				<th width="55">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${dutys}" var="duty" >
				<tr align="center" target="id" rel="${duty.id}" >
					<td>${duty.no}</td>
					<td>${duty.unit}</td>
					<td>${duty.level}</td>
					<td>${duty.deadline}</td>
					<td>${duty.copy}</td>
					<td><c:if test="${not empty duty.original }"><a href="Main/employee_duty/download/${duty.id}" style="color:blue;">点击下载</a></c:if></td>
					<td>
						<a title="点击删除" target="ajaxTodo" callback="reloadAjaxDone" href="Main/employee_duty/delete/${duty.id}" class="btnDel">删除</a>
						<a title="技术档案卡" target="dialog" href="Main/employee_duty/main/${duty.eid }-${duty.id}" mask="true" rel="employee_duty" class="btnEdit">修改</a>
					</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
</div>
