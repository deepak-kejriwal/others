import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * 
 * 
You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.

 

Example 1:

Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 

Note:

0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier. 
 * 
 */
public class ReorderLogFiles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String[] reorderLogFiles(String[] logs) {
        List<String> num = new ArrayList<>();
        TreeMap<String,Set<String>> letter = new TreeMap<>();
        for (String log : logs) {
            int idx = log.indexOf(' ');
            String iden = log.substring(0,idx);
            String val = log.substring(idx+1);
            if (Character.isDigit(val.charAt(0))) {
                num.add(log);
            }else{
                letter.putIfAbsent(val,new TreeSet<>());
                letter.get(val).add(iden);
            }
        }
        String[] res = new String[logs.length];
        int idx = 0;
        for (String key : letter.keySet()) {
            for (String iden : letter.get(key)) {
                res[idx++] = iden+" "+key;
            }
        }
        for (String digitLog : num) {
            res[idx++] = digitLog;
        }
        return res;
    }

}
