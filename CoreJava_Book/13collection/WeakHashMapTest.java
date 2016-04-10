
import java.util.*;

public class WeakHashMapTest {

	public static void testHashMap() {
		System.out.println("test HashMap...");
		Map<Integer, String> hashMap=new HashMap<>();
		Integer one=new Integer(1);
		hashMap.put(one,"one");
		System.out.println(hashMap);
		one=null;
		System.gc();
		System.out.println(hashMap);
	}

	public static void testWeakHashMap() {
		System.out.println("test WeakHashMap...");
		Map<Integer, String> weakHashMap=new WeakHashMap<>();
		Integer one=new Integer(1);
		weakHashMap.put(one,"one");
		System.out.println(weakHashMap);
		one=null;
		System.gc();
		System.out.println(weakHashMap);
	}

	public static void main(String[] args) {
		
		testHashMap();
		testWeakHashMap();
	}
}