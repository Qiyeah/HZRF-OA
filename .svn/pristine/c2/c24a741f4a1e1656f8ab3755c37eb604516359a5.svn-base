<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	if(typeof curAreaTreeIndex !== 'undefined'){
		setTimeout(function() {
			var zTreeObj = $.fn.zTree.getZTreeObj("area-tree");
			var node = zTreeObj.getNodeByTId(curAreaTreeIndex);
			zTreeObj.expandNode(node, true, true, true);
		}, 100);
	}
</script>
<ul id="area-tree" class="ztree" data-toggle="ztree" data-on-click="area_open_layout">
	<c:if test="${! empty areas}">
		<c:forEach items="${areas}" var="model">
			<li data-id="${model.id}" data-pid="${model.pid}" data-url="Main/Area/main/list/${model.id}"<c:if test="${model.pid==0}">data-open="true"</c:if>>${model.name}</li>
		</c:forEach>
	</c:if>
</ul>
