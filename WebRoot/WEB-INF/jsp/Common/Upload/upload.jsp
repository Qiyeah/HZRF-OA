<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form method="post" id="approveForm" action="Main/upload/upload/${flag }" enctype="multipart/form-data" class="pageForm" data-toggle="validate">
		<table class="table table-condensed table-hover">
			<tr>
				<td align="center" colspan="2"><h2>请选择需要导入的Excel文件</h2></td>
			</tr>
			<tr>
				<td><label class="control-label x90">Excel文件：</label></td>
				<td><input type="file" name="excel" data-rule="required" size="25" /></td>
			</tr>
		</table>
	</form>
</div>

<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>