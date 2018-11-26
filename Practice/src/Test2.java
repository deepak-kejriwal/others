import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test2 {

	public static void main(String[] args) {
		
		List<Integer> list1 = Stream.of(1, 2).collect(Collectors.toList());
		List<Integer> list2 = Stream.of(3, 4).collect(Collectors.toList());
		List<Integer> list3 = Stream.of(1, -1).collect(Collectors.toList());
		List<List<Integer>> allLocation = new ArrayList<>();
		allLocation.add(list1);
		allLocation.add(list2);
		allLocation.add(list3);
		String str;

		System.out.println(nearestSteakHouses(3, allLocation, 2));
	}

	static Comparator<List<Integer>> comparator=(List<Integer> xya, List<Integer> xyb) -> sqrt(xya).compareTo(sqrt(xyb));
	
	public static void main2(String[] args) {
		PriorityQueue<List<Integer>> pq=new PriorityQueue(comparator.reversed());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
	}
	
	public static List<List<Integer>> nearestSteakHouses(int totalSteakHouses, List<List<Integer>> allLocation,
			int numSteakHouses) {
		if(numSteakHouses>totalSteakHouses){
			return allLocation;
		}
		PriorityQueue<List<Integer>> pq=new PriorityQueue<>(numSteakHouses,comparator);
		Iterator<List<Integer>> itr=allLocation.iterator();
		while(itr.hasNext()) {
			List<Integer> data=itr.next();
			if(pq.size()<numSteakHouses) {
				pq.add(data);
			}else  {
				List<Integer> max=pq.peek();
				if(sqrt(max)>sqrt(data)) {
					pq.poll();
					pq.add(data);
				}
			}
		}
		

		return pq.stream().collect(Collectors.toList());
	}
	
	public static List<List<Integer>> nearestSteakHouses1(int totalSteakHouses, List<List<Integer>> allLocation,
			int numSteakHouses) {
		if(numSteakHouses>totalSteakHouses){
			return null;
		}
		allLocation=allLocation.stream().filter(s->s!=null).collect(Collectors.toList());
		allLocation.sort((List<Integer> xya, List<Integer> xyb) -> sqrt(xya).compareTo(sqrt(xyb)));

		return allLocation.subList(0, numSteakHouses);
	}

	private static Double sqrt(List<Integer> xya) {
		if(xya!=null && xya.size()==2) {
			Integer x = xya.get(0);
			Integer y = xya.get(1);
			if(x==null||y==null) {
				return Double.MAX_VALUE;
			}
			return Math.sqrt(x ^ 2 + y ^ 2);
		}
		return Double.MAX_VALUE;
	}

}
