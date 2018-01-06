<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="pageContent">
	<form method="post" action="Main/file/createFolder"  class="pageForm required-validate" onSubmit="return validateCallback(this, dialogAjaxDone);">
        <input name="pid" type="hidden"  value="${pid }"/>
        <input name="pfoldername" type="hidden" size="30" value="${folder.path }"/>
        <div class="pageFormContent" layoutH="58">
            <table class="wordInfo" align="center" style="width: 100%;">
                <tr>
                    <td style="width: 25%;" class="title">目 录 名</td>
                    <td style="width: 75%;">
                        <input class="required" name="foldername" type="text" style="width: 95%;" />
                    </td>
                </tr>
                <tr>
                    <td class="title">创建人ID</td>
                    <td>${loginModel.dlId}</td>
                </tr>
                <tr>
                    <td class="title">备　　注</td>
                    <td>
                        <textarea name="remark" cols="50" rows="6" style="width: 100%;"></textarea>
                    </td>
                </tr>
            </table>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
	</form>
</div>