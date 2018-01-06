<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
    <input type="hidden" name="t_Myopinion.id" value="${t_Myopinion.id}" />
    <input type="hidden" name="t_Myopinion.u_id" value="${t_Myopinion.u_id}" />
    <table class="wordInfo" align="center" style="width: 98%;">
        <tr>
            <td style="width: 25%;" class="title">序　　号</td>
            <td style="width: 75%;">
                <input type="text" name="t_Myopinion.opinionindex" value="${t_Myopinion.opinionindex}" class="required" data-rule="required;digits" data-msg-required="必填" maxlength="5" style="width: 100%" />
            </td>
        <tr>
            <td style="width: 25%;" class="title">是否启用</td>
            <td style="width: 75%;">
                <select name="t_Myopinion.status" class="required" data-toggle="selectpicker" data-rule="required"  data-msg-required="必选" data-width="100%">
                    <option value="1"  <c:if test="${t_Myopinion.status == 1}"> selected </c:if>>是</option>
                    <option value="0"  <c:if test="${t_Myopinion.status == 0}"> selected </c:if>>否</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="title">意见内容</td>
            <td><textarea name="t_Myopinion.opinion" rows="10" style="width: 100%; overflow:auto" maxlength="150">${t_Myopinion.opinion}</textarea></td>
        </tr>
    </table>
