<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/Role/add/copy" class="pageForm" data-toggle="validate">
		<input type="hidden" name="id" value="${role.id}" />
		<table class="table table-condensed table-hover">
			<tr>
				<td><label for="j_xh" class="control-label x85">角色序号：</label> <input type="text" name="xh" id="j_xh" value="${role.xh}" data-rule="required;digits" maxlength="5" size="5"></td>
			</tr>
			<tr>
				<td><label for="j_name" class="control-label x85">角色名称：</label> <input type="text" name="name" id="j_name" value="${role.name}" data-rule="required" maxlength="30"
					size="15" /></td>
			</tr>
			<tr>
				<td><label for="j_ms" class="control-label x85">角色描述：</label> <textarea name="ms" id="j_ms" rows="2" style="overflow: auto" maxlength="200" cols="30">${role.ms}</textarea></td>
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
