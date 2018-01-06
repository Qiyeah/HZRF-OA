<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#moduleTree").ajaxUrl({ url : "Main/Module/main/tree", loadingmask : true });
		$("#moduleBox").ajaxUrl({ url : "Main/Module/main/list/${pId}", loadingmask : true });
	});

	var curModuleTreeIndex;
	function module_do_open_layout(event, treeId, treeNode) {
		curModuleTreeIndex = treeNode.tId;
		$("#moduleBox").ajaxUrl({ url : treeNode.url, loadingmask : true });
        var zTreeObj = $.fn.zTree.getZTreeObj("module-layout-tree");
        zTreeObj.expandNode(treeNode, true);
		event.preventDefault();
	}
</script>
<div id="moduleTree" class="bjui-pageContent" style="width: 25%; border: solid 1px #CCC; margin-left: -1px; margin-top: -1px; padding: 0px; float: left;"></div>
<div id="moduleBox" class="bjui-layout" style="width: 75%; height: 100%; float: right; overflow: hidden"></div>
