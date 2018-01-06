<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<div class="bjui-searchBar">
		<div class="pull-right">
			<button type="button" class="btn-green" data-url="Main/Workflow/fieldaddip/${wid }" data-toggle="dialog" data-id="addFlow" data-width="350" data-height="250" data-title="添加流程" data-mask="true">添加</button>
		</div>
	</div>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top table-center">
		<thead>
			<tr align="center">
				<th width="10%">序号</th>
				<th width="20%">域名称</th>
				<th width="15%">域类型</th>
				<th width="35%">域说明</th>
				<th width="20%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="model" varStatus="vs">
				<tr>
					<td>${vs.count }</td>
					<td>${model.name }</td>
					<td>
						<c:if test="${model.type==1 }">意见域</c:if>
						<c:if test="${model.type!=1 }">条件域</c:if>
					</td>
					<td>${model.description }</td>
					<td>
						<a class="btn btn-blue" href="Main/Workflow/fieldupdateip/${model.id}" data-toggle="dialog" data-mask="true" data-id="editactive" data-width="350" data-height="250" data-title="编辑环节">编辑</a>
						<a class="btn btn-red" href="Main/Workflow/fielddel/${model.id}" data-toggle="doajax" data-confirm-msg="确定要删除该行信息吗？" data-title="删除？">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>