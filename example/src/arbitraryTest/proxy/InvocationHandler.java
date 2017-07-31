package arbitraryTest.proxy;

import java.lang.reflect.Method;

public interface InvocationHandler {
	Object invoke(Object o,Method m,Object...args) throws Throwable;
}
