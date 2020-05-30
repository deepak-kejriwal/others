import java.util.HashMap;
import java.util.Map;

public class NodeWithHighestDescendantOptimizedFurther {

	Map<String,String> childToParent = new HashMap<>();
	Map<String, Integer> descendentCount = new HashMap<>();
	String res= null;
	int count = Integer.MIN_VALUE;
	
    public void reset() {
    	childToParent.clear();
    	descendentCount.clear();
    	res = null;
    	count = Integer.MIN_VALUE;
    }


	
	public  String parentWithHighestDescendent(String[] input) {
		
		reset();
			
		for(String parentChild : input) {
			String[] parentChildLink = parentChild.split(" ",2);
			String parent = parentChildLink[0];
			String[] childs = parentChildLink[1].split(" ");
			
			for(String child : childs) {
				union(parent, child);
			}
		}
		
		
		System.out.println("count: "+count);
		return res;
	}
	
    public void union(String people1, String people2) {
        String parent1 = find(people1);
        childToParent.put(people2,parent1);
        descendentCount.put(parent1, descendentCount.getOrDefault(parent1, 0)+ descendentCount.getOrDefault(people2, 0) +1);
        if(descendentCount.get(parent1)>count) {
        	count = descendentCount.get(parent1);
        	res = parent1;
        }
    }
    
    public String find(String people) {
        while (!people.equals(childToParent.getOrDefault(people,people))) {
        	String parent = childToParent.get(people);
        	childToParent.put(people, childToParent.getOrDefault(parent ,parent));
        	people = childToParent.get(people);
        }
        return people;

    }
    
    
    
    
    
	public static void main(String[] args) {
		NodeWithHighestDescendantOptimizedFurther test = new NodeWithHighestDescendantOptimizedFurther();
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
		
		String[] input4 = {
				"E F",
				"D E",
				"C D",
				"B C",
				"A B",
				"F G",
				"C H"
				
		};
		System.out.println(test.parentWithHighestDescendent(input4));
	}
	
    
/*
 
 A
 B
 C
 D
 E
 F
 
 F-E
 E-D
 D-C
 C-B
 B-A
  
  
 */




}
