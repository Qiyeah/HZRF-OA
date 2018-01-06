<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>

<iframe src="${basePath }File/Report/${filename}.pdf" width="100%" height="100%" style="border:0px"></iframe>	
