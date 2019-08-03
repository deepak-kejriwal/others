package week2.Queue.SlidingWindow;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class StockPriceWithTime {

	public static void main(String[] args) {
		StockPriceWithTime st=new StockPriceWithTime(3);
		st.addPrice(5,1);
		st.addPrice(6,1);
		st.addPrice(7,2);
		st.addPrice(7,3);
		st.addPrice(8,4);
		st.addPrice(9,5);
		System.out.println(st.getMax());
		System.out.println(st.getMaxUsingStream());
	}

	Deque<Price> dq;
	int window;

	public StockPriceWithTime(int windowDays) {
		dq = new LinkedList<>();
		window = windowDays;
	}

	public void addPrice(int price, int day) {
		while (!dq.isEmpty() && dq.peekLast().getDay() < (day - window + 1))
			dq.removeLast();
		dq.addFirst(new Price(price, day));
	}

	// Returns max price in last 3 days
	public int getMax() {
		int maxPrice = 0;
		Iterator<Price> iter = dq.iterator();
		while (iter.hasNext()) {
			int price = ((Price) iter.next()).getPrice();
			if (price > maxPrice)
				maxPrice = price;
		}
		return maxPrice;
	}
	
	public int getMaxUsingStream() {
		return dq.stream().mapToInt(p->p.getPrice()).max().orElse(0);
	}

	/*
	 * Helper Code. Ask interviewer if they want you to implement.
	 */
	public class Price {
		int price;
		int day;

		public Price(int price, int day) {
			super();
			this.price = price;
			this.day = day;
		}

		public int getPrice() {
			return price;
		}

		public int getDay() {
			return day;
		}
	}
}
