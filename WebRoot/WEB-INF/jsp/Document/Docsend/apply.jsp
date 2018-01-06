<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	function hasFill() {
		return true;
	}
	function unitwh() { // 编辑文号
	    $("#wh").val($("#wh1").val() + $("#wh2").val() + $("#wh3").val() + $("#wh4").val());
	}
	function BianJiZhengWen() {
		var templateid = $("#templateid").val();
		var title = $("#title").val();
		var docno = $("#wh").val();
		var send1 = $("#send1").val();
		var send2 = $("#send2").val();
		var num = $("#num").val();
		var url = encodeURI("Main/Office/edit?RecordID=${tempid}&EditType=1,0&Template=" + templateid + "&Title=" + title + "&Docno=" + docno + "&Send1=" + send1 + "&Send2=" + send2 + "&Num=" + num);		
		$(this).dialog({id:'editword',url:url, title:'正文拟稿', mask:'true',drawable:'true',resizable:'false',maxable:'true',max:'true'});
	}
</script>
<div class="bjui-pageContent">
	  <form id="approveForm" action="Main/Docsend/save" method="post" data-toggle="validate" >
		<div class="pageFormContent" layoutH="25">
			<input type="hidden" name="t_Doc_Send.id" value="0" />
			<input type="hidden" name="t_Doc_Send.u_id" id="userid" value="${u_id}" />
			<input type="hidden" name="t_Doc_Send.d_id" value="${d_id}" />
			<input type="hidden" name="t_Doc_Send.pid" id="pid" value="0" />
			<input type="hidden" name="t_Doc_Send.pstatus" value="0" />
			<input type="hidden" name="tempid" value="${tempid}" />
			<input type="hidden" name="flag" value="0" />			
			
			<div style="width: 95%;height: 40px;text-align:center">
		    	<span style="font-size:25px; margin-left:10px"><B>发文呈批笺</B></span>
			</div>
			<table class="wordInfo" align="center" style="width: 98%;margin-bottom:23px">				
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">拟稿科室</td>
					<td colspan="3" style="text-align: center;">${dname}</td>
					<td class="title" style="height: 40px;font-size:15px;">文　号</td>
					<td width="36%"><select id="wh1" name="wh1" data-toggle="selectpicker" onchange="unitwh()">
					    <option value="">请选择</option>
						<c:forEach items="${docnoList}" var="docnoList" >
						<option value="${docnoList.name}">${docnoList.name}</option>
						</c:forEach>
					</select>
					<input id="wh2" name="wh2" value="〔${nowyear}〕" size="10" onchange="unitwh()" />
					<input id="wh3" name="wh3" value="" size="10" onchange="unitwh()" />
					<select id="wh4" name="wh4" data-toggle="selectpicker" onchange="unitwh()">
						<option value="号">号</option>
						<option value="期">期</option>
					</select>
					 
					<input type="hidden"  id="wh"  name="t_Doc_Send.docno"/>
					<input type="hidden"  id="templateid"  value="${templateid}"/>
					
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">拟稿时间</td>
					<td colspan="3"><input name="t_Doc_Send.approvedate" type="text" value="${nowday}" class="required" data-toggle="datepicker" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" style="width: 100%" /></td>
					<td class="title" style="height: 40px;font-size:15px;">密　级</td>
					<td ><select name="t_Doc_Send.security" data-style="selectrequired" style="width:50%" data-toggle="selectpicker" data-rule="required" data-msg-required="必选" data-width="100%">
                            <option value="">---请选择---</option>
                            <option value="无" <c:if test="${dc.security=='无' }">selected="selected"</c:if>>无</option>
                            <option value="秘密" <c:if test="${dc.security=='秘密' }">selected="selected"</c:if>>秘密</option>
                        </select>
					</td>
				</tr>
				<tr>
					<td width="15%" class="title" style="height: 40px;font-size:15px;">拟稿人</td>
					<td width="18%" colspan="3" style="text-align: center;">${uname}</td>
					<td width="15%" class="title" style="height: 40px;font-size:15px;">份　数</td>
					<td width="18%"><input name="t_Doc_Send.num" id="num" style="text-align: center;width:92%" class="number" type="text" class="required" /> 份</td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">主　送</td>
					<td colspan="5">
					<input name="sendDept1.send1" id="send1" type="text" class="required" data-rule="required" data-msg-required="必填" suggestFields="send1" suggestUrl="Main/search/send1" lookupGroup="sendDept1" value="${t_Doc_Send.send1}" style="text-align: center;width: 100%" />
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">抄　送</td>
					<td colspan="5">
					<input name="sendDept2.send2" id="send2" type="text" suggestFields="send2" suggestUrl="Main/search/send2" lookupGroup="sendDept2" value="${t_Doc_Send.send2}" style="text-align: center;width: 100%" />
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 60px;font-size:15px;">文件标题</td>
					<td colspan="5"><input name="t_Doc_Send.title" id="title" type="text" class="required" data-rule="required" data-msg-required="必填" style="font-family: '方正小标宋'; font-size: 18px; font-weight: bold; width: 100%;height: 25px;text-align: center;" /></td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">正　文</td>
					<td colspan="5">
						<c:if test="${wa.editword == '1' && wa.atype == '1'}">
							<table>
								<tr>
								    <td style="border: 0px" colspan='5'>
								    <a class="btn btn-blue" data-icon="edit" onclick="BianJiZhengWen()" style="margin-left: 100px;width: 200px;"><span>&nbsp;&nbsp;正&nbsp;&nbsp;文&nbsp;&nbsp;拟&nbsp;&nbsp;稿 &nbsp;&nbsp;</span></a>
								    </td>									
								</tr>
							</table>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="title">附件列表</td>
					<td colspan="5">
						<div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/add.jsp" /></div>
					</td>
				</tr>
				<tr>
					<td class="title"><br>批办意见<br><br>及<br><br>领导批示<br></td><td colspan="6"><div id="opinion1_1"><span style="color:red">请先点击“暂存”后才能填写意见！</span></div><c:if test="${wa.haveopinionfield == '1'}">
						<div class="noprint">
						<br/>
						<!-- <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<a class="btn btn-blue" data-icon="fa-check"  onclick="FaSong()" style="margin-left: 5px;">提交</a> -->
					</div>
					</c:if>
					</td>
				</tr>
				<%--<c:if test="${d_id != '2'}">
				 <tr>
					<td class="title" style="height: 140px;font-size:15px" >科　室<br>意　见</td>
					<td colspan="5"><div id="opinion1_1">${dc.opinion1 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion1'}">
						<br/><br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;"><span>填写意见</span></a>
						<c:if test="${wa.atype != '3'}">
							<a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;"><span>提交</span></a>
						</c:if>						
					</c:if>	
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 140px;font-size:15px">办公室<br>意　见</td>
					<td colspan="5"><div id="opinion2_1">${dc.opinion2 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion2'}">
						<br/><br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;"><span>填写意见</span></a>
						<c:if test="${wa.atype != '3'}">
							<a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;"><span>提交</span></a>
						</c:if>						
					</c:if>	
					</td>
				</tr>
				</c:if>
				<c:if test="${d_id == '2'}">
				<tr>
					<td class="title" style="height: 140px;font-size:15px" >办公室<br>意　见</td>
					<td colspan="5"><div id="opinion1_1">${dc.opinion1 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion1'}">
						<br/><br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;"><span>填写意见</span></a>
						<c:if test="${wa.atype != '3'}">
							<a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;"><span>提交</span></a>
						</c:if>						
					</c:if>	
					</td>
				</tr>
				
				</c:if>
				<tr>
					<td class="title" style="height: 140px;font-size:15px">分　管<br>副部长<br>批　示</td>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr>
					<td class="title" style="height: 140px;font-size:15px">部　长<br>批　示</td>
					<td colspan="5">&nbsp;</td>
				</tr> --%>
			</table>
			<input type="hidden" name="positionid" id="positionid" value="${positionid }" />
			<input type="hidden" name="t_Workflow.id" id="id" value="0" />
			<input type="hidden" name="t_Workflow.flowname" id="flowname" value="${wp.name}" />
			<input type="hidden" name="t_Workflow.workpath" value="${wp.path}" />
			<input type="hidden" name="t_Workflow.flowform" value="${wp.flow}" />
			<input type="hidden" name="t_Workflow.formname" value="发文处理单" />
			<input type="hidden" name="t_Workflow.title" value="${wp.name}" />
			<input type="hidden" name="t_Workflow.starter" id="starter" value="${u_id}" />
			<input type="hidden" name="t_Workflow.startdept" value="${d_id}" />
			<input type="hidden" name="t_Workflow.starttime" value="" />
			<input type="hidden" name="t_Workflow.reader" value="" />
			<input type="hidden" name="t_Workflow.todoman" value="${u_id}" />
			<input type="hidden" name="t_Workflow.doneuser" value="" />
			<input type="hidden" name="t_Workflow.todousers" value="" />
			<input type="hidden" name="t_Workflow.isopen" value="1" />
			<input type="hidden" name="t_Workflow.editor" value="${u_id}" />
			<input type="hidden" name="t_Workflow.mainflowid" value="" />
			<input type="hidden" name="t_Workflow.subflowid" value="" />
			<input type="hidden" name="t_Workflow.subflowname" value="${wa.subflow}" />
			<input type="hidden" name="t_Workflow.isend" value="0" />
			<input type="hidden" name="t_Workflow.isnormalend" value="0" />
			<input type="hidden" name="t_Workflow.ishold" value="0" />
			<input type="hidden" name="t_Workflow.islock" value="0" />
			<input type="hidden" name="t_Workflow.isnewdoc" value="1" />
			<input type="hidden" name="t_Workflow.itemid" id="itemid" value="${itemid}" />
			<input type="hidden" name="t_Workflow.hastemplate" value="${wp.model}" />
			<input type="hidden" name="t_Workflow.templatename" value="${wp.template}" />
			<input type="hidden" name="t_Workflow.worddocname" value="" />
			<input type="hidden" name="t_Workflow.bodyiscreated" value="0" />
			<input type="hidden" name="t_Workflow.bodyauthor" value="" />
			<input type="hidden" name="t_Workflow.bodyversion" value="" />			
			<input type="hidden" name="todomannum" value="${todomannum}" />
			<input type="hidden" name="issequence" value="0" />
			<input type="hidden" name="havesubflow" value="${wa.havesubflow}" />
			<input type="hidden" name="editword" value="${wa.editword}" />
			<input type="hidden" name="viewword" value="${wa.viewword}" />
			<input type="hidden" name="endprocess" value="${wa.endprocess}" />
			<input type="hidden" name="specialsend" value="${wa.specialsend}" />
			<input type="hidden" name="backlaststep" value="${wa.backlaststep}" />
			<input type="hidden" name="backfirststep" value="${wa.backfirststep}" />
			<input type="hidden" name="haveopinionfield" id="haveopinionfield" value="${wa.haveopinionfield}" />
			<input type="hidden" name="opinionfield" id="opinionfield" value="${wa.opinionfield}" />
			<input type="hidden" name="opinion" id="opinion" value="" />
			<input type="hidden" name="opiniontime" id="opiniontime" value="" />
			<input type="hidden" name="operation" id="operation" value="" />
			<input type="hidden" name="wid" id="wid" value="${wp.id}" />
			<input type="hidden" name="nexttodoman" id="nexttodoman" value="" />
			<input type="hidden" name="nextitemid" id="nextitemid" value="${nextitemid}" />
			<input type="hidden" name="nextstepsnum" id="nextstepsnum" value="${nextstepsnum}" />
			<input type="hidden" name="sendsms" id="sendsms" value="0" />
			<input type="hidden" name="sendemail" id="sendemail" value="0" />
			<input type="hidden" name="curuser" id="curuser" value="${u_id}" />
			<input type="hidden" name="curdept" id="curdept" value="${d_id}" />
			<input type="hidden" name="localarea" value="${localarea}" />
			<input type="hidden" name="seldept" id="seldept" value="${d_id}" />
			<input type="hidden" name="curuser1" id="curuser1" value="${uname}" />
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>																						
		<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<c:if test="${wa.specialsend == '1'}">
		<li><button type="button" class="btn-default" onclick="TeSong()" data-icon="fa-plane">特送</button></li>
		</c:if>
		<li><button type="button" class="btn-default" onclick="ZanCun()" data-icon="save">暂存</button></li>
		<!-- <li><button type="button" class="btn btn-blue" onclick="FaSong()" data-icon="check">提交</button></li> -->
	</ul>
</div>