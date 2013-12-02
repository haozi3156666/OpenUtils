package com.antilope.openutils.quartz;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

public class CalendarExample {

	public static void main(String[] args) throws Exception {

		SchedulerFactory sf = new StdSchedulerFactory();

		Scheduler scheduler = sf.getScheduler();

		// �ٷ�����������ÿ��Ϊ���ڵģ�����ʹ��AnnualCalendar

		AnnualCalendar holidays = new AnnualCalendar();

		// ����һ�Ͷ���

		Calendar laborDay = new GregorianCalendar();

		laborDay.add(Calendar.MONTH, 5);

		laborDay.add(Calendar.DATE, 1);

		holidays.setDayExcluded(laborDay, true);// ��-1���ų������ڣ��������Ϊfalse��Ϊ����

		// �۹����

		Calendar nationalDay = new GregorianCalendar();

		nationalDay.add(Calendar.MONTH, 10);

		nationalDay.add(Calendar.DATE, 1);

		holidays.setDayExcluded(nationalDay, true);// ��-1���ų�������

		scheduler.addCalendar("holidays", holidays, false, false);// ����Schedulerע������

		Date runDate = TriggerUtils.getDateOf(0, 0, 10, 1, 4);// ��4��1�� ����10��

		JobDetail job = new JobDetail("job1", "group1", SimpleJob.class);

		SimpleTrigger trigger = new SimpleTrigger("trigger1", "group1",

		runDate,

		null,

		SimpleTrigger.REPEAT_INDEFINITELY,

		60L * 60L * 1000L);

		trigger.setCalendarName("holidays");// ����TriggerӦ��ָ������������

		scheduler.scheduleJob(job, trigger);

		scheduler.start();

		// ʵ��Ӧ�������̲߳���ֹͣ������Scheduler�ò���ִ�У��˴�����

	}

}
