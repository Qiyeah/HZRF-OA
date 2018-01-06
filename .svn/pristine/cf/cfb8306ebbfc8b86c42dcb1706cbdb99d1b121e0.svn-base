<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/Module/add" class="pageForm" data-toggle="validate">
		<input type="hidden" name="t_Module.pid" value="${pId}" /> <input type="hidden" name="t_Module.islast" value="${islast}" />
		<table class="table table-condensed table-hover">
			<tr>
				<td colspan="2"><label class="control-label x100">模块标识：</label> <input type="text" name="t_Module.mark" maxlength="50" size="30" data-rule="required; mark"
					data-rule-mark="[/^[A-Za-z0-9_]+$/, '请输入英文字母和数字组合的模块标识']" /></td>
			</tr>
			<tr>
				<td colspan="2"><label class="control-label x100">模块名称：</label> <input type="text" name="t_Module.name" maxlength="50" size="30" data-rule="required" /></td>
			</tr>
			<tr>
				<td colspan="2"><label class="control-label x100">模块URL：</label> <input type="text" name="t_Module.address" maxlength="50" size="30" /></td>
			</tr>
			<tr>
				<td><label class="control-label x100">顺序索引：</label> <input type="text" name="t_Module.orderindex" value="99" maxlength="5" size="5" data-rule="required digits" /></td>
				<td><label class="control-label x100">图标设置：</label><input type="hidden" name="t_Module.icon" id="icontext" size="5" /> <span id="iconspan"></span><a
					href="Main/search/searchIcon" data-mask="true" data-toggle="dialog" data-id="selecticon" data-width="500" data-height="550">选择</a></td>
			</tr>
			<tr>
				<td width="50%"><label class="control-label x100">启用状态：</label> <select name="t_Module.status" data-toggle="selectpicker" class="show-tick" style="display: none;">
						<option value="1" selected>已启用</option>
						<option value="0">未启用</option>
				</select></td>
				<td width="50%"><label class="control-label x85">页面链接：</label> <select name="t_Module.opentype" data-toggle="selectpicker" class="show-tick" style="display: none;">
						<option value="0" selected>内连接</option>
						<option value="1">外连接</option>
						<option value="2">系统外连接</option>
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