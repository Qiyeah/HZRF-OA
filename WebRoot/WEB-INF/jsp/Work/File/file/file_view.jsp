<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<style>
<!--
.input dt{width:60px;text-align: right;}
-->
</style>
<div class="pageContent" >
	<div class="pageFormContent" layoutH="58">
		<dl class="input"><dt>文件名称：</dt><dd>${file.filename}</dd></dl>
		<dl class="input"><dt>文件类型：</dt><dd>${file.type }</dd></dl>
		<dl class="input"><dt>文件大小：</dt><dd>${file.size }</dd></dl>
		<dl class="input"><dt>下载次数：</dt><dd>${file.downnum}</dd></dl>
		<dl class="input"><dt>修改时间：</dt><dd><fmt:formatDate value="${file.edittime }" pattern="yyyy-MM-dd HH:mm:ss"/></dd></dl>
		<div class="divider"></div>
		<dl class="nowrap input">
			<dt>备注：</dt>
			<dd>${file.remark}</dd>
		</dl>
		<div class="divider"></div>
		<dl class="input"><dt>上传者：</dt><dd>${file.username}</dd></dl>
		<dl class="input"><dt>上传时间：</dt><dd><fmt:formatDate value="${file.uploadtime }" pattern="yyyy-MM-dd HH:mm:ss"/></dd></dl>
	</div>
	<div class="formBar">
		<ul><li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li></ul>
	</div>
</div>