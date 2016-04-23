## 第3条：Enforce the singleton property with a private constructor or an enum type
用私有构造器或者枚举类型强化Singleton属性


###Singleton类会使得它的使用者(Client)很难被测试

因为无法给Singleton类做一个mock实现，除非it implements an interface that serves as its type


###懒汉式单例
```java
public class Singleton1 {  
	private static Singleton1 instance = null; 

	private Singleton1 (){}
 
	public static synchronized Singleton1 getInstance() {  
		if (instance == null) {  
			instance = new Singleton1();  
		}  
		return instance;  
	}  
}  
```

优点：

1. lazy loading。只在第一次调用的时候初始化单例。
2. 使用synchronized保证线程同步

缺点：
1. 效率低。每次调用getInstance()都需要进行同步，即使instance已经被初始化了。

###饿汉式单例
```java
public class Singleton2 {
	private static final Singleton2 instance = new Singleton2();

	private Singleton2() { }

	public static Singleton2 getInstance() { return instance;}

}
```
优点：
基于classloder机制避免了多线程的同步问题。

缺点：
在类装载时就初始化单例，没有达到lazy loading的效果。想象一下，如果实例化instance很消耗资源，我想让他延迟加载，另外一方面，我不希望在Singleton类加载时就实例化，因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，那么这个时候实例化instance显然是不合适的。
解决：使用lazy loading的单例

###双重检查锁定：Double CheckLock
```java
public class Singleton3 {  
	private static Singleton3 instance = null; 

	private Singleton3 (){}
 
	public static Singleton3 getInstance() {  
        if (instance == null) {    
            synchronized (Singleton3.class) {    
               if (instance == null) {    
                  instance = new Singleton3();   
               }    
            }    
        }    
        return instance;   
    }  
} 
```
优点：
1. lazy loading。
2. 线程安全
3. 单例对象初始化后再调用getInstance()不进行同步锁
缺点：
1. DCL失效问题。JDK1.5之前java编译器允许处理器乱序执行。
解决办法：
1. 添加volatile关键字。JDK1.5及之后的版本，修订了内存模型，并给volatile赋予了acquire/release语义。
2. 使用静态内部类的装载类实现

###静态内部类（推荐使用）
```java
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
```java
优点：
当Singleton类被第一次装载时，并不会立即初始化instance。只有第一次调用getInstance方法时，才会导致虚拟机去装载SingletonHolder类，从而初始化instance。

### 其他参考资料
http://blog.csdn.net/jason0539/article/details/23297037
http://www.blogjava.net/kenzhh/archive/2013/03/15/357824.html
《Android源码设计模式：解析和实战》
《深入浅出设计模式》