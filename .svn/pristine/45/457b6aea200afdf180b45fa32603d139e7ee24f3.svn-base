<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form class="pageForm" action="Main/Announce/view" method="post" data-toggle="validate">
		<input type="hidden" name="t_Announce_Viewer.announceid" value="${announce.id }"/>
		<div class="pageFormContent" layoutH="60">
			<br/>
			<div><table style="width: 80%" align="center"><tr><td><span style="text-align:center;font-size: 16px;font-weight: bold;display:block;">${announce.title }</span></td></tr></table></div>
			<br/>
			<div class="divider"></div>
			<div style="text-align:center;"><span style="color: blue;">发布人：${announcedept} ${announcer}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布时间：<fmt:formatDate value="${announce.sendtime}" pattern="yyyy年MM月dd日  HH:mm:ss"/></span></div>
			<div style="width:98%;margin:0 auto;"><div style="margin-top:15px;">${announce.content }</div></div>
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li>
		<c:if test="${not empty viewer }">
			<button type="button" class="btn-close" data-icon="close">关闭</button>
		</c:if>
		</li>
		<li>
		<c:if test="${ empty viewer }">
			<button type="submit" class="btn-default" data-icon="save">确认阅读</button>
		</c:if>
		</li>
	</ul>
</div>