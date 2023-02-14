package xu.all.algorithm.sort;

import java.util.Random;

public class DataFactory {


    public static Integer[] buildTenDisorderNums() {
        Random random = new Random();
        Integer[] ints = new Integer[10];
        for (int i = 0; i < 10; i++) {
            ints[i] = random.nextInt(100);
        }
        return ints;
    }
}
