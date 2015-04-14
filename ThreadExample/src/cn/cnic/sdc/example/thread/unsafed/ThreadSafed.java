package cn.cnic.sdc.example.thread.unsafed;

public class ThreadSafed extends Thread{
	private String myname =null;
	static int common=0;
	public ThreadSafed(String name )
	{
		
		this.myname=name;
		//System.out.println("创建："+getMyName());
	}

	public String getMyName()
	{
		return this.myname;
	}
	
	synchronized public int getCommon()
	{
		int i=common;
		i=i+1;
		common=i;
		System.out.println("getCommon value is "+common);
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
			Thread mythread = new ThreadSafed("thread"+(i+1));
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
			System.out.println("common:"+ThreadSafed.common);
		}
	}

}
