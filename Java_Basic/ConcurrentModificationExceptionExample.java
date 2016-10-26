
import java.util.ArrayList;
import java.util.List;

public class ConcurrentModificationExceptionExample {
	
	static List<String> list = new ArrayList<>();

	private static void testDelete1() {
        for(String str : list){
            if(str.equals("abc5")){
                list.remove(str); // 报异常
            }
        }
    }

    private static void testDelete2() {
        for(int i = 0; i < list.size(); i++){
            String str = list.get(i);
            if(str.equals("abc5")){
                list.remove(str); // 正常
            }
        }
    }

	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			String str = "abc" + i;
			list.add(str);
		}
		
		// Exception in thread "main" java.util.ConcurrentModificationException
        // at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:859)
        // at java.util.ArrayList$Itr.next(ArrayList.java:831)
        // at ConcurrentModificationExceptionExample.testDelete1(ConcurrentModificationExceptionExample.java:10)
        // at ConcurrentModificationExceptionExample.main(ConcurrentModificationExceptionExample.java:32)
		// testDelete1();
		
		testDelete2();
	}
}