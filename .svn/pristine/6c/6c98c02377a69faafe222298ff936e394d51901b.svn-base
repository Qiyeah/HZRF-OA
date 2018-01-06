<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent">
	<form method="post" action="Main/power/add"  class="pageForm required-validate" onSubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<dl>
				<dt>角色名：</dt>
				<dd><input class="required" name="t_File_Role.rolename" type="text" size="30" /></dd>
			</dl>
			<dl>
				<dt>角色描述：</dt>
				<dd><input class="required" name="t_File_Role.desrole" type="text" size="30" /></dd>
			</dl>
			<div class="divider"></div>
			<dl>
				<dt>创建人ID：</dt>
				<dd><input readonly="readonly" name="t_File_Role.dlid" type="text" size="30" value="${loginModel.dlId}" /></dd>
			</dl>
			<dl>
				<dt>创建时间：</dt>
				<dd><input readonly="readonly" name="t_File_Role.createtime" type="text" size="30" value="${time}" /></dd>
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
