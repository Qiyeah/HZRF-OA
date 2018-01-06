<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
<!--
	$("#usetemplate").on('ifChanged', function(e)  {
		if ($("#usetemplate").attr("checked")) {
			$("#model").val("1");
		} else {
			$("#model").val("0");
		}
		$("#templatediv").toggle();
	});
//-->
</script>
<div class="bjui-pageContent">
	<form method="post" action="Main/Workflow/processadd" class="pageForm" data-toggle="validate">
		<input type="hidden" name="t_Wprocess.model" id="model" value="0" />
		<table class="table table-condensed table-hover">
			<tr>
				<td>
					<label class="control-label x90">流程标识:</label>
					<input name="t_Wprocess.flow" class="required" type="text" size="30"/>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x90">流程名称:</label>
					<input name="t_Wprocess.name" class="required" type="text" size="30"/>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x90">流程类型:</label>
					<select name="t_Wprocess.type" data-toggle="selectpicker">
						<option value="0">主流程</option>
						<option value="1">子流程</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x90">流程说明:</label>
					<textarea name="t_Wprocess.description" rows="3" cols="30"></textarea>
				</td>
			</tr>
			<tr>
				<td >
					<label class="control-label x90">流程督办员&nbsp;&nbsp;&nbsp;:</label>
					<input name="admin.userId" type="hidden"/>
             		<input name="admin.userName" type="text" readonly data-toggle="lookup" data-url="Main/Workflow/getUser" data-group="admin" data-width="600" data-height="550" size="40"/>
				</td>
			</tr>
			<tr>
				<td >
					<label class="control-label x90">允许使用范围:</label>
					<input name="scope.deptId" type="hidden" />
					<input name="scope.deptName" type="text" readonly data-toggle="lookup" data-url="Main/Workflow/getDept" data-group="scope" data-width="600" data-height="550" size="40"/>
				</td>
			</tr>
			<tr>
				<td >
					<label class="control-label x90">允许使用职位:</label>
					<input name="station.pId" type="hidden" />
					<input name="station.pName" type="text" readonly data-toggle="lookup" data-url="Main/Workflow/getPosition" data-group="station" data-width="500" data-height="450" size="40"/>
				</td>
			</tr>
			<tr>
				<td >
					<label class="control-label x90">禁止使用用户:</label>
					<input name="forbid.userId" type="hidden" />
					<input name="forbid.userName" type="text" readonly data-toggle="lookup" data-url="Main/Workflow/getUser" data-group="forbid" data-width="500" data-height="450" size="40"/>
				</td>
			</tr>
			<tr>
				<td >
					<label class="control-label x90">使用模板:</label>
					<input type="checkbox" id="usetemplate" name="usetemplate" data-toggle="icheck">
				</td>
			</tr>
			<tr id="templatediv" style="display: none" >
				<td>
					<label class="control-label x90">模板文件:</label>
					<select name="t_Wprocess.template" data-toggle="selectpicker">
						<option value="0">--</option>
						<c:if test="${! empty templates}">
							<c:forEach items="${templates}" var="template">
								<option value="${template.id}">${template.name}</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="/include/operation_footer.jsp"%>