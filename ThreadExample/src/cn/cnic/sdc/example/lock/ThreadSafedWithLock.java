package cn.cnic.sdc.example.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafedWithLock extends Thread{
	private String myname =null;
	static int common=0;
	static Lock lock = new ReentrantLock();
	public ThreadSafedWithLock(String name )
	{
		
		this.myname=name;
		//System.out.println("创建："+getMyName());
	}

	public String getMyName()
	{
		return this.myname;
	}
	
	 public int getCommon()
	{
		lock.lock();
		int i=common;
		i=i+1;
		common=i;
		System.out.println("getCommon value is "+common);
		lock.unlock();
		return common;
	}
	public void run()
	{
		try {
			Thread.sleep(0);
			getCommon();
			//common++;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("运行："+getMyName()+",i:"+common);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("*****************");
		for(int i =0;i<10;i++)
		{
			Thread mythread = new ThreadSafedWithLock("thread"+(i+1));
			mythread.start();
		}
		
		while(true)
		{
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("common:"+ThreadSafedWithLock.common);
		}
	}

}
