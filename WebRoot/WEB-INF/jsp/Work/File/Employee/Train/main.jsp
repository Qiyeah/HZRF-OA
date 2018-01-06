<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent" style="overflow: auto;">
	<div><c:import url="${jsp }"/></div>
	<table class="table" width="100%" layoutH="185">
		<thead>
			<tr align="center">
				<th width="80">培训日期</th>
				<th width="20%">培训项目</th>
				<th>培训内容</th>
				<th>培训结果</th>
				<th>证书号</th>
				<th>备注</th>
				<th width="55">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${trains}" var="train" >
				<tr align="center" target="id" rel="${train.id}" >
					<td>${train.time}</td>
					<td>${train.item}</td>
					<td>${train.content}</td>
					<td>${train.result}</td>
					<td>${train.certificate_no}</td>
					<td>${train.remark}</td>
					<td>
						<a title="点击删除" target="ajaxTodo" callback="reloadAjaxDone" href="Main/employee_train/delete/${train.id}" class="btnDel">删除</a>
						<a title="记录表" target="dialog" href="Main/employee_train/main/${train.eid }-${train.id}" mask="true" rel="employee_train" class="btnEdit">修改</a>
					</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
</div>
