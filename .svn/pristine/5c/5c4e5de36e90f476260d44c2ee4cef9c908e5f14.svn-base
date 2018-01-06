<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
 <form id="pagerForm" action="Main/Leave/endlist" method="post" data-toggle="ajaxsearch">
    <div class="bjui-searchBar">
        <input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
        <input type="hidden" name="orderField" value="${orderField}" />  
   	    <input type="hidden" name="orderDirection" value="${orderDirection}" /> 
           <label>申请人：</label><input type="text" name="sname" value="${sname}" size="12" placeholder="请输入关键字"/></td>
           <label>申请日期：</label><input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}"> - <input type="text" name="edate" style="width: 120px" data-toggle="datepicker" readonly value="${edate}"></td>
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
            <div class="pull-right">
            <a href="Main/Leave/print/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-icon="eye" data-mask="ture" data-id="approve_print" data-title="查看"
					data-width="850" data-height="600">查看</a>
			<a href="Main/Leave/exportPdf/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-id="viewpdf" data-title="打印" data-icon="fa-print" data-max="true" data-mask='true' data-width="1000" data-height="600">打印</a>		
            </div>    
    
        </form>
    </div>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed" data-selected="true">
        <thead>
            <tr align="center">
                <th width="5%">序号</th>
                <th width="15%">申请时间</th>
                <th width="10%">申请人</th>
                <th width="12%">申请科室</th>
                <th width="10%">请假类型</th>
                <th width="8%">请假天数</th>
                <th width="20%">当前环节</th>
                <th width="10%">当前处理人</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${! empty page.list}">
                    <c:forEach items="${page.list}" var="wf" varStatus="vs">
                        <tr style="cursor:hand" align="center" target="id" data-id="${wf.id}" dbDialog="true" dbTitle="查看 " rel="approve_print" max="false" mask="true" maxable="false" minable="false" resizable="false" drawable="false" dbWidth="850" dbHeight="600" href="Main/Leave/print/${wf.id}">
                            <td>${vs.index+1}</td>
                            <td>${fn:substring(wf.starttime, 0, 16)}</td>
                            <td>${wf.uname}</td>
                            <td>${wf.dpname}</td>
                            <td>${wf.leavetype}</td>
                            <td>${wf.leavedays}</td>
                            <td>${wf.active_name}</td>
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