<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent">
	<form method="post" action="Main/Personal/addTel" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<table class="wordInfo" align="center" style="width: 98%;">
				<tr>
					<td width="25%" class="title">姓　　名</td>
					<td width="75%"><input name="user.userid" type="hidden" value="${userid }">
						<input name="user.username" type="text" value="${username }" suggestFields="username" suggestUrl="Main/search/searchUser" lookupGroup="user" class="required" style="width: 98%" />
					</td>
				</tr>
				<tr>
					<td class="title">职务名称</td>
					<td><input type="text" name="t_Personal.gradename" style="width: 98%" /></td>
				</tr>
				<tr>
					<td class="title">职务级别</td>
					<td><input type="text" name="t_Personal.gradelevel" style="width: 98%" /></td>
				</tr>
				<tr>
					<td class="title">内部电话</td>
					<td><input type="text" name="t_Personal.phone" style="width: 98%" /></td>
				</tr>
				<tr>
					<td class="title">手　　机</td>
					<td><input type="text" name="t_Personal.mbphone" style="width: 98%" /></td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
