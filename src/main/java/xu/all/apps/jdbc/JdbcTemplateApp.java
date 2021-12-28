package xu.all.apps.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

public class JdbcTemplateApp {

    public static void main(String[] args) {
        HikariDataSource dataSource = DataSourceFactory.buildDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = "select name from test where id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1L);

        String result = jdbcTemplate.queryForObject(sql, params, String.class);
        System.out.println("The value of sql is [" + result + "]");
        dataSource.close();
    }
}
