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
	<form method="post" action="Main/User/add" class="pageForm" data-toggle="validate">
		<input type="hidden" name="dept.id" id="userdeptid" value=""/>
		<table class="table table-condensed table-hover">
			<tr height="30">
				<td><label class="control-label x85">登录帐号：</label> 
				<input type="text" name="dlid" maxlength="25" size="15" data-rule="required; mark" data-rule-mark="[/^[A-Za-z0-9_]+$/, '请输入英文字母和数字组合的登录帐号']"></td>
				<td><label class="control-label x85">用户名称：</label> 
				<input type="text" name="name" maxlength="20" size="15" data-rule="required" /></td>
			</tr>
			<tr height="30">
				<td><label class="control-label x85">用户密码：</label> 
				<input type="password" name="pwd" maxlength="20" size="15" data-rule="用户密码:required" /></td>
				<td><label class="control-label x85">确认密码：</label> 
				<input type="password" name="qrpwd" maxlength="20" size="15" data-rule="确认密码:required;match(pwd)" /></td>
			</tr>
			<tr height="30">
				<td colspan="2"><label class="control-label x85">所属部门：</label> 
				<input type="text" name="department" id="user_select_dept" data-toggle="selectztree" size="20" data-tree="#user_select_dept_tree" readonly />&nbsp;
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
								<option value="${ps.id}">${ps.name}</option>
							</c:forEach>
						</c:if>
				</select></td>
                <td><label class="control-label x85">职位排序：</label> 
                <input type="text" name="no" maxlength="20" size="15" data-rule="required;digits" /></td>
            </tr>
			<tr height="30">
				<td><label class="control-label x85">在职状态：</label> 
				<select name="lo" data-toggle="selectpicker" data-width="150">
						<option value="1" selected>在职</option>
						<option value="0">离职</option>
				</select></td>
				<td><label class="control-label x85">启用状态：</label> 
				<select name="usable" data-toggle="selectpicker" data-width="150">
						<option value="true" selected>是</option>
						<option value="false">否</option>
				</select></td>
			</tr>
			<tr height="30">
				<td colspan="2"><label class="control-label x85">备注信息：</label> 
				<textarea name="zwxx" rows="3" maxlength="200" cols="43"></textarea></td>
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
