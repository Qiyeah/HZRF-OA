package com.oa.controller.export;

import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.oa.model.approve.T_Leave_Approve;
import com.oa.util.ExportExcel;

/**
 * 请休假详情导出
 * 
 * @Author Andersen.Liu mail: yawen199@gmail.com Date: 15-01-23 Time: 11:42
 */
@SuppressWarnings("rawtypes")
public class ExportLeaveDetails extends ExportExcel {

    @SuppressWarnings("unchecked")
    @Override
    protected void setContent(Collection dataSet, String pattern, HSSFWorkbook workbook, HSSFSheet sheet,
	    HSSFCellStyle style2, HSSFPatriarch patriarch) {
	HSSFRow row;
	Iterator<T_Leave_Approve> it = dataSet.iterator();
	int index = 1;

	while (it.hasNext()) {
	    index++;
	    row = sheet.createRow(index);
	    T_Leave_Approve o = it.next();
	    String textValue;

	    textValue = o.getStr("uname");
	    setContentCell(workbook, style2, row, textValue, 0);

	    textValue = o.getStr("dpname");
	    setContentCell(workbook, style2, row, textValue, 1);

	    textValue = o.getStr("type");
	    setContentCell(workbook, style2, row, textValue, 2);

	    textValue = o.getDate("begindate").toString();
	    setContentCell(workbook, style2, row, textValue, 3);

	    textValue = o.getDate("enddate").toString();
	    setContentCell(workbook, style2, row, textValue, 4);
	}
    }

}
