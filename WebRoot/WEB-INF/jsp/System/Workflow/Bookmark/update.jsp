<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>

<div class="pageContent">
	<form method="post" action="Main/Bookmark/update" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input name="t_Bookmark.id" type="hidden" value="${bookmark.id}" />
		<div class="pageFormContent" layoutH="56">
			<table class="wordInfo" align="center" style="width: 98%">
				<tr>
					<td width="30%" class="title">书签名称</td>
					<td width="70%"><input name="t_Bookmark.name" type="text" value="${bookmark.name }" class="required" style="width: 98%;" /></td>
				</tr>
				<tr>
					<td class="title">书签说明</td>
					<td><input name="t_Bookmark.descript" type="text" value="${bookmark.descript }" class="required" style="width: 98%;" /></td>
				</tr>
				<tr>
					<td class="title">书签备注</td>
					<td><input name="t_Bookmark.value" type="text" value="${bookmark.value}" style="width: 98%;" /></td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
