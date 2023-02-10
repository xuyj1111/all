package xu.all.jdk8.stream;


import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: jdk8中的流操作
 * @Author: xuyujun
 * @Date: 2022/1/5
 */
public class StreamDemo {

    /**
     * @Description: peek操作
     * peek 会继续返回Stream对象
     * forEach 返回void 结束Stream操作
     *
     * 流生命周期有三个阶段：
     * 1. 起始生成阶段
     * 2. 中间操作（惰性）
     * 3. 终端操作，分为 最终的消费 （foreach 之类的）和 归纳 （collect）两类
     * 如果没有终端操作，中间操作将不执行
     */
    @Test
    public void streamPeek() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> collect = integers.stream().peek(System.out::print).collect(Collectors.toList());
    }

    /**
     * @Description: 串行流遍历
     */
    @Test
    public void streamForeach() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        integers.stream().forEach(System.out::print);
    }

    /**
     * @Description: 并行流遍历
     * parallelStream：并行流，效率高，但执行顺序不固定
     * ——
     * forEach：遍历，遇到并行流就并行遍历
     * forEachOrdered：按顺序遍历，遇到并行流强行顺序遍历，则速度会比forEach慢
     */
    @Test
    public void parallelStreamForeach() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        integers.parallelStream().forEach(System.out::print);
        System.out.println();
        integers.parallelStream().forEachOrdered(System.out::print);
    }

    /**
     * @Description: 映射每个元素到对应的结果
     * distinct：去重
     */
    @Test
    public void streamMap() {
        List<Integer> integers = Arrays.asList(1, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> collect = integers.stream().map(integer -> integer * integer).distinct().collect(Collectors.toList());
        collect.forEach(s -> System.out.print(s + " "));
    }

    /**
     * @Description: 过滤
     */
    @Test
    public void streamFilter() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        integers.stream().filter(integer -> {
            if (integer > 5) {
                return true;
            }
            return false;
        }).collect(Collectors.toList()).forEach(integer -> System.out.print(integer + " "));
    }

    /**
     * @Description: 限制数量
     */
    @Test
    public void streamLimit() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        integers.stream().limit(4).forEach(integer -> System.out.print(integer + " "));
    }

    /**
     * @Description: 排序
     * reversed：倒序
     * ——
     * random.ints(数字数量, 最小值, 最大值)
     */
    @Test
    public void streamSort() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Random random = new Random();
        random.ints(10, 1, 100).sorted().forEach(s -> System.out.print(s + " "));
        System.out.println();
        integers.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).forEach(s -> System.out.print(s + " "));
    }

    /**
     * @Description: IntStream流
     * mapToInt：映射成IntStream流（ps：也有mapTo其他类型）
     * ——
     * 只有在IntStream流中才可以使用统计的一些函数
     */
    @Test
    public void intStreamDemo() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        IntSummaryStatistics statistics = integers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("最小值：" + statistics.getMin());
        System.out.println("最大值：" + statistics.getMax());
        System.out.println("平均数：" + statistics.getAverage());
        System.out.println("总数：" + statistics.getCount());
        System.out.println("和：" + statistics.getSum());
    }

    /**
     * @Description: reduce的使用（一）
     * Optional<T> reduce(BinaryOperator<T> accumulator);
     * <p>
     * i1的初值为list的第一个，i2的初值为list的第二个
     * 此后i1的值为上一次的返回值，i2则从下标2开始遍历list
     */
    @Test
    public void reduceDemo01() {
        List<Integer> list = Arrays.asList(11, 22, 33, 44);
        Optional<Integer> reduce = list.stream().reduce((i1, i2) -> {
            System.out.println("i1: " + i1);
            System.out.println("i2: " + i2);
            System.out.println("------------------");
            return i1 > i2 ? i1 : i2;
        });
        if (reduce.isPresent()) {
            System.out.println("result：" + reduce.get());
        }
    }


    /**
     * @Description: reduce的使用（二）
     * T reduce(T identity, BinaryOperator<T> accumulator);
     *
     * i1的初值为参数1（类型需和list内值类型相同），i2的初值为list的第一个
     * 此后i1的值为上一次的返回值，i2则从下标1开始遍历list
     *
     * 第二种写法：.reduce(null, this::方法名)
     * 该方法需要有两个入参，对应i1和i2，逻辑写在方法内
     */
    @Test
    public void reduceDemo02() {
        List<Integer> list = Arrays.asList(11, 22, 33, 44);
        Integer reduce = list.stream().reduce(null, (i1, i2) -> {
            System.out.println("i1: " + i1);
            System.out.println("i2: " + i2);
            System.out.println("------------------");
            if (Objects.isNull(i1)) {
                return i2;
            }
            return i1 > i2 ? i1 : i2;
        });
        System.out.println("result：" + reduce);
    }

    /**
     * @Description: reduce的使用（三）
     * Optional<T> reduce(BinaryOperator<T> accumulator);
     *
     * i1的初值为参数1（类型无需和list内值类型相同），i2的初值为list的第一个
     * 此后i1的值为上一次的返回值，i2则从下标1开始遍历list
     * 参数3只有在parallelStream会执行，当前stream不会执行
     */
    @Test
    public void reduceDemo03() {
        List<Integer> list = Arrays.asList(11, 22, 33, 44);
        List<Integer> newList = Lists.newArrayList();
        List<Integer> reduce = list.stream().reduce(newList, (i1, i2) -> {
            i1.add(i2);
            System.out.println("i1: " + i1);
            System.out.println("i2: " + i2);
            System.out.println("------------------");
            return i1;
        }, (i1, i2) -> null);
        System.out.println("result：" + reduce);
    }
}
