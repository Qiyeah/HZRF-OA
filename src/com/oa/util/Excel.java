package com.oa.util;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class Excel {

    /** 读取excel操作对象 */
    private WorkbookSettings bookSetting;
    private Workbook book;
    private Sheet sheet;
    private Cell cell;

    /** 创建excel操作对象 */

    /** file参数构造 */
    public Excel(File file) {
	if (book == null) {
	    try {
		bookSetting = new WorkbookSettings();
		bookSetting.setEncoding("ISO-8859-1");
		book = Workbook.getWorkbook(file, bookSetting);
		sheet = book.getSheet(0);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    /** 无参构造 */
    public Excel() {
    }

    public Workbook getBook() {
	return book;
    }

    public void setBook(Workbook book) {
	this.book = book;
    }

    public Sheet getSheet() {
	return sheet;
    }

    public void setSheet(Sheet sheet) {
	this.sheet = sheet;
    }

    public Cell getCell() {
	return cell;
    }

    public void setCell(Cell cell) {
	this.cell = cell;
    }

    /**
     * @param column
     *            行
     * @param row
     *            列 ---------- a|b| ------ column 1 row 0 取 b
     */
    public String getValue(int column, int row) {
	cell = sheet.getCell(column, row);
	return cell.getContents();
    }

    /**
     * 得到列数
     * 
     * @return
     */
    public int getColumnum() {
	return sheet.getColumns();
    }

    /**
     * 得到行数
     * 
     * @return
     */
    public int getRownum() {
	return sheet.getRows();
    }

    /***
     * 关闭close
     */
    public void close() {
	if (book != null) {
	    book.close();
	}
    }

}
