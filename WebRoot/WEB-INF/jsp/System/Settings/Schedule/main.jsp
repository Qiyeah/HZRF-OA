<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="Main/Schedule/main" method="post">
		<input type="hidden" name="pageSize" value="${page.pageSize}"> <input type="hidden" name="pageCurrent" value="${page.pageNumber}"> <input type="hidden"
			name="orderField" value="${orderField}"> <input type="hidden" name="orderDirection" value="${orderDirection}">
		<div class="bjui-searchBar">
			<label>待办名称：</label><input type="text" name="qname" value="${qname}" class="form-control" size="8">&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			&nbsp; <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			<div class="pull-right">
				<button type="button" class="btn-blue" data-url="Main/Schedule/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleadd" data-title="新增待办"
					data-width="500" data-height="250">添加</button>
				&nbsp;
			</div>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr>
				<th width="10%" data-order-field="xh" class="center">序号</th>
				<th width="20%" data-order-field="name" class="center">待办标题名称</th>
				<th width="20%" class="center">待办ID</th>
				<th width="20%" class="center">脚本执行方法</th>
				<th width="10%" class="center">启用状态</th>
				<th width="5%" class="center"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
				<th width="15%" class="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="model">
						<tr>
							<td>${model.xh}</td>
							<td>${model.name}</td>
							<td>${model.tbodyId}</td>
							<td>${model.func}</td>
							<td><c:if test="${model.status=='1'}">启用</c:if> <c:if test="${model.status=='0'}">禁用</c:if></td>
							<td><input type="checkbox" name="ids" data-toggle="icheck" value="${model.id}"></td>
							<td><a href="Main/Schedule/updateip/${model.id}" class="btn btn-green" data-toggle="dialog" data-mask="ture" data-id="scheduleupdate" data-title="编辑待办" data-width="500"
								data-height="250">编辑</a> <a href="Main/Schedule/delete/${model.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该待办吗？">删除</a>&nbsp;&nbsp;&nbsp;</td>
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