<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>


<div class="bjui-pageHeader">
		<form id="pagerForm" method="post" action="Main/Relation/main" data-toggle="ajaxsearch">
		<div class="bjui-searchBar">
			<input type="hidden" name="pageCurrent" value="${page.pageNumber}" /> <input type="hidden" name="pageSize" value="${page.pageSize}" /> 
			<input type="hidden" name="orderField" value="${orderField}" /> <input type="hidden" name="orderDirection" value="${orderDirection}" /> 
			<input type="hidden" name="sort" value="${sort}" /> 
			<label>党员姓名：</label> <input type="text" placeholder="请输入关键字" name="sname" value="${sname}" size="18">
			<label>介绍类型：</label> <select name="stotype" data-toggle="selectpicker">
				<option value="" <c:if test="${stotype == 0}"> selected="selected" </c:if>>---全部---</option>
				<option value="1" <c:if test="${stotype == 1}"> selected="selected" </c:if>>转出</option>
				<option value="2" <c:if test="${stotype == 2}"> selected="selected" </c:if>>转入</option>
				<option value="3" <c:if test="${stotype == 3}"> selected="selected" </c:if>>市内</option>
			</select>
			<label>民　　族：</label> <input type="text" placeholder="请输入关键字" name="snation" value="${snation}" size="12">
			
			<div class="pull-right" >
				<c:if test="${pert:hasperti(applicationScope.relationMain_add, loginModel.limit)}">
					<a class="btn btn-green" data-icon="plus" href="Main/Relation/addip" data-toggle="dialog" data-mask="true"
						title="添加" data-width="850" data-height="550">添加</a>
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.Relation_update, loginModel.limit)}">
					<a class="btn btn-blue" data-icon="edit" href="Main/Relation/updateip/{#bjui-selected}" data-toggle="dialog" data-id="relation_dialog"  data-mask="true" 
						 title="编辑" data-title="编辑" data-width="850" data-height="550">编辑</a>
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.Relation_delete, loginModel.limit)}">
					<a class="btn btn-red" data-icon="fa-trash-o" href="Main/Relation/delete/{#bjui-selected}" data-toggle="doajax"  data-confirm-msg="您确定要删除该收件吗?">删除</a>
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.Relation_export, loginModel.limit)}">
					<a class="btn btn-green" href="javascript:exportSubmit('exportRelation')" data-icon="sign-out" title="导出Excel" >导出Excel</a>
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.Relation_export, loginModel.limit)}">
					
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.eecertadd, loginModel.limit)}">
					<a class="btn btn-blue" data-icon="upload" href="Main/upload/uploadip/3" data-toggle="dialog" data-mask="true"  data-id="upload"
						title="党组织转接导入" data-width="560" data-height="300">党组织转接导入</a>
				</c:if>
			</div><br>			
			<label>介绍地址：</label> <label class="control-label x20">由</label><input type="text" placeholder="请输入关键字" name="sfrom"  value="${sfrom}" size="16"><label class="control-label x15">去</label>　<input type="text" placeholder="请输入关键字" name="sto" value="${sto}" size="14">
			<label>办理日期：</label> <input type="text"  name="sdate" style="width: 120px" data-rule="date" data-toggle="datepicker" readonly value="${sdate}">
			 - <input type="text" name="edate" style="width: 120px" data-rule="date" data-toggle="datepicker" readonly value="${edate}"> 
			
			<br> 
			 <label>身份证号：</label> <input type="text" placeholder="请输入关键字" name="sidcard" value="${sidcard}"size="18">
			<label>落实情况：</label> <select name="sresult" data-toggle="selectpicker">
				<option value="" <c:if test="${sresult == 0}"> selected="selected" </c:if>>---全部---</option>
				<option value="1" <c:if test="${sresult == 1}"> selected="selected" </c:if>>未落实</option>
				<option value="2" <c:if test="${sresult == 2}"> selected="selected" </c:if>>已落实</option>
			</select>
			<label>有效日期：</label> <input type="text" name="validsdate" style="width: 120px" data-rule="date" data-toggle="datepicker" readonly value="${validsdate}">
			 - <input type="text" name="validedate" style="width: 120px" data-rule="date" data-toggle="datepicker" readonly value="${validedate}"> 
			  
			<br> 
			<label>抬头单位：</label> <input type="text" placeholder="请输入关键字" name="sunit" value="${sunit}" size="18">
			<label>党员类型：</label> <select name="stype" data-toggle="selectpicker">
				<option value="" <c:if test="${stype == 0}"> selected="selected" </c:if>>---全部---</option>
				<option value="1" <c:if test="${stype == 1}"> selected="selected" </c:if>>预备</option>
				<option value="2" <c:if test="${stype == 2}"> selected="selected" </c:if>>正式</option>
			</select>
			<label>党费日期：</label> <input type="text" name="paidsdate" style="width: 120px" data-rule="date" data-toggle="datepicker" readonly value="${paidsdate}">
			 - <input type="text" name="paidedate" style="width: 120px" data-rule="date" data-toggle="datepicker" readonly value="${paidedate}">
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>

