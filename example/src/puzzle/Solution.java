package puzzle;

import java.text.spi.DecimalFormatSymbolsProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javax.xml.stream.events.StartDocument;

import org.junit.Test;

import com.sun.accessibility.internal.resources.accessibility;
import com.sun.javafx.sg.prism.web.NGWebView;
import com.sun.org.apache.xalan.internal.xsltc.trax.SAX2DOM;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;
import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Detail1_1Impl;

import sun.awt.image.ImageWatched.Link;
import sun.reflect.generics.tree.Tree;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class TreeNode {
    int val;
     TreeNode right;
     TreeNode left ;
    TreeNode(int x) { val = x; }
    @Override
    public String toString() {
    	if(right==null){
    		return val+"";
    	}
    	return val+"=->"+right;
    }
   
}


public class Solution {
		    public int minSteps(int n) {
		    	if (n==1)return 0;
		    	int sum =0 ;
		    	int temp =n;
		    	for(int i=2;i<=temp;){
		    		if(temp%i==0){
		    			temp=temp/i;
		    			sum+=i;
		    		}else{
		    			i++;
		    		}
		    	}
		    	return sum==0?n:sum;
		        
		    }
    
	@Test
	 public  void main() {
		TreeNode a=new TreeNode(1);
		TreeNode b=new TreeNode(2);
		TreeNode c=new TreeNode(3);
		TreeNode d=new TreeNode(4);
		TreeNode e=new TreeNode(5);
		TreeNode f=new TreeNode(6);
		TreeNode g=new TreeNode(7);
		TreeNode h=new TreeNode(8);
		TreeNode i=new TreeNode(9);
		a.right=b;b.right=c;c.right=d;d.right=e;e.right=f;
		h.right=i;f.right=g;g.right=h;
		System.out.println(minSteps(12));
	}
	 
}