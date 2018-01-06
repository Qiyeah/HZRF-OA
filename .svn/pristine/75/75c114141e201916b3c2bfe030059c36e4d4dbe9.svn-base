<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/User/pwordset/${user.id}" class="pageForm" data-toggle="validate">
		<input type="hidden" name="t_Personnal_Register.id" value="${user.id}"/>
		<table class="table table-condensed table-hover">
			<tr>
				<td>用户账号：</td>
				<td>${user.dlid} </td>
			</tr>
			<tr>
				<td>旧密码：</td>
				<td><input type="password" name="oldpassword" data-rule="旧密码:required" size="20" /></td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td><input type="password" name="password" maxlength="20" data-rule="新密码:required;password" size="20" /></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" name="rnewPassword" data-rule="确认密码:required;match(password)" size="20" /></td>
			</tr>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close">关闭</button></li>
		<li><button type="submit" class="btn-default">保存</button></li>
	</ul>
</div>
<script type="text/javascript">
	function customvalidPwd(element) {
		if ($(element).val() == "111111") return false
		else return true;
	}
</script>