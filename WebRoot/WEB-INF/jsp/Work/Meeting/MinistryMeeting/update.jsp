<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form class="pageForm" action="Main/MinistryMeeting/update" method="post" data-toggle="validate">
	<input type="hidden" name="t_MinistryMeeting.id" value="${model.id}" />
        <%@ include file="form.jsp"%>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li>
			<button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<li>
			<button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>