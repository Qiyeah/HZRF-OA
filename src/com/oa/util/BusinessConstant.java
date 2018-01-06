package com.oa.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务常量类
 */
public class BusinessConstant {

    public static Integer PAGE_SIZE = 20; // 默认每页数据条数
    public static Integer APP_PAGE_SIZE = 5; // APP默认每页数据条数

    public final static int EXPIRES_IN = 7200; // 访问令牌有效时间(s)

    public static final long INTERVAL_SECOND = 1000; // 一秒对应的秒数
    public static final long INTERVAL_MINUTE = 60000; // 一分钟对应的秒数
    public static final long INTERVAL_HOUR = 3600000; // 一小时对应的秒数
    public static final long INTERVAL_DAY = 86400000; // 一个天对应的秒数
    public static final long INTERVAL_WEEK = 604800000; // 一个星期对应的秒数

    public final static String DATE_PATTERN_LONG = "yyyy-MM-dd HH:mm:ss"; // 日期时间格式

    public final static String DOT = "."; // 点

    public final static String PATH_CONTRACT = "/File/Report/";
    public final static String PATH_QUOTATION = "/File/Quotation/";

    public final static String OA_ID = "1"; // 企业OA应用ID
    public final static String SAFE_YES = "1"; // 消息保密
    public final static String SAFE_NO = "0"; // 消息不保密

    public final static Integer DELETE_YES = 1; // 已删除
    public final static Integer DELETE_NO = 0; // 未删除

    public final static String LOGIN_TYPE_OFFICE = "0"; // 人防办登陆
    public final static String LOGIN_TYPE_ENTERPRISE = "1"; // 企业登陆

    public final static Integer ENABLE_YES = 1; // 启用
    public final static Integer ENABLE_NO = 0; // 禁用

    public final static String VISIBLE = "1"; // 可见
    public final static String INVISIBLE = "0"; // 不可见

    public final static String NEW_INFO = "1"; // 新添信息
    public final static String RECORDED_INFO = "0"; // 已通过审核信息

    public final static String FETCH_CHILD_YES = "1"; // 递归获取子部门下面的成员
    public final static String FETCH_CHILD_NO = "0"; // 递归获取子部门下面的成员

    public final static String USER_STATUS_ALL = "0"; // 全部成员
    public final static String USER_STATUS_FOLLOWED = "1"; // 已关注成员
    public final static String USER_STATUS_DISBALED = "2"; // 禁用成员
    public final static String USER_STATUS_UNFOLLOW = "4"; // 未关注成员

    public final static int PROTECTIVE_EQUIPMENT = 1; // 防护设备
    public final static int CHEMICAL_EQUIPMENT = 2; // 防化设备
    public final static int CONSTRUCTION_EQUIPMENT = 3; // 建设部系列
    public final static int CHEMICAL_WIND = 4; // 防化通风设备
    public final static int CHEMICAL_WATER = 5; // 防化给排水设备
    public final static int CHEMICAL_ELECTRIC = 6; // 防化电器设备

    public final static String PROTECTIVE_EQUIPMENT_REMARK = "总参系列防护设备"; // 防护设备
    public final static String CHEMICAL_EQUIPMENT_REMARK = "防化设备"; // 防化设备
    public final static String CONSTRUCTION_EQUIPMENT_REMARK = "建设部系列防护设备"; // 建设部系列
    public final static String CHEMICAL_WIND_REMARK = "防化通风设备"; // 防化通风设备
    public final static String CHEMICAL_WATER_REMARK = "防化给排水设备"; // 防化给排水设备
    public final static String CHEMICAL_ELECTRIC_REMARK = "防化电器设备"; // 防化电器设备

    public static final Map<Integer, String> equipmentTypeMap = new HashMap<>();

    // 获取合同条款页面显示行数
    static {
	equipmentTypeMap.put(PROTECTIVE_EQUIPMENT, PROTECTIVE_EQUIPMENT_REMARK); // 总参系列
	equipmentTypeMap.put(CONSTRUCTION_EQUIPMENT, CONSTRUCTION_EQUIPMENT_REMARK); // 建设部系列
	equipmentTypeMap.put(CHEMICAL_WIND, CHEMICAL_WIND_REMARK); // 防化通风设备
	equipmentTypeMap.put(CHEMICAL_WATER, CHEMICAL_WATER_REMARK); // 防化给排水设备
	equipmentTypeMap.put(CHEMICAL_ELECTRIC, CHEMICAL_ELECTRIC_REMARK); // 防化电器设备
    }

    public static Map<Integer, String> getEquipmentTypeMap() {
	return equipmentTypeMap;
    }

    public static final int MAN = 1;
    public static final int FEMALE = 2;
    public static final int SEX_ANOTHER = 0;

