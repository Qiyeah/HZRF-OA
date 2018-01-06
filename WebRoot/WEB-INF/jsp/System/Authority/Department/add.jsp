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
			$("#area").val(ids);
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
	<form method="post" action="Main/Department/add" class="pageForm" data-toggle="validate">
		<input type="hidden" name="t_Department.pid" value="${organId}"/>
		<input type="hidden" name="t_Department.dlvl" value="${dlvl }" />
		<input type="hidden" name="t_Department.area" id="area" value="${area}" />
		<table class="table table-condensed table-hover">
			<tr>
				<td><label class="control-label x85">部门全称：</label>
					<input type="text" name="t_Department.fname" maxlength="50" size="30" data-rule="required" /></td>
			</tr>
			<tr>
				<td><label class="control-label x85">部门简称：</label>
					<input type="text" name="t_Department.sname" maxlength="20" size="30" data-rule="required" /></td>
			</tr>
			<tr>
				<td><label class="control-label x85">所属区域：</label>
					<input type="text" name="area" id="j_ztree_menus2" value="${areaname }" data-toggle="selectztree" size="20" data-tree="#j_select_tree2" readonly data-rule="required" />
					<ul id="j_select_tree2" class="ztree hide" data-toggle="ztree" data-expand-all="false" data-check-enable="true" data-chk-style="radio" data-radio-type="all"
						data-on-check="S_NodeCheck" data-on-click="S_NodeClick">
						<c:if test="${! empty areas}">
							<c:forEach items="${areas}" var="model">
								<li data-id="${model.id}" data-pid="${model.pid}">${model.name}</li>
							</c:forEach>
						</c:if>
					</ul></td>
			</tr>
			<tr>
				<td><label class="control-label x85">部门排序：</label>
					<input type="text" name="t_Department.no" maxlength="20" size="20" data-rule="digits" /></td>
			</tr>
			<tr>
				<td><label class="control-label x85">启用状态：</label>
					<select name="t_Department.status" data-toggle="selectpicker" class="show-tick"	style="display: none;">
						<option value="1" selected>已启用</option>
						<option value="0">未启用</option>
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
