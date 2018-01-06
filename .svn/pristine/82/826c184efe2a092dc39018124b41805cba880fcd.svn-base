<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Myschedule/statistics" method="post" data-toggle="ajaxsearch">
		<div class="bjui-searchBar">
			<label>姓名：</label><select name="u_id" data-width="80" data-toggle="selectpicker">
				<option value="">全部</option>
				<c:forEach items="${list}" var="list">
					<option value="${list.id}" <c:if test="${list.id == u_id}">selected="selected"</c:if> >${list.name}</option>
				</c:forEach>
			</select> <label>日期：</label><input type="text" name="sdate" data-toggle="datepicker" readonly value="${sdate}" size="15" /> - <input type="text" name="edate" data-toggle="datepicker"
				readonly value="${edate}" size="15" /> 
			<label>日程类型：</label><input name="chairman.type" type="text" suggestFields="type" suggestUrl="Main/Myschedule/type" lookupGroup="chairman" value="${type}"
				size="12" maxlength="200" />
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			<div class="pull-right"></div>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" data-selected="true">
		<thead>
			<tr align="center">
				<th width="6%">性名</th>
				<th width="16%">开始日期</th>
				<th width="16%">结束日期</th>
				<th width="20%">日程标题</th>
				<th width="42%">日程内容</th>
				<th width="10%">日程备注</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="temp">
						<tr>
							<td>${HMuser.get(temp.u_id)}</td>
							<td align="left"><fmt:formatDate value="${temp.wdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td align="left"><fmt:formatDate value="${temp.edate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td class="custom">${temp.title}</td>
							<td class="custom">${temp.content}</td>
							<td class="custom">${temp.reason}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="6" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>

		</tbody>
	</table>
</div>