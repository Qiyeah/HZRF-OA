<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent tableContent">
	<form method="post" action="Main/User/roleset" id="j_custom_form" class="pageForm" data-toggle="validate">
		<input type="hidden" name="id" value="${user.id}" />
		<table id="tabledit1" class="table table-bordered table-hover table-striped table-top" data-toggle="tabledit" data-initnum="0" data-single-noindex="true">
			<thead>
				<tr data-idname="customList[#index#].id">
					<th width="20%" title="优先系数"><input type="text" name="priority" data-rule="required digits" size="3" maxlength="3" /></th>
					<th width="50%" title="角色名称"><select name="r_id" data-rule="required" data-toggle="selectpicker">
							<option value="0">请选择</option>
							<c:forEach items="${allroles}" var="role">
								<option value="${role.id}">${role.name}</option>
							</c:forEach>
					</select></th>
					<th width="30%" title="操作" data-addtool="true"><a href="javascript:;" class="btn btn-green" data-toggle="dosave">保存</a> <a href="Main/User/roledelete_temp"
						class="btn btn-red row-del" data-confirm-msg="确定要删除该新增角色吗？">删除</a></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${! empty userroles}">
					<c:forEach items="${userroles}" var="userrole">
						<tr data-id="${userrole.id}">
							<td>${userrole.priority}</td>
							<td data-val="${userrole.r_id}">${userrole.name}</td>
							<td data-noedit="true">
								<button type="button" class="btn-green" data-toggle="doedit">编辑</button> <a href="Main/User/roledelete/${userrole.id}" class="btn btn-red row-del" data-toggle="doajax"
								data-confirm-msg="确定要删除该用户角色吗？">删除</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<li><button type="submit" class="btn-default" data-icon="save">全部保存</button></li>
	</ul>
</div>
