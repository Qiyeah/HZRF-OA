<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li>
        	<button type="submit" class="btn-default" data-icon="save">
	        	<c:choose>
	        		<c:when test="${saveString != null && !saveString.equals('')}">${saveString}</c:when>
	        		<c:otherwise>保存</c:otherwise>
	        	</c:choose>
        	</button>
        </li>
    </ul>
</div>
<c:set value="" var="saveString"/> 