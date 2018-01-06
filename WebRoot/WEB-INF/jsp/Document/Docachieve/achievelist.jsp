<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>

<div class="bjui-pageHeader">
	<form method="post" id="pagerForm" action="Main/Docachieve/achievelist" data-toggle="ajaxsearch">
		<input type="hidden" name="pageSize" value="${page.pageSize}"> <input type="hidden" name="pageCurrent" value="${page.pageNumber}"> <input type="hidden"
			name="orderField" value="${orderField}"> <input type="hidden" name="orderDirection" value="${orderDirection}"> 
		<div class="bjui-searchBar">
			<label>来文单位：</label><input type="text" placeholder="请输入关键字" name="sunit" value="${sunit}" size="12"> 
			<label>文号：</label><input type="text" placeholder="请输入关键字" name="sdocno" value="${sdocno}" size="12"> 
			<label>文件标题：</label><input type="text" placeholder="请输入关键字" name="stitle" style="width: 120px;" value="${stitle}">
			<label>来文分类：</label><select name="type" data-toggle="selectpicker">
				<option value="">---全部---</option>
				<c:forEach items="${details}" var="temp">
					<option value="${temp.name}" <c:if test="${type==temp.name}">selected</c:if>>${temp.name}</option>
				</c:forEach>
		</select> 
			<label>状态：</label><select name="status" data-toggle="selectpicker">
			<option value="9">---全部---</option>
			<option value="0" <c:if test="${status=='0'}">selected</c:if>>未分发</option>
			<option value="1" <c:if test="${status=='1'}">selected</c:if>>办理中</option>
			<option value="2" <c:if test="${status=='2'}">selected</c:if>>已办结</option>
			<option value="3" <c:if test="${status=='3'}">selected</c:if>>撤销</option>
		</select>
			
		<div class="pull-right">
		<c:if test="${pert:hasperti(applicationScope.achieve, loginModel.limit)}">
			<button type="button" class="btn-blue" data-url="Main/Docreceive/apply/{#bjui-selected}" data-toggle="dialog" data-icon="fa-edit" data-mask="true" data-id="approve_dialog"
						data-title="初审分办" data-width="850" title="初审分办" data-height="650">初审分办</button>
		</c:if>
		</div>
			 <br>
			<label>收文日期：</label><input type="text" name="sdate" style="width: 120px;" data-toggle="datepicker" readonly value="${sdate}"> &nbsp; -- &nbsp;&nbsp;
				<input type="text" name="edate" style="width: 120px;" data-toggle="datepicker" readonly value="${edate}"> 
			
		<button type="submit" class="btn-default" data-icon="search">查询</button>
		<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>

<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="10%">收文日期</th>
				<th width="10%">来文单位</th>
				<th width="15%">文号</th>
				<th width="29%">文件标题</th>
				<th width="7%">秘密等级</th>
				<th width="7%">紧急程度</th>
				<th width="7%">分办科室</th>
				<th width="5%">备注</th>
				<th width="5%">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="dc" varStatus="vs">
					<tr style="cursor:pointer" title="双击打开" align="center" data-id="${dc.id}" dbDialog="true" dbTitle="初审分办"  dbDialogId="approve_dialog" data-maxable="true" data-mask="true" dbWidth="850" dbHeight="600"
							href="Main/Docreceive/apply/${dc.id}" >
							<td>${vs.index+1}</td>
							<td>${dc.receivedate}</td>
							<td align="left">${dc.unit}</td>
							<td align="left">${dc.docno}</td>
							<td class="custom">${dc.title}</td>
							<td>${dc.security}</td>
							<td><c:choose>
									<c:when test="${dc.degree=='0'}">平件</c:when>
									<c:when test="${dc.degree=='1'}">平急</c:when>
                                    <c:when test="${dc.degree=='2'}">特急</c:when>
                                    <c:otherwise></c:otherwise>
								</c:choose></td>
							<td>${dc.dpname}</td>
							<td>${dc.memo}</td>
							<td><c:choose>
									<c:when test="${dc.status=='0'}">未分发</c:when>
									<c:when test="${dc.status=='1'}">办理中</c:when>
									<c:when test="${dc.status=='2'}">已办结</c:when>
									<c:when test="${dc.status=='3'}">撤销</c:when>
									<c:otherwise></c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="10" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
<%@ include file="/include/pagination.jsp" %>

