package com.antilope.openutils.quartz;

/**
 * 
 * @author 		:	许世恒
 * @time		:	1:34:20 PM
 * @email		:	by_xushiheng@boyutimes.com
 * @description	:   出账常量类
 * @修改记录     ：
 */
public final class FinaceConstant
{

	
	/*
	 * 构造出账任务的时候 根据不同的账务周期构造 
	 * CronExpression  表达式
	 */
	/**
	 * 周期 按周
	 */
	public static final int CYCLE_WEEKLY = 2;
	/**
	 * 周期 按月
	 */
	public static final int CYCLE_MONTHLY = 3;
	/**
	 * 周期 按季度
	 */
	public static final int CYCLE_QUARTERLY = 4;
	/**
	 * 周期 按半年
	 */
	public static final int CYCLE_HALFYEAR = 5;
	/**
	 * 周期 按年
	 */
	public static final int CYCLE_YEAR = 6;
	/**
	 * 周期 按学期
	 */
	public static final int CYCLE_TERM = 7;
	/**
	 * 周期 按天
	 */
	public static final int CYCLE_DAYLY = 1;
	
	/*
	 * 
	 * 
	 * 
	 */
	/**
	 * DECTYPE  扣费项目
	 * 安装费
	 */
	public static final int DECTYPE_SETUP = 1;
	/**
	 *  续费
	 */
	public static final int DECTYPE_PAYMENT = 2;
	/**
	 * 调帐 
	 */
	public static final int DECTYPE_ADJUST = 3;
	/**
	 * 业务使用
	 */
	public static final int DECTYPE_SERVICE = 4;
	/**
	 * 周期循环扣除
	 */
	public static final int DECTYPE_CYC = 5;
	
	/**
	 * 	期初余额 
	 */
	public static final int DECTYPE_BASE = 6;
	
	/**
	 * ACTION  出账项目标识
	 * 安装费
	 */
	public static final int ACTION_SETUPCHARGE = 1;
	/**
	 * WLAN 使用费
	 */
	public static final int ACTION_WLANCHAGGE = 2;
	/**
	 * SW 使用费
	 */
	public static final int ACTION_SWCHARGE = 3;
	/**
	 * VPN 使用费
	 */
	public static final int ACTION_VPNCHARGE = 4;
	/**
	 * 期初一次扣除费用
	 */
	public static final int ACTION_CYC_START = 5;
	/**
	 * 期末一次扣除费用
	 */
	public static final int ACTION_CYC_END = 6;
	/**
	 * 按天扣除费用
	 */
	public static final int ACTION_CYC_DAYLYCHARGE = 7;
	/**
	 * 按月扣除费用
	 */
	public static final int ACTION_CYC_MONTHLYCHARGE = 8;
	/**
	 * 续费
	 */
	public static final int ACTION_PAYMENT = 9;
	/**
	 * 调账
	 */
	public static final int ACTION_ADJUST = 10;
	/**
	 * 周期固定扣减
	 */
	public static final int ACTION_CYC = 11;
	/**
	 * 业务使用
	 */
	public static final int ACTION_SERVICECHAGGE = 12;
	
	/**
	 * 出账项目 
	 * 对用户出账
	 */
	public static final int DUC_ACCOUNT = 0;
	/**
	 * 对产品出账
	 */
	public static final int DUC_PRODUCT = 1;
	/**
	 * 对业务出账
	 */
	public static final int DUC_SERVICE = 2;
	/**
	 * 对业务帐号出账
	 */
	public static final int DUC_SERVICEACCT = 3;
	
	
	
	/**
	 * 扣费 规则
	 */
	public static final int DUC_CYCACCOUNT = 4;
	/**
	 * 对产品出账
	 */
	public static final int DUC_CYCPRODUCT = 3;
	/**
	 * 对业务出账
	 */
	public static final int DUC_CYCSERVICE = 2;
	/**
	 * 对业务帐号出账
	 */
	public static final int DUC_CYCSERVICEACCT = 1;
	
	/**
	 * 出账类型
	 */
	public static final int INVOICE_TYPE_ACCT = 1;
	public static final int INVOICE_TYPE_SERVICE = 2;
	public static final int INVOICE_TYPE_PRODUCT = 3;
	public static final int INVOICE_TYPE_ACCOUNT = 4;
	
	
}
