
import java.util.*;

public class HashSetTest {

	public static void testHashSet() {
		List<String> a = new LinkedList<>();
		a.add("Amy");
		a.add("Carl");
		a.add("Erica");
		a.add("Bob");
		a.add("Doug");
		a.add("Frances");
		a.add("Gloria");
		System.out.println(a);

		Set<String> words = new HashSet<>();
		long totalTime = 0;

		Iterator<String> aIter = a.iterator();
		while (aIter.hasNext()) {
			String name = aIter.next();
			long callTime = System.currentTimeMillis();
			words.add(name);
			callTime = System.currentTimeMillis() - callTime;
			totalTime += callTime;
		}

		Iterator<String> iter = words.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println("...");
		System.out.println(words);
		System.out.println(words.size() + " distinct words. " + totalTime + "milliseconds.");
	}

	public static void testHashSet2() {
		List<String> a = new LinkedList<>();
		a.add("Amy");
		a.add("Carl");
		a.add("Erica");
		a.add("Bob");
		a.add("Doug");
		a.add("Frances");
		a.add("Gloria");
		System.out.println(a);

		Set<String> words = new HashSet<>(a);
		System.out.println(words);
	}

	public static void main(String[] args) {
		testHashSet();
		testHashSet2();
	}
}