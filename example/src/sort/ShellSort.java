package sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 希尔排序
 * <p>Title: ShellSort</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	范文鑫
 * @date	2016年11月7日下午10:27:07
 * @version 1.0
 */
public class ShellSort {
	@Test
	public  void test() {
		int []a={45,22,67,12,33,25,1,62};
		int j;
		int count = 0;
		for(int gap=a.length/2;gap>0;gap/=2){
			for(int i=gap;i<a.length;i++){
				int temp=a[i];
				for(j=i;j>=gap&&temp<a[j-gap];j-=gap){
					count++;
					a[j]=a[j-gap];
				}
				a[j]=temp;
			}
		}
		System.out.println("累计交换次数："+count);
		System.out.println(Arrays.toString(a));
	}
	
	
	public static void shellSort(int []arr){
		int j;
		for(int gap=arr.length/2;gap>0;gap/=2){
			for(int p=gap;p<arr.length;p++){
				int temp = arr[p];
				for(j=p;j>=gap&&arr[j-gap]>temp;j-=gap){
					arr[j]=arr[j-gap];
				}
				arr[j] =temp;
			}
		}
		
	}
}
