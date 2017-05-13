package databinders;

import org.apache.commons.lang3.Range;
import play.data.format.Formatters;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RangeFormatter extends Formatters.SimpleFormatter<Range> {
    @Override
    public Range parse(String text, Locale locale) throws ParseException {
        Pattern pattern = Pattern.compile("^(\\d+),(\\d+)$");
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            Integer min = Integer.valueOf(matcher.group(1));
            Integer max = Integer.valueOf(matcher.group(2));
            if(min > max)
                throw new ParseException("Range is invalid.", 0);

            return Range.between(min, max);
        }
        throw new ParseException("Invalid value.", 0);
    }

    @Override
    public String print(Range range, Locale locale) {
        return range.toString("%1$s,%2$s");
    }
}
