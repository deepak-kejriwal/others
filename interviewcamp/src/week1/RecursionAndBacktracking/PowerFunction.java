package week1.RecursionAndBacktracking;

/**
* 
* @author Deepak Kejriwal
*
*/

/*
 * 
 * (Level: Easy)

Power Function: Implement a function to calculate xn. You may assume that both x and n are positive and overflow doesn't happen.
 * 
 */
public class PowerFunction {

	public static void main(String[] args) {
		int x = 2; 
        int y = 3; 
  
        System.out.printf("%d", power(x, y)); 
        System.out.printf("%d", powerWithMemo(x, y)); 
        
         x = 2; 
         y = -3; 
        System.out.printf("%f", power(x, y)); 
        System.out.printf("%d", powerWithMemo(x, y)); 

	}
	
	   /* Function to calculate x raised to the power y */
    static int power(int x, int y) 
    { 
        if (y == 0) 
            return 1; 
        else if (y % 2 == 0) 
            return power(x, y / 2) * power(x, y / 2); 
        else
            return x * power(x, y / 2) * power(x, y / 2); 
    } 
    
    static int powerWithMemo(int x, int y) 
    { 
        int temp; 
        if( y == 0) 
            return 1; 
        temp = powerWithMemo(x, y/2);  
          
        if (y%2 == 0) 
            return temp*temp; 
        else
        { 
            if(y > 0) 
                return x * temp * temp; 
            else
                return (temp * temp) / x; 
        } 
    }  

}
