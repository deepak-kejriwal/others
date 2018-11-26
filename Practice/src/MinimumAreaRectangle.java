import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 
 * 
Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.

Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2

 * 
 * 
 */
public class MinimumAreaRectangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int minAreaRect(int[][] points) {
        HashMap<Integer, List<Integer>> x = new HashMap<>(); //maps x -> list y
        HashMap<Integer, List<Integer>> y = new HashMap<>(); //maps y -> list x
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++){
            int a = points[i][0];
            int b = points[i][1];
            
            if(!x.containsKey(a)) x.put(a, new ArrayList<>());
            if(!y.containsKey(b)) y.put(b, new ArrayList<>());
            x.get(a).add(b);
            y.get(b).add(a);
        }
        
        //for each point, get the list of its vertical neighbors & horizontal neighbors
        for(int i = 0; i < points.length; i++){
            int curX = points[i][0], curY = points[i][1];
            List<Integer> horizontals = y.get(curY);
            List<Integer> verticals = x.get(curX);
            
            for(int j : horizontals){
                if(j <= curX) continue;
                int currArea = Integer.MAX_VALUE;
                for(int k : verticals){
                    if(k <= curY) continue;
                    
                    if(x.get(j).contains(k)){
                        int length = Math.abs(j - curX);
                        int height = Math.abs(k - curY);
                        currArea = Math.min(currArea, length * height);
                    }
                }
                min = Math.min(currArea, min);
            }
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }
	public int minAreaRect1(int[][] points) {
        Set<Integer> s = new HashSet<>();
        for (int[] p : points) {
            s.add(p[0] * 40001 + p[1]);
        }
        int min = Integer.MAX_VALUE;
        int l = points.length;
        for (int i = 0; i < l; i++) {
            int[] p1 = points[i];
            for (int j = i + 1; j < l; j++) {
                int[] p2 = points[j];
                if (p1[0] == p2[0] || p1[1] == p2[1]) continue;
                int need1 = p1[0] * 40001 + p2[1];
                int need2 = p2[0] * 40001 + p1[1];
                if (s.contains(need1) && s.contains(need2)) {
                   min = Math.min(Math.abs(p1[0]-p2[0]) * Math.abs(p1[1]-p2[1]), min); 
                }
            }
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
            
    }

}
