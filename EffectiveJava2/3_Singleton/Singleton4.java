
public class Singleton4 {

	private Singleton4() { }

	public static Singleton4 getInstance() {
		return SingletonHolder.instance;
	}

	// 静态内部类
	private staitc class SingletonHolder {
		private static final Singleton4 instance = new Singleton4();
	}
}