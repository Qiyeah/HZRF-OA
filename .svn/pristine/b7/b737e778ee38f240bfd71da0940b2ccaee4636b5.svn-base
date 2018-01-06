<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent">
	<form method="post" action="Main/Personal/updateTel" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input name="t_Personal.id" class="required" type="hidden" value="${personal.id}" />
		<div class="pageFormContent" layoutH="56">
			<table class="wordInfo" align="center" style="width: 98%;">
				<tr>
					<td width="25%" class="title">姓　　名</td>
					<td width="75%">&nbsp;${username }</td>
				</tr>
				<tr>
					<td class="title">职务名称</td>
					<td><input type="text" name="t_Personal.gradename" value="${personal.gradename }" style="width: 98%" /></td>
				</tr>
				<tr>
					<td class="title">职务级别</td>
					<td><input type="text" name="t_Personal.gradelevel" value="${personal.gradelevel }" style="width: 98%" /></td>
				</tr>
				<tr>
					<td class="title">内部电话</td>
					<td><input type="text" name="t_Personal.phone" value="${personal.phone }" style="width: 98%" /></td>
				</tr>
				<tr>
					<td class="title">手　　机</td>
					<td><input type="text" name="t_Personal.mbphone" value="${personal.mbphone }" style="width: 98%" /></td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
                <c:if test="${pert:hasperti(applicationScope.updateTel, loginModel.limit)}">
                    <li><div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">保存</button>
                            </div>
                        </div>
                    </li>
                </c:if>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
