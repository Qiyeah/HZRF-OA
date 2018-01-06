<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<html>
<head>
<title>编辑正文</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style>
	body {
		margin: 0px;
	}
</style>
<script type="text/javascript">
	//作用：显示操作状态
	function StatusMsg(mString) {
		//webform.StatusBar.value = mString;
	}
	//作用：载入iWebOffice
	function Load(){
		try{
			webform.WebOffice.WebUrl="${mServerUrl}";
			webform.WebOffice.RecordID="${mRecordID}";
			webform.WebOffice.Template="${mTemplate}";
			webform.WebOffice.FileName="${mFileName}";
			webform.WebOffice.FileType="${mFileType}";
			webform.WebOffice.UserName="${mUserName}";
			webform.WebOffice.EditType="${mEditType}";
			webform.WebOffice.MaxFileSize = 4 * 1024;
			webform.WebOffice.Language="CH";
			//webform.WebOffice.ShowWindow = true;
			webform.WebOffice.PenColor="#FF0000";
			webform.WebOffice.PenWidth="1";
			webform.WebOffice.Print="1";
			webform.WebOffice.ShowToolBar="1";
			webform.WebOffice.AppendTools("13","打印文档",16);
			webform.WebOffice.AppendTools("14","保存服务器",2);
			webform.WebOffice.VisibleTools("文字批注",false);
			webform.WebOffice.VisibleTools("手写批注",false);
			webform.WebOffice.VisibleTools("文档清稿",false);
			webform.WebOffice.VisibleTools("重新批注",false);
			webform.WebOffice.ShowMenu="0";                         //控制整体菜单显示
			webform.WebOffice.WebOpen();                            //打开该文档    交互OfficeServer  调出文档OPTION="LOADFILE"    调出模板OPTION="LOADTEMPLATE"     <参考技术文档>
			if ("${title}" != "") {
				webform.WebOffice.WebSetBookmarks("title", "${title}");
			}
			if ("${docno}" != "") {
				webform.WebOffice.WebSetBookmarks("docno", "${docno}");
			}
			if ("${send1}" != "") {
				webform.WebOffice.WebSetBookmarks("send1", "${send1}");
			}
			if ("${send2}" != "") {
				webform.WebOffice.WebSetBookmarks("send2", "${send2}");
			}
			if ("${num}" != "") {
				webform.WebOffice.WebSetBookmarks("num", "${num}");
			}
			webform.WebOffice.ShowType="${mShowType}";              //文档显示方式  1:表示文字批注  2:表示手写批注  0:表示文档核稿
		}catch(e){
	  		alert(e.description);                                   //显示出错误信息
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
	  }catch(e){
	    alert(e.description);
	  }
	}
	
	//作用：打开文档
	function LoadDocument(){
	  StatusMsg("正在打开文档...");
	  if (!webform.WebOffice.WebOpen()){  	//打开该文档    交互OfficeServer的OPTION="LOADFILE"
	     StatusMsg(webform.WebOffice.Status);
	  }else{
	     StatusMsg(webform.WebOffice.Status);
	  }
	}
	
	//作用：保存文档
	function SaveDocument(){
	  //webform.WebOffice.WebSetMsgByName("MyDefine1","自定义变量值1");
	  if (!webform.WebOffice.WebSave()){
		  //交互OfficeServer的OPTION="SAVEFILE"  注：WebSave()是保存复合格式文件，包括OFFICE内容和手写批注文档；如只保存成OFFICE文档格式，那么就设WebSave(true)
	     //StatusMsg(webform.WebOffice.Status);
	     alert("保存完毕");
	     return false;
	  }else{
	     //StatusMsg(webform.WebOffice.Status);
	     alert("保存完毕");
	     return true;
	  }
	}
</script>
</head>
<body bgcolor="#ffffff" onLoad="Load()" onUnload="UnLoad()">
	<form name="webform" method="post" action="Main/Office/savedoc" onSubmit="return SaveDocument();">
		<table style="width: 100%;height: 100%">

