<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
    String basePath = request.getContextPath() + "/";
%>
<ul class="tree treeFolder">
    <li><a href="javascript">我的邮箱</a>
        <ul>
            <li><a class="add" href="<%=basePath%>Main/mail/add" target="dialog" mask="true" drawable="true" resizable="true" maxable="true"
                   title="写信" width="750" height="530" rel="addMail">写信</a></li>
            <li><a href="Main/mail/receive" target="ajax" rel="mail">收件箱<c:if test="${receiveCountTotal>0}"><b>(${receiveCountTotal})</b></c:if></a></li>
            <li><a href="Main/mail/send" target="ajax" rel="mail">发件箱<c:if test="${sendCount>0}"><b>(${sendCount})</b></c:if></a></li>
            <li><a href="Main/mail/draft" target="ajax" rel="mail">草稿箱<c:if test="${draftCount>0}"><b>(${draftCount})</b></c:if></a></li>
            <li><a href="Main/mail/rubbish" target="ajax" rel="mail">垃圾箱<c:if test="${rubbishCount>0}"><b>(${rubbishCount})</b></c:if></a></li>
        </ul>
    </li>
</ul>