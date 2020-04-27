package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class VendorInterval {

	public static void main(String[] args) {
		new VendorInterval().test();
	}

	public void test() {
		Interval v1 = new Interval(1, 5, 28);
		Interval v2 = new Interval(3, 7, 15);
		Interval v3 = new Interval(6, 12, 8);
		List<Interval> vendors = new ArrayList<>();
		vendors.add(v1);
		vendors.add(v2);
		vendors.add(v3);
		vendors = minimumPriceIntervals(vendors);
		System.out.println(vendors);
	}

	public List<Interval> minimumPriceIntervals(List<Interval> vendors) {

		List<Interval> output = new LinkedList<>();
		Map<Interval, Integer> map = new TreeMap<>((v1, v2) -> Integer.compare(v1.startTime, v2.startTime));

		for (Interval v : vendors) {
			int start = v.startTime;
			int end = v.endTime;
			int price = v.price;
			for (int i = start; i < end; i++) {
				Interval key = new Interval(i, i + 1, price);
				if (map.getOrDefault(key, Integer.MAX_VALUE) > price) {
					map.put(key, price);
				}
			}
		}

		int prevPrice = Integer.MAX_VALUE;
		int start = 0;
		int end = 0;
		for (Entry<Interval, Integer> entry : map.entrySet()) {
			int price = entry.getValue();
			if (price != prevPrice) {
				output.add(new Interval(start, end, prevPrice));
				prevPrice = price;
				start = entry.getKey().startTime;
				end = entry.getKey().endTime;
			} else {
				end = entry.getKey().endTime;
			}
		}
		output.add(new Interval(start, end, prevPrice));
		output.remove(0);
		return output;
	}

	private List<Interval> minimumPriceIntervals1(List<Interval> vendors) {
		vendors.sort((v1, v2) -> Integer.compare(v1.startTime, v2.startTime));
		List<Interval> output = new ArrayList<>();

		Interval first = vendors.get(0);
		Interval second = null;
		for (int i = 1; i < vendors.size(); i++) {
			second = vendors.get(i);

		}
		return output;
	}

	class Interval {
		int startTime;
		int endTime;
		int price;

		public Interval(int startTime, int endTime, int price) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.price = price;
		}

		@Override
		public String toString() {
			return "[" + startTime + "," + endTime + "," + price + "]";
		}
	}

}
