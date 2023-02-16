package xu.all.test.multithread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 多线程
 * @Author: xuyujun
 * @Date: 2021/11/11
 */
@Slf4j
public class MultithreadTest {

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
        } catch (InterruptedException | ExecutionException e) {
            log.error("系统异常", e);
        }
    }

    @Test
    public void testReentrantLock() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("t1拿到锁");
                    Thread.sleep(1000L);
                    System.out.println("t1执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag = true;
                try {
                    System.out.println("t2等待拿锁");
//                    ****************** start **********************
//                    lock.lock();
//                    lock.lockInterruptibly();
                    flag = lock.tryLock(200, TimeUnit.MILLISECONDS);
//                    ****************** end **********************
                    if (flag) {
                        System.out.println("t2拿到锁");
                        System.out.println("t2执行结束");
                    } else {
                        System.out.println("指定时间内没有拿到锁，over");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (flag) {
                        lock.unlock();
                    }
                }
            }
        });
        t1.start();
        // 保证 t1 拿到锁
        Thread.sleep(10L);
        t2.start();
        // 保证 t2 在等待锁
        Thread.sleep(10L);
        // 若隐藏线程中断语句，t2 正常等待锁 或 指定时间内等待
        t2.interrupt();
        // 在测试类中需要保持运行一会
        Thread.sleep(2000L);
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
                log.error("系统异常", e);
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
                log.error("系统异常", e);
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
                log.error("系统异常", e);
            }
            System.out.println(Thread.currentThread().getName() + ": end");
        });
        cb.await();
        System.out.println("all threads are end");
        cb.reset();
    }

    /**
     * @Description: Semaphore
     * 比作控制流量的红绿灯，一条马路上只允许100辆车，前100看到的是绿灯，超过100的看到的是红灯，前100通过一个，后面就可以补进一个
     * ————
     * new Semaphore(2)：入参表示许可数目，允许多少线程进行访问
     * new Semaphore(2, true)：与上相比，多个一个布尔入参，表示是否公平，对等待时间越长的线程越先获得许可
     * .acquire()：获取一个许可
     * .acquire(num)：获取多个许可
     * .release()：释放一个许可
     * .release(num)：释放多个许可
     */
    @Test
    public void testSemaphore() throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        Runnable runnable = () -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "：占用中...");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "：释放");
                semaphore.release();
            } catch (InterruptedException e) {
                log.error("系统异常", e);
            }
        };
        Executor threadPool = createThreadPool();
        for (int i = 0; i < 3; i++) {
            threadPool.execute(runnable);
        }
        Thread.sleep(5000L);
        System.out.println("主线程等了五秒");
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


