package intercom_bot.messages.message_sender;

import intercom_bot.data.entities.Intercom;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.List;

public interface MessageSender {
    void sendText(TelegramLongPollingBot bot, long chatID, String message);

    void sendList(TelegramLongPollingBot bot, long chatID, List<Intercom> intercomList);
}
