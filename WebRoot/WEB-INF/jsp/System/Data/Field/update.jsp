<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/Field/update" class="pageForm" data-toggle="validate">
		<input type="hidden" name="t_Field.id" value="${model.id }"/>
		<table class="table table-condensed table-hover">
			<tr>
				<td><label for="j_tid" class="control-label x85">数据表名：</label>
				<select name="t_Field.tid" id="j_tid"  data-toggle="selectpicker" aria-required="true" class="show-tick" style="display: none;">
					<c:forEach items="${tables }" var="table">
						<option value="${table.id }" <c:if test="${table.id==model.tid }">selected</c:if>>${table.tablename }</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><label for="j_name" class="control-label x85">字段名：</label>
				<input type="text" name="t_Field.name" id="j_name" value="${model.name }" maxlength="30" size="20" data-rule="required"></td>
			</tr>
			<tr>
				<td><label for="j_keyword" class="control-label x85">替换字符：</label>
				<input type="text" name="t_Field.keyword" id="j_keyword" value="${model.keyword }" maxlength="30" size="20"></td>
			</tr>
			<tr>
				<td><label for="j_type" class="control-label x85">替换频率：</label>
				<select name="t_Field.type" id="j_type" data-toggle="selectpicker" aria-required="true" class="show-tick" style="display: none;">
					<option value="1" <c:if test="${model.type==1}">selected</c:if>>多次</option>
					<option value="0" <c:if test="${model.type==0}">selected</c:if>>单次</option>
				</select></td>
			</tr>
			<tr>
				<td>
					<label class="control-label x85">计算值：</label>
					<input type="radio" name="t_Field.calculation" value="1"
						   <c:if test="${model.calculation==1}">checked</c:if> data-toggle="icheck" data-label="是"/>
					<input type="radio" name="t_Field.calculation" value="0"
						   <c:if test="${empty model.calculation || model.calculation==0}">checked</c:if> data-toggle="icheck" data-label="否"/>
				</td>
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
