<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/CommonParameter/add" class="pageForm" data-toggle="validate">
		<input type="hidden" name="t_Common_Detail.cid" value="${common.id }" />
		<table class="table table-condensed table-hover">
			<tr>
				<td><label class="control-label x85">参数名称：</label>&nbsp;${common.name }</td>
			</tr>
			<tr>
				<td><label for="j_name" class="control-label x85">参数名：</label> <textarea name="t_Common_Detail.name" rows="3" data-rule="required" maxlength="200" cols="20"
						style="overflow: auto"></textarea></td>
			</tr>
			<tr>
				<td><label class="control-label x85">参数值：</label> <input type="text" name="t_Common_Detail.value" maxlength="50" size="30"></td>
			</tr>
			<tr>
				<td><label for="j_remark" class="control-label x85">备注信息：</label> <input type="text" name="t_Common_Detail.remark" id="j_remark" maxlength="50" size="30"></td>
			</tr>
			<tr>
				<td><label for="j_status" class="control-label x85">启用状态：</label> <select name="t_Common_Detail.status" id="j_status" data-toggle="selectpicker" aria-required="true"
					class="show-tick" style="display: none;">
						<option value="1" selected>已启用</option>
						<option value="0">未启用</option>
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


