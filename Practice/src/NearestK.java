import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NearestK {

	public static void main(String[] args) {		
		List<Integer> list1 = Stream.of(1, 2).collect(Collectors.toList());
		List<Integer> list2 = Stream.of(3, 4).collect(Collectors.toList());
		List<Integer> list3 = Stream.of(1, -1).collect(Collectors.toList());
		List<Integer> list4 = Stream.of(5, 5).collect(Collectors.toList());
		List<List<Integer>> allLocation = new ArrayList<>();
		allLocation.add(list4);
		allLocation.add(list1);
		allLocation.add(list2);
		allLocation.add(list3);
		System.out.println(nearestSteakHouses(4, allLocation, 2));
	}
	
	public static List<List<Integer>> nearestSteakHouses(int totalSteakHouses, List<List<Integer>> allLocation,
			int numSteakHouses) {
		if(numSteakHouses>totalSteakHouses){
			return allLocation;
		}
		PriorityQueue<List<Integer>> pq=new PriorityQueue<>((xya,  xyb) -> sqrt(xyb).compareTo(sqrt(xya)));
		Iterator<List<Integer>> itr=allLocation.iterator();
		while(itr.hasNext()) {
			List<Integer> data=itr.next();
			pq.add(data);
			if(pq.size()>numSteakHouses) {
				pq.poll();
			}
		}
		return pq.stream().collect(Collectors.toList());
	}
	
	private static Double sqrt(List<Integer> xya) {
		if(xya!=null && xya.size()==2) {
			Integer x = xya.get(0);
			Integer y = xya.get(1);
			if(x==null||y==null) {
				return Double.MAX_VALUE;
			}
			Double res=Math.sqrt(x*x + y*y);
			return res;
		}
		return Double.MAX_VALUE;
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
}
