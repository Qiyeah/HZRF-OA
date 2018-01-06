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
			<dl >
				<dt>当前角色名称：</dt>
				<dd>${role.rolename}</dd>
			</dl>	
			<dl style="width:300px;"><dt style="width:250px;">已被分配人员：</dt></dl>
			<div style="height: 300px; clear: both; overflow: auto;">	
				<ul class="tree treeFolder expand">
				   <c:forEach items="${departments}" var="dp">
						<li><a>${dp.sname }</a>
						   <c:forEach items="${userlist}" var="ul">
						     <c:if test="${dp.id==ul.d_id }">
							   <ul>
									<c:set var="uid" value="${join}${ul.id}${join1}" />      
									<c:if test="${fn:contains(role.userId,uid)}"><li><a >${ul.name}</a></li></c:if>
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
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
			</ul>
		</div>
	</form>
</div>