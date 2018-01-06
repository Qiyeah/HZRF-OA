<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<script type="text/javascript" src="js/Newcalendar.js"></script>
<link href="css/wnl.css" rel="stylesheet" type="text/css" />
<div class="bjui-pageContent">
	<div style="margin-top: 0px; overflow: hidden;">
		<div class="row" style="padding: 0 8px;">
			<div class="col-md-5">
				<div class="panel panel-default" style="margin-bottom: 15px;">
					<div class="panel-heading" style="padding: 2px 3px;">
						<div class="panel-title">
							<i class="fa fa-calendar fa-fw"></i> <b>工作提醒</b>
						</div>
					</div>
					<div class="panel-body" id="remainder" style="padding: 0px; height: 200px;">
						<table style="width: 100%">
							<tr align="center" style="height: 100px; top: 5px">
								<td><div style="position: relative; width: 90px">
										<c:choose>
											<c:when test="${pert:hasperts(applicationScope.Mail, loginModel.limit)}">
												<a href="Main/Mail/main" data-toggle="navtab" data-id="Mail"><img src="${basePath }images/mailbox.png" /><br>内部邮件</a>
												<c:if test="${mailboxnum>0}">
													<i class="num">${mailboxnum}</i>
												</c:if>
											</c:when>
											<c:otherwise>
												<img src="${basePath }images/mailbox1.png" />
												<br>内部邮件</c:otherwise>
										</c:choose>
									</div></td>
								<td><div style="position: relative; width: 90px">
										<c:choose>
											<c:when test="${pert:hasperts(applicationScope.Announce, loginModel.limit)}">
												<a href="Main/Announce/main" data-toggle="navtab" data-id="Announce"><img src="${basePath }images/notice.png" /><br>公告通知</a>
												<c:if test="${noticenum>0}">
													<i class="num">${noticenum}</i>
												</c:if>
											</c:when>
											<c:otherwise>
												<img src="${basePath }images/notice1.png" />
												<br>公告通知</c:otherwise>
										</c:choose>
									</div></td>
								<%-- <td><div style="position: relative; width: 100px">
										<c:choose>
											<c:when test="${pert:hasperts(applicationScope.receive_super, loginModel.limit)}">
												<a href="Main/Docreceive/superlist" data-toggle="navtab" data-id="receive_super"><img src="${basePath }images/superdo.png" /><br>收文督办</a>
												<c:if test="${superdonum>0}">
													<i class="num">${superdonum}</i>
												</c:if>
											</c:when>
											<c:otherwise>
												<img src="${basePath }images/superdo1.png" />
												<br>收文督办</c:otherwise>
										</c:choose>
									</div></td> --%>
									<td><div style="position: relative; width: 100px">
                                        <c:choose>
                                            <c:when test="${pert:hasperts(applicationScope.achieve, loginModel.limit)}">
                                                <a href="Main/Docachieve/achievelist" data-toggle="navtab" data-id="achieve"><img src="${basePath }images/superdo.png" /><br>初审分办</a>
                                                <c:if test="${achievenum>0}">
                                                    <i class="num">${achievenum}</i>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${basePath }images/superdo1.png" />
                                                <br>初审分办</c:otherwise>
                                        </c:choose>
                                    </div></td>
								<td><div style="position: relative; width: 100px">
										<c:choose>
											<c:when test="${pert:hasperts(applicationScope.receive_todo1, loginModel.limit)}">
												<a href="Main/Docreceive/todolist1" data-toggle="navtab" data-id="receive_todo1"><img src="${basePath }images/read.png" /><br>待阅文件</a>
												<c:if test="${readnum>0}">
													<i class="num">${readnum}</i>
												</c:if>
											</c:when>
											<c:otherwise>
												<img src="${basePath }images/read1.png" />
												<br>待阅文件</c:otherwise>
										</c:choose>
									</div></td>
							</tr>
							<tr align="center" style="height: 100px; top: 5px">
								<td><div style="position: relative; width: 100px">
										<c:choose>
											<c:when test="${pert:hasperts(applicationScope.receive_todo, loginModel.limit)}">
												<a href="Main/Docreceive/todolist" data-toggle="navtab" data-id="receive_todo"><img src="${basePath }images/receive.png" /><br>待办收文</a>
												<c:if test="${docreceivenum>0}">
													<i class="num">${docreceivenum}</i>
												</c:if>
											</c:when>
											<c:otherwise>
												<img src="${basePath }images/receive1.png" />
												<br>待办收文</c:otherwise>
										</c:choose>
									</div></td>
								<!-- <td><div style="position: relative; width: 100px">
										<c:choose>
											<c:when test="${pert:hasperts(applicationScope.docsendtodo, loginModel.limit)}">
												<a href="Main/Docsend/todolist" data-toggle="navtab" data-id="docsendtodo"><img src="${basePath }images/send.png" /><br>待办发文</a>
												<c:if test="${docsendnum>0}">
													<i class="num">${docsendnum}</i>
												</c:if>
											</c:when>
											<c:otherwise>
												<img src="${basePath }images/send1.png" />
												<br>待办发文</c:otherwise>
										</c:choose>
									</div></td> -->
                                <td><div style="position: relative; width: 100px">
                                        <c:choose>
                                            <c:when test="${pert:hasperts(applicationScope.readInnerSend, loginModel.limit)}">
                                                <a href="Main/InnerSend/readList" data-toggle="navtab" data-id="readInnerSend"><img src="${basePath }images/send.png" /><br>内部发文</a>
                                                <c:if test="${innersendnum>0}">
                                                    <i class="num">${innersendnum}</i>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${basePath }images/send1.png" />
                                                <br>内部发文</c:otherwise>
                                        </c:choose>
                                    </div></td>
								<td><div style="position: relative; width: 100px">
										<c:choose>
											<c:when test="${pert:hasperts(applicationScope.leavetodo, loginModel.limit)}">
												<a href="Main/Leave/todolist" data-toggle="navtab" data-id="Mail"><img src="${basePath }images/leave.png" /><br>待办请休假</a>
												<c:if test="${leavenum>0}">
													<i class="num">${leavenum}</i>
												</c:if>
											</c:when>
											<c:otherwise>
												<img src="${basePath }images/leave1.png" />
												<br>待办请休假</c:otherwise>
										</c:choose>
									</div></td>
								<td><div style="position: relative; width: 100px">
										<c:choose>
											<c:when test="${pert:hasperts(applicationScope.meeting_todo, loginModel.limit)}">
												<a href="Main/Meeting/todolist" data-toggle="navtab" data-id="meeting_todo"><img src="${basePath }images/meeting.png" /><br>待办会议</a>
												<c:if test="${meetingnum>0}">
													<i class="num">${meetingnum}</i>
												</c:if>
											</c:when>
											<c:otherwise>
												<img src="${basePath }images/meeting1.png" />
												<br>待办会议</c:otherwise>
										</c:choose>
									</div></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" style="padding: 2px 3px;">
						<div class="panel-title">
							<i class="fa fa-calendar fa-fw"></i> <b>用户活跃度（前五）</b>
							<div class="pull-right">
								<a data-toggle="navtab" data-id="PersonalLoginStatitics" style="line-height: 16px" data-title="用户使用情况" href="Main/PersonalLoginStatitics/main">更多...</a>
							</div>
						</div>
					</div>
					<div class="panel-body" style="padding: 0px; height: 200px;">
						<table class="table table-bordered table-hover table-center">
							<thead>
								<tr>
									<th width="15%" align="center">姓名</th>
									<th width="25%" align="center">部门</th>
									<th width="35%" align="center">登录次数(网页/手机)</th>
									<th width="25%" align="center">最近登录日期</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${! empty ulist}">
										<c:forEach items="${ulist}" var="u">
											<tr height="35">
												<td class="link">${u.name }</td>
												<td class="link">${deptHM.get(u.d_id) }</td>
												<td class="link">${u.webcount+u.appcount } ( ${u.webcount } / ${u.appcount } )</td>
												<td class="link">${fn:substring(u.logindate, 0, 10) }</td>
											</tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-7" style="min-width: 460px">
				<div class="panel panel-default" style="margin-bottom: 10px;">
					<div class="panel-heading" style="padding: 2px 3px;">
						<h3 class="panel-title">
							<i class="fa fa-calendar fa-fw"></i> <b>工作日历</b>
						</h3>
					</div>
					<div class="panel-body" style="padding: 0px; height: 437px;">

						<div id="main">
							<div id="so_top"></div>
							<ul id="m-result" class="result">
								<li id="first" class="res-list">
									<div id="mohe-rili" class="g-mohe" data-mohe-type="rili">
										<div class="mh-rili-wap mh-rili-only " data-mgd='{"b":"rili-body"}'>
											<div class="mh-tips">
												<div class="mh-loading">
													<i class="mh-ico-loading"></i>正在为您努力加载中...
												</div>
												<div class="mh-err-tips">
													亲，出了点问题~ 您可<a href="#reload" class="mh-js-reload">重试</a>
												</div>
											</div>
											<div class="mh-rili-widget">

												<div class="mh-doc-bd mh-calendar">
													<div class="mh-hint-bar gclearfix">
														<div class="mh-control-bar">
															<div class="mh-control-module mh-year-control mh-year-bar">
																<a href="#prev-year" action="prev" class="mh-prev" data-md='{"p":"prev-year"}'></a>
																<div class="mh-control">
																	<i class="mh-trigger"></i>
																	<div class="mh-field mh-year" val=""></div>
																</div>
																<a href="#next-year" action="next" class="mh-next" data-md='{"p":"next-year"}'></a>
																<ul class="mh-list year-list" style="display: none;" data-md='{"p":"select-year"}'></ul>
															</div>
															<div class="mh-control-module mh-month-control mh-mouth-bar">
																<a href="#prev-month" action="prev" class="mh-prev" data-md='{"p":"prev-month"}'></a>
																<div class="mh-control">
																	<i class="mh-trigger"></i>
																	<div class="mh-field mh-month" val=""></div>
																</div>
																<a href="#next-month" action="next" class="mh-next" data-md='{"p":"next-month"}'></a>
																<ul class="mh-list month-list" style="display: none;" data-md='{"p":"select-month"}'></ul>
															</div>
															<!--  <div
																class="mh-control-module mh-holiday-control mh-holiday-bar">
																<div class="mh-control">
																	<i class="mh-trigger"></i>
																	<div class="mh-field mh-holiday"></div>
																</div>
																<ul class="mh-list" style="display:none;"
																	data-md='{"p":"select-holiday"}'></ul> 
															</div> -->
															<div class="mh-btn-today" data-md='{"p":"btn-today"}'>返回今天</div>
														</div>
													</div>
													<div class="mh-cal-main">
														<div class="mh-col-1 mh-dates">
															<ul class="mh-dates-hd gclearfix">
																<li class="mh-days-title">一</li>
																<li class="mh-days-title">二</li>
																<li class="mh-days-title">三</li>
																<li class="mh-days-title">四</li>
																<li class="mh-days-title">五</li>
																<li class="mh-days-title mh-weekend">六</li>
																<li class="mh-days-title mh-last mh-weekend">日</li>
															</ul>
															<ol class="mh-dates-bd"></ol>
														</div>
														<div class="mh-col-2 mh-almanac">
															<div a class="mh-almanac-base mh-almanac-main"></div>

															<div class="mh-almanac-extra gclearfix" style="display: none;">
																<table>
																	<tr>
																		<td><div align="center" style="font-size: 18px; margin: 5px; color: #c12c50">宜</div></td>
																		<td><div class="mh-suited">
																				<ul class="mh-st-items" style="list-style: none; margin: 0;">
																				</ul>
																			</div></td>
																	</tr>
																	<tr>
																		<td><div align="center" style="font-size: 18px; margin: 5px;">忌</div></td>
																		<td><div class="mh-tapu">
																				<ul class="mh-st-items" style="list-style: none; margin: 0;"></ul>
																			</div></td>
																	</tr>
																</table>
															</div>
															<div id="oneditid"></div>

														</div>
													</div>
												</div>
												<span id="mh-date-y" style="display: none;">${Mydate}</span>
											</div>
										</div>

										<div class="mh-rili-foot"></div>
										<select class="mh-holiday-data" style="display: none;">
											<option value="0" data-desc="" data-gl="">放假安排</option>
											<option value="抗战胜利纪念日" data-desc="9月3日至5日放假调休，共3天。9月6日（星期日）上班。" data-gl="">抗战胜利纪念日</option>
											<option value="国庆节" data-desc="10月1日至7日放假调休，共7天。10月10日（星期六）上班。" data-gl="">国庆节</option>
											<option value="中秋节" data-desc="9月27日放假。" data-gl="">中秋节</option>
											<option value="端午节" data-desc="6月20日放假，6月22日（星期一）补休。" data-gl="">端午节</option>
											<option value="劳动节" data-desc="5月1日放假，与周末连休。" data-gl="">劳动节</option>
											<option value="清明节" data-desc="4月5日放假，4月6日（星期一）补休。" data-gl="">清明节</option>
											<option value="春节" data-desc="2月18日至24日放假调休，共7天。2月15日（星期日）、2月28日（星期六）上班。" data-gl="">春节</option>
											<option value="元旦" data-desc="1月1日至3日放假调休，共3天。1月4日（星期日）上班。" data-gl="">元旦</option>
										</select>
										<!--value获取当前PHP服务器时间-->
										<input type="hidden" id="mh-rili-params" value="" />
									</div>
