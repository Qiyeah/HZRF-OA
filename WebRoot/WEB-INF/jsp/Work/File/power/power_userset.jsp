<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%@ taglib prefix="pert" uri="/WEB-INF/permit.tld"%>
<c:set var="role" value="${role }"/>
<c:set var="join" value="_"/>
<c:set var="join1" value=","/>
<div class="pageContent">
	<form method="post" action="Main/power/userset" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input name="id" class="required" type="hidden" value="${role.id}" />
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>当前角色名称：</dt>
				<dd>${role.rolename}</dd>
			</dl>	
			<dl><dt>用户列表：</dt></dl>
			<div style="height: 300px; clear: both; overflow: auto;">	
				<ul class="tree treeFolder treeCheck expand">
				   <c:forEach items="${departments}" var="dp">
						<li><a>${dp.sname }</a>
						   <c:forEach items="${userlist}" var="ul">
						     <c:if test="${dp.id==ul.d_id }">
							   <ul>
									<c:set var="uid" value="${join}${ul.id}${join1}" />      
									<li><a tname="userid" tvalue="${ul.id}" <c:if test="${fn:contains(role.userId,uid)}">checked="true"</c:if> >${ul.name}</a></li>
							   </ul>
						    </c:if>	
						  </c:forEach> 
						</li>
				   </c:forEach>		
				</ul>
			</div>	
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>