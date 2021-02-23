package singleton;

/**
 * @author shaozhijiang
 * @date 2021/2/20
 * description:  单利模式 懒汉式 在调用时创建该对象的实例
 * 可能存在线程不安全的情况(但是如何解决呢? 加锁)
 */
public class SingletonHungry1 {

    private SingletonHungry1() {
    }

    //双重检查的时候需要加上 volatile  防止指令重排
    private static volatile SingletonHungry1 singleton = null;

    //这种情况可能存在线程不安全的情况 在if判断处
    //线程不安全写法
//    public static SingletonHungry1 getSingleton() {
//
//        if (singleton == null) {
//
//            singleton = new SingletonHungry1();
//        }
//        return singleton;
//    }
//
//    //线程安全的写法1 这样写 效率会略微的有些低
//    public static synchronized SingletonHungry1 getSingleton1() {
//        if (singleton == null) {
//            singleton = new SingletonHungry1();
//        }
//        return singleton;
//    }

    //线程安全写法2  最优方法
    public static SingletonHungry1 getSingleton2() {
        if (singleton == null) { //1
            //双重检查
            synchronized (SingletonHungry1.class) {
                if (singleton == null) {
                    singleton = new SingletonHungry1();
                }
            }
        }
        return singleton; //5
    }


    /**
     第一个作用是：
     因为 singleton = new Singleton() 这句话可以分为三步：

     1为 singleton 分配内存空间；
     2初始化 singleton；
     3将 singleton 指向分配的内存空间。

     但是由于JVM具有指令重排的特性，执行顺序有可能变成 1-3-2。
     指令重排在单线程下不会出现问题，但是在多线程下会导致一个线程获得一个未初始化的实例。
     例如：线程Thread1先执行了1、3，此时singleton已经指向了分配的内存空间，所以不为null，
     若这时有Thread2调用 getInstance() 在 //1 处发现 singleton 不为空，
     因此直接跳到 //5 处 return singleton， 但是此时Thread1并未对 singleton 进行初始化，
     那么返回的singleton是未被完整创建的singleton。而使用 volatile 会禁止JVM指令重排，从而保证在多线程下也能正常执行。

     第二个作用是：
     volatile关键字保证了变量的可见性，被volatile关键字修饰的变量，
     （1）在当前线程的工作内存中修改之后会立即更新回主内存；
     （2）在其他线程从自己的工作内存中读取该变量值之前，会先从主内存中更新最新的值到各自的工作内存。
         因此当 进入synchronized同步代码块的线程成功创建了实例，并释放了锁之后，等待在
         //2 处的的某个线程拿到锁进入同步代码块，并进行第二次if判断时，读取到的singleton已经成功创建实例，就
         不会再创建第二个实例，而是直接到 //5 返回创建好的singleton。
     **/


}
