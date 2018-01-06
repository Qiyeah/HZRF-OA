<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Attence/attenceStatistics" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageNum" value="${page.pageNumber}" /> <input type="hidden" name="numPerPage" value="${page.pageSize}" />
		<div class="bjui-searchBar">
            <label>姓名：</label><input type="text" placeholder="请输入关键字" name="sname" value="${sname}" size="12">
			<label>查询日期：</label>
			<input type="text" name="sdate" data-toggle="datepicker" readonly  style="width:120px" value="${sdate}"> - 
			<input type="text" name="edate" data-toggle="datepicker" readonly style="width:120px" value="${edate}">
			<button type="submit" class="btn-default" data-icon="search" >查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch"  data-clear-query="true" data-icon="undo">清空查询</a>
			<div class="pull-right">
				 <c:if test="${pert:hasperti(applicationScope.exportAttenceStatistics, loginModel.limit)}">
                	<a class="btn btn-green" data-icon="sign-out" href="javascript:exportSubmit('exportAttenceStatistics')" data-title="导出Excel"><span>导出Excel</span></a>
            	</c:if>
            </div>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
    <form method="post" action="Main/Attence/exportAttenceStatistics" onsubmit="return validateCallback(this, xxxAjaxDone)">
        <input type="hidden" name="sname" value="${sname}"/>
        <input type="hidden" name="sunit" value="${sunit}"/>
        <input type="hidden" name="sdate" value="${sdate}"/>
        <input type="hidden" name="edate" value="${edate}"/>
        <button id="exportAttenceStatistics" style="display: none" hidden="hidden" type="submit">导出Excel</button>
    </form>
	
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
	<thead>
		<tr align="center">
                <th width="5%" >序号</th>
			    <th width="20%">姓名</th>
			    <th width="30%">科室</th>
                <th width="15%">迟到(次)</th>
                <th width="15%">早退(次)</th>
                <th width="15%">缺勤(次)</th>
			</tr>
		</thead>
		<tbody>
        <c:choose>
            <c:when test="${! empty attenceStatisticsList}">
                <c:forEach items="${attenceStatisticsList}" var="temp" varStatus="vs">
                    <tr align="center">
                        <td>${vs.index+1}</td>
                        <td>${temp.name}</td>
                        <td>${temp.deptName}</td>
                        <td>${temp.late}</td>
                        <td>${temp.leave_early}</td>
                        <td>${temp.absence}</td>
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
<%@ include file="/include/pagination.jsp" %>
