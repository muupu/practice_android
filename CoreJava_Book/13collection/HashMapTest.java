
import java.util.*;

public class HashMapTest {

	public static void testHashMap() {
		Map<String, String> staff = new HashMap<>();
		staff.put("144-25-5464", "Amy Lee");
		staff.put("567-24-2546", "Harry Hacker");
		staff.put("157-62-7935", "Gary Cooper");
		staff.put("456-62-5527", "Francesca Cruz");
		System.out.println(staff);

		// remove test
		staff.remove("567-24-2546");
		System.out.println(staff);

		// use put to replace
		staff.put("456-62-5527", "Francesca Miller");
		System.out.println(staff);

		// get test
		System.out.println(staff.get("157-62-7935"));

		// iterate throut all entries
		for (Map.Entry<String, String> entry : staff.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println("key=" + key + ", value=" + value);
		}
	}

	public static void main(String[] args) {
		testHashMap();
	}
}