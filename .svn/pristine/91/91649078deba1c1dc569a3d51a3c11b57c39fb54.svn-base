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
<div class="bjui-pageHeader">
	<form method="post" id="pagerForm" action="Main/Docreceive/endlist" data-toggle="ajaxsearch">
		<input type="hidden" name="pageNum" value="${page.pageNumber}" /> <input type="hidden" name="numPerPage" value="${page.pageSize}" />
		<div class="bjui-searchBar">
			<label>来文单位：</label><input type="text" placeholder="请输入关键字" name="sunit" value="${sunit}" size="12"> 
			<label>文号：</label><input type="text" placeholder="请输入关键字" name="sdocno" value="${sdocno}" size="12"> 
			<label>来文标题：</label><input type="text" placeholder="请输入关键字" name="stitle" value="${stitle}" size="12"> 
			<div class="pull-right">    
	        	<a class="btn btn-blue" data-icon="eye" href="Main/Docreceive/toprint/{#bjui-selected}" data-toggle="dialog" data-id="approve_print" data-mask="true"  data-title="查看 " data-width="850" data-height="600">查看 </a>
	      		<a href="Main/Docreceive/exportPdf/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-id="receiveviewpdf" data-title="打印" data-icon="fa-print" data-max="true" data-mask="true" data-width="1000" data-height="600">打印</a>
	      </div><br>
			<label>收文日期：</label><input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}"> &nbsp; -- &nbsp;&nbsp; 
			<input type="text" name="edate" style="width: 120px" data-toggle="datepicker" readonly value="${edate}">
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" >
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="10%">收文时间</th>
				<th width="10%">承办科室</th>
				<th width="10%">来文单位</th>
				<th width="15%">文号</th>
				<th width="30%">来文标题</th>
				<th width="10%">当前环节</th>
				<th width="10%">当前处理人</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="wf" varStatus="vs">
						<tr  style="cursor:pointer" title="双击打开" align="center" target="id" data-id="${wf.id}" dbDialog="true" dbDialogId="approve_print"  data-mask="true" 
							 drawable="true" dbTitle="查看" dbWidth="850" dbHeight="600" href="Main/Docreceive/toprint/${wf.id}">
							<td>${vs.index+1}</td>
							<td><fmt:formatDate value="${wf.starttime}" pattern="yyyy-MM-dd"/></td>
							<td align="left" title="${wf.receive1}">${wf.receive1 }</td>
							<td  align="left" title="${wf.docunit}">${wf.unit}</td>
							<td>${wf.docno}</td>
							<td class="custom" title="${wf.doctitle}">${wf.doctitle}</td>
							<td>${wf.active_name}</td>
							<td  align="left" title="<c:forEach items="${uids}" var="uid" varStatus="vs">${userHM.get(uid)} </c:forEach>">
                                <c:forEach items="${uids}" var="uid" varStatus="vs">
                                    ${userHM.get(uid) }
                                </c:forEach>
                            </td>
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
