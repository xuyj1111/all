package xu.all.algorithm.sort;

import cn.hutool.core.util.ArrayUtil;

import java.util.Arrays;

/**
 * @Description: 冒泡排序
 * @Author: xuyujun
 * @Date: 2022/5/23
 */
public class BubbleSort {

    public static void main(String[] args) {
        Integer[] ints = DataFactory.buildTenDisorderNums();
        System.out.println("Before sorting: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After sorting: " + Arrays.toString(ints));
    }

    private static void sort(Integer[] ints) {
        for (int i = 1, length = ints.length; i < length; i++) {
            boolean swapped = false;
            for (int j = 0; j < length - 1; j++) {
                if (ints[j] > ints[j + 1]) {
                    ArrayUtil.swap(ints, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
