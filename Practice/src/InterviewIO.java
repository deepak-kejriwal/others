import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InterviewIO {
	public static void main(String[] args) {
		List<Integer> list= Stream.of(1,2,3).collect(Collectors.toList());
		PriorityQueue<Integer> pq=new PriorityQueue<>(list);
		LinkedList stk;
		Stack sk;
		String str;
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		pq=new PriorityQueue<>((x,y)->y.compareTo(x));
		pq.addAll(list);
		System.out.println(pq.poll());
		System.out.println(pq.poll());
	}
	public static void main1(String[] args) {
		int x=new InterviewIO().countPaths(3,5);
		System.out.println(x);
		Double d;
		Integer i;
Deque<String> dq=new LinkedList<>();

	}
	
	public static int[][] memo=null;//new int[m+1][n+1];

	public  int countPaths(int m, int n){
	    if(m==1 || n==1) {
	    return 1;
	    }
	    memo=new int[m+1][n+1];
	    return countPathsWithMemo(m,n);
	}

	public  int countPathsWithMemo(int m, int n){
	    
	    if(m==1 || n==1) return 1;
	    if(memo==null) memo=new int[m+1][n+1];
	    int x=0;
	    int y=0;
	    if(memo[m-1][n]!=0){
	        x=memo[m-1][n];
	    }else{
	        x=countPathsWithMemo(m-1, n);
	        memo[m-1][n]=x;
	    }
	    if(memo[m][n-1]!=0){
	        y=memo[m][n-1];
	    }else{
	        y=countPathsWithMemo(m, n-1);
	        memo[m][n-1]=y;
	    }
	    return x+y;
	}
}
