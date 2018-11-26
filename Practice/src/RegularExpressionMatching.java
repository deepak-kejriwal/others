
public class RegularExpressionMatching {

	public static void main(String[] args) {
		System.out.println(new RegularExpressionMatching().isMatch("aaa", ".*"));

	}

	public boolean isMatch(String s, String p) {

	    if (s == null || p == null) {
	        return false;
	    }
	    if(p.length()>=1&&p.charAt(0)=='*') return false;
	    boolean[][] dp = new boolean[s.length()+1][p.length()+1];
	    dp[0][0] = true;
	    for (int i = 0; i < p.length(); i++) {
	        if (p.charAt(i) == '*' && dp[0][i-1]) {
	            dp[0][i+1] = true;
	        }
	    }
	    for (int row = 1 ; row < dp.length; row++) {
	        for (int col = 1; col < dp[0].length; col++) {
	            if (p.charAt(col-1) == '.') {
	                dp[row][col] = dp[row-1][col-1];
	            }
	            if (p.charAt(col-1) == s.charAt(row-1)) {
	                dp[row][col] = dp[row-1][col-1];
	            }
	            if (p.charAt(col-1) == '*') {
	                if (p.charAt(col-2) != s.charAt(row-1) && p.charAt(col-2) != '.') {
	                    dp[row][col] = dp[row][col-2];
	                } else {
	                    dp[row][col] = dp[row][col-1] || dp[row-1][col] || dp[row][col-2];
	                }
	            }
	        }
	    }
	    return dp[s.length()][p.length()];
	}
	
    public boolean isMatch2(String s, String p) {
        if(s==null || p == null)
            return s==p;
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0]=true;
        for(int row=0, col=1; col<dp[0].length;col++){
            if(p.charAt(col-1) == '*'){
                dp[row][col]=true;
                if(col<dp.length){
                    dp[col][col]=true;
                }                 
            }            
            else{
                break;
            }
            
        }
        
        for(int row=1; row<dp.length;row++){
            for(int col=1;col<dp[0].length;col++){
                char str = s.charAt(row-1);
                char pattn = p.charAt(col-1);
                if(str == pattn || pattn == '?'){
                    dp[row][col] =dp[row][col]|| dp[row-1][col-1];
                } else if(pattn == '*'){
                    dp[row][col] = dp[row][col]||dp[row][col-1] || dp[row-1][col];
                }
            }
        }
        
        return dp[s.length()][p.length()];
        
    }
}
