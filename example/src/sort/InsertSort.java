package sort;

import java.util.Arrays;
/**
 * 插入排序 （整理扑克牌）
 * <p>Title: InsertSort</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	范文鑫
 * @date	2016年11月7日下午10:26:13
 * @version 1.0
 */
public class InsertSort {
	public static void main(String[] args) {
		int []a={45,22,67,12,33,25,1,62};
		insertion_sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public static void insertion_sort(int a[])
	{
		int count = 0;
	   int j;
	   for(int p=1; p<a.length;p++)
	   {
		   int tmp=a[p];
		   for(j=p;j>0&&tmp<a[j-1];j--){
			   a[j]=a[j-1];
			   count++;
		   }
		   a[j]=tmp;
	   }
	   System.out.println("比较次数："+count);
	}
}
