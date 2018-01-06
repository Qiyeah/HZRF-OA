<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Docreceive/filecab" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}" /> 
		<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
		<input type="hidden" name="orderField" value="${orderField}" /> 
		<input type="hidden" name="orderDirection" value="${orderDirection}" />
		<div class="bjui-searchBar">
			<label>来文单位：</label><input type="text" name="sunit" style="width: 120px;" value="${sunit}" placeholder="请输入关键字" /> 
			<label>来文文号：</label><input type="text" name="sdocno" style="width: 120px;" value="${sdocno}" placeholder="请输入关键字" /> 
			<label>来文标题：</label><input type="text" name="stitle" style="width: 120px;" value="${stitle}" placeholder="请输入关键字" />
			<div class="pull-right">
				<c:if test="${pert:hasperti(applicationScope.docreceive_newcab, loginModel.limit)}">
					<button type="button" class="btn btn-green" data-url="Main/Docreceive/newcab" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleid" data-title="添加收文归档"
						data-width="850" data-height="400">添加</button>
				</c:if>
				<a class="btn btn-blue" href="Main/Docreceive/toprint/{#bjui-selected}" data-toggle="dialog" rel="approve_print" data-mask="true" title="查看 内容" data-width="850"
					data-height="600" data-icon="eye">查看内容</a> 
				<a class="btn btn-blue" href="Main/Docreceive/viewReceiver/{#bjui-selected}" data-toggle="dialog" rel="approve_print"
					data-mask="true" title="查看签收者 " data-width="600" data-height="300" data-icon="eye">查看签收者 </a> 
				<a href="Main/Docreceive/exportPdf/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-id="viewpdf" data-title="打印" 
					data-icon="fa-print" data-max="true" data-mask='true' data-width="1000" data-height="600">打印</a>
				<c:if test="${pert:hasperti(applicationScope.deleteReceiveCab, loginModel.limit)}">
					<a href="Main/Docreceive/deleteCab/{#bjui-selected}" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o" data-confirm-msg="您确定要删除该记录吗?">删除</a>
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.exportReceiveCab, loginModel.limit)}">
					<a class="btn btn-green" data-icon="sign-out" href="javascript:exportSubmit('exportReceiveCabList')" title="导出Excel">导出Excel</a>
				</c:if>
			</div>
			<br> 
			<label>承办科室：</label><select name="sdepart" data-toggle="selectpicker" data-width="120">
				<option value="">全部</option>
				<c:forEach items="${departs}" var="temp">
					<option value="${temp.sname}" <c:if test="${sdepart==temp.sname}">selected</c:if>>${temp.sname}</option>
				</c:forEach>
			</select> 
			<label>收文日期：</label><input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}"> —— &nbsp; &nbsp;<input type="text"
				name="edate" style="width: 120px" data-toggle="datepicker" readonly value="${edate}">&nbsp;
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<form method="post" action="Main/Docreceive/exportCab">
		<input type="hidden" name="sdocno" value="${sdocno}" /> <input type="hidden" name="stitle" value="${stitle}" /> <input type="hidden" name="sdate" value="${sdate}" /> <input
			type="hidden" name="edate" value="${edate}" /> <input type="hidden" name="sdepart" value="${sdepart}" /> <input type="hidden" name="stype" value="${stype}" />
		<button id="exportReceiveCabList" style="display: none" hidden="hidden" type="submit">导出excel</button>
	</form>
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" data-selected="true">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="10%" data-order-field="starttime">收文时间</th>
				<th width="10%" data-order-field="dpname">承办科室</th>
				<th width="15%" data-order-field="unit">来文单位</th>
				<th width="15%" data-order-field="docno">来文文号</th>
				<th width="45%" data-order-field="doctitle">来文标题</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="wf" varStatus="vs">
						<tr style="cursor: pointer" title="双击打开" align="center" target="id" data-id="${wf.id}" dbDialog="true" dbDialogId="approve_print" max="false" mask="true" maxable="false"
							minable="false" resizable="false" drawable="true" dbTitle="查看  / 打印" dbWidth="850" dbHeight="600" href="Main/Docreceive/toprint/${wf.id}">
							<td>${vs.index+1}</td>
							<td>${fn:substring(wf.receivedate, 0, 10)}</td>
							<td align="left" title="${wf.receive1}">${wf.receive1}</td>
							<td align="left">${wf.unit}</td>
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
<div class="bjui-pageFooter">
	<div class="pages">
		<span>每页&nbsp;</span>
		<div class="selectPagesize">
			<select data-toggle="selectpicker" data-toggle-change="changepagesize">
				<option <c:if test="${page.pageSize==20}">selected="selected"</c:if> value="20">20</option>
				<option <c:if test="${page.pageSize==50}">selected="selected"</c:if> value="50">50</option>
				<option <c:if test="${page.pageSize==100}">selected="selected"</c:if> value="100">100</option>
				<option value="500" <c:if test="${page.pageSize==500}">selected="selected"</c:if>>200</option>
			</select>
		</div>
		<span>条，共${page.totalRow}条</span>
	</div>
	<div class="pagination-box" data-toggle="pagination" data-total="${page.totalRow}" data-page-size="${page.pageSize}" data-page-current="${page.pageNumber}"></div>
</div>
