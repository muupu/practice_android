
public class ArrayInitTest1 {

	public static void main(String[] args) {
		// static init
		String[] books = new String[] {"book1", "book2", "book3"};

		// static init
		String[] names = {"name1", "name2", "name3", "name4"};

		// dynamic init
		String[] strArr = new String[5];
	
		System.out.println("books array length: " + books.length);
		System.out.println("books array length: " + names.length);
		System.out.println("books array length: " + strArr.length);
	}
}