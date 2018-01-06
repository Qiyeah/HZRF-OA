<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<style>
<!--
form#pageForm1 span.wrap_bjui_btn_box{
	width:100%;
}
-->
</style>
<div class="bjui-pageContent">
		<form id="pageForm1" class="pageForm" action="Main/Personal/add" method="post" data-toggle="validate">
			<table class="wordInfo" align="center" style="width: 98%;">
				<tr>
					<td width="25%" class="title">姓　　名</td>
					<td width="75%"><input name="user.userid" type="hidden" value="${userid }">
						<input name="user.username" type="text" value="${username }" suggestFields="username" suggestUrl="Main/search/searchUser" lookupGroup="user" data-rule="required" data-msg-required="必填" class="required" style="width: 100%" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td class="title">开始工作日期</td>
					<td><input type="text" name="t_Personal.begindate" class="required" data-toggle="datepicker" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" style="width: 100%" maxlength="13"/></td>
				</tr>
				<tr>
					<td class="title">婚姻状态</td>
					<td><select name="t_Personal.married" data-toggle="selectpicker" data-width="100%">
						<option value="已婚">已婚</option>
						<option value="未婚">未婚</option>
					</select></td>
				</tr>
				<tr>
					<td class="title">职务名称</td>
					<td><input type="text" name="t_Personal.gradename" style="width: 100%" maxlength="20"/></td>
				</tr>
				<tr>
					<td class="title">职务级别</td>
					<td><input type="text" name="t_Personal.gradelevel" style="width: 100%" maxlength="20"/></td>
				</tr>
				<tr>
					<td class="title">内部电话</td>
					<td><input type="text" name="t_Personal.phone" style="width: 100%" maxlength="20"/></td>
				</tr>
				<tr>
					<td class="title">手　　机</td>
					<td><input type="text" name="t_Personal.mbphone" data-rule="mobile" maxlength="20" style="width: 100%" /></td>
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