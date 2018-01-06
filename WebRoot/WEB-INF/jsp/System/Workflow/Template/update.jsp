<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	function save() {
		edittemplate.window.SaveDocument();
		edittemplate.document.forms[0].submit();
		/* $.pdialog.closeCurrent();
		navTab.reload("Main/Template/main", {navTabId: "templatemg"}); */
		$(this).dialog("closeCurrent");
	}
</script>
<div class="bjui-pageContent">
	<iframe name="edittemplate" style="width: 100%; height:100%" src="${url}" frameborder="0"></iframe>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="button" class="btn-default" data-icon="save" onclick="save()">保存</button></li>
    </ul>
</div>
