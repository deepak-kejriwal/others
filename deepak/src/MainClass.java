import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    
    public static void main(String[] args) throws IOException {
       // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line="[3,10,5]";
        //while ((line = in.readLine()) != null) {
            int[] stones = stringToIntegerArray(line);
            line = "3";
            int K = Integer.parseInt(line);
            
            String ret = new MainClass().crackSafe(2,2);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        //}
    }
    
    Set<String> seen;
    StringBuilder ans;

    public String crackSafe(int n, int k) {
        if (n == 1 && k == 1) return "0";
        seen = new HashSet();
        ans = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n-1; ++i)
            sb.append("0");
        String start = sb.toString();
        System.out.print(sb);
        dfs(start, k);
        ans.append(start);
        return new String(ans);
    }
//0
//00
//01
//10
    public void dfs(String node, int k) {
        for (int x = 0; x < k; ++x) {
            String nei = node + x;
            if (!seen.contains(nei)) {
                seen.add(nei);
                dfs(nei.substring(1), k);
                ans.append(x);
            }
        }
    }
}