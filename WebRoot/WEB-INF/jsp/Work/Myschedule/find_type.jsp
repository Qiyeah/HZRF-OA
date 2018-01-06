<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
[
	<c:forEach items="${list }" var="user" varStatus="vs">
		<c:if test="${!vs.first }">,</c:if>{"id":"${user.id }", "type":"${user.type }"}
	</c:forEach>
]