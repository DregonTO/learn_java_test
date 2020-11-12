package learn.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        String msg="我实现了Callable接口！";
        System.out.println("我实现了Callable接口！");
        return msg;
    }
}
class Test3{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        String msg = futureTask.get();
        System.out.println("返回值："+msg);
    }
}