package threadlocaltest;

 class Person{
	private int age=0;
	private String name="";
	public Person(){
		
	}
	public Person(int age ,String name){
		this.age = age;
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public String toString(){
//		return "name="+name+"age= "+age;
//	}
}
public class TestNum {  
    // ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值  
	private static Person person = new Person();
    private static ThreadLocal<Person> seqNum = new ThreadLocal<Person>() {  
        public Person initialValue() {  
            return person;  
        }  
    };  
 static class A{
	 
	  private  TestNum m=new TestNum();
	  
  }
    // ②获取下一个序列值  
    public Person getNextNum() {  
//    	Person p = seqNum.get();
//    	p.setAge(p.getAge()+1);
//        seqNum.set(p);  
        return seqNum.get();  
    }  
  
    public static void main(String[] args) {  
        TestNum sn = new TestNum();  
        // ③ 3个线程共享sn，各自产生序列号  
        TestClient t1 = new TestClient(sn);  
        TestClient t2 = new TestClient(sn);  
        TestClient t3 = new TestClient(sn);  
        t1.start();  
        t2.start();  
        t3.start();  
    }  
  
    private static class TestClient extends Thread {  
        private TestNum sn;  
  
        public TestClient(TestNum sn) {  
            this.sn = sn;  
        }  
  
        public void run() {  
            for (int i = 0; i < 3; i++) {  
                // ④每个线程打出3个序列值  
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["  
                         + sn.getNextNum() + "]");  
            }  
        }  
    }  
}  