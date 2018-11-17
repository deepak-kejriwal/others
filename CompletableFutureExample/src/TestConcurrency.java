import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class TestConcurrency {
	//private static final Logger log = LoggerFactory.getLogger(DtvNowOfferFilter.class);

	public static void main(String[] args) throws Exception {
		try {
			CompletableFuture<Integer> sqc=CompletableFuture.supplyAsync(()->new Square().square(2));
			CompletableFuture<Float> cbc=CompletableFuture.supplyAsync(()->new Cube().cube(1));
			CompletableFuture<Integer> rootc=CompletableFuture.supplyAsync(()->new Root().root(3));
			CompletableFuture<Float> allc=CompletableFuture.allOf(sqc,cbc,rootc).thenApplyAsync(v->mergeResponse(sqc.join(),cbc.join(),null));
			Float sum=allc.join();
			System.out.println(sum);	
		}catch(CompletionException ex) {
			System.out.println(ex);
			System.out.println(ex.getCause());
		}

	}

	private static Float mergeResponse(Integer a, Float b, Integer c) {
		
		return a+b+c;
	}
	
}
