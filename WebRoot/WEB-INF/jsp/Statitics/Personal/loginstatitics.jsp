<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/PersonalLoginStatitics/main" method="post" data-toggle="ajaxsearch">
		<div class="bjui-searchBar">
			<label>姓名：</label><input type="text" name="qname" value="${qname }" size="10" />&nbsp;
			<label>部门：</label><select name="qdeptid" data-toggle="selectpicker" class="show-tick" style="display: none;">
						<option value="">全部科室</option>
						<c:forEach items="${deptstrs}" var="dept">
							<option value="${dept[0]}" <c:if test="${!empty qdeptid && dept[0]==qdeptid}">selected="selected"</c:if>>${dept[1]}</option>
						</c:forEach>
						<c:if test="${! empty depts}">
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.id}" <c:if test="${!empty qdeptid && dept.id==qdeptid}">selected="selected"</c:if>>${dept.sname}</option>
								<li data-id="${dept.id}" data-pid="${dept.pid}">${dept.sname}</li>				
							</c:forEach>
						</c:if>
					</select>
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed" data-selected="true">
		<thead>
			<tr>
				<th width="5%" align="center">序号</th>
				<th width="15%" align="center">姓名</th>
				<th width="20%" align="center">部门</th>
				<th width="25%" align="center">网页/手机登录次数</th>
				<th width="15%" data-order-field="dlcount" align="center">总登录次数</th>
				<th width="20%" data-order-field="logindate" align="center">最近登录日期</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="model" varStatus="s">
						<tr>
							<td>${s.count}</td>
							<td>${model.name }</td>
							<td>${deptHM.get(model.d_id)}</td>
							<td>${model.webcount } / ${model.appcount }</td>
							<td>${model.webcount+model.appcount }</td>
							<td>${fn:substring(model.logindate, 0, 10) }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="10" align="center" style="color: red">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>
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