package com.antilope.openutils.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	
	public ExecutorService getFixedThreadPool(int num){
		return Executors.newFixedThreadPool(num); 
	}
	
	public ExecutorService getSingleThreadPool(){
		return Executors.newSingleThreadExecutor();
	}
	
	public ScheduledExecutorService getScheduledExecutorService(int num){
		return Executors.newScheduledThreadPool(num);
	}
	
	/**ThreadPoolExecutor提供了四种预定义的处理程序策略： 
	在默认的 ThreadPoolExecutor.AbortPolicy 中，处理程序遭到拒绝将抛出运行时 RejectedExecutionException。 
	在 ThreadPoolExecutor.CallerRunsPolicy 中，线程调用运行该任务的 execute 本身。此策略提供简单的反馈控制机制，能够减缓新任务的提交速度。 
	在 ThreadPoolExecutor.DiscardPolicy 中，不能执行的任务将被删除。 
	在 ThreadPoolExecutor.DiscardOldestPolicy 中，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）*/
	public static ThreadPoolExecutor getThreadPoolExecutor( int minThreads , int maxThreads , int numworkqueue, int aliveTime,TimeUnit aliveUnit,ThreadFactory policy)
	{
		ThreadPoolExecutor pool = new ThreadPoolExecutor( minThreads , maxThreads , aliveTime , aliveUnit , 
		new LinkedBlockingQueue< Runnable >( numworkqueue ) , 		
		policy); 
		pool.prestartAllCoreThreads( );
		return pool;
	}
	
	
}
