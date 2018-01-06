<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	/* window.parent.$(this).BJUI.alertmsg('ok', '${msgstr}');
	window.parent.$(this).BJUI.navtab("reload","Main/Docreceive/main", {id : "receive_all"});
	window.parent.$(this).dialog("close","approve_dialog"); */
	window.parent.$(this).alertmsg('ok', "${msgstr}");
	
	window.parent.$(this).navtab('reload', {navTabId : 'receive_all'});
	window.parent.$(this).dialog("close","approve_dialog");
</script>

