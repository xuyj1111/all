package xu.all.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import xu.all.dto.PeopleBuilderDTO;

/**
 * @Description: 测试各种ThreadLocal
 * @Author: xuyujun
 * @Date: 2022/2/23
 */
public class ThreadLocalTest {

    private ThreadLocal<Object> t1 = new ThreadLocal<>();
    private ThreadLocal<Object> t2 = new InheritableThreadLocal<>();


    private void printForThreadLocal() {
        System.out.println(Thread.currentThread().getName() + ":" + t1.get());
    }

    private void printForInheritableThreadLocal() {
        System.out.println(Thread.currentThread().getName() + ":" + t2.get());
    }

    /**
     * @Description: 最简单的ThreadLocal用法
     * 证明了线程之间不共享threadLocal中的值
     * @Author: xuyujun
     * @Date: 2022/2/23
     */
    @Test
    public void test01ThreadLocal() {
        t1.set(1);

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
        t2.set(1);

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
        t2.set(1);
        printForInheritableThreadLocal();

        new Thread(() -> {
            printForInheritableThreadLocal();
            t2.set(2);
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
        PeopleBuilderDTO p = PeopleBuilderDTO.builder().name("张三").age(20).build();
        t2.set(p);
        printForInheritableThreadLocal();

        new Thread(() -> {
            printForInheritableThreadLocal();
            PeopleBuilderDTO pp = (PeopleBuilderDTO) t2.get();
            pp.setName("李四");
            t2.set(pp);
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
    public void test04InheritableThreadLocal() {
        PeopleBuilderDTO p = PeopleBuilderDTO.builder().name("张三").age(20).build();
        t2.set(p);
        printForInheritableThreadLocal();

        new Thread(() -> {
            printForInheritableThreadLocal();
            PeopleBuilderDTO pp = (PeopleBuilderDTO) t2.get();
            pp.setName("李四");
            t2.set(pp);
            printForInheritableThreadLocal();
        }).start();

        Thread.sleep(500);
        printForInheritableThreadLocal();
    }

}
