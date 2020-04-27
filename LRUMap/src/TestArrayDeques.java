import java.util.ArrayDeque;

/**
* 
* @author Deepak Kejriwal
*
*/
public class TestArrayDeques {

	public static void main(String[] args) {
		ArrayDeque<Integer> aq=new ArrayDeque();
		for(int i=0;i<100;++i){
			aq.add(i);
		}
		
		for(int i=0;i<99;++i){
			aq.remove();
		}
		aq.remove();
		System.out.println(aq);
	}

}
