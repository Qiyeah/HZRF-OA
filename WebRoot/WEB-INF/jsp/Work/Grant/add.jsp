<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<style>
<!--
form#pagerForm1 span.wrap_bjui_btn_box{
width:100%;
}
-->
</style>
<div class="bjui-pageContent">
	<form id="pagerForm1" class="pageForm" action="Main/Grant/add" method="post" data-toggle="validate">
		<input type="hidden" name="t_Grant.u_id" value="${u_id}" />
		<div class="pageFormContent" layoutH="56">
			<table class="wordInfo" align="center" style="width: 98%;">
			    <tr>
					<td class="title">被授权人</td>
					<td>
					    <input type="hidden" name="chairman.userid" />					
						<input name="chairman.username" type="text" suggestFields="username" suggestUrl="Main/search/searchUser" lookupGroup="chairman" class="required" data-rule="required" data-msg-required="必填" style="width:100%;"/>
				    </td>
				</tr>
				<tr>
					<td width="25%" class="title">开始日期</td>
					<td width="75%"><input type="text" name="t_Grant.s_date" value="${nowday }" class="required" data-toggle="datepicker" data-rule="开始日期:required;date" data-msg-date="格式错误" data-msg-required="必填" maxlength="13" style="width:100%;" /></td>
				</tr>
				<tr>
					<td width="25%" class="title">结束日期</td>
					<td width="75%"><input type="text" name="t_Grant.e_date" value="" class="required" data-toggle="datepicker" data-rule="结束日期:required; date;match[gt, t_Grant.s_date, date]" data-msg-date="格式错误" data-msg-required="必填" style="width:100%;"/></td>
				</tr>
				<tr>
					<td class="title">授权范围</td>
					<td>
					    <select name="t_Grant.type" class="combox" data-toggle="selectpicker" data-width="100%">
							<option value="9">全部</option>
							<option value="1">收文办理</option>
							<option value="2">发文办理</option>
							<option value="3">请休假审批</option>
							<option value="5">会议审批</option>
					    </select>
					</td>
				</tr>
				<tr>
					<td class="title">是否启用</td>
					<td>
					    <select name="t_Grant.usable" class="combox" data-toggle="selectpicker" data-width="100%">
							<option value="0" selected >启用</option>
							<option value="1" >禁用</option>
					    </select>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>