<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
[
	<c:forEach items="${users}" var="user" varStatus="vs">
		<c:if test="${!vs.first }">,</c:if>{"userid":"${user.id }", "username":"${user.name }"}
	</c:forEach>
]