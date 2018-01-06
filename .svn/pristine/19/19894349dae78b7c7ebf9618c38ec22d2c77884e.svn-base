package com.oa.controller.export;

import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.oa.model.document.T_Doc_Achieve;
import com.oa.util.ExportExcel;


/**
 * 资料列表导出
 * 
 * @Author Andersen.Liu mail: yawen199@gmail.com Date: 15-01-23 Time: 15:16
 */
@SuppressWarnings("rawtypes")
public class ExportDocAchieve extends ExportExcel {

	@SuppressWarnings("unchecked")
	@Override
	protected void setContent(Collection dataSet, String pattern, HSSFWorkbook workbook, HSSFSheet sheet, HSSFCellStyle style2, HSSFPatriarch patriarch) {
		HSSFRow row;
		Iterator<T_Doc_Achieve> it = dataSet.iterator();
		int index = 1;

		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T_Doc_Achieve o = it.next();
			String textValue;
            textValue = String.valueOf(index-1);
			setContentCell(workbook, style2, row, textValue, 0);

            textValue = o.getDate("receivedate").toString();
			setContentCell(workbook, style2, row, textValue, 1);

			textValue = o.getStr("unit");
			setContentCell(workbook, style2, row, textValue, 2);

			textValue = o.getStr("docno");
			setContentCell(workbook, style2, row, textValue, 3);

			textValue = o.getStr("title");
			setContentCell(workbook, style2, row, textValue, 4);

			textValue = o.getStr("security");
			setContentCell(workbook, style2, row, textValue, 5);

			if (null != o.get("degree")) {
				textValue = o.getStr("degree").equals("1") ? "急件" : (o.getStr("degree").equals("0") ? "平件" : "");
			} else {
				textValue = "";
			}
			setContentCell(workbook, style2, row, textValue, 6);

            textValue = null != o.get("count") ? o.get("count").toString() : "";
            setContentCell(workbook, style2, row, textValue, 7);

			textValue = o.getStr("dpname");
			setContentCell(workbook, style2, row, textValue, 8);

			textValue = o.getStr("memo");
			setContentCell(workbook, style2, row, textValue, 9);
			
			if (null != o.get("status")) {
			    textValue = o.getStr("status").equals("0") ? "未分发" : (o.getStr("status").equals("1") ? "已分发" : "");
			}else{
			    textValue ="";
			}
			setContentCell(workbook, style2, row, textValue, 10);
		}
	}

}
