<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/JavaScript">
	function hasFill() {
		return true;
	}
	function unitwh() { // 编辑文号
	    //$("#wh").val($("#wh1").val() + $("#wh2").val() + $("#wh3").val() + $("#wh4").val());
	}	
	function BianJiZhengWen() {
		var templateid = $("#templateid").val();
		var title = $("#title").val();
		var docno = $("#wh").val();
		var send1 = $("#send1").val();
		var send2 = $("#send2").val();
		var num = $("#num").val();
		var url = encodeURI("Main/Office/edit?RecordID=${dc.pid}&EditType=1,0&Template=" + templateid + "&Title=" + title + "&Docno=" + docno + "&Send1=" + send1 + "&Send2=" + send2 + "&Num=" + num);		
		$(this).dialog({id:'editword',url:url, title:'正文拟稿', mask:'true',drawable:'true',resizable:'false',maxable:'false',max:'true'});
	}
	function ShenHeZhengWen() {
		var url = "Main/Office/edit?RecordID=${dc.pid}&EditType=2,0";		
		$(this).dialog({id:'editword',url:url, title:'正文核稿', mask:'true',drawable:'true',resizable:'false',maxable:'false',max:'true'});
	}
	function ChaKanZhengWen() {
		var url = "Main/Office/view?RecordID=${dc.pid}&EditType=0,0";	
		$(this).dialog({id:'editword',url:url, title:'查看正文', mask:'true',drawable:'true',resizable:'false',maxable:'false',max:'true'});
	}
