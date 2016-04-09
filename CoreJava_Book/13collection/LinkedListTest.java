
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
	}

	public static void main(String[] args) {
		//testAddDel();
		testListIterator();
	}
}