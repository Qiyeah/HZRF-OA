package com.oa.util;

public class Util {

    /**
     * 去重
     * 
     * @param s
     *            原有数据
     * @param mark
     *            分割符号
     * @param s1
     *            欲添加数据
     * @return
     */
    public static String arrayAddstr(String s, String mark, String s1) {
	if (s == null)
	    s = "";

	String[] str = s.split(mark);
	boolean flag = true;
	for (int i = 0; i < str.length; i++) {
	    if (str[i].equals(s1)) {
		flag = false;
		break;
	    }
	}
	if (flag) {
	    if (s.equals(""))
		s = s1;
	    else
		s = s + mark + s1;
	}
	return s;
    }

}
