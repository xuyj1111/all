package xu.all.service.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JdbcService {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String getName(Long id) {
        String sql = "select name from test where id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbcTemplate.queryForObject(sql, params, String.class);
    }


}
