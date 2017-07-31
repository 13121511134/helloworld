package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.Test;

public class QuickSort {
	
	@Test
	public void test(){
		int []a={45,22,67,12,33,25,1,62};
		quickSort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public void quickSort(int []a){
		quickSort(a, 0, a.length-1);
	}
	/**
	 * 三数中值法
	 * <p>Title: median</p>
	 * <p>Description: </p>
	 * @param a
	 * @param left
	 * @param right
	 * @return
	 */
	public int median(int a[],int left,int right){
		int center = (left+right)/2;
		if(a[left]>a[center]){
			swap(a, left, center);
		}
		if(a[left]>a[right]){
			swap(a, left, right);
		}
		if(a[center]>a[right]){
			swap(a, center, right);
		}
		swap(a, center, right-1);
		return a[right-1];
	}
	
	public void quickSort(int a[],int left,int right){
		if(left<right){
			int median=median(a, left, right);
			int i=left,j=right-1;
			for(;;){
				while(a[++i]<median);
				while(a[--j]>median);
				if(i<j){
					swap(a, i, j);
				}else{
					break;
				}
			}
			if(i<right-1){
				swap(a, i, right-1);
				
			}
			quickSort(a, left, i-1);
			quickSort(a, i+1, right);
		}
		
	}
	
	public void swap(int []a,int index1,int index2){
		int temp =a[index1];
		a[index1]=a[index2];
		a[index2]=temp;
	}
	
	public static void insertion_sort(int a[],int left,int right)
	{
	   int j;
	   for(int p=left+1; p<=right;p++)
	   {
		   int tmp=a[p];
		   for(j=p;j>left&&tmp<a[j-1];j--){
			   a[j]=a[j-1];
		   }
		   a[j]=tmp;
	   }
	}
	
	/**
	 * 快速排序雏形
	 * 选出枢纽元，对小于/大于枢纽元的分别进行快排
	 * <p>Title: sort</p>
	 * <p>Description: </p>
	 * @param list
	 */
	public void sort(List<Integer> list){
		if(list.size()>1){
			List<Integer>smaller = new ArrayList<>();
			List<Integer>larger =new ArrayList<>();
			List<Integer> same =new ArrayList<>();
			Integer chosenItem = list.get(list.size()/2);
			for(Integer i:list){
				if(i<chosenItem){
					smaller.add(i);
				}else if(i>chosenItem){
					larger.add(i);
				}else{
					same.add(i);
				}
			}
			
			sort(smaller);
			sort(larger);
			
			list.clear();
			list.addAll(smaller);
			list.addAll(same);
			list.addAll(larger);
		}
	}
	public void test2(){
		Integer []a={45,22,67,12,33,25,1,62};
		/**
		 * Arrays.asList 方法返回值是Arrays$ArrayList，其中add,remove等方法默认继承父类
		 */
		List<Integer> array=new ArrayList<>(Arrays.asList(a));
		sort(array);
		System.out.println(Arrays.toString(array.toArray()));
		
		
	}
}
