public class SequenceFinder {

	public static void main(String[] args) {
		int searchIndex = 13;
		SequenceFinder sequenceFinder = new SequenceFinder();
		Integer result = 0;
		final long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			Integer[] inputArray = { 4,3, 2, 1 };
			result = sequenceFinder.findSequenceNumber(inputArray, searchIndex);
		}
		final long endTime = System.currentTimeMillis();
		System.out.println(result);
		System.out.println((endTime - startTime) / 10000.0 + " millisecond");

	}

	/**
	 * @param inputArray
	 *            array of number
	 * @param searchIndex
	 *            index, where value need to determine
	 * @return
	 */
	public Integer findSequenceNumber(Integer[] inputArray, int searchIndex) {
		// By default start index is 0 of given array
		return findSequenceNumber(inputArray, 0, searchIndex);
	}

	private Integer findSequenceNumber(Integer inputArray[], int startIndex,
			int searchIndex) {
		if (searchIndex <= 1) {// Check if index refer top value of array
			return returnFirstValue(inputArray);
		}
		// Check if index refer bottom value of effective array neglecting
		// number before start index.
		else if (searchIndex == factorial(inputArray.length - startIndex)) {
			return returnLastValue(inputArray, startIndex);
		}
		int effectiveArrayLength = inputArray.length - startIndex;
		int effectiveFactValue = factorial(effectiveArrayLength);
		// Determine minimum number of numbers required to find the given index
		while (searchIndex <= effectiveFactValue) {
			++startIndex;
			effectiveFactValue = factorial(--effectiveArrayLength);
		}
		--startIndex;
		int swapIndex = startIndex;
		// If searchIndex > effectiveFactValue we can ignore number till
		// effectiveFactValue * n less than searchIndex where n is a whole
		// number.
		while (searchIndex > effectiveFactValue) {
			++swapIndex;
			swapIndexValue(inputArray, startIndex, swapIndex);
			searchIndex = searchIndex - effectiveFactValue;
		}
		// Modified array with start index to get the SubArray to search with
		// effective searchIndex
		return findSequenceNumber(inputArray, ++startIndex, searchIndex);

	}

	// Return the last value of SubArray keeping remaining number same of input
	// array.
	private Integer returnLastValue(Integer[] inputArray, int startIndex) {
		StringBuffer sb = new StringBuffer("");
		for (int k = 0; k < startIndex; k++) {
			sb.append(inputArray[k]);
		}

		for (int k = inputArray.length - 1; k > startIndex - 1; k--) {
			sb.append(inputArray[k]);
		}
		return Integer.valueOf(sb.toString());
	}

	// Return array input as number
	private Integer returnFirstValue(Integer[] inputArray) {
		StringBuffer sb = new StringBuffer("");
		for (int k = 0; k < inputArray.length; k++) {
			sb.append(inputArray[k]);
		}
		return Integer.valueOf(sb.toString());
	}

	// Swap startIndex value with swapIndex
	private void swapIndexValue(Integer arr[], int startIndex, int swapIndex) {
		int temp = arr[startIndex];
		arr[startIndex] = arr[swapIndex];
		arr[swapIndex] = temp;
	}

	// Return factorial of given number
	private int factorial(int i) {
		int result = 1;
		for (int j = i; j >= 1; j--) {
			result = result * j;
		}
		return result;
	}
}
