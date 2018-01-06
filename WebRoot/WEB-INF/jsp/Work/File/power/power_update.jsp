<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent">
	<form method="post" action="Main/power/update"  class="pageForm required-validate" onSubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="t_File_Role.id" value="${role.id }"/>
			<dl>
				<dt>角色名：</dt>
				<dd><input class="required" name="t_File_Role.rolename" value="${role.rolename }" type="text" size="30" /></dd>
			</dl>
			<dl>
				<dt>角色描述：</dt>
				<dd><input class="required" name="t_File_Role.desrole" value="${role.desrole }" type="text" size="30" /></dd>
			</dl>
			<div class="divider"></div>
			<dl>
				<dt>创建人ID：</dt>
				<dd><input readonly="readonly" type="text" size="30" value="${role.dlid }" /></dd>
			</dl>
			<dl>
				<dt>创建时间：</dt>
				<dd><input readonly="readonly" type="text" size="30" value="<fmt:formatDate value="${role.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" /></dd>
			</dl>

		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
