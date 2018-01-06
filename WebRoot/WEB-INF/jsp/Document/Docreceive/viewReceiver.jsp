<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true">
		<thead>
			<tr align="center">
				<th width="7%" align="center">序号</th>
				<th width="20%" align="center">签收人</th>
				<th width="20%" align="center">签收时间</th>
				<th width="53%" align="center">反馈意见</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty list}">
					<c:forEach items="${list}" var="model" varStatus="vs">
						<tr>
							<td>${vs.index+1}</td>
							<td>${model.receive_man }</td>
							<td><fmt:formatDate value="${model.receive_time }" pattern="yyyy-MM-dd"/></td>
							<td>${model.opinion }</td>
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