<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<c:set var="deparmentList" value="${deparmentList}"/>
<c:set var="userList" value="${userList}"/>
<div class="bjui-pageContent">
	<div class="pageFormContent">
		<div style="height: 420px;width:520px;margin:0 auto;">
		<div style="float:left; display:block; overflow:auto; width:280px; height:100%; border:solid 1px #4CA5D8;">
			<ul id="urse-tree" class="ztree" data-toggle="ztree" data-expand-all="true" data-check-enable="true" data-on-check="onCheck">
				<c:if test="${! empty group}">
					<c:forEach items="${group}" var="group">
						<li data-id="${group.id}00" data-pid="">${group.name}</li>
					</c:forEach>
					<c:forEach items="${groupuserList}" var="user">
					<c:if test="${user.pid!=0}">
						<li data-id="10000" data-faicon="user" <c:forEach items="${checkUsers}" var="checkUser"><c:if test="${checkUser.id==user.id }">data-checked="true"</c:if></c:forEach> 
						data-value="${user.id}" data-name="${user.name}" data-pid="${user.pid }00">${user.name}</li>
					</c:if>
					</c:forEach>
					
				</c:if>
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
						<tr><td><div style="display:none;"><input type="checkBox" checked name="user" value="userId'${checkuser.id }'userName'${checkUser.name }'}"/></div>${checkUser.name }</td></tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
		</div>
	</div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button type="button" class="btn-green" data-toggle="lookupback" data-lookupid="user" data-warn="请选择" data-icon="check-square-o">确定</button></li>
    </ul>
</div>

<script type="text/javascript">
function onCheck(event, treeId, treeNode){
	var treeObj=$.fn.zTree.getZTreeObj("urse-tree"),nodes=treeObj.getCheckedNodes(true),result="";
    for(var i=0;i<nodes.length;i++){
    	if(nodes[i].id==10000)
	    result += "<tr><td><div style='display:none;'><input type='checkbox' checked='checked' name='user' value=\"{userId:'"+nodes[i].value+"', userName:'"+nodes[i].name+"'}\"></div>"+nodes[i].name+"</td></tr>";
    }
    $("#resultBox").html(result);
}
</script>