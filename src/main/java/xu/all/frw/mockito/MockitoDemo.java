package xu.all.frw.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.List;

/**
 * @Description: 参考API：https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html
 * @Author: xuyujun
 * @Date: 2021/10/18
 */
public class MockitoDemo {

    @Test
    public void testMock() {
        List mockObj = Mockito.mock(List.class);
        Mockito.when(mockObj.get(0)).thenReturn("hello world");
        System.out.println(mockObj.get(0));     //输出：hello world

        Mockito.when(mockObj.get(0)).thenReturn("1").thenReturn("2").thenReturn("3");
        System.out.println(mockObj.get(0));     //输出：1
        System.out.println(mockObj.get(0));     //输出：2
        System.out.println(mockObj.get(0));     //输出：3

//        Mockito.when(mockObj.get(1)).thenThrow(new Exception());
//        System.out.println(mockObj.get(1));     //抛出异常

        mockObj.add("one");
        //是否调用方法
        Mockito.verify(mockObj).add("one");
        //方法调用的次数
        Mockito.verify(mockObj, Mockito.times(1)).add("one");
        //是否从未调用方法
        Mockito.verify(mockObj, Mockito.never()).add("two");
        //最少调用 1 次
        Mockito.verify(mockObj, Mockito.atLeast(1)).add("one");
        //最少调用 1 次
        Mockito.verify(mockObj, Mockito.atLeastOnce()).add("one");
        //最多调用 5 次
        Mockito.verify(mockObj, Mockito.atMost(5)).add("one");


        mockObj.add("two");
        InOrder inOrder = Mockito.inOrder(mockObj);
        //单个 mock 对象的执行顺序校验
        inOrder.verify(mockObj).add("one");
        inOrder.verify(mockObj).add("two");

        List mockObj2 = Mockito.mock(List.class);
        mockObj2.add(111);
        InOrder inOrder2 = Mockito.inOrder(mockObj, mockObj2);
        //多个 mock 对象的执行顺序校验
        inOrder2.verify(mockObj).add("two");
        inOrder2.verify(mockObj2).add(111);

        // 验证单个或多个 mock 没有调用过方法
        List mockObj3 = Mockito.mock(List.class);
        List mockObj4 = Mockito.mock(List.class);
        Mockito.verifyZeroInteractions(mockObj3, mockObj4);
    }
}
