<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent">
	<form method="post" action="Main/employee/update" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<input type="hidden" name="t_Employee.id" value="${employee.id }"/>
		<input type="hidden" name="oldHeadIcon" value="${employee.headicon }"/>
		<div class="pageFormContent" layoutH="56">
			<br/><center><b style="font-size: 30;">个人基本信息</b></center><br/><br/>
			<table class="wordInfo" align="center" >
				<tr>
					<td align="center"><b>姓名</b></td>
					<td ><input name="t_Employee.name" type="text" value="${employee.name }"/></td>
					<td align="center"><b>性别</b></td>
					<td >
						<input type="radio" name="t_Employee.sex" <c:if test="${employee.sex==1 }">checked</c:if> value="1"/>男
						<input type="radio" name="t_Employee.sex" <c:if test="${employee.sex==0 }">checked</c:if> value="0"/>女
					</td>
					<td style="width:100px;" rowspan="4"><img style="width:100px; height:123px;" src="File/Employee/HeadIcon/${employee.headicon }"/></td>
				</tr>
				<tr>
					<td align="center"><b>年龄</b></td>
					<td><input name="t_Employee.age" type="text" value="${employee.age }"/></td>
					<td align="center"><b>出生年月</b></td>
					<td ><input name="t_Employee.brithday" class="date" readonly value="${employee.brithday }"/></td>
				</tr>
				<tr>
					<td  align="center"><b>籍贯</b></td>
					<td ><input name="t_Employee.brithplace" value="${employee.brithplace }"/></td>
					<td align="center"><b>文化程度</b></td>
					<td >
						<select name="t_Employee.education">
							<option <c:if test="${employee.education=='小学' }">selected</c:if>>小学</option>
							<option <c:if test="${employee.education=='初中' }">selected</c:if>>初中</option>
							<option <c:if test="${employee.education=='高中' }">selected</c:if>>高中</option>
							<option <c:if test="${employee.education=='中专' }">selected</c:if>>中专</option>
							<option <c:if test="${employee.education=='大专' }">selected</c:if>>大专</option>
							<option <c:if test="${employee.education=='本科' }">selected</c:if>>本科</option>
							<option <c:if test="${employee.education=='硕士' }">selected</c:if>>硕士</option>
							<option <c:if test="${employee.education=='博士' }">selected</c:if>>博士</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><b>毕业学校</b></td>
					<td colspan="3"><input name="t_Employee.school" size="55" value="${employee.school }"/></td>
				</tr>
				<tr>
					<td align="center"><b>毕业时间</b></td>
					<td><input name="t_Employee.graduation" type="text" class="date" readonly value="${employee.graduation }"/></td>
					<td colspan="3">&nbsp;<b>修改相片：</b><input type="file" name="headIcon"/></td>
				</tr>
				<tr>
					<td align="center"><b>岗位</b></td>
					<td ><input name="t_Employee.job" type="text" value="${employee.job }" /></td>
					<td  align="center"><b>所学专业</b></td>
					<td colspan="2"><input name="t_Employee.major" size="35" type="text" value="${employee.major }"/></td>
				</tr>
				<tr>
					<td align="center"><b>从事本专业时间</b></td>
					<td ><input name="t_Employee.majortime" class="date" readonly value="${employee.majortime }"/></td>
					<td align="center"><b>所在科室</b></td>
					<td colspan="2">
						<input name="t_Employee.department" type="text" suggestFields="department" suggestUrl="Main/search/searchDepartment" lookupGroup="t_Employee" value="${employee.department }"/>
					</td>
				</tr>
				<tr>
					<td align="center"><b>入单位时间</b></td>
					<td ><input name="t_Employee.indate" type="text" class="date" readonly value="${employee.indate }"/></td>
					<td align="center"><b>职务/职称</b></td>
					<td colspan="2"><input name="t_Employee.duty" value="${employee.duty }"/></td>
				</tr>
				<tr>
					<td align="center"><b>技术资质</b></td>
					<td colspan="4"><input name="t_Employee.technology" size="70" type="text" value="${employee.technology }"/></td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
