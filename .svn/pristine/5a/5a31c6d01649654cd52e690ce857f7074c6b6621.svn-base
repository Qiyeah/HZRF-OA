<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
       <form id="pagerForm" action="Main/Docsend/noendlist" method="post" data-toggle="ajaxsearch">
        <div class="bjui-searchBar">
           <input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		   <input type="hidden" name="pageSize" value="${page.pageSize}" />
           <label>文号：</label><input type="text" name="sdocno" value="${sdocno}" size="12" placeholder="请输入关键字"/>
           <label>标题：</label><input type="text" name="stitle" value="${stitle}" size="12" placeholder="请输入关键字"/>
           <label>拟稿日期：</label><input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}"> - <input type="text" name="edate" style="width: 120px" data-toggle="datepicker" readonly value="${edate}">
       	   <button type="submit" class="btn-default" data-icon="search">查询</button>
		   <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>   
        <div class="pull-right">
			<a href="Main/Docsend/toprint/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-icon="eye" data-mask="ture" data-id="Enterpdate" data-title="查看"
					data-width="850" data-height="600">查看</a>
        	<a href="Main/Docsend/approve2/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="docsendupdate" data-icon="fa-edit" data-title="修改办理中发文" data-width="850" data-height="600">编辑</a> 
        	<a href="Main/Docsend/delete/{#bjui-selected}" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o" data-confirm-msg="您确定要删除该记录吗?">删除</a>
        	<a href="Main/Docsend/exportPdf/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-id="viewpdf" data-title="打印" data-icon="fa-print" data-max="true" data-mask='true' data-width="1000" data-height="600">打印</a>		
        </div>
    </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" data-selected="true">
        <thead>
            <tr align="center">
                <th width="5%">序号</th>
                <th width="13%">拟稿时间</th>
                <th width="10%">拟稿科室</th>
                <th width="15%">文号</th>
                <th width="37%">文件标题</th>
                <th width="10%">当前环节</th>
                <th width="10%">当前处理人</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${! empty page.list}">
                    <c:forEach items="${page.list}" var="wf" varStatus="vs">
                        <c:set value="${fn:split(wf.todoman,';')}" var="uids"/>
                        <tr style="cursor:pointer" title="双击打开" align="center" target="id" data-id="${wf.id}" dbDialog="true" dbDialogId="approve_print" max="false" mask="true" maxable="false" minable="false" resizable="false" drawable="false" dbTitle="查看 " dbWidth="850" dbHeight="600" href="Main/Docsend/toprint/${wf.id}">
                            <td>${vs.index+1}</td>
                            <td>${fn:substring(wf.starttime, 0, 16)}</td>
                            <td>${wf.dpname}</td>
                            <td>${wf.docno}</td>
                            <td class="custom" title="${wf.doctitle}">${wf.doctitle}</td>
                            <td>${wf.active_name}</td>
                            <td title="<c:forEach items="${uids}" var="uid" varStatus="vs">${userHM.get(uid)} </c:forEach>">
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
<div class="bjui-pageFooter">
	<div class="pages">
		<span>每页&nbsp;</span>
		<div class="selectPagesize">
			<select data-toggle="selectpicker" data-toggle-change="changepagesize">
				<option <c:if test="${page.pageSize==20}">selected="selected"</c:if> value="20">20</option>
				<option <c:if test="${page.pageSize==50}">selected="selected"</c:if> value="50">50</option>
				<option <c:if test="${page.pageSize==100}">selected="selected"</c:if> value="100">100</option>
				<option value="500" <c:if test="${page.pageSize==500}">selected="selected"</c:if>>200</option>
			</select>
		</div>
		<span>条，共${page.totalRow}条</span>
	</div>
	<div class="pagination-box" data-toggle="pagination" data-total="${page.totalRow}" data-page-size="${page.pageSize}" data-page-current="${page.pageNumber}"></div>
</div>