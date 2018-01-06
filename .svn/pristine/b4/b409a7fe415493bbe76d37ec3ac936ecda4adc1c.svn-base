<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript" src="js/baucheHighShotMeter.js"></script>
<div class="bjui-pageContent tableContent">
    <div id="imgDiv" style="width:160px; height: 590px; background:#C7EDCC; border: 1px solid black;float:left;overflow-x: hidden;overflow-y: auto;">
        <%--<img id="img1" alt="img1" src="file://D:\Img_1.png" style="MARGIN-TOP:3px; width: 100%;" />
        <img id="img2" alt="img2" src="file://D:\Img_2.png" style="MARGIN-TOP:3px; width: 100%;" />
        <img id="img3" alt="img3" src="file://D:\Img_3.png" style="MARGIN-TOP:3px; width: 100%;" />--%>
    </div>

    <div style="width:600px; height: 590px; border: 1px solid black; background:#C7EDCC;  float:left">
        <object id="axCam_Ocx" style=" width:100%; height:544px;"
                classid="clsid:ce2d72f2-ad28-4013-a24b-c3f76c5a1944"  codebase="CamOcx.cab #version=1,0,0,1">
        </object>

        <div align="center" style=" width:100%; height:46px">
            <button type = "button" class="btn btn-default" onclick = "zoomOut();">放大</button>
            <button type = "button" class="btn btn-default" onclick = "zoomIn();">缩小</button>
            <button type = "button" class="btn btn-default" onclick = "roateL();">左旋</button>
            <button type = "button" class="btn btn-default" onclick = "roateR();">右旋</button>
            <button type = "button" class="btn btn-default" onclick = "bestSize();">适合大小</button>
            <button type = "button" class="btn btn-default" onclick = "trueSize();">实际大小</button>
        </div>
    </div>


    <div style="width:260px;height:590px;background:#C7EDCC; border: 1px solid black;float:left">
        <table style="width:100%;">
            <tr>
                <td class="style5" style="font-size: medium">
                    设备
                </td>
                <td class="style1">
                    <select id="DeviceName" name="D1" onchange = "changeDevice()">
                    </select>
                </td>
            </tr>
        </table>
        <table style="border: 1px; width:100%; height: 67px;">
            <tr>
                <td>
                    <button id="Button1" type="button" class="btn btn-default" onclick = "startVideo();">打开摄像头</button>
                </td>
                <td>
                    <button id="Button3" type="button" class="btn btn-default" onclick = "capture();">拍照</button>
                </td>
                <%--<td class="style11" >
                    <button id="Button5" type="button" class="btn btn-default" onclick = "wiseCapture();">智能连拍</button>
                </td>--%>

                <td>
                    <button id="Button2" type="button" class="btn btn-default" onclick = "closeVideo();">关闭摄像头</button>
                </td>
            </tr>
        </table>

        <%--<table style="width: 100%;">
            <tr>
                <td class="style15">
                    <button type = "button" class="btn btn-default" onclick = "convertToTiff();">合并TIFF</button>
                </td>
            </tr>

        </table>--%>

        <hr />
        <table style="width: 100%;">
            <tr>
                <td>
                    <button type = "button" class="btn btn-default" onclick = "convertToPDF();">合并PDF</button>
                </td>
                <td>
                    <button id="Button7" type="button" class="btn btn-default" onclick = "uploadFile();" >上传PDF</button>
                </td>
            </tr>
        </table>
        <hr/>
        <div style="width: 100%;height: 65%;">
            <label>操作的内容</label>
            <div id="infoDiv" readonly style="width: 100%;height:100%;overflow-x: hidden;overflow-y: auto;">
            </div>
        </div>
    </div>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn btn-close" data-icon="close">关闭</button></li>
		<li><button type="submit" class="btn btn-default" data-icon="save">保存</button></li>
	</ul>
</div>

<script>
    $(document).ready(function(){
        getDevice();
        closeVideo(); // 先关闭摄像头
        /*startVideo(); // 开启摄像头时设置分辨率*/
    });
</script>

<script id="tpl-photoImg" type="text/template">
    <div>
        <a title="删除" href="javascript:void(0);" onclick="deletePhoto(this);" style="position: relative;left: 148px; top: 0px;"><i class="fa fa-times-circle"></i></a>
        <img id="{{imgId}}" alt="{{imgAlt}}" src="{{imgSrc}}" style="MARGIN-TOP:3px; width: 100%;" />
    </div>
</script>

<script id="tpl-uploadedFile" type="text/template">
    <div id="file_{{fileId}}" style="height: 25px; line-height: 25px;">
        <span style="display: none;">
            <input name="fjid" type="checkbox" checked="" value="{{fileId}}">
        </span>
        <a class="btnAttach"></a>
        <span>
            <a title="点击下载" onclick="doc_filedownload1(this); return false;" href="Main/Attachment/downloadByTempId/{{fileId}}">
                {{fileName}}
            </a>
        </span>
        <a href="javascript:deleteFileByTempId('{{fileId}}');">
            <img height="13" align="middle" src="images/cancel.png">
        </a>
    </div>
</script>