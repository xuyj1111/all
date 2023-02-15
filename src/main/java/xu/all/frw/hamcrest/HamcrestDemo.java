package xu.all.frw.hamcrest;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import xu.all.dto.DemoDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @Description: 参考API：http://hamcrest.org/JavaHamcrest/?utm_source=testingpai.com
 * @Author: xuyujun
 * @Date: 2021/7/30
 */
public class HamcrestDemo {

    /**
     * @Description: Core
     * anything
     * describedAs
     * is：包装器，提高可读性，与equalTo无差
     */
    @Test
    public void hamcrestCore() {
        String s1 = "balabala";
        String s2 = "bilibili";
        //anything任意都对
        assertThat(s1, anything("反正写什么都是对的"));
        //describedAs错误文本
        assertThat(s1, describedAs("失败了，毁灭吧，累了", is(s2)));
    }

    /**
     * @Description: Logical
     * -allOf
     * -anyOf
     * -not
     */
    @Test
    public void hamcrestLogical() {
        String s1 = "张三";
        //类似&&
        assertThat(s1, allOf(is("张三"), not("李四")));
        //类似||
        assertThat(s1, anyOf(is("张三"), is("李四")));
    }

    /**
     * @Description: Object
     * equalTo
     * hasToString
     * instanceOf
     * notNullValue,nullValue
     * sameInstance
     */
    @Test
    public void hamcrestObject() {
        HashMap<Object, Object> map1 = Maps.newHashMap();
        HashMap<Object, Object> map2 = Maps.newHashMap();
        HashMap<Object, Object> map3 = null;
        assertThat(map1, equalTo(map2));
        //判断toString字符串
        assertThat(map1, hasToString("{}"));
        //是否哪个类
        assertThat(map1, instanceOf(HashMap.class));
        //不是null
        assertThat(map1, notNullValue());
        //是null
        assertThat(map3, nullValue());
        //判断是否为同一个对象
        assertThat(map1, sameInstance(map1));
    }

    /**
     * @Description: Beans
     * hasProperty
     */
    @Test
    public void hamcrestBeans() {
        //是否有指定属性
        assertThat(new DemoDTO(), hasProperty("name"));
    }

    /**
     * @Description: Collections
     * array
     * hasEntry, hasKey, hasValue
     * hasItem, hasItems
     * hasItemInArray
     */
    @Test
    public void hamcrestCollections() {
        //匹配数组（有顺序）
        assertThat(new Integer[]{1, 2, 3}, is(array(equalTo(1), equalTo(2), equalTo(3))));
        //匹配map实体，键或者值
        HashMap<String, String> map = new HashMap<>();
        map.put("key01", "value01");
        assertThat(map, hasEntry("key01", "value01"));
        assertThat(map, hasKey("key01"));
        assertThat(map, hasValue("value01"));
        //匹配集合
        List<String> list = new ArrayList<>();
        list.add("str01");
        list.add("str02");
        assertThat(list, hasItem("str01"));
        assertThat(list, hasItems("str01", "str02"));
        //数组是否有指定值
        assertThat(new String[]{"foo", "bar"}, hasItemInArray("foo"));
    }

    /**
     * @Description: Number
     * closeTo
     * greaterThan, greaterThanOrEqualTo, lessThan, lessThanOrEqualTo
     */
    @Test
    public void hamcrestNumber() {
        double d = 3.3d;
        // closeTo：浮点型变量的值在3.0±0.5范围内，测试通过
        assertThat(d, closeTo(3.0, 0.5));
        // greaterThan：变量的值大于指定值时，测试通过
        assertThat(d, greaterThan(3.0));
        // lessThan：变量的值小于指定值时，测试通过
        assertThat(d, lessThan(3.5));
        // greaterThanOrEuqalTo：变量的值大于等于指定值时，测试通过
        assertThat(d, greaterThanOrEqualTo(3.3));
        // lessThanOrEqualTo：变量的值小于等于指定值时，测试通过
        assertThat(d, lessThanOrEqualTo(3.4));
    }

    /**
     * @Description: Text
     * equalToIgnoringCase
     * equalToIgnoringWhiteSpace
     * containsString, endsWith, startsWith
     */
    @Test
    public void hamcrestText() {
        String n = "Magci";
        // containsString：字符串变量中包含指定字符串时，测试通过
        assertThat(n, containsString("ci"));
        // startsWith：字符串变量以指定字符串开头时，测试通过
        assertThat(n, startsWith("Ma"));
        // endsWith：字符串变量以指定字符串结尾时，测试通过
        assertThat(n, endsWith("i"));
        // euqalTo：字符串变量等于指定字符串时，测试通过
        assertThat(n, equalTo("Magci"));
        // equalToIgnoringCase：字符串变量在忽略大小写的情况下等于指定字符串时，测试通过
        assertThat(n, equalToIgnoringCase("magci"));
        // equalToIgnoringWhiteSpace：字符串变量在忽略头尾任意空格的情况下等于指定字符串时，测试通过
        assertThat(n, equalToIgnoringWhiteSpace(" Magci   "));
    }
}
