<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="Main/LeaderDept/main" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNumber}">
		<div class="bjui-searchBar">
            <div class="pull-right">
                <button type="button" class="btn-green" data-url="Main/LeaderDept/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="addLeaderDept" data-title="新增分管配置" data-width="500" data-height="250">添加</button>&nbsp;
            </div>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="15%">分管领导</th>
				<th width="65%">分管部门</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="model" varStatus="vs">
						<tr align="center">
							<td>${vs.count}</td>
							<td>${userHM.get(model.userId)}</td>
							<td>${str:findDeptnames(model.deptIds)}</td>
                            <td><a href="Main/LeaderDept/updateip/${model.id}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="updateLeaderDept" data-title="编辑分管配置" data-width="500" data-height="250">编辑</a>
                                <a href="Main/LeaderDept/delete/${model.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该分管配置吗？">删除</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="4" style="color: red;">无任何记录！</td>
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