<%--       <tr>
        <td>
          <input type=button class=SideButton value="全屏显示" onclick='webform.WebOffice.FullSize();'>
          <input type=button class=SideButton value="文字批注" onclick='webform.WebOffice.ShowType=1;'>
          <input type=button class=SideButton value="手写批注" onclick='webform.WebOffice.ShowType=2;'>
          <input type=button class=SideButton value="文档核稿" onclick='webform.WebOffice.ShowType=0;'>
          <input type=button class=SideButton value="新建文件" onclick='webform.WebOffice.CreateFile();'>
          <input type=button class=SideButton value="重新批注" onclick='webform.WebOffice.ReWrite();'>
          <input type=button class=SideButton value="关闭工具栏" onClick="webform.WebOffice.ShowToolBar=0">
          <input type=button class=SideButton value="打开工具栏" onClick="webform.WebOffice.ShowToolBar=1">
          <input type=button class=SideButton value="禁止打开文件" onClick="webform.WebOffice.DisableTools('打开文件',true)">
          <input type=button class=SideButton value="隐藏新建文件" onClick="webform.WebOffice.VisibleTools('新建文件',false)">
          <input type=button class=SideButton value="隐藏全屏" onClick="webform.WebOffice.VisibleTools('全屏',false)">
          <input type=button class=SideButton value="显示全屏" onClick="webform.WebOffice.VisibleTools('全屏',true)">
          <input type=button class=SideButton value="显示版本" onClick="alert('当前控件版本为：'+webform.WebOffice.VersionEx()+webform.WebOffice.Version());">
	    </td>
      </tr>
      <tr>
        <td>
          <input type=button class=SideButton value="显示痕迹" ${mDisable} ${mWord} onClick="ShowRevision(true)">
          <input type=button class=SideButton value="隐藏痕迹" ${mDisable} ${mWord} onClick="ShowRevision(false)">
          <input type=button class=SideButton value="获取痕迹" ${mDisable} ${mWord} onClick="WebGetRevisions()">
          <input type=button class=SideButton value="清除痕迹" ${mDisable} ${mWord} onClick="WebAcceptAllRevisions()">
          <input type=button class=SideButton value="保护文档" ${mDisable} onClick="WebProtect(true)">
          <input type=button class=SideButton value="解除保护" ${mDisable} onClick="WebProtect(false)">
          <input type=button class=SideButton value="允许拷贝" ${mDisable} onClick="WebEnableCopy(true)">
          <input type=button class=SideButton value="禁止拷贝" ${mDisable} onClick="WebEnableCopy(false)">
          <input type=button class=SideButton value="页面设置" ${mDisable} ${mWord} onClick="WebOpenPageSetup()">
          <input type=button class=SideButton value="打印文档" ${mDisable} ${mWord} onClick="WebOpenPrint()">
          <input type=button class=SideButton value="重调文档" ${mDisable} ${mWord} onClick="LoadDocument()">
          <input type=button class=SideButton value="刷新文档" ${mDisable} ${mWord} onClick="WebReFresh()">
          <input type=button class=SideButton value="打开本地文件" ${mDisable} onClick="WebOpenLocal()">
          <input type=button class=SideButton value="存为本地文件" ${mDisable} onClick="WebSaveLocal()">
          <input type=button class=SideButton value="判断编辑器" onClick="GetEditer()">
	    </td>
      </tr>
      <tr>
        <td>
          <input type=button class=SideButton value="签名印章" ${mDisable} onClick="WebOpenSignature()">
          <input type=button class=SideButton value="签章锁定文件" ${mDisable}  onclick="WebSignatureAtReadonly();">
          <input type=button class=SideButton value="验证签章[A]" ${mDisable}  onclick="WebShowSignature()">
          <input type=button class=SideButton value="验证签章[B]" ${mDisable}  onclick="WebCheckSignature()">
          <input type=button class=SideButton value="印章彩色" onClick="webform.WebOffice.SignatureColor(true);">
          <input type=button class=SideButton value="印章黑白" onClick="webform.WebOffice.SignatureColor(false);">
          <input type=button class=SideButton value="打开标签" ${mDisable}  ${mWord}  onclick="WebOpenBookMarks()">
          <input type=button class=SideButton value="填充模版标签" ${mDisable}  ${mWord}  onclick="LoadBookmarks()">
          <input type=button class=SideButton value="保存版本" ${mDisable}  onclick="WebSaveVersion()">
          <input type=button class=SideButton value="打开版本" ${mDisable}  onclick="WebOpenVersion()">
          <input type=button class=SideButton value="保存当前版本" ${mDisable}  onclick="WebSaveVersionByFileID()">
          <input type=button class=SideButton value="保存定稿版本" ${mDisable}  onclick="WebUpdateFile()">
          <input type=button class=SideButton value="下载服务器文件" ${mDisable} ${mWord} onClick="WebGetFile()">
          <input type=button class=SideButton value="上传文件到服务器" ${mDisable} ${mWord} onClick="WebPutFile()">
          <input type=button class=SideButton value="打开远程文件"  ${mDisable} ${mWord}  onclick="WebDownLoadFile()">
          <input type=button class=SideButton value="设置本地时间"  ${mDisable}  onclick="WebDateTime()">
          <input type=button class=SideButton value="信息传递"  ${mDisable}  onclick="WebSendInformation()">
    	</td>
      </tr>
      <tr>
        <td>      
          <input type=button class=SideButton value="关闭常用工具" ${mDisable}  onclick="WebToolsVisible('Standard',false)">
          <input type=button class=SideButton value="打开常用工具" ${mDisable}  onclick="WebToolsVisible('Standard',true)">
          <input type=button class=SideButton value="关闭格式工具" ${mDisable}  onclick="WebToolsVisible('Formatting',false)">
          <input type=button class=SideButton value="打开格式工具" ${mDisable}  onclick="WebToolsVisible('Formatting',true)">
          <input type=button class=SideButton value="关闭打印按钮" ${mDisable}  onclick="WebToolsEnable('Standard',2521,false);">
          <input type=button class=SideButton value="打开打印按钮" ${mDisable}  onclick="WebToolsEnable('Standard',2521,true);">
          <input type=button class=SideButton value="关闭文档" ${mDisable}  onclick="webform.WebOffice.WebClose();">
      </tr> --%>

		<tr>
			<td height="100%">
				<object id="WebOffice" width="100%" height="100%" classid="clsid:8B23EA28-2009-402F-92C4-59BE0E063499" codebase="<%=basePath %>cab/iWebOffice2009.cab#version=10,7,2,8"></object>
			</td>
		</tr>

		<tr>
			<td><input type=text name="StatusBar" style="border:0px"></td>
		</tr>

	</table>
