<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/Workflow/fieldadd" class="pageForm" data-toggle="validate">
	<input type="hidden" id="wid" name="wid" value="${wid }" />
	<table class="table table-condensed table-hover">
		<tr>
			<td width="30%" class="title">域名称</td>
			<td width="70%"><input name="name" id="name" type="text" class="required1" style="width: 98%;" /></td>
		</tr>
		<tr>
			<td class="title">域类型</td>
			<td><select name="type" id="type" class="combox">
				<option value="1">意见域</option>
				<option value="2">条件域</option>
			</select></td>
		</tr>
		<tr>
			<td class="title">域说明</td>
			<td><input name="description" id="description" type="text" class="required1" style="width: 98%;" /></td>
		</tr>
	</table>
	</form>
</div>
<%@ include file="/include/operation_footer.jsp"%>