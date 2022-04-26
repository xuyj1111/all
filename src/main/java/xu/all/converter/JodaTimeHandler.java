package xu.all.converter;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.Objects;

public class JodaTimeHandler implements TypeHandler<DateTime> {
    @Override
    public void setParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, Objects.isNull(parameter) ? null : new Timestamp(parameter.getMillis()));
    }

    @Override
    public DateTime getResult(ResultSet rs, String columnName) throws SQLException {
        return toDateTime(rs.getTimestamp(columnName));
    }

    @Override
    public DateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        return toDateTime(rs.getTimestamp(columnIndex));
    }

    @Override
    public DateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toDateTime(cs.getTimestamp(columnIndex));
    }

    private static DateTime toDateTime(Timestamp timestamp) {
        if (Objects.isNull(timestamp)) {
            return null;
        }
        return new DateTime(timestamp.getTime());
    }
}
