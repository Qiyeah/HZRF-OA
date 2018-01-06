<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
			<table class="wordInfo" align="center" style="width: 98%;">
				<tr>
					<td width="25%" class="title">创建日期</td>
					<td width="75%"><input type="text" name="t_Note.wdate" value="${note.wdate }" class="required date" style="width: 98%" /></td>
				</tr>
				<tr>
					<td class="title">日程类型</td>
					<td><input type="text" name="t_Note.type" value="${note.type }" style="width: 98%" /></td>
				</tr>
				<tr>
					<td class="title">日程标题</td>
					<td><input type="text" name="t_Note.title" value="${note.title }" class="required" style="width: 98%" /></td>
				</tr>
				<tr>
					<td class="title">日程内容</td>
					<td><textarea name="t_Note.content" rows="6" style="width: 99%; overflow:auto">${note.content }</textarea></td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
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
