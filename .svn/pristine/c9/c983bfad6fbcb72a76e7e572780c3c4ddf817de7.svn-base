<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="Main/OperationLog/main" method="post">
		<input type="hidden" name="pageSize" value="${page.pageSize}">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}">
		<input type="hidden" name="orderField" value="${orderField}">
		<input type="hidden" name="orderDirection" value="${orderDirection}">
		<div class="bjui-searchBar">
			<label>操作人：</label><input type="text" name="qname" value="${qname}" size="10">
			<label>操作时间：</label><input type="text" name="sdate" value="${sdate}" data-toggle="datepicker" readonly size="12" />&nbsp;&nbsp;至 &nbsp;&nbsp;<input type="text" name="edate" value="${edate}" data-toggle="datepicker" readonly size="12"/>&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr>
				<th width="10%" class="center">账号类型</th>
				<th width="10%" class="center">登录账号</th>
				<th width="10%" class="center">姓名</th>
				<th width="10%" class="center">操作模块</th>
				<th width="20%" class="center">操作时间</th>
				<th width="40%" class="center">操作内容</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="model" varStatus="vs">
						<tr>
                            <td>${vs.index+1}</td>
							<td>${model.dlid}</td>
							<td>${model.name}</td>
							<td>${model.operation_module }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${model.operation_time}" /></td>
							<td align="left" title="${model.name}${model.operation}">${model.name}${model.operation}</td>							
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="5" style="color: red;">无任何记录！</td>
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