<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent">
	<form id="approveForm" action="Main/Present/add" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <input type="hidden" value="${year}" name="t_Present.year"/>
		<div class="pageFormContent" layoutH="60">
			<table class="wordInfo" align="center" style="width: 98%">
				<tr>
					<td class="title" colspan="4">党员证明信</td>
				</tr>
				<tr>
					<td width="15%" class="title">编　　号</td>
					<td width="35%">
                        <table>
                            <tr>
                                <td style="border: 0px">第</td>
                                <td style="border: 0px" ><input name="t_Present.no" type="text" value="${no}" class="required digits" style="width: 98%" /></td>
                                <td style="border: 0px">号</td>
                            </tr>
                        </table>
                    </td>
                    <td class="title">日　　期</td>
                    <td>
                        <input name="t_Present.rdate" type="text" class="required date" value="${rdate}" style="width: 98%" />
                    </td>
				</tr>
				<tr>
				    
                    <td class="title">姓　　名</td>
					<td ><input name="t_Present.pname" type="text" class="required" style="width: 98%" /></td>
				    <td class="title">党员类型</td>
                    <td><select name="t_Present.type" class="combox">
                        <option value="1">预备</option>
                        <option value="2">正式</option></select></td>
                </tr>
                <tr>
                    <td width="15%" class="title">抬头单位</td>
					<td colspan="3">
                        <input type="text" name="t_Present.punit" suggestfields="unit" suggesturl="Main/search/searchUnit" lookupgroup="t_Present" class="required" style="width: 98%" autocomplete="off">
                    </td>
                </tr>
                 <tr>
                    <td class="title">所    在<br/>工作（学习）<br/>单    位</td>
                    <td>
                        <input type="text" name="t_Present.rfrom" suggestfields="rfrom" suggesturl="Main/search/searchFrom" lookupgroup="t_Present" class="required" style="width: 98%" autocomplete="off">
                     <%--   <input name="t_Relation.rfrom" type="text" class="required" style="width: 98%" />--%>
                    </td>
                    <td class="title">目的<br/>工作（学习）<br/>单    位 </td>
                    <td>
                        <input type="text" name="t_Present.rto" suggestfields="rto" suggesturl="Main/search/searchTo" lookupgroup="t_Present" class="required" style="width: 98%" autocomplete="off">
          <%--              <input name="t_Relation.rto" type="text" class="required" style="width: 98%" />--%>
                    </td>
                </tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li>
				    <div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
				    </div>
				</li>
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
