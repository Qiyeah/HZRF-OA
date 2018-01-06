<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	var zTree;
	var setting = {
		check: {enable: true},
		data: {simpleData: {enable: true,idKey: "id",pIdKey: "pId"}},
		callback: {onCheck: zTreeOnCheck}
	};
	
	function zTreeOnCheck(event, treeId, treeNode) {
	    var nodes = zTree.getCheckedNodes(true);
	    var strId = "";
	    for(var i=0; i<nodes.length; i++){
	    	strId += "_" + nodes[i].id + ",";
	    }
	    $("#xtlimit").val(strId);
	};
	
	var zNodes =[
        {id:"0",pId:"",name:"全部权限",checked:true,open:true}
        <c:forEach items="${modules}" var="md" varStatus="vx">
			,{id:"${md.id}",pId:"${md.pid}",name:"${md.name}"<c:if test="${pert:hasperti(md.id, role.xtlimit)}">,checked:true</c:if>}
		</c:forEach>
	];

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree = $.fn.zTree.getZTreeObj('treeDemo');
	});
</script>
<div class="bjui-pageContent">
	<form method="post" action="Main/Role/perset" class="pageForm" data-toggle="ajaxform">
		<input type="hidden" name="id" value="${role.id}" /> <input type="hidden" id="xtlimit" name="xtlimit" value="${role.xtlimit}" />
		<table class="table table-condensed table-hover">
			<tr>
				<td width="50%"><label class="control-label x85">角色名称：</label>${role.name}</td>
				<td width="50%"><label class="control-label x85">角色描述：</label>${role.ms}</td>
			</tr>
			<tr>
				<td colspan="2"><label class="control-label x85">拥有权限：</label>
					<div style="padding-left: 90px; height: 300px; clear: both; overflow: auto;">
						<ul id="treeDemo" class="ztree"></ul>
					</div></td>
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
