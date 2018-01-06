<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
alert("出错页面");
	window.parent.$(this).alertmsg('error', "${msgstr}");
	window.parent.dialog("closeCurrent");
</script>

