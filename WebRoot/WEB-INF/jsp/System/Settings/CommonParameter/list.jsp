<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<div class="searchBar">
		<label style="margin-top: 5px">参数名称：${common.name }（${common.fieldname }）</label>
		<div class="pull-right">
			<button type="button" class="btn-green" data-url="Main/CommonParameter/addip/${common.id }" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="commonadd" data-title="添加参数值"
				data-width="500" data-height="280">添加</button>
		</div>
	</div>
</div>
<div class="tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr>
				<th width="5%" class="center">序号</th>
				<th width="25%" class="center">参数名</th>
				<th width="20%" class="center">参数值</th>
				<th width="25%" class="center">参数备注</th>
				<th width="10%" class="center">启用状态</th>
				<th width="15%" class="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${! empty details}">
				<c:forEach items="${details}" var="model" varStatus="vs">
					<tr>
						<td>${vs.count }</td>
						<td>${model.name}</td>
						<td>${model.value}</td>
						<td>${model.remark }</td>
						<td><c:if test="${model.status == '1'}">已启用</c:if> <c:if test="${model.status == '0'}">未启用</c:if></td>
						<td><a href="Main/CommonParameter/updateip/${model.id}" class="btn btn-green" data-toggle="dialog" data-mask="ture" data-id="commonupdate" data-title="编辑参数值" data-width="500"
							data-height="280">编辑</a> <a href="Main/CommonParameter/delete/${model.id}" class="btn btn-red" data-toggle="doajax" data-id="commonBox" data-confirm-msg="确定要删除该参数值吗？">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<div class="bjui-pageFooter">
	<div class="pages">
		<span>共 <c:choose>
				<c:when test="${! empty details}">${fn:length(details)}</c:when>
				<c:otherwise>0</c:otherwise>
			</c:choose>条记录
		</span>
	</div>
</div>
