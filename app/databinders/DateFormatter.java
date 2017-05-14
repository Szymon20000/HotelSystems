package databinders;

import play.data.format.Formatters;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter extends Formatters.SimpleFormatter<LocalDate> {
    private static String pattern = "dd/MM/yyyy";

    @Override
    public LocalDate parse(String input, Locale l) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, l);
        return LocalDate.parse(input, formatter);
    }

    @Override
    public String print(LocalDate localTime, Locale l) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, l);
        return localTime.format(formatter);
    }
}
