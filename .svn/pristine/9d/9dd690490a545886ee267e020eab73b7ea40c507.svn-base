<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<div class="pageFormContent" layoutH="60">
		<h2 class="contentTitle">参加人员</h2>
		<span style="margin-left:60px;color:red">${noticesqlusers}</span>
		<span>
		<c:forEach items="${attendusers}" var="attend" varStatus="vs">
			${attend.name}<c:if test="${not vs.last}">、</c:if>
		</c:forEach>
		</span>
		<div class="divider"></div>
		<h2 class="contentTitle">未确认人员</h2>
		<span style="margin-left:60px">
		<c:forEach items="${unconfirmuers}" var="unconfirm" varStatus="vs">
			${unconfirm.name}<c:if test="${not vs.last}">、</c:if>
		</c:forEach>
		</span>
		<div class="divider"></div>
		<h2 class="contentTitle">不参加人员</h2>
		
		<c:forEach items="${noattenduers}" var="noattend" varStatus="vs">
		    <span style="margin-left:60px;display:inline-block ">
			${noattend.name}：${noattend.zwxx}
			</span><br/>
			<div style="height:5px"></div>
		</c:forEach>
		
	</div>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>  
	</ul>
</div>