
/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class TestBitShift {

	public static void main(String[] args) {
		long l1 = 241294492511762325L;
		long shift46 = l1 >> 46;
		System.out.println(shift46);
		System.out.println(Long.toBinaryString(l1));
		System.out.println(Long.toBinaryString(shift46));
	}

}
