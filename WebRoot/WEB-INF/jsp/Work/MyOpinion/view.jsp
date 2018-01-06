<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
			<table class="wordInfo" align="center" style="width: 98%;">
                <tr>
                    <td style="width: 25%;" class="title">序号</td>
                    <td style="width: 75%;">
                        ${t_Myopinion.opinionindex}
                    </td>
                </tr>
                <tr>
                    <td style="width: 25%;" class="title">是否启用</td>
                    <td style="width: 75%;">
                        <c:choose>
                            <c:when test="${t_Myopinion.usable=='1'}">是</c:when>
                            <c:otherwise>否</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td class="title" style="height: 90px;">意见内容</td>
                    <td>
                        ${t_Myopinion.opinion}
                    </td>
                </tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
</div>