<div class="bjui-pageContent tableContent">
<form method="post" action="Main/Relation/export">
		<%-- 导出用检索条件保存--%>
        <input type="hidden" name="sname" value="${sname}"/>
        <input type="hidden" name="sdate" value="${sdate}"/>
        <input type="hidden" name="edate" value="${edate}"/>
        <input type="hidden" name="sfrom" value="${sfrom}"/>
        <input type="hidden" name="sto" value="${sto}"/>
        <%-- 身份证号--%>
        <input type="hidden" name="sidcard" value="${sidcard}"/>
        <input type="hidden" name="sunit" value="${sunit}"/>
        <input type="hidden" name="validsdate" value="${validsdate}"/>
        <input type="hidden" name="validedate" value="${validedate}"/>
        <input type="hidden" name="sresult" value="${sresult}"/>
        <input type="hidden" name="stotype" value="${stotype}"/>
        <input type="hidden" name="snation" value="${snation}"/>
        <input type="hidden" name="paidsdate" value="${paidsdate}"/>
        <input type="hidden" name="paidedate" value="${paidedate}"/>
        <input type="hidden" name="stype" value="${stype}"/>
        <button id="exportRelation" style="display: none" hidden="hidden" type="submit">导出Excel</button>
	</form>
	<table class="table table-striped table-bordered table-hover nowrap" data-toggle="tablefixed" data-selected="true" >
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="24%" data-orderField="unit" class="desc">抬头单位</th>
				<th width="10%" data-orderField="name" class="desc">姓名</th>
				<th width="5%" data-orderField="sex" class="desc">性别</th>
				<th width="5%" data-orderField="age" class="desc">年龄</th>
				<th width="7%" data-orderField="type" class="desc">党员类型</th>
				<th width="12%" data-orderField="rfrom" class="desc">转出地</th>
				<th width="12%" data-orderField="rto" class="desc">转入地</th>
				<th width="10%">党员联系电话</th>
				<th width="10%" data-orderField="rdate" class="desc">办理日期</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="r" varStatus="vs">
						<tr align="center"  data-id="${r.id}" data-maxable="true" ondblclick="printPDF(${r.no})">
							<td>${vs.index+1}</td>
							<td align="left">${r.unit}</td>
							<td>${r.name}</td>
							<td><c:if test="${r.sex == 1}">男</c:if>
								<c:if test="${r.sex == 2}">女</c:if></td>
							<td>${r.age}</td>
							<td><c:if test="${r.type == 1}">预备</c:if>
								<c:if test="${r.type == 2}">正式</c:if></td>
							<td align="left">${r.rfrom}</td>
							<td align="left">${r.rto}</td>
							<td>${r.u_phone}</td>
							<td>${r.rdate}</td>
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
<%@ include file="/include/pagination.jsp"%>

<script type="text/javascript">
function printPDF(no){
    $(this).dialog({id:'viewpdf', url:'Main/Relation/viewPdf?fileName=' + no, title: no, width:1100, height:800});
	
}
</script>
