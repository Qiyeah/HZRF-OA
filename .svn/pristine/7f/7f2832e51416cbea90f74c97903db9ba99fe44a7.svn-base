<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Department/main/list/${id}" method="post" data-toggle="ajaxsearch">
		<div class="searchBar">
			<label style="margin-top: 5px">部门名称：${deptname}</label>
			<div class="pull-right">
				<button type="button" class="btn-green" data-url="Main/Department/addip/${id}" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="deptadd" data-title="添加部门"
					data-width="600" data-height="300">添加</button>
			</div>
		</div>
	</form>
</div>
<div class="tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr>
				<th width="10%" class="center">部门排序</th>
				<th width="30%" class="center">部门全称</th>
				<th width="15%" class="center">部门简称</th>
				<th width="15%" class="center">所属区域</th>
				<th width="15%" class="center">启用状态</th>
				<th width="15%" class="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${! empty depts}">
				<c:forEach items="${depts}" var="model">
					<tr>
						<td>${model.no}</td>
						<td>${model.fname}</td>
						<td>${model.sname}</td>
						<td>${areaHM.get(model.area)}</td>
						<td><c:if test="${model.status=='1'}">已启用</c:if> <c:if test="${model.status=='0'}">未启用</c:if></td>
						<td><a href="Main/Department/updateip/${model.id}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="deptupdate" data-title="编辑部门" data-width="600" data-height="300">编辑</a>
							<a href="Main/Department/delete/${model.id}?organIdParentId=${id}" class="btn btn-red" data-toggle="doajax" data-id="deptBox" data-reload="false" data-confirm-msg="确定要删除该部门吗？">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<div class="bjui-pageFooter">
	<div class="pages">
		<span>共 <c:choose>
				<c:when test="${! empty depts}">${fn:length(depts)}</c:when>
				<c:otherwise>0</c:otherwise>
			</c:choose>条记录
		</span>
	</div>
</div>
