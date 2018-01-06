<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<div class="pageFormContent" layoutH="60">
		<h2 class="contentTitle">已阅人员</h2>
		<div class="divider"></div>
		<c:forEach items="${viewers }" var="viewer" varStatus="vs">
		  <c:if test="${vs.index %2==0 }"><br></c:if>
			${user.get(viewer.viewerid) }(<fmt:formatDate value="${viewer.viewtime}" pattern="yyyy-MM-dd HH:mm"/>)<c:if test="${not vs.last }">、</c:if>
		</c:forEach>
	</div>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li>
			<button type="button" class="btn-close" data-icon="close">关闭</button></li>
	</ul>
</div>