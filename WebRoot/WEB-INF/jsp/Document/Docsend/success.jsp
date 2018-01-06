<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">

	window.parent.BJUI.alertmsg('ok', '${msgstr}');
	window.parent.BJUI.navtab('reload', {navTabId : 'docsendtodo'});
	window.parent.BJUI.dialog("closeCurrent");
</script>

