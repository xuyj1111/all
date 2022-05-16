package xu.all.algorithm;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataFactory {


    public static List<Integer> buildTenDisorderNums() {
        Random random = new Random();
        ArrayList<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }
        return list;
    }
}
