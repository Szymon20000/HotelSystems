import databinders.DateFormatter;
import databinders.RangeFormatter;
import org.apache.commons.lang3.Range;
import play.data.format.Formatters;
import play.i18n.MessagesApi;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.time.LocalDate;


@Singleton
public class FormattersProvider implements Provider<Formatters> {
    private final MessagesApi messagesApi;

    @Inject
    public FormattersProvider(MessagesApi messagesApi) {
        this.messagesApi = messagesApi;
    }

    @Override
    public Formatters get() {
        Formatters formatters = new Formatters(messagesApi);

        formatters.register(LocalDate.class, new DateFormatter());
        formatters.register(Range.class, new RangeFormatter());

        return formatters;
    }
}
