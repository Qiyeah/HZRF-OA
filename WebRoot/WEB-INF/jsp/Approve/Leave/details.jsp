<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" action="Main/Leave/details" method="post" data-toggle="ajaxsearch">
    <div class="bjui-searchBar">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
        <input type="hidden" name="orderField" value="${orderField}" />  
   	    <input type="hidden" name="orderDirection" value="${orderDirection}" />
                    <label>申请人：</label><input type="text" name="sname" value="${sname}"size="12" placeholder="请输入关键字"/>
                    <label>科室：</label><select name="sdept" data-toggle="selectpicker">
                        <option value="">全部科室</option>
                        <c:forEach items="${deparments}" var="dept">
                            <option value="${dept.id}" <c:if test="${!empty sdept && dept.id==sdept}">selected="selected"</c:if>>${dept.fname}</option>
                        </c:forEach>
                    </select>
                   <label>请假日期：</label><input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}"> - <input type="text" name="edate" style="width: 120px" data-toggle="datepicker" readonly value="${edate}">
               <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			    <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
               <div class="pull-right">
               <c:if test="${pert:hasperti(applicationScope.exportLeaveDetails, loginModel.limit)}">
                <a class="btn btn-green" data-icon="sign-out" href="javascript:exportSubmit('exportDetails')" title="导出Excel">导出Excel</a>
               </c:if>
               </div>     
    </div>
     </form>
</div>
<div class="bjui-pageContent tableContent">
    <form method="post" action="Main/Leave/exportDetails" >
        <input type="hidden" name="sname" value="${sname}"/>
        <input type="hidden" name="sdept" value="${sdept}"/>
        <input type="hidden" name="sdate" value="${sdate}"/>
        <input type="hidden" name="edate" value="${edate}"/>
        <button id="exportDetails" style="display: none" hidden="hidden" type="submit">导出excel</button>
    </form>
  <table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed" data-selected="true">
        <thead>
            <tr align="center">
                <th width="5%">序号</th>
                <th width="15%">姓名</th>
                <th width="20%">科室</th>
                <th width="20%">类型</th>
                <th width="20%">开始日期</th>
                <th width="20%">结束日期</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${! empty page.list}">
                    <c:forEach items="${page.list}" var="list" varStatus="vs">
                        <tr style="cursor:hand" align="center" target="id" data-id="${list.pid}" dbDialog="true" dbTitle="查看  " rel="approve_print" max="false" mask="true" maxable="true" minable="false" resizable="false" drawable="false" dbWidth="850" dbHeight="600" href="Main/Leave/print/${list.pid}">
                            <td>${vs.index+1}</td>
                            <td>${list.uname}</td>
                            <td>${list.dpname}</td>
                            <td>${list.type}</td>
                            <td>${list.begindate}</td>
                            <td>${list.enddate}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr align="center">
                        <td colspan="13" style="color: red;">无任何记录！</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>
	<%@ include file="/include/pagination.jsp" %>