package xu.all.javaSE.jdk8.stream;


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
    public void test() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        IntSummaryStatistics statistics = integers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("最小值：" + statistics.getMin());
        System.out.println("最大值：" + statistics.getMax());
        System.out.println("平均数：" + statistics.getAverage());
        System.out.println("总数：" + statistics.getCount());
        System.out.println("和：" + statistics.getSum());
    }
}
