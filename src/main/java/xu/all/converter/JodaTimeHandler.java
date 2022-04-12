package xu.all.converter;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;

import java.sql.*;

public class JodaTimeHandler implements TypeHandler<DateTime> {

    @Override
    public void setParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, new Timestamp(parameter.getMillis()));
    }

    @Override
    public DateTime getResult(ResultSet rs, String columnName) throws SQLException {
        return new DateTime(rs.getTimestamp(columnName).getTime());
    }

    @Override
    public DateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        return new DateTime(rs.getTimestamp(columnIndex).getTime());
    }

    @Override
    public DateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new DateTime(cs.getTimestamp(columnIndex).getTime());
    }
}
