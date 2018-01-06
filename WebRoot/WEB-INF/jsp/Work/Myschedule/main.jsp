<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<link href='fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='js/jquery-ui-1.10.2.custom.min.js'></script>
<script src='fullcalendar/fullcalendar.min.js'></script>
<script>
	$(document).ready(function() {

		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
	
		var calendar = $('#calendar').fullCalendar({
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek,agendaDay',
				
			},
			selectable : true,
			selectHelper : true,
			timeFormat:'HH:mm',
			axisFormat:'HH:mm',
			allDayText:'全天 &nbsp;',
			aspectRatio:'2.4', 
			color:'red',
			select : function(start, end, allDay) { //日期点击事件
				var sdate = new Date(+start+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');
				var edate = new Date(+end+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');
				$(this).dialog({
					id : 'Myscheduleaddip',
					url : 'Main/Myschedule/addip',
					data:{sdate:sdate,edate:edate},
					title : '日程添加',
					mask : true,
					drawable : true,
					resizable : true,
					maxable : true,
					width : 800,
					height : 500
				});
			
			},
			eventClick:function(event, jsEvent, view) { //标题点击事件
				var id = event.id;
				
				$(this).dialog({
					id : 'Myscheduleupdateip',
					url : 'Main/Myschedule/updateip',
					data:{id:id},
					title : '日程修改',
					mask : true,
					drawable : true,
					resizable : true,
					maxable : true,
					width : 800,
					height : 500
				});
			},
			editable : true,
			events : [ ${events}]
		});

	});
</script>
<div class="bjui-pageHeader">
	  <!-- <table>
		<tr>
			<td>
				 <div style="border: solid;text-align:center;height: 100px;width:100px;">
				
				<div style="border: solid;position:absolute;margin:0 ;   width:30px;">60</div>
				
				</div>
				<div style="border: solid;">70</div> 
			</td>
		</tr>
	</table>   -->
	
	<!-- <form id="pagerForm" action="Main/Myschedule/main" method="post" data-toggle="ajaxsearch">
		<div class="pull-right">
			<button type="button" class="btn btn-green" data-url="Main/Myschedule/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleid" data-title="日程添加"
				data-width="800" data-height="500">添加</button>
		<a href="Main/Myschedule/statisticsip" class="btn btn-blue" data-toggle="dialog" data-icon="line-chart" data-mask="ture" data-id="MyscheduleStatistics" data-title="统计"
							data-width="1200" data-height="600">统计</a>
		</div> -->
</div>
</form>
</div>
<div class="bjui-pageContent tableContent">
	<div id=calendar></div>
</div>

