package com.antilope.openutils.quartz;

import java.util.Date;

import org.quartz.Job;

import org.quartz.JobExecutionContext;

import org.quartz.JobExecutionException;

public class SimpleJob implements Job {

//①实例Job接口方法

public void execute(JobExecutionContext jobCtx)throws JobExecutionException {

System.out.println(jobCtx.getTrigger().getName()+ " triggered. time is:" + (new Date()));

}

}
