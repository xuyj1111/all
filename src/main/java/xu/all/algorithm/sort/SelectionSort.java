package xu.all.algorithm.sort;

import cn.hutool.core.util.ArrayUtil;
import xu.all.algorithm.DataFactory;

import java.util.Arrays;

/**
 * @Description: 选择排序
 * @Author: xuyujun
 * @Date: 2022/5/24
 */
public class SelectionSort {

    public static void main(String[] args) {
        Integer[] ints = DataFactory.buildTenDisorderNums();
        System.out.println("Before sorting: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After sorting: " + Arrays.toString(ints));
    }

    private static void sort(Integer[] ints) {
        for (int i = 0, length = ints.length; i < length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (ints[j].compareTo(ints[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                ArrayUtil.swap(ints, minIndex, i);
            }
        }
    }
}
