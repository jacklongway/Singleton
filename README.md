# 单例模式真的很简单吗？
> 单例模式真的不简单，但是单例模式也是我们开发中常用的一种设计模式,然而不管Github上开源的代码，还是很多开发同学写的单例模式，百花奇放，百家争鸣，各种版本，这篇文章我想跟大家聊聊我心中的单例模式，同时我会给出我心目中的单例模式的各种版本，同时会给出不同版本的优缺点，以及我们最后应该选用哪个版本才是最为安全，优雅，同时性能也是比较好的，最后我想说，安全，性能是一个折中的考虑。

## 单例对象创建的一般步骤
1. 构造函数`private`
2. 定义`static`成员变量
3. 提供`static getInstance` 方法获取对象


## SingletonV1

```
public class SingletonV1 {
    private static SingletonV1 sInstance;

    private SingletonV1() {

    }

    public static SingletonV1 getInstance() {
        if (sInstance == null) {
            sInstance = new SingletonV1();
        }
        return sInstance;
    }

}
```
这个版本是最简单的方式创建单例对象,但是这个版本只适合单线程场景下，在并发条件下，不能保证对象为唯一性，如果有同学也这么实现的，赶紧反思一下。

## SingletonV2

```
public class SingletonV2 {
    private static SingletonV2 sInstance;

    private SingletonV2() {

    }

    public static synchronized SingletonV2 getInstance() {
        if (sInstance == null) {
            sInstance = new SingletonV2();
        }
        return sInstance;
    }
}
```

这个版本跟第一个版本的区别就是多了个`synchronized`关键字，暂且我们这么理解，这个关键字是保证保证线程安全的，这样一来，我们保证了单例，确实没错，但是这个版本并不是我想要的，为什么了？因为这个锁是方法锁，意味着性能损失较大，至于为什么损失较大，这里就不展开讲了，有疑问的同学可以评论里，向我提问。

## SingletonV3

```
public class SingletonV3 {
    private static SingletonV3 sInstance;

    private SingletonV3() {

    }

    public static SingletonV3 getInstance() {
        synchronized (SingletonV3.class) {
            if (sInstance == null) {
                sInstance = new SingletonV3();
            }
        }
        return sInstance;
    }
}
```
这个版本看上去是代码段局部锁，但是我们仔细一看，其实跟第二个版本一模一样，没有本质区别，所以这个版本也不是我们想要的。

## SingletonV4

```
public class SingletonV4 {
    private static SingletonV4 sInstance;

    private SingletonV4() {

    }

    public static SingletonV4 getInstance() {
        if (sInstance == null) {
            synchronized (SingletonV4.class) {
                if (sInstance == null) {
                    sInstance = new SingletonV4();
                }
            }
        }
        return sInstance;
    }
}
```
这个版本最大的改进是在第三个版本的基础上实现了双层检测，在某种层度上，性能有所提升，但是是不是就没有安全问题了，不是的，这个版本也不是我们想要的，因为在指令重排的情况下，会出错，在这里也不打算展开讲为什么会出现这中诡异的现象，有想知道原因的，可以在评论里，向我提问。

## SingletonV5

```
public class SingletonV5 {
    private static SingletonV5 sInstance;

    private SingletonV5() {

    }

    public static SingletonV5 getInstance() {
        if (sInstance == null) {
            synchronized (SingletonV5.class) {
                sInstance = new SingletonV5();
            }
        }
        return sInstance;
    }
}
```
这个版本有在并发的环境下很难保证单例，也有可能报错。

## SingletonV6

```
public class SingletonV6 {
    private static volatile SingletonV6 sInstance;

    private SingletonV6() {

    }

    protected Object readResolve() {
        return getInstance();
    }

    public static SingletonV6 getInstance() {
        if (sInstance == null) {
            synchronized (SingletonV6.class) {
                if (sInstance == null) {
                    sInstance = new SingletonV6();
                }
            }
        }
        return sInstance;
    }
}
```
这个版本算比较完美了，在某种条件下，保证了单例，同时性能也有了一定的保证，同时反序列化也保证了单例，但是这种是不是也就没问题了，错了，这个版本在jdk1.5之后才能算完美，以前版本还是有问题的, 这里不展开讲原因，有疑问的同学在下面的评论，可以向我提问。

## SingletonV7

```
public enum  SingletonV7 {
    INSTANCE
}
```
这个版本是上一个版本的增强版，同时也是最优雅的版本，也是最为安全的版本，性能也是较好的版本，同时基于自身特点保证了序列化，反序列化的安全，同时也是目前最为推荐使用的单例版本，这里不展开讲原因，有兴趣的同学，可以在下面的评论，向我提问。

## SingletonV8

```
public class SingletonV8 {
    private SingletonV8() {

    }

    private static class SingletonHolder {
        private static final SingletonV8 INSTANCE = new SingletonV8();
    }

    public static SingletonV8 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```
这个版本也是虚拟机能够保证安全的版本，但是需要额外的内部类提供保证，空间复杂度比上面版本大，综合考虑还是考虑枚举版本

## SingletonV9

```
public class SingletonV9 {
    private static final SingletonV9 INSTANCE = new SingletonV9();

    private SingletonV9() {

    }

    public static SingletonV9 getInstance() {
        return INSTANCE;
    }
}
```
这个版本也是安全的，但是有个缺陷就是这个类只要其中有个静态成员加载，那么就会创建对象，也是我们经常说的饿汉式模式，上面的版本都是懒汉式模式，对于这两种模式不太了解的，可以在下面的评论，向我提问。

## 扩展
1. 对象创建的不同方式，以及不同点
2. 对象的创建过程
3. classloader的namespace
4. 虚拟机实例的个数对对象的影响
5. 继承对对象创建的影响
6. 对象代码复用
7. 对象锁，类锁，全局锁的区别
8. 锁中的坑

> 这篇由于主要由浅入深的讲解单例，上面的扩展就不展开讲了，如果有感兴趣的同学，可以在评论里，向我提问，我会详细解答的，后面的文章我也会由浅入深的讲解。

## 总结

看似很简单一种设计模式，其实我们深入思考一下，就没那么简单了，中间有很多技术点，有很多细节需要我们去挖掘，思考，最后推荐SingletonV7版本，注意这个只是保证了对象创建安全，对象成员的安全，还需要我们自己去保证。希望阅读过这篇文章的同学，再也不要写不安全的单例对象代码了，代码改进的一小步，程序稳定，可靠的一大步。

## 可能的疑问

有的同学可能会在想，客户端平台不是不建议enum吗？确实没错，那是很早以前android官网也不建议使用，但是现在你去看官网还能看到那句话吗？google已经去掉了，至于为什么去掉了，我个人认为，有几种可能的原因，第一个是目前机器的配置，现在的cpu，内存等配置跟以前都不是在一个级别上，第二个就是软件层运行时对代码优化。另外还有`JakeWharton`大神都不考虑这个了，我们还有什么理由考虑这些了，其实真的没必要考虑这个了。




