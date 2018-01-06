<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
    $(document).ready(function() {
        $("#groupTree_Personal").ajaxUrl({ url : "Main/Group/main/tree", loadingmask : true });
        $("#groupBox_Personal").ajaxUrl({ url : "Main/Group/main/list/5", loadingmask : true });
    });

    var curGroupTreeIndex;
    function group_open_layout(event, treeId, treeNode) {
        curGroupTreeIndex = treeNode.tId;
        $("#groupBox_Personal").ajaxUrl({ url : treeNode.url, loadingmask : true });
        event.preventDefault();
    }
</script>
<div id="groupTree_Personal" class="bjui-pageContent" style="width: 30%; border: solid 1px #CCC; margin-left: -1px; margin-top: -1px; padding: 0px; float: left;"></div>
<div id="groupBox_Personal" class="bjui-layout" style="width: 70%; height:100%; float: right; overflow: hidden"></div>
