<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${system_title }</title>
<base href="<%=basePath%>">
<meta name="renderer" content="ie-comp" />
<meta name="renderer" content="ie-stand" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="shortcut icon" type="image/x-icon" href="images/bitbug_favicon.ico" />
<!-- bootstrap - css -->
<link href="BJUI/themes/css/bootstrap.css" rel="stylesheet">
<!-- core - css -->
<link href="BJUI/themes/css/style.css" rel="stylesheet">
<link href="BJUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/common.css" />
<!-- plug - css -->
<link href="BJUI/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet">
<link href="BJUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<link href="BJUI/plugins/niceValidator/jquery.validator.css" rel="stylesheet">
<link href="BJUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
<link href="BJUI/plugins/zhilian.css" rel="stylesheet" type="text/css" />
<link href="BJUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">

<!--[if lte IE 7]>
<link href="BJUI/themes/css/ie7.css" rel="stylesheet">
<![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
<script type="text/javascript" src="BJUI/other/html5shiv.min.js"></script>
<script type="text/javascript" src="BJUI/other/respond.min.js"></script>
<![endif]-->
<!-- jquery -->
<script type="text/javascript" src="BJUI/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="BJUI/js/jquery.cookie.js"></script>
<!--[if lte IE 9]>
<script type="text/javascript" src="BJUI/other/jquery.iframe-transport.js"></script>    
<![endif]-->
<!-- template -->
<script type="text/javascript" src="js/template.js"></script>
<!-- 以下是bjui和主要业务功能 js -->
<script type="text/javascript" src="BJUI/js/bjui-all.js"></script>
<script type="text/javascript" src="BJUI/js/zhilian-suggest.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/workflow.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/iSignatureHTML.js"></script>

<!-- 打印 -->
<script type="text/javascript" src="js/jQuery.print.js"></script>
<!-- plugins -->
<!-- swfupload for uploadify && kindeditor -->
<script type="text/javascript" src="BJUI/plugins/swfupload/swfupload.js"></script>
<!-- kindeditor -->
<script type="text/javascript" src="BJUI/plugins/kindeditor_4.1.10/kindeditor-all.min.js"></script>
<script type="text/javascript" src="BJUI/plugins/kindeditor_4.1.10/lang/zh_CN.js"></script>
<!-- colorpicker -->
<script type="text/javascript" src="BJUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- ztree -->
<script type="text/javascript" src="BJUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
<!-- nice validate -->
<script type="text/javascript" src="BJUI/plugins/niceValidator/jquery.validator.js"></script>
<script type="text/javascript" src="BJUI/plugins/niceValidator/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script type="text/javascript" src="BJUI/plugins/bootstrap.min.js"></script>
<script type="text/javascript" src="BJUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<script type="text/javascript" src="BJUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
<!-- icheck -->
<script type="text/javascript" src="BJUI/plugins/icheck/icheck.min.js"></script>
<!-- dragsort -->
<script type="text/javascript" src="BJUI/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
<!-- HighCharts -->
<script type="text/javascript" src="BJUI/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript" src="BJUI/plugins/highcharts/highcharts-3d.js"></script>
<script type="text/javascript" src="BJUI/plugins/highcharts/themes/gray.js"></script>
<!-- ECharts -->
<script type="text/javascript" src="BJUI/plugins/echarts/echarts.min.js"></script>
<!-- other plugins -->
<script type="text/javascript" src="BJUI/plugins/other/jquery.autosize.js"></script>
<link href="BJUI/plugins/uploadify/css/uploadify.css" rel="stylesheet">
<script type="text/javascript" src="BJUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="BJUI/plugins/download/jquery.fileDownload.js"></script>
<!-- init -->
<script type="text/javascript">
	$(function() {
		BJUI.init({
			JSPATH : 'BJUI/', //[可选]框架路径
			PLUGINPATH : 'BJUI/plugins/', //[可选]插件路径
			
			loginInfo : {
				url : '/Login',
				title : '登录',
				width : 400,
				height : 200
			}, // 会话超时后弹出登录对话框
			statusCode : {
				ok : 200,
				error : 300,
				timeout : 301
			}, //[可选]
			ajaxTimeout : 50000, //[可选]全局Ajax请求超时时间(毫秒)
			pageInfo : {
				total : 'total',
				pageCurrent : 'pageCurrent',
				pageSize : 'pageSize',
				orderField : 'orderField',
				orderDirection : 'orderDirection'
			}, //[可选]分页参数
			alertMsg : {
				displayPosition : 'topcenter',
				displayMode : 'slide',
				alertTimeout : 3000
			}, //[可选]信息提示的显示位置，显隐方式，及[info/correct]方式时自动关闭延时(毫秒)
			keys : {
				statusCode : 'statusCode',
				message : 'message'
			}, //[可选]
			ui : {
				windowWidth : 0, //框架可视宽度，0=100%宽，> 600为则居中显示
				showSlidebar : true, //[可选]左侧导航栏锁定/隐藏
				clientPaging : true, //[可选]是否在客户端响应分页及排序参数
				overwriteHomeTab : false
			//[可选]当打开一个未定义id的navtab时，是否可以覆盖主navtab(我的主页)
			},
			debug : true, // [可选]调试模式 [true|false，默认false]
			theme : 'sky' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, red, green]
		});
		// main - menu
		$('#bjui-accordionmenu').collapse().on('hidden.bs.collapse', function(e) {
			$(this).find('> .panel > .panel-heading').each(function() {
				var $heading = $(this), $a = $heading.find('> h4 > a')

				if ($a.hasClass('collapsed'))
					$heading.removeClass('active')
			})
		}).on('shown.bs.collapse', function(e) {
			$(this).find('> .panel > .panel-heading').each(function() {
				var $heading = $(this), $a = $heading.find('> h4 > a')

				if (!$a.hasClass('collapsed'))
					$heading.addClass('active')
			})
		});

		$(document).on('click', 'ul.menu-items li > a', function(e) {
			var $a = $(this), $li = $a.parent(), options = $a.data('options').toObj(), $children = $li.find('> .menu-items-children')
			var onClose = function() {
				$li.removeClass('active')
			}
			var onSwitch = function() {
				$('#bjui-accordionmenu').find('ul.menu-items li').removeClass('switch')
				$li.addClass('switch')
			}

			$li.addClass('active')
			if (options) {
				options.url = $a.attr('href')
				options.onClose = onClose
				options.onSwitch = onSwitch
				if (!options.title)
					options.title = $a.text()

				if (!options.target)
					$a.navtab(options)
				else
					$a.dialog(options)
			}
			if ($children.length) {
				$li.toggleClass('open')
			}

			e.preventDefault()
		});

		//时钟
		var today = new Date(), time = today.getTime()
		$('#bjui-date').html(today.formatDate('yyyy/MM/dd'))
		setInterval(function() {
			today = new Date(today.setSeconds(today.getSeconds() + 1))
			$('#bjui-clock').html(today.formatDate('HH:mm:ss'))
		}, 1000);
		
		//消息提醒 &&待办桌面提醒
		
		/*comAlert();
		comMessage();*/
		
	})
	 
	
	function comAlert(){
		$.post("Main/backlog/getalert", function(data){
	        if(notBlank(data)){ 
	            $("#bgsound").attr("src","<%=basePath%>sound/${soundId}.wav");
	            $("#music").attr("src","<%=basePath%>sound/${soundId}.wav");
	           // showMSG('组织部OA系统',data,''); 
	          $("#alertid").val(83);
	            $(this).alertmsg('warn', data, {displayMode:'slide', displayPosition:'bottomright', okName:'关闭',  title:'组织部OA系统',
	           
	            autoClose:'false',okCall:'ChangeStatus',mask:'false'})
	       	
	        }
	    },"text");
		 setTimeout(comAlert,60000);

	}  
	
	//待办桌面刷新
	function comMessage(){
		 $.post("Main/getRemainder", function(data){
	        if(notBlank(data)){ 
	        	$('#remainder').html(data);
	        	$("#remainder").initui();
	        }
	      },"text");
		setTimeout(comMessage,60000);

	}
	
	function ChangeStatus(){
		$.post("Main/backlog/changeStatus");
	}
	
	//菜单-事件
	function MainMenuClick(event, treeId, treeNode) {
		event.preventDefault();
		if (treeNode.isParent) {
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			zTree.expandNode(treeNode, !treeNode.open, false, true, true);
			return;
		}
		if (treeNode.target && treeNode.target == 'dialog') {
			$(event.target).dialog({
				id : treeNode.tabid,
				url : treeNode.url,
				title : treeNode.name
			});
		} else {
			$(event.target).navtab({
				id : treeNode.tabid,
				url : treeNode.url,
				title : treeNode.name,
				fresh : treeNode.fresh,
				external : treeNode.external
			});
		}
	}

	function viewpdf(filename) {
		$(this).dialog({
			id : 'viewpdf',
			url : 'Main/Common/viewPdf/' + filename,
			title : filename,
			width : 800,
			height : 600
		});
	}
	
	
