package xu.all.javaSE.multithread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;

/**
 * @Description: 多线程
 * @Author: xuyujun
 * @Date: 2021/11/11
 */
public class MainClass {
    public static void main(String[] args) {
        MainClass mainClass = new MainClass();

//        mainClass.testMyThread();

//        mainClass.testMyRunnable();

//        mainClass.testMyCallable();

//        mainClass.testCompletableFutureSupplyAsync();

//        mainClass.testCompletableFutureThenApply();

//        mainClass.testCompletableFutureThenAccept();
    }

    private void testMyThread() {
        MyThread myThread01 = new MyThread();
        MyThread myThread02 = new MyThread();
        myThread01.start();
        myThread02.start();
    }

    private void testMyRunnable() {
        Thread thread01 = new Thread(new MyRunnable());
        Thread thread02 = new Thread(new MyRunnable());
        thread01.start();
        thread02.start();
    }

    private void testMyCallable() {
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
     * @Description: supplyAsync创建有返回值异步任务，runAsync创建无返回值异步任务
     */
    private void testCompletableFutureSupplyAsync() {
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("start SupplyAsync, 线程名：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1111;
        });

        System.out.println("main thread start, time: " + System.currentTimeMillis());
        try {
            //主线程会阻塞，等待子线程结束得到返回值
            System.out.println("return: " + supplyAsync.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end, time: " + System.currentTimeMillis());
    }

    /**
     * @Description: thenApply 表示某个任务执行完成后执行的动作，即回调方法，会将该任务的执行结果即方法返回值作为入参传递到回调方法中
     * thenApplyAsync 与thenApply的区别是 前者是先挂起一个新线程异步执行，直到前一个任务执行完，回调返回值；后者先执行完前一个任务，再执行后一个任务，同一个线程
     */
    private void testCompletableFutureThenApply() {
        ForkJoinPool pool = new ForkJoinPool();
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("start SupplyAsync, 线程名：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1111;
        }, pool);
        CompletableFuture<String> thenApply = supplyAsync.thenApply((result) -> {
            System.out.println("start ThenApply, 线程名：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "test:" + result;
        });

        System.out.println("main thread start, time: " + System.currentTimeMillis());
        try {
            System.out.println("supplyAsync return: " + supplyAsync.get());
            System.out.println("thenApply return: " + thenApply.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end, time: " + System.currentTimeMillis());
    }

    /**
     * @Description: thenAccept 同 thenApply 接收上一个任务的返回值作为参数，但是无返回值
     * thenRun 的方法没有入参，也没有返回值
     */
    private void testCompletableFutureThenAccept() {
        ForkJoinPool pool = new ForkJoinPool();
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("start SupplyAsync, 线程名：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1111;
        }, pool);
        CompletableFuture completableFuture = supplyAsync.thenApply((result) -> {
            System.out.println("start ThenApply, 线程名：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "test:" + result;
        }).thenAccept((result) -> {
            System.out.println("start ThenAccept, 线程名：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenRun(() -> {
            System.out.println("start thenRun, 线程名：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish...");
        });

        System.out.println("main thread start, time: " + System.currentTimeMillis());
        try {
            System.out.println("supplyAsync return: " + supplyAsync.get());
            completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end, time: " + System.currentTimeMillis());
    }

    /**
    * @Description: 创建ThreadPoolTaskExecutor
    */
    private ThreadPoolTaskExecutor create(){
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


