<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">

	<div style="margin-top: 0px; overflow: hidden;">
		<div class="row" style="padding: 0 8px;">
			<div class="col-md-6">
				<div class="panel panel-default" style="margin-bottom: 10px;">
					<div class="panel-heading" style="padding: 2px 3px;">
						<h3 class="panel-title">
							<i class="fa fa-bell-o fa-fw"></i> <b>督办提醒</b>
						</h3>
					</div>
					<div class="panel-body" style="padding: 0px; height: 300px;">
						<table class="table table-bordered table-hover table-striped table-top table-center">
							<tbody id="superdo">
								<tr align="center">
									<td width="40%"><b>督办标题</b></td>
									<td width="30%"><b>来文单位</b></td>
									<td width="15%"><b>承办科室</b></td>
									<td width="15%"><b>办理时限</b></td>
								</tr>
								<tr style="color: #003399" class="trbg">
									<td align="left"><a href="#" onclick="openTodoNavTab('Main/Docreceive/superlist','receive_super','督办列表')" style="font-size: 13px; color: #003399">测试收文标题792</a></td>
									<td>市发改局</td>
									<td>办公室</td>
									<td></td>
								</tr>
								<tr style="color: #003399">
									<td align="left"><a href="#" onclick="openTodoNavTab('Main/Docreceive/superlist','receive_super','督办列表')" style="font-size: 13px; color: #003399">测试收文标题806</a></td>
									<td>惠州市人力资源和社会保障局</td>
									<td>办公室</td>
									<td>2016-08-01</td>
								</tr>
								<tr style="color: #003399" class="trbg">
									<td align="left"><a href="#" onclick="openTodoNavTab('Main/Docreceive/superlist','receive_super','督办列表')" style="font-size: 13px; color: #003399">测试收文标题829</a></td>
									<td>中共广东省委组织部</td>
									<td>部领导</td>
									<td></td>
								</tr>
								<tr style="color: #003399">
									<td align="left"><a href="#" onclick="openTodoNavTab('Main/Docreceive/superlist','receive_super','督办列表')" style="font-size: 13px; color: #003399">测试收文标题831</a></td>
									<td>市府办</td>
									<td>部领导</td>
									<td></td>
								</tr>
								<tr style="color: #003399" class="trbg">
									<td align="left"><a href="#" onclick="openTodoNavTab('Main/Docreceive/superlist','receive_super','督办列表')" style="font-size: 13px; color: #003399">文件标题31</a></td>
									<td>中共中央办公厅</td>
									<td>部领导</td>
									<td>2016-10-04</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-6" style="min-width: 450px">
				<div class="panel panel-default" style="margin-bottom: 10px;">
					<div class="panel-heading" style="padding: 2px 3px;">
						<h3 class="panel-title">
							<i class="fa fa-calendar fa-fw"></i> <b>工作日历</b>
						</h3>
					</div>
					<div class="panel-body" style="padding: 0px; height: 300px;">
						<table class="table table-bordered table-hover" id="1">
							<tr>
								<td align="center">
									<div id="cal">
										<div id="top">
											公元 <select>
											</select> 年 <select>
											</select> 月 农历 <span> </span> 年 [ <span> </span> 年 ] <input type="button" value="回到今天" title="点击后跳转回今天" style="padding: 0px">
										</div>
										<ul id="wk">
											<li>一</li>
											<li>二</li>
											<li>三</li>
											<li>四</li>
											<li>五</li>
											<li><b> 六 </b></li>
											<li><b> 日 </b></li>
										</ul>
										<div id="cm"></div>
										<div id="bm"></div>
									</div>
								</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default" style="padding: 0px; height: 260px;">
					<div class="panel-heading" style="padding: 2px 3px;">
						<h3 class="panel-title">
							<i class="fa fa-file-o fa-fw"></i> <b>通知通告</b>
						</h3>
					</div>
					<div class="panel-body" style="padding: 0px; height: 300px;" id="announce_div">
						<table class="table table-bordered table-hover table-striped table-top table-center">
							<tbody id="announce">
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default" style="padding: 0px; height: 260px;">
					<div class="panel-heading" style="padding: 2px 3px;">
						<h3 class="panel-title">
							<i class="fa fa-bell-o fa-fw"></i> <b>待办提醒</b>
						</h3>
					</div>
					<div class="panel-body" style="padding: 0px; height: 300px;">
						<table class="table table-bordered table-hover">
							<tbody id="todo">
								<tr height="20px">
									<td colspan="4" height="30px" style="background-image: url(./images/m_title_bg2.jpg);"><span>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<b>收文办理</b> </span></td>
								</tr>
								<tr height="20px" style="color: red" class="trbg">
									<td width="20" style="border: 0px"></td>
									<td width="20" style="border: 0px" align="center" valign="middle"><span
										style="background-color: #000000; display: inline-block; padding: 2px; text-align: center; vertical-align: text-bottom; font-size: 12px; line-height: 100%; font-style: normal; color: #fff; overflow: hidden;"></span>
									</td>
									<td colspan="2"><a href="#" onclick="openTodoNavTab('Main/Docachieve/achievelist','achieve_todo','初审分办')" style="font-size: 13px; color: #996600">有<font
											style="font-size: 15px; font-weight: strong">&nbsp;1&nbsp;</font>份文件还未分发 </a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default" style="padding: 0px; height: 260px;">
					<div class="panel-heading" style="padding: 2px 3px;">
						<h3 class="panel-title">
							<i class="fa fa-envelope-o fa-fw"></i> <b>个人邮件</b>
						</h3>
					</div>
					<div class="panel-body" style="padding: 0px; height: 300px;" id="email_div">
						<table class="table table-bordered table-hover table-striped table-top table-center nowrap">
							<tbody id="email">
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	//日历
	initCalendar();

	//公告通知
	announce();
	function announce() {
		$.post("Main/backlog/announce", function(data) {
			$("#announce").html(data);
			/*  $("#announce_div").initUI();  */
		});
		setTimeout(announce, 5000);
	}
	
	//邮件通知
	email();
	function email() {
		$.post("Main/backlog/email", function(data) {
			$("#email").html(data);/* $("#email_div").initUI(); */
		});
		setTimeout(email, 5000);
	}
</script>