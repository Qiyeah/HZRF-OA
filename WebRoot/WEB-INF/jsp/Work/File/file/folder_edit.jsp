<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="pageContent">
	<form method="post" action="Main/file/updateFolder/${folder.id }"  class="pageForm required-validate" onSubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="60">
			<dl>
				<dt style="width:60px;text-align: right;">旧目录名：</dt>
				<dd><input readonly="readonly" name="ofoldername" type="text" size="30" value="${folder.foldername }" /></dd>
			</dl>
			<dl>
				<dt style="width:60px;text-align: right;">新目录名：</dt>
				<dd><input class="required" name="foldername" type="text" size="30" /></dd>
			</dl>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt style="width:60px;text-align: right;">备注：</dt>
				<dd style="width:200px;"><textarea name="remark" cols="50" rows="4">${folder.remark }</textarea></dd>
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
