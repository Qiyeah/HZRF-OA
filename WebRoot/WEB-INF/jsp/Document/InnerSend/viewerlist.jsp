<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true">
		<thead>
			<tr align="center">
				<th width="10%" align="center">序号</th>
				<th width="30%" align="center">收阅人</th>
				<th width="60%" align="center">收阅时间</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${! empty userids}">
				<c:forEach items="${userids}" var="userid" varStatus="vs">
					<tr>
						<td>${vs.index+1}</td>
						<td>${usernames[vs.index]}</td>
						<td><c:forEach items="${viewerlist }" var="viewer">
								<c:if test="${viewer.u_id == userid}">
									<fmt:formatDate value="${viewer.viewTime }" pattern="yyyy-MM-dd HH:mm" />
								</c:if>
							</c:forEach></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>