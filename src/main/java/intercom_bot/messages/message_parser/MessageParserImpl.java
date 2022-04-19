package intercom_bot.messages.message_parser;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;

/**
 * Bean that parses user messages.
 */
@Component
@Scope("prototype")
@EnableAspectJAutoProxy
public class MessageParserImpl implements MessageParser {
    @Override
    public String[] parseMessage(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String[] req = update.getMessage().getText().split(";");
            if (req.length != 2 || Arrays.asList(req).contains("")) {
                throw new RuntimeException("Некорректный запрос");
            }
            return req;
        } else throw new RuntimeException("Неизвестная команда");
    }
}
