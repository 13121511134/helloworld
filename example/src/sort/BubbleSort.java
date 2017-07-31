package sort;

import java.util.Arrays;

import org.junit.Test;
/**
 * 冒泡排序
 * <p>Title: BubbleSort</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	范文鑫
 * @date	2017年5月27日上午1:41:44
 * @version 1.0
 */
public class BubbleSort {
	@Test
	public void test(){
		int []a={45,22,67,12,33,25,1,62};
		int count = 0;
		for(int i=0;i<a.length;i++){
//			boolean flag = true;   //标记如果一次都没有交换说明排序已经完成[5,4,1,2,3]
			for(int j=1;j<a.length-i;j++){
				if(a[j-1]>a[j]){
					count++;
					int temp = a[j];
					a[j] = a[j-1];
					a[j-1] = temp;
					//flag=false;
				}
			}
//			if(flag)
//				break;
		}
		System.out.println("累计交换次数："+count);
		System.out.println(Arrays.toString(a));
	}
}
