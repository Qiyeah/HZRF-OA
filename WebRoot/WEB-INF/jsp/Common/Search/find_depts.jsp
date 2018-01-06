<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<c:set var="checkDepts" value="${checkDepts}"/>
<c:set var="deparments" value="${deparments}"/>
<div class="bjui-pageContent">
		<div class="pageFormContent" layoutH="58">
		<div style="height: 350px;width:436px;margin:0 auto;">
		<div style="float:left; display:block; margin:5px; overflow:auto; width:200px; height:100%; border:solid 1px #4CA5D8;">
			<ul id="depar-tree" class="ztree" data-toggle="ztree" data-expand-all="true" data-check-enable="true" data-on-check="onCheck">
					<c:if test="${! empty deparments}">
					<c:forEach items="${deparments}" var="deparment">
					<li data-id="${deparment.id}" data-pid="${deparment.pid}">${deparment.fname}</li>
					</c:forEach>
					</c:if>
				</ul>
			</div>
			<div style="float: left; height: 100%; line-height: 340px;">
				<img src="images/arrow_left.gif" border="0" />
			</div>
			<div style="float: left; display: block; margin: 5px; overflow: auto; width: 200px; height: 100%; border: solid 1px #4CA5D8;">
				<table  class="table table-bordered table-hover table-striped table-top table-center">
					<thead>
						<tr>
							<th align="center">科室</th>
						</tr>
					</thead>
					<tbody id="resultBox" align="center">
						<c:if test="${not empty checkDepts }">
							<c:forEach items="${checkDepts }" var="checkDept">
								<tr><td><div style="display:none"><input type='checkbox' checked='checked' name='${checkDept.fname }' value="{id:'${checkDept.id }', name:'${checkDept.fname }'}"/></div>${checkUser.fname }</td></tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<iframe src="javascript:false"
	style="position: absolute; visibility: inherit; top: 0px; left: 0px; width: 100%; height: 100%; z-index: -1; filter ='progid: DXImageTransform.Microsoft.Alpha(style=0, opacity=0 )';"></iframe>

<script type="text/javascript">
/* function onClick(event, treeId, treeNode, clickFlag){
	var treeObj=$.fn.zTree.getZTreeObj("depar-tree");
	var result="";
	for(var i=0;i<nodes.length;i++){
	    result = "<input type='checkbox' checked='checked' name='depts' value=\"{'xbks.id':'"+treeNode.value+"', 'xbks.name':'"+treeNode.name+"'}\">";
	}
	$("#resultBox").html(result);
    $("#confirmChoose").click();
    } */
function onCheck(event, treeId, treeNode){
	var treeObj=$.fn.zTree.getZTreeObj("depar-tree");
	var nodes=treeObj.getCheckedNodes(true);
	var result="";
	 for(var i=0;i<nodes.length;i++){
	    result += "<tr><td><div style='display:none;'><input type='checkbox' checked='checked' name='depts' value=\"{'xbks.id':'"+nodes[i].id+"', 'xbks.name':'"+nodes[i].name+"'}\"></div>"+nodes[i].name+"</td></tr>";
  	 }
	
	 $("#resultBox").html(result);
}
</script>

<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn btn-close" data-icon="close">关闭</button></li>
		 <li><button type="button" class="btn-green" data-toggle="lookupback" data-lookupid="depts" data-warn="请选择科室" data-icon="check-square-o">确定</button></li>
	</ul>
</div>