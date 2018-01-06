<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<div class="searchBar">
		<label style="margin-top: 5px"> <c:choose>
				<c:when test="${0!=id}">模块名称：${module.name}</c:when>
				<c:otherwise>模块名称：系统功能模块</c:otherwise>
			</c:choose>
		</label>
		<div class="pull-right">
			<button type="button" class="btn-green" data-url="Main/Module/addip/${id }" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="moduleadd" data-title="添加模块"
				data-width="600" data-height="250">添加</button>	&nbsp;
			<c:if test="${flag}">
				<button type="button" class="btn-blue" data-url="Main/Module/addFunc/${id}-0" data-toggle="doajax" data-icon="copy" data-id="moduleBox" data-confirm-msg="确定要添加“增删改”操作吗？">增删改</button>&nbsp;
				<button type="button" class="btn-blue" data-url="Main/Module/addFunc/${id}-1" data-toggle="doajax" data-icon="copy" data-id="moduleBox" data-confirm-msg="确定要添加“增删改+批删”操作吗？">增删改+批删</button>&nbsp;
			</c:if>
		</div>
	</div>
</div>
<div class="tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr>
				<th width="5%" class="center">排序</th>
				<th width="20%" class="center">模块标识</th>
				<th width="15%" class="center">模块名称</th>
				<th width="30%" class="center">模块URL</th>
				<th width="10%" class="center">启用状态</th>
				<th width="5%" class="center">图标</th>
				<th width="15%" class="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${! empty modules}">
				<c:forEach items="${modules}" var="model" varStatus="vs">
					<tr>
						<td>${model.orderindex}</td>
						<td>${model.mark}</td>
						<td>${model.name}</td>
						<td>${model.address}</td>
						<td><c:if test="${model.status == '1'}">已启用</c:if> <c:if test="${model.status == '0'}">未启用</c:if></td>
						<td><span class="fa fa-lg ${model.icon }"></span></td>
						<td><a href="Main/Module/updateip/${model.id}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="moduleupdate" data-title="编辑模块" data-width="600"
							data-height="250">编辑</a>
							<a href="Main/Module/delete/${model.id}" class="btn btn-red" data-toggle="doajax" data-id="moduleBox" data-confirm-msg="确定要删除该模块吗？">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<div class="bjui-pageFooter">
	<div class="pages">
		<span>共 <c:choose>
				<c:when test="${! empty modules}">${fn:length(modules)}</c:when>
				<c:otherwise>0</c:otherwise>
			</c:choose>条记录
		</span>
	</div>
</div>
