package xu.all.interfaces;

import javax.validation.GroupSequence;

/**
* @Description: 对@Validated注解的组进行排序
* @Author: xuyujun
* @Date: 2022/3/1 
*/
@GroupSequence({FirstGroupInterface.class, SecordGroupInterface.class})
public interface GroupInterface {
}
