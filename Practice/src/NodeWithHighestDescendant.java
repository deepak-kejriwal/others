import java.util.HashMap;
import java.util.Map;

public class NodeWithHighestDescendant {

	Map<String,String[]> parentChildMap = new HashMap<>();
	Map<String,String> childToParent = new HashMap<>();
	Map<String, Integer> descendentCount = new HashMap<>();
	
    public void reset() {
    	parentChildMap.clear();
    	childToParent.clear();
    	descendentCount.clear();
    }
	public static void main(String[] args) {
		NodeWithHighestDescendant test = new NodeWithHighestDescendant();
		String[] input1 = {
				"A B C D E",
				"B F G",
				"F H I J",
				"G K",
				"L M N"
				
		};
		System.out.println(test.parentWithHighestDescendent(input1));
		
		
		String[] input2 = {
				"A B C",
				"B D E",
				"F G H I",
				"G J K",
				"L M N"
				
		};
		System.out.println(test.parentWithHighestDescendent(input2));
		
		String[] input3 = {
				"A B C",
				"B D E",
				"F G H I",
				"G J K",
				"L M N O P Q R"
				
		};
		System.out.println(test.parentWithHighestDescendent(input3));
	}
	

	
	public  String parentWithHighestDescendent(String[] input) {
		reset();

		for(String parentChild : input) {
			String[] parentChildLink = parentChild.split(" ",2);
			//Key will be parent, value will be array of all it direct child.
			parentChildMap.put(parentChildLink[0], parentChildLink[1].split(" "));
			childToParent.put(parentChildLink[0], parentChildLink[0]);
		}
				
		for(Map.Entry<String, String[]> entry: parentChildMap.entrySet()) {
			String parent = entry.getKey();
			String[] childs = entry.getValue();
			for(String child : childs) {
				union(parent, child);
			}
		}
		
		String res = null;
		int count = Integer.MIN_VALUE;
		for(Map.Entry<String, Integer> entry : descendentCount.entrySet()) {
			if(entry.getValue()>count) {
				count = entry.getValue();
				res = entry.getKey();
			}
		}
		System.out.println("count: "+count);
		return res;
	}
	
    public void union(String people1, String people2) {
        String parent1 = find(people1);
        childToParent.put(people2,parent1);
        descendentCount.put(parent1, descendentCount.getOrDefault(parent1, 0)+ descendentCount.getOrDefault(people2, 0) +1);
    }
    
    public String find(String people) {
        while (!people.equals(childToParent.getOrDefault(people,people))) {
        	childToParent.put(people, childToParent.getOrDefault(childToParent.get(people),people));
        	people = childToParent.get(people);
        }
        return people;

    }
    


}
