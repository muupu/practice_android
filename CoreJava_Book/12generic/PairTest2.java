
import java.util.*;

public class PairTest2 {
	public static void main(String[] args) {
		testString();

	}

	public static void testString() {
		GregorianCalendar[] birthdays = 
		{
			new GregorianCalendar(1906, Calendar.DECEMBER, 9),
			new GregorianCalendar(1815, Calendar.DECEMBER, 10),
			new GregorianCalendar(1903, Calendar.DECEMBER, 3),
			new GregorianCalendar(1910, Calendar.JUNE, 22),
		};
		Pair<GregorianCalendar> mm = ArrayAlg.minmax(birthdays);
		System.out.println("min = " + mm.getFirst().getTime());
		System.out.println("max = " + mm.getSecond().getTime());
	}
}

class ArrayAlg {

	public static <T  extends Comparable> Pair<T> minmax(T[] a) {
		// if (a instanceof Pair<String> ) { } // 错误: instanceof 的泛型类型不合法
		if (a == null || a.length == 0) return null;
		T min = a[0];
		T max = a[0];
		for (int i = 1; i < a.length; i++) {
			if (min.compareTo(a[i]) > 0) min = a[i];
			if (max.compareTo(a[i]) < 0) max = a[i];
		}
		return new Pair<>(min, max);
	}

	// generic method
	public static <T> T getMiddle(T...a) {
		return a[a.length / 2];
	}

	public static <T extends Comparable> T min(T[] a) {
		if (a == null || a.length == 0) return null;
		T smallest = a[0];
		for (int i = 1; i < a.length; i++) {
			if (smallest.compareTo(a[i]) > 0) {
				smallest = a[i];
			}
		}
		return smallest;
	}
}