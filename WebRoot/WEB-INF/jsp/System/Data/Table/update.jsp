<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/Table/update" class="pageForm" data-toggle="validate">
		<input type="hidden" name="t_Table.id" value="${model.id }" />
		<table class="table table-condensed table-hover">
			<tr>
				<td><label for="j_tablecode" class="control-label x85">数据表名：</label>
				<input type="text" name="t_Table.tablecode" id="j_tablecode" value="${model.tablecode }" maxlength="30" size="20" data-rule="required"></td>
			</tr>
			<tr>
				<td><label for="j_tablename" class="control-label x85">中文名：</label>
				<input type="text" name="t_Table.tablename" id="j_tablename" value="${model.tablename }" maxlength="30" size="20" data-rule="required"></td>
			</tr>
			<tr>
				<td><label for="j_status" class="control-label x85">启用状态：</label>
				<select name="t_Table.status" id="j_status" data-toggle="selectpicker" aria-required="true" class="show-tick" style="display: none;">
					<option value="1" <c:if test="${model.status=='1' }">checked</c:if>>已启用</option>
					<option value="0" <c:if test="${model.status=='0' }">checked</c:if>>未启用</option>
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