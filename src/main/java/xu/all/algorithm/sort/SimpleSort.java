package xu.all.algorithm.sort;

import xu.all.algorithm.DataFactory;

import java.util.Arrays;

/**
 * @Description: 简单排序
 * @Author: xuyujun
 * @Date: 2022/5/23
 */
public class SimpleSort {

    public static void main(String[] args) {
        Integer[] ints = DataFactory.buildTenDisorderNums();
        System.out.println("Before sorting: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After sorting: " + Arrays.toString(ints));
    }

    private static void sort(Integer[] ints) {
        for (int i = 0, length = ints.length; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (ints[i] > ints[j]) {
                    int element = ints[i];
                    ints[i] = ints[j];
                    ints[j] = element;
                }
            }
        }
    }
}
