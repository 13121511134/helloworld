package sort;

import java.util.Arrays;

import org.junit.Test;
/**
 * 选择排序
 * <p>Title: SelectSort</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	范文鑫
 * @date	2017年5月27日上午2:13:56
 * @version 1.0
 */
public class SelectSort {
	@Test
	public void test(){
		int []a={45,22,67,12,33,25,1,62};
		int min;
		int count = 0;
		for(int i=0;i<a.length;i++){
			min=i;
			for(int j=i+1;j<a.length;j++){
				if(a[j]<a[min]){
					count++;
					min=j;
				}
			}
//			if(i!=min){
//			//防止无意义的交换
			int temp=a[min];
			a[min]=a[i];
			a[i]=temp;
//			}
		}
		System.out.println(count);
		System.out.println(Arrays.toString(a));
	}
}