</script>
<div class="bjui-pageContent">
	<form id="approveForm" action="Main/Docsend/save" method="post" data-toggle="validate" >
		<div class="pageFormContent" layoutH="65">
			<input type="hidden" name="t_Doc_Send.id" value="${dc.id}" />
			<input type="hidden" name="t_Doc_Send.u_id" id="userid" value="${u_id}" />
			<input type="hidden" name="t_Doc_Send.d_id" value="${d_id}" />
			<input type="hidden" name="t_Doc_Send.opinion1" id="opinion1" value="${dc.opinion1}" />
			<input type="hidden" name="t_Doc_Send.opinion2" id="opinion2" value="${dc.opinion2}" />
			<input type="hidden" name="t_Doc_Send.opinion3" id="opinion3" value="${dc.opinion3}" />
			<input type="hidden" name="t_Doc_Send.opinion4" id="opinion4" value="${dc.opinion4}" />
			<input type="hidden" name="t_Doc_Send.pid" id="pid" value="${dc.pid }" />
			<input type="hidden" name="t_Doc_Send.pstatus" value="${dc.pstatus }" />
			<div style="width: 95%;height: 40px;text-align:center">
		    	<span style="font-size:25px; margin-left:10px"><B>发文呈批笺</B></span>
			</div>
			<table class="wordInfo" align="center" style="width: 98%">				
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">拟稿科室</td>
					<td colspan="3" style="text-align: center;">${startdept}</td>
					<td class="title" style="height: 40px;font-size:15px;">文　号</td>
					<td width="36%"><c:if test="${empty dc.docno}">
					 <div class="noprint">
					 <select id="wh1" name="wh1" data-toggle="selectpicker" onchange="unitwh()">
					     <option value="">请选择</option>
						<c:forEach items="${docnoList}" var="docnoList" >
						<option value="${docnoList.name}">${docnoList.name}</option>
						</c:forEach>
					</select>
					<input id="wh2" name="wh2" value="〔${nowyear}〕" size="10" onchange="unitwh()"/>
					<input id="wh3" name="wh3" value="" size="10" onchange="unitwh()" />
					<select id="wh4" name="wh4" data-toggle="selectpicker" onchange="unitwh()">
						<option value="号">号</option>
						<option value="期">期</option>
					</select>
					</div>
					
					<input type="hidden" name="t_Doc_Send.docno" id="wh" value="${dc.docno }" style="text-align: center;width: 100%;" />
					</c:if>
					<c:if test="${ !empty dc.docno}">
					<input  name="t_Doc_Send.docno" id="wh" value="${dc.docno }" style="text-align: center;width: 100%;" />
					</c:if>
					</td>
					</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">拟稿时间</td>
					<td colspan="3"><input name="t_Doc_Send.approvedate" type="text" value="${dc.approvedate}" class="required" data-toggle="datepicker" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" style="width: 98%" /></td>
					<td class="title" style="height: 40px;font-size:15px;">密　级</td>
					<td><select name="t_Doc_Achieve.security" data-style="selectrequired" data-toggle="selectpicker" data-rule="required" data-msg-required="必选" data-width="100%">
                            <option value="无" <c:if test="${dc.security=='无' }">selected="selected"</c:if>>无</option>
                            <option value="秘密" <c:if test="${dc.security=='秘密' }">selected="selected"</c:if>>秘密</option>
                        </select>
					</td>
				</tr>
				<tr>
					<td width="15%" class="title" style="height: 40px;font-size:15px;">拟稿人</td>
					<td width="18%" colspan="3" style="text-align: center;">${starter}</td>
					<td width="15%" class="title" style="height: 40px;font-size:15px;">份　数</td>
					<td width="18%"><input name="t_Doc_Send.num" id="num" class="number" value="${dc.num }" class="required" type="text" style="text-align: center;width: 92%" /> 份</td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">主　送</td>
					<td colspan="5">
					<input name="sendDept1.send1" id="send1" type="text" class="required" data-rule="required" data-msg-required="必填" suggestFields="send1" suggestUrl="Main/search/send1" lookupGroup="sendDept1" value="${dc.send1}" style="text-align: center;width: 100%" />
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">抄　送</td>
					<td colspan="5">
					<input name="sendDept2.send2" id="send2" type="text"  suggestFields="send2" suggestUrl="Main/search/send2" lookupGroup="sendDept2" value="${dc.send2}" style="text-align: center;width: 100%" />
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 60px;font-size:15px;">文件标题</td>
					<td colspan="5"><input name="t_Doc_Send.title" id="title" type="text" value="${dc.title }" class="required" data-rule="required" data-msg-required="必填" style="text-align: center;font-family: '方正小标宋'; font-size: 20px;height: 40px; font-weight: bold; width: 100%" /></td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">正　文</td>
					<td colspan="5">
						<c:if test="${wa.editword == '1' && wa.atype == '1'}">
							<table>
								<tr>
								    <td style="border: 0px">
								    <a class="btn btn-blue" data-icon="edit" onclick="BianJiZhengWen()" style="margin-left: 5px;width: 200px;"><span>&nbsp;&nbsp;正&nbsp;&nbsp;文&nbsp;&nbsp;拟&nbsp;&nbsp;稿&nbsp;&nbsp;</span></a>
								    </td>									
								</tr>
							</table>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="title">附件列表</td>
					<td colspan="5"><div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/edit.jsp" /></div></td>
				</tr>
				<tr>
					<td class="title"><br>批办意见<br><br>及<br><br>领导批示<br></td><td colspan="6"><div id="opinion1_1">${opinion1 }</div><c:if test="${wa.haveopinionfield == '1'}">
						<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<a class="btn btn-blue" data-icon="fa-check"  onclick="FaSong()" style="margin-left: 5px;">提交</a>
					</div>
					</c:if>
					</td>
				</tr>
				<%-- <c:if test="${d_id != '2'}">
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
							<a class="btn btn-blue" data-icon="check" style="margin-left: 5px;"><span>提交</span></a>
						</c:if>						
					</c:if>	
					</td>
				</tr>
				
				</c:if>
				<tr>
					<td class="title" style="height: 140px;font-size:15px">分　管<br>副部长<br>批　示</td>
					<td colspan="5"><div id="opinion3_1">${dc.opinion3 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion3'}">
						<br/><br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;"><span>填写意见</span></a>
						<c:if test="${wa.atype != '3'}">
							<a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;"><span>提交</span></a>
						</c:if>
					</c:if>	
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 140px;font-size:15px">部　长<br>批　示</td>
					<td colspan="5"><div id="opinion4_1">${dc.opinion4 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion4'}">
						<br/><br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;"><span>填写意见</span></a>
						<c:if test="${wa.atype != '3'}">
							<a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;"><span>提交</span></a>
						</c:if>
					</c:if>
					</td>
				</tr> --%>
			</table>
			<input type="hidden" name="positionid" id="positionid" value="${positionid }" />
			<input type="hidden" name="t_Workflow.id" value="${wf.id}" />
			<input type="hidden" name="t_Workflow.flowname" value="${wf.flowname}" />
			<input type="hidden" name="t_Workflow.workpath" value="${wf.workpath}" />
			<input type="hidden" name="t_Workflow.flowform" value="${wf.flowform}" />
			<input type="hidden" name="t_Workflow.formname" value="${wf.formname}" />
			<input type="hidden" name="t_Workflow.title" id="title" value="${wf.title}" />
			<input type="hidden" name="t_Workflow.starter" id="starter" value="${wf.starter}" />
			<input type="hidden" name="t_Workflow.startdept" id="startdept" value="${wf.startdept}" />
			<input type="hidden" name="t_Workflow.starttime" value="${wf.starttime}" />
			<input type="hidden" name="t_Workflow.reader" value="${wf.reader}" />
			<input type="hidden" name="t_Workflow.todoman" value="${wf.todoman}" />
			<input type="hidden" name="t_Workflow.doneuser" value="${wf.doneuser}" />
			<input type="hidden" name="t_Workflow.todousers" value="${wf.todousers}" />
			<input type="hidden" name="t_Workflow.isopen" value="${wf.isopen}" />
			<input type="hidden" name="t_Workflow.editor" value="${wf.editor}" />
			<input type="hidden" name="t_Workflow.mainflowid" value="${wf.mainflowid}" />
			<input type="hidden" name="t_Workflow.subflowid" value="${wf.subflowid}" />
			<input type="hidden" name="t_Workflow.subflowname" value="${wf.subflowname}" />
			<input type="hidden" name="t_Workflow.isend" value="${wf.isend}" />
			<input type="hidden" name="t_Workflow.isnormalend" value="${wf.isnormalend}" />
			<input type="hidden" name="t_Workflow.ishold" value="${wf.ishold}" />
			<input type="hidden" name="t_Workflow.islock" value="${wf.islock}" />
			<input type="hidden" name="t_Workflow.isnewdoc" value="0" />
			<input type="hidden" name="t_Workflow.itemid" id="itemid" value="${wf.itemid}" />
			<input type="hidden" name="t_Workflow.hastemplate" value="${wf.hastemplate}" />
			<input type="hidden" name="t_Workflow.templatename" value="${wf.templatename}" />
			<input type="hidden" name="t_Workflow.worddocname" value="${wf.worddocname}" />
			<input type="hidden" name="t_Workflow.bodyiscreated" value="${wf.bodyiscreaded}" />
			<input type="hidden" name="t_Workflow.bodyauthor" value="${wf.bodyauthor}" />
			<input type="hidden" name="t_Workflow.bodyversion" value="${wf.bodyversion}" />			
			<input type="hidden" name="todomannum" value="${todomannum}" />
			<input type="hidden" name="issequence" value="${wa.issequence}" />
			<input type="hidden" name="havesubflow" value="${wa.havesubflow}" />
			<input type="hidden" name="editword" value="${wa.editword}" />
			<input type="hidden" name="viewword" value="${wa.viewword}" />
			<input type="hidden" name="endprocess" value="${wa.endprocess}" />
			<input type="hidden" name="specialsend" value="${wa.specialsend}" />
			<input type="hidden" name="backlaststep" value="${wa.backlaststep}" />
			<input type="hidden" name="backfirststep" value="${wa.backfirststep}" />
			<input type="hidden" name="haveopinionfield" id="haveopinionfield" value="${wa.haveopinionfield}" />
			<input type="hidden" name="opinionfield" id="opinionfield" value="${wa.opinionfield}" />
			<input type="hidden" name="opinionname" id="opinionname" value="${opinionname}" />
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
			<hr>
			<%-- <table class="wordInfo" align="center" style="width: 98%">
				<tr>
					<td width="15%" class="title">环节名称</td>
					<td width="10%" class="title">处理人</td>
					<td width="15%" class="title">开始时间</td>
					<td width="10%" class="title">处理操作</td>
					<td width="15%" class="title">结束时间</td>
					<td width="35%" class="title">处理意见</td>
				</tr>
				<c:forEach items="${list}" var="temp">
					<tr align="center">
						<td>${activeHM.get(temp.itemid1)}</td>
						<td>${userHM.get(temp.user1)}</td>
						<td>${fn:substring(temp.begintime,0,16)}</td>
						<td>${operationHM.get(temp.operation)}</td>
						<td>${fn:substring(temp.endtime,0,16)}</td>
						<td>${temp.opinion}</td>
					</tr>
				</c:forEach>
			</table> --%>
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
			<ul>
                <c:if test="${wkit1 != null}"><li><a class="btn btn-default" data-icon="fa-rotate-right" onclick="QuHui()"><span>取回</span></a></li></c:if>
				<c:if test="${wa.editword == '1' && wa.atype == '1'}"></li>
					<li><a class="btn btn-blue" data-icon="fa-edit" onclick="BianJiZhengWen()"><span>拟稿</span></a>
				</c:if>
                <c:if test="${wf.editor == userid && wf.ishold == '0'}">
                    <c:if test="${wa.handround == '1'}">
                        <li><a class="btn btn-default" data-icon="fa-check" onclick="TongYi()"><span>已阅</span></a></li>
                    </c:if>
                    <c:if test="${wa.haveopinionfield == '1'}">
                        <li><a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()"><span>填写意见</span></a></li>
                    </c:if>
                    <c:if test="${wa.atype != '1'}">
                        <c:if test="${wa.endprocess == '1'}">
                            <li><a class="btn btn-close" data-icon="fa-times-circle-o" onclick="ZhongZhi()"><span>终止</span></a></li>
                        </c:if>
                        <c:if test="${wa.backlaststep == '1'}">
                            <li><a class="btn btn-default" data-icon="fa-share-square" onclick="TuiHuiShangBu()"><span>退回</span></a></li>
                        </c:if>
                        <c:if test="${wa.backfirststep == '1'}">
                            <li><a class="btn btn-default" data-icon="fa-share-square" onclick="TuiHuiNiGao()"><span>退回经办人</span></a></li>
                        </c:if>
                    </c:if>
                    <c:if test="${wa.specialsend == '1'}">
                        <li><a class="btn btn-default" data-icon="fa-plane" onclick="TeSong()"><span>特送</span></a></li>
                    </c:if>
                    <c:if test="${todomannum > 1}">
                        <li><a class="btn btn-blue" data-icon="check" onclick="ChuLiWanBi()"><span>提交</span></a></li>
                    </c:if>
                    <c:if test="${canedit || wa.haveopinionfield == '1'}">
						<li><a class="btn btn-default" data-icon="save" onclick="BaoCunTuiChu()"><span>保存</span></a></li>
					</c:if>
                    <c:if test="${todomannum <= 1}">
                        <c:if test="${wa.atype == '3'}">
                            <li><a class="btn btn-default" data-icon="save" onclick="WanCheng()"><span>完成</span></a></li>
                        </c:if>
                        <c:if test="${wa.atype != '3'}">
                            <li><a class="btn btn-blue" data-icon="check" onclick="FaSong()"><span>提交</span></a></li>
                        </c:if>
                    </c:if>
					
				</c:if>
			</ul>
		</div>