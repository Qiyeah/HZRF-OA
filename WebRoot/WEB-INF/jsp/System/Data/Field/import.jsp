<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent tableContent">
	<form method="post" action="Main/Field/importTable" data-toggle="validate">
		<table class="table table-condensed table-hover">
			<tr>
				<td>
					<label class="control-label x90">表名：</label>
					<select name="tid" data-toggle="selectpicker" class="required" data-rule="required">
						<c:forEach items="${tables }" var="table">
							<option value="${table.id }">${table.tablename }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">导入</button></li>
    </ul>
</div>