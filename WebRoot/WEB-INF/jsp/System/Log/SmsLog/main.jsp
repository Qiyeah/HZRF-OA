<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="Main/SmsLog/main" method="post">
		<input type="hidden" name="pageSize" value="${page.pageSize}">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}">
		<input type="hidden" name="orderField" value="${orderField}">
		<input type="hidden" name="orderDirection" value="${orderDirection}">
		<div class="bjui-searchBar">
			<label>接收人：</label><input type="text" name="qname" value="${qname}" size="10">
			<label>发送时间：</label><input type="text" name="sdate" value="${sdate}" data-toggle="datepicker" readonly size="12" />&nbsp;&nbsp;至 &nbsp;&nbsp;<input type="text" name="edate" value="${edate}" data-toggle="datepicker" readonly size="12"/>&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr>
		      	<th width="5%" class="center">序号</th>
				<th width="20%" class="center">接收人</th>
				<th width="35%" class="center">短信内容</th>
				<th width="20%" class="center">发送时间</th>
				<th width="20%" class="center">发送结果</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="model" varStatus="vs">
						<tr>
                            <td>${vs.index+1}</td>
							<td>${model.users}</td>
							<td>${model.content}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${model.sendtime}" /></td>
							<td>${model.result}</td>							
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