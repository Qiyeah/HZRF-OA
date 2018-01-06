<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
		<table class="wordInfo" style="width: 98%">
			<tr>
				<td class="title" colspan="4">收文登记</td>
			</tr>
			<tr>
				<td width="15%" class="title">登记人</td>
				<td width="35%" class="normal" align="center">${uname}</td>
				<td width="15%" class="title">登记日期</td>
				<td width="35%" class="normal" align="center">${dc.receivedate}</td>
			</tr>
			<tr>
				<td class="title">来文单位</td>
				<td class="normal" align="center">${dc.unit}</td>
				<td class="title">文 号</td>
				<td class="normal" align="center">${dc.docno}</td>
			</tr>
			<tr>
				<td class="title">单位分类</td>
				<td class="normal" align="center">${dc.type}</td>
				<td class="title">份数</td>
				<td class="normal" align="center">${dc.count}</td>
			</tr>
			<tr>
				<td class="title">文件标题</td>
				<td colspan="3" class="normal" style="font-size:20px;width: 100%;height:40px" align="center">${dc.title}</td>
			</tr>
			<tr>
				<td class="title">文件列表</td>
				<td colspan="3">
					<div id="uploadFile" class="unitBox">
						<c:import url="../../Common/Attachment/view.jsp" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">秘密等级</td>
				<td class="normal" align="center">${dc.security}</td>
				<td class="title">紧急程度</td>
				<td class="normal" align="center"><c:if test="${dc.degree=='0' }">平件</c:if>
				    <c:if test="${dc.degree=='1'}">急件</c:if><c:if test="${dc.degree=='2'}">特急</c:if></td>
			</tr>
			<tr>
				<td class="title">分办科室</td>
				<td class="normal">${receive1}</td>
				<td class="title">备注</td>
				<td class="normal">${dc.memo}</td>
			</tr>
		</table>
	</div>
	<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn btn-close" data-icon="close">关闭</button></li>
	</ul>
</div>

