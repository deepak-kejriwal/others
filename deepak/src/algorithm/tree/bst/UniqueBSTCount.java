package algorithm.tree.bst;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * 
 * 
Problem: Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 * 
 * 
 */
public class UniqueBSTCount {

	public static void main(String[] args) {
		new UniqueBSTCount().solution();

	}

	private void solution() {
		int result = numTrees(4);
		System.out.println(result);

	}

	public int numTrees(int n) {
		int[] G = numTreesForUptoN(n);
		//List<Integer> list=Arrays.stream(G).boxed().collect(Collectors.toList());
		Map<Double,Integer> map=IntStream.range(0, G.length).boxed().collect(Collectors.toMap(i->(Double)Math.pow(i,4), i->G[i]));
		System.out.println(map);
		return G[n];
	}

	private int[] numTreesForUptoN(int n) {
		int[] G = new int[n + 1];
		G[0] = G[1] = 1;

		for (int i = 2; i <= n; ++i) {
			for (int j = 0; j < i / 2; ++j) {
				G[i] += 2 * G[j] * G[i - j - 1];
			}
			if (i % 2 != 0) {
				G[i] += G[i / 2] * G[i / 2];
			}
		}
		return G;
	}
}
