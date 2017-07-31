package arbitraryTest;

import com.sun.org.apache.xerces.internal.util.SAXInputSource;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class KafkaProperties
{
	private static Integer lock = 1;
	public static synchronized void print(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test_static_method");
	}
	public KafkaProperties(){
		System.out.println("Create a instance");
	}
	public void testSynch(){
		synchronized (lock) {
			try {
				
				System.out.println("test_start");
				Thread.sleep(10000);
				System.out.println("test_end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
    public static void main(String[] args) {
    	final KafkaProperties instance = new KafkaProperties();
//    	instance.testSynch();
    	KafkaProperties.print();
    	new Thread(new Runnable() {
			@Override
			public void run() {
				new KafkaProperties();
//				KafkaProperties.print();
			}
		}).start();
	}
}