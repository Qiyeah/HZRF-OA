<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="32">
		<div style="height: 350px; width: 436px; margin: 0 auto;">
			<div style="float: left; display: block; margin: 5px; overflow: auto; width: 200px; height: 100%; border: solid 1px #4CA5D8;">
				<ul class="tree treeFolder treeCheck expand" oncheck="onCheck">
					<c:forEach items="${deparments}" var="deparment">
					 	<li><a tname="id" tvalue="${deparment.id}" <c:if test="${! empty checkDepts }">
					 		<c:forEach items="${checkDepts}" var="checkDept" <c:if test="${checkDept.id==deparment.id }">checked</c:if>></c:forEach>
					 		</c:if>${deparment.fname} ></a></li>
					</c:forEach>
				</ul>
			</div>
			<div style="float: left; height: 100%; line-height: 340px;">
				<img src="<%=basePath%>images/arrow_left.gif" border="0" />
			</div>
			<div style="float: left; display: block; margin: 5px; overflow: auto; width: 200px; height: 100%; border: solid 1px #4CA5D8;">
				<table class="list" width="100%">
					<thead>
						<tr>
							<th align="center">科室</th>
						</tr>
					</thead>
					<tbody id="deptBox" align="center">
						<c:if test="${not empty checkDepts }">
							<c:forEach items="${checkDepts }" var="checkDept">
								<tr><td><div style="display:none"><input type='checkbox' checked='checked' name='${checkDept.fname }' value="{id:'${checkDept.id }', name:'${checkDept.fname }'}"/></div>${checkUser.fname }</td></tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive">
					<div class="buttonContent">
						<button type="button" multLookup="id" warn="noMessageUser">确定</button>
					</div>
				</div></li>
			<li><div class="button">
					<div class="buttonContent">
						<button type="button" class="close">取消</button>
					</div>
				</div></li>
		</ul>
	</div>
</div>
<iframe src="javascript:false"
	style="position: absolute; visibility: inherit; top: 0px; left: 0px; width: 100%; height: 100%; z-index: -1; filter ='progid: DXImageTransform.Microsoft.Alpha(style=0, opacity=0 )';"></iframe>

<script type="text/javascript">
	function onCheck() {
		var result = "";
		$("#deptBox").html("");
		$("input[type='checkbox']").each(
				function(i) {
					if (this.checked) { //&&this.name
						if (this.name != 'ids') {
							//alert(this.alt);
							result += "<tr><td><div style='display:none'><input type='checkbox' checked='checked' name='"+this.name+"' value=\"{id:'"+this.value+"', name:'"+this.alt+"'}\"/></div>"
									+ this.alt + "</td></tr>";
						}
					}
				});
		$("#deptBox").html(result);
	}
</script>