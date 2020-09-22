/**
* 
* @author Deepak Kejriwal
*
*/
// Not verified, will check sometime later in future
public class BaseConversionImpl {

	public static void main(String[] args) {
		BaseConversionImpl impl=new BaseConversionImpl();
		String input="11";
		String output=new String(impl.convert(input.toCharArray(), 10, 62));
		System.out.println(output);
	}
	private char[] convert(final char[] message, final int sourceBase, final int targetBase) {
		/**
		 * This algorithm is inspired by: http://codegolf.stackexchange.com/a/21672
		 */

		final int estimatedLength = estimateOutputLength(message.length, sourceBase, targetBase);

		StringBuilder out=new StringBuilder();
		//final ByteArrayOutputStream out = new ByteArrayOutputStream(estimatedLength);

		char[] source = message;

		while (source.length > 0) {
			StringBuilder quotient=new StringBuilder();
			//final ByteArrayOutputStream quotient = new ByteArrayOutputStream(source.length);

			int remainder = 0;

			for (int i = 0; i < source.length; i++) {
				final int accumulator = (source[i]-'0' & 0xFF) + remainder * sourceBase;
				final int digit = (accumulator - (accumulator % targetBase)) / targetBase;

				remainder = accumulator % targetBase;

				if (quotient.length() > 0 || digit > 0) {
					quotient.append(digit);
				}
			}

			out.append(remainder);

			source = quotient.toString().toCharArray();
		}

		// pad output with zeroes corresponding to the number of leading zeroes in the
		// message
		for (int i = 0; i < message.length - 1 && message[i] == 0; i++) {
			out.append(0);
		}
		return out.reverse().toString().toCharArray();
		//return reverse(out.toByteArray());
	}
	
	/**
	 * Estimates the length of the output in bytes.
	 */
	private int estimateOutputLength(int inputLength, int sourceBase, int targetBase) {
		return (int) Math.ceil((Math.log(sourceBase) / Math.log(targetBase)) * inputLength);
	}
	

	/**
	 * Reverses a byte array.
	 */
	private byte[] reverse(final byte[] arr) {
		final int length = arr.length;

		final byte[] reversed = new byte[length];

		for (int i = 0; i < length; i++) {
			reversed[length - i - 1] = arr[i];
		}

		return reversed;
	}
}
