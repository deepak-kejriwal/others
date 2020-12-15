public class Base62Converter {

	public static void main(String[] args) {		
		Long num = Long.parseLong("011111111111111111111111111111111111111111111111", 2);				
		System.out.println(convertToBase62(num));
	}
	
	private static String convertToBase62(long num) {
		int n = 11;
		char[] result = new char[n+1];
		
		long rem;
		while(num>0) {
			rem=num%62;
			result[n--] = CHARLIST[(int)rem];
			num=num/62;
		}
		
		while(n>-1) {
			result[n--] = CHARLIST[0];
		}
		
		return new String(result);
	}
	private static final char[] CHARLIST = { 
			 '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
			 'A', 'B', 'C', 'D','E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T','U', 'V', 'W', 'X', 'Y', 'Z', 
			 'a', 'b', 'c', 'd','e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r','s', 't', 'u', 'v', 'w', 'x', 'y', 'z' 
			 };

}