</script>

<script type="text/javascript">
    /**
     * 类名：CLASS_MSN_MESSAGE
     * 功能：提供类似MSN消息框
     * 示例：
     * var MSG = new CLASS_MSN_MESSAGE("aa",200,120,"短消息提示：","您有1封消息","今天请我吃饭哈");
     * MSG.show();
     * 消息构造
     */
     function CLASS_MSN_MESSAGE(id,width,height,caption,title,contentSize,target,action){
        this.id       = id;
        this.title    = title;
        this.caption = caption;
        this.contentSize = contentSize;
        this.target   = target;
        this.action   = action;
        this.width    = width?width:200;
        this.height   = height?height:120;
        this.timeout = 150;
        this.speed    = 20;
        this.step     = 1;
        this.right    = screen.width -1;
        this.bottom   = screen.height;
        this.left     = this.right - this.width;
        this.top      = this.bottom - this.height;
        this.timer    = 0;
        this.pause    = false;
        this.close    = false;
        this.autoHide = false;
    }

    /**
     * 隐藏消息方法
     */
    CLASS_MSN_MESSAGE.prototype.hide = function(){
        if(this.onunload()){
            var offset = this.height>this.bottom-this.top?this.height:this.bottom-this.top;
            var me = this;
            if(this.timer>0){
                ///window.clearInterval(me.timer);
            }

            var fun = function(){
                if(me.pause==false||me.close){
                    var x = me.left;
                    var y = 0;
                    var width = me.width;
                    var height = 0;
                    if(me.offset>0){
                        height = me.offset;
                    }

                    y = me.bottom - height;
                    if(true){
                        window.clearInterval(me.timer);
                        me.Pop.hide();
                    } else {
                        me.offset = me.offset - me.step;
                    }
                    // me.Pop.show(x,y,width,height);
                }
            }
            fun();
            //  this.timer = window.setInterval(fun,1000)
        }
    }

    /**
     * 消息卸载事件，可以重写
     */
    CLASS_MSN_MESSAGE.prototype.onunload = function() {
        return true;
    }
    /**
     * 消息命令事件，要实现自己的连接，请重写它
     * window.open("http://www.lost63.com");
     */
    CLASS_MSN_MESSAGE.prototype.oncommand = function(){
        this.close = true;
        this.hide();
    }
    /**
     * 消息显示方法
     */
    /*
     * 设置速度方法
     */
     
    
    CLASS_MSN_MESSAGE.prototype.speed = function(s){
        var t = 20;
        try {
            t = praseInt(s);
        } catch(e){}
        this.speed = t;
    }
    /**
     * 设置步长方法
     */
    CLASS_MSN_MESSAGE.prototype.step = function(s){
        var t = 1;
        try {
            t = praseInt(s);
        } catch(e){}
        this.step = t;
    }

    CLASS_MSN_MESSAGE.prototype.rect = function(left,right,top,bottom){
        try {
            this.left        = left    !=null?left:this.right-this.width;
            this.right        = right    !=null?right:this.left +this.width;
            this.bottom        = bottom!=null?(bottom>screen.height?screen.height:bottom):screen.height;
            this.top        = top    !=null?top:this.bottom - this.height;
        } catch(e){}
    }

    /**
     * 显示提示框
     */
    function showMSG(caption,title,content){
        var MSG = new CLASS_MSN_MESSAGE("popId",400,200,caption,title,20);
        MSG.rect(null,null,null,screen.height-50);
        MSG.speed    = 10;
        MSG.step    = 5;
        MSG.show();
    }

