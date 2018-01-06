<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageContent">
	<form method="post" action="Main/employee/add" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<br/><center><b style="font-size: 30;">个人基本信息</b></center><br/><br/>
			<table class="wordInfo" align="center" >
				<tr>
					<td align="center"><b>姓名</b></td>
					<td ><input name="t_Employee.name" type="text" /></td>
					<td align="center"><b>性别</b></td>
					<td >
						<input type="radio" name="t_Employee.sex" checked="checked" value="1"/>男
						<input type="radio" name="t_Employee.sex" value="0"/>女
					</td>
					<td style="width:100px;" rowspan="4"><img style="width:100px; height:123px;" src="File/Employee/HeadIcon/default.jpg"/></td>
				</tr>
				<tr>
					<td align="center"><b>年龄</b></td>
					<td><input name="t_Employee.age" type="text" /></td>
					<td align="center"><b>出生年月</b></td>
					<td ><input name="t_Employee.brithday" class="date" readonly/></td>
				</tr>
				<tr>
					<td  align="center"><b>籍贯</b></td>
					<td ><input name="t_Employee.brithplace" /></td>
					<td align="center"><b>文化程度</b></td>
					<td >
						<select name="t_Employee.education">
							<option>小学</option>
							<option>初中</option>
							<option>高中</option>
							<option>中专</option>
							<option>大专</option>
							<option selected>本科</option>
							<option>硕士</option>
							<option>博士</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><b>毕业学校</b></td>
					<td colspan="3"><input size="55" name="t_Employee.school" /></td>
				</tr>
				<tr>
					<td align="center"><b>毕业时间</b></td>
					<td><input name="t_Employee.graduation" type="text" class="date" readonly /></td>
					<td colspan="3">&nbsp;<b>上传相片：</b><input type="file" name="headIcon"/></td>
				</tr>
				<tr>
					<td align="center"><b>岗位</b></td>
					<td ><input name="t_Employee.job" type="text" /></td>
					<td  align="center"><b>所学专业</b></td>
					<td colspan="2"><input name="t_Employee.major" size="35" type="text" /></td>
				</tr>
				<tr>
					<td align="center"><b>从事本专业时间</b></td>
					<td ><input name="t_Employee.majortime" class="date" readonly/></td>
					<td align="center"><b>所在科室</b></td>
					<td colspan="2">
						<input name="t_Employee.department" type="text" suggestFields="department" suggestUrl="Main/search/searchDepartment" lookupGroup="t_Employee"/>
					</td>
				</tr>
				<tr>
					<td align="center"><b>入单位时间</b></td>
					<td ><input name="t_Employee.indate" type="text" class="date" readonly/></td>
					<td align="center"><b>职务/职称</b></td>
					<td colspan="2"><input name="t_Employee.duty" /></td>
				</tr>
				<tr>
					<td align="center"><b>技术资质</b></td>
					<td colspan="4"><input name="t_Employee.technology" size="70" type="text" /></td>
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
