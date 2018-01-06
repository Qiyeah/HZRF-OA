<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<div class="searchBar">
		<label style="margin-top: 5px">参数目录</label>
		<div class="pull-right">
			<button type="button" class="btn-green" data-url="Main/CommonParameter/dir_addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="commonadd" data-title="添加参数"
				data-width="500" data-height="250">添加</button>
		</div>
	</div>
</div>
<div class="tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr>
				<th width="5%" class="center">序号</th>
				<th width="15%" class="center">参数名称</th>
				<th width="15%" class="center">关键字名</th>
				<th width="40%" class="center">备注信息</th>
				<th width="10%" class="center">启用状态</th>
				<th width="15%" class="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${! empty commons}">
				<c:forEach items="${commons}" var="model" varStatus="vs">
					<tr>
						<td>${vs.count }</td>
						<td>${model.name}</td>
						<td>${model.fieldname}</td>
						<td>${model.remark}</td>
						<td><c:if test="${model.status=='1'}">已启用</c:if> <c:if test="${model.status=='0'}">未启用</c:if></td>
						<td><a href="Main/CommonParameter/dir_updateip/${model.id}" class="btn btn-green" data-toggle="dialog" data-mask="ture" data-id="commonupdate" data-title="编辑参数"
							data-width="500" data-height="250">编辑</a> <a href="Main/CommonParameter/dir_delete/${model.id}" class="btn btn-red" data-toggle="doajax" data-id="commonBox"
							data-confirm-msg="确定要删除该参数吗？">删除</a>&nbsp;&nbsp;&nbsp;</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<div class="bjui-pageFooter">
	<div class="pages">
		<span>共 <c:choose>
				<c:when test="${! empty commons}">${fn:length(commons)}</c:when>
				<c:otherwise>0</c:otherwise>
			</c:choose>条记录
		</span>
	</div>
</div>
