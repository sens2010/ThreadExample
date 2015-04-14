package cn.cnic.sdc.example.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafedWithRWLock extends Thread{
	private String myname =null;
	static int common=0;
	static ReadWriteLock  lock = new ReentrantReadWriteLock();
	public ThreadSafedWithRWLock(String name )
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
		Lock writelock = lock.writeLock();
		writelock.lock();
		int i=common;
		i=i+1;
		common=i;
		System.out.println("getCommon before value is "+common);
		writelock.unlock();
		Lock readlock = lock.readLock();
		readlock.lock();
		System.out.println("getCommon after value is "+common);
		readlock.unlock();
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
			Thread mythread = new ThreadSafedWithRWLock("thread"+(i+1));
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
			System.out.println("common:"+ThreadSafedWithRWLock.common);
		}
	}

}
