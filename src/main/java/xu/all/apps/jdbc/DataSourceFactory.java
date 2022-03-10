package xu.all.apps.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
* @Description: 使用hikari连接池
* @Author: xuyujun
* @Date: 2022/3/8
*/
@Slf4j
public class DataSourceFactory {

    private static String USERNAME = "datasource.username";
    private static String PASSWARD = "datasource.password";
    private static String URL = "datasource.url";
    private final static Long CONNECTION_TIMEOUT = TimeUnit.SECONDS.toMillis(120L);
    private final static Long MAX_LIEFTIME = TimeUnit.MINUTES.toMillis(300L);
    private final static Long IDLE_TIMEOUT = TimeUnit.SECONDS.toMillis(120L);
    private final static Long VALIDATION_TIMEOUT = TimeUnit.SECONDS.toMillis(5L);
    private final static Long LEAK_DETECTION_THRESHOLD = TimeUnit.SECONDS.toMillis(10L);
    private final static Integer MAXIMUM_POOL_SIZE = 20;
    private final static Integer MINIMUM_IDLE_SIZE = 5;

    public static HikariDataSource buildDataSource() {
        Properties properties = new Properties();
        try {
            //读取类路径下的配置文件
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            log.error("系统异常", e);
        }
        HikariConfig config = defaultConfig();
        config.setUsername(properties.getProperty(USERNAME));
        config.setPassword(properties.getProperty(PASSWARD));
        config.setJdbcUrl(properties.getProperty(URL));
        return new HikariDataSource(config);
    }

    private static HikariConfig defaultConfig() {
        HikariConfig config = new HikariConfig();
//        config.setAutoCommit(false);
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
