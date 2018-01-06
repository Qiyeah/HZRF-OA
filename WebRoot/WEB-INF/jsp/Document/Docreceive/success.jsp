<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	var flag ="${flag}";
	if(flag == 'true'){
	window.parent.$(this).alertmsg('ok', "${msgstr}");
	}else{
	window.parent.$(this).alertmsg('error', "数据处理出现错误！请检查后重新提交!");
	}
	
	window.parent.$(this).navtab('refresh', 'receive_todo1');
	window.parent.$(this).navtab('refresh', 'receive_todo');
	window.parent.$(this).dialog("close","approve_dialog1");
</script>