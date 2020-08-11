package converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

// Date de java.sql
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
    // Para persistir
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return localDate == null? null : Date.valueOf(localDate);
    }

    // Para traer de DB
    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return date == null? null : date.toLocalDate();
    }
}
