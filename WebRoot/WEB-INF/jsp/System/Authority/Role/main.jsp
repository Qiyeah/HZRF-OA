<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="Main/Role/main" method="post">
		<input type="hidden" name="pageSize" value="${page.pageSize}">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}">
		<input type="hidden" name="orderField" value="${orderField}">
		<input type="hidden" name="orderDirection" value="${orderDirection}">
		<div class="bjui-searchBar">
			<label>角色名称：</label><input type="text" name="qname" value="${qname}" class="form-control" size="10">&nbsp;
			<label>启用状态：</label><select name="qstatus"	data-toggle="selectpicker" class="show-tick" style="display: none;">
				<option value="1" <c:if test="${!empty qstatus && qstatus=='1'}">selected="selected"</c:if>>已启用</option>
				<option value="0" <c:if test="${!empty qstatus && qstatus=='0'}">selected="selected"</c:if>>未启用</option>
			</select>&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			<div class="pull-right">
				<button type="button" class="btn-green" data-url="Main/Role/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="roleadd" data-title="新增角色" data-width="500" data-height="250">添加</button>&nbsp;
				<button type="button" class="btn-red" data-url="Main/Role/deletes" data-toggle="doajaxchecked" data-confirm-msg="确定要删除选中项吗？" data-idname="delids" data-group="ids" data-icon="remove" title="可以在控制台(network)查看被删除ID">删除选中</button>&nbsp;
			</div>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr>
				<th width="5%" data-order-field="xh" class="center">序号</th>
				<th width="10%" data-order-field="name" class="center">角色名称</th>
				<th width="10%" class="center">角色描述</th>
				<th width="25%" class="center">待办平台</th>
				<th width="5%" class="center">启用状态</th>
				<th width="5%" class="center"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
				<th width="30%" class="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="model">
						<tr>
							<td>${model.xh}</td>
							<td>${model.name}</td>
							<td>${model.memo}</td>
							<td><c:set var="limits" value="${fn:split(model.dblimit, ',')}" /> <c:forEach items="${limits }" var="limit" varStatus="vs">${scheduleHM.get(limit) }<c:if
										test="${not vs.last }">、</c:if>
								</c:forEach></td>
							<td><c:if test="${model.status=='1'}">已启用</c:if> <c:if test="${model.status=='0'}">未启用</c:if></td>
							<td><input type="checkbox" name="ids" data-toggle="icheck" value="${model.id}"></td>
							<td><a href="Main/Role/updateip/${model.id}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="roleupdate" data-title="编辑角色" data-width="500"
								data-height="250">编辑</a> <a href="Main/Role/delete/${model.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该角色吗？">删除</a>&nbsp;&nbsp;&nbsp; <a
								href="Main/Role/addip/copy/${model.id}" class="btn btn-green" data-toggle="dialog" data-mask="true" data-id="roleadd" data-title="复制新增角色" data-width="500" data-height="250">复制</a>
								<a href="Main/Role/persetip/${model.id}" class="btn btn-blue" data-toggle="dialog" data-mask="true" data-id="roleperset" data-title="角色权限分配" data-width="600"
								data-height="460">权限分配</a> <a href="Main/Role/scheduleip/${model.id}" class="btn btn-blue" data-toggle="dialog" data-mask="true" data-id="roleschedule" data-title="角色待办分配"
								data-width="600" data-height="460">待办分配</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="7" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
<div class="bjui-pageFooter">
	<div class="pages">
		<span>每页&nbsp;</span>
		<div class="selectPagesize">
			<select data-toggle="selectpicker" data-toggle-change="changepagesize">
				<option <c:if test="${page.pageSize==20}">selected="selected"</c:if> value="20">20</option>
				<option <c:if test="${page.pageSize==50}">selected="selected"</c:if> value="50">50</option>
				<option <c:if test="${page.pageSize==100}">selected="selected"</c:if> value="100">100</option>
				<option value="200" <c:if test="${page.pageSize==200}">selected="selected"</c:if>>200</option>
			</select>
		</div>
		<span>条，共${page.totalRow}条</span>
	</div>
	<div class="pagination-box" data-toggle="pagination" data-total="${page.totalRow}" data-page-size="${page.pageSize}" data-page-current="${page.pageNumber}"></div>
</div>