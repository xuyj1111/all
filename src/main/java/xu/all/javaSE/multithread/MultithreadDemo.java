package xu.all.javaSE.multithread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description: 多线程
 * @Author: xuyujun
 * @Date: 2021/11/11
 */
public class MultithreadDemo {

    @Test
    public void testMyThread() {
        MyThread myThread01 = new MyThread();
        MyThread myThread02 = new MyThread();
        myThread01.start();
        myThread02.start();
    }

    @Test
    public void testMyRunnable() {
        Thread thread01 = new Thread(new MyRunnable());
        Thread thread02 = new Thread(new MyRunnable());
        thread01.start();
        thread02.start();
    }

    @Test
    public void testMyCallable() {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


