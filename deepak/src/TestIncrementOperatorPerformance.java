
public class TestIncrementOperatorPerformance {
private static long counter=3000000000l;
	public static void main(String[] args) {
		
		testPositiveIncrement();
		testNegativeIncrement();
		//testPostIncrement();
		//testPreIncrement();
	}

	private static void testNegativeIncrement(){
		
		long count=0;
		long st=System.currentTimeMillis();
		for(int i=-2147483648;i<1;i++) {
	
				count++;
			
			
		}
		long ed=System.currentTimeMillis();
		System.out.println(count);
		System.out.println(ed-st);
	}
	private static void testPositiveIncrement(){
		boolean enable=false;
		long count=0;
		long st=System.currentTimeMillis();
		for(int i=0;i<2147483647;i++) {
			count++;
			
		}
		long ed=System.currentTimeMillis();
		System.out.println(count);
		System.out.println(ed-st);
	}
	private static void testPostIncrement(){
		boolean enable=false;
		long count=0;
		long st=System.currentTimeMillis();
		for(int i=0;i<counter;i++) {
			if(i<-1) {
				enable=true;
				System.out.println("halt");
			}else if(i>=0){
				if(enable) {
					System.out.println("correct");
				}
				count++;
			}
			
		}
		long ed=System.currentTimeMillis();
		System.out.println(count);
		System.out.println(ed-st);
	}
	
	private static void testPreIncrement(){
		long count=0;
		long st=System.currentTimeMillis();
		for(long i=0;i<counter;++i) {
			++count;
		}
		long ed=System.currentTimeMillis();
		System.out.println(count);
		System.out.println(ed-st);
	}
}
