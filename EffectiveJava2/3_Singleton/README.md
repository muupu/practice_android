## 第3条：Enforce the singleton property with a private constructor or an enum type

用私有构造器或者枚举类型强化Singleton属性


###Singleton类会使得它的使用者(Client)很难被测试

因为无法给Singleton类做一个mock实现，除非it implements an interface that serves as its type


###实现方式1：饿汉式单例
`
public class Singleton1 {
	private static final Singleton1 instance = new Singleton1();

	private Singleton1() { }

	public static Singleton1 getInstance() { return instance;}

}
`

特点：

1. 在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以天生是线程安全的。


### 其他参考资料
http://blog.csdn.net/jason0539/article/details/23297037