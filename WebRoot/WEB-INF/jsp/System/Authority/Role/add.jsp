<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/Role/add" class="pageForm" data-toggle="validate">
		<table class="table table-condensed table-hover">
			<tr>
				<td><label class="control-label x85">角色序号：</label> <input type="text" name="xh" data-rule="required;digits" maxlength="5" size="5"></td>
			</tr>
			<tr>
				<td><label class="control-label x85">角色名称：</label> <input type="text" name="name" data-rule="required" maxlength="30" size="15" /></td>
			</tr>
			<tr>
				<td><label class="control-label x85">角色描述：</label> <textarea name="memo" rows="2" style="overflow: auto" maxlength="200" cols="30"></textarea></td>
			</tr>
			<tr>
				<td><label class="control-label x85">启用状态：</label> <select name="status" data-toggle="selectpicker" class="show-tick" style="display: none;">
						<option value="1" selected>是</option>
						<option value="0">否</option>
				</select></td>
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
