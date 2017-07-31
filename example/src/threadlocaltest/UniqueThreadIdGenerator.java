package threadlocaltest;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class UniqueThreadIdGenerator {

    private static final AtomicInteger uniqueId = new AtomicInteger(0);

    private static final ThreadLocal < Integer > uniqueNum = 
        new ThreadLocal < Integer > () {
            @Override protected Integer initialValue() {
                return uniqueId.getAndIncrement();
        }
    };

    public static int getCurrentThreadId() {
        return uniqueNum.get();
    }

     public static void main(String[] args) throws InterruptedException {
    	 Thread [] threads = new Thread[10];
    	 for(int i=0;i<threads.length;i++){
    		 
    		 threads[i]=new Thread(new Runnable() {
    			 
    			 @Override
    			 public void run() {
    				 System.out.println(getCurrentThreadId());
    				 try {
						Thread.sleep(3+new Random().nextInt(10)*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			 }
    		 });
    		 threads[i].start();
    	 }
    	 Thread.sleep(5000);
    	for (int i = 0; i < 10; i++) {
    		 threads[i].join();
		}
		System.out.println("uniqueId="+uniqueId);
	}
 } // UniqueThreadIdGenerator
 