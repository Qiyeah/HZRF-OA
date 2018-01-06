<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/Position/update" class="pageForm" data-toggle="validate">
		<input type="hidden" name="t_Position.id" value="${position.id}" />
		<table class="table table-condensed table-hover">
			<tr>
				<td><label for="j_xh" class="control-label x85">职位序号：</label> <input type="text" name="t_Position.xh" id="j_xh" value="${position.xh}" maxlength="5" size="5"
					data-rule="required digits"></td>
			</tr>
			<tr>
				<td><label for="j_name" class="control-label x85">职位名称：</label> <input type="text" name="t_Position.name" id="j_name" value="${position.name}" maxlength="30" size="20"
					data-rule="required"></td>
			</tr>
			<tr>
				<td><label for="j_status" class="control-label x85">启用状态：</label> <select name="t_Position.status" id="j_status" data-toggle="selectpicker" aria-required="true"
					class="show-tick" style="display: none;">
						<option <c:if test="${position.status=='1'}">selected</c:if> value="1">已启用</option>
						<option <c:if test="${position.status=='0'}">selected</c:if> value="0">未启用</option>
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
