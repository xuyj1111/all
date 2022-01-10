package xu.all.javaSE.jdk8.option;

import org.junit.jupiter.api.Test;
import xu.all.javaSE.jdk8.stream.DemoDTO;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @Description: Option类的demo
 * @Author: xuyujun
 * @Date: 2022/1/10
 */
public class OptionDemo {

    /**
     * @Description: 创建optional实例
     * ● Optional.of(T value)——将入参转换为optional对象
     * ● Optional.ofNullable(T value)——与上区别为可传为null
     * ● Optional.empty()——创建一个空的optional对象
     */
    @Test
    public void createOptional() {

        String s1 = "hahaha";
        String s2 = null;

        Optional<String> o1 = Optional.of(s1);
        System.out.println(o1.isPresent());
        Optional<String> o2 = Optional.ofNullable(s2);
        System.out.println(o2.isPresent());

        Optional<Object> o3 = Optional.empty();
        System.out.println(o3.isPresent());
    }

    /**
     * @Description: 包装可变对象的optional的测试
     * optional实例是不可变的
     */
    @Test
    public void changeOptional() {
        AtomicInteger num = new AtomicInteger();
        Optional<AtomicInteger> o = Optional.of(num);
        num.incrementAndGet();
        System.out.println(o);
    }

    /**
     * @Description: orElse方法的使用
     * orElse有三种形式
     * orElse(T value)：返回包含的值，若空则返回指定的值
     * orElseGet(Supplier<? extends T> other)：返回包含的值，若空则调用Supplier并返回对应的值
     * orElseThrow(Supplier<? extends X> exceptionSupplier)：返回包含的值，若空则抛出Supplier产生的异常
     */
    @Test
    public void orElseOptional() {
        System.out.println("--------------orElse(T value)--------------");
        System.out.println(Optional.of("hello world").orElse("This is empty"));
        System.out.println(Optional.empty().orElse("This is empty"));

        System.out.println("--------------orElseGet(Supplier<? extends T> other)--------------");
        Supplier<DemoDTO> s1 = DemoDTO::new;
        System.out.println(Optional.empty().orElseGet(s1));

        System.out.println("--------------orElseThrow(Supplier<? extends X> exceptionSupplier)--------------");
        Supplier<Exception> s2 = NullPointerException::new;
        try {
            System.out.println(Optional.empty().orElseThrow(s2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description:
     */
    @Test
    public void flatMapOptional() {

    }
}
