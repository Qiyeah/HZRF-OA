<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	if (typeof currentCommonTreeIndex !== 'undefined') {
		setTimeout(function() {
			var zTreeObj = $.fn.zTree.getZTreeObj("common-layout-tree");
			var node = zTreeObj.getNodeByTId(currentCommonTreeIndex);
			zTreeObj.expandNode(node, true, true, true);
		}, 100);
	}
</script>
<ul id="common-layout-tree" class="ztree" data-toggle="ztree" data-expand-all="true" data-on-click="common_do_open_layout">
	<li data-id="1" data-pid="0" data-url="Main/CommonParameter/main/list/1">参数目录</li>
	<c:if test="${! empty commons}">
		<c:forEach items="${commons}" var="model">
			<li data-id="${model.id}" data-pid="1" data-url="Main/CommonParameter/main/list/${model.id}">${model.name}</li>
		</c:forEach>
	</c:if>
</ul>