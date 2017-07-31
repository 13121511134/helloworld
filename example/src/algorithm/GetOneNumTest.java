package algorithm;

import org.junit.Test;
/**
 * 取正整数 二进制形式1的个数
 * <p>Title: GetOneNumTest</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	范文鑫
 * @date	2016年11月8日下午9:37:43
 * @version 1.0
 */
public class GetOneNumTest {
	int n=15;
	@Test
	public void test1() {
		int tmp = n - ((n >> 1) & 033333333333) - ((n >> 2) & 011111111111);
		System.out.println(((tmp + (tmp >> 3)) & 030707070707) % 63);

	}
	
	@Test
	public void test2() {
		int c;
		for(c=0;n>0;n >>= 1){
			c+=n&1;
		}
		System.out.println(c);
	}
}
