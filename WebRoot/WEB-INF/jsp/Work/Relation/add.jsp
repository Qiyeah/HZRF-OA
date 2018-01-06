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
	}else {
	    $("#loading").hide();
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
<div id="loading" style="display:none;z-index: 999999;position: absolute;top: 200;left:0;width: 100%;height: 100%;opacity: 0.4;float:left;background: url(<%=basePath%>images/load.gif) center  no-repeat">
</div>
<div class="pageContent">
	<form id="approveForm" action="Main/Relation/add" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, relationJson);">
        <input type="hidden" value="${year}" name="t_Relation.year"/>
		<div class="pageFormContent" layoutH="60">
			<table class="wordInfo" align="center" style="width: 98%">
				<tr>
					<td class="title" colspan="4">中国共产党党员组织关系介绍信</td>
				</tr>
				<tr>
					<td width="15%" class="title">抬头单位</td>
					<td width="35%">
                        <input type="text" name="t_Relation.unit" suggestfields="unit" suggesturl="Main/search/searchUnit" lookupgroup="t_Relation" class="required" style="width: 98%" autocomplete="off">
                    </td>
					<td width="15%" class="title">编　　号</td>
					<td width="35%">
                        <table>
                            <tr>
                                <td style="border: 0px">第</td>
                                <td style="border: 0px" ><input name="t_Relation.no" type="text" value="${no}" class="required" style="width: 98%" /></td>
                                <td style="border: 0px">号</td>
                            </tr>
                        </table>
                    </td>
				</tr>
				<tr>
                    <td class="title">姓　　名</td>
					<td><input name="t_Relation.name" type="text" class="required" style="width: 98%" /></td>
					<td class="title">性　　别</td>
					<td><select name="t_Relation.sex" class="combox">
                        <option value="1">男</option>
                        <option value="2">女</option></select></td>
				</tr>
				<tr>
				    <td class="title">年　　龄</td>
					<td > <input name="t_Relation.age" type="text" style="width: 98%" class="digits"/></td>
					<td class="title">民　　族</td>
					<td><input name="t_Relation.nation" type="text" style="width: 98%" value="汉"/></td>
				</tr>
				<tr>
                    <td class="title">身份证号</td>
                    <td><input name="t_Relation.idcard" type="text" class="required" style="width: 98%" /></td>
                    <td class="title">党员类型</td>
                    <td><select name="t_Relation.type" class="combox">
                        <option value="1">预备</option>
                        <option value="2">正式</option></select></td>
				</tr>
                <tr>
                    <td class="title">转 出 地<br/>（ 单 位 ）<br/>党 组 织</td>
                    <td>
                        <input type="text" name="t_Relation.rfrom" suggestfields="rfrom" suggesturl="Main/search/searchFrom" lookupgroup="t_Relation" class="required" style="width: 98%" autocomplete="off">
                     <%--   <input name="t_Relation.rfrom" type="text" class="required" style="width: 98%" />--%>
                    </td>
                    <td class="title">接 收 地<br/>（ 单 位 ）<br/>党 组 织</td>
                    <td>
                        <input type="text" name="t_Relation.rto" suggestfields="rto" suggesturl="Main/search/searchTo" lookupgroup="t_Relation" class="required" style="width: 98%" autocomplete="off">
          <%--              <input name="t_Relation.rto" type="text" class="required" style="width: 98%" />--%>
                    </td>
                </tr>
                <tr>
                    <td class="title">党费交到</td>
                    <td>
                        <table>
                            <tr>
                                <td style="border: 0px"><select name="t_Relation.paid_year" class="combox">
                                    <c:forEach var="x" begin="1980" end="2030" step="1">
                                        <option value="${x}" <c:if test="${x == year}"> selected="selected" </c:if>>${x}</option>
                                    </c:forEach>
                                </select></td>
                                <td style="border: 0px"> 年 </td>
                                <td style="border: 0px">
                                    <select name="t_Relation.paid_month" class="combox">
                                        <c:forEach var="x" begin="1" end="12" step="1">
                                            <option value="${x}">${x}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table></td>
                    <td class="title">有 效 期</td>
                    <td >
                        <table>
                            <tr>
                                <td style="border: 0px"><input name="t_Relation.valid" type="text" class="digits" style="width: 98%" vale="${valid}"/></td>
                                <td style="border: 0px"> 天 </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td class="title">党　　员<br/>联系电话</td>
                    <td><input name="t_Relation.u_phone" type="text" style="width: 98%" /></td>
                    <td class="title">基层党委<br/>通讯地址</td>
                    <td><input name="t_Relation.u_address" type="text" style="width: 98%" /></td>
                </tr>
                <tr>
                    <td class="title">基层党委<br/>联系电话</td>
                    <td>
                        <input name="t_Relation.phone" type="text" style="width: 98%" />
                    </td>
                    <td class="title">基层党委<br/>传　　真</td>
                    <td>
                        <input name="t_Relation.fax" type="text" style="width: 98%" />
                    </td>
                </tr>
                <tr>
                    <td class="title">基层党委<br/>邮　　编</td>
                    <td>
                        <input name="t_Relation.zipcode" type="text"style="width: 98%" />
                    </td>
                    <td class="title">办理日期</td>
                    <td>
                        <input name="t_Relation.rdate" type="text" class="required date" value="${rdate}" style="width: 98%" />
                    </td>
                </tr>
                <tr>
                    <td class="title">介绍类型</td>
                    <td>
                        <select name="t_Relation.totype" class="combox">
                        <option value="0">请选择</option>
                        <option value="1">转出</option>
                        <option value="2">转入</option>
                        <option value="3">市内</option>
                        </select>
                    </td>
                    <td class="title">落实情况</td>
                    <td>
                        <select name="t_Relation.result" class="combox">
                        <option value="1">未落实</option>
                        <option value="2">已落实</option></select>
                    </td>
                </tr>
			</table>			
		</div>
		<div class="formBar">
			<ul>
				<li>
				    <div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="submitRelation()">保存</button>
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
