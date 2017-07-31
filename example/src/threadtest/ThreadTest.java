package threadtest;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes.Name;

import org.omg.CORBA.PolicyListHelper;

public class ThreadTest {
	public static void main(String[] args)  { 
		List<Product> productList = new LinkedList<>();
		for(int i =0;i<10000;i++){
			Product pro = new Product();
			pro.setName("Product "+i);
			pro.setPrice(10);
			productList.add(pro);
		}
		
		Task task =new Task(productList,0, productList.size(), 0.2);
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		do{
			System.out.printf("Main : 线程数：%d\n",pool.getActiveThreadCount());
			System.out.printf("主线程：线程steal：%d\n", pool.getStealCount());
			System.out.printf("主线程：Parallelism %d\n", pool.getParallelism());
			try{
				TimeUnit.MILLISECONDS.sleep(5);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}while(!task.isDone());
		
		pool.shutdown();
		if(task.isCompletedNormally()){
			System.out.println("任务正常运行");
		}
		
		for(int i=0;i<productList.size();i++){
			Product product = productList.get(i);
			if(product.getPrice()!=12){
				System.out.printf("产品 %s:%f\n",product.getName(),product.getPrice());
			}
		}
		
		System.out.println("The End");
		
	}
}

class Task extends RecursiveAction{
	private List<Product> productList;
	private int low;
	private int high;
	private double increment;
	
	public Task(List<Product> productList, int low, int high, double increment) {
		super();
		this.productList = productList;
		this.low = low;
		this.high = high;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		if(high-low<10){
			increase();
		}else{
			int middle=(low+high)>>>1;
			System.out.printf("任务：pending 任务： %s\n",getQueuedTaskCount());
			invokeAll(new Task(productList, low, middle+1, increment),
					new Task(productList, middle+1, high, increment)
					);
		}
	}

	private void increase() {
		for(int i= low;i<high;i++){
			Product product = productList.get(i);
			product.setPrice(product.getPrice()*(1+increment));
		}
	}
	
}



class Product{
	private String name;
	private double price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}