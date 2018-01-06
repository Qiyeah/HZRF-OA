package com.oa.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * word 基本操作方法
 */
@SuppressWarnings("all")
public class Word {

    private ActiveXComponent word; // word运行程序对象
    private Dispatch doc; // word文档
    private Dispatch documents; // 所有word文档集合
    private Dispatch selection; // 选定的范围或插入点
    private Dispatch table; // 表格
    private Dispatch font; // 字体
    private boolean saveOnExit = true; // 保存退出

    public ActiveXComponent getWord() {
	return word;
    }

    public void setWord(ActiveXComponent word) {
	this.word = word;
    }

    public Dispatch getDoc() {
	return doc;
    }

    public void setDoc(Dispatch doc) {
	this.doc = doc;
    }

    public Dispatch getDocuments() {
	return documents;
    }

    public void setDocuments(Dispatch documents) {
	this.documents = documents;
    }

    public Dispatch getSelection() {
	return selection;
    }

    public void setSelection(Dispatch selection) {
	this.selection = selection;
    }

    public Dispatch getTable() {
	return table;
    }

    public void setTable(Dispatch table) {
	this.table = table;
    }

    public Dispatch getFont() {
	return font;
    }

    public void setFont(Dispatch font) {
	this.font = font;
    }

    /**
     * 构造函数
     */
    public Word() {
	ComThread.InitMTA();// 初始化jacob线程

	if (word == null || word.m_pDispatch == 0) {
	    word = new ActiveXComponent("Word.Application");
	    word.setProperty("Visible", new Variant(false));
	}
	if (documents == null || documents.m_pDispatch == 0) {
	    documents = word.getProperty("Documents").toDispatch();
	}
    }

    /**
     * 设置退出时参数   boolean true-退出时保存文件，false-退出时不保存文件  
     */
    public void setSaveOnExit(boolean saveOnExit) {
	this.saveOnExit = saveOnExit;
    }

    /**
     * 创建一个新的word
     */
    public void createNewDocument() {
	doc = Dispatch.call(documents, "Add").toDispatch();
	selection = Dispatch.get(word, "Selection").toDispatch();
    }

    /**
     * 打开一个已存在的文档  
     */
    public void openDocument(String docPath) {
	this.closeDocument();
	doc = Dispatch.call(documents, "Open", docPath).toDispatch();
	selection = Dispatch.get(word, "Selection").toDispatch();
    }

    /**
     * * 把选定的内容或插入点向上移动   * @param pos 移动的距离  
     */
    public void moveUp(int pos) {
	if (selection == null) {
	    selection = Dispatch.get(word, "Selection").toDispatch();
	}
	for (int i = 0; i < pos; i++) {
	    Dispatch.call(selection, "MoveUp");
	}
    }

    /**
     * 把选定的内容或者插入点向下移动
     *
     * @param pos
     *            移动的距离  
     */
    public void moveDown(int pos) {
	if (selection == null) {
	    selection = Dispatch.get(word, "Selection").toDispatch();
	}
	for (int i = 0; i < pos; i++) {
	    Dispatch.call(selection, "MoveDown");
	}
    }

    /**
     * 把选定的内容或者插入点向左移动  
     *
     * @param pos
     *            移动的距离  
     */
    public void moveLeft(int pos) {
	if (selection == null) {
	    selection = Dispatch.get(word, "Selection").toDispatch();
	}
	for (int i = 0; i < pos; i++) {
	    Dispatch.call(selection, "MoveLeft");
	}
    }

    /**
     * 把选定的内容或者插入点向右移动  
     *
     * @param pos
     *            移动的距离  
     */
    public void moveRight(int pos) {
	if (selection == null) {
	    selection = Dispatch.get(word, "Selection").toDispatch();
	}
	for (int i = 0; i < pos; i++) {
	    Dispatch.call(selection, "MoveRight");
	}
    }

    /**
     * 把插入点移到第二页
     */
    public void moveNextPage() {
	if (selection == null) {
	    selection = Dispatch.get(word, "Selection").toDispatch();
	}
	Dispatch.call(selection, "EndKey", "6");
    }

    /**
     * 把插入点移动到文件首位置  
     */
    public void moveStart() {
	if (selection == null) {
	    selection = Dispatch.get(word, "Selection").toDispatch();
	}
	Dispatch.call(selection, "HomeKey", new Variant(6));
    }

