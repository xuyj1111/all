package xu.all.frw.elasticJob;

import com.cxytiandi.elasticjob.annotation.ElasticJobConf;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Stack;

@Slf4j
@ElasticJobConf(name = "MyDataflowJob", cron = "0/15 * * * * ?", shardingTotalCount = 2
        , shardingItemParameters = "0=shard0,1=shard1", description = "数据流任务", eventTraceRdbDataSource = "dataSource")
public class MyDataflowJob implements DataflowJob<Object> {

    private static Stack stack = new Stack<>();
    static {
        stack.push(1);
        stack.push(2);
    }

    /**
     * @Description: 读取数据。若返回null则表示没数据 停止job，不然一直读取
     */
    @Override
    public List<Object> fetchData(ShardingContext shardingContext) {
        log.info("MyDataflowJob 读取数据");
        return stack.empty() ? null : Lists.newArrayList(stack.pop());
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Object> data) {
        log.info("MyDataflowJob 执行数据");
        String shardingParameter = shardingContext.getShardingParameter();
        System.out.println("数据：" + data + "，分片参数：" + shardingParameter);
        log.info("MyDataflowJob 执行结束");
    }
}
