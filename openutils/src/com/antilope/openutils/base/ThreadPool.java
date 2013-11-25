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
	
	/**ThreadPoolExecutor�ṩ������Ԥ����Ĵ��������ԣ� 
	��Ĭ�ϵ� ThreadPoolExecutor.AbortPolicy �У���������⵽�ܾ����׳�����ʱ RejectedExecutionException�� 
	�� ThreadPoolExecutor.CallerRunsPolicy �У��̵߳������и������ execute �����˲����ṩ�򵥵ķ������ƻ��ƣ��ܹ�������������ύ�ٶȡ� 
	�� ThreadPoolExecutor.DiscardPolicy �У�����ִ�е����񽫱�ɾ���� 
	�� ThreadPoolExecutor.DiscardOldestPolicy �У����ִ�г�����δ�رգ���λ�ڹ�������ͷ�������񽫱�ɾ����Ȼ������ִ�г�������ٴ�ʧ�ܣ����ظ��˹��̣�*/
	public static ThreadPoolExecutor getThreadPoolExecutor( int minThreads , int maxThreads , int numworkqueue, int aliveTime,TimeUnit aliveUnit,ThreadFactory policy)
	{
		ThreadPoolExecutor pool = new ThreadPoolExecutor( minThreads , maxThreads , aliveTime , aliveUnit , 
		new LinkedBlockingQueue< Runnable >( numworkqueue ) , 		
		policy); 
		pool.prestartAllCoreThreads( );
		return pool;
	}
	
	
}
