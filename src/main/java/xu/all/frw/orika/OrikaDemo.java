package xu.all.frw.orika;

import xu.all.frw.orika.pojo.Car;
import xu.all.frw.orika.pojo.Element;
import xu.all.frw.orika.pojo.UserDO;
import xu.all.frw.orika.pojo.UserDTO;
import com.google.common.collect.Lists;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.TypeFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 参考API：http://orika-mapper.github.io/orika-docs/index.html
 * @Author: xuyujun
 * @Date: 2021/6/1
 */
public class OrikaDemo {

    /**
     * @Description: OrikaBase
     * field(field01，field02)，field01和field02相互映射。
     * exclude(field)，排除指定field不映射，要写在byDefault方法之前。
     * byDefault()，其他field同名则相互映射（区分大小写）。
     */
    @Test
    public void orikaBase() {
        UserDTO userDTO = new UserDTO(1, "张三", 20, "南京");
        //创建一个MapperFactory实例，作为建造者
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        //两个类之间属性名不同，通过field映射，若相同字段名也映射，则使用byDefault
        factory.classMap(UserDTO.class, UserDO.class)
                .field("name", "userName")
                .field("age", "userAge")
                .exclude("address")
                .byDefault()
                .register();
        //1、实例化userDO，并将userDTO属性值映射到上面
        UserDO userDO = factory.getMapperFacade().map(userDTO, UserDO.class);
        //2、第二种方式，也可绑定类型，比上一种能提供更好的性能
//        UserDO userDO = factory.getMapperFacade(UserDTO.class, UserDO.class).map(userDTO);
        System.out.println(userDO.toString());
    }

    /**
     * @Description: field单向双向映射 fieldAToB(field01 ， field02)，field01往field02单向映射
     * fieldBToA(field01，field02)，相反
     */
    @Test
    public void orikaField01() {
        UserDTO userDTO = new UserDTO(1, "张三", 20);
        MapperFactory factory = new DefaultMapperFactory.Builder().build();

//**************************************BEGIN**************************************
        UserDO userDO = new UserDO(2, "李四", 30);
        //1、field是双向映射
//        factory.classMap(UserDTO.class, UserDO.class)
//                .field("name", "userName")
//                .field("age", "userAge")
//                .byDefault()
//                .register();
        //userDTO映射到userDO上，成功！
//        factory.getMapperFacade().map(userDTO, userDO);
        //或userDO映射到userDTO上，成功！
//        factory.getMapperFacade().map(userDO, userDTO);

        //2、fieldAToB或fieldBToA单向映射
        factory.classMap(UserDTO.class, UserDO.class)
                .fieldAToB("name", "userName")
                .fieldAToB("age", "userAge")
                .byDefault()
                .register();
        //userDTO映射到userDO上，成功！
        factory.getMapperFacade().map(userDTO, userDO);
        //userDO映射到userDTO上，失败！
//        factory.getMapperFacade().map(userDO, userDTO);
//**************************************END**************************************

        System.out.println(userDTO.toString());
        System.out.println(userDO.toString());
    }

    /**
     * @Description:field通过数组形式获取list中数据
     */
    @Test
    public void orikaField02() {
        ArrayList<String> list = new ArrayList<>();
        list.add("ZhangSan");
        list.add("LiSi");
        UserDTO userDTO = new UserDTO(1, "张三", 20, list);
        MapperFactory factory = new DefaultMapperFactory.Builder().build();

//**************************************BEGIN**************************************
        factory.classMap(UserDTO.class, UserDO.class)
                .field("nameParts[0]", "firstName")
                .field("nameParts[1]", "lastName")
                .register();
//**************************************END**************************************

        UserDO userDO = factory.getMapperFacade().map(userDTO, UserDO.class);
        System.out.println(userDO.toString());
    }

    /**
     * @Description:field通过单引号或双引号键值，获取map中数据
     */
    @Test
    public void orikaField03() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name1", "ZhangSan");
        map.put("name2", "LiSi");
        UserDTO userDTO = new UserDTO(1, "张三", 20, map);
        MapperFactory factory = new DefaultMapperFactory.Builder().build();

//**************************************BEGIN**************************************
        factory.classMap(UserDTO.class, UserDO.class)
                .field("namePartsMap['name1']", "firstName")
                .field("namePartsMap[\"name2\"]", "lastName")
                .register();
//**************************************END**************************************

