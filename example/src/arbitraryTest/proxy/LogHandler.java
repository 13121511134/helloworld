package arbitraryTest.proxy;

import java.lang.reflect.Method;

import util.LoggerUtil;

public class LogHandler implements InvocationHandler{

	Object target;
	public LogHandler(Object target) {
		this.target = target;
	}
	@Override
	public 	Object invoke(Object o,Method m,Object[]args) throws Throwable {
		LoggerUtil.info("代理————日志---开始");
		Object result = null;
		result= m.invoke(target, args);
		LoggerUtil.info("代理————日志---结束");
		return result;
	}

}
