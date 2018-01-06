<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" action="Main/Workflow/transupdate" class="pageForm" data-toggle="validate">
	<input type="hidden" name="t_Wtrans_Sp.id" value="${model.id }" />
	<table class="table table-condensed table-hover">
		<tr>
			<td>
				<label class="control-label x90">部门:</label>
				<select name="t_Wtrans_Sp.dept" id = "dept" data-toggle="selectpicker">
						<option value="0" selected>所有部门</option>
						<c:if test="${! empty depts}">
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.id}" <c:if test="${model.dept==dept.id }">selected</c:if>>${dept.fname}</option>
							</c:forEach>
						</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label class="control-label x90">职务:</label>
				<select name="t_Wtrans_Sp.positions" id = "positions" data-toggle="selectpicker">
			        <option value="0">所有职位</option>
					<c:if test="${! empty positions}">
						<c:forEach items="${positions}" var="position">
							<option value="${position.id}" <c:if test="${model.positions==position.id }">selected</c:if>>${position.name}</option>
						</c:forEach>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label class="control-label x90">下一环节:</label>
				<select name="t_Wtrans_Sp.ato" data-toggle="selectpicker">
					<option value=""></option>
					<c:if test="${! empty actives}">
						<c:forEach items="${actives}" var="ac">
							<option value="${ac.id}" <c:if test="${model.ato==ac.id }">selected</c:if>>${ac.name}</option>
						</c:forEach>
					</c:if>
				</select>
			</td>
		</tr>
	</table>
	</form>
</div>
<%@ include file="/include/operation_footer.jsp"%>