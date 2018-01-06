<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<c:set var="deparmentList" value="${deparmentList}" />
<c:set var="userList" value="${userList}" />
<script type="text/javascript"><!--
	function onCheck(event, treeId, treeNode) {
		var treeObj=$.fn.zTree.getZTreeObj("urse-tree"),nodes=treeObj.getCheckedNodes(true),result="";
   		for(var i=0;i<nodes.length;i++){
    	if(nodes[i].id==10000)
	    result += "<tr><td><div style='display:none;'><input type='checkbox' checked='checked' name='userId' value=\""+nodes[i].value+"\"></div>"+nodes[i].name+"</td></tr>";
    }
    	$("#userBox").html(result);
}
	
	function send() {
		var tmpstr = "";
		var tmpnum = 0;
		$("input[name='userId']:checked").each(function() {
			tmpstr += ";" + $(this).val();
			tmpnum = tmpnum + 1;
		});
		if (tmpnum < 1) {
			alert("请选择下一环节执行人！");
			return false;
		} else if (tmpnum > 1 && $("#amount").val() == "1") {
			alert("下一环节只能选择一个执行人！");
			return false;
		}
	
		if (tmpstr.length > 0) {
			tmpstr = tmpstr.substring(1);
		}
		$("#nexttodoman", window.parent.document).val(tmpstr);
		$("#approveForm", window.parent.document).submit();
		$(this).dialog('close', "selna");
		$(this).dialog('close', "approve_dialog");
	}
//--></script>
<div class="bjui-pageContent">
	<table class="table table-condensed table-hover">
		<tr>
			<td width="30%" class="title">环节名称</td>
			<td width="70%">&nbsp;${wa.name }</td>
		</tr>
		<tr>
			<td colspan="2">
				<div style="width: 100%; overflow: auto" layoutH="92">
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
				<div style="display: none">
					<table class="list" width="100%">
						<tbody id="userBox" align="center">	</tbody>
					</table>
				</div>
			</td>
		</tr>
	</table>			
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="button" class="btn-default" data-icon="save" onclick="send()">保存</button></li>
    </ul>
</div>
