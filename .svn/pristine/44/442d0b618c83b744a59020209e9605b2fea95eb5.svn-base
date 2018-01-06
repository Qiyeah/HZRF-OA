<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>

<div class="bjui-pageHeader">
	<form method="post" id="pagerForm" action="Main/Docachieve/readabilityList" data-toggle="ajaxsearch">
		<input type="hidden" name="pageSize" value="${page.pageSize}"> <input type="hidden" name="pageCurrent" value="${page.pageNumber}"> <input type="hidden"
			name="orderField" value="${orderField}"> <input type="hidden" name="orderDirection" value="${orderDirection}"> <input type="hidden" name="doflag" value="${doflag}" /> <input type="hidden"
			name="sort" value="${sort}" />
		<div class="bjui-searchBar">
			<label>来文单位：</label><input type="text" placeholder="请输入关键字" name="sunit" value="${sunit}" size="12"> 
			<label>文号：</label><input type="text" placeholder="请输入关键字" name="sdocno" value="${sdocno}" size="12"> 
			<label>来文标题：</label><input type="text"placeholder="请输入关键字" name="stitle" value="${stitle}" size="12">
			<label>收文日期：</label><input  type="text" name="sdate" size="12" data-toggle="datepicker" readonly value="${sdate}">
			 &nbsp; -- &nbsp;&nbsp; <input  type="text" name="edate" size="12" data-toggle="datepicker" readonly value="${edate}">
			<button  type="submit" class="btn-default" data-icon="search">查询</button>
			<a   class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		
			<div class="pull-right" >
				<a class="btn btn-blue" href="Main/Docreceive/toprint/{#bjui-selected}" data-toggle="dialog" data-icon="fa-edit" data-mask="true" data-id="achievetodo_dialog"
					data-drawable="false" title="编辑 /审批" data-width="850" data-height="600">查看 </a> 
			</div>
			</div>
			
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="10%" data-order-field="starttime">收文时间</th>
		
				<th width="30%" data-order-field="unit">来文单位</th>
				<th width="20%" data-order-field="docno">文号</th>
				<th width="35%" data-order-field="doctitle">来文标题</th>
				
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="wf" varStatus="vs">
						<c:set value="${fn:split(wf.todoman,';')}" var="uids" />
							<tr  style="cursor:pointer" title="双击打开" align="center" data-id="${wf.id}" dbDialog="true" dbTitle="编辑 /审批" dbDialogId="achievetodo_dialog" maxable="true" mask="true" dbWidth="850" dbHeight="600"
							href="Main/Docreceive/toprint/${wf.id}">
							<td>${vs.index+1}</td>
							<td><fmt:formatDate value="${wf.starttime}" pattern="yyyy-MM-dd"/></td>
							
							<td title="${wf.docunit}">${wf.unit}</td>
							<td>${wf.docno}</td>
							<td class="custom" title="${wf.doctitle}">${wf.doctitle}</td>
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
	<%@ include file="/include/pagination.jsp" %>