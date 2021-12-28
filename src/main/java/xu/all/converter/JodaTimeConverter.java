package xu.all.converter;

import org.joda.time.DateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

@Converter
public class JodaTimeConverter implements AttributeConverter<DateTime, Date> {
    private static final int TIME_ZONE_OFFSET_MINUTES = -480;

    public JodaTimeConverter() {
    }

    @Override
    public Date convertToDatabaseColumn(DateTime attribute) {
        return attribute == null ? null : attribute.toDate();
    }

    @Override
    public DateTime convertToEntityAttribute(Date dbData) {
        if (dbData == null) {
            return null;
        } else {
            long timestamp = dbData.getTime() + (long) ((-480 - dbData.getTimezoneOffset()) * 60 * 1000);
            return new DateTime(timestamp);
        }
    }
}
