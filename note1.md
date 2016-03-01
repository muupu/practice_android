
* 目录结构
src
gen
assets
bin
libs
res
AndroidManifest.xml
project.proprerties

* Android项目如何运行起来
必须在AndroidManifest.xml中注册Activity，该Activity才能被使用。

* 主活动的设置：
必须有设置一个主活动，程序才能知道从哪个Activity启动。
在intent-filter里设置了
<action android:name="android.intent.action.MAIN" />和
<category android:name="android.intent.category.LAUNCHER" />
的表示 HelloWorldActivity 是这个项目的主活动。
在手机上点击应用图标，首先启动的就是这个活动。

* Activity类的定义
extends Activity：必须继承自Activity基类。
onCreate(Bundle savedInstanceState):
在setContentView(R.layout.hello_world_layout)中调用本活动的界面布局。

* 布局文件
布局文件都是定义在res/layout目录下的。
R.layout.hello_world_layout指向hello_world_layout.xml 这个文件。

* 项目中的字符串
Android不推荐在程序中对字符串进行硬编码，一般是把字符串定义在res/values/strings.xml里，然后可以在布局文件或代码中引用。

* 项目中的资源目录：res/
drawable开头的文件夹：用来放图片的。为了兼容更多设备所以有多个文件夹。
values开头的文件夹：  用来放字符串的
layout文件夹:放布局文件的
menu文件夹:放菜单文件的

* 使用资源
字符串资源：
1、代码中使用：R.string.hello_world
2、XML文件中使用：@string/hello_world

* 日志工具：LogCat
Android中的日志工具类是Log（android.util.Log）。
1、Log.v()
2、Log.d()
3、Log.i()
4、Log.w()
5、Log.e()
Log.d("HelloWorldActivity", "onCreate execute");
第一个参数是tag，用来过滤。第二个参数是text，具体内容。

* LogCat中添加过滤器








