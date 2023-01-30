package xu.all.frw.elasticJob;

import com.cxytiandi.elasticjob.annotation.ElasticJobConf;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import xu.all.utils.DateTimeUtil;

@Slf4j
@ElasticJobConf(name = "MySimpleJob", cron = "0/10 * * * * ?", shardingTotalCount = 2
        , shardingItemParameters = "0=shard0,1=shard1", description = "简单任务", eventTraceRdbDataSource = "dataSource")
public class MySimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("mySimpleJob 开始执行...");
        String shardingParameter = shardingContext.getShardingParameter();
        String dateTimeStr = DateTime.now().toString(DateTimeUtil.standardDateTimeFormatter);
        System.out.println("当前时间：" + dateTimeStr + "，分片参数：" + shardingParameter);
        log.info("mySimpleJob 执行结束");
    }
}
