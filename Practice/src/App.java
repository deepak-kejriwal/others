

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Map<String,List<String>> map = new HashMap<>();
    	
    	String str1= "cat";
    	char[] res = new char[26];
    	Arrays.fill(res, 'a');
    	//res[0] = 127;
    	char[] chs = str1.toCharArray();
    	for(char c:chs) {
    		res[c-'a']++;
    	}
    	String key = new String(res);
    	System.out.println(key);
    	map.putIfAbsent(key, new LinkedList<>());
    	map.get(key).add(str1);
    	
    	 str1= "tac";
    	res = new char[26];
    	Arrays.fill(res, 'a');
    	//res[0] = 127;
    	 chs = str1.toCharArray();
    	for(char c:chs) {
    		res[c-'a']++;
    	}
    	key = new String(res);
    	System.out.println(key);
    	map.putIfAbsent(key, new LinkedList<>());
    	map.get(key).add(str1);
    	
    	System.out.println(map);
    	
        System.out.println( "Hello World!" );
    }
}
