package xu.all.algorithm.sort;

import cn.hutool.core.util.ArrayUtil;

import java.util.Arrays;

/**
 * @Description: 快速排序
 * @Author: xuyujun
 * @Date: 2022/5/23
 */
public class QuickSort {

    public static void main(String[] args) {
        Integer[] ints = DataFactory.buildTenDisorderNums();
        System.out.println("Before sorting: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After sorting: " + Arrays.toString(ints));
    }

    private static void sort(Integer[] ints) {
        doSort(ints, 0, ints.length - 1);
    }

    private static void doSort(Integer[] ints, int left, int right) {
        if (left < right) {
            int pivot = partition(ints, left, right);
            doSort(ints, left, pivot - 1);
            doSort(ints, pivot, right);
        }
    }

    private static int partition(Integer[] ints, int left, int right) {
        int middle = ints[(left + right) >> 1];
        while (left < right) {
            while (ints[left] < middle) {
                left++;
            }
            while (ints[right] > middle) {
                right--;
            }
            if (left <= right) {
                ArrayUtil.swap(ints, left, right);
                left++;
                right--;
            }
        }
        return left;
    }
}
