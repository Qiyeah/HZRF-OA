<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<c:set var="receiveCount" value="${receiveCount }" />
<c:set var="sendCount" value="${sendCount}" />
<c:set var="draftCount" value="${draftCount}" />
<c:set var="rubbishCount" value="${rubbishCount}" />
<c:set var="page" value="${page}" />
<%
	String basePath = request.getContextPath() + "/";
%>

<script type="text/javascript">
	$(document).ready(function() {
		$("#Tree_Mail").ajaxUrl({ url : "Main/Mail/main/tree", loadingmask : true });
		$("#Box_Mail").ajaxUrl({ url : "Main/Mail/main/list", loadingmask : true	});
	});

	var curContractEquipmentTreeIndex;
	function contractequipment_open_layout(event, treeId, treeNode) {
		curContractEquipmentTreeIndex = treeNode.tId;
		//判断treeId
		if (treeNode.id == 10) {
			$(this).dialog({
				id : 'SelectReader',
				url : treeNode.url,
				title : '写信',
				mask : true,
				drawable : true,
				resizable : true,
				maxable : true,
				width : 1000,
				height : 600
			});
		} else {
			$("#Box_Mail").ajaxUrl({
				url : treeNode.url,
				loadingmask : true
			});
		}
		event.preventDefault();
	}
</script>
<div id="Tree_Mail" class="bjui-pageContent" style="width: 20%; border: solid 1px #CCC; margin-left: -1px; margin-top: -1px; padding: 0px; float: left;"></div>
<div id="Box_Mail" class="bjui-layout" style="width: 80%; float: right; height: 100%; overflow: hidden"></div>
