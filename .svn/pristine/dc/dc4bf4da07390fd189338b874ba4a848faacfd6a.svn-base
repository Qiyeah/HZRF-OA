<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<style>
<!--
.ico_mailtitle{background:url(images/mail.png) 1px -82px no-repeat;width:26px;height:16px;}

.cir{float:left;display:block;width:18px;height:16px;overflow:hidden;}
.mail_forward{background:url(images/mail.png) -48px -48px no-repeat;}
.mail_reply{background:url(images/mail.png) -48px -32px no-repeat;}
.Rr{background:url(images/mail.png) -48px -16px no-repeat;}
.Ru{background:url(images/mail.png) -48px 0 no-repeat;}

.cij{float:left;display:block;width:12px;height:12px;overflow:hidden;margin-top:3px;}
.Ju{background:url(images/mail.png) -65px 0 no-repeat;}
-->
</style>

<%-- <c:set var="page" value="${page}"/> --%>
<div class="bjui-pageHeader">
  <form id="pagerForm" action="Main/Mail/receive" method="post" data-toggle="ajaxsearch">
	<div class="bjui-searchBar">
	<input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
	<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
	
	<label>发件人：</label> <input type="hidden" name="mail.userid" value="${senderId}" /><input name="mail.username" type="text" value="${senderName}" suggestFields="username" suggestUrl="Main/search/searchUser" lookupGroup="mail" size="12" placeholder="请输入关键字"/>
    <label>邮件主题：</label> <input type="text" name="title" value="${title}" size="12" placeholder="请输入关键字"/>
    <div class="pull-right">
       <a href="Main/Mail/delete/99-receive" class="btn btn-red" data-toggle="doajaxchecked" data-group="ids" data-icon="fa-trash-o" data-confirm-msg="您确定要删记录吗?">删除</a>
      
       <a href="Main/Mail/forward/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="Mailview" data-icon="fa-rotate-right" data-title="转发"
					data-width="1000" data-height="600">转发</a>
					
       <a href="Main/Mail/reply/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="Mailview" data-icon="fa-edit" data-title="回复"
					data-width="1000" data-height="600">回复</a>      
                   
       <a href="Main/Mail/view/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-icon="fa-eye" data-mask="ture" data-id="Mailview" data-title="查看"
					data-width="1000" data-height="600">查看</a>
           
        <a href="Main/Mail/delete/1-receive" class="btn btn-red" data-toggle="doajaxchecked" data-group="ids" data-icon="fa-trash-o" data-confirm-msg="彻底删除后邮件将无法恢复，您确定要删除吗？">彻底删除</a>  
       </div> 
    <br>
    <label>时&nbsp;&nbsp;&nbsp;间：</label> <input type="text" name="sdate" data-toggle="datepicker" data-pattern="yyyy-MM-dd" readonly value="${sdate}" size="12"> 　——　　 <input type="text" name="edate" data-toggle="datepicker" data-pattern="yyyy-MM-dd" readonly value="${edate}" size="12">
	<button type="submit" class="btn-default" data-icon="search">查询</button>
	<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
	
	</div>
	</form>
</div> 

<div class="tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" data-selected="true">
		<thead>
			<tr>
				<th width="5%"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
				<th align="center" width="3%"><ul class="ico_mailtitle"></ul></th>
				<th align="center" width="12%">发件人</th>
				<th align="center" width="70%">主题</th>
                <th align="center" width="5%">附件</th>
				<th align="center" width="25%">时间</th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
				<c:when test="${! empty page.list}">
			<c:forEach items="${page.list}" var="list">
              <tr style="cursor:pointer" title="双击打开" data-id="${list.id}"  dbDialog="true" dbTitle="查看" dbDialogId="Mailview" maxable="true"
                data-mask="true" dbWidth="1000" dbHeight="600" href="Main/Mail/view/${list.id}">  
				<td><input type="checkbox" name="ids" data-toggle="icheck" value="${list.id}"></td>
				<td>
                    <c:choose>
                        <c:when test="${list.isRead==0}">
                            <ul class="cir Ru"></ul>
                        </c:when>
                        <c:when test="${list.isRead==1}">
                            <c:choose>
                                <c:when test="${list.status==1}">
                                    <ul class="cir mail_reply"></ul>
                                </c:when>
                                <c:when test="${list.status==2}">
                                    <ul class="cir mail_forward"></ul>
                                </c:when>
                                <c:otherwise>
                                    <ul class="cir Rr"></ul>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <ul class="cir Ru"></ul>
                          
                        </c:otherwise>
                    </c:choose>
				</td>
				<td>
					<c:if test="${list.isRead==1}">${list.fromUserName}</c:if>
					<c:if test="${list.isRead==0}"><b>${list.fromUserName}</b></c:if>
				</td>
				<td align="left">
					<c:if test="${list.isRead==1}">${list.title}</c:if>
					<c:if test="${list.isRead==0}"><b>${list.title}</b></c:if>
				</td>
                <td><c:if test="${!empty list.fjid}"><ul class="cij Ju"></ul></c:if></td>
				<td>
					<c:if test="${list.isRead==1}"><fmt:formatDate value="${list.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></c:if>
					<c:if test="${list.isRead==0}"><b><fmt:formatDate value="${list.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></b></c:if>
				</td>
			</tr>
			</c:forEach>
			</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="6" style="color: red;">无任何记录！</td>
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
    	<span>条，共${page.totalRow}条 &nbsp;&nbsp;</span><c:if test="${receiveCount > 0}"><span style="color: red;">其中新邮件&nbsp;<b>${receiveCount}</b>&nbsp;封</span></c:if>
    </div>
   	<div class="pagination-box" data-toggle="pagination" data-total="${page.totalRow}" data-page-size="${page.pageSize}" data-page-current="${page.pageNumber}"></div>
</div>