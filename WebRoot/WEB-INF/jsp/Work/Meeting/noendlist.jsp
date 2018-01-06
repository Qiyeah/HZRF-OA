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
    
   <form method="post" id="pagerForm" action="Main/Meeting/noendlist" onsubmit="return navTabSearch(this);">
      <div class="bjui-searchBar">
	      <input type="hidden" name="pageNum" value="${page.pageNumber}" />
	      <input type="hidden" name="numPerPage" value="${page.pageSize}" />
	      <label>标题：</label> <input type="text" placeholder="请输入关键字" name="stitle" value="${stitle}" size="12"/>
	      <label>类型：</label> <input type="text" placeholder="请输入关键字" name="stype" value="${stype}" size="12"/>
	      <label>申请日期：</label> <input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}"> - 
	      <input type="text" name="edate" data-toggle="datepicker" style="width: 120px"  readonly value="${edate}">
	      <button type="submit" class="btn-default" data-icon="search">查询</button>
		  <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>  
	      <div class="pull-right">
	         	<a class="btn btn-blue" data-icon="edit" href="Main/Meeting/approve2/{#bjui-selected}" data-toggle="dialog" data-id="approve" data-title="编辑" data-mask="true" data-width="850" data-height="600">编辑</a>
	         	<a class="btn btn-blue" data-icon="eye" href="Main/Meeting/print/{#bjui-selected}" data-toggle="dialog" data-id="approve" data-title="查看" data-mask="true" data-width="850" data-height="600">查看</a>
	         	
	         	<a class="btn btn-red" data-icon="fa-trash" href="Main/Meeting/delete/{#bjui-selected}" data-toggle="doajax" data-confirm-msg="您确定要删除该请假申请?">删除</a>
	     		<a class="btn btn-blue"  data-icon="print" href="Main/Meeting/exportPdf/{#bjui-selected}" data-toggle="dialog" data-id="approve_print" data-mask="true"
	                title="打印">打印</a>
	     	</div>
     	</div>
     </form>
    
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
		<thead>
			<tr align="center">
                <th width="5%">序号</th>
                <th width="15%">申请时间</th>
                <th width="10%">申请人</th>
                <th width="12%">申请科室</th>
                <th width="20%">会议标题</th>
                <th width="8%">会议类型</th>
                <th width="20%">当前环节</th>
                <th width="10%">当前处理人</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${! empty page.list}">
                    <c:forEach items="${page.list}" var="wf" varStatus="vs">
                        <c:set value="${fn:split(wf.todoman,';')}" var="uids"/>
                        <tr style="cursor:pointer" title="双击打开" align="center" target="id" data-id="${wf.id}" dbDialog="true" mask="true" dbTitle="查看" dbDialogId="approve_view"  minable="false" resizable="false" drawable="false" dbWidth="850" dbHeight="600" href="Main/Meeting/print/${wf.id}">
                           
                            <td>${vs.index+1}</td>
                            <td>${fn:substring(wf.starttime, 0, 16)}</td>
                            <td>${wf.uname}</td>
                            <td>${wf.dpname}</td>
                            <td class="custom" title="${wf.meetingtitle}">${wf.meetingtitle}</td>
                            <td>${wf.meetingtype}</td>
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
                        <td colspan="8" style="color: red;">无任何记录！</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>
<%@ include file="/include/pagination.jsp" %>