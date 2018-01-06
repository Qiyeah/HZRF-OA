<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/CommonParameter/dir_update" class="pageForm" data-toggle="validate">
		<input type="hidden" name="t_Common.id" value="${model.id }" />
		<table class="table table-condensed table-hover">
			<tr>
				<td><label for="j_name" class="control-label x85">参数名称：</label> <input type="text" name="t_Common.name" id="j_name" value="${model.name}" maxlength="30" size="20"
					data-rule="required"></td>
			</tr>
			<tr>
				<td><label for="j_fieldname" class="control-label x85">关键字名：</label> <input type="text" name="t_Common.fieldname" id="j_fieldname" value="${model.fieldname}"
					maxlength="30" size="20" data-rule="required"></td>
			</tr>
			<tr>
				<td><label for="j_remark" class="control-label x85">备注信息：</label> <input type="text" name="t_Common.remark" id="j_remark" value="${model.remark}" maxlength="50" size="30"></td>
			</tr>
			<tr>
				<td><label for="j_status" class="control-label x85">启用状态：</label> <select name="t_Common.status" id="j_status" data-toggle="selectpicker" aria-required="true"
					class="show-tick" style="display: none;">
						<option value="1" <c:if test="${model.status=='1'}">selected="selected"</c:if>>已启用</option>
						<option value="0" <c:if test="${model.status=='0'}">selected="selected"</c:if>>未启用</option>
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
