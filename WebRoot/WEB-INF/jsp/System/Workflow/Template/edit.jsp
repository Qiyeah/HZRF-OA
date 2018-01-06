<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<html>
<head>
<title>模板管理</title>
<!-- bootstrap - css -->
<link href="<%=basePath %>BJUI/themes/css/bootstrap.css" rel="stylesheet">
<!-- core - css -->
<link href="<%=basePath %>BJUI/themes/css/style.css" rel="stylesheet">
<link href="<%=basePath %>BJUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
<style>
@media print {
	.noprint {
		display: none
	}
}
</style>
<script type="text/javascript" for=WebOffice event="OnMenuClick(vIndex,vCaption)">
	if (vIndex==1) { //打开本地文件
		WebOpenLocal();
	}
	if (vIndex==2) { //保存本地文件
		WebSaveLocal();
	}
	if (vIndex==4) { //重调远程文件
		LoadDocument();
	}
	if (vIndex==5) { //保存远程文件
		SaveDocument();
	}
	if (vIndex==7) { //打印文档
		WebOpenPrint();
	}
	if (vIndex==8) { //打印文档
		WebOpenBookMarks();
	}
</script>
<script type="text/javascript">	
	//作用：显示操作状态
	function StatusMsg(mString){
	  StatusBar.innerText=mString;
	}
	
	//作用：载入iWebOffice
	function Load(){
	  try{	
	  //以下属性必须设置，实始化iWebOffice
	  webform.WebOffice.WebUrl="${mServerUrl}";	//WebUrl:系统服务器路径，与服务器文件交互操作，如保存、打开文档，重要文件
	  webform.WebOffice.RecordID="${mRecordID}";	//RecordID:本文档记录编号
	  webform.WebOffice.Template="${mRecordID}";	//Template:模板编号
	  webform.WebOffice.FileName="${mFileName}";	//FileName:文档名称
	  webform.WebOffice.FileType="${mFileType}";	//FileType:文档类型  .doc  .xls  .wps
	  webform.WebOffice.EditType="${mEditType}";	//EditType:编辑类型  方式一、方式二  <参考技术文档>
	  webform.WebOffice.UserName="${mUserName}";	//UserName:操作用户名	
	  //以下属性可以不要
	  webform.WebOffice.ShowToolBar="0";		//ShowToolBar:是否显示工具栏:1显示,0不显示
	  webform.WebOffice.ShowMenu="1";		//ShowMenu:1 显示菜单  0 隐藏菜单
	  webform.WebOffice.AppendMenu("1","打开本地文件(&L)");
	  webform.WebOffice.AppendMenu("2","保存本地文件(&S)");
	  webform.WebOffice.AppendMenu("3","-");
	  webform.WebOffice.AppendMenu("4","重调远程文件(&Y)");
	  webform.WebOffice.AppendMenu("5","保存远程文件(&E)");
	  webform.WebOffice.AppendMenu("6","-");
	  webform.WebOffice.AppendMenu("7","打印文档(&P)");
	  webform.WebOffice.AppendMenu("8","定义书签(&B)");
	  webform.WebOffice.DisableMenu("宏(&M);选项(&O)...");      //禁止菜单	
	  webform.WebOffice.WebOpen();			//打开该文档    交互OfficeServer的OPTION="LOADTEMPLATE"
	  webform.WebOffice.ShowType=1;			//文档显示方式  1:表示文字批注  2:表示手写批注  0:表示文档核稿
	  StatusMsg(webform.WebOffice.Status);
	  } catch(e) {
		  
	  }
	}
	
	//作用：退出iWebOffice
	function UnLoad(){
	  try{
	  if (!webform.WebOffice.WebClose()){
	     StatusMsg(webform.WebOffice.Status);
	  }else{
	     StatusMsg("关闭文档...");
	  }
	  }catch(e){}
	}
	
	
	//作用：打开文档
	function LoadDocument(){
	  StatusMsg("正在打开文档...");
	  if (!webform.WebOffice.WebLoadTemplate()){  //交互OfficeServer的OPTION="LOADTEMPLATE"
	     StatusMsg(webform.WebOffice.Status);
	  }else{
	     StatusMsg(webform.WebOffice.Status);
	  }
	}
	
	
	//作用：刷新文档
	function WebReFresh(){
	  webform.WebOffice.WebReFresh();
	  StatusMsg("文档已刷新...");
	}
	
	
	//作用：保存文档
	function SaveDocument(){
	  webform.WebOffice.WebClearMessage();            //清空iWebOffice变量
	  if ("${mFileType}"==".doc"){
	    if (!webform.WebOffice.WebSaveBookMarks()){    //交互OfficeServer的OPTION="SAVEBOOKMARKS"
	      StatusMsg(webform.WebOffice.Status);
	      return false;
	    }
	  }
	  if (!webform.WebOffice.WebSaveTemplate(true)){    //交互OfficeServer的OPTION="SAVETEMPLATE"，参数true表示保存OFFICE文档
	     StatusMsg(webform.WebOffice.Status);
	     return false;
	  }else{
	     StatusMsg(webform.WebOffice.Status);
	     return true;
	  }
	}
	
	//作用：填充模板
	function LoadBookmarks(){
	  StatusMsg("正在填充模扳...");
	  if (!webform.WebOffice.WebLoadBookmarks()){    //交互OfficeServer的OPTION="LOADBOOKMARKS"
	     StatusMsg(webform.WebOffice.Status);
	  }else{
	     StatusMsg(webform.WebOffice.Status);
	  }
	}
	
	//作用：设置书签值  vbmName:标签名称，vbmValue:标签值   标签名称注意大小写
	function SetBookmarks(vbmName,vbmValue){
	  if (!webform.WebOffice.WebSetBookmarks(vbmName,vbmValue)){
	     StatusMsg(webform.WebOffice.Status);
	  }else{
	     StatusMsg(webform.WebOffice.Status);
	  }
	}
	
	//作用：根据标签名称获取标签值  vbmName:标签名称
	function GetBookmarks(vbmName){
	  var vbmValue;
	  vbmValue=webform.WebOffice.WebGetBookmarks(vbmName);
	  return vbmValue;
	}
	
	//作用：打印文档
	function WebOpenPrint(){
	  try{
	    webform.WebOffice.WebOpenPrint();
	    StatusMsg(webform.WebOffice.Status);
	  }catch(e){}
	}
	
	//作用：页面设置
	function WebOpenPageSetup(){
	   try{
		if (webform.WebOffice.FileType==".doc"){
		  webform.WebOffice.WebObject.Application.Dialogs(178).Show();
		}
		if(webform.WebOffice.FileType==".xls"){
		  webform.WebOffice.WebObject.Application.Dialogs(7).Show();
		}
	   }catch(e){
	
	   }
	}
	
	//作用：标签管理
	function WebOpenBookMarks(){
	  try{
	    webform.WebOffice.WebOpenBookmarks();    //交互OfficeServer的OPTION="LISTBOOKMARKS"
	    StatusMsg(webform.WebOffice.Status);
	  }catch(e){}
	}
	
	//作用：存为本地文件
	function WebSaveLocal(){
	  try{
	    webform.WebOffice.WebSaveLocal();
	    StatusMsg(webform.WebOffice.Status);
	  }catch(e){}
	}
	
	//作用：打开本地文件
	function WebOpenLocal(){
	  try{
	    webform.WebOffice.WebOpenLocal();
	    StatusMsg(webform.WebOffice.Status);
	  }catch(e){}
	}
</script>
</head>
<body onload="Load()" onunload="UnLoad()">
<div class="bjui-pageContent">
	<form name="webform" method="post" action="<%=basePath%>Main/Template/save">
		<input type="hidden" name="RecordID" value="${mRecordID}">
		<table class="wordInfo" style="width: 98%; height: 100%">
			<tr>
				<td width="10%" class="title">模版名称</td>
				<td width="25%"><input type="text" name="FileName" value="${mFileName}" style="width:96%" /></td>
				<td width="10%" class="title">模版说明</td>
				<td width="25%"><input type="text" name="Descript" value="${mDescript}" style="width:96%" /></td>
				<td width="30%" class="title"><div id=StatusBar>状态栏</div></td>
			</tr>
		  	<tr>
				<td height="100%" colspan="5">
					<object id="WebOffice" width="100%" height="100%" classid="clsid:8B23EA28-2009-402F-92C4-59BE0E063499" codebase="/cab/iWebOffice2009.cab#version=10,7,2,8"></object>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>