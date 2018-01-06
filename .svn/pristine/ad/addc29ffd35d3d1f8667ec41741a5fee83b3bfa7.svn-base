<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-selected="true">
		<thead>
			<tr align="center">
				<th width="5%" align="center">序号</th>
				<th width="12%" align="center">申请日期</th>
				<th width="10%" align="center">请假类型</th>
				<th width="8%" align="center">请假天数</th>
				<th width="21%" align="center">请假事由</th>
				<th width="12%" align="center">开始日期</th>
				<th width="12%" align="center">结束日期</th>
				<th width="12%" align="center">销假日期</th>
				<th width="8%" align="center">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty list}">
					<c:forEach items="${list}" var="lv" varStatus="vs">
						<tr align="center">
							<td>${vs.index+1}</td>
							<td>${lv.approvedate}</td>
							<td>${lv.type}</td>
							<td><c:choose>
							    <c:when test="${lv.days==0}">${lv.dayt}</c:when>
							    <c:otherwise>${lv.days}</c:otherwise>
							</c:choose></td>
							<td>${lv.reason}</td>
							<td>${lv.begindate}</td>
							<td>${lv.enddate}</td>
							<td>${lv.backdate}</td>
							<td><c:choose>
									<c:when test="${lv.pstatus=='0'}">起草中</c:when>
									<c:when test="${lv.pstatus=='1'}">审批中</c:when>
									<c:when test="${lv.pstatus=='2'}">已办结</c:when>
									<c:when test="${lv.pstatus=='3'}">已终止</c:when>
									<c:otherwise></c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="9" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
<div class="bjui-pageFooter">
			<ul>
				<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
			</ul>
        </div>