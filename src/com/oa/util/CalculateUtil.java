package com.oa.util;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Stack;

public class CalculateUtil {

    // 求和
    public static Double sum(Double[] datas) {
	double sum = 0;
	if (datas != null) {
	    for (Double data : datas) {
		sum += data;
	    }
	}
	return sum;
    }

    // 取最大值
    public static Double getMax(Double[] datas) {
	if (datas == null) {
	    return null;
	}
	Arrays.sort(datas);
	return datas[datas.length - 1];
    }

    // 取最小值
    public static Double getMin(Double[] datas) {
	if (datas == null) {
	    return null;
	}
	Arrays.sort(datas);
	return datas[0];
    }

    // 去最值（去最大值和最小值）
    public static Double[] outPole(Double[] datas) {
	if (datas == null) {
	    return null;
	}
	Arrays.sort(datas);
	if (datas.length > 2) {
	    Double[] data = new Double[datas.length - 2];
	    for (int i = 1; i < datas.length - 1; i++) {
		data[i - 1] = datas[i];
	    }
	    return data;
	}
	return datas;
    }

    // 去最值（去最大值）
    public static Double[] outMax(Double[] datas) {
	if (datas == null) {
	    return null;
	}
	Arrays.sort(datas);
	if (datas.length > 1) {
	    Double[] data = new Double[datas.length - 1];
	    for (int i = 0; i < datas.length - 1; i++) {
		data[i] = datas[i];
	    }
	    return data;
	}
	return datas;
    }

    // 去最值（去最小值）
    public static Double[] outMin(Double[] datas) {
	if (datas == null) {
	    return null;
	}
	Arrays.sort(datas);
	if (datas.length > 1) {
	    Double[] data = new Double[datas.length - 1];
	    for (int i = 1; i < datas.length; i++) {
		data[i - 1] = datas[i];
	    }
	    return data;
	}
	return datas;
    }

    // 平均值(默认)
    public static Double average(Double[] datas) {
	double average = 0.0;
	if (datas != null) {
	    average = sum(datas) / datas.length;
	}
	return average;
    }

    // 平均值(指定除数)
    public static Double average(Double[] datas, Integer divisor) {
	double average = 0;
	if (datas != null) {
	    average = sum(datas) / divisor;
	}
	return average;
    }

    // 平均值(去除最大值)
    public static Double noMaxverage(Double[] datas) {
	double average = 0;
	if (datas != null) {
	    Double[] data = outMax(datas);
	    average = average(data);
	}
	return average;
    }

    // 平均值(去除最小值)
    public static Double noMinverage(Double[] datas) {
	double average = 0;
	if (datas != null) {
	    Double[] data = outMin(datas);
	    average = average(data);
	}
	return average;
    }

    // 平均值(去除最大值和最小值后的平均)
    public static Double noPoleAverage(Double[] datas) {
	double average = 0;
	if (datas != null) {
	    Double[] data = outPole(datas);
	    average = average(data);
	}
	return average;
    }

    // 试件模式
    public static String testModel(Double[] datas, String len, String wid) {
	DecimalFormat df = new DecimalFormat("#.#");
	double area = Double.parseDouble(len) * Double.parseDouble(wid);
	StringBuffer sb = new StringBuffer();
	for (Double data : datas) {
	    sb.append("," + df.format(data / area));
	}
	if (sb.length() > 0) {
	    sb.deleteCharAt(0);
	}
	return sb.toString();
    }

    // 试件模式
    public static String testModel(Double data, String len, String wid) {
	DecimalFormat df = new DecimalFormat("#.#");
	double area = Double.parseDouble(len) * Double.parseDouble(wid);
	return df.format(data / area);
    }

    // 复杂计算 公式例子 String formaula = "(4+3+4*(4*(10-2)-10/2)+10/2)/2";
    public static Double calculate(String formaula) {
	formaula = formaula + "#";
	try {
	    Stack<Character> operators = new Stack<Character>();
	    Stack<Double> numbers = new Stack<Double>();
	    StringBuffer number = new StringBuffer();
	    char peek, indexChar, operator;
	    Double number_, _number;
	    for (int i = 0; i < formaula.length(); i++) {
		indexChar = formaula.charAt(i);
		if (String.valueOf(indexChar).matches("[0-9]|\\.")) {
		    number.append(indexChar);
		} else {
		    if (number.length() > 0) {
			numbers.push(Double.parseDouble(number.toString()));
			number.setLength(0);
		    }
		    while (!operators.empty()) {// 处理能处理的值
			peek = operators.peek();
			boolean flag = false;
			/** 判断是否要进行计算 */
			if (indexChar == '#') {
			    flag = true;
			} else if (indexChar == '+' || indexChar == '-' || indexChar == '|' || indexChar == '!') {
			    if (peek != '(') {
				flag = true;
			    }
			} else if (indexChar != '(') {
			    if (peek == ')' || peek == '*' || peek == '/') {
				flag = true;
			    }
			}
			/** 判断是否要进行计算 */
			if (!flag) {
			    break;
			}
			operator = operators.pop();
			if (operator == ')') {// 多出栈两次
			    operator = operators.pop();
			    if (operator == '(') {
				continue;
			    } else {
				operators.pop();
			    }
			}
			_number = numbers.pop();
			number_ = numbers.pop();
			switch (operator) {
			case '+':
			    numbers.push(number_ + _number);
			    break;
			case '-':
			    numbers.push(number_ - _number);
			    break;
			case '|':
			    numbers.push(number_ > _number ? number_ : _number);
			    break;
			case '!':
			    numbers.push(number_ < _number ? number_ : _number);
			    break;
			case '*':
			    numbers.push(number_ * _number);
			    break;
			case '/':
			    // change zjw 被除数为0的时候结果为0；
			    if (_number != 0) {
				numbers.push(number_ / _number);
			    } else {
				numbers.push(new Double(0));
			    }
			    break;
			}
		    }
		    if (indexChar != '#') {
			operators.push(indexChar);
		    }
		}
	    }
	    return numbers.pop();
	} catch (Exception e) {
	    System.out.println("公式有误");
	    return 0.0;
	}
    }
}
