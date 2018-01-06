<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%@ taglib prefix="pert" uri="/WEB-INF/permit.tld"%>
<div class="pageContent">
	<form method="post" action="Main/power/perset" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input name="id" class="required" type="hidden" value="${role.id}" />
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>当前角色名称：</dt>
				<dd>${role.rolename}</dd>
			</dl>	
			<dl><dt>权限设置：</dt></dl>
			<div style="height: 245px; clear: both; overflow: auto;">	
				${folders}
			</div>	
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