    /**
     * 光标移动到文档最后
     */
    public void moveEnd() {
	Dispatch.call(selection, "EndKey", "6");
    }

    /**
     * 换行
     */
    public void enter() {
	Dispatch.call(selection, "TypeParagraph");
    }

    /**
     * 换页
     */
    public void nextPage() {
	Dispatch.call(selection, "InsertBreak");
    }

    /**
     * 把光标转换为上标
     */
    public void insertSuper(boolean flag) {
	Dispatch font = Dispatch.get(selection, "Font").toDispatch();
	Dispatch.put(font, "Superscript", new Variant(flag));
    }

    /**
     * 把光标转换为下标
     */
    public void insertSub(boolean flag) {
	Dispatch font = Dispatch.get(selection, "Font").toDispatch();
	Dispatch.put(font, "Subscript", new Variant(flag));
    }

    /**
     * 从选定内容或插入点开始查找文本  
     *
     * @param toFindText
     *            要查找的文本  
     * @return boolean true-查找到并选中该文本，false-未查找到文本  
     */
    public boolean find(String toFindText) {
	if (toFindText == null || toFindText.equals(""))
	    return false;
	Dispatch find = word.call(selection, "Find").toDispatch();// 从selection所在位置开始查询
	Dispatch.put(find, "Text", toFindText);// 设置要查找的内容
	Dispatch.put(find, "Forward", "True");// 向前查找
	Dispatch.put(find, "Format", "True");// 设置格式
	Dispatch.put(find, "MatchCase", "True");// 大小写匹配
	Dispatch.put(find, "MatchWholeWord", "True");// 全字匹配
	return Dispatch.call(find, "Execute").getBoolean();// 查找并选中
    }

    /**
     * 把选定选定内容设定为替换文本  
     *
     * @param toFindText
     *            查找字符串  
     * @param newText
     *            要替换的内容
     * @return  
     */
    public boolean replaceText(String toFindText, String newText) {
	moveStart();
	if (!find(toFindText)) {
	    return false;
	}
	Dispatch.put(selection, "Text", newText);
	return true;
    }

    /**
     * * 全局替换文本   * @param toFindText 查找字符串   * @param newText 要替换的内容  
     */
    public void replaceAllText(String toFindText, String newText) {
	moveStart();
	while (find(toFindText)) {
	    Dispatch.put(selection, "Text", newText);
	    Dispatch.call(selection, "MoveRight");
	}
    }

    /**
     * 在当前插入点插入字符串  
     *
     * @param newText
     *            要插入的新字符串  
     */
    public void insertText(String newText) {
	Dispatch.call(selection, "TypeText", newText);
    }

    /**
     * 插入结果
     */
    public void insertResult(String value) {
	if (value.contains("e") || value.contains("E")) {
	    String[] values = value.split("e|E");
	    Dispatch.call(selection, "TypeText", values[0] + "×10");
	    this.insertSuper(true);
	    this.insertText(values[1]);
	} else {
	    this.insertText(value);
	}
    }

    /**
     * 插入报告备注，调用此方法前，已判断是否为remark和有特殊字符
     */
    public void replaceRemark(String toFindText, String remarks) {
	String[] remark = remarks.split("e|E");
	for (int i = 0; i < remark.length - 1; i++) {// 最后一个字符串片段就不用进行操作
	    if (i == 0) {// 第一个要作替换处理，起定位作用
		this.replaceText(toFindText, remark[i]);
		this.moveRight(1);// 右移一格
	    }
	    Dispatch.call(selection, "TypeText", "×10");
	    // 到，之间还有个单位，所以这里不能通过,来查找
	    int index = remark[i + 1].indexOf(",");
	    // int index = StringUtil.getLowerIndex(remark[i+1]);
	    String fornt = remark[i + 1].substring(0, index);
	    String last = remark[i + 1].substring(index);
	    this.insertSuper(true);
	    this.insertText(fornt);
	    this.insertSuper(false);
	    this.insertText(last);
	}
    }

    /**
     * @param toFindText
     *            要查找的字符串  
     * @param imagePath
     *            图片路径  
     * @return  
     */
    public boolean replaceImage(String toFindText, String imagePath) {
	if (!find(toFindText)) {
	    return false;
	}
	Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
	return true;
    }

