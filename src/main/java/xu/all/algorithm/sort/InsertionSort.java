package xu.all.algorithm.sort;

import cn.hutool.core.util.NumberUtil;

import java.util.Arrays;

/**
 * @Description: 插入排序
 * @Author: xuyujun
 * @Date: 2022/5/24
 */
public class InsertionSort {

    public static void main(String[] args) {
        Integer[] ints = DataFactory.buildTenDisorderNums();
        System.out.println("Before sorting: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After sorting: " + Arrays.toString(ints));
    }

    private static void sort(Integer[] ints) {
        for (int i = 1; i < ints.length; i++) {
            int insertValue = ints[i];
            int j;
            for (j = i - 1; j >= 0 && NumberUtil.compare(ints[j], insertValue) > 0; j--) {
                ints[j + 1] = ints[j];
            }
            if (j != i - 1) {
                ints[j + 1] = insertValue;
            }
        }
    }
}
