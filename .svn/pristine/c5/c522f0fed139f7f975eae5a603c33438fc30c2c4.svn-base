<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/MinistryMeeting/main" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
		
		<div class="bjui-searchBar">
		<label>日期：</label><input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}"> - <input type="text" name="edate" style="width: 120px" data-toggle="datepicker" readonly value="${edate}">
		<label>分类：</label><select name="classification" data-toggle="selectpicker">
					<option value="">--请选择--</option>
					<c:forEach items="${list}" var="list">
					 <option value="${list.id}" <c:if test="${classification == list.id}"> selected </c:if> >${list.name}</option>
					</c:forEach>
                </select> 
        <button type="submit" class="btn-default" data-icon="search">查询</button>
		<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>         
               <div class="pull-right">
            	<button type="button" class="btn btn-green" data-url="Main/MinistryMeeting/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleid" data-title="添加防办内部会议"
					data-width="800" data-height="450">添加</button>
           		
           		 <a href="Main/MinistryMeeting/updateip/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="Enterprpdate" data-icon="fa-edit" data-title="修改防办内部会议"
					data-width="800" data-height="450">编辑</a> 
					
                <a href="Main/MinistryMeeting/delete/{#bjui-selected}" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o"
					data-confirm-msg="您确定要删除该记录吗?">删除</a>
		</div>
		</div>
	</form>
 </div>
<div class="bjui-pageContent tableContent">
	
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" data-selected="true">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="13%">日期</th>
				<th width="17%">地点</th>
				<th width="15%">议题</th>
				<th width="10%">分类</th>
				<th width="8%">主持人</th>
				<th width="15%">参加人员</th>
                <th width="17%">列席人员</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="wf" varStatus="vs">
                            <tr style="cursor:pointer" title="双击打开" align="center" data-id="${wf.id}" dbDialog="true" dbTitle="修改防办内部会议" dbDialogId="opinionUpdate" maxable="true"
                            mask="true" dbWidth="800" dbHeight="450" href="Main/MinistryMeeting/view/${wf.id}">
							<td>${vs.index+1}</td>
							<td>${fn:substring(wf.date, 0, 16)}</td>
							<td align="left">${wf.place}</td>
							<td>${wf.lssue}</td>
							<td>${classificationHM.get(wf.classification)}</td>
							<td>${wf.host}</td>
							<td align="left">${wf.attendee}</td>
							<td align="left">${wf.attend}</td>
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