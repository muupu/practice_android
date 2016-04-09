
import java.util.*;

public class LinkedListTest {

	public static void testAddDel() {
		List<String> staff = new LinkedList<>();
		staff.add("Amy");
		staff.add("Bob");
		staff.add("Carl");
		System.out.println(staff);

		Iterator iter = staff.iterator();
		String first = (String)iter.next();
		String second = (String)iter.next();
		iter.remove();
		System.out.println(staff);
	}

	public static void testListIterator() {
		List<String> staff = new LinkedList<>();
		staff.add("Amy");
		staff.add("Bob");
		staff.add("Carl");
		System.out.println(staff);

		ListIterator<String> iter = staff.listIterator();
		iter.next();
		iter.add("Juliet");
		System.out.println(staff);

		ListIterator<String> iter1 = staff.listIterator();
		String oldValue = iter1.next();
		iter1.set("NewValue");
		System.out.println(staff);
	}

	public static void testRemoveall() {
		List<String> a = new LinkedList<>();
		a.add("Amy");
		a.add("Carl");
		a.add("Erica");
		System.out.println(a);

		List<String> b = new LinkedList<>();
		b.add("Bob");
		b.add("Doug");
		b.add("Frances");
		b.add("Gloria");
		System.out.println(b);

		// merge b into a
		ListIterator<String> aIter = a.listIterator();
		Iterator<String> bIter = b.iterator();
		while (bIter.hasNext()) {
			if (aIter.hasNext()) aIter.next();
			aIter.add(bIter.next());
		}
		System.out.println(a);

		// remove every second word from b

		bIter = b.iterator();
		while (bIter.hasNext()) {
			bIter.next(); // skip one element
			if (bIter.hasNext()) {
				bIter.next();
				bIter.remove();
			}
		}
		System.out.println(b);

		// remove all elements in b from a
		a.removeAll(b);
		System.out.println(a);
	}

	public static void main(String[] args) {
		//testAddDel();
		//testListIterator();
		testRemoveall();
	}
}