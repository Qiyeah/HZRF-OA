<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Meeting/main" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageNum" value="${page.pageNumber}" /> 
		<input type="hidden" name="numPerPage" value="${page.pageSize}" />
		<div class="bjui-searchBar">
			<label>标题：</label> <input type="text" placeholder="请输入关键字" name="stitle" value="${stitle}" size="12"/>
			<label>类型：</label> <input type="text" placeholder="请输入关键字" name="stype" value="${stype}" size="12"/>
			<label>会议日期：</label> <input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}"> - 
			<input type="text" name="edate" style="width: 120px" data-toggle="datepicker" readonly value="${edate}">
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>  
       		<div class="pull-right">	
       			<a class="btn btn-blue" data-icon="print" href="Main/Meeting/exportPdf/{#bjui-selected }" data-toggle="dialog" data-max="true" data-id="approveDialog" data-mask="true" data-width="850" data-height="600"
                  data-title="打印">打印</a>
       		</div>		
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="15%">申请时间</th>
				<th width="10%">申请人</th>
				<th width="12%">申请科室</th>
                <th width="20%">会议标题</th>
                <th width="8%">会议类型</th>
				<th width="20%">当前环节</th>
				<th width="10%">当前处理人</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="wf" varStatus="vs">
						<tr style="cursor:pointer" title="双击打开" align="center" target="id" data-id="${wf.pid}" dbDialog="true" mask="true" dbTitle="查看 " dbDialogId="viewMeeting"  dbWidth="850" dbHeight="600" href="Main/Meeting/print/${wf.pid}">
							<td>${vs.index+1}</td>
							<td>${fn:substring(wf.approvedate, 0, 16)}</td>
							<td>${wf.uname}</td>
							<td>${wf.dpname}</td>
							<td class="custom" title="${wf.title}">${wf.title}</td>
							<td>${wf.type}</td>
							<td>会议纪要</td>
							<td></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="8" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>

		</tbody>
	</table>
</div>
<%@ include file="/include/pagination.jsp" %>