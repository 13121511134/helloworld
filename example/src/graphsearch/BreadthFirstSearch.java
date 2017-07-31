package graphsearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

//深度/广度优先搜索
//使用队列
public class BreadthFirstSearch {
	private String [] node={"A", "B", "C", "D", "E", "F", "G", "H", "I"};
	private boolean[] flag;
	private int[][] edges = {   
            { 0, 1, 0, 0, 0, 1, 0, 0, 0 }, 
            { 1, 0, 1, 0, 0, 0, 1, 0, 1 }, 
            { 0, 1, 0, 1, 0, 0, 0, 0, 1 },  
            { 0, 0, 1, 0, 1, 0, 1, 1, 1 }, 
            { 0, 0, 0, 1, 0, 1, 0, 1, 0 }, 
            { 1, 0, 0, 0, 1, 0, 1, 0, 0 },  
            { 0, 1, 0, 1, 0, 1, 0, 1, 0 }, 
            { 0, 0, 0, 1, 1, 0, 1, 0, 0 }, 
            { 0, 1, 1, 1, 0, 0, 0, 0, 0 }   
            };   
	public void testBFS(){
		
	}
	@Test
	public void testDFS_recursion(){
		flag=new boolean[node.length];
		for(int i=0;i<node.length;i++){
			if(!flag[i]){
				dfs_recursion(i);
			}
		}
	}
	@Test
	public void testDFS_stack(){
		flag=new boolean[node.length];
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<node.length;i++){
			if(!flag[i]){
				stack.push(i);
			}
			for(;!stack.isEmpty();){
				int k= stack.pop();
				flag[k] = true;
				System.out.print(node[k]+" ");
				for(int j=0;j<node.length;j++){
					if(!flag[j]&&edges[k][j]==1){
						stack.push(j);
						break;
					}
				}
			}
		}
	}
	@Test
	public void breadthFirstSearch(){
		flag=new boolean[node.length];
		Queue<Integer> queue = new LinkedList<>();
		for(int i=0;i<node.length;i++){
			if(!flag[i]){
				queue.add(i);
				flag[i] = true;
				System.out.print(node[i]+" ");
			}
			for(;!queue.isEmpty();){
				int k= queue.poll();
				for(int j=0;j<node.length;j++){
					if(!flag[j]&&edges[k][j]==1){
						queue.add(j);
						flag[j] = true;
						System.out.print(node[j]+" ");
					}
				}
			}
		}
	}
	
	private void dfs_recursion(int i){
		flag[i] = true;
		System.out.print(node[i]+" ");
		for(int j=0;j<node.length;j++){
			if(!flag[j]&&edges[i][j]==1){
				dfs_recursion(j);
			}
		}
	}

}
