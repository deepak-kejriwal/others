package algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSumII {

	public static void main(String[] args) {
		System.out.println(new CombinationSumII().combinationSum2(new int[] { 1, 2, 7, 6, 1, 5 }, 8));
	}

	public Set<List<Integer>> combinationSum2(int[] candidates, int target) {
		String v360s;
		Arrays.sort(candidates);
		int len = Arrays.binarySearch(candidates, target);
		len = Math.min(Math.abs(len), candidates.length);
		Set<List<Integer>> res = new HashSet<>();
		// System.out.println(len);
		backtrack(res, new ArrayList<Integer>(), candidates, 0, len, target);
		return res;
	}

	private void backtrack(Set<List<Integer>> res, List<Integer> tempList, int[] nums, int index, int len,
			int target) {

		if (target < 0) {
			return;
		}
		System.out.println("T:" + target);
		if (target == 0) {
			System.out.println("dk01");
			res.add(new ArrayList<>(tempList));
			return;
		}

		for (int i = index; i < len; i++) {
			System.out.println(nums[i]);
			tempList.add(nums[i]);
			backtrack(res, tempList, nums, i + 1, len, target - nums[i]);
			tempList.remove(tempList.size() - 1);
		}

	}
}
