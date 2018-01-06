<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
     <form id="pagerForm" action="Main/Leave/todolist" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
        <input type="hidden" name="orderField" value="${orderField}" />  
   	    <input type="hidden" name="orderDirection" value="${orderDirection}" />
   	    <div class="bjui-searchBar">
         	<label>申请人：</label><input type="text" name="sname" value="${sname}" size="12" placeholder="请输入关键字"/></td>
            <label>申请日期：</label><input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}" /> - <input type="text" name="edate" style="width: 120px" data-toggle="datepicker" readonly value="${edate}">
             <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			 <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
              <div class="pull-right">
              
               <c:if test="${pert:hasperti(applicationScope.leave_apply, loginModel.limit)}">
              <button type="button" class="btn btn-green" data-url="Main/Leave/apply" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="approve_dialog" data-title="请休假申请"
					data-width="850" data-height="600">请休假申请</button>
					</c:if>
				<c:if test="${pert:hasperti(applicationScope.leave_approve, loginModel.limit)}">
              	 <a href="Main/Leave/approve/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="approve_dialog" data-icon="fa-edit" data-title="编辑 / 审批"
					data-width="850" data-height="600">编辑/审批</a>
					<a href="Main/Leave/exportPdf/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-id="viewpdf" data-title="打印" data-icon="fa-print" data-max="true" data-mask='true' data-width="1000" data-height="600">打印</a>		 
					</c:if>
              </div>
              </div>      
        </form>
    </div>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" data-selected="true">
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
                        <c:set value="${fn:split(wf.todoman,';')}" var="uids"/>
                        <tr  style="cursor:pointer" title="双击打开" align="center" target="id" data-id="${wf.id}" dbDialog="true" dbTitle="编辑 / 审批" dbDialogId="approve_dialog"
                             max="false" mask="true" maxable="false" minable="false" resizable="false" drawable="true"  dbWidth="850" dbHeight="600" href="Main/Leave/toapprove/${wf.id}">
                            <td>${vs.index+1}</td>
                            <td>${fn:substring(wf.starttime, 0, 16)}</td>
                            <td>${wf.uname}</td>
                            <td>${wf.dpname}</td>
                            <td>${wf.leavetype}</td>
                            <td>${wf.dayt}</td>
                            <td>${wf.active_name}</td>
                            <td>
                                <c:forEach items="${uids}" var="uid" varStatus="vs">
                                    ${userHM.get(uid) }
                                </c:forEach>
                            </td>
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