package week2.ArraysAndStringsII;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class StockKTime {

	public static void main(String[] args) {
		List<Double> list = Stream.of(1.0, 2.0, 1.0, 3.0, 1.0, 3.0, 1.0, 4.0, 1.0, 5.0, 1.0, 6.0)
				.collect(Collectors.toList());
		int k = 5;
		double result=maxKPairsProfits(list,k);
		System.out.println(result);
	}

	public static double maxKPairsProfits(List<Double> A, int k) {
		List<Double> kSum = new ArrayList<>();
		for (int i = 0; i < k * 2; ++i) {
			kSum.add(Double.NEGATIVE_INFINITY);
		}
		for (int i = 0; i < A.size(); ++i) {
			List<Double> preKSum = new ArrayList<>(kSum);
			for (int j = 0, sign = -1; j < kSum.size() && j <= i; ++j, sign *= -1) {
				double diff = sign * A.get(i) + (j == 0 ? 0 : preKSum.get(j - 1));
				kSum.set(j, Math.max(diff, preKSum.get(j)));
			}
		}
		// Returns the last selling profits as the answer.
		return kSum.get(kSum.size() - 1);
	}
}
