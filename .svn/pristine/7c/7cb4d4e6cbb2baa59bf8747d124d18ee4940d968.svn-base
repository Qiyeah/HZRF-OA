<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>

<div class="bjui-pageHeader">
	
	<form method="post" id="pagerForm" action="Main/Docreceive/superlist" data-toggle="ajaxsearch">
		<div class="bjui-searchBar">
			<input type="hidden" name="pageCurrent" value="${page.pageNumber}" /> <input type="hidden" name="pageSize" value="${page.pageSize}" /> 
			<label>来文单位：</label> <input type="text" placeholder="请输入关键字" name="sunit" value="${sunit}" size="12"> 
			<label>文号：</label> <input type="text" placeholder="请输入关键字" name="sdocno" value="${sdocno}" size="12"> 
			<label>来文标题：</label> <input type="text" placeholder="请输入关键字" name="stitle" value="${stitle}" size="12"> 
			<label>督办员：</label> <select name="ssuperman"  data-toggle="selectpicker" data-width="120px">
				<option value="">---全部---</option>
				<c:forEach items="${offmebers}" var="off">
					<option value="${off.id}" <c:if test="${ssuperman==off.id}">selected</c:if>>${off.name}</option>
				</c:forEach>
				<option value="47" <c:if test="${ssuperman=='47'}">selected</c:if>>谢理涛</option>
			</select>
			
			
			<div class="pull-right" >
				<a class="btn btn-blue" data-icon="eye" href="Main/Docreceive/toapprove/{#bjui-selected}-2" data-toggle="dialog" data-mask="true"
				 data-title="督办" data-width="850" data-height="600">督办</a>
			</div><br>
			<label>分办日期：</label> <input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}"> &nbsp;&nbsp; -- &nbsp;&nbsp; <input type="text"
				name="edate" style="width: 120px" data-toggle="datepicker" readonly value="${edate}">
			<label>办理时限：</label>
			<input type="text" name="stdate" style="width: 120px" data-toggle="datepicker" readonly value="${stdate}" > &nbsp;&nbsp;&nbsp; -- &nbsp;&nbsp;　<input type="text" name="etdate" style="width: 120px"
			 data-toggle="datepicker" readonly value="${etdate}" >
			
			<button type="submit" class="btn-default" data-icon="search" >查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
	
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="10%">收文时间</th>
				<th width="10%">承办科室</th>
				<th width="10%">办理时限</th>
				<th width="15%">文号</th>
				<th width="30%">来文标题</th>
				<th width="10%">当前处理人</th>
				<th width="10%">督办员</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="wf" varStatus="vs">
						<c:set value="${fn:split(wf.todoman,';')}" var="uids" />
						<tr  style="cursor:pointer" title="双击打开" align="center" data-id="${wf.id}" dbDialog="true" dbTitle="督办" dbDialogId="super_dialog" data-maxable="true" data-mask="true" dbWidth="850" dbHeight="600"
							 href="Main/Docreceive/toapprove/${wf.id}-2">
							<td>${vs.index+1}</td>
							<td><fmt:formatDate value="${wf.starttime}" pattern="yyyy-MM-dd"/></td>
							<td>${wf.receive1 }</td>
							<td>${wf.termdate1}</td>
							<td>${wf.docno}</td>
							<td class="custom" title="${wf.doctitle}">${wf.doctitle}</td>
							<td><c:forEach items="${uids}" var="uid" varStatus="vs">
                                    ${userHM.get(uid) }
                                </c:forEach></td>
							<td>${wf.uname}</td>
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
