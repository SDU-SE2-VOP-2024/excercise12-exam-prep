package dk.sdu.se.vop.examprep.facade;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class Facade {
	private final Random random;
	private int[] intArray;

	public Facade() {
		this.random = new Random();
	}

	public static void main(final String[] args) {
		final Facade facade = new Facade();
		System.out.printf("fillArray: %s\n", Arrays.toString(facade.fillArray(10, 100)));
		final int divisor = 3;
		System.out.printf("divisors sum of %d = %d\n", divisor, facade.sumOfDivisors(divisor));
		System.out.printf("fillUniqueArray: %s\n", Arrays.toString(facade.fillUniqueArray(10, 100)));

		try {
			System.out.printf("fillUniqueArray: %s\n", Arrays.toString(facade.fillUniqueArray(20, 10)));
		} catch (final IllegalArgumentException e) {
			System.out.println("Yay we got the error we wanted!");
			throw e;
		}
	}

	public int[] getIntArray() {
		return this.intArray;
	}

	public int[] fillArray(final int size, final int max) {
		this.intArray = new int[size];
		for (int i = 0; i < this.intArray.length; i++) {
			this.intArray[i] = this.random.nextInt(0, max);
		}
		return this.intArray;
	}

	public int sumOfDivisors(final int x) {
		if (this.intArray == null) return 0;
		int sum = 0;
		for (final int i : this.intArray) {
			if (i % x == 0) sum += i;
		}
		return sum;
	}

	/**
	 * Fills in the internal array with unique random number between 0 and {@code max}.
	 *
	 * @param size The size of the array to be generated. Must not be greater than {@code max}.
	 * @param max  The maximum number which can be generated. Must not be less than {@code size}.
	 * @return The array with the generated numbers.
	 * @throws IllegalArgumentException if {@code size > max}.
	 */
	public int[] fillUniqueArray(final int size, final int max) {
		if (size > max) throw new IllegalArgumentException("'size' must be less than or equal to 'max'.");
		final Collection<Integer> set = new HashSet<>();
		while (set.size() < size) {
			set.add(random.nextInt(0, max));
		}
		this.intArray = set.stream().mapToInt(Integer::intValue).toArray();
		return this.intArray;
	}
}