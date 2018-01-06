<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="Main/Template/main" method="post">
		<input type="hidden" name="pageSize" value="${page.pageSize}"> <input type="hidden" name="pageCurrent" value="${page.pageNumber}"> <input
			type="hidden" name="orderField" value="${orderField}"> <input type="hidden" name="orderDirection" value="${orderDirection}">
		<div class="bjui-searchBar">
			<label>模版名称：</label><input type="text" name="sname" value="${sname }" class="form-control" size="10" />&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			&nbsp; <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			<div class="pull-right">
				<button type="button" class="btn-green" data-url="Main/Template/update?FileType=.doc" data-toggle="dialog" data-icon="plus" data-mask="true"
					data-id="templateadd" data-title="新建word模版" data-width="1000" data-height="600">新建word模版</button>
				&nbsp;
			</div>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr>
				<th width="5%">序号</th>
				<th width="10%">模板编号</th>
				<th width="25%">模板名称</th>
				<th width="10%">模板类型</th>
				<th width="30%">模板说明</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="model" varStatus="vs">
						<tr>
							<td>${vs.index+1}</td>
							<td>${model.recordid}</td>
							<td>${model.filename}</td>
							<td>${model.filetype}</td>
							<td>${model.descript}</td>
							<td><a href="Main/Template/update?RecordID=${model.recordid}" class="btn btn-blue" data-toggle="dialog" data-mask="ture"
								data-id="templateupdate" data-title="编辑word模板" data-width="1000" data-height="600">编辑</a> <a href="Main/Template/delete/${model.id}"
								class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该模版吗？">删除</a>&nbsp;&nbsp;&nbsp;</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="6" style="color: red;">无任何记录！</td>
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
	<div class="pagination-box" data-toggle="pagination" data-total="${page.totalRow}" data-page-size="${page.pageSize}"
		data-page-current="${page.pageNumber}"></div>
</div>