package arbitraryTest.proxy;

import util.LoggerUtil;

public class Tank implements Movable{

	@Override
	public void move() {
		LoggerUtil.info("tank moves! ");
	}

	@Override
	public String move(int x, int y) {
		LoggerUtil.info("tank moves to x :{} , move to y :{} ! ",x,y);
		return null;
	}

}
