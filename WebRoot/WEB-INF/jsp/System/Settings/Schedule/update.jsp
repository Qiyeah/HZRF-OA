<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/Schedule/update" class="pageForm" data-toggle="validate">
		<input type="hidden" name="t_Schedule.id" value="${model.id}" />
		<table class="table table-condensed table-hover">
			<tr>
				<td><label for="j_xh" class="control-label x95">序号：</label> <input type="text" name="t_Schedule.xh" id="j_xh" value="${model.xh}" maxlength="5" size="5"
					data-rule="required digits"></td>
			</tr>
			<tr>
				<td><label for="j_name" class="control-label x95">待办名称：</label> <input type="text" name="t_Schedule.name" id="j_name" value="${model.name}" maxlength="30" size="20"
					data-rule="required"></td>
			</tr>
			<tr>
				<td><label for="j_tbodyId" class="control-label x95">待办ID：</label> <input type="text" name="t_Schedule.tbodyId" id="j_tbodyId" value="${model.tbodyId}" maxlength="30"
					size="20" data-rule="required"></td>
			</tr>
			<tr>
				<td><label for="j_func" class="control-label x95">脚本执行方法：</label> <input type="text" name="t_Schedule.func" id="j_func" value="${model.func}" maxlength="30" size="20"
					data-rule="required"></td>
			</tr>
			<tr>
				<td><label for="j_status" class="control-label x95">启用状态：</label> <select name="t_Schedule.status" id="j_status" data-toggle="selectpicker" aria-required="true"
					class="show-tick" style="display: none;">
						<option value="1" <c:if test="${model.status==1}">selected="selected"</c:if>>启用</option>
						<option value="0" <c:if test="${model.status==0}">selected="selected"</c:if>>禁用</option>
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