<script type="text/javascript">
(function(){window.OB=window.OB||{},
	window.OB.RiLi=window.OB.RiLi||{},
	
	//假日，变成日程日期
	window.OB.RiLi.dateRest=[${set}],
	
	//假日后，班日                     
	window.OB.RiLi.dateWork=[/*"0104","0215","0228","0906","1010"*/],
	
	window.OB.RiLi.dateFestival=[],
	                        
	window.OB.RiLi.dateAllFestival=[];
	
	var e="https://s.ssl.qhimg.com/!97be6a4c/data/"/*本地老黄历库在lhl文件夹，此处是官网调用的*/;
	location.protocol=="https:"&&(e="https://s.ssl.qhimg.com/!97be6a4c/data/")/*本地老黄历库在lhl文件夹，此处是官网调用的*/,
	
	window.OB.RiLi.hlUrl={2008:e+"hl2008.js",2009:e+"hl2009.js",2010:e+"hl2010.js",2011:e+"hl2011.js",
	2012:e+"hl2012.js",2013:e+"hl2013.js",2014:e+"hl2014.js",2015:e+"hl2015.js",2016:e+"hl2016.js",
	2017:e+"hl2017.js",2018:e+"hl2018.js",2019:e+"hl2019.js",2020:e+"hl2020.js"},
	
	window.OB.RiLi.dateHuochepiao=["-20141201||20","20141201||30","20141202||36","20141203||42",
	                        "20141204||48","20141205||54","+20141205||60","c20141221-20141228||red"],
	
	window.OB.RiLi.useLunarTicketDay={2016:{/*"0218":"除夕","0219":"初一","0220":"初二","0221":"初三",
		"0222":"初四","0223":"初五","0224":"初六","0225":"初七"*/}}})()

									</script> <script type="text/javascript" src="js/NewcalendarBottom.js"></script>
								</li>
							</ul>
						</div>
						<!-- END #main -->
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="isremain" value="${isremain }">
<div id="t3" style="display: none; z-index: 99;"></div>
<script type="text/javascript">
	$(document).ready(function(){ 
	 if(${isremain }==1){
		 $("#bgsound").attr("src","<%=basePath%>sound/${soundId}.wav");
	     $("#music").attr("src","<%=basePath%>sound/${soundId}.wav");
	 }
	});
	function show(obj, id) {
		$('#t3').css("display", "");
		$('#t3').css("position", "fixed");
		$('#t3').css("left", event.clientX + 15);
		$('#t3').css("top", event.clientY + 15);
		
		$.post("Main/backlog/calendar?date="+ id +"", function(data) {
			$('#t3').html(data);
		});
}
	function hide(obj, id) {
		$('#t3').css("display", "none");
	}
	
	function onedit(obj, id){
	$.post("Main/backlog/onedit?date="+ id +"", function(data) {
		$('#oneditid').html(data);
		});
	}	
	 <c:if test="${! empty tdate}"> 
		var tdate = "${tdate}";
		onedit(this,tdate);
	</c:if> 
</script>
<style>
.mh-solar:hover {
	font-size: 25px;
	font-size-adjust: none;
}

#t3 {
	line-height: 16px;
	border: 1px solid #639663;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 2px;
	-webkit-box-shadow: #666 0px 0px 8px;
	-moz-box-shadow: #666 0px 0px 8px;
	box-shadow: #666 0px 0px 8px;
	background: #aad7fd;
	color: #333;
}
</style>