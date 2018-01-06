<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#deptTree").ajaxUrl({ url : "Main/Department/main/tree", loadingmask : true });
		$("#deptBox").ajaxUrl({ url : "Main/Department/main/list/0", loadingmask : true	});
	});

	var curDeptTreeIndex;
	function dept_open_layout(event, treeId, treeNode) {
		curDeptTreeIndex = treeNode.tId;
		$("#deptBox").ajaxUrl({ url : treeNode.url, loadingmask : true });
		event.preventDefault();
	}
</script>
<div id="deptTree" class="bjui-pageContent" style="width: 30%; border: solid 1px #CCC; margin-left: -1px; margin-top: -1px; padding: 0px; float: left;"></div>
<div id="deptBox" style="width: 70%; height:100%; float: right; overflow: hidden"></div>
