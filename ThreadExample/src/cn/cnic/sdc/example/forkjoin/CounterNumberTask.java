package cn.cnic.sdc.example.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CounterNumberTask extends RecursiveTask<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6180160154741467794L;
	private static final int RANK = 500000000;
	private long start;
	private long end;
	public CounterNumberTask(long start,long end)
	{
		this.start=start;
		this.end=end;
	}
	@Override
	protected Long compute() {
		// TODO Auto-generated method stub
		long sum = 0;
		boolean isCompute=(end-start)<=RANK;
		if(isCompute)
		{
			for(long i=start;i<=end;i++)
			{
				sum+=i;
			}
		}
		else
		{
			long middle=(start+end)/2;
			CounterNumberTask leftTask=new CounterNumberTask(start,middle);
			CounterNumberTask rightTask=new CounterNumberTask(middle+1,end);
			leftTask.fork();
			rightTask.fork();
			long leftResult=leftTask.join();
			long rightResult=rightTask.join();
			sum=leftResult+rightResult;
		}
		return sum;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long start = 1;
		long end = 19000000000L;
		long start_time =0;
		long end_time = 0;
		
		start_time=System.currentTimeMillis();
		ForkJoinPool forkjoinpool= new ForkJoinPool();
		CounterNumberTask task= new CounterNumberTask(start,end);
		Future<Long> result=forkjoinpool.submit(task);
		try {
			
			System.out.print(result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		end_time=System.currentTimeMillis();
		
		System.out.println("method1 time cost:"+(end_time-start_time));
		start_time=System.currentTimeMillis();
		long sum = 0;
		for(long i=start;i<=end;i++)
			sum+=i;
		end_time=System.currentTimeMillis();
		
		System.out.println("method2 time cost:"+(end_time-start_time)+"result is"+sum);
		
	}

	
}
