package learn.java.thread;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("我实现了Runnable接口！");
    }
}

class Test2{
    public static void main(String[] args) {
        new Thread(new MyRunnable()).start();
    }
}
