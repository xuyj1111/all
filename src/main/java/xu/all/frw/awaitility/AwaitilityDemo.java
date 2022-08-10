package xu.all.frw.awaitility;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import xu.all.dto.PeopleBuilderDTO;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.fieldIn;
import static org.hamcrest.Matchers.greaterThan;

@Slf4j
public class AwaitilityDemo {

    private PeopleBuilderDTO peopleDTO = PeopleBuilderDTO.builder().age(20).build();

    /**
     * @Description: until方法
     * 表示等待到需要满足什么条件
     * 内部实现Callable重写方法，jdk8可缩写，return结果即为条件
     * .until(new Callable<..>(){...})：返回值为Boolean
     * .until(new Callable<..>(){...}, Matcher<? super T> matcher)：可再多加一个matcher校验参数，对条件结果校验，此时返回值可以是任意类型
     * ————
     * .pollInterval(long pollInterval, TimeUnit unit)：轮询间隔
     * .atMost(long timeout, TimeUnit unit)：最多等待时间
     */
    @Test
    public void testUntil() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": i=" + i);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    log.error("系统异常", e);
                }
                peopleDTO.setAge(peopleDTO.getAge() + 1);
            }
        }).start();

        await().pollInterval(1, SECONDS).atMost(30, SECONDS).until(() -> peopleDTO.getAge() > 22);
        //以下使用matcher校验参数
//        await().pollInterval(1, SECONDS).atMost(30, SECONDS).until(() -> peopleDTO.getAge(), greaterThan(22));
        System.out.println("over");
    }

    /**
     * @Description: 引用对象字段判断
     * fieldIn(Object object)：传入判断字段的对象
     * .ofType(Class<T> fieldType)：标识字段类型
     * .andWithName(String fieldName)：标识字段名
     * .andAnnotatedWith(Class<? extends Annotation> annotationType)：标识字段注解
     * 若定位不到字段，会报错
     */
    @Test
    public void testFiled() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": i=" + i);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    log.error("系统异常", e);
                }
                peopleDTO.setAge(peopleDTO.getAge() + 1);
            }
        }).start();

        await().pollInterval(1, SECONDS).atMost(30, SECONDS).until(
                fieldIn(peopleDTO).ofType(Integer.class).andWithName("age").andAnnotatedWith(JsonProperty.class), greaterThan(22));
        System.out.println("over");
    }
}
