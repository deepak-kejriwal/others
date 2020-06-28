import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class TestJavaFeatures {

	public static void main(String[] args) throws Exception {
		List<String> list = new ArrayList<>(3);
		String st = list.get(0);
		Map<String,Integer> map = new HashMap<>();
		String[] strs= {"I","am","good"};
		String str=String.join(" ",strs);
		System.out.println(str);
		map.compute("1", (k,v)-> v==null ? 1 : v+1);
		map.compute("1", (k,v)-> v==null ? 1 : v+1);
		System.out.println(map);
		map.compute("1", (k,v)-> v==1 ? map.remove(k) : v-1);
		map.compute("1", (k,v)-> v==1 ? map.remove(k) : v-1);
		System.out.println(map);
		Set<String> set;new HashSet<>();
		Stack<String> stack = new Stack<>();
		stack.push("a");
		stack.push("b");
		stack.push("b");
		stack.push("d");
		System.out.println(String.join(":", stack));
		
		/*
		
		List<String> list = Stream.of("abc", "defg", "hi").collect(Collectors.toList());
		List<Integer> listInt = list.stream().map((@NotNull String str) -> {
			return str.length();

		}).collect(Collectors.toList());
		System.out.println(listInt);
		LinkedList<Integer>[] lists=new LinkedList[10];
		System.out.println(lists);
		Stack<Integer> stk=new Stack<>();
		stk.firstElement();
		*/
		
	}


}
