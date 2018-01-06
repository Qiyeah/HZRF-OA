<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<table class="wordInfo" align="center" style="width: 98%;">
		<tr>
			<td width="20%" class="title">日期</td>
			<td width="80%">${fn:substring(model.date, 0, 16)}</td>
		<tr>
			<td class="title">地点</td>
			<td>${model.place}</td>
		</tr>
		<tr>
			<td class="title">议题</td>
			<td>${model.lssue}</td>
		</tr>
		<tr>
			<td class="title">分类</td>
			<td><c:forEach items="${list}" var="list">
					 <c:if test="${model.classification == list.id}">${list.name}</c:if>
					</c:forEach>
			</td>
		</tr>
		<tr>
			<td class="title">主持人</td>
			<td>${model.host}</td>
		</tr>
		<tr>
			<td class="title">参加人员</td>
			<td>${model.attendee }</td>
		</tr>
		<tr>
			<td class="title">列席人员</td>
			<td>${model.attend }</td>
		</tr>		
		<tr>
			<td class="title">附件列表<br>(议程安排<br>呈批笺<br>会议材料<br>会议记录)</td>
			<td><div id="uploadFile" class="unitBox" style="overflow-x: hidden;">
					<c:import url="/WEB-INF/jsp/Common/Attachment/view.jsp" />
				</div></td>
		</tr>
	</table>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
	</ul>
</div>