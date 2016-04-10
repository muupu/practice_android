
import java.util.*;

public class HashMapTest {

	public static void testHashMap() {
		Map<String, String> staff = new HashMap<>();
		staff.put("144-25-5464", "Amy Lee");
		staff.put("567-24-2546", "Harry Hacker");
		staff.put("157-62-7935", "Gary Cooper");
		staff.put("456-62-5527", "Francesca Cruz");
		System.out.println(staff);

	}

	public static void main(String[] args) {
		testHashMap();
	}
}