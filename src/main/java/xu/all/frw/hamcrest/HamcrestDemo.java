package xu.all.frw.hamcrest;

import xu.all.frw.orika.pojo.UserDTO;
import org.junit.jupiter.api.Test;

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

    private UserDTO userDTO1 = new UserDTO(1, "张三", 20);
    private UserDTO userDTO2 = new UserDTO(1, "张三", 20);
    private UserDTO userDTO3 = new UserDTO(3, "李四", 30);
    private UserDTO userDTO4 = userDTO1;

    /**
     * @Description: Core
     * anything
     * describedAs
     * is：包装器，提高可读性，与equalTo无差
     */
    @Test
    public void hamcrestCore() {

        //anything任意都对
        assertThat(userDTO1, anything("反正写什么都是对的"));
        //describedAs错误文本
        assertThat(userDTO1, describedAs("失败了，毁灭吧，累了", is(userDTO2)));

    }

    /**
     * @Description: Logical
     * -allOf
     * -anyOf
     * -not
     */
    @Test
    public void hamcrestLogical() {

        //类似&&
        assertThat(userDTO1.getName(), allOf(is("张三"), not("李四")));
        //类似||
        assertThat(userDTO1.getName(), anyOf(is("张三"), is("李四")));

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

        assertThat(userDTO1, equalTo(userDTO2));
        //判断toString字符串
        assertThat(userDTO1, hasToString("UserDTO(id=1, name=张三, age=20, address=null, nameParts=null, namePartsMap=null, car=null)"));
        //是否哪个类
        assertThat(userDTO1, instanceOf(UserDTO.class));
        //不是null
        assertThat(userDTO1, notNullValue());
        //是null
        assertThat(userDTO1.getAddress(), nullValue());
        //判断是否为同一个对象
        assertThat(userDTO1, sameInstance(userDTO4));

    }

    /**
     * @Description: Beans
     * hasProperty
     */
    @Test
    public void hamcrestBeans() {

        //是否有指定属性
        assertThat(userDTO1, hasProperty("name"));

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
//        https:
//blog.csdn.net/fanxiaobin577328725/article/details/78407192
    }

    /**
     * @Description: Text
     * equalToIgnoringCase
     * equalToIgnoringWhiteSpace
     * containsString, endsWith, startsWith
     */
    @Test
    public void hamcrestText() {

    }
}
