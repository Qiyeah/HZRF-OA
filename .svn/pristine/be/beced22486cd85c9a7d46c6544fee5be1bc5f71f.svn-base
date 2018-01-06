var savePath = "d:\\HZRFOA\\Image"; // 图片保存路径
var fileType = 2;   // 图片类型--png
var fileColorType = 0;   // 图片颜色格式--彩色
var CamID  = 0;  // 摄像头ID(默认第一个摄像头)
var width = 2048;   // 视屏宽度
var height = 1536;   // 视屏高度
var SupportFormat; // 设备支持的视频格式代号：1->MJPG; 2->YUY2
var OpenFormat; // 打开格式：0->YUY；1->MJPG
var captureCount = 0;
var isOpen = false;
var isWiseCapture =false;
var fileBasePath = "d:\\HZRFOA";
var pdfPath = fileBasePath + "\\convert.pdf";
var tiffPath = fileBasePath + "\\convert.tiff";
var combinePath = fileBasePath + "\\combineImg.jpg";
var combineDirection = 1;   // 图片合并方向，1->垂直合并  2->水平合并
var photoImgTplId = "tpl-photoImg"; // 图片模板ID
var uploadedFileTplId = "tpl-uploadedFile"; // 已上传文件模板ID
var imgDivId = "imgDiv";    // 显示图片的容器
var infoDivId = "infoDiv";    // 显示提示信息的容器
var fileListDivId = "fileList";    // 显示已上传文件信息的容器

//获取设备;
function getDevice() {
    var devCount = axCam_Ocx.GetDevCount();
    var deviceNameSelect = document.getElementById("DeviceName");

    for (var i = 0; i < devCount; i++)
    {
        var devName = axCam_Ocx.GetDevFriendName(i);
        var objOption = document.createElement("option");
        objOption.text = devName;
        objOption.value = i;
        deviceNameSelect.options.add(objOption);
    }
    if (devCount > 0){
        deviceNameSelect.value = 0;
    }

    var formatSum  = 0;  //设备支持的视频格式代号总和
    var sCount  = axCam_Ocx.GetFormatCount(CamID);   //获取设备支持的视频格式数目
    for (var k = 0; k < sCount; k++){
        formatSum = formatSum + axCam_Ocx.GetFormatNumber(k);    //GetFormatNumber（）获取设备支持的视频格式代号
    }

    //判断视频格式代号总和的值
    videoFormat(formatSum);
}

//切换摄像头
function changeDevice()
{
    closeVideo(); // 先关闭摄像头
    var devObj = document.getElementById("DeviceName");
    CamID = devObj.selectedIndex;

    var formatSum = 0;  // 设备支持的视频格式代号总和
    var sCount = axCam_Ocx.GetFormatCount(CamID);   // 获取设备支持的视频格式数目
    for (var k = 0; k < sCount; k++) {
        formatSum = formatSum + axCam_Ocx.GetFormatNumber(k);    // GetFormatNumber（）获取设备支持的视频格式代号
    }

    // 判断视频格式代号总和的值
    videoFormat(formatSum);
    // 开启摄像头
    startVideo();
}

/**
 * 判断视频格式代号总和的值
 * @param FormatSum
 */
function videoFormat(FormatSum){
    switch(FormatSum)
    {
        case 1:   //（设备只支持MJPG格式）
        {
            OpenFormat = 1 ;     //打开格式为MJPG
            SupportFormat = 1;
        }
            break;
        case 2:   //（设备只支持YUY2格式）
        {
            OpenFormat = 0 ;     //打开格式为YUY
            SupportFormat = 2;
        }
            break;
        case 3:   //（设备支持MJPG与YUY2两种格式）
        {
            OpenFormat = 1 ;     //默认打开格式为MJPG
            SupportFormat = 1;
        }
            break;
    }
}

//放大
function zoomOut() {
    axCam_Ocx.CAM_ZoomOut();
}

//缩小
function zoomIn() {
    axCam_Ocx.CAM_ZoomIn();
}

//左旋
function roateL() {
    axCam_Ocx.CAM_RotateL();
}

//右旋
function roateR() {
    axCam_Ocx.CAM_RotateR();
}

