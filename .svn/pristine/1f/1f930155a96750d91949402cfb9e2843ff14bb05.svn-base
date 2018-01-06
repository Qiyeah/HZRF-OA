<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<div class="pageHeader">
	<div class="searchBar">
		<form method="post" id="pagerForm" action="Main/Relation/AnalyzeMap" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="pageNum" value="${page.pageNumber}" /> <input type="hidden" name="numPerPage" value="${page.pageSize}" />
			<table id="todoSearch" class="searchContent">
				<tr>
					<td><span>统计类别：</span></td>
					<td><select id="tjtotype" name="t_Relation.totype" class="combox">
							<!-- <option value="0">请选择</option> -->
							<option value="1">转接类型</option>
							<option value="2">落实情况</option>
							<option value="3">党员类型</option>
							<option value="4">办理月份</option>
							<option value="5">党费月份</option>
					</select>
					</td>
					 <td><span>转接类型</span></td>
                    <td>
                        <select id="totype" name="t_Relation.totype" class="combox">
                        <option value="0">请选择</option>
                        <option value="1">转出</option>
                        <option value="2">转入</option>
                        <option value="3">市内</option>
                        </select>
                    </td>
					 <td><span>落实情况：</span></td>
					<td><select id="result" name="t_Relation.result" class="combox">
							<option value="0">请选择</option>
							<option value="1">未落实</option>
							<option value="2">已落实</option>
					</select>
					</td>
					 <td><span>党员类型：</span></td>
                    <td><select id="type" name="t_Relation.type" class="combox">
                     	<option value="0">请选择</option>
                        <option value="1">预备</option>
                        <option value="2">正式</option></select></td> 
                        
					 <td><span>办理时间：</span></td>
					<td><input type="text" id="sdate" name="sdate" style="width:100px" class="date" readonly value="${sdate}"> - <input type="text" id="edate" name="edate"
						style="width:100px" class="date" readonly value="${edate}"></td>
					<td>
						<div class="subBar">
							<div class="buttonActive">
								<div class="buttonContent">
									<button id="btngo" type="button" style="width: 50px;">查询</button>
								</div>
							</div>
						</div>
					</td> 
					
					
				</tr>
				 <!-- <tr>
					<td><span>受理月份：</span></td>
					<td colspan="8"><select id="type2" name="type2" class="combox">
							<option value="">全部</option>
							<option value="z_01">1</option>
							<option value="b_02">2</option>
							<option value="b_02">3</option>
							<option value="b_02">4</option>
							<option value="b_02">5</option>
							<option value="b_02">6</option>
							<option value="b_02">7</option>
							<option value="b_02">8</option>
							<option value="b_02">9</option>
							<option value="b_02">10</option>
							<option value="b_02">11</option>
							<option value="b_02">12</option>
					</select>
					</td>
					
				</tr>  -->
			</table>
		</form>
	</div>
</div>
<!-- ECharts单文件引入 -->
<script src="<%=basePath%>styles/echarts/build/dist/echarts-all.js"></script>

  <div class="pageContent" style="vertical-align:middle" layoutH="10"> 
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<center>
	<div id="main" align="center" style="height:400px;width:900px;margin-top: 60px;">请选择查询条件进行数据分析...</div>
