package com.antilope.openutils.quartz;

import org.quartz.CronExpression;

import org.quartz.CronTrigger;

import org.quartz.JobDetail;

import org.quartz.Scheduler;

import org.quartz.SchedulerFactory;

import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerRunner {

	public static void main(String args[]) {

		try {

			JobDetail jobDetail = new JobDetail("job1_2", "jGroup1",SimpleJob.class);

			// ①-1：创建CronTrigger，指定组及名称

			CronTrigger cronTrigger = new CronTrigger("trigger1_2", "tgroup1");

			CronExpression cexp = new CronExpression("0/5 * * * * ?");// ①-2：定义Cron表达式

			cronTrigger.setCronExpression(cexp);// ①-3：设置Cron表达式

			SchedulerFactory schedulerFactory = new StdSchedulerFactory();

			Scheduler scheduler = schedulerFactory.getScheduler();

			scheduler.scheduleJob(jobDetail, cronTrigger);

			scheduler.start();

			// ②

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
