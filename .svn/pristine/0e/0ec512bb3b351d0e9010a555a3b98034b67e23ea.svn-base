<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#deptTree_Personal").ajaxUrl({ url : "Main/Personal/main/tree", loadingmask : true });
		$("#deptBox_Personal").ajaxUrl({ url : "Main/Personal/main/telListmain/0-${uname}-${numPerPage}-${pageNum}", loadingmask : true	});
	});

	var curDeptTreeIndex;
	function dept_open_layout(event, treeId, treeNode) {
		curDeptTreeIndex = treeNode.tId;
		$("#deptBox_Personal").ajaxUrl({ url : treeNode.url, loadingmask : true });
		event.preventDefault();
	}
</script>
<div id="deptTree_Personal" class="bjui-pageContent" style="width: 30%; border: solid 1px #CCC; margin-left: -1px; margin-top: -1px; padding: 0px; float: left;"></div>
<div id="deptBox_Personal" class="bjui-layout" style="width: 70%; height:100%; float: right; overflow: hidden"></div>
