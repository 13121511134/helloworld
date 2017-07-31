package sort;

import java.util.Arrays;

import org.junit.Test;
/**
 * 堆排序
 * <p>Title: HeapSort</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	范文鑫
 * @date	2016年11月10日下午10:55:36
 * @version 1.0
 */
public class HeapSort {

	@Test
	public void test() {
		int[]a={45,22,67,12,33,25,1,62};
		buildHeap(a);
		System.out.println(Arrays.toString(a));
		heapSort(a);
		System.out.println(Arrays.toString(a));
	}
	public static int leftChild(int i){
		return 2*i+1;
	}
	/**
	 * 下滤调整堆序
	 * <p>Title: percDown</p>
	 * <p>Description: </p>
	 * @param a
	 * @param i'
	 * @param n
	 */
	static void  percDown(int []a,int i, int n){
		int child;
		int tmp;
		for(tmp=a[i];leftChild(i)<n;i=child){
			child=leftChild(i);
			if(child!=n-1&&a[child]>a[child+1]){
				child++;
			}
			if(a[child]<tmp){
				a[i]=a[child];
			}else{
				break;
			}
		}
		a[i]=tmp;
	}
	/**
	 * 构建堆
	 * <p>Title: buildHeap</p>
	 * <p>Description: </p>
	 * @param a
	 */
	static void buildHeap(int[]a){
		for(int i=a.length/2-1;i>=0;i--){
			percDown(a, i, a.length);
		}
	}
	/**
	 * 排序。取堆顶元素，与最后一个交换，下滤，循环此过程
	 * <p>Title: heapSort</p>
	 * <p>Description: </p>
	 * @param a
	 */
	static void heapSort(int a[]){
		for(int i=a.length-1;i>0;i--){
			swap(a,0,i);
			percDown(a, 0,i);
		}
	}
	
	/**
	 * 交换两数
	 * <p>Title: swap</p>
	 * <p>Description: </p>
	 * @param a
	 * @param index1
	 * @param index2
	 */
	static void swap(int []a,int index1,int index2){
		a[index1]=a[index1]^a[index2];
		a[index2]=a[index1]^a[index2];
		a[index1]=a[index1]^a[index2];
	}
}
