package sort;

public class HeapSort_2 {
	public int getLeft(int i){
		return 2*i+1;
	}
	
	public void percDown(int []arr,int i,int length){
		int temp=arr[i];
		int child;
		for(;getLeft(i)<length;i=child){
			child=getLeft(i);
			if(child!=length-1&&arr[child]<arr[child+1]){
				child++;
			}
			if(temp<arr[child]){
				arr[i]=arr[child];
			}else{
				break;
			}
		}
		arr[i]=temp;
		
	}
	public void buildHeap(int [] arr){
		for(int i=arr.length/2-1;i>=0;i--){
			percDown(arr, i, arr.length);
		}
	}
	public void heapSort(int [] arr){
		for(int i=arr.length-1;i>0;i--){
			//交换头尾
			percDown(arr, 0, i);
		}
	}
}
