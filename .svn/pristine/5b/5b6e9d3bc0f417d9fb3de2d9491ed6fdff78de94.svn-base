<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	if(typeof curContractEquipmentTreeIndex !== 'undefined'){
		setTimeout(function() {
			var zTreeObj = $.fn.zTree.getZTreeObj("contractequipment-tree");
			var node = zTreeObj.getNodeByTId(curContractEquipmentTreeIndex);
			zTreeObj.expandNode(node, true, true, true);
		}, 100);
	}
</script>
<ul id="contractequipment-tree" class="ztree" data-toggle="ztree" data-on-click="contractequipment_open_layout" data-expand-all="true">
		<li data-id="1" data-pid="0" data-url="Main/Mail/receive" data-faicon-close="cab">我的邮箱 <c:if test="${totle>0}">(${totle})</c:if></li>
		<li data-id="10" data-pid="1" data-url="Main/Mail/add" data-tabid="form" data-faicon="edit">写信</li>
		<li data-id="11" data-pid="1" data-url="Main/Mail/receive" data-tabid="form-input" data-faicon="envelope">收件箱<c:if test="${receiveCountTotal>0}">(${receiveCountTotal})</c:if></li>
		<li data-id="12" data-pid="1" data-url="Main/Mail/send" data-tabid="form-input" data-faicon="twitter-square">发信箱 <c:if test="${sendCount>0}">(${sendCount})</c:if></li>
		<li data-id="13" data-pid="1" data-url="Main/Mail/draft" data-tabid="form-input" data-faicon="copy">草稿箱 <c:if test="${draftCount>0}">(${draftCount})</c:if></li>
		<li data-id="14" data-pid="1" data-url="Main/Mail/rubbish" data-tabid="form-input" data-faicon="trash">垃圾箱 <c:if test="${rubbishCount>0}">(${rubbishCount})</c:if></li>
</ul>
