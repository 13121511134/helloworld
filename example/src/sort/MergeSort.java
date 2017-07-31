package sort;

import java.util.Arrays;

import org.junit.Test;
/**
 * 归并排序
 * <p>Title: MergeSort</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	范文鑫
 * @date	2016年11月10日下午11:42:46
 * @version 1.0
 */
public class MergeSort {

	@Test
	public void test(){
		int []a={45,22,67,12,33,25,1,62};
		mergeSort(a);
		System.out.println(Arrays.toString(a));
	}
	public void mergeSort(int []a){
		int []temp =new int[a.length];
		mergeSort(a, temp, 0, a.length-1);
	}
	public void mergeSort(int []a,int []temp,int left,int right){
		if(left<right){
			int center = (left+right)/2;
			mergeSort(a, temp, left, center);
			mergeSort(a, temp, center+1, right);
			merge(a,temp,left,center+1,right);
		}
	}

	private void merge(int[] a, int[] temp, int leftPos, int rightPos, int rightEnd) {
		int leftEnd=rightPos-1;
		int tempPos=leftPos;
		//记录合并后的总数，拷贝回原数组时用
		int elementsNum=rightEnd-leftPos+1;
		//比较左右数组，进行合并
		while(leftPos<=leftEnd&&rightPos<=rightEnd){
			if(a[leftPos]<=a[rightPos]){
				temp[tempPos++]=a[leftPos++];
			}else{
				temp[tempPos++]=a[rightPos++];
			}
		}
		//合并左/右数组剩余项
		while(leftPos<=leftEnd){
			temp[tempPos++]=a[leftPos++]; 
		}
		while(rightPos<=rightEnd){
			temp[tempPos++]=a[rightPos++];
		}
		//拷贝回原数组
		for(int i=0;i<elementsNum;i++,rightEnd--){
			a[rightEnd]=temp[rightEnd];
		}
		
	}
}
