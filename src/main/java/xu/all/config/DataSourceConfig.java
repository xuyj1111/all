package xu.all.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Value("${activiti.dataSource.url}")
    private String databaseUrl;

    @Value("${spring.dataSource.username}")
    private String databaseUsername;

    @Value("${spring.dataSource.password}")
    private String databasePassword;

    private final static Long CONNECTION_TIMEOUT = TimeUnit.SECONDS.toMillis(120L);

    private final static Long MAX_LIEFTIME = TimeUnit.MINUTES.toMillis(300L);

    private final static Long IDLE_TIMEOUT = TimeUnit.SECONDS.toMillis(120L);

    private final static Long VALIDATION_TIMEOUT = TimeUnit.SECONDS.toMillis(5L);

    private final static Long LEAK_DETECTION_THRESHOLD = TimeUnit.SECONDS.toMillis(10L);

    private final static Integer MAXIMUM_POOL_SIZE = 20;

    private final static Integer MINIMUM_IDLE_SIZE = 5;

    @Bean
    public DataSource dataSource() {
        log.info("Database connection string is: {}", databaseUrl);
        HikariConfig config = defaultConfig();
        config.setJdbcUrl(databaseUrl);
        config.setUsername(databaseUsername);
        config.setPassword(databasePassword);
        return new HikariDataSource(config);
    }

    private static HikariConfig defaultConfig() {
        HikariConfig config = new HikariConfig();
        config.setAllowPoolSuspension(false);
        config.setConnectionTestQuery("/* ping */ SELECT 1");
        config.setConnectionInitSql("SELECT 1");
        config.setConnectionTimeout(CONNECTION_TIMEOUT);
        config.setValidationTimeout(VALIDATION_TIMEOUT);
        config.setMaxLifetime(MAX_LIEFTIME);
        config.setIdleTimeout(IDLE_TIMEOUT);
        config.setMinimumIdle(MINIMUM_IDLE_SIZE);
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setLeakDetectionThreshold(LEAK_DETECTION_THRESHOLD);
        config.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
        config.setPoolName("MySQLDatabasePool");
        config.setReadOnly(false);
        config.setThreadFactory((r) -> new Thread(r, "MySQLDatabasePool"));
        config.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", 250);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        config.addDataSourceProperty("useServerPrepStmts", true);
        config.addDataSourceProperty("useLocalSessionState", true);
        config.addDataSourceProperty("useLocalTransactionState", true);
        config.addDataSourceProperty("rewriteBatchedStatements", true);
        config.addDataSourceProperty("cacheResultSetMetadata", true);
        config.addDataSourceProperty("cacheServerConfiguration", true);
        config.addDataSourceProperty("elideSetAutoCommits", true);
        config.addDataSourceProperty("maintainTimeStats", false);
        return config;
    }
}
