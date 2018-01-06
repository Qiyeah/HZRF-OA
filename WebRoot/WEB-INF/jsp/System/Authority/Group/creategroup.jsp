<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<style>
<!--
form#pageForm1 span.wrap_bjui_btn_box{
	width:100%;
}
-->
</style>
<div class="bjui-pageContent">
		<form id="pageForm1" class="pageForm" action="Main/Group/creategroup" method="post" data-toggle="validate">		
		<input name="t_Personal.id" class="required" type="hidden" value="${personal.id}" />
		<input type = "hidden" name="u_id" value="${u_id }"/>
		<label style="margin-bottom:10px">群组名称:</label><span><input type="text" name="t_Group.name" class="required" data-rule="required"></span>
		<div class="pageFormContent">
		<div style="height: 420px;width:520px;margin:0 auto;">
		<div style="float:left; display:block; overflow:auto; width:280px; height:100%; border:solid 1px #4CA5D8;">
			<ul id="urse-tree" class="ztree" data-toggle="ztree" data-expand-all="true" data-check-enable="true" data-on-check="onCheck">
				<c:if test="${! empty deparmentList}">
					<c:forEach items="${deparmentList}" var="department">
						<li data-id="${department.id}" data-pid="${department.pid}">${department.fname}</li>
					</c:forEach>
					<c:forEach items="${userList}" var="user">
						<li data-id="10000" data-faicon="user" <c:forEach items="${checkUsers}" var="checkUser"><c:if test="${checkUser.id==user.id }">data-checked="true"</c:if></c:forEach> 
						data-value="${user.id}" data-name="${user.name}" data-pid="${user.d_id }">${user.name}</li>
					</c:forEach>
				</c:if>
			</ul>
			
			
		</div>
		<div style="float:left;height:100%;line-height:340px;padding-top: 180px;">
			<img src="images/arrow_left.gif" border="0"/>
		</div>
		<div style="float:right; display:block;overflow:auto; width:200px; height:100%; border:solid 1px #4CA5D8;">
			<table class="table table-bordered table-hover table-striped table-top table-center">
				<thead><tr><th>选定人</th></tr></thead>
				<tbody id="resultBox">
					<c:forEach items="${checkUsers }" var="checkUser">
						<tr><td><input type="text"  name="u_ids" value="${checkuser.id }"/><input type="text"  name="u_names" value="${checkuser.name }"/>${checkUser.name }</td></tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
		</div>
	</div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>
<script type="text/javascript">
function onCheck(event, treeId, treeNode){
	var treeObj=$.fn.zTree.getZTreeObj("urse-tree"),nodes=treeObj.getCheckedNodes(true),result="";
    for(var i=0;i<nodes.length;i++){
    	if(nodes[i].id==10000)
    		
	    result += "<tr><td><input type='hidden'  name='u_ids' value='"+nodes[i].value+"'/><input type='hidden'  name='u_names' value='"+nodes[i].name+"'/>"+nodes[i].name+"</td></tr>";
    }
    $("#resultBox").html(result);
}
</script>