import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class TestAtomicInteger {
	static volatile int count = 0;
	static AtomicInteger atomicCount = new AtomicInteger(0);

	public static void main(String[] args) {
		testVolatileCount();
		testAtomicCount();
	}

	private static void testVolatileCount() {
		IntStream.range(0, 100).parallel().forEach(e -> count++);

		System.out.println(count);
	}

	private static void testAtomicCount() {
		IntStream.range(0, 100).parallel().forEach(e -> atomicCount.getAndIncrement());

		System.out.println(atomicCount.get());
	}

}
