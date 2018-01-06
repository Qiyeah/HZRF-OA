<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent">
	<form method="post" action="Main/file/updateFolder/${folder.id }"  class="pageForm required-validate" onSubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="60">
			<dl>
				<dt style="width:60px;text-align: right;">目录名：</dt>
				<dd>${folder.foldername }</dd>
			</dl>
			<dl>
				<dt style="width:60px;text-align: right;">创建者ID：</dt>
				<dd>${folder.dlid }</dd>
			</dl>
			<dl>
				<dt style="width:60px;text-align: right;">创建时间：</dt>
				<dd><fmt:formatDate value="${folder.creattime }" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			</dl>
			<dl class="nowrap">
				<dt style="width:60px;text-align: right;">备注：</dt>
				<dd style="width:200px;">${folder.remark }</dd>
		  	</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
