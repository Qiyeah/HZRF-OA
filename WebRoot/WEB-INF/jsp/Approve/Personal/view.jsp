<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent">
    <div class="pageFormContent" layoutH="56">
        <table class="wordInfo" align="center" style="width: 98%;">
            <tr>
                <td width="25%" class="title">姓　　名</td>
                <td width="75%" class="normal">${username }</td>
            </tr>
            <tr>
                <td class="title">开始工作日期</td>
                <td class="normal">${personal.begindate}</td>
            </tr>
            <tr>
                <td class="title">婚姻状态</td>
                <td class="normal">${personal.married}</td>
            </tr>
            <tr>
                <td class="title">职务名称</td>
                <td class="normal">${personal.gradename }</td>
            </tr>
            <tr>
                <td class="title">职务级别</td>
                <td class="normal">${personal.gradelevel }</td>
            </tr>
            <tr>
                <td class="title">内部电话</td>
                <td class="normal">${personal.phone }</td>
            </tr>
            <tr>
                <td class="title">手　　机</td>
                <td class="normal">${personal.mbphone }</td>
            </tr>
        </table>
    </div>
</div>
