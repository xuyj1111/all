package xu.all.algorithm;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * @Description: 将数组规定一个拆分大小，按顺序从小到大获取子数组
 * arr.size = 20, len = 5
 * 第一次：0-5
 * 第二次：5-10
 * 第三次：10-15
 * 第四次：15-20
 * @Author: xuyujun
 * @Date: 2021/12/6
 */
public class ArraySplit {
    private static final Integer LIMIT_LEN = 5;

    public static void main(String[] args) {
        int len = 20;
        double[] arr = new double[0];
        for (int i = 0; i < len; i++) {
            arr = ArrayUtils.add(arr, Math.round(Math.random() * 100));
        }

        int times = len % LIMIT_LEN != 0 ? len / LIMIT_LEN + 1 : len / LIMIT_LEN;
        for (int i = 0, start, end; i < times; i++) {
            start = i * LIMIT_LEN;
            end = i < times - 1 ? start + LIMIT_LEN : len;
            double[] doubles = Arrays.copyOfRange(arr, start, end);
            System.out.println("start: " + start + ", end: " + end);
            System.out.println(ArrayUtils.toString(doubles));
        }
    }
}
