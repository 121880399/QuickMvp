# Demo示例图
![](https://github.com/121880399/QuickMvp/raw/master/doc/demo_img.png)  

# QuickMvp
[![](https://jitpack.io/v/121880399/QuickMvp.svg)](https://jitpack.io/#121880399/QuickMvp)<br>

QuickMvp 是一个轻量级的MVP快速开发框架，从实际项目中抽取出来，参考了gitHub上一些知名的MVP框架，非常适用于中小型项目的快速开发，非常的轻，不像其他的MVP快速开发框架，虽然集成了很多东西，但是用起来实在太繁琐，学习成本也很高。本框架把通用性的东西封装了起来，把各种View库排除掉，毕竟每个项目需求不同。本MVP框架一般情况下不需要写Contract接口。
![](https://github.com/121880399/QuickMvp/raw/master/doc/QuickMVP.png)  


## 目录结构

![](https://github.com/121880399/QuickMvp/raw/master/doc/menu.png)  

## UML图

![](https://github.com/121880399/QuickMvp/raw/master/doc/QuickMvpUML.png) 

## Libraries介绍

QuickMvp集成了许多流行的开源库，使我们能够更快速更高效的进行开发。原则上QuickMvp包含的开源库都是在QuickMvp中使用到的，还有很多view类型的库并没有直接包含进来，但是我们提供了强大的config.gradle，里面有质量很高的开源库供大家引用。<br> 
1.[Rxjava 一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序的库.](https://github.com/ReactiveX/RxJava)<br> 
2.[RxAndroid 是RxJava在Android上的一个扩展，大牛JakeWharton的项目.](https://github.com/ReactiveX/RxAndroid)<br> 
3.[Rxlifecycle 为了防止RxJava中内存泄漏而诞生的.](https://github.com/trello/RxLifecycle)<br>
4.[RxPermissions 解决Android 6.0权限适配问题.](https://github.com/tbruyelle/RxPermissions)<br> 
5.[Retrofit 是Square开发的一个Android和Java的REST客户端库.](https://github.com/square/retrofit)<br> 
6.[Okhttp是Square出品的网络请求库.](https://github.com/square/okhttp)<br> 
7.[Gson是Google公司发布的用于为Java对象序列化和反序列化的库](https://github.com/google/gson).<br> 
8.[Butterknife JakeWharton大神出品的view注入框架.](https://github.com/JakeWharton/butterknife)<br> 
9.[Androideventbus一个轻量级使用注解的Eventbus.](https://github.com/hehonghui/AndroidEventBus)<br> 
10.[Glide 一个专注于平滑滚动的图片加载和缓存库.](https://github.com/bumptech/glide)<br> 
11.[logger 用于打印Log的封装库，功能强大.](https://github.com/orhanobut/logger)<br> 

## 第一种方式<br>
1.clone QuickMvp到本地:<br>
```
git clone https://github.com/121880399/QuickMvp.git
```

2.在app module的build.gradle中加入以下代码:<br>
```
compile project(':quick')
```

3.将config.gradle拷贝到项目根目录,并修改全局build.gradle：<br>
```
apply from: "config.gradle"
```
并添加如下代码:<br>
```
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

## 第二种方式，通过JitPack引入<br>
1.在根目录的gradle文件中配置:<br>
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  2.添加依赖:<br>
  ```
  	dependencies {
	        compile 'com.github.121880399:QuickMvp:v1.0.0'
	}
  ```
## About Me
Email:zhouzhengyi007@126.com<br>
职位:android高级工程师<br>
就职于:[中车互联运力科技有限公司](http://www.unitransdata.com/)<br>
简书:http://www.jianshu.com/u/ff764c6c19e4
