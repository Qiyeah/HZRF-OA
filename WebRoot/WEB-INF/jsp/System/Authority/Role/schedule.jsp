<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/Role/schedule" class="pageForm" data-toggle="ajaxform">
		<input type="hidden" name="id" value="${role.id}" />
		<table class="table table-condensed table-hover">
			<tr>
				<td width="50%"><label class="control-label x85">角色名称：</label>${role.name}</td>
				<td width="50%"><label class="control-label x85">角色描述：</label>${role.ms}</td>
			</tr>
			<tr>
				<td colspan="2"><label class="control-label x85">拥有待办：</label>
					<div style="padding-left: 90px; height: 300px; clear: both; overflow: auto;">
						<c:forEach items="${schedules}" var="schedule" varStatus="vs">
							<input type="checkbox" name="schedule" value="${schedule.id}" data-toggle="icheck" <c:if test="${dbli.get(schedule.id)=='1'}">checked</c:if> data-label=" ${schedule.name}" />
							<br />
							<br />
						</c:forEach>
					</div></td>
			</tr>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>

