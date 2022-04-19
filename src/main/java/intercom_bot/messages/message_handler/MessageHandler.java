package intercom_bot.messages.message_handler;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageHandler {
    void handle(TelegramLongPollingBot bot, Update update);
}
