<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/Workflow/fieldupdate" class="pageForm" data-toggle="validate">
	<input type="hidden" value="${model.id }" name="t_Wfield.id"/>
	<table class="table table-condensed table-hover">
		<tr>
			<td width="30%" class="title">域名称</td>
			<td width="70%"><input name="t_Wfield.name" type="text" class="required1" style="width: 98%;" value="${model.name }" /></td>
		</tr>
		<tr>
			<td class="title">域类型</td>
			<td><select name="t_Wfield.type" class="combox">
				<option value="1" <c:if test="${model.type==1 }">selected</c:if>>意见域</option>
				<option value="2" <c:if test="${model.type==2 }">selected</c:if>>条件域</option>
			</select></td>
		</tr>
		<tr>
			<td class="title">域说明</td>
			<td><input name="t_Wfield.description" type="text" class="required1" style="width: 98%;" value="${model.description }"/></td>
		</tr>
	</table>
	</form>
</div>
<%@ include file="/include/operation_footer.jsp"%>