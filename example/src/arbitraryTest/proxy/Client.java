package arbitraryTest.proxy;

import arbitraryTest.proxy.test.Bird;
import arbitraryTest.proxy.test.Singable;


public class Client {
	public static void main(String[] args) throws Exception {
		Singable target = new Bird();
		InvocationHandler handler =new TimeHandler(target);
		Singable proxy =Proxy.newProxyInstance(Singable.class.getClassLoader(), Singable.class, handler);
		InvocationHandler handler2 =new LogHandler(proxy);
		Singable proxy2 =Proxy.newProxyInstance(Singable.class.getClassLoader(), Singable.class, handler2);
		String result = proxy2.sing("say something");
		System.out.println(result);
	}
}
	