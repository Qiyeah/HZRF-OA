<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
	<div class="bjui-pageHeader">
        <form id="pagerForm" action="Main/Docsend/filecab" method="post" data-toggle="ajaxsearch">
            <input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
			<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
            <input type="hidden" name="orderField" value="${orderField}" />  
   			<input type="hidden" name="orderDirection" value="${orderDirection}" />
            <div class="bjui-searchBar">
                    <label class="control-label x80">文&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label><input type="text" name="sdocno" value="${sdocno}" size="12" placeholder="请输入关键字"/>
                    <label class="control-label x80">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</label><input type="text" name="stitle" value="${stitle}" size="12" placeholder="请输入关键字"/>
  					 <label class="control-label x80">拟稿科室：</label><select name="sdepart" data-toggle="selectpicker" data-width="120">
		              <option value="" >全部</option>
		              <c:forEach items="${departs}" var="temp">
		                  <option value="${temp.id}" <c:if test="${sdepart==temp.id}">selected</c:if>>${temp.fname}</option>
		              </c:forEach>
		              </select>
                    
					<div class="pull-right">
		              <c:if test="${pert:hasperti(applicationScope.docsend_newcab, loginModel.limit)}">
		                <button type="button" class="btn btn-green" data-url="Main/Docsend/newcab" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleid" data-title="添加发文归档"
							data-width="850" data-height="600">添加</button>
		                 </c:if>
						<a href="Main/Docsend/approve2/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="docsendupdate" data-icon="fa-edit" data-title="修改发文归档" data-width="850" data-height="600">编辑</a> 
						<a href="Main/Docsend/toprint/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-icon="eye" data-mask="ture" data-id="Enterpdate" data-title="查看"
							data-width="850" data-height="600">查看</a>
					    <a href="Main/Docsend/exportPdf/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-id="viewpdf" data-title="打印" data-icon="fa-print" data-max="true" data-mask='true' data-width="1000" data-height="600">打印</a>
		               	
		                  <c:if test="${pert:hasperti(applicationScope.deleteSendCab, loginModel.limit)}">
		                 <a href="Main/Docsend/deleteCab/{#bjui-selected}" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o" data-confirm-msg="您确定要删除该记录吗?">删除</a>
		                </c:if>
		                <c:if test="${pert:hasperti(applicationScope.exportSendCab, loginModel.limit)}">
		                  <a class="btn btn-green" data-icon="sign-out" href="javascript:exportSubmit('exportSendCabList')" title="导出Excel">导出Excel</a>
		                </c:if> 
		            </div>    
		            <br>
                     <label class="control-label x80">发文分类：</label><select name="stype" data-toggle="selectpicker" data-width="120">
	                     <option value="" >全部</option>
	                     <option value="惠人防" <c:if test="${stype == '惠人防'}">selected</c:if>>惠人防</option>
	                   
	                     <option value="会议纪要" <c:if test="${stype == '会议纪要'}">selected</c:if>>会议纪要</option>
                     </select>&nbsp;
                      <label>拟稿日期：</label> <input type="text" name="sdate" data-toggle="datepicker" size="12" readonly value="${sdate}">　　—— &nbsp;　 <input type="text" name="edate" data-toggle="datepicker" size="12" readonly value="${edate}">       
            		<button type="submit" class="btn-default" data-icon="search">查询</button>
					<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>   
            </div>
        </form>
	</div>
<div class="bjui-pageContent tableContent" >
        <form method="post" action="Main/Docsend/exportCab" >
            <input type="hidden" name="sdocno" value="${sdocno}"/>
            <input type="hidden" name="stitle" value="${stitle}"/>
            <input type="hidden" name="sdate" value="${sdate}"/>
            <input type="hidden" name="edate" value="${edate}"/>
            <input type="hidden" name="sdepart" value="${sdepart}"/>
            <input type="hidden" name="stype" value="${stype}"/>
            <button id="exportSendCabList" style="display: none" hidden="hidden" type="submit">导出excel</button>
        </form>
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" data-selected="true"> 
			<thead>
				<tr align="center" >
					<th width="5%" >序号</th>
					<th width="15%"  data-order-field="starttime">拟稿时间</th>
					<th width="10%"  data-order-field="dpname">拟稿科室</th>
					<th width="15%"  data-order-field="docno">文号</th>
					<th width="55%"  data-order-field="doctitle">文件标题</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${! empty page.list}">
						<c:forEach items="${page.list}" var="wf" varStatus="vs">
							<tr  style="cursor:pointer" title="双击打开" align="center" target="id" data-id="${wf.id}" dbDialog="true" dbDialogId="approve_print" max="false" mask="true" maxable="false" minable="false" resizable="false" drawable="true" dbTitle="查看 / 打印" dbWidth="850" dbHeight="600" href="Main/Docsend/toprint/${wf.id}">
								<td>${vs.index+1}</td>
								<td>${fn:substring(wf.starttime, 0, 10)}</td>
								<td>${wf.dpname}</td>
								<td>${wf.docno}</td>
								<td class="custom" title="${wf.doctitle}">${wf.doctitle}</td>
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

