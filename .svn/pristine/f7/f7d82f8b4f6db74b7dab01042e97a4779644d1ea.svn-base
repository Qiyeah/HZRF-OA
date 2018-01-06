<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<c:if test="${empty list}"><div style="color: red;text-align: center">无任何记录！</div></c:if>
	<c:forEach items="${list}" var="temp" varStatus="vs">
		<fieldset>
		    <legend style="background-color: #ccc;color:white;padding: 3px;">${vs.count }.&nbsp;${activeHM.get(temp.itemid1)}</legend>
		    <c:set value="${fn:split(temp.user2,';')}" var="uids"/>	
			<table class="wordInfo" align="center" style="width: 96%">
				<tr>
					<td class="title">环节名称</td>
					<td colspan="3">&nbsp;${activeHM.get(temp.itemid1)}</td>
					<td class="title">开始时间</td>
					<td>&nbsp;${temp.begintime}</td>
				</tr>
				<tr>
					<td width="12%" class="title">处 理 人</td>
					<td width="21%">&nbsp;${userHM.get(temp.user1)}</td>
					<td width="12%" class="title">处理操作</td>
					<td width="18%">&nbsp;${operationHM.get(temp.operation)}</td>
					<td width="12%" class="title">结束时间</td>
					<td width="25%">&nbsp;${temp.endtime}</td>
				</tr>
				<tr>
					<td class="title">处理意见</td>
					<td colspan="5">&nbsp;${temp.opinion}</td>
				</tr>
				<tr>
					<td class="title">下一步<br/>处理人</td>
					<td colspan="5" title="<c:forEach items="${uids}" var="uid" varStatus="vs">${userHM.get(uid)} </c:forEach>">
									<c:forEach items="${uids}" var="uid" varStatus="vs">
										${userHM.get(uid) }
									</c:forEach>
					</td>
				</tr>
			</table>
		</fieldset>
	</c:forEach>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    </ul>
</div>