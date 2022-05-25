package xu.all.algorithm.sort;

import cn.hutool.core.util.ArrayUtil;
import com.google.common.collect.Lists;
import xu.all.algorithm.DataFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 桶排序
 * @Author: xuyujun
 * @Date: 2022/5/24
 */
public class BucketSort {

    public static void main(String[] args) {
        Integer[] ints = DataFactory.buildTenDisorderNums();
        System.out.println("Before sorting: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After sorting: " + Arrays.toString(ints));
    }

    private static void sort(Integer[] ints) {
        int numberOfBucket = ArrayUtil.max(ints) - ArrayUtil.min(ints) + 1;
        ArrayList<List> buckets = new ArrayList<>();

        // init buckets
        for (int i = 0; i < numberOfBucket; i++) {
            buckets.add(Lists.newArrayList());
        }

        // store elements to buckets
        for (Integer i : ints) {
            int hash = i - ArrayUtil.min(ints);
            buckets.get(hash).add(i);
        }

        // sort individual bucket
        for (List bucket : buckets) {
            Collections.sort(bucket);
        }

        // concatenate buckets to origin array
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int value : bucket) {
                ints[index++] = value;
            }
        }
    }
}
