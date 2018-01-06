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
    
   <form method="post" id="pagerForm" action="Main/Docreceive/uncirculationlist" data-toggle="ajaxsearch">
       <div class="bjui-searchBar">
       <input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
       <input type="hidden" name="pageSize" value="${page.pageSize}" />
       <label>来文单位：</label><input type="text" placeholder="请输入关键字" name="sunit" value="${sunit}" size="12">
       <label>文号：</label><input type="text" placeholder="请输入关键字" name="sdocno" value="${sdocno}" size="12">
       <label>来文标题：</label><input type="text" placeholder="请输入关键字" name="stitle" value="${stitle}" size="12">
       
		<div class="pull-right">
	 		<a class="btn btn-blue" href="Main/Docreceive/tocirculation/{#bjui-selected}" data-toggle="dialog" id="approve_print" mask="true" title="查看 传阅情况" data-width="850" data-height="600">传阅情况</a>
	   </div><br>
       <label>收文日期：</label><input type="text" name="sdate" style = "width:120px" data-toggle="datepicker" readonly value="${sdate}"> &nbsp; -- &nbsp;&nbsp; 
       <input type="text" name="edate" style = "width:120px" data-toggle="datepicker" readonly value="${edate}">
       <button type="submit" class="btn-default" data-icon="search" >查询</button>
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
                <th width="10%">办理时限</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${! empty page.list}">
                    <c:forEach items="${page.list}" var="wf" varStatus="vs">
                        <c:set value="${fn:split(wf.todoman,';')}" var="uids"/>
                         <tr  style="cursor:pointer" title="双击打开" align="center" data-id="${wf.id}" dbDialog="true" dbTitle="查看 传阅情况" dbDialogId="doing_dialog" data-maxable="true" data-mask="true" dbWidth="850" dbHeight="600"
							 href="Main/Docreceive/tocirculation/${wf.id}">
                            <td>${vs.index+1}</td>
                            <td><fmt:formatDate value="${wf.starttime}" pattern="yyyy-MM-dd"/></td>
                            <td>${wf.dpname}</td>
                            <td title="${wf.docunit}">${wf.unit}</td>
                            <td>${wf.docno}</td>
                            <td class="custom" title="${wf.doctitle}">${wf.doctitle}</td>
                            <td>${wf.termdate} </td>
                           </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr align="center">
                        <td colspan="7" style="color: red;">无任何记录！</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>
<%@ include file="/include/pagination.jsp" %>
