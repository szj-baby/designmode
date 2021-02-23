package singleton;

/**
 * @author shaozhijiang
 * @date 2021/2/20
 * description : 单例模式  懒汉式1
 * 这种方式在类初始化的时候创建类的对象  线程安全
 * 缺点.: 在类初始化的时候就创建了实例 ,也许用不到这个实例也被创建出来了
 */
public class SingletonLazy1 {

    private SingletonLazy1() {

    }
    private static final SingletonLazy1 singleton = new SingletonLazy1();

    public static SingletonLazy1 getSingleton() {
        return singleton;
    }
}
