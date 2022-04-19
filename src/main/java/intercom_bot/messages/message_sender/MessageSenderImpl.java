package intercom_bot.messages.message_sender;

import intercom_bot.data.entities.Intercom;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * Bean that sends messages to user.
 */
@Component
@Scope("prototype")
@EnableAspectJAutoProxy
public class MessageSenderImpl implements MessageSender {
    /**
     * Sends simple text message to user.
     * @param bot telegram bot object.
     * @param chatID user chat id.
     * @param message message.
     */
    @Override
    public void sendText(TelegramLongPollingBot bot, long chatID, String message) {
        send(bot, chatID, message);
    }

    /**
     * Send list of finded intercoms to user.
     * @param bot telegram bot object.
     * @param chatID user chat id.
     * @param intercomList list of finded intercoms.
     */
    @Override
    public void sendList(TelegramLongPollingBot bot, long chatID, List<Intercom> intercomList) {
        if (intercomList.size() > 20) {
            sendText(bot, chatID, "Слишком много результатов. Уточните запрос.");
        } else if (intercomList.size() == 0) {
            sendText(bot, chatID, "Ничего не найдено. Уточните запрос.");
        } else {
            StringBuilder msgBuilder = new StringBuilder();
            intercomList.forEach(intercom -> msgBuilder
                    .append("Адрес: ").append(intercom.getHouseAddress())
                    .append("\nПодъезд: ").append(intercom.getEntranceNumber())
                    .append("\nКоды:\n").append(intercom.getFirstCode())
                    .append("\n").append(intercom.getSecondCode()).append("\n"));
            send(bot, chatID, msgBuilder.toString());
        }
    }

    private void send(TelegramLongPollingBot bot, long chatID, String message) {
        SendMessage messageRequest = new SendMessage();
        messageRequest.setChatId(String.valueOf(chatID));
        messageRequest.setText(message);
        try {
            bot.execute(messageRequest);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
