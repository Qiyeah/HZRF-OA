<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<c:set var="deparmentList" value="${deparmentList}" />
<c:set var="userList" value="${userList}" />
<script type="text/javascript">
	
	/* function send() {
		alert("ss5");
		$("#approveForm").submit();
	} */
</script>
<div class="bjui-pageContent">
	<form>
		<input type="hidden" name="cond" id="cond" value="${cond}" />
		<div style="width: 95%; overflow: hidden;" layoutH="40">
			<div style="float: left; display: block; margin: 5px; overflow: auto; width: 200px; height: 95%;">
				<table class="wordInfo" align="center" style="width: 98%; height:300px; border: solid 1px #4CA5D8;">
					
				</table>
			</div>
			</div>
	</form>
</div>
<script type="text/javascript">
    <c:choose>
		<c:when test="1">
			send();
		</c:when>
		<c:otherwise></c:otherwise>
	</c:choose>
</script>