<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#areaTree").ajaxUrl({ url : "Main/Area/main/tree", loadingmask : true });
		$("#areaBox").ajaxUrl({ url : "Main/Area/main/list/1", loadingmask : true	});
	});

	var curAreaTreeIndex;
	function area_open_layout(event, treeId, treeNode) {
		curAreaTreeIndex = treeNode.tId;
		$("#areaBox").ajaxUrl({ url : treeNode.url, loadingmask : true });
		event.preventDefault();
	}
</script>
<div id="areaTree" class="bjui-pageContent" style="width: 30%; border: solid 1px #CCC; margin-left: -1px; margin-top: -1px; padding: 0px; float: left;"></div>
<div id="areaBox" style="width: 70%; height:100%; float: right; overflow: hidden"></div>
