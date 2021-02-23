package singleton;

/**
 * @author shaozhijiang
 * @date 2021/2/20
 * * description : 单例模式  懒汉式2
 */
public class SingletonLazy2 {
    private SingletonLazy2() {
    }

    private static final SingletonLazy2 singleton;

    static {
        singleton = new SingletonLazy2();
    }

    public static SingletonLazy2 getSingleton() {
        return singleton;
    }
}
