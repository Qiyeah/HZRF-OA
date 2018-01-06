<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	$("#commonBox").ajaxUrl({url:"Main/CommonParameter/main/list/1", loadingmask:true});

});

var currentCommonTreeIndex;
function common_do_open_layout(event, treeId, treeNode) {
	currentCommonTreeIndex = treeNode.tId;
    $("#commonBox").ajaxUrl({url:treeNode.url, loadingmask:true});
    event.preventDefault();
}
</script>
<div id="commonTree" class="bjui-pageContent" style="width: 20%; border: solid 1px #CCC; margin-left: -1px; margin-top: -1px; padding: 0px; float: left;">
	<%@include file="tree.jsp"%>
</div>
<div id="commonBox" class="bjui-layout" style="width: 80%; height:100%; float: right; overflow: hidden"></div>


