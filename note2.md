
## 活动的基本用法：
###1、创建活动

###2、创建和加载文件
android:id
	@id/id_name：在XML文件中引用资源id
	@+id/id_name：在XML文件中定义资源id
android:layout_width 当前元素的宽度
	match_parent：让当前元素和父元素一样宽
android:layout_height 当前元素的高度
	wrap_content：当前元素的高度只要能刚好包含里面的内容就行
android:text 元素中显示的文字内容
项目中添加的任何资源都会在R文件中生成一个相应的资源id。

###3、在AndroidManifest文件中注册
活动的注册声明要放在<application>标签内。
android:name=".FirstActivity" ：指定具体注册哪一个活动
android:label="This is FirstActivity" ：指定活动中标题栏的内容
主活动指定的label不仅会成为标题栏中的内容，还会成为启动器（Launcher）中应用程序显示的名称。

###4、隐藏标题栏
在 onCreate()方法中添加：requestWindowFeature(Window.FEATURE_NO_TITLE);
这句代码一定要在 setContentView()之前执行，不然会报错。

###5、在活动中使用Toast
Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT).show();
参数1：Context。活动本身就是一个 Context 对象，因此直接传入FirstActivity.this即可。
参数2：Text。Toast显示的文本内容。
参数3：Toast显示的时长，有两个内置常量Toast.LENGTH_SHORT和 Toast.LENGTH_LONG。

findViewById()方法：获取在布局文件中定义的元素。
返回的是一个 View 对象，我们需要向下转型将它转成相应的对象
Button button1 = (Button) findViewById(R.id.button_1);

###6、在活动中使用 Menu
1.首先要在res目录下新建一个menu文件夹
2.右击menu文件夹→New→Android XML File，创建main.xml
3.在 main.xml 中添加代码:
	<item
	android:id="@+id/add_item"
	android:title="Add"/>
android:id:给这个菜单项唯一标识符
android:title:菜单项名称
4.重写 onCreateOptionsMenu()方法:
getMenuInflater().inflate(R.menu.main, menu); 
    -- 获取MenuInflater对象，再调用它的 inflate()方法就可以给当前活动创建菜单了
    -- 参数1：指定使用哪一个资源文件来创建菜单
    -- 参数2：指定我们的菜单项将添加到哪一个 Menu对象当中
return true;
    -- 返回 true，表示允许创建的菜单显示出来，如果返回了 false，创建的菜单将无法显示。
5.定义菜单响应事件：重写 onOptionsItemSelected()
	-- switch (item.getItemId()) 

###7、销毁一个活动
1.Back键
2.Activity 类的finish()方法


## 使用Intent
###1、使用显式 Intent
Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
	-- 参数1：Context，提供一个启动活动的上下文
	-- 参数2：指定想要启动的目标活动
startActivity(intent);
	-- 接收一个Intent参数，启动目标活动
回到上一个活动：按下 Back键就可以销毁当前活动，从而回到上一个活动
###2、使用隐式 Intent
通过action和 category等信息，启动相应的活动。
AndroidManifest.xml中<intent-filter>里添加
	-- <action>标签：指明了当前活动可以响应的action
	-- <category>标签：更精确地指明了当前的活动能够响应的 Intent 中还可能带有的 category
代码中添加：
	Intent intent = new Intent("com.example.activitytest.ACTION_START");
	-- 表明我们想要启动能够响应 com.example.activitytest.ACTION_START这个 action的活动
每个 Intent中只能指定一个 action，但却能指定多个 category。
	-- intent.addCategory("com.example.activitytest.MY_CATEGORY");
###3、更多隐式 Intent的用法
1. 还可以启动其他程序的活动
例如：调用系统的浏览器来打开网页
Intent intent = new Intent(Intent.ACTION_VIEW);
	-- Intent.ACTION_VIEW，这是一个Android系统内置的动作，其常量值为android.intent.action.VIEW
intent.setData(Uri.parse("http://www.baidu.com"));
	-- 接收一个Uri对象，指定当前Intent正在操作的数据
2. 在<intent-filter>标签中配置一个<data>标签，能够更精确地指定该活动能够响应什么类型的数据。
例如：<data android:scheme="http" />
	--  这样该活动就和浏览器一样，能够响应一个打开网页的Intent了


