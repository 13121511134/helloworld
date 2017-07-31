package arbitraryTest.proxy.test;

import util.LoggerUtil;

public class Bird implements Singable{

	@Override
	public String sing() {
		LoggerUtil.info("I'm a bird, I like sing song.");
		return "天台见";
	}

	@Override
	public void openMouse() {
		LoggerUtil.info("I'll open my mouse before singing.");
	}

	@Override
	public String sing(String msg) {
		LoggerUtil.info("I'm fine, thank you !"+msg);
		return "ok";
	}

}
