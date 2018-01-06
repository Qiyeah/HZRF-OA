<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<style>
    table.wordInfo td{
        height:40px;
        font-size:15px;
    }
    table.wordInfo td input[type=text]{
        height:20px;
        font-size:15px;
    }
</style>
<div class="pageContent">
	<div id="leave_print" class="pageFormContent" layoutH="60">
			<table class="wordInfo" align="center" style="width: 98%">
				<tr>
					<td class="title" colspan="4">会议申请表</td>
				</tr>
				<tr>
					<td width="15%" class="title">申 请 人</td>
					<td width="35%">&nbsp;${starter}</td>
					<td width="15%" class="title">申请科室</td>
					<td width="35%">&nbsp;${startdept}</td>
				</tr>
				<tr>
					<td class="title">申请日期</td>
					<td>&nbsp;${mt.approvedate }</td>
					<td class="title">会议时间</td>
					<td>&nbsp;${mt.mdate}${mt.hour}${mt.minute}</td>
				</tr>
				<tr>
					<td class="title">地　　点</td>
					<td colspan="3">&nbsp;${mt.address }</td>
				</tr>
				<tr>
					<td class="title">主 持 人</td>
					<td>&nbsp;${chairman }</td>
					<td class="title">类　　型</td>
					<td>&nbsp;${mt.type }</td>
				</tr>
				<tr>
                    <td class="title">参加人员</td>
					<td colspan="3">&nbsp;${attendee }</td>
				</tr>
				<tr>
					<td class="title">标　　题</td>
					<td colspan="3">&nbsp;${mt.title }</td>
				</tr>
				<tr>
					<td class="title">内　　容</td>
					<td colspan="3">&nbsp;${mt.content }</td>
				</tr>
				<tr>
					<td class="title">附件列表</td>
					<td colspan="3">
						<div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/view.jsp" /></div>
					</td>
				</tr>
				<tr>
                    <td class="title" style="height: 60px">科　　室<br/>意　　见</td>
					<td colspan="3"><div id="opinion1_1">${opinion1 }</div></td>
				</tr>
				<tr>
                    <td class="title" style="height: 60px">分　　管<br/>部 领 导<br/>意　　见</td>
					<td colspan="3"><div id="opinion2_1">${opinion2 }</div></td>
				</tr>
				<tr>
                    <td class="title" style="height: 60px">部　　长<br/>批　　示</td>
					<td colspan="3"><div id="opinion3_1">${opinion3 }</div></td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
</div>
