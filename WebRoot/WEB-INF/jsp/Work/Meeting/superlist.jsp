<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#todoReset").click(function() {
			var table = $("#todoSearch");
			table.find("input").val("");
			table.find("select").val("");
		});
	});
</script>
<form method="post" id="pagerForm" action="Main/Meeting/superlist" onsubmit="return navTabSearch(this);">
	<input type="hidden" name="pageNum" value="${page.pageNumber}" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<div class="pageHeader">
		<div class="bjui-searchBar">
			<table id="todoSearch" class="searchContent">
				<tr>
					<td><span>标题：</span></td><td><input type="text" name="stitle" value="${stitle}" /></td>
					<td><span>类型：</span></td><td><input type="text" name="stype" value="${stype}" /></td>
					<td><span>申请日期：</span></td><td><input type="text" name="sdate" class="date" readonly value="${sdate}"> - <input type="text" name="edate" class="date" readonly value="${edate}"></td>
					<td>
						<div class="subBar">
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">查询</button>
								</div>
							</div>
						</div>
					</td>
					<td>
						<div class="subBar">
							<div class="buttonActive">
								<div class="buttonContent">
									<button id="todoReset" type="button">重置</button>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<!-- <li><a class="add" href="Main/Meeting/apply" target="dialog" mask="true" drawable="true" resizable="true" title="填写申请" width="850" height="400"><span>填写申请</span></a></li> -->
			</ul>
		</div>
		<table class="table" width="100%" layoutH="112">
			<thead>
				<tr align="center">
					<th width="5%">序号</th>
					<th width="15%">申请时间</th>
					<th width="10%">申请人</th>
					<th width="12%">申请科室</th>
					<th width="10%">会议标题</th>
					<th width="8%">会议类型</th>
					<th width="20%">当前环节</th>
					<th width="10%">当前处理人</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${! empty page.list}">
						<c:forEach items="${page.list}" var="wf" varStatus="vs">
							<c:set value="${fn:split(wf.todoman,';')}" var="uids"/>
							<tr align="center">
								<td>${vs.index+1}</td>
								<td>${fn:substring(wf.starttime, 0, 16)}</td>
								<td>${wf.uname}</td>
								<td>${wf.dpname}</td>
								<td class="custom">${wf.meetingtitle}</td>
								<td>${wf.meetingtype}</td>
								<td>${wf.active_name}</td>
								<td>
									<c:forEach items="${uids}" var="uid" varStatus="vs">
										${userHM.get(uid) }
									</c:forEach>
								</td>
								<td><a class="btnView" href="Main/Meeting/detail/${wf.id}" target="dialog" mask="true" drawable="true" resizable="true" maxable="true" rel="approveDialog" width="850" height="600"
									title="查看"></a></td>
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
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<option <c:if test="${page.pageSize==20}">selected</c:if> value="20">20</option>
					<option <c:if test="${page.pageSize==50}">selected</c:if> value="50">50</option>
					<option <c:if test="${page.pageSize==100}">selected</c:if> value="100">100</option>
					<option <c:if test="${page.pageSize==200}">selected</c:if> value="200">200</option>
				</select> <span>条，共${page.totalRow}条</span>
			</div>
			<div class="pagination" targetType="navTab" totalCount="${page.totalRow}" numPerPage="${page.pageSize}" pageNumShown="5" currentPage="${page.pageNumber}"></div>
		</div>
	</div>
</form>