    public static final String COMMON_ONAME_EDUCATION = "education";// 公共参数名称：学历
    public static final String COMMON_ONAME_PROTECTIONENGINEER = "protection_engineer";// 公共参数名称：防护工程师
    public static final String COMMON_ONAME_PROFESSIONAL = "professional";// 公共参数名称：专业工程师
    public static final String COMMON_ONAME_CERTIFICATIONTYPE = "certificationType";// 公共参数名称：证书类型
    public static final String COMMON_ONAME_INVENTIONTYPE = "invention"; // 科研成果类型
    public static final String COMMON_ONAME_PROJECTTYPE = "project_type"; // 工程类别
    public static final String COMMON_ONAME_POLITICAL = "political"; // 政治面貌
    public static final String COMMON_ONAME_NATION = "nation"; // 民族
    public static final String COMMON_ONAME_POSITION = "position_protect"; // 防护设备岗位名称
    public static final String COMMON_ONAME_NOTIFYTYPE = "notifyType";

    public static final String COMMON_NAME_NORMAL_FUNCTION = "normalFunction"; // 公共参数名称：平时功能
    public static final String COMMON_NAME_WARTIME_FUNCTION = "wartimeFunction"; // 公共参数名称：战时功能
    public static final String COMMON_NAME_CHEMICAL_DEFENCE_LEVEL = "chemicalDefenceLevel"; // 公共参数名称：防化等级
    public static final String COMMON_NAME_NUCLEAR_DEFENCE_LEVEL = "nuclearDefenceLevel"; // 公共参数名称：防核等级
    public static final String COMMON_NAME_GENERAL_DEFENCE_LEVEL = "generalDefenceLevel"; // 公共参数名称：常规防护等级
    public static final String COMMON_NAME_PRODUCT_TYPE = "productType"; // 公共参数名称：产品类别
    public static final String COMMON_NAME_PROJECT_WARTIME_FUNCTION = "projectWartimeFunction"; // 公共参数名称：工程战时功能

    public static final int STATUS_NOT_AUDIT = 0; // 审核状态位：未审核
    public static final int STATUS_AUDITED_PASS = 1; // 审核状态位：审核通过/正常
    public static final int STATUS_AUDITED_FAIL = 2; // 审核状态位：审核未通过

    public static final int STATUS_CANCELLATION = 4; // 个人启用状态:注销
    public static final int STATUS_SUSPEND = 3; // 个人启用状态:暂停

    public static final int STATUS_NOT_RECOED = 0; // 备案状态位：未备案
    public static final int STATUS_RECORDING = 1; // 备案状态位:备案审核中
    public static final int STATUS_RECORD_PASS = 2; // 备案状态位：已备案
    public static final int STATUS_CHANGING = 3; // 备案状态位：备案变更审核中

    public static final String INTRADUCE = "注册审核一般需要3个工作日左右，审核通过后才可登录系统进行个人信息备案，如有疑问请致电0731-232123122咨询";

    public static final String ENTERPRISEJB_CATEGORY = "enterpriseCategory";// 公共参数名称：企业类别
    public static final String ENTERPRISEJB_HY_CATEGORY = "enterprisehy_Category";// 公共参数名称：行业类别
    public static final String ENTERPRISE_ACHI = "enterprise_achi";// 科研发明类型
    public static final String ENTERPRISE_PRODUCT = "enterprise_product";// 产品目录设备类型
    public static final String ENTERPRISE_PRODUCT_UNIT = "enterprise_product_unit";// 产品目录单位

    public static final int DRAWING_STATUS_UNAUDITED = 0; // 审图状态：未审图
    public static final int DRAWING_STATUS_ON_AUDIT = 1; // 审图状态：审图中
    public static final int DRAWING_STATUS_AUDITED = 2; // 审图状态：已审图

    public static final String MAIL_REPLY = "1"; // 邮件已回复
    public static final String MAIL_FORWARD = "2"; // 邮件已转发

    public static final String MYSCHEDULETYPE = "MyscheduleType";// 日程类型

    public static final int LEAVE_STATUS_APPROVED = 2; // 请休假已批准

    public static final String ATTENDANCE_STATUS_NORMAL = "1"; // 考勤记录状态-正常
    public static final String ATTENDANCE_STATUS_LEAVE = "2"; // 考勤记录状态-请假
    public static final String ATTENDANCE_STATUS_LATE = "3"; // 考勤记录状态-迟到
    public static final String ATTENDANCE_STATUS_LEAVE_EARLY = "4"; // 考勤记录状态-早退
    public static final String ATTENDANCE_STATUS_ABSENCE = "5"; // 考勤记录状态-缺勤
    public static final String ATTENDANCE_STATUS_CONFIRMED_NORMAL = "6"; // 考勤记录状态-确认正常

    // 推迟15分钟
    public static final String AM_ON_TIME = "08:45:00"; // 上午上班时间
    public static final String AM_OFF_TIME = "11:15:00"; // 上午下班时间
    public static final String PM_ON_TIME = "14:45:00"; // 下午上班时间
    public static final String PM_OFF_TIME = "17:15:00"; // 下午下班时间

    public static final String DOCNO = "docno"; // 发文文号

    public static final String TEMP_ID_PREFIX = "temp"; // 临时ID前缀
}
