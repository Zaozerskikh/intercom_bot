package intercom_bot.messages.message_parser;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageParser {
    String[] parseMessage(Update update);
}
