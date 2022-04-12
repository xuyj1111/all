package xu.all.spring.batch.messageJob;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import xu.all.entity.jpa.Message;

import javax.persistence.EntityManagerFactory;
import java.io.File;

/**
 * @Description: message迁移任务配置
 * @Author: xuyujun
 * @Date: 2022/2/21
 */
public class MessageMigrationJobConfiguration {

    private static final Integer CHUNK_SIZE = 5;
    private static final String MESSAGE_FILE = "src/main/resources/file/message.txt";
    private static final Integer SKIP_LIMIT = 1;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManager;

    /**
     * @Description: 构建job
     */
    @Bean
    public Job messageMigrationJob(@Qualifier("messageMigrationStep") Step messageMigrationStep) {
        return jobBuilderFactory.get("messageMigrationJob")
                .flow(messageMigrationStep)
                .end()
                .build();
    }

    /**
     * @Description: 构建step
     */
    @Bean
    public Step messageMigrationStep(@Qualifier("jsonMessageReader") FlatFileItemReader<Message> jsonMessageReader,
                                     @Qualifier("messageItemWriter") JpaItemWriter<Message> messageItemWriter) {
        return stepBuilderFactory.get("messageMigrationStep")
                .<Message, Message>chunk(CHUNK_SIZE)
                .reader(jsonMessageReader).faultTolerant().skip(JsonParseException.class).skipLimit(SKIP_LIMIT)
                .listener(new MessageItemReadListener())
                .writer(messageItemWriter).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .listener(new MessageWriteListener())
                .build();
    }

    /**
     * @Description: 构建reader
     * FlatFileItemReader：从文件里面一行一行的读取数据
     * .setResource：设置文件路径
     * .setLineMapper：把一行文本映射为Message类，自定义类
     */
    @Bean
    public FlatFileItemReader<Message> jsonMessageReader() {
        FlatFileItemReader<Message> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(new File(MESSAGE_FILE)));
        reader.setLineMapper(new MessageLineMapper());
        return reader;
    }

    /**
     * @Description: 构建writer
     */
    @Bean
    public JpaItemWriter<Message> messageItemWriter() {
        JpaItemWriter<Message> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManager);
        return writer;
    }
}
