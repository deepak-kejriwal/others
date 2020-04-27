package algorithm;

public class TreeNode<T> {
	public static void main(String[] args) {
		TreeNode<Integer> node=new TreeNode<>();
		node.data=1;
		TreeNode<Integer> left=new TreeNode<>();
		left.data=2;
		TreeNode<Integer> right=new TreeNode<>();
		right.data=3;
		node.left=left;
		node.right=right;
		left.left=right;
		System.out.println(node);
		
		Integer a=5;
		Integer b=6;
		Integer c=7;
		String str1="    "+a+"    \n  /   \\\n /     \\\n"+b+"       "+c;
		String str2="  /   \\\n";
		String str3=" /     \\\n";
		String str4=b+"       "+c;
		//System.out.println(str1);
	}
	T data;
	TreeNode<T> left;
	TreeNode<T> right;

	@Override
	public String toString() {
		Integer a=5;
		Integer b=6;
		Integer c=7;
		String str="    "+data+"    \n  /   \\\n /     \\\n"+left.data+"       "+right.data;
		return str;

	}

}
