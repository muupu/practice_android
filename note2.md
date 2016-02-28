
* 活动的基本用法：
1、创建活动

2、创建和加载文件
android:id
	@id/id_name：在XML文件中引用资源id
	@+id/id_name：在XML文件中定义资源id
android:layout_width 当前元素的宽度
	match_parent：让当前元素和父元素一样宽
android:layout_height 当前元素的高度
	wrap_content：当前元素的高度只要能刚好包含里面的内容就行
android:text 元素中显示的文字内容
项目中添加的任何资源都会在R文件中生成一个相应的资源id。

3、在AndroidManifest文件中注册
活动的注册声明要放在<application>标签内。
android:name=".FirstActivity" ：指定具体注册哪一个活动
android:label="This is FirstActivity" ：指定活动中标题栏的内容
主活动指定的label不仅会成为标题栏中的内容，还会成为启动器（Launcher）中应用程序显示的名称。

4、隐藏标题栏
在 onCreate()方法中添加：requestWindowFeature(Window.FEATURE_NO_TITLE);
这句代码一定要在 setContentView()之前执行，不然会报错。

5、在活动中使用Toast
Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT).show();
参数1：Context。活动本身就是一个 Context 对象，因此直接传入FirstActivity.this即可。
参数2：Text。Toast显示的文本内容。
参数3：Toast显示的时长，有两个内置常量Toast.LENGTH_SHORT和 Toast.LENGTH_LONG。

findViewById()方法：获取在布局文件中定义的元素。
返回的是一个 View 对象，我们需要向下转型将它转成相应的对象
Button button1 = (Button) findViewById(R.id.button_1);

6、在活动中使用 Menu