    /**
     *   * 全局替换图片   * @param toFindText 查找字符串   * @param imagePath 图片路径  
     */
    public void replaceAllImage(String toFindText, String imagePath) {
	while (find(toFindText)) {
	    Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
	    Dispatch.call(selection, "MoveRight");
	}
    }

    /**
     *  * 在当前插入点插入图片   * @param imagePath 图片路径  
     */
    public void insertImage(String imagePath) {
	Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
    }

    /**
     * 获取表格
     *
     * @param tableIndex
     * @return Dispatch table表格
     */
    public Dispatch getTable(int tableIndex) {
	Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();// 所有表格
	Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();// 要填充的表格
	return table;
    }

    /**
     * 合并单元格
     *
     * @param fstCellRowIdx
     * @param fstCellColIdx
     * @param secCellRowIdx
     * @param secCellColIdx
     *             
     */
    public void mergeCell(Dispatch table, int fstCellRowIdx, int fstCellColIdx, int secCellRowIdx, int secCellColIdx) {
	Dispatch fstCell = Dispatch.call(table, "Cell", new Variant(fstCellRowIdx), new Variant(fstCellColIdx))
		.toDispatch();
	Dispatch secCell = Dispatch.call(table, "Cell", new Variant(secCellRowIdx), new Variant(secCellColIdx))
		.toDispatch();
	Dispatch.call(fstCell, "Merge", secCell);
    }

    /**
     * 在指定的单元格里填写数据
     * 
     * @param cellRowIdx
     * @param cellColIdx
     * @param txt
     *             
     */
    public void putTxtToCell(Dispatch table, int cellRowIdx, int cellColIdx, String txt) {
	Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
	Dispatch.call(cell, "Select");
	Dispatch.put(selection, "Text", txt);
    }

    /**
     * 在指定的单元格里填写数据
     * 
     * @param cellRowIdx
     * @param cellColIdx
     * @param align
     *            对齐格式 默认:居左 0:居左 1:居中 2:居左 3:两端对齐 4:分散对齐
     * @param bold
     *            是否粗体 默认:否 0:否 1:是
     * @param txt
     *             
     */
    public void putTxtToCell(Dispatch table, int cellRowIdx, int cellColIdx, int align, int bold, String txt) {
	Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
	Dispatch.call(cell, "Select");
	setParaFormat(align, 0);
	if (bold == 1) {
	    Dispatch font = Dispatch.get(selection, "Font").toDispatch();
	    Dispatch.put(font, "Bold", new Variant(true));
	}
	Dispatch.put(selection, "Text", txt);
    }

    /**
     * 在指定的单元格里填写数据  
     *
     * @param table
     * @param cellRowIdx
     * @param cellColIdx
     * @param txt
     *             
     */
    public void putTxtToCellRPT(Dispatch table, int cellRowIdx, int cellColIdx, String txt) {
	Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
	Dispatch.put(cell, "VerticalAlignment", new Variant(1));
	Dispatch.call(cell, "Select");
	setParaFormat(1, 0);
	// Dispatch font = Dispatch.get(selection, "Font").toDispatch();
	// Dispatch.put(font, "Bold", new Variant(true));
	// Dispatch.put(font, "Size", "9");
	// Dispatch.put(selection, "Font", font);
	Dispatch.put(selection, "Text", txt);
    }

    /**
     * 在指定的单元格里填写数据  
     *
     * @param table
     *             
     * @param cellRowIdx
     *             
     * @param cellColIdx
     *             
     * @param txt
     *             
     */
    public void putTxtToCellRPC(Dispatch table, int cellRowIdx, int cellColIdx, int align, String txt) {
	Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
	Dispatch.put(cell, "VerticalAlignment", new Variant(1));
	Dispatch.call(cell, "Select");
	setParaFormat(align, 0);
	Dispatch font = Dispatch.get(selection, "Font").toDispatch();
	Dispatch.put(font, "Bold", new Variant(false));
	Dispatch.put(font, "Size", "10");
	Dispatch.put(selection, "Text", txt);
    }

    /**
     * 在指定的单元格里填写数据  
     *
     * @param table
     *             
     * @param cellRowIdx
     *             
     * @param cellColIdx
     *             
     * @param txt
     *             
     */
    public void putTxtToCellRPF(Dispatch table, int cellRowIdx, int cellColIdx, String txt) {
	Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
	Dispatch.put(cell, "VerticalAlignment", new Variant(1));
	Dispatch.call(cell, "Select");
	setParaFormat(1, 0);
	Dispatch font = Dispatch.get(selection, "Font").toDispatch();
	Dispatch.put(font, "Bold", new Variant(true));
	Dispatch.put(font, "Size", "11");
	Dispatch.put(selection, "Text", txt);
    }