        UserDO userDO = factory.getMapperFacade().map(userDTO, UserDO.class);
        System.out.println(userDO.toString());
    }

    /**
     * @Description:field通过.获取属性对象的属性
     */
    @Test
    public void orikaField04() {
        UserDTO userDTO = new UserDTO(1, "张三", 20, "南京", new Car("3系", "BMW"));
        MapperFactory factory = new DefaultMapperFactory.Builder().build();

//**************************************BEGIN**************************************
        factory.classMap(UserDTO.class, UserDO.class)
                .field("car.name", "carName")
                .field("car.brand", "carBrand")
                .register();
//**************************************END**************************************

        UserDO userDO = factory.getMapperFacade().map(userDTO, UserDO.class);
        System.out.println(userDO.toString());
    }

    /**
     * @Description: 映射时null值的处理，class级别
     * mapNulls(true|false)
     * mapNullsInReverse(true|false)
     * <p>
     * 第一个设置true，表示”name“ ”userName“ null值时也能相互映射；
     * 第二个设置false，表示”age“ ”userAge“ 及其他所有字段 null值时不能能相互映射；
     */
    @Test
    public void orikaClassNull() {
        UserDTO userDTO = new UserDTO(1, null, null, (String) null);
        UserDO userDO = new UserDO(2, "张三", 30, "北京");
        MapperFactory factory = new DefaultMapperFactory.Builder().build();

//**************************************BEGIN**************************************
        factory.classMap(UserDTO.class, UserDO.class)
                .mapNulls(true).mapNullsInReverse(true)
                .field("name", "userName")
                .mapNulls(false).mapNullsInReverse(false)
                .field("age", "userAge")
                .byDefault()
                .register();
//**************************************END**************************************

        factory.getMapperFacade().map(userDTO, userDO);
        System.out.println(userDO.toString());
    }

    /**
     * @Description: 映射时null值的处理，field级别
     * 方法同上，区别为只修改指定field
     */
    @Test
    public void orikaFieldNull() {
        UserDTO userDTO = new UserDTO(1, null, null, (String) null);
        UserDO userDO = new UserDO(2, "张三", 30, "北京");
        MapperFactory factory = new DefaultMapperFactory.Builder().build();

//**************************************BEGIN**************************************
        factory.classMap(UserDTO.class, UserDO.class)
                .fieldMap("name", "userName").mapNulls(false).mapNullsInReverse(false).add()
                .field("age", "userAge")
                .byDefault()
                .register();
//**************************************END**************************************

        factory.getMapperFacade().map(userDTO, userDO);
        System.out.println(userDO.toString());
    }

    /**
     * @Description: 自定义方法
     * customize中参数重写方法，以下代码实现了firstName和lastName的赋值
     */
    @Test
    public void orikaCustomize() {
        UserDTO userDTO = new UserDTO(1, "李四", 20, "南京");
        UserDO userDO = new UserDO(2, "张三", 30, "北京");
        MapperFactory factory = new DefaultMapperFactory.Builder().build();

//**************************************BEGIN**************************************
        factory.classMap(UserDTO.class, UserDO.class)
                .field("name", "userName")
                .field("age", "userAge")
                .customize(new CustomMapper<UserDTO, UserDO>() {
                    @Override
                    public void mapAtoB(UserDTO userDTO, UserDO userDO, MappingContext context) {
                        userDO.setFirstName(userDTO.getName().substring(0, 1));
                        userDO.setLastName(userDTO.getName().substring(1));
                    }
                })
                .byDefault()
                .register();
//**************************************END**************************************

        factory.getMapperFacade().map(userDTO, userDO);
        System.out.println(userDO.toString());
    }

    /**
     * @Description: 内联属性映射
     * （内联：告诉编译器将指定的函数体插入并替代每一处调用该函数的地方，从而减少调用函数需要花费的固有时间）
     * 例：
     * 1.Element类中map存Car对象，内联字符串后跟“.属性名”，而不是”.getXxx()“
     * 2.Element类中map存Element对象，Element对象的map存name、age，嵌套时可用”.“；可使用名字来调用已定义的内联属性
     * 内联属性格式：
     * «name» :{ «getter» | «setter» [ | type= «type» ] }
     * «name» :{ «getter» [ | type= «type» ] }
     * «name» :{| «setter» [ | type= «type» ] }
     */
    @Test
    public void orikaInLineProperty() {
        String car = "car:{getAttributes('car')|setAttributes('car', %s)|type=xu.all.frw.orika.pojo.Car}";
        String element = "element:{getAttributes('element')|setAttributes('element', %s)|type=xu.all.frw.orika.pojo.Element}";
        String name = "name:{getAttributes('name')|setAttributes('name', %s)|type=java.lang.String}";
        String age = "age:{getAttributes('age')|setAttributes('age', %s)|type=java.lang.Integer}";

//**************************************1**************************************
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Element.class, UserDO.class)
                .field(car + ".name", "carName")
                .register();
        Element elementObj = new Element();
        elementObj.setAttributes("car", new Car("TT", "Audi"));
        UserDO userDO = factory.getMapperFacade().map(elementObj, UserDO.class);
        System.out.println(userDO.toString());

//**************************************2**************************************
        factory.classMap(Element.class, UserDTO.class)
                .field(element + "." + name, "name")
                .field("element." + age, "age")
                .register();
        elementObj.setAttributes("element", new Element().setAttributes("name", "张三").setAttributes("age", 20));
        UserDTO userDTO = factory.getMapperFacade().map(elementObj, UserDTO.class);
        System.out.println(userDTO.toString());
    }

    /**
     * @Description: mapAsList
     */
    @Test
    public void orikaMapAsList() {
        UserDTO userDTO1 = new UserDTO(1, "张三", 20, "南京");
        UserDTO userDTO2 = new UserDTO(2, "李四", 30, "北京");
        UserDTO userDTO3 = new UserDTO(3, "王五", 40, "上海");
        ArrayList<UserDTO> userDTOS = Lists.newArrayList(userDTO1, userDTO2, userDTO3);

        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(UserDTO.class, UserDO.class)
                .field("name", "userName")
                .field("age", "userAge")
                .exclude("address")
                .byDefault()
                .register();
//**************************************BEGIN**************************************
        List<UserDO> userDOS = factory.getMapperFacade().mapAsList(userDTOS
                , TypeFactory.valueOf(UserDTO.class), TypeFactory.valueOf(UserDO.class));
        userDOS.stream().forEach(userDO -> System.out.println(userDO));
//**************************************END**************************************
    }
}