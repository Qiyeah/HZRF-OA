<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
<!--
	function selectIcon(obj) {
		$("#icontext").val(obj);
		$("#iconspan").removeAttr("class");
		$("#iconspan").addClass("fa " + obj);
		$(this).dialog('closeCurrent');
	}
//-->
</script>
<div class="bjui-pageContent">
	<c:forEach items="${icons }" var="icon">
		<a onclick="selectIcon('${icon.iconcode }')"><span title="${icon.iconcode }" style="width: 40px; height: 40px; text-align: center; cursor: pointer"
			class="fa fa-lg fa-2x ${icon.iconcode }"></span></a>
	</c:forEach>
</div>