//适合大小
function bestSize() {
    axCam_Ocx.BestSize();
}

//实际大小
function trueSize() {
    axCam_Ocx.TrueSize();
}

/**
 * 初始化视屏设置
 */
function initVideo(){
    // 设置自动剪裁（1,0）。不剪裁(0,0),手动剪裁(0,1)
    axCam_Ocx.SetAutoCut(1);
    axCam_Ocx.CusCrop(0);
    // 设置图片格式，0：bmp、1：jpg、2：png、3：tiff、4：gif、5：pdf；
    axCam_Ocx.SetFileType(fileType);
    // 设置图片颜色格式， 0：彩色、1：灰度、2：黑白
    axCam_Ocx.SetImageColorMode(fileColorType);
}

//开启摄像头
function startVideo()
{
    initVideo();
    var success = axCam_Ocx.CAM_Open(CamID, OpenFormat, width, height);
    if (success == 0){
        showInfo("开启设备成功，请稍候！");
    }else{
        showInfo("开启设备失败");
    }
}

//关闭摄像头
function closeVideo()
{
    axCam_Ocx.CAM_Close()
    showInfo("关闭设备");
}

// 显示提示信息
function showInfo(op) {
    $("#"+infoDivId).append("<br/>"+op);
}

//抓图拍照
function capture() {
    captureCount = captureCount + 1;
    var objFSO;
    try{
        objFSO = new ActiveXObject("Scripting.FileSystemObject");
    }catch(err){
        regsvrScrrun();
        objFSO = new ActiveXObject("Scripting.FileSystemObject");
    }

    // 检查文件夹是否存在
    if (!objFSO.FolderExists(savePath)) {
        // 创建文件夹
        var strFolderName = objFSO.CreateFolder(savePath);
    }

    var path = savePath + "\\Img_" + captureCount;
    var suffix = getFileSuffix(fileType);
    path = path + suffix;
    // 拍照
    axCam_Ocx.CaptureImage(path);

    var data = {imgId:"photoId"+captureCount,imgAlt:"图片"+captureCount,imgSrc:path};
    var imgHtml = template(photoImgTplId, data);
    var imgDiv = $("#"+imgDivId);
    imgDiv.append(imgHtml);
    imgDiv.initui();

    var info="拍照成功<br/>" + "位置:" + path;
    showInfo(info);
}

//智能连拍
function wiseCapture() {

    var objFSO = new ActiveXObject("Scripting.FileSystemObject");
    // 检查文件夹是否存在
    if (!objFSO.FolderExists(savePath)) {
        // 创建文件夹
        var strFolderName = objFSO.CreateFolder(savePath);
    }

    if (isWiseCapture == false) {
        axCam_Ocx.WiseCapture(1, strFolder);
        isWiseCapture = true;
    }
    else {
        axCam_Ocx.WiseCapture(0, strFolder);
        isWiseCapture = false;
    }
}

//条码识别
function readQrCode()
{
    var CodeStr = axCam_Ocx.GetQRcodeContent("");
    showInfo("二维码内容:" + CodeStr);
}


//合并PDF
function convertToPDF(imgNameArray){
    var fileCount = axCam_Ocx.GetDirectoryFileCount(savePath);
    var imgNameArray = [];
    for(var i=1;i <= fileCount;i++){
        var fileName = axCam_Ocx.GetFileNameOnDirectory(i);
        imgNameArray.push(fileName);
        console.log(i+":"+fileName);
    }
    var imgNameArrayLength = imgNameArray.length;
    for(var i = 0; i < imgNameArrayLength; i++){
        var imgName = imgNameArray[i];
        var imagePath = savePath + "\\" + imgName;
        console.log("imagePath"+(i+1)+":"+imagePath);
        axCam_Ocx.AddPDFImageFile(imagePath,"",0);
    }
    var timestamp = new Date().getTime();
    pdfPath = fileBasePath + "\\" + timestamp + ".pdf";
    axCam_Ocx.Convert2PDF(pdfPath);
    showInfo("合成PDF成功，PDF路径:"+pdfPath);
}