</center>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		 $("#btngo").click(function () {
		 
		 var tjtotype = $("#tjtotype").val();//统计类型
		 var totype  = $("#totype").val();//转接类型
		 var result = $("#result").val();//落实情况
		 var type = $("#type").val();//党员类型
		 var sdate = $("#sdate").val();//开始时间
		 var edate = $("#edate").val();//结束时间
		 
		 switch (tjtotype) {
		case "1":	
				$.ajax({
                    type: "post",
                    async: false, //同步执行
                    url: "Main/Relation/AnalyzeMap",
                    dataType: "json", //返回数据形式为json
                    data: {tjtotype:tjtotype,totype:totype,result:result,type:type,sdate:sdate,edate:edate},
                    success: function(data) {
                        if (data) {
       			 		var myChart = echarts.init(document.getElementById('main'));
				option = {
            		color:['#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'],
				    title : {
				        text: '转接类型',
				        subtext: '',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient : 'vertical',
				        x : 'left',
				        data:[]
				    },
				    calculable : true,
				    series : [
				        {
				            name:'访问来源',
				            type:'pie',
				            radius : '55%',
				            center: ['50%', '50%'],
				            data:[{value: data.listy[0],name : data.listx[0]},
								  {value: data.listy[1],name : data.listx[1]}, 
								  {value: data.listy[2],name : data.listx[2]}],
			            itemStyle:{ 
			                normal:{ 
			                      label:{ 
			                        show: true, 
			                        formatter: '{b} : {c} ({d}%)' 
			                      }, 
			                      labelLine :{show:true} 
			                    } 
			                } 
				        }
				    ]
				};
					// 使用刚指定的配置项和数据显示图表。
					myChart.setOption(option);
                        }else{
                       alert("无任何记录!");
                        } 
                    },
                    error: function(errorMsg) {
                        alert("图表请求数据失败啦!");
                    }
                }); 
			break;
		case "2":	
			$.ajax({
                    type: "post",
                    async: false, //同步执行
                    url: "Main/Relation/AnalyzeMap",
                    dataType: "json", //返回数据形式为json
                    data: {tjtotype:tjtotype,totype:totype,result:result,type:type,sdate:sdate,edate:edate},
                    success: function(data) {
                        if (data) {
     				var myChart = echarts.init(document.getElementById('main'));
					option = {
            	    title : {
            	        text: '落实情况',
            	        x : 'center',
            	        subtext: ''
            	    },
            	    tooltip : {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	        data:[]
            	    },
            	    calculable : true,
            	    xAxis : [
            	        {
            	            type : 'value'
            	        }
            	    ],
            	    yAxis : [
            	        {
            	        type : 'category',
            	        data : data.listx
            	        }
            	    ],
            	    series : [
            	        {
            	            name:'共',
            	            type:'bar',
            	            barWidth : 50,
            	            data:data.listy,
            	            itemStyle : { //柱子颜色
							normal : {
								color : function(params) {
									// build a color map as your need.
									var colorList = [ '#C1232B', '#B5C334',
											'#FCCE10', '#E87C25', '#27727B',
											'#FE8463', '#9BCA63', '#FAD860',
											'#F3A43B', '#60C0DD', '#D7504B',
											'#C6E579', '#F4E001', '#F0805A',
											'#26C0C0' ];
									return colorList[params.dataIndex]
								},
								label : {//数字显示
									show : true,
									position : 'right',
									formatter : '{b}\n{c}'
								}
							}
						},
            	        }
            	    ]
            	};

			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
       
                        }else{
                       alert("无任何记录!");
                        } 
                    },
                    error: function(errorMsg) {
                        alert("图表请求数据失败啦!");
                    }
                });
			break;
		case "3":	
			$.ajax({
                    type: "post",
                    async: false, //同步执行
                    url: "Main/Relation/AnalyzeMap",
                    dataType: "json", //返回数据形式为json
                    data: {tjtotype:tjtotype,totype:totype,result:result,type:type,sdate:sdate,edate:edate},
                    success: function(data) {
                        if (data) {
    	 				var myChart = echarts.init(document.getElementById('main'));
		
			option = {
            	    title : {
            	        text: '党员类型',
            	        x : 'center',
            	        subtext: '' //副标题
            	    },
            	    tooltip : {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	        data:[]
            	    },
            	    calculable : true,
            	    xAxis : [
            	        {
            	         type : 'category',
            	         data : data.listx
            	            
            	        }
            	    ],
            	    yAxis : [
            	        {
            	       type : 'value'
            	            
            	        }
            	    ],
            	    series : [
            	        {
            	            name:'共',
            	            type:'bar',
            	            barWidth : 50,
            	            data:data.listy,
            	            itemStyle : { //柱子颜色
							normal : {
								color : function(params) {
									// build a color map as your need.
									var colorList = [ '#C1232B', '#B5C334',
											'#FCCE10', '#E87C25', '#27727B',
											'#FE8463', '#9BCA63', '#FAD860',
											'#F3A43B', '#60C0DD', '#D7504B',
											'#C6E579', '#F4E001', '#F0805A',
											'#26C0C0' ];
									return colorList[params.dataIndex]
								},
								label : {//数字显示
									show : true,
									position : 'top',
									formatter : '{b}\n{c}'
								}
							}
						},
            	        }
            	    ]
            	};
					// 使用刚指定的配置项和数据显示图表。
					myChart.setOption(option);
                        }else{
                       alert("无任何记录!");
                        } 
                    },
                    error: function(errorMsg) {
                        alert("图表请求数据失败啦!");
                    }
                });
			break;
		case "4":	
			$.ajax({
                    type: "post",
                    async: false, //同步执行
                    url: "Main/Relation/AnalyzeMap",
                    dataType: "json", //返回数据形式为json
                    data: {tjtotype:tjtotype,totype:totype,result:result,type:type,sdate:sdate,edate:edate},
                    success: function(data) {
                    	
                    if (data) {
                       var myChart = echarts.init(document.getElementById('main'));
					 option = {
					 color:['#00A2E8','#22B14C','#26C0C0','#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                           '#D7504B','#C6E579','#F4E001','#F0805A'],
    					title : {
    					 x : 'center',
        				text: '受理月份',
   					 },
   				 		tooltip : {
       					trigger: 'axis'
  				  	},
  						legend: {
     					data:[]
    				},
   						calculable : true,
    					xAxis : [{
           				type : 'category',
           			    boundaryGap : false,
            			data : data.listx
        			}
   				 	],
    					yAxis : [
       				{
            			type : 'value'
        			}
   					],
    					series : [
       
        			{
           				name:'共',
            			type:'line',
           				smooth:true,
            			itemStyle: {normal: {areaStyle: {type: 'default'}}},
            			data:data.listy,
           				itemStyle: {
            	            	normal: {
            	                    label : {
            	                        show : true,
            	                        textStyle : {
            	                            fontSize : '15',
            	                            fontFamily : '微软雅黑',
            	                            fontWeight : 'bold'
            	                        }
            	                    },
            	            		areaStyle: {
            	            			type: 'default'
            	            			}
            	            		}
            	            } 
       					 }
  			  		]
					};
						// 使用刚指定的配置项和数据显示图表。
						myChart.setOption(option); 
                        }else{
                       	alert("无任何记录!");
                        } 
                    },
                    error: function(errorMsg) {
                        alert("图表请求数据失败啦!");
                    }
                });
			break;
			case "5":	
			$.ajax({
                    type: "post",
                    async: false, //同步执行
                    url: "Main/Relation/AnalyzeMap",
                    dataType: "json", //返回数据形式为json
                    data: {tjtotype:tjtotype,totype:totype,result:result,type:type,sdate:sdate,edate:edate},
                    success: function(data) {
                    	
                    if (data) {
                       var myChart = echarts.init(document.getElementById('main'));
					 option = {
					 color:['#00A2E8','#22B14C','#26C0C0','#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                           '#D7504B','#C6E579','#F4E001','#F0805A'],
    					title : {
    					 x : 'center',
        				text: '党费月份',
   					 },
   				 		tooltip : {
       					trigger: 'axis'
  				  	},
  						legend: {
     					data:[]
    				},
   						calculable : true,
    					xAxis : [{
           				type : 'category',
           			    boundaryGap : false,
            			data : data.listx
        			}
   				 	],
    					yAxis : [
       				{
            			type : 'value'
        			}
   					],
    					series : [
       
        			{
           				name:'共',
            			type:'line',
           				smooth:true,
            			itemStyle: {normal: {areaStyle: {type: 'default'}}},
            			data:data.listy,
           				itemStyle: {
            	            	normal: {
            	                    label : {
            	                        show : true,
            	                        textStyle : {
            	                            fontSize : '15',
            	                            fontFamily : '微软雅黑',
            	                            fontWeight : 'bold'
            	                        }
            	                    },
            	            		areaStyle: {
            	            			type: 'default'
            	            			}
            	            		}
            	            } 
       					 }
  			  		]
					};
						// 使用刚指定的配置项和数据显示图表。
						myChart.setOption(option); 
                        }else{
                       	alert("无任何记录!");
                        } 
                    },
                    error: function(errorMsg) {
                        alert("图表请求数据失败啦!");
                    }
                });
			break;
		default:
			break;
		}
		 });
	</script>
</div>
