package com.oa.controller.export;

import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.oa.model.system.workflow.T_Workflow;
import com.oa.util.ExportExcel;

/**
 * 发文归档导出
 * 
 * @Author Andersen.Liu mail: yawen199@163.com Date: 15-04-23 Time: 16:12
 */
@SuppressWarnings("rawtypes")
public class ExportSendCab extends ExportExcel {

    @SuppressWarnings("unchecked")
    @Override
    protected void setContent(Collection dataSet, String pattern, HSSFWorkbook workbook, HSSFSheet sheet,
	    HSSFCellStyle style2, HSSFPatriarch patriarch) {
	HSSFRow row;
	Iterator<T_Workflow> it = dataSet.iterator();
	int index = 1;

	while (it.hasNext()) {
	    index++;
	    row = sheet.createRow(index);
	    T_Workflow o = it.next();
	    String textValue;

	    textValue = (index - 1) + "";
	    setContentCell(workbook, style2, row, textValue, 0);

	    textValue = o.get("starttime").toString().substring(0, 16);
	    setContentCell(workbook, style2, row, textValue, 1);

	    textValue = o.getStr("dpname");
	    setContentCell(workbook, style2, row, textValue, 2);

	    textValue = o.getStr("docno");
	    setContentCell(workbook, style2, row, textValue, 3);

	    textValue = o.getStr("doctitle");
	    setContentCell(workbook, style2, row, textValue, 4);
	}
    }

}
