<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	if (typeof curModuleTreeIndex !== 'undefined') {
		setTimeout(function() {
			var zTreeObj = $.fn.zTree.getZTreeObj("module-layout-tree");
			var node = zTreeObj.getNodeByTId(curModuleTreeIndex);
			zTreeObj.expandNode(node, true, true, true);
		}, 100);
	}
	function divUrl() {
		$("#moduleBox").ajaxUrl({ url : "Main/Module/main/list/0", loadingmask : true });
	}
</script>

<ul id="module-layout-tree" class="ztree" data-toggle="ztree" data-expand-all="false" data-on-click="module_do_open_layout">
    <li data-open="true" data-url="Main/Module/main/list/0">系统功能模块
        <c:if test="${! empty modules}">
            <c:forEach items="${modules}" var="model">
                <li data-id="${model.id}" data-pid="${model.pid}" <c:if test="${model.islast==1 }">data-faicon="circle"</c:if> data-url="Main/Module/main/list/${model.id}">${model.name}</li>
            </c:forEach>
        </c:if>
	</li>
</ul>

