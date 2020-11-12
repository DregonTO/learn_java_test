package learn.java.thread;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("线程执行了！");
    }
}

class Test1 {
    public static void main(String[] args) {
        new MyThread().start();
    }
}