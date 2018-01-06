<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Area/main/list/${id}" method="post" data-toggle="ajaxsearch">
		<div class="searchBar">
			<label style="margin-top: 5px">区划名称：${area.name}</label>
			<div class="pull-right">
				<button type="button" class="btn-green" data-url="Main/Area/addip/${id}" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="areaadd" data-title="添加区划"
					data-width="600" data-height="300">添加</button>
			</div>
		</div>
	</form>
</div>
<div class="tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr>
				<th width="10%" class="center">排序</th>
				<th width="60%" class="center">区划名称</th>
				<th width="15%" class="center">启用状态</th>
				<th width="15%" class="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${! empty areas}">
				<c:forEach items="${areas}" var="model">
					<tr>
						<td>${model.xh}</td>
						<td>${model.name}</td>
						<td><c:if test="${model.status=='1'}">已启用</c:if> <c:if test="${model.status=='0'}">未启用</c:if></td>
						<td><a href="Main/Area/updateip/${model.id}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="areaupdate" data-title="编辑区划" data-width="600" data-height="300">编辑</a>
							<a href="Main/Area/delete/${model.id}?pId=${id}" class="btn btn-red" data-toggle="doajax" data-id="areaBox" data-reload="false" data-confirm-msg="确定要删除该区划吗？">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<div class="bjui-pageFooter">
	<div class="pages">
		<span>共 <c:choose>
				<c:when test="${! empty areas}">${fn:length(areas)}</c:when>
				<c:otherwise>0</c:otherwise>
			</c:choose>条记录
		</span>
	</div>
</div>
