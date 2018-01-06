<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
	<div class="bjui-pageHeader">
       <form id="pagerForm" action="Main/Leave/stat" method="post" data-toggle="ajaxsearch">
       <div class="bjui-searchBar">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
        <input type="hidden" name="orderField" value="${orderField}" />  
   	    <input type="hidden" name="orderDirection" value="${orderDirection}" />
				<label>申请人：</label><input type="text" name="sname" value="${sname}" size="12" placeholder="请输入关键字"/></td>
				<label>科室：</label><select name="sdept" data-toggle="selectpicker" >
							<option value="">全部科室</option>
							<c:forEach items="${deparments}" var="dept">
                            <option value="${dept.id}" <c:if test="${!empty sdept && dept.id==sdept}">selected="selected"</c:if>>${dept.fname}</option>
                        </c:forEach>
						</select>
					<label>年度：</label><select name="syear" data-toggle="selectpicker">
						<option value="">--请选择--</option>
						<c:forEach begin="2014" end="2025" varStatus="vs">
							<option value="${vs.index}" <c:if test="${vs.index==syear}">selected</c:if>>${vs.index}</option>
						</c:forEach>
						</select>
				<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			    <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
            <div class="pull-right">
            <c:if test="${pert:hasperti(applicationScope.exportStat, loginModel.limit)}">
            	 <a class="btn btn-green" data-icon="sign-out" href="javascript:exportSubmit('exportStat')" title="导出Excel">导出Excel</a>
            	</c:if>
            </div>
       </form>
            
		</div>
	</div>
<div class="bjui-pageContent tableContent">
        <form method="post" action="Main/Leave/exportStat" >
            <input type="hidden" name="sname" value="${sname}"/>
            <input type="hidden" name="sdept" value="${sdept}"/>
            <input type="hidden" name="syear" value="${syear}"/>
            <button id="exportStat" style="display: none" hidden="hidden" type="submit">导出excel</button>
        </form>
		<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed" data-selected="true">
			<thead>
				<tr align="center">
					<th width="5%">序号</th>
					<th width="5%">年度</th>
					<th width="10%">姓名</th>
					<th width="17%">科室</th>
					<th width="7%">年休假</th>
                    <th width="7%">事假</th>  
                    <th width="7%">病假</th>              
					<th width="7%">婚丧假</th>
					<th width="7%">产假</th>
					<th width="7%">探亲假</th>
					<th width="7%">总计</th>	
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${! empty page.list}">
						<c:forEach items="${page.list}" var="list" varStatus="vs">
							<tr align="center">
								<td>${vs.index+1}</td>
								<td>${list.year}</td>
								<td>${list.uname}</td>
								<td>${list.dpname}</td>
								<td>${list.nxj}</td>
                                <td>${list.sij}</td>
                                <td>${list.bij}</td>
								<td>${list.jhj}</td>
								<td>${list.jsj}</td>
								<td>${list.tqj}</td>
								<td>${list.days}</td>
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