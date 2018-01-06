<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%--
[
	<c:forEach items="${datas }" var="data" varStatus="vs">
		<c:if test="${!vs.first }">,</c:if>{"id":"${data.id }", 
		&lt;%&ndash;"sid":"${data.sid }",&ndash;%&gt;
		&lt;%&ndash;"name":"${data.name }",
		"unit":"${data.unit }", 
		"duty":"${data.duty }", 
		"sex":"${data.sex }", 
		"tmp":"${data.tmp }",
		"married":"${data.married }"&ndash;%&gt;}
	</c:forEach>
]--%>
[
<c:forEach items="${cadre }" var="data" varStatus="vs">
    <c:if test="${!vs.first }">,</c:if>
    {"id":"${data.id }",
    "u_id":"${data.u_id }",
    "name":"${data.name }",
    "sex":"${data.sex }",
     "duty":"${data.duty }",
     "tmp":"${data.tmp }"}
</c:forEach>
]