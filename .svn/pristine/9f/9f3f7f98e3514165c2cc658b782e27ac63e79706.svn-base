<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<script>
relationJson = function(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		$("#loading").hide();
                window.parent.navTab.reload("Main/Relation/main", {navTabId : "relationMain"});
		$.pdialog.closeCurrent();
	}
}

function submitRelation(){
    if($("#approveForm").valid()){
        //alert("请确认:"); 
        $("#loading").show();
        $("#approveForm").submit();
    }
}
</script>

<div class="bjui-pageContent tableContent">
	<form id="approveForm" action="Main/Relation/update" class="pageForm" method="post" data-toggle="validate">
			<input type="hidden" name="t_Relation.id" value="${t_relation.id}" />
            <table class="wordInfo" align="center" style="width: 95%;margin-bottom:23px;margin-top:23px">
                <tr>
                    <td class="title" colspan="4" style="line-height:40px;font-size:25px">中国共产党党员组织关系介绍信</td>
                </tr>
                <tr>
                    <td width="15%" class="title">抬头单位</td>
                    <td width="35%"><input name="t_Relation.unit" type="text" class="required" data-rule="required" data-msg-required="必填"  maxlength="100" style="width: 100%" value="${t_relation.unit}"/></td>
                    <td width="15%" class="title">编　　号</td>
                    <td width="35%">
                        <table>
                            <tr>
                                <td style="border: 0px">第</td>
                                <td style="border: 0px" ><input name="t_Relation.no" type="text" value="${t_relation.no}" class="required" data-msg-required="必填" data-rule="required digits" style="width: 90%" /></td>
                                <td style="border: 0px">号</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td class="title">姓　　名</td>
                    <td><input name="t_Relation.name" type="text" class="required" data-msg-required="必填" data-rule="required" maxlength="100" style="width: 100%" value="${t_relation.name}"/></td>
                    <td class="title">性　　别</td>
                    <td><select name="t_Relation.sex" data-width="100%" data-toggle="selectpicker">
                        <option value="1" <c:if test="${t_relation.sex == 1}"> selected="selected" </c:if>>男</option>
                        <option value="2" <c:if test="${t_relation.sex == 2}"> selected="selected" </c:if>>女</option></select></td>
                </tr>
                <tr>
                    <td class="title">年　　龄</td>
                    <td > <input name="t_Relation.age" type="text" data-rule="digits" maxlength="3" style="width: 100%" value="${t_relation.age}"/></td>
                    <td class="title">民　　族</td>
                    <td><input name="t_Relation.nation" type="text" style="width: 100%" value="${t_relation.nation}"/></td>
                </tr>
                <tr>
                    <td class="title">身份证号</td>
                    <td><input name="t_Relation.idcard" type="text" class="required" data-msg-required="必填" data-rule="required ID_card" maxlength="18"  style="width: 100%" value="${t_relation.idcard}"/></td>
                    <td class="title">党员类型</td>
                    <td><select name="t_Relation.type" data-width="100%" data-toggle="selectpicker">
                        <option value="1" <c:if test="${t_relation.type == 1}"> selected="selected" </c:if>>预备</option>
                        <option value="2" <c:if test="${t_relation.type == 2}"> selected="selected" </c:if>>正式</option></select></td>
                </tr>
                <tr>
                    <td class="title">转 出 地<br/>（ 单 位 ）<br/>党 组 织</td>
                    <td>
                        <input name="t_Relation.rfrom" type="text" class="required" data-msg-required="必填" data-rule="required" maxlength="100" value="${t_relation.rfrom}" style="width: 100%" />
                    </td>
                    <td class="title">接 收 地<br/>（ 单 位 ）<br/>党 组 织</td>
                    <td>
                        <input name="t_Relation.rto" type="text" class="required" data-msg-required="必填" data-rule="required" maxlength="100" value="${t_relation.rto}" style="width: 100%" />
                    </td>
                </tr>
                <tr>
                    <td class="title">党费交到</td>
                    <td>
                        <table>
                            <tr>
                                <td style="border: 0px"><select name="t_Relation.paid_year" class="combox" data-toggle="selectpicker">
                                    <c:forEach var="x" begin="1980" end="2030" step="1">
                                        <option value="${x}" <c:if test="${t_relation.paid_year == x}"> selected="selected" </c:if> >${x}</option>
                                    </c:forEach>
                                </select></td>
                                <td style="border: 0px"> 年 </td>
                                <td style="border: 0px">
                                    <select name="t_Relation.paid_month" class="combox" data-toggle="selectpicker">
                                        <c:forEach var="x" begin="1" end="12" step="1">
                                            <option value="${x}" <c:if test="${t_relation.paid_month == x}"> selected="selected" </c:if> >${x}</option>
                                        </c:forEach>
                                    </select>月
                                 </td>
                            </tr>
                        </table> </td>
                    <td class="title">有 效 期</td>
                    <td >
                        <table>
                            <tr>
                                <td style="border: 0px"><input name="t_Relation.valid" type="text" data-rule="digits required" class="required"" style="width: 100%" value="${t_relation.valid}"/></td>
                                <td style="border: 0px"> 天 </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td class="title">党　　员<br/>联系电话</td>
                    <td><input name="t_Relation.u_phone" type="text" maxlength="50" value="${t_relation.u_phone}" style="width: 100%"  /></td>
                    <td class="title">基层党委<br/>通讯地址</td>
                    <td><input name="t_Relation.u_address" type="text" maxlength="200" value="${t_relation.u_address}" style="width: 100%" /></td>
                </tr>
                <tr>
                    <td class="title">基层党委<br/>联系电话</td>
                    <td>
                        <input name="t_Relation.phone" type="text" maxlength="50" value="${t_relation.phone}"  style="width: 100%" />
                    </td>
                    <td class="title">基层党委<br/>传　　真</td>
                    <td>
                        <input name="t_Relation.fax" type="text" maxlength="50" value="${t_relation.fax}" style="width: 100%" />
                    </td>
                </tr>
                <tr>
                    <td class="title">基层党委<br/>邮　　编</td>
                    <td>
                        <input name="t_Relation.zipcode" type="text" maxlength="6" value="${t_relation.zipcode}" style="width: 100%" />
                    </td>
                    <td class="title">办理日期</td>
                    <td>
                        <input name="t_Relation.rdate" type="text"  class="required date" value="${t_relation.rdate}" style="width: 100%" />
                    </td>
                </tr>
                <tr>
                    <td class="title">介绍类型</td>
                    <td>
                        <select name="t_Relation.totype" data-width="100%" data-toggle="selectpicker">
                        <option value="0" <c:if test="${t_relation.totype == 0}"> selected="selected" </c:if>>请选择</option>
                        <option value="1" <c:if test="${t_relation.totype == 1}"> selected="selected" </c:if>>转出</option>
                        <option value="2" <c:if test="${t_relation.totype == 2}"> selected="selected" </c:if>>转入</option>
                        <option value="3" <c:if test="${t_relation.totype == 3}"> selected="selected" </c:if>>市内</option>
                        </select>
                    </td>
                    <td class="title">落实情况</td>
                    <td>
                        <select name="t_Relation.result" data-width="100%" data-toggle="selectpicker">
                        <option value="1" <c:if test="${t_relation.result == 1}"> selected="selected" </c:if>>未落实</option>
                        <option value="2" <c:if test="${t_relation.result == 2}"> selected="selected" </c:if>>已落实</option>
                        </select>
                    </td>
                </tr>
            </table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn btn-close" data-icon="close">关闭</button></li>
		<li><button type="submit" class="btn btn-default" data-icon="save">保存</button></li>
		
	</ul>
</div>