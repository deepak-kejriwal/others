import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
    Map<Integer,Integer> map=new HashMap();
    for(int x:ar){
       int count=map.getOrDefault(x,0);
       map.put(x,count+1); 
    }    
    int pair=0;
        
    for(int x:map.keySet()){
       int count=map.get(x);
       while(count>=2){
           count=count/2;
           pair++;
       }
    } 
        return pair;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
int[] ar= {10,20,20,10,10,20,10,20};
int n=6;

        int result = sockMerchant(n, ar);
System.out.println(result);
    }
}
