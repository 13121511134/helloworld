package threadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.experimental.theories.Theories;

import sun.management.snmp.jvminstr.JvmClassLoadingImpl;


class DeadLock implements Runnable{
	public DeadLock (int a,int b){
		this.a=a ;
		this.b =b;
	}
	int a ,b;
	public void run(){
		synchronized(Integer.valueOf(a)){
			synchronized (Integer.valueOf(b)) {
				System.out.println(a+b);
			}
		}
	}
	
}
public class JVMTest {
	public static void main(String[] args) {
		String name =new JVMTest().getClass().getName();
		System.out.println(name);
	}
}
