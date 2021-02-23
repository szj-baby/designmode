package singleton;

/**
 * @author shaozhijiang
 * @date 2021/2/20
 * 单例模式 第三种静态内部类
 * jvm保证单例
 * 加载外部类时不会加载内部类这样实现了懒加载
 * 最完美写法
 */
public class Singleton3 {


    private Singleton3() {
    }
    //静态内部类
    private static class SingletonHolder {
        private static final Singleton3 instance = new Singleton3();
    }

    public static Singleton3 getInstance(){
        return SingletonHolder.instance;
    }
}
