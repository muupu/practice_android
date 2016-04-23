## 第3条：Enforce the singleton property with a private constructor or an enum type

用私有构造器或者枚举类型强化Singleton属性

* Singleton类会使得它的使用者(Client)很难被测试

因为无法给Singleton类做一个mock实现，除非it implements an interface that serves as its type

* 

