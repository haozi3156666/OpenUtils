package com.antilope.openutils.quartz;

/**
 * 
 * @author 		:	������
 * @time		:	1:34:20 PM
 * @email		:	by_xushiheng@boyutimes.com
 * @description	:   ���˳�����
 * @�޸ļ�¼     ��
 */
public final class FinaceConstant
{

	
	/*
	 * ������������ʱ�� ���ݲ�ͬ���������ڹ��� 
	 * CronExpression  ���ʽ
	 */
	/**
	 * ���� ����
	 */
	public static final int CYCLE_WEEKLY = 2;
	/**
	 * ���� ����
	 */
	public static final int CYCLE_MONTHLY = 3;
	/**
	 * ���� ������
	 */
	public static final int CYCLE_QUARTERLY = 4;
	/**
	 * ���� ������
	 */
	public static final int CYCLE_HALFYEAR = 5;
	/**
	 * ���� ����
	 */
	public static final int CYCLE_YEAR = 6;
	/**
	 * ���� ��ѧ��
	 */
	public static final int CYCLE_TERM = 7;
	/**
	 * ���� ����
	 */
	public static final int CYCLE_DAYLY = 1;
	
	/*
	 * 
	 * 
	 * 
	 */
	/**
	 * DECTYPE  �۷���Ŀ
	 * ��װ��
	 */
	public static final int DECTYPE_SETUP = 1;
	/**
	 *  ����
	 */
	public static final int DECTYPE_PAYMENT = 2;
	/**
	 * ���� 
	 */
	public static final int DECTYPE_ADJUST = 3;
	/**
	 * ҵ��ʹ��
	 */
	public static final int DECTYPE_SERVICE = 4;
	/**
	 * ����ѭ���۳�
	 */
	public static final int DECTYPE_CYC = 5;
	
	/**
	 * 	�ڳ���� 
	 */
	public static final int DECTYPE_BASE = 6;
	
	/**
	 * ACTION  ������Ŀ��ʶ
	 * ��װ��
	 */
	public static final int ACTION_SETUPCHARGE = 1;
	/**
	 * WLAN ʹ�÷�
	 */
	public static final int ACTION_WLANCHAGGE = 2;
	/**
	 * SW ʹ�÷�
	 */
	public static final int ACTION_SWCHARGE = 3;
	/**
	 * VPN ʹ�÷�
	 */
	public static final int ACTION_VPNCHARGE = 4;
	/**
	 * �ڳ�һ�ο۳�����
	 */
	public static final int ACTION_CYC_START = 5;
	/**
	 * ��ĩһ�ο۳�����
	 */
	public static final int ACTION_CYC_END = 6;
	/**
	 * ����۳�����
	 */
	public static final int ACTION_CYC_DAYLYCHARGE = 7;
	/**
	 * ���¿۳�����
	 */
	public static final int ACTION_CYC_MONTHLYCHARGE = 8;
	/**
	 * ����
	 */
	public static final int ACTION_PAYMENT = 9;
	/**
	 * ����
	 */
	public static final int ACTION_ADJUST = 10;
	/**
	 * ���ڹ̶��ۼ�
	 */
	public static final int ACTION_CYC = 11;
	/**
	 * ҵ��ʹ��
	 */
	public static final int ACTION_SERVICECHAGGE = 12;
	
	/**
	 * ������Ŀ 
	 * ���û�����
	 */
	public static final int DUC_ACCOUNT = 0;
	/**
	 * �Բ�Ʒ����
	 */
	public static final int DUC_PRODUCT = 1;
	/**
	 * ��ҵ�����
	 */
	public static final int DUC_SERVICE = 2;
	/**
	 * ��ҵ���ʺų���
	 */
	public static final int DUC_SERVICEACCT = 3;
	
	
	
	/**
	 * �۷� ����
	 */
	public static final int DUC_CYCACCOUNT = 4;
	/**
	 * �Բ�Ʒ����
	 */
	public static final int DUC_CYCPRODUCT = 3;
	/**
	 * ��ҵ�����
	 */
	public static final int DUC_CYCSERVICE = 2;
	/**
	 * ��ҵ���ʺų���
	 */
	public static final int DUC_CYCSERVICEACCT = 1;
	
	/**
	 * ��������
	 */
	public static final int INVOICE_TYPE_ACCT = 1;
	public static final int INVOICE_TYPE_SERVICE = 2;
	public static final int INVOICE_TYPE_PRODUCT = 3;
	public static final int INVOICE_TYPE_ACCOUNT = 4;
	
	
}
