<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	if(typeof curDeptTreeIndex !== 'undefined'){
		setTimeout(function() {
			var zTreeObj = $.fn.zTree.getZTreeObj("dept-trees");
			var node = zTreeObj.getNodeByTId(curDeptTreeIndex);
			zTreeObj.expandNode(node, true, true, true);
		}, 100);
	}
</script>
<ul id="dept-trees" class="ztree" data-toggle="ztree" data-expand-all="true" data-on-click="dept_open_layout">
	<li data-id="10" data-pid="" data-url="Main/Personal/main/telListmain/0">所有单位</li>
	<c:if test="${! empty depts}">
		<c:forEach items="${depts}" var="model">
			<li data-id="1${model.id}" data-pid="1${model.pid}" data-url="Main/Personal/main/telListmain/${model.id}">${model.fname}</li>
		</c:forEach>
	</c:if>
	<li data-id="100" data-pid="" data-url="Main/Personal/main/telListmain/0">所有群组</li>
	<c:if test="${! empty group}">
		<c:forEach items="${group}" var="model">
			<li data-id="${model.id}" data-pid="100" data-url="Main/Personal/main/telGroup/${model.id}">${model.name}</li>
		</c:forEach>
	</c:if>
</ul>
