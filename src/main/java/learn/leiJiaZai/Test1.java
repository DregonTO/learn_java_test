package learn.leiJiaZai;

/**
 * 静态方法 静态代码块 代码块 静态代码块中的代码块 的加载情况
 */
public class Test1 {
    public Test1() {
        System.out.println("默认构造方法！--");
    }

    //非静态代码块
    {
        System.out.println("非静态代码块！--");
    }

    //静态代码块
    static{
        System.out.println("静态代码块！--");
    }

    private static void test(){
        System.out.println("静态方法中的内容！--");
        {
            System.out.println("静态方法中的代码块！--");
        }
    }

    public static void main(String[] args) {
//        Test1 test1 = new Test1();
        Test1.test();//静态代码块！--静态方法中的内容! --静态方法中的代码块！--
    }

}