//合并TIFF
function convertToTiff(imgNameArray) {
    var imgNameArrayLength = imgNameArray.length;
    for(var i = 0; i < imgNameArrayLength; i++){
        var imgName = imgNameArray[i];
        var imagePath = savePath + "\\" + imgName;
        axCam_Ocx.AddPDFImageFile(imagePath);
    }
    axCam_Ocx.Convert2Tiff(tiffPath);
    showInfo("合成TIFF成功，TIFF路径:"+tiffPath);
}

/**
 * 图片合并
 * @param direction 合并方向
 * @param imgName1 图片1文件名
 * @param imgName2 图片2文件名
 */
function combineTwoImage(direction,imgName1,imgName2){
    var imgPath1 = savePath + "\\" + imgName1;
    var imgPath2 = savePath + "\\" + imgName2;
    axCam_Ocx.CombineTwoImage(combinePath, imgPath1, imgPath2, direction);
}

/**
 * 上传文件
 *
 */
function uploadFile() {
    console.log("开始上传文件...");
    console.log("文件路径："+pdfPath);
    var pdfName = pdfPath.replace(fileBasePath+"\\","");
    console.log("pdfName:"+pdfName);
    var tempId = pdfName.substr(0,pdfName.indexOf(".pdf"));
    console.log("tempId:"+tempId);
    var success = axCam_Ocx.UpdataFile("http://localhost:8083/Main/Attachment/savePDF?tempId=temp"+tempId, pdfPath);
    if(success == 0){
        showInfo("文件上传成功！");
        console.log("文件上传成功！");
        var data = {fileId:"temp"+tempId,fileName:pdfName};
        var uploadedFileHtml = template(uploadedFileTplId, data);
        $("#"+fileListDivId).append(uploadedFileHtml);
        $("#"+fileListDivId).initui();
    }else{
        showInfo("文件上传失败！");
        console.log("文件上传失败！");
    }
}

function getFileSuffix(fileType){
    var suffix = "";
    switch(fileType){
        case 0:suffix=".bmp";break;
        case 1:suffix=".jpg";break;
        case 2:suffix=".png";break;
        case 3:suffix=".tiff";break;
        case 4:suffix=".gif";break;
        case 5:suffix=".pdf";break;
    }
    return suffix;
}

/**
 * 清除临时文件
 */
function clearBasePath(){
    var photoFileCount = axCam_Ocx.GetDirectoryFileCount(savePath);
    if(photoFileCount > 0){
        console.log("开始删除文件的照片...");
        for(var i=1;i <= photoFileCount;i++){
            var fileName = axCam_Ocx.GetFileNameOnDirectory(i);
            var filePath = savePath + "\\" + fileName;
            var success = axCam_Ocx.DeleteFile(filePath);
            if(success == 1){
                console.log("删除文件:"+filePath);
            }
        }
        console.log("文件的照片删除完成");
    }
    var pdfFileCount = axCam_Ocx.GetDirectoryFileCount(fileBasePath);
    if(pdfFileCount > 0){
        for(var i=1;i <= pdfFileCount;i++){
            var fileName = axCam_Ocx.GetFileNameOnDirectory(i);
            var filePath = fileBasePath + "\\" + fileName;
            var success = axCam_Ocx.DeleteFile(filePath);
            if(success == 1){
                console.log("删除文件:"+filePath);
            }
        }
    }
}

function deletePhoto(aLink) {
    var deleteALink = $(aLink);
    var deleteImgDiv = deleteALink.closest("div");  // 将要删除的包含图片的div
    var divIndex = $("#"+imgDivId).find("div").index(deleteImgDiv);
    console.log("divIndex："+divIndex);
    var fileCount = axCam_Ocx.GetDirectoryFileCount(savePath);
    if(fileCount > 0){
        var fileIndex = parseInt(divIndex) + 1;
        var fileName = axCam_Ocx.GetFileNameOnDirectory(fileIndex);
        var filePath = savePath + "\\" + fileName;
        var success = axCam_Ocx.DeleteFile(filePath);
        if(success == 1){
            var op = "删除文件:"+filePath;
            showInfo(op)
            console.log(op);
        }
    }
    deleteImgDiv.remove();
}

/**
 * 注册dll
 */
function regsvrScrrun(){
    var wsh = new ActiveXObject('WSCript.shell');
    wsh.run('regsvr32 scrrun.dll');
}