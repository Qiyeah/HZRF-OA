<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	if(typeof curGroupTreeIndex !== 'undefined'){
		setTimeout(function() {
			var zTreeObj = $.fn.zTree.getZTreeObj("dept-trees");
			var node = zTreeObj.getNodeByTId(curGroupTreeIndex);
			zTreeObj.expandNode(node, true, true, true);
		}, 100);
	}
</script>
<ul id="group-trees" class="ztree" data-toggle="ztree" data-expand-all="true" data-on-click="group_open_layout">

	<li data-id="100" data-pid="" data-url="Main/Group/main/list/0">所有群组</li>
	<c:if test="${! empty group}">
		<c:forEach items="${group}" var="model">
			<li data-id="${model.id}" data-pid="100" data-url="Main/Group/main/list/${model.id}">${model.name}</li>
		</c:forEach>
	</c:if>
</ul>
