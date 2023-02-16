package xu.all.test;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import xu.all.dto.TestDTO;
import xu.all.test.multithread.MyTaskDecorator;

import java.util.concurrent.Executor;

/**
 * @Description: 测试各种ThreadLocal
 * @Author: xuyujun
 * @Date: 2022/2/23
 */
public class ThreadLocalTest {

    private ThreadLocal<Object> t = new ThreadLocal<>();
    private ThreadLocal<Object> it = new InheritableThreadLocal<>();
    private ThreadLocal tt = new TransmittableThreadLocal();


    private void printForThreadLocal() {
        System.out.println(Thread.currentThread().getName() + ":" + t.get());
    }

    private void printForInheritableThreadLocal() {
        System.out.println(Thread.currentThread().getName() + ":" + it.get());
    }

    private void printForTransmittableThreadLocal() {
        System.out.println(Thread.currentThread().getName() + ":" + tt.get());
    }

    /**
     * @Description: 创建线程池
     */
    private Executor createThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(10);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(60);
        executor.setThreadNamePrefix("myThreadPool: ");
        executor.setTaskDecorator(new MyTaskDecorator());
        executor.initialize();
        return executor;
    }


    /**
     * @Description: 最简单的ThreadLocal用法
     * 证明了线程之间不共享threadLocal中的值
     * @Author: xuyujun
     * @Date: 2022/2/23
     */
    @Test
    public void test01ThreadLocal() {
        t.set(1);

        new Thread(() -> printForThreadLocal()).start();

        printForThreadLocal();
    }

    /**
     * @Description: InheritableThreadLocal实现子父线程可传递
     * @Author: xuyujun
     * @Date: 2022/2/23
     */
    @Test
    public void test01InheritableThreadLocal() {
        it.set(1);

        new Thread(() -> printForInheritableThreadLocal()).start();

        printForInheritableThreadLocal();
    }

    /**
     * @Description: 引用对象不可证明子父线程指向同一对象，因为对象被重新赋值后变的是整个引用
     * @Author: xuyujun
     * @Date: 2022/2/23
     */
    @SneakyThrows
    @Test
    public void test02InheritableThreadLocal() {
        it.set(1);
        printForInheritableThreadLocal();

        new Thread(() -> {
            printForInheritableThreadLocal();
            it.set(2);
            printForInheritableThreadLocal();
        }).start();

        Thread.sleep(500);
        printForInheritableThreadLocal();
    }

    /**
     * @Description: InheritableThreadLocal中子父线程中指向同一对象
     * @Author: xuyujun
     * @Date: 2022/2/23
     */
    @SneakyThrows
    @Test
    public void test03InheritableThreadLocal() {
        TestDTO testDTO = new TestDTO();
        testDTO.setName("张三");
        testDTO.setAge(20);
        it.set(testDTO);
        printForInheritableThreadLocal();

        new Thread(() -> {
            printForInheritableThreadLocal();
            TestDTO pp = (TestDTO) it.get();
            pp.setName("李四");
            it.set(pp);
            printForInheritableThreadLocal();
        }).start();

        Thread.sleep(500);
        printForInheritableThreadLocal();
    }

    /**
     * @Description: 线程池在init新的线程时，会将主线程的ThreadLocal传递给子线程
     * @Author: xuyujun
     * @Date: 2022/2/23
     */
    @SneakyThrows
    @Test
    public void test04InheritableThreadLocal() {
        it.set(1);
        printForInheritableThreadLocal();

        Executor threadPool = createThreadPool();
        threadPool.execute(() -> printForInheritableThreadLocal());

        printForInheritableThreadLocal();
    }

    /**
     * @Description: 但如果在init子线程时，主线程ThreadLocal中值为null，子线程也是null；
     *               之后主线程被赋了新值，已经被init的子线程再次被调用时，主线程不会赋值给子线程（当前线程池大小为1，则可实现二次调用创建的子线程）
     * @Author: xuyujun
     * @Date: 2022/2/23
     */
    @SneakyThrows
    @Test
    public void test05InheritableThreadLocal() {
        Executor threadPool = createThreadPool();
        threadPool.execute(() -> printForInheritableThreadLocal());

        it.set(1);

        threadPool.execute(() -> printForInheritableThreadLocal());

        printForInheritableThreadLocal();
    }


    /**
     * @Description: 解决了InheritableThreadLocal父子线程不传递的问题
     */
    @Test
    public void test01TransmittableThreadLocal() {
        Executor threadPool = TtlExecutors.getTtlExecutor(createThreadPool());

        threadPool.execute(() -> printForTransmittableThreadLocal());

        tt.set(1);

        threadPool.execute(() -> printForTransmittableThreadLocal());

        printForTransmittableThreadLocal();
    }
}
