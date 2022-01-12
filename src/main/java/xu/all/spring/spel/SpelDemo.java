package xu.all.spring.spel;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/** 
* @Description: https://zhuanlan.zhihu.com/p/174786047
* @Author: xuyujun
* @Date: 2021/12/6 
*/ 
public class SpelDemo {

    private static final ExpressionParser parser = new SpelExpressionParser();

    private Expression expression;

    private DemoDTO dto;

    /**
     * @Description: 字面量表达式
     */
    @Test
    public void literalQuantityExpression() {
        expression = parser.parseExpression("'hello world'");
        System.out.println(expression.getValue(String.class));

        expression = parser.parseExpression("1");
        System.out.println(expression.getValue(Integer.class));

        expression = parser.parseExpression("-1L");
        System.out.println(expression.getValue(Long.class));

        expression = parser.parseExpression("1.1");
        System.out.println(expression.getValue(Float.class));

        expression = parser.parseExpression("1.1E+2");
        System.out.println(expression.getValue(Double.class));

        expression = parser.parseExpression("0xa");
        System.out.println(expression.getValue(Integer.class));

        expression = parser.parseExpression("0xaL");
        System.out.println(expression.getValue(Long.class));

        expression = parser.parseExpression("true");
        System.out.println(expression.getValue(Boolean.class));

        expression = parser.parseExpression("null");
        System.out.println(expression.getValue(Object.class));
    }

    /**
     * @Description: 算数表达式
     * 等于（==）、不等于(!=)、大于(>)、大于等于(>=)、小于(<)、小于等于(<=)，区间（between）运算
     * “EQ” 、“NE”、 “GT”、“GE”、 “LT” 、“LE”来表示等于、不等于、大于、大于等于、小于、小于等于，不区分大小写
     */
    @Test
    public void arithmeticExpression() {
        expression = parser.parseExpression("1+2");
        System.out.println(expression.getValue(Integer.class));

        expression = parser.parseExpression("1>2");
        System.out.println(expression.getValue(Boolean.class));

        expression = parser.parseExpression("1 between {1,2}");
        System.out.println(expression.getValue(Boolean.class));

        expression = parser.parseExpression("1 EQ 2");
        System.out.println(expression.getValue(Boolean.class));
    }

    /**
     * @Description: 逻辑表达式
     * 且（and或者&&）、或(or或者||)、非(!或NOT)
     */
    @Test
    public void logicExpression() {
        expression = parser.parseExpression("2>1 and (!true or !false)");
        System.out.println(expression.getValue(Boolean.class));

        expression = parser.parseExpression("2>1 && (NOT true || NOT false)");
        System.out.println(expression.getValue(Boolean.class));
    }

    /**
     * @Description: 其他表达式
     * ps：括号具有高优先级
     */
    @Test
    public void otherExpression() {
        //字符串截图单个字符
        expression = parser.parseExpression("'Hello World!'[0]");
        System.out.println(expression.getValue(String.class));
        //字符串连接
        expression = parser.parseExpression("'Hello' + 'World!'");
        System.out.println(expression.getValue(String.class));
        //三目表达式
        expression = parser.parseExpression("2 > 1 ? true : false");
        System.out.println(expression.getValue(Boolean.class));
        //正则表达式
        //不会
    }

    /**
     * @Description: 类类型表达式
     */
    @Test
    public void clazzTypeExpression() {
        //java.lang不用全限名
        expression = parser.parseExpression("T(String)");
        System.out.println(expression.getValue(Class.class));
        //其他包下的类
        expression = parser.parseExpression("T(DemoDTO)");
        System.out.println(expression.getValue(Class.class) == DemoDTO.class);
        //类静态字段访问
        expression = parser.parseExpression("T(Integer).MAX_VALUE");
        System.out.println(expression.getValue(int.class) == Integer.MAX_VALUE);
        //类静态方法调用
        expression = parser.parseExpression("T(Integer).parseInt('1')");
        System.out.println(expression.getValue(int.class));
        //instance表达式
        expression = parser.parseExpression("'路人甲' instanceof T(String)");
        System.out.println(expression.getValue(Boolean.class));
    }

    /**
     * @Description: 类实例化
     */
    @Test
    public void clazzInstanceExpression() {
        //java.lang不用全限名
        expression = parser.parseExpression("new String('hello java')");
        System.out.println(expression.getValue(String.class));
        //其他包下的类
        expression = parser.parseExpression("new DemoDTO()");
        System.out.println(expression.getValue(DemoDTO.class));
    }

    /**
     * @Description: 变量定义及引用
     * 使用StandardEvaluationContext
     */
    @Test
    public void variableExpression() {
        StandardEvaluationContext context = new StandardEvaluationContext("root");
        context.setVariable("name", "路人甲java");
        context.setVariable("lesson", "Spring系列");

        String name = parser.parseExpression("#name").getValue(context, String.class);
        System.out.println(name);
        String lesson = parser.parseExpression("#lesson").getValue(context, String.class);
        System.out.println(lesson);
        //引用根对象
        String rootObj = parser.parseExpression("#root").getValue(context, String.class);
        System.out.println(rootObj);
        //引用上下文对象
        String thisObj = parser.parseExpression("#this").getValue(context, String.class);
        System.out.println(thisObj);
    }

    /**
     * @Description: 自定义函数
     * 使用StandardEvaluationContext，registerFunction方法进行注册自定义函数，其实完全可以使用setVariable代替，两者其实本质是一样的
     */
    @Test
    public void functionExpression() throws NoSuchMethodException {
        StandardEvaluationContext context = new StandardEvaluationContext();
        Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
        context.registerFunction("parseInt1", parseInt);
        context.setVariable("parseInt2", parseInt);

        expression = parser.parseExpression("#parseInt1('3')");
        System.out.println(expression.getValue(context, Integer.class));
        expression = parser.parseExpression("#parseInt2('4')");
        System.out.println(expression.getValue(context, Integer.class));
    }
}
