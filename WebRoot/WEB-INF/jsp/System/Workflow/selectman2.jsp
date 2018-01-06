<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<c:set var="deparments" value="${deparmentList}" />
<c:set var="users" value="${userList}" />
<input type="hidden" name="amount" id="amount" value="${amount }" />
<ul id="user-tree" class="ztree" data-toggle="ztree" data-expand-all="true" data-check-enable="true" data-on-check="onCheck">
	<c:if test="${! empty deparments}">
		<c:forEach items="${deparments}" var="department">
			<li data-id="d${department.id}" data-pid="d${department.pid}">${department.fname}</li>
		</c:forEach>
		<c:forEach items="${users}" var="user">
			<li data-id="10000" data-faicon="user" data-value="${user.id}" data-name="${user.name}" data-pid="d${user.d_id }">${user.name}
			<c:if test="${user.pid==12 }">(科长)</c:if><c:if test="${user.pid==6 }">(副科长)</c:if></li>
		</c:forEach>
	</c:if>
</ul>