    /**
     * 在当前文档拷贝剪贴板数据  
     *
     * @param pos
     *            当前文档指定的位置  
     */
    public void pasteExcelSheet(String pos) {
	this.moveStart();
	if (this.find(pos)) {
	    Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
	    Dispatch.call(textRange, "Paste");
	}
    }

    /**
     * 在当前文档指定的位置拷贝表格  
     *
     * @param pos
     *            当前文档指定的位置  
     * @param tableIndex
     *            被拷贝的表格在word文档中所处的位置  
     */
    public void copyTable(String pos, Dispatch table) {
	Dispatch range = Dispatch.get(table, "Range").toDispatch();
	Dispatch.call(range, "Copy");
	if (this.find(pos)) {
	    Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
	    Dispatch.call(textRange, "Paste");
	}
    }

    /**
     * 默认拷贝到下一新页
     */
    public void copyTable(Dispatch table) {
	Dispatch range = Dispatch.get(table, "Range").toDispatch();
	Dispatch.call(range, "Copy");
	moveEnd();
	nextPage();
	Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
	Dispatch.call(textRange, "Paste");
    }

    /**
     * 默认拷贝到下一行
     */
    public void copyTableEnter(Dispatch table) {
	Dispatch range = Dispatch.get(table, "Range").toDispatch();
	Dispatch.call(range, "Copy");
	moveEnd();
	enter();
	enter();
	Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
	Dispatch.call(textRange, "Paste");
    }

    /**
     * 默认拷贝到下一新页
     */
    public void copyTableF(Dispatch table) {
	Dispatch range = Dispatch.get(table, "Range").toDispatch();
	Dispatch.call(range, "Copy");
	moveEnd();
	Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
	Dispatch.call(textRange, "Paste");
    }

