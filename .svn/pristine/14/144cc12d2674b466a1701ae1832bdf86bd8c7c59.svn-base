<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	//选择事件
	function S_NodeCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId), nodes = zTree.getCheckedNodes(true)
		var ids = '', names = ''

		for (var i = 0; i < nodes.length; i++) {
			ids += ',' + nodes[i].id
			names += ',' + nodes[i].name
		}
		if (ids.length > 0) {
			ids = ids.substr(1);
			names = names.substr(1);
		}
		var $from = $('#' + treeId).data('fromObj')
		if ($from && $from.length) {
			$from.val(names);
			$("#userdeptid").val(ids);
		}
	}
	//单击事件
	function S_NodeClick(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId)
		zTree.checkNode(treeNode, !treeNode.checked, true, true)
		event.preventDefault()
	}
</script>
<div class="bjui-pageContent">
	<form method="post" action="Main/User/update" class="pageForm" data-toggle="validate">
		<input type="hidden" name="id" value="${user.id}" />
		<input type="hidden" name="dept.id" id="userdeptid" value="${user.d_id }"  />
		<table class="table table-condensed table-hover">
			<tr height="30">
				<td><label class="control-label x85">登录帐号：</label>&nbsp;${user.dlid}</td>				
				<td><label class="control-label x85">用户名称：</label>
				<input type="text" name="name" value="${user.name}" maxlength="20" size="15" data-rule="required" /></td>
			</tr>
			<tr height="30">
				<td><label class="control-label x85">新 密 码：</label>
				<input type="password" name="pwd" maxlength="20" size="15" data-tip="修改则填写" /></td>
				<td><label class="control-label x85">确认密码：</label>
				<input type="password" name="qrpwd" maxlength="20" size="15" data-tip="修改则填写" /></td>
			</tr>
			<tr height="30">
				<td colspan="2"><label class="control-label x85">所属部门：</label> 
				<input type="text" name="department" value="${department.sname }" id="user_select_dept" data-toggle="selectztree" size="20" data-tree="#user_select_dept_tree" readonly />
					<ul id="user_select_dept_tree" class="ztree hide" data-toggle="ztree" data-expand-all="false" data-check-enable="true" data-chk-style="radio" data-radio-type="all"
						data-on-check="S_NodeCheck" data-on-click="S_NodeClick">
						<c:if test="${! empty depts}">
							<c:forEach items="${depts}" var="dept">
								<li data-id="${dept.id}" data-pid="${dept.pid}">${dept.sname}</li>				
							</c:forEach>
						</c:if>
					</ul></td>
			</tr>
			<tr height="30">
				<td><label class="control-label x85">所属职位：</label>
				<select name="position.id" data-toggle="selectpicker" data-width="150">
						<c:if test="${! empty positions}">
							<c:forEach items="${positions}" var="ps">
								<option value="${ps.id}" <c:if test="${user.pid==ps.id}">selected="selected"</c:if>>${ps.name}</option>
							</c:forEach>
						</c:if>
					</select></td>
                <td><label class="control-label x85">职位排序：</label> 
                <input type="text" name="no" value="${user.no}" maxlength="20" size="15" data-rule="required;digits" /></td>
            </tr>
			<tr height="30">
				<td><label class="control-label x85">在职状态：</label>
				<select name="lo" data-toggle="selectpicker" data-width="150">
						<option value="1" <c:if test="${user.lo=='1'}">selected</c:if>>在职</option>
						<option value="0" <c:if test="${user.lo=='0'}">selected</c:if>>离职</option>
					</select></td>
				<td><label class="control-label x85">启用状态：</label>
				<select name="usable" data-toggle="selectpicker" data-width="150">
						<option <c:if test="${user.usable=='1'}">selected</c:if> value="true">是</option>
						<option <c:if test="${user.usable=='0'}">selected</c:if> value="false">否</option>
					</select></td>
			</tr>
			<tr height="30">
				<td colspan="2"><label class="control-label x85">备注信息：</label>
				<textarea name="zwxx" rows="3" maxlength="200" cols="43">${user.zwxx}</textarea></td>
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
