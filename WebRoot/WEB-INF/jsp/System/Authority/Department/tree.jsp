<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	if(typeof curDeptTreeIndex !== 'undefined'){
		setTimeout(function() {
			var zTreeObj = $.fn.zTree.getZTreeObj("dept-tree");
			var node = zTreeObj.getNodeByTId(curDeptTreeIndex);
			zTreeObj.expandNode(node, true, true, true);
		}, 100);
	}
</script>
<ul id="dept-tree" class="ztree" data-toggle="ztree" data-expand-all="true" data-on-click="dept_open_layout">
	<li data-id="10" data-pid="" data-url="Main/Department/main/list/0">惠州市人民防空办公室</li>
	<c:if test="${! empty depts}">
		<c:forEach items="${depts}" var="model">
			<li data-id="1${model.id}" data-pid="1${model.pid}" data-url="Main/Department/main/list/${model.id}">${model.fname}</li>
		</c:forEach>
	</c:if>
</ul>
