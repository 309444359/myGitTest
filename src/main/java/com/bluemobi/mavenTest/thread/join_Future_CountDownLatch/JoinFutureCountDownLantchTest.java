package com.bluemobi.mavenTest.thread.join_Future_CountDownLatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.appcore.core.pool.ThreadPoolFactory;
/**
 * 测试线程栈工具 
 * 四种方式，模拟多线程处理多个任务然，等多个任务处理完成后一起汇总结果
 * @author haoj 309444359@qq.com
 * @date 2018年3月29日 上午11:33:49
 */
public class JoinFutureCountDownLantchTest {

	// 循环次数
	public static long LOOP_COUNT = 1000;

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		long b = System.currentTimeMillis();
		
		//futureTest();
		//futureTest2();
		//countDownLatchTest();
		joinTest();
		
		long e = System.currentTimeMillis();
		
		System.out.println("执行耗时："+(e-b)/1000d+" 秒");
	}
	
	// 通过 Future Callable 实现多线程处理3个任务然，等3个任务处理完成后一起汇总结果
	public static void futureTest() throws InterruptedException, ExecutionException {

		// ThreadPoolFactory.execute("RunnableTest", new MyRunnable());
		Future<String> future1 = ThreadPoolFactory.submitToCached("CallableTest", new MyCallable());
		Future<String> future2 = ThreadPoolFactory.submitToCached("CallableTest", new MyCallable());
		Future<String> future3 = ThreadPoolFactory.submitToCached("CallableTest", new MyCallable());

		String s1 = future1.get();
		String s2 = future1.get();
		String s3 = future1.get();

		if ("SUCCESS".equals(s1) || "SUCCESS".equals(s2) || "SUCCESS".equals(s3)) {
			System.out.println("执行完毕！");
		}

		ThreadPoolFactory.shutDownNow("CallableTest");
	}

	// 通过 Future Runnable 实现多线程处理3个任务然，等3个任务处理完成后一起汇总结果
	public static void futureTest2() throws InterruptedException, ExecutionException {

		Future future1 = ThreadPoolFactory.submitToCached("RunnableTest", new MyRunnable());
		Future future2 = ThreadPoolFactory.submitToCached("RunnableTest", new MyRunnable());
		Future future3 = ThreadPoolFactory.submitToCached("RunnableTest", new MyRunnable());

		future1.get();
		future1.get();
		future1.get();

		System.out.println("执行完毕！执行结果未知");

		ThreadPoolFactory.shutDownNow("RunnableTest");

	}

	// 通过CountDownLatch实现多线程处理3个任务然，等3个任务处理完成后一起汇总结果
	public static void countDownLatchTest() throws InterruptedException {

		CountDownLatch countDownLatch = new CountDownLatch(3);

		ThreadPoolFactory.executeToCached("RunnableTest", new MyRunnable(countDownLatch));
		ThreadPoolFactory.executeToCached("RunnableTest", new MyRunnable(countDownLatch));
		ThreadPoolFactory.executeToCached("RunnableTest", new MyRunnable(countDownLatch));

		countDownLatch.await();
		
		System.out.println("执行完毕！执行结果未知");
		
		ThreadPoolFactory.shutDownNow("RunnableTest");

	}
	
	//通过join实现多线程处理3个任务然，等3个任务处理完成后一起汇总结果
	public static void joinTest() throws InterruptedException{
		Runnable r1 = new MyRunnable();
		Runnable r2 = new MyRunnable();
		Runnable r3 = new MyRunnable();
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(r3);
		
		t1.start();
		System.out.println("线程1启动...");
		t2.start();
		System.out.println("线程2启动...");
		t3.start();
		System.out.println("线程3启动...");
		
		t1.join();
		t2.join();
		t3.join();
		
		System.out.println("执行完毕！执行结果未知");
	}

	private static class MyCallable implements Callable<String> {

		@Override
		public String call() throws Exception {
			for (int i = 0; i < LOOP_COUNT; i++) {
				byte[] bb = new byte[10 * 1024];
				Thread.sleep(10);
			}
			return "SUCCESS";
		}

	}

	private static class MyRunnable implements Runnable {

		public CountDownLatch countDownLatch = null;

		public MyRunnable() {
		};

		public MyRunnable(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		};

		@Override
		public void run() {
			for (int i = 0; i < LOOP_COUNT; i++) {
				byte[] bb = new byte[10 * 1024];
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (countDownLatch != null) {
				countDownLatch.countDown();
			}

		}

	}

}