</script>
</head>
<body>
	<div id="bjui-window">
		<header id="bjui-header">
		<div class="bjui-navbar-header">
			<button type="button" class="bjui-navbar-toggle btn-default" data-toggle="collapse" data-target="#bjui-navbar-collapse">
				<i class="fa fa-bars"></i>
			</button>
			<a class="bjui-navbar-logo"><img src="./images/rf-logo.png" style="margin-bottom: 8px;" /><label
				style="margin-top: 17px; margin-left: 8px; font-family: '黑体'; font-size: 24px; color: #FFFFFF">${system_title }</label><label
				style="color: #fff; margin-left: 5px; font-weight: normal">&nbsp;&nbsp;Ver 1.0</label>
			</a>
		</div>
		<nav id="bjui-navbar-collapse">		
		<a href="Main/downloadApp" data-toggle="dialog" data-title="扫描二维码下载手机客户端" data-id="downloadApp" data-mask="true" data-width="300" data-height="300"><img src="./images/erweima.jpg" /></a>
		<ul class="bjui-navbar-right">					
			<li class="datetime"><div>
					<span id="bjui-date"></span> <span id="bjui-clock"></span>
				</div></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.loginModel.userName} <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="Main/User/pwordsetip" data-toggle="dialog" data-id="changepwd_page" data-mask="true" data-width="500" data-height="260">&nbsp;<span
							class="glyphicon glyphicon-lock"></span> 修改密码&nbsp;
					</a></li>
					<li class="divider"></li>
					<li><a href="Login/logout" class="red">&nbsp;<span class="glyphicon glyphicon-off"></span> 注销登陆
					</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle theme blue" data-toggle="dropdown" title="切换皮肤"><i class="fa fa-tree"></i></a>
				<ul class="dropdown-menu" role="menu" id="bjui-themes">
					<li><a href="javascript:;" class="theme_default" data-toggle="theme" data-theme="default">&nbsp;<i class="fa fa-tree"></i> 黑白分明&nbsp;&nbsp;
					</a></li>
					<li><a href="javascript:;" class="theme_orange" data-toggle="theme" data-theme="orange">&nbsp;<i class="fa fa-tree"></i> 橘子红了
					</a></li>
					<li><a href="javascript:;" class="theme_purple" data-toggle="theme" data-theme="purple">&nbsp;<i class="fa fa-tree"></i> 紫罗兰
					</a></li>
					<li class="active"><a href="javascript:;" class="theme_blue" data-toggle="theme" data-theme="blue">&nbsp;<i class="fa fa-tree"></i> 天空蓝
					</a></li>
					<li><a href="javascript:;" class="theme_green" data-toggle="theme" data-theme="green">&nbsp;<i class="fa fa-tree"></i> 绿草如茵
					</a></li>
				</ul></li>
		</ul>		
		</nav>
		<div style="position: absolute; top:40px; right: 35px"><span style="color: #fff">系统使用交流群：<i class="fa fa-qq"></i> 418960090</span>
		<span> <c:if test="${sessionScope.utype == 1}"> <a href="Main/help" data-toggle="dialog" data-mask="true" data-id="helpid" data-title="帮助"
						data-width="1366" data-height="600"><span style="color: #fff">帮助</span></a></c:if></span></div>
		
		<div id="bjui-hnav">
			<button type="button" class="btn-default bjui-hnav-more-left" title="导航菜单左移">
				<i class="fa fa-angle-double-left"></i>
			</button>
			<div id="bjui-hnav-navbar-box">
				<ul id="bjui-hnav-navbar">
					<c:forEach items="${modules}" var="module" varStatus="modulevs">
						<li <c:if test="${modulevs.first }">class="active"</c:if>><a href="javascript:;" data-toggle="slidebar"><i class="fa ${module.icon }"></i> ${module.name}</a>
							<div class="items hide" data-noinit="true">
								<c:forEach items="${childModuleMap.get(module.id) }" var="smd">
									<ul class="menu-items" data-faicon="${smd.icon}" data-tit="${smd.name}">
										<c:forEach items="${threeModuleMap.get(smd.id)}" var="smt">
											<li><a href="${smt.address}" data-options="{id:'${smt.mark}', faicon:'${smt.icon }',fresh:'true'}">${smt.name}</a></li>
										</c:forEach>
									</ul>
								</c:forEach>
							</div></li>
					</c:forEach>
				</ul>
			</div>
			<button type="button" class="btn-default bjui-hnav-more-right" title="导航菜单右移">
				<i class="fa fa-angle-double-right"></i>
			</button>
		</div>
		</header>
		<div id="bjui-container" class="clearfix">
			<div id="bjui-leftside">
				<div id="bjui-sidebar-s">
					<div class="collapse"></div>
				</div>
				<div id="bjui-sidebar">
					<div class="toggleCollapse">
						<h2>
							<i class="fa fa-bars"></i> 菜单栏 <i class="fa fa-bars"></i>
						</h2>
						<a href="javascript:;" class="lock"><i class="fa fa-lock"></i></a>
					</div>
					<div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu"></div>
				</div>
			</div>
			<div id="bjui-navtab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<ul class="navtab-tab nav nav-tabs">
							<li data-url="Main/getTask"><a href="javascript:;"><span><i class="fa fa-home"></i> #maintab#</span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">
						<i class="fa fa-angle-double-left"></i>
					</div>
					<div class="tabsRight">
						<i class="fa fa-angle-double-right"></i>
					</div>
					<div class="tabsMore">
						<i class="fa fa-angle-double-down"></i>
					</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">#maintab#</a></li>
				</ul>
				<div class="navtab-panel tabsPageContent">
					<div class="navtabPage unitBox">
						<input type="hidden" id="navtabHeight" />
						<div class="bjui-pageContent" id="maintask" style="background: #FFF;">Loading...</div>
					</div>
				</div>
			</div>
		</div>
		<footer id="bjui-footer">Copyright &copy; 2016 ${system_version_company }     ${system_sugest }</footer>
	</div>
		
		<bgsound id="bgsound"/>
        <audio id="music" src="" autoplay="autoplay"/>
</body>
</html>