<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
[
	<c:forEach items="${datas }" var="data" varStatus="vs">
		<c:if test="${!vs.first }">,</c:if>
            {"id":"${data.id }", "name":"${data.name }", "code":"${data.code }","type":"${data.type }"}
	</c:forEach>
]