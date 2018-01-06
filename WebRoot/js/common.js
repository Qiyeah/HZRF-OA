/**
 * 判断字符串是否为空
 * @param strVal 字符串
 * @returns {boolean}
 */
function isBlank(strVal) {
    if (strVal == '' || strVal == null || strVal == undefined) {
        return true;
    } else {
        return false;
    }
}

function notBlank(strVal){
    return !isBlank(strVal);
}

/**
 * 局部刷新
 * @param divId 待刷新div的ID
 * @param url
 */
function ajaxLoad(divId,url){
    var id = "#"+divId;
    $(id).ajaxUrl({url:url});
}

function viewPhoto(imgname) {
	$(this).dialog({id:'viewphoto', url:'Main/Common/viewPhoto?imageName=' + imgname, title: imgname, width:850, height:600});
}

function EntviewPdf(filename) {
    $(this).dialog({id:'entviewpdf', url:'Main/Common/EntviewPdf?fileName=' + filename, title: filename, width:850, height:600});
}

function viewPdf(filename) {
    $(this).dialog({id:'viewpdf', url:'Main/Common/viewPdf?fileName=' + filename, title: filename, width:850, height:600});
}

function viewImg(imgame) {
	$(this).dialog({id:'viewImg', url:'Main/Common/viewImg?imageName=' + imgame, title: imgame, width:800, height:600});
}
/**
 * 按钮提交
 * @param buttonId 按钮ID
 */
exportSubmit = function(buttonId){
    $("#"+buttonId).click();
}

/**
 * 根据临时ID删除附件
 * @param id
 */
function deleteFileByTempId(id) {
    $("#file_" + id).load("Main/Attachment/deleteFileByTempId/" + id);
    $("#file_" + id).remove();
}

