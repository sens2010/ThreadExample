package cn.cnic.sdc.example.thread.unsafed;

public class ThreadUnsafed extends Thread{
	private String myname =null;
	static int common=0;
	public ThreadUnsafed(String name )
	{
		
		this.myname=name;
		System.out.println("创建："+getMyName());
	}

	public String getMyName()
	{
		return this.myname;
	}
	
	public void run()
	{
		try {
			Thread.sleep(1000);
			int i=common;
			i=i+1;
			common=i;
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
			Thread mythread = new ThreadUnsafed("thread"+i);
			mythread.start();
		}
		
		/*while(true)
		{
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("common:"+ThreadUnsafed.common);
		}*/
	}

}
