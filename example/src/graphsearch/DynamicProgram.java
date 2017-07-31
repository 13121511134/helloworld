package graphsearch;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.experimental.max.MaxCore;
//动态规划、LCS（最大公共子序列）LIS（最大递增子序列）
public class DynamicProgram {
	@Test
	public void test(){
		int [] a ={5,3,4,8,6,7};
		int [] dp=new int[a.length];
		for(int i=0;i<a.length;i++){
			dp[i]=1;
			for(int j=0;j<i;j++){
				if(a[j]<a[i]){
					dp[i]=dp[i]>dp[j]+1?dp[i]:dp[j]+1;
				}
			}
		}
		System.out.println(Arrays.toString(dp));
	}
	/**
	 * 找出唯一一个只出现一次的数字
	 * <p>Title: FindNumber</p>
	 * <p>Description: </p>
	 */
	@Test
	public void FindNumber(){
		  int a[] = {2, 3, 1, 2, 3, 6, 1, 2, 3, 1};  
		  int bits[]=new int[32];  
		  int i, j;  
		  for (i = 0; i < 10; i++)  
		    for (j = 0; j < 32; j++)  
		      bits[j] += ((a[i] >> j) & 1);  
		  // 如果某位上的结果不能被整除，则肯定目标数字在这一位上为  
		  int result = 0;  
		  for (j = 0; j < 32; j++)  
		    if (bits[j] % 3 != 0)  
		      result += (1 << j);  
		  System.out.println(result);
	}
	@Test
	public void testReflect() throws Exception{
		//LCS最长公共子序列
		int  a[]= {2,4,3,6,1,8,12};
		int [] b ={4,7,3,6,9,8,11,12,14};
		int m=a.length,n=b.length;
		int d[][]=new int [m+1][n+1];
		for(int i=1;i<=m;i++){
			for(int j=1;j<=n;j++){
				if(a[i-1]==b[j-1])
					d[i][j] = d[i-1][j-1]+1;
				else
					d[i][j] = Math.max(d[i][j-1], d[i-1][j]);
			}
		}
		for(int i=0;i<=m;i++){
			System.out.println(Arrays.toString(d[i]));
		}
		
	}
}
