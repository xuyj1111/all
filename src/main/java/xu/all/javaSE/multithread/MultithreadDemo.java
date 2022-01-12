package xu.all.javaSE.multithread;

import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

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

    /**
     * @Description: CountDownLatch
     * CountDownLatch是通过计数器实现的，每当一个线程完成任务后，
     * 调用countDown方法，使计数器减1，直到减到0时，调用await方法而等待的主线程重新启动
     * ————
     * CountDownLatch cdl = new CountDownLatch(2)：创建计数器初值为2的CountDownLatch对象
     * .countDown()：计数器减1
     * .await()：等待计数器减到0
     * .await(long timeout, TimeUnit unit)：等待计数器减到0，或者传参的时间到
     */
    @Test
    public void testCountDownLatch() throws InterruptedException {
        //创建CountDownLatch，传参设置计数器初值
        CountDownLatch cdl = new CountDownLatch(2);
        Executor threadPool = createThreadPool();
        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ": starting");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": end");
            //计数器减1
            cdl.countDown();
        });
        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ": starting");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": end");
            //计数器减1
            cdl.countDown();
        });
        //主线程等待计数器减到0
        cdl.await();
        System.out.println("all threads are end");
    }

    /**
     * @Description: CyclicBarrier
     * 让一组线程到达一个同步点，然后阻塞，直到最后一个线程到达，开启全部线程
     * ————
     * CyclicBarrier cb = new CyclicBarrier(2)：表示等待两个，若当前没有参数那么多的线程，则会永远阻塞
     * CyclicBarrier cb = new CyclicBarrier(2, Runnable实现对象)：在最后一个线程到达后，优先执行参数Runnable
     * .await()：表示当前线程到达同步点
     * .reset()：重制CyclicBarrier，可重复使用，而CountDownLatch只能使用一次
     */
    @Test
    public void testCyclicBarrier() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(2);
        Executor threadPool = createThreadPool();
        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ": starting");
            try {
                Thread.sleep(1000);
                cb.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": end");
        });
        cb.await();
        System.out.println("all threads are end");
        cb.reset();
    }

    /**
     * @Description: 创建线程池
     */
    private Executor createThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(60);
        executor.setThreadNamePrefix("myThreadPool: ");
        executor.setTaskDecorator(new MyTaskDecorator());
        executor.initialize();
        return executor;
    }
}