    /**
     * 在当前文档指定的位置拷贝来自另一个文档中的表格  
     *
     * @param anotherDocPath
     *            另一个文档的磁盘路径  
     * @param tableIndex
     *            被拷贝的表格在另一格文档中的位置  
     * @param pos
     *            当前文档指定的位置  
     */
    public void copyTableFromAnotherDoc(String anotherDocPath, int tableIndex, String pos) {
	Dispatch doc2 = null;
	try {
	    doc2 = Dispatch.call(documents, "Open", anotherDocPath).toDispatch();
	    // 所有表格
	    Dispatch tables = Dispatch.get(doc2, "Tables").toDispatch();
	    // 要填充的表格
	    Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
	    Dispatch range = Dispatch.get(table, "Range").toDispatch();
	    Dispatch.call(range, "Copy");
	    if (this.find(pos)) {
		Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
		Dispatch.call(textRange, "Paste");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (doc2 != null) {
		Dispatch.call(doc2, "Close", new Variant(saveOnExit));
		doc2 = null;
	    }
	}
    }

    /**
     *   * 在当前文档指定的位置拷贝来自另一个文档中的图片   * @param anotherDocPath 另一个文档的磁盘路径  
     * * @param shapeIndex 被拷贝的图片在另一格文档中的位置   * @param pos 当前文档指定的位置  
     */
    public void copyImageFromAnotherDoc(String anotherDocPath, int shapeIndex, String pos) {
	Dispatch doc2 = null;
	try {
	    doc2 = Dispatch.call(documents, "Open", anotherDocPath).toDispatch();
	    Dispatch shapes = Dispatch.get(doc2, "InLineShapes").toDispatch();
	    Dispatch shape = Dispatch.call(shapes, "Item", new Variant(shapeIndex)).toDispatch();
	    Dispatch imageRange = Dispatch.get(shape, "Range").toDispatch();
	    Dispatch.call(imageRange, "Copy");
	    if (this.find(pos)) {
		Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
		Dispatch.call(textRange, "Paste");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (doc2 != null) {
		Dispatch.call(doc2, "Close", new Variant(saveOnExit));
		doc2 = null;
	    }
	}
    }

    /**
     * 创建表格  
     *
     * @param pos
     *            位置  
     * @param cols
     *            列数  
     * @param rows
     *            行数  
     */
    public void createTable(String pos, int numCols, int numRows) {
	if (find(pos)) {
	    Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
	    Dispatch range = Dispatch.get(selection, "Range").toDispatch();
	    Dispatch newTable = Dispatch.call(tables, "Add", range, new Variant(numRows), new Variant(numCols))
		    .toDispatch();
	    Dispatch.call(selection, "MoveRight");
	}
    }

    /**
     * 在指定行前面增加行  
     *
     * @param tableIndex
     *            word文件中的第N张表(从1开始)
     * @param rowIndex
     *            指定行的序号(从1开始)  
     */
    public void addTableRow(Dispatch table, int rowIndex) {
	Dispatch rows = Dispatch.get(table, "Rows").toDispatch();// 表格的所有行
	Dispatch row = Dispatch.call(rows, "Item", new Variant(rowIndex)).toDispatch();
	Dispatch.call(rows, "Add", new Variant(row));
    }

    /**
     *   * 在第1行前增加一行   * @param tableIndex word文档中的第N张表(从1开始)  
     */
    public void addFirstTableRow(int tableIndex) {
	// 所有表格
	Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
	// 要填充的表格
	Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
	// 表格的所有行
	Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
	Dispatch row = Dispatch.get(rows, "First").toDispatch();
	Dispatch.call(rows, "Add", new Variant(row));
    }

    /**
     *   * 在最后1行前增加一行   * @param tableIndex word文档中的第N张表(从1开始)  
     */
    public void addLastTableRow(int tableIndex) {
	// 所有表格
	Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
	// 要填充的表格
	Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
	// 表格的所有行
	Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
	Dispatch row = Dispatch.get(rows, "Last").toDispatch();
	Dispatch.call(rows, "Add", new Variant(row));
    }

    /**
     *   * 在最后一行后增加一行   * @param tableIndex word文档中的第N张表(从1开始)  
     */
    public void addRow(int tableIndex) {
	Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
	// 要填充的表格
	Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
	// 表格的所有行
	Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
	Dispatch.call(rows, "Add");
    }

    public void addRow(Dispatch table) {
	Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
	Dispatch.call(rows, "Add");
    }

    /**
     *   * 增加一列   * @param tableIndex word文档中的第N张表(从1开始)  
     */
    public void addCol(int tableIndex) {
	// 所有表格
	Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
	// 要填充的表格
	Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
	// 表格的所有行
	Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
	Dispatch.call(cols, "Add").toDispatch();
	Dispatch.call(cols, "AutoFit");
    }

    /**
     *   * 在指定列前面增加表格的列   * @param tableIndex word文档中的第N张表(从1开始)   * @param
     * colIndex 制定列的序号 (从1开始)  
     */
    public void addTableCol(int tableIndex, int colIndex) {
	// 所有表格
	Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
	// 要填充的表格
	Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
	// 表格的所有行
	Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
	System.out.println(Dispatch.get(cols, "Count"));
	Dispatch col = Dispatch.call(cols, "Item", new Variant(colIndex)).toDispatch();
	// Dispatch col = Dispatch.get(cols, "First").toDispatch();
	Dispatch.call(cols, "Add", col).toDispatch();
	Dispatch.call(cols, "AutoFit");
    }

    /**
     *   * 在第1列前增加一列   * @param tableIndex word文档中的第N张表(从1开始)  
     */
    public void addFirstTableCol(int tableIndex) {
	Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
	// 要填充的表格
	Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
	// 表格的所有行
	Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
	Dispatch col = Dispatch.get(cols, "First").toDispatch();
	Dispatch.call(cols, "Add", col).toDispatch();
	Dispatch.call(cols, "AutoFit");
    }

    /**
     *   * 在最后一列前增加一列   * @param tableIndex word文档中的第N张表(从1开始)  
     */
    public void addLastTableCol(int tableIndex) {
	Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
	// 要填充的表格
	Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
	// 表格的所有行
	Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
	Dispatch col = Dispatch.get(cols, "Last").toDispatch();
	Dispatch.call(cols, "Add", col).toDispatch();
	Dispatch.call(cols, "AutoFit");
    }

    /**
     *   * 自动调整表格  
     */
    public void autoFitTable() {
	Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
	int count = Dispatch.get(tables, "Count").toInt();
	for (int i = 0; i < count; i++) {
	    Dispatch table = Dispatch.call(tables, "Item", new Variant(i + 1)).toDispatch();
	    Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
	    Dispatch.call(cols, "AutoFit");
	}
    }

    /**
     *      * 调用word里的宏以调整表格的宽度,其中宏保存在document下     
     */
    public void callWordMacro() {
	Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
	int count = Dispatch.get(tables, "Count").toInt();
	Variant vMacroName = new Variant("Normal.NewMacros.tableFit");
	Variant vParam = new Variant("param1");
	Variant para[] = new Variant[] { vMacroName };
	for (int i = 0; i < count; i++) {
	    Dispatch table = Dispatch.call(tables, "Item", new Variant(i + 1)).toDispatch();
	    Dispatch.call(table, "Select");
	    Dispatch.call(word, "Run", "tableFitContent");
	}
    }

    /**
     *   * 设置当前选定内容的字体   * @param boldSize   * @param italicSize   * @param
     * underLineSize 下划线   * @param colorSize 字体颜色   * @param size 字体大小  
     * * @param name 字体名称  
     */
    public void setFont(boolean bold, boolean italic, boolean underLine, String colorSize, String size, String name) {
	Dispatch font = Dispatch.get(selection, "Font").toDispatch();
	Dispatch.put(font, "Name", new Variant(name));
	Dispatch.put(font, "Bold", new Variant(bold));
	Dispatch.put(font, "Italic", new Variant(italic));
	Dispatch.put(font, "Underline", new Variant(underLine));
	Dispatch.put(font, "Color", colorSize);
	Dispatch.put(font, "Size", size);
    }

    public void setCellFont() {
	Dispatch font = Dispatch.get(selection, "Font").toDispatch();
	Dispatch.put(font, "Bold", new Variant(false));
	Dispatch.put(font, "Size", "10");

    }

    /**
     * 恢复默认字体 不加粗，不倾斜，没下划线，黑色，小四号字，宋体
     */
    public void clearFont() {
	this.setFont(false, false, false, "0,0,0", "12", "宋体");
    }

    /**
     * 对当前段落进行格式化
     *
     * @param align
     *            设置排列方式 默认：居左 0:居左 1:居中 2:居右 3:两端对齐 4:分散对齐
     * @param lineSpace
     *            设置行间距 默认：1.0 0：1.0 1：1.5 2：2.0 3：最小值 4：固定值
     */
    public void setParaFormat(int align, int lineSpace) {
	if (align < 0 || align > 4) {
	    align = 0;
	}
	if (lineSpace < 0 || lineSpace > 4) {
	    lineSpace = 0;
	}

	Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();
	Dispatch.put(alignment, "Alignment", align);

	Dispatch.put(alignment, "LineSpacingRule", new Variant(lineSpace));
	Dispatch.put(alignment, "LineUnitBefore", new Variant(0));
	Dispatch.put(alignment, "LineUnitAfter", new Variant(0));

    }

    /**
     * 还原段落默认的格式 左对齐,行间距：1.0
     */
    public void clearParaFormat() {
	this.setParaFormat(0, 0);
    }

    /**
     * 设置是否为黑体
     */
    public void setBold(boolean bold) {
	Dispatch font = Dispatch.get(selection, "Font").toDispatch();
	Dispatch.put(font, "Bold", new Variant(bold));
    }

    /**
     * 文件保存
     */
    public void save() {
	Dispatch.call(Dispatch.call(word, "WordBasic").getDispatch(), "save");
    }

    /**
     * 文件保存或另存为  
     *
     * @param savePath
     *            保存或另存为路径  
     */
    public void saveAs(String savePath) {
	Dispatch.call(Dispatch.call(word, "WordBasic").getDispatch(), "FileSaveAs", savePath);
    }

    /**
     * 另存为PDF
     */
    public void saveToPDF(String savePath) {
	Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { savePath, new Variant(17) }, new int[1]);
    }

    /**
     * 替换书签
     *
     * @param bookMarkKey
     *            书签名
     * @param info
     *            插入的数据
     * @return
     */
    public boolean intoValueBookMark(String bookMarkKey, String info) throws Exception {
	Dispatch activeDocument = word.getProperty("ActiveDocument").toDispatch();
	Dispatch bookMarks = word.call(activeDocument, "Bookmarks").toDispatch();
	boolean bookMarkExist = word.call(bookMarks, "Exists", bookMarkKey).toBoolean();
	if (bookMarkExist) {
	    Dispatch rangeItem = Dispatch.call(bookMarks, "Item", bookMarkKey).toDispatch();
	    Dispatch range = Dispatch.call(rangeItem, "Range").toDispatch();
	    Dispatch.put(range, "Text", new Variant(info));
	    return true;
	}
	return false;
    }

    /**
     * 关闭当前word文档  
     */
    public void closeDocument() {
	if (doc != null) {
	    Dispatch.call(doc, "Save");
	    Dispatch.call(doc, "Close", new Variant(saveOnExit));
	    doc = null;
	}
    }

    /**
     * 关闭全部应用  
     */
    public void close() {
	if (doc != null) {
	    Dispatch.call(doc, "Close", new Variant(saveOnExit));
	    doc = null;
	}
	if (word != null) {
	    Dispatch.call(word, "Quit");
	    word = null;
	}
	selection = null;
	documents = null;
	ComThread.Release();// 释放线程 jvm gc释放不到
    }

    /**
     * 打印当前word文档  
     */
    public void printFile() {
	if (doc != null) {
	    Dispatch.call(doc, "PrintOut");
	}
    }

    /**
     * 获取总页数
     */
    public Integer getPage() {
	Integer pages = Integer.parseInt(Dispatch.call(selection, "information", 4).toString());
	return pages;
    }

    /**
     * 替换页眉或页脚关键字
     */
    public void replaceHeaderFoot(String toFindText, String newText, Integer select) {
	moveNextPage();// 选中第二页(如果页脚、页眉分页)
	Dispatch ActiveWindow = word.getProperty("ActiveWindow").toDispatch();// 取得活动窗体对象
	Dispatch ActivePane = Dispatch.get(ActiveWindow, "ActivePane").toDispatch();// 取得活动窗格对象
	Dispatch view = Dispatch.get(ActivePane, "View").toDispatch();// 取得视窗对象
	Dispatch.put(view, "SeekView", new Variant(select));// 打开页眉，值为9，页脚为10
	replaceText(toFindText, newText);// 替换
	Dispatch.put(view, "SeekView", new Variant(0));// 0恢复视图
    }

    /**
     * 设置页脚页码
     */
    public void setPageNum(int pagenum) {
	Dispatch.call(selection, "HomeKey", new Variant(6));
	Dispatch ActiveWindow = word.getProperty("ActiveWindow").toDispatch();
	Dispatch ActivePane = Dispatch.get(ActiveWindow, "ActivePane").toDispatch();
	Dispatch View = Dispatch.get(ActivePane, "View").toDispatch();
	// 当前页眉位置
	Dispatch.put(View, "SeekView", new Variant(9));
	selection = word.getProperty("Selection").toDispatch();
	Dispatch pf = Dispatch.call(selection, "ParagraphFormat").toDispatch();
	Dispatch borders = Dispatch.call(pf, "Borders").toDispatch();
	Dispatch bb = Dispatch.call(borders, "Item", 1).toDispatch();
	Dispatch.put(borders, "Enable", new Variant(false));
	Dispatch.put(bb, "Visible", new Variant(true));
	// 当前页脚位置
	Dispatch.put(View, "SeekView", new Variant(10));
	selection = word.getProperty("Selection").toDispatch();
	Dispatch headerFooter = Dispatch.get(selection, "HeaderFooter").toDispatch();
	Dispatch pageNumbers = Dispatch.get(headerFooter, "PageNumbers").toDispatch();
	Dispatch.put(pageNumbers, "RestartNumberingAtSection", new Variant(true));
	Dispatch.put(pageNumbers, "StartingNumber", pagenum);
	// 退出页码设置视图
	Dispatch.put(View, "SeekView", new Variant(0));
    }

    /**
     * 插入文本框内容
     */
    public void inputShapeValue(int index, String value) {
	Dispatch shapes = Dispatch.get(doc, "Shapes").toDispatch();
	String count = Dispatch.get(shapes, "Count").toString();
	Dispatch shape = Dispatch.call(shapes, "Item", new Variant(index)).toDispatch();
	Dispatch textframe = Dispatch.get(shape, "TextFrame").toDispatch();
	Dispatch TextRange = Dispatch.get(textframe, "TextRange").toDispatch();
	Dispatch.put(TextRange, "Text", value);
    }

}