<script type="text/javascript" for="WebOffice" event="OnMenuClick(vIndex,vCaption)">
  if (vIndex==1){
    WebOpenLocal();     //打开本地文件
  }
  if (vIndex==2){  
    WebSaveLocal();     //保存本地文件
  }
  if (vIndex==3){
    SaveDocument();     //保存正文到服务器上（不退出）
  }
  if (vIndex==5){  
    WebOpenSignature(); //签名印章
  }
  if (vIndex==6){  
    WebShowSignature(); //验证签章
  }
  if (vIndex==8){  
    WebSaveVersion();   //保存版本
  }
  if (vIndex==9){  
    WebOpenVersion();   //打开版本
  }
  if (vIndex==11){
    SaveDocument();     //保存正文到服务器上
    window.parent.$.pdialog.closeCurrent();
  }
  if (vIndex==13){  
    WebOpenPrint();     //打印文档
  }
  if (vIndex==14){
	webform.WebOffice.FullSize();
  }

</script>
<script type="text/javascript" for="WebOffice" event="OnToolsClick(vIndex,vCaption)">
	//响应工具栏事件
	if (vIndex==11){
		webform.WebOffice.VisibleTools('手写批注',false);       //隐藏或显示iWebOffice工具栏 true显示  false隐藏
	} 
	if (vIndex==12){
		webform.WebOffice.VisibleTools('手写批注',true);        //隐藏或显示iWebOffice工具栏 true显示  false隐藏
	}  
	if (vIndex==13){  
		WebOpenPrint();     //打印文档
	}
	if (vIndex==14){
		SaveDocument();     //保存正文到服务器上
	}
	if (vIndex==15){  
		webform.WebOffice.WebLoadTemplate();   //套用模版
	}
	//if (vIndex==-1){webform.WebOffice.Alert(vCaption);}     //在完成相应操作后响应iWebOffice标准工具栏操作铵钮事件，如"手写批注",vCaption="手写批注"
</script>
</form>
</body>
</html>