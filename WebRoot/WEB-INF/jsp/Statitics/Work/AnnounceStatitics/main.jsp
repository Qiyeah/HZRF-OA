<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	$(function(){
		
		<c:if test="${atype >= 0}">
		
		var creditChart = echarts.init(document.getElementById('creditChartAnnounceStatics'));
		option = {
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} ({d}%)"
			},		
			title: {
				show: true,
				text:'收件登记汇总统计图',
				left: 140,
				textStyle : {
					fontSize: 16
				}
			},
			color: ['#eb4f38','#ea8010','#f4c600','#11cd6e','#00bb9c','#56abe4','#9d55b8','#33475f'],
		    series: [
		        {
		            name:'收件登记数量',
		            type:'pie',
		            radius: [0, '40%'],
		            center: ['45%', '50%'],	
		            data:[
		            	 /* <c:forEach items="${swmap}" var="o" varStatus="vs">
		            	<c:if test="${!vs.first }">,</c:if>
		            	 {value:${o.value}, name:'${o.key}'}
						</c:forEach>  */
		            ]
		        }
		    ]
		};		
		creditChart.setOption(option);
		</c:if> 
		
	});
	
	
</script>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/AnnounceStatitics/main" method="post" data-toggle="ajaxsearch">
		<div class="bjui-searchBar">
			
			<label>公告类型：</label> <select data-toggle="selectpicker" name="atype" data-width="120px">
						<option value="" >--请选择--</option>
						<option value="99" <c:if test="${atype==99 }">selected</c:if>>全部</option>
						<option value="0" <c:if test="${atype==0 }">selected</c:if>>普通公告</option>
						<option value="1" <c:if test="${atype==1 }">selected</c:if>>紧急公告</option>
						</select>&nbsp;
		
		<label>收文日期：</label> <input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}">-<input type="text" name="edate" style="width: 120px" 
			data-toggle="datepicker" readonly value="${edate}">
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		
		</div>
	</form>
</div>

<div class="bjui-pageContent">
	<div style="width:50%;float: left">
		<c:if test="${atype >= 0}">
		
	<table class="wordInfo">
		<tr>
			<td class="title" width="30%">公告类型</td>
			<td class="title" width="35%">普通公告</td>
			<td class="title" width="35%">紧急公告</td>
		</tr>
		<tr>
		<td align="center">数量</td>
			<td align="center">${swNumber}</td>
			<td align="center">${fwNumber}</td>
		</tr>
		</table>
		
	</c:if>
	</div>
	
	<c:if test="${atype >= 0}">
	<div style="width:50%;float: right">
			<div style="width: 500px; height: 350px;float: left;margin-left: 10px;" id="creditChartAnnounceStatics"></div> 
	</div>
	</c:if>
	
	<c:if test="${atype < 0}">
		<div align="center">
			<span style="color: red;">无任何记录！！</span>
		</div>
	</c:if>
</div>


<div class="bjui-pageFooter">
	
</div>
 
 
 